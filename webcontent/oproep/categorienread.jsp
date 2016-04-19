<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/SecurityTagLib.tld" prefix="security" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<security:isLoggedIn />

<html:html locale="true">
<head>
	<title>Databank Taaladvies</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="<html:rewrite page='/style/taaladvies.css' />" rel="stylesheet" type="text/css">
	<link href="<html:rewrite page='/style/taaladvies.js' />" type="text/JavaScript" />
	<script language="JavaScript">	
		function doSubmit(doAction){
 		   	document.forms['CategorienInvoerForm'].action = doAction;
 		   	document.forms['CategorienInvoerForm'].submit();
		}
	</script>
	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>		
</head>

<body>

<bean:define name="CategorienInvoerForm" property="categorienLinked" type="java.util.ArrayList" id="categorienbean" />

<h2>BEKIJKEN Categorie</h2>

<html:form method="post" action="/categorienInvoer">
	
	<logic:present name="CategorienInvoerForm" property="taalvraag">
			<a href="../do/initTaalvragenInvoer?id=<bean:write name="CategorienInvoerForm" property="taalvraag.id" />&ro=true<logic:equal name="CategorienInvoerForm" property="adm" value="true">&adm=true</logic:equal>">
				<logic:notEqual name="CategorienInvoerForm" property="taalvraag.titel" value="">
					<bean:write name="CategorienInvoerForm" property="taalvraag.titel" filter="true" />
				</logic:notEqual>
				<logic:equal name="CategorienInvoerForm" property="taalvraag.titel" value="">
					<bean:write name="CategorienInvoerForm" property="taalvraag.vraag" />
				</logic:equal> 
			</a>
	</logic:present>
	<logic:present name="CategorienInvoerForm" property="tekst">
			<a href="../do/initOproep_TekstInvoer?id=<bean:write name="CategorienInvoerForm" property="tekst.oproepId" />&ro=true">
				<bean:write name="CategorienInvoerForm" property="tekst.titel" filter="true" />
			</a>
	</logic:present>
	
				<table cellspacing="5" width="100%">
					<tr bgcolor="#dddddd">
						<th align="left">Categorie</th>
					</tr>		
					<logic:iterate id="categorienlinked" name="categorienbean">
						<tr>
							<td>
								<logic:notEqual name="categorienlinked" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
									<bean:write name="categorienlinked" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.omschrijving" /> > 
								</logic:notEqual>
								<logic:notEqual name="categorienlinked" property="superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
									<bean:write name="categorienlinked" property="superCategorie.superCategorie.superCategorie.superCategorie.omschrijving" /> > 
								</logic:notEqual>
								<logic:notEqual name="categorienlinked" property="superCategorie.superCategorie.superCategorie.id" value="0">
									<bean:write name="categorienlinked" property="superCategorie.superCategorie.superCategorie.omschrijving" /> > 
								</logic:notEqual>
								<logic:notEqual name="categorienlinked" property="superCategorie.superCategorie.id" value="0">
									<bean:write name="categorienlinked" property="superCategorie.superCategorie.omschrijving" /> >
								</logic:notEqual>							
								<logic:notEqual name="categorienlinked" property="superCategorie.id" value="0">
									<bean:write name="categorienlinked" property="superCategorie.omschrijving" /> > 
								</logic:notEqual>
								<logic:notEqual name="categorienlinked" property="id" value="0">
									<bean:write name="categorienlinked" property="omschrijving" />
								</logic:notEqual>
							</td>									
						</tr>
					</logic:iterate>		
				</table>
</html:form>
</body>
</html:html>