package be.vlaanderen.sbs.s6.framework.error;


/**
 * An exception caused by a class belonging to the model of the application.
 * These exceptions can be recovered from and are not considered to be a
 * show stopper.
 * @author Gert Vandenbemden
 * @version 1.0
 */
public class ModelFailureException extends ChainedException
{

    /**
     * 
     */
    private static final long serialVersionUID = 7784808688786947673L;
    public ModelFailureException(String message)
    {
        super(message);
    }
    public ModelFailureException(String message, Throwable cause)
    {
        super(message, cause);
    }
}