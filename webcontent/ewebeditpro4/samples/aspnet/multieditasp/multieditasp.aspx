<!--  
	Copyright 2002,	Ektron Inc.  Amherst NH  
	Latest Revision: 2002-10-03
-->

<html>

<head>
<title>eWebEditPro</title>
<!-- #Include file="../../../ewebeditpro.aspx" -->
</head>

<body>

<%
Dim bPreview
bPreview = Request.QueryString("preview")
Dim strContent1
Dim strContent2
	
If bPreview Then
%>
	<% =Request.Form("Text1") %>
	<br><hr>
	<% =Request.Form("TextHTML1") %>
	<br><hr>
	<% =Request.Form("TextHTML2") %>
	<br><br><center><a href="javascript:window.history.back()"><font size=2 face="Arial">BACK</font></a></center>
<%
Else
	strContent1 = "<p>Initial content 1</p>"
	strContent2 = "<p>Initial content 2</p>"
%>
<p>
<form name="frmMain" action="multieditasp.aspx?preview=true" method="POST">

	Enter some text: <input type="text" size=80 name="Text1" value="Sample text">
	<% =eWebEditProPopupButton("btnEditText1", "Text1") %>
	<hr>
	Area 1:<br>
	<% =eWebEditProEditor("TextHTML1", "100%", 250, strContent1) %>
	<hr>
	Area 2:<br>
	<% =eWebEditProEditor("TextHTML2", "100%", 250, strContent2) %>
	
	<br><center><input type="submit" name="btnSubmit" value="Preview"></center>
</form>
<%
End If
%>
</body>
</html>
