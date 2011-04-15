/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.db.table;

import java.util.Collection;
import java.util.List;

import com.eighty8.inform8.db.Settings;

/**
 * Represents the basic structure of a Database table plus Inform8 extras.
 * 
 * @author ryanhenderson
 * 
 */
public interface Inform8Table {

  /** The table name as it qppears in the database */
  public String getName();


  /**
   * Returns all the columns of table.<br>
   * <br>
   * Excludes the Primary Key, this can be obtained using the getPrimaryKey method.
   * 
   * @return the list of table columns
   */
  public Collection<Inform8Column> getColumns();


  /**
   * Get a column with the supplied named
   * 
   * @param name
   *          the name of the column
   * @return the column or null if not found
   */
  public Inform8Column getColumn(String name);


  /**
   * The settings of the table which are specific to generation.
   * 
   * @return the tables display settings
   */
  public Settings getSettings();


  /**
   * Gets a list of columns having the required label in their settings.
   * 
   * @param labelName
   *          the name of the label.
   */
  public List<Inform8Column> columnsWithLabel(String labelName);


  /**
   * 
   * @return the primary key column of the table
   */
  public Inform8Column getPrimaryKey();


  /**
   * Set the primary key column of the table.
   * 
   * @param col
   *          the Primary Key column
   */
  public void setPrimaryKey(Inform8Column col);


  /**
   * Adds the column to the table definition
   * 
   * @param col
   *          the column to add
   */
  public void addColumn(Inform8Column col);
  
  public void removeColumn(Inform8Column col);
}
