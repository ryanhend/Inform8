/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.db.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColumnModel {

  protected Map<String, Inform8Column> columns = new HashMap<String, Inform8Column>();


  public void addColumn(Inform8Column col) {
    columns.put(col.getName(), col);
  }


  public List<Inform8Column> getColumns() {
    return new ArrayList<Inform8Column>(columns.values());
  }


  /**
   * 
   * @param name
   */
  public void remove(String name) {
    Inform8Column toRemove = null;
    for (Inform8Column col : columns.values()) {
      if (col.getName().equals(name)) {
        toRemove = col;
      }
    }
    if (toRemove != null) {
      columns.remove(toRemove.getName());
    }
  }


  public Inform8Column get(String name) {
    return columns.get(name);
  }


  public ColumnModel copy() {
    ColumnModel columnModel = new ColumnModel();
    for (Inform8Column col : columns.values()) {
      columnModel.addColumn(col);
    }
    return columnModel;
  }

}