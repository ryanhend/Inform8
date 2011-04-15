/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.db.table;

import java.util.List;

import com.eighty8.inform8.db.Settings;
import com.eighty8.inform8.db.types.FieldType;

public interface Inform8Column {

  /**
   * Get the name of the field as it is in the database.
   * 
   * @return
   */
  public String getName();


  /**
   * Get the display settings for this field.
   * 
   * @return
   */
  public Settings getSettings();


  /**
   * Returns the Inform8 Field Type type
   * 
   * @return
   */
  public FieldType getType();


  /**
   * Sets the Inform8 Field Type type
   * 
   * @param type
   *          the field type
   */
  public void setType(FieldType type);


  /**
   * This will return the Foreign Key on this column, where this column represents the Child in the relationship.
   * 
   * @return the child foreign key on this column that references the primary key of this or another table
   */
  public Inform8ForeignKey getChildForeignKey();


  public void setChildForeignKey(Inform8ForeignKey fk);


  /**
   * This will return all the foreign keys that refer to this column. This column represents the Master in the relationship. i.e. This is applicable only to
   * primary keys.
   * 
   * @return all foreign keys that reference this field
   */
  public List<Inform8ForeignKey> getMasterForeignKeys();


  public void addMasterForeignKeys(Inform8ForeignKey fk);
}
