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

<logic:equal name="eopro" value="true">
	<script type="text/javascript" language="javascript" src="/eopro/editonpro.js"></script>
	<script language="JavaScript">
			
			strConfig = '<bean:write name="eoproconfig" filter="false"/>';
			strUIConfig = '<bean:write name="eoprouiconfig" filter="false"/>';
			
			var appletLoaded = new Array(3);
			
			for(var i=0;i<appletLoaded.length;i++)
				appletLoaded[i] = false;
	
			function InhoudLoaded()
			{
				appletLoaded[0] = true;
			}
			
			function SlotgroetLoaded()
			{
				appletLoaded[1] = true;
			}
	
			function HandtekeningLoaded()
			{
				appletLoaded[2] = true;
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
			inhoudEop.setHTMLData(document.SjablonenBeheerForm.elements["sjabloon.inhoudHTML"].value);
			inhoudEop.pumpEvents();
		}	
		function fnOnLoad2() {
			slotgroetEop.setHTMLData(document.SjablonenBeheerForm.elements["sjabloon.slotgroetHTML"].value);
			slotgroetEop.pumpEvents();
	
		}
		function fnOnLoad3() {
			handtekeningEop.setHTMLData(document.SjablonenBeheerForm.elements["sjabloon.handtekeningHTML"].value);
			handtekeningEop.pumpEvents();
	
		}
			
		function doSubmit(doAction){
			fnOnSubmit();

			document.forms['SjablonenBeheerForm'].action = doAction;
			document.forms['SjablonenBeheerForm'].submit();
		}
		
		function fnOnSubmit() {
			if(AppletsLoaded()) {
				document.SjablonenBeheerForm.elements["sjabloon.inhoudHTML"].value = inhoudEop.getHTMLData();
				document.SjablonenBeheerForm.elements["sjabloon.slotgroetHTML"].value = slotgroetEop.getHTMLData();
				document.SjablonenBeheerForm.elements["sjabloon.handtekeningHTML"].value = handtekeningEop.getHTMLData();
				document.SjablonenBeheerForm.elements["sjabloon.inhoud"].value = inhoudEop.getPlainText();
				document.SjablonenBeheerForm.elements["sjabloon.slotgroet"].value = slotgroetEop.getPlainText();
				document.SjablonenBeheerForm.elements["sjabloon.handtekening"].value = handtekeningEop.getPlainText();
			}
		}
	
		function selecteer(){
			window.location.href = 'initSjablonenBeheer?id=' + document.SjablonenBeheerForm.elements['sjabloon.id'].options[document.SjablonenBeheerForm.elements['sjabloon.id'].selectedIndex].value		
		}	
	
	</script>
</logic:equal>

<logic:notEqual name="eopro" value="true">
	<script language="JavaScript">
		function doSubmit(doAction){
			fnOnSubmit();

			document.forms['SjablonenBeheerForm'].action = doAction;
			document.forms['SjablonenBeheerForm'].submit();
		}
		
		function fnOnSubmit() {
			document.SjablonenBeheerForm.elements["sjabloon.inhoudHTML"].value = document.SjablonenBeheerForm.elements["sjabloon.inhoud"].value;
			document.SjablonenBeheerForm.elements["sjabloon.slotgroetHTML"].value = document.SjablonenBeheerForm.elements["sjabloon.slotgroet"].value;
			document.SjablonenBeheerForm.elements["sjabloon.handtekeningHTML"].value = document.SjablonenBeheerForm.elements["sjabloon.handtekening"].value;
		}
	
		function selecteer(){
			window.location.href = 'initSjablonenBeheer?id=' + document.SjablonenBeheerForm.elements['sjabloon.id'].options[document.SjablonenBeheerForm.elements['sjabloon.id'].selectedIndex].value		
		}	
	
	</script>
</logic:notEqual>


	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>		
</head>
<body> 

