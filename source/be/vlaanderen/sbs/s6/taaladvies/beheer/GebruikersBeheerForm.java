package be.vlaanderen.sbs.s6.taaladvies.beheer;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Gebruiker;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterBase;
import be.vlaanderen.sbs.s6.taaladvies.model.Toegangsrecht;
import be.vlaanderen.sbs.s6.util.validation.REValidator;

public class GebruikersBeheerForm extends ActionForm {

	/**
     * 
     */
    private static final long serialVersionUID = 3754260000530485603L;
	Gebruiker gebruiker = new Gebruiker();
	java.util.ArrayList<Gebruiker> gebruikers = new java.util.ArrayList<Gebruiker>();
	java.util.ArrayList<Toegangsrecht> toegangsrechten = new java.util.ArrayList<Toegangsrecht>();
	java.util.ArrayList<ParameterBase> domeinen = new java.util.ArrayList<ParameterBase>();
	

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		
		ActionErrors errors = new ActionErrors();
		
		if (gebruiker.getNaam().length() < 1) {
			errors.add(ActionErrors.GLOBAL_ERROR,	new ActionError("beheer.gebruikers.ontbreken.naam"));
		}
		if (gebruiker.getLogin().length() < 1) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("beheer.gebruikers.ontbreken.login"));
		}
		if (gebruiker.getEmail().length() > 0) {
			if (!REValidator.validate("email", gebruiker.getEmail())) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("global.error.validation.email"));
			}
		}
		
		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		gebruiker = new Gebruiker();
		gebruiker.setActief(false);
	}


	/**
	 * Gets the gebruiker
	 * @return Returns a Gebruiker
	 */
	public Gebruiker getGebruiker() {
		return gebruiker;
	}
	/**
	 * Sets the gebruiker
	 * @param gebruiker The gebruiker to set
	 */
	public void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}
	
	/**
	 * Gets the gebruikers
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Gebruiker> getGebruikers() {
		return gebruikers;
	}
	/**
	 * Sets the gebruikers
	 * @param gebruikers The gebruikers to set
	 */
	public void setGebruikers(java.util.ArrayList<Gebruiker> gebruikers) {
		this.gebruikers = gebruikers;
	}

	/**
	 * Gets the toegangsrechten
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Toegangsrecht> getToegangsrechten() {
		return toegangsrechten;
	}
	/**
	 * Sets the toegangsrechten
	 * @param toegangsrechten The toegangsrechten to set
	 */
	public void setToegangsrechten(java.util.ArrayList<Toegangsrecht> toegangsrechten) {
		this.toegangsrechten = toegangsrechten;
	}

	/**
	 * Gets the domeinen
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<ParameterBase> getDomeinen() {
		return domeinen;
	}
	/**
	 * Sets the domeinen
	 * @param domeinen The domeinen to set
	 */
	public void setDomeinen(java.util.ArrayList<ParameterBase> domeinen) {
		this.domeinen = domeinen;
	}

}

