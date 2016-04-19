<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html-el"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/SecurityTagLib.tld" prefix="security" %>
<security:isLoggedIn />


<bean:define name="Oproep_TaalvragenInvoerForm" property="media" type="java.util.ArrayList" id="media" />
<bean:define name="Oproep_TaalvragenInvoerForm" property="herkomsten" type="java.util.ArrayList" id="herkomsten" />
<bean:define name="Oproep_TaalvragenInvoerForm" property="domeinen" type="java.util.ArrayList" id="domeinen" />
<bean:define name="Oproep_TaalvragenInvoerForm" property="taalvragen" type="java.util.ArrayList" id="taalvragenbean" />
<bean:define name="Oproep_TaalvragenInvoerForm" property="statussen" type="java.util.ArrayList" id="statussen" />
<bean:define name="Gebruiker" id="gebruiker" />
<bean:size name="taalvragenbean" id="taalvragenSize"/>

<html:html locale="true">
<head>
	<title>Databank Taaladvies</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="<html:rewrite page='/style/taaladvies.css' />" rel="stylesheet" type="text/css">
	<link href="<html:rewrite page='/style/taaladvies.js' />" type="text/JavaScript" />
	
	<script type="text/javascript" language="javascript" src="/eopro/editonpro.js"></script>
	
	<logic:equal name="eopro" value="true">
		<script language="JavaScript">
			
			strConfig = '<bean:write name="eoproconfig" filter="false"/>';
			strUIConfig = '<bean:write name="eoprouiconfig" filter="false"/>';
			strUIConfig3 = '<bean:write name="eoprouiconfig3" filter="false"/>';
			strUIConfig4 = '<bean:write name="eoprouiconfig4" filter="false"/>';
	
			var appletLoaded = new Array(4);
			
			for(var i=0;i<appletLoaded.length;i++)
				appletLoaded[i] = false;
	
			function TitelLoaded()
			{
				appletLoaded[0] = true;
			}
			
			function VraagLoaded()
			{
				appletLoaded[1] = true;
			}
	
			function AntwoordLoaded()
			{
				appletLoaded[2] = true;
			}
			
			function ToelichtingLoaded()
			{
				appletLoaded[3] = true;
			}
							
			function AppletsLoaded()
			{
				for(var i=0;i<appletLoaded.length;i++)
				{
					if(!appletLoaded[i])
					{
						return false;
					}
				}
				
				return true;
			}	
			</script>	
		</logic:equal>
		
		<script language="JavaScript">
			function doFocus(element, elementToFocus) {
				document.forms['Oproep_TaalvragenInvoerForm'].elements[elementToFocus].select();
			}
			
			function doSubmit(doAction){
				<logic:lessThan name="taalvragenSize" value="1">
					fnOnSubmit()
				</logic:lessThan>
				document.forms['Oproep_TaalvragenInvoerForm'].action = doAction;
				document.forms['Oproep_TaalvragenInvoerForm'].submit();
			}
			
			function changeMedium() {
				document.forms['Oproep_TaalvragenInvoerForm'].elements['oproep.distributie.mediumId'].value = document.forms['Oproep_TaalvragenInvoerForm'].elements['oproep.mediumId'].value;
			}
					
		</script>
	
	<logic:equal name="eopro" value="true">
		<logic:lessThan name="taalvragenSize" value="1">
			<script>	
				function fnOnLoad1() {
					titelEop.setHTMLData(document.Oproep_TaalvragenInvoerForm.elements["taalvraag.titel"].value);
					titelEop.pumpEvents();
				}
				function fnOnLoad2() {
					vraagEop.setHTMLData(document.Oproep_TaalvragenInvoerForm.elements["taalvraag.vraag"].value);
					vraagEop.pumpEvents();
				}
				function fnOnLoad3() {			
					antwoordEop.setHTMLData(document.Oproep_TaalvragenInvoerForm.elements["taalvraag.antwoordHTML"].value);
					antwoordEop.pumpEvents();
				}
				function fnOnLoad4() {			
					toelichtingEop.setHTMLData(document.Oproep_TaalvragenInvoerForm.elements["taalvraag.toelichtingHTML"].value);
					toelichtingEop.pumpEvents();
				}			
				
				function fnOnSubmit() {
					if(AppletsLoaded()) {
						document.Oproep_TaalvragenInvoerForm.elements["taalvraag.vraagHTML"].value =vraagEop.getHTMLData();
						document.Oproep_TaalvragenInvoerForm.elements["taalvraag.antwoordHTML"].value = antwoordEop.getHTMLData();
						document.Oproep_TaalvragenInvoerForm.elements["taalvraag.toelichtingHTML"].value = toelichtingEop.getHTMLData();
						document.Oproep_TaalvragenInvoerForm.elements["taalvraag.titelHTML"].value = titelEop.getHTMLData();
						document.Oproep_TaalvragenInvoerForm.elements["taalvraag.vraag"].value = vraagEop.getPlainText();
						document.Oproep_TaalvragenInvoerForm.elements["taalvraag.antwoord"].value = antwoordEop.getPlainText();
						document.Oproep_TaalvragenInvoerForm.elements["taalvraag.toelichting"].value = toelichtingEop.getPlainText();
						document.Oproep_TaalvragenInvoerForm.elements["taalvraag.titel"].value = titelEop.getPlainText();
					}
				}	
	
			</script>
		</logic:lessThan>
	</logic:equal>

	<logic:notEqual name="eopro" value="true">
		<logic:lessThan name="taalvragenSize" value="1">
			<script>		
				function fnOnSubmit() {
					document.Oproep_TaalvragenInvoerForm.elements["taalvraag.vraagHTML"].value =document.Oproep_TaalvragenInvoerForm.elements["taalvraag.vraag"].value;
					document.Oproep_TaalvragenInvoerForm.elements["taalvraag.antwoordHTML"].value = document.Oproep_TaalvragenInvoerForm.elements["taalvraag.antwoord"].value;
					document.Oproep_TaalvragenInvoerForm.elements["taalvraag.toelichtingHTML"].value = document.Oproep_TaalvragenInvoerForm.elements["taalvraag.toelichting"].value ;
					document.Oproep_TaalvragenInvoerForm.elements["taalvraag.titelHTML"].value =document.Oproep_TaalvragenInvoerForm.elements["taalvraag.titel"].value;
				}	
	
			</script>
		</logic:lessThan>
	</logic:notEqual>

		
	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>		
