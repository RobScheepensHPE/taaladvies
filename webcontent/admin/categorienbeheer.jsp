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
            window.location.href = 'initCategorienBeheer?id=' + document.CategorienBeheerForm.elements['categorie.id'].options[document.CategorienBeheerForm.elements['categorie.id'].selectedIndex].value
        }

    </script>
    <%
        response.setHeader("Pragma", "No-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
    %>

</head>

<body>

<bean:define name="CategorienBeheerForm" property="categorien" type="java.util.ArrayList" id="categorien"/>


<logic:notEqual name="CategorienBeheerForm" property="categorie.id" value="0">
    <h2>Aanpassen <i>Categorie</i></h2>
</logic:notEqual>
<logic:equal name="CategorienBeheerForm" property="categorie.id" value="0">
    <h2>Invoeren <i>Categorie</i></h2>
</logic:equal>

<html:errors/>

<html:form method="post" action="/categorienBeheer">
<table cellspacing="5">
<tr>
    <th colspan="2">
        <html:hidden name="CategorienBeheerForm" property="parentCategorie.id"/>
        <logic:notEqual name="CategorienBeheerForm"
                        property="categorie.superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id"
                        value="0">
            <bean:write name="CategorienBeheerForm"
                        property="categorie.superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.omschrijving"/>
            >
        </logic:notEqual>
        <logic:notEqual name="CategorienBeheerForm"
                        property="categorie.superCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
            <bean:write name="CategorienBeheerForm"
                        property="categorie.superCategorie.superCategorie.superCategorie.superCategorie.omschrijving"/>
            >
        </logic:notEqual>
        <logic:notEqual name="CategorienBeheerForm" property="categorie.superCategorie.superCategorie.superCategorie.id"
                        value="0">
            <bean:write name="CategorienBeheerForm"
                        property="categorie.superCategorie.superCategorie.superCategorie.omschrijving"/>
            >
        </logic:notEqual>
        <logic:notEqual name="CategorienBeheerForm" property="categorie.superCategorie.superCategorie.id" value="0">
            <bean:write name="CategorienBeheerForm" property="categorie.superCategorie.superCategorie.omschrijving"/>
            >
        </logic:notEqual>
        <logic:notEqual name="CategorienBeheerForm" property="categorie.superCategorie.id" value="0">
            <bean:write name="CategorienBeheerForm" property="categorie.superCategorie.omschrijving"/>
            >
        </logic:notEqual>
        <logic:notEqual name="CategorienBeheerForm" property="categorie.id" value="0">
            <bean:write name="CategorienBeheerForm" property="categorie.omschrijving"/>
        </logic:notEqual>
        <logic:equal name="CategorienBeheerForm" property="categorie.id" value="0">
            <logic:notEqual name="CategorienBeheerForm"
                            property="parentCategorie.superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id"
                            value="0">
                <bean:write name="CategorienBeheerForm"
                            property="parentCategorie.superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.omschrijving"/>
                >
            </logic:notEqual>
            <logic:notEqual name="CategorienBeheerForm"
                            property="parentCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id"
                            value="0">
                <bean:write name="CategorienBeheerForm"
                            property="parentCategorie.superCategorie.superCategorie.superCategorie.superCategorie.omschrijving"/>
                >
            </logic:notEqual>
            <logic:notEqual name="CategorienBeheerForm"
                            property="parentCategorie.superCategorie.superCategorie.superCategorie.id" value="0">
                <bean:write name="CategorienBeheerForm"
                            property="parentCategorie.superCategorie.superCategorie.superCategorie.omschrijving"/>
                >
            </logic:notEqual>
            <logic:notEqual name="CategorienBeheerForm" property="parentCategorie.superCategorie.superCategorie.id"
                            value="0">
                <bean:write name="CategorienBeheerForm"
                            property="parentCategorie.superCategorie.superCategorie.omschrijving"/>
                >
            </logic:notEqual>
            <logic:notEqual name="CategorienBeheerForm" property="parentCategorie.superCategorie.id" value="0">
                <bean:write name="CategorienBeheerForm" property="parentCategorie.superCategorie.omschrijving"/>
                >
            </logic:notEqual>
            <logic:notEqual name="CategorienBeheerForm" property="parentCategorie.id" value="0">
                <bean:write name="CategorienBeheerForm" property="parentCategorie.omschrijving"/>
            </logic:notEqual>
        </logic:equal>
    </th>
</tr>
<tr>
    <td>
        <html:select name="CategorienBeheerForm" property="categorie.id" onchange="selecteer()" multiple="25" size="25">
            <html:options collection="categorien" property="id" labelProperty="omschrijving"/>
        </html:select>
    </td>
    <td valign="top">
        <table>
            <tr>
                <td>
                    <html:img page="/images/label_categorie.gif"/>
                </td>
                <td>
                    <html:text name="CategorienBeheerForm" property="categorie.omschrijving"/>
                    <html:hidden name="CategorienBeheerForm" property="categorie.parentId"/>
                    <html:hidden name="CategorienBeheerForm" property="categorie.nummer"/>
                </td>
            </tr>
            <tr>
                <td>
                    <html:img page="/images/label_nummer.gif"/>
                </td>
                <logic:notEqual name="CategorienBeheerForm" property="categorie.id" value="0">
                    <html:hidden name="CategorienBeheerForm" property="categorie.parentNummer"/>
                    <td>
                        <bean:write name="CategorienBeheerForm" property="categorie.parentNummer"/>
                        <html:text name="CategorienBeheerForm" property="categorie.ownNummer"/>
                    </td>
                </logic:notEqual>
                <logic:equal name="CategorienBeheerForm" property="categorie.id" value="0">
                    <logic:notEqual name="CategorienBeheerForm" property="parentCategorie.id" value="0">
                        <td>
                            <bean:write name="CategorienBeheerForm" property="parentCategorie.nummer"/>
                            .
                            <html:text name="CategorienBeheerForm" property="categorie.ownNummer"/>
                        </td>
                    </logic:notEqual>
                    <logic:equal name="CategorienBeheerForm" property="parentCategorie.id" value="0">
                        <td>
                            <html:text name="CategorienBeheerForm" property="categorie.ownNummer"/>
                        </td>
                    </logic:equal>
                </logic:equal>

            </tr>
            <tr>
                <td>
                    <html:img page="/images/label_actief.gif"/>
                </td>
                <td>
                    <html:checkbox name="CategorienBeheerForm" property="categorie.actief"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <logic:notEqual name="CategorienBeheerForm" property="categorie.id" value="0">
                        <html:image property="Wijzigen" value="Wijzigen" page="/images/button_wijzigen.gif"/>
                    </logic:notEqual>
                    <logic:equal name="CategorienBeheerForm" property="categorie.id" value="0">
                        <html:image property="Toevoegen" value="Toevoegen" page="/images/button_toevoegen.gif"/>
                        <%--<html:image property="Toevoegen" value="Toevoegen" page="/images/button_toevoegen.gif" />--%>
                    </logic:equal>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <html:image property="Naar Subcategorie" value="Naar Subcategorie"
                                page="/images/button_niveaumin1.gif"/>
                        <%--<html:submit value="Naar Subcategorie" property="Button" />--%>
                    <html:image property="Naar Hoofdcategorie" value="Naar Hoofdcategorie"
                                page="/images/button_niveauplus1.gif"/>
                        <%--<html:submit value="Naar Hoofdcategorie" property="Button" />--%>
                </td>
                <tr>
                    <td colspan="2" align="center">

                        <html:link forward="beheerMenu" style="border:none">
                            <img src="<html:rewrite page='/images/button_menu.gif'/>" border="0"
                                 alt="<bean:message key="algemeen.menu"/>"/>
                        </html:link>

                        <logic:notEqual name="CategorienBeheerForm" property="categorie.id" value="0">                                                       
                            <html:link action="/initCategorienBeheer" paramName="CategorienBeheerForm"
                                       paramProperty="categorie.parentId" paramId="parentId"
                                       style="border:none">
                                <img src="<html:rewrite page='/images/button_nieuwe_categorie.gif' />" border="0"
                                     alt="<bean:message key="algemeen.categorie.nieuw"/>"/>
                            </html:link>
                        </logic:notEqual>

                        <logic:equal name="CategorienBeheerForm" property="categorie.id" value="0">
                            <html:link action="/initCategorienBeheer" paramName="CategorienBeheerForm"
                                       paramProperty="parentCategorie.id" paramId="parentId"
                                       style="border:none">
                                <img src="<html:rewrite page='/images/button_nieuwe_categorie.gif' />" border="0"
                                     alt="<bean:message key="algemeen.categorie.nieuw"/>"/>
                            </html:link>
                        </logic:equal>
                    </td>
                </tr>
            </tr>
        </table>
    </td>
</tr>
</table>
</html:form>
</body>
</html:html>