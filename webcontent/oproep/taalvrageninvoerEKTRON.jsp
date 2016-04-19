<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/SecurityTagLib.tld" prefix="security" %>
<security:isLoggedIn/>
<html:html locale="true">
<head>
<title>Databank Taaladvies</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="<html:rewrite page='/style/taaladvies.css' />" rel="stylesheet" type="text/css">
<link href="<html:rewrite page='/style/taaladvies.js' />" type="text/JavaScript"/>
<script language="JavaScript1.2" src="<html:rewrite page='/style/taaladvies.js' />"></script>


<logic:equal name="eopro" value="true">
    <script language="JavaScript1.2" src="/ewebeditpro4/ewebeditpro.js"></script>
    <script language="JavaScript">

        var appletLoaded = new Array(4);

        for (var i = 0; i < appletLoaded.length; i++)
            appletLoaded[i] = false;

        appletLoaded[0] = true;
        appletLoaded[1] = true;

        function TitelLoaded()
        {
            /*if (eWebEditPro.event.srcName == "taalvraag.titelHTML") {*/
            appletLoaded[0] = true;
            /*}*/
        }

        function VraagLoaded()
        {
            /*if (eWebEditPro.event.srcName == "taalvraag.vraagHTML") {*/
            appletLoaded[1] = true;
            /*}*/
        }

        function AntwoordLoaded()
        {
            if (eWebEditPro.event.srcName == "taalvraag.antwoordHTML") {
                appletLoaded[2] = true;
            }
        }

        function ToelichtingLoaded()
        {
            if (eWebEditPro.event.srcName == "taalvraag.toelichtingHTML") {
                appletLoaded[3] = true;
            }
        }

        function AppletsLoaded()
        {
            for (var i = 0; i < appletLoaded.length; i++)
            {
                if (!appletLoaded[i])
                {
                    return false;
                }
            }

            return true;
        }


        function doSubmit(doAction) {
            if (fnOnSubmit()) {
                document.forms['TaalvragenInvoerForm'].action = doAction;
                document.forms['TaalvragenInvoerForm'].submit();
            }
        }


        function fnOnSubmit() {
            if (AppletsLoaded()) {
                eWebEditPro.actionOnUnload = EWEP_ONUNLOAD_NOSAVE;
                //document.TaalvragenInvoerForm.elements["taalvraag.vraag"].value = eWebEditPro.instances["taalvraag.vraagHTML"].editor.getBodyText();
                document.TaalvragenInvoerForm.elements["taalvraag.antwoord"].value = eWebEditPro.instances["taalvraag.antwoordHTML"].editor.getBodyText();
                document.TaalvragenInvoerForm.elements["taalvraag.toelichting"].value = eWebEditPro.instances["taalvraag.toelichtingHTML"].editor.getBodyText();
                //document.TaalvragenInvoerForm.elements["taalvraag.titel"].value = eWebEditPro.instances["taalvraag.titelHTML"].editor.getBodyText();
                eWebEditPro.save();
                return true;
            } else {
                alert("<bean:message key='algemeen.opmaaknotready'/> ");
                return false;
            }
        }
    </script>
</logic:equal>

<logic:notEqual name="eopro" value="true">
    <script language="JavaScript">
        function doSubmit(doAction) {
            fnOnSubmit();

            document.forms['TaalvragenInvoerForm'].action = doAction;
            document.forms['TaalvragenInvoerForm'].submit();
        }

        function fnOnSubmit() {
            //document.TaalvragenInvoerForm.elements["taalvraag.vraagHTML"].value = document.TaalvragenInvoerForm.elements["taalvraag.vraag"].value;

            document.TaalvragenInvoerForm.elements["taalvraag.antwoordHTML"].value = document.TaalvragenInvoerForm.elements["taalvraag.antwoord"].value;

            document.TaalvragenInvoerForm.elements["taalvraag.toelichtingHTML"].value = document.TaalvragenInvoerForm.elements["taalvraag.toelichting"].value;

            //document.TaalvragenInvoerForm.elements["taalvraag.titelHTML"].value = document.TaalvragenInvoerForm.elements["taalvraag.titel"].value;

        }
    </script>

</logic:notEqual>

<% response.setHeader("Pragma", "No-cache");
    response.setDateHeader("Expires", 0);
    response.setHeader("Cache-Control", "no-cache");
%>
</head>
<body>

<html:form method="post" action="/taalvragenInvoer" onsubmit="return fnOnSubmit()">

