<!-- 
This page is used to preview an image
 -->
<?php
	require("globals.php");
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title>Image List</title>
</head>
<body bgcolor="d3d3d3" leftmargin=0 topmargin=0>

<?php
	if (isset($id)) {
		$dboConnect = odbc_connect( "$gstrDataSourceName", "", "", "" );
		$query = odbc_exec( $dboConnect, "SELECT media_id, media_path, media_filename, media_title, media_filesize, media_width, media_height FROM media_tbl WHERE media_id = $id" );
		while( odbc_fetch_row( $query )){ 
			$id =  odbc_result( $query, 1 );
			$media_path = odbc_result( $query, 2 );
			$imagename = odbc_result( $query, 3 );
			$title = odbc_result( $query, 4 );
			$file_size = odbc_result( $query, 5);
			$width = odbc_result( $query, 6 );
			$height = odbc_result( $query, 7 );
			
		} 
		odbc_close( $dboConnect );
		echo "<img src=\"$media_path$imagename\" alt=\"$title\" border=\"0\">";
		echo "<script language=\"javascript\">top.imageinfo.location.href=\"imageinformation.php?filesize=$file_size&width=$width&height=$height\";</script>";
	}
	elseif (isset($imagename)) {
	echo "<img src=\"file:///$imagename\" border=\"0\">";
	}
	else {
		echo "
<table height=\"100%\" width=\"100%\">
<tr>
	<td align=\"center\"><h3><font face=\"verdana\">Preview</font></h3></td>
</tr>
</table>";
	}
?>
</body>
</html>
