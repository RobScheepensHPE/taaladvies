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
	<logic:equal name="eopro" value="true">
		<script language="JavaScript1.2" src="/ewebeditpro4/ewebeditpro.js"></script>
		
		<script language="JavaScript">
			var appletLoaded = false
			
			function CitaatLoaded()
			{
				appletLoaded = true;
			}				
			
			function fnOnSubmit() {
				if(appletLoaded){
					eWebEditPro.actionOnUnload = EWEP_ONUNLOAD_NOSAVE;
					document.CitatenInvoerForm.elements["citaat.citaat"].value = eWebEditPro.instances["citaat.citaatHTML"].editor.getBodyText();
					eWebEditPro.save();
					return true;
				} else {
					alert("<bean:message key='algemeen.opmaaknotready'/> ");
					return false;
				}
					
			}
			
			function doSubmit(doAction){
				
				if(fnOnSubmit()){
					document.forms['CitatenInvoerForm'].action = doAction;
					document.forms['CitatenInvoerForm'].submit();
				}
			}
						
		</script>
	</logic:equal>
	
	<logic:notEqual name="eopro" value="true">
	
		<script language="JavaScript">
		
			function fnOnSubmit() {
					document.CitatenInvoerForm.elements["citaat.citaatHTML"].value = document.CitatenInvoerForm.elements["citaat.citaat"].value ;
			}
			
			function doSubmit(doAction){
				fnOnSubmit();
				document.forms['CitatenInvoerForm'].action = doAction;
				document.forms['CitatenInvoerForm'].submit();
			}
						
		</script>
	</logic:notEqual>
	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>		
</head>

<body>

<bean:define name="CitatenInvoerForm" property="citaten" type="java.util.ArrayList" id="citatenbean" />
<bean:define name="CitatenInvoerForm" property="zoekomgevingen" type="java.util.ArrayList" id="zoekomgevingen" />

<h2>
	<logic:notEqual name="CitatenInvoerForm" property="citaat.id" value="0">
		AANPASSEN 
	</logic:notEqual>
	<logic:equal name="CitatenInvoerForm" property="citaat.id" value="0">
		INVOEREN 
	</logic:equal>
	Citaat
</h2>

<html:errors />

<html:form method="post" action="/citatenInvoer">
	<html:hidden name="CitatenInvoerForm" property="citaat.citaatHTML" />
	<logic:equal name="eopro" value="true">
		<html:hidden name="CitatenInvoerForm" property="citaat.citaat" />
	</logic:equal>
	
				<table cellspacing="5" width="100%">
					<tr>
						<td colspan="6">
							<logic:present name="CitatenInvoerForm" property="taalvraag">
									<a class="bigger" href="../do/initTaalvragenInvoer?id=<bean:write name="CitatenInvoerForm" property="taalvraag.id" />">
										<logic:notEqual name="CitatenInvoerForm" property="taalvraag.titel" value="">
											<logic:notEqual name="CitatenInvoerForm" property="taalvraag.titel" value="<p></p>">
												<bean:write name="CitatenInvoerForm" property="taalvraag.titel" filter="true" />
											</logic:notEqual>
										</logic:notEqual>
										<logic:equal name="CitatenInvoerForm" property="taalvraag.titel" value="">
											<bean:write name="CitatenInvoerForm" property="taalvraag.vraag" filter="true" />
										</logic:equal>
										<logic:equal name="CitatenInvoerForm" property="taalvraag.titel" value="<p></p>">
											<bean:write name="CitatenInvoerForm" property="taalvraag.vraag" filter="true" />
										</logic:equal>				
									</a>
							</logic:present>
							<logic:present name="CitatenInvoerForm" property="tekst">
								<a class="bigger" href="../do/initOproep_TekstInvoer?id=<bean:write name="CitatenInvoerForm" property="tekst.oproepId" />">
									<bean:write name="CitatenInvoerForm" property="tekst.titel" filter="true" />
								</a>
								<html:hidden name="CitatenInvoerForm" property="tekst.id" />
							</logic:present>						
						</td>
					</tr>
					<tr bgcolor="#dddddd">
						<th align="left">&nbsp;</th>
						<th align="left">Zoekomgeving</th>
						<th align="left">Specificatie</th>
						<th align="left">URL</th>
						<th align="left">Variant</th>
						<th align="left">Citaat</th>
					</tr>		
					<logic:iterate id="citaten" name="citatenbean">
						<tr>
							<td valign="top">
								<a href="../do/citatenInvoer?id=<bean:write name="citaten" property="id" />&Button=Verwijder">
									<img src="<html:rewrite page='/images/Delete24.gif' />" border="0" />
								</a>
							</td>							
							<td valign="top">
								<a href="../do/citatenInvoer?id=<bean:write name="citaten" property="id" />&Button=Wijzig">
									<bean:write name="citaten" property="zoekomgeving.omschrijving" filter="true" />
								</a>									
							</td>
							<td valign="top"><bean:write name="citaten" property="specificatie" filter="true" /></td>
							<td valign="top"><a href="<bean:write name="citaten" property="url" filter="true" />" target="blank">link</a></td>
							<td valign="top"><bean:write name="citaten" property="variant" filter="true" /></td>
							<td valign="top"><bean:write name="citaten" property="citaatHTML" filter="false" /></td>
						</tr>
					</logic:iterate>		
				</table>
