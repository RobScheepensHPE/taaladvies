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
			document.forms['KlantenGegevensInvoerForm'].action = doAction;
 		   	document.forms['KlantenGegevensInvoerForm'].submit();
		}
		
		
	</script>
	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>		
</head>

<body>

<bean:define name="KlantenGegevensInvoerForm" property="doelgroepenAlgemeen" type="java.util.ArrayList" id="doelgroepenAlgemeen" />
<bean:define name="KlantenGegevensInvoerForm" property="doelgroepen" type="java.util.ArrayList" id="doelgroepen" />
<bean:define name="KlantenGegevensInvoerForm" property="landen" type="java.util.ArrayList" id="landen" />
<bean:define name="KlantenGegevensInvoerForm" property="gemeenten" type="java.util.ArrayList" id="gemeenten" />
<bean:define name="KlantenGegevensInvoerForm" property="domeinen" type="java.util.ArrayList" id="domeinen" />
<bean:define name="KlantenGegevensInvoerForm" property="herkomsten" type="java.util.ArrayList" id="herkomsten" />

<h2>BEHANDELEN Klant</h2>

<html:errors />

<html:form method="post" action="/klantenGegevensInvoer">
	
	<html:hidden name="KlantenGegevensInvoerForm" property="oproep.id" />
	<html:hidden name="KlantenGegevensInvoerForm" property="oproep.type" />

	<table cellspacing="5">
		<tr>
			<td colspan="4">
				<a class="bigger" href="javascript:doSubmit('../do/klantenGegevensInvoer?Button=Overzicht')" border="0">
					<bean:write name="KlantenGegevensInvoerForm" property="oproep.id" /> 
				</a> 
				<bean:write name="KlantenGegevensInvoerForm" property="oproep.voornaam" /> 
				<bean:write name="KlantenGegevensInvoerForm" property="oproep.naam" />
			</td>
		</tr>
		<tr>
			<td><html:img page="/images/label_voornaam.gif" /></td>
			<td><html:text name="KlantenGegevensInvoerForm" property="oproep.voornaam" /></td>	
			<td><html:img page="/images/label_naam.gif" /></td>
			<td><html:text name="KlantenGegevensInvoerForm" property="oproep.naam" /></td>
		</tr>
		<tr>	
			<td><html:img page="/images/label_geslacht.gif" /></td>
			<td>
				<html:select name="KlantenGegevensInvoerForm" property="oproep.geslacht">
					<option value="o" <logic:equal name="KlantenGegevensInvoerForm" property="oproep.geslacht" value="o">SELECTED</logic:equal>>Onbekend</option>
					<option value="m" <logic:equal name="KlantenGegevensInvoerForm" property="oproep.geslacht" value="m">SELECTED</logic:equal>>Man</option>
					<option value="v" <logic:equal name="KlantenGegevensInvoerForm" property="oproep.geslacht" value="v">SELECTED</logic:equal>>Vrouw</option>
				</html:select>
			</td>
			<td><html:img page="/images/label_functie.gif" /></td>
			<td><html:text name="KlantenGegevensInvoerForm" property="oproep.functie" /></td>				
		</tr>
		<tr>
			<td><html:img page="/images/label_telefoon.gif" /></td>
			<td><html:text name="KlantenGegevensInvoerForm" property="oproep.telefoon" /></td>		
			<td><html:img page="/images/label_email.gif" /></td>
			<td colspan="3"><html:text name="KlantenGegevensInvoerForm" property="oproep.email" /></td>
		</tr>
		<tr>
			<td><html:img page="/images/label_fax.gif" /></td>
			<td colspan="3"><html:text name="KlantenGegevensInvoerForm" property="oproep.fax" /></td>
		</tr>
		<tr><td colspan="4"><hr /></td></tr>
		<tr>
			<td><html:img page="/images/label_domein.gif" /></td>
			<td colspan="3">							
				<html:select name="KlantenGegevensInvoerForm" property="oproep.domeinId">
					<option value="0">Selecteer...</option>
					<html:options collection="domeinen" property="id" labelProperty="omschrijving" />
				</html:select>
			</td>
		</tr>
		<tr>
			<td><html:img page="/images/label_herkomst.gif" /></td>
			<td>
				<html:select name="KlantenGegevensInvoerForm" property="oproep.herkomstId">
					<option value="0">Selecteer...</option>
					<html:options collection="herkomsten" property="id" labelProperty="omschrijving" />
				</html:select>
			</td>
			<td><html:img page="/images/label_herkomstnr.gif" /></td>		
			<td><html:text name="KlantenGegevensInvoerForm" property="oproep.herkomstnummer" /></td>
		</tr>
		<tr>
			<td><html:img page="/images/label_doelgroep_alg.gif" /></td>
			<td>										
				<html:select name="KlantenGegevensInvoerForm" property="oproep.doelgroep.parentId" onchange="doSubmit('../do/klantenGegevensInvoer?Button=Change2')">
					<option value="0">Selecteer...</option>
					<html:options collection="doelgroepenAlgemeen" property="id" labelProperty="omschrijving" />
				</html:select>			
			</td>					
			<td><html:img page="/images/label_doelgroep.gif" /></td>
			<td>
				<html:select name="KlantenGegevensInvoerForm" property="oproep.doelgroepId">
					<option value="0">Selecteer...</option>
					<html:options collection="doelgroepen" property="id" labelProperty="omschrijving" />
				</html:select>
			</td>
		</tr>
		<tr><td colspan="4"><hr /></td></tr>
		<tr>			
			<td><html:img page="/images/label_organisatie.gif" /></td>
			<td colspan="3"><html:text name="KlantenGegevensInvoerForm" property="oproep.organisatie" /></td>						
		</tr>
		<tr>	
			<td><html:img page="/images/label_straat.gif" /></td>
			<td><html:text name="KlantenGegevensInvoerForm" property="oproep.straat" /></td>			
			<td><html:img page="/images/label_huisnummer.gif" /></td>
			<td><html:text name="KlantenGegevensInvoerForm" property="oproep.huisnummer" /></td>			
		</tr>
		<tr>
			<td><html:img page="/images/label_busnummer.gif" /></td>
			<td><html:text name="KlantenGegevensInvoerForm" property="oproep.busnummer" /></td>
			<td><html:img page="/images/label_land.gif" /></td>
			<td>
				<html:select name="KlantenGegevensInvoerForm" property="oproep.gemeente.parentId" >
					<html:options collection="landen" property="id" labelProperty="omschrijving" />
				</html:select>
			</td>
		</tr>
		<tr>					
			<td><html:img page="/images/label_postnummer.gif" /></td>
			<td><html:text name="KlantenGegevensInvoerForm" property="oproep.gemeente.post" /></td>						
			<td><html:img page="/images/label_gemeente.gif" /></td>
			<td>
				<html:text name="KlantenGegevensInvoerForm" property="oproep.gemeente.omschrijving" />
				<html:image property="Zoek Gemeente" value="Zoek Gemeente" page="/images/button_zoek_gemeente.gif" />
			</td>
		</tr>
		<tr>				
			<td><html:img page="/images/label_select_gemeente.gif" /></td>
			<td colspan="3">
				<html:select name="KlantenGegevensInvoerForm" property="oproep.gemeenteId">
					<option value="0">Selecteer...</option>
					<html:options collection="gemeenten" property="id" labelProperty="post_omschrijving" />
				</html:select>
			</td>
		</tr>
		<tr><td colspan="4"><hr /></td></tr>
		<tr>
			<td><html:img page="/images/label_opmerking.gif" /></td>
			<td colspan="3"><html:textarea name="KlantenGegevensInvoerForm" property="oproep.opmerking" cols="50" rows="5" /></td>		
		</tr>
	</table>		
</html:form>
</body>
</html:html>