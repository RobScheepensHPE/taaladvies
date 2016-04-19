package be.vlaanderen.sbs.s6.framework.error;

/**
 * Mimicks new functionality in 1.4
 * http://www.javaworld.com/javaworld/jw-08-2001/jw-0803-exceptions.html
 * @author Brian Geotz
 * @version $Revision: 1.1 $ $Date: 2005/06/27 10:37:44 $
 */
public class ChainedException extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = -7372324928501028418L;
    private Throwable cause;

    public ChainedException()
    {
        super();
    }

    public ChainedException(String message)
    {
        super(message);
    }

    public ChainedException(String message, Throwable cause)
    {
        super(message);
        this.cause = cause;
    }

    public Throwable getCause()
    {
        return this.cause;
    }

    public String getCauseMessage()
    {
        if (this.cause == null)
            return null;
        return this.cause.getMessage();
    }

    public boolean isCause()
    {
        return (this.cause != null);
    }

    public void printStackTrace()
    {
        super.printStackTrace();
        if (cause != null)
            {
            System.err.println("Caused by:");
            cause.printStackTrace();
        }
    }

    public void printStackTrace(java.io.PrintStream ps)
    {
        super.printStackTrace(ps);
        if (cause != null)
            {
            ps.println("Caused by:");
            cause.printStackTrace(ps);
        }
    }

    public void printStackTrace(java.io.PrintWriter pw)
    {
        super.printStackTrace(pw);
        if (cause != null)
            {
            pw.println("Caused by:");
            cause.printStackTrace(pw);
        }
    }
}