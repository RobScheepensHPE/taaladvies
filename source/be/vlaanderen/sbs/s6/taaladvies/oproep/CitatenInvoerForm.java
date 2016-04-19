package be.vlaanderen.sbs.s6.taaladvies.oproep;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Citaat;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;


public class CitatenInvoerForm extends ActionForm {


	/**
     * 
     */
    private static final long serialVersionUID = -197473209162135230L;
	Citaat citaat = new Citaat();
	java.util.ArrayList<Citaat> citaten = new java.util.ArrayList<Citaat>();
	java.util.ArrayList<ParameterActief> zoekomgevingen = new java.util.ArrayList<ParameterActief>();

	Taalvraag taalvraag = null;
	Tekst tekst = null;
	
	boolean adm = false;
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();

		if (request.getParameter("Annuleren.x") == null && request.getParameter("Button") == null) {
			if (citaat.getZoekomgevingId() == 0) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("citaat.ontbreken.zoekomgeving"));
			}			
		}
	
		return errors;
	}


	public void reset(ActionMapping mapping, HttpServletRequest request) {
		citaat = new Citaat();
	}


	/**
	 * Gets the citaat
	 * @return Returns a Citaat
	 */
	public Citaat getCitaat() {
		return citaat;
	}
	/**
	 * Sets the citaat
	 * @param citaat The citaat to set
	 */
	public void setCitaat(Citaat citaat) {
		this.citaat = citaat;
	}


	/**
	 * Gets the citaten
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Citaat> getCitaten() {
		return citaten;
	}
	/**
	 * Sets the citaten
	 * @param citaten The citaten to set
	 */
	public void setCitaten(java.util.ArrayList<Citaat> citaten) {
		this.citaten = citaten;
	}


	/**
	 * Gets the zoekomgevingen
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<ParameterActief> getZoekomgevingen() {
		return zoekomgevingen;
	}
	/**
	 * Sets the zoekomgevingen
	 * @param zoekomgevingen The zoekomgevingen to set
	 */
	public void setZoekomgevingen(java.util.ArrayList<ParameterActief> zoekomgevingen) {
		this.zoekomgevingen = zoekomgevingen;
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


