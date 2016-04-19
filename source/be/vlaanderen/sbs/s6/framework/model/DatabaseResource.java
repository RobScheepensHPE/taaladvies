package be.vlaanderen.sbs.s6.framework.model;


/**
 * Represents a Database Resource. Maps to a database resource from the application
 * configuration xml file (enclosed in <code><database>...</database></code> tags).
 * @author Gert Vandenbemden
 * @version 1.0 
 */
public class DatabaseResource extends BaseResource
{
    /** Driver used to connect to the database. */
    private java.lang.String dbDriver;
    /** Url used to connect to the database. */
    private java.lang.String dbUrl;
    /** Login used to connect to the database. */
    private java.lang.String dbLogin;
    /** Password used to connect to the database. */
    private java.lang.String dbPassword;
    /** Maximum number of connections to be used to connect to the database. */
    private java.lang.String dbMaxConns;
    /** Initial number of connections to be used to connect to the database. */
    private java.lang.String dbInitConns;
    /** Connection timeout to be used when connecting to the database. */
    private java.lang.String dbTimeout;
    /**Contains the resource tag name from the <code>AppConf.xml<code> file describing an individual database resource.*/
    public static final String RESOURCE_TYPE = "database";
    /**Contains the constant tag name from the <code>AppConf.xml<code> file describing the object type used for an individual database resource.*/
    public static final String RESOURCE_OBJECT_TYPE = "databaseObjectType";

    /**
     * DatabaseResource constructor.
     */
    public DatabaseResource()
    {
        dbDriver = "";
        dbUrl = "";
        dbLogin = "";
        dbPassword = "";
        dbMaxConns = "";
        dbInitConns = "";
        dbTimeout = "";
    }
    /**
     * Gets the database driver.
     * @return java.lang.String
     */
    public java.lang.String getDbDriver()
    {
        return dbDriver;
    }
    /**
     * Gets the initial number of connections for the database.
     * @return java.lang.String
     */
    public java.lang.String getDbInitConns()
    {
        return dbInitConns;
    }
    /**
     * Gets the login used for the database.
     * @return java.lang.String
     */
    public java.lang.String getDbLogin()
    {
        return dbLogin;
    }
    /**
     * Gets the maximum number of connections for the database.
     * @return java.lang.String
     */
    public java.lang.String getDbMaxConns()
    {
        return dbMaxConns;
    }
    /**
     * Gets the password for the database.
     * @return java.lang.String
     */
    public java.lang.String getDbPassword()
    {
        return dbPassword;
    }
    /**
     * Gets the timeout for the database.
     * @return java.lang.String
     */
    public java.lang.String getDbTimeout()
    {
        return dbTimeout;
    }
    /**
     * Gets the url to connect to the database.
     * @return java.lang.String
     */
    public java.lang.String getDbUrl()
    {
        return dbUrl;
    }
    /**
     * Sets the database driver.
     * @param newDbDriver java.lang.String
     */
    public void setDbDriver(java.lang.String newDbDriver)
    {
        dbDriver = newDbDriver;
    }
    /**
     * Sets the initial number of connections for the database.
     * @param newDbInitConns java.lang.String
     */
    public void setDbInitConns(java.lang.String newDbInitConns)
    {
        dbInitConns = newDbInitConns;
    }
    /**
     * Sets the login used for the database.
     * @param newDbLogin java.lang.String
     */
    public void setDbLogin(java.lang.String newDbLogin)
    {
        dbLogin = newDbLogin;
    }
    /**
     * Sets the maximum number of connections for the database.
     * @param newDbMaxConns java.lang.String
     */
    public void setDbMaxConns(java.lang.String newDbMaxConns)
    {
        dbMaxConns = newDbMaxConns;
    }
    /**
     * Sets the password for the database.
     * @param newDbPassword java.lang.String
     */
    public void setDbPassword(java.lang.String newDbPassword)
    {
        dbPassword = newDbPassword;
    }
    /**
     * Sets the timeout for the database.
     * @param newDbTimeout java.lang.String
     */
    public void setDbTimeout(java.lang.String newDbTimeout)
    {
        dbTimeout = newDbTimeout;
    }
    /**
     * Sets the url to connect to the database.
     * @param newDbUrl java.lang.String
     */
    public void setDbUrl(java.lang.String newDbUrl)
    {
        dbUrl = newDbUrl;
    }

}