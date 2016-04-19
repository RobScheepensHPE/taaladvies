<!--   Ektron Inc.  Amherst NH  Copyright (©) 1999-2001 -->
<!--- 
  Name: index.cfm
   
  Descr: Start page or Index page for ColdFusion Integration example.
  
  Revision date: 2001-04-06
  
--->
<cfinclude template="ewebeditprodefinedsn2.cfm">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title>Ektron WYSIWYG Editor</title>
</head>
<script language="JavaScript1.2" src="../../../ewebeditpro.js"></script>
<body bgcolor="White">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td colspan="3"><font face="verdana, arial, helvetica" size="2">Ektron, Inc. <br>5 Northern Blvd <br>Amherst, NH 03031, USA <br>Phone: +1 603-594-0249<br>Fax: +1 603-594-0258
</tr>
<tr>
	<td colspan="3">&nbsp;</td>
</tr>
<tr>
	<td colspan="3" align="center"><font size="3" face="verdana, arial, helvetica"><b>Ektron eWebEditPro ColdFusion Integration Example</b></font></td>
</tr>
<tr>
	<td valign="top"></td>
	<td align="center">
		<!--- Get document content from the Database --->
		<cfquery datasource="#DSN#" name="q_editor"> 
			SELECT 	edit_id, edit_title 
			FROM 	wysiwyg_tbl
		</cfquery>
		
		<table cellpadding="3" cellspacing="3" width="100%" border="1"> 
			
			<!--- Display list of avaiabled doucment in a form of table --->
			<tr>
				<td bgcolor="Black"><font size="2" face="verdana, arial, helvetica" color="White"><b>ID</b></font></td>
				<td bgcolor="Black" width="75%"><font size="2" face="verdana, arial, helvetica" color="White"><b>Title</b></font></td>
				<td bgcolor="Black" nowrap align="center"><font size="2" face="verdana, arial, helvetica" color="White"><b>Available Actions <a href="edit.cfm"><font color="lightblue">[Add]</a></font></b></font></td>
			</tr>
			<cfif q_editor.recordcount> 
				<!--- If there are documents in the Database --->
				<cfset nCounter = 1>
				<cfset sColor = "white">
				<cfoutput query="q_editor">
					<!--- obtain alternative cell bgcolor --->
					<cfif variables.nCounter mod 2> 
						<cfset sColor = "white">
					<cfelse>
						<cfset sColor = "lightgrey">
					</cfif>
					<tr>
						<td bgcolor="#variables.sColor#"><font size="2" face="verdana, arial, helvetica">#edit_id#</font></td>
						<td bgcolor="#variables.sColor#"><font size="2" face="verdana, arial, helvetica">#edit_title#</font></td>
						<td bgcolor="#variables.sColor#" align="center"><a href="edit.cfm?edit_id=#q_editor.edit_id#"><font size="2" face="verdana, arial, helvetica">Edit</font></a>&nbsp;|&nbsp;<a href="view.cfm?edit_id=#q_editor.edit_id#"><font size="2" face="verdana, arial, helvetica">View</font></a>&nbsp;|&nbsp;<a href="action.cfm?delete_id=#q_editor.edit_id#"><font size="2" face="verdana, arial, helvetica">Delete</font></a></td>	
					</tr>
					<cfset nCounter = nCounter + 1>
				</cfoutput>	
			<cfelse> 
				<!--- if there are no document in the Database --->
				<tr>
					<td bgcolor="white" colspan="3"><font size="2" face="verdana, arial, helvetica">No Document is available.  Please add a new document.</font></td>
				</tr>
			</cfif>
		</table>
	</td>
</tr>
<tr>
	<td colspan="3">&nbsp;</td>
</tr>
<tr>
	<td colspan="3"><font size="2" face="verdana, arial, helvetica"><b>Note:</b></font></td>
</tr>
<tr>
	<td colspan="3"><font size="2" face="verdana, arial, helvetica">Add - Insert a new document into the Database</font></td>
</tr>
<tr>
	<td colspan="3"><font size="2" face="verdana, arial, helvetica">Edit - Modify a document and update the Database</font></td>
</tr>
<tr>
	<td colspan="3"><font size="2" face="verdana, arial, helvetica">View - View the document in HTML</font></td>
</tr>
<tr>
	<td colspan="3"><font size="2" face="verdana, arial, helvetica">Delete - Delete a document from the Database</font></td>
</tr>
</table>
</body>
</html>