</head>

<body>
	
<html:form method="post" action="/oproep_TaalvragenInvoer">

<table width="80%" border="0">
	<tr>

		<logic:greaterThan name="taalvragenSize" value="0">
			<th><html:image property="In Behandeling" value="In Behandeling" page="/images/link_inbehandeling.gif" border="0"/></th>
			<th><html:image property="Nieuwe Oproep Taalvragen" value="Nieuwe Oproep Taalvragen" page="/images/link_nieuweoproeptaalvragen.gif" border="0" /></th>
			<th><html:image property="Nieuwe Oproep Tekst" value="Nieuwe Oproep Tekst" page="/images/link_nieuweoproeptekst.gif" border="0" /></th>
			<th><html:image property="Zoeken" value="Zoeken" page="/images/button_zoek.gif" border="0" /></th>
			<th><html:image property="Profielen" value="Profielen" page="/images/button_profielen.gif" border="0" /></th>		
		</logic:greaterThan>
		<logic:lessThan name="taalvragenSize" value="1">
			<th><html:image property="In Behandeling Alles" value="In Behandeling Alles" page="/images/link_inbehandeling.gif" border="0" onclick="return fnOnSubmit()" /></th>
			<th><html:image property="Nieuwe Oproep Taalvragen Alles" value="Nieuwe Oproep Taalvragen Alles" page="/images/link_nieuweoproeptaalvragen.gif" border="0" onclick="return fnOnSubmit()" /></th>
			<th><html:image property="Nieuwe Oproep Tekst Alles" value="Nieuwe Oproep Tekst Alles" page="/images/link_nieuweoproeptekst.gif" border="0" onclick="return fnOnSubmit()" /></th>
			<th><html:image property="Zoeken Alles" value="Zoeken Alles" page="/images/button_zoek.gif" border="0" onclick="return fnOnSubmit()" /></th>
			<th><html:image property="Profielen Alles" value="Profielen Alles" page="/images/button_profielen.gif" border="0" onclick="return fnOnSubmit()" /></th>
		</logic:lessThan>
	</tr>
</table>

