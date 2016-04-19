<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/iflogic.tld" prefix="iflogic" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/SecurityTagLib.tld" prefix="security" %>
<security:isLoggedIn />
<script language="javascript">
	function doSubmit(doAction){
		document.forms[1].action = doAction;
 	   	document.forms[1].submit();
	}	
	
</script>
<html:html locale="true">
<head>
<title>Databank Taaladvies</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="<html:rewrite page='/style/taaladvies.css' />"    rel="stylesheet" type="text/css">
<link href="<html:rewrite page='/style/taaladvies.js' />"    type="text/JavaScript"/>
</head>

<body>

<h2>Zoekfunctie tekst Koppelen</h2>

<html:errors/>
<p>
	<a class="bigger" href="../do/initTaalvragenInvoer?id=<bean:write name="TaalvragenInvoerForm" property="taalvraag.id" />&parentId=<bean:write name="TaalvragenInvoerForm" property="taalvraag.oproepId" />">
		<logic:equal name="TaalvragenInvoerForm" property="taalvraag.titel" value="">
			<bean:write name="TaalvragenInvoerForm" property="taalvraag.vraag" />
		</logic:equal>
		<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.titel" value="">
			<bean:write name="TaalvragenInvoerForm" property="taalvraag.titel" />
		</logic:notEqual>
	</a>
</p>
<html:form method="post" action="/zoekenKoppelTXT">

<bean:define name="SearchForm" property="relevanties" type="java.util.ArrayList" id="relevanties" />

                <table cellspacing="5" width="100%">
                    <tr>
                        <td><b>Zoek resultaten </b>:
                        	<table>
                        	<tr>
 		                       	<td>met alle woorden</td>
                        		<td><html:text property="andTerms" size="50" maxlength="200"/></td>
                        	</tr>
                        	<tr>
 		                       	<td>met de exacte woordcombinatie</td>
                        		<td><html:text property="phrase" size="50" maxlength="200"/></td>
                        	</tr>
							</table>
                        </td>
                    </tr>                    
                    <tr>
                        <td><b>Waar?</b></td>
                    </tr>
                    <tr>
                        <td>Eigen teksten <html:checkbox property="eigenTXT" /> | Andere teksten <html:checkbox property="alleTXT" /></td>
                    </tr>
                    <tr>
                        <td>Teksten: titel <html:checkbox property="tekstTitel" /> | tekstbloktitel <html:checkbox property="tekstSubtitels" /> | tekstblokken <html:checkbox property="tekstVelden" /></td>
                    </tr>
					<tr>		
				        <td><html:radio property="dateSearch" value="1" /> Zoek vanaf datum: 
							<html:text property="startDateD" size="2" maxlength="2" />
							<html:text property="startDateM" size="2" maxlength="2" />
							<html:text property="startDateY" size="4" maxlength="4" />
							tot en met datum: 
							<html:text property="endDateD" size="2" maxlength="2" />
							<html:text property="endDateM" size="2" maxlength="2" />
							<html:text property="endDateY" size="4" maxlength="4" />
						</td>
					</tr>
					<tr>		
				        <td><html:radio property="dateSearch" value="2" /> Zoek op datum: 
							<html:text property="singleDateD" size="2" maxlength="2" />
							<html:text property="singleDateM" size="2" maxlength="2" />
							<html:text property="singleDateY" size="4" maxlength="4" />
						</td>
					</tr>	      
                    <tr>
                        <td>Relevantie: 
							<html:select property="relevantie">
								<option value="0">Selecteer...</option>
								<html:options collection="relevanties" property="id" labelProperty="omschrijving" />
							</html:select>
						</td>
                    </tr>
                    <tr>
                        <td>Distributie: <bean:message key="zoek.emailuit"/> <html:checkbox property="emailUit"></html:checkbox>
						</td>
                    </tr>
                </table>

		<html:image property="submit" page="/images/button_zoeken.gif" />
