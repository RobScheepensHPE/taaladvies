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
import be.vlaanderen.sbs.s6.taaladvies.model.Notitie;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;

public class NotitiesInvoerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean /*check = */false;
		NotitiesInvoerForm referenceForm = (NotitiesInvoerForm)form;
		Notitie notitie = referenceForm.getNotitie();
		//int id = notitie.getId();
		int type = 0;
		int taalvraagId = 0;
		int tekstId = 0;
		int changeId = 0;
		int oproepId = 0;

		if(session.getAttribute("eopro") == null ||(session.getAttribute("eopro").equals("false"))){
		    formatHTMLVelden(notitie);
		}

		if (referenceForm.getTaalvraag() != null) {
			type = 1;
			taalvraagId = referenceForm.getTaalvraag().getId();
			notitie.setTaalvraagId(taalvraagId);
			changeId = taalvraagId;
		}
		if (referenceForm.getTekst() != null) {
			type = 2;
			Tekst tekst = referenceForm.getTekst();
			tekstId = tekst.getId();
			notitie.setTekstId(tekstId);
			changeId = tekstId;
			oproepId = tekst.getOproepId();
		}

/*			WIJZIGEN VAN NOTITIES BLEEK NIET MEER NODIG!

			if (request.getParameter("Button").equals("Wijzig")) {
			id = Integer.parseInt(request.getParameter("id"));
			notitie = Notitie.findByPK(id);
			referenceForm.setNotitie(notitie);
	  		session.setAttribute("NotitiesInvoerForm", referenceForm);
			if (request.getParameter("ro") != null) {
				return(mapping.findForward("readonly"));
			}
			else {
				return(mapping.findForward("success"));
			}
		}

		if (request.getParameter("Button").equals("Wijzigen")) {
			check = Notitie.update(notitie);
			java.util.ArrayList notities = Notitie.findAllByParent(taalvraagId, tekstId);
			if (request.getParameter("ro") != null) {
				return (new ActionForward("/do/initNotitiesInvoer?id=" + changeId + "&ro=true&type=" + type + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			}
			else {
				return (new ActionForward("/do/initNotitiesInvoer?id=" + changeId + "&type=" + type + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			}
		}
*/

		if (request.getParameter("Overzicht.x") != null) {

			if (request.getParameter("ro") != null) {
				/*id = */Notitie.insert(notitie);

				if (referenceForm.getAdm()) {
					return (new ActionForward("/do/initNotitiesInvoer?id=" + changeId + "&ro=true&adm=true&type=" + type + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
				}
				else {
					return (new ActionForward("/do/initNotitiesInvoer?id=" + changeId + "&ro=true&type=" + type + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
				}
			}
			else {
				if (referenceForm.getNotities().size() != 0) {
					/*check = */Notitie.update(notitie);
				}
				else {
					/*id = */Notitie.insert(notitie);
				}

				return (new ActionForward("/do/initNotitiesInvoer?id=" + changeId + "&type=" + type + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			}
		}

		if (request.getParameter("Annuleren.x") != null) {
			if (request.getParameter("ro") != null) {
				if (type==1) 	return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&ro=true&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
				else			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&ro=true&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			}
			else {
				if (type==1) 	return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
				else			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			}
		}

		if (request.getParameter("Toevoegen.x") != null) {


			if (request.getParameter("ro") != null) {
				/*id = */Notitie.insert(notitie);

				if (type==1) {
					if (referenceForm.getAdm()) {
						return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&ro=true&adm=true&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
					}
					else {
						return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&ro=true&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
					}
				}
				else {
					return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&ro=true&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
				}
			}
			else {
				if (referenceForm.getNotities().size() != 0) {
					/*check = */Notitie.update(notitie);
				}
				else {
					/*id = */Notitie.insert(notitie);
				}
				if (type==1) 	return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
				else			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			}
		}

		if (request.getParameter("Button") != null) {
			if (referenceForm.getNotities().size() != 0) {
				/*check = */Notitie.update(notitie);
			}
			else {
				/*id = */Notitie.insert(notitie);
			}
			if (type==1) 	return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			else			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		return (mapping.findForward("backtopage"));

	}

    /**
     * vervangt de new lines in dit veld door br - html tags in niet-eopro editeer modus
     *
     * @param notitie de te formateren notitie
     */
    private void formatHTMLVelden(Notitie notitie) {
        notitie.setNotitieHTML(Util.formatNewLinesToBr(notitie.getNotitieHTML()));
    }

}

