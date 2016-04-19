<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/SecurityTagLib.tld" prefix="security" %>
<security:isLoggedIn />

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
			
			var appletLoaded = false
			
			function TitelLoaded()
			{
				appletLoaded = true;
			}				
			
			function fnOnLoad() {
				titelEop.setHTMLData(document.Oproep_TekstInvoerForm.elements["tekst.titelHTML"].value);
				titelEop.pumpEvents();
			}
			function fnOnSubmit() {
				if (appletLoaded) {
					document.Oproep_TekstInvoerForm.elements["tekst.titelHTML"].value = titelEop.getHTMLData();
					document.Oproep_TekstInvoerForm.elements["tekst.titel"].value = titelEop.getPlainText();	
				}
			}	
			function doFocus(element, elementToFocus) {
				if (document.forms['Oproep_TekstInvoerForm'].elements[element].value.length == 2) {
					document.forms['Oproep_TekstInvoerForm'].elements[elementToFocus].value = '';
					document.forms['Oproep_TekstInvoerForm'].elements[elementToFocus].select();
				}
			}
			
			function doSubmit(doAction){
				fnOnSubmit()
				document.forms['Oproep_TekstInvoerForm'].action = doAction;
				document.forms['Oproep_TekstInvoerForm'].submit();
			}
			
			function changeMedium() {
				document.forms['Oproep_TekstInvoerForm'].elements['oproep.distributie.mediumId'].value = document.forms['Oproep_TekstInvoerForm'].elements['oproep.mediumId'].value;
			}		
		</script>
	</logic:equal>
	
	<logic:notEqual name="eopro" value="true">
		<script language="JavaScript">
			function doSubmit(doAction){
				fnOnSubmit()
				document.forms['Oproep_TekstInvoerForm'].action = doAction;
				document.forms['Oproep_TekstInvoerForm'].submit();
			}
			
			function changeMedium() {
				document.forms['Oproep_TekstInvoerForm'].elements['oproep.distributie.mediumId'].value = document.forms['Oproep_TekstInvoerForm'].elements['oproep.mediumId'].value;
			}	
			
			function fnOnSubmit() {
				document.Oproep_TekstInvoerForm.elements["tekst.titelHTML"].value = document.Oproep_TekstInvoerForm.elements["tekst.titel"].value;
			}		
		</script>
	</logic:notEqual>
	
	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>			
</head>

<body>

<bean:define name="Oproep_TekstInvoerForm" property="media" type="java.util.ArrayList" id="media" />
<bean:define name="Oproep_TekstInvoerForm" property="herkomsten" type="java.util.ArrayList" id="herkomsten" />
<bean:define name="Oproep_TekstInvoerForm" property="domeinen" type="java.util.ArrayList" id="domeinen" />
<bean:define name="Oproep_TekstInvoerForm" property="tekstblokken" type="java.util.ArrayList" id="tekstblokkenbean" />
<bean:define name="Oproep_TekstInvoerForm" property="statussen" type="java.util.ArrayList" id="statussen" />
<bean:define name="Oproep_TekstInvoerForm" property="relevanties" type="java.util.ArrayList" id="relevanties" />
<bean:define name="Gebruiker" id="gebruiker" />

<html:form method="post" action="/oproep_TekstInvoer">
	<html:hidden name="Oproep_TekstInvoerForm" property="tekst.titelHTML" />
	<logic:equal name="eopro" value="true">
		<html:hidden name="Oproep_TekstInvoerForm" property="tekst.titel" />	
	</logic:equal>
