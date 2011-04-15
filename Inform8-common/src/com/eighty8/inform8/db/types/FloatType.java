/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.db.types;

import com.eighty8.inform8.displaysettings.Property;

public class FloatType extends BaseFieldType {

  private final int columns;


  public FloatType(int columns) {
    this.columns = columns;
    this.properties.put(Property.COLUMNS.name(), String.valueOf(columns));
  }


  public int getColumns() {
    return columns;
  }


  @Override
  public String getName() {
    return "float";
  }

}
