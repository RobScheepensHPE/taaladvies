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
			strUIConfig6 = '<bean:write name="eoprouiconfig6" filter="false"/>';
			
			var appletLoaded = new Array(2);
			
			for(var i=0;i<appletLoaded.length;i++)
				appletLoaded[i] = false;
	
			function TitelLoaded()
			{
				appletLoaded[0] = true;
			}
			
			function TekstblokLoaded()
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
				titelEop.setHTMLData(document.TekstblokkenInvoerForm.elements["tekstblok.titelHTML"].value);
				titelEop.pumpEvents();
			}
			function fnOnLoad2() {
				tekstblokEop.setHTMLData(document.TekstblokkenInvoerForm.elements["tekstblok.tekstblokHTML"].value);
				tekstblokEop.pumpEvents();
			}		
			
			function fnOnSubmit() {
				if(AppletsLoaded()) {
					document.TekstblokkenInvoerForm.elements["tekstblok.tekstblokHTML"].value = tekstblokEop.getHTMLData();
					document.TekstblokkenInvoerForm.elements["tekstblok.tekstblok"].value = tekstblokEop.getPlainText();
					document.TekstblokkenInvoerForm.elements["tekstblok.titelHTML"].value = titelEop.getHTMLData();
					document.TekstblokkenInvoerForm.elements["tekstblok.titel"].value = titelEop.getPlainText();
				}
			}
			
			function doSubmit(doAction){
				fnOnSubmit();
	
				document.forms['TekstblokkenInvoerForm'].action = doAction;
				document.forms['TekstblokkenInvoerForm'].submit();
			}		
		</script>
	</logic:equal>
	
	
	<logic:notEqual name="eopro" value="true">
		<script language="JavaScript">

			function fnOnSubmit() {
					document.TekstblokkenInvoerForm.elements["tekstblok.tekstblokHTML"].value = document.TekstblokkenInvoerForm.elements["tekstblok.tekstblok"].value;
					document.TekstblokkenInvoerForm.elements["tekstblok.titelHTML"].value = document.TekstblokkenInvoerForm.elements["tekstblok.titel"].value;
			}
			
			function doSubmit(doAction){
				fnOnSubmit();	
				document.forms['TekstblokkenInvoerForm'].action = doAction;
				document.forms['TekstblokkenInvoerForm'].submit();
			}		
		</script>
	</logic:notEqual>
	
	
	
	
	<%	
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>
</head>
<body> 

<h2>
	<logic:notEqual name="TekstblokkenInvoerForm" property="tekstblok.id" value="0">
		AANPASSEN 
	</logic:notEqual>
	<logic:equal name="TekstblokkenInvoerForm" property="tekstblok.id" value="0">
		INVOEREN 
	</logic:equal>
	Tekstblokken
</h2>


