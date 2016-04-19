package be.vlaanderen.sbs.s6.taaladvies.beheer;

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

public class InitRelevantiesBeheerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
			
		HttpSession session = request.getSession();
		
		RelevantiesBeheerForm referenceform = new RelevantiesBeheerForm();
		if (request.getParameter("id") != null) {
			if (!request.getParameter("id").equals("0")) {
				referenceform.setRelevantie((ParameterActief)ParameterActief.findByPK(Queries.RELEVANTIE_BY_PK, Integer.parseInt(request.getParameter("id"))));
			}
		}
		
		java.util.ArrayList<ParameterActief> relevanties = ParameterActief.findAll(Queries.ALL_RELEVANTIES);
		
		referenceform.setRelevanties(relevanties);
  		
  		session.setAttribute("RelevantiesBeheerForm", referenceform);
		
		
		return(mapping.findForward("success"));
		
	}

}

