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

public final class Gemeente extends ParameterBase implements Serializable{


	/**
     * 
     */
    private static final long serialVersionUID = -6737255172848189703L;
	private String post;
	private int parentId;
	private String post_omschrijving;

	private ParameterBase land;


	/**
	 * Gets the post
	 * @return Returns a String
	 */
	public String getPost() {
		return post;
	}
	/**
	 * Sets the post
	 * @param post The post to set
	 */
	public void setPost(String post) {
		this.post = post;
	}


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
	 * Gets the land
	 * @return Returns a ParameterBase
	 */
	public ParameterBase getLand() {
		if (land == null) {
			if (parentId != 0) {
				land = (ParameterBase)ParameterBase.findByPK(Queries.LAND_BY_PK, parentId);
			}
			else {
				land = new ParameterBase();
			}
		}
		return land;
	}
	/**
	 * Sets the land
	 * @param land The land to set
	 */
	public void setLand(ParameterBase land) {
		this.land = land;
	}

	/**
	 * Gets the Gemeente from the database by it's own id
	 * @return Returns a ParameterBase
	 * @param id The id from the Gemeente to retrieve
	 */

	public static ParameterBase findByPK(int id) {


		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Gemeente gemeente = new Gemeente();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.GEMEENTE_BY_PK);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if (rs.next()) {

					gemeente.setId(id);
					gemeente.setOmschrijving(rs.getString("omschrijving"));
					gemeente.setPost(rs.getString("post"));
					gemeente.setParentId(rs.getInt("parentId"));

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


		return gemeente;


	}

	/**
	 * Gets all Gemeenten from the database
	 * @return Returns a ArrayList
	 */

	public static ArrayList<Gemeente> findAll() {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Gemeente> allGemeenten = new ArrayList<Gemeente>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.ALL_GEMEENTEN);
				rs = pst.executeQuery();
				while (rs.next()) {

					Gemeente gemeente = new Gemeente();
					gemeente.setId(rs.getInt("id"));
					gemeente.setOmschrijving(rs.getString("omschrijving"));
					gemeente.setPost(rs.getString("post"));
					gemeente.setParentId(rs.getInt("parentId"));
					allGemeenten.add(gemeente);
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

		return allGemeenten;

	}

	/**
	 * Gets all Gemeenten from the database by search parameters
	 * @return Returns a ArrayList
	 * @param searchgemeente The gemeente with all search criteria
	 */

	public static ArrayList<Gemeente> findBySearch(Gemeente searchgemeente) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Gemeente> allGemeenten = new ArrayList<Gemeente>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.ZOEK_GEMEENTE);
				pst.setString(1, "%" + searchgemeente.getOmschrijving().trim() + "%");
				pst.setString(2, "%" + searchgemeente.getPost().trim() + "%");
				pst.setInt(3, searchgemeente.getParentId());
				rs = pst.executeQuery();
				while (rs.next()) {

					Gemeente gemeente = new Gemeente();
					gemeente.setId(rs.getInt("id"));
					gemeente.setOmschrijving(rs.getString("omschrijving"));
					gemeente.setPost(rs.getString("post"));
					gemeente.setParentId(rs.getInt("parentId"));
					allGemeenten.add(gemeente);

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


		return allGemeenten;

	}

	/**
	 * Gets all Gemeenten from the database by the parentId
	 * @return Returns a ArrayList
	 * @param gemeenteId The gemeenteId to use
	 */

	public static ArrayList<Gemeente> findAllByParent(int gemeenteId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Gemeente> allGemeenten = new ArrayList<Gemeente>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.GEMEENTEN_BY_PARENT);
				pst.setInt(1, gemeenteId);
				rs = pst.executeQuery();
				while (rs.next()) {

					Gemeente gemeente = new Gemeente();
					gemeente.setId(rs.getInt("id"));
					gemeente.setOmschrijving(rs.getString("omschrijving"));
					gemeente.setPost(rs.getString("post"));
					gemeente.setParentId(rs.getInt("parentId"));
					allGemeenten.add(gemeente);

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

		return allGemeenten;

	}

	/**
	 * Inserts a Gemeente
	 * @return Return int
	 * @param gemeente The Gemeente to insert
	 */

	public static int insert(Gemeente gemeente) {

		int id = 0;
		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try {
			if (con !=null) {
				pst = con.prepareStatement(Queries.INSERT_GEMEENTE);

				pst.setString(1, gemeente.getOmschrijving());
				pst.setString(2, gemeente.getPost());
				Util.pstSetInt(pst, 3, gemeente.getParentId());

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
	 * Updates a Gemeente
	 * @return Return boolean
	 * @param gemeente The Gemeente to change
	 */


	public static boolean update(Gemeente gemeente) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.UPDATE_GEMEENTE);

				pst.setString(1, gemeente.getOmschrijving());
				pst.setString(2, gemeente.getPost());
				Util.pstSetInt(pst, 3, gemeente.getParentId());
				pst.setInt(4, gemeente.getId());

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
	 * Gets the post_omschrijving
	 * @return Returns a String
	 */
	public String getPost_omschrijving() {
		post_omschrijving = post + " " + this.getOmschrijving();
		return post_omschrijving;
	}
	/**
	 * Sets the post_omschrijving
	 * @param post_omschrijving The post_omschrijving to set
	 */
	public void setPost_omschrijving(String post_omschrijving) {
		this.post_omschrijving = post_omschrijving;
	}


}


