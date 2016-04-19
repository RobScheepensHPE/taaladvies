package be.vlaanderen.sbs.s6.taaladvies.beheer;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;

public class HerkomstenBeheerForm extends ActionForm {

	/**
     * 
     */
    private static final long serialVersionUID = -4074727673222047936L;
	ParameterActief herkomst = new ParameterActief();
	java.util.ArrayList<ParameterActief> herkomsten = new java.util.ArrayList<ParameterActief>();


	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();

		if (herkomst.getOmschrijving().length() < 1) {
			errors.add(ActionErrors.GLOBAL_ERROR,	new ActionError("beheer.herkomsten.ontbreken.herkomst"));
		}
		
		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		herkomst = new ParameterActief();
		herkomst.setActief(false);
	}


	/**
	 * Gets the herkomst
	 * @return Returns a ParameterActief
	 */
	public ParameterActief getHerkomst() {
		return herkomst;
	}
	/**
	 * Sets the herkomst
	 * @param herkomst The herkomst to set
	 */
	public void setHerkomst(ParameterActief herkomst) {
		this.herkomst = herkomst;
	}
	
	/**
	 * Gets the herkomsten
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<ParameterActief> getHerkomsten() {
		return herkomsten;
	}
	/**
	 * Sets the herkomsten
	 * @param herkomsten The media to set
	 */
	public void setHerkomsten(java.util.ArrayList<ParameterActief> herkomsten) {
		this.herkomsten = herkomsten;
	}

}

