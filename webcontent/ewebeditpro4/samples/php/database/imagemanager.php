<?php require("globals.php"); ?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title>Library</title>
</head>
<?php 
	if (isset($exit)) {
		echo "
		
		";
	}
	else {
	
		echo "
		<frameset cols=\"300,300\" border=\"1\" bordercolor=\"Gray\">
	   		<frameset  rows=\"50%,*\"  border=\"1\" bordercolor=\"Gray\">
		    	<frame src=\"imagelist.php\" frameborder=\"No\" scrolling=\"Auto\" noresize marginwidth=\"0\" marginheight=\"0\" name=\"imagelist\">
			    <frame src=\"imageuploader.php\" name=\"upload\" frameborder=\"No\" scrolling=\"No\" noresize marginwidth=\"0\" marginheight=\"0\">
			</frameset>
		    <frameset rows=\"15%, *\"  border=\"1\" bordercolor=\"Gray\">
				<frame src=\"imageinformation.php\" name=\"imageinfo\" frameborder=\"No\" scrolling=\"Auto\" noresize marginwidth=\"0\" marginheight=\"0\">
				<frame src=\"imagepreview.php\" name=\"preview\" frameborder=\"No\" scrolling=\"Auto\" noresize marginwidth=\"0\" marginheight=\"0\">
				
			</frameset>
		</frameset>
		";
	}
?>
</html>