<table class="borderbottom">
	<tr>
		<td class="h2style">
			<logic:lessThan name="taalvragenSize" value="1">
				INVOEREN 
			</logic:lessThan>
			<logic:greaterThan name="taalvragenSize" value="0">
				AANPASSEN 
			</logic:greaterThan>
			Oproep met taalvragen
		</td>
		
		<td class="modus" align="right">			
			<bean:message key="algemeen.modus"/>:	
			<logic:greaterThan name="taalvragenSize" value="0">
				<c:choose>
					<c:when test="${sessionScope.eopro}">						
						<input type="radio" name=useEopro value="true" onclick="javascript:doSubmit('../do/initOproep_TaalvragenInvoer?id=<c:out value="${Oproep_TaalvragenInvoerForm.oproep.id}"/>&Button=EditeerModus');" CHECKED/><bean:message key="algemeen.metopmaak"/>
						<input type="radio" name=useEopro value="false" onclick="javascript:doSubmit('../do/initOproep_TaalvragenInvoer?id=<c:out value="${Oproep_TaalvragenInvoerForm.oproep.id}"/>&Button=EditeerModus');" /><bean:message key="algemeen.eenvoudig"/>
					</c:when>
					<c:otherwise>
						<input type="radio" name=useEopro value="true" onclick="javascript:doSubmit('../do/initOproep_TaalvragenInvoer?id=<c:out value="${Oproep_TaalvragenInvoerForm.oproep.id}"/>&Button=EditeerModus');" /><bean:message key="algemeen.metopmaak"/>
						<input type="radio" name=useEopro value="false" onclick="javascript:doSubmit('../do/initOproep_TaalvragenInvoer?id=<c:out value="${Oproep_TaalvragenInvoerForm.oproep.id}"/>&Button=EditeerModus');" CHECKED/><bean:message key="algemeen.eenvoudig"/>
					</c:otherwise>
				</c:choose> 
				
				
			</logic:greaterThan>
			<logic:lessThan name="taalvragenSize" value="1">
				<c:choose>
					<c:when test="${sessionScope.eopro}">	
						<input type="radio" name=useEopro value="true" onclick="javascript:doSubmit('../do/oproep_TaalvragenInvoer?Button=EditeerModus');" CHECKED/><bean:message key="algemeen.metopmaak"/>
						<input type="radio" name=useEopro value="false" onclick="javascript:doSubmit('../do/oproep_TaalvragenInvoer?Button=EditeerModus');"/><bean:message key="algemeen.eenvoudig"/>
					</c:when>
					<c:otherwise>	
						<input type="radio" name=useEopro value="true" onclick="javascript:doSubmit('../do/oproep_TaalvragenInvoer?Button=EditeerModus');" /><bean:message key="algemeen.metopmaak"/>
						<input type="radio" name=useEopro value="false" onclick="javascript:doSubmit('../do/oproep_TaalvragenInvoer?Button=EditeerModus');" CHECKED/><bean:message key="algemeen.eenvoudig"/>
					</c:otherwise>
				</c:choose> 
			</logic:lessThan>
		
		</td>
	</tr>				
</table>


<html:errors />
		
				<table cellspacing="5">					
					<tr>
						<td colspan="6">
							<logic:equal name="Oproep_TaalvragenInvoerForm" property="oproep.id" value="0">
								<a class="bigger" href="javascript:doSubmit('../do/oproep_TaalvragenInvoer?Button=AllesToevoegen')">Oproep</a>
							</logic:equal>
							<logic:notEqual name="Oproep_TaalvragenInvoerForm" property="oproep.id" value="0">
								Oproep <bean:write name="Oproep_TaalvragenInvoerForm" property="oproep.id" />
							</logic:notEqual>						
						</td>
					</tr>
					<tr>
						<td><html:img page="/images/label_domein.gif" /></td>
						<td>
							<html:hidden name="Oproep_TaalvragenInvoerForm" property="oproep.id" />
							<html:hidden name="Oproep_TaalvragenInvoerForm" property="oproep.distributieId" />
													
							<html:select name="Oproep_TaalvragenInvoerForm" property="oproep.domeinId" tabindex="1">								
								<option value="0">Selecteer...</option>
								<logic:equal name="Oproep_TaalvragenInvoerForm" property="oproep.id" value="0">
									<option value="1" <logic:equal name="gebruiker" property="domeinId" value="1">SELECTED</logic:equal>>Intern</option>
									<option value="2" <logic:equal name="gebruiker" property="domeinId" value="2">SELECTED</logic:equal>>Extern</option>
								</logic:equal>
								<logic:notEqual name="Oproep_TaalvragenInvoerForm" property="oproep.id" value="0">
									<html:options collection="domeinen" property="id" labelProperty="omschrijving" />
								</logic:notEqual>	
							</html:select>
						</td>
						<td><html:img page="/images/label_datum_in.gif" /></td>
						<td>
							<html:text name="Oproep_TaalvragenInvoerForm" property="oproep.oproepdatum_d" size="2" maxlength="2" onfocus="this.select()" tabindex="5"  />/
							<html:text name="Oproep_TaalvragenInvoerForm" property="oproep.oproepdatum_m" size="2" maxlength="2" onfocus="this.select()" tabindex="6" />/
							<html:text name="Oproep_TaalvragenInvoerForm" property="oproep.oproepdatum_y" size="4" maxlength="4" onfocus="this.select()" tabindex="7" />
						</td>					
						<td><html:img page="/images/label_herkomst.gif" /></td>
						<td>
							<html:select name="Oproep_TaalvragenInvoerForm" property="oproep.herkomstId" tabindex="11" >
								<option value="0">Selecteer...</option>
								<html:options collection="herkomsten" property="id" labelProperty="omschrijving" />
							</html:select>
						</td>					
					</tr>
					<tr>
						<td><html:img page="/images/label_voornaam.gif" /></td>
						<td><html:text name="Oproep_TaalvragenInvoerForm" property="oproep.voornaam"  tabindex="2" /></td>
						<td><html:img page="/images/label_email.gif" /></td>
						<td><html:text name="Oproep_TaalvragenInvoerForm" property="oproep.email" size="30" tabindex="8" /></td>				
						<td><html:img page="/images/label_herkomstnummer.gif" /></td>
						<td><html:text name="Oproep_TaalvragenInvoerForm" property="oproep.herkomstnummer" tabindex="12" /></td>
					</tr>	
					<tr>
						<td><html:img page="/images/label_achternaam.gif" /></td>
						<td><html:text name="Oproep_TaalvragenInvoerForm" property="oproep.naam" tabindex="3" /></td>								
						<td><html:img page="/images/label_medium_in.gif" /></td>
						<td>
							<html:select name="Oproep_TaalvragenInvoerForm" property="oproep.mediumId" onchange="changeMedium()" tabindex="9" >
								<option value="0">Selecteer...</option>
								<html:options collection="media" property="id" labelProperty="omschrijving" />
							</html:select>
						</td>
						<td><html:img page="/images/label_status.gif" /></td>
						<td>
							<html:select name="Oproep_TaalvragenInvoerForm" property="oproep.statusId" tabindex="13" >
								<option value="0">Selecteer...</option>
								<html:options collection="statussen" property="id" labelProperty="omschrijving" />
							</html:select>
						</td>										
					</tr>	
					<tr>
						<td><html:img page="/images/label_telefoon.gif" /></td>
						<td><html:text name="Oproep_TaalvragenInvoerForm" property="oproep.telefoon" tabindex="4" /></td>					
						<td><html:img page="/images/label_medium_uit.gif" /></td>
						<td>
							<html:select name="Oproep_TaalvragenInvoerForm" property="oproep.distributie.mediumId" tabindex="10" >
								<option value="0">Selecteer...</option>
								<html:options collection="media" property="id" labelProperty="omschrijving" />
							</html:select>
						</td>
						<td><html:img page="/images/label_deadline.gif" /></td>
						<td>
							<html:text name="Oproep_TaalvragenInvoerForm" property="oproep.deadline_d" size="2" maxlength="2" onfocus="this.select()" tabindex="14"  />/
							<html:text name="Oproep_TaalvragenInvoerForm" property="oproep.deadline_m" size="2" maxlength="2" onfocus="this.select()" tabindex="15" />/
							<html:text name="Oproep_TaalvragenInvoerForm" property="oproep.deadline_y" size="4" maxlength="4" onfocus="this.select()" tabindex="16" />
						</td>		
					</tr>
					<tr>
						<td colspan="6" align="center">
							<logic:greaterThan name="taalvragenSize" value="0">
								<%--<input type="Button" name="Oproep Afsluiten" value="Oproep Afsluiten">--%>
								<html:image property="Oproep Afsluiten" value="Oproep Afsluiten" page="/images/button_oproepafsluiten.gif" onclick="return fnOnSubmit()" />
								<html:image property="Klant" value="Klant" page="/images/button_klant.gif" onclick="return fnOnSubmit()" />
								<html:image property="Nieuwe Taalvraag" value="Nieuwe Taalvraag" page="/images/button_nieuwetaalvraag.gif" onclick="return fnOnSubmit()" />
							</logic:greaterThan>
							<logic:lessThan name="taalvragenSize" value="1">
								<html:image property="Toevoegen" value="Toevoegen" page="/images/button_sluiten.gif" onclick="return fnOnSubmit()" />
								<html:image property="Annuleren" value="Annuleren" page="/images/button_oproep_annuleren.gif" onclick="return fnOnSubmit()" />	
							</logic:lessThan>
						</td>
					</tr>
				</table>
