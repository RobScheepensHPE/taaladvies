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
                window.location.href = 'initBibliografienBeheer?id=' + document.BibliografienBeheerForm.elements['bibliografie.id'].options[document.BibliografienBeheerForm.elements['bibliografie.id'].selectedIndex].value
            }
        </script>
        <%
            response.setHeader("Pragma", "No-cache");
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-cache");
        %>
    </head>

    <body>

    <bean:define name="BibliografienBeheerForm" property="bibliografien" type="java.util.ArrayList" id="bibliografien"/>

    <logic:notEqual name="BibliografienBeheerForm" property="bibliografie.id" value="0">
        <h2>Aanpassen <i>Bibliografische categorie</i></h2>
    </logic:notEqual>
    <logic:equal name="BibliografienBeheerForm" property="bibliografie.id" value="0">
        <h2>Invoeren <i>Bibliografische categorie</i></h2>
    </logic:equal>

    <html:errors/>

    <html:form method="post" action="/bibliografienBeheer">
        <table cellspacing="5">
            <tr>
                <td colspan="4" align="center">
                    <html:select name="BibliografienBeheerForm" property="bibliografie.id" onchange="selecteer()">
                        <option value="0">Selecteer...</option>
                        <html:options collection="bibliografien" property="id" labelProperty="omschrijving"/>
                    </html:select>
                    <hr/>
                </td>
            </tr>
            <tr>
                <td>
                    <html:img page="/images/label_bibliografische_categorie.gif"/>
                </td>
                <td>
                    <html:text name="BibliografienBeheerForm" property="bibliografie.omschrijving"/>
                </td>
                <td>
                    <html:img page="/images/label_actief.gif"/>
                </td>
                <td>
                    <html:checkbox name="BibliografienBeheerForm" property="bibliografie.actief"/>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="center">
                    <logic:notEqual name="BibliografienBeheerForm" property="bibliografie.id" value="0">
                        <html:image property="Wijzigen" value="Wijzigen" page="/images/button_wijzigen.gif" altKey="algemeen.bibliografie.wijzigen"/>
                    </logic:notEqual>
                    <logic:equal name="BibliografienBeheerForm" property="bibliografie.id" value="0">
                        <html:image property="Toevoegen" value="Toevoegen" page="/images/button_toevoegen.gif" altKey="algemeen.bibliografie.toevoegen"/>
                    </logic:equal>

                    <html:link action="/initBibliografienBeheer" style="border:none">
                        <img src="<html:rewrite page='/images/button_nieuwe_bibliografische_categorie.gif' />"
                             border="0"
                             alt="<bean:message key="algemeen.bibliografie.nieuw"/>"/>
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