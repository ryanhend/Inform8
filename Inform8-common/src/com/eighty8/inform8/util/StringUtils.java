/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.util;

public class StringUtils {

  /**
   * Converts an array to a single string
   * 
   * @param options
   *          the array to join together
   * @param separator
   *          placed between each element in the string
   * @param prefix
   *          added at the start of the string
   * @param appendix
   *          appended to the end of the string
   * @return
   */
  public static String convertArrayToString(String[] options, String separator, String prefix, String appendix) {
    StringBuilder builder = new StringBuilder();
    builder.append(prefix);
    boolean sep = false;
    for (String option : options) {
      if (sep) {
        builder.append(separator);
      }
      builder.append(option);
      sep = true;
    }
    builder.append(appendix);
    return builder.toString();
  }


  public static String uppercaseFirstChar(String text) {
    return String.format("%s%s", Character.toUpperCase(text.charAt(0)), text.substring(1));
  }


  /**
   * Turns AnyCamelCasedString into a space separated string like so: Any Camel Cased String
   * 
   * @param text
   * @param stripId
   *          remove 'Id' from the end of the string if present
   * @return
   */
  public static String getCaseExplodedName(String text, boolean stripId) {
    StringBuilder label = new StringBuilder();
    for (int i = 0; i < text.length(); i++) {
      if (i > 0 && Character.isUpperCase(text.charAt(i)) && !Character.isUpperCase(text.charAt(i - 1))) {
        label.append(' ');
      }

      if (i > 0 && Character.isDigit(text.charAt(i)) && !Character.isDigit(text.charAt(i - 1))) {
        label.append(' ');
      }

      label.append(text.charAt(i));
    }

    if (stripId) {
      if (label.length() >= 4 && label.substring(label.length() - 2).equals("Id")) {
        label.setLength(label.length() - 2);
      }
    }
    return label.toString().trim();
  }

}
