<?php
	/*
		File ewebeditprofunctions.php
		coypright 2001 Ektron, Inc. (http://www.ektron.com)
	*/

	
	// Function: eWebEditProEditorImageUpload()
	// Purpose:  Uploads images to the ewebeditpro image library
	// Parameters:
	//		DataSourceName:		(String) The name of image labrary datasource value is specified in the globals.php file ($gstrDataSourceName)
	//		ImageTitle:			(String) The title of the image (completes the alt attribute in the img HTML tag.  Value is specied in the description field
	//		FileName:			(String) The name of the file without path information
	//		Location:			(String) The physical path on the server where the file is to be copied.  Value is defined in the globals.asp file ($imagepath) 
	//		ImageDirectory:		(String) The path, relative to the root of the site, where the image is stored. value is specified in the globals.php file ($gstrImageUploadDirectory)
	//		file_size			(string) The size of the image (in bytes)
	//		width				(string) the width of the image
	//		height				(string) the height of the image
	function eWebEditProEditorImageUpload( $DataSourceName, $ImageTitle, $Image, $FileName, $Location, $ImageDirectory, $file_size, $width, $height) {
		if (copy( $Image, $Location."/".$FileName )) {
			$strMsg = $FileName." uploaded successfully.";
			eWebEditProEditorUpdateImageLibrary ( $DataSourceName, $ImageTitle, $FileName, $ImageDirectory, $file_size, $width, $height);
		}
		else {
			$strMsg = $FileName." could not be uploaded.";
		}
		unlink($Image);
		return $strMsg;
	}
	
		// Function: eWebEditProEditorUpdateImageLibrary()
	// Purpose:  Uploads the image library database
	// Parameters:
	//		DataSourceName:	(String) The name of image library datasource
	//		ImageTitle:		(String) The name of title of the inserted into the database
	//		FileName:			(String) The name of the file without path information
	//		ImageDirectory:		(String) The path, relative to the root of the site, where the image is stored.
	//		file_size			(string) The size of the image (in bytes)
	//		width				(string) the width of the image
	//		height				(string) the height of the image
	function eWebEditProEditorUpdateImageLibrary ( $DataSourceName, $ImageTitle, $FileName, $ImageDirectory, $file_size, $width, $height) {
		$dboConnect = odbc_connect( "$DataSourceName", "", "", "" );
		if (!$dboConnect) {
			echo "error connecting to database...";
		}
		else {
			$qs_html = odbc_exec( $dboConnect, "INSERT INTO media_tbl (media_title, media_path, media_filename, media_filesize, user_name, site_id, media_deleted, extension_id, media_width, media_height) values ('$ImageTitle', '$ImageDirectory', '$FileName', '$file_size', 'user name', 0, 0,1, '$width', '$height')" ); 
			odbc_close( $dboConnect );
		}
		
		echo "<script language=\"javascript\">
var editorname=top.opener.eWebEditPro.event.srcName;
top.opener.eWebEditPro.instances[editorname].insertMediaFile('$ImageDirectory$FileName', 0, '$ImageTitle', 'image', $width, $height);
top.close();
</script>";
	}

	// Function: eWebEditProEditorGenerateContentLinks()
	// Purpose:  Generates the list of links for maintaining the content in the eWebEditPro sample
	// Parameters:
	//		DataSourceName:		(String) The name of content datasource
	function eWebEditProEditorGenerateContentLinks ( $DataSourceName ) {
		$addpage = "edit.php";
		$editpage = "edit.php";
		$viewpage = "view.php";
		$deletepage = "action.php";
	
		$dboConnect = odbc_connect( "$DataSourceName", "", "", "" );
		if (!$dboConnect) {
			echo "error connecting to database...";
		}
		else {
			$qs_html = odbc_exec( $dboConnect, "SELECT edit_id, edit_title, edit_html FROM wysiwyg_tbl ORDER BY 1" );
			echo "
			<script language=\"JavaScript\">
				function ConfirmDelete(sPath){
					if (confirm(\"Are you sure you want to delete this content?\")){
						document.location.href=sPath;
					}
				}
			</script>
			<table cellspacing=\"0\" cellpadding=\"2\" width=\"100%\" border=\"1\">
			<tr bgcolor=\"black\">
				<td width=\"5%\"><font size=\"2\" face=\"Verdana\" color=\"White\"><b>ID</b></font></td>
				<td width=\"75%\"><font size=\"2\" face=\"Verdana\" color=\"White\"><b>Title</b></font></td>
				<td width=\"20%\" align=\"center\"><font size=\"2\" face=\"Verdana\" color=\"White\"><b>Available Actions</b></font> <a href=\"$addpage?add=x\"><font size=\"2\" face=\"Verdana\" color=\"White\"><b>[Add]</b></font></a></td>
			</tr>\n";
			$x = 0;
			while( odbc_fetch_row( $qs_html )){
				$id = odbc_result( $qs_html, 1 );
				$title = odbc_result( $qs_html, 2 );
				$html = odbc_result( $qs_html, 3 );
				if ($x % 2) {
					$bgcolor="eeeeee";
				}
				else {
					$bgcolor="ffffff";
				}
				echo "<tr bgcolor=\"$bgcolor\">\n";
				echo "<td><font face=\"verdana\" size=\"2\">$id</font></td>\n";
				echo "<td><font face=\"verdana\" size=\"2\">$title</font></td>\n";
				echo "<td colspan=\"2\" align=\"center\"><font size=\"2\" face=\"Verdana\"><a href=\"$editpage?edit_id=$id\">Edit</a> | <a href=\"#\" onClick=\"ConfirmDelete('$deletepage?delete_id=$id');\">Delete</a> | <a href=\"$viewpage?preview_id=$id\">Preview</a></font></td>";
				echo "</tr>\n";
				$x += 1;
			} 
			echo "
			<tr>
				<td colspan=\"4\">
					<font face=\"verdana\" size=\"2\">
					<b>Note:</b><br>
					Add - Insert content into the Database<br> 
					Edit - Modify content and update the Database<br>
					View - View content<br> 
					Delete - Delete content from the Database<br>
					</font>
				</td>
			</tr>
			</table>\n";
			odbc_close( $dboConnect );
		}
	}
	
	
	// Function: eWebEditProEditorViewContent()
	// Purpose:  Returns the selected content to the calling module
	// Parameters:
	//		DataSourceName:	(String) The name of content datasource
	//		ID:				(Number) The id of the content to be retrieved
	//		Field:			(String) The field to retrieve from the content table
	function eWebEditProEditorViewContent ( $DataSourceName, $ID, $Field ) {
		$dboConnect = odbc_connect( "$DataSourceName", "", "", "" );
		$qs_html = odbc_exec( $dboConnect, "SELECT $Field FROM wysiwyg_tbl WHERE edit_id = $ID");
		$sReturnField = odbc_result( $qs_html, 1 );
		odbc_close( $dboConnect );
		return $sReturnField;
	}
?>