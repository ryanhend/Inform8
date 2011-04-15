/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.eighty8.inform8.config;

public class TableProperty {

  private final String table;

  private final String key;

  private final String value;


  public TableProperty(String table, String key, String value) {
    this.table = table;
    this.key = key;
    this.value = value;
  }


  public String getKey() {
    return key;
  }


  public String getValue() {
    return value;
  }


  public String getTable() {
    return table;
  }
}
