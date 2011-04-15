/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtils {

  /**
   * Saves the data into the supplied file.
   * 
   * @param data
   *          the data to write into the file.
   * @param fileName
   *          the filename of the file
   * @param dir
   *          the directory to save the file into
   * @throws IOException
   *           if the file save fails
   */
  public static void saveFile(String data, String fileName, File dir) throws IOException {
    if (!dir.exists()) {
      dir.mkdirs();
    }
    File file = new File(dir, fileName);
    file.createNewFile();
    PrintWriter printWriter = new PrintWriter(file);
    printWriter.write(data);
    printWriter.flush();
    printWriter.close();
  }

}
