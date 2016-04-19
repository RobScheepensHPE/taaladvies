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
	
</head>

<bean:define name="Gebruiker" id="gebruiker" />
<bean:define name="Toegangsrechten" type="java.util.ArrayList" id="toegangsrechtenbean" />

<body>



<table class="borderbottom">
	<tr>
		<td class="h2style">
			Kies profiel
		</td>
		
		<td class="modus" align="right">
			<bean:message key="algemeen.modus"/>:			
			<input type="radio" name="useEopro" value="true" onclick="javascript:location.href='<html:rewrite page='/do/login?Button=EditeerModus&useEopro=true' />';" <logic:equal name="eopro" value="true">CHECKED</logic:equal>/><bean:message key="algemeen.metopmaak"/>
			<input type="radio" name="useEopro" value="false" onclick="javascript:location.href='<html:rewrite page='/do/login?Button=EditeerModus&useEopro=false' />';" <logic:notEqual name="eopro" value="true">CHECKED</logic:notEqual>/><bean:message key="algemeen.eenvoudig"/>	
		</td>		
	</tr>				
</table>
<br/>

<table border="0" cellspacing="5" align="center">  
	<tr>
		<th>Profielen van <bean:write name="gebruiker" property="voornaam" /> <bean:write name="gebruiker" property="naam" />: </th>
	</tr>
	<logic:iterate id="toegangsrechten" name="toegangsrechtenbean">
	<tr>
		<td align="center">
			<logic:equal name="toegangsrechten" property="omschrijving" value="Beheerder">
				<html:link page="/admin/beheerdermenu.jsp">
					<%-- ORIGINAL <bean:write name="toegangsrechten" property="omschrijving" /> --%>
					<html:img page="/images/link_beheerder.gif" border="0"/>
				</html:link>
			</logic:equal>
			<logic:equal name="toegangsrechten" property="omschrijving" value="Taaladviseur">
				<html:link page="/do/initEigenTaalvragen">
					
					<html:img page="/images/link_taaladviseur.gif" border="0"/>
					<%-- ORIGINAL <bean:write name="toegangsrechten" property="omschrijving" /> --%>
				</html:link>				
			</logic:equal>
			<logic:equal name="toegangsrechten" property="omschrijving" value="Externe raadpleger">
				<html:link page="/do/initZoekenExtern">
					<html:img page="/images/link_externeraadgever.gif" border="0"/>
					<%-- <bean:write name="toegangsrechten" property="omschrijving" /> --%>
				</html:link>
			</logic:equal>
	</tr>
	
	</logic:iterate>

</table>
<div align="center">
	<html:link page="/do/logout">
		<html:img page="/images/button_uitloggen.gif" border="0"/>
	</html:link>
</div>
<br></br>

</body>
</html:html>
