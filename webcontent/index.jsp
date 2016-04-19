<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/SecurityTagLib.tld" prefix="security" %>
<security:isLoggedIn />

<html:html locale="true">
<head>
	<title>Databank Taaladvies</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  <link href="<html:rewrite page='/style/taaladvies.css' />" rel="stylesheet" type="text/css">
	<script language="JavaScript1.2" src="<html:rewrite page='/style/taaladvies.js' />" type="text/JavaScript1.2" />
</head>

<body>
<h2>voorlopige start pagina</h2>
<p><a href="admin/beheerdermenu.jsp">beheerdermenu</a></p>
</body>
</html:html>