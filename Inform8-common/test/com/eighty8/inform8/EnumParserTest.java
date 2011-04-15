/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.eighty8.inform8.db.mysql.EnumParser;


public class EnumParserTest {

  @Test
  public void testParseSimpleEnum() {
    String[] parse = new EnumParser().parse("enum('Ordered','Confirmed - Awaiting Payment','In Progress','Delayed','Filled','Dispatched','Cancelled')");
    assertTrue(parse.length == 7);
    assertTrue(parse[0].equals("Ordered"));
    assertTrue(parse[1].equals("Confirmed - Awaiting Payment"));
    assertTrue(parse[6].equals("Cancelled"));
  }
  
   
}
