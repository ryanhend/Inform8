/* Copyright 2011 - 88 Creative Pty Ltd. 
 * Copyright of this program is the property of 88 Creative, 
 * without whose written permission reproduction in
 * whole or in part is prohibited. All rights reserved.
 * http://www.inform8.com
 * http://www.88creative.com.au
 */
package com.eighty8.inform8.parser.mysql;

import java.util.ArrayList;
import java.util.List;

import com.eighty8.inform8.db.table.Inform8Table;
import com.eighty8.inform8.db.table.MasterChildForeignKey;

public class ForeignKeyParser {

  private final List<Inform8Table> tables;

  /**
   * 
   * @param tables
   */
  public ForeignKeyParser(List<Inform8Table> tables) {
    this.tables = tables;
  }
  
  
  /**
   * 
   * @param sql
   * @return
   */
  public List<MasterChildForeignKey> splitAlterTables(String sql) {
    ArrayList<MasterChildForeignKey> fks = new ArrayList<MasterChildForeignKey>();
    String[] split = sql.split("ALTER TABLE");
    
    for (String theSplit : split) {
      if (theSplit.contains(";")) {
        String allStatements = theSplit.substring(0, theSplit.indexOf(';'));
        String table = allStatements.substring(allStatements.indexOf('`')+1);
        table= table.substring(0, table.indexOf('`'));
        
        String[] statements = allStatements.split(",");
        for (String statement : statements) {
          if (statement.contains("FOREIGN KEY")) {
            String field = statement.substring(statement.indexOf("(")+1, statement.indexOf(")")).replace("`", "");
            String temp = statement.substring(statement.indexOf("REFERENCES")).replaceAll("REFERENCES", "");
            String otherTable = temp.substring(temp.indexOf("`")+1);
            otherTable = otherTable.substring(0, otherTable.indexOf("`"));
            String otherField = temp.substring(temp.indexOf("(")+1, temp.indexOf(")")).replace("`", "");
            
            System.out.println(field);
            System.out.println(otherTable);
            System.out.println(otherField);
            
            fks.add(new MasterChildForeignKey(findTable(otherTable), otherField, findTable(table), field));
          }
        }
      }
    }
    return fks;
  }
  
  /**
   * 
   * @param name
   * @return
   */
  public Inform8Table findTable(String name) {
    for (Inform8Table table: tables) {
      if (table.getName().equalsIgnoreCase(name)) {
        return table;
      }
    }
    return null;
  }
  
}