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
	
	<script type="text/javascript" language="javascript" src="/eopro/editonpro.js"></script>
	
	<logic:equal name="eopro" value="true">
		<script language="JavaScript">
			strConfig = '<bean:write name="eoproconfig" filter="false"/>';
			strUIConfig4 = '<bean:write name="eoprouiconfig4" filter="false"/>';
	
			
			var appletLoaded = false
			
			function NotitieLoaded()
			{
				appletLoaded = true;
			}				
			
			function fnOnLoad() {
				notitieEop.setHTMLData(document.NotitiesInvoerForm.elements["notitie.notitieHTML"].value);
				notitieEop.pumpEvents();
			}
			function fnOnSubmit() {
				if (appletLoaded) {
					document.NotitiesInvoerForm.elements["notitie.notitieHTML"].value = notitieEop.getHTMLData();
					document.NotitiesInvoerForm.elements["notitie.notitie"].value = notitieEop.getPlainText();		
				}
			}
						
		</script>
	</logic:equal>
		
	<logic:notEqual name="eopro" value="true">
		<script language="JavaScript">
			function fnOnSubmit() {
				document.NotitiesInvoerForm.elements["notitie.notitieHTML"].value = document.NotitiesInvoerForm.elements["notitie.notitie"].value;
			}
		</script>
	</logic:notEqual>

	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>		
</head>

<body>

<bean:define name="Gebruiker" id="gebruiker" />
<bean:define name="NotitiesInvoerForm" property="notities" type="java.util.ArrayList" id="notitiesbean" />

<h2>
<logic:present name="NotitiesInvoerForm" property="taalvraag">
	<logic:equal name="NotitiesInvoerForm" property="taalvraag.oproep.afgehandeld" value="true">
		BEHANDELEN 
	</logic:equal>
</logic:present>
<logic:present name="NotitiesInvoerForm" property="tekst">
	<logic:equal name="NotitiesInvoerForm" property="tekst.oproep.afgehandeld" value="true">
		BEHANDELEN 
	</logic:equal>
</logic:present>

<logic:present name="NotitiesInvoerForm" property="taalvraag">
	<logic:notEqual name="NotitiesInvoerForm" property="taalvraag.oproep.afgehandeld" value="true">
		BEKIJKEN 
	</logic:notEqual>
</logic:present>
<logic:present name="NotitiesInvoerForm" property="tekst">
	<logic:notEqual name="NotitiesInvoerForm" property="tekst.oproep.afgehandeld" value="true">
		BEKIJKEN 
	</logic:notEqual>
</logic:present>
Notitie</h2>
<html:errors />
<html:form method="post" action="/notitiesInvoerReadOnly">
	<html:hidden name="NotitiesInvoerForm" property="notitie.notitieHTML" />
	<logic:equal name="eopro" value="true">
		<html:hidden name="NotitiesInvoerForm" property="notitie.notitie" />	
	</logic:equal>
				<table cellspacing="5" width="100%">
					<tr>
						<td>
							<logic:present name="NotitiesInvoerForm" property="taalvraag">
									<a class="bigger" href="../do/initTaalvragenInvoer?id=<bean:write name="NotitiesInvoerForm" property="taalvraag.id" />&ro=true<logic:equal name="NotitiesInvoerForm" property="adm" value="true">&adm=true</logic:equal>">
										<logic:notEqual name="NotitiesInvoerForm" property="taalvraag.titel" value="">
											<logic:notEqual name="NotitiesInvoerForm" property="taalvraag.titel" value="<p></p>">
												<bean:write name="NotitiesInvoerForm" property="taalvraag.titel" filter="true" />
											</logic:notEqual>
										</logic:notEqual>
										<logic:equal name="NotitiesInvoerForm" property="taalvraag.titel" value="">
											<bean:write name="NotitiesInvoerForm" property="taalvraag.vraag" filter="true" />
										</logic:equal>
										<logic:equal name="NotitiesInvoerForm" property="taalvraag.titel" value="<p></p>">
											<bean:write name="NotitiesInvoerForm" property="taalvraag.vraag" filter="true" />
										</logic:equal>				
									</a>
							</logic:present>
							<logic:present name="NotitiesInvoerForm" property="tekst">
								<a class="bigger" href="../do/initOproep_TekstInvoer?id=<bean:write name="NotitiesInvoerForm" property="tekst.oproepId" />&ro=true">
									<bean:write name="NotitiesInvoerForm" property="tekst.titel" filter="true" />
								</a>
								<html:hidden name="NotitiesInvoerForm" property="tekst.id" />
							</logic:present>						
						</td>
					</tr>									
					<logic:iterate id="notities" name="notitiesbean">
						<tr>
							<td><b><bean:write name="notities" property="gebruiker.voornaam" /> <bean:write name="notities" property="gebruiker.naam" filter="true" /> op <bean:write name="notities" property="datumString" />: </b></td>
						</tr>
						<tr>
							<td><bean:write name="notities" property="notitieHTML" filter="false" /></td>
						</tr>
					</logic:iterate>		
