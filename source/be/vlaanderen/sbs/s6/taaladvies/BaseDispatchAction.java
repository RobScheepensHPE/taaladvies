package be.vlaanderen.sbs.s6.taaladvies;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.Globals;
//import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.taglib.html.Constants;

public class BaseDispatchAction extends DispatchAction
{
	protected static final String SESSION_KEY_CACHE_TLV = "cacheTLV";
	protected static final String SESSION_KEY_CACHE_TXT = "cacheTXT";

	public ActionForward perform(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException
	{
		//AppLogger.getInstance().debug("BaseDispatchAction.perform invoked for instance of " + this.getClass().getName());

	    if(!isTokenValid(request))
        {
            // De gebruiker is via back naar een vorige pagina gegaan -> Flow = verbroken
            return (mapping.findForward("error"));
        }

		saveToken(request);

		try
		{
		    return super.execute(mapping,form,request,response);
		} catch (Exception e)
		{
		    throw new ServletException(e);
		}
	}

	protected boolean isTokenValid(HttpServletRequest request) {

//		return true;

	    // Retrieve the saved transaction token from our
	    // session
	    HttpSession session = request.getSession(false);
	    if (session == null)
	        return (true);
	    String saved = (String)
	        session.getAttribute(Globals.TRANSACTION_TOKEN_KEY);
	    if (saved == null)
	        return (true);
	    // Retrieve the transaction token included in this
	    // request
	    String token = (String)
	        request.getParameter(Constants.TOKEN_KEY);
	    if (token == null)
	        return (true);

	    // Do the values match?
	    return (saved.equals(token));

	}

	/**
	* Save a new transaction token in the
	* user's current session, creating
	* a new session if necessary.
	*
	* @param request The servlet request we are processing
	*/
	protected void saveToken(HttpServletRequest request) {

	HttpSession session = request.getSession();
	String token = generateToken(request);
	if (token != null)
	  	session.setAttribute(Globals.TRANSACTION_TOKEN_KEY, token);
	}



	// --------------------------------------------------------- Public Methods
	/**
	 * This method performs the following custom security:
	 * First we check if a userobject (@see java.util.principal) is available in the session.
	 * If not we send the user back to the login form.
	 * Second, if a identifier is specified, we check if the user is in the specified role
	 * If not, we send the user back to the input page
	 * else return back to the action and proceed.
	 *
	 * @param identifier the
	 *
	 */

	public ActionForward isUserInRole(String identifier, ActionMapping mapping, HttpServletRequest request)
	{
		//ActionErrors errors = new ActionErrors();
		ActionForward forward = null;

//		try
//		{
//			TAPrincipal TAPrincipal = (TAPrincipal) request.getSession().getAttribute("TAPrincipalSO");
//
//			if (TAPrincipal == null)
//			{
//				errors.add("usernotloggedin", new ActionError("user.error.usernotloggedin"));
//				saveErrors(request, errors);
//				forward = mapping.findForward("logon");
//			}
//
//			// validate the role only if one is specified
//			if (identifier != null)
//			{
//				//			if (!iAuthenticate.isPrincipalInRole(TAPrincipal, role))
//				if (!Validator.getInstance().ifAllowed(identifier, request))
//				{
//					errors.add("role", new ActionError("user.error.nopermission"));
//					saveErrors(request, errors);
//					forward = new ActionForward(mapping.getInput());
//				}
//			}
//		}
//		catch (Exception e)
//		{
//			AppLogger.getInstance().debug("EbbtBaseAction.isUserInRole(): " + e);
//		}

		return forward;
	}
}