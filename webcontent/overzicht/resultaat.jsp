<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html" pageEncoding="iso-8859-1" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/iflogic.tld" prefix="iflogic" %>
<%@ taglib uri="/WEB-INF/SecurityTagLib.tld" prefix="security" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<security:isLoggedIn/>


<html:html locale="true">
<head>
    <title>Databank Taaladvies</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <!--<meta http-equiv="Content-Type" content="text/html">-->
    <link href="<html:rewrite page='/style/taaladvies.css' />" rel="stylesheet" type="text/css">
    <link href="<html:rewrite page='/style/taaladvies.js' />" type="text/JavaScript"/>
</head>

<body>
<table width="80%" border="0">
    <tr>
        <th>
            <html:link page="/do/initEigenTaalvragen">
                <html:img page="/images/link_inbehandeling.gif" border="0"/>
            </html:link>
        </th>
        <th>
            <html:link page="/do/initOproep_TaalvragenInvoer">
                <html:img page="/images/link_nieuweoproeptaalvragen.gif" border="0"/>
            </html:link>
        </th>
        <th>
            <html:link page="/do/initOproep_TekstInvoer">
                <html:img page="/images/link_nieuweoproeptekst.gif" border="0"/>
            </html:link>
        </th>
        <th>
            <html:link page="/do/initZoeken">
                <html:img page="/images/button_zoek.gif" border="0"/>
            </html:link>
        </th>
        <th>
            <html:link page="/profielen.jsp">
                <html:img page="/images/button_profielen.gif" border="0"/>
            </html:link>
        </th>
    </tr>
</table>
<br>

<h2>Zoekresultaten :</h2>

<html:errors/>
Op basis van uw trefwoorden en criteria werden de volgende gegevens gevonden :

<logic:present name="cacheTLV" scope="session">

<iflogic:if name="cacheTLV" property="maxCount" op="equal" value="0">
<iflogic:then>
    <table>
        <tr>
            <th>Er werden geen taalvragen gevonden die aan de zoekcriteria voldoen</th>
        </tr>
    </table>
</iflogic:then>
<iflogic:else>
<bean:define name="cacheTLV" property="records" id="taalvragen"/>
<table cellspacing="5" width="100%" align="center">
<tr>
    <th colspan="4">
        Taalvragen (Pagina
        <bean:write name="cacheTLV" property="currentCount"/>
        /
        <bean:write name="cacheTLV" property="pageCount"/>
        )
    </th>
</tr>
<tr bgcolor="#dddddd">
    <th align="left">Taalvraag</th>
    <th align="left">&nbsp;</th>
    <th align="left">Oproepdatum</th>
    <th align="left">Categorie&euml;n</th>
    <th>&nbsp;</th>