<bean:define name="SjablonenBeheerForm" property="sjablonen" type="java.util.ArrayList" id="sjablonen" />
<html:form method="post" action="/sjablonenBeheer" onsubmit="return fnOnSubmit()">

	<table class="borderbottom">
		<tr>
			<td class="h2style">
				<logic:notEqual name="SjablonenBeheerForm" property="sjabloon.id" value="0">
				Aanpassen <i>Sjabloon</i>
				</logic:notEqual>
				<logic:equal name="SjablonenBeheerForm" property="sjabloon.id" value="0">
				Invoeren <i>Sjabloon</i>
				</logic:equal>
			</td>
			
			<td class="modus" align="right"><bean:message key="algemeen.modus"/>: 				
				<input type="radio" name=useEopro value="true" onclick="javascript:doSubmit('../do/sjablonenBeheer?Button=EditeerModus<c:if test="${SjablonenBeheerForm.sjabloon.id != 0}">&U=true</c:if>');" <c:if test="${sessionScope.eopro}">CHECKED</c:if>/><bean:message key="algemeen.metopmaak"/>
				<input type="radio" name=useEopro value="false" onclick="javascript:doSubmit('../do/sjablonenBeheer?Button=EditeerModus<c:if test="${SjablonenBeheerForm.sjabloon.id != 0}">&U=true</c:if>');" <c:if test="${!sessionScope.eopro}">CHECKED</c:if>/><bean:message key="algemeen.eenvoudig"/>
			</td>
		</tr>				
	</table>
	
	<html:errors />
	
	<html:hidden name="SjablonenBeheerForm" property="sjabloon.inhoudHTML" />
	<html:hidden name="SjablonenBeheerForm" property="sjabloon.slotgroetHTML" />
	<html:hidden name="SjablonenBeheerForm" property="sjabloon.handtekeningHTML" />
	<logic:equal name="eopro" value="true">
		<html:hidden name="SjablonenBeheerForm" property="sjabloon.inhoud" />
		<html:hidden name="SjablonenBeheerForm" property="sjabloon.slotgroet" />
		<html:hidden name="SjablonenBeheerForm" property="sjabloon.handtekening" />
	</logic:equal>
		


	<table cellspacing="5">
		<tr>
			<td colspan="4" align="center">
				<html:select name="SjablonenBeheerForm" property="sjabloon.id" onchange="selecteer()">
					<option value="0">Selecteer...</option>
					<html:options collection="sjablonen" property="id" labelProperty="omschrijving"/>
				</html:select>
				<hr />
			</td>
		</tr>
		<tr>
			<td><html:img page="/images/label_omschrijving.gif" /></td>
			<td><html:text name="SjablonenBeheerForm" property="sjabloon.omschrijving" /></td>
			<td><html:img page="/images/label_onderwerp.gif" /></td>
			<td><html:text name="SjablonenBeheerForm" property="sjabloon.onderwerp" /></td>
		</tr>
		<tr><td><html:img page="/images/label_eigenlijke_tekst.gif" /></td>	
			<td colspan="3">
				
				<logic:equal name="eopro" value="true">
					<script type="text/javascript" language="javascript">	
						inhoudEop = new editOnPro(700, 150, "inhoud", "editor", "inhoudEop");
						inhoudEop.setCodebase("/eopro");
						inhoudEop.setUIConfig(strUIConfig);
						inhoudEop.setConfig(strConfig);
						inhoudEop.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
						inhoudEop.setStartUpScreenBackgroundColor("#EBF0FF");
						inhoudEop.setStartUpScreenTextColor("navy");
						inhoudEop.setLocaleCode("en_US");
						inhoudEop.setParam("bodyonly", "true");
						inhoudEop.setParam("oldfontstylemode","true");
						inhoudEop.setParam("sourceview","true");
						inhoudEop.setParam("cache_option","plugin");
						inhoudEop.setParam("cache_archive", "edit-on-pro-4.jar");
						inhoudEop.setOnEditorLoaded("fnOnLoad1");
						inhoudEop.setOnDataLoaded("InhoudLoaded");				
						inhoudEop.loadEditor();
					</script> 	
				</logic:equal>
				
				<logic:notEqual name="eopro" value="true">
					<html:textarea name="SjablonenBeheerForm" property="sjabloon.inhoud"  cols="80" rows="7"/>
				</logic:notEqual>

			
			
<!--				<applet code="com.realobjects.eop.applet.EditorApplet" archive="edit-on-pro-signed.jar,tidy.jar,ssce.jar" 
				codebase="/eopro/" height="150" width="700" name="inhoud" id="editor" MAYSCRIPT>

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
					<PARAM name="ONDATALOADED" value="InhoudLoaded" />			
				</applet>
