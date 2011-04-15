/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.config;

public class TableAndColumn {

  private final String table;
  private final String column;


  public TableAndColumn(String table, String column) {
    super();
    this.table = table;
    this.column = column;
  }


  public String getTable() {
    return table;
  }


  public String getColumn() {
    return column;
  }


  @Override
  public String toString() {
    return "TableAndColumn: " + table + "." + column;
  }
}
