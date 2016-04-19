<?php
	
	require("globals.php");
	include("ewebeditprofunctions.php");

	$dboConnect = odbc_connect( "$gstrDataSourceName", "", "", "" );
	if ( isset( $_POST["edit_id"] ) && isset ( $_POST["edit_title"] ) && isset ( $_POST["edit_html"] ) ) {
		$edit_id = $_POST["edit_id"];
		$edit_title = $_POST["edit_title"];
		$edit_html = $_POST["edit_html"];
		
		$strContentHTML =  stripslashes ( ereg_replace( "'", "''", $edit_html ) );
		$qu_html = odbc_exec( $dboConnect, "UPDATE wysiwyg_tbl SET edit_title = '$edit_title', edit_html = '$strContentHTML' WHERE edit_id = $edit_id" );
	}
	elseif (isset($_POST["edit_title"]) && isset($_POST["edit_html"])) {
		$edit_title = $_POST["edit_title"];
		$edit_html = $_POST["edit_html"];
		$strContentHTML = stripslashes (ereg_replace ( "'", "''", $edit_html ) );
		$qi_html = odbc_exec( $dboConnect, "INSERT INTO wysiwyg_tbl (edit_title, edit_html) VALUES('$edit_title', '$strContentHTML')" );
	}
	elseif (isset($_GET["delete_id"])) {
		$delete_id = $_GET["delete_id"];
		$qd_html = odbc_exec( $dboConnect, "DELETE FROM wysiwyg_tbl WHERE edit_id = $delete_id" );
	}
	odbc_close( $dboConnect );
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title>eWebEditPro</title>
</head>
<body onload="document.location.href='index.php';">
<script language="JavaScript">
	document.write("<font face=\"Verdana\" size=\"2\"><a href=\"index.php\">Back to index</a></font>");
</script>
</body>
</html>
