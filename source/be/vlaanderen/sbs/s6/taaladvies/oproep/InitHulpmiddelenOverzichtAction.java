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
import be.vlaanderen.sbs.s6.taaladvies.model.Bron;
import be.vlaanderen.sbs.s6.taaladvies.model.Categorie;
import be.vlaanderen.sbs.s6.taaladvies.model.Citaat;
import be.vlaanderen.sbs.s6.taaladvies.model.Frequentie;
import be.vlaanderen.sbs.s6.taaladvies.model.Naslagreferentie;
import be.vlaanderen.sbs.s6.taaladvies.model.Notitie;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.model.Webreferentie;

public class InitHulpmiddelenOverzichtAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
			
		HttpSession session = request.getSession();
		
		HulpmiddelenOverzichtForm referenceform = new HulpmiddelenOverzichtForm();
		
		if (request.getParameter("adm") != null) {
			referenceform.setAdm(true);
		}		
		
		java.util.ArrayList<Categorie> categorien;
		int taalvraagId = 0;
		int tekstId = 0;
		int id = Integer.parseInt(request.getParameter("id"));
		int type = Integer.parseInt(request.getParameter("type"));
		if (type == 1) {
			taalvraagId = id;
			Taalvraag taalvraag = Taalvraag.findByPK(id);
			referenceform.setTaalvraag(taalvraag);
			categorien = Categorie.findByTaalvraagId(taalvraag.getId());
		}
		else {
			tekstId = id;
			Tekst tekst = Tekst.findByPK(id);
			referenceform.setTekst(tekst);
			categorien = Categorie.findByTekstId(tekst.getId());
		}
		
		java.util.ArrayList<Frequentie> frequenties = Frequentie.findAllByParent(taalvraagId, tekstId);
		java.util.ArrayList<Webreferentie> webreferenties = Webreferentie.findAllByParent(taalvraagId, tekstId);
		java.util.ArrayList<Bron> bronnen = Bron.findAllByParent(taalvraagId, tekstId);
		java.util.ArrayList<Citaat> citaten = Citaat.findAllByParent(taalvraagId, tekstId);
		java.util.ArrayList<Notitie> notities = Notitie.findAllByParent(taalvraagId, tekstId);
		java.util.ArrayList<Naslagreferentie> naslagreferenties = Naslagreferentie.findAllByParent(taalvraagId, tekstId);
		
		if (frequenties.size() == 0) frequenties = null;
		if (webreferenties.size() == 0) webreferenties = null;
		if (bronnen.size() == 0) bronnen = null;
		if (citaten.size() == 0) citaten = null;
		if (notities.size() == 0) notities = null;
		if (naslagreferenties.size() == 0) naslagreferenties = null;
		if (categorien != null) {
			if (categorien.size() == 0) categorien = null;
		}		

		referenceform.setFrequenties(frequenties);
		referenceform.setWebreferenties(webreferenties);
		referenceform.setBronnen(bronnen);
		referenceform.setCitaten(citaten);
		referenceform.setNotities(notities);
		referenceform.setNaslagreferenties(naslagreferenties);
		referenceform.setCategorien(categorien);
		
  		session.setAttribute("HulpmiddelenOverzichtForm", referenceform);
		
		if (request.getParameter("ro") != null) {
			return(mapping.findForward("readonly"));
		}
		else {
			return(mapping.findForward("success"));
		}
		
	}

}

