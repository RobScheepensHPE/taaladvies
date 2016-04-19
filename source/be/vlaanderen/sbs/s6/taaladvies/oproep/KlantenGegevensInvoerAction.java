package be.vlaanderen.sbs.s6.taaladvies.oproep;

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
import be.vlaanderen.sbs.s6.taaladvies.model.Gemeente;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;

public class KlantenGegevensInvoerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean check = false;
		KlantenGegevensInvoerForm referenceForm = (KlantenGegevensInvoerForm)form;
		Oproep oproep = referenceForm.getOproep();
		int id = oproep.getId();
		//Gebruiker gebruiker = (Gebruiker)session.getAttribute("Gebruiker");

		if (request.getParameter("Zoek Gemeente.x") != null) {

			Gemeente gemeente = oproep.getGemeente();
			//int gemeenteId = gemeente.getId();
			if (!gemeente.getLand().getOmschrijving().trim().equals("Belgie") && !gemeente.getLand().getOmschrijving().trim().equals("België")) {
				if ((Gemeente.findBySearch(gemeente)).size() == 0) {
					/*gemeenteId = */Gemeente.insert(gemeente);
				}
			}
			java.util.ArrayList<Gemeente> gemeenten = Gemeente.findBySearch(gemeente);
			if (gemeenten.size() != 0) {
				oproep.setGemeenteId(((Gemeente)gemeenten.get(0)).getId());
				referenceForm.setOproep(oproep);
			}
			referenceForm.setGemeenten(gemeenten);
			session.setAttribute("KlantenGegevensInvoerForm", referenceForm);
			return (mapping.findForward("success"));
		}

		if (request.getParameter("Wijzigen.x") != null) {
			/*check = */Oproep.updateKlantenGegevens(oproep);

			return (new ActionForward("/do/initKlantenGegevensInvoer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Annuleren.x") != null) {
			if (oproep.getType() == 1) 	return (new ActionForward("/do/initOproep_TaalvragenInvoer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			else						return (new ActionForward("/do/initOproep_TekstInvoer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Button").equals("Overzicht")) {
			/*check = */Oproep.updateKlantenGegevens(oproep);

			if (oproep.getType() == 1) 	return (new ActionForward("/do/initOproep_TaalvragenInvoer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			else						return (new ActionForward("/do/initOproep_TekstInvoer?id=" + id + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Button").equals("Change2")) {
			id = oproep.getDoelgroep().getParentId();
			if (id != 0) {
				java.util.ArrayList<Doelgroep> doelgroepen = Doelgroep.findAllByParentActief(id);
				referenceForm.setDoelgroepen(doelgroepen);
				session.setAttribute("KlantenGegevensInvoerForm", referenceForm);
			}
			return(mapping.findForward("success"));
		}

		return (mapping.findForward("blabla"));

	}


}

