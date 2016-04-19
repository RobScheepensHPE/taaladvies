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
                window.location.href = 'initMediaBeheer?id=' + document.MediaBeheerForm.elements['medium.id'].options[document.MediaBeheerForm.elements['medium.id'].selectedIndex].value
            }


        </script>
        <% response.setHeader("Pragma", "No-cache");
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-cache");
        %>
    </head>

    <body>

    <bean:define name="MediaBeheerForm" property="media" type="java.util.ArrayList" id="media"/>

    <logic:notEqual name="MediaBeheerForm" property="medium.id" value="0">
        <h2>Aanpassen <i>Medium</i></h2>
    </logic:notEqual>
    <logic:equal name="MediaBeheerForm" property="medium.id" value="0">
        <h2>Invoeren <i>Medium</i></h2>
    </logic:equal>

    <html:errors/>

    <html:form method="post" action="/mediaBeheer">
        <table cellspacing="5">
            <tr>
                <td colspan="4" align="center">
                    <html:select name="MediaBeheerForm" property="medium.id" onchange="selecteer()">
                        <option value="0">Selecteer...</option>
                        <html:options collection="media" property="id" labelProperty="omschrijving"/>
                    </html:select>
                    <hr/>
                </td>
            </tr>
            <tr>
                <td>
                    <html:img page="/images/label_medium.gif"/>
                </td>
                <td>
                    <html:text name="MediaBeheerForm" property="medium.omschrijving"/>
                </td>
                <td>
                    <html:img page="/images/label_actief.gif"/>
                </td>
                <td>
                    <html:checkbox name="MediaBeheerForm" property="medium.actief"/>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="center">
                    <logic:notEqual name="MediaBeheerForm" property="medium.id" value="0">
                        <html:image property="Wijzigen" value="Wijzigen" page="/images/button_wijzigen.gif" altKey="algemeen.mediabeheer.wijzigen"/>
                    </logic:notEqual>
                    <logic:equal name="MediaBeheerForm" property="medium.id" value="0">
                        <html:image property="Toevoegen" value="Toevoegen" page="/images/button_toevoegen.gif" altKey="algemeen.mediabeheer.toevoegen"/>
                    </logic:equal>                                        
                               
                    <html:link action="/initMediaBeheer" style="border:none">
                        <img src="<html:rewrite page='/images/button_nieuw_medium.gif' />" border="0"
                             alt="<bean:message key="algemeen.mediabeheer.nieuw"/>"/>
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