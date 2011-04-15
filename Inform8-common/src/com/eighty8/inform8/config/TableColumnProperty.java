/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.eighty8.inform8.config;

import org.apache.commons.lang.builder.ToStringBuilder;

public class TableColumnProperty {

  private final String table;
  private final String column;
  private final String label;
  private final String property;


  public TableColumnProperty(String table, String column, String label, String property) {
    super();
    this.table = table;
    this.column = column;
    this.label = label;
    this.property = property;
  }


  public String getLabel() {
    return label;
  }


  public String getProperty() {
    return property;
  }


  public String getTable() {
    return table;
  }


  public String getColumn() {
    return column;
  }


  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

}