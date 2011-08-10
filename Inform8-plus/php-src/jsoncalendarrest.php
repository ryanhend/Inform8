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
	
	$calendarEntries = array();
	
	$tables = Request::getSafeGetOrPost('object');
	$tableList = array();
	
	if($tables == 'ALL') {
    $tableCount = count($JACK_TABLE_LIST);
    
    for ($i = 0; $i < $tableCount; $i++) {
      $table = $JACK_TABLE_LIST[$i];
      //echo $table;
      $reqDef = $table."Definition";
      $def = new $reqDef();
      if($def->getTable()->getDisplaySettings()->hasLabel('CALENDAR')) {
        $tableList[] = $def->getTable()->getName();
	    }
	  }
	}else {
     $tableList = explode(',', $tables);
	}
	
	$tableCount = count($tableList);
	for ($i = 0; $i < $tableCount; $i++) {
		$table = $tableList[$i];
		//echo $table;
    $reqDef = $table."Definition";
    $def = new $reqDef();
   	if($def->getTable()->getDisplaySettings()->hasLabel('CALENDAR')) { 

			$members = $def->getTable()->getMembers();
			$startField = "";
			$titleField = "";
			$endField = NULL;
			$completeField = NULL;
			$descriptionField = NULL;
			
			
    	foreach ( $members as $member ) {
      	if($member->getDisplaySettings()->hasLabel('CALENDAR_START')) {
					$startField = $member->getName();
       	}else if($member->getDisplaySettings()->hasLabel('CALENDAR_END')) {
       		$endField = $member->getName();
       	}else if($member->getDisplaySettings()->hasLabel('CALENDAR_COMPLETE')) {
       		$completeField = $member->getName();
       	}else if($member->getDisplaySettings()->hasLabel('CALENDAR_DESCRIPTION')) {
       		$descriptionField = $member->getName();
       	}else if($member->getDisplaySettings()->hasLabel('CALENDAR_TITLE')) {
       		$titleField = $member->getName();
       	}
			}

			$reqDao = $table."Dao";
			$dao = new $reqDao();
			$objs = $dao->getAll();
			if (!is_null($objs) && $objs != -1) {
	    	foreach ( $objs as $obj ) {
	    	  
	    	  $titleMethod  = 'get' . $titleField;
	    	  $startMethod  = 'get' . $startField;
	    	 
	       	  $tempEntry = array(
						'id' => $obj->getPk(),
						'title' => $obj->$titleMethod(),
						'start' => $obj->$startMethod(),
					  'url' => Tabs::viewInNewTabJs('Update', $table,  $obj->getPk(), WebContext::getLanguage()->get($table) . ' ' . $obj->getPk()) 
					);
					if($endField != NULL) {
					    $endMethod  = 'get' . $endField;
						$tempEntry['end'] = $obj->$endMethod();
					}
					$tempClassName = 'cal-'.$def->getTable()->name;
					if ($completeField != NULL) {
					    $completeMethod  = 'get' . $completeField();
						if ($obj->$completeMethod()) {
							$tempClassName .= ' jack-cal-complete';  
							$tempEntry['title'] = 'COMPLETE:' . $tempEntry['title'];
						}else {
							$tempClassName .= ' jack-cal-incomplete';
							$tempEntry['title'] = 'PENDING: ' . $tempEntry['title'];
						}
					}
					//if($descriptionField != NULL && $obj->$descriptionField != NULL && $obj->$descriptionField != '') {
					//	$tempEntry['description'] = $obj->$descriptionField;
					//}
					$tempEntry['className'] = $tempClassName;
					$calendarEntries[] = $tempEntry;
				}
			}
    }
	}
	echo json_encode($calendarEntries);
?>