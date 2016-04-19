/*
 * Created on Dec 2, 2004
 */
package be.vlaanderen.sbs.s6.taaladvies.taalunie;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import be.vlaanderen.sbs.s6.framework.logging.AppLogger;
import be.vlaanderen.sbs.s6.taaladvies.appconf.AppConf;

/**
 * @author nzvwbg
 */
public class TestAntwoordenServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -7760006409730914681L;
    /**
     * <code>EXTRA_INFO</code> de extra info tag
     */
    private static final String EXTRA_INFO = "extra_info";
    /**
     * <code>OPLADEN</code> de opladen tag
     */
    private static final String OPLADEN = "opladen";
    /**
     * <code>DOORSTUUR_RESULTAAT</code> de doorstuur_resultaat tag
     */
    private static final String DOORSTUUR_RESULTAAT = "doorstuur_resultaat";
    
    /**
     * inherit van super implementatie
     */
    public TestAntwoordenServlet() {
        super();
    }
    
    /**
     * Servlet die het de oplaad site van de taalunie nabootst
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String taalantwoord = request.getParameter("taalantwoord");
        PrintWriter writer = response.getWriter();
        AppLogger.getInstance().debug("########### dopost received: " + taalantwoord + "############");
        SAXBuilder builder = null;
        boolean correct = true;
        try {
            // build JDOM Document van xml-bestand (inputstream)
            builder =
                new SAXBuilder("org.apache.xerces.parsers.SAXParser", true);
            builder.setFeature("http://apache.org/xml/features/validation/schema", true);
            builder.setFeature("http://xml.org/sax/features/validation", true);
            builder.setFeature("http://xml.org/sax/features/namespaces", true);
            builder.setErrorHandler(new TaalunieSaxErrorHandler());
            // try to build received xml parameter (attention: saxbuilder does not take a plain string object as parameter)
            AppLogger.getInstance().debug("...start building receiving xml");
            builder.build(new StringReader(taalantwoord));
            AppLogger.getInstance().debug("...finished building receiving xml");
        } 
        catch (JDOMException ex) {
            correct = false;
            AppLogger.getInstance().error("error while building incoming xml", ex);
            writer.print(createResponseXml("NOK", ex.getMessage()));
        }
        
        if(correct){
            String output = createResponseXml("OK", null);
            AppLogger.getInstance().debug("writing response to output: " + output);
            writer.print(output);
        }
        writer.flush();
        writer.close();       
    }
    
    /**
     * Creates the xml document following the doorstuurresultaat.xsd scheme
     * @param opladenString 	OK or NOK depending on whether the xml was received successfully or not
     * @param extraInfo			String containing optional extra information e.g. errors
     * @return String het antwoord in xml formaat
     */
    private String createResponseXml(String opladenString, String extraInfo) {
        
        AppLogger.getInstance().debug("...start creating response xml");
        Document doc = new Document();
        
        Element root = new Element(DOORSTUUR_RESULTAAT);
        Namespace xsiNS = Namespace.getNamespace("xsi","http://www.w3.org/2001/XMLSchema-instance");
        root.addNamespaceDeclaration(xsiNS);
        
        AppLogger.getInstance().debug("DOORSTUURRESULTAAT_XMLSCHEMALOCATIE: " 
                				+ AppConf.getConstant("DOORSTUURRESULTAAT_XMLSCHEMALOCATIE"));
        //specifieer de locatie van het gebruikte xml-schema
        root.setAttribute(new Attribute
                		("noNamespaceSchemaLocation"
                		  ,AppConf.getConstant("DOORSTUURRESULTAAT_XMLSCHEMALOCATIE")
                		  , xsiNS));
               
        doc.setRootElement(root);
    
        Element eId = new Element(OPLADEN);
        eId.setText(opladenString);
        root.addContent(eId);
        
        if(extraInfo != null){
            Element eVoornaam = new Element(EXTRA_INFO);
            eVoornaam.setText(extraInfo);
            root.addContent(eVoornaam);
        }
        
        XMLOutputter outputter = new XMLOutputter();
        AppLogger.getInstance().debug("...creating output response xml");
        String outputString = outputter.outputString(doc);
        AppLogger.getInstance().debug("...finished creating response xml");
        return outputString;        
    }
}
