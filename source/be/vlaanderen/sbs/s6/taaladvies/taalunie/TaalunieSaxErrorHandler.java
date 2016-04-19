package be.vlaanderen.sbs.s6.taaladvies.taalunie;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * implementeert het gedrag voor het behandelen van sax parser fouten
 * @author nzvwbg
 */
public class TaalunieSaxErrorHandler implements ErrorHandler {

    /**
     * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
     */
    public void warning(SAXParseException exception) {
        // Ignore warnings
    }

    /**
     * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
     */
    public void error(SAXParseException exception) throws SAXException {

        // A validity error; rethrow the exception.
        throw new SAXException("Het opgehaalde XML-bestand bevat een VALIDATIE fout op lijn " 
                	+ exception.getLineNumber()
                	+ " kolom " + exception.getColumnNumber()
                	+ ": " + exception.getLocalizedMessage());
    }

    /**
     * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
     */
    public void fatalError(SAXParseException exception) throws SAXException {
        // A well-formedness error
        throw new SAXException("Het opgehaalde XML-bestand bevat een OPMAAK fout (well-formdness error) op lijn " 
            	+ exception.getLineNumber()
            	+ " kolom " + exception.getColumnNumber()
            	+ ": " + exception.getMessage());

    }

}