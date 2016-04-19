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

public class Frequentie implements Serializable {


	/**
     * 
     */
    private static final long serialVersionUID = 4329997080751106612L;
	private int id;
	private String variant;
	private String specificatie;
	private String aantal;

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
	 * Gets the aantal
	 * @return Returns a String
	 */
	public String getAantal() {
		return aantal;
	}
	/**
	 * Sets the aantal
	 * @param aantal The aantal to set
	 */
	public void setAantal(String aantal) {
		this.aantal = aantal;
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
	 * Gets the Frequentie from the database by it's own id
	 * @param id The id from the Frequentie to retrieve
	 */
	public static Frequentie findByPK(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Frequentie frequentie = new Frequentie();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.FREQUENTIE_BY_PK);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if (rs.next()) {

					frequentie.setId(id);
					frequentie.setVariant(rs.getString("variant"));
					frequentie.setSpecificatie(rs.getString("specificatie"));
					frequentie.setAantal(rs.getString("aantal"));
					frequentie.setZoekomgevingId(rs.getInt("zoekomgevingId"));
					frequentie.setTaalvraagId(rs.getInt("taalvraagId"));
					frequentie.setTekstId(rs.getInt("tekstId"));
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

		return frequentie;
	}


	/**
	 * Gets all Frequenties from the database by the parentId
	 * @return Returns a ArrayList
	 * @param taalvraagId The taalvraagId to use (or left as a value "0" if not used)
	 * @param tekstId The tekstId to use (or left as a value "0" if not used)
	 */

	public static ArrayList<Frequentie> findAllByParent(int taalvraagId, int tekstId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Frequentie> allFrequenties = new ArrayList<Frequentie>();

		try {
			if (con != null) {

				if (taalvraagId != 0) {
					pst = con.prepareStatement(Queries.FREQUENTIES_BY_PARENT_TLV);
					pst.setInt(1, taalvraagId);
				}
				else {
					pst = con.prepareStatement(Queries.FREQUENTIES_BY_PARENT_TKS);
					pst.setInt(1, tekstId);
				}
				rs = pst.executeQuery();
				while (rs.next()) {

					Frequentie frequentie = new Frequentie();
					frequentie.setId(rs.getInt("id"));
					frequentie.setVariant(rs.getString("variant"));
					frequentie.setSpecificatie(rs.getString("specificatie"));
					frequentie.setAantal(rs.getString("aantal"));
					frequentie.setZoekomgevingId(rs.getInt("zoekomgevingId"));
					frequentie.setTaalvraagId(rs.getInt("taalvraagId"));
					frequentie.setTekstId(rs.getInt("tekstId"));
					allFrequenties.add(frequentie);

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


		return allFrequenties;
	}



	/**
	 * Inserts a Frequentie
	 * @return Return int
	 * @param frequentie The Frequentie to insert
	 */

	public static int insert(Frequentie frequentie) {

		int id = 0;
		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try {
			if (con !=null) {
				pst = con.prepareStatement(Queries.INSERT_FREQUENTIE);

				pst.setString(1, frequentie.getVariant());
				pst.setString(2, frequentie.getSpecificatie());
				pst.setString(3, frequentie.getAantal());
				Util.pstSetInt(pst, 4, frequentie.getZoekomgevingId());
				Util.pstSetInt(pst, 5, frequentie.getTaalvraagId());
				Util.pstSetInt(pst, 6, frequentie.getTekstId());

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
	 * Updates a Frequentie
	 * @return Return boolean
	 * @param frequentie The Frequentie to change
	 */


	public static boolean update(Frequentie frequentie) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.UPDATE_FREQUENTIE);

				pst.setString(1, frequentie.getVariant());
				pst.setString(2, frequentie.getSpecificatie());
				pst.setString(3, frequentie.getAantal());
				Util.pstSetInt(pst, 4, frequentie.getZoekomgevingId());
				Util.pstSetInt(pst, 5, frequentie.getTaalvraagId());
				Util.pstSetInt(pst, 6, frequentie.getTekstId());
				pst.setInt(7, frequentie.getId());

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
	 * deletes a Frequentie
	 * @return Return boolean
	 * @param frequentie The Frequentie to delete
	 */

  	public static boolean delete(Frequentie frequentie) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_FREQUENTIE);

				pst.setInt(1, frequentie.getId());

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
	 * deletes all Frequenties for a taalvraag
	 * @return Return boolean
	 * @param id The taalvraagId to delete frequenties for
	 */

  	public static boolean deleteForTaalvraag(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_ALL_FREQUENTIES_FOR_TAALVRAAG);

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
	 * deletes all Frequenties for a tekst
	 * @return Return boolean
	 * @param id The tekstId to delete frequenties for
	 */

  	public static boolean deleteForTekst(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_ALL_FREQUENTIES_FOR_TEKST);

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


