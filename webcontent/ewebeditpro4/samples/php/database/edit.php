<?php
	include("../../../ewebeditpro.php");
	require("globals.php");
	include("ewebeditprofunctions.php");
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!--
	Ektron, Inc. Copyright 2001
	03-19-2001
 -->
<html>
<head>
	<title>eWebEditPro</title>
</head>
<body>
<form action="action.php" method="post" name="frmMain">
<?php 
	if (isset($_GET["edit_id"])) {
		$edit_id = $_GET["edit_id"];
		$dboConnect = odbc_connect( "$gstrDataSourceName", "", "", "" );
		$qs_html = odbc_exec( $dboConnect, "SELECT edit_title, edit_html FROM wysiwyg_tbl WHERE edit_id = $edit_id" );
		$title = odbc_result( $qs_html, 1 );
		$html = odbc_result( $qs_html, 2 );
		odbc_close( $dboConnect );
		
		echo "<input type=\"hidden\" name=\"edit_id\" value=\"$edit_id\">";
	}
	else {
		$title = "";
		$html = "";
	}
	echo "
	<table width=\"80%\">
	<tr>
		<td height=\"5%\"><font face=\"verdana\" size=\"2\">Title: </font></td>
		<td><input type=\"Text\" name=\"edit_title\" value=\"$title\" size=\"30\" maxlength=\"50\"></td>
		<td align=\"right\">";
	echo "<input type=\"Submit\" value=\"&nbsp;&nbsp;Save&nbsp;&nbsp;\"> <input type=\"Button\" onclick=\"document.location.href='index.php';\" value=\"Cancel\"> ";
	echo "</td></tr>";
	echo "</table>\n";
	echo "<p>";
	echo eWebEditProEditor("edit_html", "75%", "95%", "$html");
	
?>
		<script language="JavaScript1.2">
		<!--
		eWebEditPro.addEventHandler("onready", "initTransferMethod(eWebEditPro.event.srcName, 'imagemanager.php?editor=' + eWebEditPro.event.srcName)");
		function initTransferMethod(sEditor, strURL)
		{
			if (eWebEditPro.instances[sEditor].editor)
			{
				eWebEditPro.instances[sEditor].editor.MediaFile().setProperty("TransferMethod", strURL);
			}
		}
		// -->
		</script>
</form>

</body>
</html>
