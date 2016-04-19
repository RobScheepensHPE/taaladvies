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
import be.vlaanderen.sbs.s6.taaladvies.model.DoelgroepAlgemeen;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterBase;

public class InitDoelgroepenAlgemeenBeheerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
			
		HttpSession session = request.getSession();
		
		DoelgroepenAlgemeenBeheerForm referenceform = new DoelgroepenAlgemeenBeheerForm();
		if (request.getParameter("id") != null) {
			if (!request.getParameter("id").equals("0")) {
				referenceform.setDoelgroepAlgemeen((DoelgroepAlgemeen)DoelgroepAlgemeen.findByPK(Integer.parseInt(request.getParameter("id"))));
			}
		}
		
		java.util.ArrayList<DoelgroepAlgemeen> doelgroepenAlgemeen = DoelgroepAlgemeen.findAll();
		java.util.ArrayList<ParameterBase> domeinen = ParameterBase.findAll(Queries.ALL_DOMEINEN);
		
		referenceform.setDoelgroepenAlgemeen(doelgroepenAlgemeen);
  		referenceform.setDomeinen(domeinen);
  		
  		session.setAttribute("DoelgroepenAlgemeenBeheerForm", referenceform);
		
		
		return(mapping.findForward("success"));
		
	}

}

