<?php 
/* Copyright 2011 - 88 Creative Pty Ltd. 
 * Copyright of this program is the property of 88 Creative, 
 * without whose written permission reproduction in
 * whole or in part is prohibited. All rights reserved.
 * http://www.inform8.com
 * http://www.88creative.com.au
 */
?><?php
  // site settings + config + seession load
  require_once 'config/settings.php';

	//TODO add checks to ensure table ok.
	$reqobj = Request::getSafeGetOrPost("object");
	$field = Request::getSafeGetOrPost("field");
	$value = Request::getSafeGetOrPost("value");
	$fields = Request::getSafeGetOrPost("fields");
    $expanded = Request::getSafeGetOrPost("expanded");

	$fieldsArray = array('*');
	$jsonFieldsArray = NULL;
	if($fields != NULL && $fields != '') {
	  $fieldsArray = explode(',', $fields);
	  $jsonFieldsArray = $fieldsArray;
	}
	
//	print($reqobj);
//	print($field);	
//	print($value);	
	
	$reqDao = $reqobj."Dao";
	$reqIql = $reqobj."IQL";
	$dao = new $reqDao();
	
	$reqJsonBuilder = $reqobj."JsonBuilder";
	
	$builder = new $reqJsonBuilder($jsonFieldsArray);
	
	if ($field == 'ALL' && $value == 'ALL') {
		$objs = call_user_func($reqIql. '::selectFields', $fieldsArray)->get();
	} else {
		$query = call_user_func($reqIql . '::selectFields', $fieldsArray);
		$objs = $query->where(NULL, $field, '=', $value)->get();
	}
	
	if (isset($objs) && is_array($objs)) {
	  if($expanded) {
	    echo $builder->toExpandedJson($objs);
	  }else {
	    echo $builder->toJson($objs);
	  }
	}
?>