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

<table width="80%" border="0">
</table>
<br>

<h2>BEKIJKEN Klant</h2>

<html:errors />

<html:form method="post" action="/klantenGegevensInvoer">
	
				<table cellspacing="5">					
					<tr>
						<td>Naam: </td>
						<td>
							<logic:equal name="KlantenGegevensInvoerForm" property="oproep.type" value="1">
								<a href="../do/initOproep_TaalvragenInvoer?id=<bean:write name="KlantenGegevensInvoerForm" property="oproep.id" />&ro=true<logic:equal name="KlantenGegevensInvoerForm" property="adm" value="true">&adm=true</logic:equal>">
									<bean:write name="KlantenGegevensInvoerForm" property="oproep.voornaam" /> 
									<bean:write name="KlantenGegevensInvoerForm" property="oproep.naam" />
								</a>							
							</logic:equal>							
							<logic:equal name="KlantenGegevensInvoerForm" property="oproep.type" value="2">
								<a href="../do/initOproep_TekstInvoer?id=<bean:write name="KlantenGegevensInvoerForm" property="oproep.id" />&ro=true">
									<bean:write name="KlantenGegevensInvoerForm" property="oproep.voornaam" /> 
									<bean:write name="KlantenGegevensInvoerForm" property="oproep.naam" />
								</a>							
							</logic:equal>							
						</td>
						<td><html:img page="/images/label_telefoon.gif" /></td>
						<td>
							<bean:write name="KlantenGegevensInvoerForm" property="oproep.telefoon" />
						</td>
					</tr>
					<tr><td colspan="4"><hr /></td></tr>
					<tr>
						<td><html:img page="/images/label_voornaam.gif" /></td>
						<td><bean:write name="KlantenGegevensInvoerForm" property="oproep.voornaam" /></td>
						<td><html:img page="/images/label_naam.gif" /></td>
						<td><bean:write name="KlantenGegevensInvoerForm" property="oproep.naam" /></td>
					</tr>
					<tr>
						<td><html:img page="/images/label_geslacht.gif" /></td>
						<td>
							<logic:present name="KlantenGegevensInvoerForm" property="oproep.geslacht">
								<logic:equal name="KlantenGegevensInvoerForm" property="oproep.geslacht" value="m">Man</logic:equal>
								<logic:equal name="KlantenGegevensInvoerForm" property="oproep.geslacht" value="v">Vrouw</logic:equal>
								<logic:equal name="KlantenGegevensInvoerForm" property="oproep.geslacht" value="o">Onbekend</logic:equal>
							</logic:present>
						</td>
						<td><html:img page="/images/label_functie.gif" /></td>
						<td><bean:write name="KlantenGegevensInvoerForm" property="oproep.functie" /></td>
					</tr>
					<tr>
						<td><html:img page="/images/label_telefoon.gif" /></td>
						<td><bean:write name="KlantenGegevensInvoerForm" property="oproep.telefoon" /></td>
						<td><html:img page="/images/label_email.gif" /></td>
						<td><bean:write name="KlantenGegevensInvoerForm" property="oproep.email" /></td>
					</tr>
					<tr>				
						<td><html:img page="/images/label_fax.gif" /></td>
						<td colspan="3"><bean:write name="KlantenGegevensInvoerForm" property="oproep.fax" /></td>
					</tr>
					<tr><td colspan="4"><hr /></td></tr>
					<tr>
						<td><html:img page="/images/label_domein.gif" /></td>
						<td colspan="3"><bean:write name="KlantenGegevensInvoerForm" property="oproep.domein.omschrijving" /></td>
					</tr>
					<tr>
						<td><html:img page="/images/label_herkomst.gif" /></td>
						<td><bean:write name="KlantenGegevensInvoerForm" property="oproep.herkomst.omschrijving" /></td>
						<td><html:img page="/images/label_herkomstnr.gif" /></td>
						<td><bean:write name="KlantenGegevensInvoerForm" property="oproep.herkomstnummer" /></td>
					</tr>
					<tr>
						<td><html:img page="/images/label_doelgroep_alg.gif" /></td>
						<td><bean:write name="KlantenGegevensInvoerForm" property="oproep.doelgroep.doelgroepAlgemeen.omschrijving" /></td>
						<td><html:img page="/images/label_doelgroep.gif" /></td>
						<td><bean:write name="KlantenGegevensInvoerForm" property="oproep.doelgroep.omschrijving" /></td>
					</tr>
					<tr><td colspan="4"><hr /></td></tr>
					<tr>
						<td><html:img page="/images/label_organisatie.gif" /></td>
						<td colspan="3"><bean:write name="KlantenGegevensInvoerForm" property="oproep.organisatie" /></td>
					</tr>
					<tr>				
						<td><html:img page="/images/label_straat.gif" /></td>
						<td><bean:write name="KlantenGegevensInvoerForm" property="oproep.straat" /></td>
						<td><html:img page="/images/label_huisnummer.gif" /></td>
						<td><bean:write name="KlantenGegevensInvoerForm" property="oproep.huisnummer" /></td>
					</tr>
					<tr>
						<td><html:img page="/images/label_busnummer.gif" /></td>
						<td><bean:write name="KlantenGegevensInvoerForm" property="oproep.busnummer" /></td>
						<td><html:img page="/images/label_land.gif" /></td>
						<td><bean:write name="KlantenGegevensInvoerForm" property="oproep.gemeente.land.omschrijving" /></td>
					</tr>
					<tr>
						<td><html:img page="/images/label_postnummer.gif" /></td>
						<td><bean:write name="KlantenGegevensInvoerForm" property="oproep.gemeente.post" /></td>
						<td><html:img page="/images/label_gemeente.gif" /></td>
						<td><bean:write name="KlantenGegevensInvoerForm" property="oproep.gemeente.omschrijving" /></td>
					</tr>
					<tr><td colspan="4"><hr /></td></tr>
					<tr>
						<td><html:img page="/images/label_opmerking.gif" /></td>
						<td colspan="3"><bean:write name="KlantenGegevensInvoerForm" property="oproep.opmerking" /></td>
					</tr>
				</table>
</html:form>
</body>
</html:html>