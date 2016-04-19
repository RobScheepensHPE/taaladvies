package be.vlaanderen.sbs.s6.framework.error;

/**
 * A GeneralFailureException indicates that something
 * has gone horribly wrong within the Application (i.e. a show stopper). 
 * This Exception should be thrown by all classes and caught by the servlet container,
 * which subsequently passes it to the pre-defined error.jsp page
 * (see web.xml).
 * @author: Gert Vandenbemden
 * @version	1.0
 */
public class GeneralFailureException extends ChainedException
{
    /**
     * 
     */
    private static final long serialVersionUID = -8363643211228916926L;
    /**
     * Default constructor using a message describing the Exception.
     * @param msg java.lang.String
     */
    public GeneralFailureException(String msg)
    {
        super(msg);
    }
    /**
     * Default constructor using a message describing the Exception and the exception itself.
     * @param msg java.lang.String
     * @param e java.lang.Exception 
     */
    public GeneralFailureException(String msg, Exception e)
    {
        super(msg, e);
    }
}