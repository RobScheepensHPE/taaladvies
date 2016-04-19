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
								if (rs != null) rs.close();
								if (stmt != null) stmt.close();
								if (con != null) con.close();
							}
							catch (SQLException e) {
								// ignore
							}
						}
					%>