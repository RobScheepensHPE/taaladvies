package be.vlaanderen.sbs.s6.taaladvies.oproep;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.BaseAction;
import be.vlaanderen.sbs.s6.taaladvies.Queries;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;

public class InitKenmerkenInvoerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
			
		HttpSession session = request.getSession();
		
		KenmerkenInvoerForm referenceform = new KenmerkenInvoerForm();

		if (request.getParameter("adm") != null) {
			referenceform.setAdm(true);
		}

		int id = Integer.parseInt(request.getParameter("id"));
		
		Taalvraag taalvraag = Taalvraag.findByPK(id);
		java.util.ArrayList<ParameterActief> relevanties = ParameterActief.findAllActief(Queries.ALL_RELEVANTIES_ACTIEF);
		
		referenceform.setTaalvraag(taalvraag);
		referenceform.setRelevanties(relevanties);
				
  		session.setAttribute("KenmerkenInvoerForm", referenceform);
		
		if (request.getParameter("ro") != null) {
			return(mapping.findForward("readonly"));
		}
		else {
			return(mapping.findForward("success"));
		}
		
	}

}

