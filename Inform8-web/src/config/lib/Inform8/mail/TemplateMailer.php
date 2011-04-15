<?php
/**
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */


class FromName {

  private $from;
  private $fromName;

  function __construct($from, $fromName) {
    $this->from = $from;
    $this->fromName = $fromName;
  }

}

/**
 * Mailer to send out emails created by merging objects with a template and by adding attachments. 
 *  
 * @author ryanhenderson
 *
 */
class TemplateMailer {

  /**
   * The list of To addresses.
   * @var array(String) email address to send to
   */
  private $to = array();

  private $theObject; // the primary object for the template
  
  private $mapObjects = array(); // accessory Objects, variables etc

  /**
   * @var int the template primary key value for lookup.
   */
  private $templateId;

  /**
   * Settings for sending the email
   * @var MailSettings
   */
  private $settings;

  
  function __construct() {
    $this->settings = StaticConfig::getEmailSettings(); 
  }

  /**
   * 
   * Add an object that is visible during the template merge.
   * @param String $key
   * @param mixed $object
   */
  function addObject($key, $object) {
    $this->mapObjects[$key] = $object;
  }

  /**
   * Add the email address to the senders list. Each email address is sent to individually. 
   * @param String $to
   */
  function addTo($to) {
    $this->to[] = $to;
  }

  function setTemplateId($id) {
    $this->templateId = $id;
  }

  function send() {
    $templateRes = $this->parseDbTemplate($this->templateId);

    $templates = IQL::select(EmailTemplateIQL::$_TABLE)->where(null, EmailTemplateIQL::$TEMPLATEID, '=', $this->templateId)->get();
    $template = $templates[0];

    // get template files to add to the email
    $templateFiles = IQL::select(TemplateFileIQL::$_TABLE)
    ->where(null, TemplateFileIQL::$TEMPLATEID, '=', $template->getPk())->get();

    $storageSettingsDao = new JackStorageDao();
    $allSettings = $storageSettingsDao->getAll();
    $storageSettings = $allSettings[0];

    $storageFolder = $storageSettings->getStorageFolder();

    $errors = array();

    foreach ($this->to as $sendTo) {
      $mail = new PHPMailer(); // defaults to using php "mail()"
      $mail->From = $this->getSettings()->getFromEmail();
      $mail->FromName = $this->getSettings()->getFromName();

      $mail->Subject = $templateRes['subject'];
      $mail->AddAddress($sendTo);
      $mail->MsgHTML($templateRes['html']);
      $mail->AltBody = $templateRes['text'];
      
      if ($this->getSettings()->useSMTP()) {
        $mail->IsSMTP(); // telling the class to use SMTP
        $mail->SMTPDebug  = 0;                     // enables SMTP debug information (for testing)
        $mail->Host       = $this->getSettings()->getSmtpServer();
        $mail->Port       = $this->getSettings()->getSmtpPort();  

        if ($this->getSettings()->isSmtpAuthReqd()) {
          $mail->SMTPAuth   = true;                  // enable SMTP authentication
          $mail->Username   = $this->getSettings()->getSmtpUsername(); // SMTP account username
          $mail->Password   = $this->getSettings()->getSmtpPassword();        // SMTP account password
        }
      }else {
        $mail->IsMail();
      }
      
      if (isset($templateFiles) && is_array($templateFiles)) {
        foreach ($templateFiles as $afile) {
          $mail->AddEmbeddedImage(StaticConfig::getStorageLocation() . '/' .  $afile->getFileName(), $afile->getName());
        }
      }

      if (!$mail->Send()) {
        $errors[] = 'Failed to send email to: ' . $sendTo;
      }
      Inform8Context::getLogger()->log(BaseLogger::$DEBUG, 'Sent email to: ' . $sendTo);
      set_time_limit(30);
    }
    return $errors;
  }


  private function parseDbTemplate($id) {
    // check template exists
    $templates = IQL::select(EmailTemplateIQL::$_TABLE)
    ->where(null, EmailTemplateIQL::$TEMPLATEID, '=', $id)
    ->get();
    $template = $templates[0];

    $v = array();
    $v = array_merge($v, $this->mapObjects);

    // load generic variables
    $genericVars = IQL::select(VariableIQL::$_TABLE)->where(null, VariableIQL::$ENABLED, '=', 1)->get();
    if (is_array($genericVars)) {
      foreach ($genericVars as $gv) {
        $v[$gv->getName()] = $gv->getValue();
      }
    }

    // load template specific variables - may overwrite generic.
    $templateVars = IQL::select(VariableIQL::$_TABLE)->where(null, VariableIQL::$ENABLED, '=', 1)->_and(null, VariableIQL::$TEMPLATEID, '=', $template->getPk());
    foreach ($templateVars as $tv) {
      $v[$tv->getName()] = $tv->getValue();
    }

    $v['siteConfig'] = $this->getSiteConfig();
    $v['emailSettings'] = $this->getSettings();

    ob_start();
    // using the termination so it's treated it like an include file....
    // see the eval for more docs...
    eval('?>' . $template->getHtmlTemplate());
    $htmlContents = ob_get_contents();
    ob_end_clean();

    ob_start();
    // using the termination so it's treated it like an include file....
    // see the eval for more docs...
    eval('?>' . $template->getTextTemplate());
    $textContents = ob_get_contents();
    ob_end_clean();

    ob_start();
    // using the termination so it's treated it like an include file....
    // see the eval for more docs...
    eval('?>' . $template->getSubjectTemplate());
    $subjectContents = ob_get_contents();
    ob_end_clean();

    return array('text'=>$textContents, 'html'=>$htmlContents, 'subject'=>$subjectContents);
  }
  
  function loadVariables() {
    
  }
  
  

  /**
   * @return MailSettings the settings
   */
  public function getSettings() {
    return $this->settings;
  }

  /**
   * Set the new email settings
   * @param MailSettings the email settings
   */
  public function setSettings(MailSettings $settings) {
    $this->settings = $settings;
  }
  
  private function getSiteConfig() {
    $dao = new SiteConfigurationDao();
    $configs = $dao->getAll();
    $config = $configs[0];
    return $config;
  }

}
?>