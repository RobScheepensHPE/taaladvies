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
			strUIConfig5 = '<bean:write name="eoprouiconfig5" filter="false"/>';
			
			var appletLoaded = false
			
			function CitaatLoaded()
			{
				appletLoaded = true;
			}				
			
			function fnOnLoad() {
				citaatEop.setHTMLData(document.NaslagreferentiesInvoerForm.elements["naslagreferentie.citaatHTML"].value);
				citaatEop.pumpEvents();
			}
			function fnOnSubmit() {
				if (appletLoaded) {
					document.NaslagreferentiesInvoerForm.elements["naslagreferentie.citaatHTML"].value = citaatEop.getHTMLData();
					document.NaslagreferentiesInvoerForm.elements["naslagreferentie.citaat"].value = citaatEop.getPlainText();
				}
			}
			
			function doSubmit(doAction){
				fnOnSubmit();
				document.forms['NaslagreferentiesInvoerForm'].action = doAction;
				document.forms['NaslagreferentiesInvoerForm'].submit();
			}
				
		</script>
	</logic:equal>
	
	<logic:notEqual name="eopro" value="true">
		<script language="JavaScript">
			function fnOnSubmit() {
				document.NaslagreferentiesInvoerForm.elements["naslagreferentie.citaatHTML"].value = document.NaslagreferentiesInvoerForm.elements["naslagreferentie.citaat"].value;
			}
			
			function doSubmit(doAction){
				fnOnSubmit();
				document.forms['NaslagreferentiesInvoerForm'].action = doAction;
				document.forms['NaslagreferentiesInvoerForm'].submit();
			}				
		</script>
	</logic:notEqual>
	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>		
</head>

<body>

<bean:define name="NaslagreferentiesInvoerForm" property="naslagreferenties" type="java.util.ArrayList" id="naslagreferentiesbean" />
<bean:define name="NaslagreferentiesInvoerForm" property="naslagwerken" type="java.util.ArrayList" id="naslagwerken" />
<bean:define name="NaslagreferentiesInvoerForm" property="bibliografien" type="java.util.ArrayList" id="bibliografien" />

<h2>
	<logic:notEqual name="NaslagreferentiesInvoerForm" property="naslagreferentie.id" value="0">
		AANPASSEN 
	</logic:notEqual>
	<logic:equal name="NaslagreferentiesInvoerForm" property="naslagreferentie.id" value="0">
		INVOEREN 
	</logic:equal>
	Naslagwerk
</h2>

<html:errors />

<html:form method="post" action="/naslagreferentiesInvoer">
	<html:hidden name="NaslagreferentiesInvoerForm" property="naslagreferentie.citaatHTML" />
	<logic:equal name="eopro" value="true">
		<html:hidden name="NaslagreferentiesInvoerForm" property="naslagreferentie.citaat" />
	</logic:equal>
				<table cellspacing="5" width="100%">
					<tr>
						<td colspan="5">
							<logic:present name="NaslagreferentiesInvoerForm" property="taalvraag">
									<a class="bigger" href="../do/initTaalvragenInvoer?id=<bean:write name="NaslagreferentiesInvoerForm" property="taalvraag.id" />">
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
								<a class="bigger" href="../do/initOproep_TekstInvoer?id=<bean:write name="NaslagreferentiesInvoerForm" property="tekst.oproepId" />">
									<bean:write name="NaslagreferentiesInvoerForm" property="tekst.titel" filter="true" />
								</a>
								<html:hidden name="NaslagreferentiesInvoerForm" property="tekst.id" />
							</logic:present>						
						</td>
					</tr>
					<tr bgcolor="#dddddd">
						<th align="left">&nbsp;</th>
						<th align="left">Verkorte titel</th>
						<th align="left">Pagina</th>
						<th align="left">Variant</th>
						<th align="left">Informatie</th>
					</tr>		
					<logic:iterate id="naslagreferenties" name="naslagreferentiesbean">
						<tr>
							<td valign="top">
								<a href="../do/naslagreferentiesInvoer?id=<bean:write name="naslagreferenties" property="id" />&Button=Verwijder" border="0"><img src="<html:rewrite page='/images/Delete24.gif' />" border="0" /></a>
							</td>							
							<td valign="top">
								<a href="../do/naslagreferentiesInvoer?id=<bean:write name="naslagreferenties" property="id" />&Button=Wijzig">
									<bean:write name="naslagreferenties" property="naslagwerk.omschrijving" filter="true" />
								</a>
							</td>
							<td valign="top"><bean:write name="naslagreferenties" property="paginas" filter="true" /></td>
							<td valign="top"><bean:write name="naslagreferenties" property="variant" filter="true" /></td>
							<td valign="top"><bean:write name="naslagreferenties" property="citaatHTML" filter="false" /></td>
						</tr>
					</logic:iterate>		
				</table>
