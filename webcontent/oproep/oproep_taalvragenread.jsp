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

<bean:define name="Oproep_TaalvragenInvoerForm" property="taalvragen" type="java.util.ArrayList" id="taalvragenbean" />
<%  
	be.vlaanderen.sbs.s6.taaladvies.model.Gebruiker gebruiker = (be.vlaanderen.sbs.s6.taaladvies.model.Gebruiker)session.getAttribute("Gebruiker");
	be.vlaanderen.sbs.s6.taaladvies.oproep.Oproep_TaalvragenInvoerForm oproep_TaalvragenInvoerForm = (be.vlaanderen.sbs.s6.taaladvies.oproep.Oproep_TaalvragenInvoerForm)session.getAttribute("Oproep_TaalvragenInvoerForm"); 
%>			
<table width="80%" border="0">
	<tr>
		<th><html:link page="/do/initAlleTaalvragen"><html:img page="/images/link_inbehandeling.gif" border="0" /></html:link></th>
		<th><html:link page="/do/initOproep_TaalvragenInvoer"><html:img page="/images/link_nieuweoproeptaalvragen.gif" border="0" /></html:link></th>
		<th><html:link page="/do/initOproep_TekstInvoer"><html:img page="/images/link_nieuweoproeptekst.gif" border="0" /></html:link></th>
		<th><html:link page="/do/initZoeken"><html:img page="/images/button_zoek.gif" border="0" /></html:link></th>
		<th><html:link page="/profielen.jsp"><html:img page="/images/button_profielen.gif" border="0" /></html:link></th>
	</tr>
</table>
<br>

<h2>BEKIJKEN Oproep met taalvragen</h2>

<html:errors />

<html:form method="post" action="/oproep_TaalvragenInvoer">
	
				<table cellspacing="5">					
					<tr>
						<td colspan="6">
							Oproep <bean:write name="Oproep_TaalvragenInvoerForm" property="oproep.id" />
						</td>
					</tr>
					<tr>
						<td><html:img page="/images/label_domein.gif" /></td>
						<td><bean:write name="Oproep_TaalvragenInvoerForm" property="oproep.domein.omschrijving" /></td>					
						<td><html:img page="/images/label_datum_in.gif" /></td>
						<td>
							<bean:write name="Oproep_TaalvragenInvoerForm" property="oproep.oproepdatum_d" /> - 
							<bean:write name="Oproep_TaalvragenInvoerForm" property="oproep.oproepdatum_m" /> - 
							<bean:write name="Oproep_TaalvragenInvoerForm" property="oproep.oproepdatum_y" />
						</td>
						<td><html:img page="/images/label_herkomst.gif" /></td>
						<td><bean:write name="Oproep_TaalvragenInvoerForm" property="oproep.herkomst.omschrijving" /></td>					
					</tr>
					<tr>
						<td><html:img page="/images/label_voornaam.gif" /></td>
						<td><bean:write name="Oproep_TaalvragenInvoerForm" property="oproep.voornaam" /></td>
						<td><html:img page="/images/label_deadline.gif" /></td>
						<td>
							<bean:write name="Oproep_TaalvragenInvoerForm" property="oproep.deadline_d" /> - 
							<bean:write name="Oproep_TaalvragenInvoerForm" property="oproep.deadline_m" /> - 
							<bean:write name="Oproep_TaalvragenInvoerForm" property="oproep.deadline_y" />
						</td>
						<td><html:img page="/images/label_herkomstnummer.gif" /></td>
						<td><bean:write name="Oproep_TaalvragenInvoerForm" property="oproep.herkomstnummer" /></td>
					</tr>
					<tr>
						<td><html:img page="/images/label_achternaam.gif" /></td>
						<td><bean:write name="Oproep_TaalvragenInvoerForm" property="oproep.naam" /></td>					
						<td><html:img page="/images/label_medium_in.gif" /></td>
						<td><bean:write name="Oproep_TaalvragenInvoerForm" property="oproep.medium.omschrijving" /></td>
						<td><html:img page="/images/label_status.gif" /></td>
						<td><bean:write name="Oproep_TaalvragenInvoerForm" property="oproep.status.omschrijving" /></td>
					</tr>
					<tr>
						<td><html:img page="/images/label_telefoon.gif" /></td>
						<td><bean:write name="Oproep_TaalvragenInvoerForm" property="oproep.telefoon" /></td>					
						<td><html:img page="/images/label_medium_uit.gif" /></td>
						<td><bean:write name="Oproep_TaalvragenInvoerForm" property="oproep.distributie.medium.omschrijving" /></td>					
						<td><html:img page="/images/label_email.gif" /></td>
						<td><bean:write name="Oproep_TaalvragenInvoerForm" property="oproep.email" /></td>
					</tr>					
					<tr>
						<td colspan="6" align="center">
							<a href="../do/initKlantenGegevensInvoer?id=<bean:write name="Oproep_TaalvragenInvoerForm" property="oproep.id" />&ro=true<logic:equal name="Oproep_TaalvragenInvoerForm" property="oproep.afgehandeld" value="true">&adm=true</logic:equal>">
								<html:img page="/images/button_klant.gif" border="0"/>
							</a>
							<logic:equal name="Oproep_TaalvragenInvoerForm" property="oproep.afgehandeld" value="true">
								<a href="../overzicht/search2result.jsp">
									<html:img page="/images/button_terug_naar_resultaat.gif" border="0" />
								</a>
								<a href="../overzicht/search2.jsp" border="0">
									<html:img page="/images/button_verfijn_zoekopdracht.gif" border="0" />
								</a>
								<a href="../do/initSearch2" border="0">
									<html:img page="/images/button_nieuwe_zoekopdracht.gif" border="0" />
								</a>
							</logic:equal>
						</td>
					</tr>
				</table>
