package be.vlaanderen.sbs.s6.taaladvies.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import be.vlaanderen.sbs.s6.taaladvies.Queries;
import be.vlaanderen.sbs.s6.taaladvies.db.IDbConnection;
import be.vlaanderen.sbs.s6.taaladvies.db.MyDbConnection;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;
import com.informix.jdbc.IfxStatement;

public class Distributie implements Serializable {


	/**
     * 
     */
    private static final long serialVersionUID = 8040631867968870700L;
	private int id;
	private Date distributiedatum;
	private int mediumId = 1; //op 1 geïnitialiseerd omdat "telefoon" standaard moet staan... zijnde dus id 1 uit medium
	private int sjabloonId;

	private String cc;
	private String bcc;

	private ParameterActief medium;
	private Sjabloon sjabloon;


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
	 * Gets the distributiedatum
	 * @return Returns a Date
	 */
	public Date getDistributiedatum() {
		return distributiedatum;
	}
	/**
	 * Sets the distributiedatum
	 * @param distributiedatum The distributiedatum to set
	 */
	public void setDistributiedatum(Date distributiedatum) {
		this.distributiedatum = distributiedatum;
	}


	/**
	 * Gets the mediumId
	 * @return Returns a int
	 */
	public int getMediumId() {
		return mediumId;
	}
	/**
	 * Sets the mediumId
	 * @param mediumId The mediumId to set
	 */
	public void setMediumId(int mediumId) {
		this.mediumId = mediumId;
	}


	/**
	 * Gets the sjabloonId
	 * @return Returns a int
	 */
	public int getSjabloonId() {
		return sjabloonId;
	}
	/**
	 * Sets the sjabloonId
	 * @param sjabloonId The sjabloonId to set
	 */
	public void setSjabloonId(int sjabloonId) {
		this.sjabloonId = sjabloonId;
	}


	/**
	 * Gets the cc
	 * @return Returns a String
	 */
	public String getCc() {
		return cc;
	}
	/**
	 * Sets the cc
	 * @param cc The cc to set
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}


	/**
	 * Gets the bcc
	 * @return Returns a String
	 */
	public String getBcc() {
		return bcc;
	}
	/**
	 * Sets the bcc
	 * @param bcc The bcc to set
	 */
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}


	/**
	 * Gets the medium
	 * @return Returns a ParameterActief
	 */
	public ParameterActief getMedium() {
		if (medium == null) {
			if (mediumId != 0) {
				medium = (ParameterActief)ParameterActief.findByPK(Queries.MEDIUM_BY_PK, mediumId);
			}
			else {
				medium = new ParameterActief();
			}
		}
		return medium;
	}
	/**
	 * Sets the medium
	 * @param medium The medium to set
	 */
	public void setMedium(ParameterActief medium) {
		this.medium = medium;
	}


	/**
	 * Gets the sjabloon
	 * @return Returns a Sjabloon
	 */
	public Sjabloon getSjabloon() {
		if (sjabloon == null) {
			if (sjabloonId != 0) {
				sjabloon = (Sjabloon)Sjabloon.findByPK(sjabloonId);
			}
			else {
				sjabloon = new Sjabloon();
			}
		}
		return sjabloon;
	}
	/**
	 * Sets the sjabloon
	 * @param sjabloon The sjabloon to set
	 */
	public void setSjabloon(Sjabloon sjabloon) {
		this.sjabloon = sjabloon;
	}


	/**
	 * Gets the Distributie from the database by it's id
	 * @return Returns a Distributie
	 * @param id The id to retrieve
	 */
	public static Distributie findByPK(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Distributie distributie = new Distributie();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.DISTRIBUTIE_BY_PK);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if (rs.next()) {

					distributie.setId(id);
					distributie.setDistributiedatum(rs.getDate("distributiedatum"));
					distributie.setMediumId(rs.getInt("mediumId"));
					distributie.setSjabloonId(rs.getInt("sjabloonId"));
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

		return distributie;

	}

	/**
	 * Gets all Distributies from the database
	 * @return Returns a ArrayList
	 */

	public static ArrayList<Distributie> findAll() {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Distributie> allDistributies = new ArrayList<Distributie>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.ALL_DISTRIBUTIES);
				rs = pst.executeQuery();
				while (rs.next()) {

					Distributie distributie = new Distributie();
					distributie.setId(rs.getInt("id"));
					distributie.setDistributiedatum(rs.getDate("distributiedatum"));
					distributie.setMediumId(rs.getInt("mediumId"));
					distributie.setSjabloonId(rs.getInt("sjabloonId"));
					allDistributies.add(distributie);

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

		return allDistributies;
	}


	/**
	 * Inserts a Distributie
	 * @return Return int
	 * @param distributie The Distributie to insert
	 */

	public static int insert(Distributie distributie) {

		int id = 0;
		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try {
			if (con !=null) {
				pst = con.prepareStatement(Queries.INSERT_DISTRIBUTIE);

				pst.setDate(1, distributie.getDistributiedatum());
				Util.pstSetInt(pst, 2, distributie.getMediumId());
				Util.pstSetInt(pst, 3, distributie.getSjabloonId());

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
	 * Updates a Distributie
	 * @return Return boolean
	 * @param distributie The Distributie to change
	 */

	public static boolean update(Distributie distributie) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.UPDATE_DISTRIBUTIE);

				pst.setDate(1, distributie.getDistributiedatum());
				Util.pstSetInt(pst, 2, distributie.getMediumId());
				Util.pstSetInt(pst, 3, distributie.getSjabloonId());
				pst.setInt(4, distributie.getId());

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
	 * deletes a Distributie
	 * @return Return boolean
	 * @param id The id to delete
	 */

  	public static boolean delete(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_DISTRIBUTIE);

				pst.setInt(1, id);

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

