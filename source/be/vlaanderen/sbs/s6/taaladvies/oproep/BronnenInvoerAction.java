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
import be.vlaanderen.sbs.s6.taaladvies.model.Bron;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;

public class BronnenInvoerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean check = false;
		BronnenInvoerForm referenceForm = (BronnenInvoerForm)form;
		Bron bron = referenceForm.getBron();
		int id = bron.getId();
		int type = 0;
		int taalvraagId = 0;
		int tekstId = 0;
		int changeId = 0;
		int oproepId = 0;

		if(session.getAttribute("eopro") == null ||(session.getAttribute("eopro").equals("false"))){
		    formatHTMLVelden(bron);
		}

		if (referenceForm.getTaalvraag() != null) {
			type = 1;
			taalvraagId = referenceForm.getTaalvraag().getId();
			bron.setTaalvraagId(taalvraagId);
			changeId = taalvraagId;
		}
		if (referenceForm.getTekst() != null) {
			type = 2;
			Tekst tekst = referenceForm.getTekst();
			tekstId = tekst.getId();
			bron.setTekstId(tekstId);
			changeId = tekstId;
			oproepId = tekst.getOproepId();
		}

		if (request.getParameter("Overzicht Update.x") != null) {
			/*check = */Bron.update(bron);
			//java.util.ArrayList bronnen = Bron.findAllByParent(taalvraagId, tekstId);
			return (new ActionForward("/do/initBronnenInvoer?id=" + changeId + "&type=" + type + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Overzicht Insert.x") != null) {
			id = Bron.insert(bron);
			return (new ActionForward("/do/initBronnenInvoer?id=" + changeId + "&type=" + type + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Wijzigen.x") != null) {
			/*check = */Bron.update(bron);
			//java.util.ArrayList bronnen = Bron.findAllByParent(taalvraagId, tekstId);
			if (type==1) 	return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			else			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Toevoegen.x") != null) {
			id = Bron.insert(bron);
			if (type==1) 	return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			else			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Annuleren.x") != null) {
			if (type==1) 	return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			else			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Button").equals("Verwijder")) {
			id = Integer.parseInt(request.getParameter("id"));
			bron.setId(id);
			/*check = */Bron.delete(bron);
			java.util.ArrayList<Bron> bronnen = Bron.findAllByParent(taalvraagId, tekstId);
			referenceForm.setBronnen(bronnen);
			referenceForm.setBron(new Bron());
			session.setAttribute("BronnenInvoerForm", referenceForm);
			return(mapping.findForward("success"));
		}

		if (request.getParameter("Button").equals("Wijzig")) {
			id = Integer.parseInt(request.getParameter("id"));
			bron = Bron.findByPK(id);
			referenceForm.setBron(bron);
	  		session.setAttribute("BronnenInvoerForm", referenceForm);
			return(mapping.findForward("success"));
		}

		return (mapping.findForward("backtopage"));

	}

    /**
     * @param bron de bron waarvan de velden moeten worden aangepast
     */
    private void formatHTMLVelden(Bron bron) {
        bron.setCitaatHTML(Util.formatNewLinesToBr(bron.getCitaatHTML()));
        bron.setTitelHTML(Util.formatNewLinesToBr(bron.getTitelHTML()));
    }

}

