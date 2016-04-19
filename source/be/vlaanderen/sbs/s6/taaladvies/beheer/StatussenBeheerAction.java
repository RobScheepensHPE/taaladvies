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
import be.vlaanderen.sbs.s6.taaladvies.Queries;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;

public class StatussenBeheerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean check = false;
		StatussenBeheerForm referenceForm = (StatussenBeheerForm)form;
		ParameterActief status = (ParameterActief)referenceForm.getStatus();
		int id = status.getId();

		if (request.getParameter("Wijzigen.x") != null) {
			/*check = */ParameterActief.update(Queries.UPDATE_STATUS, status);
			return (new ActionForward("/do/initStatussenBeheer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Toevoegen.x") != null) {
			int max = ParameterActief.findMax(Queries.MAX_STATUS) + 1;
			status.setId(max);
			id = ParameterActief.insertWithId(Queries.INSERT_STATUS, status);
			return (new ActionForward("/do/initStatussenBeheer?id=" + max + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		return (mapping.findForward("backtopage"));

	}

}

