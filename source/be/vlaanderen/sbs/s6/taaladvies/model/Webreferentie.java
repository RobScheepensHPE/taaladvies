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

public class Webreferentie implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = -4071720611691553476L;
	private int id;
	private String url;
	private String toelichting;
	private String toelichtingHTML;
	private String omgeving;

	// one of these allways will be 0
	private int taalvraagId;
	private int tekstId;


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
	 * Gets the toelichting
	 * @return Returns a String
	 */
	public String getToelichting() {
		return toelichting;
	}
	/**
	 * Sets the toelichting
	 * @param toelichting The toelichting to set
	 */
	public void setToelichting(String toelichting) {
		this.toelichting = toelichting;
	}




	/**
	 * Gets the toelichtingHTML
	 * @return Returns a String
	 */
	public String getToelichtingHTML() {
		return toelichtingHTML;
	}
	/**
	 * Sets the toelichtingHTML
	 * @param toelichtingHTML The toelichtingHTML to set
	 */
	public void setToelichtingHTML(String toelichtingHTML) {
		this.toelichtingHTML = toelichtingHTML;
	}




	/**
	 * Gets the omgeving
	 * @return Returns a String
	 */
	public String getOmgeving() {
		return omgeving;
	}
	/**
	 * Sets the omgeving
	 * @param omgeving The omgeving to set
	 */
	public void setOmgeving(String omgeving) {
		this.omgeving = omgeving;
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
	 * Gets the Webreferentie from the database by it's own id
	 * @return Returns a Web
	 * @param id The id from the Web to retrieve
	 */
	public static Webreferentie findByPK(int id) {


		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Webreferentie webreferentie = new Webreferentie();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.WEBREFERENTIE_BY_PK);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if (rs.next()) {

					webreferentie.setId(id);
					webreferentie.setUrl(rs.getString("url"));
					webreferentie.setToelichtingHTML(rs.getString("toelichtingHTML"));
					webreferentie.setToelichting(rs.getString("toelichting"));
					webreferentie.setOmgeving(rs.getString("omgeving"));
					webreferentie.setTaalvraagId(rs.getInt("taalvraagId"));
					webreferentie.setTekstId(rs.getInt("tekstId"));

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


		return webreferentie;


	}



	/**
	 * Gets all Webreferenties from the database by the parentId
	 * @return Returns a ArrayList
	 * @param taalvraagId The taalvraagId to use (or left as a value "0" if not used)
	 * @param tekstId The tekstId to use (or left as a value "0" if not used)
	 */

	public static ArrayList<Webreferentie> findAllByParent(int taalvraagId, int tekstId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Webreferentie> allWebreferenties = new ArrayList<Webreferentie>();

		try {
			if (con != null) {
				if (taalvraagId != 0) {
					pst = con.prepareStatement(Queries.WEBREFERENTIES_BY_PARENT_TLV);
					pst.setInt(1, taalvraagId);
				}
				else {
					pst = con.prepareStatement(Queries.WEBREFERENTIES_BY_PARENT_TKS);
					pst.setInt(1, tekstId);
				}
				rs = pst.executeQuery();
				while (rs.next()) {

					Webreferentie webreferentie = new Webreferentie();
					webreferentie.setId(rs.getInt("id"));
					webreferentie.setUrl(rs.getString("url"));
					webreferentie.setToelichtingHTML(rs.getString("toelichtingHTML"));
					webreferentie.setToelichting(rs.getString("toelichting"));
					webreferentie.setOmgeving(rs.getString("omgeving"));
					webreferentie.setTaalvraagId(rs.getInt("taalvraagId"));
					webreferentie.setTekstId(rs.getInt("tekstId"));
					allWebreferenties.add(webreferentie);

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


		return allWebreferenties;
	}



	/**
	 * Inserts a Webreferentie
	 * @return Return int
	 * @param webreferentie The Webreferentie to insert
	 */

	public static int insert(Webreferentie webreferentie) {

		int id = 0;
		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try {
			if (con !=null) {
				pst = con.prepareStatement(Queries.INSERT_WEBREFERENTIE);


				pst.setString(1, webreferentie.getUrl());
				Util.pstSetClob(pst, 2, webreferentie.getToelichting());
				Util.pstSetClob(pst, 3, webreferentie.getToelichtingHTML());
				Util.pstSetInt(pst, 4, webreferentie.getTaalvraagId());
				Util.pstSetInt(pst, 5, webreferentie.getTekstId());
				pst.setString(6, webreferentie.getOmgeving());
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
		                    if (pst != null) {
		                        pst.close();
		                    }
					dbconnection.releaseConnection(con);
				}


			} catch (java.sql.SQLException sqle) {
				sqle.printStackTrace(System.err);
			}
		}


		return id;
	}

	/**
	 * Updates a Webreferentie
	 * @return Return boolean
	 * @param webreferentie The Webreferentie to change
	 */


	public static boolean update(Webreferentie webreferentie) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.UPDATE_WEBREFERENTIE);

				pst.setString(1, webreferentie.getUrl());
				Util.pstSetClob(pst, 2, webreferentie.getToelichting());
				Util.pstSetClob(pst, 3, webreferentie.getToelichtingHTML());
				Util.pstSetInt(pst, 4, webreferentie.getTaalvraagId());
				Util.pstSetInt(pst, 5, webreferentie.getTekstId());
				pst.setString(6, webreferentie.getOmgeving());
				pst.setInt(7, webreferentie.getId());

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
                    if (pst != null) {
                        pst.close();
                    }
                	dbconnection.releaseConnection(con);
            	}


        	} catch (java.sql.SQLException sqle) {
            	sqle.printStackTrace(System.err);
        	}
    	}
	}


	/**
	 * deletes a Webreferentie
	 * @return Return boolean
	 * @param notitie The Webreferentie to delete
	 */

  	public static boolean delete(Webreferentie webreferentie) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_WEBREFERENTIE);

				pst.setInt(1, webreferentie.getId());

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
                    if (pst != null) {
                        pst.close();
                    }
                	dbconnection.releaseConnection(con);
            	}


        	} catch (java.sql.SQLException sqle) {
            	sqle.printStackTrace(System.err);
        	}
    	}
	}

	/**
	 * deletes all Webreferenties for a taalvraag
	 * @return Return boolean
	 * @param id The taalvraagId to delete webreferenties for
	 */


  	public static boolean deleteForTaalvraag(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_ALL_WEBREFERENTIES_FOR_TAALVRAAG);

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
                    if (pst != null) {
                        pst.close();
                    }
                	dbconnection.releaseConnection(con);
            	}


        	} catch (java.sql.SQLException sqle) {
            	sqle.printStackTrace(System.err);
        	}
    	}
	}


	/**
	 * deletes all Webreferenties for a tekst
	 * @return Return boolean
	 * @param id The tekstId to delete webreferenties for
	 */


  	public static boolean deleteForTekst(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_ALL_WEBREFERENTIES_FOR_TEKST);

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
                    if (pst != null) {
                        pst.close();
                    }
                	dbconnection.releaseConnection(con);
            	}


        	} catch (java.sql.SQLException sqle) {
            	sqle.printStackTrace(System.err);
        	}
    	}
	}


}


