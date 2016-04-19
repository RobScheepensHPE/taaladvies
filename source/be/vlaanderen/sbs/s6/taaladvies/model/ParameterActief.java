package be.vlaanderen.sbs.s6.taaladvies.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import be.vlaanderen.sbs.s6.taaladvies.db.IDbConnection;
import be.vlaanderen.sbs.s6.taaladvies.db.MyDbConnection;
import com.informix.jdbc.IfxStatement;

/**
 * This class represents the BaseClass for each Parameter Entity that contains an "actief" field.  Every Parameter Entity with "actief" field is thus derived from this class<br>
 * Entities that are fully represented by this class are:
 * <ul>
 * <li>Herkomst</li>
 * <li>Status</li>
 * <li>Relevantie</li>
 * <li>Medium</li>
 * <li>Bibliografie</li>
 * <li>Zoekomgeving</li>
 * </ul>
 */

public class ParameterActief extends ParameterBase implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5694990956461019400L;

    public static int MEDIUM_EMAIL_ID = 2;

    private boolean actief = true;

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
     * Gets the "ParameterActief" from the database by it's id
     *
     * @param query The query to use
     * @param id    The id to retrieve
     * @return Returns a ParameterBase
     */
    public static ParameterBase findByPK(String query, int id) {
        IDbConnection dbconnection = MyDbConnection.getInstance();
        Connection con = dbconnection.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        ParameterActief parameterActief = new ParameterActief();

        try {
            if (con != null) {
                pst = con.prepareStatement(query);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    parameterActief.setId(id);
                    parameterActief.setOmschrijving(rs.getString("omschrijving"));
                    parameterActief.setActief(rs.getBoolean("actief"));
                }
            } else {
                parameterActief.setId(id);
                parameterActief.setOmschrijving("ParameterActief " + id);
                parameterActief.setActief(true);
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
        return parameterActief;
    }


    /**
     * Gets the maximum id from the database
     *
     * @param query The query to use
     * @return Returns a int
     */
    public static int findMax(String query) {
        IDbConnection dbconnection = MyDbConnection.getInstance();
        Connection con = dbconnection.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        int max = 0;

        try {
            if (con != null) {
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                if (rs.next()) {
                    max = rs.getInt("max");
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
        return max;
    }

    /**
     * Gets all "ParameterActieven" from the database
     *
     * @param query The query to use
     * @return Returns a ArrayList
     */

    public static ArrayList<ParameterActief> findAll(String query) {
        IDbConnection dbconnection = MyDbConnection.getInstance();
        Connection con = dbconnection.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<ParameterActief> allParameterActieven = new ArrayList<ParameterActief>();

        try {
            if (con != null) {
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                while (rs.next()) {

                    ParameterActief parameterActief = new ParameterActief();
                    parameterActief.setId(rs.getInt("id"));
                    parameterActief.setOmschrijving(rs.getString("omschrijving"));
                    parameterActief.setActief(rs.getBoolean("actief"));
                    allParameterActieven.add(parameterActief);

                }
            } else {
                for (int i = 1; i < 6; i++) {
                    ParameterActief parameterActief = new ParameterActief();
                    parameterActief.setId(i);
                    parameterActief.setOmschrijving("ParameterActief " + i);
                    parameterActief.setActief(true);
                    allParameterActieven.add(parameterActief);
                }
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
        return allParameterActieven;
    }

    /**
     * Gets all "actieve" "ParameterActieven" from the database
     *
     * @param query The query to use
     * @return Returns a ArrayList
     */

    public static ArrayList<ParameterActief> findAllActief(String query) {
        IDbConnection dbconnection = MyDbConnection.getInstance();
        Connection con = dbconnection.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<ParameterActief> allParameterActieven = new ArrayList<ParameterActief>();
        try {
            if (con != null) {
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                while (rs.next()) {

                    ParameterActief parameterActief = new ParameterActief();
                    parameterActief.setId(rs.getInt("id"));
                    parameterActief.setOmschrijving(rs.getString("omschrijving"));
                    parameterActief.setActief(rs.getBoolean("actief"));
                    allParameterActieven.add(parameterActief);
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
        return allParameterActieven;
    }

    /**
     * Inserts a ParameterActief
     *
     * @param query           The query to use
     * @param parameterActief The ParameterActief to insert
     * @return Return int
     */

    public static int insert(String query, ParameterActief parameterActief) {
        int id = 0;
        IDbConnection dbconnection = MyDbConnection.getInstance();
        Connection con = dbconnection.getConnection();
        PreparedStatement pst = null;
        try {
            if (con != null) {
                pst = con.prepareStatement(query);
                pst.setString(1, parameterActief.getOmschrijving());
                pst.setBoolean(2, parameterActief.getActief());
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
     * Inserts a ParameterActief
     *
     * @param query           The query to use
     * @param parameterActief The ParameterActief to insert
     * @return Return int
     */

    public static int insertWithId(String query, ParameterActief parameterActief) {

        int id = 0;
        IDbConnection dbconnection = MyDbConnection.getInstance();
        Connection con = dbconnection.getConnection();
        PreparedStatement pst = null;
        try {
            if (con != null) {
                pst = con.prepareStatement(query);
                pst.setInt(1, parameterActief.getId());
                pst.setString(2, parameterActief.getOmschrijving());
                pst.setBoolean(3, parameterActief.getActief());
                pst.executeUpdate();
            } else {
                System.err.println("Geen connectie!");
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
     * Updates a ParameterActief
     *
     * @param query           The query to use
     * @param parameterActief The ParameterActief to change
     * @return Return boolean
     */

    public static boolean update(String query, ParameterActief parameterActief) {
        IDbConnection dbconnection = MyDbConnection.getInstance();
        Connection con = dbconnection.getConnection();
        PreparedStatement pst = null;
        try {
            if (con != null) {
                pst = con.prepareStatement(query);
                pst.setString(1, parameterActief.getOmschrijving());
                pst.setBoolean(2, parameterActief.getActief());
                pst.setInt(3, parameterActief.getId());
                int check = pst.executeUpdate();
                return check == 1;
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
     * deletes a ParameterActief
     *
     * @param query           The query to use
     * @param parameterActief The ParameterActief to delete
     * @return Return boolean
     */
    public static boolean delete(String query, ParameterActief parameterActief) {
        IDbConnection dbconnection = MyDbConnection.getInstance();
        Connection con = dbconnection.getConnection();
        PreparedStatement pst = null;
        try {
            if (con != null) {
                pst = con.prepareStatement(query);
                pst.setInt(1, parameterActief.getId());
                int check = pst.executeUpdate();
                return check == 1;
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