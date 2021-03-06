/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.db.table;

import org.apache.log4j.Logger;

/**
 * 
 * 
 * @author ryanhenderson
 * 
 */
public class MasterChildForeignKey implements Inform8ForeignKey {

  static Logger LOG = Logger.getLogger(MasterChildForeignKey.class);

  private final Inform8Table masterTable;
  private final String masterColumn;
  private final Inform8Table childTable;
  private final String childColumn;


  public MasterChildForeignKey(Inform8Table masterTable, String masterColumn, Inform8Table childTable, String childColumn) {
    this.masterTable = masterTable;
    this.masterColumn = masterColumn;
    this.childTable = childTable;
    this.childColumn = childColumn;
  }


  public Inform8Table getMasterTable() {
    return masterTable;
  }


  public String getMasterColumnName() {
    return masterColumn;
  }


  public Inform8Table getChildTable() {
    return childTable;
  }


  public String getChildColumnName() {
    return childColumn;
  }

}
