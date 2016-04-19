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
			document.forms['GetCategorieForSearchForm'].action = doAction;
 		   	document.forms['GetCategorieForSearchForm'].submit();
		}
	</script>
	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>		
</head>

<body>

<bean:define name="GetCategorieForSearchForm" property="categorien" type="java.util.ArrayList" id="categorien" />

<h2>SELECTEREN Categorie</h2>

<html:errors />

<html:form method="post" action="/getCategorieForSearch">
	<a class="bigger" href="javascript:doSubmit('../do/getCategorieForSearch?Annuleren=true')">Zoekscherm</a>
				<table cellspacing="5">
					<tr>
						<th>
							<logic:notEqual name="GetCategorieForSearchForm" property="categorie.superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
								<bean:write name="GetCategorieForSearchForm" property="categorie.superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.omschrijving" /> > 
							</logic:notEqual>
							<logic:notEqual name="GetCategorieForSearchForm" property="categorie.superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
								<bean:write name="GetCategorieForSearchForm" property="categorie.superCategorie.superCategorie.superCategorie.superCategorie.omschrijving" /> > 
							</logic:notEqual>
							<logic:notEqual name="GetCategorieForSearchForm" property="categorie.superCategorie.superCategorie.superCategorie.id" value="0">
								<bean:write name="GetCategorieForSearchForm" property="categorie.superCategorie.superCategorie.superCategorie.omschrijving" /> > 
							</logic:notEqual>
							<logic:notEqual name="GetCategorieForSearchForm" property="categorie.superCategorie.superCategorie.id" value="0">
								<bean:write name="GetCategorieForSearchForm" property="categorie.superCategorie.superCategorie.omschrijving" /> >
							</logic:notEqual>							
							<logic:notEqual name="GetCategorieForSearchForm" property="categorie.superCategorie.id" value="0">
								<bean:write name="GetCategorieForSearchForm" property="categorie.superCategorie.omschrijving" /> > 
							</logic:notEqual>
							<logic:notEqual name="GetCategorieForSearchForm" property="categorie.id" value="0">
								<bean:write name="GetCategorieForSearchForm" property="categorie.omschrijving" />
							</logic:notEqual>							
						</th>
					</tr>
					<tr>
						<td>
							<html:select name="GetCategorieForSearchForm" property="categorie.id" multiple="20" size="20">
								<html:options collection="categorien" property="id" labelProperty="omschrijving"/>
							</html:select>
							<html:hidden name="GetCategorieForSearchForm" property="categorie.parentId" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<html:image property="Naar Subcategorie" value="Naar Subcategorie" page="/images/button_niveaumin1.gif" />
							<html:image property="Naar Hoofdcategorie" value="Naar Hoofdcategorie" page="/images/button_niveauplus1.gif" />
							<html:image property="Selecteren" value="Selecteren" page="/images/button_selecteer.gif" />
						</td>
					</tr>
				</table>
	
</html:form>
</body>
</html:html>