<!--- Copyright 2000-2001, Ektron, Inc. --->
<!--- Revision Date:23-Mar-2001 --->

<!-- #include file="functions.asp" -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<meta http-equiv="pragma" content="no-cache">
	<title>Media Information</title>
</head>
<body bgcolor="d3d3d3">

<%
if (Request.QueryString("filesize") > 0) then
%>
	<table width="100%" height="100%">
		<tr>
			<td>
			<font face="arial" size="2">
				<b>File Information:</b>
			</font>
			</td>
		</tr>
		<tr>
			<td>
			<font face="arial" size="2">
				Filelength: <%=(Request.QueryString("filesize"))%> Bytes
			</font>
			</td>
		</tr>
		<tr>
			<td>
			<font face="arial" size="2">
				Width: <%=(Request.QueryString("width"))%>
			</font>
			</td>
			<td>
			<font face="arial" size="2">
				Height: <%=(Request.QueryString("height"))%>
			</font>
			</td>
		</tr>
	</table>
<%
else
%>
	<table width="100%" height="100%">
		<tr>
			<td>
			<font face="arial">
				<b>File Information:</b>
			</font>
			</td>
		</tr>
		<tr>
			<td>
			<font face="arial">
				Not Available
			</font>
			</td>
		</tr>
	</table>
<%
end if
%>

</body>
</html>
