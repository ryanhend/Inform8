/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.displaysettings;

public enum Label {

  DB_READ_ONLY, // DB layer affected.
  UI_READ_ONLY, // UI layer affected. (Observe db as well if set, no point having in UI if not reflected in db ??)
  
  NOT_NULL, 
  AUTO_INCREMENT,
  PRIMARY_KEY, 
  DEFAULT_VALUE,
  
  PASSWORD, 
  EMAIL, 
  WEB_ADDRESS, 
  RATING1TO10, 
  HIDDEN,
  CALENDAR,
  
  FILE,
  
  DISPLAY_INDEX

}