<table width="80%" border="0">
	<tr>
		<logic:notEqual name="Oproep_TekstInvoerForm" property="tekst.id" value="0">
			<th><html:image property="In Behandeling" value="In Behandeling" page="/images/link_inbehandeling.gif" border="0" onclick="return fnOnSubmit()" /></th>
			<th><html:image property="Nieuwe Oproep Taalvragen" value="Nieuwe Oproep Taalvragen" page="/images/link_nieuweoproeptaalvragen.gif" border="0" onclick="return fnOnSubmit()" /></th>
			<th><html:image property="Nieuwe Oproep Tekst" value="Nieuwe Oproep Tekst" page="/images/link_nieuweoproeptekst.gif" border="0" onclick="return fnOnSubmit()" /></th>
			<th><html:image property="Zoeken" value="Zoeken" page="/images/button_zoek.gif" border="0" onclick="return fnOnSubmit()" /></th>
			<th><html:image property="Profielen" value="Profielen" page="/images/button_profielen.gif" border="0" onclick="return fnOnSubmit()" /></th>
		</logic:notEqual>
		<logic:equal name="Oproep_TekstInvoerForm" property="tekst.id" value="0">		
			<th><html:image property="In Behandeling Alles" value="In Behandeling Alles" page="/images/link_inbehandeling.gif" border="0" onclick="return fnOnSubmit()" /></th>
			<th><html:image property="Nieuwe Oproep Taalvragen Alles" value="Nieuwe Oproep Taalvragen Alles" page="/images/link_nieuweoproeptaalvragen.gif" border="0" onclick="return fnOnSubmit()" /></th>
			<th><html:image property="Nieuwe Oproep Tekst Alles" value="Nieuwe Oproep Tekst Alles" page="/images/link_nieuweoproeptekst.gif" border="0" onclick="return fnOnSubmit()" /></th>
			<th><html:image property="Zoeken Alles" value="Zoeken Alles" page="/images/button_zoek.gif" border="0" onclick="return fnOnSubmit()" /></th>
			<th><html:image property="Profielen Alles" value="Profielen Alles" page="/images/button_profielen.gif" border="0" onclick="return fnOnSubmit()" /></th>
		</logic:equal>




		<!--	
		<th><html:link page="/do/initEigenTaalvragen"><html:img page="/images/link_inbehandeling.gif" border="0" /></html:link></th>
		<th><html:link page="/do/initOproep_TaalvragenInvoer"><html:img page="/images/link_nieuweoproeptaalvragen.gif" border="0" /></html:link></th>
		<th><html:link page="/do/initOproep_TekstInvoer"><html:img page="/images/link_nieuweoproeptekst.gif" border="0" /></html:link></th>
		<th><html:link page="/do/initZoeken"><html:img page="/images/button_zoek.gif" border="0" /></html:link></th>
		<th><html:link page="/profielen.jsp"><html:img page="/images/button_profielen.gif" border="0" /></html:link></th>
		-->
	</tr>
</table>
<br>

<table class="borderbottom">
	<tr>
		<td class="h2style">
			<logic:equal name="Oproep_TekstInvoerForm" property="oproep.id" value="0">
				INVOEREN 
			</logic:equal>
			<logic:notEqual name="Oproep_TekstInvoerForm" property="oproep.id" value="0">
				AANPASSEN 
			</logic:notEqual>
			Oproep voor een tekst
		</td>
		
		<td class="modus" align="right">		
			<bean:message key="algemeen.modus"/>:
			<logic:notEqual name="Oproep_TekstInvoerForm" property="oproep.id" value="0">
				<c:choose>
					<c:when test="${sessionScope.eopro}">
						<input type="radio" name=useEopro value="true" onclick="javascript:doSubmit('../do/oproep_TekstInvoer?Button=EditeerModus&U=true');" CHECKED/><bean:message key="algemeen.metopmaak"/>
						<input type="radio" name=useEopro value="false" onclick="javascript:doSubmit('../do/oproep_TekstInvoer?Button=EditeerModus&U=true');" /><bean:message key="algemeen.eenvoudig"/>
					</c:when>
					<c:otherwise>
						<input type="radio" name=useEopro value="true" onclick="javascript:doSubmit('../do/oproep_TekstInvoer?Button=EditeerModus&U=true');" /><bean:message key="algemeen.metopmaak"/>
						<input type="radio" name=useEopro value="false" onclick="javascript:doSubmit('../do/oproep_TekstInvoer?Button=EditeerModus&U=true');" CHECKED/><bean:message key="algemeen.eenvoudig"/>						
					</c:otherwise>
				</c:choose> 				
			</logic:notEqual>

			<logic:equal name="Oproep_TekstInvoerForm" property="oproep.id" value="0">
				<c:choose>
					<c:when test="${sessionScope.eopro}">
						<input type="radio" name=useEopro value="true" onclick="javascript:doSubmit('../do/oproep_TekstInvoer?Button=EditeerModus');" CHECKED/><bean:message key="algemeen.metopmaak"/>
						<input type="radio" name=useEopro value="false" onclick="javascript:doSubmit('../do/oproep_TekstInvoer?Button=EditeerModus');" /><bean:message key="algemeen.eenvoudig"/>						
					</c:when>
					<c:otherwise>
						<input type="radio" name=useEopro value="true" onclick="javascript:doSubmit('../do/oproep_TekstInvoer?Button=EditeerModus');" /><bean:message key="algemeen.metopmaak"/>					
						<input type="radio" name=useEopro value="false" onclick="javascript:doSubmit('../do/oproep_TekstInvoer?Button=EditeerModus');" CHECKED/><bean:message key="algemeen.eenvoudig"/>
					</c:otherwise>
				</c:choose> 		
			</logic:equal>


		</td>
	</tr>				
