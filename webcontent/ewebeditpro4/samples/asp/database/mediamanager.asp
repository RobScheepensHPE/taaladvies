<!--   Ektron Inc.  Amherst NH  Copyright (©) 1999 -->
<!--- 
  Name: imagemanager.cfm
   
  Descr:  Opens 5 Frames for uploading images, viewing images from the image library
  
  Author, date: William Rogers, 11/9/99
  
  Notes: 
  
--->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<title>Insert Media Item</title>
	</head>
	<frameset cols="300,300" border="1" bordercolor="Gray">
		<frameset rows="350,250" border="1" bordercolor="Gray">
	   		<frame name="medialist" src="medialist.asp?editorname=<%=(Request.QueryString("editorname"))%>" marginwidth="2" marginheight="2" scrolling="no" frameborder="0">
			<frame name="mediauploader" src="mediauploader.asp?editorname=<%=(Request.QueryString("editorname"))%>&upload=<%=(Request.QueryString("upload"))%>" marginwidth="2" marginheight="2" scrolling="no" frameborder="0">
		</frameset> 
		<frameset rows="100,425,75" border="1" bordercolor="Gray">
			<frame name="mediainfo" src="mediainformation.asp" marginwidth="2" marginheight="2" scrolling="no" frameborder="0">	
		    <frame name="mediapreview" src="mediapreview.asp?editorname=<%=(Request.QueryString("editorname"))%>" marginwidth="2" marginheight="2" scrolling="auto" frameborder="0">	
			<frame name="mediainsert" src="mediainsert.asp?editorname=<%=(Request.QueryString("editorname"))%>" marginwidth="2" marginheight="2" scrolling="no" frameborder="0">	
		</frameset>
	</frameset>
<body bgcolor="d3d3d3" onload="javascript:self.focus();">
</body>
</html>
