/* Copyright 2011 - 88 Creative Pty Ltd. 
 * Copyright of this program is the property of 88 Creative, 
 * without whose written permission reproduction in
 * whole or in part is prohibited. All rights reserved.
 * http://www.inform8.com
 * http://www.88creative.com.au
 */
package com.eighty8.inform8.gen;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.eighty8.inform8.config.GeneratorConfig;
import com.eighty8.inform8.db.table.Inform8Table;
import com.eighty8.inform8.file.FileManager;
import com.eighty8.inform8.util.FileUtils;
import com.eighty8.inform8.velocity.DummyLogger;

public class ClassGen {

  private File adminDir;

  private final GeneratorConfig config;
  private final FileManager fileManager;
  private VelocityEngine velocityEngine;


  public ClassGen(File outDir, GeneratorConfig generatorConfig, FileManager fileManager) throws Exception {
    this.config = generatorConfig;
    this.fileManager = fileManager;
    this.adminDir = new File(outDir, "admin");

    velocityEngine = new VelocityEngine(generatorConfig.getAllProperties());
    velocityEngine.setProperty(VelocityEngine.RUNTIME_LOG_LOGSYSTEM, new DummyLogger());
    velocityEngine.init();
  }


  public void gen(Inform8Table table) {
    List<File> folders = fileManager.getFolders();
    try {
      for (File file : folders) {
        String[] tableTemplates = fileManager.getAllTemplates(file.getName(), ".tbl.vm");
        if (tableTemplates.length > 0) {
          File outdir = new File(adminDir, file.getName());
          outdir.mkdirs();
          for (String templateName : tableTemplates) {
            generateAndSave(table, file.getName() + File.separator + templateName, table.getName() + templateName.replace(".tbl.vm", ""), outdir);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   * 
   * @param table
   * @param templateName
   * @param saveFilename
   * @param outdirectory
   * @throws Exception
   */
  private void generateAndSave(Inform8Table table, String templateName, String saveFilename, File outdirectory) throws Exception {
    String mergeResult = merge(table, templateName);
    FileUtils.saveFile(mergeResult, saveFilename, outdirectory);
  }


  private String merge(Inform8Table t, String template) throws Exception {
    System.out.println("--> MERGE: " + t.getName() + ", " + template);

    HashMap<String, Object> data = new HashMap<String, Object>();
    data.put("table", t);

    data.put("ds", "$");
    data.put("config", config);
    data.put("fileManager", fileManager);

    VelocityContext vc = new VelocityContext(data);
    StringWriter stringWriter = new StringWriter();

    Template templ = velocityEngine.getTemplate(template);
    templ.merge(vc, stringWriter);

    return stringWriter.toString();
  }

}
