 <%    
					    	
	}
	catch (ClassNotFoundException cnfe) {
		out.println(cnfe);
	}
	catch (SQLException sqle) {
		out.println(sqle);
	}
	finally {
		try {
			if (rs2 != null) rs2.close();
			if (stmt2 != null) stmt2.close();
			if (con2 != null) con2.close();
		}
		catch (SQLException e) {
			// ignore
		}
	}
%>