/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.db.types;

import java.util.Map;

public interface FieldType {

  /** The edit template name */
  public String getEditTemplate();


  /** The view template name */
  public String getViewTemplate();


  /** The List template name */
  public String getListTemplate();


  /** The List template name */
  public String getSearchTemplate();


  /** name of this type */
  public String getName();


  public Map<String, String> getProperties();
}
