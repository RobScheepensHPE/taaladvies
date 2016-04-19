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

public final class Doelgroep extends ParameterActief implements Serializable {


	/**
     * 
     */
    private static final long serialVersionUID = 488201444141188711L;
	private int parentId;
	private DoelgroepAlgemeen doelgroepAlgemeen;

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
	 * Gets the doelgroepAlgemeen
	 * @return Returns a DoelgroepAlgemeen
	 */
	public DoelgroepAlgemeen getDoelgroepAlgemeen() {
		if (doelgroepAlgemeen == null) {
			if (parentId != 0) {
				doelgroepAlgemeen = (DoelgroepAlgemeen)DoelgroepAlgemeen.findByPK(parentId);
			}
			else {
				doelgroepAlgemeen = new DoelgroepAlgemeen();
			}
		}
		return doelgroepAlgemeen;
	}
	/**
	 * Sets the doelgroepAlgemeen
	 * @param doelgroepAlgemeen The doelgroepAlgemeen to set
	 */
	public void setDoelgroepAlgemeen(DoelgroepAlgemeen doelgroepAlgemeen) {
		this.doelgroepAlgemeen = doelgroepAlgemeen;
	}

	/**
	 * Gets the Doelgroep from the database by it's own id
	 * @return Returns a ParameterBase
	 * @param id The id from the Doelgroep to retrieve
	 */

	public static ParameterBase findByPK(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Doelgroep doelgroep = new Doelgroep();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.DOELGROEP_BY_PK);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if (rs.next()) {

					doelgroep.setId(id);
					doelgroep.setOmschrijving(rs.getString("omschrijving"));
					doelgroep.setActief(rs.getBoolean("actief"));
					doelgroep.setParentId(rs.getInt("parentId"));

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

		return doelgroep;

	}

	/**
	 * Gets all Doelgroepen from the database
	 * @return Returns a ArrayList
	 */

	public static ArrayList<Doelgroep> findAll() {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Doelgroep> allDoelgroepen = new ArrayList<Doelgroep>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.ALL_DOELGROEPEN);
				rs = pst.executeQuery();
				while (rs.next()) {

					Doelgroep doelgroep = new Doelgroep();
					doelgroep.setId(rs.getInt("id"));
					doelgroep.setOmschrijving(rs.getString("omschrijving"));
					doelgroep.setActief(rs.getBoolean("actief"));
					doelgroep.setParentId(rs.getInt("parentId"));
					allDoelgroepen.add(doelgroep);

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

		return allDoelgroepen;

	}

	/**
	 * Gets all "actieve" Doelgroepen from the database
	 * @return Returns a ArrayList
	 */

	public static ArrayList<Doelgroep> findAllActief() {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Doelgroep> allDoelgroepen = new ArrayList<Doelgroep>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.ALL_DOELGROEPEN_ACTIEF);
				rs = pst.executeQuery();
				while (rs.next()) {

					Doelgroep doelgroep = new Doelgroep();
					doelgroep.setId(rs.getInt("id"));
					doelgroep.setOmschrijving(rs.getString("omschrijving"));
					doelgroep.setActief(rs.getBoolean("actief"));
					doelgroep.setParentId(rs.getInt("parentId"));
					allDoelgroepen.add(doelgroep);

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

		return allDoelgroepen;

	}


	/**
	 * Gets all Doelgroepen from the database by the parentId
	 * @return Returns a ArrayList
	 * @param doelgroepAlgemeenId The doelgroepAlgemeenId to use
	 */

	public static ArrayList<Doelgroep> findAllByParent(int doelgroepAlgemeenId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Doelgroep> allDoelgroepen = new ArrayList<Doelgroep>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.DOELGROEPEN_BY_PARENT);
				pst.setInt(1, doelgroepAlgemeenId);
				rs = pst.executeQuery();
				while (rs.next()) {

					Doelgroep doelgroep = new Doelgroep();
					doelgroep.setId(rs.getInt("id"));
					doelgroep.setOmschrijving(rs.getString("omschrijving"));
					doelgroep.setActief(rs.getBoolean("actief"));
					doelgroep.setParentId(rs.getInt("parentId"));
					allDoelgroepen.add(doelgroep);

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

		return allDoelgroepen;

	}


