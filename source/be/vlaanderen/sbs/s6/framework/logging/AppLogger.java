package be.vlaanderen.sbs.s6.framework.logging;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.NDC;
import org.apache.log4j.PropertyConfigurator;
import be.vlaanderen.sbs.s6.framework.error.GeneralFailureException;

/**
 * <p>AppLogger is a singleton wrapper around the Log4j <code><b>org.apache.log4j.Category</b></code>
 *   Class used for logging purposes. </p>
 * <ul>
 *   <li><b>Configuration :</b> </li>
 * </ul>
 * <blockquote>
 *   <p>Only one root Category is being used. It is configured through the <code>"<b>log4j.properties</b>"</code>
 *     configuration file which should reside in the <code>"<b>web_app_name/WEB-INF/classes/conf/</b>"</code>
 *     directory of your application. A default configuration is used instead if
 *     the aforementioned file cannot be located (without an <code>SMTPAppender</code>).
 *     <br>
 *     Typically, 2 appenders are configured with an <code>org.apache.log4j.PatternLayout</code>:<br>
 *   </p>
 * </blockquote>
 * <table width="100%" border="0" cellspacing="0" cellpadding="0">
 *   <tr>
 *     <td width="7%">&nbsp;</td>
 *     <td width="93%">an <code><b>org.apache.log4j.ConsoleAppender</b></code> which
 *       writes its messages to <code>System.out</code> and </td>
 *   </tr>
 *   <tr>
 *     <td width="7%">&nbsp;</td>
 *     <td width="93%">&nbsp;</td>
 *   </tr>
 *   <tr>
 *     <td width="7%">&nbsp;</td>
 *     <td width="93%">an <code><b>org.apache.log4j.SMTPAppender</b></code> which
 *       sends e-mails containing a predefined number of buffered messages through
 *       SMTP. Its threshold priority is set to <code>ERROR</code> to avoid flooding
 *       the mail server with messages of a lesser priority (and it is commented
 *       out by default in the provided <code>"log4j.properties"</code> file for
 *       debugging purposes). <br>
 *       The order of precedence of the priorities is : <code>DEBUG</code> &lt; <code>INFO</code>
 *       &lt; <code>WARN</code> &lt; <code>ERROR</code> &lt; <code>FATAL</code>.
 *       <br>
 *       In addition, the following .jar files should be present in the classpath
 *       : mail.jar (version JavaMail 1.1.3), activation.jar, xerces.jar (and log4j.jar).</td>
 *   </tr>
 *   <tr>
 *     <td width="7%">&nbsp;</td>
 *     <td width="93%">&nbsp;</td>
 *   </tr>
 *   <tr>
 *     <td width="7%">&nbsp;</td>
 *     <td width="93%">They are both configured to use the same conversion pattern
 *       to let the user specify the output format of the messages (see below). A
 *       definition of the conversion characters used by these patterns can be found
 *       in the API documentation of the <code>org.apache.log4j.PatternLayout</code>
 *       class. <br>
 *       <br>
 *       Pattern used: [%d{dd MMM yyyy HH:mm:ss}] - %-5p - %c - [%x] \n\t - %m\n<br>
 *       <br>
 *       This looks like: <br>
 *       <code>...<br>
 *       [22 feb 2002 14:37:22] - DEBUG - Root Category Name From Props - [Yet another
 *       NDC .....] <br>
 *       - Debugging .... <br>
 *       ... </code><br>
 *       <br>
 *       The initialization of the <code>AppLogger</code> takes place in the <code>init()</code>
 *       method of the <code> InitializationServlet</code> through the following
 *       code:<br>
 *       <code>...</code><br>
 *       <code>AppLogger al = AppLogger.getInstance(); // --- This retrieves the
 *       one and only instance of the AppLogger class ---</code> <br>
 *       <code>...</code> </td>
 *   </tr>
 * </table>
 * <br>
 * <br>
 * <ul>
 *   <li><b>Usage :</b><br>
 *     <br>
 *     Each class using the Log4j functionality should get an instance of the <code>AppLogger</code>
 *     class. This instance can then subsequently be used to log messages with different
 *     priority levels to the configured Log4j Appenders. <br>
 *     e.g. <br>
 *     <table width="100%" border="0" cellspacing="0" cellpadding="0">
 *       <tr>
 *         <td width="7%">&nbsp;</td>
 *         <td width="93%"><code>...</code> <br>
 *           <code>// --- Use the following declaration for each class or ---</code>
 *           <br>
 *           <code>// --- declare a private static variable if used in classes with
 *           static methods. ---</code> <br>
 *           <code>private AppLogger al; </code> <br>
 *           <code>...</code> <br>
 *           <code>// --- Only done once for each class (e.g. put it in the constructor)
 *           ---</code> <br>
 *           <code>al = AppLogger.getInstance(); </code> <br>
 *           <code>...</code><br>
 *           <code>...</code><br>
 *           <code>try</code><br>
 *           <code>&nbsp;&nbsp;{</code><br>
 *           <code>&nbsp;&nbsp;... // --- Something going horribly wrong ---</code><br>
 *           <code>&nbsp;&nbsp;}</code><br>
 *           <code>catch (Exception e)</code><br>
 *           <code>&nbsp;&nbsp;{</code><br>
 *           <code>&nbsp;&nbsp;&nbsp;&nbsp;al.error("message", "NDC", this.getClass().getName(),
 *           e);</code> <br>
 *           <code>&nbsp;&nbsp;}</code><br>
 *           <code>...</code> </td>
 *       </tr>
 *     </table>
 *     <br>
 *     <br>
 *     Note1: The NDC (Nested Diagnostic Context) could be a unique identification
 *     of the currently logged on user (e.g. his/her ip address obtained through
 *     the <code>getRemoteAddr()</code> method of the <code>ServletRequest</code>
 *     class; i.e. for classes being able to gain access to such an object). <br>
 *     <br>
 *     Note2: Please have a look at the Log4j API docs for a detailed description
 *     of the various Log4j classes mentioned above. These can be obtained <a href=http://jakarta.apache.org/log4j>here</a>
 *     (http://jakarta.apache.org/log4j).</li>
 * </ul>
 * @author Gert Vandenbemden
 * @version	1.1
 */
