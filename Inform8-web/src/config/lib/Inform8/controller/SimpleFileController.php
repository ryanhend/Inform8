<?php 

class SimpleFileController extends BaseController {
  
  /** The page to load. */
  private $page;
  
  /**
   * (non-PHPdoc)
   * @see Controller::run()
   */
  public function runImpl() {
    $this->page = $this->getPage(); 
    
    if ($this->isAuthenticatedPage() && !$this->isAuthenticated()) {
      $this->redirectToLogin();
    }
    
    $this->loadProcessor();
    $this->loadForms();
    $this->loadPage();
    
    // displays the resultant page using the desired page template (templates/default.php if not set)
    include 'config/templates/' . Response::getInstance()->getPageTemplate();
  }

  /**
   *  
   * @return boolean true if the page requires an authenticated user.
   */
  function isAuthenticatedPage() {
    return file_exists('config/authenticatedpages/' . $this->page . '.page.php');
  }
  
  
  /**
   * 
   *
   */
  function isAuthenticated() {
    return Session::getInstance()->getAuthenticationManager()->isAuthenticated();
  }
  
  
  /**
   * Load any page forms.
   */
  function loadForms() {
    if($this->isAuthenticatedPage()) {
      $formFile = file_exists('config/authenticatedpages/' . $this->page.'.form.php');
      if ($formFile) {
        include 'config/authenticatedpages/' . $this->page . '.form.php';
      }
    }else {
      $formFile = file_exists('config/pages/' . $this->page.'.form.php');
      if ($formFile) {
        include 'config/pages/' . $this->page . '.form.php';
      }      
    }
  }
  
  
  /**
   * 
   * 
   */
  function loadProcessor() {
    if($this->isAuthenticatedPage()) {
      $processingFile = file_exists('config/authenticatedpages/' . $this->page.'.process.php');
      if($processingFile) {
        ob_start();
        include 'config/authenticatedpages/' . $this->page. '.process.php';
        $processingContent = ob_get_contents();
        ob_end_clean();
      }
    }else {
      $pfile = 'config/pages/' . $this->page . '.process.php';
      $processingFile = file_exists($pfile);
      Inform8Context::getLogger()->log($ALL, 'Processing File: ' . $pfile . ', Exists: ' . $processingFile);
      if($processingFile) {
        ob_start();
        include $pfile;
        $processingContent = ob_get_contents();
        ob_end_clean();
      }     
    }
  }
  
  
  /**
   * loads the page using file based resources.
   */  
  protected function loadPage() {
    if($this->isAuthenticatedPage()) {
      $thePage = 'config/authenticatedpages/' . $this->page . '.page.php';
      ob_start();
      include $thePage;
      $content = ob_get_contents();
      ob_end_clean();
      Response::getInstance()->set('content', $content);
    }else {
      $thePage = 'config/pages/' . $this->page . '.page.php';
      ob_start();
      include $thePage;
      $content = ob_get_contents();
      ob_end_clean();
      Response::getInstance()->set('content', $content);    
    } 
  }
  
    
  /**
   * Redirects to the login page.
   */
  protected function redirectToLogin() {
    $this->redirect("/login.htm?postcmd=" . $this->page);
  }  
  
} 
?>