<table class="borderbottom">
    <tr>
        <td class="h2style">
            <logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">
                AANPASSEN
            </logic:notEqual>
            <logic:equal name="TaalvragenInvoerForm" property="taalvraag.id" value="0">
                INVOEREN
            </logic:equal>
            Taalvraag
        </td>

        <td class="modus" align="right">
            <bean:message key="algemeen.modus"/>
            :

            <logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">
                <c:choose>
                    <c:when test="${sessionScope.eopro}">
                        <input type="radio" name=useEopro value="true"
                               onclick="doSubmit('../do/taalvragenInvoer?Button=EditeerModus&U=true');"
                               CHECKED/>
                        <bean:message key="algemeen.metopmaak"/>
                        <input type="radio" name=useEopro value="false"
                               onclick="doSubmit('../do/taalvragenInvoer?Button=EditeerModus&U=true');"/>
                        <bean:message key="algemeen.eenvoudig"/>
                    </c:when>
                    <c:otherwise>
                        <input type="radio" name=useEopro value="true"
                               onclick="doSubmit('../do/taalvragenInvoer?Button=EditeerModus&U=true');"/>
                        <bean:message key="algemeen.metopmaak"/>
                        <input type="radio" name=useEopro value="false"
                               onclick="doSubmit('../do/taalvragenInvoer?Button=EditeerModus&U=true');"
                               CHECKED/>
                        <bean:message key="algemeen.eenvoudig"/>
                    </c:otherwise>
                </c:choose>
            </logic:notEqual>

            <logic:equal name="TaalvragenInvoerForm" property="taalvraag.id" value="0">
                <c:choose>
                    <c:when test="${sessionScope.eopro}">
                        <input type="radio" name=useEopro value="true"
                               onclick="doSubmit('../do/taalvragenInvoer?Button=EditeerModus');"
                               CHECKED/>
                        <bean:message key="algemeen.metopmaak"/>
                        <input type="radio" name=useEopro value="false"
                               onclick="doSubmit('../do/taalvragenInvoer?Button=EditeerModus');"/>
                        <bean:message key="algemeen.eenvoudig"/>
                    </c:when>
                    <c:otherwise>
                        <input type="radio" name=useEopro value="true"
                               onclick="doSubmit('../do/taalvragenInvoer?Button=EditeerModus');"/>
                        <bean:message key="algemeen.metopmaak"/>
                        <input type="radio" name=useEopro value="false"
                               onclick="doSubmit('../do/taalvragenInvoer?Button=EditeerModus');"
                               CHECKED/>
                        <bean:message key="algemeen.eenvoudig"/>
                    </c:otherwise>
                </c:choose>
            </logic:equal>
        </td>
    </tr>
</table>

<html:errors/>

<html:hidden name="TaalvragenInvoerForm" property="taalvraag.vraagHTML"/>
<html:hidden name="TaalvragenInvoerForm" property="taalvraag.antwoordHTML"/>
<html:hidden name="TaalvragenInvoerForm" property="taalvraag.toelichtingHTML"/>
<html:hidden name="TaalvragenInvoerForm" property="taalvraag.titelHTML"/>
<logic:equal name="eopro" value="true">
    <%--<html:hidden name="TaalvragenInvoerForm" property="taalvraag.vraag"/>--%>
    <html:hidden name="TaalvragenInvoerForm" property="taalvraag.antwoord"/>
    <html:hidden name="TaalvragenInvoerForm" property="taalvraag.toelichting"/>
    <%--<html:hidden name="TaalvragenInvoerForm" property="taalvraag.titel"/>--%>
</logic:equal>


<table cellspacing="5">
<tr>
    <td>
        <logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">
            <a class="bigger"
               href="javascript:doSubmit('../do/taalvragenInvoer?Button=Wijzigen')"
               style="border:none">
                Oproep
                <bean:write name="TaalvragenInvoerForm" property="oproep.id"/>
            </a>
        </logic:notEqual>
        <logic:equal name="TaalvragenInvoerForm" property="taalvraag.id" value="0">
            <a class="bigger"
               href="javascript:doSubmit('../do/taalvragenInvoer?Button=Toevoegen')"
               style="border:none">
                Oproep
                <bean:write name="TaalvragenInvoerForm" property="oproep.id"/>
            </a>
        </logic:equal>
    </td>
    <td>
        <bean:write name="TaalvragenInvoerForm" property="oproep.voornaam"/>
        <bean:write name="TaalvragenInvoerForm" property="oproep.naam"/>
        <html:hidden name="TaalvragenInvoerForm" property="oproep.id"/>
    </td>

    <logic:notEqual name="TaalvragenInvoerForm" property="oproep.telefoon" value="">
        <td>
            <html:img page="/images/label_telefoon.gif"/>
        </td>
        <td align="left">
            <bean:write name="TaalvragenInvoerForm" property="oproep.telefoon"/>
        </td>
    </logic:notEqual>
    <logic:equal name="TaalvragenInvoerForm" property="oproep.telefoon" value="">
        <td colspan="1">&nbsp;</td>
    </logic:equal>
    <td>
        <logic:equal name="TaalvragenInvoerForm" property="taalvraag.id" value="0">
            <html:image property="Annuleren" value="Annuleren" page="/images/button_taalvraag_annuleren.gif"
                        onclick="return fnOnSubmit()"/>
        </logic:equal>
        &nbsp;
    </td>

