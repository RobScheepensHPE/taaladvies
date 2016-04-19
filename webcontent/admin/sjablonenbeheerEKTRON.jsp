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
        var appletLoaded = new Array(3);

        for (var i = 0; i < appletLoaded.length; i++)
            appletLoaded[i] = false;

        function InhoudLoaded()
        {
            if (eWebEditPro.event.srcName == "sjabloon.inhoudHTML") {
                appletLoaded[0] = true;
            }
        }

        function SlotgroetLoaded()
        {
            if (eWebEditPro.event.srcName == "sjabloon.slotgroetHTML") {
                appletLoaded[1] = true;
            }
        }

        function HandtekeningLoaded()
        {
            if (eWebEditPro.event.srcName == "sjabloon.handtekeningHTML") {
                appletLoaded[2] = true;
            }
        }


        function AppletsLoaded() {
            for (var i = 0; i < appletLoaded.length; i++) {
                if (!appletLoaded[i]) {
                    return false;
                }
            }

            return true;
        }

        function doSubmit(doAction) {
            if (fnOnSubmit()) {
                document.forms['SjablonenBeheerForm'].action = doAction;
                document.forms['SjablonenBeheerForm'].submit();
            }
        }

        function fnOnSubmit() {
            if (AppletsLoaded()) {
            eWebEditPro.actionOnUnload = EWEP_ONUNLOAD_NOSAVE;
            document.SjablonenBeheerForm.elements["sjabloon.inhoud"].value = eWebEditPro.instances["sjabloon.inhoudHTML"].editor.getBodyText();
            document.SjablonenBeheerForm.elements["sjabloon.slotgroet"].value = eWebEditPro.instances["sjabloon.slotgroetHTML"].editor.getBodyText();
            document.SjablonenBeheerForm.elements["sjabloon.handtekening"].value = eWebEditPro.instances["sjabloon.handtekeningHTML"].editor.getBodyText();
            eWebEditPro.save();
            return true;
            } else {
                alert("<bean:message key='algemeen.opmaaknotready'/> ");
                return false;
            }
        }

        function selecteer() {
			window.location.href = 'initSjablonenBeheer?id=' + document.SjablonenBeheerForm.elements['sjabloon.id'].options[document.SjablonenBeheerForm.elements['sjabloon.id'].selectedIndex].value		
        }

    </script>
</logic:equal>

<logic:notEqual name="eopro" value="true">
    <script language="JavaScript">
        function doSubmit(doAction) {
            fnOnSubmit();

            document.forms['SjablonenBeheerForm'].action = doAction;
            document.forms['SjablonenBeheerForm'].submit();
        }

        function fnOnSubmit() {
            document.SjablonenBeheerForm.elements["sjabloon.inhoudHTML"].value = document.SjablonenBeheerForm.elements["sjabloon.inhoud"].value;
            document.SjablonenBeheerForm.elements["sjabloon.slotgroetHTML"].value = document.SjablonenBeheerForm.elements["sjabloon.slotgroet"].value;
            document.SjablonenBeheerForm.elements["sjabloon.handtekeningHTML"].value = document.SjablonenBeheerForm.elements["sjabloon.handtekening"].value;
        }

        function selecteer() {
            window.location.href = 'initSjablonenBeheer?id=' + document.SjablonenBeheerForm.elements['sjabloon.id'].options[document.SjablonenBeheerForm.elements['sjabloon.id'].selectedIndex].value
        }

    </script>
</logic:notEqual>

<% response.setHeader("Pragma", "No-cache");
    response.setDateHeader("Expires", 0);
    response.setHeader("Cache-Control", "no-cache");
%>
</head>
<body>

<bean:define name="SjablonenBeheerForm" property="sjablonen" type="java.util.ArrayList" id="sjablonen"/>
<html:form method="post" action="/sjablonenBeheer" onsubmit="return fnOnSubmit()">

<table class="borderbottom">
    <tr>
        <td class="h2style">
            <logic:notEqual name="SjablonenBeheerForm" property="sjabloon.id" value="0">
                Aanpassen <i>Sjabloon</i>
            </logic:notEqual>
            <logic:equal name="SjablonenBeheerForm" property="sjabloon.id" value="0">
                Invoeren <i>Sjabloon</i>
            </logic:equal>
        </td>

			<td class="modus" align="right"><bean:message key="algemeen.modus"/>:
				<input type="radio" name=useEopro value="true" onclick="javascript:doSubmit('../do/sjablonenBeheer?Button=EditeerModus<c:if test="${SjablonenBeheerForm.sjabloon.id != 0}">&U=true</c:if>');" <c:if test="${sessionScope.eopro}">CHECKED</c:if>/><bean:message key="algemeen.metopmaak"/>
				<input type="radio" name=useEopro value="false" onclick="javascript:doSubmit('../do/sjablonenBeheer?Button=EditeerModus<c:if test="${SjablonenBeheerForm.sjabloon.id != 0}">&U=true</c:if>');" <c:if test="${!sessionScope.eopro}">CHECKED</c:if>/><bean:message key="algemeen.eenvoudig"/>
        </td>
    </tr>
</table>

<html:errors/>

