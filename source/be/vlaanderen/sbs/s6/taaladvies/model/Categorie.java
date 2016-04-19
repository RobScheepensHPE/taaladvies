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

public class Categorie extends ParameterActief implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 7512805342336143638L;

	private int parentId;

	private String parentNummer;
	private String ownNummer;

	private String nummer;
	private Categorie superCategorie;

	/**
	 * Gets the parentId
	 * @return Returns a int
	 */
	public int getParentId() {
		return parentId;
	}
	/**
	 * Sets the parentId
	 * @param parentId The parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}


	/**
	 * Gets the nummer
	 * @return Returns a String
	 */
	public String getNummer() {
		return nummer;
	}
	/**
	 * Sets the nummer
	 * @param nummer The nummer to set
	 */
	public void setNummer(String nummer) {
		this.nummer = be.vlaanderen.sbs.s6.taaladvies.utils.Util.processInputString(nummer);
	}

	/**
	 * Gets the Categorie from the database by it's id
	 * @return Returns a ParameterBase
	 * @param id The id to retrieve
	 */

	/**
	 * Gets the superCategorie
	 * @return Returns a Categorie
	 */
	public Categorie getSuperCategorie() {
		if (superCategorie == null) {
			if (parentId != 0) {
				superCategorie = (Categorie)Categorie.findByPK(parentId);
			}
			else {
				superCategorie = new Categorie();
			}
		}
		return superCategorie;
	}
	/**
	 * Sets the superCategorie
	 * @param superCategorie The superCategorie to set
	 */
	public void setSuperCategorie(Categorie superCategorie) {
		this.superCategorie = superCategorie;
	}

	/**
	 * Gets the Categorie from the database by it's id
	 * @return Returns a ParameterBase
	 * @param id The id to retrieve
	 */

	public static ParameterBase findByPK(int id) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Categorie categorie = new Categorie();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.CATEGORIE_BY_PK);
				pst.setInt(1, id);
				rs = pst.executeQuery();
				if (rs.next()) {

					categorie.setId(id);
					categorie.setOmschrijving(rs.getString("omschrijving"));
					categorie.setActief(rs.getBoolean("actief"));
					categorie.setParentId(rs.getInt("parentId"));
					categorie.setNummer(rs.getString("nummer"));

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

		return categorie;

	}

	/**
	 * Gets all Categorieën from the database
	 * @return Returns a ArrayList
	 */

	public static ArrayList<Categorie> findAll() {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Categorie> allCategorien = new ArrayList<Categorie>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.ALL_CATEGORIEN);
				rs = pst.executeQuery();
				while (rs.next()) {

					Categorie categorie = new Categorie();
					categorie.setId(rs.getInt("id"));
					categorie.setOmschrijving(rs.getString("omschrijving"));
					categorie.setActief(rs.getBoolean("actief"));
					categorie.setParentId(rs.getInt("parentId"));
					categorie.setNummer(rs.getString("nummer"));
					allCategorien.add(categorie);

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

		return allCategorien;

	}

	/**
	 * Gets all "actieve" Categoriën from the database
	 * @return Returns a ArrayList
	 */

	public static ArrayList<Categorie> findAllActief() {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Categorie> allCategorien = new ArrayList<Categorie>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.ALL_CATEGORIEN_ACTIEF);
				rs = pst.executeQuery();
				while (rs.next()) {

					Categorie categorie = new Categorie();
					categorie.setId(rs.getInt("id"));
					categorie.setOmschrijving(rs.getString("omschrijving"));
					categorie.setActief(rs.getBoolean("actief"));
					categorie.setParentId(rs.getInt("parentId"));
					categorie.setNummer(rs.getString("nummer"));
					allCategorien.add(categorie);

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

		return allCategorien;

	}

	/**
	 * Gets all Categoriën from the database by it's parentCategorie
	 * @return Returns a ArrayList
	 * @param parentId The parentId to use
	 */

	public static ArrayList<Categorie> findByParent(int parentId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Categorie> allCategorien = new ArrayList<Categorie>();

		try {
			if (con != null) {
				if (parentId != 0) {
					pst = con.prepareStatement(Queries.CATEGORIEN_BY_PARENT);
					pst.setInt(1, parentId);
				}
				else {
					pst = con.prepareStatement(Queries.CATEGORIEN_BY_PARENT_NULL);
				}
				rs = pst.executeQuery();
				while (rs.next()) {

					Categorie categorie = new Categorie();
					categorie.setId(rs.getInt("id"));
					categorie.setOmschrijving(rs.getString("omschrijving"));
					categorie.setActief(rs.getBoolean("actief"));
					categorie.setParentId(rs.getInt("parentId"));
					categorie.setNummer(rs.getString("nummer"));
					allCategorien.add(categorie);

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

		return allCategorien;

	}

	/**
	 * Gets all "Actieve" Categoriën from the database by it's parentCategorie
	 * @return Returns a ArrayList
	 * @param parentId The parentId to use
	 */

	public static ArrayList<Categorie> findByParentActief(int parentId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Categorie> allCategorien = new ArrayList<Categorie>();

		try {
			if (con != null) {
				if (parentId != 0) {
					pst = con.prepareStatement(Queries.CATEGORIEN_BY_PARENT_ACTIEF);
					pst.setInt(1, parentId);
				}
				else {
					pst = con.prepareStatement(Queries.CATEGORIEN_BY_PARENT_ACTIEF_NULL);
				}
				rs = pst.executeQuery();
				while (rs.next()) {

					Categorie categorie = new Categorie();
					categorie.setId(rs.getInt("id"));
					categorie.setOmschrijving(rs.getString("omschrijving"));
					categorie.setActief(rs.getBoolean("actief"));
					categorie.setParentId(rs.getInt("parentId"));
					categorie.setNummer(rs.getString("nummer"));
					allCategorien.add(categorie);

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

		return allCategorien;

	}

	/**
	 * Gets all Categoriën linked to a taalvraag from the database
	 * @return Returns a ArrayList
	 * @param taalvraagId The taalvraagId to use
	 */

	public static ArrayList<Categorie> findByTaalvraagId(int taalvraagId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Categorie> allCategorien = new ArrayList<Categorie>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.CATEGORIEN_BY_TAALVRAAG);
				pst.setInt(1, taalvraagId);
				rs = pst.executeQuery();
				while (rs.next()) {

					Categorie categorie = new Categorie();
					categorie.setId(rs.getInt("id"));
					categorie.setOmschrijving(rs.getString("omschrijving"));
					categorie.setActief(rs.getBoolean("actief"));
					categorie.setParentId(rs.getInt("parentId"));
					categorie.setNummer(rs.getString("nummer"));
					allCategorien.add(categorie);

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

		return allCategorien;

	}


	/**
	 * Gets all Categoriën linked to a tekst from the database
	 * @return Returns a ArrayList
	 * @param tekstId The tekstId to use
	 */

	public static ArrayList<Categorie> findByTekstId(int tekstId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Categorie> allCategorien = new ArrayList<Categorie>();

		try {
			if (con != null) {
				pst = con.prepareStatement(Queries.CATEGORIEN_BY_TEKST);
				pst.setInt(1, tekstId);
				rs = pst.executeQuery();
				while (rs.next()) {

					Categorie categorie = new Categorie();
					categorie.setId(rs.getInt("id"));
					categorie.setOmschrijving(rs.getString("omschrijving"));
					categorie.setActief(rs.getBoolean("actief"));
					categorie.setParentId(rs.getInt("parentId"));
					categorie.setNummer(rs.getString("nummer"));
					allCategorien.add(categorie);

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

		return allCategorien;

	}


	/**
	 * Inserts a Categorie
	 * @return Return int
	 * @param categorie The Categorie to insert
	 */

	public static int insert(Categorie categorie) {

		int id = 0;
		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;


		char[] nummer_Array = categorie.getNummer().toCharArray();
		StringBuffer nummer_Buffer = new StringBuffer();
		for (int i = 0, j = nummer_Array.length; i < j; i++) {
			char ch = nummer_Array[i];
			if (!String.valueOf(ch).equals(".")) {
				nummer_Buffer.append(String.valueOf(ch));
			}
		}

		try {
			if (con !=null) {
				pst = con.prepareStatement(Queries.INSERT_CATEGORIE);

				pst.setString(1, categorie.getOmschrijving());
				pst.setBoolean(2, categorie.getActief());
				Util.pstSetInt(pst, 3, categorie.getParentId());
				pst.setString(4, categorie.getNummer());
				Util.pstSetInt(pst, 5, Integer.parseInt(nummer_Buffer.toString().trim()));

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
	 * Inserts a Categorie for a Taalvraag
	 * @return Return void
	 * @param taalvraagId The taavraagId to insert
	 * @param categorieId The categorieId to insert
	 */

	public static void insertForTaalvraag(int taalvraagId, int categorieId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try {
			if (con !=null) {
				pst = con.prepareStatement(Queries.INSERT_CATEGORIE_FOR_TAALVRAAG);

				pst.setInt(1, taalvraagId);
				pst.setInt(2, categorieId);

				pst.executeUpdate();

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

	}


	/**
	 * Inserts a Categorie for a tekst
	 * @return Return void
	 * @param TekstId The tekstId to insert
	 * @param categorieId The categorieId to insert
	 */

	public static void insertForTekst(int tekstId, int categorieId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try {
			if (con !=null) {
				pst = con.prepareStatement(Queries.INSERT_CATEGORIE_FOR_TEKST);

				pst.setInt(1, tekstId);
				pst.setInt(2, categorieId);

				pst.executeUpdate();

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

	}

	/**
	 * Checks if a certain categorie-number is available within the current hierarchy
	 * @return Return boolean
	 * @param prefix the whole categorie-number-string
	 */

	public static boolean isNumberAvailable(String prefix) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.CATEGORIEN_BY_HIERARCHIE);

				pst.setString(1, prefix + "%");

				ResultSet rs = pst.executeQuery();

				if (rs.next()){
					return false;
				}else{
					return true;
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
	 * Updates a Categorie
	 * @return Return boolean
	 * @param categorie The Categorie to change
	 */

	public static boolean update(Categorie categorie) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		char[] nummer_Array = categorie.getNummer().toCharArray();
		StringBuffer nummer_Buffer = new StringBuffer();
		for (int i = 0, j = nummer_Array.length; i < j; i++) {
			char ch = nummer_Array[i];
			if (!String.valueOf(ch).equals(".")) {
				nummer_Buffer.append(String.valueOf(ch));
			}
		}

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.UPDATE_CATEGORIE);

				pst.setString(1, categorie.getOmschrijving());
				pst.setBoolean(2, categorie.getActief());
				Util.pstSetInt(pst, 3, categorie.getParentId());
				pst.setString(4, categorie.getNummer());
				Util.pstSetInt(pst, 5, Integer.parseInt(nummer_Buffer.toString().trim()));
				pst.setInt(6, categorie.getId());

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
	 * Updates a Categorie
	 * @return Return boolean
	 * @param categorie The Categorie to change
	 */

	public static boolean updateByParent(String oldPrefix, String newPrefix)
	{
		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		ResultSet rs = null;

		try{
			if (con !=null)
			{
				pst = con.prepareStatement(Queries.CATEGORIEN_BY_HIERARCHIE);

				pst.setString(1, oldPrefix + "%");

				rs = pst.executeQuery();

				while(rs.next())
				{
					String nummer = rs.getString("nummer").trim();

					//int length = nummer.length() - oldPrefix.length();

					String suffix = nummer.substring( oldPrefix.length() , nummer.length() );

					String newNummer = newPrefix + suffix;

					char[] nummer_Array = newNummer.toCharArray();
					StringBuffer nummer_Buffer = new StringBuffer();
					for (int i = 0, j = nummer_Array.length; i < j; i++) {
						char ch = nummer_Array[i];
						if (!String.valueOf(ch).equals(".")) {
							nummer_Buffer.append(String.valueOf(ch));
						}
					}

					pst2 = con.prepareStatement(Queries.UPDATE_CATEGORIE_BY_PARENTNUMMER);
					pst2.setString(1, newPrefix + suffix);
					Util.pstSetInt(pst2, 2, Integer.parseInt(nummer_Buffer.toString().trim()));
					pst2.setInt(3, rs.getInt("id"));
					/*int check = */pst2.executeUpdate();

					System.out.print(nummer + " naar " + newNummer + " : ");

					pst2.close();

				}

				//pst = con.prepareStatement(Queries.UPDATE_CATEGORIE_BY_PARENTNUMMER);

				//int check = pst.executeUpdate();

//				if (check == 1){
//					return true;
//				}else{
					return false;
//				}
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
	 * deletes a Categorie for a Taalvraag
	 * @return Return boolean
	 * @param taalvraagId The taalvraag wherefore the categorie has to be deleted
	 * @param categorieId The categorie which has to be deleted for the taalvraag or the tekst
	 */

  	public static boolean deleteForTaalvraag(int taalvraagId, int categorieId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_CATEGORIE_FOR_TAALVRAAG);

				pst.setInt(1, taalvraagId);
				pst.setInt(2, categorieId);

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
	 * deletes a Categorie for a Tekst
	 * @return Return boolean
	 * @param TekstId The tekst wherefore the categorie has to be deleted
	 * @param categorieId The categorie which has to be deleted for the taalvraag or the tekst
	 */

  	public static boolean deleteForTekst(int tekstId, int categorieId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_CATEGORIE_FOR_TEKST);

				pst.setInt(1, tekstId);
				pst.setInt(2, categorieId);

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
	 * deletes all Categoriën for a Taalvraag
	 * @return Return boolean
	 * @param taalvraagId The taalvraag wherefore the categorie has to be deleted
	 */

  	public static boolean deleteForTaalvraag(int taalvraagId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_ALL_CATEGORIEN_FOR_TAALVRAAG);

				pst.setInt(1, taalvraagId);

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
	 * deletes all Categoriën for a Tekst
	 * @return Return boolean
	 * @param TekstId The tekst wherefore the categorie has to be deleted
	 */

  	public static boolean deleteForTekst(int tekstId) {

		IDbConnection dbconnection = MyDbConnection.getInstance();
    	Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;

		try{
			if (con !=null) {
				pst = con.prepareStatement(Queries.DELETE_ALL_CATEGORIEN_FOR_TEKST);

				pst.setInt(1, tekstId);

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
	 * Gets the parentNummer
	 * @return Returns a String
	 */
	public String getParentNummer()
	{
		if(parentNummer == null && nummer != null)
		{
			divideNummer();
		}

		return parentNummer;
	}

	/**
	 * Sets the parentNummer
	 * @param parentNummer The parentNummer to set
	 */
	public void setParentNummer(String parentNummer)
	{
		this.parentNummer = parentNummer;
	}

	private void divideNummer()
	{
			int index = nummer.lastIndexOf(".");

			if(index > 0)
			{
				parentNummer = nummer.substring(0,index+1);
				ownNummer = nummer.substring(index+1,nummer.length());
			}
			else
			{
				parentNummer = "";
				ownNummer = nummer;
			}
	}

	/**
	 * Gets the ownNummer
	 * @return Returns a String
	 */
	public String getOwnNummer()
	{
		if(ownNummer == null && nummer != null)
		{
			divideNummer();
		}

		return ownNummer;
	}
	/**
	 * Sets the ownNummer
	 * @param ownNummer The ownNummer to set
	 */
	public void setOwnNummer(String ownNummer)
	{
		this.ownNummer = ownNummer;
	}


}

