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
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.model.Webreferentie;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;

public class WebreferentiesInvoerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean /*check = */false;
		WebreferentiesInvoerForm referenceForm = (WebreferentiesInvoerForm)form;
		Webreferentie webreferentie = referenceForm.getWebreferentie();
		int id = webreferentie.getId();
		int type = 0;
		int taalvraagId = 0;
		int tekstId = 0;
		int changeId = 0;
		int oproepId = 0;

		if(session.getAttribute("eopro") == null ||(session.getAttribute("eopro").equals("false"))){
		    formatHTMLVelden(webreferentie);
		}

		if (referenceForm.getTaalvraag() != null) {
			type = 1;
			taalvraagId = referenceForm.getTaalvraag().getId();
			webreferentie.setTaalvraagId(taalvraagId);
			changeId = taalvraagId;
		}
		if (referenceForm.getTekst() != null) {
			type = 2;
			Tekst tekst = referenceForm.getTekst();
			tekstId = tekst.getId();
			webreferentie.setTekstId(tekstId);
			changeId = tekstId;
			oproepId = tekst.getOproepId();
		}

		if (request.getParameter("Overzicht Update.x") != null) {
			/*check = */Webreferentie.update(webreferentie);
			//java.util.ArrayList webreferenties = Webreferentie.findAllByParent(taalvraagId, tekstId);
			return (new ActionForward("/do/initWebreferentiesInvoer?id=" + changeId + "&type=" + type + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Overzicht Insert.x") != null) {
			id = Webreferentie.insert(webreferentie);
			return (new ActionForward("/do/initWebreferentiesInvoer?id=" + changeId + "&type=" + type + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Wijzigen.x") != null) {
			/*check = */Webreferentie.update(webreferentie);
			//java.util.ArrayList webreferenties = Webreferentie.findAllByParent(taalvraagId, tekstId);
			if (type==1) 	return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			else			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Toevoegen.x") != null) {
			id = Webreferentie.insert(webreferentie);
			if (type==1) 	return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			else			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Annuleren.x") != null) {
			if (type==1) 	return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			else			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Button").equals("Verwijder")) {
			id = Integer.parseInt(request.getParameter("id"));
			webreferentie.setId(id);
			/*check = */Webreferentie.delete(webreferentie);
			java.util.ArrayList<Webreferentie> webreferenties = Webreferentie.findAllByParent(taalvraagId, tekstId);
			referenceForm.setWebreferenties(webreferenties);
			referenceForm.setWebreferentie(new Webreferentie());
			session.setAttribute("WebreferentiesInvoerForm", referenceForm);
			return(mapping.findForward("success"));
		}

		if (request.getParameter("Button").equals("Wijzig")) {
			id = Integer.parseInt(request.getParameter("id"));
			webreferentie = Webreferentie.findByPK(id);
			referenceForm.setWebreferentie(webreferentie);
	  		session.setAttribute("WebreferentiesInvoerForm", referenceForm);
			return(mapping.findForward("success"));
		}


		return (mapping.findForward("backtopage"));

	}

    /**
     * @param webreferentie de te formateren webreferentie
     */
    private void formatHTMLVelden(Webreferentie webreferentie) {
        webreferentie.setToelichtingHTML(Util.formatNewLinesToBr(webreferentie.getToelichtingHTML()));
    }

}

