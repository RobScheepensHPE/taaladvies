package be.vlaanderen.sbs.s6.taaladvies;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public abstract class BaseAction2 extends Action
{
	protected abstract ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException;

	public ActionForward perform(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException
	{
		//AppLogger.getInstance().debug("BaseAction2.perform invoked by instance of " + this.getClass().getName());

	    if(!isTokenValid(request))
        {
            // De gebruiker is via back naar een vorige pagina gegaan -> Flow = verbroken
            return (mapping.findForward("error"));
        }

		saveToken(request);

		return performAction(mapping,form,request,response);
	}

	protected boolean isTokenValid(HttpServletRequest request) {

		return true;
/*
	    // Retrieve the saved transaction token from ourx
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
*/
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
}