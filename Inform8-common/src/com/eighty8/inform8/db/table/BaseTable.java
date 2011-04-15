/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.db.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.eighty8.inform8.db.Settings;
import com.eighty8.inform8.displaysettings.Label;
import com.eighty8.inform8.util.StringUtils;

/**
 * 
 * @author ryanhenderson
 * 
 */
public class BaseTable implements Inform8Table {

  /** The name of the table */
  protected final String name;

  /**
   * The primary key of this table.<br>
   * Only single column primary keys are supported.
   */
  protected Inform8Column primaryKey;

  /** The columns of this table */

  protected ColumnModel columnModel = new ColumnModel();

  /**
   * The settings of this table
   */
  protected final Settings settings = new Settings();


  /**
   * 
   * @param dbname
   */
  public BaseTable(String dbname) {
    this.name = dbname;
  }


  public void addColumn(Inform8Column col) {
    columnModel.addColumn(col);
  }


  public Collection<Inform8Column> getColumns() {
    return columnModel.getColumns();
  }


  public Inform8Column getPrimaryKey() {
    return primaryKey;
  }


  public void setPrimaryKey(Inform8Column pk) {
    this.primaryKey = columnModel.get(pk.getName());
    this.primaryKey.getSettings().addLabel(Label.PRIMARY_KEY);
    this.primaryKey.getSettings().addProperty(Label.DISPLAY_INDEX, "0");

    columnModel.remove(pk.getName());
  }
  
  
  public void removeColumn(Inform8Column col) {
    columnModel.remove(col.getName());
  }  


  public Inform8Column getColumn(String name) {
    return columnModel.get(name);
  }


  public Settings getSettings() {
    return settings;
  }


  /**
   * Returns the name of the table as it is in the database.
   */
  public String getName() {
    return name;
  }


  /**
   * TODO move to the plus version or remove entirely if not currently used. Converts names like 'CustomerOrder' into 'Customer Order' - Useful for display.
   * 
   * @return
   */
  public String getCaseExplodedName() {
    return StringUtils.getCaseExplodedName(name, false);
  }


  @Override
  public List<Inform8Column> columnsWithLabel(String labelName) {
    List<Inform8Column> cols = new ArrayList<Inform8Column>();
    for (Inform8Column col : columnModel.getColumns()) {
      if (col.getSettings().hasLabel(labelName)) {
        cols.add(col);
      }
    }
    return cols;
  }


  public ColumnModel getColumnModel() {
    return columnModel;
  }

}