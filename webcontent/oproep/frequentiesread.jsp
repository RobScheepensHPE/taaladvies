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

<bean:define name="FrequentiesInvoerForm" property="frequenties" type="java.util.ArrayList" id="frequentiesbean" />

<h2>BEKIJKEN Frequentie</h2>

<html:form method="post" action="/frequentiesInvoer">
	
				<table cellspacing="5" width="100%">
					<tr>
						<td colspan="4">
							<logic:present name="FrequentiesInvoerForm" property="taalvraag">
									<a class="bigger" href="../do/initTaalvragenInvoer?id=<bean:write name="FrequentiesInvoerForm" property="taalvraag.id" />&ro=true<logic:equal name="FrequentiesInvoerForm" property="adm" value="true">&adm=true</logic:equal>">
										<logic:notEqual name="FrequentiesInvoerForm" property="taalvraag.titel" value="">
											<logic:notEqual name="FrequentiesInvoerForm" property="taalvraag.titel" value="<p></p>">
												<bean:write name="FrequentiesInvoerForm" property="taalvraag.titel" filter="true" />
											</logic:notEqual>
										</logic:notEqual>
										<logic:equal name="FrequentiesInvoerForm" property="taalvraag.titel" value="">
											<bean:write name="FrequentiesInvoerForm" property="taalvraag.vraag" filter="true" />
										</logic:equal>
										<logic:equal name="FrequentiesInvoerForm" property="taalvraag.titel" value="<p></p>">
											<bean:write name="FrequentiesInvoerForm" property="taalvraag.vraag" filter="true" />
										</logic:equal>				
									</a>
							</logic:present>
							<logic:present name="FrequentiesInvoerForm" property="tekst">
								<a class="bigger" href="../do/initOproep_TekstInvoer?id=<bean:write name="FrequentiesInvoerForm" property="tekst.oproepId" />&ro=true">
									<bean:write name="FrequentiesInvoerForm" property="tekst.titel" filter="true" />
								</a>
								<html:hidden name="FrequentiesInvoerForm" property="tekst.id" />
							</logic:present>						
						</td>
					</tr>					
					<tr bgcolor="#dddddd">
						<th align="left">Zoekomgeving</th>
						<th align="left">Specificatie</th>
						<th align="left">Variant</th>
						<th align="left">Aantal</th>
					</tr>		
					<logic:iterate id="frequenties" name="frequentiesbean">
						<tr>
							<td valign="top"><bean:write name="frequenties" property="zoekomgeving.omschrijving" filter="true" /></td>
							<td valign="top"><bean:write name="frequenties" property="specificatie" filter="true" /></td>
							<td valign="top"><bean:write name="frequenties" property="variant" filter="true" /></td>
							<td valign="top"><bean:write name="frequenties" property="aantal" filter="true" /></td>
						</tr>
					</logic:iterate>		
				</table>
</html:form>
</body>
</html:html>