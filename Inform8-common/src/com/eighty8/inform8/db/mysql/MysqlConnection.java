/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.eighty8.inform8.GenerationContext;
import com.eighty8.inform8.db.DbConnection;

/**
 * Connects to a MySql Data Source using a JDBC connection.
 * 
 * @author ryanhenderson
 * 
 */
public class MysqlConnection implements DbConnection {

  static Logger LOG = Logger.getLogger(MysqlConnection.class);

  /**
   * Static block to load the driver.
   */
  static {
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      LOG.trace("Mysql Driver Loaded");
    } catch (Exception e) {
      LOG.error(e);
      e.printStackTrace();
      throw new RuntimeException(e); // complete failure
    }
  }

  private Connection connection;


  @Override
  public synchronized Connection getConnection() throws SQLException {
    if (this.connection == null) {
      GenerationContext gc = GenerationContext.getInstance();

      LOG.trace("Loading MySQL Connection");
      connection = DriverManager.getConnection("jdbc:mysql://" + gc.getDatabaseHost() + ":" + gc.getDatabasePort() + "/" + gc.getDatabaseName() + "?user="
          + gc.getDatabaseUsername() + "&password=" + gc.getDatabasePassword());
      LOG.info("MySQL Connection Loaded");
    }
    return this.connection;
  }


  /**
   * 
   * @throws SQLException
   */
  public synchronized void closeConnection() throws SQLException {
    if (this.connection != null) {
      Connection old = connection;
      connection = null; // forget about it, not bothered by what happens next ...
      LOG.trace("Closing MySQL Connection");
      old.close();
      LOG.trace("MySQL Connection closed");
    }
  }


  @Override
  public int getDatabaseMajorVersion() throws SQLException {
    return connection.getMetaData().getDatabaseMajorVersion();
  }


  @Override
  public int getDatabaseMinorVersion() throws SQLException {
    return connection.getMetaData().getDatabaseMinorVersion();
  }


  @Override
  public int getConnectorMajorVersion() throws SQLException {
    return connection.getMetaData().getDriverMajorVersion();
  }


  @Override
  public int getConnectorMinorVersion() throws SQLException {
    return connection.getMetaData().getDriverMinorVersion();
  }

}