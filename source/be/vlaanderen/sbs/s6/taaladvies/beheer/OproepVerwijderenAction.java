package be.vlaanderen.sbs.s6.taaladvies.beheer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.BaseAction;
import be.vlaanderen.sbs.s6.taaladvies.model.Bron;
import be.vlaanderen.sbs.s6.taaladvies.model.Categorie;
import be.vlaanderen.sbs.s6.taaladvies.model.Citaat;
import be.vlaanderen.sbs.s6.taaladvies.model.Distributie;
import be.vlaanderen.sbs.s6.taaladvies.model.Frequentie;
import be.vlaanderen.sbs.s6.taaladvies.model.Naslagreferentie;
import be.vlaanderen.sbs.s6.taaladvies.model.Notitie;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekstblok;
import be.vlaanderen.sbs.s6.taaladvies.model.Webreferentie;

public class OproepVerwijderenAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		//HttpSession session = request.getSession();

		OproepVerwijderenForm referenceForm = (OproepVerwijderenForm)form;
		int id = referenceForm.getId();
		//boolean check;

		Oproep oproep = Oproep.findByPK(id);
		if (oproep.getType() == 1) {
			// verwijderen alle taalvragen + alle hulpmiddelen voor deze
			java.util.ArrayList<Taalvraag> taalvragen = Taalvraag.findAllByOproep(id);
			for (int i = 0, j = taalvragen.size(); i < j; i++) {
			 	Taalvraag taalvraag = (Taalvraag)taalvragen.get(i);
			 	/*check = */Categorie.deleteForTaalvraag(taalvraag.getId());
			 	/*check = */Bron.deleteForTaalvraag(taalvraag.getId());
			 	/*check = */Citaat.deleteForTaalvraag(taalvraag.getId());
			 	/*check = */Frequentie.deleteForTaalvraag(taalvraag.getId());
			 	/*check = */Naslagreferentie.deleteForTaalvraag(taalvraag.getId());
			 	/*check = */Notitie.deleteForTaalvraag(taalvraag.getId());
			 	/*check = */Webreferentie.deleteForTaalvraag(taalvraag.getId());
			 	/*check = *///Taalvraag.deleteVerityEntry(taalvraag.getId());

			 	/*check = */Taalvraag.delete(taalvraag.getId());
			}

		}
		else {
			Tekst tekst = Tekst.findByOproep(id);
		 	java.util.ArrayList<Tekstblok> tekstblokken = Tekstblok.findByParent(tekst.getId());
		 	for (int i = 0, j = tekstblokken.size(); i < j; i++) {
		 		Tekstblok tekstblok = (Tekstblok)tekstblokken.get(i);
		 		/*check = */Tekstblok.delete(tekstblok.getId());
		 	}
		 	/*check = */Categorie.deleteForTekst(tekst.getId());
			/*check = */Bron.deleteForTekst(tekst.getId());
			/*check = */Citaat.deleteForTekst(tekst.getId());
			/*check = */Frequentie.deleteForTekst(tekst.getId());
			/*check = */Naslagreferentie.deleteForTekst(tekst.getId());
			/*check = */Notitie.deleteForTekst(tekst.getId());
			/*check = */Webreferentie.deleteForTekst(tekst.getId());
			/*check = *///Tekst.deleteVerityEntry(tekst.getId());

			/*check = */Tekst.delete(tekst.getId());
		}
		/*check = */Oproep.removeFromTaalunieSendQueue(id);
		/*check = */Oproep.delete(id);
		/*check = */Distributie.delete(oproep.getDistributieId());

		return (mapping.findForward("success"));

	}

}

