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

<bean:define name="CitatenInvoerForm" property="citaten" type="java.util.ArrayList" id="citatenbean" />

<h2>BEKIJKEN Citaat</h2>

<html:form method="post" action="/citatenInvoer">
	
				<table cellspacing="5" width="100%">
					<tr>
						<td colspan="5">
							<logic:present name="CitatenInvoerForm" property="taalvraag">
									<a class="bigger" href="../do/initTaalvragenInvoer?id=<bean:write name="CitatenInvoerForm" property="taalvraag.id" />&ro=true<logic:equal name="CitatenInvoerForm" property="adm" value="true">&adm=true</logic:equal>">
										<logic:notEqual name="CitatenInvoerForm" property="taalvraag.titel" value="">
											<logic:notEqual name="CitatenInvoerForm" property="taalvraag.titel" value="<p></p>">
												<bean:write name="CitatenInvoerForm" property="taalvraag.titel" filter="true" />
											</logic:notEqual>
										</logic:notEqual>
										<logic:equal name="CitatenInvoerForm" property="taalvraag.titel" value="">
											<bean:write name="CitatenInvoerForm" property="taalvraag.vraag" filter="true" />
										</logic:equal>
										<logic:equal name="CitatenInvoerForm" property="taalvraag.titel" value="<p></p>">
											<bean:write name="CitatenInvoerForm" property="taalvraag.vraag" filter="true" />
										</logic:equal>				
									</a>
							</logic:present>
							<logic:present name="CitatenInvoerForm" property="tekst">
								<a class="bigger" href="../do/initOproep_TekstInvoer?id=<bean:write name="CitatenInvoerForm" property="tekst.oproepId" />&ro=true">
									<bean:write name="CitatenInvoerForm" property="tekst.titel" filter="true" />
								</a>
								<html:hidden name="CitatenInvoerForm" property="tekst.id" />
							</logic:present>						
						</td>
					</tr>					
					<tr bgcolor="#dddddd">
						<th align="left">Zoekomgeving</th>
						<th align="left">Specificatie</th>
						<th align="left">URL</th>
						<th align="left">Variant</th>
						<th align="left">Citaat</th>
					</tr>		
					<logic:iterate id="citaten" name="citatenbean">
						<tr>
							<td valign="top"><bean:write name="citaten" property="zoekomgeving.omschrijving" filter="true" /></td>
							<td valign="top"><bean:write name="citaten" property="specificatie" filter="true" /></td>
							<td valign="top"><a href="<bean:write name="citaten" property="url" filter="true" />" target="blank">link</a></td>
							<td valign="top"><bean:write name="citaten" property="variant" filter="true" /></td>
							<td valign="top"><bean:write name="citaten" property="citaatHTML" filter="false" /></td>
						</tr>
					</logic:iterate>		
				</table>		
</html:form>
</body>
</html:html>