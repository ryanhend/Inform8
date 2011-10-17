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
      die('Error - 1000');
  }

  $call = Request::getSafeGetOrPost("call");
  $pkg = Request::getSafeGetOrPost("pkg");
  
  if( $pkg == '' || ctype_alpha($pkg) && ctype_alpha($call)) {
    if($pkg == '') {
      include 'config/authenticatedajax/' . $call. '.php';
    }else {
      include 'config/authenticatedajax/' . $pkg . '/' . $call. '.php';
    }
  }else {
    die('Error - 2000');
  }
  
  
?>