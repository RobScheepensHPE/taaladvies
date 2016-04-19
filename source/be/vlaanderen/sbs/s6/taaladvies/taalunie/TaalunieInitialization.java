/*
 * Created on Dec 6, 2004
 */
package be.vlaanderen.sbs.s6.taaladvies.taalunie;

import java.io.IOException;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.jdom.JDOMException;
import be.vlaanderen.sbs.s6.framework.logging.AppLogger;
import be.vlaanderen.sbs.s6.taaladvies.appconf.AppConf;
import be.vlaanderen.sbs.s6.taaladvies.appconf.AppConfException;
import be.vlaanderen.sbs.s6.taaladvies.db.MyDbConnection;

/**
 * @author nzvwbg
 */
public class TaalunieInitialization
{
    /**
         * initialiseer de appConf class nodig voor de resources , constants
         */
    public void init()
    {
	// --- Initializes the Log4j logging functionality ---
	AppLogger al = AppLogger.getInstance();
	if (al == null)
	{
	    System.err.println(new StringBuffer("::FATAL ERROR - ").append(this.getClass().getName()).append(
		" - Could not initialize Applogger. All logging disabled !!!").toString());
	} else
	{
	    // --- Creates a new configuration object which is used throughout ---
	    // --- the entire application ---
	    al.info("Application configured successfully.\n");
	}
	try
	{
	    AppConf.init();
	} catch (AppConfException e)
	{
	    if (al != null)
	    {
		al.fatal("Unable to initialize AppConf", e);
	    }
	} catch (IOException e)
	{
	    if (al != null)
	    {
		al.fatal("Unable to initialize AppConf", e);
	    }
	} catch (JDOMException e)
	{
	    if (al != null)
	    {
		al.fatal("Unable to initialize AppConf", e);
	    }
	}
	MyDbConnection.setConnectionType(MyDbConnection.JDBCCONNECTION);
	// try
	// {
	// ConManagerFactory.setConConfig(
	// new be.vlaanderen.sbs.s6.taaladvies.db.conmanager.AppConfConConfig
	// ("taaladviesDB")
	// );
	// ConManagerFactory.getConManager();
	// Version.main(null);
	// }
	// catch (SQLException ex)
	// {
	// al.error(ex.getMessage(), ex);
	// }
    }

    /**
         * Creëert een nieuwe HTTPClient op basis van de settings uit appconf.xml
         * @return HTTPCient een httpclient
         */
    public static HttpClient createHttpClient()
    {
	// Create an instance of HttpClient.
	HttpClient client = new HttpClient();
	// speed up authentication PLUS is necessary for https connections (bug in http-client)
	client.getState().setAuthenticationPreemptive(true);
	AppLogger.getInstance().debug("proxy host:" + AppConf.getConstant("PROXY_HOST"));
	AppLogger.getInstance().debug("proxy port:" + AppConf.getConstant("PROXY_PORT"));
	AppLogger.getInstance().debug("proxy gebruikersnaam:" + AppConf.getConstant("PROXY_USER"));
	AppLogger.getInstance().debug("proxy paswoord:" + AppConf.getConstant("PROXY_PASSWORD"));
	AppLogger.getInstance().debug("gebruikersnaam:" + AppConf.getConstant("HTTP_AUTH_USER"));
	AppLogger.getInstance().debug("paswoord:" + AppConf.getConstant("HTTP_AUTH_PASSWORD"));
	// set proxy-settings if not empty
	if (AppConf.getConstant("PROXY_HOST") != null && AppConf.getConstant("PROXY_HOST").trim().length() > 0)
	{
	    client.getHostConfiguration().setProxy(AppConf.getConstant("PROXY_HOST"),
		new Integer(AppConf.getConstant("PROXY_PORT")).intValue());
	    // check if proxy authenticatie is used
	    if (AppConf.getConstant("PROXY_USER") != null)
	    {
		Credentials creds =
		    new UsernamePasswordCredentials(AppConf.getConstant("PROXY_USER"), AppConf.getConstant("PROXY_PASSWORD"));
		client.getState().setProxyCredentials(null, null, creds);
	    }
	}
	if (AppConf.getConstant("HTTP_AUTH_USER") != null && AppConf.getConstant("HTTP_AUTH_USER").trim().length() > 0)
	{
	    Credentials creds =
		new UsernamePasswordCredentials(AppConf.getConstant("HTTP_AUTH_USER"), AppConf
		    .getConstant("HTTP_AUTH_PASSWORD"));
	    client.getState().setCredentials(null, null, creds);
	}
	return client;
    }
}
