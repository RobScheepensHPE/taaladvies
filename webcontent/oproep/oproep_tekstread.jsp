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

<bean:define name="Oproep_TekstInvoerForm" property="tekstblokken" type="java.util.ArrayList" id="tekstblokkenbean" />
<%  
	be.vlaanderen.sbs.s6.taaladvies.model.Gebruiker gebruiker = (be.vlaanderen.sbs.s6.taaladvies.model.Gebruiker)session.getAttribute("Gebruiker");
	be.vlaanderen.sbs.s6.taaladvies.oproep.Oproep_TekstInvoerForm oproep_TekstInvoerForm = (be.vlaanderen.sbs.s6.taaladvies.oproep.Oproep_TekstInvoerForm)session.getAttribute("Oproep_TekstInvoerForm"); 
%>		
<table width="80%" border="0">
	<tr>
		<th><html:link page="/do/initAlleTeksten"><html:img page="/images/link_inbehandeling.gif" border="0" /></html:link></th>
		<th><html:link page="/do/initOproep_TaalvragenInvoer"><html:img page="/images/link_nieuweoproeptaalvragen.gif" border="0" /></html:link></th>
		<th><html:link page="/do/initOproep_TekstInvoer"><html:img page="/images/link_nieuweoproeptekst.gif" border="0" /></html:link></th>
		<th><html:link page="/do/initZoeken"><html:img page="/images/button_zoek.gif" border="0" /></html:link></th>
		<th><html:link page="/profielen.jsp"><html:img page="/images/button_profielen.gif" border="0" /></html:link></th>
	</tr>
</table>
<br>

<h2>BEKIJKEN Oproep Voor een Tekst</h2>

<html:errors />

<html:form method="post" action="/oproep_TekstInvoer">
	
				<table cellspacing="5">					
					<tr>
						<td colspan="6">
							Oproep <bean:write name="Oproep_TekstInvoerForm" property="oproep.id" />
						</td>
					<tr>
						<td><html:img page="/images/label_domein.gif" /></td>
						<td><bean:write name="Oproep_TekstInvoerForm" property="oproep.domein.omschrijving" /></td>										
						<td><html:img page="/images/label_startdatum.gif" /></td>
						<td>
							<bean:write name="Oproep_TekstInvoerForm" property="oproep.oproepdatum_d" />-
							<bean:write name="Oproep_TekstInvoerForm" property="oproep.oproepdatum_m" />-
							<bean:write name="Oproep_TekstInvoerForm" property="oproep.oproepdatum_y" />
						</td>					
						<td><html:img page="/images/label_herkomst.gif" /></td>
						<td><bean:write name="Oproep_TekstInvoerForm" property="oproep.herkomst.omschrijving" /></td>
					</tr>
					<tr>
						<td><html:img page="/images/label_voornaam.gif" /></td>
						<td><bean:write name="Oproep_TekstInvoerForm" property="oproep.voornaam" /></td>					
						<td><html:img page="/images/label_deadline.gif" /></td>
						<td>
							<bean:write name="Oproep_TekstInvoerForm" property="oproep.deadline_d" />-
							<bean:write name="Oproep_TekstInvoerForm" property="oproep.deadline_m" />-
							<bean:write name="Oproep_TekstInvoerForm" property="oproep.deadline_y" />
						</td>					
						<td><html:img page="/images/label_herkomstnummer.gif" /></td>
						<td><bean:write name="Oproep_TekstInvoerForm" property="oproep.herkomstnummer" /></td>
					</tr>
					<tr>
						<td><html:img page="/images/label_achternaam.gif" /></td>
						<td><bean:write name="Oproep_TekstInvoerForm" property="oproep.naam" /></td>					
						<td><html:img page="/images/label_medium_in.gif" /></td>
						<td><bean:write name="Oproep_TekstInvoerForm" property="oproep.medium.omschrijving" /></td>					
						<td><html:img page="/images/label_status.gif" /></td>
						<td><bean:write name="Oproep_TekstInvoerForm" property="oproep.status.omschrijving" /></td>
					</tr>
					<tr>
						<td><html:img page="/images/label_telefoon.gif" /></td>
						<td><bean:write name="Oproep_TekstInvoerForm" property="oproep.telefoon" /></td>					
						<td><html:img page="/images/label_medium_uit.gif" /></td>
						<td><bean:write name="Oproep_TekstInvoerForm" property="oproep.distributie.medium.omschrijving" /></td>
						<td><html:img page="/images/label_email.gif" /></td>
						<td><bean:write name="Oproep_TekstInvoerForm" property="oproep.email" /></td>
					</tr>				
				</table>
