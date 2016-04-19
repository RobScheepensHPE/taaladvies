package be.vlaanderen.sbs.s6.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

public class PropertiesFile
{

	/**
	 * Used for handling property files that contain data used by the application
	 * <br>
	 * Such a property file consist of multiple lines with the parameters and their values,
	 * each line followed by a newline :
	 * <br>
	 * mailhost = localhost
	 * email = mail@test.com
	 * <p>
	 * <i>Creation date: (18/06/2001)</i>
	 * @author: Tim Appelmans, Jeroen Van Beirendonck
	 */

	/**
	 * Hastable containing the parameters read from the input file
	 */
	private java.util.Hashtable<String, String> hash;

	/**
	 * Reads the contents of a specified properties file
	 * @param File The filename of the properties file to be read
	 */

	public PropertiesFile(String File) throws Exception
	{
		hash = new Hashtable<String, String>();

		try
		{
			String fileName = File;
			InputStream in = new FileInputStream(fileName);
			Properties props = new Properties();
			props.load(in);
			Enumeration enumeration = props.propertyNames();

			while (enumeration.hasMoreElements())
			{
				String name = (String) enumeration.nextElement();
				String value = props.getProperty(name);

				hash.put(name, value.trim());
			}

			props = null;
			in = null;
			fileName = null;
		}
		catch (NullPointerException nullPrtExpj)
		{
			throw new Exception("Nullpointer exception while retrieving Properties-File (" + File + ")");
		}
		catch (FileNotFoundException fileNotFoundExp)
		{
			throw new Exception("Properties-File not found (" + File + ")");
		}
		catch (SecurityException securityExp)
		{
			throw new Exception("Security exception with reading Properties-File (" + File + ")");
		}
		catch (IOException ioExpObj)
		{
			throw new Exception("IO Exception while reading Properties-File (" + File + ")");
		}
	}
	/**
	 * Reads the contents of a specified properties file
	 * @param File The filename of the properties file to be read
	 */

	public PropertiesFile(String File, boolean absolute) throws Exception
	{
		hash = new Hashtable<String, String>();

		try
		{
			String fileName = File;
			InputStream in;

			if (absolute)
				in = new FileInputStream(fileName);
			else
				in = getClass().getResourceAsStream(fileName);

			Properties props = new Properties();
			props.load(in);
			Enumeration enumeration = props.propertyNames();

			while (enumeration.hasMoreElements())
			{
				String name = (String) enumeration.nextElement();
				String value = props.getProperty(name);

				hash.put(name, value.trim());
			}

			props = null;
			in = null;
			fileName = null;
		}
		catch (NullPointerException nullPrtExpj)
		{
			throw new Exception("Nullpointer exception while retrieving Properties-File (" + File + ")");
		}
		catch (FileNotFoundException fileNotFoundExp)
		{
			throw new Exception("Properties-File not found (" + File + ")");
		}
		catch (SecurityException securityExp)
		{
			throw new Exception("Security exception with reading Properties-File (" + File + ")");
		}
		catch (IOException ioExpObj)
		{
			throw new Exception("IO Exception while reading Properties-File (" + File + ")");
		}
	}

	/**
	 * Get the value in the Hashtable for a certain parameter from the properties file
	 * @param name The name of the parameter requested
	 * @return The value of the requested parameter
	 */

	public String getValueFromHash(String name)
	{
		String value = hash.get(name);
		return value;
	}
}