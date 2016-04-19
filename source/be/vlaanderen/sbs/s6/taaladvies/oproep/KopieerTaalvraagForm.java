package be.vlaanderen.sbs.s6.taaladvies.oproep;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;

public class KopieerTaalvraagForm extends ActionForm {
	
	/**
     * 
     */
    private static final long serialVersionUID = -4751928135458188644L;
	private boolean check_Titel = true;
	private boolean check_Vraag;
	private boolean check_Antwoord = true;
	private boolean check_Toelichting = true;
	private boolean check_Bijzonderheid = true;
	private boolean check_Categorie;
	private boolean check_Naslagreferentie;
	private boolean check_Bron;
	private boolean check_Citaat;
	private boolean check_Frequentie;
	private boolean check_Webreferentie;
	private boolean check_Notitie;
	private boolean check_Teksten;
	
	private Taalvraag taalvraag;
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		check_Titel = false;
		check_Vraag = false;
		check_Antwoord = false;
		check_Toelichting = false;
		check_Bijzonderheid = false;
	}

	/**
	 * Gets the check_Titel
	 * @return Returns a boolean
	 */
	public boolean getCheck_Titel() {
		return check_Titel;
	}
	/**
	 * Sets the check_Titel
	 * @param check_Titel The check_Titel to set
	 */
	public void setCheck_Titel(boolean check_Titel) {
		this.check_Titel = check_Titel;
	}


	/**
	 * Gets the check_Vraag
	 * @return Returns a boolean
	 */
	public boolean getCheck_Vraag() {
		return check_Vraag;
	}
	/**
	 * Sets the check_Vraag
	 * @param check_Vraag The check_Vraag to set
	 */
	public void setCheck_Vraag(boolean check_Vraag) {
		this.check_Vraag = check_Vraag;
	}


	/**
	 * Gets the check_Antwoord
	 * @return Returns a boolean
	 */
	public boolean getCheck_Antwoord() {
		return check_Antwoord;
	}
	/**
	 * Sets the check_Antwoord
	 * @param check_Antwoord The check_Antwoord to set
	 */
	public void setCheck_Antwoord(boolean check_Antwoord) {
		this.check_Antwoord = check_Antwoord;
	}


	/**
	 * Gets the check_Toelichting
	 * @return Returns a boolean
	 */
	public boolean getCheck_Toelichting() {
		return check_Toelichting;
	}
	/**
	 * Sets the check_Toelichting
	 * @param check_Toelichting The check_Toelichting to set
	 */
	public void setCheck_Toelichting(boolean check_Toelichting) {
		this.check_Toelichting = check_Toelichting;
	}


	/**
	 * Gets the check_Bijzonderheid
	 * @return Returns a boolean
	 */
	public boolean getCheck_Bijzonderheid() {
		return check_Bijzonderheid;
	}
	/**
	 * Sets the check_Bijzonderheid
	 * @param check_Bijzonderheid The check_Bijzonderheid to set
	 */
	public void setCheck_Bijzonderheid(boolean check_Bijzonderheid) {
		this.check_Bijzonderheid = check_Bijzonderheid;
	}


	/**
	 * Gets the check_Categorie
	 * @return Returns a boolean
	 */
	public boolean getCheck_Categorie() {
		return check_Categorie;
	}
	/**
	 * Sets the check_Categorie
	 * @param check_Categorie The check_Categorie to set
	 */
	public void setCheck_Categorie(boolean check_Categorie) {
		this.check_Categorie = check_Categorie;
	}


	/**
	 * Gets the check_Naslagreferentie
	 * @return Returns a boolean
	 */
	public boolean getCheck_Naslagreferentie() {
		return check_Naslagreferentie;
	}
	/**
	 * Sets the check_Naslagreferentie
	 * @param check_Naslagreferentie The check_Naslagreferentie to set
	 */
	public void setCheck_Naslagreferentie(boolean check_Naslagreferentie) {
		this.check_Naslagreferentie = check_Naslagreferentie;
	}


	/**
	 * Gets the check_Bron
	 * @return Returns a boolean
	 */
	public boolean getCheck_Bron() {
		return check_Bron;
	}
	/**
	 * Sets the check_Bron
	 * @param check_Bron The check_Bron to set
	 */
	public void setCheck_Bron(boolean check_Bron) {
		this.check_Bron = check_Bron;
	}


	/**
	 * Gets the check_Citaat
	 * @return Returns a boolean
	 */
	public boolean getCheck_Citaat() {
		return check_Citaat;
	}
	/**
	 * Sets the check_Citaat
	 * @param check_Citaat The check_Citaat to set
	 */
	public void setCheck_Citaat(boolean check_Citaat) {
		this.check_Citaat = check_Citaat;
	}


	/**
	 * Gets the check_Frequentie
	 * @return Returns a boolean
	 */
	public boolean getCheck_Frequentie() {
		return check_Frequentie;
	}
	/**
	 * Sets the check_Frequentie
	 * @param check_Frequentie The check_Frequentie to set
	 */
	public void setCheck_Frequentie(boolean check_Frequentie) {
		this.check_Frequentie = check_Frequentie;
	}


	/**
	 * Gets the check_Webreferentie
	 * @return Returns a boolean
	 */
	public boolean getCheck_Webreferentie() {
		return check_Webreferentie;
	}
	/**
	 * Sets the check_Webreferentie
	 * @param check_Webreferentie The check_Webreferentie to set
	 */
	public void setCheck_Webreferentie(boolean check_Webreferentie) {
		this.check_Webreferentie = check_Webreferentie;
	}


	/**
	 * Gets the check_Notitie
	 * @return Returns a boolean
	 */
	public boolean getCheck_Notitie() {
		return check_Notitie;
	}
	/**
	 * Sets the check_Notitie
	 * @param check_Notitie The check_Notitie to set
	 */
	public void setCheck_Notitie(boolean check_Notitie) {
		this.check_Notitie = check_Notitie;
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
	 * Gets the check_Teksten
	 * @return Returns a boolean
	 */
	public boolean getCheck_Teksten() {
		return check_Teksten;
	}
	/**
	 * Sets the check_Teksten
	 * @param check_Teksten The check_Teksten to set
	 */
	public void setCheck_Teksten(boolean check_Teksten) {
		this.check_Teksten = check_Teksten;
	}


}