<hr />	
		<logic:notEqual name="Oproep_TaalvragenInvoerForm" property="oproep.id" value="0">
				<table cellspacing="5">					
								
					<tr bgcolor="#dddddd">
						<th align="left">Taalvraag</th>
						<th align="left">Categorie</th>
						<th align="left">Afgehandeld</th>
						<th align="left">Distributie + DistributieDatum</th>
					</tr>
					<logic:iterate id="taalvragen" name="taalvragenbean" indexId="index">
						<tr>
							<td valign="top">
								<a href="../do/initTaalvragenInvoer?parentId=<bean:write name="Oproep_TaalvragenInvoerForm" property="oproep.id" />&id=<bean:write name="taalvragen" property="id" />&ro=true<logic:equal name="Oproep_TaalvragenInvoerForm" property="adm" value="true">&adm=true</logic:equal>">
									<logic:notEqual name="taalvragen" property="titel" value="<p></p>">
									<logic:notEqual name="taalvragen" property="titel" value="">
										<bean:write name="taalvragen" property="titel" filter="true" />
									</logic:notEqual>
									</logic:notEqual>
									<logic:equal name="taalvragen" property="titel" value="<p></p>">
										<bean:write name="taalvragen" property="vraag" filter="true" />
									</logic:equal>
									<logic:equal name="taalvragen" property="titel" value="">
										<bean:write name="taalvragen" property="vraag" filter="true" />
									</logic:equal>									
								</a>
							</td>
							<td valign="top">
								<logic:present name="taalvragen" property="categorien">
									<logic:iterate id="i" name="taalvragen" property="categorien">
										<logic:notEqual name="i" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
											<bean:write name="i" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.omschrijving" /> > 
											<bean:write name="i" property="omschrijving" />
										</logic:notEqual>
										<logic:equal name="i" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
											<logic:notEqual name="i" property="superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
												<bean:write name="i" property="superCategorie.superCategorie.superCategorie.superCategorie.omschrijving" /> > 
												<bean:write name="i" property="omschrijving" />
											</logic:notEqual>
											<logic:equal name="i" property="superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
												<logic:notEqual name="i" property="superCategorie.superCategorie.superCategorie.id" value="0">
													<bean:write name="i" property="superCategorie.superCategorie.superCategorie.omschrijving" /> > 
													<bean:write name="i" property="omschrijving" />
												</logic:notEqual>
												<logic:equal name="i" property="superCategorie.superCategorie.superCategorie.id" value="0">
													<logic:notEqual name="i" property="superCategorie.superCategorie.id" value="0">
														<bean:write name="i" property="superCategorie.superCategorie.omschrijving" /> >
														<bean:write name="i" property="omschrijving" />
													</logic:notEqual>							
													<logic:equal name="i" property="superCategorie.superCategorie.id" value="0">
														<logic:notEqual name="i" property="superCategorie.id" value="0">
															<bean:write name="i" property="superCategorie.omschrijving" /> > 
															<bean:write name="i" property="omschrijving" />
														</logic:notEqual>
														<logic:equal name="i" property="superCategorie.id" value="0">
															<logic:notEqual name="i" property="id" value="0">
																<bean:write name="i" property="omschrijving" />
															</logic:notEqual>
														</logic:equal>
													</logic:equal>
												</logic:equal>
											</logic:equal>
										</logic:equal>
										<br>							
									</logic:iterate>
								</logic:present>
							</td>
							<td valign="top">
								<logic:equal name="taalvragen" property="afgehandeld" value="true">
									Ja
								</logic:equal>
								&nbsp;
							</td>
							<td valign="top">
								<bean:write name="taalvragen" property="distributiedatumString" />
							</td>
							<% if (gebruiker.getId() == oproep_TaalvragenInvoerForm.getOproep().getGebruikerId()) {%> 
								<td>
									<input type="hidden" name="ro" value="true" />
									<input type="checkbox" name="toDistribute_<bean:write name="index" />">
								</td>
							<%}%>							
						</tr>						
					</logic:iterate>							
					<html:hidden name="Oproep_TaalvragenInvoerForm" property="oproep.id" />
					<% if (gebruiker.getId() == oproep_TaalvragenInvoerForm.getOproep().getGebruikerId()) {%> 
						<tr>
							<td>&nbsp;</td>	
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>						
								<html:image property="Distributie" value="Distributie" page="/images/button_distribueer.gif" />
							</td>
						</tr>
					<%}%>
				</table>
		</logic:notEqual>
</html:form>
</body>
</html:html>