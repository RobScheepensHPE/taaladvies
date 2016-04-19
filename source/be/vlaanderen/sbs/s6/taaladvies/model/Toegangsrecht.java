package be.vlaanderen.sbs.s6.taaladvies.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import be.vlaanderen.sbs.s6.taaladvies.Queries;
import be.vlaanderen.sbs.s6.taaladvies.db.IDbConnection;
import be.vlaanderen.sbs.s6.taaladvies.db.MyDbConnection;

public class Toegangsrecht extends ParameterBase implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = -5073605400035862524L;
	private String beschrijving;

	/**
	 * Gets the beschrijving
	 * @return Returns a String
	 */
	public String getBeschrijving() {
		return beschrijving;
	}
	/**
	 * Sets the beschrijving
	 * @param beschrijving The beschrijving to set
	 */
	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}


	/**
	 * Gets the Toegangsrecht from the database by it's id
	 * @return Returns a ParameterBase
	 * @param id The id to retrieve
	 */

	public static ParameterBase findByPK(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Toegangsrecht toegangsrecht = new Toegangsrecht();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.TOEGANGSRECHT_BY_PK);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if (rs.next()) {

					toegangsrecht.setId(id);
					toegangsrecht.setOmschrijving(rs.getString("omschrijving"));
					toegangsrecht.setBeschrijving(rs.getString("beschrijving"));
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

		return toegangsrecht;

	}

	/**
	 * Gets all Toegangsrechten from the database
	 * @return Returns a ArrayList
	 */

	public static ArrayList<Toegangsrecht> findAll() {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Toegangsrecht> allToegangsrechten = new ArrayList<Toegangsrecht>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.ALL_TOEGANGSRECHTEN);
				rs = pst.executeQuery();
				while (rs.next()) {

					Toegangsrecht toegangsrecht = new Toegangsrecht();
					toegangsrecht.setId(rs.getInt("id"));
					toegangsrecht.setOmschrijving(rs.getString("omschrijving"));
					toegangsrecht.setBeschrijving(rs.getString("beschrijving"));
					allToegangsrechten.add(toegangsrecht);

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

		return allToegangsrechten;

	}


	/**
	 * Gets all Toegangsrechten for a adviseur from the database
	 * @return Returns a ArrayList
	 */

	public static ArrayList<Toegangsrecht> findByGebruiker(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Toegangsrecht> allToegangsrechten = new ArrayList<Toegangsrecht>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.TOEGANGSRECHTEN_BY_GEBRUIKER);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				while (rs.next()) {

					Toegangsrecht toegangsrecht = new Toegangsrecht();
					toegangsrecht.setId(rs.getInt("id"));
					toegangsrecht.setOmschrijving(rs.getString("omschrijving"));
					toegangsrecht.setBeschrijving(rs.getString("beschrijving"));
					allToegangsrechten.add(toegangsrecht);

				}

			} else {

				System.err.println("Geen connectie !");
			}
		} catch (java.sql.SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			try {
				if (con != null) {
					if (rs != null) rs.close();
					if (pst != null) pst.close();
					dbconnection.releaseConnection(con);
				}

			} catch (java.sql.SQLException sqle) {
				sqle.printStackTrace(System.err);
			}
		}

		return allToegangsrechten;

	}

	/**
	 * Inserts toegangsrechten by GebruikerId
	 * @return Return void
	 * @param gebruiker The Gebruiker to insert toegangsrechten for
	 */

	public static void insertByGebruiker(Gebruiker gebruiker) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try {
			if (con !=null) {
				if (gebruiker.getToegangsrechtenAsIs() != null) {
					for (int i = 0; i < gebruiker.getToegangsrechten().length; i++) {
						pst = con.prepareStatement(Queries.INSERT_TOEGANGSRECHTEN_BY_GEBRUIKER);

						pst.setInt(1, gebruiker.getId());
						pst.setInt(2, gebruiker.getToegangsrechten()[i]);

						pst.executeUpdate();
					}
				}
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

	}

	/**
	 * deletes toegangsrechten by GebruikerId
	 * @return Return boolean
	 * @param gebruiker The Gebruiker to delete toegangsrechten for
	 */

  	public static boolean deleteByGebruiker(Gebruiker gebruiker) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_TOEGANGSRECHTEN_BY_GEBRUIKER);

				pst.setInt(1, gebruiker.getId());

				int check = pst.executeUpdate();

				if (check != 0){
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

