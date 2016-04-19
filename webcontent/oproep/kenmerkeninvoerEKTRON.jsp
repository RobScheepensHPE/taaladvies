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
			var appletLoaded = new Array(2);
			
			for(var i=0;i<appletLoaded.length;i++)
				appletLoaded[i] = false;
	
			function InformatieLoaded()
			{
				if (eWebEditPro.event.srcName == "taalvraag.informatieHTML") {
					appletLoaded[0] = true;
				}
			}
			
			function HerformuleringLoaded()
			{
				if (eWebEditPro.event.srcName == "taalvraag.herformuleringHTML") {
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
				
			function fnOnSubmit() {
				if(AppletsLoaded()) {
					eWebEditPro.actionOnUnload = EWEP_ONUNLOAD_NOSAVE;
					document.KenmerkenInvoerForm.elements["taalvraag.informatie"].value = eWebEditPro.instances["taalvraag.informatieHTML"].editor.getBodyText();
					document.KenmerkenInvoerForm.elements["taalvraag.herformulering"].value = eWebEditPro.instances["taalvraag.herformuleringHTML"].editor.getBodyText();
					eWebEditPro.save();
					return true;
				} else {
					alert("<bean:message key='algemeen.opmaaknotready'/> ");
					return false;
				}
			}
			
			function doSubmit(doAction){
				if(fnOnSubmit()){
					document.forms['KenmerkenInvoerForm'].action = doAction;
					document.forms['KenmerkenInvoerForm'].submit();
				}
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
								<c:url value="/ewebeditproConfig/toolbar-taaladvies.jsp" var="toolbarConfig"/>
								<script language="JavaScript1.2">		
									eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";								
									eWebEditPro.create("taalvraag.herformuleringHTML", "700", "200");
									eWebEditPro.addEventHandler("onready",  HerformuleringLoaded);
								</script>		
							</logic:equal>
							
							<logic:notEqual name="eopro" value="true">
								<html:textarea name="KenmerkenInvoerForm" property="taalvraag.herformulering" rows="9"  cols="80"/>
							</logic:notEqual>
						</td>	
					</tr>
					<tr>
						<td><html:img page="/images/label_informatie.gif" /></td>
						<td>
							<logic:equal name="eopro" value="true">
								<c:url value="/ewebeditproConfig/toolbar-taaladvies2.jsp" var="toolbarConfig"/>
								<script language="JavaScript1.2">		
									eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";								
									eWebEditPro.create("taalvraag.informatieHTML", "700", "400");
									eWebEditPro.addEventHandler("onready",  InformatieLoaded);
								</script>		
							</logic:equal>
							
							<logic:notEqual name="eopro" value="true">
								<html:textarea name="KenmerkenInvoerForm" property="taalvraag.informatie" rows="18"  cols="80"/>
							</logic:notEqual>
						</td>
					</tr>
				</table>

</html:form>
</body>
</html:html>