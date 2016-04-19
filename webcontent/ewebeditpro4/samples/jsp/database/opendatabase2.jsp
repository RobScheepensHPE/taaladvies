<%
Connection con2 = null;
Statement stmt2 = null;
ResultSet rs2 = null;
ResultSetMetaData rsmd2 = null;

try {
	// Load the jdbc driver
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	// Connect to the database
	con2 = DriverManager.getConnection("jdbc:odbc:"+szODBCNAME);
	// Create a statement object
	stmt2 = con.createStatement();
	// Execute query	
	
	rs2 = stmt2.executeQuery(sqlquery);
	rsmd2 = rs2.getMetaData();
%>	