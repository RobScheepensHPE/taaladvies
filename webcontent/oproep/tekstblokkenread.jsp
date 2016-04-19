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
	<script language="JavaScript">
	</script>
	<%	
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>
</head>
<body> 

<h2>BEKIJKEN Tekstblokken</h2>

<html:form method="post" action="/tekstblokkenInvoer" onsubmit="return fnOnSubmit()">

				<table cellspacing="5">
					<tr>
						<td colspan="4">
							<a class="bigger" href="../do/initOproep_TekstInvoer?id=<bean:write name="TekstblokkenInvoerForm" property="tekst.oproepId" />&ro=true">							
								<bean:write name="TekstblokkenInvoerForm" property="tekst.titel" filter="true" />
							</a>	
							<html:hidden name="TekstblokkenInvoerForm" property="tekst.oproep.id" />
						</td>													
					</tr>
					<tr><td colspan="4"><hr /></td></tr>
					<tr>
						<td valign="top"><html:img page="/images/label_volgnummer.gif" /></td>
						<td colspan="3"><bean:write name="TekstblokkenInvoerForm" property="tekstblok.volgnummer" /></td>
					</tr>
					<tr>	
						<td valign="top"><html:img page="/images/label_bloktitel.gif" /></td>
						<td colspan="3"><bean:write name="TekstblokkenInvoerForm" property="tekstblok.titelHTML" filter="false" /></td>
					</tr>
					<tr>
						<td valign="top"><html:img page="/images/label_tekstblok.gif" /></td>	
						<td colspan="3"><bean:write name="TekstblokkenInvoerForm" property="tekstblok.tekstblokHTML" filter="false" /></td>
					</tr>
				</table>

</html:form>
</body>
</html:html>