<hr />	
				<table cellspacing="5">
					
					<tr>
						<td><html:img page="/images/label_categorie.gif" /><html:hidden name="NaslagreferentiesInvoerForm" property="naslagreferentie.id" /></td>
						<td>
							<html:select name="NaslagreferentiesInvoerForm" property="naslagreferentie.naslagwerk.parentId" onchange="doSubmit('../do/naslagreferentiesInvoer?Button=Change1')">
								<option value="0">Selecteer...</option>
								<html:options collection="bibliografien" property="id" labelProperty="omschrijving" />
							</html:select>
						</td>	
						<td><html:img page="/images/label_naslagwerk.gif" /></td>
						<td>
							<html:select name="NaslagreferentiesInvoerForm" property="naslagreferentie.naslagwerkId" onchange="doSubmit('../do/naslagreferentiesInvoer?Button=Change2')">
								<option value="0">Selecteer...</option>
								<html:options collection="naslagwerken" property="id" labelProperty="omschrijving" />
							</html:select>
						</td>
					<tr>
						<td><html:img page="/images/label_volledige_titel.gif" /></td>
						<td colspan="3"><bean:write name="NaslagreferentiesInvoerForm" property="naslagreferentie.naslagwerk.titelVoluitHTML" filter="false" /></td>
					</tr>
					<tr>
						<td><html:img page="/images/label_pagina.gif" /></td>
						<td><html:text name="NaslagreferentiesInvoerForm" property="naslagreferentie.paginas" /></td>
						<td><html:img page="/images/label_variant.gif" /></td>
						<td><html:text name="NaslagreferentiesInvoerForm" property="naslagreferentie.variant" /></td>
					</tr>
					<tr>
						<td><html:img page="/images/label_informatie.gif" /></td>
						<td colspan="3">
							<logic:equal name="eopro" value="true">
								<script type="text/javascript" language="javascript">	
									citaatEop = new editOnPro(700, 450, "citaat", "editor", "citaatEop");
									citaatEop.setCodebase("/eopro");
									citaatEop.setUIConfig(strUIConfig5);
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
									citaatEop.setOnEditorLoaded("fnOnLoad");
									citaatEop.setOnDataLoaded("CitaatLoaded");				
									citaatEop.loadEditor();
								</script>
							</logic:equal>
							
							<logic:notEqual name="eopro" value="true">
								<html:textarea name="NaslagreferentiesInvoerForm" property="naslagreferentie.citaat" style="width: 100%;" rows="20"/>
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
								<PARAM name="TOOLBARURL" value="toolbar-taaladvies5.xml" />
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
								<PARAM name="ONDATALOADED" value="CitaatLoaded" />
							</applet>		
-->
						</td>	
					</tr>
					<tr>
						<td colspan="4" align="center">
							<logic:notEqual name="NaslagreferentiesInvoerForm" property="naslagreferentie.id" value="0">
								<html:image property="Wijzigen" value="Wijzigen" page="/images/button_wijzigen.gif" onclick="return fnOnSubmit()" />
								<html:image property="Overzicht Update" value="Overzicht Update" page="/images/button_wijzigen_overzicht.gif" onclick="return fnOnSubmit()" />
							</logic:notEqual>	
							<logic:equal name="NaslagreferentiesInvoerForm" property="naslagreferentie.id" value="0">
								<html:image property="Toevoegen" value="Toevoegen" page="/images/button_toevoegen.gif" onclick="return fnOnSubmit()" />
								<html:image property="Overzicht Insert" value="Overzicht Insert" page="/images/button_toevoegen_overzicht.gif" onclick="return fnOnSubmit()" />
							</logic:equal>
						</td>
					</tr>
				</table>

</html:form>
</body>
</html:html>