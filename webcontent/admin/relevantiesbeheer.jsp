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
                window.location.href = 'initRelevantiesBeheer?id=' + document.RelevantiesBeheerForm.elements['relevantie.id'].options[document.RelevantiesBeheerForm.elements['relevantie.id'].selectedIndex].value
            }
        </script>
        <% response.setHeader("Pragma", "No-cache");
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-cache");
        %>
    </head>

    <body>

    <bean:define name="RelevantiesBeheerForm" property="relevanties" type="java.util.ArrayList" id="relevanties"/>

    <logic:notEqual name="RelevantiesBeheerForm" property="relevantie.id" value="0">
        <h2>Aanpassen <i>Relevantie</i></h2>
    </logic:notEqual>
    <logic:equal name="RelevantiesBeheerForm" property="relevantie.id" value="0">
        <h2>Invoeren <i>Relevantie</i></h2>
    </logic:equal>

    <html:errors/>

    <html:form method="post" action="/relevantiesBeheer">
        <table cellspacing="5">
            <tr>
                <td colspan="4" align="center">
                    <html:select name="RelevantiesBeheerForm" property="relevantie.id" onchange="selecteer()">
                        <option value="0">Selecteer...</option>
                        <html:options collection="relevanties" property="id" labelProperty="omschrijving"/>
                    </html:select>
                    <hr/>
                </td>
            </tr>
            <tr>
                <td>
                    <html:img page="/images/label_status.gif"/>
                </td>
                <td>
                    <html:text name="RelevantiesBeheerForm" property="relevantie.omschrijving"/>
                </td>
                <td>
                    <html:img page="/images/label_actief.gif"/>
                </td>
                <td>
                    <html:checkbox name="RelevantiesBeheerForm" property="relevantie.actief"/>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="center">
                    <logic:notEqual name="RelevantiesBeheerForm" property="relevantie.id" value="0">
                        <html:image property="Wijzigen" value="Wijzigen" page="/images/button_wijzigen.gif" altKey="algemeen.relevantie.wijzigen"/>
                    </logic:notEqual>
                    <logic:equal name="RelevantiesBeheerForm" property="relevantie.id" value="0">
                        <html:image property="Toevoegen" value="Toevoegen" page="/images/button_toevoegen.gif" altKey="algemeen.relevantie.toevoegen"/>
                    </logic:equal>

                    <html:link action="/initRelevantiesBeheer" style="border:none">
                        <img src="<html:rewrite page='/images/button_nieuwe_relevantie.gif' />" border="0"
                             alt="<bean:message key="algemeen.relevantie.nieuw"/>"/>
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