/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.db.model;

import java.util.ArrayList;
import java.util.List;

import com.eighty8.inform8.db.table.Inform8Table;

/**
 * Basic model of a database.
 * 
 * @author ryanhenderson
 * 
 */
public class DatabaseModel {

  private List<Inform8Table> tables = new ArrayList<Inform8Table>();


  public List<Inform8Table> getTables() {
    return tables;
  }


  /**
   * 
   * @param name
   * @return
   */
  public Inform8Table getTable(String name) {
    for (Inform8Table table : tables) {
      if (table.getName().equals(name)) {
        return table;
      }
    }
    return null;
  }


  /**
   * Get all tables with settings that have the required label.
   * 
   * @param labelName
   * @return
   */
  public List<Inform8Table> getTableWithLabel(String labelName) {
    List<Inform8Table> tables = new ArrayList<Inform8Table>();
    for (Inform8Table table : tables) {
      if (table.getSettings().hasLabel(labelName)) {
        tables.add(table);
      }
    }
    return tables;
  }


  /**
   * Sets the the tables in the model.
   * 
   * @param tables
   */
  public void setTables(List<Inform8Table> tables) {
    this.tables = tables;
  }

}
