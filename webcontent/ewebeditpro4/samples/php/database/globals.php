<?php
//gets the host name
	$gstrHostName = "http://".getenv("server_name");
// the datasource for the database
	$gstrDataSourceName = "ewebeditpro4";
// the image upload path, relative from host
	$gstrImageUploadDirectory = "/ewebeditpro4/upload/";
// the full directory path to the image upload directory specified above.
	$imagepath=realpath("../../../upload/");

?>
