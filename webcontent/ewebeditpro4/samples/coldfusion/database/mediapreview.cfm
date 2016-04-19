<!--- Copyright 2000-2001, Ektron, Inc. --->
<!--- Revision Date:23-Mar-2001 --->

<cfinclude template="ewebeditprodefinedsn2.cfm">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<meta http-equiv="pragma" content="no-cache">
	<title>Media Preview</title>
</head>
<body bgcolor="d3d3d3">

<cfif IsDefined("URL.media_id")>  <!--- someone clicked on imagelist --->
	<CFQUERY NAME="q_media" DATASOURCE="#DSN#">
		SELECT    media_path, media_filename as file_name, media_title
		FROM      media_tbl
		WHERE     media_id = #url.media_id#
	</cfquery>
	<cfoutput>
		<a href="#q_media.media_path##q_media.file_name#">Preview #q_media.media_title#</a>
	</cfoutput>
<cfelseif IsDefined("URL.medianame")>
	<cfoutput>
		<a href="file:///#URL.medianame#">Preview #URL.medianame#</a>
	</cfoutput>
<cfelse>
	<table width="100%" height="100%">
		<tr>
			<td align="center">
			<font face="arial">
				<h3>Preview</h3>
			</font>
			</td>
		</tr>
	</table>
</cfif>

</body>
</html>
