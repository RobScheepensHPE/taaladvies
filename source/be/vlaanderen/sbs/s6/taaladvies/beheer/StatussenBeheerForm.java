package be.vlaanderen.sbs.s6.taaladvies.beheer;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;

public class StatussenBeheerForm extends ActionForm {

	/**
     * 
     */
    private static final long serialVersionUID = 7289280517605440826L;
	ParameterActief status = new ParameterActief();
	java.util.ArrayList<ParameterActief> statussen = new java.util.ArrayList<ParameterActief>();


	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();

		if (status.getOmschrijving().length() < 1) {
			errors.add(ActionErrors.GLOBAL_ERROR,	new ActionError("beheer.statussen.ontbreken.status"));
		}
	
		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		status = new ParameterActief();
		status.setActief(false);
	}


	/**
	 * Gets the status
	 * @return Returns a ParameterActief
	 */
	public ParameterActief getStatus() {
		return status;
	}
	/**
	 * Sets the status
	 * @param status The status to set
	 */
	public void setStatus(ParameterActief status) {
		this.status = status;
	}
	
	/**
	 * Gets the statussen
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<ParameterActief> getStatussen() {
		return statussen;
	}
	/**
	 * Sets the statussen
	 * @param statussen The statussen to set
	 */
	public void setStatussen(java.util.ArrayList<ParameterActief> statussen) {
		this.statussen = statussen;
	}

}

