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
import be.vlaanderen.sbs.s6.taaladvies.Queries;
import be.vlaanderen.sbs.s6.taaladvies.model.Naslagreferentie;
import be.vlaanderen.sbs.s6.taaladvies.model.Naslagwerk;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;

public class NaslagreferentiesInvoerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean check = false;
		NaslagreferentiesInvoerForm referenceForm = (NaslagreferentiesInvoerForm)form;
		Naslagreferentie naslagreferentie = referenceForm.getNaslagreferentie();
		int id = naslagreferentie.getId();
		int type = 0;
		int taalvraagId = 0;
		int tekstId = 0;
		int changeId = 0;
		int oproepId = 0;

		if(session.getAttribute("eopro") == null ||(session.getAttribute("eopro").equals("false"))){
		    formatHTMLVelden(naslagreferentie);
		}

		if (referenceForm.getTaalvraag() != null) {
			type = 1;
			taalvraagId = referenceForm.getTaalvraag().getId();
			naslagreferentie.setTaalvraagId(taalvraagId);
			changeId = taalvraagId;
		}
		if (referenceForm.getTekst() != null) {
			type = 2;
			Tekst tekst = referenceForm.getTekst();
			tekstId = tekst.getId();
			naslagreferentie.setTekstId(tekstId);
			changeId = tekstId;
			oproepId = tekst.getOproepId();
		}

		if (request.getParameter("Overzicht Update.x") != null) {
			/*check = */Naslagreferentie.update(naslagreferentie);
			//java.util.ArrayList naslagreferenties = Naslagreferentie.findAllByParent(taalvraagId, tekstId);
			return (new ActionForward("/do/initNaslagreferentiesInvoer?id=" + changeId + "&type=" + type + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Overzicht Insert.x") != null) {
			id = Naslagreferentie.insert(naslagreferentie);
			return (new ActionForward("/do/initNaslagreferentiesInvoer?id=" + changeId + "&type=" + type + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Wijzigen.x") != null) {
			/*check = */Naslagreferentie.update(naslagreferentie);
			//java.util.ArrayList naslagreferenties = Naslagreferentie.findAllByParent(taalvraagId, tekstId);
			if (type==1) 	return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			else			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Toevoegen.x") != null) {
			id = Naslagreferentie.insert(naslagreferentie);
			if (type==1) 	return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			else			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Annuleren.x") != null) {
			if (type==1) 	return (new ActionForward("/do/initTaalvragenInvoer?id=" + changeId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
			else			return (new ActionForward("/do/initOproep_TekstInvoer?id=" + oproepId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Button").equals("Change1")) {
			java.util.ArrayList<Naslagwerk> naslagwerken = new java.util.ArrayList<Naslagwerk>();
			if (naslagreferentie.getNaslagwerk().getParentId() != 0) {
				naslagwerken = Naslagwerk.findAllByParentActief(naslagreferentie.getNaslagwerk().getParentId());
			}
			else {
				naslagwerken = Naslagwerk.findAllActief();
			}
			referenceForm.setNaslagwerken(naslagwerken);
			referenceForm.setNaslagreferentie(naslagreferentie);
			session.setAttribute("NaslagreferentiesInvoerForm", referenceForm);
			return(mapping.findForward("success"));
		}

		if (request.getParameter("Button").equals("Change2")) {
			Naslagwerk naslagwerk = (Naslagwerk)Naslagwerk.findByPK(naslagreferentie.getNaslagwerkId());
			naslagreferentie.setNaslagwerk(naslagwerk);
			referenceForm.setNaslagreferentie(naslagreferentie);
			session.setAttribute("NaslagreferentiesInvoerForm", referenceForm);
			return(mapping.findForward("success"));
		}

		if (request.getParameter("Button").equals("Verwijder")) {
			id = Integer.parseInt(request.getParameter("id"));
			naslagreferentie.setId(id);
			/*check = */Naslagreferentie.delete(naslagreferentie);
			java.util.ArrayList<Naslagreferentie> naslagreferenties = Naslagreferentie.findAllByParent(taalvraagId, tekstId);



			java.util.ArrayList<ParameterActief> bibliografien = ParameterActief.findAllActief(Queries.ALL_BIBLIOGRAFIEN_ACTIEF);

			java.util.ArrayList<ParameterActief> bibliografien_sorted = new java.util.ArrayList<ParameterActief>();
			int basiswerkenId = 0;
			for (int i = 0, j = bibliografien.size(); i < j; i++) {
				ParameterActief bibliografie = (ParameterActief)bibliografien.get(i);
				if (bibliografie.getOmschrijving().equals("Basiswerken")) {
					bibliografien_sorted.add(0, bibliografie);
					basiswerkenId = bibliografie.getId();
				}
				else {
					bibliografien_sorted.add(bibliografie);
				}
			}
			naslagreferentie = new Naslagreferentie();
			naslagreferentie.getNaslagwerk().setParentId(basiswerkenId);
			java.util.ArrayList<Naslagwerk> naslagwerken = Naslagwerk.findAllByParentActief(basiswerkenId);


			referenceForm.setNaslagwerken(naslagwerken);
			referenceForm.setBibliografien(bibliografien_sorted);




			referenceForm.setNaslagreferenties(naslagreferenties);
			referenceForm.setNaslagreferentie(naslagreferentie);
			session.setAttribute("NaslagreferentiesInvoerForm", referenceForm);
			return(mapping.findForward("success"));
		}

		if (request.getParameter("Button").equals("Wijzig")) {
			id = Integer.parseInt(request.getParameter("id"));
			naslagreferentie = Naslagreferentie.findByPK(id);
			referenceForm.setNaslagwerken(Naslagwerk.findAllActief());
			referenceForm.setNaslagreferentie(naslagreferentie);
	  		session.setAttribute("NaslagreferentiesInvoerForm", referenceForm);
			return(mapping.findForward("success"));
		}

		return (mapping.findForward("backtopage"));

	}

    /**
     * @param naslagreferentie de te formateren naslagreferentie
     */
    private void formatHTMLVelden(Naslagreferentie naslagreferentie) {
       naslagreferentie.setCitaatHTML(Util.formatNewLinesToBr(naslagreferentie.getCitaatHTML()));
    }

}

