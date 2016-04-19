package be.vlaanderen.sbs.s6.taaladvies.oproep;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Notitie;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;


public class NotitiesInvoerForm extends ActionForm {


	/**
     * 
     */
    private static final long serialVersionUID = -2743742850727391675L;
	Notitie notitie = new Notitie();
	java.util.ArrayList<Notitie> notities = new java.util.ArrayList<Notitie>();
	Taalvraag taalvraag = null;
	Tekst tekst = null;

	boolean adm = false;
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();

		if (request.getParameter("Annuleren.x") == null && request.getParameter("empty") == null) {
			if (notitie.getNotitie().trim().equals("")) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("notitie.ontbreken.notitie"));
			}
		}
	
		return errors;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		notitie = new Notitie();
	}


	/**
	 * Gets the notities
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Notitie> getNotities() {
		return notities;
	}
	/**
	 * Sets the notities
	 * @param notities The notities to set
	 */
	public void setNotities(java.util.ArrayList<Notitie> notities) {
		this.notities = notities;
	}

	/**
	 * Gets the taalvraag
	 * @return Returns a Taalvraag
	 */
	public Taalvraag getTaalvraag() {
		return taalvraag;
	}
	/**
	 * Sets the taalvraag
	 * @param taalvraag The taalvraag to set
	 */
	public void setTaalvraag(Taalvraag taalvraag) {
		this.taalvraag = taalvraag;
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


	/**
	 * Gets the notitie
	 * @return Returns a Notitie
	 */
	public Notitie getNotitie() {
		return notitie;
	}
	/**
	 * Sets the notitie
	 * @param notitie The notitie to set
	 */
	public void setNotitie(Notitie notitie) {
		this.notitie = notitie;
	}


	/**
	 * Gets the adm
	 * @return Returns a boolean
	 */
	public boolean getAdm() {
		return adm;
	}
	/**
	 * Sets the adm
	 * @param adm The adm to set
	 */
	public void setAdm(boolean adm) {
		this.adm = adm;
	}


}


