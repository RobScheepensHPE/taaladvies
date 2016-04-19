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
			strUIConfig4 = '<bean:write name="eoprouiconfig4" filter="false"/>';
			
			var appletLoaded = false
			
			function BijzonderheidLoaded()
			{
				appletLoaded = true;
			}		
					
			function fnOnLoad() {
				bijzonderheidEop.setHTMLData(document.BijzonderheidInvoerForm.elements["taalvraag.bijzonderheidHTML"].value);
				bijzonderheidEop.pumpEvents();
			}
			function fnOnSubmit() {
				if (appletLoaded) {
					document.BijzonderheidInvoerForm.elements["taalvraag.bijzonderheidHTML"].value = bijzonderheidEop.getHTMLData();
					document.BijzonderheidInvoerForm.elements["taalvraag.bijzonderheid"].value = bijzonderheidEop.getPlainText();	
				}
			}
	
			function doSubmit(doAction){
				fnOnSubmit();
	
				document.forms['BijzonderheidInvoerForm'].action = doAction;
				document.forms['BijzonderheidInvoerForm'].submit();
			}
				
		</script>
	</logic:equal>
	
	<logic:notEqual name="eopro" value="true">
		<script language="JavaScript">
			function fnOnSubmit() {
					document.BijzonderheidInvoerForm.elements["taalvraag.bijzonderheidHTML"].value = document.BijzonderheidInvoerForm.elements["taalvraag.bijzonderheid"].value;
			}
	
			function doSubmit(doAction){
				fnOnSubmit();
	
				document.forms['BijzonderheidInvoerForm'].action = doAction;
				document.forms['BijzonderheidInvoerForm'].submit();
			}
				
		</script>	
	</logic:notEqual>
	
	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>		
</head>

<body>

<h2>INVOEREN Bijzonderheid Taalvraag</h2>

<html:errors />

<html:form method="post" action="/bijzonderheidInvoer">
	<html:hidden name="BijzonderheidInvoerForm" property="taalvraag.bijzonderheidHTML" />
	<logic:equal name="eopro" value="true">
		<html:hidden name="BijzonderheidInvoerForm" property="taalvraag.bijzonderheid" />		
	</logic:equal>
				<table cellspacing="5">
					<tr>
						<td colspan="2">
							<a class="bigger" href="javascript:doSubmit('../do/bijzonderheidInvoer?Button=Opslaan')">
								<logic:notEqual name="BijzonderheidInvoerForm" property="taalvraag.titel" value="">
									<logic:notEqual name="BijzonderheidInvoerForm" property="taalvraag.titel" value="<p></p>">
										<bean:write name="BijzonderheidInvoerForm" property="taalvraag.titel" filter="true" />
									</logic:notEqual>
								</logic:notEqual>
								<logic:equal name="BijzonderheidInvoerForm" property="taalvraag.titel" value="">
									<bean:write name="BijzonderheidInvoerForm" property="taalvraag.vraag" filter="true" />
								</logic:equal>
								<logic:equal name="BijzonderheidInvoerForm" property="taalvraag.titel" value="<p></p>">
									<bean:write name="BijzonderheidInvoerForm" property="taalvraag.vraag" filter="true" />
								</logic:equal>				
							</a>					
						</td>
					</tr>
					<tr>	
						<td><html:img page="/images/label_bijzonderheid.gif" /></td>
						<td>
							<logic:equal name="eopro" value="true">
								<script type="text/javascript" language="javascript">
									bijzonderheidEop = new editOnPro(700, 150, "bijzonderheid", "editor", "bijzonderheidEop");
									bijzonderheidEop.setCodebase("/eopro");
									bijzonderheidEop.setUIConfig(strUIConfig4);
									bijzonderheidEop.setConfig(strConfig);
									bijzonderheidEop.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
									bijzonderheidEop.setStartUpScreenBackgroundColor("#EBF0FF");
									bijzonderheidEop.setStartUpScreenTextColor("navy");
									bijzonderheidEop.setLocaleCode("en_US");
									bijzonderheidEop.setParam("bodyonly", "true");
									bijzonderheidEop.setParam("oldfontstylemode","true");
									bijzonderheidEop.setParam("sourceview","true");
									bijzonderheidEop.setParam("cache_archive", "edit-on-pro-4.jar");
									bijzonderheidEop.setParam("cache_option","plugin");
									bijzonderheidEop.setOnEditorLoaded("fnOnLoad");
									bijzonderheidEop.setOnDataLoaded("BijzonderheidLoaded");						
									bijzonderheidEop.loadEditor();
								</script> 	
							</logic:equal>
							
							<logic:notEqual name="eopro" value="true">
								<html:textarea name="BijzonderheidInvoerForm" property="taalvraag.bijzonderheid" rows="7"  cols="80"/>		
							</logic:notEqual>
							
<!--
							<applet code="com.realobjects.eop.applet.EditorApplet" archive="edit-on-pro-signed.jar,tidy.jar,ssce.jar" 
							codebase="/eopro/" height="150" width="700" name="bijzonderheid" id="editor" MAYSCRIPT>							

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
								<PARAM name="cache_version" value="3.1.200.0,0.0.0.0,0.0.0.0" />								
								<PARAM name="cache_option" value="plugin" />
								<PARAM name="cache_archive" value="edit-on-pro-signed.jar,ssce.jar,tidy.jar" />
					            <PARAM name="ONEDITORLOADED" value="fnOnLoad"/>
								<PARAM name="ONDATALOADED" value="BijzonderheidLoaded" />							
							</applet>			
-->
						</td>
					</tr>
				</table>

</html:form>
</body>
</html:html>