</tr>
<tr>
    <td colspan="5">
        <hr/>
    </td>
</tr>
<tr>
    <td colspan="5" align="center">
        <logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">
            <html:image property="Nieuwe Taalvraag Update" value="Nieuwe Taalvraag Update"
                        page="/images/button_nieuwetaalvraag.gif" onclick="return fnOnSubmit()"/>
            <html:image property="Kopieer Update" value="Kopieer Update" page="/images/button_kopieer.gif"
                        onclick="return fnOnSubmit()"/>
            <html:image property="Klant Update" value="Klant Update" page="/images/button_klant.gif"
                        onclick="return fnOnSubmit()"/>
            <html:image property="Koppel Tekst" value="Koppel Tekst" page="/images/button_koppel_tekst.gif"/>
        </logic:notEqual>
        <logic:equal name="TaalvragenInvoerForm" property="taalvraag.id" value="0">
            <html:image property="Nieuwe Taalvraag Save" value="Nieuwe Taalvraag Save"
                        page="/images/button_nieuwetaalvraag.gif" onclick="return fnOnSubmit()"/>
            <html:image property="Kopieer Save" value="Kopieer Save" page="/images/button_kopieer.gif"
                        onclick="return fnOnSubmit()"/>
            <html:image property="Klant Save" value="Klant Save" page="/images/button_klant.gif"
                        onclick="return fnOnSubmit()"/>
            <html:image property="Koppel Tekst Save" value="Koppel Tekst Save" page="/images/button_koppel_tekst.gif"/>
        </logic:equal>

    </td>
</tr>
<tr>
    <td colspan="5" align="center">
        <a href="javascript:doSubmit('../do/taalvragenInvoer?Button=Categorie<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">&U=true</logic:notEqual>')">categorie</a>
        &nbsp;|&nbsp;
        <a href="javascript:doSubmit('../do/taalvragenInvoer?Button=Naslagwerk<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">&U=true</logic:notEqual>')">naslagwerk</a>
        --
        <a href="javascript:doSubmit('../do/taalvragenInvoer?Button=Bron<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">&U=true</logic:notEqual>')">bron</a>
        --
        <a href="javascript:doSubmit('../do/taalvragenInvoer?Button=Citaat<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">&U=true</logic:notEqual>')">citaat</a>
        --
        <a href="javascript:doSubmit('../do/taalvragenInvoer?Button=Frequentie<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">&U=true</logic:notEqual>')">frequentie</a>
        --
        <a href="javascript:doSubmit('../do/taalvragenInvoer?Button=Koppeling<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">&U=true</logic:notEqual>')">koppeling</a>
        --
        <a href="javascript:doSubmit('../do/taalvragenInvoer?Button=Hulpmiddelen<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">&U=true</logic:notEqual>')">hulpmiddelen</a>
        &nbsp;|&nbsp;
        <a href="javascript:doSubmit('../do/taalvragenInvoer?Button=Vraag<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">&U=true</logic:notEqual>')">vraag</a>
        --
        <a href="javascript:doSubmit('../do/taalvragenInvoer?Button=Bijzonderheid<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">&U=true</logic:notEqual>')">bijzonderheid</a>
        &nbsp;|&nbsp;
        <a href="javascript:doSubmit('../do/taalvragenInvoer?Button=Notitie<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">&U=true</logic:notEqual>')">notitie</a>
    </td>
</tr>

<tr>
    <td>
        <html:img page="/images/label_vraagnummer.gif"/>
    </td>
    <td>
        <bean:write name="TaalvragenInvoerForm" property="taalvraag.volgnummer"/>
        <html:hidden name="TaalvragenInvoerForm" property="taalvraag.volgnummer"/>
    </td>
    <td align="left">
        <html:img page="/images/label_afgehandeld.gif"/>
    </td>
    <td align="left">
        <html:checkbox name="TaalvragenInvoerForm" property="taalvraag.afgehandeld"/>
    </td>
    <td width="42%">&nbsp;


    </td>
