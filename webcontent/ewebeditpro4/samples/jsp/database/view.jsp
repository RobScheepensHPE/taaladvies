<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ include file="../../../ewebeditpro.jsp" %>
<html>
<!-- modify date: 02/09/01 -->
<head>
	<title>View content from the database</title>
</head>
<body>
<p align="center"><B><% out.println(getTitle(szODBCNAME, request.getParameter("id"))); %></B></p>                             <!--- Display Title --->
<% out.println(getContent(szODBCNAME, request.getParameter("id"))); %> <!---  passing ODBC name and Content ID  --->
</body>
</html>
