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

public class Tekstblok implements Serializable {


	/**
     * 
     */
    private static final long serialVersionUID = -3347581308370088763L;
	private int id;
	private String titel;
	private String titelHTML;
	private String tekstblok;
	private String tekstblokHTML;

	private int volgnummer;
	private int tekstId;

	private Tekst tekst;
	private boolean check_Titel = true;
	private boolean check_Tekstblok = true;

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
	 * Gets the tekstblok
	 * @return Returns a String
	 */
	public String getTekstblok() {
		return tekstblok;
	}
	/**
	 * Sets the tekstblok
	 * @param tekstblok The tekstblok to set
	 */
	public void setTekstblok(String tekstblok) {
		this.tekstblok = tekstblok;
	}


	/**
	 * Gets the tekstblokHTML
	 * @return Returns a String
	 */
	public String getTekstblokHTML() {
		return tekstblokHTML;
	}
	/**
	 * Sets the tekstblokHTML
	 * @param tekstblokHTML The tekstblokHTML to set
	 */
	public void setTekstblokHTML(String tekstblokHTML) {
		this.tekstblokHTML = tekstblokHTML;
	}


	/**
	 * Gets the volgnummer
	 * @return Returns a int
	 */
	public int getVolgnummer() {
		return volgnummer;
	}
	/**
	 * Sets the volgnummer
	 * @param volgnummer The volgnummer to set
	 */
	public void setVolgnummer(int volgnummer) {
		this.volgnummer = volgnummer;
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
	 * Gets the tekst
	 * @return Returns a Tekst
	 */
	public Tekst getTekst() {
		if (tekst == null) {
			if (tekstId != 0) {
				tekst = Tekst.findByPK(tekstId);
			}
			else {
				tekst = new Tekst();
			}
		}
		return tekst;
	}
	/**
	 * Sets the tekst
	 * @param tekst The tekst to set
	 */
	public void setTekst(Tekst tekst) {
		this.tekst = tekst;
	}


	/**
	 * Gets the Tekst from the database by it's id
	 * @return Returns a Tekst
	 * @param id The id to retrieve
	 */
	public static Tekstblok findByPK(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Tekstblok tekstblok = new Tekstblok();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.TEKSTBLOK_BY_PK);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if (rs.next()) {

					tekstblok.setId(id);
					tekstblok.setTitel(rs.getString("titel"));
					tekstblok.setTitelHTML(rs.getString("titelHTML"));
					tekstblok.setTekstblok(rs.getString("tekstblok"));
					tekstblok.setTekstblokHTML(rs.getString("tekstblokHTML"));
					tekstblok.setVolgnummer(rs.getInt("volgnummer"));
					tekstblok.setTekstId(rs.getInt("tekstId"));
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
			                    if (pst != null) {
			                        pst.close();
			                    }
					dbconnection.releaseConnection(con);
				}

			} catch (java.sql.SQLException sqle) {
				sqle.printStackTrace(System.err);
			}
		}

		return tekstblok;

	}


	/**
	 * Gets all Tekstblokken by Tekst from the database
	 * @return Returns a ArrayList
	 * @param tekstId The id from the tekst we want to get the tekstblokken for
	 */

	public static ArrayList<Tekstblok> findByParent(int tekstId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Tekstblok> allTekstblokken = new ArrayList<Tekstblok>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.TEKSTBLOKKEN_BY_PARENT);
				pst.setInt(1, tekstId);
				rs = pst.executeQuery();
				while (rs.next()) {

					Tekstblok tekstblok = new Tekstblok();
					tekstblok.setId(rs.getInt("id"));
					tekstblok.setTitel(rs.getString("titel"));
					tekstblok.setTitelHTML(rs.getString("titelHTML"));
					tekstblok.setTekstblok(rs.getString("tekstblok"));
					tekstblok.setTekstblokHTML(rs.getString("tekstblokHTML"));
					tekstblok.setVolgnummer(rs.getInt("volgnummer"));
					tekstblok.setTekstId(tekstId);
					allTekstblokken.add(tekstblok);
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
			                    if (pst != null) {
			                        pst.close();
			                    }
					dbconnection.releaseConnection(con);
				}

			} catch (java.sql.SQLException sqle) {
				sqle.printStackTrace(System.err);
			}
		}

		return allTekstblokken;
	}


	/**
	 * Inserts a Tekstblok
	 * @return Return int
	 * @param tekstblok The Tekstblok to insert
	 */

	public static int insert(Tekstblok tekstblok) {

		int id = 0;
		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try {
			if (con !=null) {
				pst = con.prepareStatement(Queries.INSERT_TEKSTBLOK);

				if (tekstblok.getTitel() == null) {
					tekstblok.setTitel("");
				}

				Util.pstSetClob(pst, 1, tekstblok.getTitel());
				Util.pstSetClob(pst, 2, tekstblok.getTitelHTML());
				Util.pstSetClob(pst, 3, tekstblok.getTekstblok());
				Util.pstSetClob(pst, 4, tekstblok.getTekstblokHTML());

				pst.setInt(5, tekstblok.getVolgnummer());
				pst.setInt(6, tekstblok.getTekstId());

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
	 * Updates a Tekstblok
	 * @return Return boolean
	 * @param tekstblok The Tekstblok to change
	 */

	public static boolean update(Tekstblok tekstblok) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.UPDATE_TEKSTBLOK);

				if (tekstblok.getTitel() == null) {
					tekstblok.setTitel("");
				}
				//pst.setString(1, tekstblok.getTitel());
				Util.pstSetClob(pst, 1, tekstblok.getTitel());
				Util.pstSetClob(pst, 2, tekstblok.getTitelHTML());
				Util.pstSetClob(pst, 3, tekstblok.getTekstblok());
				Util.pstSetClob(pst, 4, tekstblok.getTekstblokHTML());

				pst.setInt(5, tekstblok.getVolgnummer());
				pst.setInt(6, tekstblok.getTekstId());
				pst.setInt(7, tekstblok.getId());

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
	 * Updates a Tekstblok to change the "volgnummer"
	 * @return Return boolean
	 * @param tekstblok The Tekstblok to change
	 */

	public static boolean updateVolgnummer(Tekstblok tekstblok) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.UPDATE_TEKSTBLOK_VOLGNUMMER);

				pst.setInt(1, tekstblok.getVolgnummer());
				pst.setInt(2, tekstblok.getId());

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
	 * Gets the maximum number of al the volgnummers from the tekstblokken linked to a tekst
	 * @return Returns a int
	 * @param tekstId The tekstId to retrieve the maximum volgnummer of its tekstblokken from
	 */
	public static int findMaxVolgnummer(int tekstId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.TEKSTBLOK_MAX_VOLGNUMMER);
				pst.setInt(1, tekstId);
				rs = pst.executeQuery();
				if (rs.next()) {
					return rs.getInt("maximum");
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
			                    if (pst != null) {
			                        pst.close();
			                    }
					dbconnection.releaseConnection(con);
				}


			} catch (java.sql.SQLException sqle) {
				sqle.printStackTrace(System.err);
			}
		}
		return 1;
	}

	/**
	 * Deletes a Tekstblok
	 * @return Return boolean
	 * @param id The id to delete
	 */

	public static boolean delete(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_TEKSTBLOK);

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
	 * Gets the check_Titel
	 * @return Returns a boolean
	 */
	public boolean getCheck_Titel() {
		return check_Titel;
	}
	/**
	 * Sets the check_Titel
	 * @param check_Titel The check_Titel to set
	 */
	public void setCheck_Titel(boolean check_Titel) {
		this.check_Titel = check_Titel;
	}


	/**
	 * Gets the check_Tekstblok
	 * @return Returns a boolean
	 */
	public boolean getCheck_Tekstblok() {
		return check_Tekstblok;
	}
	/**
	 * Sets the check_Tekstblok
	 * @param check_Tekstblok The check_Tekstblok to set
	 */
	public void setCheck_Tekstblok(boolean check_Tekstblok) {
		this.check_Tekstblok = check_Tekstblok;
	}


}

