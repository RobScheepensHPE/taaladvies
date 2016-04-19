<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/SecurityTagLib.tld" prefix="security" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
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
            var appletLoaded = false;

            function TitelVoluitLoaded() {
                if (eWebEditPro.event.srcName == "naslagwerk.titelVoluitHTML") {
                    appletLoaded = true;
                }
            }

            function doSubmit(doAction) {
                if (fnOnSubmit()) {
                    document.forms['NaslagwerkenBeheerForm'].action = doAction;
                    document.forms['NaslagwerkenBeheerForm'].submit();
                }
            }

            function fnOnSubmit() {                
                if (appletLoaded) {
                    eWebEditPro.actionOnUnload = EWEP_ONUNLOAD_NOSAVE;
                    document.NaslagwerkenBeheerForm.elements["naslagwerk.titelVoluit"].value = eWebEditPro.instances["naslagwerk.titelVoluitHTML"].editor.getBodyText();
                    eWebEditPro.save();
                    return true;
                } else {
                    alert("<bean:message key='algemeen.opmaaknotready'/> ");
                    return false;
                }
            }

            function selecteer() {
                window.location.href = '../do/initNaslagwerkenBeheer?id=' + document.NaslagwerkenBeheerForm.elements['naslagwerk.id'].options[document.NaslagwerkenBeheerForm.elements['naslagwerk.id'].selectedIndex].value
            }
        </script>
    </logic:equal>

    <logic:notEqual name="eopro" value="true">
        <script language="JavaScript">
            function doSubmit(doAction) {
                fnOnSubmit();

                document.forms['NaslagwerkenBeheerForm'].action = doAction;
                document.forms['NaslagwerkenBeheerForm'].submit();
            }

            function fnOnSubmit() {
                document.NaslagwerkenBeheerForm.elements["naslagwerk.titelVoluitHTML"].value = document.NaslagwerkenBeheerForm.elements["naslagwerk.titelVoluit"].value;
            }

            function selecteer() {
                window.location.href = '../do/initNaslagwerkenBeheer?id=' + document.NaslagwerkenBeheerForm.elements['naslagwerk.id'].options[document.NaslagwerkenBeheerForm.elements['naslagwerk.id'].selectedIndex].value
            }
        </script>
    </logic:notEqual>

    <% response.setHeader("Pragma", "No-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
    %>
</head>
<body>

<bean:define name="NaslagwerkenBeheerForm" property="naslagwerken" type="java.util.ArrayList" id="naslagwerken"/>
<bean:define name="NaslagwerkenBeheerForm" property="bibliografien" type="java.util.ArrayList" id="bibliografien"/>
<html:form method="post" action="/naslagwerkenBeheer" onsubmit="return fnOnSubmit()">

<table class="borderbottom">
    <tr>
        <td class="h2style">
            <logic:notEqual name="NaslagwerkenBeheerForm" property="naslagwerk.id" value="0">
                Aanpassen <i>naslagwerk</i>
            </logic:notEqual>
            <logic:equal name="NaslagwerkenBeheerForm" property="naslagwerk.id" value="0">
                Invoeren <i>naslagwerk</i>
            </logic:equal>
        </td>

        <td class="modus" align="right">
            <bean:message key="algemeen.modus"/>
				<input type="radio" name=useEopro value="true" onclick="javascript:return doSubmit('../do/naslagwerkenBeheer?Button=EditeerModus<c:if test="${NaslagwerkenBeheerForm.naslagwerk.id != 0}">&U=true</c:if>');" <c:if test="${sessionScope.eopro}">CHECKED</c:if>/><bean:message key="algemeen.metopmaak"/>
				<input type="radio" name=useEopro value="false" onclick="javascript:return doSubmit('../do/naslagwerkenBeheer?Button=EditeerModus<c:if test="${NaslagwerkenBeheerForm.naslagwerk.id != 0}">&U=true</c:if>');" <c:if test="${!sessionScope.eopro}">CHECKED</c:if>/><bean:message key="algemeen.eenvoudig"/>
        </td>
    </tr>
</table>


<html:errors/>


<html:hidden name="NaslagwerkenBeheerForm" property="naslagwerk.titelVoluitHTML"/>
<logic:equal name="eopro" value="true">
    <html:hidden name="NaslagwerkenBeheerForm" property="naslagwerk.titelVoluit"/>
</logic:equal>
<table cellspacing="5">
    <tr>
        <td colspan="4" align="center">
            <html:select name="NaslagwerkenBeheerForm" property="naslagwerk.id" onchange="selecteer()">
                <option value="0">Selecteer...</option>
                <html:options collection="naslagwerken" property="id" labelProperty="omschrijving"/>
            </html:select>
            <hr/>
        </td>
    </tr>
    <tr>
        <td>
            <html:img page="/images/label_categorie.gif"/>
        </td>
        <td>
            <html:select name="NaslagwerkenBeheerForm" property="naslagwerk.parentId">
                <html:options collection="bibliografien" property="id" labelProperty="omschrijving"/>
            </html:select>
        </td>
        <td>
            <html:img page="/images/label_titel_kort.gif"/>
        </td>
        <td>
            <html:text name="NaslagwerkenBeheerForm" property="naslagwerk.omschrijving"/>
        </td>
    </tr>
    <tr>
        <td>
            <html:img page="/images/label_titel_lang.gif"/>
        </td>
        <td colspan="3">
            <logic:equal name="eopro" value="true">
                <c:url value="/ewebeditproConfig/toolbar-taaladvies.jsp" var="toolbarConfig"/>
                <script language="JavaScript1.2" type="text/javascript">
                    eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";
                    eWebEditPro.create("naslagwerk.titelVoluitHTML", "700", "150");
                    eWebEditPro.addEventHandler("onready", TitelVoluitLoaded);
                </script>
            </logic:equal>

            <logic:notEqual name="eopro" value="true">
                <html:textarea name="NaslagwerkenBeheerForm" property="naslagwerk.titelVoluit" rows="7" cols="80"/>
            </logic:notEqual>
        </td>
    </tr>
    <tr>
        <td>
            <html:img page="/images/label_actief.gif"/>
        </td>
        <td colspan="3">
            <html:checkbox name="NaslagwerkenBeheerForm" property="naslagwerk.actief"/>
        </td>
    </tr>
    <tr>
        <td colspan="4" align="center">

            <logic:notEqual name="NaslagwerkenBeheerForm" property="naslagwerk.id" value="0">
                <html:image property="Wijzigen" value="Wijzigen" page="/images/button_wijzigen.gif"
                            onclick="return fnOnSubmit()" altKey="algemeen.naslagwerk.wijzigen"/>
            </logic:notEqual>

            <logic:equal name="NaslagwerkenBeheerForm" property="naslagwerk.id" value="0">
                <html:image property="Toevoegen" value="Toevoegen" page="/images/button_toevoegen.gif"
                            onclick="return fnOnSubmit()" altKey="algemeen.naslagwerk.toevoegen"/>
            </logic:equal>

            <html:link action="/initNaslagwerkenBeheer" style="border:none">
                <img src="<html:rewrite page='/images/button_nieuw_naslagwerk.gif' />" border="0"
                     alt="<bean:message key="algemeen.naslagwerk.nieuw"/>"/>
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