</table>

<html:errors />
				<table cellspacing="5">					
					<tr>
						<td colspan="6">
							<logic:notEqual name="Oproep_TekstInvoerForm" property="oproep.id" value="0">
								Oproep <bean:write name="Oproep_TekstInvoerForm" property="oproep.id" />
							</logic:notEqual>						
						</td>
					</tr>					
					<tr>
						<td><html:img page="/images/label_domein.gif" /></td>
						<td>
						
							<html:hidden name="Oproep_TekstInvoerForm" property="oproep.id" />
							<html:hidden name="Oproep_TekstInvoerForm" property="oproep.distributieId" />

							<html:select name="Oproep_TekstInvoerForm" property="oproep.domeinId" tabindex="1" >
								<option value="0">Selecteer...</option>
								<logic:equal name="Oproep_TekstInvoerForm" property="oproep.id" value="0">
									<option value="1" <logic:equal name="gebruiker" property="domeinId" value="1">SELECTED</logic:equal>>Intern</option>
									<option value="2" <logic:equal name="gebruiker" property="domeinId" value="2">SELECTED</logic:equal>>Extern</option>
								</logic:equal>
								<logic:notEqual name="Oproep_TekstInvoerForm" property="oproep.id" value="0">
									<html:options collection="domeinen" property="id" labelProperty="omschrijving" />
								</logic:notEqual>
							</html:select>
						</td>
						<td><html:img page="/images/label_startdatum.gif" /></td>
						<td>
							<html:text name="Oproep_TekstInvoerForm" property="oproep.oproepdatum_d" size="2" maxlength="2" onfocus="this.select()" tabindex="5" />/
							<html:text name="Oproep_TekstInvoerForm" property="oproep.oproepdatum_m" size="2" maxlength="2" onfocus="this.select()" tabindex="6" />/
							<html:text name="Oproep_TekstInvoerForm" property="oproep.oproepdatum_y" size="4" maxlength="4" onfocus="this.select()" tabindex="7" />
						</td>					
						<td><html:img page="/images/label_herkomst.gif" /></td>
						<td>
							<html:select name="Oproep_TekstInvoerForm" property="oproep.herkomstId" tabindex="11" >
								<option value="0">Selecteer...</option>
								<html:options collection="herkomsten" property="id" labelProperty="omschrijving" />
							</html:select>
						</td>					
					</tr>
					<tr>
						<td><html:img page="/images/label_voornaam.gif" /></td>
						<td><html:text name="Oproep_TekstInvoerForm" property="oproep.voornaam" tabindex="2" /></td>					
						<td><html:img page="/images/label_email.gif" /></td>
						<td><html:text name="Oproep_TekstInvoerForm" property="oproep.email" size="30" tabindex="8" /></td>						
						<td><html:img page="/images/label_herkomstnummer.gif" /></td>
						<td><html:text name="Oproep_TekstInvoerForm" property="oproep.herkomstnummer" tabindex="12" /></td>
					</tr>
					<tr>
						<td><html:img page="/images/label_achternaam.gif" /></td>
						<td><html:text name="Oproep_TekstInvoerForm" property="oproep.naam" tabindex="3" /></td>
						<td><html:img page="/images/label_medium_in.gif" /></td>
						<td>
							<html:select name="Oproep_TekstInvoerForm" property="oproep.mediumId" onchange="changeMedium()" tabindex="9" >
								<option value="0">Selecteer...</option>
								<html:options collection="media" property="id" labelProperty="omschrijving" />
							</html:select>
						</td>					
						<td><html:img page="/images/label_status.gif" /></td>
						<td>
							<html:select name="Oproep_TekstInvoerForm" property="oproep.statusId" tabindex="13" >
								<option value="0">Selecteer...</option>
								<html:options collection="statussen" property="id" labelProperty="omschrijving" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td><html:img page="/images/label_telefoon.gif" /></td>
						<td><html:text name="Oproep_TekstInvoerForm" property="oproep.telefoon" tabindex="4" /></td>					
						<td><html:img page="/images/label_medium_uit.gif" /></td>
						<td>
							<html:select name="Oproep_TekstInvoerForm" property="oproep.distributie.mediumId" tabindex="10" >
								<option value="0">Selecteer...</option>
								<html:options collection="media" property="id" labelProperty="omschrijving" />
							</html:select>
						</td>
						<td><html:img page="/images/label_deadline.gif" /></td>
						<td>
							<html:text name="Oproep_TekstInvoerForm" property="oproep.deadline_d" size="2" maxlength="2" onfocus="this.select()" tabindex="14" />/
							<html:text name="Oproep_TekstInvoerForm" property="oproep.deadline_m" size="2" maxlength="2" onfocus="this.select()" tabindex="15" />/
							<html:text name="Oproep_TekstInvoerForm" property="oproep.deadline_y" size="4" maxlength="4" onfocus="this.select()" tabindex="16" />
						</td>					
					</tr>
				</table>