	/**
	 * Gets all "actieve" Doelgroepen from the database by the parentId
	 * @return Returns a ArrayList
	 * @param doelgroepAlgemeenId The doelgroepAlgemeenId to use
	 */

	public static ArrayList<Doelgroep> findAllByParentActief(int doelgroepAlgemeenId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Doelgroep> allDoelgroepen = new ArrayList<Doelgroep>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.DOELGROEPEN_BY_PARENT_ACTIEF);
				pst.setInt(1, doelgroepAlgemeenId);
				rs = pst.executeQuery();
				while (rs.next()) {

					Doelgroep doelgroep = new Doelgroep();
					doelgroep.setId(rs.getInt("id"));
					doelgroep.setOmschrijving(rs.getString("omschrijving"));
					doelgroep.setActief(rs.getBoolean("actief"));
					doelgroep.setParentId(rs.getInt("parentId"));
					allDoelgroepen.add(doelgroep);

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

		return allDoelgroepen;

	}


	/**
	 * Gets all Doelgroepen from the database by the domeinId (grandparentId)
	 * @return Returns a ArrayList
	 * @param domeinId The domeinId to use
	 */

	public static ArrayList<Doelgroep> findAllByDomein(int domeinId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Doelgroep> allDoelgroepen = new ArrayList<Doelgroep>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.DOELGROEPEN_BY_DOMEIN);
				pst.setInt(1, domeinId);
				rs = pst.executeQuery();
				while (rs.next()) {

					Doelgroep doelgroep = new Doelgroep();
					doelgroep.setId(rs.getInt("id"));
					doelgroep.setOmschrijving(rs.getString("omschrijving"));
					doelgroep.setActief(rs.getBoolean("actief"));
					doelgroep.setParentId(rs.getInt("parentId"));
					allDoelgroepen.add(doelgroep);

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

		return allDoelgroepen;

	}

	/**
	 * Gets all "actieve" Doelgroepen from the database by the domeinId (grandparentId)
	 * @return Returns a ArrayList
	 * @param domeinId The domeinId to use
	 */

	public static ArrayList<Doelgroep> findAllByDomeinActief(int domeinId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Doelgroep> allDoelgroepen = new ArrayList<Doelgroep>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.DOELGROEPEN_BY_DOMEIN_ACTIEF);
				pst.setInt(1, domeinId);
				rs = pst.executeQuery();
				while (rs.next()) {

					Doelgroep doelgroep = new Doelgroep();
					doelgroep.setId(rs.getInt("id"));
					doelgroep.setOmschrijving(rs.getString("omschrijving"));
					doelgroep.setActief(rs.getBoolean("actief"));
					doelgroep.setParentId(rs.getInt("parentId"));
					allDoelgroepen.add(doelgroep);

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

		return allDoelgroepen;

	}


	/**
	 * Inserts a Doelgroep
	 * @return Return int
	 * @param doelgroep The Doelgroep to insert
	 */

	public static int insert(Doelgroep doelgroep) {

		int id = 0;
		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try {
			if (con !=null) {
				pst = con.prepareStatement(Queries.INSERT_DOELGROEP);

				pst.setString(1, doelgroep.getOmschrijving());
				pst.setBoolean(2, doelgroep.getActief());
				Util.pstSetInt(pst, 3, doelgroep.getParentId());

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
	 * Updates a Doelgroep
	 * @return Return boolean
	 * @param doelgroep The Doelgroep to change
	 */

	public static boolean update(Doelgroep doelgroep) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.UPDATE_DOELGROEP);

				pst.setString(1, doelgroep.getOmschrijving());
				pst.setBoolean(2, doelgroep.getActief());
				Util.pstSetInt(pst, 3, doelgroep.getParentId());
				pst.setInt(4, doelgroep.getId());

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

