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
        function toeeigenen(id, taalvraagid) {
            window.location.href = '../do/toeeigenen?id=' + id + '&taalvraagid=' + taalvraagid + '&edit=true';
        }
    </script>

    <% response.setHeader("Pragma", "No-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
    %>

</head>
<body>

<h2>BEKIJKEN Taalvraag</h2>

<html:form method="post" action="/taalvragenInvoer" onsubmit="return fnOnSubmit()">

<table cellspacing="5">
<tr>
    <td colspan="2">
        <logic:equal name="TaalvragenInvoerForm" property="taalvraag.oproep.afgehandeld" value="false">
        <a href="../do/initOproep_TaalvragenInvoer?id=<bean:write name="TaalvragenInvoerForm" property="oproep.id" />&ro=true&binnengekomen=true">
            </logic:equal>
            <logic:equal name="TaalvragenInvoerForm" property="adm" value="true">
            <a href="../do/initOproep_TaalvragenInvoer?id=<bean:write name="TaalvragenInvoerForm" property="oproep.id" />&ro=true&adm=true">
                </logic:equal>
                Oproep
                <bean:write name="TaalvragenInvoerForm" property="oproep.id"/>
                <logic:equal name="TaalvragenInvoerForm" property="taalvraag.oproep.afgehandeld" value="false">
            </a>
            </logic:equal>
            <logic:equal name="TaalvragenInvoerForm" property="adm" value="true">
        </a>
        </logic:equal>
    </td>
    <td>
        <html:img page="/images/label_telefoon.gif"/>
    </td>
    <td>
        <bean:write name="TaalvragenInvoerForm" property="oproep.telefoon"/>
        <html:hidden name="TaalvragenInvoerForm" property="oproep.id"/>
    </td>
</tr>
<tr>
    <td colspan="4">
        <hr/>
    </td>
</tr>

<tr>
    <td>
        <html:img page="/images/label_vraagnummer.gif"/>
    </td>
    <td colspan="3">
        <bean:write name="TaalvragenInvoerForm" property="taalvraag.volgnummer"/>
    </td>
</tr>
<tr>
    <td valign="top">
        <html:img page="/images/label_titel.gif"/>
    </td>
    <td colspan="3">
        <bean:write name="TaalvragenInvoerForm" property="taalvraag.titel" filter="false"/>
    </td>
</tr>
<tr>
    <td valign="top">
        <html:img page="/images/label_vraag.gif"/>
    </td>
    <td colspan="3">
        <bean:write name="TaalvragenInvoerForm" property="taalvraag.vraag" filter="false"/>
    </td>
</tr>
<tr>
    <td valign="top">
        <html:img page="/images/label_antwoord.gif"/>
    </td>
    <td colspan="3">
        <bean:write name="TaalvragenInvoerForm" property="taalvraag.antwoordHTML" filter="false"/>
    </td>
</tr>
<logic:present name="TaalvragenInvoerForm" property="taalvraag.toelichting">
    <logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.toelichting" value="">
        <tr>
            <td valign="top">
                <html:img page="/images/label_toelichting.gif"/>
            </td>
            <td colspan="3">
                <bean:write name="TaalvragenInvoerForm" property="taalvraag.toelichtingHTML" filter="false"/>
            </td>
        </tr>
    </logic:notEqual>
</logic:present>
<logic:present name="TaalvragenInvoerForm" property="taalvraag.bijzonderheid">
    <logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.bijzonderheid" value="">
        <tr>
            <td valign="top">
                <html:img page="/images/label_bijzonderheid.gif"/>
            </td>
            <td colspan="3">
                <bean:write name="TaalvragenInvoerForm" property="taalvraag.bijzonderheidHTML" filter="false"/>
            </td>
        </tr>
    </logic:notEqual>
</logic:present>
<logic:equal name="TaalvragenInvoerForm" property="taalvraag.oproep.afgehandeld" value="true">
    <tr>
        <td colspan="4" align="center">
            <logic:equal name="TaalvragenInvoerForm" property="adm" value="true">
                <a href="../overzicht/search2result.jsp" border="0">
                    <html:img page="/images/button_terug_naar_resultaat.gif" border="0"/>
                </a>
                <a href="../overzicht/search2.jsp" border="0">
                    <html:img page="/images/button_verfijn_zoekopdracht.gif" border="0"/>
                </a>
                <html:link action="/initSearch2" style="border:0">
                    <html:img page="/images/button_nieuwe_zoekopdracht.gif" border="0"/>
                </html:link>
            </logic:equal>
            <logic:equal name="TaalvragenInvoerForm" property="adm" value="false">
                <a href="../overzicht/resultaat.jsp" border="0">
                    <html:img page="/images/button_terug_naar_resultaat.gif" border="0"/>
                </a>
                <a href="../overzicht/zoeken.jsp" border="0">
                    <html:img page="/images/button_verfijn_zoekopdracht.gif" border="0"/>
                </a>
                <html:link action="/initZoeken" style="border:0">
                    <html:img page="/images/button_nieuwe_zoekopdracht.gif" border="0"/>
                </html:link>
            </logic:equal>
        </td>
    </tr>
</logic:equal>
<tr>
    <td colspan="4">
        <hr/>
    </td>
</tr>
<tr>
    <td colspan="4" align="center">
        <table cellspacing="5">
            <tr>
                <th colspan="2">Gekoppelde teksten</th>
            </tr>
            <logic:present name="TaalvragenInvoerForm" property="taalvraag.teksten">
                <logic:iterate name="TaalvragenInvoerForm" property="taalvraag.teksten" id="teksteni">
                    <tr>
                        <td>
                            <bean:write name="teksteni" property="titel"/>
                        </td>
                    </tr>
                </logic:iterate>
            </logic:present>
        </table>
    </td>
</tr>
<tr>
    <td colspan="4" align="center">
        <a href="javascript:toeeigenen(<bean:write name="TaalvragenInvoerForm" property="taalvraag.oproepId"  />,
							<bean:write name="TaalvragenInvoerForm" property="taalvraag.id"  />)" border="0">
            <img src="<html:rewrite page='/images/button_toeeigenen.gif' />" border="0" alt="Eigen Toe Knop"/>
        </a>
    </td>
</tr>
</table>


</html:form>
</body>
</html:html>