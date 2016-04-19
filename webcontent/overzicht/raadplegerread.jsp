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

	<%	response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>		

</head>

<body>



	<logic:present name="RaadplegerReadForm" property="taalvraag">
		<h2>BEKIJKEN Taalvraag</h2>
		<table>
			<logic:present name="RaadplegerReadForm" property="taalvraag.titel">
			<logic:notEqual name="RaadplegerReadForm" property="taalvraag.titelHTML" value="">
			<logic:notEqual name="RaadplegerReadForm" property="taalvraag.titelHTML" value="<p></p>">	
				<tr>
					<td valign="top" width="150"><b>Titel  </b></td>
					<td>		
						<bean:write name="RaadplegerReadForm" property="taalvraag.titelHTML" filter="false" />
					</td>
				</tr>
			</logic:notEqual>
			</logic:notEqual>
			</logic:present>
			<logic:present name="RaadplegerReadForm" property="taalvraag.vraag">
			<logic:notEqual name="RaadplegerReadForm" property="taalvraag.vraag" value="">
				<tr>
					<td valign="top"><b>Vraag  </b></td>
					<td>		
						<bean:write name="RaadplegerReadForm" property="taalvraag.vraag" filter="false" />
					</td>
				</tr>
			</logic:notEqual>
			</logic:present>
			<logic:present name="RaadplegerReadForm" property="taalvraag.antwoord">
			<logic:notEqual name="RaadplegerReadForm" property="taalvraag.antwoord" value="">
				<tr>
					<td valign="top"><b>Antwoord  </b></td>
					<td>		
						<bean:write name="RaadplegerReadForm" property="taalvraag.antwoordHTML" filter="false" />
					</td>
				</tr>
			</logic:notEqual>
			</logic:present>					
			<logic:present name="RaadplegerReadForm" property="taalvraag.toelichtingHTML">
			<logic:notEqual name="RaadplegerReadForm" property="taalvraag.toelichtingHTML" value="">
			<logic:notEqual name="RaadplegerReadForm" property="taalvraag.toelichtingHTML" value="<p></p>">
				<tr>
					<td valign="top"><b>Toelichting  </b></td>
					<td>			
						<bean:write name="RaadplegerReadForm" property="taalvraag.toelichtingHTML" filter="false" />	
					</td>
				</tr>
			</logic:notEqual>
			</logic:notEqual>
			</logic:present>				
			<logic:present name="RaadplegerReadForm" property="taalvraag.bijzonderheidHTML">
			<logic:notEqual name="RaadplegerReadForm" property="taalvraag.bijzonderheidHTML" value="">
			<logic:notEqual name="RaadplegerReadForm" property="taalvraag.bijzonderheidHTML" value="<p></p>">	
				<tr>
					<td valign="top"><b>Bijzonderheid  </b></td>
					<td>	
						<bean:write name="RaadplegerReadForm" property="taalvraag.bijzonderheidHTML" filter="false" />
					</td>
				</tr>
			</logic:notEqual>
			</logic:notEqual>
			</logic:present>
		</table>
			
		<hr align="left" width="80%" />
	</logic:present>

	<logic:present name="RaadplegerReadForm" property="tekst">
	<h2>BEKIJKEN Tekst</h2>	
	<bean:define name="RaadplegerReadForm" property="tekst.tekstblokken" id="tekstblokkenbean" />	
		

		<p align="center">
			<bean:write name="RaadplegerReadForm" property="tekst.titelHTML" filter="false" />
		</p>
		<hr />
		<table align="center">
			<logic:present name="RaadplegerReadForm" property="tekst.tekstblokken">
				<logic:iterate name="RaadplegerReadForm" property="tekst.tekstblokken" id="tekstblokkeni">
					<tr>
						<td valign="top"><bean:write name="tekstblokkeni" property="titelHTML" filter="false" /></td>
						<td><bean:write name="tekstblokkeni" property="tekstblokHTML" filter="false" /><hr /></td>
					</tr>
				</logic:iterate>
			</logic:present>
		</table>
	</logic:present>
	
	<p>
		<html:link page="/do/initZoekenExtern"><html:img page="/images/button_nieuwe_zoekopdracht.gif" border="0" /></html:link>
		<img src="../images/button_terug_naar_resultaat.gif" border="0" onclick="javascript:history.back()"/>
		<img src="../images/button_print.gif" border="0" onclick="javascript:window.print()" />
	</p>

</body>
</html:html>