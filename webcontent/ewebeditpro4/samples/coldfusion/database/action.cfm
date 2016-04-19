<!-- Ektron Inc.  Amherst NH  Copyright ©1999-2001 -->
<!--
  Name: action.cfm
   
  Descr: An action page that handle Add/Edit/Delete functions of the documents, updated the Database
  		 and then re-direct them to index page 
  
  Revision date: 2001-03-19
-->
<cfinclude template="ewebeditprodefinedsn2.cfm">
<html>
<head>
<title>eWebEditPro</title>
</head>
<body>
<cfif IsDefined("form.edit_id")> 
	<!--- in the case of editing document --->
	<cfif IsDefined("form.myContent1") AND IsDefined("form.edit_id")> 
	<!--- Update the Database and return to index.cfm for the list of documents --->
			<!--- checks to see if the image title entered in the form has either single or double quotes --->
			<!--- """" checks for double quotes in the image title --->
			<cfif '#form.edit_title#' CONTAINS "'" OR '#form.edit_title#' CONTAINS """">
				<script language="JavaScript">
					alert ('The file name can not contain any single or double quotes')
					history.back();
				</script> 
			</cfif>
			<!--- Update the Database --->
			<CFQUERY NAME="q_gethtml" DATASOURCE="#DSN#">
				UPDATE 	wysiwyg_tbl
				SET		edit_title = '#Trim(form.edit_title)#', edit_html = '#Trim(form.myContent1)#'
				WHERE	edit_id = #form.edit_id#
			</CFQUERY>
			<!--- re-direct back to the index page --->
			<script language="JavaScript"> 
				window.location.href="index.cfm?n=<cfoutput>#timeformat(now(),'hhmmss')#</cfoutput>";
			</script>
	</cfif>
	
<cfelseif IsDefined("url.delete_id")> 
<!--- in the case of deleting document --->
	<!--- Delete the document from the Database --->
	<cfquery datasource="#DSN#" name="d_editor"> 
		DELETE	FROM	wysiwyg_tbl
		WHERE	edit_id = #url.delete_id#
	</cfquery>
	<!--- re-direct back to the index page --->
	<script language="JavaScript">
		window.location.href="index.cfm?n=<cfoutput>#timeformat(now(),'hhmmss')#</cfoutput>";
	</script>
	
<cfelse> 
<!--- In the case of adding a document --->
	<!--- insert the new content to the Database --->
	<cfif IsDefined("form.myContent1")> 
		<CFQUERY NAME="q_gethtml" DATASOURCE="#DSN#">
			INSERT INTO	wysiwyg_tbl (edit_title, edit_html)
			VALUES	('#Trim(form.edit_title)#', '#Trim(form.myContent1)#')
		</CFQUERY>
		<!--- re-direct back to the index page --->
		<script language="JavaScript">
			window.location.href="index.cfm?n=<cfoutput>#timeformat(now(),'hhmmss')#</cfoutput>";
		</script>
	</cfif>
</cfif>
</body>
</html>
