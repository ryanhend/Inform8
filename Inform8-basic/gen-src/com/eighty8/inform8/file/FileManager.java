/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.file;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.eighty8.inform8.config.Config;

/**
 * Utitlity methods for finding files and folders used through the generation process.
 * 
 * @author ryanhenderson
 * 
 */
public class FileManager {

  static Logger LOG = Logger.getLogger(FileManager.class);

  private final List<File> templateFolders;

  public FileManager(Config generatorConfig) {
    this.templateFolders = generatorConfig.getTemplateFolders();
  }


  public String[] getAllTemplates(String subfolder, final List<String> extensions) {
    List<String> allFiles = new ArrayList<String>();
    for (File folder : templateFolders) {
      if (new File(folder, subfolder).exists()) {
        allFiles.addAll(Arrays.asList(new File(folder, subfolder).list(new FilenameFilter() {

          @Override
          public boolean accept(File dir, String name) {
            for (String daExtension : extensions) {
              if (name.endsWith(daExtension)) {
                return true;
              }
            }
            return false;
          }

        })));
      }
    }
    LOG.trace(allFiles.size() + " Matching Templates Found");
    return allFiles.toArray(new String[] {});
  }


  public List<File> getTemplateFolders() {
    return templateFolders;
  }


  public List<String> getFolders() {
    List<File> allFiles = new ArrayList<File>();
    List<String> allFolderNames = new ArrayList<String>();

    for (File folder : templateFolders) {
      if (!folder.exists()) {
        continue;
      }

      getFolders(folder, allFiles);
      
      for (File f : allFiles) {
        allFolderNames.add(f.getPath().substring(folder.getPath().length()));
      }      
      
    }
    
    System.out.println(allFolderNames);
    return allFolderNames;
  }
  
  
  public void getFolders(File folder, List<File> allFiles) {
    File[] listFiles = folder.listFiles(new FileFilter() {

      @Override
      public boolean accept(File pathname) {
        return pathname.isDirectory() && !pathname.getName().startsWith(".");
      }

    });

    for (File file : listFiles) {
      getFolders(file, allFiles);
    }
    
    if (listFiles != null) {
      allFiles.addAll(Arrays.asList(listFiles));
    }
    
  }


  /**
   * Test to see if an extension file exists
   * 
   * @param fileName
   * @return true if an extension file is found
   */
  public boolean extensionExists(final String fileName) {
    for (File folder : this.templateFolders) {
      if (new File(folder, "extensions").exists()) {
        return new File(new File(folder, "extensions"), fileName).exists();
      }
    }
    return false;
  }

}