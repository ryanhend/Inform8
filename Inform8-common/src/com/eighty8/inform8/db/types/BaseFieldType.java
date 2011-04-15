/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.db.types;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseFieldType implements FieldType {

  Map<String, String> properties = new HashMap<String, String>();


  @Override
  public String getSearchTemplate() {
    return "fields" + File.separator + getName() + "-search.php.inc.vm";
  }


  @Override
  public String getEditTemplate() {
    return "fields" + File.separator + getName() + "-edit.php.inc.vm";
  }


  @Override
  public String getListTemplate() {
    return "fields" + File.separator + getName() + "-list.php.inc.vm";
  }


  @Override
  public String getViewTemplate() {
    return "fields" + File.separator + getName() + "-view.php.inc.vm";
  }


  public void addProperty(String key, String property) {
    properties.put(key, property);
  }


  public Map<String, String> getProperties() {
    return properties;
  }

}
