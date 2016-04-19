package be.vlaanderen.sbs.s6.taaladvies.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import be.vlaanderen.sbs.s6.taaladvies.Queries;
import be.vlaanderen.sbs.s6.taaladvies.db.IDbConnection;
import be.vlaanderen.sbs.s6.taaladvies.db.MyDbConnection;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;
import com.informix.jdbc.IfxStatement;

public class Notitie implements Serializable {


	/**
     * 
     */
    private static final long serialVersionUID = 8419522318227915568L;
	private int id;
	private String notitie;
	private String notitieHTML;
	private Date datum = new Date(new java.util.Date().getTime());
	private String datumString;

	private int gebruikerId;

	// one of these allways will be 0
	private int taalvraagId;
	private int tekstId;

	private Gebruiker gebruiker;

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
	 * Gets the notitie
	 * @return Returns a String
	 */
	public String getNotitie() {
		return notitie;
	}
	/**
	 * Sets the notitie
	 * @param notitie The notitie to set
	 */
	public void setNotitie(String notitie) {
		this.notitie = notitie;
	}


	/**
	 * Gets the notitieHTML
	 * @return Returns a String
	 */
	public String getNotitieHTML() {
		return notitieHTML;
	}
	/**
	 * Sets the notitieHTML
	 * @param notitieHTML The notitieHTML to set
	 */
	public void setNotitieHTML(String notitieHTML) {
		this.notitieHTML = notitieHTML;
	}


	/**
	 * Gets the datum
	 * @return Returns a Date
	 */
	public Date getDatum() {
		return datum;
	}
	/**
	 * Sets the datum
	 * @param datum The datum to set
	 */
	public void setDatum(Date datum) {
		this.datum = datum;
	}


	/**
	 * Gets the datumString
	 * @return Returns a String
	 */
	public String getDatumString() {

		if (datum != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			String dateString = formatter.format(datum);
			return dateString;
		}
		else {
			return datumString;
		}
	}
	/**
	 * Sets the datumString
	 * @param datumString The datumString to set
	 */
	public void setDatumString(String datumString) {
		this.datumString = datumString;
	}


	/**
	 * Gets the gebruikerId
	 * @return Returns a int
	 */
	public int getGebruikerId() {
		return gebruikerId;
	}
	/**
	 * Sets the gebruikerId
	 * @param gebruikerId The gebruikerId to set
	 */
	public void setGebruikerId(int gebruikerId) {
		this.gebruikerId = gebruikerId;
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
	 * Gets the gebruiker
	 * @return Returns a Gebruiker
	 */
	public Gebruiker getGebruiker() {
		if (gebruiker == null) {
			if (gebruikerId != 0) {
				gebruiker = (Gebruiker)Gebruiker.findByPK(gebruikerId);
			}
			else {
				gebruiker = new Gebruiker();
			}
		}
		return gebruiker;
	}
	/**
	 * Sets the gebruiker
	 * @param gebruiker The gebruiker to set
	 */
	public void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}


	/**
	 * Gets the Notitie from the database by it's own id
	 * @return Returns a Notitie
	 * @param id The id from the Notitie to retrieve
	 */
	public static Notitie findByPK(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Notitie notitie = new Notitie();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.NOTITIE_BY_PK);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if (rs.next()) {

					notitie.setId(id);
					notitie.setNotitie(rs.getString("notitie"));
					notitie.setNotitieHTML(rs.getString("notitieHTML"));
					notitie.setDatum(rs.getDate("datum"));
					notitie.setGebruikerId(rs.getInt("gebruikerId"));
					notitie.setTaalvraagId(rs.getInt("taalvraagId"));
					notitie.setTekstId(rs.getInt("tekstId"));

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

		return notitie;

	}


	/**
	 * Gets all Notities from the database by the parentId
	 * @return Returns a ArrayList
	 * @param taalvraagId The taalvraagId to use (or left as a value "0" if not used)
	 * @param tekstId The tekstId to use (or left as a value "0" if not used)
	 */

