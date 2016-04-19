package be.vlaanderen.sbs.s6.taaladvies.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import be.vlaanderen.sbs.s6.taaladvies.db.IDbConnection;
import be.vlaanderen.sbs.s6.taaladvies.db.MyDbConnection;
import com.informix.jdbc.IfxStatement;

/**
 * This class represents the BaseClass for each Parameter Entity.  Every Parameter Entity is thus derived from this class<br>
 * Entities that are fully represented by this class are:
 * <ul>
 * 	<li>Domein</li>
 * </ul>
 */

public class ParameterBase implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 9153348260938832201L;
	private int id;
	private String omschrijving;

	/**
	 * Gets the id
	 * @return Returns a int
	 */
	public int getId() {
		return id;
	}
	/**
	 * Sets the id
	 * @param id The id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * Gets the omschrijving
	 * @return Returns a String
	 */
	public String getOmschrijving() {
		if (omschrijving != null) {
			return omschrijving.trim();
		}
		else {
			return omschrijving;
		}
	}
	/**
	 * Sets the omschrijving
	 * @param omschrijving The omschrijving to set
	 */
	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	/**
	 * Gets the "ParameterBase" from the database by it's id
	 * @return Returns a ParameterBase
	 * @param query The query to use
	 * @param id The id to retrieve
	 */

	public static ParameterBase findByPK(String query, int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ParameterBase parameterBase = new ParameterBase();
		String sql = query;

		try {
			if (con != null) {
				pst = con.prepareStatement(sql);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if (rs.next()) {
					parameterBase.setId(id);
					parameterBase.setOmschrijving(rs.getString("omschrijving"));
				}

			} else {

				System.err.println("Geen connectie !");
			}
		} catch (java.sql.SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			try {
				if (con != null) {
					if (rs != null) {rs.close();}
					if (pst != null) {pst.close();}
					dbconnection.releaseConnection(con);
				}

			} catch (java.sql.SQLException sqle) {
				sqle.printStackTrace(System.err);
			}
		}

		return parameterBase;

	}

	/**
	 * Gets all "ParameterBases" from the database
	 * @return Returns a ArrayList
	 * @param query The query to use
	 */

	public static ArrayList findAll(String query) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<ParameterBase> allParameterBases = new ArrayList<ParameterBase>();
		String sql = query;

		try {
			if (con != null) {
				pst = con.prepareStatement(sql);
				rs = pst.executeQuery();
				while (rs.next()) {

					ParameterBase parameterBase = new ParameterBase();
					parameterBase.setId(rs.getInt("id"));
					parameterBase.setOmschrijving(rs.getString("omschrijving"));
					if (parameterBase.getId() == 1) {
						allParameterBases.add(0, parameterBase);
					}else if (parameterBase.getId() == 3) {
						allParameterBases.add(1, parameterBase);
					}
					else {
						allParameterBases.add(parameterBase);
					}

				}

			} else {

				System.err.println("Geen connectie !");
			}
		} catch (java.sql.SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			try {
				if (con != null) {
					if (rs != null) {rs.close();}
					if (pst != null) {pst.close();}
					dbconnection.releaseConnection(con);
				}

			} catch (java.sql.SQLException sqle) {
				sqle.printStackTrace(System.err);
			}
		}

		return allParameterBases;
	}


	/**
	 * Gets all "actieve" "ParameterBases" from the database
	 * @return Returns a ArrayList
	 * @param query The query to use
	 */

	public static ArrayList findAllActief(String query) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<ParameterBase> allParameterBases = new ArrayList<ParameterBase>();
		String sql = query;

		try {
			if (con != null) {
				pst = con.prepareStatement(sql);
				rs = pst.executeQuery();
				while (rs.next()) {

					ParameterBase parameterBase = new ParameterBase();
					parameterBase.setId(rs.getInt("id"));
					parameterBase.setOmschrijving(rs.getString("omschrijving"));
					allParameterBases.add(parameterBase);

				}

			} else {

				System.err.println("Geen connectie !");
			}
		} catch (java.sql.SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			try {
				if (con != null) {
					if (rs != null) {rs.close();}
					if (pst != null) {pst.close();}
					dbconnection.releaseConnection(con);
				}

			} catch (java.sql.SQLException sqle) {
				sqle.printStackTrace(System.err);
			}
		}

		return allParameterBases;
	}


	/**
	 * Inserts a ParameterBase
	 * @return Return int
	 * @param query The query to use
	 * @param parameterBase The ParameterBase to insert
	 */

	public static int insert(String query, ParameterBase parameterBase) {

		int id = 0;
		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		String sql = query;
		try {
			if (con !=null) {
				pst = con.prepareStatement(sql);

				pst.setString(1, parameterBase.getOmschrijving());

				pst.executeUpdate();
				id = ((IfxStatement)pst).getSerial();

			} else {
				System.err.println("Geen connectie !");
			}
		} catch (java.sql.SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			try {
				if (con != null) {
					if (pst != null) {pst.close();}
					dbconnection.releaseConnection(con);
				}

			} catch (java.sql.SQLException sqle) {
				sqle.printStackTrace(System.err);
			}
		}

		return id;
	}

	/**
	 * Updates a ParameterBase
	 * @return Return boolean
	 * @param query The query to use
	 * @param parameterBase The ParameterBase to change
	 */

	public static boolean update(String query, ParameterBase parameterBase) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		String sql = query;
		try{
			if (con !=null) {
				pst = con.prepareStatement(sql);

				pst.setString(1, parameterBase.getOmschrijving());
				pst.setInt(2, parameterBase.getId());

				int check = pst.executeUpdate();

				if (check == 1){
					return true;
				}else{
					return false;
				}
        	} else {
            	System.err.println("Geen connectie !");
				return false;
        	}
    	} catch (java.sql.SQLException e) {
        	e.printStackTrace(System.err);
			return false;
    	} finally {
        	try {
            	if (con != null) {
                	if (pst != null) {pst.close();}
                	dbconnection.releaseConnection(con);
            	}

        	} catch (java.sql.SQLException sqle) {
            	sqle.printStackTrace(System.err);
        	}
    	}
	}

	/**
	 * deletes a ParameterBase
	 * @return Return boolean
	 * @param query The query to use
	 * @param parameterBase The ParameterBase to delete
	 */

  	public static boolean delete(String query, ParameterBase parameterBase) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		String sql = query;
		try{
			if (con !=null) {
				pst = con.prepareStatement(sql);

				pst.setInt(1, parameterBase.getId());

				int check = pst.executeUpdate();

				if (check == 1){
					return true;
				}else{
					return false;
				}
        	} else {
            	System.err.println("Geen connectie !");
				return false;
        	}
    	} catch (java.sql.SQLException e) {
        	e.printStackTrace(System.err);
			return false;
    	} finally {
        	try {
            	if (con != null) {
                	if (pst != null) {pst.close();}
                	dbconnection.releaseConnection(con);
            	}

        	} catch (java.sql.SQLException sqle) {
            	sqle.printStackTrace(System.err);
        	}
    	}
	}

}

