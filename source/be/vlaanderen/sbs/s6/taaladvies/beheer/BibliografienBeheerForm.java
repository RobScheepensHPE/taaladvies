package be.vlaanderen.sbs.s6.taaladvies.beheer;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;

public class BibliografienBeheerForm extends ActionForm {

	/**
     *
     */
    private static final long serialVersionUID = -289691893558576220L;
	ParameterActief bibliografie = new ParameterActief();
	java.util.ArrayList<ParameterActief> bibliografien = new java.util.ArrayList<ParameterActief>();


	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();

		if (bibliografie.getOmschrijving().length() < 1) {
			errors.add(ActionErrors.GLOBAL_ERROR,	new ActionError("beheer.bibliografien.ontbreken.bibliografie"));
		}

		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		bibliografie = new ParameterActief();
		bibliografie.setActief(false);
	}


	/**
	 * Gets the bibliografie
	 * @return Returns a ParameterActief
	 */
	public ParameterActief getBibliografie() {
		return bibliografie;
	}
	/**
	 * Sets the bibliografie
	 * @param medium The bibliografie to set
	 */
	public void setBibliografie(ParameterActief bibliografie) {
		this.bibliografie = bibliografie;
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

