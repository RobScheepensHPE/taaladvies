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

public class Citaat implements Serializable {


	/**
     * 
     */
    private static final long serialVersionUID = 1834152802251089987L;
	private int id;
	private String variant;
	private String specificatie;
	private String citaat;
	private String citaatHTML;
	private String url;

	private int zoekomgevingId;

	// one of these allways will be 0
	private int taalvraagId;
	private int tekstId;

	private ParameterActief zoekomgeving;


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
	 * Gets the variant
	 * @return Returns a String
	 */
	public String getVariant() {
		return variant;
	}
	/**
	 * Sets the variant
	 * @param variant The variant to set
	 */
	public void setVariant(String variant) {
		this.variant = variant;
	}


	/**
	 * Gets the specificatie
	 * @return Returns a String
	 */
	public String getSpecificatie() {
		return specificatie;
	}
	/**
	 * Sets the specificatie
	 * @param specificatie The specificatie to set
	 */
	public void setSpecificatie(String specificatie) {
		this.specificatie = specificatie;
	}


	/**
	 * Gets the citaat
	 * @return Returns a String
	 */
	public String getCitaat() {
		return citaat;
	}
	/**
	 * Sets the citaat
	 * @param citaat The citaat to set
	 */
	public void setCitaat(String citaat) {
		this.citaat = citaat;
	}


	/**
	 * Gets the citaatHTML
	 * @return Returns a String
	 */
	public String getCitaatHTML() {
		return citaatHTML;
	}
	/**
	 * Sets the citaatHTML
	 * @param citaatHTML The citaatHTML to set
	 */
	public void setCitaatHTML(String citaatHTML) {
		this.citaatHTML = citaatHTML;
	}


	/**
	 * Gets the url
	 * @return Returns a String
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * Sets the url
	 * @param url The url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	/**
	 * Gets the zoekomgevingId
	 * @return Returns a int
	 */
	public int getZoekomgevingId() {
		return zoekomgevingId;
	}
	/**
	 * Sets the zoekomgevingId
	 * @param zoekomgevingId The zoekomgevingId to set
	 */
	public void setZoekomgevingId(int zoekomgevingId) {
		this.zoekomgevingId = zoekomgevingId;
	}


	/**
	 * Gets the taalvraagId
	 * @return Returns a int
	 */
	public int getTaalvraagId() {
		return taalvraagId;
	}
	/**
	 * Sets the taalvraagId
	 * @param taalvraagId The taalvraagId to set
	 */
	public void setTaalvraagId(int taalvraagId) {
		this.taalvraagId = taalvraagId;
	}


	/**
	 * Gets the tekstId
	 * @return Returns a int
	 */
	public int getTekstId() {
		return tekstId;
	}
	/**
	 * Sets the tekstId
	 * @param tekstId The tekstId to set
	 */
	public void setTekstId(int tekstId) {
		this.tekstId = tekstId;
	}


	/**
	 * Gets the zoekomgeving
	 * @return Returns a ParameterActief
	 */
	public ParameterActief getZoekomgeving() {
		if (zoekomgeving == null) {
			if (zoekomgevingId != 0) {
				zoekomgeving = (ParameterActief)ParameterActief.findByPK(Queries.ZOEKOMGEVING_BY_PK, zoekomgevingId);
			}
			else {
				zoekomgeving = new ParameterActief();
			}
		}
		return zoekomgeving;
	}
	/**
	 * Sets the zoekomgeving
	 * @param zoekomgeving The zoekomgeving to set
	 */
	public void setZoekomgeving(ParameterActief zoekomgeving) {
		this.zoekomgeving = zoekomgeving;
	}


	/**
	 * Gets the Citaat from the database by it's own id
	 * @return Returns a Citaat
	 * @param id The id from the Citaat to retrieve
	 */
	public static Citaat findByPK(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Citaat citaat = new Citaat();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.CITAAT_BY_PK);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if (rs.next()) {

					citaat.setId(id);
					citaat.setVariant(rs.getString("variant"));
					citaat.setSpecificatie(rs.getString("specificatie"));
					citaat.setCitaat(rs.getString("citaat"));
					citaat.setCitaatHTML(rs.getString("citaatHTML"));
					citaat.setUrl(rs.getString("url"));
					citaat.setZoekomgevingId(rs.getInt("zoekomgevingId"));
					citaat.setTaalvraagId(rs.getInt("taalvraagId"));
					citaat.setTekstId(rs.getInt("tekstId"));
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

		return citaat;
	}


