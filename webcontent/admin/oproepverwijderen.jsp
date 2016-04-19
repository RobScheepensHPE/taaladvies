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
	
	<script>
		function verwijder(){
			var agree = confirm ("Bent U zeker dat U oproep " + document.verwijderenform.id.value + " wilt verwijderen?");
			if (agree){
				document.verwijderenform.submit(); 
			}
		}
	</script>
	
	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>		
</head>

<body>

	<h2>Verwijderen <i>Oproep</i></h2>

<html:errors />

<form method="post" action="../do/oproepVerwijderen" name="verwijderenform">
	<table cellspacing="5">
		<tr>
			<td>Geef oproepnummer in: </td>
			<td><input type="text" name="id" /></td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<a href="javascript:verwijder();" border="0"><img src="<html:rewrite page='/images/button_verwijder.gif' />" border="0"></a>
				<a href="../admin/beheerdermenu.jsp" border="0"><img src="<html:rewrite page='/images/button_menu.gif' />" border="0" /></a>
			</td>
		</tr>	
	</table>
</form>
</body>
</html:html>

