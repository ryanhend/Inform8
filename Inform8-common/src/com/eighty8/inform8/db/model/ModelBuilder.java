/* 
 * Copyright 2011 - Inform8
 * http://www.inform8.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.eighty8.inform8.db.model;

import java.sql.SQLException;

/**
 * The model builder extracts the Database Model (As required for generation) using a JDBC connection. Most information should be extracted using JDC metadata.
 * If the metadata is not detailed enough then it will resort to extracting data from the database through SQL commands.
 * 
 * @author ryanhenderson
 * 
 */
public interface ModelBuilder {

  public DatabaseModel extractModel() throws SQLException;

}
