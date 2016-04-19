package be.vlaanderen.sbs.s6.framework.utils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionForwards;

/**
 * @author	Jan Van Lysebeth
 */
public class StrutsNavigation
{
	/**
	 * Logger instance for performing logging.
	 */
	private static Log log = LogFactory.
		getLog("be.vlaanderen.sbs.s6.framework.utils.StrutsNavigation");

	/**
	 * Looks up a forward in the Struts global-forwards.
	 * @param forwardName the name of the forward to look up.
	 * @return the associated ActionForwar
	 * @throws JspTagException when no ActionForwards
	 * 		can be found under the <code>Action.FORWARDS_KEY</code>
	 */
	public static ActionForward lookupForward(String forwardName,
		PageContext pageContext)
		throws JspTagException
	{
		ActionForwards forwards =
			(ActionForwards) pageContext.getAttribute(
				Action.FORWARDS_KEY,
				PageContext.APPLICATION_SCOPE);

		if (forwards != null)
		{
			return forwards.findForward(forwardName);
		}
		else
		{
			throw new JspTagException(NO_FORWARDS_ERRMSG);
		}
	}
	/**
	 * Executes the <code>ActionForward</code>. If the <code>forward</code>'s
	 * redirect attribute is true, then the request will be redirected.
	 * Otherwise a forward will be performed.
	 * @param request
	 * @param response
	 * @param forward
	 * @throws IOException
	 * @throws ServletException
	 */
	public static void performActionForward(
		HttpServletRequest request,
		HttpServletResponse response,
		PageContext pageContext,
		ActionForward forward) throws IOException, ServletException
	{
		String path = forward.getPath();
		if (forward.getRedirect())
		{
			performRedirect(request, response, path);
		}
		else
		{
			performForward(path, pageContext);
		}
	}
	/**
	 * Forwards this request to <code>path</code>.
	 * @param path may be relative as well as context-relative.
	 * @throws ServletException when forward fails
	 * @throws IOException when forward fails
	 */
	private static void performForward(String path, PageContext pageContext)
		throws ServletException, IOException
	{
		log.debug("Forwarding to " + path);
		pageContext.forward(path);
	}
	/**
	 * Sends a redirect for the <code>path </code> URL.
	 * @param request
	 * @param response
	 * @param path	may be relative as well as context-relative.
	 * @throws IOException when input output on the <code>path</code>
	 *  fails.
	 */
	private static void performRedirect( HttpServletRequest request,
		HttpServletResponse response, String path) throws IOException
	{
		if (path.startsWith("/"))
			path = request.getContextPath() + path;
		log.debug("Redirecting to " + path);
		response.sendRedirect(response.encodeRedirectURL(path));
	}

	/**
	 * The error message of the <code>IllegalStateException</code> when no
	 * <code>ActionForwards</code> object is found under the
	 * <code>Action.FORWARDS_KEY</code>.
	 */
	private final static String NO_FORWARDS_ERRMSG =
		"Can't find struts global forwards under the PageContext"
			+ " attribute: "
			+ Action.FORWARDS_KEY;
}
