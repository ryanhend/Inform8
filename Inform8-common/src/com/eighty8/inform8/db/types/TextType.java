/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.db.types;

import com.eighty8.inform8.displaysettings.Property;

public class TextType extends BaseFieldType {

  private final int length;


  public TextType() {
    this(0);
  }


  public TextType(int length) {
    this.length = length;
    this.properties.put(Property.DATA_LENGTH.name(), String.valueOf(length));
  }


  public int getLength() {
    return length;
  }


  @Override
  public String getName() {
    return "text";
  }

}
