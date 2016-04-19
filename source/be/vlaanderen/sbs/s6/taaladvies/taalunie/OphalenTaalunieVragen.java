/*
 * Created on Nov 29, 2004
 */
package be.vlaanderen.sbs.s6.taaladvies.taalunie;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.httpclient.DefaultMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import be.vlaanderen.sbs.s6.framework.logging.AppLogger;
import be.vlaanderen.sbs.s6.taaladvies.Queries;
import be.vlaanderen.sbs.s6.taaladvies.appconf.AppConf;
import be.vlaanderen.sbs.s6.taaladvies.db.IDbConnection;
import be.vlaanderen.sbs.s6.taaladvies.db.MyDbConnection;
import be.vlaanderen.sbs.s6.taaladvies.model.Distributie;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;

/**
 * @author nzvwbg
 */
/**
 * @author nzvwbg
 */
public class OphalenTaalunieVragen {



    /**
     * <code>HERKOMSTNUMMER</code>: de NAAM van de xml-tag die het herkomstnummer bevat
     */
    private static final String HERKOMSTNUMMER = "id";

    /**
	 * <code>BINNENGEKOMEN</code>: de NAAM van de xml-tag die de ontvangstdatum bevat
	 */
	private static final  String BINNENGEKOMEN = "binnengekomen";

	/**
	 * <code>VOORNAAM_KLANT</code>: de NAAM van de xml-tag die de voornaam van de klant bevat
	 */
	private static final  String VOORNAAM_KLANT = "voornaam";

	/**
	 * <code>NAAM_KLANT</code>: de NAAM van de xml-tag die die de naam van de klant bevat
	 */
	private static final  String NAAM_KLANT = "naam";

	/**
	 * <code>ORGANISATIE</code>: de NAAM van de xml-tag die de organisatie naam bevat
	 */
	private static final  String ORGANISATIE = "organisatie";

	/**
	 * <code>E_MAIL</code>: de NAAM van de xml-tag die het e-mailadres van de klant bevat
	 */
	private static final  String E_MAIL = "e-mail";

	/**
	 * <code>FAX</code>: de NAAM van de xml-tag die het faxnummer van de klant bevat
	 */
	private static final  String FAX = "fax";

	/**
	 * <code>TELEFOON</code>: de NAAM van de xml-tag die het telefoonnummer van de klant bevat
	 */
	private static final  String TELEFOON = "telefoon";

	/**
	 * <code>VRAAG</code>: de NAAM van de xml-tag die de taalvraag bevat
	 */
	private static final  String VRAAG = "vraag";



	/**
     * Methode die de nieuwe taalvragen van de Taalunieversum website ophaalt
     *
     * @throws TaalunieException algemene exception die opgevangen moet worden in de main zodat de batch
     * file een return-code kan afleveren (0 of 1)
     */
    public void ophalenTaalunieVragen() throws TaalunieException {
        // Exit code voor de batch: 0 is okee, 1 is niet okee

        //initialiseer de appConf class nodig voor de resources , constants
        new TaalunieInitialization().init();
        Document doc = null;
        AppLogger.getInstance().debug("ophaalurl: " + AppConf.getConstant("OPHAALURL"));

        // Create an instance of HttpClient.
        HttpClient client = TaalunieInitialization.createHttpClient();

        // Create a method instance.
        GetMethod httpGet = new GetMethod(AppConf.getConstant("OPHAALURL"));

        String url = "";
        try {
            url = httpGet.getURI().toString();
        } catch (URIException e1) {
            e1.printStackTrace();
        }
        AppLogger.getInstance().info("Ophaalurl met querystring: " + url);

        // Provide custom retry handler is necessary
        DefaultMethodRetryHandler retryhandler = new DefaultMethodRetryHandler();
        retryhandler.setRequestSentRetryEnabled(true);
        retryhandler.setRetryCount(3);
        httpGet.setMethodRetryHandler(retryhandler);

        int xmlId = 0;
        try {
          // Execute the method.
          int statusCode = client.executeMethod(httpGet);

          if (statusCode != HttpStatus.SC_OK) {
              throw new TaalunieException("Ophalen van de nieuwe taalvragen faalde: "
                      + httpGet.getStatusLine()
                      + " "
                      + httpGet.getStatusText());
          }

          // Read the response body.
          // The response first has to be stored in a string because we upload it

          byte[] xmlBytes = httpGet.getResponseBody();
          String xmlString = new String(xmlBytes, "UTF-8");

          AppLogger.getInstance().debug("totale lengte van het xml bestand: " + xmlString.length());
          AppLogger.getInstance().debug("xml bestand: " + xmlString);
          xmlId = Oproep.insertTaalvragenXML(httpGet.getURI().toString(), xmlString.toString());
          AppLogger.getInstance().debug("xml bestand succesvol toegevoegd aan de databank");
          doc = readXML(new StringReader(xmlString.toString()));
          AppLogger.getInstance().debug("xml bestand succesvol geparsed");

          verwerkXML(doc);

        } catch (HttpException e) {
            storeExceptionInDb(xmlId, e);
        } catch (IOException e) {
            storeExceptionInDb(xmlId, e);
        } catch (SQLException e) {
            AppLogger.getInstance().error("sql state: " + e.getSQLState());
            AppLogger.getInstance().error("errorcode: " + e.getErrorCode());
            storeExceptionInDb(xmlId, e);
        } catch (JDOMException e) {
            storeExceptionInDb(xmlId, e);
        } catch (ParseException e) {
            storeExceptionInDb(xmlId, e);
        } finally {
          // Release the connection.
          httpGet.releaseConnection();
        }
    }