</tr>
<logic:iterate name="taalvragen" id="currentItem" type="be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag">
    <tr>
        <td width="35%" valign="top">
            <a href="../do/initTaalvragenInvoer?id=<bean:write name="currentItem" property="id" />&ro=true">
                <logic:notEqual name="currentItem" property="titel" value="">
                    <!--BUGFIX    2006-10-30    -->
                    <!--<bean:write name="currentItem" property="titelHTML" filter="true"/>-->
                    <bean:write name="currentItem" property="titel" filter="false"/>
                </logic:notEqual>
                <logic:equal name="currentItem" property="titel" value="">
                    <bean:write name="currentItem" property="vraag" filter="true"/>
                </logic:equal>
            </a>
        </td>
        <td valign="top">
            <logic:equal name="currentItem" property="oproep.distributie.medium.id" value="2">
                <html:img page="/images/mail.gif"/>
            </logic:equal>
        </td>
        <td width="15%" valign="top">
            <bean:write name="currentItem" property="oproep.oproepdatumString" filter="false"/>
        </td>
        <td width="35%" valign="top">
            <logic:present name="currentItem" property="categorien">
                <ul>
                    <logic:iterate id="i" name="currentItem" property="categorien">
                        <li>
                            <logic:notEqual name="i"
                                            property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id"
                                            value="0">
                                <bean:write name="i"
                                            property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.omschrijving"/>
                                >
                                <bean:write name="i" property="omschrijving"/>
                            </logic:notEqual>
                            <logic:equal name="i"
                                         property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id"
                                         value="0">
                                <logic:notEqual name="i"
                                                property="superCategorie.superCategorie.superCategorie.superCategorie.id"
                                                value="0">
                                    <bean:write name="i"
                                                property="superCategorie.superCategorie.superCategorie.superCategorie.omschrijving"/>
                                    >
                                    <bean:write name="i" property="omschrijving"/>
                                </logic:notEqual>
                                <logic:equal name="i"
                                             property="superCategorie.superCategorie.superCategorie.superCategorie.id"
                                             value="0">
                                    <logic:notEqual name="i"
                                                    property="superCategorie.superCategorie.superCategorie.id"
                                                    value="0">
                                        <bean:write name="i"
                                                    property="superCategorie.superCategorie.superCategorie.omschrijving"/>
                                        >
                                        <bean:write name="i" property="omschrijving"/>
                                    </logic:notEqual>
                                    <logic:equal name="i"
                                                 property="superCategorie.superCategorie.superCategorie.id"
                                                 value="0">
                                        <logic:notEqual name="i" property="superCategorie.superCategorie.id"
                                                        value="0">
                                            <bean:write name="i"
                                                        property="superCategorie.superCategorie.omschrijving"/>
                                            >
                                            <bean:write name="i" property="omschrijving"/>
                                        </logic:notEqual>
                                        <logic:equal name="i" property="superCategorie.superCategorie.id"
                                                     value="0">
                                            <logic:notEqual name="i" property="superCategorie.id" value="0">
                                                <bean:write name="i" property="superCategorie.omschrijving"/>
                                                >
                                                <bean:write name="i" property="omschrijving"/>
                                            </logic:notEqual>
                                            <logic:equal name="i" property="superCategorie.id" value="0">
                                                <logic:notEqual name="i" property="id" value="0">
                                                    <bean:write name="i" property="omschrijving"/>
                                                </logic:notEqual>
                                            </logic:equal>
                                        </logic:equal>
                                    </logic:equal>
                                </logic:equal>
                            </logic:equal>
                        </li>
                    </logic:iterate>
                </ul>
            </logic:present>
        </td>
        <td width="15%" valign="top">
            <a href="../do/initNotitiesInvoer?type=1&id=<bean:write name="currentItem" property="id" />&ro=true">notities</a>
        </td>
    </tr>
</logic:iterate>
<tr>
    <td colspan="5">
        <hr/>
    </td>
</tr>
<tr>
    <td colspan="5">
        <iflogic:if name="cacheTLV" property="currentCount" op="notequal" value="1">
            <iflogic:then>
                <html:link href="../do/zoekenVervolg?method=previousTLV">
                    <html:img page="/images/button_vorige.gif" border="0"/>
                </html:link>
                |
            </iflogic:then>
        </iflogic:if>
        <iflogic:if name="cacheTLV" property="lastPage" op="notequal" value="true">
            <iflogic:then>
                <html:link href="../do/zoekenVervolg?method=nextTLV">
                    <html:img page="/images/button_volgende.gif" border="0"/>
                </html:link>
            </iflogic:then>
        </iflogic:if>
    </td>
</tr>
</table>
</iflogic:else>
</iflogic:if>

<br/>
</logic:present>

<logic:present name="cacheTXT" scope="session">
<iflogic:if name="cacheTXT" property="maxCount" op="equal" value="0">
<iflogic:then>
    <table>
        <tr>
            <th>Er werden geen teksten gevonden die aan de zoekcriteria voldoen</th>
        </tr>
    </table>
</iflogic:then>
<iflogic:else>
<bean:define name="cacheTXT" property="records" id="teksten"/>
<table cellspacing="5" width="100%" align="center">
<tr>
    <th colspan="4"><b>Teksten (Pagina
        <bean:write name="cacheTXT" property="currentCount"/>
        /
        <bean:write name="cacheTXT" property="pageCount"/>
        )</b></th>
</tr>
<tr bgcolor="#dddddd">
    <th align="left">Tekst</th>
    <th align="left">Oproepdatum</th>
    <th align="left">Categorie&euml;n</th>
    <th>&nbsp;</th>
