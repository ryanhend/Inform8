/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8;

import java.io.File;

import com.eighty8.inform8.config.BaseConfig;
import com.eighty8.inform8.db.DbConnection;

/**
 * Generation context, holds config and db settings.
 * 
 * Singleton reference.
 * 
 * @author ryanhenderson
 * 
 */
public class GenerationContext {

  /**
   * The static context instance
   */
  private static GenerationContext instance = new GenerationContext();

  /**
   * The path to the generation config file.
   */
  private String configPath;

  /**
   * The reference database connection. The database used to extract the database structure for generation.
   */
  private DbConnection refDbConnection;

  private BaseConfig config;


  private GenerationContext() {
  }


  public DbConnection getRefDbConnection() {
    return refDbConnection;
  }


  public void setRefDbConnection(DbConnection refDbConnection) {
    this.refDbConnection = refDbConnection;
  }


  /**
   * Sets the config file path.
   * 
   * @param configFilePath
   *          path to the config file.
   */
  public void setConfigPath(String configFilePath) {
    this.configPath = configFilePath;
  }


  /**
   * Gets the path to the config file.
   * 
   * @return the path to the config file.
   */
  public String getConfigPath() {
    return configPath;
  }

  public String getConfigFolder() {
    return new File(configPath).getParent();
  }  

  public static GenerationContext getInstance() {
    return instance;
  }


  public void setConfig(BaseConfig theConfig) {
    this.config = theConfig;
  }


  /**
   * Gets the reference database name.
   * 
   * @return the database name or null if not set
   */
  public String getDatabaseName() {
    return config != null ? config.getDbname() : null;
  }


  /**
   * Gets the reference host database
   * 
   * @return the host database or null if not set
   */
  public String getDatabaseHost() {
    return config != null ? config.getHost() : null;
  }


  /**
   * 
   * @return
   */
  public String getDatabaseUsername() {
    return config != null ? config.getUser() : null;
  }


  /**
   * 
   * @return
   */
  public String getDatabasePassword() {
    return config != null ? config.getPwd() : null;
  }


  /**
   * 
   * @return
   */
  public String getDatabasePort() {
    return config != null ? config.getDbPort() : null;
  }


  /**
   * 
   * @return
   */
  public String getDatabaseType() {
    return config != null ? config.getDbServerType() : null;
  }

}
