/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.db.mysql;

/**
 * Mysql does not seemt o expose the enum options through the Database meta data. The value can be retrieved sing SQL and parsed using this class to return the
 * list of enum options.
 * 
 * @author ryanhenderson
 */
public class EnumParser {

  /**
   * Parses the MySql Definition returning an array of strings representing the individual enum options.
   * 
   * @param enumDef
   * @return
   */
  public String[] parse(String enumDef) {
    String[] split = enumDef.substring(0, enumDef.length() - 1).replace("enum(", "").split(",");
    for (int i = 0; i < split.length; i++) {
      split[i] = split[i].substring(1, split[i].length() - 1); // trim off the enclosing apostrophes
    }
    return split;
  }

}
