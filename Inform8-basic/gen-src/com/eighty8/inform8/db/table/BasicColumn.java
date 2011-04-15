/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.db.table;

import org.apache.log4j.Logger;

public class BasicColumn extends BaseColumn {

  static Logger LOG = Logger.getLogger(BasicColumn.class);

  public BasicColumn(String name, int displayIndex) {
    super(name, displayIndex);
  }

}
