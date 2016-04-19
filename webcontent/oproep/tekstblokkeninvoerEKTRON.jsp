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

	<script language="JavaScript1.2" src="/ewebeditpro4/ewebeditpro.js"></script>	
	<script language="JavaScript1.2" src="<html:rewrite page='/style/taaladvies.js' />"></script>
	<logic:equal name="eopro" value="true">
		<script language="JavaScript">
		
			var appletLoaded = new Array(2);
			
			for(var i=0;i<appletLoaded.length;i++)
				appletLoaded[i] = false;
	
			function TitelLoaded()
			{
				if (eWebEditPro.event.srcName == "tekstblok.titelHTML") {
					appletLoaded[0] = true;
				}
			}
			
			function TekstblokLoaded()
			{
				if (eWebEditPro.event.srcName == "tekstblok.tekstblokHTML") {
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
				if(AppletsLoaded()){
					eWebEditPro.actionOnUnload = EWEP_ONUNLOAD_NOSAVE;
					document.TekstblokkenInvoerForm.elements["tekstblok.tekstblok"].value = eWebEditPro.instances["tekstblok.tekstblokHTML"].editor.getBodyText();
					document.TekstblokkenInvoerForm.elements["tekstblok.titel"].value = eWebEditPro.instances["tekstblok.titelHTML"].editor.getBodyText();
					eWebEditPro.save();
					return true;
				} else {
					alert("<bean:message key='algemeen.opmaaknotready'/> ");
					return false;
				}
			}
			
			function doSubmit(doAction){
				if(fnOnSubmit()){
					document.forms['TekstblokkenInvoerForm'].action = doAction;
					document.forms['TekstblokkenInvoerForm'].submit();
				}
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
								<c:url value="/ewebeditproConfig/toolbar-taaladvies.jsp" var="toolbarConfig"/>
								<script language="JavaScript1.2">		
									eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";								
									eWebEditPro.create("tekstblok.titelHTML", "700", "100");
									eWebEditPro.addEventHandler("onready",  TitelLoaded);
								</script>	 	
							</logic:equal>
							
							<logic:notEqual name="eopro" value="true">
								<html:text name="TekstblokkenInvoerForm" property="tekstblok.titel" style="width: 99%;" />
							</logic:notEqual>

						</td>
					</tr>
					<tr><td><html:img page="/images/label_tekstblok.gif" /><html:hidden name="TekstblokkenInvoerForm" property="tekstblok.id" /><html:hidden name="TekstblokkenInvoerForm" property="tekstblok.tekstId" /></td>
						<td colspan="3">		
							<logic:equal name="eopro" value="true"> 
								<c:url value="/ewebeditproConfig/toolbar-taaladvies6.jsp" var="toolbarConfig"/>
								<script language="JavaScript1.2">		
									eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";								
									eWebEditPro.create("tekstblok.tekstblokHTML", "700", "500");
									eWebEditPro.addEventHandler("onready",  TekstblokLoaded);
								</script>	 	
							</logic:equal>
							
							<logic:notEqual name="eopro" value="true">
								<html:textarea name="TekstblokkenInvoerForm" property="tekstblok.tekstblok" rows="20" cols="80"  />
							</logic:notEqual>

						</td>
					</tr>
				</table>
</html:form>
</body>
</html:html>