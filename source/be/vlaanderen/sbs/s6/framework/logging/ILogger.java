package be.vlaanderen.sbs.s6.framework.logging;


/**
 * Interface used by classes implementing logging through the Log4J logging api.
 * @author Gert Vandenbemden
 * @version 1.0
 */
public interface ILogger
{

/**
 * Log a DEBUG level message.
 *
 * @param msg the message to log
 */
public void debug(String msg);
/**
 * Log a DEBUG level message along with an Exception
 *
 * @param msg the message to log
 * @param aThrowable the Exception to log
 */
public void debug(String msg, Throwable aThrowable);
/**
 * Log an ERROR level message.
 *
 * @param msg the message to log
 */
public void error(String msg);
/**
 * Log an ERROR level message along with an Exception
 *
 * @param msg the message to log
 * @param aThrowable the Exception to log
 */
public void error(String msg, Throwable aThrowable);
/**
 * Log an ERROR level Exception only
 *
 * @param msg the message to log
 * @param aThrowable the Exception to log
 */
public void error(Throwable aThrowable);
/**
 * Log a FATAL level message.
 *
 * @param msg the message to log
 */
public void fatal(String msg);
/**
 * Log a FATAL level message along with an Exception
 *
 * @param msg the message to log
 * @param aThrowable the Exception to log
 */
public void fatal(String msg, Throwable aThrowable);
/**
 * Log an INFO level message.
 *
 * @param msg the message to log
 */
public void info(String msg);
/**
 * Log an INFO level message along with an Exception
 *
 * @param msg the message to log
 * @param aThrowable the Exception to log
 */
public void info(String msg, Throwable aThrowable);
/**
 * @return true if the Log4j priority level is debugging.
 */
public boolean isDebugEnabled();
/**
 * Log a WARNING level message.
 *
 * @param msg the message to log
 */
public void warn(String msg);
/**
 * Log a WARNING level message along with an Exception
 *
 * @param msg the message to log
 * @param aThrowable the Exception to log
 */
public void warn(String msg, Throwable aThrowable);

/**
 * Log a DEBUG level message and an NDC (Nested Diagnostic Context).
 *
 * @param msg the message to log
 * @param ndc the nested diagnostic context to log
 */
public void debug(String msg, String ndc);


/**
 * Log a DEBUG level message along with an Exception and an NDC (Nested Diagnostic Context).
 *
 * @param msg the message to log
 * @param ndc the nested diagnostic context to log
 * @param aThrowable the Exception to log
 */
public void debug(String msg, String ndc, Throwable aThrowable);

/**
 * Log an ERROR level message and an NDC (Nested Diagnostic Context).
 *
 * @param msg the message to log
 * @param ndc the nested diagnostic context to log
 */
public void error(String msg, String ndc);

/**
 * Log an ERROR level message along with an Exception and an NDC (Nested Diagnostic Context).
 *
 * @param msg the message to log
 * @param ndc the nested diagnostic context to log
 * @param aThrowable the Exception to log
 */
public void error(String msg, String ndc, Throwable aThrowable);

/**
 * Log a FATAL level message and an NDC (Nested Diagnostic Context).
 *
 * @param msg the message to log
 * @param ndc the nested diagnostic context to log
 */
public void fatal(String msg, String ndc);

/**
 * Log a FATAL level message along with an Exception and an NDC (Nested Diagnostic Context).
 *
 * @param msg the message to log
 * @param ndc the nested diagnostic context to log
 * @param aThrowable the Exception to log
 */
public void fatal(String msg, String ndc, Throwable aThrowable);

/**
 * Log an INFO level message and an NDC (Nested Diagnostic Context).
 *
 * @param msg the message to log
 * @param ndc the nested diagnostic context to log
 */
public void info(String msg, String ndc);

/**
 * Log a WARNING level message and an NDC (Nested Diagnostic Context).
 *
 * @param msg the message to log
 * @param ndc the nested diagnostic context to log
 */
public void warn(String msg, String ndc);

/**
 * Log a WARNING level message along with an Exception and an NDC (Nested Diagnostic Context).
 *
 * @param msg the message to log
 * @param ndc the nested diagnostic context to log
 * @param aThrowable the Exception to log
 */
public void warn(String msg, String ndc, Throwable aThrowable);

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
    Throwable aThrowable);

/**
 * Log a DEBUG level message, an NDC (Nested Diagnostic Context) and a Class name.
 *
 * @param msg the message to log
 * @param ndc the nested diagnostic context to log
 * @param className the name of the Class in which the message was logged
 */
public void debug(String msg, String ndc, String className);


/**
 * Log an ERROR level message, an NDC (Nested Diagnostic Context) and a Class name.
 *
 * @param msg the message to log
 * @param ndc the nested diagnostic context to log
 * @param className the name of the Class in which the message was logged
 */
public void error(String msg, String ndc, String className);


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
    Throwable aThrowable);


/**
 * Log a FATAL level message, an NDC (Nested Diagnostic Context) and a Class name.
 *
 * @param msg the message to log
 * @param ndc the nested diagnostic context to log
 * @param className the name of the Class in which the message was logged
 */
public void fatal(String msg, String ndc, String className);


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
    Throwable aThrowable);


/**
 * Log an INFO level message, an NDC (Nested Diagnostic Context) and a Class name.
 *
 * @param msg the message to log
 * @param ndc the nested diagnostic context to log
 * @param className the name of the Class in which the message was logged
 */
public void info(String msg, String ndc, String className);


/**
 * Log an INFO level message along with an Exception, an NDC (Nested Diagnostic Context)
 * and Class name.
 *
 * @param msg the message to log
 * @param ndc the nested diagnostic context to log
 * @param className the name of the Class in which the message was logged
 * @param aThrowable the Exception to log
 */
public void info(
    String msg,
    String ndc,
    String className,
    Throwable aThrowable);


/**
 * Log an INFO level message along with an Exception and an NDC (Nested Diagnostic Context).
 *
 * @param msg the message to log
 * @param ndc the nested diagnostic context to log
 * @param aThrowable the Exception to log
 */
public void info(String msg, String ndc, Throwable aThrowable);


/**
 * Log a WARNING level message, an NDC (Nested Diagnostic Context) and a Class name.
 *
 * @param msg the message to log
 * @param ndc the nested diagnostic context to log
 * @param className the name of the Class in which the message was logged
 */
public void warn(String msg, String ndc, String className);


/**
 * Log a WARNING level message along with an Exception, an NDC (Nested Diagnostic Context)
 * and a Class name.
 *
 * @param msg the message to log
 * @param ndc the nested diagnostic context to log
 * @param className the name of the Class in which the message was logged
 * @param aThrowable the Exception to log
 */
public void warn(
    String msg,
    String ndc,
    String className,
    Throwable aThrowable);
}