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

    <logic:equal name="eopro" value="true">
        <script type="text/javascript" language="javascript" src="/eopro/editonpro.js"></script>

        <script language="JavaScript">
            strConfig = '<bean:write name="eoproconfig" filter="false"/>';
            strUIConfig = '<bean:write name="eoprouiconfig" filter="false"/>';

            var appletLoaded = false

            function TitelVoluitLoaded()
            {
                appletLoaded = true;
            }

            function fnOnLoad() {
                titelVoluitEop.setHTMLData(document.NaslagwerkenBeheerForm.elements["naslagwerk.titelVoluitHTML"].value);
                titelVoluitEop.pumpEvents();
            }

            function doSubmit(doAction) {
                fnOnSubmit();

                document.forms['NaslagwerkenBeheerForm'].action = doAction;
                document.forms['NaslagwerkenBeheerForm'].submit();
            }
            function fnOnSubmit() {
                if (appletLoaded) {
                    document.NaslagwerkenBeheerForm.elements["naslagwerk.titelVoluitHTML"].value = titelVoluitEop.getHTMLData();
                    document.NaslagwerkenBeheerForm.elements["naslagwerk.titelVoluit"].value = titelVoluitEop.getPlainText();
                }
            }

            function selecteer() {
                window.location.href = 'initNaslagwerkenBeheer?id=' + document.NaslagwerkenBeheerForm.elements['naslagwerk.id'].options[document.NaslagwerkenBeheerForm.elements['naslagwerk.id'].selectedIndex].value
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
                window.location.href = 'initNaslagwerkenBeheer?id=' + document.NaslagwerkenBeheerForm.elements['naslagwerk.id'].options[document.NaslagwerkenBeheerForm.elements['naslagwerk.id'].selectedIndex].value
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
                Aanpassen <i>Naslagwerk</i>
            </logic:notEqual>
            <logic:equal name="NaslagwerkenBeheerForm" property="naslagwerk.id" value="0">
                Invoeren <i>Naslagwerk</i>
            </logic:equal>
        </td>

        <td class="modus" align="right">
            <bean:message key="algemeen.modus"/>
            :
            <input type="radio" name=useEopro value="true"
                   onclick="javascript:doSubmit('../do/naslagwerkenBeheer?Button=EditeerModus<c:if test="${NaslagwerkenBeheerForm.naslagwerk.id != 0}">&U=true</c:if>');"
                    <c:if test="${sessionScope.eopro}">CHECKED</c:if>
                    />
            <bean:message key="algemeen.metopmaak"/>
            <input type="radio" name=useEopro value="false"
                   onclick="javascript:doSubmit('../do/naslagwerkenBeheer?Button=EditeerModus<c:if test="${NaslagwerkenBeheerForm.naslagwerk.id != 0}">&U=true</c:if>');"
                    <c:if test="${!sessionScope.eopro}">CHECKED</c:if>
                    />
            <bean:message key="algemeen.eenvoudig"/>
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
            <script type="text/javascript" language="javascript">
                titelVoluitEop = new editOnPro(700, 150, "titelVoluit", "editor", "titelVoluitEop");
                titelVoluitEop.setCodebase("/eopro");
                titelVoluitEop.setUIConfig(strUIConfig);
                titelVoluitEop.setConfig(strConfig);
                titelVoluitEop.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                titelVoluitEop.setStartUpScreenBackgroundColor("#EBF0FF");
                titelVoluitEop.setStartUpScreenTextColor("navy");
                titelVoluitEop.setLocaleCode("en_US");
                titelVoluitEop.setParam("bodyonly", "true");
                titelVoluitEop.setParam("oldfontstylemode", "true");
                titelVoluitEop.setParam("sourceview", "true");
                titelVoluitEop.setParam("cache_archive", "edit-on-pro-4.jar");
                titelVoluitEop.setParam("cache_option", "plugin");
                titelVoluitEop.setOnEditorLoaded("fnOnLoad");
                titelVoluitEop.setOnDataLoaded("TitelVoluitLoaded");
                titelVoluitEop.loadEditor();
            </script>
        </logic:equal>

        <logic:notEqual name="eopro" value="true">
            <html:textarea name="NaslagwerkenBeheerForm" property="naslagwerk.titelVoluit" rows="7" cols="80"/>
        </logic:notEqual>

        <!--				<applet code="com.realobjects.eop.applet.EditorApplet" archive="edit-on-pro-signed.jar,tidy.jar,ssce.jar"
                        codebase="/eopro/" height="150" width="700" name="titelVoluit" id="editor" MAYSCRIPT>

                            <PARAM name="WINDOWFACECOLOR" value="#EBF0FF">
                            <PARAM name="TABPANEACTIVECOLOR" value="#cce3ff">
                            <PARAM name="WINDOWHIGHLIGHTCOLOR" value="#FFffff">
                            <PARAM name="LIGHTEDGECOLOR" value="#ebf0ff">
                            <PARAM name="DARKEDGECOLOR" value="#C5CCFF">
                            <PARAM name="INNERTEXTCOLOR" value="#000000">
                            <PARAM name="STARTUPSCREENBACKGROUNDCOLOR" VALUE="#EBF0FF">
                            <PARAM name="STARTUPSCREENTEXTCOLOR" VALUE="navy">
                            <PARAM name="CABBASE" value="tidy.cab,ssce.cab,edit-on-pro-signed.cab" />
                            <PARAM name="LOCALE" value="en_US" />
                            <PARAM name="TOOLBARURL" value="toolbar-taaladvies.xml" />
                            <PARAM name="CONFIGURL" value="config-taaladvies.xml" />
                            <PARAM name="TABLENSPFILL" value="true" />
                            <PARAM name="BODYONLY" value="true" />
                            <PARAM name="OLDFONTSTYLEMODE" value="true" />
                            <PARAM name="SOURCEVIEW" value="true" />
                            <PARAM name="NAMESPACE" value="roEOP3" />
                            <PARAM name="USESLIBRARY" value="RealObjects edit-on Pro 3.1" />
                            <PARAM name="USESLIBRARYCODEBASE" value="edit-on-pro-signed.cab" />
                            <PARAM name="USESLIBRARYVERSION" value="3,1,200,0" />
                            <PARAM name="cache_option" value="plugin" />
                            <PARAM name="cache_archive" value="edit-on-pro-signed.jar,ssce.jar,tidy.jar" />
                            <PARAM name="cache_version" value="3.1.200.0,0.0.0.0,0.0.0.0" />
                            <PARAM name="ONEDITORLOADED" value="fnOnLoad"/>
                            <PARAM name="ONDATALOADED" value="TitelVoluitLoaded" />
                        </applet>
        -->
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
                        onclick="return fnOnSubmit()"/>
        </logic:notEqual>
        <logic:equal name="NaslagwerkenBeheerForm" property="naslagwerk.id" value="0">
            <html:image property="Toevoegen" value="Toevoegen" page="/images/button_toevoegen.gif"
                        onclick="return fnOnSubmit()"/>
        </logic:equal>


        <html:link action="/initNaslagwerkenBeheer" style="border:none">
            <img src="<html:rewrite page='/images/button_nieuw_naslagwerk.gif' />" border="0"
                 alt="<bean:message key="algemeen.naslagwerk.nieuw"/>"/>
        </html:link>
        <html:link forward="beheerMenu" style="border:none">
            <img src="<html:rewrite page='/images/button_menu.gif' />" border="0"
                 alt="<bean:message key="algemeen.menu"/> "/>
        </html:link>
    </td>
</tr>
</table>

</html:form>
</body>
</html:html>