package be.vlaanderen.sbs.s6.taaladvies.oproep;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Frequentie;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;


public class FrequentiesInvoerForm extends ActionForm {


	/**
     * 
     */
    private static final long serialVersionUID = 1126833376128327623L;
	Frequentie frequentie = new Frequentie();
	java.util.ArrayList<Frequentie> frequenties = new java.util.ArrayList<Frequentie>();
	java.util.ArrayList<ParameterActief> zoekomgevingen = new java.util.ArrayList<ParameterActief>();

	Taalvraag taalvraag = null;
	Tekst tekst = null;
	
	boolean adm = false;
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();

		if (request.getParameter("Annuleren.x") == null && request.getParameter("Button") == null) {
			if (frequentie.getZoekomgevingId() == 0) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("frequentie.ontbreken.zoekomgeving"));
			}			
		}
	
		return errors;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		frequentie = new Frequentie();
	}

	/**
	 * Gets the frequentie
	 * @return Returns a Frequentie
	 */
	public Frequentie getFrequentie() {
		return frequentie;
	}
	/**
	 * Sets the frequentie
	 * @param frequentie The frequentie to set
	 */
	public void setFrequentie(Frequentie frequentie) {
		this.frequentie = frequentie;
	}


	/**
	 * Gets the frequenties
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Frequentie> getFrequenties() {
		return frequenties;
	}
	/**
	 * Sets the frequenties
	 * @param frequenties The frequenties to set
	 */
	public void setFrequenties(java.util.ArrayList<Frequentie> frequenties) {
		this.frequenties = frequenties;
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


