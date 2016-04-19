package be.vlaanderen.sbs.s6.taaladvies.oproep;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Naslagreferentie;
import be.vlaanderen.sbs.s6.taaladvies.model.Naslagwerk;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;

public class NaslagreferentiesInvoerForm extends ActionForm {

	/**
     * 
     */
    private static final long serialVersionUID = 967857296065026881L;
	Naslagreferentie naslagreferentie = new Naslagreferentie();
	java.util.ArrayList<Naslagwerk> naslagwerken = new java.util.ArrayList<Naslagwerk>();
	java.util.ArrayList<ParameterActief> bibliografien = new java.util.ArrayList<ParameterActief>();
	java.util.ArrayList<Naslagreferentie> naslagreferenties = new java.util.ArrayList<Naslagreferentie>();
	Taalvraag taalvraag = null;
	Tekst tekst = null;
	
	boolean adm = false;	
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
		
		if (request.getParameter("Annuleren.x") == null && request.getParameter("Button") == null) {
			if (naslagreferentie.getNaslagwerkId() == 0) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("naslagreferentie.ontbreken.naslagwerk"));
			}			
		}
		return errors;
	}
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		naslagreferentie = new Naslagreferentie();
	}	
	
	/**
	 * Gets the naslagreferentie
	 * @return Returns a Naslagreferentie
	 */
	public Naslagreferentie getNaslagreferentie() {
		return naslagreferentie;
	}
	/**
	 * Sets the naslagreferentie
	 * @param naslagreferentie The naslagreferentie to set
	 */
	public void setNaslagreferentie(Naslagreferentie naslagreferentie) {
		this.naslagreferentie = naslagreferentie;
	}
	
	/**
	 * Gets the naslagwerken
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Naslagwerk> getNaslagwerken() {
		return naslagwerken;
	}
	/**
	 * Sets the naslagwerken
	 * @param naslagwerken The naslagwerken to set
	 */
	public void setNaslagwerken(java.util.ArrayList<Naslagwerk> naslagwerken) {
		this.naslagwerken = naslagwerken;
	}

	/**
	 * Gets the bibliografien
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<ParameterActief> getBibliografien() {
		return bibliografien;
	}
	/**
	 * Sets the bibliografien
	 * @param bibliografien The bibliografien to set
	 */
	public void setBibliografien(java.util.ArrayList<ParameterActief> bibliografien) {
		this.bibliografien = bibliografien;
	}


	/**
	 * Gets the naslagreferenties
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Naslagreferentie> getNaslagreferenties() {
		return naslagreferenties;
	}
	/**
	 * Sets the naslagreferenties
	 * @param naslagreferenties The naslagreferenties to set
	 */
	public void setNaslagreferenties(java.util.ArrayList<Naslagreferentie> naslagreferenties) {
		this.naslagreferenties = naslagreferenties;
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

