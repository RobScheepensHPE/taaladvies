<!-- #include file="functions.asp" -->

<% ' contentView.asp 
   ' This script retrieves and displays a document from the database.  The id
   ' contains the row number for the document to be displayed.
   
   Dim id
   id = Request.Querystring("id") %>

<% ' Assuming the id is non-empty, retrieves the document from the database. 
   If id <> "" then
	DBConnect "SELECT * FROM wysiwyg_tbl WHERE edit_id = " & id %>
	
<% ' Assuming that the recordset is non-empty (ie the document was found), the
   ' the basic HTML structure is set up and the title and contents displayed.
   If Not objRS.EOF Then %>
<html>
<head>
	<title><% = objRS("edit_title") %></title>
</head>
		
<body>
	<% = objRS("edit_html") %>
</body>
			
</html>
<% End If %>
		
	<% ' At the end of each page's script portion, the DBClose 
	   ' function ought to be called to close and destroy the
	   ' recordset objects
		DBClose %>

<% End If %>
