package be.vlaanderen.sbs.s6.taaladvies.beheer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.taglib.html.Constants;
import be.vlaanderen.sbs.s6.taaladvies.BaseAction;
import be.vlaanderen.sbs.s6.taaladvies.model.Doelgroep;

public class DoelgroepenBeheerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean check = false;
		DoelgroepenBeheerForm referenceForm = (DoelgroepenBeheerForm)form;
		Doelgroep doelgroep = referenceForm.getDoelgroep();
		int id = doelgroep.getId();

		if (request.getParameter("Wijzigen.x") != null) {
			/*check = */Doelgroep.update(doelgroep);
			return (new ActionForward("/do/initDoelgroepenBeheer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Toevoegen.x") != null) {
			id = Doelgroep.insert(doelgroep);
			return (new ActionForward("/do/initDoelgroepenBeheer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Button").equals("Change1")) {
			java.util.ArrayList<Doelgroep> doelgroepen = Doelgroep.findAllByParentActief(doelgroep.getDoelgroepAlgemeen().getId());
			referenceForm.setDoelgroepen(doelgroepen);
			session.setAttribute("DoelgroepenBeheerForm", referenceForm);
			return(mapping.findForward("success"));
		}

		if (request.getParameter("Button").equals("Change2")) {
			doelgroep = (Doelgroep)Doelgroep.findByPK(doelgroep.getId());
			referenceForm.setDoelgroep(doelgroep);
			session.setAttribute("DoelgroepenBeheerForm", referenceForm);
			return(mapping.findForward("success"));
		}

		return (mapping.findForward("backtopage"));

	}

}

