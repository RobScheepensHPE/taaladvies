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
			strUIConfig = '<bean:write name="eoprouiconfig" filter="false"/>';
			strUIConfig7 = '<bean:write name="eoprouiconfig7" filter="false"/>';
			
			var appletLoaded = new Array(2);
			
			for(var i=0;i<appletLoaded.length;i++)
				appletLoaded[i] = false;
	
			function TitelLoaded()
			{
				appletLoaded[0] = true;
			}
			
			function CitaatLoaded()
			{
				appletLoaded[1] = true;
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
			
			function fnOnLoad1() {
				titelEop.setHTMLData(document.BronnenInvoerForm.elements["bron.titelHTML"].value);
				titelEop.pumpEvents();
			}
			function fnOnLoad2() {
				citaatEop.setHTMLData(document.BronnenInvoerForm.elements["bron.citaatHTML"].value);
				citaatEop.pumpEvents();
			}		
			function fnOnSubmit() {
				if(AppletsLoaded()) {
					document.BronnenInvoerForm.elements["bron.citaatHTML"].value = citaatEop.getHTMLData();
					document.BronnenInvoerForm.elements["bron.titelHTML"].value = titelEop.getHTMLData();
					document.BronnenInvoerForm.elements["bron.citaat"].value = citaatEop.getPlainText();
					document.BronnenInvoerForm.elements["bron.titel"].value = titelEop.getPlainText();
				}
			}
			
		</script>
	</logic:equal>
		
	<logic:notEqual name="eopro" value="true">
		<script language="JavaScript">
			function fnOnSubmit() {
				document.BronnenInvoerForm.elements["bron.citaatHTML"].value = document.BronnenInvoerForm.elements["bron.citaat"].value;
				document.BronnenInvoerForm.elements["bron.titelHTML"].value = document.BronnenInvoerForm.elements["bron.titel"].value;
			}		
		</script>
	</logic:notEqual>
		
	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>		
</head>

<body>

<bean:define name="BronnenInvoerForm" property="bronnen" type="java.util.ArrayList" id="bronnenbean" />

<h2>
	<logic:notEqual name="BronnenInvoerForm" property="bron.id" value="0">
		AANPASSEN 
	</logic:notEqual>
	<logic:equal name="BronnenInvoerForm" property="bron.id" value="0">
		INVOEREN 
	</logic:equal>
	Bron
</h2>

<html:errors />

<html:form method="post" action="/bronnenInvoer">
	<html:hidden name="BronnenInvoerForm" property="bron.titelHTML" />
	<html:hidden name="BronnenInvoerForm" property="bron.citaatHTML" />
	<logic:equal name="eopro" value="true">
		<html:hidden name="BronnenInvoerForm" property="bron.titel" />
		<html:hidden name="BronnenInvoerForm" property="bron.citaat" />
	</logic:equal>

				<table cellspacing="5" width="100%">
					<tr>
						<td colspan="5">
							<logic:present name="BronnenInvoerForm" property="taalvraag">
									<a class="bigger" href="../do/initTaalvragenInvoer?id=<bean:write name="BronnenInvoerForm" property="taalvraag.id" />">
										<logic:notEqual name="BronnenInvoerForm" property="taalvraag.titel" value="">
											<logic:notEqual name="BronnenInvoerForm" property="taalvraag.titel" value="<p></p>">
												<bean:write name="BronnenInvoerForm" property="taalvraag.titel" filter="true" />
											</logic:notEqual>
										</logic:notEqual>
										<logic:equal name="BronnenInvoerForm" property="taalvraag.titel" value="">
											<bean:write name="BronnenInvoerForm" property="taalvraag.vraag" filter="true" />
										</logic:equal>
										<logic:equal name="BronnenInvoerForm" property="taalvraag.titel" value="<p></p>">
											<bean:write name="BronnenInvoerForm" property="taalvraag.vraag" filter="true" />
										</logic:equal>				
									</a>
							</logic:present>
							<logic:present name="BronnenInvoerForm" property="tekst">
								<a class="bigger" href="../do/initOproep_TekstInvoer?id=<bean:write name="BronnenInvoerForm" property="tekst.oproepId" />">
									<bean:write name="BronnenInvoerForm" property="tekst.titel" filter="true" />
								</a>
								<html:hidden name="BronnenInvoerForm" property="tekst.id" />
							</logic:present>						
						</td>
					</tr>
					<tr bgcolor="#dddddd">
						<th align="left">&nbsp;</th>
						<th align="left">Titel</th>
						<th align="left">Pagina</th>
						<th align="left">Variant</th>
						<th align="left">Informatie</th>
					</tr>		
					<logic:iterate id="bronnen" name="bronnenbean">
						<tr>
							<td valign="top">
								<a href="../do/bronnenInvoer?id=<bean:write name="bronnen" property="id" />&Button=Verwijder" border="0">
									<img src="<html:rewrite page='/images/Delete24.gif' />" border="0"  alt="Delete"/>	
								</a>
							</td>
							<td valign="top">
								<a href="../do/bronnenInvoer?id=<bean:write name="bronnen" property="id" />&Button=Wijzig">
									<bean:write name="bronnen" property="titelHTML" filter="false" />
								</a>	
							</td>
							<td valign="top"><bean:write name="bronnen" property="paginas" filter="true" /></td>
							<td valign="top"><bean:write name="bronnen" property="variant" filter="true" /></td>
							<td valign="top"><bean:write name="bronnen" property="citaatHTML" filter="false" /></td>
						</tr>
					</logic:iterate>		
				</table>
<hr />	
				<table cellspacing="5">
					<tr>
						<td><html:img page="/images/label_titel.gif" /><html:hidden name="BronnenInvoerForm" property="bron.id" /></td>
						<td colspan="3">
						<logic:equal name="eopro" value="true">		
							<script type="text/javascript" language="javascript">	
								titelEop = new editOnPro(700, 150, "titel", "editor", "titelEop");
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
							<html:textarea name="BronnenInvoerForm" property="bron.titel" cols="80"  rows="7"/>
						</logic:notEqual>
<!--
							<applet code="com.realobjects.eop.applet.EditorApplet" archive="edit-on-pro-signed.jar,tidy.jar,ssce.jar" 
							codebase="/eopro/" height="150" width="700" name="titel" id="editor" MAYSCRIPT>
		
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
					<tr>
						<td><html:img page="/images/label_pagina.gif" /></td>
						<td><html:text name="BronnenInvoerForm" property="bron.paginas" /></td>
						<td><html:img page="/images/label_variant.gif" /></td>
						<td><html:text name="BronnenInvoerForm" property="bron.variant" /></td>
					</tr>
					<tr>
						<td><html:img page="/images/label_informatie.gif" /></td>
						<td colspan="3">
							<logic:equal name="eopro" value="true">
								<script type="text/javascript" language="javascript">	
									citaatEop = new editOnPro(700, 450, "citaat", "editor", "citaatEop");
									citaatEop.setCodebase("/eopro");
									citaatEop.setUIConfig(strUIConfig7);
									citaatEop.setConfig(strConfig);
									citaatEop.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
									citaatEop.setStartUpScreenBackgroundColor("#EBF0FF");
									citaatEop.setStartUpScreenTextColor("navy");
									citaatEop.setLocaleCode("en_US");
									citaatEop.setParam("bodyonly", "true");
									citaatEop.setParam("oldfontstylemode","true");
									citaatEop.setParam("sourceview","true");
									citaatEop.setParam("cache_archive", "edit-on-pro-4.jar");
									citaatEop.setParam("cache_option","plugin");
									citaatEop.setOnEditorLoaded("fnOnLoad2");
									citaatEop.setOnDataLoaded("CitaatLoaded");				
									citaatEop.loadEditor();
								</script> 							
							</logic:equal>
							<logic:notEqual name="eopro" value="true">
									<html:textarea name="BronnenInvoerForm" property="bron.citaat" cols="80" rows="20"/>
							</logic:notEqual>
<!--
							<applet code="com.realobjects.eop.applet.EditorApplet" archive="edit-on-pro-signed.jar,tidy.jar,ssce.jar" 
							codebase="/eopro/" height="150" width="700" name="citaat" id="editor" MAYSCRIPT>
		
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
								<PARAM name="TOOLBARURL" value="toolbar-taaladvies7.xml" />
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
					            <PARAM name="ONEDITORLOADED" value="fnOnLoad2" />
								<PARAM name="ONDATALOADED" value="CitaatLoaded" />
							</applet>				
-->
						</td>	
					</tr>
					<tr>
						<td colspan="4" align="center">
							<logic:notEqual name="BronnenInvoerForm" property="bron.id" value="0">
								<html:image property="Wijzigen" value="Wijzigen" page="/images/button_wijzigen.gif" onclick="return fnOnSubmit()" />
								<html:image property="Overzicht Update" value="Overzicht Update" page="/images/button_wijzigen_overzicht.gif" onclick="return fnOnSubmit()" />
							</logic:notEqual>	
							<logic:equal name="BronnenInvoerForm" property="bron.id" value="0">
								<html:image property="Toevoegen" value="Toevoegen" page="/images/button_toevoegen.gif" onclick="return fnOnSubmit()" />
								<html:image property="Overzicht Insert" value="Overzicht Insert" page="/images/button_toevoegen_overzicht.gif" onclick="return fnOnSubmit()" />
							</logic:equal>					
						</td>
					</tr>
				</table>

</html:form>
</body>
</html:html>