<logic:present name="NotitiesInvoerForm" property="taalvraag">
	<logic:equal name="NotitiesInvoerForm" property="taalvraag.oproep.afgehandeld" value="false">
						<tr>
							<td>
							<input type="hidden" name="ro" value="true" />
								<logic:present name="NotitiesInvoerForm" property="taalvraag">
									<html:image property="Annuleren" value="Annuleren" page="/images/button_taalvraag.gif" onclick="return fnOnSubmit()" />
								</logic:present>
								<logic:present name="NotitiesInvoerForm" property="tekst">
									<html:image property="Annuleren" value="Annuleren" page="/images/button_tekst.gif" onclick="return fnOnSubmit()" />
								</logic:present>						
							</td>
						</tr>
	</logic:equal>
</logic:present>
<logic:present name="NotitiesInvoerForm" property="tekst">
	<logic:equal name="NotitiesInvoerForm" property="tekst.oproep.afgehandeld" value="false">
						<tr>
							<td>
							<input type="hidden" name="ro" value="true" />
								<logic:present name="NotitiesInvoerForm" property="taalvraag">
									<html:image property="Annuleren" value="Annuleren" page="/images/button_taalvraag.gif" onclick="return fnOnSubmit()" />
								</logic:present>
								<logic:present name="NotitiesInvoerForm" property="tekst">
									<html:image property="Annuleren" value="Annuleren" page="/images/button_tekst.gif" onclick="return fnOnSubmit()" />
								</logic:present>						
							</td>
						</tr>
	</logic:equal>
</logic:present>
				</table>
<logic:present name="NotitiesInvoerForm" property="taalvraag">
	<logic:equal name="NotitiesInvoerForm" property="taalvraag.oproep.afgehandeld" value="true">
<hr />
				<table cellspacing="5">
					<tr>
						<td>&nbsp;</td>
						<td><b>
							<bean:write name="gebruiker" property="voornaam" /> <bean:write name="gebruiker" property="naam" />: 
							<input type="hidden" name="notitie.gebruikerId" value="<bean:write name="gebruiker" property="id" />" />
							<html:hidden name="NotitiesInvoerForm" property="notitie.datumString" />
							<bean:write name="NotitiesInvoerForm" property="notitie.datumString" />
							<input type="hidden" name="ro" value="true" />
						</b></td>
					</tr>
					<tr>
						<td colspan="2">
							<logic:equal name="eopro" value="true">
								<script type="text/javascript" language="javascript">
									notitieEop = new editOnPro(700, 150, "notitie", "editor", "notitieEop");
									notitieEop.setCodebase("/eopro");
									notitieEop.setUIConfig(strUIConfig4);
									notitieEop.setConfig(strConfig);
									notitieEop.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
									notitieEop.setStartUpScreenBackgroundColor("#EBF0FF");
									notitieEop.setStartUpScreenTextColor("navy");
									notitieEop.setLocaleCode("en_US");
									notitieEop.setParam("bodyonly", "true");
									notitieEop.setParam("oldfontstylemode","true");
									notitieEop.setParam("sourceview","true");
									notitieEop.setParam("cache_archive", "edit-on-pro-4.jar");
									notitieEop.setParam("cache_option","plugin");
									notitieEop.setOnEditorLoaded("fnOnLoad");
									notitieEop.setOnDataLoaded("NotitieLoaded");						
									notitieEop.loadEditor();
								</script> 	
							</logic:equal>
							
							<logic:notEqual name="eopro" value="true">
								<html:textarea name="NotitiesInvoerForm" property="notitie.notitie" rows="6" style="width: 100%;"/>	
							</logic:notEqual>
