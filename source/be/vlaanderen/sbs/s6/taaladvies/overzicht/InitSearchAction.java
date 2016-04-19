package be.vlaanderen.sbs.s6.taaladvies.overzicht;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.BaseAction;

public class InitSearchAction extends BaseAction {
	
	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException 
	{
			
		request.getSession().removeAttribute("cacheTLV");
		request.getSession().removeAttribute("cacheTXT");
		request.getSession().removeAttribute("SearchForm");	
		
		// since struts 1.1 reset is also called when the jsp creates a new form-object
		// when it not yet exists; this code simulates 1.0 behaviour (reset is not called
		// the first time)
		
		request.getSession().setAttribute("SearchForm", new SearchForm());
	
		return(mapping.findForward("success"));		
	}	
}

