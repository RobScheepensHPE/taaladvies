<?php
	require("globals.php");
	include("ewebeditprofunctions.php");
?>
<!--
	Ektron, Inc. Copyright 2001
	03-19-2001 
	-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title>eWebEditPro</title>
</head>
<body bgcolor="White">
<?php 

	if (isset($_GET["preview_id"])) {
		$preview_id = $_GET["preview_id"];
		echo "<font face=\"verdana\" size=\"2\"><b>Title:</b></font>";
		echo eWebEditProEditorViewContent ( $gstrDataSourceName, $preview_id, "edit_title" );
		echo "<br><br>";
		echo eWebEditProEditorViewContent ( $gstrDataSourceName, $preview_id, "edit_html" );

	}
?>
</body>
</html>
