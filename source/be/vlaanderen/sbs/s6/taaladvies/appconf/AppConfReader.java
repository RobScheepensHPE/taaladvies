/**
 * @(#)RCFactory.java	1.00	01/04/2002
 */
package be.vlaanderen.sbs.s6.taaladvies.appconf;


import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Iterator;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


/**
 * <p>This is a reader for a specific config-xml-file format.
 * It is just a helper class in the appconfig package.
 * You can make it parse any file in the classpath.</p>
 * <p/>
 * <p>Default it will search for <code>conf/appconf.xml</code>.
 * There is a constructor available to can make it search somewhere else.
 * The path to the file is always interpreted as relative to the
 * classpath.</p>
 *
 * @author Jan Van Lysebeth
 * @version 1.0
 * @see <a href="AppConf.html">AppConf.java</a>
 */
class AppConfReader {

    private String xmlpath = AppConf.DEFAULT_PATH;
    private Hashtable<String, Element> resources = new Hashtable<String, Element>();
    private Hashtable<String, Element> constants = new Hashtable<String, Element>();

    void read() throws AppConfException, JDOMException, IOException {
        Document doc;
        InputStream in = null;

        Element resourcesElement;
        Element constantsElement;
        try {
            //free the parser to parse
            SAXBuilder builder = new SAXBuilder();
            in = getClass().getResourceAsStream(xmlpath);
            if (in == null) {
                throw new IOException(xmlpath + AppConf.CLASSPATH_ERROR_MESSAGE);
            }
            doc = builder.build(in);

            //check for mandatory tags and attribs
            Element root = doc.getRootElement();
            if (root.getAttribute("name") == null) {
                throw new AppConfException(
                        xmlpath + AppConf.NO_NAME_TAG_ERROR_MESSAGE + root.getName());
            } else {
                //String appName = root.getAttribute("name").getValue();
            }
            resourcesElement = root.getChild("resources");
            constantsElement = root.getChild("constants");
            if (resourcesElement == null) {
                throw new AppConfException(
                        AppConf.MISSING_RESOURCESTAG_ERROR_MESSAGE + xmlpath);
            }
            if (constantsElement == null) {
                throw new AppConfException(
                        AppConf.MISSING_CONSTANTSTAG_ERROR_MESSAGE + xmlpath);
            }
        }
        finally {
            if (in != null) {
                in.close();
            }
        }

        elementToHashtable(this.resources, resourcesElement, "");
        elementToHashtable(this.constants, constantsElement, "");
    }


    private void elementToHashtable(
            Hashtable<String, Element> hashtable,
            Element elem,
            String elemName)
            throws AppConfException {

        String childName = "";
        String partOfChildName = "";
        Iterator<Element> children = elem.getChildren().iterator();
        //for all children of elem
        while (children.hasNext()) {
            Element child = children.next();

            //build last part of restant name
            Attribute nameAttribute = child.getAttribute("name");
            if (nameAttribute == null) {
                if (child.hasChildren()) {
                    throw new AppConfException(
                            xmlpath + AppConf.NO_NAME_TAG_ERROR_MESSAGE + elem.getName());
                }
                partOfChildName = child.getName().trim();
            } else {
                partOfChildName = nameAttribute.getValue().trim();
            }

            //complete restant name
            if (elemName.equals("")) {
                childName = partOfChildName;
            } else {
                childName = elemName + "." + partOfChildName;
            }

            //write restant to hashtable
            if (!child.getText().trim().equals("")) {
                hashtable.put(childName, child);
            }

            //if child has children, repeat process
            if (child.hasChildren()) {
                elementToHashtable(hashtable, child, childName);
            }
        }

    }

    /**
     * Gets the resources
     *
     * @return Returns a Hashtable
     */
    Hashtable<String, Element> getResources() {
        return resources;
    }


    /**
     * Gets the constants
     *
     * @return Returns a Hashtable
     */
    Hashtable<String, Element> getConstants() {
        return constants;
    }
}


