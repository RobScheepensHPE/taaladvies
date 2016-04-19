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
            window.location.href = 'initDoelgroepenBeheer?id=' + document.DoelgroepenBeheerForm.elements['doelgroep.id'].options[document.DoelgroepenBeheerForm.elements['doelgroep.id'].selectedIndex].value
        }

        function doSubmit(doAction) {
            document.forms['DoelgroepenBeheerForm'].action = doAction;
            document.forms['DoelgroepenBeheerForm'].submit();
        }

    </script>
    <%
        response.setHeader("Pragma", "No-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
    %>
</head>

<body>

<bean:define name="DoelgroepenBeheerForm" property="doelgroepen" type="java.util.ArrayList" id="doelgroepen"/>
<bean:define name="DoelgroepenBeheerForm" property="doelgroepenAlgemeen" type="java.util.ArrayList"
             id="doelgroepenAlgemeen"/>

<logic:notEqual name="DoelgroepenBeheerForm" property="doelgroep.id" value="0">
    <h2>Aanpassen <i>Doelgroep</i></h2>
</logic:notEqual>
<logic:equal name="DoelgroepenBeheerForm" property="doelgroep.id" value="0">
    <h2>Invoeren <i>Doelgroep</i></h2>
</logic:equal>

<html:errors/>

<html:form method="post" action="/doelgroepenBeheer">
    <table cellspacing="5">
        <tr>
            <td colspan="4" align="center">
                <html:select name="DoelgroepenBeheerForm" property="doelgroep.doelgroepAlgemeen.id"
                             onchange="javascript:doSubmit('../do/doelgroepenBeheer?Button=Change1')">
                    <option value="0">Selecteer...</option>
                    <html:options collection="doelgroepenAlgemeen" property="id" labelProperty="omschrijving"/>
                </html:select>
                <html:select name="DoelgroepenBeheerForm" property="doelgroep.id"
                             onchange="javascript:doSubmit('../do/doelgroepenBeheer?Button=Change2')">
                    <option value="0">Selecteer...</option>
                    <html:options collection="doelgroepen" property="id" labelProperty="omschrijving"/>
                </html:select>
                <hr/>
            </td>
        </tr>
        <tr>
            <td>
                <html:img page="/images/label_doelgroep.gif"/>
            </td>
            <td>
                <html:text name="DoelgroepenBeheerForm" property="doelgroep.omschrijving"/>
            </td>
            <td>
                <html:img page="/images/label_doelgroep_algemeen.gif"/>
            </td>
            <td>
                <html:select name="DoelgroepenBeheerForm" property="doelgroep.parentId">
                    <html:options collection="doelgroepenAlgemeen" property="id" labelProperty="omschrijving"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td>
                <html:img page="/images/label_actief.gif"/>
            </td>
            <td colspan="3">
                <html:checkbox name="DoelgroepenBeheerForm" property="doelgroep.actief"/>
            </td>
        </tr>
        <tr>
            <td colspan="4" align="center">
                <logic:notEqual name="DoelgroepenBeheerForm" property="doelgroep.id" value="0">
                    <html:image property="Wijzigen" value="Wijzigen" page="/images/button_wijzigen.gif"
                                altKey="algemeen.doelgroep.wijzigen"/>
                </logic:notEqual>
                <logic:equal name="DoelgroepenBeheerForm" property="doelgroep.id" value="0">
                    <html:image property="Toevoegen" value="Toevoegen" page="/images/button_toevoegen.gif"
                                altKey="algemeen.doelgroep.toevoegen"/>
                </logic:equal>

                <html:link action="/initDoelgroepenBeheer" style="border:none">
                    <img src="<html:rewrite page='/images/button_nieuwe_doelgroep.gif' />" border="0"
                         alt="<bean:message key="algemeen.doelgroep.nieuw"/>"/>
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