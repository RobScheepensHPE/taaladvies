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

<bean:define name="WebreferentiesInvoerForm" property="webreferenties" type="java.util.ArrayList" id="webreferentiesbean" />

<h2>BEKIJKEN Koppeling</h2>

<html:form method="post" action="/webreferentiesInvoer">

				<table cellspacing="5" width="100%">
					<tr>
						<td colspan="3">
							<logic:present name="WebreferentiesInvoerForm" property="taalvraag">
									<a class="bigger" href="../do/initTaalvragenInvoer?id=<bean:write name="WebreferentiesInvoerForm" property="taalvraag.id" />&ro=true<logic:equal name="WebreferentiesInvoerForm" property="adm" value="true">&adm=true</logic:equal>">
										<logic:notEqual name="WebreferentiesInvoerForm" property="taalvraag.titel" value="">
											<logic:notEqual name="WebreferentiesInvoerForm" property="taalvraag.titel" value="<p></p>">
												<bean:write name="WebreferentiesInvoerForm" property="taalvraag.titel" filter="true" />
											</logic:notEqual>
										</logic:notEqual>
										<logic:equal name="WebreferentiesInvoerForm" property="taalvraag.titel" value="">
											<bean:write name="WebreferentiesInvoerForm" property="taalvraag.vraag" filter="true" />
										</logic:equal>
										<logic:equal name="WebreferentiesInvoerForm" property="taalvraag.titel" value="<p></p>">
											<bean:write name="WebreferentiesInvoerForm" property="taalvraag.vraag" filter="true" />
										</logic:equal>				
									</a>
							</logic:present>
							<logic:present name="WebreferentiesInvoerForm" property="tekst">
								<a class="bigger" href="../do/initOproep_TekstInvoer?id=<bean:write name="WebreferentiesInvoerForm" property="tekst.oproepId" />&ro=true">
									<bean:write name="WebreferentiesInvoerForm" property="tekst.titel" filter="true" />
								</a>
								<html:hidden name="WebreferentiesInvoerForm" property="tekst.id" />
							</logic:present>						
						</td>
					</tr>							
					<tr bgcolor="#dddddd">
						<th align="left">Omgeving</th>
						<th align="left">URL</th>
						<th align="left">Toelichting</th>
					</tr>		
					<logic:iterate id="webreferenties" name="webreferentiesbean">
						<tr>
							<td valign="top"><bean:write name="webreferenties" property="omgeving" filter="true" /></td>
							<td valign="top"><a href="<bean:write name="webreferenties" property="url" filter="true" />" target="blank">link</a></td>
							<td valign="top"><bean:write name="webreferenties" property="toelichtingHTML" filter="false" /></td>
						</tr>
					</logic:iterate>		
				</table>
</html:form>
</body>
</html:html>