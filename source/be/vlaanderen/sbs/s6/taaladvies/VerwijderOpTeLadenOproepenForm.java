package be.vlaanderen.sbs.s6.taaladvies;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class VerwijderOpTeLadenOproepenForm extends ActionForm
{
	/**
     * 
     */
    private static final long serialVersionUID = 6908805594661151040L;
	private String id;

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
		try {
		    if (id != null && !id.equals("")) {
			Integer.parseInt(id);
		    }
		} catch (NumberFormatException ex) {
		    errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("verwijderopteladenoproepen.geennummer"));
		    return errors;
		}
		return errors;
	}
	/**
	 * @return the id
	 */
	public String getId()
	{
	    return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id)
	{
	    this.id = id;
	}
}