</tr>
<logic:iterate name="teksten" id="currentItem" type="be.vlaanderen.sbs.s6.taaladvies.model.Tekst">
    <tr>
        <td width="35%" valign="top">
            <a href="../do/initOproep_TekstInvoer?id=<bean:write name="currentItem" property="oproep.id" />&ro=true">
                <bean:write name="currentItem" property="titel" filter="false"/>
            </a>
        </td>
        <td width="15%" valign="top">
            <bean:write name="currentItem" property="oproep.oproepdatumString"/>
        </td>
        <td width="35%" valign="top">
            <logic:present name="currentItem" property="categorien">
                <ul>
                    <logic:iterate id="i" name="currentItem" property="categorien">
                        <li>
                            <logic:notEqual name="i"
                                            property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id"
                                            value="0">
                                <bean:write name="i"
                                            property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.omschrijving"/>
                                >
                                <bean:write name="i" property="omschrijving"/>
                            </logic:notEqual>
                            <logic:equal name="i"
                                         property="superCategorie.superCategorie.superCategorie.superCategorie.superCategorie.id"
                                         value="0">
                                <logic:notEqual name="i"
                                                property="superCategorie.superCategorie.superCategorie.superCategorie.id"
                                                value="0">
                                    <bean:write name="i"
                                                property="superCategorie.superCategorie.superCategorie.superCategorie.omschrijving"/>
                                    >
                                    <bean:write name="i" property="omschrijving"/>
                                </logic:notEqual>
                                <logic:equal name="i"
                                             property="superCategorie.superCategorie.superCategorie.superCategorie.id"
                                             value="0">
                                    <logic:notEqual name="i"
                                                    property="superCategorie.superCategorie.superCategorie.id"
                                                    value="0">
                                        <bean:write name="i"
                                                    property="superCategorie.superCategorie.superCategorie.omschrijving"/>
                                        >
                                        <bean:write name="i" property="omschrijving"/>
                                    </logic:notEqual>
                                    <logic:equal name="i"
                                                 property="superCategorie.superCategorie.superCategorie.id"
                                                 value="0">
                                        <logic:notEqual name="i"
                                                        property="superCategorie.superCategorie.id"
                                                        value="0">
                                            <bean:write name="i"
                                                        property="superCategorie.superCategorie.omschrijving"/>
                                            >
                                            <bean:write name="i" property="omschrijving"/>
                                        </logic:notEqual>
                                        <logic:equal name="i"
                                                     property="superCategorie.superCategorie.id"
                                                     value="0">
                                            <logic:notEqual name="i" property="superCategorie.id"
                                                            value="0">
                                                <bean:write name="i"
                                                            property="superCategorie.omschrijving"/>
                                                >
                                                <bean:write name="i" property="omschrijving"/>
                                            </logic:notEqual>
                                            <logic:equal name="i" property="superCategorie.id"
                                                         value="0">
                                                <logic:notEqual name="i" property="id" value="0">
                                                    <bean:write name="i" property="omschrijving"/>
                                                </logic:notEqual>
                                            </logic:equal>
                                        </logic:equal>
                                    </logic:equal>
                                </logic:equal>
                            </logic:equal>
                        </li>
                    </logic:iterate>
                </ul>
            </logic:present>
        </td>
        <td width="15%" valign="top">
            <a href="../do/initNotitiesInvoer?type=2&id=<bean:write name="currentItem" property="id" />&ro=true">notities</a>
        </td>
    </tr>
</logic:iterate>
<tr>
    <td colspan="4">
        <hr/>
    </td>
</tr>
<tr>
    <td colspan="4">
        <iflogic:if name="cacheTXT" property="currentCount" op="notequal" value="1">
            <iflogic:then>
                <html:link href="../do/zoekenVervolg?method=previousTXT">
                    <html:img page="/images/button_vorige.gif" border="0"/>
                </html:link>
                |
            </iflogic:then>
        </iflogic:if>              
        <iflogic:if name="cacheTXT" property="lastPage" op="notequal" value="true">
            <iflogic:then>
                <html:link href="../do/zoekenVervolg?method=nextTXT">
                    <html:img page="/images/button_volgende.gif" border="0"/>
                </html:link>
            </iflogic:then>
        </iflogic:if>

    </td>
</tr>
</table>
</iflogic:else>
</iflogic:if>
<br/>
</logic:present>

<p>
    <html:link action="/initZoekOpnieuw">
        <html:img page="/images/button_nieuwe_zoekopdracht.gif" border="0"/>
    </html:link>

    <html:link page="/overzicht/zoeken.jsp">
        <html:img page="/images/button_verfijn_zoekopdracht.gif" border="0"/>
    </html:link>
</p>
</body>
</html:html>