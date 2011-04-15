/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class BaseConfig {

  protected Properties config;

  protected final List<TableAndColumnAndLabel> tableFieldLabels = new ArrayList<TableAndColumnAndLabel>();
  protected final List<TableColumnProperty> tableFieldProperties = new ArrayList<TableColumnProperty>();

  protected final List<TableLabel> tableLbls = new ArrayList<TableLabel>();
  protected final List<TableProperty> tableProperties = new ArrayList<TableProperty>();

  /** The type of server, e.g. mysql,db2,sybase etc */
  protected String dbServerType;

  /** The host of the server */
  protected String host;

  /** the port to connect over */
  protected String dbPort;

  /** the name of the database to connect to */
  protected String dbname;

  /** The username to connect with */
  protected String user;

  /** The password to connect with */
  protected String pwd;

  /** The base generation output folder */
  private String genFolder;

  /** The distribution output folder */
  private String distFolder;


  /**
   * 
   * @param configFile
   */
  public BaseConfig(File configFile) {
    try {
      config = new Properties();
      config.load(new FileInputStream(configFile));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    parseOutputProperties();
    parseDbProperties();
    parseTableLabels();
    parseTablePropeties();
    parseFieldLabels();
    parseFieldProperties();
  }


  /**
   * 
   * @return
   */
  public Properties getAllProperties() {
    return config;
  }


  private void parseOutputProperties() {
    this.genFolder = config.getProperty("gen.out.folder").trim();
    this.distFolder = config.getProperty("gen.dist.folder").trim();
  }


  /**
   * 
   * @return
   */
  public List<String> getGenericExtensions() {
    ArrayList<String> extensions = new ArrayList<String>();
    String[] split = config.getProperty("gen.file.extensions").trim().split(",");
    for (String anExtension : split) {
      extensions.add(anExtension.trim() + ".vm");
    }
    System.out.println(extensions);
    return extensions;
  }


  public List<String> getTableExtensions() {
    ArrayList<String> extensions = new ArrayList<String>();
    String[] split = config.getProperty("gen.file.extensions").trim().split(",");
    for (String anExtension : split) {
      extensions.add(anExtension.trim() + ".tbl.vm");
    }
    return extensions;
  }


  /**
   * parse all the database properties.
   */
  private void parseDbProperties() {
    this.dbServerType = config.getProperty("ref.db.type").trim();
    this.host = config.getProperty("ref.db.host").trim();
    this.dbPort = config.getProperty("ref.db.port").trim();
    this.dbname = config.getProperty("ref.db.name").trim();
    this.user = config.getProperty("ref.db.user").trim();
    this.pwd = config.getProperty("ref.db.pwd").trim();
  }


  /**
   * Parse the table labels
   */
  private void parseTableLabels() {
    Set<Object> keySet = config.keySet();
    for (Object key : keySet) {
      if (key.toString().toLowerCase().startsWith("tablelabel.")) {
        String tableLabels = config.getProperty(key.toString()).trim();
        String[] split = tableLabels.split("\\:");
        tableLbls.add(new TableLabel(split[0], split[1]));
      }
    }
  }


  /**
   * Parse the table properties
   */
  private void parseTablePropeties() {
    Set<Object> keySet = config.keySet();
    for (Object key : keySet) {
      if (key.toString().toLowerCase().startsWith("tableprop.")) {
        String tableProp = config.getProperty(key.toString()).trim();

        String[] split = tableProp.split("\\.");
        String[] parts = split[1].split("\\:", 2);

        getTableProperties().add(new TableProperty(split[0].trim(), parts[0].trim(), parts[1].trim()));
      }
    }
  }


  /**
   * Parse the field properties
   */
  private void parseFieldProperties() {
    Set<Object> keySet = config.keySet();
    for (Object key : keySet) {
      if (key.toString().toLowerCase().startsWith("fieldprop.")) {
        String fieldProperty = config.getProperty(key.toString()).trim();

        // Task.Description:grid-width:250
        String[] split = fieldProperty.split("\\.");
        String[] parts = split[1].split("\\:");
        tableFieldProperties.add(new TableColumnProperty(split[0], parts[0].trim(), parts[1].trim(), parts[2].trim()));
      }
    }
  }


  /**
   * Parse the field labels
   */
  private void parseFieldLabels() {
    Set<Object> keySet = config.keySet();
    for (Object key : keySet) {
      if (key.toString().toLowerCase().startsWith("fieldlabel.")) {
        String tableAndMember = config.getProperty(key.toString()).trim();
        String[] split = tableAndMember.split("\\.");
        tableFieldLabels.add(new TableAndColumnAndLabel(split[0], split[1].substring(0, split[1].indexOf(':')), split[1].substring(split[1].indexOf(':') + 1)));
      }
    }
  }


  public String getGenFolder() {
    return genFolder;
  }


  public String getDistFolder() {
    return distFolder;
  }


  /**
   * Get the type/name of the database server.
   * 
   * @return the type of server.
   */
  public String getDbServerType() {
    return dbServerType;
  }


  /**
   * Set the server type.
   * 
   * @param dbServerType
   *          The type of server.
   */
  public void setDbServerType(String dbServerType) {
    this.dbServerType = dbServerType;
  }


  /**
   * Gets the hostname/ip of the database server
   * 
   * @return the hostname/ip of the db server
   */
  public String getHost() {
    return host;
  }


  /**
   * Sets the hostname/ip address of the Database server
   * 
   * @param host
   */
  public void setHost(String host) {
    this.host = host;
  }


  public String getDbPort() {
    return dbPort;
  }


  public void setDbPort(String dbPort) {
    this.dbPort = dbPort;
  }


  public String getDbname() {
    return dbname;
  }


  public void setDbname(String dbname) {
    this.dbname = dbname;
  }


  public String getUser() {
    return user;
  }


  public void setUser(String user) {
    this.user = user;
  }


  public String getPwd() {
    return pwd;
  }


  public void setPwd(String pwd) {
    this.pwd = pwd;
  }


  public String getSqlFile() {
    return config.getProperty("project.sql");
  }


  public List<TableLabel> getTableLabels() {
    return tableLbls;
  }


  public List<TableAndColumnAndLabel> getTableFieldLabels() {
    return tableFieldLabels;
  }


  public List<TableColumnProperty> getTableFieldProperties() {
    return tableFieldProperties;
  }


  public List<TableProperty> getTableProperties() {
    return tableProperties;
  }

}