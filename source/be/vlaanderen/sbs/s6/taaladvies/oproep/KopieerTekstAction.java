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
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekstblok;
import be.vlaanderen.sbs.s6.taaladvies.model.Webreferentie;

public class KopieerTekstAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		//boolean check = false;
		KopieerTekstForm referenceForm = (KopieerTekstForm)form;
		Tekst tekst = referenceForm.getTekst();
		//int id = tekst.getId();
		Tekst tekst_sessie = Tekst.findByPK(((Integer)session.getAttribute("TekstId")).intValue());
		java.util.ArrayList<Tekstblok> tekstblokken = referenceForm.getTekstblokken();
		java.util.ArrayList<Tekstblok> tekstblokken_sessie = tekst_sessie.getTekstblokken();

		int new_tekstblok_volgnummer;

		if (tekstblokken_sessie != null) {
			new_tekstblok_volgnummer = tekstblokken_sessie.size() + 1;
		}
		else {
			new_tekstblok_volgnummer = 1;
		}

		if (referenceForm.getCheck_Titel()) {
			tekst_sessie.setTitel(tekst.getTitel());
			tekst_sessie.setTitelHTML(tekst.getTitelHTML());
		}

		/*check = */Tekst.update(tekst_sessie);

		for (int i = 0, j = tekstblokken.size(); i < j; i++) {
			Tekstblok tekstblok = tekstblokken.get(i);
			Tekstblok newTekstblok = new Tekstblok();
			if (tekstblok.getCheck_Titel()) {
				newTekstblok.setTitel(tekstblok.getTitel());
				newTekstblok.setTitelHTML(tekstblok.getTitelHTML());
				newTekstblok.setTekstblok(tekstblok.getTekstblok());
				newTekstblok.setTekstblokHTML(tekstblok.getTekstblokHTML());
/*			}
			if (tekstblok.getCheck_Tekstblok()) {
				newTekstblok.setTekstblok(tekstblok.getTekstblok());
				newTekstblok.setTekstblokHTML(tekstblok.getTekstblokHTML());
			}
			if (tekstblok.getCheck_Titel() || tekstblok.getCheck_Tekstblok()) {
*/
				newTekstblok.setVolgnummer(new_tekstblok_volgnummer);
				newTekstblok.setTekstId(tekst_sessie.getId());
				/*int checkint = */Tekstblok.insert(newTekstblok);
				new_tekstblok_volgnummer++;
			}
		}

		if (referenceForm.getCheck_Categorie()) {
			Categorie.deleteForTekst(tekst_sessie.getId());
			java.util.ArrayList<Categorie> categorien = tekst.getCategorien();
			for (int i = 0, j = categorien.size(); i < j; i++) {
				Categorie categorie = (Categorie)categorien.get(i);
				Categorie.insertForTekst(tekst_sessie.getId(), categorie.getId());
			}
		}

		if (referenceForm.getCheck_Naslagreferentie()) {
			java.util.ArrayList<Naslagreferentie> naslagreferenties = tekst.getNaslagreferenties();
			for (int i = 0, j = naslagreferenties.size(); i < j; i++) {
				Naslagreferentie naslagreferentie = (Naslagreferentie)naslagreferenties.get(i);
				naslagreferentie.setTekstId(tekst_sessie.getId());
				/*int naslagrefentieId = */Naslagreferentie.insert(naslagreferentie);
			}
		}

		if (referenceForm.getCheck_Bron()) {
			java.util.ArrayList<Bron> bronnen = tekst.getBronnen();
			for (int i = 0, j = bronnen.size(); i < j; i++) {
				Bron bron = (Bron)bronnen.get(i);
				bron.setTekstId(tekst_sessie.getId());
//				int bronId = Bron.insert(bron);
			}
		}

		if (referenceForm.getCheck_Citaat()) {
			java.util.ArrayList<Citaat> citaten = tekst.getCitaten();
			for (int i = 0, j = citaten.size(); i < j; i++) {
				Citaat citaat = (Citaat)citaten.get(i);
				citaat.setTekstId(tekst_sessie.getId());
//				int citaatId = Citaat.insert(citaat);
			}
		}

		if (referenceForm.getCheck_Frequentie()) {
			java.util.ArrayList<Frequentie> frequenties = tekst.getFrequenties();
			for (int i = 0, j = frequenties.size(); i < j; i++) {
				Frequentie frequentie = (Frequentie)frequenties.get(i);
				frequentie.setTekstId(tekst_sessie.getId());
//				int frequentieId = Frequentie.insert(frequentie);
			}
		}

		if (referenceForm.getCheck_Webreferentie()) {
			java.util.ArrayList<Webreferentie> webreferenties = tekst.getWebreferenties();
			for (int i = 0, j = webreferenties.size(); i < j; i++) {
				Webreferentie webreferentie = (Webreferentie)webreferenties.get(i);
				webreferentie.setTekstId(tekst_sessie.getId());
//				int webreferentieId = Webreferentie.insert(webreferentie);
			}
		}

		if (referenceForm.getCheck_Notitie()) {
			java.util.ArrayList<Notitie> notities = tekst.getNotities();
			for (int i = 0, j = notities.size(); i < j; i++) {
				Notitie notitie = (Notitie)notities.get(i);
				notitie.setTekstId(tekst_sessie.getId());
//				int notitieId = Notitie.insert(notitie);
			}
		}

		return (new ActionForward("/do/initOproep_TekstInvoer?id=" + tekst_sessie.getOproepId() + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));

	}

}

