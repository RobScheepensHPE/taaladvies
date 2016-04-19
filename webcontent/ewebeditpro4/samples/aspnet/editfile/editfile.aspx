<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!--  
	Copyright 2001-2002, Ektron Inc., Amherst NH  
	Latest Revision: 8-Nov-02
-->

<html>
<head>
	<title>Edit File</title>
<!-- #Include file="..\..\..\ewebeditpro.aspx" -->
</head>

<body>
<%
Const cACTIONPAGE = "editfile.aspx"
Dim strContent
Dim objFSO
Dim objFile
Dim bMapPath
Dim strFilename
Dim strPath
Dim strBaseURL 
Dim P
Dim bSave

objFSO = CreateObject("Scripting.FileSystemObject")

bMapPath = True
strFilename = Request.Form("Filename")
If Len(strFilename) = 0 Then
	strFilename = Request.QueryString("name")
End If
If Len(strFilename) = 0 Then
	strFilename = Request.QueryString("file")
	bMapPath = False
End If

bSave = Request.QueryString("save")

' **********************************************************************
' FOR SECURITY REASONS, DO NOT ALLOW OPEN OR SAVE
' To enable Open and Save and permit files to be read from and written to
' your web server, remove the next five lines.
strFilename = "" ' PREVENT OPEN OF ANY FILE FROM THE SERVER FILE SYSTEM. Comment this line if you like to allow open of any file from the server file system.
bSave = False ' PREVENT SAVE TO THE SERVER FILE SYSTEM. Comment this line if you like to allow save to the server file system.
%>
<font size=4 color="red" face="Arial, Helvetica">For security reasons, the Open and Save features have been disabled. Access to the server's file system should only be allowed within a secured, password protected environment.</font>
<%
' **********************************************************************

If Request.ServerVariables("SERVER_PORT_SECURE") = "1" Then
	strPath = "https://"
Else
	strPath = "http://"
End If
strPath = strPath & Request.ServerVariables("SERVER_NAME") 
If Request.ServerVariables("SERVER_PORT") <> "80" Then
	strPath = strPath & ":" & Request.ServerVariables("SERVER_PORT")
End If
strPath = strPath & Request.ServerVariables("PATH_INFO")
strPath = Left(strPath, Len(strPath) - Len(cACTIONPAGE))

Dim strServerFilename
If Len(strFilename) > 0 Then
	If bMapPath Then
		strServerFilename = Server.MapPath(strFilename)
	Else
		strServerFilename = strFilename
	End If
Else
	strServerFilename = ""
End If

Dim strErrMsg
strErrMsg = ""
Err.Clear
If bSave Then
	strContent = Request.Form("Content") ' entire document
	On Error Resume Next
	objFile = objFSO.CreateTextFile(strServerFilename)
	If Err.Number = 0 Then
		objFile.Write(strContent)
	Else
		strErrMsg = "Unable to save file: " & strServerFilename
	End If
ElseIf Len(strFilename) > 0 Then
	On Error Resume Next
	objFile = objFSO.OpenTextFile(strServerFilename)
	If Err.Number = 0 Then
		strContent = objFile.ReadAll
	Else
		strErrMsg = "Unable to open file: " & strServerFilename
	End If
Else
	strContent = ""
End If

P = InStrRev(strFilename, "/")
If P > 0 Then
	strBaseURL = Left(strFilename, P)
Else
	strBaseURL = strFilename
End If

%>
<h1 style="{font-family: sans-serif; font-size: large}">Edit Static HTML File</h1>
<form name="frmMain" action="<% =cACTIONPAGE %>" method="post">
<input type=submit name="btnOpen" value="Open">&nbsp;&nbsp;&nbsp;
<input type=submit name="btnSave" value="Save" onclick='frmMain.action += "?save=true"'>
<%
If Len(strErrMsg) > 0 Then
%>
<br><br>
<font face="Arial,Helvetica" color=red size=2><b><% =strErrMsg %></b></font>
<%
End If
%>
<br><br>
<font face="Arial,Helvetica" size="3"><b>File name:</b></font>
<input type="text" name="Filename" maxlength="1024" size="64" value="<% =strFilename %>">
<br><br>
<font face="Arial,Helvetica" size="3"><b>Contents:</b></font>
<br>

<script language="JavaScript1.2">
<!--
	eWebEditPro.parameters.editorGetMethod = "getDocument"; // Get the entire document, not just the body.
	eWebEditPro.parameters.baseURL = "<% =strBaseURL %>";
//-->
</script>

<% =eWebEditProEditor("Content", "100%", "70%", strContent) %>

<br><br>
</form>
<% If bSave Then %>
<font face="Arial,Helvetica" size="2"><a href="<% =strFilename %>" target="_blank">View <% =strFilename %></a></font>
<% End If %>
</body>
</html>
