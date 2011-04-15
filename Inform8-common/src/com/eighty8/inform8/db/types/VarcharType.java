/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.db.types;

import com.eighty8.inform8.displaysettings.Property;

public class VarcharType extends BaseFieldType {

  private final int length;


  public VarcharType() {
    this(40);

  }


  public VarcharType(int length) {
    this.length = length;
    this.properties.put(Property.DATA_LENGTH.name(), String.valueOf(length));
  }


  public int getLength() {
    return length;
  }


  public int getHtmlLength() {
    int ratio = length >= 128 ? 4 : length >= 64 ? 3 : length >= 24 ? 2 : 1;
    return length / ratio;
  }


  @Override
  public String getName() {
    return "varchar";
  }

}
