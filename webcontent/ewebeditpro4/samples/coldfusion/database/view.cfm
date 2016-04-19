<!--   Ektron Inc.  Amherst NH  Copyright (©) 1999-2001 -->
<!--- 
  Name: view.cfm
   
  Descr: View HTML page for ColdFusion.  
  
  Revision date: 2001-03-19
  
--->
<cfinclude template="ewebeditprodefinedsn2.cfm">
<cfif IsDefined("url.edit_id")>
	<!--- get the document from the DB --->
	<cfquery datasource="#DSN#" name="q_editor"> 
		select	edit_title, edit_html
		from 	wysiwyg_tbl 
		where edit_id = #url.edit_id#
	</cfquery>	
</cfif>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<!--- Display the Document title at the Browser Title --->
	<title><cfif IsDefined("url.edit_id")><cfoutput>#q_editor.edit_title#</cfoutput><cfelse>eWebEditPro</cfif></title>
	<!--- Include the Style sheet here and replace the stylesheet filename if necessary --->
	<!--- <link rel='stylesheet' type='text/css' href='stylesheet.css'> --->
</head>
<body bgcolor="White">
	<table border="0" cellspacing="0" cellpadding="0" width="500">
	<tr>
	    <td>
		<cfif IsDefined("url.edit_id")>
			<!--- Display the doucment content if the URL.edit_id is provided. --->
			<cfoutput>#q_editor.edit_html#</cfoutput>
		<cfelse>
			No Document is defined.
		</cfif>
		</td>
	</tr>
	</table>
</body>
</html> 
 