    /**
     * @param xmlId de id van de opgehaalde xml in de databank
     * @param e de foutboodschap
     * @throws TaalunieException een algemene exceptie
     */
    private void storeExceptionInDb(int xmlId, Exception e) throws TaalunieException {
        try {
            Oproep.insertTaalvragenXMLFoutBoodschap(xmlId, e.getMessage());
        } catch (SQLException e1) {
            AppLogger
            	.getInstance()
            	.error("De applicatie kon de volgende foutboodschap niet in de databank wegschrijven: "
                    	+ e.getMessage());
        }
        throw new TaalunieException("Ophalen van de nieuwe taalunievragen faalde: " + e.getMessage(), e);
    }

    /**
     * Parset het xml document, creëert nieuwe oproep per taalvraag en stockeert die taalvraag
     * @param doc het xml document dat de nieuwe taalvragen bevat
     *
     * @throws ParseException exceptie tijdens het parsen van de zenddatum
     * file een return-code kan afleveren (0 of 1)
     */
    private void verwerkXML(Document doc) throws ParseException{

        int opgehaaldeTaalvragenCounter = 0;
        int nieuweTaalvragenCounter = 0;
        Element rootElement = doc.getRootElement();
        List<Element> children = rootElement.getChildren();
        for (Iterator<Element> iter = children.iterator(); iter.hasNext();) {
            opgehaaldeTaalvragenCounter++;
            Element element = iter.next();

            String id = element.getChildText(HERKOMSTNUMMER);
            String binnengekomen = element.getChildText(BINNENGEKOMEN);
            String voornaam = element.getChildText(VOORNAAM_KLANT);
            String naam = element.getChildText(NAAM_KLANT);
            String organisatie = element.getChildText(ORGANISATIE);
            String email = element.getChildText(E_MAIL);
            String fax = element.getChildText(FAX);
            String telefoon = element.getChildText(TELEFOON);
            String vraag = element.getChildText(VRAAG);

            AppLogger.getInstance().debug("herkomstnummer: " + id);
            AppLogger.getInstance().debug("binnengekomen: " + binnengekomen);
            AppLogger.getInstance().debug("voornaam: " + voornaam);
            AppLogger.getInstance().debug("naam: " + naam);
            AppLogger.getInstance().debug("organisatie: " + organisatie);
            AppLogger.getInstance().debug("e-mail: " + email);
            AppLogger.getInstance().debug("fax: " + fax);
            AppLogger.getInstance().debug("telefoon: " + telefoon);
            AppLogger.getInstance().debug("vraag: " + vraag);

            // als het herkomstnummer al in de taaladvies databank zit, ga door met volgende taalvraag
            if(Oproep.taalunieVraagAlreadyInDB(id)){
                AppLogger.getInstance().debug("taalvraag met herkomstnummer "
                        + id
                        + "("
                        + vraag
                        +") al aanwezig in databank");
                continue;
            }

            nieuweTaalvragenCounter++;
    		//creëer een nieuwe oproep
            Oproep oproep = creeerOproep(id, binnengekomen, voornaam, naam, email, telefoon, fax, organisatie);

    		//creëer een nieuwe taalvraag
    		Taalvraag taalvraag = creeerVraag(vraag);

    		//haal id van extern domein, taalunieversum herkomst en e-mail medium op uit de databank
            zetDomeinMediumHerkomst(oproep);

            //voeg de oproep aan de databank toe
    		int oproepID = Oproep.insert(oproep);
    		taalvraag.setOproepId(oproepID);
    		//voeg de taalvraag aan de databank toe
    		Taalvraag.insert(taalvraag);
        }
        AppLogger.getInstance().info(opgehaaldeTaalvragenCounter + " taalvragen opgehaald...");
        AppLogger.getInstance().info(nieuweTaalvragenCounter + " nieuwe taalvragen bewaard...");
    }

