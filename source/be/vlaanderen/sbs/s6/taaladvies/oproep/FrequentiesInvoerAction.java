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
import be.vlaanderen.sbs.s6.taaladvies.model.Frequentie;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;

public class FrequentiesInvoerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean /*check = */false;
		FrequentiesInvoerForm referenceForm = (FrequentiesInvoerForm)form;
		Frequentie frequentie = referenceForm.getFrequentie();
		int id = frequentie.getId();
		int type = 0;
		int taalvraagId = 0;
		int tekstId = 0;
		int changeId = 0;
		int oproepId = 0;

		if (referenceForm.getTaalvraag() != null) {
			type = 1;
			taalvraagId = referenceForm.getTaalvraag().getId();
			frequentie.setTaalvraagId(taalvraagId);
			changeId = taalvraagId;
		}
		if (referenceForm.getTekst() != null) {
			type = 2;
			Tekst tekst = referenceForm.getTekst();
			tekstId = tekst.getId();
			frequentie.setTekstId(tekstId);
			changeId = tekstId;
			oproepId = tekst.getOproepId();
		}

		if (request.getParameter("Overzicht Update.x") != null) {
			/*check = */Frequentie.update(frequentie);
			//java.util.ArrayList frequenties = Frequentie.findAllByParent(taalvraagId, tekstId);
			return (new ActionForward("/do/initFrequentiesInvoer?id=" + changeId + "&type=" + type + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Overzicht Insert.x") != null) {
			id = Frequentie.insert(frequentie);
			return (new ActionForward("/do/initFrequentiesInvoer?id=" + changeId + "&type=" + type + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Wijzigen.x") != null) {
			/*check = */Frequentie.update(frequentie);
			//java.util.ArrayList frequenties = Frequentie.findAllByParent(taalvraagId, tekstId);
			if (type==1) 	return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			else			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Toevoegen.x") != null) {
			id = Frequentie.insert(frequentie);
			if (type==1) 	return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			else			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Annuleren.x") != null) {
			if (type==1) 	return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			else			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Button").equals("Verwijder")) {
			id = Integer.parseInt(request.getParameter("id"));
			frequentie.setId(id);
			/*check = */Frequentie.delete(frequentie);
			java.util.ArrayList<Frequentie> frequenties = Frequentie.findAllByParent(taalvraagId, tekstId);
			referenceForm.setFrequenties(frequenties);
			referenceForm.setFrequentie(new Frequentie());
			session.setAttribute("FrequentiesInvoerForm", referenceForm);
			return(mapping.findForward("success"));
		}

		if (request.getParameter("Button").equals("Wijzig")) {
			id = Integer.parseInt(request.getParameter("id"));
			frequentie = Frequentie.findByPK(id);
			referenceForm.setFrequentie(frequentie);
	  		session.setAttribute("FrequentiesInvoerForm", referenceForm);
			return(mapping.findForward("success"));
		}



		return (mapping.findForward("success"));

	}

}

