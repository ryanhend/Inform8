/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface DbConnection {

  /**
   * Gets The JDBC connection
   * 
   * @return the JDBC connection
   * @throws SQLException
   *           if a connection error occured.
   */
  public Connection getConnection() throws SQLException;


  /**
   * 
   * @return the Database Major version
   * @throws SQLException
   */
  public int getDatabaseMajorVersion() throws SQLException;


  /**
   * 
   * @return the Database Minor version
   * @throws SQLException
   */
  public int getDatabaseMinorVersion() throws SQLException;


  /**
   * 
   * @return the Connector Major version
   * @throws SQLException
   */
  public int getConnectorMajorVersion() throws SQLException;


  /**
   * 
   * @return the Connector Minor version
   * @throws SQLException
   */
  public int getConnectorMinorVersion() throws SQLException;

}