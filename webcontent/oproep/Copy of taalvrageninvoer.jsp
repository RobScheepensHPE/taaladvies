
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
	<script language="JavaScript">

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
		
		function doSubmit(doAction){
			fnOnSubmit();

			document.forms['TaalvragenInvoerForm'].action = doAction;
 		   	document.forms['TaalvragenInvoerForm'].submit();
		}

		function fnOnLoad1() {
			document.titel.setHTMLData("http://",document.TaalvragenInvoerForm.elements["taalvraag.titelHTML"].value);
		}
		function fnOnLoad2() {
			document.vraag.setHTMLData("http://",document.TaalvragenInvoerForm.elements["taalvraag.vraagHTML"].value);
		}
		function fnOnLoad3() {
			document.antwoord.setHTMLData("http://",document.TaalvragenInvoerForm.elements["taalvraag.antwoordHTML"].value);
		}
		function fnOnLoad4() {
			document.toelichting.setHTMLData("http://",document.TaalvragenInvoerForm.elements["taalvraag.toelichtingHTML"].value);
		}
		
		function fnOnSubmit() {
			if(AppletsLoaded())
			{			
				document.TaalvragenInvoerForm.elements["taalvraag.vraag"].value = document.vraag.getPlainText();
				document.TaalvragenInvoerForm.elements["taalvraag.vraagHTML"].value = document.vraag.getHTMLData("http://");
				document.TaalvragenInvoerForm.elements["taalvraag.antwoord"].value = document.antwoord.getPlainText();
				document.TaalvragenInvoerForm.elements["taalvraag.antwoordHTML"].value = document.antwoord.getHTMLData("http://");
				document.TaalvragenInvoerForm.elements["taalvraag.toelichting"].value = document.toelichting.getPlainText();
				document.TaalvragenInvoerForm.elements["taalvraag.toelichtingHTML"].value = document.toelichting.getHTMLData("http://");
				document.TaalvragenInvoerForm.elements["taalvraag.titel"].value = document.titel.getPlainText();
				document.TaalvragenInvoerForm.elements["taalvraag.titelHTML"].value = document.titel.getHTMLData("http://");			
			}
		}	
	</script>
	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>		
</head>
<body> 

<h2>
	<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">
		AANPASSEN 
	</logic:notEqual>
	<logic:equal name="TaalvragenInvoerForm" property="taalvraag.id" value="0">
		INVOEREN 
	</logic:equal>
	Taalvraag
</h2>

<html:errors />

