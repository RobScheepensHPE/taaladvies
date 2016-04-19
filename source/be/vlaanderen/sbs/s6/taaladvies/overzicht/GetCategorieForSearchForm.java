package be.vlaanderen.sbs.s6.taaladvies.overzicht;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Categorie;

public class GetCategorieForSearchForm extends ActionForm {

	/**
     * 
     */
    private static final long serialVersionUID = 562951200894068066L;
	Categorie categorie = new Categorie();
	java.util.ArrayList<Categorie> categorien = new java.util.ArrayList<Categorie>();
	
	String page;
	int whichCategorie;

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
	 * Gets the page
	 * @return Returns a String
	 */
	public String getPage() {
		return page;
	}
	/**
	 * Sets the page
	 * @param page The page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}


	/**
	 * Gets the whichCategorie
	 * @return Returns a int
	 */
	public int getWhichCategorie() {
		return whichCategorie;
	}
	/**
	 * Sets the whichCategorie
	 * @param whichCategorie The whichCategorie to set
	 */
	public void setWhichCategorie(int whichCategorie) {
		this.whichCategorie = whichCategorie;
	}

}


