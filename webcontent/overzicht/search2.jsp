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


</head>

<body>

<bean:define name="Search2Form" property="gemeenten" type="java.util.ArrayList" id="gemeenten" />
<bean:define name="Search2Form" property="landen" type="java.util.ArrayList" id="landen" />
<bean:define name="Search2Form" property="media" type="java.util.ArrayList" id="media" />
<bean:define name="Search2Form" property="herkomsten" type="java.util.ArrayList" id="herkomsten" />


<table width="80%" border="0">
	<tr>
		<th><html:link page="/do/initEigenTaalvragen"><html:img page="/images/link_inbehandeling.gif" border="0" /></html:link></th>
		<th><html:link page="/do/initOproep_TaalvragenInvoer"><html:img page="/images/link_nieuweoproeptaalvragen.gif" border="0" /></html:link></th>
		<th><html:link page="/do/initOproep_TekstInvoer"><html:img page="/images/link_nieuweoproeptekst.gif" border="0" /></html:link></th>
		<th><html:link page="/do/initZoeken"><html:img page="/images/button_zoek.gif" border="0" /></html:link></th>
		<th><html:link page="/profielen.jsp"><html:img page="/images/button_profielen.gif" border="0" /></html:link></th>
	</tr>
</table>
<br>

<h2>Zoekfunctie administratief en op oproepnummer</h2>

<html:errors />

<html:form method="post" action="/search2">
	

				<table cellspacing="5">					
					<tr>
						<th colspan="2" align="left">1. Zoeken op oproepnummer</th>
					</tr>
					<tr>
						<td>Zoek op oproepnummer: </td>
						<td>
							<input type="text" name="oproep.id" value="" />
						</td>
					</tr>
					<tr>
						<td colspan="2"><html:image property="Zoek" value="Zoek" page="/images/button_zoeken.gif" /></td>
					</tr>
				</table>

	

				<table cellspacing="5">
					<tr>
						<th colspan="4" align="left">2. Zoeken binnen administratieve gegevens</th>
					</tr>
                    <tr>
                        <td>Eigen taalvragen <html:checkbox property="eigenTV" /></td>
                        <td>Andere taalvragen <html:checkbox property="alleTV" /></td>
                        <td>Eigen teksten <html:checkbox property="eigenTXT" /></td>
                        <td>Andere teksten <html:checkbox property="alleTXT" /></td>
                    </tr>
					<tr>
						<td>Achternaam: </td>
						<td><html:text name="Search2Form" property="oproep.naam" /></td>
						<td>Voornaam: </td>
						<td><html:text name="Search2Form" property="oproep.voornaam" /></td>
					</tr>
					<tr>
						<td>Organisatie: </td>
						<td colspan="3"><html:text name="Search2Form" property="oproep.organisatie" /></td>
					</tr>
					<tr>
						<td>Straat: </td>
						<td><html:text name="Search2Form" property="oproep.straat" /></td>
						<td>Postcode: </td>
						<td><html:text name="Search2Form" property="oproep.gemeente.post" /></td>
					</tr>
					<tr>
						<td>Gemeente: </td>
						<td>	
							<html:select name="Search2Form" property="oproep.gemeenteId">
								<option value="0">Selecteer...</option>
								<html:options collection="gemeenten" property="id" labelProperty="omschrijving"/>
							</html:select>						
						</td>
						<td>Land: </td>
						<td>
							<html:select name="Search2Form" property="oproep.gemeente.parentId">
								<option value="0">Selecteer...</option>
								<html:options collection="landen" property="id" labelProperty="omschrijving" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td>Medium in: </td>
						<td>
							<html:select name="Search2Form" property="oproep.mediumId">
								<option value="0" SELECTED>Selecteer...</option>
								<html:options collection="media" property="id" labelProperty="omschrijving" />
							</html:select>
						</td>
						<td>Medium uit: </td>
						<td>	
							<html:select name="Search2Form" property="oproep.distributie.mediumId">
								<option value="0" SELECTED>Selecteer...</option>
								<html:options collection="media" property="id" labelProperty="omschrijving" />
							</html:select>
						</td>					
					</tr>
					<tr>
						<td>Herkomst: </td>
						<td colspan="3">
							<html:select name="Search2Form" property="oproep.herkomstId">
								<option value="0" SELECTED>Selecteer...</option>
								<html:options collection="herkomsten" property="id" labelProperty="omschrijving" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td>Telefoon: </td>
						<td><html:text name="Search2Form" property="oproep.telefoon" />
						<td>Fax: </td>
						<td><html:text name="Search2Form" property="oproep.fax" />
					</tr>
					<tr>
						<td>Zoek vanaf datum: </td>
						<td>
							<html:text name="Search2Form" property="datum1_DD" size="2" maxlength="2" />
							<html:text name="Search2Form" property="datum1_MM" size="2" maxlength="2" />
							<html:text name="Search2Form" property="datum1_YYYY" size="4" maxlength="4" />
						</td>
						<td>tot en met datum: </td>
						<td>
							<html:text name="Search2Form" property="datum2_DD" size="2" maxlength="2" />
							<html:text name="Search2Form" property="datum2_MM" size="2" maxlength="2" />
							<html:text name="Search2Form" property="datum2_YYYY" size="4" maxlength="4" />
						</td>
					</tr>
					<tr>
						<td>Zoek op datum: </td>
						<td colspan="3">
							<html:text name="Search2Form" property="datum3_DD" size="2" maxlength="2" />
							<html:text name="Search2Form" property="datum3_MM" size="2" maxlength="2" />
							<html:text name="Search2Form" property="datum3_YYYY" size="4" maxlength="4" />
						</td>
					</tr>
					<tr>
						<td colspan="4"><html:image property="Zoek2" value="Zoek2" page="/images/button_zoeken.gif" /></td>
					</tr>												
				</table>

</html:form>
</body>
</html:html>