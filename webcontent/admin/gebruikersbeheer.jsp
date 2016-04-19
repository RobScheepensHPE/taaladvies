<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/SecurityTagLib.tld" prefix="security" %>
<security:isLoggedIn/>

<html:html locale="true">
<head>
    <title>Databank Taaladvies</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link href="<html:rewrite page='/style/taaladvies.css' />" rel="stylesheet" type="text/css">
    <link href="<html:rewrite page='/style/taaladvies.js' />" type="text/JavaScript"/>
    <script>

        function selecteer() {
            window.location.href = 'initGebruikersBeheer?id=' + document.GebruikersBeheerForm.elements['gebruiker.id'].options[document.GebruikersBeheerForm.elements['gebruiker.id'].selectedIndex].value
        }


    </script>
    <%
        response.setHeader("Pragma", "No-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
    %>
</head>

<body>

<bean:define name="GebruikersBeheerForm" property="gebruikers" type="java.util.ArrayList" id="gebruikers"/>
<bean:define name="GebruikersBeheerForm" property="domeinen" type="java.util.ArrayList" id="domeinen"/>
<bean:define name="GebruikersBeheerForm" property="toegangsrechten" type="java.util.ArrayList" id="toegangsrechten"/>

<logic:notEqual name="GebruikersBeheerForm" property="gebruiker.id" value="0">
    <h2>Aanpassen <i>Gebruiker</i></h2>
</logic:notEqual>
<logic:equal name="GebruikersBeheerForm" property="gebruiker.id" value="0">
    <h2>Invoeren <i>Gebruiker</i></h2>
</logic:equal>

<html:errors/>

<html:form method="post" action="/gebruikersBeheer">
<table cellspacing="5">
<tr>
    <td colspan="4" align="center">
        <html:select name="GebruikersBeheerForm" property="gebruiker.id" onchange="selecteer()">
            <option value="0">Selecteer...</option>
            <html:options collection="gebruikers" property="id" labelProperty="naam"/>
        </html:select>
        <hr/>
    </td>
</tr>
<tr>
    <td>
        <html:img page="/images/label_voornaam.gif"/>
    </td>
    <td>
        <html:text name="GebruikersBeheerForm" property="gebruiker.voornaam"/>
    </td>
    <td>
        <html:img page="/images/label_naam.gif"/>
    </td>
    <td>
        <html:text name="GebruikersBeheerForm" property="gebruiker.naam"/>
    </td>
</tr>
<tr>
    <td>
        <html:img page="/images/label_email.gif"/>
    </td>
    <td>
        <html:text name="GebruikersBeheerForm" property="gebruiker.email"/>
    </td>
    <td>
        <html:img page="/images/label_gebruikersnaam.gif"/>
    </td>
    <td>
        <html:text name="GebruikersBeheerForm" property="gebruiker.login"/>
    </td>
</tr>
<tr>
    <td>
        <html:img page="/images/label_geslacht.gif"/>
    </td>
    <td>
        <html:select name="GebruikersBeheerForm" property="gebruiker.geslacht">
            <option value="m"
                    <logic:present name="GebruikersBeheerForm" property="gebruiker.geslacht">
                        <logic:equal name="GebruikersBeheerForm" property="gebruiker.geslacht" value="m">
                            SELECTED
                        </logic:equal>
                    </logic:present>
                    >Man
            </option>
            <option value="f"
                    <logic:present name="GebruikersBeheerForm" property="gebruiker.geslacht">
                        <logic:equal name="GebruikersBeheerForm" property="gebruiker.geslacht" value="f">
                            SELECTED
                        </logic:equal>
                    </logic:present>
                    >Vrouw
            </option>
        </html:select>
    </td>
    <td>
        <html:img page="/images/label_domein.gif"/>
    </td>
    <td>
        <html:select name="GebruikersBeheerForm" property="gebruiker.domeinId">
            <option value="0">Niet relevant</option>
            <html:options collection="domeinen" property="id" labelProperty="omschrijving"/>
        </html:select>
    </td>
</tr>
<tr>
    <td>
        <html:img page="/images/label_profielen.gif"/>
    </td>
    <td>
        <html:select name="GebruikersBeheerForm" property="gebruiker.toegangsrechten" multiple="3" size="3">
            <html:options collection="toegangsrechten" property="id" labelProperty="omschrijving"/>
        </html:select>
    </td>
    <td>
        <html:img page="/images/label_actief.gif"/>
    </td>
    <td>
        <html:checkbox name="GebruikersBeheerForm" property="gebruiker.actief"/>
    </td>
</tr>
<tr>
    <td colspan="4" align="center">
        <logic:notEqual name="GebruikersBeheerForm" property="gebruiker.id" value="0">
            <!--originele knop moet vervangen worden door: -->
            <html:image property="Wijzigen" value="Wijzigen" page="/images/button_wijzigen.gif"
                        altKey="algemeen.gebruiker.wijzigen"/>
        </logic:notEqual>
        <logic:equal name="GebruikersBeheerForm" property="gebruiker.id" value="0">
            <!--originele knop moet vervangen worden door: -->
            <html:image property="Toevoegen" value="Toevoegen" page="/images/button_toevoegen.gif"
                        altKey="algemeen.gebruiker.toevoegen"/>
        </logic:equal>

        <html:link action="/initGebruikersBeheer" style="border:none">
            <img src="<html:rewrite page='/images/button_nieuwe_gebruiker.gif' />" border="0"
                 alt="<bean:message key="algemeen.gebruiker.nieuw"/>"/>
        </html:link>
        <html:link forward="beheerMenu" style="border:none">
            <img src="<html:rewrite page='/images/button_menu.gif'/>" border="0"
                 alt="<bean:message key="algemeen.menu"/>"/>
        </html:link>
    </td>
</tr>
</table>
</html:form>
</body>
</html:html>