	public static ArrayList<Notitie> findAllByParent(int taalvraagId, int tekstId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Notitie> allNotities = new ArrayList<Notitie>();

		try {
			if (con != null) {
				if (taalvraagId != 0) {
					pst = con.prepareStatement(Queries.NOTITIES_BY_PARENT_TLV);
					pst.setInt(1, taalvraagId);
				}
				else {
					pst = con.prepareStatement(Queries.NOTITIES_BY_PARENT_TKS);
					pst.setInt(1, tekstId);
				}
				rs = pst.executeQuery();
				while (rs.next()) {

					Notitie notitie = new Notitie();
					notitie.setId(rs.getInt("id"));
					notitie.setNotitie(rs.getString("notitie"));
					notitie.setNotitieHTML(rs.getString("notitieHTML"));
					notitie.setDatum(rs.getDate("datum"));
					notitie.setGebruikerId(rs.getInt("gebruikerId"));
					notitie.setTaalvraagId(rs.getInt("taalvraagId"));
					notitie.setTekstId(rs.getInt("tekstId"));
					allNotities.add(notitie);

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

		return allNotities;
	}


	/**
	 * Inserts a Notitie
	 * @return Return int
	 * @param notitie The Notitie to insert
	 */

	public static int insert(Notitie notitie) {

		int id = 0;
		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		ParsePosition pos1 = new ParsePosition(0);
		try {
			if (con !=null) {
				pst = con.prepareStatement(Queries.INSERT_NOTITIE);

				Util.pstSetClob(pst, 1, notitie.getNotitie());
				Util.pstSetClob(pst, 2, notitie.getNotitieHTML());

				if (notitie.getDatumString() != null && !notitie.getDatumString().equals("")) {
					java.util.Date datumUtilDate = df.parse(notitie.getDatumString(), pos1);
					Date datumSqlDate = new java.sql.Date(datumUtilDate.getTime());
					pst.setDate(3, datumSqlDate);
				}
				Util.pstSetInt(pst, 4, notitie.getGebruikerId());
				Util.pstSetInt(pst, 5, notitie.getTaalvraagId());
				Util.pstSetInt(pst, 6, notitie.getTekstId());

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
	 * Updates a Notitie
	 * @return Return boolean
	 * @param notitie The Notitie to change
	 */

	public static boolean update(Notitie notitie) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		ParsePosition pos1 = new ParsePosition(0);
		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.UPDATE_NOTITIE);

				Util.pstSetClob(pst, 1, notitie.getNotitie());
				Util.pstSetClob(pst, 2, notitie.getNotitieHTML());

				if (notitie.getDatumString() != null && !notitie.getDatumString().equals("")) {
					java.util.Date datumUtilDate = df.parse(notitie.getDatumString(), pos1);
					Date datumSqlDate = new java.sql.Date(datumUtilDate.getTime());
					pst.setDate(3, datumSqlDate);
				}
				Util.pstSetInt(pst, 4, notitie.getGebruikerId());
				Util.pstSetInt(pst, 5, notitie.getTaalvraagId());
				Util.pstSetInt(pst, 6, notitie.getTekstId());
				pst.setInt(7, notitie.getId());

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
	 * deletes a Notitie
	 * @return Return boolean
	 * @param notitie The Notitie to delete
	 */

  	public static boolean delete(Notitie notitie) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_NOTITIE);

				pst.setInt(1, notitie.getId());

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
	 * deletes all Notities for a taalvraag
	 * @return Return boolean
	 * @param id The taalvraagId to delete notities for
	 */

  	public static boolean deleteForTaalvraag(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_ALL_NOTITIES_FOR_TAALVRAAG);

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
	 * deletes all Notities for a tekst
	 * @return Return boolean
	 * @param id The tekstId to delete notities for
	 */

  	public static boolean deleteForTekst(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_ALL_NOTITIES_FOR_TEKST);

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

