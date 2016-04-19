<%@ include file="../../../ewebeditpro.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
	<title>Media Information</title>
</head>

<body bgcolor="d3d3d3" leftmargin=0 topmargin=0 marginheight=0 marginwidth=0 onload="self.focus()">
<% if (request.getParameter("filesize") != null){%>
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
				Filelength: <%=request.getParameter("filesize");%> Bytes
			</font>
			</td>
		</tr>
		<tr>
			<td>
			<font face="arial" size="2">
				Width: <%=request.getParameter("width");%>
			</font>
			</td>
			<td>
			<font face="arial" size="2">
				Height: <%=request.getParameter("height");%>
			</font>
			</td>
		</tr>
	</table>
<% } else { %>
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
<%}%>

</body>
</html>
