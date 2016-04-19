package be.vlaanderen.sbs.s6.taaladvies.oproep;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Bron;
import be.vlaanderen.sbs.s6.taaladvies.model.Categorie;
import be.vlaanderen.sbs.s6.taaladvies.model.Citaat;
import be.vlaanderen.sbs.s6.taaladvies.model.Frequentie;
import be.vlaanderen.sbs.s6.taaladvies.model.Naslagreferentie;
import be.vlaanderen.sbs.s6.taaladvies.model.Notitie;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.model.Webreferentie;

public class HulpmiddelenOverzichtForm extends ActionForm {

	
	/**
     * 
     */
    private static final long serialVersionUID = 6450185695343718463L;
	private java.util.ArrayList<Frequentie> frequenties = new java.util.ArrayList<Frequentie>();
	private java.util.ArrayList<Webreferentie> webreferenties = new java.util.ArrayList<Webreferentie>();
	private java.util.ArrayList<Bron> bronnen = new java.util.ArrayList<Bron>();
	private java.util.ArrayList<Citaat> citaten = new java.util.ArrayList<Citaat>();
	private java.util.ArrayList<Naslagreferentie> naslagreferenties = new java.util.ArrayList<Naslagreferentie>();
	private java.util.ArrayList<Notitie> notities = new java.util.ArrayList<Notitie>();
	private java.util.ArrayList<Categorie> categorien = new java.util.ArrayList<Categorie>();
	private Taalvraag taalvraag;
	private Tekst tekst;
	
	private boolean adm = false;
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
		return errors;
	}


	public void reset(ActionMapping mapping, HttpServletRequest request) {
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
	 * Gets the categorien
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Categorie> getCategorien() {
		return categorien;
	}
	/**
	 * Sets the categorien
	 * @param categorien The categorien to set
	 */
	public void setCategorien(java.util.ArrayList<Categorie> categorien) {
		this.categorien = categorien;
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


