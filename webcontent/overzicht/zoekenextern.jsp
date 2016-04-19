<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/iflogic.tld" prefix="iflogic" %>
<%@ taglib uri="/WEB-INF/SecurityTagLib.tld" prefix="security" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<security:isLoggedIn />	
<html:html locale="true">
<head>
<title>Databank Taaladvies</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="<html:rewrite page='/style/taaladvies.css' />"    rel="stylesheet" type="text/css">
<link href="<html:rewrite page='/style/taaladvies.js' />"    type="text/JavaScript"/>
<script language="javascript">
	
	function doSubmit(doAction){
		document.forms[1].action = doAction;
 	   	document.forms[1].submit();
	}	
	
</script>
</head>

<body>

<table width="100%">
	<tr>
		<td>
			<h2>Zoeken van taalvragen en teksten</h2>
		</td>
		<td align="right">
			<a href="../profielen.jsp">
				<html:img page="/images/button_uitloggen.gif" border="0" />
			</a>
		</td>
	</tr>
</table>

<html:errors/>

<html:form method="post" action="/zoekenExtern">

<html:hidden property="oproepAfgesloten" value="true"/>

<input type="hidden" name="ownerIrrelevant" value="true" />


<table width="80%">

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
                        <td>
                        	Taalvragen <html:checkbox property="alleTV"/> | Teksten <html:checkbox property="alleTXT"/>
                        	<html:hidden property="titel" />
                        	<html:hidden property="vraag" />
                        	<html:hidden property="antwoord" />
                        	<html:hidden property="toelichting" />
                        	<html:hidden property="bijzonderheid" />
                        	<html:hidden property="tekstTitel" />
                        	<html:hidden property="tekstSubtitels" />
                        	<html:hidden property="tekstVelden" />
                        </td>
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
                </table>
                </td>
            </tr>
        </table>
        </td>
    </tr>
    <tr>
    <td>
		<html:image property="submit" page="/images/button_zoeken.gif" />
    </td>
    </tr>
</table>
</html:form>

<html:form method="post" action="/zoekenExtern">

<input type="hidden" name="ownerIrrelevant" value="true" />

<h2>Zoeken binnen een categorie</h2>

<table>
	<logic:iterate name="SearchForm" property="categorieList"  id="cat" indexId="idx" >
		<tr>
			<td>
				<a href="javascript:doSubmit('../do/initGetCategorieForSearch?page=ZKEXT&whichCategorie=<bean:write name="idx" />')" border="0"><html:img page="/images/button_categorienlijst.gif" border="0" /></a>
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
				<a href="javascript:doSubmit('../do/getCategorieForSearch?page=ZKEXT&Verwijderen=true&whichCategorie=<bean:write name="idx" />')" border="0"><html:img page="/images/Delete24.gif" border="0" /></a>
			</td>
		</tr>
	</logic:iterate>
	<tr>
		<c:if test="${empty idx}">
			<c:set var="idx" value="-1"></c:set>
		</c:if>
		<td>
			<a href="javascript:doSubmit('../do/initGetCategorieForSearch?page=ZKEXT&whichCategorie=<c:out value="${idx + 1}"></c:out>')" border="0"><html:img page="/images/button_categorienlijst.gif" border="0" /></a>
		</td>	
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
 		<td colspan="2"><b>Waar?</b></td>
	</tr>	
    <tr>
        <td colspan="2">Taalvragen <html:checkbox property="alleTV"/> | Teksten <html:checkbox property="alleTXT"/></td>
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
    	<td colspan="2">
    		<html:image property="submit2" page="/images/button_zoeken.gif" />
    	</td>			
	</tr>
</table>

</html:form>

</body>
</html:html>