<hr />

		<logic:greaterThan name="taalvragenSize" value="0">
				<table cellspacing="5">					
								
					<tr bgcolor="#dddddd">
						<th>&nbsp;</th>
						<th align="left">Taalvraag</th>
						<th align="left">Categorie</th>
						<th align="left">Afgehandeld</th>
						<th align="left">Datum distributie</th>
						<th>&nbsp;</th>
					</tr>
					<logic:iterate id="taalvragen" name="taalvragenbean" indexId="index">
						<tr>
							<td valign="top">
								<a href="javascript:doSubmit('../do/oproep_TaalvragenInvoer?Button2=Verwijder&idToDelete=<bean:write name="taalvragen" property="id" />')"><img src="<html:rewrite page='/images/Delete24.gif' />" border="0" /></a>
							</td>
							<td valign="top">
								<a href="javascript:doSubmit('../do/oproep_TaalvragenInvoer?Button3=GoTo&idToGoTo=<bean:write name="taalvragen" property="id" />')">
									<logic:present name="taalvragen" property="titelHTML" >
										<logic:notEqual name="taalvragen" property="titelHTML" value="<p></p>">
										<logic:notEqual name="taalvragen" property="titelHTML" value="">
											<bean:write name="taalvragen" property="titel" filter="true" />
										</logic:notEqual>
										</logic:notEqual>
										<logic:equal name="taalvragen" property="titelHTML" value="<p></p>">
											<bean:write name="taalvragen" property="vraag" filter="true" />
										</logic:equal>
										<logic:equal name="taalvragen" property="titelHTML" value="">
											<bean:write name="taalvragen" property="vraag" filter="true" />
										</logic:equal>
									</logic:present>									
								</a>
							</td>
							<td valign="top">
								<logic:present name="taalvragen" property="categorien">
									<ul>
										<logic:iterate id="i" name="taalvragen" property="categorien">
											<li>
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
											</li>								
										</logic:iterate>
									</ul>
								</logic:present>
							</td>
							<td valign="top">
								<logic:equal name="taalvragen" property="afgehandeld" value="true">
									Ja
								</logic:equal>
							</td>
							<td valign="top">
								<bean:write name="taalvragen" property="distributiedatumString" filter="false" />
							</td>
							<td valign="top">
								<input type="checkbox" name="toDistribute_<bean:write name="index" />" CHECKED>
							</td>
						</tr>
					</logic:iterate>							
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>	
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td colspan="2" align="right">
							<html:image property="Distributie" value="Distributie" page="/images/button_distribueer.gif" />
						</td>
					</tr>
				</table>
		</logic:greaterThan>
	
	<logic:lessThan name="taalvragenSize" value="1">
	<html:hidden name="Oproep_TaalvragenInvoerForm" property="taalvraag.vraagHTML" />
	<html:hidden name="Oproep_TaalvragenInvoerForm" property="taalvraag.antwoordHTML" />
	<html:hidden name="Oproep_TaalvragenInvoerForm" property="taalvraag.toelichtingHTML" />
	<html:hidden name="Oproep_TaalvragenInvoerForm" property="taalvraag.titelHTML" />
	<logic:equal name="eopro" value="true">	
		<html:hidden name="Oproep_TaalvragenInvoerForm" property="taalvraag.vraag" />
		<html:hidden name="Oproep_TaalvragenInvoerForm" property="taalvraag.antwoord" />
		<html:hidden name="Oproep_TaalvragenInvoerForm" property="taalvraag.toelichting" />
		<html:hidden name="Oproep_TaalvragenInvoerForm" property="taalvraag.titel" />	
	</logic:equal>
					<table cellspacing="5">
						<tr>
							<td colspan="4" align="center">
								<%--<html:submit value="Alles Toevoegen" property="Button" onclick="return fnOnSubmit()" />--%>
								<html:image property="Nieuwe Taalvraag" value="Nieuwe Taalvraag" page="/images/button_nieuwetaalvraag.gif" onclick="return fnOnSubmit()" />
								<html:image property="Kopieer" value="Kopieer" page="/images/button_kopieer.gif" onclick="return fnOnSubmit()" />
								<html:image property="Klant" value="Klant" page="/images/button_klant.gif" onclick="return fnOnSubmit()" />	
								<html:image property="Koppel Tekst" value="Koppel Tekst" page="/images/button_koppel_tekst.gif" onclick="return fnOnSubmit()" />
							</td>
						</tr>						
						<tr>	
							<td colspan="4" align="center">
								<a href="javascript:doSubmit('../do/oproep_TaalvragenInvoer?Button=Categorie')">categorie</a>
								&nbsp;|&nbsp;
								<a href="javascript:doSubmit('../do/oproep_TaalvragenInvoer?Button=Naslagwerk')">naslagwerk</a>
								--
								<a href="javascript:doSubmit('../do/oproep_TaalvragenInvoer?Button=Bron')">bron</a>
								--
								<a href="javascript:doSubmit('../do/oproep_TaalvragenInvoer?Button=Citaat')">citaat</a>
								--
								<a href="javascript:doSubmit('../do/oproep_TaalvragenInvoer?Button=Frequentie')">frequentie</a>
								--
								<a href="javascript:doSubmit('../do/oproep_TaalvragenInvoer?Button=Koppeling')">koppeling</a>	
								&nbsp;|&nbsp;
								<a href="javascript:doSubmit('../do/oproep_TaalvragenInvoer?Button=Vraag')">vraag</a>
								--
								<a href="javascript:doSubmit('../do/oproep_TaalvragenInvoer?Button=Bijzonderheid')">bijzonderheid</a>
								&nbsp;|&nbsp;
								<a href="javascript:doSubmit('../do/oproep_TaalvragenInvoer?Button=Notitie')">notitie</a>
							</td>
						</tr>						
						<tr>
							<td width="18%"><html:img page="/images/label_vraagnummer.gif" /></td>
							<td width="3%"><bean:write name="Oproep_TaalvragenInvoerForm" property="taalvraag.volgnummer" /><html:hidden name="Oproep_TaalvragenInvoerForm" property="taalvraag.volgnummer" /></td>
							<td align="left"><html:img page="/images/label_afgehandeld.gif" />&nbsp;&nbsp;<html:checkbox name="Oproep_TaalvragenInvoerForm" property="taalvraag.afgehandeld" /></td>
							<td align="left">&nbsp;</td>	
						</tr>											
						<tr><td><html:img page="/images/label_titel.gif" /></td>
							<td colspan="3">
								<logic:equal name="eopro" value="true">
									<script type="text/javascript" language="javascript">	
										titelEop = new editOnPro(700, 90, "titel", "editor", "titelEop");
										titelEop.setCodebase("/eopro");
										titelEop.setUIConfig(strUIConfig);
										titelEop.setConfig(strConfig);
										titelEop.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
										titelEop.setStartUpScreenBackgroundColor("#EBF0FF");
										titelEop.setStartUpScreenTextColor("navy");
										titelEop.setLocaleCode("en_US");
										titelEop.setParam("bodyonly", "true");
										titelEop.setParam("oldfontstylemode","true");
										titelEop.setParam("sourceview","true");
										titelEop.setParam("cache_archive", "edit-on-pro-4.jar");
										titelEop.setParam("cache_option","plugin");
										titelEop.setOnEditorLoaded("fnOnLoad1");
										titelEop.setOnDataLoaded("TitelLoaded");				
										titelEop.loadEditor();
									</script> 	
								</logic:equal>
								
								<logic:notEqual name="eopro" value="true">
									<html:text name="Oproep_TaalvragenInvoerForm" property="taalvraag.titel" size="80"/>	
								</logic:notEqual>