public class AppLogger implements ILogger
{
    /**
     * Corresponding Log4j <code>Category</code> instance.
     */
    private Category category;
    /**
     * The single instance of the <code><b>AppLogger</b></code> Class to be used for logging.
     * To avoid possible synchronization issues in the getInstance() method, it is initialized here.
     */
    private static final AppLogger _instance = new AppLogger();
    /**
     * Error prefix for constructing error message during initialization.
     */
    private static final String ERROR_PREFIX = "::ERROR ";
    /**
     * Error message used to indicate that the <code><b>"log4j.properties"</b></code> file could not be read.
     */
    private static final String ERROR_IO = " - Could not read from input file : ";
    /**
     * Error message used to indicate that the <code><b>"log4j.properties"</b></code> file could not be found.
     */
    private static final String ERROR_NULL = " - Could not find input file : ";
    /**
     * Error message used to indicate that the <code><b>AppConf</b></code> object passed for configuration purposes could not be used (null).
     */
    private static final String ERROR_MISCONFIGURE =
        " - Improper initialization due to an invalid (null) Application Configuration (Using defaults instead): ";
    /**
     * Fully qualified Class name.
     */
    private final static String CLASS_NAME =
        "be.vlaanderen.sbs.s6.framework.logging.AppLogger";
    /**
    * Default location of the Log4j Tag configuration file.
    */
    private final static String DEFAULT_LOG4J_CONF_PATH = "/conf/log4j.properties";
    /**
     * Default name of the root category used throughout the entire application.
     */
    //private static final String DEFAULT_LOG4J_CAT_NAME = "appCategoryName";
    /**
     * Default Root Category definition with a priority of INFO.
     */
    private final static String DEFAULT_LOG4J_ROOTCAT_DEF = "INFO, CONSOLE";
    /**
     * Default Root debugging mode (false).
     */
    private final static String DEFAULT_LOG4J_ROOTCAT_DBG = "false";
    /**
     * Default Root appender type is an <code>org.apache.log4j.ConsoleAppender</code>.
     */
    private final static String DEFAULT_LOG4J_ROOTCAT_APPENDERTYPE =
        "org.apache.log4j.ConsoleAppender";
    /**
     * Default Root category uses an <code>org.apache.log4j.PatternLayout</code>.
     */
    private final static String DEFAULT_LOG4J_ROOTCAT_LAYOUT =
        "org.apache.log4j.PatternLayout";
    /**
     * Default Root pattern used by the <code>org.apache.log4j.PatternLayout</code>.
     */
    private final static String DEFAULT_LOG4J_ROOTCAT_PATTERN =
        "[%d{dd MMM yyyy HH:mm:ss}] - %-5p - %c - [%x] \n\t - %m\n";
    /**
     * Separator String between NDC and Class Name.
     */
    private static final String LOG4J_NDC_CLASS_SEP = " - <C>: ";
    /**
      * Key name from the <code><b>"log4j.properties"</b></code> file containing the name of the root category used throughout the entire application.
      */
    private static final String LOG4J_ROOTCAT_NAME = "log4j.rootcatname";

