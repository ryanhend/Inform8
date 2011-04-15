/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.db.types;

public class BitType extends BaseFieldType {

  private final int length;


  public BitType(int length) {
    this.length = length;
  }


  public int getLength() {
    return length;
  }


  @Override
  public String getName() {
    return "bit";
  }

}
