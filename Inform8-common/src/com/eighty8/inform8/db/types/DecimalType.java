/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.db.types;

import com.eighty8.inform8.displaysettings.Property;

public class DecimalType extends BaseFieldType {

  private final int wholeDigits;
  private final int decimalDigits;


  public DecimalType(int wholeDigits, int decimalDigits) {
    this.wholeDigits = wholeDigits;
    this.decimalDigits = decimalDigits;
    this.properties.put(Property.WHOLE_DIGITS.name(), String.valueOf(wholeDigits));
    this.properties.put(Property.DECIMAL_DIGITS.name(), String.valueOf(decimalDigits));
  }


  public int getWholeDigits() {
    return wholeDigits;
  }


  public int getDecimalDigits() {
    return decimalDigits;
  }


  @Override
  public String getName() {
    return "decimal";
  }

}
