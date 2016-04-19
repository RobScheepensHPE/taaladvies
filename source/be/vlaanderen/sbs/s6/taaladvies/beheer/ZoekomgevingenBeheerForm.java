package be.vlaanderen.sbs.s6.taaladvies.beheer;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;

public class ZoekomgevingenBeheerForm extends ActionForm {

	/**
     * 
     */
    private static final long serialVersionUID = 7511636159805765642L;
	ParameterActief zoekomgeving = new ParameterActief();
	java.util.ArrayList<ParameterActief> zoekomgevingen = new java.util.ArrayList<ParameterActief>();


	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();

		if (zoekomgeving.getOmschrijving().length() < 1) {
			errors.add(ActionErrors.GLOBAL_ERROR,	new ActionError("beheer.zoekomgevingen.ontbreken.zoekomgeving"));
		}
	
		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		zoekomgeving = new ParameterActief();
		zoekomgeving.setActief(false);
	}


	/**
	 * Gets the zoekomgeving
	 * @return Returns a ParameterActief
	 */
	public ParameterActief getZoekomgeving() {
		return zoekomgeving;
	}
	/**
	 * Sets the zoekomgeving
	 * @param zoekomgeving The zoekomgeving to set
	 */
	public void setZoekomgeving(ParameterActief zoekomgeving) {
		this.zoekomgeving = zoekomgeving;
	}
	
	/**
	 * Gets the zoekomgevingen
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<ParameterActief> getZoekomgevingen() {
		return zoekomgevingen;
	}
	/**
	 * Sets the zoekomgevingen
	 * @param zoekomgevingen The zoekomgevingen to set
	 */
	public void setZoekomgevingen(java.util.ArrayList<ParameterActief> zoekomgevingen) {
		this.zoekomgevingen = zoekomgevingen;
	}

}

