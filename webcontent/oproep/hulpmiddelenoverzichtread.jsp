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

<h2>OVERZICHT Hulpmiddelen</h2>

<html:form method="post" action="/frequentiesInvoer">
	
	<logic:present name="HulpmiddelenOverzichtForm" property="taalvraag">
			<a class="bigger" href="../do/initTaalvragenInvoer?id=<bean:write name="HulpmiddelenOverzichtForm" property="taalvraag.id" />&ro=true<logic:equal name="HulpmiddelenOverzichtForm" property="adm" value="true">&adm=true</logic:equal>">
				<logic:notEqual name="HulpmiddelenOverzichtForm" property="taalvraag.titel" value="">
					<bean:write name="HulpmiddelenOverzichtForm" property="taalvraag.titel" filter="true" />
				</logic:notEqual>
				<logic:equal name="HulpmiddelenOverzichtForm" property="taalvraag.titel" value="">
					<bean:write name="HulpmiddelenOverzichtForm" property="taalvraag.vraag" filter="true" />
				</logic:equal>
			</a>
			<html:hidden name="HulpmiddelenOverzichtForm" property="taalvraag.id" />
	</logic:present>
	<logic:present name="HulpmiddelenOverzichtForm" property="tekst">
			<a class="bigger" href="../do/initOproep_TekstInvoer?id=<bean:write name="HulpmiddelenOverzichtForm" property="tekst.oproepId" />&ro=true">
				<bean:write name="HulpmiddelenOverzichtForm" property="tekst.titel" filter="true" />
			</a>
			<html:hidden name="HulpmiddelenOverzichtForm" property="tekst.id" />
	</logic:present>
	
	<table>
		<logic:present name="HulpmiddelenOverzichtForm" property="categorien">
			<tr>
				<td valign="top">
					<b>
						<logic:present name="HulpmiddelenOverzichtForm" property="taalvraag">
							<a href="../do/initCategorienInvoer?type=1&id=<bean:write name='HulpmiddelenOverzichtForm' property='taalvraag.id' />&ro=true<logic:equal name="HulpmiddelenOverzichtForm" property="adm" value="true">&adm=true</logic:equal>">
						</logic:present>
						<logic:present name="HulpmiddelenOverzichtForm" property="tekst">
							<a href="../do/initCategorienInvoer?type=2&id=<bean:write name='HulpmiddelenOverzichtForm' property='tekst.id' />&ro=true">
						</logic:present>						
							Categorie   
						</a>		
					</b>
				</td>
				<td valign="top">
				
					<logic:iterate name="HulpmiddelenOverzichtForm" property="categorien" id="categorieni">
						
						<logic:notEqual name="categorieni" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
							<bean:write name="categorieni" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.omschrijving" /> > 
							<bean:write name="categorieni" property="omschrijving" />
						</logic:notEqual>
						<logic:equal name="categorieni" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
							<logic:notEqual name="categorieni" property="superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
								<bean:write name="categorieni" property="superCategorie.superCategorie.superCategorie.superCategorie.omschrijving" /> > 
								<bean:write name="categorieni" property="omschrijving" /> 
							</logic:notEqual>
							<logic:equal name="categorieni" property="superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
								<logic:notEqual name="categorieni" property="superCategorie.superCategorie.superCategorie.id" value="0">
									<bean:write name="categorieni" property="superCategorie.superCategorie.superCategorie.omschrijving" /> > 
									<bean:write name="categorieni" property="omschrijving" />
								</logic:notEqual>
								<logic:equal name="categorieni" property="superCategorie.superCategorie.superCategorie.id" value="0">
									<logic:notEqual name="categorieni" property="superCategorie.superCategorie.id" value="0">
										<bean:write name="categorieni" property="superCategorie.superCategorie.omschrijving" /> >
										<bean:write name="categorieni" property="omschrijving" />
									</logic:notEqual>							
									<logic:equal name="categorieni" property="superCategorie.superCategorie.id" value="0">
										<logic:notEqual name="categorieni" property="superCategorie.id" value="0">
											<bean:write name="categorieni" property="superCategorie.omschrijving" /> > 
											<bean:write name="categorieni" property="omschrijving" />
										</logic:notEqual>
										<logic:equal name="categorieni" property="superCategorie.id" value="0">
											<logic:notEqual name="categorieni" property="id" value="0">
												<bean:write name="categorieni" property="omschrijving" />
											</logic:notEqual>
										</logic:equal>
									</logic:equal>
								</logic:equal>
							</logic:equal>
						</logic:equal>
						<br	>							
					</logic:iterate>	
				</td>
			</tr>
			<tr><td colspan="2"><hr /></td></tr>
		</logic:present>
		<logic:present name="HulpmiddelenOverzichtForm" property="naslagreferenties">
			<tr>
				<td valign="top">
					<b>
						<logic:present name="HulpmiddelenOverzichtForm" property="taalvraag">
							<a href="../do/initNaslagreferentiesInvoer?type=1&id=<bean:write name='HulpmiddelenOverzichtForm' property='taalvraag.id' />&ro=true<logic:equal name="HulpmiddelenOverzichtForm" property="adm" value="true">&adm=true</logic:equal>">
						</logic:present>
						<logic:present name="HulpmiddelenOverzichtForm" property="tekst">
							<a href="../do/initNaslagreferentiesInvoer?type=2&id=<bean:write name='HulpmiddelenOverzichtForm' property='tekst.id' />&ro=true">
						</logic:present>						
							Naslagwerk   
						</a>
					</b>
				</td>
				<td valign="top">
					<table cellpadding="2" width="100%" align="center">
						<tr bgcolor="#dddddd">
							<th align="left" valign="top">Verkorte titel</th>
							<th align="left" valign="top">Pagina</th>
							<th align="left" valign="top">Variant</th>
							<th align="left" valign="top">Informatie</th>
						</tr>		
						<logic:iterate name="HulpmiddelenOverzichtForm" property="naslagreferenties" id="naslagreferentiesi">
							<tr>
								<td valign="top"><bean:write name="naslagreferentiesi" property="naslagwerk.omschrijving" filter="true" /></td>
								<td valign="top"><bean:write name="naslagreferentiesi" property="paginas" filter="true" /></td>
								<td valign="top"><bean:write name="naslagreferentiesi" property="variant" filter="true" /></td>
								<td valign="top"><bean:write name="naslagreferentiesi" property="citaatHTML" filter="false" /></td>
							</tr>
						</logic:iterate>		
					</table>
				</td>
			</tr>
			<tr><td colspan="2"><hr /></td></tr>
		</logic:present>
		<logic:present name="HulpmiddelenOverzichtForm" property="bronnen">
			<tr>
				<td valign="top">
					<b>
						<logic:present name="HulpmiddelenOverzichtForm" property="taalvraag">
							<a href="../do/initBronnenInvoer?type=1&id=<bean:write name='HulpmiddelenOverzichtForm' property='taalvraag.id' />&ro=true<logic:equal name="HulpmiddelenOverzichtForm" property="adm" value="true">&adm=true</logic:equal>">
						</logic:present>
						<logic:present name="HulpmiddelenOverzichtForm" property="tekst">
							<a href="../do/initBronnenInvoer?type=2&id=<bean:write name='HulpmiddelenOverzichtForm' property='tekst.id' />&ro=true">
						</logic:present>								
							Bron  
						</a>
					</b>
				</td>
				<td valign="top">
					<table cellpadding="2" width="100%" align="center">
						<tr bgcolor="#dddddd">
							<th align="left" valign="top">Titel</th>
							<th align="left" valign="top">Pagina</th>
							<th align="left" valign="top">Variant</th>
							<th align="left" valign="top">Informatie</th>
						</tr>		
						<logic:iterate name="HulpmiddelenOverzichtForm" property="bronnen" id="bronneni">
							<tr>
								<td valign="top"><bean:write name="bronneni" property="titelHTML" filter="false" /></td>
								<td valign="top"><bean:write name="bronneni" property="paginas" filter="true" /></td>
								<td valign="top"><bean:write name="bronneni" property="variant" filter="true" /></td>
								<td valign="top"><bean:write name="bronneni" property="citaatHTML" filter="false" /></td>
							</tr>
						</logic:iterate>		
					</table>
				</td>
			</tr>
			<tr><td colspan="2"><hr /></td></tr>
		</logic:present>		
		<logic:present name="HulpmiddelenOverzichtForm" property="citaten">
			<tr>
				<td valign="top">
					<b>
						<logic:present name="HulpmiddelenOverzichtForm" property="taalvraag">
							<a href="../do/initCitatenInvoer?type=1&id=<bean:write name='HulpmiddelenOverzichtForm' property='taalvraag.id' />&ro=true<logic:equal name="HulpmiddelenOverzichtForm" property="adm" value="true">&adm=true</logic:equal>">
						</logic:present>
						<logic:present name="HulpmiddelenOverzichtForm" property="tekst">
							<a href="../do/initCitatenInvoer?type=2&id=<bean:write name='HulpmiddelenOverzichtForm' property='tekst.id' />&ro=true">
						</logic:present>							
							Citaat  
						</a>
					</b>
				</td>
				<td valign="top">
					<table cellpadding="2" width="100%" align="center">
						<tr bgcolor="#dddddd">
							<th align="left" valign="top">Zoekomgeving</th>
							<th align="left" valign="top">Specificatie</th>
							<th align="left" valign="top">URL</th>							
							<th align="left" valign="top">Variant</th>
							<th align="left" valign="top">Citaat</th>
						</tr>		
						<logic:iterate name="HulpmiddelenOverzichtForm" property="citaten" id="citateni">
							<tr>
								<td valign="top"><bean:write name="citateni" property="zoekomgeving.omschrijving" filter="true" /></td>
								<td valign="top"><bean:write name="citateni" property="specificatie" filter="true" /></td>
								<td valign="top"><a href="<bean:write name="citateni" property="url" filter="true" />" target="blank">link</a></td>								
								<td valign="top"><bean:write name="citateni" property="variant" filter="true" /></td>
								<td valign="top"><bean:write name="citateni" property="citaatHTML" filter="false" /></td>
							</tr>
						</logic:iterate>		
					</table>				
				</td>
			</tr>
			<tr><td colspan="2"><hr /></td></tr>		
		</logic:present>
		<logic:present name="HulpmiddelenOverzichtForm" property="frequenties">
			<tr>
				<td valign="top">
					<b>
						<logic:present name="HulpmiddelenOverzichtForm" property="taalvraag">
							<a href="../do/initFrequentiesInvoer?type=1&id=<bean:write name='HulpmiddelenOverzichtForm' property='taalvraag.id' />&ro=true<logic:equal name="HulpmiddelenOverzichtForm" property="adm" value="true">&adm=true</logic:equal>">
						</logic:present>
						<logic:present name="HulpmiddelenOverzichtForm" property="tekst">
							<a href="../do/initFrequentiesInvoer?type=2&id=<bean:write name='HulpmiddelenOverzichtForm' property='tekst.id' />&ro=true">
						</logic:present>							
							Frequentie   
						</a>
					</b>
				</td>
				<td valign="top">
					<table cellpadding="2" width="100%" align="center">
						<tr bgcolor="#dddddd">
							<th align="left" valign="top">Zoekomgeving</th>
							<th align="left" valign="top">Specificatie</th>
							<th align="left" valign="top">Variant</th>
							<th align="left" valign="top">Aantal</th>
						</tr>		
						<logic:iterate name="HulpmiddelenOverzichtForm" property="frequenties" id="frequentiesi">
							<tr>
								<td valign="top"><bean:write name="frequentiesi" property="zoekomgeving.omschrijving" filter="true" /></td>
								<td valign="top"><bean:write name="frequentiesi" property="specificatie" filter="true" /></td>
								<td valign="top"><bean:write name="frequentiesi" property="variant" filter="true" /></td>
								<td valign="top"><bean:write name="frequentiesi" property="aantal" filter="true" /></td>
							</tr>
						</logic:iterate>		
					</table>					
				</td>
			</tr>
			<tr><td colspan="2"><hr /></td></tr>
		</logic:present>
		<logic:present name="HulpmiddelenOverzichtForm" property="webreferenties">
			<tr>
				<td valign="top">
					<b>
						<logic:present name="HulpmiddelenOverzichtForm" property="taalvraag">
							<a href="../do/initWebreferentiesInvoer?type=1&id=<bean:write name='HulpmiddelenOverzichtForm' property='taalvraag.id' />&ro=true<logic:equal name="HulpmiddelenOverzichtForm" property="adm" value="true">&adm=true</logic:equal>">
						</logic:present>
						<logic:present name="HulpmiddelenOverzichtForm" property="tekst">
							<a href="../do/initWebreferentiesInvoer?type=2&id=<bean:write name='HulpmiddelenOverzichtForm' property='tekst.id' />&ro=true">
						</logic:present>
							Koppeling   
						</a>
					</b>
				</td>
				<td valign="top">
					<table cellspacing="5" width="100%" align="center">
						<tr bgcolor="#dddddd">
							<th align="left" valign="top">Omgeving</th>
							<th align="left" valign="top">URL</th>
							<th align="left" valign="top">Toelichting</th>
						</tr>		
						<logic:iterate name="HulpmiddelenOverzichtForm" property="webreferenties" id="webreferentiesi">
							<tr>
								<td valign="top"><bean:write name="webreferentiesi" property="omgeving" filter="true" /></td>
								<td valign="top"><a href="<bean:write name="webreferentiesi" property="url" filter="true" />" target="blank">link</a></td>
								<td valign="top"><bean:write name="webreferentiesi" property="toelichtingHTML" filter="false" /></td>
							</tr>
						</logic:iterate>		
					</table>
				</td>
			<tr>
			<tr><td colspan="2"><hr /></td></tr>
		</logic:present>
		<logic:present name="HulpmiddelenOverzichtForm" property="notities">
			<tr>
				<td valign="top">
					<b>
						<logic:present name="HulpmiddelenOverzichtForm" property="taalvraag">
							<a href="../do/initNotitiesInvoer?type=1&id=<bean:write name='HulpmiddelenOverzichtForm' property='taalvraag.id' />&ro=true<logic:equal name="HulpmiddelenOverzichtForm" property="adm" value="true">&adm=true</logic:equal>">
						</logic:present>
						<logic:present name="HulpmiddelenOverzichtForm" property="tekst">
							<a href="../do/initNotitiesInvoer?type=2&id=<bean:write name='HulpmiddelenOverzichtForm' property='tekst.id' />&ro=true">
						</logic:present>							
							Notitie   
						</a>
					</b>
				</td>
				<td valign="top">
					<table cellpadding="2" width="100%" align="center">
						<logic:iterate name="HulpmiddelenOverzichtForm" property="notities" id="notitiesi">
							<tr>
								<td valign="top"><b><bean:write name="notitiesi" property="gebruiker.voornaam" /> <bean:write name="notitiesi" property="gebruiker.naam" filter="true" /> op <bean:write name="notitiesi" property="datumString" />: </b></td>
							</tr>
							<tr>
								<td valign="top"><bean:write name="notitiesi" property="notitieHTML" filter="false" /></td>
							</tr>
						</logic:iterate>		
					</table>
				</td>
			</tr>
		</logic:present>
	</table>		
	
</html:form>
</body>
</html:html>