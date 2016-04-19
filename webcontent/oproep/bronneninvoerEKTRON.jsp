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
	<script type="text/javascript" language="javascript" src="/eopro/editonpro.js"></script>
	<script language="JavaScript1.2" src="/ewebeditpro4/ewebeditpro.js"></script>
	
	<logic:equal name="eopro" value="true">
		<script language="JavaScript">
		
			var appletLoaded = new Array(2);
			
			for(var i=0;i<appletLoaded.length;i++)
				appletLoaded[i] = false;
	
			function TitelLoaded()
			{
				if (eWebEditPro.event.srcName == "bron.titelHTML") {
					appletLoaded[0] = true;
				}
			}
			
			function CitaatLoaded()
			{
				if (eWebEditPro.event.srcName == "bron.citaatHTML") {
					appletLoaded[1] = true;
				}
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
				
			function fnOnSubmit(){
				if(AppletsLoaded()) {
					eWebEditPro.actionOnUnload = EWEP_ONUNLOAD_NOSAVE;
					document.BronnenInvoerForm.elements["bron.titel"].value = eWebEditPro.instances["bron.titelHTML"].editor.getBodyText();
					document.BronnenInvoerForm.elements["bron.citaat"].value = eWebEditPro.instances["bron.citaatHTML"].editor.getBodyText();
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
									<img src="<html:rewrite page='/images/Delete24.gif' />" border="0" />	
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

							<c:url value="/ewebeditproConfig/toolbar-taaladvies.jsp" var="toolbarConfig"/>
							<script language="JavaScript1.2">		
								eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";								
								eWebEditPro.create("bron.titelHTML", "700", "120");
								eWebEditPro.addEventHandler("onready",  TitelLoaded);
							</script>
						</logic:equal>
						<logic:notEqual name="eopro" value="true">
							<html:textarea name="BronnenInvoerForm" property="bron.titel" cols="80"  rows="1"/>
						</logic:notEqual>
						</td>
                     </tr>
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


								<script language="JavaScript1.2">
									<c:url value="/ewebeditproConfig/toolbar-taaladvies7.jsp" var="toolbarConfig"/>
									eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";									
									eWebEditPro.create("bron.citaatHTML", "700", "500");
									eWebEditPro.addEventHandler("onready",  CitaatLoaded);
								</script>					
							</logic:equal>
							<logic:notEqual name="eopro" value="true">
									<html:textarea name="BronnenInvoerForm" property="bron.citaat" cols="80" rows="25"/>
							</logic:notEqual>

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