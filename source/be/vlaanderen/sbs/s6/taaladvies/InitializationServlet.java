package be.vlaanderen.sbs.s6.taaladvies;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServlet;
import be.vlaanderen.sbs.s6.framework.logging.AppLogger;
import be.vlaanderen.sbs.s6.taaladvies.appconf.AppConf;
import be.vlaanderen.sbs.s6.taaladvies.db.MyDbConnection;

/**
 * Servlet used for initialization of the miscellaneous application components.
 * @author Gert Vandenbemden
 * @version 1.0
 */
public class InitializationServlet extends HttpServlet
{
    /**
     *
     */
    private static final long serialVersionUID = 2776625224318014994L;

    /**
         * Initializes the entire application.
         */
    public void init()
    {
	// --- Initializes the Log4j logging functionality ---
	AppLogger al = AppLogger.getInstance();
	if (al == null)
	    System.err.println(new StringBuffer("::FATAL ERROR - ").append(this.getClass().getName()).append(
		" - Could not initialize Applogger. All logging disabled !!!").toString());
	// --- Creates a new configuration object which is used throughout ---
	// --- the entire application ---
	try
	{
	    if (al != null)
	    {
		al.info("Application configured successfully.\n");
	    }
	    AppConf.init();
	} catch (Exception e)
	{
	    if (al != null)
	    {
		al.fatal("Unable to initialize AppConf", e);
	    }
	}
	// MyDbConnection.setConnectionType(MyDbConnection.DATASOURCECONNECTION);
	String tomcatDebug = AppConf.getConstant("TOMCATDEBUG");
	System.err.println("tomcat:" + tomcatDebug);
	if (tomcatDebug != null && (tomcatDebug.equalsIgnoreCase("true")))
	{
	    MyDbConnection.setConnectionType(MyDbConnection.JDBCCONNECTION);
	} else
	{
	    MyDbConnection.setConnectionType(MyDbConnection.DATASOURCECONNECTION);
	}
	/*
         * try { ConManagerFactory cmf = new ConManagerFactory(); cmf .setConConfig(new
         * be.vlaanderen.sbs.s6.taaladvies.db.conmanager.AppConfConConfig( "taaladviesDB")); cmf.getConManager();
         * Version.main(null); } catch (SQLException ex) { }
         */
	// set eopro config files in context attributes
	this.getServletContext().setAttribute("eoproconfig", readEoproConfigFile("/conf/config-taaladvies.xml"));
	this.getServletContext().setAttribute("eoprouiconfig", readEoproConfigFile("/conf/toolbar-taaladvies.xml"));
	this.getServletContext().setAttribute("eoprouiconfig2", readEoproConfigFile("/conf/toolbar-taaladvies2.xml"));
	this.getServletContext().setAttribute("eoprouiconfig3", readEoproConfigFile("/conf/toolbar-taaladvies3.xml"));
	this.getServletContext().setAttribute("eoprouiconfig4", readEoproConfigFile("/conf/toolbar-taaladvies4.xml"));
	this.getServletContext().setAttribute("eoprouiconfig5", readEoproConfigFile("/conf/toolbar-taaladvies5.xml"));
	this.getServletContext().setAttribute("eoprouiconfig6", readEoproConfigFile("/conf/toolbar-taaladvies6.xml"));
	this.getServletContext().setAttribute("eoprouiconfig7", readEoproConfigFile("/conf/toolbar-taaladvies7.xml"));
    }

    private String readEoproConfigFile(String configFilename)
    {
	String config = "";
	try
	{
	    InputStream is = getClass().getResourceAsStream(configFilename);
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    String str;
	    while ((str = reader.readLine()) != null)
	    {
		config += str;
	    }
	    reader.close();
	} catch (FileNotFoundException e)
	{
	    e.printStackTrace();
	} catch (IOException e)
	{
	    e.printStackTrace();
	}
	return config;
    }
}
