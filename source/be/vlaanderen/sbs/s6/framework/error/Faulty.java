package be.vlaanderen.sbs.s6.framework.error;

/**
 * A simple example class throwing a GeneralFailureException.
 * This Exception is caught by the default error page of the web application.
 * @author Gert Vandenbemden
 */
public class Faulty
{
/**
 * Faulty constructor.
 */
public Faulty()
{
    super();
}
/**
 * Simple Method causing a GeneralFailureException.
 * @exception be.vlaanderen.sbs.s6.framework.error.GeneralFailureException The Eexception being thrown.
 */
public void gowrong() throws GeneralFailureException
{
    throw new GeneralFailureException("Going Horribly Wrong ..."+new java.io.IOException("No more battery power left."));
}
/**
 * For Testing purposes.
 * @param args java.lang.String[]
 */
public static void main(String[] args)
{
    Faulty faulty = new Faulty();
    try
        {
        faulty.gowrong();
    }
    catch (Exception e)
        {
        System.out.println("Exception was caught ..." + e.getMessage());
        e.printStackTrace(System.out);
    }

}
}
