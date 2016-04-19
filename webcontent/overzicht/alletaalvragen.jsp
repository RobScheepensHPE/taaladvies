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
		function overname(id){
			var agree = confirm ("Bent U zeker dat U oproep " + id + " wilt overnemen?");
			if (agree){
				
				window.location.href= '../do/overname?id=' + id;
			}
		}
	</script>
	<%	
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
	%>

</head>

<bean:define name="Gebruiker" id="gebruiker" />
<bean:define name="AlleTaalvragenForm" property="oproepen" type="java.util.ArrayList" id="oproepenbean" />
<bean:define name="AlleTaalvragenForm" property="currentindex" type="Integer" id="currentindex" />
<bean:define name="AlleTaalvragenForm" property="totalsize" type="Integer" id="totalsize" />
<body>

<%@ include file="/overzicht/navigatie.jsp" %>

<table class="borderbottom">
	<tr>
		<td class="h2style">
			Andere taalvragen in behandeling
		</td>
		
		<td class="modus" align="right">			
			<bean:message key="algemeen.modus"/>: 
			<input type="radio" name="useEopro" value="true" onclick="javascript:location.href='../do/initAlleTaalvragen?Button=EditeerModus&useEopro=true';" <logic:equal name="eopro" value="true">CHECKED</logic:equal>/><bean:message key="algemeen.metopmaak"/>
			<input type="radio" name="useEopro" value="false" onclick="javascript:location.href='../do/initAlleTaalvragen?Button=EditeerModus&useEopro=false';" <logic:notEqual name="eopro" value="true">CHECKED</logic:notEqual>/><bean:message key="algemeen.eenvoudig"/>	
		</td>
	</tr>				
</table>	
<p></p>

<table cellspacing="5" width="100%" align="center">
	<tr bgcolor="#dddddd">
		<th align="left">Oproep</th>
		<th align="left">Klant</th>
		<th align="left">Datum in</th>
		<th align="left">Deadline</th>
		<th align="left">Taaladviseur</th>
		<th align="left">Status</th>
		<th>&nbsp;</th>
	</tr>
	<logic:iterate id="oproepen" name="oproepenbean" offset="<%=String.valueOf(currentindex)%>" length="10">
		<tr>
			<td>
				<a href="../do/initOproep_TaalvragenInvoer?id=<bean:write name="oproepen" property="id" />&ro=true">
					<bean:write name="oproepen" property="id" />
				</a>
			</td>
			<td>
				<bean:write name="oproepen" property="voornaam" /> 
				<logic:notEqual name="oproepen" property="naam" value="Onbekend">
					<bean:write name="oproepen" property="naam" />
				</logic:notEqual>
			</td>
			<td><bean:write name="oproepen" property="oproepdatumString" /></td>
			<td><bean:write name="oproepen" property="deadlineString" /></td>
			<td><bean:write name="oproepen" property="gebruiker.voornaam" /> <bean:write name="oproepen" property="gebruiker.naam" /></td>
			<td><bean:write name="oproepen" property="status.omschrijving" /></td>		
			<td>
				<a href="javascript:overname(<bean:write name="oproepen" property="id" />)" border="0""><img src="<html:rewrite page='/images/button_overname.gif' />" border="0" /></a>
			</td>
		</tr>
	</logic:iterate>	
</table>
<hr />

<% 

if (totalsize.intValue() != 0) {
	if (currentindex.intValue() != 0) {
%>
		<a href="../do/initAlleTaalvragen?index=<%=currentindex.intValue() - 10%>">
			 -vorige- 
		</a>
<%
	}
%>
	<a href="../do/initAlleTaalvragen?index=0">
		-<% if (currentindex.intValue() == 0) { %><font size="+1"><%}%>
			
		1
		
		<% if (currentindex.intValue() == 0) { %></font><%}%>-
	</a>
<% }			
for (int i= 1; i < totalsize.intValue() + 1 ; i++) { %>
	<a href="../do/initAlleTaalvragen?index=<%=((i)*10)%>">
		-<% if (currentindex.intValue() == (i*10)) {%><font size="+1"><%}%>
		
		<%=i+1%>
	
		<% if (currentindex.intValue() == (i*10)) {%></font><%}%>-
	</a>
<% } %>		
<% if (currentindex.intValue() < (totalsize.intValue() *10)) {%>
	<a href="../do/initAlleTaalvragen?index=<%=currentindex.intValue() + 10%>">
		 -volgende- 
	</a>
<%
	}
%> 

</body>
</html:html>