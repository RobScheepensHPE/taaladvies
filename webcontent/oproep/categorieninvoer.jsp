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
<bean:define name="CategorienInvoerForm" property="categorien" type="java.util.ArrayList" id="categorien" />

<h2>INVOEREN Categorie</h2>

<html:errors />

<html:form method="post" action="/categorienInvoer">
	
	<logic:present name="CategorienInvoerForm" property="taalvraag">
		<table width="100%">
			<tr>
				<td align="left" width="25%">
					<a class="bigger" href="../do/initOproep_TaalvragenInvoer?id=<bean:write name="CategorienInvoerForm" property="taalvraag.oproepId" />">
						Oproep <bean:write name="CategorienInvoerForm" property="taalvraag.oproep.id" /> 		
					</a>
				</td>
				<td align="right" width="25%">
					<a class="bigger" href="../do/initTaalvragenInvoer?id=<bean:write name="CategorienInvoerForm" property="taalvraag.id" />">
						<logic:notEqual name="CategorienInvoerForm" property="taalvraag.titel" value="">
							<logic:notEqual name="CategorienInvoerForm" property="taalvraag.titel" value="<p></p>">
								<bean:write name="CategorienInvoerForm" property="taalvraag.titel" filter="true" />
							</logic:notEqual>
						</logic:notEqual>
						<logic:equal name="CategorienInvoerForm" property="taalvraag.titel" value="">
							<bean:write name="CategorienInvoerForm" property="taalvraag.vraag" filter="true" />
						</logic:equal>
						<logic:equal name="CategorienInvoerForm" property="taalvraag.titel" value="<p></p>">
							<bean:write name="CategorienInvoerForm" property="taalvraag.vraag" filter="true" />
						</logic:equal>				
					</a>
					<html:hidden name="CategorienInvoerForm" property="taalvraag.id" />
				</td>
				<td width="50%">&nbsp;</td>
			</tr>
		</table>
	</logic:present>
	<logic:present name="CategorienInvoerForm" property="tekst">
			<a class="bigger" href="../do/initOproep_TekstInvoer?id=<bean:write name="CategorienInvoerForm" property="tekst.oproepId" />">
				<bean:write name="CategorienInvoerForm" property="tekst.titel" filter="true" />
			</a>
			<html:hidden name="CategorienInvoerForm" property="tekst.id" />
	</logic:present>
				<table cellspacing="5" width="100%">
					<tr bgcolor="#dddddd">
						<th>&nbsp;</th>
						<th align="left">Categorie</th>
					</tr>		
					<logic:iterate id="categorienlinked" name="categorienbean">
						<tr>
							<td>
								<a href="../do/categorienInvoer?id=<bean:write name="categorienlinked" property="id" />&Button=Verwijder" style="border:none">
									<img src="<html:rewrite page='/images/Delete24.gif' />" border="0"  alt="Delete"/>
								</a>
							</td>							
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
	
				<table cellspacing="5" width="100%">
					<tr>
						<td>
							<html:image property="Naar Hoofdcategorie" value="Naar Hoofdcategorie" page="/images/button_niveauplus1.gif" />
							
						</td>
					</tr>
					<tr>
						<td><b>
							<logic:notEqual name="CategorienInvoerForm" property="categorie.superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
								<bean:write name="CategorienInvoerForm" property="categorie.superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.omschrijving" /> > 
							</logic:notEqual>
							<logic:notEqual name="CategorienInvoerForm" property="categorie.superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
								<bean:write name="CategorienInvoerForm" property="categorie.superCategorie.superCategorie.superCategorie.superCategorie.omschrijving" /> > 
							</logic:notEqual>
							<logic:notEqual name="CategorienInvoerForm" property="categorie.superCategorie.superCategorie.superCategorie.id" value="0">
								<bean:write name="CategorienInvoerForm" property="categorie.superCategorie.superCategorie.superCategorie.omschrijving" /> > 
							</logic:notEqual>
							<logic:notEqual name="CategorienInvoerForm" property="categorie.superCategorie.superCategorie.id" value="0">
								<bean:write name="CategorienInvoerForm" property="categorie.superCategorie.superCategorie.omschrijving" /> >
							</logic:notEqual>							
							<logic:notEqual name="CategorienInvoerForm" property="categorie.superCategorie.id" value="0">
								<bean:write name="CategorienInvoerForm" property="categorie.superCategorie.omschrijving" /> > 
							</logic:notEqual>
							<logic:notEqual name="CategorienInvoerForm" property="categorie.id" value="0">
								<bean:write name="CategorienInvoerForm" property="categorie.omschrijving" />
							</logic:notEqual>							
						</b></td>
					</tr>
					<tr>
						<td>
							<html:select name="CategorienInvoerForm" property="categorie.id" onchange="doSubmit('../do/categorienInvoer?Button=Change')" multiple="20" size="35">
								<html:options collection="categorien" property="id" labelProperty="omschrijving"/>
							</html:select>
							<html:hidden name="CategorienInvoerForm" property="categorie.parentId" />
						</td>
					</tr>
					<tr>
						<td>
							<html:image property="Naar Hoofdcategorie" value="Naar Hoofdcategorie" page="/images/button_niveauplus1.gif" />
							
						</td>
					</tr>
				</table>
</html:form>
</body>
</html:html>