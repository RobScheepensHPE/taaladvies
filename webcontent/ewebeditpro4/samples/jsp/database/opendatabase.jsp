<%
Connection con = null;
Statement stmt = null;
ResultSet rs = null;
ResultSetMetaData rsmd = null;

try {
	// Load the jdbc driver
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	// Connect to the database
	con = DriverManager.getConnection("jdbc:odbc:"+szODBCNAME);
	// Create a statement object
	stmt = con.createStatement();
	// Execute query	
	
	rs = stmt.executeQuery(sqlquery);
	rsmd = rs.getMetaData();
%>	