<!--
								<applet code="com.realobjects.eop.applet.EditorApplet" archive="edit-on-pro-signed.jar,tidy.jar,ssce.jar" 
								codebase="/eopro/" height="90" width="700" name="titel" id="editor" MAYSCRIPT>
			
									<PARAM name="WINDOWFACECOLOR" value="#EBF0FF">
  							      	<PARAM name="TABPANEACTIVECOLOR" value="#cce3ff">
    							   	<PARAM name="WINDOWHIGHLIGHTCOLOR" value="#FFffff">
    							   	<PARAM name="LIGHTEDGECOLOR" value="#ebf0ff">
     							  	<PARAM name="DARKEDGECOLOR" value="#C5CCFF">
      							 	<PARAM name="INNERTEXTCOLOR" value="#000000">
       								<PARAM name="STARTUPSCREENBACKGROUNDCOLOR" VALUE="#EBF0FF">
       								<PARAM name="STARTUPSCREENTEXTCOLOR" VALUE="navy">	
									<PARAM name="CABBASE" value="tidy.cab,ssce.cab,edit-on-pro-signed.cab" />
									<PARAM name="LOCALE" value="en_US" />
									<PARAM name="TOOLBARURL" value="toolbar-taaladvies.xml" />
									<PARAM name="CONFIGURL" value="config-taaladvies.xml" />
									<PARAM name="TABLENSPFILL" value="true" />
									<PARAM name="BODYONLY" value="true" />
									<PARAM name="OLDFONTSTYLEMODE" value="true" />
									<PARAM name="SOURCEVIEW" value="true" />
									<PARAM name="NAMESPACE" value="roEOP3" />
									<PARAM name="USESLIBRARY" value="RealObjects edit-on Pro 3.1" />
									<PARAM name="USESLIBRARYCODEBASE" value="edit-on-pro-signed.cab" />
									<PARAM name="USESLIBRARYVERSION" value="3,1,200,0" />
									<PARAM name="cache_option" value="plugin" />
									<PARAM name="cache_archive" value="edit-on-pro-signed.jar,ssce.jar,tidy.jar" />
									<PARAM name="cache_version" value="3.1.200.0,0.0.0.0,0.0.0.0" />
						            <PARAM name="ONEDITORLOADED" value="fnOnLoad1"/>									
									<PARAM name="ONDATALOADED" value="TitelLoaded" />								
								</applet>
