<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<meta http-equiv="pragma" content="no-cache">
	<title>Image Preview</title>
	<%@ include file="../../../ewebeditpro.jsp" %>
</head>
<body bgcolor="d3d3d3">

<% 
		if(request.getParameter("id") != null) { 
%> 
<img src="<%= getImageName(szODBCNAME, request.getParameter("id")) %>" border=0 alt="Preview">
<script language="javascript">
top.imageinfo.location.href="imageinformation.jsp?filesize=<%= getImageWidth(szODBCNAME, request.getParameter("id")) %>&width=<%= getImageHeight(szODBCNAME, request.getParameter("id")) %>&height=<%= getImageSize(szODBCNAME, request.getParameter("id")) %>";
</script>
<% } else if (request.getParameter("imagename") != null) { %>

	<img src="file:///<%= request.getParameter("imagename") %>" border=0 alt="Preview">
<% } else  { %>
	<table width="100%" height="100%">
	<tr><td align="center"><font face="arial"><h3>Image Preview</h3></font></td></tr>
	</table>
<% } %> 

</body>
</html>
