package be.vlaanderen.sbs.s6.taaladvies.oproep;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekstblok;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;


public class TekstblokkenInvoerForm extends ActionForm {


	/**
     * 
     */
    private static final long serialVersionUID = -3717195036791694291L;
	Tekstblok tekstblok = new Tekstblok();
	Tekst tekst = new Tekst();

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();

		if (request.getParameter("Annuleren.x") == null) {
			java.util.ArrayList<Tekstblok> tekstblokken = tekst.getTekstblokken();
			if (tekstblok.getTitelHTML().trim().equals("") || tekstblok.getTitelHTML().trim().equals(Util.EOPRO_EMPTY_HTMLFORM)) {
				if (tekstblokken != null) {
					Tekstblok check_tekstblok = (Tekstblok)tekstblokken.get(0);
					if (tekstblok.getId() != check_tekstblok.getId()) {
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("algemeen.ontbreken.titel"));
					}
				}
			}
			if (tekstblokken != null) {
				Tekstblok check_tekstblok = (Tekstblok)tekstblokken.get(0);
				if (tekstblok.getId() != check_tekstblok.getId()) {
					if (check_tekstblok.getTitel().equals("")) {
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("tekstblok.invoeren.ontbreken.titel.eerstetekstblok"));
					}
				}
			}
			
			if (request.getParameter("Nieuw Tekstblok Update.x") != null || request.getParameter("Nieuw Tekstblok Save.x") != null) {
				if (tekstblok.getTitel().trim().equals("")) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("tekstblok.invoeren.ontbreken.titel.eerstetekstblok"));
				}
			}

		}
		return errors;
	}


	public void reset(ActionMapping mapping, HttpServletRequest request) {
		tekstblok = new Tekstblok();
	}


	/**
	 * Gets the tekstblok
	 * @return Returns a Tekstblok
	 */
	public Tekstblok getTekstblok() {
		return tekstblok;
	}
	/**
	 * Sets the tekstblok
	 * @param tekstblok The tekstblok to set
	 */
	public void setTekstblok(Tekstblok tekstblok) {
		this.tekstblok = tekstblok;
	}

	/**
	 * Gets the tekst
	 * @return Returns a Tekst
	 */
	public Tekst getTekst() {
		return tekst;
	}
	/**
	 * Sets the tekst
	 * @param tekst The tekst to set
	 */
	public void setTekst(Tekst tekst) {
		this.tekst = tekst;
	}


}