-->
			</td>
		</tr>
		<tr><td><html:img page="/images/label_slotgroet.gif" /></td>  
			<td colspan="3">
				<logic:equal name="eopro" value="true">
					<script type="text/javascript" language="javascript">	
						slotgroetEop = new editOnPro(700, 150, "slotgroet", "editor", "slotgroetEop");
						slotgroetEop.setCodebase("/eopro");
						slotgroetEop.setUIConfig(strUIConfig);
						slotgroetEop.setConfig(strConfig);
						slotgroetEop.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
						slotgroetEop.setStartUpScreenBackgroundColor("#EBF0FF");
						slotgroetEop.setStartUpScreenTextColor("navy");
						slotgroetEop.setLocaleCode("en_US");
						slotgroetEop.setParam("bodyonly", "true");
						slotgroetEop.setParam("oldfontstylemode","true");
						slotgroetEop.setParam("sourceview","true");
						slotgroetEop.setParam("cache_archive", "edit-on-pro-4.jar");
						slotgroetEop.setParam("cache_option","plugin");
						slotgroetEop.setOnEditorLoaded("fnOnLoad2");
						slotgroetEop.setOnDataLoaded("SlotgroetLoaded");				
						slotgroetEop.loadEditor();
					</script> 	
				</logic:equal>
				
				<logic:notEqual name="eopro" value="true">
					<html:textarea name="SjablonenBeheerForm" property="sjabloon.slotgroet" rows="7"  cols="80"/>
				</logic:notEqual>
<!--				<applet code="com.realobjects.eop.applet.EditorApplet" archive="edit-on-pro-signed.jar,tidy.jar,ssce.jar" 
				codebase="/eopro/" height="150" width="700" name="slotgroet" id="editor" MAYSCRIPT>

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
					<PARAM name="ONDATALOADED" value="SlotgroetLoaded" />
				</applet>
-->
			</td>
		</tr>
		<tr><td><html:img page="/images/label_handtekening.gif" /></td>
			<td colspan="3">
				<logic:equal name="eopro" value="true">
					<script type="text/javascript" language="javascript">	
						handtekeningEop = new editOnPro(700, 150, "handtekening", "editor", "handtekeningEop");
						handtekeningEop.setCodebase("/eopro");
						handtekeningEop.setUIConfig(strUIConfig);
						handtekeningEop.setConfig(strConfig);
						handtekeningEop.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
						handtekeningEop.setStartUpScreenBackgroundColor("#EBF0FF");
						handtekeningEop.setStartUpScreenTextColor("navy");
						handtekeningEop.setLocaleCode("en_US");
						handtekeningEop.setParam("bodyonly", "true");
						handtekeningEop.setParam("oldfontstylemode","true");
						handtekeningEop.setParam("sourceview","true");
						handtekeningEop.setParam("cache_archive", "edit-on-pro-4.jar");
						handtekeningEop.setParam("cache_option","plugin");
						handtekeningEop.setOnEditorLoaded("fnOnLoad3");
						handtekeningEop.setOnDataLoaded("HandtekeningLoaded");				
						handtekeningEop.loadEditor();
					</script> 	
				</logic:equal>
				
				<logic:notEqual name="eopro" value="true">
					<html:textarea name="SjablonenBeheerForm" property="sjabloon.handtekening" rows="7"  cols="80"/>
				</logic:notEqual>
				
<!--				<applet code="com.realobjects.eop.applet.EditorApplet" archive="edit-on-pro-signed.jar,tidy.jar,ssce.jar" 
				codebase="/eopro/" height="150" width="700" name="handtekening" id="editor" MAYSCRIPT>

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
		            <PARAM name="ONEDITORLOADED" value="fnOnLoad3"/>
					<PARAM name="ONDATALOADED" value="HandtekeningLoaded" />
				</applet>
-->
			</td>
		</tr>
		<tr><td><html:img page="/images/label_actief.gif" /></td>
			<td colspan="3"><html:checkbox name="SjablonenBeheerForm" property="sjabloon.actief" /></td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<logic:notEqual name="SjablonenBeheerForm" property="sjabloon.id" value="0">
					<%--<html:submit value="Wijzigen" property="Button" onclick="return fnOnSubmit()" />--%>
					<html:image property="Wijzigen" value="Wijzigen" page="/images/button_wijzigen.gif" onclick="return fnOnSubmit()" />
				</logic:notEqual>
				<logic:equal name="SjablonenBeheerForm" property="sjabloon.id" value="0">
					<%--<html:submit value="Toevoegen" property="Button" onclick="return fnOnSubmit()" />--%>
					<html:image property="Toevoegen" value="Toevoegen" page="/images/button_toevoegen.gif" onclick="return fnOnSubmit()" />
				</logic:equal>			
				<a href="initSjablonenBeheer" border="0"><img src="<html:rewrite page='/images/button_nieuw_sjabloon.gif' />" border="0" /></a>
				<a href="../admin/beheerdermenu.jsp" border="0"><img src="<html:rewrite page='/images/button_menu.gif' />" border="0" /></a>						
			</td>
		</tr>
	</table>


</html:form>
</body>
</html:html>