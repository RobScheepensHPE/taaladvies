package be.vlaanderen.sbs.s6.utils;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * Utility routine to add date and time to messages<P>
 * The constructor sets up:<BR>
 * <EM>level</EM> = the highest level of message that will be output.  Messages
 * with a level greater than that set up by the constructor will be
 * formatted in &lt;utMessage name&gt.text but will not be output to either
 * stderr.log or stdout.log.  See &lt;utMessage name&gt;.doMsg()
 * for detailed explanation of level.<BR>
 * <EM>source</EM> = an identifier to be printed with each message to show
 * where it came from.<BR>
 * <H2><CENTER>Messages</CENTER></H2>
 * <P>
 * utMessage itself can produce messages.
 * <P>The format of the messages is:<BR><BR>
 *
 * dd/mm/yy hh:mm:ss type source (as specified by user) message<P>
 *
 * <TABLE BORDER="1" CELLPADDING="3" CELLSPACING="0" WIDTH="100%">
 * <TR BGCOLOR="#CCCCFF"><TH>debugLevel</TH><TH>Message</TH><TH>Cause</TH></TR>
 * <TR>
 * <TD>1</TD><TD>Open failed on logfile &lt;file path&gt;</TD>
 * <TD>The log file specified could not be opened. Output will go to stderr.log
 *     and stdout.log.</TD>
 * </TR>
 * </TABLE>
 */

public class utMessage
{
/**
 * Always contains the last message formatted by doMsg() - even if it was not
 * output to stderr.log or stdout.log.
 */
    public String text;
/**
 * The highest level of message that will be output.
 */
    private int level;
/**
 * Identifier to show where the message came from.
 */
    private String source;
/**
 * "dd/MM/yy HH:mm:ss "
 */
    private SimpleDateFormat dateformat;
/**
 * Alternate (to stderr.log and stdout.log) output file
 */
    private PrintStream logfile;
/**
 * Constructs a utMessage with level = 4 and source = "".
 */
    public utMessage()
    {
        this(4, "");
    }

/**
 * Constructs a utMessage with level = debugLevel and source = "".
 */
    public utMessage(int debugLevel)
    {
        this(debugLevel, "");
    }

/**
 * Constructs a utMessage with level = 4 and source = className.
 */
    public utMessage(String className)
    {
        this(4, className);
    }

/**
 * Constructs a utMessage with level = debugLevel and source = className and logfile = logfilepath.
 */
    public utMessage(int debugLevel, String className, String logfilepath) throws Exception
    {
        this(debugLevel, className);

        try
        {
            this.logfile = new PrintStream(new FileOutputStream(logfilepath, true), true);
        }
        catch (Exception e)
        {
// Report the problem on stderr.log
            this.logfile = null;
            this.doTrace(e, "Open failed on logfile " + logfilepath);
            throw e;
        }
    }

/**
 * Constructs a utMessage with level = debugLevel and source = className.
 */
    public utMessage(int debugLevel, String className)
    {
        this.text = "";
        this.level = debugLevel;
        if (level < 0)
        {
// Ensure CRITICAL messages are ALWAYS output
            level = 0;
        }
        this.source = className;
        this.dateformat = new SimpleDateFormat("dd/MM/yy HH:mm:ss ");
        this.logfile = null;
    }

/**
 * Convert an Exception and a String to:<BR>
 * newmsg = Exception.getMessage + " " + String<BR>
 * then doMsg(debugLevel, newmsg)
 */
    public void doMsg(int debugLevel, Exception e, String message)
    {
        String newmsg = e.getMessage() + " " + message;
        doMsg(debugLevel, newmsg);
    }

/**
 * Convert an SQLException and a String to:<BR>
 * newmsg = SQLException.getMessage + " "<BR>
 *          + SQLException.getErrorCode + " "+ String<BR>
 * then doMsg(debugLevel, newmsg)
 */
    public void doMsg(int debugLevel, SQLException e, String message)
    {
        String newmsg = e.getMessage() + " "
                      + e.getErrorCode() + " "
                      + message;
        doMsg(debugLevel, newmsg);
    }

/**
 * Create a message of the form<BR><BR>
 * dd/MM/yy HH:mm:ss [type] source message<BR><BR>
 * Where dd/MM/yy HH:mm:ss are the current date & time<BR>
 * type is CRITICAL for debugLevel 0, ERROR for debugLevel 1,
 * WARN. for debugLevel 2, and "     " for debugLevel 3.<BR>
 * type is not present for other values of debugLevel.<BR>
 * source is the value set-up by the constructor<BR>
 * message is the value passed as a parameter<P>
 * Any message with a debugLevel higher than the level set-up by
 * the constructor will be formatted in <EM>text</EM>(and can be used by the caller)
 * but will not be output.  CRITICAL messages are ALWAYS output.
 * Otherwise, debugLevels 1-3 will be written
 * to stderr.log and other debugLevels will be written to stdout.log.
 *
 * <P>If a logfilepath was specified to the constructor, ALL output will
 * go to this file.
 */
    public void doMsg(int debugLevel, String message)
    {
        this.text = this.dateformat.format(new java.util.Date());

        if (debugLevel == 0)
        {
            this.text += "CRITICAL ";
        }

        if (debugLevel == 1)
        {
            this.text += "ERROR ";
        }

        if (debugLevel == 2)
        {
            this.text += "WARN. ";
        }

        if (debugLevel == 3)
        {
            this.text += "      ";
        }

        if (this.source != "")
        {
            this.text += this.source + " ";
        }

        this.text += message;

        if (debugLevel <= this.level)
        {
            if (logfile == null)
            {
                if (debugLevel < 4)
                {
                    System.err.println(this.text);
                }
                else
                {
                    System.out.println(this.text);
                }
            }
            else
            {
                logfile.println(this.text);
            }
        }
    }

/**
 * Calls doMsg(1, e, "STACK TRACE") then e.printStackTrace().
 */
    public void doTrace(Exception e)
    {
        doMsg(1, e, "STACK TRACE");
        e.printStackTrace(System.err);
    }


/**
 * Calls doMsg(debugLevel, e, "STACK TRACE") then e.printStackTrace().
 */
    public void doTrace(int debugLevel, Exception e)
    {
        doMsg(debugLevel, e, "STACK TRACE");
        e.printStackTrace(System.err);
    }


/**
 * Calls doMsg(1, e, "STACK TRACE " + message) then e.printStackTrace().
 */
    public void doTrace(Exception e, String message)
    {
        doMsg(1, e, "STACK TRACE " + message);
        e.printStackTrace(System.err);
    }


/**
 * Calls doMsg(debugLevel, e, "STACK TRACE " + message) then e.printStackTrace().
 */
    public void doTrace(int debugLevel, Exception e, String message)
    {
        doMsg(debugLevel, e, "STACK TRACE " + message);
        e.printStackTrace(System.err);
    }
/**
 * Sets a new level for output messages.
 */
    public void setLevel(int newLevel)
    {
        level = newLevel;
        if (level < 0)
        {
// Ensure CRITICAL messages are ALWAYS output
            level = 0;
        }
    }
}


