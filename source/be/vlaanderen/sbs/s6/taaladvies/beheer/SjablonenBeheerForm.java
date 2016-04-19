package be.vlaanderen.sbs.s6.taaladvies.beheer;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Sjabloon;

public class SjablonenBeheerForm extends ActionForm {

	/**
     * 
     */
    private static final long serialVersionUID = 3958836595878865972L;
	Sjabloon sjabloon = new Sjabloon();
	java.util.ArrayList<Sjabloon> sjablonen = new java.util.ArrayList<Sjabloon>();
	String useEopro = "false";


    public String getUseEopro() {
        return useEopro;
    }
    
    public void setUseEopro(String useEopro) {
        this.useEopro = useEopro;
    }
    
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();

		if (sjabloon.getOmschrijving().length() < 1) {
			errors.add(ActionErrors.GLOBAL_ERROR,	new ActionError("beheer.sjablonen.ontbreken.sjabloon"));
		}
	
		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		sjabloon = new Sjabloon();
		sjabloon.setActief(false);
	}


	/**
	 * Gets the sjabloon
	 * @return Returns a Sjabloon
	 */
	public Sjabloon getSjabloon() {
		return sjabloon;
	}
	/**
	 * Sets the sjabloon
	 * @param sjabloon The sjabloon to set
	 */
	public void setSjabloon(Sjabloon sjabloon) {
		this.sjabloon = sjabloon;
	}
	
	/**
	 * Gets the sjablonen
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Sjabloon> getSjablonen() {
		return sjablonen;
	}
	/**
	 * Sets the sjablonen
	 * @param sjablonen The media to set
	 */
	public void setSjablonen(java.util.ArrayList<Sjabloon> sjablonen) {
		this.sjablonen = sjablonen;
	}

}

