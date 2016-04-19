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
import be.vlaanderen.sbs.s6.taaladvies.model.Gebruiker;
import be.vlaanderen.sbs.s6.taaladvies.model.Toegangsrecht;

public class GebruikersBeheerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean /*check = */false;
		GebruikersBeheerForm referenceForm = (GebruikersBeheerForm)form;
		Gebruiker gebruiker = referenceForm.getGebruiker();
		int id = gebruiker.getId();

		if (request.getParameter("Wijzigen.x") != null) {
			/*check = */Gebruiker.update(gebruiker);
			/*check = */Toegangsrecht.deleteByGebruiker(gebruiker);
			Toegangsrecht.insertByGebruiker(gebruiker);
			return (new ActionForward("/do/initGebruikersBeheer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Toevoegen.x") != null) {
			id = Gebruiker.insert(gebruiker);
			gebruiker.setId(id);
			Toegangsrecht.insertByGebruiker(gebruiker);
			return (new ActionForward("/do/initGebruikersBeheer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		return (mapping.findForward("backtopage"));

	}

}

