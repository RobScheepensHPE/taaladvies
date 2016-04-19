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
	
	<logic:equal name="eopro" value="true">
		<script type="text/javascript" language="javascript" src="/eopro/editonpro.js"></script>
		
		<script language="JavaScript">
			
			strConfig = '<bean:write name="eoproconfig" filter="false"/>';
			strUIConfig = '<bean:write name="eoprouiconfig" filter="false"/>';
			strUIConfig2 = '<bean:write name="eoprouiconfig2" filter="false"/>';
			
			var appletLoaded = new Array(2);
			
			for(var i=0;i<appletLoaded.length;i++)
				appletLoaded[i] = false;
	
			function HerformuleringLoaded()
			{
				appletLoaded[0] = true;
			}
			
			function InformatieLoaded()
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
				herformuleringEop.setHTMLData(document.KenmerkenInvoerForm.elements["taalvraag.herformuleringHTML"].value);
				herformuleringEop.pumpEvents();
			}
			function fnOnLoad2() {
				informatieEop.setHTMLData(document.KenmerkenInvoerForm.elements["taalvraag.informatieHTML"].value);
				informatieEop.pumpEvents();
			}
					
			function fnOnSubmit() {
				if(AppletsLoaded()) {
					document.KenmerkenInvoerForm.elements["taalvraag.informatieHTML"].value = informatieEop.getHTMLData();
					document.KenmerkenInvoerForm.elements["taalvraag.herformuleringHTML"].value = herformuleringEop.getHTMLData();
					document.KenmerkenInvoerForm.elements["taalvraag.informatie"].value = informatieEop.getPlainText();
					document.KenmerkenInvoerForm.elements["taalvraag.herformulering"].value = herformuleringEop.getPlainText();
				}
			}
			
			function doSubmit(doAction){
				fnOnSubmit();
	
				document.forms['KenmerkenInvoerForm'].action = doAction;
				document.forms['KenmerkenInvoerForm'].submit();
			}
				
		</script>
	</logic:equal>
	
	<logic:notEqual name="eopro" value="true">	
		<script language="JavaScript">
			
			function fnOnSubmit() {
				document.KenmerkenInvoerForm.elements["taalvraag.informatieHTML"].value = document.KenmerkenInvoerForm.elements["taalvraag.informatie"].value;
				document.KenmerkenInvoerForm.elements["taalvraag.herformuleringHTML"].value = document.KenmerkenInvoerForm.elements["taalvraag.herformulering"].value;
			}
			
			function doSubmit(doAction){
				fnOnSubmit();
	
				document.forms['KenmerkenInvoerForm'].action = doAction;
				document.forms['KenmerkenInvoerForm'].submit();
			}
				
		</script>
	
	</logic:notEqual>
	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>		
</head>

<bean:define name="KenmerkenInvoerForm" property="relevanties" type="java.util.ArrayList" id="relevanties" />

<body>

<h2>INVOEREN Kenmerken Taalvraag</h2>

<html:errors />