<hr />	
				<table cellspacing="5">							
					<tr>	
						<td colspan="3" align="center">
							<a href="javascript:doSubmit('../do/oproep_TekstInvoer?Button=Categorie')">categorie</a>
							&nbsp;|&nbsp;
							<a href="javascript:doSubmit('../do/oproep_TekstInvoer?Button=Naslagwerk')">naslagwerk</a>
							--
							<a href="javascript:doSubmit('../do/oproep_TekstInvoer?Button=Bron')">bron</a>
							--
							<a href="javascript:doSubmit('../do/oproep_TekstInvoer?Button=Citaat')">citaat</a>
							--
							<a href="javascript:doSubmit('../do/oproep_TekstInvoer?Button=Frequentie')">frequentie</a>
							--
							<a href="javascript:doSubmit('../do/oproep_TekstInvoer?Button=Koppeling')">koppeling</a>	
							&nbsp;|&nbsp;
							<a href="javascript:doSubmit('../do/oproep_TekstInvoer?Button=Notitie')">notitie</a>
						</td>
					</tr>
					<tr>
						<td width="18%"><html:img page="/images/label_titel.gif" /></td>
						<td colspan="2">
							<logic:equal name="eopro" value="true">
								<script type="text/javascript" language="javascript">	
									titelEop = new editOnPro(700, 120, "titel", "editor", "titelEop");
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
									titelEop.setOnEditorLoaded("fnOnLoad");
									titelEop.setOnDataLoaded("TitelLoaded");				
									titelEop.loadEditor();
								</script> 	
							</logic:equal>
							
							<logic:notEqual name="eopro" value="true">
								<html:text name="Oproep_TekstInvoerForm" property="tekst.titel" style="width: 99%;"/>	
							</logic:notEqual>
<!--
							<applet code="com.realobjects.eop.applet.EditorApplet" archive="edit-on-pro-signed.jar,tidy.jar,ssce.jar" 
							codebase="/eopro/" height="120" width="700" name="titel" id="editor" MAYSCRIPT>

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
					            <PARAM name="ONEDITORLOADED" value="fnOnLoad"/>
								<PARAM name="ONDATALOADED" value="TitelLoaded" />							
							</applet>	
