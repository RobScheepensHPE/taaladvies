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

public class Bron implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = -1922548941143229737L;
	private int id;
	private String variant;
	private String titel;
	private String titelHTML;
	private String citaat;
	private String citaatHTML;
	private String paginas;

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
	 * Gets the titel
	 * @return Returns a String
	 */
	public String getTitel() {
		return titel;
	}
	/**
	 * Sets the titel
	 * @param titel The titel to set
	 */
	public void setTitel(String titel) {
		this.titel = titel;
	}


	/**
	 * Gets the titelHTML
	 * @return Returns a String
	 */
	public String getTitelHTML() {
		return titelHTML;
	}
	/**
	 * Sets the titelHTML
	 * @param titelHTML The titelHTML to set
	 */
	public void setTitelHTML(String titelHTML) {
		this.titelHTML = titelHTML;
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
	 * Gets the Bron from the database by it's own id
	 * @return Returns a Bron
	 * @param id The id from the Bron to retrieve
	 */
	public static Bron findByPK(int id) {


		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Bron bron = new Bron();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.BRON_BY_PK);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if (rs.next()) {

					bron.setId(id);
					bron.setVariant(rs.getString("variant"));
					bron.setTitel(rs.getString("titel"));
					bron.setTitelHTML(rs.getString("titelHTML"));
					bron.setCitaat(rs.getString("citaat"));
					bron.setCitaatHTML(rs.getString("citaatHTML"));
					bron.setPaginas(rs.getString("paginas"));
					bron.setTaalvraagId(rs.getInt("taalvraagId"));
					bron.setTekstId(rs.getInt("tekstId"));

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

		return bron;
	}


	/**
	 * Gets all Bronnen from the database by the parentId
	 * @return Returns a ArrayList
	 * @param taalvraagId The taalvraagId to use (or left as a value "0" if not used)
	 * @param tekstId The tekstId to use (or left as a value "0" if not used)
	 */

	public static ArrayList<Bron> findAllByParent(int taalvraagId, int tekstId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Bron> allBronnen = new ArrayList<Bron>();

		try {
			if (con != null) {
				if (taalvraagId != 0) {
					pst = con.prepareStatement(Queries.BRONNEN_BY_PARENT_TLV);
					pst.setInt(1, taalvraagId);
				}
				else {
					pst = con.prepareStatement(Queries.BRONNEN_BY_PARENT_TKS);
					pst.setInt(1, tekstId);
				}
				rs = pst.executeQuery();
				while (rs.next()) {

					Bron bron = new Bron();
					bron.setId(rs.getInt("id"));
					bron.setVariant(rs.getString("variant"));
					bron.setTitel(rs.getString("titel"));
					bron.setTitelHTML(rs.getString("titelHTML"));
					bron.setCitaat(rs.getString("citaat"));
					bron.setCitaatHTML(rs.getString("citaatHTML"));
					bron.setPaginas(rs.getString("paginas"));
					bron.setTaalvraagId(rs.getInt("taalvraagId"));
					bron.setTekstId(rs.getInt("tekstId"));
					allBronnen.add(bron);
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


		return allBronnen;
	}



	/**
	 * Inserts a Bron
	 * @return Return int
	 * @param bron The Bron to insert
	 */

	public static int insert(Bron bron) {

		int id = 0;
		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try {
			if (con !=null) {
				pst = con.prepareStatement(Queries.INSERT_BRON);

				pst.setString(1, bron.getVariant());
				Util.pstSetClob(pst, 2, bron.getTitel());
				Util.pstSetClob(pst, 3, bron.getTitelHTML());
				Util.pstSetClob(pst, 4, bron.getCitaat());
				Util.pstSetClob(pst, 5, bron.getCitaatHTML());
				pst.setString(6, bron.getPaginas());
				Util.pstSetInt(pst, 7, bron.getTaalvraagId());
				Util.pstSetInt(pst, 8, bron.getTekstId());

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
	 * Updates a Bron
	 * @return Return boolean
	 * @param bron The Bron to change
	 */

	public static boolean update(Bron bron) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.UPDATE_BRON);

				pst.setString(1, bron.getVariant());
				Util.pstSetClob(pst, 2, bron.getTitel());
				Util.pstSetClob(pst, 3, bron.getTitelHTML());
				Util.pstSetClob(pst, 4, bron.getCitaat());
				Util.pstSetClob(pst, 5, bron.getCitaatHTML());
				pst.setString(6, bron.getPaginas());
				Util.pstSetInt(pst, 7, bron.getTaalvraagId());
				Util.pstSetInt(pst, 8, bron.getTekstId());
				pst.setInt(9, bron.getId());

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
	 * deletes a Bron
	 * @return Return boolean
	 * @param bron The Bron to delete
	 */

  	public static boolean delete(Bron bron) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_BRON);

				pst.setInt(1, bron.getId());

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
	 * deletes all Bronnen for a taalvraag
	 * @return Return boolean
	 * @param id The taalvraagid to delete bronnen for
	 */

  	public static boolean deleteForTaalvraag(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_ALL_BRONNEN_FOR_TAALVRAAG);

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
	 * deletes all Bronnen for a tekst
	 * @return Return boolean
	 * @param id The tekstid to delete bronnen for
	 */

  	public static boolean deleteForTekst(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_ALL_BRONNEN_FOR_TEKST);

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


