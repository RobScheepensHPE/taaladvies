<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/SecurityTagLib.tld" prefix="security" %>
<security:isLoggedIn />

<html:html locale="true">
<head>
	<title>Databank Taaladvies</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="<html:rewrite page='/style/taaladvies.css' />" rel="stylesheet" type="text/css">
	<link href="<html:rewrite page='/style/taaladvies.js' />" type="text/JavaScript" />
	<script type="text/javascript" language="Javascript" src="<html:rewrite page='/style/taaladvies.js' />" ></script>
</head>

<body>

<table width="100%"><tr><td><h2>Beheerdersmenu</h2></td><td align="right"><a href="../profielen.jsp"><html:img page="/images/button_profielen.gif" border="0" /></a></td></tr></table>
<p><b>Beheer van gebruikers</b></p>
<ul>
	<li><html:link page="/do/initGebruikersBeheer">Gebruiker</html:link></li>
</ul>
<p><b>Beheer van hulptabellen</b></p>
<ul>
	<li><html:link page="/do/initDoelgroepenBeheer">Doelgroep</html:link></li>
	<li><html:link page="/do/initDoelgroepenAlgemeenBeheer">Doelgroep Algemeen</html:link></li>
	<li><html:link page="/do/initMediaBeheer">Medium</html:link></li>
	<li><html:link page="/do/initZoekomgevingenBeheer">Zoekomgeving</html:link></li>
	<li><html:link page="/do/initStatussenBeheer">Status</html:link></li>
	<li><html:link page="/do/initHerkomstenBeheer">Herkomst</html:link></li>
	<li><html:link page="/do/initRelevantiesBeheer">Relevantie</html:link></li>
	<li><html:link page="/do/initCategorienBeheer">Categorie</html:link></li>
	<li><html:link page="/do/initNaslagwerkenBeheer">Naslagwerk</html:link></li>
	<li><html:link page="/do/initBibliografienBeheer">Bibliografische categorie</html:link></li>
	<li><html:link page="/do/initSjablonenBeheer">Sjabloon</html:link></li>
</ul>
<p><b>Beheer van oproepen</b></p>
<ul>
	<li><html:link page="/admin/oproepverwijderen.jsp">Verwijderen oproep</html:link></li>
	<li><html:link page="/admin/oproepopenen.jsp">Systeemstatus oproep openen</html:link></li>
</ul>

</body>
</html:html>