package be.vlaanderen.sbs.s6.taaladvies.beheer;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Naslagwerk;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;

public class NaslagwerkenBeheerForm extends ActionForm {

	/**
     * 
     */
    private static final long serialVersionUID = -3284631281211469985L;
	Naslagwerk naslagwerk = new Naslagwerk();
	java.util.ArrayList<Naslagwerk> naslagwerken = new java.util.ArrayList<Naslagwerk>();
	java.util.ArrayList<ParameterActief> bibliografien = new java.util.ArrayList<ParameterActief>();
	String useEopro = "false";
	
    public String getUseEopro() {
        return useEopro;
    }
    
    public void setUseEopro(String useEopro) {
        this.useEopro = useEopro;
    }
    
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();

		if (naslagwerk.getOmschrijving().length() < 1) {
			errors.add(ActionErrors.GLOBAL_ERROR,	new ActionError("beheer.naslagwerken.ontbreken.titel"));
		}
	
		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		naslagwerk = new Naslagwerk();
		naslagwerk.setActief(false);
	}


	/**
	 * Gets the naslagwerk
	 * @return Returns a Naslagwerk
	 */
	public Naslagwerk getNaslagwerk() {
		return naslagwerk;
	}
	/**
	 * Sets the naslagwerk
	 * @param naslagwerk The naslagwerk to set
	 */
	public void setNaslagwerk(Naslagwerk naslagwerk) {
		this.naslagwerk = naslagwerk;
	}
	
	/**
	 * Gets the naslagwerken
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Naslagwerk> getNaslagwerken() {
		return naslagwerken;
	}
	/**
	 * Sets the naslagwerken
	 * @param naslagwerken The naslagwerken to set
	 */
	public void setNaslagwerken(java.util.ArrayList<Naslagwerk> naslagwerken) {
		this.naslagwerken = naslagwerken;
	}

	/**
	 * Gets the bibliografien
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<ParameterActief> getBibliografien() {
		return bibliografien;
	}
	/**
	 * Sets the bibliografien
	 * @param bibliografien The bibliografien to set
	 */
	public void setBibliografien(java.util.ArrayList<ParameterActief> bibliografien) {
		this.bibliografien = bibliografien;
	}

}

