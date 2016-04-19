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

	<script language="javascript">

</script>


</head>

<body>

<bean:define name="Result2Form" property="oproepen1" type="java.util.ArrayList" id="oproepenbean1" />
<bean:define name="Result2Form" property="currentindex1" type="Integer" id="currentindex1" />
<bean:define name="Result2Form" property="totalsize1" type="Integer" id="totalsize1" />
<bean:define name="Result2Form" property="oproepen2" type="java.util.ArrayList" id="oproepenbean2" />
<bean:define name="Result2Form" property="currentindex2" type="Integer" id="currentindex2" />
<bean:define name="Result2Form" property="totalsize2" type="Integer" id="totalsize2" />

<table width="80%" border="0">
	<tr>
		<th><html:link page="/do/initEigenTaalvragen"><html:img page="/images/link_inbehandeling.gif" border="0" /></html:link></th>
		<th><html:link page="/do/initOproep_TaalvragenInvoer"><html:img page="/images/link_nieuweoproeptaalvragen.gif" border="0" /></html:link></th>
		<th><html:link page="/do/initOproep_TekstInvoer"><html:img page="/images/link_nieuweoproeptekst.gif" border="0" /></html:link></th>
		<th><html:link page="/do/initZoeken"><html:img page="/images/button_zoek.gif" border="0" /></html:link></th>
		<th><html:link page="/profielen.jsp"><html:img page="/images/button_profielen.gif" border="0" /></html:link></th>
	</tr>
</table>
<br>

<h2>Resultaat zoekopdracht oproepen</h2>

<table cellspacing="5" width="100%" align="center">
	<tr bgcolor="#dddddd">
		<th align="left">Oproep</th>
		<th align="left">Klant</th>
		<th align="left">Datum in</th>
		<th align="left">Deadline</th>
		<th align="left">Taaladviseur</th>
		<th align="left">Status</th>
	</tr>
	<logic:iterate id="oproepen1" name="oproepenbean1" offset="<%=String.valueOf(currentindex1)%>" length="10">
		<tr>
			<td>
				<a href="../do/initOproep_TaalvragenInvoer?id=<bean:write name="oproepen1" property="id" />&ro=true&adm=true">
					<bean:write name="oproepen1" property="id" />
				</a>
			</td><td>
				<bean:write name="oproepen1" property="voornaam" /> 
				<logic:notEqual name="oproepen1" property="naam" value="Onbekend">
					<bean:write name="oproepen1" property="naam" />
				</logic:notEqual>
			</td>
			<td><bean:write name="oproepen1" property="oproepdatumString" /></td>
			<td><bean:write name="oproepen1" property="deadlineString" /></td>
			<td><bean:write name="oproepen1" property="gebruiker.voornaam" /> <bean:write name="oproepen1" property="gebruiker.naam" /></td>
			<td><bean:write name="oproepen1" property="status.omschrijving" /></td>		
		</tr>
	</logic:iterate>	
</table>
<hr />

<% 

if (totalsize1.intValue() != 0) {
	if (currentindex1.intValue() != 0) {
%>
		<a href="../do/change2?index=<%=currentindex1.intValue() - 10%>&Change1=true">
			 -vorige- 
		</a>
<%
	}
%>
	<a href="../do/change2?index=0&Change1=true">
		-<% if (currentindex1.intValue() == 0) { %><font size="+1"><%}%>
			
		1
		
		<% if (currentindex1.intValue() == 0) { %></font><%}%>-
	</a>
<% }			
for (int i= 1; i < totalsize1.intValue() + 1 ; i++) { %>
	<a href="../do/change2?index=<%=((i)*10)%>&Change1=true">
		-<% if (currentindex1.intValue() == (i*10)) {%><font size="+1"><%}%>
		
		<%=i+1%>
	
		<% if (currentindex1.intValue() == (i*10)) {%></font><%}%>-
	</a>
<% } %>		
<% if (currentindex1.intValue() < (totalsize1.intValue() *10)) {%>
	<a href="../do/change2?index=<%=currentindex1.intValue() + 10%>&Change1=true">
		 -volgende- 
	</a>
<%
	}
%> 


<table cellspacing="5" width="100%" align="center">
	<tr bgcolor="#dddddd">
		<th align="left">Oproep</th>
		<th align="left">Titel</th>
		<th align="left">Startdatum</th>
		<th align="left">Deadline</th>
		<th align="left">Taaladviseur</th>
		<th align="left">Status</th>
	</tr>
	<logic:iterate id="oproepen2" name="oproepenbean2" offset="<%=String.valueOf(currentindex2)%>" length="10">
		<tr>
			<td>
				<a href="../do/initOproep_TekstInvoer?id=<bean:write name="oproepen2" property="id" />&ro=true">
					<bean:write name="oproepen2" property="id" />
				</a>
			</td>
			<td><bean:write name="oproepen2" property="tekst.titelHTML" filter="false" /></td>
			<td><bean:write name="oproepen2" property="oproepdatumString" /></td>
			<td><bean:write name="oproepen2" property="deadlineString" /></td>
			<td><bean:write name="oproepen2" property="gebruiker.voornaam" /> <bean:write name="oproepen2" property="gebruiker.naam" /></td>
			<td><bean:write name="oproepen2" property="status.omschrijving" /></td>		
		</tr>
	</logic:iterate>	
</table>
<hr />

<% 

if (totalsize2.intValue() != 0) {
	if (currentindex2.intValue() != 0) {
%>
		<a href="../do/change2?index=<%=currentindex2.intValue() - 10%>&Change2=true">
			 -vorige- 
		</a>
<%
	}
%>
	<a href="../do/change2?index=0&Change2=true">
		-<% if (currentindex2.intValue() == 0) { %><font size="+1"><%}%>
			
		1
		
		<% if (currentindex2.intValue() == 0) { %></font><%}%>-
	</a>
<% }			
for (int i= 1; i < totalsize2.intValue() + 1 ; i++) { %>
	<a href="../do/change2?index=<%=((i)*10)%>&Change2=true">
		-<% if (currentindex2.intValue() == (i*10)) {%><font size="+1"><%}%>
		
		<%=i+1%>
	
		<% if (currentindex2.intValue() == (i*10)) {%></font><%}%>-
	</a>
<% } %>		
<% if (currentindex2.intValue() < (totalsize2.intValue() *10)) {%>
	<a href="../do/change2?index=<%=currentindex2.intValue() + 10%>&Change2=true">
		 -volgende- 
	</a>
<%
	}
%> 
<p>
	<html:link page="/do/initSearch2"><html:img page="/images/button_nieuwe_zoekopdracht.gif" border="0" /></html:link>
	<html:link page="/overzicht/search2.jsp"><html:img page="/images/button_verfijn_zoekopdracht.gif" border="0" /></html:link>
</p>


</body>
</html:html>