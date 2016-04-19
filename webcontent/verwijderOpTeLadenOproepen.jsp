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
</head>

<bean:define name="Gebruiker" id="gebruiker" />
<bean:define name="Toegangsrechten" type="java.util.ArrayList" id="toegangsrechtenbean" />

<body>



<table class="borderbottom">
	<tr>
		<td class="h2style">
			Verwijder op te laden oproepen.
		</td>	
	</tr>				
</table>
<br/>

<table border="0" cellspacing="5" align="center">  
	<tr>
		<th>Deze pagina geeft u de mogelijkheid om ids uit de queue voor de taalunie te halen</th>
	</tr>
	<logic:iterate id="toegangsrechten" name="toegangsrechtenbean">
	<tr>
		<td align="center">
			<logic:equal name="toegangsrechten" property="omschrijving" value="Beheerder">
				<html:errors /><br />
				<html:form action="/verwijderOpTeLadenOproepen">
				Geef een id op die verwijderd moet worden:<br />
				<input type="text" size="5" name="id" /><br />
				<html:image property="submit" page="/images/button_verwijder.gif" />
				</html:form>
			</logic:equal>
		</td>
	</tr>
	</logic:iterate>
</body>
</html:html>
