/*
 * Created on Dec 1, 2004
 */
package be.vlaanderen.sbs.s6.taaladvies.taalunie;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.httpclient.DefaultMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import be.vlaanderen.sbs.s6.framework.logging.AppLogger;
import be.vlaanderen.sbs.s6.taaladvies.Queries;
import be.vlaanderen.sbs.s6.taaladvies.appconf.AppConf;
import be.vlaanderen.sbs.s6.taaladvies.db.IDbConnection;
import be.vlaanderen.sbs.s6.taaladvies.db.MyDbConnection;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;

/**
 * @author nzvwbg
 */
public class DoorsturenTaalunieVragen
{
    private static final String TAOCODE = "2";
    /**
         * <code>ZENDDATUMPNAME</code>: bevat de parameterNAAM die de zenddatum bevat
         */
    private static final String ZENDDATUMPNAME = "zenddatum";
    /**
         * <code>TAOPNAME</code>: bevat de parameterNAAM die de TAOPNAME bevat
         */
    private static final String TAOPNAME = "tao";
    /**
         * <code>TITELPNAME</code>: bevat de parameterNAAM die de titel van de taalvraag bevat
         */
    private static final String TITELPNAME = "titel";
    /**
         * <code>TOELICHTINGPNAME</code>: bevat de parameterNAAM die de toelichting voor de taalvraag bevat
         */
    private static final String TOELICHTINGPNAME = "toelichting";
    /**
         * <code>ANTWOORDPNAME</code>: bevat de parameterNAAM die het antwoord op de taalvraag bevat
         */
    private static final String ANTWOORDPNAME = "antwoord";
    /**
         * <code>NAAMPNAME</code>: bevat de parameterNAAM die de naam van de taaladviseur bevat
         */
    private static final String NAAMPNAME = "naam";
    /**
         * <code>VOORNAAMPNAME</code>: bevat de parameterNAAM die de voornaam van de taaladviseur bevat
         */
    private static final String VOORNAAMPNAME = "voornaam";
    /**
         * <code>HERKOMSTNUMMERPNAME</code>: bevat de parameterNAAM die het herkomstnummer van de taalvraag bevat
         */
    private static final String HERKOMSTNUMMERPNAME = "id";

