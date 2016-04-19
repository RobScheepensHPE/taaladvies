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
	<script language="JavaScript1.2" src="<html:rewrite page='/style/taaladvies.js' />"></script>
	<script language="JavaScript1.2" src="/ewebeditpro4/ewebeditpro.js"></script>	
	<logic:equal name="eopro" value="true">
		<script language="JavaScript">
			var appletLoaded = false
			
			function ToelichtingLoaded()
			{
				if (eWebEditPro.event.srcName == "webreferentie.toelichtingHTML") {
					appletLoaded = true;
				}
			}	
			
			function fnOnSubmit() {
				if(appletLoaded) {
					eWebEditPro.actionOnUnload = EWEP_ONUNLOAD_NOSAVE;
					document.WebreferentiesInvoerForm.elements["webreferentie.toelichting"].value = eWebEditPro.instances["webreferentie.toelichtingHTML"].editor.getBodyText();
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
				document.WebreferentiesInvoerForm.elements["webreferentie.toelichtingHTML"].value = document.WebreferentiesInvoerForm.elements["webreferentie.toelichting"].value;
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

<bean:define name="WebreferentiesInvoerForm" property="webreferenties" type="java.util.ArrayList" id="webreferentiesbean" />

<h2>
	<logic:notEqual name="WebreferentiesInvoerForm" property="webreferentie.id" value="0">
		AANPASSEN 
	</logic:notEqual>
	<logic:equal name="WebreferentiesInvoerForm" property="webreferentie.id" value="0">
		INVOEREN 
	</logic:equal>
	Koppeling
</h2>

<html:errors />

<html:form method="post" action="/webreferentiesInvoer">
	<logic:notEqual name="WebreferentiesInvoerForm" property="webreferentie.id" value="0">
		<html:hidden name="WebreferentiesInvoerForm" property="webreferentie.id" />
	</logic:notEqual>

	<html:hidden name="WebreferentiesInvoerForm" property="webreferentie.toelichtingHTML" />
	<logic:equal name="eopro" value="true">
		<html:hidden name="WebreferentiesInvoerForm" property="webreferentie.toelichting" />	
	</logic:equal>
				<table cellspacing="5" width="100%">
					<tr>
						<td colspan="4">
							<logic:present name="WebreferentiesInvoerForm" property="taalvraag">
									<a class="bigger" href="../do/initTaalvragenInvoer?id=<bean:write name="WebreferentiesInvoerForm" property="taalvraag.id" />">
										<logic:notEqual name="WebreferentiesInvoerForm" property="taalvraag.titel" value="">
											<logic:notEqual name="WebreferentiesInvoerForm" property="taalvraag.titel" value="<p></p>">
												<bean:write name="WebreferentiesInvoerForm" property="taalvraag.titel" filter="true" />
											</logic:notEqual>
										</logic:notEqual>
										<logic:equal name="WebreferentiesInvoerForm" property="taalvraag.titel" value="">
											<bean:write name="WebreferentiesInvoerForm" property="taalvraag.vraag" filter="true" />
										</logic:equal>
										<logic:equal name="WebreferentiesInvoerForm" property="taalvraag.titel" value="<p></p>">
											<bean:write name="WebreferentiesInvoerForm" property="taalvraag.vraag" filter="true" />
										</logic:equal>				
									</a>
							</logic:present>
							<logic:present name="WebreferentiesInvoerForm" property="tekst">
								<a class="bigger" href="../do/initOproep_TekstInvoer?id=<bean:write name="WebreferentiesInvoerForm" property="tekst.oproepId" />">
									<bean:write name="WebreferentiesInvoerForm" property="tekst.titel" filter="true" />
								</a>
								<html:hidden name="WebreferentiesInvoerForm" property="tekst.id" />
							</logic:present>						
						</td>
					</tr>					
					<tr bgcolor="#dddddd">
						<th align="left">&nbsp;</th>
						<th align="left">Omgeving</th>
						<th align="left">URL</th>
						<th align="left">Toelichting</th>
					</tr>		
					<logic:iterate id="webreferenties" name="webreferentiesbean">
						<tr>
							<td valign="top">
								<a href="../do/webreferentiesInvoer?id=<bean:write name="webreferenties" property="id" />&Button=Verwijder" border="0"><img src="<html:rewrite page='/images/Delete24.gif' />" border="0" /></a>
							</td>
							<td valign="top">
								<a href="../do/webreferentiesInvoer?id=<bean:write name="webreferenties" property="id" />&Button=Wijzig">
									<bean:write name="webreferenties" property="omgeving" filter="true" />
								</a>
							</td>
							<td valign="top"><a href="<bean:write name="webreferenties" property="url" filter="true" />" target="blank">link</a></td>
							<td valign="top"><bean:write name="webreferenties" property="toelichtingHTML" filter="false" /></td>
						</tr>
					</logic:iterate>		
				</table>
<hr />	
				<table cellspacing="5">
					<tr>
						<td><html:img page="/images/label_omgeving.gif" /></td>
						<td><html:text name="WebreferentiesInvoerForm" property="webreferentie.omgeving" size="90" /></td>
					</tr>
					<tr>
						<td><html:img page="/images/label_url.gif" /></td>
						<td><html:text name="WebreferentiesInvoerForm" property="webreferentie.url" size="90" /></td>
					</tr>
					<tr>
						<td><html:img page="/images/label_toelichting.gif" /></td>
						<td colspan="3">
						
							
							<logic:equal name="eopro" value="true">
								<c:url value="/ewebeditproConfig/toolbar-taaladvies4.jsp" var="toolbarConfig"/>
								<script language="JavaScript1.2">		
									eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";								
									eWebEditPro.create("webreferentie.toelichtingHTML", "700", "500");
									eWebEditPro.addEventHandler("onready",  ToelichtingLoaded);
								</script> 	
							</logic:equal>
							<logic:notEqual name="eopro" value="true">
								<html:textarea name="WebreferentiesInvoerForm" property="webreferentie.toelichting" style="width: 100%;" rows="25" />	
							</logic:notEqual>
								
						</td>	
					</tr>
					<tr>
						<td colspan="2" align="center">
							<logic:notEqual name="WebreferentiesInvoerForm" property="webreferentie.id" value="0">
								<html:image property="Wijzigen" value="Wijzigen" page="/images/button_wijzigen.gif" onclick="return fnOnSubmit()" />
								<html:image property="Overzicht Update" value="Overzicht Update" page="/images/button_wijzigen_overzicht.gif" onclick="return fnOnSubmit()" />
							</logic:notEqual>	
							<logic:equal name="WebreferentiesInvoerForm" property="webreferentie.id" value="0">
								<html:image property="Toevoegen" value="Toevoegen" page="/images/button_toevoegen.gif" onclick="return fnOnSubmit()" />
								<html:image property="Overzicht Insert" value="Overzicht Insert" page="/images/button_toevoegen_overzicht.gif" onclick="return fnOnSubmit()" />
							</logic:equal>			
						</td>
					</tr>
				</table>
</html:form>
</body>
</html:html>