    /**
     * Methode die het domein op "extern" zet, de herkomst op "taalunieversum" en het medium
     * op "e-mail"
     * @param oproep de oproep waarvan de velden worden aangepast
     */
    private void zetDomeinMediumHerkomst(Oproep oproep) {

        IDbConnection dbconnection = MyDbConnection.getInstance();
        Connection con = dbconnection.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
        	if (con != null) {

        	    // zet extern als domein
        		pst = con.prepareStatement(Queries.ID_DOMEIN_EXTERN);
        		rs = pst.executeQuery();
        		if (rs.next()) {
        			oproep.setDomeinId(rs.getInt("id"));
        		}

        		// zet Taalunieversum als herkomst
        		pst = con.prepareStatement(Queries.HERKOMST_BY_NAME);
        		pst.setString(1,"Taalunieversum");
        		rs = pst.executeQuery();
        		if (rs.next()) {
        			oproep.setHerkomstId(rs.getInt("id"));
        		}

        		// zet medium gegevens
        		pst = con.prepareStatement(Queries.ID_MEDIUM_EMAIL);
        		rs = pst.executeQuery();
        		if (rs.next()) {
        			oproep.setMediumId(rs.getInt("id"));
        		}
        		Distributie distributie = new Distributie();
        		distributie.setMediumId(oproep.getMediumId());
        		distributie.setDistributiedatum(new Date(new java.util.Date().getTime()));
        		int distributieId = Distributie.insert(distributie);
        		oproep.setDistributieId(distributieId);

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
    }

    /**
     * @param vraag de vraag als simpele string
     * @return Taalvraag een object van de klasse Taalvraag aan de hand van de vraag string
     */
    private Taalvraag creeerVraag(String vraag) {
        Taalvraag taalvraag = new Taalvraag();
        taalvraag.setVraag(vraag);
        taalvraag.setVraagHTML(vraag);
        taalvraag.setAfgehandeld(false);
        taalvraag.setVolgnummer(1);
        taalvraag.setAntwoordHTML("");
        taalvraag.setToelichting("");
        taalvraag.setToelichtingHTML("");
        return taalvraag;
    }

    /**
     * creëert een nieuwe oproep aan de hand van de ontvangen gegevens
     *
     * @param id het herkomstnummer van het Taalunieversum
     * @param binnengekomen de datum van ontvangst
     * @param voornaam de voornaam van de vraagsteller
     * @param naam de naam van de vraagsteller
     * @param email het e-mail adres van de vraagsteller
     * @param telefoon het telefoonnummer van de vraagsteller
     * @param fax het faxnummer van de vraagsteller
     * @param organisatie organisatienaam van de vraagsteller
     * @return Oproep een nieuwe oproep
     * @throws ParseException fout in het parsen van de datum
     * file een return-code kan afleveren (0 of 1)
     */
    private Oproep creeerOproep(String id
            					, String binnengekomen
            					, String voornaam
            					, String naam
            					, String email
            					, String telefoon
            					, String fax
            					, String organisatie) throws ParseException{
        Oproep oproep = new Oproep();
        oproep.setHerkomstnummer(id);

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        java.util.Date parsedDate;
        parsedDate = sd.parse(binnengekomen);
        date = new Date(parsedDate.getTime());
        oproep.setOproepdatum(date);

        oproep.setVoornaam(voornaam);
        oproep.setNaam(naam);
        oproep.setEmail(email);
        oproep.setTelefoon(telefoon);
        oproep.setFax(fax);
        oproep.setOrganisatie(organisatie);

        oproep.setType(1);
        return oproep;
    }

    /**
     *  Build het xml-<code>Document</code> uit de <code>InputStream</code>.
     *
     *@param  xml                    xml
     *@return                        the return value
     * @throws JDOMException algemene exception die opgevangen moet worden in de main zodat de batch
     * file een return-code kan afleveren (0 of 1)
     *
     */
    private static Document readXML(Reader xml) throws JDOMException{
        SAXBuilder builder = null;
        Document doc = null;

        // build JDOM Document van xml-bestand (inputstream)
        builder =
            new SAXBuilder("org.apache.xerces.parsers.SAXParser", true);
        builder.setFeature("http://apache.org/xml/features/validation/schema", true);
        builder.setFeature("http://xml.org/sax/features/validation", true);
        builder.setFeature("http://xml.org/sax/features/namespaces", true);
        builder.setProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation"
                			, AppConf.getConstant("OPHAAL_XMLSCHEMALOCATIE"));
        builder.setErrorHandler(new TaalunieSaxErrorHandler());
        doc = builder.build(xml);

        return doc;
    }

    /**
     * @param args argumenten worden genegeerd
     */
    public static void main(String[] args) {

        OphalenTaalunieVragen ophalenTaalvragen = new OphalenTaalunieVragen();
        try {
            ophalenTaalvragen.ophalenTaalunieVragen();
            System.exit(0);
        } catch (TaalunieException e) {
            AppLogger.getInstance().fatal(e.getMessage() + "\n" + e.toString());
            System.exit(1);
        }
    }
}
