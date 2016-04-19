<!-- #include file="functions.asp" -->

<% ' Index.asp (March 2001)
   ' This collection of ASP files demonstrates the integration
   ' and typical use capabilities of eWebEditPro.  
   '
   ' This page retrieves a list of documents stored in the 
   ' database (using an ADODB object). %>

<html>

<head>
	<title>Ektron eWebEditPro Examples</title>
</head>

<body>

<font face="Verdana" size=3>
	<b>Ektron's eWebEditPro ASP Database Sample</b><br>
</font>

<font face="Verdana" size=2>
<a href="http://www.ektron.com">Ektron, Inc.</a> <br>
5 Northern Blvd <br>
Amherst, NH 03031, USA<br> 
Phone: +1 603-594-0249<br>
Fax: +1 603-594-0258  <br>
</font>

<br>

<div align=right>
<font face="Verdana" size=2>
[ <font color="lightgrey">Update Content</font> ]
</font>
</div>

<br>

<table width=100% border="1" cellspacing="2" cellpadding="5">
<tr>
	<td bgcolor="black" width=5% align="center"> 
		<font face="Verdana" size="2" color="white">
			<b>ID</b>
		</font>
	</td>
	
	<td bgcolor="black" width=60%>
		<font face="Verdana" size="2" color="white">
			<b>Title</b>
		</font>
	</td>
				
	<td bgcolor="black" width="35%" align="center">
		<font face="Verdana" size="2" color="white">
			<b>Available Actions</b> [<a href="new.asp"><font color="lightblue">Add</font></a>]
		</font>
	</td>
</tr>

<% ' This portion of code creates and populate a table based 
   ' the results of the database query.
   
   Dim colorCounter, tableColor, SQL
   SQL = "SELECT * FROM wysiwyg_tbl ORDER BY edit_id" 
   DBConnect SQL %>

<%  ' The While loop iterates through the recordset (objRS)
    ' and generates a new row in the table for each row in
    ' the database. The colorCounter variable is used to
    ' create alternating color patterns for the output table.  %>
	
	<% While Not objRS.EOF %>
	
	<% If colorCounter Mod 2 = 0 Then tableColor = "white" Else tableColor = "lightgrey" %>
	<tr>
		<td bgcolor="<% = tableColor %>" width=15 align="center">
			<font face="Verdana" size="2">
				<% Response.Write(objRS("edit_id")) %>
			</font>
		</td>
		
		<td bgcolor="<% = tableColor %>">
			<font face="Verdana" size="2">
				<% Response.Write(objRS("edit_title")) %>
			</font>
		</td>
						
		<td bgcolor="<% = tableColor %>" align="center">
			<font face="Verdana" size="2">
				<% ' This section displays links to each of the content 
				   ' pages - one for deleting, editing, and viewing a
				   ' document.  The id variable is passed through
				   ' the querystring.  %>
				<a href="edit.asp?id=<% = objRS("edit_id") %>">Edit</a>&nbsp;|
				<a href="view.asp?id=<% = obJRS("edit_id") %>">View</a>&nbsp;|
				<a href="delete.asp?id=<% = objRS("edit_id") %>">Delete</a>&nbsp;
			</font>
		</td>
	</tr>
	<% ' Advanced the recordset, and increments the row color
		objRS.MoveNext
		colorCounter = colorCounter + 1 %>
<% Wend %>

<% ' At the end of each page's script portion, the DBClose 
   ' function ought to be called to close and destroy the
   ' recordset objects    
    DBClose %>
    
</table>

<br>

<font face="Verdana" size=2>
<b>Note: </b><br>
Add - Insert a new document into the Database <br>
Edit - Modify a document and update the Database <br>
View - View the document in HTML <br>
Delete - Delete a document from the Database <br>
</font>


</body>
</html>