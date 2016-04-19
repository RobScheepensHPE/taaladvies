
<%@ include file="../../../ewebeditpro.jsp" %>

<%
		String strSQL = "";
		String szEditId = request.getParameter("id");
		String szContent = request.getParameter("MyContent1"); // this is the content from the editor
		String szTitle = request.getParameter("edit_title"); // title of the content
		szContent =  "'" + replaceString( szContent, "\'", "\'\'" ) + "'";
		szTitle =  "'" + replaceString( szTitle, "\'", "\'\'" ) + "'";
		if(request.getParameter("id") != null)
		{
			strSQL = "UPDATE wysiwyg_tbl SET edit_title = " + szTitle + ", edit_html = " + szContent + " WHERE edit_id = " + szEditId;
		}
		else {
			strSQL = "INSERT INTO wysiwyg_tbl (edit_title, edit_html) VALUES (" + szTitle + "," + szContent + ")";
		  } 
		  
	 actionDatabase(szODBCNAME, "", strSQL, "write");
	 
	 
%>


<jsp:forward page = "index.jsp" />

