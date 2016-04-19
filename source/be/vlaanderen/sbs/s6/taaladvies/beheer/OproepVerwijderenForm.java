package be.vlaanderen.sbs.s6.taaladvies.beheer;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OproepVerwijderenForm extends ActionForm {

	/**
     * 
     */
    private static final long serialVersionUID = -1510240224884674790L;
	int id;


	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();

		if (id == 0) {
			errors.add(ActionErrors.GLOBAL_ERROR,	new ActionError("beheer.oproepopenenenverwijderen.ontbreken.id"));
		}

		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

	}		


	/**
	 * Gets the id
	 * @return Returns a int
	 */
	public int getId() {
		return id;
	}
	/**
	 * Sets the id
	 * @param id The id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


}


