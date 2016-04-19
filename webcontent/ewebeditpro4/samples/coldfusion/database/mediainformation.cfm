<!--- Copyright 2000-2001, Ektron, Inc. --->
<!--- Revision Date:23-Mar-2001 --->

<cfinclude template="ewebeditprodefinedsn2.cfm">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<meta http-equiv="pragma" content="no-cache">
	<title>Media Information</title>
</head>
<body bgcolor="d3d3d3">

<cfif IsDefined("URL.filesize")>
	<table width="100%" height="100%">
		<tr>
			<td>
			<font face="arial" size="2">
				<b>File Information:</b>
			</font>
			</td>
		</tr>
		<tr>
			<td>
			<font face="arial" size="2">
				Filelength: <cfoutput>#url.filesize#</cfoutput> Bytes
			</font>
			</td>
		</tr>
		<tr>
			<td>
			<font face="arial" size="2">
				Width: <cfoutput>#url.width#</cfoutput>
			</font>
			</td>
			<td>
			<font face="arial" size="2">
				Height: <cfoutput>#url.height#</cfoutput>
			</font>
			</td>
		</tr>
	</table>
<cfelse>
	<table width="100%" height="100%">
		<tr>
			<td>
			<font face="arial">
				<b>File Information:</b>
			</font>
			</td>
		</tr>
		<tr>
			<td>
			<font face="arial">
				Not Available
			</font>
			</td>
		</tr>
	</table>
</cfif>

</body>
</html>