-->
							</td>
						</tr>						
						<tr><td><html:img page="/images/label_vraag.gif" /></td>
							<td colspan="3">
								<logic:equal name="eopro" value="true">
									<script type="text/javascript" language="javascript">
										vraagEop = new editOnPro(700, 120, "vraag", "editor", "vraagEop");
										vraagEop.setCodebase("/eopro");
										vraagEop.setUIConfig(strUIConfig);
										vraagEop.setConfig(strConfig);
										vraagEop.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
										vraagEop.setStartUpScreenBackgroundColor("#EBF0FF");
										vraagEop.setStartUpScreenTextColor("navy");
										vraagEop.setLocaleCode("en_US");
										vraagEop.setParam("bodyonly", "true");
										vraagEop.setParam("oldfontstylemode","true");
										vraagEop.setParam("sourceview","true");
										vraagEop.setParam("cache_archive", "edit-on-pro-4.jar");
										vraagEop.setParam("cache_option","plugin");
										vraagEop.setOnEditorLoaded("fnOnLoad2");
										vraagEop.setOnDataLoaded("VraagLoaded");
										vraagEop.loadEditor();
									</script> 	
								</logic:equal>	
								
								<logic:notEqual name="eopro" value="true">
									<html:textarea name="Oproep_TaalvragenInvoerForm" property="taalvraag.vraag"  cols="80" rows="5"/>
								</logic:notEqual>
