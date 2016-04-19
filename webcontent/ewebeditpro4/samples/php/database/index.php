<?php
	require("globals.php");
	include("ewebeditprofunctions.php");
?>
<!---
   Ektron, Inc. Copyright 2001  
   ' Index.php modify: 2001-03-19
   ' This collection of php files demonstrates the integration
   ' and typical use capabilities of eWebEditPro.  
   ' This page retrieves a list of documents stored in the 
   ' database (using an ADODB object).
 --->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title>PHP Sample Code</title>
	<meta http-equiv="pragma" content="no-cache">
</head>
<body bgcolor="White" text="Black" link="Black" vlink="Black" alink="Black">
<table width="100%">
<tr>
	<td><h3><a href="http://www.ektron.com"><font face="verdana"><b>Ektron, Inc.</b></font></a></h3></td>
	<td valign="top" align="right"><h3><font face="verdana"><b>eWebEditPro PHP Sample</b></font></h3></td>
</tr>
<tr>
	<td colspan="2"><font face="verdana" size="2"><a href="http://www.ektron.com">http://www.ektron.com</a><br><a href="mailto:sales@ektron.com">sales@ektron.com</a></font></td>
	
</tr>
<tr>
	<td colspan="2"></td>
</tr>
</table>
<?php 
	eWebEditProEditorGenerateContentLinks ( $gstrDataSourceName );
?>


</body>
</html>