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
import be.vlaanderen.sbs.s6.taaladvies.model.Categorie;
import be.vlaanderen.sbs.s6.taaladvies.model.Citaat;
import be.vlaanderen.sbs.s6.taaladvies.model.Frequentie;
import be.vlaanderen.sbs.s6.taaladvies.model.Naslagreferentie;
import be.vlaanderen.sbs.s6.taaladvies.model.Notitie;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.model.Webreferentie;

public class KopieerTaalvraagAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean check = false;
		KopieerTaalvraagForm referenceForm = (KopieerTaalvraagForm)form;
		Taalvraag taalvraag = referenceForm.getTaalvraag();
		//int id = taalvraag.getId();
		Taalvraag taalvraag_sessie = Taalvraag.findByPK(((Integer)session.getAttribute("TaalvraagId")).intValue());


		if (referenceForm.getCheck_Titel()) {
			taalvraag_sessie.setTitel(taalvraag.getTitel());
			taalvraag_sessie.setTitelHTML(taalvraag.getTitelHTML());
		}

		if (referenceForm.getCheck_Vraag()) {
			taalvraag_sessie.setVraag(taalvraag.getVraag());
			taalvraag_sessie.setVraagHTML(taalvraag.getVraagHTML());
		}

		if (referenceForm.getCheck_Antwoord()) {
			taalvraag_sessie.setAntwoord(taalvraag.getAntwoord());
			taalvraag_sessie.setAntwoordHTML(taalvraag.getAntwoordHTML());
		}

		if (referenceForm.getCheck_Toelichting()) {
			taalvraag_sessie.setToelichting(taalvraag.getToelichting());
			taalvraag_sessie.setToelichtingHTML(taalvraag.getToelichtingHTML());
		}

		/*check = */Taalvraag.update(taalvraag_sessie);

		if (referenceForm.getCheck_Bijzonderheid()) {
			taalvraag_sessie.setBijzonderheid(taalvraag.getBijzonderheid());
			taalvraag_sessie.setBijzonderheidHTML(taalvraag.getBijzonderheidHTML());
		}

		if (taalvraag.getBijzonderheid() != null && taalvraag.getBijzonderheidHTML() != null) {
			/*check = */Taalvraag.updateBijzonderheid(taalvraag_sessie);
		}

		if (referenceForm.getCheck_Categorie()) {
			Categorie.deleteForTaalvraag(taalvraag_sessie.getId());
			java.util.ArrayList<Categorie> categorien = taalvraag.getCategorien();
			for (int i = 0, j = categorien.size(); i < j; i++) {
				Categorie categorie = (Categorie)categorien.get(i);
				Categorie.insertForTaalvraag(taalvraag_sessie.getId(), categorie.getId());
			}
		}

		if (referenceForm.getCheck_Naslagreferentie()) {
			java.util.ArrayList<Naslagreferentie> naslagreferenties = taalvraag.getNaslagreferenties();
			for (int i = 0, j = naslagreferenties.size(); i < j; i++) {
				Naslagreferentie naslagreferentie = (Naslagreferentie)naslagreferenties.get(i);
				naslagreferentie.setTaalvraagId(taalvraag_sessie.getId());
				/*int naslagrefentieId = */Naslagreferentie.insert(naslagreferentie);
			}
		}

		if (referenceForm.getCheck_Bron()) {
			java.util.ArrayList<Bron> bronnen = taalvraag.getBronnen();
			for (int i = 0, j = bronnen.size(); i < j; i++) {
				Bron bron = (Bron)bronnen.get(i);
				bron.setTaalvraagId(taalvraag_sessie.getId());
				/*int bronId = */Bron.insert(bron);
			}
		}

		if (referenceForm.getCheck_Citaat()) {
			java.util.ArrayList<Citaat> citaten = taalvraag.getCitaten();
			for (int i = 0, j = citaten.size(); i < j; i++) {
				Citaat citaat = (Citaat)citaten.get(i);
				citaat.setTaalvraagId(taalvraag_sessie.getId());
				/*int citaatId = */Citaat.insert(citaat);
			}
		}

		if (referenceForm.getCheck_Frequentie()) {
			java.util.ArrayList<Frequentie> frequenties = taalvraag.getFrequenties();
			for (int i = 0, j = frequenties.size(); i < j; i++) {
				Frequentie frequentie = (Frequentie)frequenties.get(i);
				frequentie.setTaalvraagId(taalvraag_sessie.getId());
				/*int frequentieId = */Frequentie.insert(frequentie);
			}
		}

		if (referenceForm.getCheck_Webreferentie()) {
			java.util.ArrayList<Webreferentie> webreferenties = taalvraag.getWebreferenties();
			for (int i = 0, j = webreferenties.size(); i < j; i++) {
				Webreferentie webreferentie = (Webreferentie)webreferenties.get(i);
				webreferentie.setTaalvraagId(taalvraag_sessie.getId());
				/*int webreferentieId = */Webreferentie.insert(webreferentie);
			}
		}

		if (referenceForm.getCheck_Notitie()) {
			java.util.ArrayList<Notitie> notities = taalvraag.getNotities();
			for (int i = 0, j = notities.size(); i < j; i++) {
				Notitie notitie = (Notitie)notities.get(i);
				notitie.setTaalvraagId(taalvraag_sessie.getId());
				/*int notitieId = */Notitie.insert(notitie);
			}
		}

		if (referenceForm.getCheck_Teksten()) {
			java.util.ArrayList<Tekst> teksten = taalvraag.getTeksten();
			for (int i = 0, j = teksten.size(); i < j; i++) {
				Tekst tekst = (Tekst)teksten.get(i);
				/*check = */Taalvraag.insertTekst(taalvraag_sessie.getId(), tekst.getId());
			}
		}

		return (new ActionForward("/do/initTaalvragenInvoer?id=" + taalvraag_sessie.getId() + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));

	}

}

