/*
 * Created on Mar 10, 2005
 */
package be.vlaanderen.sbs.s6.taaladvies.overzicht;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.framework.logging.AppLogger;
import be.vlaanderen.sbs.s6.taaladvies.BaseAction;

	
	/**
	 * @author nzvwbg
	 */
	public class EditeerModusAction extends BaseAction {
	    public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
			
		HttpSession session = request.getSession();
		
		EditeerModusForm referenceform = (EditeerModusForm) form;
		AppLogger.getInstance().error("forward URL: " + referenceform.getForwardURL());
		AppLogger.getInstance().error("current eopro attribute: " + session.getAttribute("eopro"));
		AppLogger.getInstance().error("eopro boolean received: " + referenceform.getUseEopro());
		
		session.setAttribute("eopro", Boolean.valueOf(referenceform.getUseEopro()));
		
		AppLogger.getInstance().error("new eopro attribute: " + session.getAttribute("eopro"));
		
		return(new ActionForward(referenceform.getForwardURL()));
		
	}

}
