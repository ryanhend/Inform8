/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.velocity;

import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.log.LogChute;

public class DummyLogger implements LogChute {

  @Override
  public void init(RuntimeServices arg0) throws Exception {
  }


  @Override
  public boolean isLevelEnabled(int arg0) {
    return false;
  }


  @Override
  public void log(int arg0, String arg1) {
  }


  @Override
  public void log(int arg0, String arg1, Throwable arg2) {
  }

}
