<!--  
	Copyright 2001-2003, Ektron Inc.  
	Latest Revision: 15-Dec-2003
-->
<html>
<head>
<title>eWebEditPro</title>
<!-- #Include virtual="/ewebeditpro4/ewebeditpro.asp" -->
</head>
<body>
<%
bPreview = Request.QueryString("preview")
	
If TypeName(bPreview) = "String" Then
%>
	<% =Request.Form("Text1") %>
	<br><hr>
	<% =Request.Form("TextHTML1") %>
	<% If Request.Form("TextHTML1_isChanged") <> 0 Then %>
	<br><hr>
	<% =Request.Form("TextOnly1") %>
	<% End If %>
	<br><hr>
	<% =Request.Form("TextHTML2") %>
	<br><br><center><a href="javascript:window.history.back()"><font size=2 face="Arial">BACK</font></a></center>
<%
Else
	strContent1 = "<p>Initial content 1</p>"
	strContent2 = "<p>Initial content 2</p>"
%>
<p>
<form name="frmMain" action="multiedit.asp?preview" method="POST">

	Enter some text: <input type="text" size=80 name="Text1" value="Sample text">
	<% =eWebEditProPopupButton("btnEditText1", "Text1") %>
	<hr>
	Area 1:<br>
	<% =eWebEditProEditor("TextHTML1", "100%", 250, strContent1) %>
	<% =eWebEditProField("TextHTML1", "TextHTML1", "htmlbody", "htmlbody", "") %>
	<% =eWebEditProField("TextHTML1", "TextOnly1", "", "text", "") %>
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