<!--
							<applet code="com.realobjects.eop.applet.EditorApplet" archive="edit-on-pro-signed.jar,tidy.jar,ssce.jar" 
							codebase="/eopro/" height="150" width="700" name="notitie" id="editor" MAYSCRIPT>

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
								<PARAM name="ONEDITORLOADED" value="fnOnLoad"/>
								<PARAM name="ONDATALOADED" value="NotitieLoaded" />
							</applet>
-->
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<html:image property="Toevoegen" value="Toevoegen" page="/images/button_toevoegen.gif" onclick="return fnOnSubmit()" />	
							<html:image property="Overzicht" value="Overzicht" page="/images/button_toevoegen_overzicht.gif" onclick="return fnOnSubmit()" />
						</td>
					</tr>
				</table>
	</logic:equal>
</logic:present>
<logic:present name="NotitiesInvoerForm" property="tekst">
	<logic:equal name="NotitiesInvoerForm" property="tekst.oproep.afgehandeld" value="true">
<hr />
				<table cellspacing="5">
					<tr>
						<td>&nbsp;</td>
						<td><b>
							<bean:write name="gebruiker" property="voornaam" /> <bean:write name="gebruiker" property="naam" />: 
							<input type="hidden" name="notitie.gebruikerId" value="<bean:write name="gebruiker" property="id" />" />
							<html:hidden name="NotitiesInvoerForm" property="notitie.datumString" />
							<bean:write name="NotitiesInvoerForm" property="notitie.datumString" />
							<input type="hidden" name="ro" value="true" />
						</b></td>
					</tr>
					<tr>
						<td colspan="2">
							<logic:equal name="eopro" value="true">
								<script type="text/javascript" language="javascript">
									notitieEop = new editOnPro(700, 150, "notitie", "editor", "notitieEop");
									notitieEop.setCodebase("/eopro");
									notitieEop.setUIConfig(strUIConfig4);
									notitieEop.setConfig(strConfig);
									notitieEop.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
									notitieEop.setStartUpScreenBackgroundColor("#EBF0FF");
									notitieEop.setStartUpScreenTextColor("navy");
									notitieEop.setLocaleCode("en_US");
									notitieEop.setParam("bodyonly", "true");
									notitieEop.setParam("oldfontstylemode","true");
									notitieEop.setParam("sourceview","true");
									notitieEop.setParam("cache_archive", "edit-on-pro-4.jar");
									notitieEop.setParam("cache_option","plugin");
									notitieEop.setOnEditorLoaded("fnOnLoad");
									notitieEop.setOnDataLoaded("NotitieLoaded");						
									notitieEop.loadEditor();
								</script> 	
							</logic:equal>
							
							<logic:notEqual name="eopro" value="true">
								<html:textarea name="NotitiesInvoerForm" property="notitie.notitie" rows="6"  cols="80"/>	
							</logic:notEqual>
						
<!--
							<applet code="com.realobjects.eop.applet.EditorApplet" archive="edit-on-pro-signed.jar,tidy.jar,ssce.jar" 
							codebase="/eopro/" height="150" width="700" name="notitie" id="editor" MAYSCRIPT>

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
								<PARAM name="ONEDITORLOADED" value="fnOnLoad"/>
								<PARAM name="ONDATALOADED" value="NotitieLoaded" />
							</applet>
-->
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<html:image property="Toevoegen" value="Toevoegen" page="/images/button_toevoegen.gif" onclick="return fnOnSubmit()" />	
							<html:image property="Overzicht" value="Overzicht" page="/images/button_toevoegen_overzicht.gif" onclick="return fnOnSubmit()" />
						</td>
					</tr>
				</table>
	</logic:equal>	
</logic:present>

	
</html:form>
</body>
</html:html>