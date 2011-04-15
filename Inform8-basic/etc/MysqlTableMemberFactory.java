/* Copyright 2011 - 88 Creative Pty Ltd. 
 * Copyright of this program is the property of 88 Creative, 
 * without whose written permission reproduction in
 * whole or in part is prohibited. All rights reserved.
 * http://www.inform8.com
 * http://www.88creative.com.au
 */
package com.eighty8.inform8.db;

import com.eighty8.inform8.db.table.BasicColumn;
import com.eighty8.inform8.db.table.Inform8Column;
import com.eighty8.inform8.db.types.FieldType;
import com.eighty8.inform8.displaysettings.Label;

public class MysqlTableMemberFactory {

	private final MysqlFieldFactory fieldFactory;

	public MysqlTableMemberFactory() {
		fieldFactory = new MysqlFieldFactory();
	}

	public Inform8Column createTableMember(String row, int displayIndex) {
		String[] split = row.split("`");
        String memberName = split[1].trim();
		memberName = String.format("%s%s", Character.toUpperCase(memberName.charAt(0)), memberName.substring(1));

		String restofRow = row.trim().substring(2+memberName.length());
		
		final BasicColumn newMember = new BasicColumn(memberName, displayIndex);
		FieldType type = fieldFactory.getType(row);
		newMember.setType(type);
		newMember.getSettings().getProperties().putAll(type.getProperties());
		
		fieldFactory.setProperties(row, newMember);
		
		if (restofRow.toLowerCase().contains("not null")) {
			newMember.setNotNull(true);
			newMember.getSettings().addLabel(Label.NOT_NULL);
		}
		if (restofRow.toLowerCase().contains("default")) {
			String defaultVal = restofRow.substring(restofRow.toLowerCase().indexOf("default"));
//			`End` DATE default NULL,
			if (defaultVal.contains("'")) {
			    defaultVal = defaultVal.substring(defaultVal.indexOf("'")+1);
	            defaultVal = defaultVal.substring(0, defaultVal.indexOf("'"));
	            newMember.setDefaultValue(defaultVal);  
			}else {
			  defaultVal = defaultVal.trim().replaceAll(",", "");
			  newMember.setDefaultValue(defaultVal);
			}
			
		}
		return newMember;
	}

}
