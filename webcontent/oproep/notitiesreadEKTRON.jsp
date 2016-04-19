<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/SecurityTagLib.tld" prefix="security" %>
<security:isLoggedIn />

<html:html locale="true">
<head>
	<title>Databank Taaladvies</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="<html:rewrite page='/style/taaladvies.css' />" rel="stylesheet" type="text/css">
	<link href="<html:rewrite page='/style/taaladvies.js' />" type="text/JavaScript" />
	<script language="JavaScript1.2" src="<html:rewrite page='/style/taaladvies.js' />"></script>
	<script language="JavaScript1.2" src="/ewebeditpro4/ewebeditpro.js"></script>
	
	<logic:equal name="eopro" value="true">
		<script language="JavaScript">
			var appletLoaded = false
			
			function NotitieLoaded()
			{
				if (eWebEditPro.event.srcName == "notitie.notitieHTML") {
					appletLoaded = true;
				}
			}	
			
			function fnOnSubmit() {
				if(appletLoaded) {
					eWebEditPro.actionOnUnload = EWEP_ONUNLOAD_NOSAVE;
					document.NotitiesInvoerForm.elements["notitie.notitie"].value = eWebEditPro.instances["notitie.notitieHTML"].editor.getBodyText();
					eWebEditPro.save();
					return true;
				} else {
					alert("<bean:message key='algemeen.opmaaknotready'/> ");
					return false;
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
								<c:url value="/ewebeditproConfig/toolbar-taaladvies4.jsp" var="toolbarConfig"/>
								<script language="JavaScript1.2">		
									eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";								
									eWebEditPro.create("notitie.notitieHTML", "700", "500");
									eWebEditPro.addEventHandler("onready",  NotitieLoaded);
								</script>
							</logic:equal>
							
							<logic:notEqual name="eopro" value="true">
								<html:textarea name="NotitiesInvoerForm" property="notitie.notitie" rows="25" style="width: 100%;"/>	
							</logic:notEqual>

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
								<c:url value="/ewebeditproConfig/toolbar-taaladvies4.jsp" var="toolbarConfig"/>
								<script language="JavaScript1.2">		
									eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";								
									eWebEditPro.create("notitie.notitieHTML", "700", "500");
									eWebEditPro.addEventHandler("onready",  NotitieLoaded);
								</script> 	
							</logic:equal>
							
							<logic:notEqual name="eopro" value="true">
								<html:textarea name="NotitiesInvoerForm" property="notitie.notitie" rows="25"  cols="80"/>	
							</logic:notEqual>
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