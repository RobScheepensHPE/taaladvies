package be.vlaanderen.sbs.s6.taaladvies.oproep;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Bron;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;


public class BronnenInvoerForm extends ActionForm {


	/**
     * 
     */
    private static final long serialVersionUID = -7821392175098542637L;
	Bron bron = new Bron();
	java.util.ArrayList<Bron> bronnen = new java.util.ArrayList<Bron>();
	Taalvraag taalvraag = null;
	Tekst tekst = null;
	boolean adm = false;
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();

		if (request.getParameter("Annuleren.x") == null && request.getParameter("Button") == null) {
			if (bron.getTitel().trim().equals("")) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("bron.ontbreken.titel"));
			}
		}		
	
		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		bron = new Bron();
	}

	/**
	 * Gets the bronnen
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Bron> getBronnen() {
		return bronnen;
	}
	/**
	 * Sets the bronnen
	 * @param bronnen The bronnen to set
	 */
	public void setBronnen(java.util.ArrayList<Bron> bronnen) {
		this.bronnen = bronnen;
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
	 * Gets the bron
	 * @return Returns a Bron
	 */
	public Bron getBron() {
		return bron;
	}
	/**
	 * Sets the bron
	 * @param bron The bron to set
	 */
	public void setBron(Bron bron) {
		this.bron = bron;
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


