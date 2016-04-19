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

public class Sjabloon extends ParameterActief implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 1506450734464614803L;
	private String inhoud;
	private String inhoudHTML;
	private String handtekening;
	private String handtekeningHTML;
	private String onderwerp;
	private String slotgroet;
	private String slotgroetHTML;
	private String aanspreking;

	/**
	 * Gets the aanspreking
	 * @return Returns a String
	 */
	public String getAanspreking() {
		return aanspreking;
	}
	/**
	 * Sets the aanspreking
	 * @param aanspreking The aanspreking to set
	 */
	public void setAanspreking(String aanspreking) {
		if(aanspreking != null && aanspreking.trim().equals(""))
			aanspreking = null;

		this.aanspreking = aanspreking;
	}

	/**
	 * Gets the inhoud
	 * @return Returns a String
	 */
	public String getInhoud() {
		return inhoud;
	}
	/**
	 * Sets the inhoud
	 * @param inhoud The inhoud to set
	 */
	public void setInhoud(String inhoud) {
		this.inhoud = inhoud;
	}


	/**
	 * Gets the inhoudHTML
	 * @return Returns a String
	 */
	public String getInhoudHTML() {
		return inhoudHTML;
	}
	/**
	 * Sets the inhoudHTML
	 * @param inhoudHTML The inhoudHTML to set
	 */
	public void setInhoudHTML(String inhoudHTML) {
		this.inhoudHTML = inhoudHTML;
	}


	/**
	 * Gets the handtekening
	 * @return Returns a String
	 */
	public String getHandtekening() {
		return handtekening;
	}
	/**
	 * Sets the handtekening
	 * @param handtekening The handtekening to set
	 */
	public void setHandtekening(String handtekening) {
		this.handtekening = handtekening;
	}


	/**
	 * Gets the handtekeningHTML
	 * @return Returns a String
	 */
	public String getHandtekeningHTML() {
		return handtekeningHTML;
	}
	/**
	 * Sets the handtekeningHTML
	 * @param handtekeningHTML The handtekeningHTML to set
	 */
	public void setHandtekeningHTML(String handtekeningHTML) {
		this.handtekeningHTML = handtekeningHTML;
	}


	/**
	 * Gets the onderwerp
	 * @return Returns a String
	 */
	public String getOnderwerp() {
		return onderwerp;
	}
	/**
	 * Sets the onderwerp
	 * @param onderwerp The onderwerp to set
	 */
	public void setOnderwerp(String onderwerp) {
		this.onderwerp = onderwerp;
	}


	/**
	 * Gets the slotgroet
	 * @return Returns a String
	 */
	public String getSlotgroet() {
		return slotgroet;
	}
	/**
	 * Sets the slotgroet
	 * @param slotgroet The slotgroet to set
	 */
	public void setSlotgroet(String slotgroet) {
		this.slotgroet = slotgroet;
	}


	/**
	 * Gets the slotgroetHTML
	 * @return Returns a String
	 */
	public String getSlotgroetHTML() {
		return slotgroetHTML;
	}
	/**
	 * Sets the slotgroetHTML
	 * @param slotgroetHTML The slotgroetHTML to set
	 */
	public void setSlotgroetHTML(String slotgroetHTML) {
		this.slotgroetHTML = slotgroetHTML;
	}

	/**
	 * Gets the Sjabloon from the database by it's id
	 * @return Returns a ParameterBase
	 * @param id The id to retrieve
	 */

	public static ParameterBase findByPK(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Sjabloon sjabloon = new Sjabloon();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.SJABLOON_BY_PK);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if (rs.next()) {

					sjabloon.setId(id);
					sjabloon.setOmschrijving(rs.getString("omschrijving"));
					sjabloon.setActief(rs.getBoolean("actief"));
					sjabloon.setInhoud(rs.getString("inhoud"));
					sjabloon.setInhoudHTML(rs.getString("inhoudHTML"));
					sjabloon.setHandtekening(rs.getString("handtekening"));
					sjabloon.setHandtekeningHTML(rs.getString("handtekeningHTML"));
					sjabloon.setOnderwerp(rs.getString("onderwerp"));
					sjabloon.setSlotgroet(rs.getString("slotgroet"));
					sjabloon.setSlotgroetHTML(rs.getString("slotgroetHTML"));

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

		return sjabloon;

	}

	/**
	 * Gets all Sjablonen from the database
	 * @return Returns a ArrayList
	 */

	public static ArrayList<Sjabloon> findAll() {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Sjabloon> allSjablonen = new ArrayList<Sjabloon>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.ALL_SJABLONEN);
				rs = pst.executeQuery();
				while (rs.next()) {

					Sjabloon sjabloon = new Sjabloon();
					sjabloon.setId(rs.getInt("id"));
					sjabloon.setOmschrijving(rs.getString("omschrijving"));
					sjabloon.setActief(rs.getBoolean("actief"));
					sjabloon.setInhoud(rs.getString("inhoud"));
					sjabloon.setInhoudHTML(rs.getString("inhoudHTML"));
					sjabloon.setHandtekening(rs.getString("handtekening"));
					sjabloon.setHandtekeningHTML(rs.getString("handtekeningHTML"));
					sjabloon.setOnderwerp(rs.getString("onderwerp"));
					sjabloon.setSlotgroet(rs.getString("slotgroet"));
					sjabloon.setSlotgroetHTML(rs.getString("slotgroetHTML"));
					allSjablonen.add(sjabloon);

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

		return allSjablonen;

	}

	/**
	 * Gets all "actieve" Sjablonen from the database
	 * @return Returns a ArrayList
	 */

	public static ArrayList<Sjabloon> findAllActief() {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Sjabloon> allSjablonen = new ArrayList<Sjabloon>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.ALL_SJABLONEN_ACTIEF);
				rs = pst.executeQuery();
				while (rs.next()) {

					Sjabloon sjabloon = new Sjabloon();
					sjabloon.setId(rs.getInt("id"));
					sjabloon.setOmschrijving(rs.getString("omschrijving"));
					sjabloon.setActief(rs.getBoolean("actief"));
					sjabloon.setInhoud(rs.getString("inhoud"));
					sjabloon.setInhoudHTML(rs.getString("inhoudHTML"));
					sjabloon.setHandtekening(rs.getString("handtekening"));
					sjabloon.setHandtekeningHTML(rs.getString("handtekeningHTML"));
					sjabloon.setOnderwerp(rs.getString("onderwerp"));
					sjabloon.setSlotgroet(rs.getString("slotgroet"));
					sjabloon.setSlotgroetHTML(rs.getString("slotgroetHTML"));
					allSjablonen.add(sjabloon);

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

		return allSjablonen;

	}

	/**
	 * Inserts a Sjabloon
	 * @return Return int
	 * @param sjabloon The Sjabloon to insert
	 */

	public static int insert(Sjabloon sjabloon) {

		int id = 0;
		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try {
			if (con !=null) {
				pst = con.prepareStatement(Queries.INSERT_SJABLOON);

				pst.setString(1, sjabloon.getOmschrijving());
				pst.setBoolean(2, sjabloon.getActief());

				Util.pstSetClob(pst, 3, sjabloon.getOnderwerp());
				Util.pstSetClob(pst, 4, sjabloon.getInhoud());
				Util.pstSetClob(pst, 5, sjabloon.getInhoudHTML());
				Util.pstSetClob(pst, 6, sjabloon.getHandtekening());
				Util.pstSetClob(pst, 7, sjabloon.getHandtekeningHTML());
				Util.pstSetClob(pst, 8, sjabloon.getSlotgroet());
				Util.pstSetClob(pst, 9, sjabloon.getSlotgroetHTML());

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
	 * Updates a ParameterActief
	 * @return Return boolean
	 * @param sjabloon The Sjabloon to change
	 */

	public static boolean update(Sjabloon sjabloon) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.UPDATE_SJABLOON);

				pst.setString(1, sjabloon.getOmschrijving());
				pst.setBoolean(2, sjabloon.getActief());

				Util.pstSetClob(pst, 3, sjabloon.getOnderwerp());
				Util.pstSetClob(pst, 4, sjabloon.getInhoud());
				Util.pstSetClob(pst, 5, sjabloon.getInhoudHTML());
				Util.pstSetClob(pst, 6, sjabloon.getHandtekening());
				Util.pstSetClob(pst, 7, sjabloon.getHandtekeningHTML());
				Util.pstSetClob(pst, 8, sjabloon.getSlotgroet());
				Util.pstSetClob(pst, 9, sjabloon.getSlotgroetHTML());

				pst.setInt(10, sjabloon.getId());

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

