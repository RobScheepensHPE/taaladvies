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
	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>		

</head>

<body>

<h2>BEKIJKEN Kenmerken</h2>

<html:form method="post" action="/kenmerkenInvoer">
	
				<table cellspacing="5">
					<tr>
						<td>
							<a href="../do/initTaalvragenInvoer?id=<bean:write name="KenmerkenInvoerForm" property="taalvraag.id" />&ro=true<logic:equal name="KenmerkenInvoerForm" property="adm" value="true">&adm=true</logic:equal>">
								<logic:notEqual name="KenmerkenInvoerForm" property="taalvraag.titel" value="">
									<bean:write name="KenmerkenInvoerForm" property="taalvraag.titel" filter="true" />
								</logic:notEqual>
								<logic:equal name="KenmerkenInvoerForm" property="taalvraag.titel" value="">
									<bean:write name="KenmerkenInvoerForm" property="taalvraag.vraag" filter="true" />
								</logic:equal>
							</a>						
						</td>
					</tr>
					<tr>
						<td><html:img page="/images/label_relevantie.gif" /></td>
						<td><bean:write name="KenmerkenInvoerForm" property="taalvraag.relevantie.omschrijving" /></td>
					</tr>
					<logic:present name="KenmerkenInvoerForm" property="taalvraag.herformulering">
						<logic:notEqual name="KenmerkenInvoerForm" property="taalvraag.herformulering" value="">
							<tr>
								<td><html:img page="/images/label_herformulering.gif" /></td>
								<td><bean:write name="KenmerkenInvoerForm" property="taalvraag.herformuleringHTML" filter="false" /></td>
							</tr>					
						</logic:notEqual>
					</logic:present>
					<logic:present name="KenmerkenInvoerForm" property="taalvraag.informatie">
						<logic:notEqual name="KenmerkenInvoerForm" property="taalvraag.informatie" value="">
							<tr>
								<td><html:img page="/images/label_informatie.gif" /></td>
								<td><bean:write name="KenmerkenInvoerForm" property="taalvraag.informatieHTML" filter="false" /></td>
							</tr>
						</logic:notEqual>
					</logic:present>
				</table>
</html:form>
</body>
</html:html>