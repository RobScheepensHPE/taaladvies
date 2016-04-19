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
	<link href="<html:rewrite page='/style/taaladvies.js' />" type="text/JavaScript" />
</head>

<body>

<h2>Distributie bevestiging</h2>

Het systeem heeft uw vraag om een e-mail te versturen ontvangen.
<p>
<logic:equal name="DistributiekeuzeForm" property="oproep.type" value="1">
	<a href="../do/initOproep_TaalvragenInvoer?id=<bean:write name="DistributiekeuzeForm" property="oproep.id" />">
		<html:img page="/images/button_overzicht.gif" border="0"/>
	</a>
</logic:equal>
<logic:equal name="DistributiekeuzeForm" property="oproep.type" value="2">
	<a href="../do/initOproep_TekstInvoer?id=<bean:write name="DistributiekeuzeForm" property="oproep.id" />">
		<html:img page="/images/button_overzicht.gif" border="0"/>
	</a>
</logic:equal>
</p>


</body>
</html:html>
