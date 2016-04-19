<!--- Copyright 2000-2001, Ektron, Inc. --->
<!--- Revision Date:23-Mar-2001 --->

<!-- #include file="functions.asp" -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<meta http-equiv="pragma" content="no-cache">
	<title>Media Preview</title>
</head>
<body bgcolor="d3d3d3">

<%
if (Request.QueryString("media_id") > 0) then
%>
	<%
	Dim SQL, Record, nMedia, Media_recordcount, q_media_objConn, objMediaRS

	SQL = "SELECT    media_path, media_filename as filename, media_title" _
				& " FROM      media_tbl" _
				& " WHERE     media_id = " & Request.QueryString("media_id")

	Set q_media_objConn = Server.CreateObject("ADODB.Connection")
	q_media_objConn.ConnectionString = "DSN=" & dsn
	q_media_objConn.Open
	Set objMediaRS = Server.CreateObject("ADODB.RecordSet")
	objMediaRS.Open SQL, q_media_objConn
	%>
	<a href="<%=(objMediaRS("media_path") & objMediaRS("filename"))%>">Preview <%=(objMediaRS("media_title"))%></a>
	<%
	objMediaRS.Close
	q_media_objConn.Close
	%>
<%
elseif (Request.QueryString("medianame") <> "") then
%>
	<a href="file:///<%=(Request.QueryString("medianame"))%>">Preview <%=(Request.QueryString("medianame"))%></a>
<%
else
%>
	<table width="100%" height="100%">
		<tr>
			<td align="center">
			<font face="arial">
				<h3>Preview</h3>
			</font>
			</td>
		</tr>
	</table>
<%
end if
%>
</body>
</html>
