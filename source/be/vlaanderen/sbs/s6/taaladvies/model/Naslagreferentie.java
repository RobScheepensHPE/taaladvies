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

public class Naslagreferentie implements Serializable {


	/**
     * 
     */
    private static final long serialVersionUID = -7984087235326112142L;
	private int id;
	private String variant;
	private String citaat;
	private String citaatHTML;
	private String paginas;

	private int naslagwerkId;

	// one of these allways will be 0
	private int taalvraagId;
	private int tekstId;

	private Naslagwerk naslagwerk;


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
	 * Gets the paginas
	 * @return Returns a String
	 */
	public String getPaginas() {
		return paginas;
	}
	/**
	 * Sets the paginas
	 * @param paginas The paginas to set
	 */
	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}


	/**
	 * Gets the naslagwerkId
	 * @return Returns a int
	 */
	public int getNaslagwerkId() {
		return naslagwerkId;
	}
	/**
	 * Sets the naslagwerkId
	 * @param naslagwerkId The naslagwerkId to set
	 */
	public void setNaslagwerkId(int naslagwerkId) {
		this.naslagwerkId = naslagwerkId;
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
	 * Gets the naslagwerk
	 * @return Returns a Naslagwerk
	 */
	public Naslagwerk getNaslagwerk() {
		if (naslagwerk == null) {
			if (naslagwerkId != 0) {
				naslagwerk = (Naslagwerk)Naslagwerk.findByPK(naslagwerkId);
			}
			else {
				naslagwerk = new Naslagwerk();
			}
		}
		return naslagwerk;
	}
	/**
	 * Sets the naslagwerk
	 * @param naslagwerk The naslagwerk to set
	 */
	public void setNaslagwerk(Naslagwerk naslagwerk) {
		this.naslagwerk = naslagwerk;
	}


	/**
	 * Gets the Naslagreferentie from the database by it's own id
	 * @return Returns a Naslagreferentie
	 * @param id The id from the Naslagreferentie to retrieve
	 */
	public static Naslagreferentie findByPK(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Naslagreferentie naslagreferentie = new Naslagreferentie();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.NASLAGREFERENTIE_BY_PK);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if (rs.next()) {

					naslagreferentie.setId(id);
					naslagreferentie.setVariant(rs.getString("variant"));
					naslagreferentie.setCitaat(rs.getString("citaat"));
					naslagreferentie.setCitaatHTML(rs.getString("citaatHTML"));
					naslagreferentie.setPaginas(rs.getString("paginas"));
					naslagreferentie.setNaslagwerkId(rs.getInt("naslagwerkId"));
					naslagreferentie.setTaalvraagId(rs.getInt("taalvraagId"));
					naslagreferentie.setTekstId(rs.getInt("tekstId"));

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

		return naslagreferentie;

	}


	/**
	 * Gets all Naslagreferenties from the database by the parentId
	 * @return Returns a ArrayList
	 * @param taalvraagId The taalvraagId to use (or left as a value "0" if not used)
	 * @param tekstId The tekstId to use (or left as a value "0" if not used)
	 */

	public static ArrayList<Naslagreferentie> findAllByParent(int taalvraagId, int tekstId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Naslagreferentie> allNaslagreferenties = new ArrayList<Naslagreferentie>();

		try {
			if (con != null) {
				if (taalvraagId != 0) {
					pst = con.prepareStatement(Queries.NASLAGREFERENTIES_BY_PARENT_TLV);
					pst.setInt(1, taalvraagId);
				}
				else {
					pst = con.prepareStatement(Queries.NASLAGREFERENTIES_BY_PARENT_TKS);
					pst.setInt(1, tekstId);
				}
				rs = pst.executeQuery();
				while (rs.next()) {

					Naslagreferentie naslagreferentie = new Naslagreferentie();
					naslagreferentie.setId(rs.getInt("id"));
					naslagreferentie.setVariant(rs.getString("variant"));
					naslagreferentie.setCitaat(rs.getString("citaat"));
					naslagreferentie.setCitaatHTML(rs.getString("citaatHTML"));
					naslagreferentie.setPaginas(rs.getString("paginas"));
					naslagreferentie.setNaslagwerkId(rs.getInt("naslagwerkId"));
					naslagreferentie.setTaalvraagId(rs.getInt("taalvraagId"));
					naslagreferentie.setTekstId(rs.getInt("tekstId"));
					allNaslagreferenties.add(naslagreferentie);

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


		return allNaslagreferenties;
	}



	/**
	 * Inserts a Naslagreferentie
	 * @return Return int
	 * @param naslagreferentie The Naslagreferentie to insert
	 */

	public static int insert(Naslagreferentie naslagreferentie) {

		int id = 0;
		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try {
			if (con !=null) {
				pst = con.prepareStatement(Queries.INSERT_NASLAGREFERENTIE);

				pst.setString(1, naslagreferentie.getVariant());
				Util.pstSetClob(pst, 2, naslagreferentie.getCitaat());
				Util.pstSetClob(pst, 3, naslagreferentie.getCitaatHTML());
				pst.setString(4, naslagreferentie.getPaginas());
				Util.pstSetInt(pst, 5, naslagreferentie.getNaslagwerkId());
				Util.pstSetInt(pst, 6, naslagreferentie.getTaalvraagId());
				Util.pstSetInt(pst, 7, naslagreferentie.getTekstId());

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
	 * Updates a Naslagreferentie
	 * @return Return boolean
	 * @param naslagreferentie The Naslagreferentie to change
	 */


	public static boolean update(Naslagreferentie naslagreferentie) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.UPDATE_NASLAGREFERENTIE);

				pst.setString(1, naslagreferentie.getVariant());
				Util.pstSetClob(pst, 2, naslagreferentie.getCitaat());
				Util.pstSetClob(pst, 3, naslagreferentie.getCitaatHTML());
				pst.setString(4, naslagreferentie.getPaginas());
				Util.pstSetInt(pst, 5, naslagreferentie.getNaslagwerkId());
				Util.pstSetInt(pst, 6, naslagreferentie.getTaalvraagId());
				Util.pstSetInt(pst, 7, naslagreferentie.getTekstId());
				pst.setInt(8, naslagreferentie.getId());

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
	 * deletes a Naslagreferentie
	 * @return Return boolean
	 * @param naslagreferentie The Naslagreferentie to delete
	 */

  	public static boolean delete(Naslagreferentie naslagreferentie) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_NASLAGREFERENTIE);

				pst.setInt(1, naslagreferentie.getId());

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
	 * deletes all Naslagreferenties for a taalvraag
	 * @return Return boolean
	 * @param id The taalvraagId to delete naslagreferenties for
	 */

  	public static boolean deleteForTaalvraag(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_ALL_NASLAGREFERENTIES_FOR_TAALVRAAG);

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
	 * deletes all Naslagreferenties for a tekst
	 * @return Return boolean
	 * @param id The tekstId to delete naslagreferenties for
	 */

  	public static boolean deleteForTekst(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_ALL_NASLAGREFERENTIES_FOR_TEKST);

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


