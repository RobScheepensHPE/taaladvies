<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
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
    <link href="<html:rewrite page='/style/taaladvies.css' />" rel="stylesheet" type="text/css">
    <link href="<html:rewrite page='/style/taaladvies.js' />" type="text/JavaScript"/>
</head>

<body>

<h2>Zoekresultaten taalvraag kopi&euml;ren :</h2>

<html:errors/>
<p>
    <a class="bigger"
       href="../do/initTaalvragenInvoer?id=<bean:write name="TaalvragenInvoerForm" property="taalvraag.id" />&parentId=<bean:write name="TaalvragenInvoerForm" property="taalvraag.oproepId" />">
        <logic:equal name="TaalvragenInvoerForm" property="taalvraag.titel" value="">
            <bean:write name="TaalvragenInvoerForm" property="taalvraag.vraag"/>
        </logic:equal>
        <logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.titel" value="">
            <bean:write name="TaalvragenInvoerForm" property="taalvraag.titel"/>
        </logic:notEqual>
    </a>
</p>

<p>Op basis van uw trefwoorden en criteria werden de volgende gegevens gevonden :</p>

<logic:present name="cacheTLV" scope="session">

<iflogic:if name="cacheTLV" property="maxCount" op="equal" value="0">
<iflogic:then>
    <table>
        <th>Er werden geen taalvragen gevonden die aan de zoekcriteria voldoen</th>
    </table>
</iflogic:then>
<iflogic:else>

<bean:define name="cacheTLV" property="records" id="taalvragen"/>

<table cellspacing="5" width="100%" align="center">
<tr>
    <th colspan="3">
        <b>Taalvragen (Pagina
            <bean:write name="cacheTLV" property="currentCount"/>
            /
            <bean:write name="cacheTLV" property="pageCount"/>
            )</b>
    </th>
</tr>
<tr bgcolor="#dddddd">
    <th align="left">Taalvraag</th>
    <th align="left">&nbsp;</th>
    <th align="left">Oproepdatum</th>
    <th align="left">Categorie&euml;n</th>
</tr>
<logic:iterate name="taalvragen" id="currentItem"
               type="be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag">
    <tr>
        <td valign="top">
            <a href="../do/initKopieerTaalvraag?id=<bean:write name="currentItem" property="id" />">
                <bean:write name="currentItem" property="titel" filter="false"/>                    
            </a>
        </td>
        <td valign="top">
            <logic:equal name="currentItem" property="oproep.distributie.medium.omschrijving"
                         value="E-mail">
                <html:img page="/images/mail.gif"/>
            </logic:equal>
        </td>
        <td valign="top">
            <bean:write name="currentItem" property="oproep.oproepdatumString" filter="false"/>
        </td>
        <td valign="top">
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
    </tr>
</logic:iterate>
<tr>
    <td colspan="4">
        <hr/>
    </td>
</tr>
<tr>
    <td colspan="4">
        <iflogic:if name="cacheTLV" property="currentCount" op="notequal" value="1">
            <iflogic:then>
                <html:link page="/do/zoekenVervolgKopieerTLV?method=previousTLV">
                    <html:img page="/images/button_vorige.gif" border="0"/>
                </html:link>
                |
            </iflogic:then>
        </iflogic:if>
        <iflogic:if name="cacheTLV" property="lastPage" op="notequal" value="true">
        <iflogic:then>
        <html:link page="/do/zoekenVervolgKopieerTLV?method=nextTLV">
            <html:img page="/images/button_volgende.gif" border="0"/>
        </html:link>
    </td>
</tr>
</iflogic:then>
</iflogic:if>
</table>
</iflogic:else>
</iflogic:if>
<br>
</logic:present>
<p>
    <html:link page="/do/initZoekenKopieerTLV">
        <html:img page="/images/button_nieuwe_zoekopdracht.gif" border="0"/>
    </html:link>
    <html:link page="/overzicht/zoekenkopieerTLV.jsp">
        <html:img page="/images/button_verfijn_zoekopdracht.gif" border="0"/>
    </html:link>
</p>
</body>
</html:html>















