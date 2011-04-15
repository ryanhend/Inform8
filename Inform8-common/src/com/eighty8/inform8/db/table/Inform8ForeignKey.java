/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.db.table;

/**
 * 
 * This class can hold a simple Foreign key where a single Child Column references a single Master Column
 * 
 * @author ryanhenderson
 * 
 */
public interface Inform8ForeignKey {

  /**
   * 
   * @return
   */
  public Inform8Table getMasterTable();


  /**
   * 
   * @return
   */
  public String getMasterColumnName();


  /**
   * 
   * @return
   */
  public Inform8Table getChildTable();


  /**
   * 
   * @return
   */
  public String getChildColumnName();

}
