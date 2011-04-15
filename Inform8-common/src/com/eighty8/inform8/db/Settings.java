/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eighty8.inform8.displaysettings.Label;

/**
 * Settings holds all the extra information needed for generation.
 * 
 * @author ryanhenderson
 * 
 */
public class Settings {

  /**
   * Simple labels
   */
  private List<String> labels = new ArrayList<String>();

  /**
   * Properties, key/value pairs
   */
  private Map<String, String> properties = new HashMap<String, String>();


  public Settings() {
  }


  public void addLabel(String label) {
    labels.add(label);
  }


  public void addProperty(String key, String property) {
    properties.put(key, property);
  }


  public void addProperty(Label key, String property) {
    addProperty(key.toString(), property);
  }


  public List<String> getLabels() {
    return labels;
  }


  public boolean hasLabel(String lbl) {
    return labels.contains(lbl);
  }


  public Map<String, String> getProperties() {
    return properties;
  }


  public String getProperty(String key, String defaultValue) {
    if (properties.containsKey(key)) {
      return properties.get(key);
    }
    return defaultValue;
  }


  public boolean hasProperty(String key) {
    return properties.containsKey(key);
  }


  public String getProperty(Label lbl, String defaultValue) {
    return getProperty(lbl.toString(), defaultValue);
  }


  public void addLabel(Label lbl) {
    addLabel(lbl.toString());
  }

}