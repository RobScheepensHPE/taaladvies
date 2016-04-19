package be.vlaanderen.sbs.s6.taaladvies.oproep;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;


public class KenmerkenInvoerForm extends ActionForm {


	/**
     * 
     */
    private static final long serialVersionUID = 5678261031869539870L;
	Taalvraag taalvraag;
	java.util.ArrayList<ParameterActief> relevanties;
	
	boolean adm = false;
	
	/**
	 * Gets the taalvraag
	 * @return Returns a Taalvraag
	 */
	public Taalvraag getTaalvraag() {
		return taalvraag;
	}
	/**
	 * Sets the taalvraag
	 * @param taalvraag The taalvraag to set
	 */
	public void setTaalvraag(Taalvraag taalvraag) {
		this.taalvraag = taalvraag;
	}



	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
	
		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

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


	/**
	 * Gets the adm
	 * @return Returns a boolean
	 */
	public boolean getAdm() {
		return adm;
	}
	/**
	 * Sets the adm
	 * @param adm The adm to set
	 */
	public void setAdm(boolean adm) {
		this.adm = adm;
	}


}