    /**
     * Private constructor to setup the singleton AppLogger instance.
     * Switches to a default Log4J setup in case the custom configuration could
     * not be located.
     */
    private AppLogger()
    {
        boolean configured = true;
        Properties log4jprops = new Properties();

        try
        {
            // --- Load the Log4J properties file from the default location ---
            InputStream is = getClass().getResourceAsStream(DEFAULT_LOG4J_CONF_PATH);
            log4jprops.load(is);
            is.close();

            // --- Configure Log4J with the settings from the supplied properties file ---
            PropertyConfigurator.configure(log4jprops);
        }
        catch (IOException e)
        {
            printError(e, ERROR_IO);
            configured = false;
        }
        catch (NullPointerException e)
        {
            printError(e, ERROR_NULL);
            configured = false;
        }

        // --- Use a simple custom configuration if the properties file could not be found---
        if (!configured)
        {
            // --- Indicate at the application level that the properties file couldn't be used correctly ---
            printError(
                new GeneralFailureException(
                    new StringBuffer(ERROR_PREFIX)
                        .append(ERROR_MISCONFIGURE)
                        .toString()),
                "");

            // --- Initialize Log4J with basic configuration settings ---
            PropertyConfigurator.configure(getDefaultProperties());
        }

        // --- Create a Category with the predefined application name ---
        category =
            Category.getInstance(
                (!configured)
                    ? "<Default App Name>"
                    : log4jprops.getProperty(LOG4J_ROOTCAT_NAME));

        info(
            new StringBuffer("\n__________\n:: ")
                .append(
                    (!configured)
                        ? " - Log4J configured with default settings.\n"
                        : "- Log4J successfully configured. \nFile used: WEB-INF/classes")
                .append(
                    (!configured)
                        ? "The Log4J configuration file"
                        : DEFAULT_LOG4J_CONF_PATH)
                .append(
                    (!configured)
                        ? " could NOT be found.\n__________\n"
                        : "\n__________\n")
                .toString(),
            CLASS_NAME);
    }

    /**
     * Log a DEBUG level message.
     *
     * @param msg the message to log
     */
    public void debug(String msg)
    {
        category.debug(msg);
    }

    /**
     * Log a DEBUG level message and an NDC (Nested Diagnostic Context).
     *
     * @param msg the message to log
     * @param ndc the nested diagnostic context to log
     */
    public void debug(String msg, String ndc)
    {
        NDC.push(ndc);
        category.debug(msg);
        NDC.pop();
        NDC.remove();
    }

    /**
     * Log a DEBUG level message, an NDC (Nested Diagnostic Context) and a Class name.
     *
     * @param msg the message to log
     * @param ndc the nested diagnostic context to log
     * @param className the name of the Class in which the message was logged
     */
    public void debug(String msg, String ndc, String className)
    {
        debug(
            msg,
            new StringBuffer(ndc)
                .append(LOG4J_NDC_CLASS_SEP)
                .append(className)
                .toString());
    }

    /**
     * Log a DEBUG level message along with an Exception, an NDC (Nested Diagnostic Context)
     * and a Class name.
     *
     * @param msg the message to log
     * @param ndc the nested diagnostic context to log
     * @param className the name of the Class in which the message was logged
     * @param aThrowable the exception to log
     */
    public void debug(
        String msg,
        String ndc,
        String className,
        Throwable aThrowable)
    {
        debug(
            msg,
            new StringBuffer(ndc)
                .append(LOG4J_NDC_CLASS_SEP)
                .append(className)
                .toString(),
            aThrowable);
    }

