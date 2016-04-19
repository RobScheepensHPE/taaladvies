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

<bean:define name="NaslagreferentiesInvoerForm" property="naslagreferenties" type="java.util.ArrayList" id="naslagreferentiesbean" />

<h2>BEKIJKEN Naslagwerk</h2>

<html:form method="post" action="/naslagreferentiesInvoer">
	
				<table cellspacing="5" width="100%">
					<tr>
						<td colspan="4">
							<logic:present name="NaslagreferentiesInvoerForm" property="taalvraag">
									<a class="bigger" href="../do/initTaalvragenInvoer?id=<bean:write name="NaslagreferentiesInvoerForm" property="taalvraag.id" />&ro=true<logic:equal name="NaslagreferentiesInvoerForm" property="adm" value="true">&adm=true</logic:equal>">
										<logic:notEqual name="NaslagreferentiesInvoerForm" property="taalvraag.titel" value="">
											<logic:notEqual name="NaslagreferentiesInvoerForm" property="taalvraag.titel" value="<p></p>">
												<bean:write name="NaslagreferentiesInvoerForm" property="taalvraag.titel" filter="true" />
											</logic:notEqual>
										</logic:notEqual>
										<logic:equal name="NaslagreferentiesInvoerForm" property="taalvraag.titel" value="">
											<bean:write name="NaslagreferentiesInvoerForm" property="taalvraag.vraag" filter="true" />
										</logic:equal>
										<logic:equal name="NaslagreferentiesInvoerForm" property="taalvraag.titel" value="<p></p>">
											<bean:write name="NaslagreferentiesInvoerForm" property="taalvraag.vraag" filter="true" />
										</logic:equal>				
									</a>
							</logic:present>
							<logic:present name="NaslagreferentiesInvoerForm" property="tekst">
								<a class="bigger" href="../do/initOproep_TekstInvoer?id=<bean:write name="NaslagreferentiesInvoerForm" property="tekst.oproepId" />&ro=true">
									<bean:write name="NaslagreferentiesInvoerForm" property="tekst.titel" filter="true" />
								</a>
								<html:hidden name="NaslagreferentiesInvoerForm" property="tekst.id" />
							</logic:present>						
						</td>
					</tr>					
					<tr bgcolor="#dddddd">
						<th align="left">Verkorte titel</th>
						<th align="left">Pagina</th>
						<th align="left">Variant</th>
						<th align="left">Informatie</th>
					</tr>		
					<logic:iterate id="naslagreferenties" name="naslagreferentiesbean">
						<tr>
							<td valign="top"><bean:write name="naslagreferenties" property="naslagwerk.omschrijving" filter="true" /></td>
							<td valign="top"><bean:write name="naslagreferenties" property="paginas" filter="true" /></td>
							<td valign="top"><bean:write name="naslagreferenties" property="variant" filter="true" /></td>
							<td valign="top"><bean:write name="naslagreferenties" property="citaatHTML" filter="false" /></td>
						</tr>
					</logic:iterate>		
				</table>
</html:form>
</body>
</html:html>