<html:form method="post" action="/kenmerkenInvoer">
	<html:hidden name="KenmerkenInvoerForm" property="taalvraag.informatieHTML" />
	<html:hidden name="KenmerkenInvoerForm" property="taalvraag.herformuleringHTML" />
	<logic:equal name="eopro" value="true">
		<html:hidden name="KenmerkenInvoerForm" property="taalvraag.informatie" />
		<html:hidden name="KenmerkenInvoerForm" property="taalvraag.herformulering" />
	</logic:equal>
				<table cellspacing="5">
					<tr>
						<td colspan="2">
							<a class="bigger" href="javascript:doSubmit('../do/kenmerkenInvoer?Button=Opslaan')">
								<logic:notEqual name="KenmerkenInvoerForm" property="taalvraag.titel" value="">
									<logic:notEqual name="KenmerkenInvoerForm" property="taalvraag.titel" value="<p></p>">
										<bean:write name="KenmerkenInvoerForm" property="taalvraag.titel" filter="true" />
									</logic:notEqual>
								</logic:notEqual>
								<logic:equal name="KenmerkenInvoerForm" property="taalvraag.titel" value="">
									<bean:write name="KenmerkenInvoerForm" property="taalvraag.vraag" filter="true" />
								</logic:equal>
								<logic:equal name="KenmerkenInvoerForm" property="taalvraag.titel" value="<p></p>">
									<bean:write name="KenmerkenInvoerForm" property="taalvraag.vraag" filter="true" />
								</logic:equal>				
							</a>					
						</td>
					</tr>
					<tr>
						<td><html:img page="/images/label_relevantie.gif" /></td>
						<td>
							<html:select name="KenmerkenInvoerForm" property="taalvraag.relevantieId">
								<option value="0">Selecteer...</option>
								<html:options collection="relevanties" property="id" labelProperty="omschrijving"/>
							</html:select>
						</td>
					</tr>					
					<tr>
						<td><html:img page="/images/label_herformulering.gif" /></td>
						<td>
							<logic:equal name="eopro" value="true">
								<script type="text/javascript" language="javascript">
									herformuleringEop = new editOnPro(700, 200, "herformulering", "editor", "herformuleringEop");
									herformuleringEop.setCodebase("/eopro");
									herformuleringEop.setUIConfig(strUIConfig);
									herformuleringEop.setConfig(strConfig);
									herformuleringEop.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
									herformuleringEop.setStartUpScreenBackgroundColor("#EBF0FF");
									herformuleringEop.setStartUpScreenTextColor("navy");
									herformuleringEop.setLocaleCode("en_US");
									herformuleringEop.setParam("bodyonly", "true");
									herformuleringEop.setParam("oldfontstylemode","true");
									herformuleringEop.setParam("sourceview","true");
									herformuleringEop.setParam("cache_archive", "edit-on-pro-4.jar");
									herformuleringEop.setParam("cache_option","plugin");
									herformuleringEop.setOnEditorLoaded("fnOnLoad1");
									herformuleringEop.setOnDataLoaded("HerformuleringLoaded");						
									herformuleringEop.loadEditor();
								</script> 	
							</logic:equal>
							
							<logic:notEqual name="eopro" value="true">
								<html:textarea name="KenmerkenInvoerForm" property="taalvraag.herformulering" rows="9"  cols="80"/>
							</logic:notEqual>
<!--
							<applet code="com.realobjects.eop.applet.EditorApplet" archive="edit-on-pro-signed.jar,tidy.jar,ssce.jar" 
							codebase="/eopro/" height="200" width="700" name="herformulering" id="editor" MAYSCRIPT>							
		
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
								<PARAM name="ONDATALOADED" value="HerformuleringLoaded" />							
							</applet>			
-->	
						</td>	
					</tr>
					<tr>
						<td><html:img page="/images/label_informatie.gif" /></td>
						<td>
							<logic:equal name="eopro" value="true">
								<script type="text/javascript" language="javascript">
									informatieEop = new editOnPro(700, 400, "informatie", "editor", "informatieEop");
									informatieEop.setCodebase("/eopro");
									informatieEop.setUIConfig(strUIConfig2);
									informatieEop.setConfig(strConfig);
									informatieEop.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
									informatieEop.setStartUpScreenBackgroundColor("#EBF0FF");
									informatieEop.setStartUpScreenTextColor("navy");
									informatieEop.setLocaleCode("en_US");
									informatieEop.setParam("bodyonly", "true");
									informatieEop.setParam("oldfontstylemode","true");
									informatieEop.setParam("sourceview","true");
									informatieEop.setParam("cache_archive", "edit-on-pro-4.jar");
									informatieEop.setParam("cache_option","plugin");
									informatieEop.setOnEditorLoaded("fnOnLoad2");
									informatieEop.setOnDataLoaded("InformatieLoaded");						
									informatieEop.loadEditor();
								</script> 	
							</logic:equal>
							
							<logic:notEqual name="eopro" value="true">
								<html:textarea name="KenmerkenInvoerForm" property="taalvraag.informatie" rows="18"  cols="80"/>
							</logic:notEqual>
<!--
							<applet code="com.realobjects.eop.applet.EditorApplet" archive="edit-on-pro-signed.jar,tidy.jar,ssce.jar" 
							codebase="/eopro/" height="400" width="700" name="informatie" id="editor" MAYSCRIPT>

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
								<PARAM name="TOOLBARURL" value="toolbar-taaladvies2.xml" />
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
								<PARAM name="ONDATALOADED" value="InformatieLoaded" />
							</applet>			
-->
						</td>
					</tr>
				</table>

</html:form>
</body>
</html:html>