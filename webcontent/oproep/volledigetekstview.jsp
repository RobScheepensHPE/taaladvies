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


<bean:define name="VolledigeTekstViewForm" property="tekstblokken" type="java.util.ArrayList" id="tekstblokkenbean" />



<h2>OVERZICHT Tekst</h2>

<html:errors />


<p>
	<a href="../do/initOproep_TekstInvoer?id=<bean:write name="VolledigeTekstViewForm" property="oproep.id" />">
		<bean:write name="VolledigeTekstViewForm" property="tekst.titelHTML" filter="false" />
	</a>
</p>
<hr />
<table width="600">
	<logic:present name="VolledigeTekstViewForm" property="tekst.tekstblokken">
		<logic:iterate name="VolledigeTekstViewForm" property="tekst.tekstblokken" id="tekstblokkeni">
			<tr>
				<td valign="top"><bean:write name="tekstblokkeni" property="titelHTML" filter="false" /></td>
				<td><bean:write name="tekstblokkeni" property="tekstblokHTML" filter="false" /><hr /></td>
			</tr>
		</logic:iterate>
	</logic:present>
</table>

</body>
</html:html>