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
import be.vlaanderen.sbs.s6.taaladvies.model.Citaat;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;

public class CitatenInvoerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean check = false;
		CitatenInvoerForm referenceForm = (CitatenInvoerForm)form;
		Citaat citaat = referenceForm.getCitaat();
		int id = citaat.getId();
		int type = 0;
		int taalvraagId = 0;
		int tekstId = 0;
		int changeId = 0;
		int oproepId = 0;

		if(session.getAttribute("eopro") == null ||(session.getAttribute("eopro").equals("false"))){
		    formatHTMLVelden(citaat);
		}

		if (referenceForm.getTaalvraag() != null) {
			type = 1;
			taalvraagId = referenceForm.getTaalvraag().getId();
			citaat.setTaalvraagId(taalvraagId);
			changeId = taalvraagId;
		}
		if (referenceForm.getTekst() != null) {
			type = 2;
			Tekst tekst = referenceForm.getTekst();
			tekstId = tekst.getId();
			citaat.setTekstId(tekstId);
			changeId = tekstId;
			oproepId = tekst.getOproepId();
		}

		if (request.getParameter("Overzicht Update.x") != null) {
			/*check = */Citaat.update(citaat);
			//java.util.ArrayList citaten = Citaat.findAllByParent(taalvraagId, tekstId);
			return (new ActionForward("/do/initCitatenInvoer?id=" + changeId + "&type=" + type + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Overzicht Insert.x") != null) {
			id = Citaat.insert(citaat);
			return (new ActionForward("/do/initCitatenInvoer?id=" + changeId + "&type=" + type + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Wijzigen.x") != null) {
			/*check = */Citaat.update(citaat);
			//java.util.ArrayList citaten = Citaat.findAllByParent(taalvraagId, tekstId);
			if (type==1) 	return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			else			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Toevoegen.x") != null) {
			id = Citaat.insert(citaat);
			if (type==1) 	return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			else			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Annuleren.x") != null) {
			if (type==1) 	return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			else			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Button").equals("Verwijder")) {
			id = Integer.parseInt(request.getParameter("id"));
			citaat.setId(id);
			/*check = */Citaat.delete(citaat);
			java.util.ArrayList<Citaat> citaten = Citaat.findAllByParent(taalvraagId, tekstId);
			referenceForm.setCitaten(citaten);
			referenceForm.setCitaat(new Citaat());
			session.setAttribute("CitatenInvoerForm", referenceForm);
			return(mapping.findForward("success"));
		}

		if (request.getParameter("Button").equals("Wijzig")) {
			id = Integer.parseInt(request.getParameter("id"));
			citaat = Citaat.findByPK(id);
			referenceForm.setCitaat(citaat);
	  		session.setAttribute("CitatenInvoerForm", referenceForm);
			return(mapping.findForward("success"));
		}


		return (mapping.findForward("backtopage"));

	}

	/**
     * vervangt de new lines in dit veld door br - html tags in niet-eopro editeer modus
     * @param taalvraag de te formateren taalvraag
     */
    private void formatHTMLVelden(Citaat citaat) {
       citaat.setCitaatHTML(Util.formatNewLinesToBr(citaat.getCitaatHTML()));
    }

}