</html:form>
<h2>Zoeken binnen een categorie</h2>

<html:form method="post" action="/zoekenKoppelTXT">
<table>
	<logic:iterate name="SearchForm" property="categorieList"  id="cat" indexId="idx" >
		<tr>
			<td>
				<a href="javascript:doSubmit('../do/initGetCategorieForSearch?page=ZKKOP&whichCategorie=<bean:write name="idx" />')" border="0"><html:img page="/images/button_categorienlijst.gif" border="0" /></a>
			</td>
			<td>
				<logic:notEqual name="cat" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
					<bean:write name="cat" property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.omschrijving" /> > 
				</logic:notEqual>
				<logic:notEqual name="cat" property="superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
					<bean:write name="cat" property="superCategorie.superCategorie.superCategorie.superCategorie.omschrijving" /> > 
				</logic:notEqual>
				<logic:notEqual name="cat" property="superCategorie.superCategorie.superCategorie.id" value="0">
					<bean:write name="cat" property="superCategorie.superCategorie.superCategorie.omschrijving" /> > 
				</logic:notEqual>
				<logic:notEqual name="cat" property="superCategorie.superCategorie.id" value="0">
					<bean:write name="cat" property="superCategorie.superCategorie.omschrijving" /> >
				</logic:notEqual>							
				<logic:notEqual name="cat" property="superCategorie.id" value="0">
					<bean:write name="cat" property="superCategorie.omschrijving" /> > 
				</logic:notEqual>
				<logic:notEqual name="cat" property="id" value="0" >
					<bean:write name="cat" property="omschrijving" />
				</logic:notEqual>														
				<html:hidden name="cat" property="id" indexed="true"/>
			</td>
			<td>
				<a href="javascript:doSubmit('../do/getCategorieForSearch?page=ZKKOP&Verwijderen=true&whichCategorie=<bean:write name="idx" />')" border="0"><html:img page="/images/Delete24.gif" border="0" /></a>
			</td>
		</tr>
	</logic:iterate>
	<tr>
		<c:if test="${empty idx}">
			<c:set var="idx" value="-1"></c:set>
		</c:if>
		<td>
			<a href="javascript:doSubmit('../do/initGetCategorieForSearch?page=ZKKOP&whichCategorie=<c:out value="${idx + 1}"></c:out>')" border="0"><html:img page="/images/button_categorienlijst.gif" border="0" /></a>
		</td>	
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
 		<td colspan="2"><b>Waar?</b></td>
	</tr>	
	<tr>
 		<td colspan="2">Eigen teksten <html:checkbox property="eigenTXT" /> | Andere teksten <html:checkbox property="alleTXT" /></td>
	</tr>
	<tr>		
        <td colspan="2"><html:radio property="dateSearch" value="1" /> Zoek vanaf datum: 
			<html:text property="startDateD" size="2" maxlength="2" />
			<html:text property="startDateM" size="2" maxlength="2" />
			<html:text property="startDateY" size="4" maxlength="4" />
			tot en met datum: 
			<html:text property="endDateD" size="2" maxlength="2" />
			<html:text property="endDateM" size="2" maxlength="2" />
			<html:text property="endDateY" size="4" maxlength="4" />
		</td>
	</tr>
	<tr>		
        <td colspan="2"><html:radio property="dateSearch" value="2" /> Zoek op datum: 
			<html:text property="singleDateD" size="2" maxlength="2" />
			<html:text property="singleDateM" size="2" maxlength="2" />
			<html:text property="singleDateY" size="4" maxlength="4" />
		</td>
	</tr>
	<tr>
		<td colspan="2">Distributie: <bean:message key="zoek.emailuit"/> <html:checkbox property="emailUit"></html:checkbox>
		</td>
	</tr>	
    <tr>
    	<td colspan="2">
    		<html:image property="submit2" page="/images/button_zoeken.gif" />
    	</td>			
	</tr>
</table>

</html:form>

</body>
</html:html>















