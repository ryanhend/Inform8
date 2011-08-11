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

  // TODO Database Checks
  $reqobj = Request::getSafeGetOrPost("obj");
  $action = Request::getSafeGetOrPost("act");
  
  if($action == 'help') {
    include ('config/authenticatedajax/help.php');
  }else if($action == 'home') {
    include ('config/authenticatedajax/home.php');
  }else if($action == 'calendar') {
    include ('config/authenticatedajax/calendar.php');
  }else {
    require ('config/authenticatedajax/' . $reqobj . $action . '.php');
  }
  
?>