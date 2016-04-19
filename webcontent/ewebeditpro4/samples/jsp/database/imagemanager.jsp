
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title>Library</title>
</head>
<% String editorname = request.getParameter("editor"); %>
		<frameset cols="300,300" border="1" bordercolor="Gray">
	   		 <frameset  rows="50%,*"  border="1" bordercolor="Gray">
		    	<frame src="imagelist.jsp?editor=<%= editorname %>" name="library" frameborder="No" scrolling="Auto" noresize marginwidth="0" marginheight="0">
<frame src="imageuploader.jsp?editor=<%= editorname %>" name="upload" frameborder="No" scrolling="No" noresize marginwidth="0" marginheight="0">
			    </frameset>
				<frameset  rows="15%,*"  border="1" bordercolor="Gray">
			<frame src="imageinformation.jsp?editor=<%= editorname %>" name="imageinfo" frameborder="No" scrolling="Auto" noresize marginwidth="0" marginheight="0">
			<frame src="imagepreview.jsp?editor=<%= editorname %>" name="preview" frameborder="No" scrolling="Auto" noresize marginwidth="0" marginheight="0">
		   		</frameset>
		</frameset>
</html>

