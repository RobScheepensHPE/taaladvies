package be.vlaanderen.sbs.s6.taaladvies.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import be.vlaanderen.sbs.s6.taaladvies.Queries;
import be.vlaanderen.sbs.s6.taaladvies.db.IDbConnection;
import be.vlaanderen.sbs.s6.taaladvies.db.MyDbConnection;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;
import com.informix.jdbc.IfxStatement;

public final class Naslagwerk extends ParameterActief implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 6280614567106612194L;
	private int parentId;
	private String omschrijvingHTML;
	private String titelVoluit;
	private String titelVoluitHTML;
	private ParameterActief bibliografie;


	/**
	 * Gets the parentId
	 * @return Returns a int
	 */
	public int getParentId() {
		return parentId;
	}
	/**
	 * Sets the parentId
	 * @param parentId The parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}


	/**
	 * Gets the omschrijvingHTML
	 * @return Returns a String
	 */
	public String getOmschrijvingHTML() {
		return omschrijvingHTML;
	}
	/**
	 * Sets the omschrijvingHTML
	 * @param omschrijvingHTML The omschrijvingHTML to set
	 */
	public void setOmschrijvingHTML(String omschrijvingHTML) {
		this.omschrijvingHTML = omschrijvingHTML;
	}


	/**
	 * Gets the titelVoluit
	 * @return Returns a String
	 */
	public String getTitelVoluit() {
		return titelVoluit;
	}
	/**
	 * Sets the titelVoluit
	 * @param titelVoluit The titelVoluit to set
	 */
	public void setTitelVoluit(String titelVoluit) {
		this.titelVoluit = titelVoluit;
	}


	/**
	 * Gets the titelVoluitHTML
	 * @return Returns a String
	 */
	public String getTitelVoluitHTML() {
		return titelVoluitHTML;
	}
	/**
	 * Sets the titelVoluitHTML
	 * @param titelVoluitHTML The titelVoluitHTML to set
	 */
	public void setTitelVoluitHTML(String titelVoluitHTML) {
		this.titelVoluitHTML = titelVoluitHTML;
	}


	/**
	 * Gets the bibliografie
	 * @return Returns a ParameterActief
	 */
	public ParameterActief getBibliografie() {
		if (bibliografie == null) {
			if (parentId != 0) {
				bibliografie = (ParameterActief)ParameterActief.findByPK(Queries.BIBLIOGRAFIE_BY_PK, parentId);
			}
			else {
				bibliografie = new ParameterActief();
			}
		}
		return bibliografie;
	}
	/**
	 * Sets the bibliografie
	 * @param bibliografie The bibliografie to set
	 */
	public void setBibliografie(ParameterActief bibliografie) {
		this.bibliografie = bibliografie;
	}

	/**
	 * Gets the Naslagwerk from the database by it's own id
	 * @return Returns a ParameterBase
	 * @param id The id from the Naslagwerk to retrieve
	 */

	public static ParameterBase findByPK(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Naslagwerk naslagwerk = new Naslagwerk();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.NASLAGWERK_BY_PK);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if (rs.next()) {

					naslagwerk.setId(id);
					naslagwerk.setOmschrijving(rs.getString("omschrijving"));
					naslagwerk.setActief(rs.getBoolean("actief"));
					naslagwerk.setParentId(rs.getInt("parentId"));
					naslagwerk.setOmschrijvingHTML(rs.getString("omschrijvingHTML"));
					naslagwerk.setTitelVoluit(rs.getString("titelVoluit"));
					naslagwerk.setTitelVoluitHTML(rs.getString("titelVoluitHTML"));

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

		return naslagwerk;

	}

	/**
	 * Gets all Naslagwerken from the database
	 * @return Returns a ArrayList
	 */

	public static ArrayList<Naslagwerk> findAll() {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Naslagwerk> allNaslagwerken = new ArrayList<Naslagwerk>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.ALL_NASLAGWERKEN);
				rs = pst.executeQuery();
				while (rs.next()) {

					Naslagwerk naslagwerk = new Naslagwerk();
					naslagwerk.setId(rs.getInt("id"));
					naslagwerk.setOmschrijving(rs.getString("omschrijving"));
					naslagwerk.setActief(rs.getBoolean("actief"));
					naslagwerk.setParentId(rs.getInt("parentId"));
					naslagwerk.setOmschrijvingHTML(rs.getString("omschrijvingHTML"));
					naslagwerk.setTitelVoluit(rs.getString("titelVoluit"));
					naslagwerk.setTitelVoluitHTML(rs.getString("titelVoluitHTML"));
					allNaslagwerken.add(naslagwerk);

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

		return allNaslagwerken;

	}

	/**
	 * Gets all "actieve" Naslagwerken from the database
	 * @return Returns a ArrayList
	 */

	public static ArrayList<Naslagwerk> findAllActief() {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Naslagwerk> allNaslagwerken = new ArrayList<Naslagwerk>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.ALL_NASLAGWERKEN_ACTIEF);
				rs = pst.executeQuery();
				while (rs.next()) {

					Naslagwerk naslagwerk = new Naslagwerk();
					naslagwerk.setId(rs.getInt("id"));
					naslagwerk.setOmschrijving(rs.getString("omschrijving"));
					naslagwerk.setActief(rs.getBoolean("actief"));
					naslagwerk.setParentId(rs.getInt("parentId"));
					naslagwerk.setOmschrijvingHTML(rs.getString("omschrijvingHTML"));
					naslagwerk.setTitelVoluit(rs.getString("titelVoluit"));
					naslagwerk.setTitelVoluitHTML(rs.getString("titelVoluitHTML"));
					allNaslagwerken.add(naslagwerk);

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

		return allNaslagwerken;

	}


	/**
	 * Gets all Naslagwerken from the database by the parentId
	 * @return Returns a ArrayList
	 * @param parentId The parentId to use
	 */

	public static ArrayList<Naslagwerk> findAllByParent(int parentId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Naslagwerk> allNaslagwerken = new ArrayList<Naslagwerk>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.NASLAGWERKEN_BY_PARENT);
				pst.setInt(1, parentId);
				rs = pst.executeQuery();
				while (rs.next()) {

					Naslagwerk naslagwerk = new Naslagwerk();
					naslagwerk.setId(rs.getInt("id"));
					naslagwerk.setOmschrijving(rs.getString("omschrijving"));
					naslagwerk.setActief(rs.getBoolean("actief"));
					naslagwerk.setParentId(rs.getInt("parentId"));
					naslagwerk.setOmschrijvingHTML(rs.getString("omschrijvingHTML"));
					naslagwerk.setTitelVoluit(rs.getString("titelVoluit"));
					naslagwerk.setTitelVoluitHTML(rs.getString("titelVoluitHTML"));
					allNaslagwerken.add(naslagwerk);

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

		return allNaslagwerken;

	}


	/**
	 * Gets all "actieve" Naslagwerken from the database by the parentId
	 * @return Returns a ArrayList
	 * @param parentId The parentId to use
	 */

	public static ArrayList<Naslagwerk> findAllByParentActief(int parentId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Naslagwerk> allNaslagwerken = new ArrayList<Naslagwerk>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.NASLAGWERKEN_BY_PARENT_ACTIEF);
				pst.setInt(1, parentId);
				rs = pst.executeQuery();
				while (rs.next()) {

					Naslagwerk naslagwerk = new Naslagwerk();
					naslagwerk.setId(rs.getInt("id"));
					naslagwerk.setOmschrijving(rs.getString("omschrijving"));
					naslagwerk.setActief(rs.getBoolean("actief"));
					naslagwerk.setParentId(rs.getInt("parentId"));
					naslagwerk.setOmschrijvingHTML(rs.getString("omschrijvingHTML"));
					naslagwerk.setTitelVoluit(rs.getString("titelVoluit"));
					naslagwerk.setTitelVoluitHTML(rs.getString("titelVoluitHTML"));
					allNaslagwerken.add(naslagwerk);

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

		return allNaslagwerken;

	}


	/**
	 * Inserts a Naslagwerk
	 * @return Return int
	 * @param naslagwerk The Naslagwerk to insert
	 */

	public static int insert(Naslagwerk naslagwerk) {

		int id = 0;
		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try {
			if (con !=null) {
				pst = con.prepareStatement(Queries.INSERT_NASLAGWERK);

				pst.setString(1, naslagwerk.getOmschrijving());
				pst.setBoolean(2, naslagwerk.getActief());
				Util.pstSetInt(pst, 3, naslagwerk.getParentId());

				Util.pstSetClob(pst, 4, naslagwerk.getTitelVoluit());
				Util.pstSetClob(pst, 5, naslagwerk.getTitelVoluitHTML());

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
	 * Updates a Naslagwerk
	 * @return Return boolean
	 * @param naslagwerk The Naslagwerk to change
	 */

	public static boolean update(Naslagwerk naslagwerk) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.UPDATE_NASLAGWERK);

				pst.setString(1, naslagwerk.getOmschrijving());
				pst.setBoolean(2, naslagwerk.getActief());
				Util.pstSetInt(pst, 3, naslagwerk.getParentId());
				Util.pstSetClob(pst, 4, naslagwerk.getTitelVoluit());
				Util.pstSetClob(pst, 5, naslagwerk.getTitelVoluitHTML());
				pst.setInt(6, naslagwerk.getId());

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

