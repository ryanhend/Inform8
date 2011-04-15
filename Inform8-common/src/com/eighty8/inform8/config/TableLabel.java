/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.eighty8.inform8.config;

public class TableLabel {

  private final String table;
  private final String label;


  public TableLabel(String table, String label) {
    super();
    this.table = table;
    this.label = label;
  }


  public String getLabel() {
    return label;
  }


  public String getTable() {
    return table;
  }
}
