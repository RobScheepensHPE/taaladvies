package be.vlaanderen.sbs.s6.taaladvies.appconf;


import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.JDOMException;


/**
 * Container for constants and resources. This is an object that can store
 * configuration parameters.
 *
 * @author Jan Van Lysebeth
 * @version 1.0
 */
public class AppConf {
    final static String APP_CONFIG_ERROR_MESSAGE = "APPLICATION CONFIGURATION ERROR!";
    final static String MISSING_RESOURCESTAG_ERROR_MESSAGE = "No '<resources>' tag in ";
    final static String MISSING_CONSTANTSTAG_ERROR_MESSAGE = "No '<constants>' tag in ";
    final static String CLASSPATH_ERROR_MESSAGE = " file not found in classpath.";
    final static String NO_NAME_TAG_ERROR_MESSAGE = " contains error!\nNo 'name' attribute tag in ";
    final static String DEFAULT_PATH = "/conf/appconf.xml";

    private static Hashtable<String, Element> resources = null;
    private static Hashtable<String, Element> constants = null;

    //private static AppConf singletonInstance = null;

    public static void init() throws AppConfException, IOException, JDOMException {
        AppConfReader reader = new AppConfReader();
        reader.read();
        resources = reader.getResources();
        constants = reader.getConstants();
    }

    /**
     * Gets the value of the constant. Returns null for constants that
     * can't be found.
     *
     * @param    constantName    the name of the constant you wish to get the value from.
     * @return the constant the value of the constants
     */
    public static String getConstant(String constantName) {
        String value = null;
        Element element = constants.get(constantName);
        if (element != null) {
            value = element.getText();
        }
        return value;
    }

    /**
     * Gets the value of the resource. Returns null for resources that
     * can't be found.
     *
     * @param resourceName the name of the resource you wish to get the value from.
     * @return the constant the value of the resource
     */
    public static String getResource(String resourceName) {
        String value = null;
        Element element = resources.get(resourceName);
        if (element != null) {
            value = element.getText();
        }
        return value;
    }

    /**
     * Gets a list of attributes.
     *
     * @param    constantName    the name of the resource you wish to get the value from.
     * @return the constant the value of the resource
     */
    public static Hashtable<String, String> getConstantAttribs(String constantName) {
        Hashtable<String, String> attribs = null;
        Element element = constants.get(constantName);
        if (element != null) {
            attribs = attribsToList(element.getAttributes());
        }
        return attribs;
    }

    /**
     * Gets the value of the resource.
     *
     * @param resourceName the name of the resource you wish to get the value from.
     * @return the constant the value of the resource
     */
    public static Hashtable<String, String> getResourceAttribs(String resourceName) {
        Hashtable<String, String> attribs = null;
        Element element = resources.get(resourceName);
        if (element != null) {
            attribs = attribsToList(element.getAttributes());
        }
        return attribs;
    }

    /**
     * Not implemented yet.
     *
     * @return
     */
    public boolean setResource(String resourceName) {

        return false;
    }

    /**
     * Not implemented yet.
     *
     * @return
     */
    public boolean setConstant(String constant) {

        return false;
    }

    /**
     * Not implemented yet.
     *
     * @return
     */
    public boolean setResourceAttribs(Hashtable<String, String> resourceAttribs) {

        return false;
    }

    /**
     * Not implemented yet.
     *
     * @return
     */
    public boolean setConstantAttribs(Hashtable<String, String> constantAttribs) {

        return false;
    }

    /**
     * Not implemented yet.
     *
     * @throws Exception
     */
    public void write() throws Exception {

    }

    /**
     * Prints every constant and resource stored in the config HashMap.
     */
    public void printAll() {

        printHashtable(resources);
        printHashtable(constants);
    }


    /**
     * prints all key value pairs in a hashtable. Used for debugging.
     */
    private void printHashtable(Hashtable<String, Element> hash) {
        Enumeration<String> enumeration = hash.keys();
        while (enumeration.hasMoreElements()) {
            Object key = enumeration.nextElement();
            Element element = hash.get(key);
            Object value = element.getText().trim();
            System.out.println(value);
        }
    }

    private static Hashtable<String, String> attribsToList(List<Attribute> attribs) {

        Hashtable<String, String> table = new Hashtable<String, String>();
        Iterator<Attribute> iterator = attribs.iterator();
        while (iterator.hasNext()) {
            Attribute attrib = iterator.next();
            table.put(attrib.getName(), attrib.getValue());
        }
        return table;
    }

    /**
     * Retrieves the constants for this application.
     *
     * @return java.util.Hashtable
     */
    public java.util.Hashtable<String, Element> getConstants() {
        return constants;
    }


    /**
     * Retrieves the resources for this application.
     *
     * @return java.util.Hashtable
     */
    public java.util.Hashtable<String, Element> getResources() {
        return resources;
    }

}
