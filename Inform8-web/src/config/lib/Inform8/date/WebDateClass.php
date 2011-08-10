<?php
/**
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */

class WebDate {

  public static $SECONDS_IN_A_DAY = 86400; 
  
  public static function newMysqlDate() {
    return date("Y-m-d");
  }

  public static function fromMysqlDate($date) {
    return strtotime($date);
  }

  public static function newMysqlDateTime($theTime = NULL) {
    if ($theTime == NULL) {
      $theTime = time();
    }
    return date("Y-m-d H:i:s", $theTime);
  }

  public static function newMysqlToday() {
    return date("Y-m-d 00:00:00", time());
  }   
  
}
?>