package be.vlaanderen.sbs.s6.taaladvies.beheer;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;

public class RelevantiesBeheerForm extends ActionForm {

	/**
     * 
     */
    private static final long serialVersionUID = -3573627620423179841L;
	ParameterActief relevantie = new ParameterActief();
	java.util.ArrayList<ParameterActief> relevanties = new java.util.ArrayList<ParameterActief>();


	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();

		if (relevantie.getOmschrijving().length() < 1) {
			errors.add(ActionErrors.GLOBAL_ERROR,	new ActionError("beheer.relevanties.ontbreken.relevantie"));
		}
		
		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		relevantie = new ParameterActief();
		relevantie.setActief(false);
	}


	/**
	 * Gets the relevantie
	 * @return Returns a ParameterActief
	 */
	public ParameterActief getRelevantie() {
		return relevantie;
	}
	/**
	 * Sets the relevantie
	 * @param relevantie The relevantie to set
	 */
	public void setRelevantie(ParameterActief relevantie) {
		this.relevantie = relevantie;
	}
	
	/**
	 * Gets the relevanties
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<ParameterActief> getRelevanties() {
		return relevanties;
	}
	/**
	 * Sets the relevanties
	 * @param relevanties The relevanties to set
	 */
	public void setRelevanties(java.util.ArrayList<ParameterActief> relevanties) {
		this.relevanties = relevanties;
	}

}

