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
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;

public class TaalvragenInvoerAction extends BaseAction {

	public ActionForward performAction(ActionMapping mapping,	ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		//boolean /*check = */false;
		int taalvraagId;

		HttpSession session = request.getSession();

		TaalvragenInvoerForm referenceForm = (TaalvragenInvoerForm)form;

		Taalvraag taalvraag = referenceForm.getTaalvraag();
		Oproep oproep = referenceForm.getOproep();
		taalvraagId = taalvraag.getId();
		//int type = 0;



		if(session.getAttribute("eopro") == null ||(session.getAttribute("eopro").equals("false"))){
		    formatHTMLVelden(taalvraag);
		}

		session.setAttribute("eopro", referenceForm.getUseEopro());

		if (request.getParameter("Koppel Tekst.x") != null) {
			/*check = */this.wijzigen(referenceForm);
			return (new ActionForward("/do/initZoekenKoppelTXT", true));
		}

		if (request.getParameter("Koppel Tekst Save.x") != null) {
			taalvraagId = this.toevoegen(referenceForm);
			referenceForm.getTaalvraag().setId(taalvraagId);
			return (new ActionForward("/do/initZoekenKoppelTXT", true));
		}

		if (request.getParameter("Button2") != null) {

			/*check = */this.wijzigen(referenceForm);
			int idToDelete = Integer.parseInt(request.getParameter("idToDelete"));

			/*check = */Taalvraag.deleteTekst(taalvraagId, idToDelete);

			return (new ActionForward("/do/initTaalvragenInvoer?parentId=" + taalvraag.getOproepId() + "&id=" + taalvraagId, true));
		}

		if (request.getParameter("Nieuwe Taalvraag Update.x")!= null) {

		    /*check = */this.wijzigen(referenceForm);
			return (new ActionForward("/do/initTaalvragenInvoer?parentId=" + referenceForm.getOproep().getId(), true));
		}

		if (request.getParameter("Nieuwe Taalvraag Save.x")!= null) {

		    taalvraagId = this.toevoegen(referenceForm);
			return (new ActionForward("/do/initTaalvragenInvoer?parentId=" + referenceForm.getOproep().getId(), true));
		}

		if (request.getParameter("Kopieer Update.x") != null) {

		    /*check = */this.wijzigen(referenceForm);
			session.setAttribute("TaalvraagId", new Integer(taalvraagId));
			session.setAttribute("OproepId", new Integer(taalvraag.getOproepId()));
			return (new ActionForward("/do/initZoekenKopieerTLV", true));
		}

		if (request.getParameter("Kopieer Save.x") != null) {
		    taalvraagId = this.toevoegen(referenceForm);
			session.setAttribute("TaalvraagId", new Integer(taalvraagId));
			session.setAttribute("OproepId", new Integer(taalvraag.getOproepId()));
			referenceForm.getTaalvraag().setId(taalvraagId);
			return (new ActionForward("/do/initZoekenKopieerTLV", true));
		}

		if (request.getParameter("Klant Update.x") != null) {

		    /*check = */this.wijzigen(referenceForm);
			return (new ActionForward("/do/initKlantenGegevensInvoer?id=" + oproep.getId(), true));
		}

		if (request.getParameter("Klant Save.x") != null) {

		    taalvraagId = this.toevoegen(referenceForm);
			return (new ActionForward("/do/initKlantenGegevensInvoer?id=" + oproep.getId(), true));
		}

		if (request.getParameter("Annuleren.x") != null) {

			return (new ActionForward("/do/initOproep_TaalvragenInvoer?id=" + referenceForm.getOproep().getId() + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Button").equals("Wijzigen")) {

		    /*check = */this.wijzigen(referenceForm);
			return (new ActionForward("/do/initOproep_TaalvragenInvoer?id=" + taalvraag.getOproepId() + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Button").equals("Toevoegen")) {

		    taalvraagId = this.toevoegen(referenceForm);
			return (new ActionForward("/do/initOproep_TaalvragenInvoer?id=" + taalvraag.getOproepId() + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true));
		}

		if (request.getParameter("Button").equals("Categorie")) {

			if (request.getParameter("U") != null){
			    /*check = */this.wijzigen(referenceForm);
			} else {
			    taalvraagId = this.toevoegen(referenceForm);
			}
			return (new ActionForward("/do/initCategorienInvoer?type=1&id=" + taalvraagId, true));
		}

		if (request.getParameter("Button").equals("Naslagwerk")) {

			if (request.getParameter("U") != null){
			    /*check = */this.wijzigen(referenceForm);
			} else {
			    taalvraagId = this.toevoegen(referenceForm);
			}
			return (new ActionForward("/do/initNaslagreferentiesInvoer?type=1&id=" + taalvraagId, true));
		}

		if (request.getParameter("Button").equals("Bron")) {

			if (request.getParameter("U") != null){
			    /*check = */this.wijzigen(referenceForm);
			} else {
			    taalvraagId = this.toevoegen(referenceForm);
			}
			return (new ActionForward("/do/initBronnenInvoer?type=1&id=" + taalvraagId, true));
		}

		if (request.getParameter("Button").equals("Citaat")) {

			if (request.getParameter("U") != null){
			    /*check = */this.wijzigen(referenceForm);
			} else {
			    taalvraagId = this.toevoegen(referenceForm);
			}
			return (new ActionForward("/do/initCitatenInvoer?type=1&id=" + taalvraagId, true));
		}

		if (request.getParameter("Button").equals("Frequentie")) {

			if (request.getParameter("U") != null){
			    /*check = */this.wijzigen(referenceForm);
			} else {
			    taalvraagId = this.toevoegen(referenceForm);
			}
			return (new ActionForward("/do/initFrequentiesInvoer?type=1&id=" + taalvraagId, true));
		}

		if (request.getParameter("Button").equals("Koppeling")) {

			if (request.getParameter("U") != null){
			    /*check = */this.wijzigen(referenceForm);
			} else {
			    taalvraagId = this.toevoegen(referenceForm);
			}
			return (new ActionForward("/do/initWebreferentiesInvoer?type=1&id=" + taalvraagId, true));
		}

		if (request.getParameter("Button").equals("Hulpmiddelen")) {

			if (request.getParameter("U") != null){
			    /*check = */this.wijzigen(referenceForm);
			} else {
			    taalvraagId = this.toevoegen(referenceForm);
			}
			return (new ActionForward("/do/initHulpmiddelenOverzicht?type=1&id=" + taalvraagId, true));
		}

		if (request.getParameter("Button").equals("Vraag")) {

			if (request.getParameter("U") != null){
			    /*check = */this.wijzigen(referenceForm);
			} else {
			    taalvraagId = this.toevoegen(referenceForm);
			}
			return (new ActionForward("/do/initKenmerkenInvoer?id=" + taalvraagId, true));
		}

		if (request.getParameter("Button").equals("Bijzonderheid")) {

			if (request.getParameter("U") != null){
			    /*check = */this.wijzigen(referenceForm);
			} else {
			    taalvraagId = this.toevoegen(referenceForm);
			}
			return (new ActionForward("/do/initBijzonderheidInvoer?id=" + taalvraagId, true));
		}

		if (request.getParameter("Button").equals("Notitie")) {

			if (request.getParameter("U") != null){
			    /*check = */this.wijzigen(referenceForm);
			} else {
			    taalvraagId = this.toevoegen(referenceForm);
			}
			return (new ActionForward("/do/initNotitiesInvoer?type=1&id=" + taalvraagId, true));
		}

		if (request.getParameter("Button").equals("EditeerModus")){
		    if (request.getParameter("U") != null){
		        /*check = */this.wijzigen(referenceForm);
		    }else{
		        taalvraagId = this.toevoegen(referenceForm);
		    }
		    return new ActionForward("/do/initTaalvragenInvoer?parentId=" + referenceForm.getOproep().getId() + "&id=" + taalvraagId + "&" + Constants.TOKEN_KEY + "=" + (String)session.getAttribute(Globals.TRANSACTION_TOKEN_KEY), true);
		}

		return (mapping.findForward("blabla"));

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

    private boolean wijzigen(TaalvragenInvoerForm referenceForm) {
        return Taalvraag.update(referenceForm.getTaalvraag());
	}

	private int toevoegen(TaalvragenInvoerForm referenceForm) {
		Taalvraag taalvraag = referenceForm.getTaalvraag();
		taalvraag.setOproepId(referenceForm.getOproep().getId());
		return Taalvraag.insert(taalvraag);
	}

}

