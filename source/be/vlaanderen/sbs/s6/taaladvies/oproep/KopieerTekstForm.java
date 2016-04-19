package be.vlaanderen.sbs.s6.taaladvies.oproep;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekstblok;


public class KopieerTekstForm extends ActionForm {

	/**
     *
     */
    private static final long serialVersionUID = -7958639643411940836L;
	private boolean check_Titel = true;
	private boolean check_Categorie;
	private boolean check_Naslagreferentie;
	private boolean check_Bron;
	private boolean check_Citaat;
	private boolean check_Frequentie;
	private boolean check_Webreferentie;
	private boolean check_Notitie;

	private Tekst tekst;
	private java.util.ArrayList<Tekstblok> tekstblokken = new java.util.ArrayList<Tekstblok>();

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
		return errors;
	}


	public void reset(ActionMapping mapping, HttpServletRequest request) {
		check_Titel = false;
System.out.println("before reset loop:");
System.out.println("length: " + tekstblokken.size());
		for (int i = 0, j = tekstblokken.size(); i < j; i++) {
			Tekstblok blok = tekstblokken.get(i);
			blok.setCheck_Tekstblok(false);
			blok.setCheck_Titel(false);
		}
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
	public Tekstblok getTekstblok(int index) {
		while (tekstblokken.size() <= index)
			tekstblokken.add(new Tekstblok());
		return tekstblokken.get(index);
	}

	/**
	 * Sets the tekstblokken
	 * @param tekstblokken The tekstblokken to set
	 */
	public void setTekstblok(int index, Tekstblok newTekstblok) {
		tekstblokken.set(index, newTekstblok);
	}


}


