package be.vlaanderen.sbs.s6.taaladvies;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.RequestUtils;
import be.vlaanderen.sbs.s6.framework.utils.StrutsNavigation;

/**
 * Checks if the request came from an authenticated user or not.
 * If the user hasn't been authenticated yet, then the request is
 * forwarded or redirected to the login page. The original requested url is
 * saved as request attribute.
 */
public class CheckAuthenticationTag extends TagSupport
{
	/**
     * 
     */
    private static final long serialVersionUID = 4222370392590417672L;
	/**
	 * This is the name that will be used to search a forward
	 * to the login page in the struts global-forwards.
	 */
	private final static String GLOBAL_FORWARD_TO_LOGIN = "login";
	private final static String LOGIN_PRINCIPAL = "Gebruiker";

	private static final String NO_GLOBAL_FORWARD_TO_LOGIN_FOUND_ERRMSG =
		"can't find global-forward to " + GLOBAL_FORWARD_TO_LOGIN;

	/**
	 * Logger instance for performing logging.
	 */
	private static Log log =
		LogFactory.getLog(
			"be.vlaanderen.sbs.s6.framework.security.taglib.CheckAuthenticationTag");

	/**
	 * Defer processing until the end of this tag is encountered.
	 */
	public int doStartTag()
	{
		return (SKIP_BODY);
	}

	/**
	 * Looks up the ActionForward to the login and performs the forward
	 * or redirect. The Struts global-forwards are expected to be stored
	 * under the <code>Action.FORWARDS_KEY</code>.
	 * The ActionForward to the login page should be named according to
	 * <code>GLOBAL_FORWARD_TO_LOGIN</code>.
	 * The original requested url is saved as request attribute under the
		 * key with name: <code>REFERRING_URL_KEY</code>.
		 * @throws JspTagException
		 * 		1. when no ActionForwards
	 * 		can be found under the <code>Action.FORWARDS_KEY</code>.
	 * 		2. when executing the <code>ActionForward </code> fails.
	 */
	public int doEndTag() throws JspTagException
	{
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();

		if (request.getSession() != null && request.getSession().getAttribute(LOGIN_PRINCIPAL) != null)
		{
			return EVAL_PAGE;
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			errors.add("no_login", new ActionError("global.login.notloggedin"));
			request.setAttribute(Globals.ERROR_KEY, errors);

			ActionForward forwardToLogin = StrutsNavigation.lookupForward( GLOBAL_FORWARD_TO_LOGIN, pageContext );
			if (forwardToLogin == null)
			{
				throw new JspTagException(NO_GLOBAL_FORWARD_TO_LOGIN_FOUND_ERRMSG);
			}

			try
			{
				StrutsNavigation.performActionForward(request, response, pageContext, forwardToLogin);
			}
			catch (IOException e)
			{
				wrapInJspException(e);
			}
			catch (ServletException e)
			{
				wrapInJspException(e);
			}
			return SKIP_PAGE;
		}
	}

	/**
	 * Exception handling method. stores the original error
	 * like Struts does and throws a new <code>JspException</code>.
	 * Struts saves original error messages under the Action.EXCEPTION_KEY
	 * in the request.
	 * @param e		the exception to handle
	 */
	private JspTagException wrapInJspException(Exception e)
	{
		log.debug(e.getMessage(), e);
		RequestUtils.saveException(pageContext, e);
		return new JspTagException(e.getMessage());
	}
}