/**
 * @(#)AppConf.java	1.00	01/04/2002
 */


package be.vlaanderen.sbs.s6.taaladvies.appconf;



/**
 * Exception that can occure in the internals of the AppConf.init() method.
 * It means that something went wrong in the initialization of the
 * configuration of an application.
 * <P>Chances are that the configuration file doesn't exist or is corruptive.<P>
 *
 * @author	Jan Van Lysebeth
 * @version	1.0
 */


public class AppConfException extends Exception {

/**
     * 
     */
    private static final long serialVersionUID = 1893203239384435666L;

public AppConfException(final String msg)
{

    super(msg);
}


}


