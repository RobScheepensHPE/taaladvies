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

public final class DoelgroepAlgemeen extends ParameterActief implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = -2823426076906703191L;
	private int parentId;
	private ParameterBase domein;

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
	 * Gets the domein
	 * @return Returns a ParameterBase
	 */
	public ParameterBase getDomein() {
		if (domein == null) {
			if (parentId != 0) {
				domein = ParameterBase.findByPK(Queries.DOMEIN_BY_PK, parentId);
			}
			else {
				domein = new ParameterBase();
			}
		}
		return domein;
	}
	/**
	 * Sets the domein
	 * @param domein The domein to set
	 */
	public void setDomein(ParameterBase domein) {
		this.domein = domein;
	}

	/**
	 * Gets the DoelgroepAlgemeen from the database by it's own id
	 * @return Returns a ParameterBase
	 * @param id The id from the DoelgroepAlgemeen to retrieve
	 */

	public static ParameterBase findByPK(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		DoelgroepAlgemeen doelgroepAlgemeen = new DoelgroepAlgemeen();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.DOELGROEP_ALGEMEEN_BY_PK);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				while (rs.next()) {

					doelgroepAlgemeen.setId(id);
					doelgroepAlgemeen.setOmschrijving(rs.getString("omschrijving"));
					doelgroepAlgemeen.setActief(rs.getBoolean("actief"));
					doelgroepAlgemeen.setParentId(rs.getInt("parentId"));

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

		return doelgroepAlgemeen;

	}

	/**
	 * Gets all DoelgroepenAlgemeen from the database
	 * @return Returns a ArrayList
	 */

	public static ArrayList<DoelgroepAlgemeen> findAll() {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<DoelgroepAlgemeen> allDoelgroepenAlgemeen = new ArrayList<DoelgroepAlgemeen>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.ALL_DOELGROEPEN_ALGEMEEN);
				rs = pst.executeQuery();
				while (rs.next()) {

					DoelgroepAlgemeen doelgroepAlgemeen = new DoelgroepAlgemeen();
					doelgroepAlgemeen.setId(rs.getInt("id"));
					doelgroepAlgemeen.setOmschrijving(rs.getString("omschrijving"));
					doelgroepAlgemeen.setActief(rs.getBoolean("actief"));
					doelgroepAlgemeen.setParentId(rs.getInt("parentId"));
					allDoelgroepenAlgemeen.add(doelgroepAlgemeen);

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

		return allDoelgroepenAlgemeen;

	}

	/**
	 * Gets all "actieve" DoelgroepenAlgemeen from the database
	 * @return Returns a ArrayList
	 */

	public static ArrayList<DoelgroepAlgemeen> findAllActief() {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<DoelgroepAlgemeen> allDoelgroepenAlgemeen = new ArrayList<DoelgroepAlgemeen>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.ALL_DOELGROEPEN_ALGEMEEN_ACTIEF);
				rs = pst.executeQuery();
				while (rs.next()) {

					DoelgroepAlgemeen doelgroepAlgemeen = new DoelgroepAlgemeen();
					doelgroepAlgemeen.setId(rs.getInt("id"));
					doelgroepAlgemeen.setOmschrijving(rs.getString("omschrijving"));
					doelgroepAlgemeen.setActief(rs.getBoolean("actief"));
					doelgroepAlgemeen.setParentId(rs.getInt("parentId"));
					allDoelgroepenAlgemeen.add(doelgroepAlgemeen);

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

		return allDoelgroepenAlgemeen;

	}

	/**
	 * Gets all DoelgroepenAlgemeen from the database by the parentId
	 * @return Returns a ArrayList
	 * @param domeinId The domeinId to use
	 */

	public static ArrayList<DoelgroepAlgemeen> findAllByParent(int domeinId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<DoelgroepAlgemeen> allDoelgroepenAlgemeen = new ArrayList<DoelgroepAlgemeen>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.DOELGROEPEN_ALGEMEEN_BY_PARENT);
				pst.setInt(1, domeinId);
				rs = pst.executeQuery();
				while (rs.next()) {

					DoelgroepAlgemeen doelgroepAlgemeen = new DoelgroepAlgemeen();
					doelgroepAlgemeen.setId(rs.getInt("id"));
					doelgroepAlgemeen.setOmschrijving(rs.getString("omschrijving"));
					doelgroepAlgemeen.setActief(rs.getBoolean("actief"));
					doelgroepAlgemeen.setParentId(rs.getInt("parentId"));
					allDoelgroepenAlgemeen.add(doelgroepAlgemeen);

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

		return allDoelgroepenAlgemeen;

	}


	/**
	 * Gets all "actieve" DoelgroepenAlgemeen from the database by the parentId
	 * @return Returns a ArrayList
	 * @param domeinId The domeinId to use
	 */

	public static ArrayList<DoelgroepAlgemeen> findAllByParentActief(int domeinId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<DoelgroepAlgemeen> allDoelgroepenAlgemeen = new ArrayList<DoelgroepAlgemeen>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.DOELGROEPEN_ALGEMEEN_BY_PARENT_ACTIEF);
				pst.setInt(1, domeinId);
				rs = pst.executeQuery();
				while (rs.next()) {

					DoelgroepAlgemeen doelgroepAlgemeen = new DoelgroepAlgemeen();
					doelgroepAlgemeen.setId(rs.getInt("id"));
					doelgroepAlgemeen.setOmschrijving(rs.getString("omschrijving"));
					doelgroepAlgemeen.setActief(rs.getBoolean("actief"));
					doelgroepAlgemeen.setParentId(rs.getInt("parentId"));
					allDoelgroepenAlgemeen.add(doelgroepAlgemeen);

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

		return allDoelgroepenAlgemeen;

	}


	/**
	 * Inserts a DoelgroepAlgemeen
	 * @return Return int
	 * @param doelgroepAlgemeen The DoelgroepAlgemeen to insert
	 */

	public static int insert(DoelgroepAlgemeen doelgroepAlgemeen) {

		int id = 0;
		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try {
			if (con !=null) {
				pst = con.prepareStatement(Queries.INSERT_DOELGROEP_ALGEMEEN);

				pst.setString(1, doelgroepAlgemeen.getOmschrijving());
				pst.setBoolean(2, doelgroepAlgemeen.getActief());
				Util.pstSetInt(pst, 3, doelgroepAlgemeen.getParentId());

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
	 * Updates a DoelgroepAlgemeen
	 * @return Return boolean
	 * @param doelgroepAlgemeen The DoelgroepAlgemeen to change
	 */

	public static boolean update(DoelgroepAlgemeen doelgroepAlgemeen) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.UPDATE_DOELGROEP_ALGEMEEN);

				pst.setString(1, doelgroepAlgemeen.getOmschrijving());
				pst.setBoolean(2, doelgroepAlgemeen.getActief());
				Util.pstSetInt(pst, 3, doelgroepAlgemeen.getParentId());
				pst.setInt(4, doelgroepAlgemeen.getId());

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

