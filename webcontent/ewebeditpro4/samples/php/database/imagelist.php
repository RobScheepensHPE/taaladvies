<?php

	require("globals.php");
	include("ewebeditprofunctions.php");
?>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title>Library</title>
	<?php
//deletes an image 
	$dboConnect = odbc_connect( "$gstrDataSourceName", "", "", "" );
	if (isset($id)){
		$query = odbc_exec( $dboConnect, "DELETE FROM media_tbl WHERE media_id = $id" );
	}
	else {
?>
	<script language="JavaScript">
		function InsertFile(nID, strTitle) {

			var	strImage="";
			var strTitle="";
			var strWidth="";
			var strHeight="";
			switch (nID) {
		
				<?php 
				//get list of images from the database
				$query = odbc_exec( $dboConnect, "SELECT media_id, media_title, media_path, media_filename, media_width, media_height FROM media_tbl ORDER BY media_title");
					while( odbc_fetch_row( $query )){ 
						$id = odbc_result( $query, 1 );
						$title=odbc_result( $query, 2);
						$loc = odbc_result( $query, 3 );
						$file_name = odbc_result( $query,  4);
						$width= odbc_result( $query,  5);
						$height= odbc_result( $query,  6);
						echo "case \"$id\": strImage= \"$loc$file_name\"; strTitle=\"$title\"; strWidth=\"$width\";strHeight=\"$height\";  break;\n";
					} 
				?>
			}
			if (!top.opener.closed){ //Make sure window is not closed
			strEditorName=top.opener.eWebEditPro.event.srcName;
			top.opener.eWebEditPro.instances[strEditorName].insertMediaFile(strImage, 0, strTitle, 'image', strWidth, strHeight);//insert image.  insertMediaFile is documented in the developer's reference guide.
				top.close();//close window
			}
			else { //if window is closed, image can't be inserted.
				alert("Your image could not be insert as the editing window has been closed.");
			}
		}
		function doInsert() {
		if (document.imageform.id.selectedIndex != -1) {
				InsertFile(document.imageform.id.options[document.imageform.id.selectedIndex].value, document.imageform.id.options[document.imageform.id.selectedIndex].text);
			}
			else {
				alert('You need to select an image first');
				return false;
			}
		}
	</script>
	<?php } ?>
</head>
<body bgcolor="d3d3d3" leftmargin=0 topmargin=0 marginheight=0 marginwidth=0>
<table cellpadding=0 cellspacing=0 width="100%">
<tr>
	<td>
		<table cellpadding=1 cellspacing=1 width="100%">
		<form name="imageform" action="imagelist.php" method="post">
		<input type="Hidden" name="DeleteID" value="">
		<tr>
			<tdvalign="top">
				<font face="verdana" size="2"><b>Select an Existing File:</b></font><br>

			</td>
		</tr>
		<tr>
			<td>
				<select name="id" size="8" onChange="top.preview.location.href = 'imagepreview.php?id=' + this.options[this.selectedIndex].value;">
				<?php 
					$query = odbc_exec( $dboConnect, "	Select media_id, media_title, media_path, media_filesize, media_width, media_height, media_filename AS filename
	From media_tbl
	WHERE media_deleted = 0
	Order by media_title" );
					while( odbc_fetch_row( $query )){ 
						$id = odbc_result( $query, 1 );
						$title = odbc_result( $query, 2 );
						echo "\n<option value=\"$id\">$title";
					} 
					odbc_close( $dboConnect );
				?>
				</select>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="button" name="button1" value="Insert" onClick="doInsert();" style="font-family: Verdana;"> &nbsp;<input type="Submit" name="Delete" value="Delete" style="font-family: Verdana;">
			</td>
		</tr>
		</form>
		</table>
	</td>
</tr>
</table>
</body>
</html>
