/* Copyright 2011 - 88 Creative Pty Ltd. 
 * Copyright of this program is the property of 88 Creative, 
 * without whose written permission reproduction in
 * whole or in part is prohibited. All rights reserved.
 * http://www.inform8.com
 * http://www.88creative.com.au
 */
package com.eighty8.inform8.db;

import com.eighty8.inform8.db.table.Inform8Column;
import com.eighty8.inform8.db.types.BigIntType;
import com.eighty8.inform8.db.types.BooleanType;
import com.eighty8.inform8.db.types.DateTimeType;
import com.eighty8.inform8.db.types.DateType;
import com.eighty8.inform8.db.types.DecimalType;
import com.eighty8.inform8.db.types.EnumType;
import com.eighty8.inform8.db.types.FieldType;
import com.eighty8.inform8.db.types.IntType;
import com.eighty8.inform8.db.types.LongTextType;
import com.eighty8.inform8.db.types.MediumIntType;
import com.eighty8.inform8.db.types.SmallIntType;
import com.eighty8.inform8.db.types.TextType;
import com.eighty8.inform8.db.types.TimeStampType;
import com.eighty8.inform8.db.types.TinyIntType;
import com.eighty8.inform8.db.types.UnknownType;
import com.eighty8.inform8.db.types.VarcharType;
import com.eighty8.inform8.util.StringUtils;

/**
 * Resolves the correct type and sets it properties based on the given String
 * @author ryanhend
 */
public class MysqlFieldFactory {

	public FieldType getType(String row) {
	    System.out.println("ROW:: " + row + "\n");
	    if (row.toLowerCase().indexOf(" longtext") >= 0) {
	    	return new LongTextType();
	    } if (row.toLowerCase().indexOf(" text") >= 0) {
	      return new TextType();
	    } else if (row.toLowerCase().indexOf(" varchar") >= 0) {
	      int len = Integer.parseInt(row.substring(row.indexOf("varchar") + 8, row.indexOf(")")));
	      return new VarcharType(len);
	    } else if (row.toLowerCase().indexOf(" decimal") >= 0) {
	        return new DecimalType();      
	    } else if (row.toLowerCase().indexOf(" tinyint(1)") >= 0) {
	      return new BooleanType();
	    } else if (row.toLowerCase().indexOf(" bit(1)") >= 0) {
	      return new BooleanType();
	    } else if (row.toLowerCase().indexOf(" tinyint") >= 0) {
	      return new TinyIntType();
        } else if (row.toLowerCase().indexOf(" bigint") >= 0) {
          return new BigIntType();	      
	    } else if (row.toLowerCase().indexOf(" int") >= 0) {
	      return new IntType();
	    } else if (row.toLowerCase().indexOf(" mediumint") >= 0) {
	      return new MediumIntType();
	    } else if (row.toLowerCase().indexOf(" smallint") >= 0) {
	      return new SmallIntType();
        } else if (row.toLowerCase().indexOf(" datetime") >= 0) {
          return new DateTimeType();
	    } else if (row.toLowerCase().indexOf(" date") >= 0) {
	      return new DateType();
	    } else if (row.toLowerCase().indexOf(" enum") >= 0) {
	      // `Type` enum('Normal','Set') default NULL,
	      String[] enumTypes = row.substring(row.indexOf("enum"), row.indexOf(")")).replace("enum", "")
	          .replace("(", "").replace(")", "").replace("'", "").split(",");
	      return new EnumType(enumTypes);
	    } else if (row.toLowerCase().indexOf(" timestamp") >= 0) {
	      return new TimeStampType();
	    }
	    return new UnknownType();
	  }

/** TODO remove, migrate to field type properties*/
public void setProperties(String row, Inform8Column newMember) {
    if (row.toLowerCase().indexOf("enum") >= 0) {
      String[] enumTypes = row.substring(row.indexOf("enum"), row.indexOf(")")).replace("enum", "")
          .replace("(", "").replace(")", "").replace("'", "").split(",");
      
      newMember.getSettings().addProperty("enumOptions", 
          StringUtils.convertArrayToString(enumTypes, ",", "", ""));
    }
  }	
	
}
