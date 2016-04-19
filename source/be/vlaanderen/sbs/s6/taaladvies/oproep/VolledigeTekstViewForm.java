package be.vlaanderen.sbs.s6.taaladvies.oproep;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekstblok;

public class VolledigeTekstViewForm extends ActionForm {

	/**
     * 
     */
    private static final long serialVersionUID = 454923750904257733L;
	Oproep oproep = new Oproep();
	Tekst tekst = new Tekst();
	java.util.ArrayList<Tekstblok> tekstblokken = new java.util.ArrayList<Tekstblok>();

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
		return errors;
	}


	public void reset(ActionMapping mapping, HttpServletRequest request) {
		oproep = new Oproep();
		tekst = new Tekst();
	}


	/**
	 * Gets the oproep
	 * @return Returns a Oproep
	 */
	public Oproep getOproep() {
		return oproep;
	}
	/**
	 * Sets the oproep
	 * @param oproep The oproep to set
	 */
	public void setOproep(Oproep oproep) {
		this.oproep = oproep;
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
	 * Gets the tekstblokken
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Tekstblok> getTekstblokken() {
		return tekstblokken;
	}
	/**
	 * Sets the tekstblokken
	 * @param tekstblokken The tekstblokken to set
	 */
	public void setTekstblokken(java.util.ArrayList<Tekstblok> tekstblokken) {
		this.tekstblokken = tekstblokken;
	}

}


