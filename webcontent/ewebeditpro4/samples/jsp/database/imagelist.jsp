
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title>Library</title>
	<%@ include file="../../../ewebeditpro.jsp" %>
	<%  
		String editorname = request.getParameter("editor"); 
		if(request.getParameter("id") != null) { 
			DeleteImage(szODBCNAME, request.getParameter("id"));
		}
	%>
	<script language="JavaScript1.2">
	//var MyEditor = top.opener.eWebEditPro.event.SrcName();
	//alert (MyEditor);
		function InsertFile(nID, strTitle, strAlignment) {
			
			switch (nID) {			
				<%	String sqlquery = "SELECT media_id, media_title, media_filename, media_path FROM media_tbl ORDER BY media_title"; %>	
					<%@ include file="opendatabase.jsp" %>
					<%
					while (rs.next()) {
								Object objmedia_id = rs.getObject("media_id");
								Object objmedia_title = rs.getObject("media_title");
								Object objmedia_filename = rs.getObject("media_filename");
								Object objmedia_path = rs.getObject("media_path");
					%>	
				case "<% out.print(objmedia_id.toString()); %>": var strmedia_title =  "<% out.print(objmedia_title.toString()); %>"; var strfile_name =  "<% out.print(objmedia_filename.toString()); %>"; var strmedia_path =  "<% out.print(objmedia_path.toString()); %>" ;break;
				
				<%	} %>	
				<%@ include file="closedatabase.jsp" %>
				
			}

			
			if (!top.opener.closed){
			//insert the image.  The insertMediaFile object is documented.
		top.opener.eWebEditPro.instances.MyContent1.insertMediaFile(strmedia_path + "/" + strfile_name, 0, strmedia_title);

				top.close(); //close the library
				
			}
			else {
				alert("Your image could not be insert as the editing window has been closed.");
			}
		}
		
		function doInsert() {
		if (document.imageform.id.selectedIndex != -1) {
				InsertFile(document.imageform.id.options[document.imageform.id.selectedIndex].value, document.imageform.id.options[document.imageform.id.selectedIndex].text);
			}
			else {
				alert('You need to select an image first');
				return false;
			}
		}
	</script>
</head>
<body bgcolor="d3d3d3" leftmargin=0 topmargin=0 marginheight=0 marginwidth=0>
<table cellpadding=0 cellspacing=0 width="100%">
<tr>
	<td>
		<table cellpadding=1 cellspacing=1 width="100%">
		<form name="imageform" action="imagelist.jsp" method="post">
		<input type="Hidden" name="editor" value="<%= editorname %>">
		<input type="Hidden" name="DeleteID" value="">
		<tr>
			<td width="30%" valign="top">
				<font face="verdana" size="2"><b>Select a file:</b></font><br>
			</td>
		</tr>
		<tr>
			<td width="70%">
				<select name="id" size="8" onChange="top.preview.location.href = 'imagepreview.jsp?id=' + this.options[this.selectedIndex].value;">
					<%	sqlquery = "SELECT media_id, media_title FROM media_tbl ORDER BY media_title"; %>
					<%@ include file="opendatabase2.jsp" %>	
					<%
					while (rs2.next()) {
								Object objid2 = rs2.getObject("media_id");
								Object objtitle2 = rs2.getObject("media_title");
								%>	
						<option value="<% out.print(objid2.toString()); %>"><% out.print( objtitle2.toString()); %>
					<%	} %>	
					<%@ include file="closedatabase2.jsp" %>
				</select>
		</tr>
		<tr>
			<td colspan="2" align="center">
		
			<input type="Submit" name="Delete" value="Delete" style="font-family: Verdana;">&nbsp;&nbsp;&nbsp;<input type="button" name="button1" value="Insert" onClick="doInsert();" style="font-family: Verdana;">
			</td>
		</tr>
		</form>
		</table>
	</td>
</tr>
</table>
</body>
</html>