<!--
								<applet code="com.realobjects.eop.applet.EditorApplet" archive="edit-on-pro-signed.jar,tidy.jar,ssce.jar" 
								codebase="/eopro/" height="120" width="700" name="vraag" id="editor" MAYSCRIPT>
			
									<PARAM name="WINDOWFACECOLOR" value="#EBF0FF">
  							      	<PARAM name="TABPANEACTIVECOLOR" value="#cce3ff">
   		 						   	<PARAM name="WINDOWHIGHLIGHTCOLOR" value="#FFffff">
   		 						   	<PARAM name="LIGHTEDGECOLOR" value="#ebf0ff">
   		  						  	<PARAM name="DARKEDGECOLOR" value="#C5CCFF">
   		   						 	<PARAM name="INNERTEXTCOLOR" value="#000000">
    	   							<PARAM name="STARTUPSCREENBACKGROUNDCOLOR" VALUE="#EBF0FF">
    	   							<PARAM name="STARTUPSCREENTEXTCOLOR" VALUE="navy">	
									<PARAM name="CABBASE" value="tidy.cab,ssce.cab,edit-on-pro-signed.cab" />
									<PARAM name="LOCALE" value="en_US" />
									<PARAM name="TOOLBARURL" value="toolbar-taaladvies.xml" />
									<PARAM name="CONFIGURL" value="config-taaladvies.xml" />
									<PARAM name="TABLENSPFILL" value="true" />
									<PARAM name="BODYONLY" value="true" />
									<PARAM name="OLDFONTSTYLEMODE" value="true" />
									<PARAM name="SOURCEVIEW" value="true" />
									<PARAM name="NAMESPACE" value="roEOP3" />
									<PARAM name="USESLIBRARY" value="RealObjects edit-on Pro 3.1" />
									<PARAM name="USESLIBRARYCODEBASE" value="edit-on-pro-signed.cab" />
									<PARAM name="USESLIBRARYVERSION" value="3,1,200,0" />
									<PARAM name="cache_option" value="plugin" />
									<PARAM name="cache_archive" value="edit-on-pro-signed.jar,ssce.jar,tidy.jar" />
									<PARAM name="cache_version" value="3.1.200.0,0.0.0.0,0.0.0.0" />						            
						            <PARAM name="ONEDITORLOADED" value="fnOnLoad2"/>
									<PARAM name="ONDATALOADED" value="VraagLoaded" />							
								</applet>
-->
							</td>
						</tr>
						<tr><td><html:img page="/images/label_antwoord.gif" /></td>	
							<td colspan="3">
								<logic:equal name="eopro" value="true">
									<script type="text/javascript" language="javascript">
										antwoordEop = new editOnPro(700, 170, "antwoord", "editor", "antwoordEop");
										antwoordEop.setCodebase("/eopro");								
										antwoordEop.setUIConfig(strUIConfig3);
										antwoordEop.setConfig(strConfig);
										antwoordEop.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
										antwoordEop.setStartUpScreenBackgroundColor("#EBF0FF");
										antwoordEop.setStartUpScreenTextColor("navy");
										antwoordEop.setLocaleCode("en_US");
										antwoordEop.setParam("bodyonly", "true");
										antwoordEop.setParam("oldfontstylemode","true");
										antwoordEop.setParam("sourceview","true");
										antwoordEop.setParam("cache_archive", "edit-on-pro-4.jar");
										antwoordEop.setParam("cache_option","plugin");
										antwoordEop.setOnEditorLoaded("fnOnLoad3");
										antwoordEop.setOnDataLoaded("AntwoordLoaded");						
										antwoordEop.loadEditor();
									</script> 	
								</logic:equal>
								
								<logic:notEqual name="eopro" value="true">
									<html:textarea name="Oproep_TaalvragenInvoerForm" property="taalvraag.antwoord"  cols="80" rows="5"/>
								</logic:notEqual>
<!--
								<applet code="com.realobjects.eop.applet.EditorApplet" archive="edit-on-pro-signed.jar,tidy.jar,ssce.jar" 
								codebase="/eopro/" height="170" width="700" name="antwoord" id="editor" MAYSCRIPT>
			
									<PARAM name="WINDOWFACECOLOR" value="#EBF0FF">
  							      	<PARAM name="TABPANEACTIVECOLOR" value="#cce3ff">
    							   	<PARAM name="WINDOWHIGHLIGHTCOLOR" value="#FFffff">
    							   	<PARAM name="LIGHTEDGECOLOR" value="#ebf0ff">
     							  	<PARAM name="DARKEDGECOLOR" value="#C5CCFF">
      							 	<PARAM name="INNERTEXTCOLOR" value="#000000">
       								<PARAM name="STARTUPSCREENBACKGROUNDCOLOR" VALUE="#EBF0FF">
       								<PARAM name="STARTUPSCREENTEXTCOLOR" VALUE="navy">	
									<PARAM name="CABBASE" value="tidy.cab,ssce.cab,edit-on-pro-signed.cab" />
									<PARAM name="LOCALE" value="en_US" />
									<PARAM name="TOOLBARURL" value="toolbar-taaladvies3.xml" />
									<PARAM name="CONFIGURL" value="config-taaladvies.xml" />
									<PARAM name="TABLENSPFILL" value="true" />
									<PARAM name="BODYONLY" value="true" />
									<PARAM name="OLDFONTSTYLEMODE" value="true" />
									<PARAM name="SOURCEVIEW" value="true" />
									<PARAM name="NAMESPACE" value="roEOP3" />
									<PARAM name="USESLIBRARY" value="RealObjects edit-on Pro 3.1" />
									<PARAM name="USESLIBRARYCODEBASE" value="edit-on-pro-signed.cab" />
									<PARAM name="USESLIBRARYVERSION" value="3,1,200,0" />
									<PARAM name="cache_option" value="plugin" />
									<PARAM name="cache_archive" value="edit-on-pro-signed.jar,ssce.jar,tidy.jar" />
									<PARAM name="cache_version" value="3.1.200.0,0.0.0.0,0.0.0.0" />
						            <PARAM name="ONEDITORLOADED" value="fnOnLoad3"/>								
									<PARAM name="ONDATALOADED" value="AntwoordLoaded" />
								</applet>
