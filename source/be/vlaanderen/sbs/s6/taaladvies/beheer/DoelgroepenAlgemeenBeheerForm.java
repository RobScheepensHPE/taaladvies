package be.vlaanderen.sbs.s6.taaladvies.beheer;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.DoelgroepAlgemeen;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterBase;

public class DoelgroepenAlgemeenBeheerForm extends ActionForm {

	/**
     * 
     */
    private static final long serialVersionUID = -7977985971170534669L;
	DoelgroepAlgemeen doelgroepAlgemeen = new DoelgroepAlgemeen();
	java.util.ArrayList<DoelgroepAlgemeen> doelgroepenAlgemeen = new java.util.ArrayList<DoelgroepAlgemeen>();
	java.util.ArrayList<ParameterBase> domeinen = new java.util.ArrayList<ParameterBase>();
	

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
		
		if (request.getParameter("Button") == null) {
			if (doelgroepAlgemeen.getOmschrijving().length() < 1) {
				errors.add(ActionErrors.GLOBAL_ERROR,	new ActionError("beheer.doelgroepenalgemeen.ontbreken.doelgroepalgemeen"));
			}		
		}
			
		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		doelgroepAlgemeen = new DoelgroepAlgemeen();
		doelgroepAlgemeen.setActief(false);
	}


	/**
	 * Gets the doelgroepAlgemeen
	 * @return Returns a DoelgroepAlgemeen
	 */
	public DoelgroepAlgemeen getDoelgroepAlgemeen() {
		return doelgroepAlgemeen;
	}
	/**
	 * Sets the doelgroepAlgemeen
	 * @param doelgroepAlgemeen The doelgroepAlgemeen to set
	 */
	public void setDoelgroepAlgemeen(DoelgroepAlgemeen doelgroepAlgemeen) {
		this.doelgroepAlgemeen = doelgroepAlgemeen;
	}
	
	/**
	 * Gets the doelgroepenAlgemeen
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<DoelgroepAlgemeen> getDoelgroepenAlgemeen() {
		return doelgroepenAlgemeen;
	}
	/**
	 * Sets the doelgroepenAlgemeen
	 * @param doelgroepenAlgemeen The doelgroepenAlgemeen to set
	 */
	public void setDoelgroepenAlgemeen(java.util.ArrayList<DoelgroepAlgemeen> doelgroepenAlgemeen) {
		this.doelgroepenAlgemeen = doelgroepenAlgemeen;
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

