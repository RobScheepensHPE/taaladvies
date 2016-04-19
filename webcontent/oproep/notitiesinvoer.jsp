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
					strConfig = '<%= application.getAttribute("eoproconfig")%>';
			strUIConfig4 = '<%= application.getAttribute("eoprouiconfig4")%>';
			
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
	
			function doSubmit(doAction){
				fnOnSubmit();
				document.forms['NotitiesInvoerForm'].action = doAction;
				document.forms['NotitiesInvoerForm'].submit();
			}
						
		</script>
	</logic:equal>
	
	<logic:notEqual name="eopro" value="true">	
		<script language="JavaScript">
			function fnOnSubmit() {
					document.NotitiesInvoerForm.elements["notitie.notitieHTML"].value = document.NotitiesInvoerForm.elements["notitie.notitie"].value;
			}
	
			function doSubmit(doAction){
				fnOnSubmit();
				document.forms['NotitiesInvoerForm'].action = doAction;
				document.forms['NotitiesInvoerForm'].submit();
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

<h2>INVOEREN Notitie</h2>

<html:errors />

<html:form method="post" action="/notitiesInvoer">
	<html:hidden name="NotitiesInvoerForm" property="notitie.notitieHTML" />
	<logic:equal name="eopro" value="true">
		<html:hidden name="NotitiesInvoerForm" property="notitie.notitie" />	
	</logic:equal>
				<table cellspacing="5">
					<tr>
						<td>								
							<logic:present name="NotitiesInvoerForm" property="taalvraag">
									<a class="bigger" href="javascript:doSubmit('../do/notitiesInvoer?Button=Toevoegen&empty=true')">
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
									<html:hidden name="NotitiesInvoerForm" property="taalvraag.id" />									
							</logic:present>
							<logic:present name="NotitiesInvoerForm" property="tekst">
								<a class="bigger" href="javascript:doSubmit('../do/notitiesInvoer?Button=Toevoegen&empty=true')">
									<bean:write name="NotitiesInvoerForm" property="tekst.titel" filter="true" />
								</a>
								<html:hidden name="NotitiesInvoerForm" property="tekst.id" />
							</logic:present>						
						</td>
					</tr>						
					<tr>
						<td><b>
							<bean:write name="gebruiker" property="voornaam" /> <bean:write name="gebruiker" property="naam" />: 
							<input type="hidden" name="notitie.gebruikerId" value="<bean:write name="gebruiker" property="id" />" />
							<html:hidden name="NotitiesInvoerForm" property="notitie.datumString" />
							<html:hidden name="NotitiesInvoerForm" property="notitie.id" />
							<bean:write name="NotitiesInvoerForm" property="notitie.datumString" />
						</b></td>
					</tr>
					<tr>
						<td>
							<logic:equal name="eopro" value="true">
								<script type="text/javascript" language="javascript">
									notitieEop = new editOnPro(700, 150, "x", "editor", "notitieEop");
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
									notitieEop.setParam("cache_option","plugin");
									notitieEop.setOnEditorLoaded("fnOnLoad");
									notitieEop.setOnDataLoaded("NotitieLoaded");						
									notitieEop.loadEditor();
								</script> 	
							</logic:equal>
							
							<logic:notEqual name="eopro" value="true">
								<html:textarea name="NotitiesInvoerForm" property="notitie.notitie" rows="7"  cols="80"/>	
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
				</table>
</html:form>
</body>
</html:html>