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
import be.vlaanderen.sbs.s6.taaladvies.model.DoelgroepAlgemeen;

public class DoelgroepenAlgemeenBeheerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean check = false;
		DoelgroepenAlgemeenBeheerForm referenceForm = (DoelgroepenAlgemeenBeheerForm)form;
		DoelgroepAlgemeen doelgroepAlgemeen = referenceForm.getDoelgroepAlgemeen();
		int id = doelgroepAlgemeen.getId();

		if (request.getParameter("Wijzigen.x") != null) {
			/*check = */DoelgroepAlgemeen.update(doelgroepAlgemeen);
			return (new ActionForward("/do/initDoelgroepenAlgemeenBeheer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Toevoegen.x") != null) {
			id = DoelgroepAlgemeen.insert(doelgroepAlgemeen);
			return (new ActionForward("/do/initDoelgroepenAlgemeenBeheer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Button").equals("Change1")) {
			java.util.ArrayList<DoelgroepAlgemeen> doelgroepenAlgemeen = DoelgroepAlgemeen.findAllByParentActief(doelgroepAlgemeen.getDomein().getId());
			referenceForm.setDoelgroepenAlgemeen(doelgroepenAlgemeen);
			session.setAttribute("DoelgroepenAlgemeenBeheerForm", referenceForm);
			return(mapping.findForward("success"));
		}

		if (request.getParameter("Button").equals("Change2")) {
			doelgroepAlgemeen = (DoelgroepAlgemeen)DoelgroepAlgemeen.findByPK(doelgroepAlgemeen.getId());
			referenceForm.setDoelgroepAlgemeen(doelgroepAlgemeen);
			session.setAttribute("DoelgroepenAlgemeenBeheerForm", referenceForm);
			return(mapping.findForward("success"));
		}

		return (mapping.findForward("backtopage"));

	}

}

