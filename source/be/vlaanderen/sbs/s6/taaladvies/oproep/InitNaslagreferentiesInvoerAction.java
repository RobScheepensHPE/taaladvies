package be.vlaanderen.sbs.s6.taaladvies.oproep;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.BaseAction;
import be.vlaanderen.sbs.s6.taaladvies.Queries;
import be.vlaanderen.sbs.s6.taaladvies.model.Naslagreferentie;
import be.vlaanderen.sbs.s6.taaladvies.model.Naslagwerk;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;

public class InitNaslagreferentiesInvoerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		NaslagreferentiesInvoerForm referenceform = new NaslagreferentiesInvoerForm();

		if (request.getParameter("adm") != null) {
			referenceform.setAdm(true);
		}

		Naslagreferentie naslagreferentie = new Naslagreferentie();
		int taalvraagId = 0;
		int tekstId = 0;
		int id = Integer.parseInt(request.getParameter("id"));
		int type = Integer.parseInt(request.getParameter("type"));
		if (type == 1) {
			taalvraagId = id;
			Taalvraag taalvraag = Taalvraag.findByPK(id);
			referenceform.setTaalvraag(taalvraag);
		}
		else {
			tekstId = id;
			Tekst tekst = Tekst.findByPK(id);
			referenceform.setTekst(tekst);
		}

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
		naslagreferentie.getNaslagwerk().setParentId(basiswerkenId);
		java.util.ArrayList<Naslagwerk> naslagwerken = Naslagwerk.findAllByParentActief(basiswerkenId);

		referenceform.setNaslagreferentie(naslagreferentie);
		referenceform.setNaslagreferenties(naslagreferenties);
		referenceform.setNaslagwerken(naslagwerken);
		referenceform.setBibliografien(bibliografien_sorted);

  		session.setAttribute("NaslagreferentiesInvoerForm", referenceform);

		if (request.getParameter("ro") != null) {
			return(mapping.findForward("readonly"));
		}
		else {
			return(mapping.findForward("success"));
		}

	}

}

