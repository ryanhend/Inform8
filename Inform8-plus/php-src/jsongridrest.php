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

	//TODO add checks
	$reqobj = Request::getOrPost("object");
	$field = Request::getOrPost("field");
	
	$searchOption = Request::getOrPost("searchoption");
	
	$searchType = Request::getOrPost("searchtype");
	if($searchType == 'fk') { // foreign key
		$value = Request::getOrPost("fkValue");
	}else if($searchType == 'en') { //enum
		$value = Request::getOrPost("enumValue");
	}else if($searchType == 'dt') { // date
		$value = Request::getOrPost("datepickervalue");
	}else if($searchType == 'bl') { // date
		if($searchOption == 'tru') {
			$value = 1;
		}else {
			$value = 0;
		}
	}else {	// value
		$value = Request::getOrPost("value");
	}

	$expanded = Request::getOrPost("expanded");

	$page = Request::getOrPost('page'); // get the requested page 
	$limit = Request::getOrPost('rows'); // get how many rows we want to have into the grid 
	$sidx = Request::getOrPost('sidx'); // get index row - i.e. user click to sort 
	$sord = Request::getOrPost('sord'); // get the direction 
	$start = $limit*$page - $limit;
	//print_r($_GET);
	
	$reqDao = $reqobj."Dao";
	$dao = new $reqDao();

	$reqDef = $reqobj."Definition";
	$def = new $reqDef();

	$reqBuilder = $reqobj."JqGridBuilder";
	$builder = new $reqBuilder();

	$joinTable = Request::getSafeGetOrPost('joinTable');
	$joinField = Request::getSafeGetOrPost('joinField');
	$otherJoinField = Request::getSafeGetOrPost('otherJoinField');
	
    Inform8Context::getLogger()->log(0, 'jt:' . $joinTable);
    Inform8Context::getLogger()->log(0, 'jf:' . $joinField);
    Inform8Context::getLogger()->log(0, 'ojf:' . $otherJoinField);
	
	$totalCount = 0;
	if ($field == 'ALL' && $value == 'ALL') {
		$objs = $dao->getAll($limit, $start, $sidx, $sord);
		$totalCount = $dao->countAll();
		if( $totalCount >0 ) { 
			$totalPages = ceil($totalCount/$limit); 
		} else { 
			$totalPages = 0; 
		} 
	}else {
	  Inform8Context::getLogger()->log(0, 'jt:' . $joinTable);
		if ($joinTable != null && $joinTable != '') {
			$reqJoinIql = $joinTable."IQL";
			
			$joinobs = IQL::select($joinTable, array('*'))->where(null, $joinField, '=', $value)
			  ->start($start)->limit($limit)->get();
			  
			$totalCount = IQL::count($joinTable, '*')->where(null, $joinField, '=', $value)->get();
			
			if( $totalCount >0 ) { 
				$totalPages = ceil($totalCount/$limit); 
			} else { 
				$totalPages = 0; 
			} 			
			$objs = array();
			$accesor = 'get'.$otherJoinField.'AsObject';
			if($joinobs != -1) {
					foreach($joinobs as $join) {
						$objs[] = $join->$accesor();
					}
			}else {
				$objs=-1;
			}
		}else {		
		  $q = IQL::select($reqobj);
		  if (strpos($field, '.') > 0) {
		      $fields = explode('.', $field);
		      $field = $fields[1];
		  }
		  addWhereClause($q, NULL, $field, $value, $searchOption);
		  $objs = $q->start($start)->limit($limit)->orderBy(NULL, $sidx, $sord)->get();
		  
		  $q = IQL::count($reqobj);
		  addWhereClause($q, NULL, $fields[1], $value, $searchOption);
		  $totalCount = $q->get();
		   
    		if( $totalCount >0 ) { 
    			$totalPages = ceil($totalCount/$limit); 
    		} else { 
    			$totalPages = 0; 
    		} 
		}
	}
	
	if ($objs == -1) {
    $response = new stdClass();
    $response->page = 0; 
    $response->total = 0; 
    $response->records = 0;
    echo json_encode($response);    
	}

	$objCount = count($objs);
	if ($objs != -1) {
		$response = new stdClass();
		$response->page = $page; 
		$response->total = $totalPages; 
		$response->records = $totalCount;
		
		for ($i=0; $i < $objCount; $i++) {
			$response->rows[$i]=$builder->toJqGridArray($objs[$i]); 
		}		
 
		echo json_encode($response); 		
	}
	
	
	function addWhereClause($query, $table, $field, $value, $searchOption) {
        if ($searchOption == 'cnt') {
            $query->where($table, $field, 'LIKE', '%'.$value.'%');
        }else if ($searchOption == 'beg') {
            $query->where($table, $field, 'LIKE', $value.'%');
        }else if ($searchOption == 'end') {
            $query->where($table, $field, 'LIKE', '%'.$value);
        }else if ($searchOption == 'neq') {
            $query->where($table, $field, '!=', $value);
        }else if ($searchOption == 'lt') {
            $query->where($table, $field, '<', $value);
        }else if ($searchOption == 'lte') {
            $query->where($table, $field, '<=', $value);
        }else if ($searchOption == 'gt') {
            $query->where($table, $field, '>', $value);
        }else if ($searchOption == 'gte') {
            $query->where($table, $field, '>=', $value);
        }else if ($searchOption == 'tru') {
            $query->where($table, $field, '=', 1);
        }else if ($searchOption == 'fls') {
            $query->where($table, $field, '=', 0);
        }else {
            $query->where($table, $field, '=', $value);
        }   
    
    
    }
	
	
?>