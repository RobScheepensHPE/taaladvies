package be.vlaanderen.sbs.s6.taaladvies.overzicht;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.BaseAction;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;

public class RaadplegerReadAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
			
		HttpSession session = request.getSession();
		
		RaadplegerReadForm referenceform = new RaadplegerReadForm();

		if (request.getParameter("type").equals("1")) {
			Taalvraag taalvraag = Taalvraag.findByPK(Integer.parseInt(request.getParameter("id")));
			referenceform.setTaalvraag(taalvraag);
		}
		else {
			Tekst tekst = Tekst.findByPK(Integer.parseInt(request.getParameter("id")));
			referenceform.setTekst(tekst);
		}
		 		
  		session.setAttribute("RaadplegerReadForm", referenceform);
		
		return(mapping.findForward("success"));
		
	}

}
