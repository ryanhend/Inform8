/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Config File.
 * 
 * @author ryanhenderson
 * 
 */
public class Config extends BaseConfig {

  static Logger LOG = Logger.getLogger(Config.class);


  /**
   * Creates a new config from the passed config file.
   * 
   * @param configFile
   */
  public Config(File configFile) {
    super(configFile);
  }


  public List<File> getTemplateFolders() {
    List<File> templateFolders = new ArrayList<File>();
    String[] folders = config.getProperty("file.resource.loader.path").split(",");
    for (String folder : folders) {
      templateFolders.add(new File(folder.trim()));
    }

    return templateFolders;
  }

}