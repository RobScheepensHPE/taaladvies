<%@ page import="java.sql.*" %>
<%@ include file="../../../ewebeditpro.jsp" %>

<%
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	String sqlquery = "";
	ResultSetMetaData rsmd = null;
	
	try {
		// Load the jdbc driver
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		// Connect to the database
		con = DriverManager.getConnection("jdbc:odbc:"+szODBCNAME);
		// Create a statement object
		stmt = con.createStatement();
		String szEditId = request.getParameter("id");
				sqlquery = "DELETE FROM wysiwyg_tbl WHERE edit_id = " + szEditId;
			stmt.executeUpdate(sqlquery);
	// rsmd = rs.getMetaData();
	
	}
	catch (ClassNotFoundException cnfe) {
		out.println(cnfe);
	}
	catch (SQLException sqle) {
		out.println(sqle);
	}
	finally {
		try {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (con != null) con.close();
		}
		catch (SQLException e) {
			// ignore
		}
	}

%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<script language="JavaScript1.2">
		function gotopage () {
			var place='index.jsp?'; 
			location.href = place;
		}
	</script>
</head>

<body>

<script language="JavaScript1.2">
	gotopage ();
</script>

</body>
</html>
