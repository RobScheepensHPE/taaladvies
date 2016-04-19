<%@ include file="../../../ewebeditpro.jsp" %>
<!---  Index.jsp modify: 2001-03-19
   ' This collection of jsp files demonstrates the integration
   ' and typical use capabilities of eWebEditPro.  
   ' This page retrieves a list of documents stored in the 
   ' database (using an ADODB object).
 --->
<html>
<head>
	<title>Ektron eWebEditPro Examples</title>
</head>
<body>
<table width=100% border="0" cellspacing="2" cellpadding="5">
	<tr>
		<td><font face="verdana, arial, helvetica" size="3">Ektron's eWebEditPro Demo</font></td>
	</tr>
	<tr>
		<td><font face="verdana, arial, helvetica" size="2">Ektron Inc, 5 Northern Blvd, Amherst NH, 03031, 603-594-0249
	</tr>
	<tr>
		<td align="center"><font size="3" face="verdana, arial, helvetica"><b>Ektron eWebEditPro JSP Database Example</b></font></td>
	</tr>
	<tr>
	<td align="right"></td>
	</tr>
</table>
<table width=100% border="1" cellspacing="2" cellpadding="5">
	<tr>
		<td bgcolor="black" width=5% align="center"> 
			<font face="verdana, arial, helvetica" size="2" color="white">
				<b>ID</b>
			</font>
		</td>
		
		<td bgcolor="black" width=50%>
			<font face="verdana, arial, helvetica" size="2" color="white">
				<b>Title</b>
			</font>
		</td>
		<td bgcolor="black" width="35%" align="center">
			<font face="verdana, arial, helvetica" size="2" color="white">
				<b>Available Actions &nbsp;&nbsp; <a href="edit.jsp"><font color="lightblue">[Add]</font></a></b>
			</font>
		</td>
	</tr>
	<%	String sqlquery = "SELECT edit_id, edit_title FROM wysiwyg_tbl "; %>	
		<%@ include file="opendatabase.jsp" %>
		<%
		while (rs.next()) {
					Object obj = rs.getObject("edit_id");
					Object objtitle = rs.getObject("edit_title");
		%>	
			<tr>			
			<td width=15 align="center">
				<font face="verdana, arial, helvetica" size="2">
					<% out.println(obj.toString()); %>
				</font>
			</td>
			
			<td>
				<font face="verdana, arial, helvetica" size="2">
					<% out.println(objtitle.toString()); %>
				</font>
			</td>
				
			<td align="center">
				<font face="verdana, arial, helvetica" size="2">
					<!--- ' This section displays links to each of the content 
					   ' pages - one for deleting, editing, and viewing a
					   ' document.  The id variable is passed through
					   ' the querystring. --->
					<a href="edit.jsp?id=<% out.println(obj.toString()); %>">Edit</a>&nbsp;|
					<a href="view.jsp?id=<% out.println(obj.toString()); %>">View</a>&nbsp;|
					<a href="delete.jsp?id=<% out.println(obj.toString()); %>">Delete</a>&nbsp;
				</font>
			</td>
			</tr>
	<%	} %>	<!--- End of the While loop --->
	<%@ include file="closedatabase.jsp" %>
</table>
<br><br>
<font face="verdana, arial, helvetica" size=2>
	The following options are available:<br>
	<br>
	&nbsp;&nbsp;<b>Edit</b> <i>(edit.jsp) </i> allows you to change existing content and save it to the database<br>
	&nbsp;&nbsp;<b>View</b> <i>(view.jsp) </i>displays the content from the database as a standalone page<br>
	&nbsp;&nbsp;<b>Delete</b> <i>(delete.jsp) </i>removes content from the database<br>
	&nbsp;&nbsp;<b>Add</b> <i>(edit.jsp) </i>allows the addition of new content to the database
<br><br>
</body>
</html>