-->
							</td>
						</tr>
						<tr><td><html:img page="/images/label_toelichting.gif" /></td>
							<td colspan="3">
								<logic:equal name="eopro" value="true">
									<script type="text/javascript" language="javascript">								
										toelichtingEop = new editOnPro(700, 400, "toelichting", "editor", "toelichtingEop");
										toelichtingEop.setCodebase("/eopro");									
										toelichtingEop.setUIConfig(strUIConfig4);
										toelichtingEop.setConfig(strConfig);
										toelichtingEop.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
										toelichtingEop.setStartUpScreenBackgroundColor("#EBF0FF");
										toelichtingEop.setStartUpScreenTextColor("navy");
										toelichtingEop.setLocaleCode("en_US");
										toelichtingEop.setParam("bodyonly", "true");
										toelichtingEop.setParam("oldfontstylemode","true");
										toelichtingEop.setParam("sourceview","true");
										toelichtingEop.setParam("cache_archive", "edit-on-pro-4.jar");
										toelichtingEop.setParam("cache_option","plugin");
										toelichtingEop.setOnEditorLoaded("fnOnLoad4");
										toelichtingEop.setOnDataLoaded("ToelichtingLoaded");					
										toelichtingEop.loadEditor();								
									</script> 	
								</logic:equal>
								
								<logic:notEqual name="eopro" value="true">
									<html:textarea name="Oproep_TaalvragenInvoerForm" property="taalvraag.toelichting"  cols="80" rows="15"/>	
								</logic:notEqual>
<!--
								<applet code="com.realobjects.eop.applet.EditorApplet" archive="edit-on-pro-signed.jar,tidy.jar,ssce.jar" 
								codebase="/eopro/" height="400" width="700" name="toelichting" id="editor" MAYSCRIPT>
			
									<PARAM name="WINDOWFACECOLOR" value="#EBF0FF">
  							      	<PARAM name="TABPANEACTIVECOLOR" value="#cce3ff">
    							   	<PARAM name="WINDOWHIGHLIGHTCOLOR" value="#FFffff">
    							   	<PARAM name="LIGHTEDGECOLOR" value="#ebf0ff">
     							  	<PARAM name="DARKEDGECOLOR" value="#C5CCFF">
      							 	<PARAM name="INNERTEXTCOLOR" value="#000000">
       								<PARAM name="STARTUPSCREENBACKGROUNDCOLOR" VALUE="#EBF0FF">
       								<PARAM name="STARTUPSCREENTEXTCOLOR" VALUE="navy">	
									<PARAM name="CABBASE" value="tidy.cab,ssce.cab,edit-on-pro-signed.cab" />
									<PARAM name="LOCALE" value="en_US" />
									<PARAM name="TOOLBARURL" value="toolbar-taaladvies4.xml" />
									<PARAM name="CONFIGURL" value="config-taaladvies.xml" />
									<PARAM name="TABLENSPFILL" value="true" />
									<PARAM name="BODYONLY" value="true" />
									<PARAM name="OLDFONTSTYLEMODE" value="true" />
									<PARAM name="SOURCEVIEW" value="true" />
									<PARAM name="NAMESPACE" value="roEOP3" />
									<PARAM name="USESLIBRARY" value="RealObjects edit-on Pro 3.1" />
									<PARAM name="USESLIBRARYCODEBASE" value="edit-on-pro-signed.cab" />
									<PARAM name="USESLIBRARYVERSION" value="3,1,200,0" />
									<PARAM name="cache_option" value="plugin" />
									<PARAM name="cache_archive" value="edit-on-pro-signed.jar,ssce.jar,tidy.jar" />
									<PARAM name="cache_version" value="3.1.200.0,0.0.0.0,0.0.0.0" />
						            <PARAM name="ONEDITORLOADED" value="fnOnLoad4"/>									
									<PARAM name="ONDATALOADED" value="ToelichtingLoaded" />							
								</applet>
-->
							</td>
						</tr>
					</table>
	</logic:lessThan>
</html:form>
</body>
</html:html>