<html:form method="post" action="/tekstblokkenInvoer" onsubmit="return fnOnSubmit()">
	<html:hidden name="TekstblokkenInvoerForm" property="tekstblok.tekstblokHTML" />
	<html:hidden name="TekstblokkenInvoerForm" property="tekstblok.titelHTML" />
	<logic:equal name="eopro" value="true">
		<html:hidden name="TekstblokkenInvoerForm" property="tekstblok.tekstblok" />
		<html:hidden name="TekstblokkenInvoerForm" property="tekstblok.titel" />
	</logic:equal>
				<table cellspacing="5">
					<tr>
						<td colspan="4">
							<logic:notEqual name="TekstblokkenInvoerForm" property="tekstblok.id" value="0">
								<a class="bigger" href="javascript:doSubmit('../do/tekstblokkenInvoer?Button=Wijzigen')" border="0">
									<bean:write name="TekstblokkenInvoerForm" property="tekst.titel" filter="true" />
								</a>
							</logic:notEqual>
							<logic:equal name="TekstblokkenInvoerForm" property="tekstblok.id" value="0">
								<a class="bigger" href="javascript:doSubmit('../do/tekstblokkenInvoer?Button=Toevoegen')" border="0">
									<bean:write name="TekstblokkenInvoerForm" property="tekst.titel" filter="true" />
								</a>
							</logic:equal>
							<html:hidden name="TekstblokkenInvoerForm" property="tekst.oproep.id" />
						</td>													
					</tr>
					<tr><td colspan="4"><hr /></td></tr>
					<tr>
						<td colspan="4" align="center">
							<logic:notEqual name="TekstblokkenInvoerForm" property="tekstblok.id" value="0">
								<html:image property="Nieuw Tekstblok Update" value="Nieuw Tekstblok Update" page="/images/button_nieuw_tekstblok.gif" onclick="return fnOnSubmit()" />
							</logic:notEqual>
							<logic:equal name="TekstblokkenInvoerForm" property="tekstblok.id" value="0">
								<html:image property="Nieuw Tekstblok Save" value="Nieuw Tekstblok Save" page="/images/button_nieuw_tekstblok.gif" onclick="return fnOnSubmit()" />
								<html:image property="Annuleren" value="Annuleren" page="/images/button_tekstblok_annuleren.gif" onclick="return fnOnSubmit()" />
							</logic:equal>			
						</td>
					</tr>					
					<tr>	
						<td><html:img page="/images/label_volgnummer.gif" /></td>
						<td colspan="3"><bean:write name="TekstblokkenInvoerForm" property="tekstblok.volgnummer" /><html:hidden name="TekstblokkenInvoerForm" property="tekstblok.volgnummer" /></td>
					</tr>
					<tr>	
						<td><html:img page="/images/label_bloktitel.gif" /></td>
						<td colspan="3">		
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
									titelEop.setOnEditorLoaded("fnOnLoad1");
									titelEop.setOnDataLoaded("TitelLoaded");				
									titelEop.loadEditor();
								</script> 	
							</logic:equal>
							
							<logic:notEqual name="eopro" value="true">
								<html:text name="TekstblokkenInvoerForm" property="tekstblok.titel" style="width: 99%;" />
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
					            <PARAM name="ONEDITORLOADED" value="fnOnLoad1"/>
								<PARAM name="ONDATALOADED" value="TitelLoaded" />							
							</applet>
-->
						</td>
					</tr>
					<tr><td><html:img page="/images/label_tekstblok.gif" /><html:hidden name="TekstblokkenInvoerForm" property="tekstblok.id" /><html:hidden name="TekstblokkenInvoerForm" property="tekstblok.tekstId" /></td>
						<td colspan="3">		
							<logic:equal name="eopro" value="true">
								<script type="text/javascript" language="javascript">	
									tekstblokEop = new editOnPro(700, 500, "x", "editor", "tekstblokEop");
									tekstblokEop.setCodebase("/eopro");
									tekstblokEop.setUIConfig(strUIConfig6);
									tekstblokEop.setConfig(strConfig);
									tekstblokEop.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
									tekstblokEop.setStartUpScreenBackgroundColor("#EBF0FF");
									tekstblokEop.setStartUpScreenTextColor("navy");
									tekstblokEop.setLocaleCode("en_US");
									tekstblokEop.setParam("bodyonly", "true");
									tekstblokEop.setParam("oldfontstylemode","true");
									tekstblokEop.setParam("sourceview","true");
									tekstblokEop.setParam("cache_archive", "edit-on-pro-4.jar");
									tekstblokEop.setParam("cache_option","plugin");
									tekstblokEop.setOnEditorLoaded("fnOnLoad2");
									tekstblokEop.setOnDataLoaded("TekstblokLoaded");				
									tekstblokEop.loadEditor();
								</script> 	
							</logic:equal>
							
							<logic:notEqual name="eopro" value="true">
								<html:textarea name="TekstblokkenInvoerForm" property="tekstblok.tekstblok" rows="20" cols="80"  />
							</logic:notEqual>
<!--
							<applet code="com.realobjects.eop.applet.EditorApplet" archive="edit-on-pro-signed.jar,tidy.jar,ssce.jar" 
							codebase="/eopro/" height="500" width="700" name="tekstblok" id="editor" MAYSCRIPT>
		
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
								<PARAM name="TOOLBARURL" value="toolbar-taaladvies6.xml" />
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
								<PARAM name="ONDATALOADED" value="TekstblokLoaded" />
							</applet>
-->
						</td>
					</tr>
				</table>
</html:form>
</body>
</html:html>