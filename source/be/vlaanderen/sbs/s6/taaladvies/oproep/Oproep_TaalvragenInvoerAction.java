package be.vlaanderen.sbs.s6.taaladvies.oproep;

import java.io.IOException;
import java.sql.Date;
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
import be.vlaanderen.sbs.s6.taaladvies.appconf.AppConf;
import be.vlaanderen.sbs.s6.taaladvies.distributie.DistributiekeuzeForm;
import be.vlaanderen.sbs.s6.taaladvies.model.Bron;
import be.vlaanderen.sbs.s6.taaladvies.model.Categorie;
import be.vlaanderen.sbs.s6.taaladvies.model.Citaat;
import be.vlaanderen.sbs.s6.taaladvies.model.Distributie;
import be.vlaanderen.sbs.s6.taaladvies.model.Frequentie;
import be.vlaanderen.sbs.s6.taaladvies.model.Gebruiker;
import be.vlaanderen.sbs.s6.taaladvies.model.Naslagreferentie;
import be.vlaanderen.sbs.s6.taaladvies.model.Notitie;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;
import be.vlaanderen.sbs.s6.taaladvies.model.Sjabloon;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.model.Webreferentie;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;

public class Oproep_TaalvragenInvoerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		HttpSession session = request.getSession();

		//boolean check = false;

		Oproep_TaalvragenInvoerForm referenceForm = (Oproep_TaalvragenInvoerForm)form;
		Oproep oproep = referenceForm.getOproep();
		Taalvraag taalvraag = referenceForm.getTaalvraag();

		//int type = 0;
		Gebruiker gebruiker = (Gebruiker)session.getAttribute("Gebruiker");
		java.util.ArrayList<Taalvraag> taalvragen = referenceForm.getTaalvragen();

		if(session.getAttribute("eopro") == null ||(session.getAttribute("eopro").equals("false"))){
		    formatHTMLVelden(taalvraag);
		}

		session.setAttribute("eopro", referenceForm.getUseEopro());

		if (request.getParameter("Oproep Afsluiten.x") != null) {

			/*check = */this.wijzigen(referenceForm, gebruiker);

			/*check = */Oproep.close(referenceForm.getOproep().getId());

			for (int i = 0, j = taalvragen.size(); i < j; i++) {
				Taalvraag distributietaalvraag = (Taalvraag)taalvragen.get(i);
				if (distributietaalvraag.getDistributiedatum() == null) {
					distributietaalvraag.setDistributiedatum(new java.sql.Date(new java.util.Date().getTime()));
					/*check = */Taalvraag.updateDistributiedatum(distributietaalvraag);
				}
			}

			return (new ActionForward("/do/initEigenTaalvragen", true));
		}

		if (request.getParameter("Distributie.x") != null) {

			if (request.getParameter("ro") == null) {
				this.wijzigen(referenceForm, gebruiker);
			}

			for (int i = taalvragen.size(), j = 0; i > j ; i--) {
				if (request.getParameter("toDistribute_" + (i - 1)) == null) {
					taalvragen.remove(i - 1);
				}
			}

			DistributiekeuzeForm distributiekeuzeform = new DistributiekeuzeForm();
			distributiekeuzeform.setOproep(Oproep.findByPK(referenceForm.getOproep().getId()));

			if(distributiekeuzeform.getOproep().getDistributie() == null)
			{
				distributiekeuzeform.getOproep().setDistributie(new Distributie());
			}

			if (distributiekeuzeform.getOproep().getDomeinId() == 1)
			{
				distributiekeuzeform.getOproep().getDistributie().setBcc(AppConf.getResource("mail.fromIntern"));
			}
			else
			{
				distributiekeuzeform.getOproep().getDistributie().setBcc(AppConf.getResource("mail.fromExtern"));
			}

			distributiekeuzeform.setTaalvragen(taalvragen);
			distributiekeuzeform.setSjablonen(Sjabloon.findAllActief());
			if (request.getParameter("ro") != null) {
				distributiekeuzeform.setRo(true);
			}
			session.setAttribute("DistributiekeuzeForm", distributiekeuzeform);
			return (mapping.findForward("distributie"));

		}

		if (request.getParameter("In Behandeling.x") != null) {
			this.wijzigen(referenceForm, gebruiker);

			return (new ActionForward("/do/initEigenTaalvragen", true));
		}
		if (request.getParameter("Nieuwe Oproep Taalvragen.x") != null) {
			this.wijzigen(referenceForm, gebruiker);
			return (new ActionForward("/do/initOproep_TaalvragenInvoer", true));
		}
		if (request.getParameter("Nieuwe Oproep Tekst.x") != null) {
			this.wijzigen(referenceForm, gebruiker);
			return (new ActionForward("/do/initOproep_TekstInvoer", true));
		}
		if (request.getParameter("Zoeken.x") != null) {
			this.wijzigen(referenceForm, gebruiker);
			return (new ActionForward("/do/initZoeken", true));
		}
		if (request.getParameter("Profielen.x") != null) {
			this.wijzigen(referenceForm, gebruiker);
			return (new ActionForward("/profielen.jsp", true));
		}
		if (request.getParameter("In Behandeling Alles.x") != null) {
			this.allesToevoegen(referenceForm, gebruiker);
			return (new ActionForward("/do/initEigenTaalvragen", true));
		}
		if (request.getParameter("Nieuwe Oproep Taalvragen Alles.x") != null) {
		    this.allesToevoegen(referenceForm, gebruiker);
			return (new ActionForward("/do/initOproep_TaalvragenInvoer", true));
		}
		if (request.getParameter("Nieuwe Oproep Tekst Alles.x") != null) {
		    this.allesToevoegen(referenceForm, gebruiker);
			return (new ActionForward("/do/initOproep_TekstInvoer", true));
		}
		if (request.getParameter("Zoeken Alles.x") != null) {
		    this.allesToevoegen(referenceForm, gebruiker);
			return (new ActionForward("/do/initZoeken", true));
		}
		if (request.getParameter("Profielen Alles.x") != null) {
		    this.allesToevoegen(referenceForm, gebruiker);
			return (new ActionForward("/profielen.jsp", true));
		}

		if (request.getParameter("Wijzigen.x") != null) {

			this.wijzigen(referenceForm, gebruiker);
			return (new ActionForward("/do/initOproep_TaalvragenInvoer?id=" + referenceForm.getOproep().getId()+ "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Toevoegen.x") != null) {
		    this.allesToevoegen(referenceForm, gebruiker);
			return (new ActionForward("/do/initTaalvragenInvoer?parentId=" + referenceForm.getOproep().getId() + "&id=" + referenceForm.getTaalvraag().getId(), true));
		}

		if (request.getParameter("Annuleren.x") != null)
		{
			this.verwijderen(referenceForm, gebruiker);
			return (new ActionForward("/do/initEigenTaalvragen", true));
		}


		if (request.getParameter("Koppel Tekst.x") != null) {
		    this.allesToevoegen(referenceForm, gebruiker);
			this.goToZoekPage(referenceForm, session);
			return (new ActionForward("/do/initZoekenKoppelTXT", true));
		}

		if (request.getParameter("Alles Toevoegen.x") != null) {
		    this.allesToevoegen(referenceForm, gebruiker);
			return (new ActionForward("/do/initOproep_TaalvragenInvoer?id=" + referenceForm.getOproep().getId() + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Nieuwe Taalvraag.x") != null) {

			if (referenceForm.getOproep().getId() == 0) {
			    this.allesToevoegen(referenceForm, gebruiker);
			}
			else {
				this.wijzigen(referenceForm, gebruiker);
			}
			return (new ActionForward("/do/initTaalvragenInvoer?parentId=" + referenceForm.getOproep().getId(), true));
		}

		if (request.getParameter("Kopieer.x") != null) {
		    this.allesToevoegen(referenceForm, gebruiker);
			this.goToZoekPage(referenceForm, session);
			session.setAttribute("TaalvraagId", new Integer(referenceForm.getTaalvraag().getId()));
			session.setAttribute("OproepId", new Integer(referenceForm.getOproep().getId()));
			return (new ActionForward("/do/initZoekenKopieerTLV", true));
		}

		if (request.getParameter("Klant.x") != null) {

			if (referenceForm.getOproep().getId() == 0) {
			    this.allesToevoegen(referenceForm, gebruiker);
			}
			else {
				this.wijzigen(referenceForm, gebruiker);
			}
			return (new ActionForward("/do/initKlantenGegevensInvoer?id=" + referenceForm.getOproep().getId(), true));
		}

		if (request.getParameter("Button2") != null) {

			this.wijzigen(referenceForm, gebruiker);

			int idToDelete = Integer.parseInt(request.getParameter("idToDelete"));

			/*check = */Categorie.deleteForTaalvraag(idToDelete);
			/*check = */Bron.deleteForTaalvraag(idToDelete);
			/*check = */Citaat.deleteForTaalvraag(idToDelete);
			/*check = */Frequentie.deleteForTaalvraag(idToDelete);
			/*check = */Naslagreferentie.deleteForTaalvraag(idToDelete);
			/*check = */Notitie.deleteForTaalvraag(idToDelete);
			/*check = */Webreferentie.deleteForTaalvraag(idToDelete);
			/*check = */Taalvraag.deleteTeksten(idToDelete);
			/*check = *///Taalvraag.deleteVerityEntry(idToDelete);
			/*check = */Taalvraag.delete(idToDelete);

			taalvragen = Taalvraag.findAllByOproep(referenceForm.getOproep().getId());
			if (taalvragen == null || taalvragen.size() == 0) {

				/*check = */Oproep.delete(oproep.getId());
				/*check = */Distributie.delete(oproep.getDistributieId());
			}
			else {
				for (int i = 0, j = taalvragen.size(); i < j ; i++) {
					taalvraag = (Taalvraag)taalvragen.get(i);
					taalvraag.setVolgnummer(i+1);
					/*check = */Taalvraag.updateVolgnummer(taalvraag);
				}
			}
			return (new ActionForward("/do/initOproep_TaalvragenInvoer?id=" + referenceForm.getOproep().getId(), true));
		}

		if (request.getParameter("Button3") != null) {

			this.wijzigen(referenceForm, gebruiker);

			int idToGoTo = Integer.parseInt(request.getParameter("idToGoTo"));
			return (new ActionForward("/do/initTaalvragenInvoer?parentId=" + referenceForm.getOproep().getId() + "&id=" + idToGoTo, true));
		}

		if (request.getParameter("Button").equals("AllesToevoegen")) {
			this.allesToevoegen(referenceForm, gebruiker);
			return (new ActionForward("/do/initOproep_TaalvragenInvoer?id=" + referenceForm.getOproep().getId(), true));
		}

		if (request.getParameter("Button").equals("Categorie")) {
		    this.allesToevoegen(referenceForm, gebruiker);
			return (new ActionForward("/do/initCategorienInvoer?type=1&id=" + referenceForm.getTaalvraag().getId(), true));
		}

		if (request.getParameter("Button").equals("Naslagwerk")) {
			this.allesToevoegen(referenceForm, gebruiker);
			return (new ActionForward("/do/initNaslagreferentiesInvoer?type=1&id=" + referenceForm.getTaalvraag().getId(), true));
		}

		if (request.getParameter("Button").equals("Bron")) {
			this.allesToevoegen(referenceForm, gebruiker);
			return (new ActionForward("/do/initBronnenInvoer?type=1&id=" + referenceForm.getTaalvraag().getId(), true));
		}

		if (request.getParameter("Button").equals("Citaat")) {
		    this.allesToevoegen(referenceForm, gebruiker);
			return (new ActionForward("/do/initCitatenInvoer?type=1&id=" + referenceForm.getTaalvraag().getId(), true));
		}

		if (request.getParameter("Button").equals("Frequentie")) {
		    this.allesToevoegen(referenceForm, gebruiker);
			return (new ActionForward("/do/initFrequentiesInvoer?type=1&id=" + referenceForm.getTaalvraag().getId(), true));
		}

		if (request.getParameter("Button").equals("Koppeling")) {
		    this.allesToevoegen(referenceForm, gebruiker);
			return (new ActionForward("/do/initWebreferentiesInvoer?type=1&id=" + referenceForm.getTaalvraag().getId(), true));
		}

		if (request.getParameter("Button").equals("Vraag")) {
		    this.allesToevoegen(referenceForm, gebruiker);
			return (new ActionForward("/do/initKenmerkenInvoer?id=" + referenceForm.getTaalvraag().getId(), true));
		}

		if (request.getParameter("Button").equals("Bijzonderheid")) {
		    this.allesToevoegen(referenceForm, gebruiker);
			return (new ActionForward("/do/initBijzonderheidInvoer?id=" + referenceForm.getTaalvraag().getId(), true));
		}

		if (request.getParameter("Button").equals("Notitie")) {
		    this.allesToevoegen(referenceForm, gebruiker);
			return (new ActionForward("/do/initNotitiesInvoer?type=1&id=" + referenceForm.getTaalvraag().getId(), true));
		}

		if (request.getParameter("Button").equals("EditeerModus")){
		    if (request.getParameter("U") != null){
		        this.wijzigen(referenceForm, gebruiker);
		    }else{
		        this.allesToevoegen(referenceForm, gebruiker);
		    }
		    return new ActionForward("/do/initTaalvragenInvoer?parentId=" + referenceForm.getOproep().getId() + "&id=" + referenceForm.getTaalvraag().getId() + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true);
		}


		return (mapping.findForward("blabla"));

	}

	public boolean wijzigen(Oproep_TaalvragenInvoerForm referenceForm, Gebruiker gebruiker) {

		boolean check = false;
	    Oproep oproep = referenceForm.getOproep();

		oproep.setType(1);
		oproep.setGebruikerId(gebruiker.getId());
		check = Oproep.update(oproep);
		Distributie distributie = new Distributie();
		distributie.setId(oproep.getDistributieId());
		distributie.setMediumId(oproep.getDistributieAsIs().getMediumId());
		distributie.setDistributiedatum(new Date(new java.util.Date().getTime()));
		check = Distributie.update(distributie);
		return check;
	}

	public int toevoegen(Oproep_TaalvragenInvoerForm referenceForm, Gebruiker gebruiker) {

		Oproep oproep = referenceForm.getOproep();

		if (oproep.getNaam().equals("")) {
			oproep.setNaam("Onbekend");
		}
		oproep.setType(1);
		oproep.setGebruikerId(gebruiker.getId());
		Distributie distributie = new Distributie();
		distributie.setMediumId(oproep.getDistributieAsIs().getMediumId());
		distributie.setDistributiedatum(new Date(new java.util.Date().getTime()));
		int distributieId = Distributie.insert(distributie);
		oproep.setDistributieId(distributieId);
		return Oproep.insert(oproep);
	}

	public void allesToevoegen(Oproep_TaalvragenInvoerForm referenceForm, Gebruiker gebruiker) {

		Taalvraag taalvraag = referenceForm.getTaalvraag();

		if((referenceForm.getOproep() != null) && referenceForm.getOproep().getId() == 0)
		{
		    referenceForm.getOproep().setId(this.toevoegen(referenceForm, gebruiker));
		}

		taalvraag.setOproepId(referenceForm.getOproep().getId());
		referenceForm.getTaalvraag().setId(Taalvraag.insert(taalvraag));
	}

	public void verwijderen(Oproep_TaalvragenInvoerForm referenceForm, Gebruiker gebruiker) {

		if((referenceForm.getOproep() != null) && (referenceForm.getOproep().getId() != 0))
		{
			Oproep.delete(referenceForm.getOproep().getId());
		}

	}

	public void goToZoekPage(Oproep_TaalvragenInvoerForm referenceForm, HttpSession session) {
		TaalvragenInvoerForm taalform = new TaalvragenInvoerForm();
		taalform.setOproep(referenceForm.getOproep());
		taalform.setTaalvraag(referenceForm.getTaalvraag());
		taalform.getTaalvraag().setId(referenceForm.getTaalvraag().getId());
		session.setAttribute("TaalvragenInvoerForm", taalform);
	}

	/**
	 * vervangt de new lines in dit veld door br - html tags in niet-eopro editeer modus
     * @param taalvraag de te formateren taalvraag
     */
    private void formatHTMLVelden(Taalvraag taalvraag) {
        taalvraag.setAntwoordHTML(Util.formatNewLinesToBr(taalvraag.getAntwoordHTML()));
        taalvraag.setBijzonderheidHTML(Util.formatNewLinesToBr(taalvraag.getBijzonderheidHTML()));
        taalvraag.setHerformuleringHTML(Util.formatNewLinesToBr(taalvraag.getHerformuleringHTML()));
        taalvraag.setInformatieHTML(Util.formatNewLinesToBr(taalvraag.getInformatieHTML()));
        taalvraag.setTitelHTML(Util.formatNewLinesToBr(taalvraag.getTitelHTML()));
        taalvraag.setToelichtingHTML(Util.formatNewLinesToBr(taalvraag.getToelichtingHTML()));
        taalvraag.setVraagHTML(Util.formatNewLinesToBr(taalvraag.getVraagHTML()));
    }
}

