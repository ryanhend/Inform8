<?php
	$HostName =   "localhost";
	$DBName =     "dbname";
	$DBUsername = "root";
	$DBPassword = "root";
	
	$mysqli = new mysqli($HostName, $DBUsername, $DBPassword, $DBName);
	if ($mysqli->connect_error) {
	  include 'config/includes/pages/sitedown.php';
	  die();
	}
	
	Inform8Context::setDbConnection($mysqli);
?>