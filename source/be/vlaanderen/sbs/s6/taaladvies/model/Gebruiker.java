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

public class Gebruiker implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -3852285611822650695L;
    private int id;
    private String login;
    private String naam;
    private String voornaam;
    private String email;
    private String geslacht;
    private int domeinId;
    private boolean actief = true;
    private ParameterBase domein;

    private int[] toegangsrechten;

    /**
     * Gets the id
     *
     * @return Returns a int
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id
     *
     * @param id The id to set
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the login
     *
     * @return Returns a String
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login
     *
     * @param login The login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }


    /**
     * Gets the naam
     *
     * @return Returns a String
     */
    public String getNaam() {
        return naam;
    }

    /**
     * Sets the naam
     *
     * @param naam The naam to set
     */
    public void setNaam(String naam) {
        this.naam = naam;
    }

    /**
     * Gets the voornaam
     *
     * @return Returns a String
     */
    public String getVoornaam() {
        return voornaam;
    }

    /**
     * Sets the voornaam
     *
     * @param voornaam The voornaam to set
     */
    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    /**
     * Gets the email
     *
     * @return Returns a String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email
     *
     * @param email The email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the geslacht
     *
     * @return Returns a String
     */
    public String getGeslacht() {
        return geslacht;
    }

    /**
     * Sets the geslacht
     *
     * @param geslacht The geslacht to set
     */
    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }


    /**
     * Gets the domeinId
     *
     * @return Returns a int
     */
    public int getDomeinId() {
        return domeinId;
    }

    /**
     * Sets the domeinId
     *
     * @param domeinId The domeinId to set
     */
    public void setDomeinId(int domeinId) {
        this.domeinId = domeinId;
    }


    /**
     * Gets the actief
     *
     * @return Returns a boolean
     */
    public boolean getActief() {
        return actief;
    }

    /**
     * Sets the actief
     *
     * @param actief The actief to set
     */
    public void setActief(boolean actief) {
        this.actief = actief;
    }

    /**
     * Gets the superCategorie
     *
     * @return Returns a Categorie
     */
    public ParameterBase getDomein() {
        if (domein == null) {
            if (domeinId != 0) {
                domein = ParameterBase.findByPK(Queries.DOMEIN_BY_PK, domeinId);
            } else {
                domein = new ParameterBase();
            }
        }
        return domein;
    }

    /**
     * Sets the domein
     *
     * @param domein The domein to set
     */
    public void setDomein(ParameterBase domein) {
        this.domein = domein;
    }

    /**
     * Gets the toegangsrechten
     *
     * @return Returns a int[]
     */
    public int[] getToegangsrechten() {

        if (toegangsrechten == null) {
            java.util.ArrayList<Toegangsrecht> toegangsrechtenByGebruikerList = Toegangsrecht.findByGebruiker(id);
            int[] toegangsrechtenByGebruiker = new int[toegangsrechtenByGebruikerList.size()];
            for (int i = 0; i < toegangsrechtenByGebruikerList.size(); i++) {
                toegangsrechtenByGebruiker[i] = toegangsrechtenByGebruikerList.get(i).getId();
            }
            return toegangsrechtenByGebruiker;
        }
        return toegangsrechten;
    }

    public int[] getToegangsrechtenAsIs() {
        return toegangsrechten;
    }

    /**
     * Sets the toegangsrechten
     *
     * @param toegangsrechten The toegangsrechten to set
     */
    public void setToegangsrechten(int[] toegangsrechten) {
        this.toegangsrechten = toegangsrechten;
    }

    /**
     * Gets the Taaladviseur from the database by it's own id
     *
     * @param id The id from the Taaladviseur to retrieve
     * @return Returns a Taaladviseur
     */

    public static Gebruiker findByPK(int id) {

        IDbConnection dbconnection = MyDbConnection.getInstance();
        Connection con = dbconnection.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Gebruiker taaladviseur = new Gebruiker();

        try {
            if (con != null) {
                pst = con.prepareStatement(Queries.GEBRUIKER_BY_PK);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                if (rs.next()) {

                    taaladviseur.setId(id);
                    taaladviseur.setLogin(rs.getString("login"));
                    taaladviseur.setNaam(rs.getString("naam"));
                    taaladviseur.setVoornaam(rs.getString("voornaam"));
                    taaladviseur.setEmail(rs.getString("email"));
                    if (rs.getBoolean("geslacht")) {
                        taaladviseur.setGeslacht("m");
                    } else {
                        taaladviseur.setGeslacht("f");
                    }
                    taaladviseur.setDomeinId(rs.getInt("domeinId"));
                    taaladviseur.setActief(rs.getBoolean("actief"));

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

        return taaladviseur;

    }

    /**
     * Gets the Taaladviseur from the database by it's login
     *
     * @param login The login from the Taaladviseur to retrieve
     * @return Returns a Taaladviseur
     */

    public static Gebruiker findByLogin(String login) {

        IDbConnection dbconnection = MyDbConnection.getInstance();
        Connection con = dbconnection.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Gebruiker taaladviseur = new Gebruiker();

        try {
            if (con != null) {
                pst = con.prepareStatement(Queries.GEBRUIKER_BY_LOGIN);
                pst.setString(1, login.trim());
                rs = pst.executeQuery();
                if (rs.next()) {

                    taaladviseur.setId(rs.getInt("id"));
                    taaladviseur.setLogin(rs.getString("login"));
                    taaladviseur.setNaam(rs.getString("naam"));
                    taaladviseur.setVoornaam(rs.getString("voornaam"));
                    taaladviseur.setEmail(rs.getString("email"));
                    if (rs.getBoolean("geslacht")) {
                        taaladviseur.setGeslacht("m");
                    } else {
                        taaladviseur.setGeslacht("f");
                    }
                    taaladviseur.setDomeinId(rs.getInt("domeinId"));
                    taaladviseur.setActief(rs.getBoolean("actief"));

                }

            } else {

                System.err.println("Geen connectie !");
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                if (con != null) {
                    if (rs != null) {
                	rs.close();
                    }
                    if (pst != null) {
                        pst.close();
                    }
                    dbconnection.releaseConnection(con);
                }

            } catch (java.sql.SQLException sqle) {
                sqle.printStackTrace(System.err);
            }
        }

        return taaladviseur;

    }


    /**
     * Gets all Taaladviseurs from the database
     *
     * @return Returns a ArrayList
     */

    public static ArrayList<Gebruiker> findAll() {

        IDbConnection dbconnection = MyDbConnection.getInstance();
        Connection con = dbconnection.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Gebruiker> allTaaladviseurs = new ArrayList<Gebruiker>();

        try {
            if (con != null) {
                pst = con.prepareStatement(Queries.ALL_GEBRUIKERS);
                rs = pst.executeQuery();
                while (rs.next()) {

                    Gebruiker taaladviseur = new Gebruiker();
                    taaladviseur.setId(rs.getInt("id"));
                    taaladviseur.setLogin(rs.getString("login"));
                    taaladviseur.setNaam(rs.getString("naam"));
                    taaladviseur.setVoornaam(rs.getString("voornaam"));
                    taaladviseur.setEmail(rs.getString("email"));
                    if (rs.getBoolean("geslacht")) {
                        taaladviseur.setGeslacht("m");
                    } else {
                        taaladviseur.setGeslacht("f");
                    }
                    taaladviseur.setDomeinId(rs.getInt("domeinId"));
                    taaladviseur.setActief(rs.getBoolean("actief"));
                    allTaaladviseurs.add(taaladviseur);

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

        return allTaaladviseurs;

    }

    /**
     * Inserts a Taaladviseur
     *
     * @param taaladviseur The Taaladviseur to insert
     * @return Return int
     */

    public static int insert(Gebruiker gebruiker) {

        int id = 0;
        IDbConnection dbconnection = MyDbConnection.getInstance();
        Connection con = dbconnection.getConnection();
        PreparedStatement pst = null;

        try {
            if (con != null) {
                pst = con.prepareStatement(Queries.INSERT_GEBRUIKER);

                pst.setString(1, gebruiker.getLogin());
                pst.setString(2, gebruiker.getNaam());
                pst.setString(3, gebruiker.getVoornaam());
                pst.setString(4, gebruiker.getEmail());
                if (gebruiker.getGeslacht().equals("m")) {
                    pst.setBoolean(5, true);
                } else {
                    pst.setBoolean(5, false);
                }
                Util.pstSetInt(pst, 6, gebruiker.getDomeinId());
                pst.setBoolean(7, gebruiker.getActief());

                pst.executeUpdate();
                id = ((IfxStatement) pst).getSerial();

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
     * Updates a Taaladviseur
     *
     * @param taaladviseur The Taaladviseur to change
     * @return Return boolean
     */

    public static boolean update(Gebruiker gebruiker) {

        IDbConnection dbconnection = MyDbConnection.getInstance();
        Connection con = dbconnection.getConnection();
        PreparedStatement pst = null;

        try {
            if (con != null) {
                pst = con.prepareStatement(Queries.UPDATE_GEBRUIKER);
                pst.setString(1, gebruiker.getLogin());
                pst.setString(2, gebruiker.getNaam());
                pst.setString(3, gebruiker.getVoornaam());
                pst.setString(4, gebruiker.getEmail());

                if (gebruiker.getGeslacht().trim().equals("m")) {
                    pst.setBoolean(5, true);
                } else {
                    pst.setBoolean(5, false);
                }
                Util.pstSetInt(pst, 6, gebruiker.getDomeinId());
                pst.setBoolean(7, gebruiker.getActief());
                pst.setInt(8, gebruiker.getId());

                int check = pst.executeUpdate();

                if (check == 1) {
                    return true;
                } else {
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

