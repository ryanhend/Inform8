<?php 
/* Copyright 2011 - 88 Creative Pty Ltd. 
 * Copyright of this program is the property of 88 Creative, 
 * without whose written permission reproduction in
 * whole or in part is prohibited. All rights reserved.
 * http://www.inform8.com
 * http://www.88creative.com.au
 */
?><?php
  // site settings + config + seession load
  require_once 'config/settings.php';

  $auth = Session::getInstance()->getAuthenticationManager()->isAuthenticated();
  if (!$auth) {
      die();
  }
    
  $reqobj = Request::getSafeGetOrPost("obj");
  $action = Request::getSafeGetOrPost("act");
  
  require ('config/authenticatedajax/crud/' . $reqobj . 'Crud' . '.php');
  $crudName = $reqobj . 'Crud';
  $crud = new $crudName(WebContext::getLanguage());
  $res = $crud->$action();
  
  $wrapWithTa = Request::getSafeGetOrPost("wta");
  if ($wrapWithTa == 1) {
    echo '<textarea>' . json_encode($res) . '</textarea>';
  }else{
    echo json_encode($res);
  }
  
?>