    /**
         * Methode die de afgesloten en nog niet doorgezonden taalvragen afkomstig van de Taalunieversum website, doorzendt naar
         * de Taalunieversum website
         * @throws TaalunieException algemene exception die opgevangen moet worden in de main zodat de batch file een
         *         return-code kan afleveren (0 of 1)
         */
    public void doorsturenTaalvragen() throws TaalunieException
    {
	// initialiseer de appConf class nodig voor de resources , constants
	new TaalunieInitialization().init();
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		int taalantwoordenCounter = 0;
		// get all closed "taalvragen" that have to be sent to the "taalunie"
		pst = con.prepareStatement(Queries.ALL_GESLOTEN_OPROEPEN_TO_UPLOAD_TO_TAALUNIE);
		ResultSet rs = pst.executeQuery();
		while (rs.next())
		{
		    // send taalvraag as parameters of the POST request:
		    // get current date and time
		    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		    String now = sd.format(new Date());
		    String oproepnummer = rs.getString("id");
		    String herkomstnummer = rs.getString("herkomstnummer");
		    String voornaam = rs.getString("voornaam");
		    String naam = rs.getString("naam");
		    String antwoord = rs.getString("antwoord");
		    String toelichting = rs.getString("toelichting");
		    String titel = rs.getString("titel");
		    String tao = rs.getString("tao");
		    if (tao != null && tao.equals(TAOCODE))
		    {
			tao = "ja";
		    } else
		    {
			tao = "nee";
		    }
		    NameValuePair[] params =
			{new NameValuePair(HERKOMSTNUMMERPNAME, herkomstnummer), new NameValuePair(VOORNAAMPNAME, voornaam),
			    new NameValuePair(NAAMPNAME, naam), new NameValuePair(ANTWOORDPNAME, antwoord),
			    new NameValuePair(TOELICHTINGPNAME, toelichting), new NameValuePair(TITELPNAME, titel),
			    new NameValuePair(TAOPNAME, tao), new NameValuePair(ZENDDATUMPNAME, now)};
		    AppLogger.getInstance().info(
			"taalvraag met oproepnummer: " + oproepnummer + "\nherkomstnummer: " + herkomstnummer
			    + "\nvoornaam: " + voornaam + "\nnaam: " + naam + "\nantwoord: " + antwoord + "\ntoelichting: "
			    + toelichting + "\ntitel: " + titel + "\ntao: " + tao + "\nzenddatum: " + now + "\n");
		    try
		    {
			sendParams(params);
			// if successfully sent, delete the oproep from the send queue
			taalantwoordenCounter++;
			Oproep.removeFromTaalunieSendQueue(rs.getInt("id"));
			AppLogger.getInstance().info("oproep " + oproepnummer + " verwijderd uit queue");
		    } catch (TaalunieException e)
		    {
			AppLogger.getInstance().error(
			    "taalvraag met oproepnummer: " + oproepnummer + "\nherkomstnummer: " + herkomstnummer
				+ "\nvoornaam: " + voornaam + "\nnaam: " + naam + "\nantwoord: " + antwoord
				+ "\ntoelichting: " + toelichting + "\ntitel: " + titel + "\ntao: " + tao + "\nzenddatum: "
				+ now + "\n" + e.getMessage());
		    }
		}
		AppLogger.getInstance().info(taalantwoordenCounter + " antwoorden doorgestuurd...");
	    } else
	    {
		throw new TaalunieException("Geen Databank Connectie !");
	    }
	} catch (java.sql.SQLException e)
	{
	    throw new TaalunieException("een databank fout: ", e); // just throw it
	} finally
	{
	    try
	    {
		if (con != null)
		{
		    if (pst != null)
		    {
			pst.close();
		    }
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		throw new TaalunieException("een databank fout: ", sqle);
	    }
	}
    }

    /**
         * Zend het taalvraag-antwoord als http post met parameters naar de taalunie.
         * @param params bevat de door te sturen data in de vorm van http parameters
         * @exception TaalunieException werpt een exceptie als het doorsturen misloopt, wordt opgevangen in de main method
         */
    private void sendParams(NameValuePair[] params) throws TaalunieException
    {
	// Create an instance of HttpClient.
	HttpClient client = TaalunieInitialization.createHttpClient();
	// Create a method instance.
	PostMethod post = new PostMethod(AppConf.getConstant("DOORSTUURURL"));
	post.setRequestBody(params);
	// Provide custom retry handler is necessary
	DefaultMethodRetryHandler retryhandler = new DefaultMethodRetryHandler();
	retryhandler.setRequestSentRetryEnabled(false);
	retryhandler.setRetryCount(3);
	post.setMethodRetryHandler(retryhandler);
	try
	{
	    // post the xml document
	    int statusCode = client.executeMethod(post);
	    if (statusCode != HttpStatus.SC_OK)
	    {
		throw new TaalunieException("De gebruikte http methode faalde: " + post.getStatusLine());
	    }
	    // Process the response body. (throws exception if opladen != "OK")
	    this.verwerkHTTPResponse(post.getResponseBodyAsStream());
	} catch (IOException e)
	{
	    String url = "";
	    try
	    {
		url = post.getURI().toString();
	    } catch (URIException e1)
	    {
		e1.printStackTrace();
	    }
	    String exceptionString = "De applicatie faalde de taalvraag door te sturen naar de volgende URL: '" + url;
	    if (!post.getHostConfiguration().isProxySet())
	    {
		exceptionString += "' en GEEN Proxy configuratie";
	    } else
	    {
		exceptionString +=
		    "' en als host-configuratie : ProxyHost = " + post.getHostConfiguration().getProxyHost()
			+ "' en ProxyPort = '" + post.getHostConfiguration().getProxyPort() + "'";
	    }
	    throw new TaalunieException(exceptionString + ": " + e.getMessage(), e);
	} finally
	{
	    // Release the connection.
	    post.releaseConnection();
	}
    }

    /**
         * Build het xml-<code>Document</code> uit de <code>InputStream</code>.
         * @param xml xml
         * @return the return value
         * @throws TaalunieException JDOMException
         */
    private static Document leesHTTPResponse(InputStream xml) throws TaalunieException
    {
	SAXBuilder builder = null;
	Document doc = null;
	// build JDOM Document van xml-bestand (inputstream)
	builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser", true);
	builder.setFeature("http://apache.org/xml/features/validation/schema", true);
	builder.setFeature("http://xml.org/sax/features/validation", true);
	builder.setFeature("http://xml.org/sax/features/namespaces", true);
	builder.setErrorHandler(new TaalunieSaxErrorHandler());
	builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser", true);
	builder.setFeature("http://apache.org/xml/features/validation/schema", true);
	builder.setFeature("http://xml.org/sax/features/validation", true);
	builder.setFeature("http://xml.org/sax/features/namespaces", true);
	builder.setProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", AppConf
	    .getConstant("DOORSTUURRESULTAAT_XMLSCHEMALOCATIE"));
	builder.setErrorHandler(new TaalunieSaxErrorHandler());
	try
	{
	    doc = builder.build(xml);
	} catch (JDOMException e)
	{
	    throw new TaalunieException("Fout tijdens het opbouwen van de xml response code van het Taalunieversum", e);
	}
	return doc;
    }

    /**
         * @param xmlStream de te verwerken xml stream met taalunie taalvragen
         * @throws TaalunieException werpt een exceptie als het doorsturen misloopt, wordt opgevangen in de main method
         */
    private void verwerkHTTPResponse(InputStream xmlStream) throws TaalunieException
    {
	Document doc = leesHTTPResponse(xmlStream);
	XMLOutputter outputter = new XMLOutputter();
	AppLogger.getInstance().debug(outputter.outputString(doc));
	Element rootElement = doc.getRootElement();
	AppLogger.getInstance().debug("rootelement: " + rootElement.getName());
	String opladen = rootElement.getChildText("opladen");
	String extraInfo = rootElement.getChildText("extra_info");
	AppLogger.getInstance().debug("opladen: " + opladen);
	AppLogger.getInstance().debug("extra_info: " + extraInfo);
	if (opladen == null || !opladen.equals("OK"))
	{
	    throw new TaalunieException("De HTTP-response van de taalunie was negatief: '" + opladen
		+ "'\nextra informatie: " + extraInfo);
	}
    }

    /**
         * main methode om het ophalen van nieuwe taalunie taalvragen
         * @param args argumenten worden genegeerd
         */
    public static void main(String[] args)
    {
	DoorsturenTaalunieVragen doorsturen = new DoorsturenTaalunieVragen();
	try
	{
	    doorsturen.doorsturenTaalvragen();
	    System.exit(0);
	} catch (TaalunieException e)
	{
	    AppLogger.getInstance().fatal(e.getMessage(), e);
	    System.exit(1);
	}
    }
}
