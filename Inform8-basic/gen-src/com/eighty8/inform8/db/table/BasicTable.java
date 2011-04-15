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
 * @author ryanhenderson
 *
 */
public class BasicTable extends BaseTable {

  static Logger LOG = Logger.getLogger(BasicTable.class);

  /**
   * 
   * @param dbname
   *          the name of the table as it appears in the database.
   */
  public BasicTable(String dbname) {
    super(dbname);
  }


  public void addColumn(Inform8Column tableMember) {
    super.addColumn(tableMember);
  }


  public void addForeignKey(MasterChildForeignKey foreignKey) {
    // System.out.println("addForiegnKey: tb:" + dbname + ", fkField:" + foreignKey.getBaseField());
    if (getPrimaryKey().getName().equals(foreignKey.getMasterColumnName())) {
      // Foreign Key is referencing this Primary Key Column
      columnModel.get(foreignKey.getMasterColumnName()).addMasterForeignKeys(foreignKey);
    } else {
      // This column has a Foreign key that references another column, i.e it's the shild part of the foreign key
      columnModel.get(foreignKey.getChildColumnName()).setChildForeignKey(foreignKey);
    }
  }


  /**
   * @deprecated
   * @return
   */
  public String getDbname() {
    return name;
  }

}