    /**
     * Log a DEBUG level message along with an Exception and an NDC (Nested Diagnostic Context).
     *
     * @param msg the message to log
     * @param ndc the nested diagnostic context to log
     * @param aThrowable the Exception to log
     */
    public void debug(String msg, String ndc, Throwable aThrowable)
    {
        NDC.push(ndc);
        category.debug(msg, aThrowable);
        NDC.pop();
        NDC.remove();
    }

    /**
     * Log a DEBUG level message along with an Exception
     *
     * @param msg the message to log
     * @param aThrowable the Exception to log
     */
    public void debug(String msg, Throwable aThrowable)
    {
        category.debug(msg, aThrowable);
    }

    /**
     * Log an ERROR level message.
     *
     * @param msg the message to log
     */
    public void error(String msg)
    {
        category.error(msg);
    }

    /**
     * Log an ERROR level message and an NDC (Nested Diagnostic Context).
     *
     * @param msg the message to log
     * @param ndc the nested diagnostic context to log
     */
    public void error(String msg, String ndc)
    {
        NDC.push(ndc);
        category.error(msg);
        NDC.pop();
        NDC.remove();
    }

    /**
     * Log an ERROR level message, an NDC (Nested Diagnostic Context) and a Class name.
     *
     * @param msg the message to log
     * @param ndc the nested diagnostic context to log
     * @param className the name of the Class in which the message was logged
     */
    public void error(String msg, String ndc, String className)
    {
        error(
            msg,
            new StringBuffer(ndc)
                .append(LOG4J_NDC_CLASS_SEP)
                .append(className)
                .toString());
    }

    /**
     * Log an ERROR level message along with an Exception, an NDC (Nested Diagnostic Context)
     * and a Class name.
     *
     * @param msg the message to log
     * @param ndc the nested diagnostic context to log
     * @param className the name of the Class in which the message was logged
     * @param aThrowable the Exception to log
     */
    public void error(
        String msg,
        String ndc,
        String className,
        Throwable aThrowable)
    {
        error(
            msg,
            new StringBuffer(ndc)
                .append(LOG4J_NDC_CLASS_SEP)
                .append(className)
                .toString(),
            aThrowable);
    }

    /**
     * Log an ERROR level message along with an Exception and an NDC (Nested Diagnostic Context).
     *
     * @param msg the message to log
     * @param ndc the nested diagnostic context to log
     * @param aThrowable the Exception to log
     */
    public void error(String msg, String ndc, Throwable aThrowable)
    {
        NDC.push(ndc);
        category.error(msg, aThrowable);
        NDC.pop();
        NDC.remove();
    }

    /**
     * Log an ERROR level message along with an Exception
     *
     * @param msg the message to log
     * @param aThrowable the Exception to log
     */
    public void error(String msg, Throwable aThrowable)
    {
        category.error(msg, aThrowable);
    }

    /**
     * Log an ERROR level Exception only
     *
     * @param aThrowable the Exception to log
     */
    public void error(Throwable aThrowable)
    {
        category.error("", aThrowable);
    }

    /**
     * Log a FATAL level message.
     *
     * @param msg the message to log
     */
    public void fatal(String msg)
    {
        category.fatal(msg);
    }

    /**
     * Log a FATAL level message and an NDC (Nested Diagnostic Context).
     *
     * @param msg the message to log
     * @param ndc the nested diagnostic context to log
     */
    public void fatal(String msg, String ndc)
    {
        NDC.push(ndc);
        category.fatal(msg);
        NDC.pop();
        NDC.remove();
    }

    /**
     * Log a FATAL level message, an NDC (Nested Diagnostic Context) and a Class name.
     *
     * @param msg the message to log
     * @param ndc the nested diagnostic context to log
     * @param className the name of the Class in which the message was logged
     */
    public void fatal(String msg, String ndc, String className)
    {
        fatal(
            msg,
            new StringBuffer(ndc)
                .append(LOG4J_NDC_CLASS_SEP)
                .append(className)
                .toString());
    }

    /**
     * Log a FATAL level message along with an Exception, an NDC (Nested Diagnostic Context)
     * and a Class name.
     *
     * @param msg the message to log
     * @param ndc the nested diagnostic context to log
     * @param className the name of the Class in which the message was logged
     * @param aThrowable the Exception to log
     */
    public void fatal(
        String msg,
        String ndc,
        String className,
        Throwable aThrowable)
    {
        fatal(
            msg,
            new StringBuffer(ndc)
                .append(LOG4J_NDC_CLASS_SEP)
                .append(className)
                .toString(),
            aThrowable);
    }