	/**
	 * Gets all Citaten from the database by the parentId
	 * @return Returns a ArrayList
	 * @param taalvraagId The taalvraagId to use (or left as a value "0" if not used)
	 * @param tekstId The tekstId to use (or left as a value "0" if not used)
	 */

	public static ArrayList<Citaat> findAllByParent(int taalvraagId, int tekstId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Citaat> allCitaten = new ArrayList<Citaat>();

		try {
			if (con != null) {
				if (taalvraagId != 0) {
					pst = con.prepareStatement(Queries.CITATEN_BY_PARENT_TLV);
					pst.setInt(1, taalvraagId);
				}
				else {
					pst = con.prepareStatement(Queries.CITATEN_BY_PARENT_TKS);
					pst.setInt(1, tekstId);
				}
				rs = pst.executeQuery();
				while (rs.next()) {

					Citaat citaat = new Citaat();
					citaat.setId(rs.getInt("id"));
					citaat.setVariant(rs.getString("variant"));
					citaat.setSpecificatie(rs.getString("specificatie"));
					citaat.setCitaat(rs.getString("citaat"));
					citaat.setCitaatHTML(rs.getString("citaatHTML"));
					citaat.setUrl(rs.getString("url"));
					citaat.setZoekomgevingId(rs.getInt("zoekomgevingId"));
					citaat.setTaalvraagId(rs.getInt("taalvraagId"));
					citaat.setTekstId(rs.getInt("tekstId"));
					allCitaten.add(citaat);
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

		return allCitaten;
	}



	/**
	 * Inserts a Citaat
	 * @return Return int
	 * @param citaat The Citaat to insert
	 */

	public static int insert(Citaat citaat) {

		int id = 0;
		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try {
			if (con !=null) {
				pst = con.prepareStatement(Queries.INSERT_CITAAT);

				pst.setString(1, citaat.getVariant());
				pst.setString(2, citaat.getSpecificatie());
				Util.pstSetClob(pst, 3, citaat.getCitaat());
				Util.pstSetClob(pst, 4, citaat.getCitaatHTML());
				pst.setString(5, citaat.getUrl());
				Util.pstSetInt(pst, 6, citaat.getZoekomgevingId());
				Util.pstSetInt(pst, 7, citaat.getTaalvraagId());
				Util.pstSetInt(pst, 8, citaat.getTekstId());

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
	 * Updates a Citaat
	 * @return Return boolean
	 * @param citaat The Citaat to change
	 */

	public static boolean update(Citaat citaat) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.UPDATE_CITAAT);

				pst.setString(1, citaat.getVariant());
				pst.setString(2, citaat.getSpecificatie());
				Util.pstSetClob(pst, 3, citaat.getCitaat());
				Util.pstSetClob(pst, 4, citaat.getCitaatHTML());
				pst.setString(5, citaat.getUrl());
				Util.pstSetInt(pst, 6, citaat.getZoekomgevingId());
				Util.pstSetInt(pst, 7, citaat.getTaalvraagId());
				Util.pstSetInt(pst, 8, citaat.getTekstId());
				pst.setInt(9, citaat.getId());

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
	 * deletes a Citaat
	 * @return Return boolean
	 * @param citaat The Citaat to delete
	 */

  	public static boolean delete(Citaat citaat) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_CITAAT);

				pst.setInt(1, citaat.getId());

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
	 * deletes all Citaten for a taalvraag
	 * @return Return boolean
	 * @param id The taalvraagId to delete citaten for
	 */

  	public static boolean deleteForTaalvraag(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_ALL_CITATEN_FOR_TAALVRAAG);

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

	/**
	 * deletes all Citaten for a tekst
	 * @return Return boolean
	 * @param id The tekstId to delete citaten for
	 */

  	public static boolean deleteForTekst(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_ALL_CITATEN_FOR_TEKST);

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