</tr>
<tr>
    <td>
        <html:img page="/images/label_titel.gif"/>
    </td>
    <td colspan="4">
        <html:text style="width: 85%;" name="TaalvragenInvoerForm" property="taalvraag.titel"/>

            <%--<logic:equal name="eopro" value="true">
                <c:url value="/ewebeditproConfig/toolbar-taaladvies.jsp" var="toolbarConfig"/>
                <script language="JavaScript1.2">
                    eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";
                    eWebEditPro.create("taalvraag.titelHTML", "700", "100");
                    eWebEditPro.addEventHandler("onready", TitelLoaded);
                </script>
            </logic:equal>

            <logic:notEqual name="eopro" value="true">
                <html:text style="width: 99%;" name="TaalvragenInvoerForm" property="taalvraag.titel"/>
            </logic:notEqual>--%>

    </td>
</tr>
<tr>
    <td>
        <html:img page="/images/label_vraag.gif"/>
        <html:hidden name="TaalvragenInvoerForm" property="taalvraag.id"/>
        <html:hidden name="TaalvragenInvoerForm" property="taalvraag.oproepId"/>
    </td>
    <td colspan="4">
        <html:textarea name="TaalvragenInvoerForm" property="taalvraag.vraag" style="width: 85%;" rows="5"/>
            <%--
            <logic:equal name="eopro" value="true">
                <c:url value="/ewebeditproConfig/toolbar-taaladvies.jsp" var="toolbarConfig"/>
                <script language="JavaScript1.2">
                    eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";
                    eWebEditPro.create("taalvraag.vraagHTML", "700", "150");
                    eWebEditPro.addEventHandler("onready", VraagLoaded);
                </script>
            </logic:equal>

            <logic:notEqual name="eopro" value="true">
                <html:textarea name="TaalvragenInvoerForm" property="taalvraag.vraag" style="width: 100%;" rows="5"/>
            </logic:notEqual>--%>
    </td>
</tr>
<tr>
    <td>
        <html:img page="/images/label_antwoord.gif"/>
    </td>
    <td colspan="4">


        <logic:equal name="eopro" value="true">
            <c:url value="/ewebeditproConfig/toolbar-taaladvies3.jsp" var="toolbarConfig"/>
            <script language="JavaScript1.2">
                eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";
                eWebEditPro.create("taalvraag.antwoordHTML", "700", "170");
                eWebEditPro.addEventHandler("onready", AntwoordLoaded);
            </script>
        </logic:equal>

        <logic:notEqual name="eopro" value="true">
            <html:textarea name="TaalvragenInvoerForm" property="taalvraag.antwoord" style="width: 100%;" rows="5"/>
        </logic:notEqual>
    </td>
</tr>
<tr>
    <td>
        <html:img page="/images/label_toelichting.gif"/>
    </td>
    <td colspan="4">

        <logic:equal name="eopro" value="true">
            <c:url value="/ewebeditproConfig/toolbar-taaladvies4.jsp" var="toolbarConfig"/>
            <script language="JavaScript1.2">
                eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";
                eWebEditPro.create("taalvraag.toelichtingHTML", "700", "400");
                eWebEditPro.addEventHandler("onready", ToelichtingLoaded);
            </script>
        </logic:equal>
        <logic:notEqual name="eopro" value="true">
            <html:textarea name="TaalvragenInvoerForm" property="taalvraag.toelichting" style="width: 100%;" rows="15"/>
        </logic:notEqual>

    </td>
</tr>
<tr>
    <td colspan="5">
        <hr/>
    </td>
</tr>
<logic:notEqual name="TaalvragenInvoerForm" property="taalvraag.id" value="0">
    <tr>
        <td colspan="5" align="center">
            <table cellspacing="5">
                <tr>
                    <th colspan="2">Gekoppelde teksten</th>
                </tr>
                <logic:present name="TaalvragenInvoerForm" property="taalvraag.teksten">
                    <logic:iterate name="TaalvragenInvoerForm" property="taalvraag.teksten" id="teksteni">
                        <tr>
                            <td>
                                <a href="javascript:doSubmit('../do/taalvragenInvoer?Button2=Verwijder&idToDelete=<bean:write name="teksteni" property="id" />')">
                                    <img src="<html:rewrite page='/images/Delete24.gif' />" border="0" alt="Delete"/>
                                </a>
                            </td>
                            <td>
                                <bean:write name="teksteni" property="titel"/>
                            </td>
                        </tr>
                    </logic:iterate>
                </logic:present>
                <tr>
                    <td colspan="2">
                        <html:image property="Koppel Tekst" value="Koppel Tekst"
                                    page="/images/button_koppel_tekst.gif"/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</logic:notEqual>
</table>

</html:form>

</body>
</html:html>