package be.vlaanderen.sbs.s6.taaladvies.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import be.vlaanderen.sbs.s6.framework.logging.AppLogger;
import be.vlaanderen.sbs.s6.taaladvies.appconf.AppConf;
import com.informix.jdbc.Version;


/**
 *  <p>
 *
 *  Titel: MyDbConnection</p> <p>
 *
 *  Omschrijving: Connections to the database via a datasource.</p> <p>
 */
public final class MyDbConnection
    implements IDbConnection {

    private static final String TAALADVIESDB = "taaladviesDB";
    public static final String DATASOURCECONNECTION = "datasource";
    public static final String JDBCCONNECTION = "jdbc";
    private static final String JDBC_DATASOURCENAME = "java:comp/env/jdbc/Taaladvies";
    private static MyDbConnection instance = null;
    private static String connectionType = null;
    private static DataSource datasource = null;


    /**  Private constructor for singleton pattern.  */
    private MyDbConnection() {
        if(MyDbConnection.connectionType.equals(MyDbConnection.DATASOURCECONNECTION)){
            lookupDatasource();
        } else {
            loadJDBCDriverClass();
        }
        // print jdbc informix version
        Version.main(null);
    }


    /**
     *
     */
    private void loadJDBCDriverClass() {
        AppLogger.getInstance().debug("creating MyDbConnection as simple jdbc connection");
        try
        {
        	Class.forName(AppConf.getResource(TAALADVIESDB + ".driver"));
        }
        catch ( ClassNotFoundException ex)
        {
           AppLogger.getInstance().fatal("Fout tijdens het laden van de gewone jdbc driver '"
        		+ ex.getMessage());
        }
    }


    /**
     *
     */
    private void lookupDatasource() {
        AppLogger.getInstance().debug("creating MyDbConnection as Datasource, looking up datasource");
        try {
            InitialContext context = new InitialContext();
            datasource = (DataSource) context.lookup(JDBC_DATASOURCENAME);
        } catch (NamingException e1) {
            AppLogger.getInstance().fatal("Fout tijdens het opzoeken van de DataSource '"
                    						+ JDBC_DATASOURCENAME+ "': "
                    						+ e1.getMessage());
        }
    }


    /**
     *  Gets a connection from the pool.
     *
     *@return                    MyDbConnection the connection we can use
     *@throws   NamingException  ApplicationException
     *@throws   SQLException     ApplicationException
     */
    public Connection getConnection(){
        Connection con = null;

        if(MyDbConnection.connectionType.equals(MyDbConnection.DATASOURCECONNECTION)){
		    java.util.Date d = new java.util.Date();
			con = getDatasourceConnection();
			java.util.Date d2 = new java.util.Date();
			AppLogger.getInstance().debug("getting datasource connection duurde " + (d2.getTime() - d.getTime()) + "ms");

		} else {
		    java.util.Date d = new java.util.Date();
		    con = getDriverManagerConnection();
            java.util.Date d2 = new java.util.Date();
			AppLogger.getInstance().debug("getting simpele jdbc connection duurde " + (d2.getTime() - d.getTime()) + "ms");
		}
        return con;
    }


    /**
     * @return a SQL connection
     */
    private Connection getDriverManagerConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(AppConf.getResource(TAALADVIESDB + ".url")
                    							,AppConf.getResource(TAALADVIESDB + ".user")
                    							,AppConf.getResource(TAALADVIESDB + ".password")
                    							);
        } catch (SQLException e) {
            AppLogger.getInstance().fatal("Fout tijdens het opvragen van een simpele jdbc connectie: "
        			+ e.getMessage());
        }
        return con;
    }


    /**
     * @return a SQL connection
     */
    private Connection getDatasourceConnection() {
        Connection con = null;
        if (datasource==null) {
            lookupDatasource();
        }
        try {
            con = datasource.getConnection();
        } catch (SQLException e) {
            AppLogger.getInstance().fatal("Fout tijdens het opvragen van een datasource db connectie: "
        			+ e.getMessage());
        }
        return con;
    }


    /**
     *  Release the connection to the pool.
     *
     *@param  con             con
     *@throws   SQLException  ApplicationException
     */
    public void releaseConnection(Connection con) throws SQLException {

	if (con != null) {

	    if (!con.isClosed()) {

		con.close();
	    }
	}

    }

    /**
     *  Tests the connection.
     *
     *@return                   true if successfull, otherwise false.
     *@throws  NamingException NamingException
     *@throws  SQLException SQLException
     */
    public boolean testConnection() throws NamingException, SQLException {

	boolean result = false;

	IDbConnection dbConnection = MyDbConnection.getInstance();
	Connection con = dbConnection.getConnection();
	String catalog = con.getCatalog();

	//LogHandler.printLogMessage(LogHandler.MODULE_COMMON, LogHandler.MSG_INFO,MyDbConnection.class +".testConnection(), catalog= " + catalog);

	dbConnection.releaseConnection(con);

	if (catalog != null) {
	    result = true;
	}

	return result;
    }


    /**
     *  Gets the instance of this singleton object.
     *
     *@return    IDbConnection
     */
    public static IDbConnection getInstance() {

	if (instance == null) {

	    instance = new MyDbConnection();
	}

	return instance;
    }

    public static void setConnectionType(String connectionType){
        MyDbConnection.connectionType = connectionType;
    }

}