<hr />

		<logic:notEqual name="Oproep_TekstInvoerForm" property="oproep.id" value="0">
				<table cellspacing="5">					
					<logic:notEqual name="Oproep_TekstInvoerForm" property="tekst.id" value="0">
						<tr>
							<td align="center" colspan="2">

								<a href="../do/initCategorienInvoer?type=2&id=<bean:write name="Oproep_TekstInvoerForm" property="tekst.id" />&ro=true">
									Categorie
								</a>								
								&nbsp;|&nbsp;
								<a href="../do/initNaslagreferentiesInvoer?type=2&id=<bean:write name="Oproep_TekstInvoerForm" property="tekst.id" />&ro=true">
									Naslagwerk
								</a>
								--
								<a href="../do/initBronnenInvoer?type=2&id=<bean:write name="Oproep_TekstInvoerForm" property="tekst.id" />&ro=true">
									Bron
								</a>								
								--
								<a href="../do/initCitatenInvoer?type=2&id=<bean:write name="Oproep_TekstInvoerForm" property="tekst.id" />&ro=true">
									Citaat
								</a>
								--
								<a href="../do/initFrequentiesInvoer?type=2&id=<bean:write name="Oproep_TekstInvoerForm" property="tekst.id" />&ro=true">
									Frequentie
								</a>								
								--
								<a href="../do/initWebreferentiesInvoer?type=2&id=<bean:write name="Oproep_TekstInvoerForm" property="tekst.id" />&ro=true">
									Koppeling
								</a>
								&nbsp;|&nbsp;
								<a href="../do/initNotitiesInvoer?type=2&id=<bean:write name="Oproep_TekstInvoerForm" property="tekst.id" />&ro=true">
									Notitie
								</a>
							</td>					
						</tr>
					</logic:notEqual>					
					<tr>
						<td><html:img page="/images/label_titel.gif" /></td>
						<td><bean:write name="Oproep_TekstInvoerForm" property="tekst.titel" filter="true" /></td>
					</tr>
					<tr>
						<td><html:img page="/images/label_relevantie.gif" /></td>
						<td><bean:write name="Oproep_TekstInvoerForm" property="tekst.relevantie.omschrijving" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center">	
							<a href="../do/initVolledigeTekstView?id=<bean:write name="Oproep_TekstInvoerForm" property="oproep.id" />&ro=true">
								<html:img page="/images/button_overzicht.gif" border="0" />
							</a>
							<a href="../do/initKlantenGegevensInvoer?id=<bean:write name="Oproep_TekstInvoerForm" property="oproep.id" />&ro=true">
								<html:img page="/images/button_klant.gif" border="0" />
							</a>
							<logic:equal name="Oproep_TekstInvoerForm" property="oproep.afgehandeld" value="true">
								<a href="../overzicht/resultaat.jsp" border="0">
									<html:img page="/images/button_terug_naar_resultaat.gif" border="0" />
								</a>
								<a href="../overzicht/zoeken.jsp" border="0">
									<html:img page="/images/button_verfijn_zoekopdracht.gif" border="0" />
								</a>
							</logic:equal>							
							<logic:equal name="Oproep_TekstInvoerForm" property="oproep.afgehandeld" value="false">
								<a href="../do/initAlleTeksten">
									<html:img page="/images/button_annuleren.gif" border="0" />
								</a>							
							</logic:equal>
							<% if (gebruiker.getId() == oproep_TekstInvoerForm.getOproep().getGebruikerId()) {%>
								<html:image property="Distributie" value="Distributie" page="/images/button_distribueer.gif" /> 											
								<input type="hidden" name="ro" value="true" />
								<html:hidden name="Oproep_TekstInvoerForm" property="oproep.id" />
							<%}%>	
						</td>
					</tr>			
					<tr bgcolor="#dddddd"><th align="left" colspan="2">Tekstblok</th>
					</tr>
					<logic:iterate id="tekstblokkeni" name="tekstblokkenbean">
						<tr>
							<td colspan="2">
								<a href="../do/initTekstblokkenInvoer?parentId=<bean:write name="Oproep_TekstInvoerForm" property="tekst.id" />&id=<bean:write name="tekstblokkeni" property="id" />&ro=true">
									<logic:notEqual name="tekstblokkeni" property="titel" value="">
										<bean:write name="tekstblokkeni" property="titel" filter="true" />
									</logic:notEqual>
									<logic:equal name="tekstblokkeni" property="titel" value="">
										[zonder titel]
									</logic:equal>
								</a>
							</td>
						</tr>
					</logic:iterate>							

				</table>

		</logic:notEqual>
					
	
	



</html:form>
</body>
</html:html>