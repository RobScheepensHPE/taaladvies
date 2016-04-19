package be.vlaanderen.sbs.s6.taaladvies.oproep;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Categorie;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;


public class CategorienInvoerForm extends ActionForm {


	/**
     * 
     */
    private static final long serialVersionUID = -6432048544877829755L;
	Categorie categorie = new Categorie();
	java.util.ArrayList<Categorie> categorienLinked = new java.util.ArrayList<Categorie>();
	java.util.ArrayList<Categorie> categorien = new java.util.ArrayList<Categorie>();
	Taalvraag taalvraag = null;
	Tekst tekst = null;
	boolean adm = false;

	/**
	 * Gets the categorienLinked
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Categorie> getCategorienLinked() {
		return categorienLinked;
	}
	/**
	 * Sets the categorienLinked
	 * @param categorienLinked The categorienLinked to set
	 */
	public void setCategorienLinked(java.util.ArrayList<Categorie> categorienLinked) {
		this.categorienLinked = categorienLinked;
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
	 * Gets the categorie
	 * @return Returns a Categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}
	/**
	 * Sets the categorie
	 * @param categorie The categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
		
		if (request.getParameter("Button") != null) {
			if (request.getParameter("Button").equals("Change")) {
				if (categorienLinked.size() > 3) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.categorien.maximum"));
				}
			}
		}
			
		
		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		categorie = new Categorie();
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