<hr />	
				<table cellspacing="5">
					
					<tr>
						<td><html:img page="/images/label_zoekomgeving.gif" /></td>
						<td>
							<html:select name="CitatenInvoerForm" property="citaat.zoekomgevingId">
								<option value="0">Selecteer...</option>
								<html:options collection="zoekomgevingen" property="id" labelProperty="omschrijving" />
							</html:select>
						</td>
						<td><html:img page="/images/label_specificatie.gif" /></td>
						<td><html:text name="CitatenInvoerForm" property="citaat.specificatie" /></td>
					<tr>
						<td><html:img page="/images/label_url.gif" /></td>
						<td colspan="3"><html:text name="CitatenInvoerForm" property="citaat.url" size="90" /></td>
					</tr>
					<tr>
						<td><html:img page="/images/label_variant.gif" /><html:hidden name="CitatenInvoerForm" property="citaat.id" /></td>
						<td colspan="3"><html:text name="CitatenInvoerForm" property="citaat.variant" size="90" /></td>
					</tr>						
					<tr>
						<td><html:img page="/images/label_citaat.gif" /></td>
						<td colspan="3">
							<logic:equal name="eopro" value="true">
								<c:url value="/ewebeditproConfig/toolbar-taaladvies5.jsp" var="toolbarConfig"/>
								<script language="JavaScript1.2">		
									eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";								
									eWebEditPro.create("citaat.citaatHTML", "700", "500");
									eWebEditPro.addEventHandler("onready",  CitaatLoaded);
								</script>	
							</logic:equal>
							
							<logic:notEqual name="eopro" value="true">
								<html:textarea name="CitatenInvoerForm" property="citaat.citaat" rows="25" style="width: 100%;"/>
							</logic:notEqual>

						</td>	
					</tr>
					<tr>
						<td colspan="4" align="center">
							<logic:notEqual name="CitatenInvoerForm" property="citaat.id" value="0">
								<html:image property="Wijzigen" value="Wijzigen" page="/images/button_wijzigen.gif" onclick="return fnOnSubmit()" />
								<html:image property="Overzicht Update" value="Overzicht Update" page="/images/button_wijzigen_overzicht.gif" onclick="return fnOnSubmit()" />							
							</logic:notEqual>	
							<logic:equal name="CitatenInvoerForm" property="citaat.id" value="0">
								<html:image property="Toevoegen" value="Toevoegen" page="/images/button_toevoegen.gif" onclick="return fnOnSubmit()" />
								<html:image property="Overzicht Insert" value="Overzicht Insert" page="/images/button_toevoegen_overzicht.gif" onclick="return fnOnSubmit()" />
							</logic:equal>
						</td>
					</tr>
				</table>

</html:form>
</body>
</html:html>