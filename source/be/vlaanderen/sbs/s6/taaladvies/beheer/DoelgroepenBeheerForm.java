package be.vlaanderen.sbs.s6.taaladvies.beheer;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Doelgroep;
import be.vlaanderen.sbs.s6.taaladvies.model.DoelgroepAlgemeen;

public class DoelgroepenBeheerForm extends ActionForm {

	/**
     * 
     */
    private static final long serialVersionUID = 6465358914321293627L;
	Doelgroep doelgroep = new Doelgroep();
	java.util.ArrayList<Doelgroep> doelgroepen = new java.util.ArrayList<Doelgroep>();
	java.util.ArrayList<DoelgroepAlgemeen> doelgroepenAlgemeen = new java.util.ArrayList<DoelgroepAlgemeen>();

	
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
	
		if (request.getParameter("Button") == null) {
			if (doelgroep.getOmschrijving().length() < 1) {
				errors.add(ActionErrors.GLOBAL_ERROR,	new ActionError("beheer.doelgroepen.ontbreken.doelgroep"));
			}
		}
	
		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		doelgroep = new Doelgroep();
		doelgroep.setActief(false);
	}


	/**
	 * Gets the doelgroep
	 * @return Returns a Doelgroep
	 */
	public Doelgroep getDoelgroep() {
		return doelgroep;
	}
	/**
	 * Sets the doelgroep
	 * @param doelgroep The doelgroep to set
	 */
	public void setDoelgroep(Doelgroep doelgroep) {
		this.doelgroep = doelgroep;
	}
	
	/**
	 * Gets the doelgroepen
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Doelgroep> getDoelgroepen() {
		return doelgroepen;
	}
	/**
	 * Sets the doelgroepen
	 * @param doelgroepen The doelgroepen to set
	 */
	public void setDoelgroepen(java.util.ArrayList<Doelgroep> doelgroepen) {
		this.doelgroepen = doelgroepen;
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


}

