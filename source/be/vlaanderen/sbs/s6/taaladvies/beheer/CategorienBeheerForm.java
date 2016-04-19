package be.vlaanderen.sbs.s6.taaladvies.beheer;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Categorie;
import be.vlaanderen.sbs.s6.util.validation.REValidator;

public class CategorienBeheerForm extends ActionForm {

	/**
     * 
     */
    private static final long serialVersionUID = 4070898530371048765L;
	Categorie parentCategorie = new Categorie();
	Categorie categorie = new Categorie();
	java.util.ArrayList<Categorie> categorien = new java.util.ArrayList<Categorie>();


	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
		
		if (request.getParameter("Wijzigen.x") != null || request.getParameter("Toevoegen.x") != null) 
		{
			if (categorie.getOmschrijving().length() < 1) 
			{
				errors.add(ActionErrors.GLOBAL_ERROR,	new ActionError("beheer.categorien.ontbreken.categorie"));
			}						
			
			if (categorie.getOwnNummer().length() < 1) 
			{				
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("beheer.categorien.ontbreken.nummer"));
			}
			else if(!REValidator.validate("positiveInteger",categorie.getOwnNummer()))
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("beheer.categorien.ontbreken.nummer"));
			}
		}
				
		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		categorie = new Categorie();
		categorie.setActief(false);
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
	

	/**
	 * Gets the categoriën
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Categorie> getCategorien() {
		return categorien;
	}
	/**
	 * Sets the categoriën
	 * @param categorien The categoriën to set
	 */
	public void setCategorien(java.util.ArrayList<Categorie> categorien) {
		this.categorien = categorien;
	}


	/**
	 * Gets the parentCategorie
	 * @return Returns a Categorie
	 */
	public Categorie getParentCategorie() {
		return parentCategorie;
	}
	/**
	 * Sets the parentCategorie
	 * @param parentCategorie The parentCategorie to set
	 */
	public void setParentCategorie(Categorie parentCategorie) {
		this.parentCategorie = parentCategorie;
	}


}