<html:hidden name="SjablonenBeheerForm" property="sjabloon.inhoudHTML"/>
<html:hidden name="SjablonenBeheerForm" property="sjabloon.slotgroetHTML"/>
<html:hidden name="SjablonenBeheerForm" property="sjabloon.handtekeningHTML"/>
<logic:equal name="eopro" value="true">
    <html:hidden name="SjablonenBeheerForm" property="sjabloon.inhoud"/>
    <html:hidden name="SjablonenBeheerForm" property="sjabloon.slotgroet"/>
    <html:hidden name="SjablonenBeheerForm" property="sjabloon.handtekening"/>
</logic:equal>


<table cellspacing="5">
<tr>
    <td colspan="4" align="center">
        <html:select name="SjablonenBeheerForm" property="sjabloon.id" onchange="selecteer()">
            <option value="0">Selecteer...</option>
            <html:options collection="sjablonen" property="id" labelProperty="omschrijving"/>
        </html:select>
        <hr/>
    </td>
</tr>
<tr>
    <td>
        <html:img page="/images/label_omschrijving.gif"/>
    </td>
    <td>
        <html:text name="SjablonenBeheerForm" property="sjabloon.omschrijving"/>
    </td>
    <td>
        <html:img page="/images/label_onderwerp.gif"/>
    </td>
    <td>
        <html:text name="SjablonenBeheerForm" property="sjabloon.onderwerp"/>
    </td>
</tr>
<tr>
    <td>
        <html:img page="/images/label_eigenlijke_tekst.gif"/>
    </td>
    <td colspan="3">

        <logic:equal name="eopro" value="true">
            <c:url value="/ewebeditproConfig/toolbar-taaladvies.jsp" var="toolbarConfig"/>
            <script language="JavaScript1.2">
                eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";
                eWebEditPro.create("sjabloon.inhoudHTML", "700", "200");
                eWebEditPro.addEventHandler("onready", InhoudLoaded);
            </script>
        </logic:equal>

        <logic:notEqual name="eopro" value="true">
            <html:textarea name="SjablonenBeheerForm" property="sjabloon.inhoud" cols="80" rows="7"/>
        </logic:notEqual>

    </td>
</tr>
<tr>
    <td>
        <html:img page="/images/label_slotgroet.gif"/>
    </td>
    <td colspan="3">
        <logic:equal name="eopro" value="true">
            <c:url value="/ewebeditproConfig/toolbar-taaladvies.jsp" var="toolbarConfig"/>
            <script language="JavaScript1.2">
                eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";
                eWebEditPro.create("sjabloon.slotgroetHTML", "700", "150");
                eWebEditPro.addEventHandler("onready", SlotgroetLoaded);
            </script>
        </logic:equal>

        <logic:notEqual name="eopro" value="true">
            <html:textarea name="SjablonenBeheerForm" property="sjabloon.slotgroet" rows="7" cols="80"/>
        </logic:notEqual>
    </td>
</tr>
<tr>
    <td>
        <html:img page="/images/label_handtekening.gif"/>
    </td>
    <td colspan="3">
        <logic:equal name="eopro" value="true">
            <c:url value="/ewebeditproConfig/toolbar-taaladvies.jsp" var="toolbarConfig"/>
            <script language="JavaScript1.2">
                eWebEditPro.parameters.config = "<c:out value="${toolbarConfig}"/>";
                eWebEditPro.create("sjabloon.handtekeningHTML", "700", "150");
                eWebEditPro.addEventHandler("onready", HandtekeningLoaded);
            </script>
        </logic:equal>

        <logic:notEqual name="eopro" value="true">
            <html:textarea name="SjablonenBeheerForm" property="sjabloon.handtekening" rows="7" cols="80"/>
        </logic:notEqual>
    </td>
</tr>
<tr>
    <td>
        <html:img page="/images/label_actief.gif"/>
    </td>
    <td colspan="3">
        <html:checkbox name="SjablonenBeheerForm" property="sjabloon.actief"/>
    </td>
</tr>
<tr>
    <td colspan="4" align="center">
        <logic:notEqual name="SjablonenBeheerForm" property="sjabloon.id" value="0">
            <%--<html:submit value="Wijzigen" property="Button" onclick="return fnOnSubmit()" />--%>
            <html:image property="Wijzigen" value="Wijzigen" page="/images/button_wijzigen.gif"
                        altKey="algemeen.sjabloon.wijzigen"
                        onclick="return fnOnSubmit()"/>
        </logic:notEqual>
        <logic:equal name="SjablonenBeheerForm" property="sjabloon.id" value="0">
            <%--<html:submit value="Toevoegen" property="Button" onclick="return fnOnSubmit()" />--%>
            <html:image property="Toevoegen" value="Toevoegen" page="/images/button_toevoegen.gif"
                        altKey="algemeen.sjabloon.toevoegen"
                        onclick="return fnOnSubmit()"/>
        </logic:equal>

        <html:link action="/initSjablonenBeheer" style="border:none">
            <img src="<html:rewrite page='/images/button_nieuw_sjabloon.gif' />" border="0"
                 alt="<bean:message key="algemeen.sjabloon.nieuw"/>"/>
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