<html:form method="post" action="/taalvragenInvoer" onsubmit="return fnOnSubmit()">
	<html:hidden name="TaalvragenInvoerForm" property="taalvraag.vraagHTML" />
	<html:hidden name="TaalvragenInvoerForm" property="taalvraag.antwoordHTML" />
	<html:hidden name="TaalvragenInvoerForm" property="taalvraag.toelichtingHTML" />
	<html:hidden name="TaalvragenInvoerForm" property="taalvraag.titelHTML" />
	<html:hidden name="TaalvragenInvoerForm" property="taalvraag.vraag" />
	<html:hidden name="TaalvragenInvoerForm" property="taalvraag.antwoord" />
	<html:hidden name="TaalvragenInvoerForm" property="taalvraag.toelichting" />
	<html:hidden name="TaalvragenInvoerForm" property="taalvraag.titel" />
				<table cellspacing="5">
					<tr>
						<td>					
							<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">
								<a class="bigger" href="javascript:doSubmit('../do/taalvragenInvoer?Button=Wijzigen')" border="0">
									Oproep <bean:write name="TaalvragenInvoerForm" property="oproep.id" /> 
								</a>
							</logic:notEqual>
							<logic:equal name="TaalvragenInvoerForm" property="taalvraag.id" value="0">
								<a class="bigger" href="javascript:doSubmit('../do/taalvragenInvoer?Button=Toevoegen')" border="0">
									Oproep <bean:write name="TaalvragenInvoerForm" property="oproep.id" /> 
								</a>
							</logic:equal>			
						</td>
						<td>
							<bean:write name="TaalvragenInvoerForm" property="oproep.voornaam" /> <bean:write name="TaalvragenInvoerForm" property="oproep.naam" />
							<html:hidden name="TaalvragenInvoerForm" property="oproep.id" />
						</td>
						<logic:notEqual name="TaalvragenInvoerForm" property="oproep.telefoon" value="">
						<td><html:img page="/images/label_telefoon.gif" /></td>
						<td align="left">
							<bean:write name="TaalvragenInvoerForm" property="oproep.telefoon" />
						</td>
						</logic:notEqual>
						<logic:equal name="TaalvragenInvoerForm" property="oproep.telefoon" value="">
							<td colspan="2">&nbsp;</td>
						</logic:equal>
						<td>
							<logic:equal name="TaalvragenInvoerForm" property="taalvraag.id" value="0">
								<html:image property="Annuleren" value="Annuleren" page="/images/button_taalvraag_annuleren.gif" onclick="return fnOnSubmit()" />
							</logic:equal>
							&nbsp;
						</td>
					</tr>
					<tr><td colspan="5"><hr /></td></tr>
					<tr>
						<td colspan="5" align="center">
							<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">
								<html:image property="Nieuwe Taalvraag Update" value="Nieuwe Taalvraag Update" page="/images/button_nieuwetaalvraag.gif" onclick="return fnOnSubmit()" />
								<html:image property="Kopieer Update" value="Kopieer Update" page="/images/button_kopieer.gif" onclick="return fnOnSubmit()" />	
								<html:image property="Klant Update" value="Klant Update" page="/images/button_klant.gif" onclick="return fnOnSubmit()" />
								<html:image property="Koppel Tekst" value="Koppel Tekst" page="/images/button_koppel_tekst.gif" />							
							</logic:notEqual>
							<logic:equal name="TaalvragenInvoerForm" property="taalvraag.id" value="0">
								<html:image property="Nieuwe Taalvraag Save" value="Nieuwe Taalvraag Save" page="/images/button_nieuwetaalvraag.gif" onclick="return fnOnSubmit()" />
								<html:image property="Kopieer Save" value="Kopieer Save" page="/images/button_kopieer.gif" onclick="return fnOnSubmit()" />	
								<html:image property="Klant Save" value="Klant Save" page="/images/button_klant.gif" onclick="return fnOnSubmit()" />	
								<html:image property="Koppel Tekst Save" value="Koppel Tekst Save" page="/images/button_koppel_tekst.gif" />
							</logic:equal>
			
						</td>
					</tr>
					<tr>
						<td colspan="5" align="center">
								
								
							<a href="javascript:doSubmit('../do/taalvragenInvoer?Button=Categorie<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">&U=true</logic:notEqual>')">categorie</a>
							&nbsp;|&nbsp;
							<a href="javascript:doSubmit('../do/taalvragenInvoer?Button=Naslagwerk<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">&U=true</logic:notEqual>')">naslagwerk</a>
							--
							<a href="javascript:doSubmit('../do/taalvragenInvoer?Button=Bron<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">&U=true</logic:notEqual>')">bron</a>
							--
							<a href="javascript:doSubmit('../do/taalvragenInvoer?Button=Citaat<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">&U=true</logic:notEqual>')">citaat</a>
							--
							<a href="javascript:doSubmit('../do/taalvragenInvoer?Button=Frequentie<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">&U=true</logic:notEqual>')">frequentie</a>
							--
							<a href="javascript:doSubmit('../do/taalvragenInvoer?Button=Koppeling<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">&U=true</logic:notEqual>')">koppeling</a>	
							--
							<a href="javascript:doSubmit('../do/taalvragenInvoer?Button=Hulpmiddelen<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">&U=true</logic:notEqual>')">hulpmiddelen</a>	
							&nbsp;|&nbsp;
							<a href="javascript:doSubmit('../do/taalvragenInvoer?Button=Vraag<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">&U=true</logic:notEqual>')">vraag</a>
							--
							<a href="javascript:doSubmit('../do/taalvragenInvoer?Button=Bijzonderheid<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">&U=true</logic:notEqual>')">bijzonderheid</a>
							&nbsp;|&nbsp;
							<a href="javascript:doSubmit('../do/taalvragenInvoer?Button=Notitie<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">&U=true</logic:notEqual>')">notitie</a>															
						</td>
					</tr>
					
					<tr>
						<td><html:img page="/images/label_vraagnummer.gif" /></td>
						<td><bean:write name="TaalvragenInvoerForm" property="taalvraag.volgnummer" /><html:hidden name="TaalvragenInvoerForm" property="taalvraag.volgnummer" /></td>
						<td align="left"><html:img page="/images/label_afgehandeld.gif" /></td>
						<td align="left"><html:checkbox name="TaalvragenInvoerForm" property="taalvraag.afgehandeld" /></td>
						<td width="42%">&nbsp;</td>
					</tr>
					<tr>
						<td><html:img page="/images/label_titel.gif" /></td>
						<td colspan="4">
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
						</td>
					</tr>
					<tr>
						<td>
							<html:img page="/images/label_vraag.gif" />
							<html:hidden name="TaalvragenInvoerForm" property="taalvraag.id" />
							<html:hidden name="TaalvragenInvoerForm" property="taalvraag.oproepId" />
						</td>
						<td colspan="4">		
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
						</td>
					</tr>
					<tr>
						<td><html:img page="/images/label_antwoord.gif" /></td>	
						<td colspan="4">
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
						</td>
					</tr>
					<tr>
						<td><html:img page="/images/label_toelichting.gif" /></td>
						<td colspan="4">
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
						</td>
					</tr>
					<tr><td colspan="5"><hr /></td></tr>
					<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">	
						<tr>
							<td colspan="5" align="center">
								<table cellspacing="5">
									<tr>
										<th colspan="2">Gekoppelde teksten</th>
									</tr>
									<logic:present name="TaalvragenInvoerForm" property="taalvraag.teksten">
										<logic:iterate name="TaalvragenInvoerForm" property="taalvraag.teksten" id="teksteni">
											<tr>
												<td>
													<a href="javascript:doSubmit('../do/taalvragenInvoer?Button2=Verwijder&idToDelete=<bean:write name="teksteni" property="id" />')">
														<img src="<html:rewrite page='/images/Delete24.gif' />" border="0" />
													</a>
												</td>
												<td><bean:write name="teksteni" property="titel" /></td>
											</tr>
										</logic:iterate>
									</logic:present>
									<tr>
										<td colspan="2">
											<html:image property="Koppel Tekst" value="Koppel Tekst" page="/images/button_koppel_tekst.gif" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</logic:notEqual>						
				</table>

</html:form>

</body>
</html:html>