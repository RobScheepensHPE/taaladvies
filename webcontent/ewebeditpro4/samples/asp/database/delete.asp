<!-- #include file="functions.asp" -->

<% ' contentDelete.asp
   ' This script confirms that you are indeed intending to delete
   ' the specified row from the database.  It performs a lookup
   ' to display the title.  %>

<% ' The id variable is requested from the querystring 
   ' and stored for use in the database query
   Dim id
   id = Request.QueryString("id") %>
   
<% ' Assuming that the id is non-empty, the content is 
   ' retrieved from the database.  Assuming that the query
   ' is non-empty (ie the row exists), the title of the
   ' document is display for confirmation. 
 
If id <> "" Then 
	If trim(Request.Form("btnSubmit")) = "Cancel" Then 
		Response.Redirect("index.asp")

	ElseIf trim(Request.Form("btnSubmit")) = "Yes" Then
		' Assuming a non-empty id, perform SQL statement removing
		' row from the databse
			DBConnect "DELETE FROM wysiwyg_tbl WHERE edit_id = " & id

		' Redirect to the table of contents page 
			Response.Redirect("index.asp")
	
	Else
		' Retrieve title from the table and display delete confirmation
			DBConnect "SELECT * FROM wysiwyg_tbl WHERE edit_id = " & id
			If not objRS.EOF then %>

			<html>
			<head>
				<title>Delete File</title>
			</head>
	
			<body>
				<font face="Verdana" size="4" color="black">
					Delete Document
				</font>
		
				<br><br>
		
				<font face="Verdana" size=2>
					<% ' A message displaying the title of the document,
					   ' as well as the date and time of creation or last
					   ' modification is shown.  %> 
				   
						Do you wish to permanently delete the document
						titled '<% = objRS("edit_title") %>'?.
					
						<br><br>
				
					<% ' If the user clicks the Yes link, it passes the 
					   ' document ID back into the delete.asp script,
					   ' which actually deletes the file.  Clicking
					   ' cancel will return the user to the main table of
					   ' contents. %>
				
						<form action="delete.asp?id=<% = id %>" method=post>   
							<input type=submit name="btnSubmit" value="    Yes    ">
							<input type=submit name="btnSubmit" value="Cancel">
						</form>
				</font>
			</body>
		</html>
		<% End If %>

		<% ' At the end of each page's script portion, the DBClose 
		   ' function ought to be called to close and destroy the
	       ' recordset objects
			DBClose %>

	<% End If %>
<% End If %>