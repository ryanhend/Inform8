/* Copyright 2011 - 88 Creative Pty Ltd. 
 * Copyright of this program is the property of 88 Creative, 
 * without whose written permission reproduction in
 * whole or in part is prohibited. All rights reserved.
 * http://www.inform8.com
 * http://www.88creative.com.au
 */
package com.eighty8.inform8.parser.mysql;

import com.eighty8.inform8.db.MysqlTableMemberFactory;
import com.eighty8.inform8.db.table.BasicTable;
import com.eighty8.inform8.db.table.Inform8Table;
import com.eighty8.inform8.db.table.Inform8Column;


public class TableParser {

  private String tablesql;
  private final MysqlTableMemberFactory memberFactory;
  
  public TableParser(String tablesql) {
    this.tablesql = tablesql;
    memberFactory = new MysqlTableMemberFactory();
  }

  public Inform8Table parseTable() {
    int fieldEncloserStart = tablesql.indexOf("(");
    int fieldEncloserEnd = tablesql.lastIndexOf(")");

    String name = tablesql.substring(0, fieldEncloserStart);
    name = name.replaceAll("CREATE TABLE", "");
    name = name.replaceAll("'", "").replaceAll("`", "");
    name = name.trim();
    BasicTable t = new BasicTable(name);
    
    String[] tableData = tablesql.substring(fieldEncloserStart + 1, fieldEncloserEnd).split(
        "\\r?\\n");
    int count = 0;
    for (String row : tableData) {
      row = row.trim();
      if (row.startsWith("`")) {
    	Inform8Column createTableMember = memberFactory.createTableMember(row, count++);
        t.addColumn(createTableMember);
      } else if (row.toUpperCase().contains("PRIMARY KEY")) {
        String[] split = row.split("`");
        String key = split[1].trim();
        t.setPrimaryKey(t.getColumn(key));
      }
    }
    
    return t;
  }

}
