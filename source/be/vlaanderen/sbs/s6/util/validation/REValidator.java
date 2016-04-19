package be.vlaanderen.sbs.s6.util.validation;

// Import voor gebruik reguliere expressies voor het uitvoeren van checks
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import org.apache.regexp.RE;

/**
 * @author Jeroen Van Beirendonck
 * @version 0.1 This class takes care of the different validations possible in an application <p/> The user calls the static
 *          method <b>validate<b> with 2 parameters :<br>
 *          <li>the type of validation, listed in the file "REValidatorTypes.properties"</li>
 *          <li>the actual expression that needs to be validated</li>
 *          <p/> TODO : <p/> Since the jakarta-regexp suite is NOT thread safe, there might need to be object-pooling That way,
 *          we can try to limit the amount of RE's generated at all times. Since validation is a re-ocurring event, even several
 *          times per form, the ability to reuse RE-objects is a definite advantage. Look into it !
 */
public class REValidator
{
    private static REValidator instance = new REValidator();
    private static String propertiesFile = "REValidatorTypes.properties";
    private static java.util.Hashtable<String, String> REObjects;

    private REValidator()
    {
    }

    private static void init()
    {
	REObjects = new Hashtable<String, String>();
	InputStream in = null;
	Properties props = null;
	Enumeration enumeration = null;
	try
	{
	    in = instance.getClass().getResourceAsStream(propertiesFile);
	    props = new Properties();
	    props.load(in);
	    enumeration = props.propertyNames();
	    while (enumeration.hasMoreElements())
	    {
		String className = (String) enumeration.nextElement();
		REObjects.put(className, props.getProperty(className));
	    }
	} catch (NullPointerException nullPrtExpj)
	{
	    System.err.println("Nullpointer exception while retrieving " + propertiesFile);
	} catch (FileNotFoundException fileNotFoundExp)
	{
	    System.err.println("File not found : " + propertiesFile);
	} catch (SecurityException securityExp)
	{
	    System.err.println("Security exception with reading " + propertiesFile);
	} catch (IOException ioExpObj)
	{
	    System.err.println("IO Exception while reading " + propertiesFile);
	} catch (Exception ex)
	{
	    System.err.println("Exception while processing " + propertiesFile);
	    ex.printStackTrace();
	}
    }

    public static boolean validate(String validationType, String expression)
    {
	RE evaluator = null;
	if (REObjects == null)
	    init();
	String validationString = null;
	try
	{
	    validationString = REObjects.get(validationType);
	    if (validationString != null)
		evaluator = new RE(REObjects.get(validationType));
	    else
		throw new Exception("No existing validation-pattern found for \"" + validationType + "\"");
	} catch (Exception ex)
	{
	    System.err.println("Exception while loading regular expression : " + ex.getMessage());
	    ex.printStackTrace();
	}
	if (evaluator != null)
	{
	    return evaluator.match(expression);
	} else
	{
	    return false;
	}
    }

    /**
         * Main method for testing purposes only
         */
    public static void main(String args[])
    {
	try
	{
	    boolean done = false;
	    while (!done)
	    {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		init();
		Enumeration<String> keys = REObjects.keys();
		int index = 1;
		// String currentElement = "";
		while (keys.hasMoreElements())
		{
		    /* currentElement = (String) */keys.nextElement();
		}
		String line = in.readLine().trim();
		if (validate("nonNegativeInteger", line))
		{
		    System.err.println("Parsed ok !!");
		    int patternNumber = Integer.parseInt(line);
		    System.err.println(patternNumber);
		    if (patternNumber > 0 && patternNumber < REObjects.size())
		    {
			index = 1;
			keys = REObjects.keys();
			while (keys.hasMoreElements() && index < patternNumber)
			{
			    keys.nextElement();
			    index++;
			}
			// String expression = "";
			// String pattern = (String) keys.nextElement();
			System.out.flush();
			/* expression = */in.readLine();
		    } else
		    {
		    }
		}
		System.out.flush();
	    }
	} catch (Exception ex)
	{
	    ex.printStackTrace();
	}
	/*
         * String pattern = "wildcardNumber"; String expression = "*4566*"; expression = "*4566*"; expression = "*4566*";
         * expression = "*4566*"; String test1 = "+3232885557"; String test2 = "03/288.55.57"; String test3 = "(03)288.55.57";
         * String test4 = "+323/288.5A557"; try { RE rekening = new RE(accountPattern); RE telefoon = new RE(telephonePattern);
         * RE straat = new RE(streetnrPattern); System.err.println("12A " + straat.match("12A")); System.err.println("324A" +
         * straat.match("324A")); System.err.println("1245" + straat.match("1245")); System.err.println("A2443" +
         * straat.match("A2443")); if(!straat.match("A2443") || !straat.getParen(0).equals("A2443"))
         * System.err.println(straat.getParen(0) + " NOT OK !"); else System.err.println(telefoon.getParen(0) + " OK !"); String
         * email1 = "testje.lalal-blahblah.rofl@shite.com"; String email2 = ".@test-yadda1234.12345.com"; String email3 =
         * "_-__---__@nothingness.org"; String email4 = "me@myplace.org"; RE emailadres = new RE(emailPattern);
         * System.err.println(email1 + " " + emailadres.match(email1)); System.err.println(email2 + " " +
         * emailadres.match(email2)); System.err.println(email3 + " " + emailadres.match(email3)); System.err.println(email4 + " " +
         * emailadres.match(email4)); } catch(RESyntaxException ex) { ex.printStackTrace(); }
         */
    }
}