    /**
     * Log a FATAL level message along with an Exception and an NDC (Nested Diagnostic Context).
     *
     * @param msg the message to log
     * @param ndc the nested diagnostic context to log
     * @param aThrowable the Exception to log
     */
    public void fatal(String msg, String ndc, Throwable aThrowable)
    {
        NDC.push(ndc);
        category.fatal(msg, aThrowable);
        NDC.pop();
        NDC.remove();
    }

    /**
     * Log a FATAL level message along with an Exception
     *
     * @param msg the message to log
     * @param aThrowable the Exception to log
     */
    public void fatal(String msg, Throwable aThrowable)
    {
        category.fatal(msg, aThrowable);
    }

    /**
     * Creates a set of limited default properties to be used by the <code>org.apache.log4j.PropertyConfigurator<code>.
     * @return java.util.Properties
     */
    private Properties getDefaultProperties()
    {
        // --- Create some custom default properties ---
        Properties defProps = new Properties();
        defProps.setProperty("log4j.rootCategory", DEFAULT_LOG4J_ROOTCAT_DEF);
        defProps.setProperty("log4j.debug", DEFAULT_LOG4J_ROOTCAT_DBG);
        defProps.setProperty(
            "log4j.appender.CONSOLE",
            DEFAULT_LOG4J_ROOTCAT_APPENDERTYPE);
        defProps.setProperty(
            "log4j.appender.CONSOLE.layout",
            DEFAULT_LOG4J_ROOTCAT_LAYOUT);
        defProps.setProperty(
            "log4j.appender.CONSOLE.layout.ConversionPattern",
            DEFAULT_LOG4J_ROOTCAT_PATTERN);
        return defProps;
    }

    /**
     * Returns an instance of the AppLogger Class.
     * No lazy initialization of the _instance variable to avoid synchronization issues.
     * @return be.vlaanderen.sbs.s6.framework.logging.AppLogger
     */
    public static AppLogger getInstance()
    {
        // --- Return the existing instance of the AppLogger ---
        return _instance;
    }

    /**
     * Special method that returns the Log4j root <code>Appender</code>. This
     * method is useful for unit testing, where you can define a test
     * <code>Appender</code> and use it to verify the result of a log.
     *
     * @param theAppenderName the name of the <code>Appender</code> to return.
     *        It is the name defined in the log properties file.
     * @return the root appender or null if not found
     */
    public Appender getRootAppender(String theAppenderName)
    {
        return Category.getRoot().getAppender(theAppenderName);
    }

    /**
     * Log an INFO level message.
     *
     * @param msg the message to log
     */
    public void info(String msg)
    {
        category.info(msg);
    }

    /**
     * Log an INFO level message and an NDC (Nested Diagnostic Context).
     *
     * @param msg the message to log
     * @param ndc the nested diagnostic context to log
     */
    public void info(String msg, String ndc)
    {
        NDC.push(ndc);
        category.info(msg);
        NDC.pop();
        NDC.remove();
    }

    /**
     * Log an INFO level message, an NDC (Nested Diagnostic Context) and a Class name.
     *
     * @param msg the message to log
     * @param ndc the nested diagnostic context to log
     * @param className the name of the Class in which the message was logged
     */
    public void info(String msg, String ndc, String className)
    {
        info(
            msg,
            new StringBuffer(ndc)
                .append(LOG4J_NDC_CLASS_SEP)
                .append(className)
                .toString());
    }

    /**
     * Log an INFO level message along with an Exception, an NDC (Nested Diagnostic Context)
     * and a Class name.
     *
     * @param msg the message to log
     * @param ndc the nested diagnostic context to log
     * @param className the name of the Class in which the message was logged
     * @param aThrowable the Exception to log
     */
    public void info(String msg, String ndc, String className, Throwable aThrowable)
    {
        info(
            msg,
            new StringBuffer(ndc)
                .append(LOG4J_NDC_CLASS_SEP)
                .append(className)
                .toString(),
            aThrowable);
    }