-->
						</td>
					</tr>
					<tr>
						<td><html:img page="/images/label_relevantie.gif" /></td>
						<td colspan="2">
							<html:select name="Oproep_TekstInvoerForm" property="tekst.relevantieId">
								<option value="0">Selecteer...</option>
								<html:options collection="relevanties" property="id" labelProperty="omschrijving" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center">	
							<logic:notEqual name="Oproep_TekstInvoerForm" property="tekst.id" value="0">
								<html:image property="Nieuw Tekstblok" value="Nieuw Tekstblok" page="/images/button_nieuw_tekstblok.gif" onclick="return fnOnSubmit()" />
								<html:image property="Distributie" value="Distributie" page="/images/button_distribueer.gif" onclick="return fnOnSubmit()" />
								<html:image property="Volledige Tekst" value="Volledige Tekst" page="/images/button_overzicht.gif" onclick="return fnOnSubmit()" />
								<html:image property="Oproep Afsluiten" value="Oproep Afsluiten" page="/images/button_oproepafsluiten.gif" onclick="return fnOnSubmit()" />											
							</logic:notEqual>
							<logic:equal name="Oproep_TekstInvoerForm" property="tekst.id" value="0">
								<html:image property="Nieuw Tekstblok" value="Nieuw Tekstblok" page="/images/button_nieuw_tekstblok.gif" onclick="return fnOnSubmit()" />
							</logic:equal>			
							<html:hidden name="Oproep_TekstInvoerForm" property="tekst.id" />
							<html:image property="Kopieer" value="Kopieer" page="/images/button_kopieer.gif" onclick="return fnOnSubmit()" />
							<html:image property="Klant" value="Klant" page="/images/button_klant.gif" onclick="return fnOnSubmit()" />	
							<logic:equal name="Oproep_TekstInvoerForm" property="tekst.id" value="0">
								<html:link page="/do/initEigenTeksten"><html:img page="/images/button_oproep_annuleren.gif" border="0" /></html:link>
							</logic:equal>
						</td>
					</tr>			
					<tr bgcolor="#dddddd">
						<th>&nbsp;</th>
						<th align="left">Tekstblok</th>
						<th>&nbsp;</th>
					</tr>
					<logic:iterate id="tekstblokkeni" name="tekstblokkenbean">
						<tr>
							<td>
								<a href="javascript:doSubmit('../do/oproep_TekstInvoer?Button2=Verwijder&idToDelete=<bean:write name="tekstblokkeni" property="id" />')"><img src="<html:rewrite page='/images/Delete24.gif' />" border="0" /></a>
							</td>							
							<td>
								<a href="../do/initTekstblokkenInvoer?parentId=<bean:write name="Oproep_TekstInvoerForm" property="tekst.id" />&id=<bean:write name="tekstblokkeni" property="id" />">
									<logic:notEqual name="tekstblokkeni" property="titel" value="">	
										<bean:write name="tekstblokkeni" property="titel" filter="true" />
									</logic:notEqual>
									<logic:equal name="tekstblokkeni" property="titel" value="">
										[zonder titel]
									</logic:equal>
								</a>
							</td>
							<td>
								<input type="tekst" name="toChange_<bean:write name="tekstblokkeni" property="id" />" value="<bean:write name="tekstblokkeni" property="volgnummer" />" size="2" />
							</td>
						</tr>
					</logic:iterate>							
					<bean:size id="tekstblokkensize" name="tekstblokkenbean" />
					<logic:greaterThan name="tekstblokkensize" value="1">							
						<tr>
							<td>&nbsp;</td>
							<td><html:image property="Herschikken" value="Herschikken" page="/images/button_herschikken.gif" onclick="return fnOnSubmit()" /></td>
							<td>&nbsp;</td>
						</tr>
					</logic:greaterThan>
				</table>
	
</html:form>
</body>
</html:html>