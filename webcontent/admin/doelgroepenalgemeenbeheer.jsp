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
            window.location.href = 'initDoelgroepenAlgemeenBeheer?id=' + document.DoelgroepenAlgemeenBeheerForm.elements['doelgroepAlgemeen.id'].options[document.DoelgroepenAlgemeenBeheerForm.elements['doelgroepAlgemeen.id'].selectedIndex].value
        }

        function doSubmit(doAction) {
            document.forms['DoelgroepenAlgemeenBeheerForm'].action = doAction;
            document.forms['DoelgroepenAlgemeenBeheerForm'].submit();
        }

    </script>
    <%
        response.setHeader("Pragma", "No-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
    %>
</head>

<body>

<bean:define name="DoelgroepenAlgemeenBeheerForm" property="doelgroepenAlgemeen" type="java.util.ArrayList"
             id="doelgroepenAlgemeen"/>
<bean:define name="DoelgroepenAlgemeenBeheerForm" property="domeinen" type="java.util.ArrayList" id="domeinen"/>

<logic:notEqual name="DoelgroepenAlgemeenBeheerForm" property="doelgroepAlgemeen.id" value="0">
    <h2>Aanpassen <i>Doelgroep Algemeen</i></h2>
</logic:notEqual>
<logic:equal name="DoelgroepenAlgemeenBeheerForm" property="doelgroepAlgemeen.id" value="0">
    <h2>Invoeren <i>Doelgroep Algemeen</i></h2>
</logic:equal>

<html:errors/>

<html:form method="post" action="/doelgroepenAlgemeenBeheer">
    <table cellspacing="5">
        <tr>
            <td colspan="4" align="center">
                <html:select name="DoelgroepenAlgemeenBeheerForm" property="doelgroepAlgemeen.domein.id"
                             onchange="javascript:doSubmit('../do/doelgroepenAlgemeenBeheer?Button=Change1')">
                    <option value="0">Selecteer...</option>
                    <html:options collection="domeinen" property="id" labelProperty="omschrijving"/>
                </html:select>
                <html:select name="DoelgroepenAlgemeenBeheerForm" property="doelgroepAlgemeen.id"
                             onchange="javascript:doSubmit('../do/doelgroepenAlgemeenBeheer?Button=Change2')">
                    <option value="0">Selecteer...</option>
                    <html:options collection="doelgroepenAlgemeen" property="id" labelProperty="omschrijving"/>
                </html:select>
                <hr/>
            </td>
        </tr>
        <tr>
            <td>
                <html:img page="/images/label_doelgroep.gif"/>
            </td>
            <td>
                <html:text name="DoelgroepenAlgemeenBeheerForm" property="doelgroepAlgemeen.omschrijving"/>
            </td>
            <td>
                <html:img page="/images/label_domein.gif"/>
            </td>
            <td>
                <html:select name="DoelgroepenAlgemeenBeheerForm" property="doelgroepAlgemeen.parentId">
                    <html:options collection="domeinen" property="id" labelProperty="omschrijving"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td>
                <html:img page="/images/label_actief.gif"/>
            </td>
            <td colspan="3">
                <html:checkbox name="DoelgroepenAlgemeenBeheerForm" property="doelgroepAlgemeen.actief"/>
            </td>
        </tr>
        <tr>
            <td colspan="4" align="center">
                <logic:notEqual name="DoelgroepenAlgemeenBeheerForm" property="doelgroepAlgemeen.id" value="0">
                    <html:image property="Wijzigen" value="Wijzigen" page="/images/button_wijzigen.gif"
                                altKey="algemeen.doelgroepenalgemeen.wijzigen"/>
                </logic:notEqual>
                <logic:equal name="DoelgroepenAlgemeenBeheerForm" property="doelgroepAlgemeen.id" value="0">
                    <html:image property="Toevoegen" value="Toevoegen" page="/images/button_toevoegen.gif"
                                altKey="algemeen.doelgroepenalgemeen.toevoegen"/>
                </logic:equal>

                <html:link action="/initDoelgroepenAlgemeenBeheer" style="border:none">
                    <img src="<html:rewrite page='/images/button_nieuwe_doelgroep_algemeen.gif' />" border="0"
                         alt="<bean:message key="algemeen.doelgroepenalgemeen.nieuw"/>"/>
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