    /**
     * Log an INFO level message along with an Exception and an NDC (Nested Diagnostic Context).
     *
     * @param msg the message to log
     * @param ndc the nested diagnostic context to log
     * @param aThrowable the Exception to log
     */
    public void info(String msg, String ndc, Throwable aThrowable)
    {
        NDC.push(ndc);
        category.info(msg, aThrowable);
        NDC.pop();
        NDC.remove();
    }

    /**
     * Log an INFO level message along with an Exception
     *
     * @param msg the message to log
     * @param aThrowable the Exception to log
     */
    public void info(String msg, Throwable aThrowable)
    {
        category.info(msg, aThrowable);
    }

    /**
     * @return true if the Log4j priority level is debugging
     */
    public boolean isDebugEnabled()
    {
        return category.isDebugEnabled();
    }

    /**
     * Used for testing the logging features of Log4J.
     * @param args java.lang.String[]
     */
    public static void main(String[] args)
    {
        AppLogger al = AppLogger.getInstance();
        al.debug("Debugging yippikajee ....");
        al.debug("Debugging yippikajee ....", "NDC ...");
        al.debug("Debugging yippikajee ....");
        al.debug("Debugging yippikajee ....", "Yet another NDC .....");
        al.debug("Debugging yippikajee ....");
        al.info("Showing very interesting Info ....");
        al.info(
            "Showing very interesting Info ....",
            "... with an equally interesting NDC",
            new Exception("Very interesting Exception"));
        al.warn("Warning .... Danger is imminent  ....");
        al.error("Error occurred ... Houston, we have a problem ....");
        al.error(
            "Error occurred ... She's gonna break, Captain ...."
                + new Exception("Scotty Exception"));
        al.fatal("Fatal .... aaaaaargggghhhhhl....");
    }

    /**
     * Prints a msg to the default System Error Log.
     * @param e java.lang.Exception
     */
    public void printError(Exception e, String msg)
    {
        System.err.println(
            new StringBuffer(ERROR_PREFIX)
                .append(CLASS_NAME)
                .append(msg)
                .append(e.getMessage())
                .toString());
        e.printStackTrace(System.err);
    }

    /**
     * Log a WARNING level message.
     *
     * @param msg the message to log
     */
    public void warn(String msg)
    {
        category.warn(msg);
    }

    /**
     * Log a WARNING level message and an NDC (Nested Diagnostic Context).
     *
     * @param msg the message to log
     * @param ndc the nested diagnostic context to log
     */
    public void warn(String msg, String ndc)
    {
        NDC.push(ndc);
        category.warn(msg);
        NDC.pop();
        NDC.remove();
    }

    /**
     * Log a WARNING level message, an NDC (Nested Diagnostic Context) and a Class name.
     *
     * @param msg the message to log
     * @param ndc the nested diagnostic context to log
     * @param className the name of the Class in which the message was logged
     */
    public void warn(String msg, String ndc, String className)
    {
        warn(
            msg,
            new StringBuffer(ndc)
                .append(LOG4J_NDC_CLASS_SEP)
                .append(className)
                .toString());
    }

    /**
     * Log a WARNING level message along with an Exception, an NDC (Nested Diagnostic Context)
     * and a Class name.
     *
     * @param msg the message to log
     * @param ndc the nested diagnostic context to log
     * @param className the name of the Class in which the message was logged
     * @param aThrowable the Exception to log
     */
    public void warn(String msg, String ndc, String className, Throwable aThrowable)
    {
        warn(
            msg,
            new StringBuffer(ndc)
                .append(LOG4J_NDC_CLASS_SEP)
                .append(className)
                .toString(),
            aThrowable);
    }

    /**
     * Log a WARNING level message along with an Exception and an NDC (Nested Diagnostic Context).
     *
     * @param msg the message to log
     * @param ndc the nested diagnostic context to log
     * @param aThrowable the Exception to log
     */
    public void warn(String msg, String ndc, Throwable aThrowable)
    {
        NDC.push(ndc);
        category.warn(msg, aThrowable);
        NDC.pop();
        NDC.remove();
    }

    /**
     * Log a WARNING level message along with an Exception
     *
     * @param msg the message to log
     * @param aThrowable the Exception to log
     */
    public void warn(String msg, Throwable aThrowable)
    {
        category.warn(msg, aThrowable);
    }
}