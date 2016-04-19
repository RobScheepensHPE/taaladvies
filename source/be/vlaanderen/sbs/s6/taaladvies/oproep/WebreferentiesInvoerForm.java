package be.vlaanderen.sbs.s6.taaladvies.oproep;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.model.Webreferentie;


public class WebreferentiesInvoerForm extends ActionForm {


	/**
     * 
     */
    private static final long serialVersionUID = 7385335840153494503L;
	Webreferentie webreferentie = new Webreferentie();
	java.util.ArrayList<Webreferentie> webreferenties = new java.util.ArrayList<Webreferentie>();
	Taalvraag taalvraag = null;
	Tekst tekst = null;

	boolean adm = false;
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();

		if (request.getParameter("Annuleren.x") == null && request.getParameter("Button") == null) {
			if (webreferentie.getOmgeving().equals("")) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("webreferentie.ontbreken.omgeving"));
			}
		}
	
		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		webreferentie = new Webreferentie();
	}
	

	/**
	 * Gets the webreferenties
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Webreferentie> getWebreferenties() {
		return webreferenties;
	}
	/**
	 * Sets the webreferenties
	 * @param webreferenties The webreferenties to set
	 */
	public void setWebreferenties(java.util.ArrayList<Webreferentie> webreferenties) {
		this.webreferenties = webreferenties;
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
	 * Gets the webreferentie
	 * @return Returns a Webreferentie
	 */
	public Webreferentie getWebreferentie() {
		return webreferentie;
	}
	/**
	 * Sets the webreferentie
	 * @param webreferentie The webreferentie to set
	 */
	public void setWebreferentie(Webreferentie webreferentie) {
		this.webreferentie = webreferentie;
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


