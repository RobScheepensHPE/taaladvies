<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/SecurityTagLib.tld" prefix="security" %>

<html:html locale="true">
<head>
	<title>Databank Taaladvies</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="<html:rewrite page='/style/taaladvies.css' />" rel="stylesheet" type="text/css">
	<link href="<html:rewrite page='/style/taaladvies.js' />" type="text/JavaScript" />
</head>

<body>
<h1 align="center">Databank Taaladvies</h1>
<h2>Aanmelding</h2>

<html:errors />

<html:form action="/login">
  <table border="0" cellspacing="5" align="center">
    <tr>
      <td align="right"><html:img page="/images/label_gebruikersnaam.gif" /></td>
      <td align="left"><html:text property="login" /></td>
    </tr>
    <tr> 
      <td align="right"><html:img page="/images/label_paswoord.gif" /></td>
      <td align="left"><html:password property="paswoord" /></td>
    </tr>
    <tr>
      
	  <td align="right"><html:image property="submit" page="/images/button_inloggen.gif" /></td>
  	  <td align="left"><a href="javascript:document.forms[0].reset()" border="0"><img src="<html:rewrite page='/images/button_wissen.gif' />" border="0"></a></td>	  
    </tr>
  </table>
  
</html:form>

</body>
</html:html>
