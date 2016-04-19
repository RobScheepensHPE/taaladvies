package be.vlaanderen.sbs.s6.taaladvies.beheer;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;

public class MediaBeheerForm extends ActionForm {

	/**
     * 
     */
    private static final long serialVersionUID = 1792283846365241143L;
	ParameterActief medium = new ParameterActief();
	java.util.ArrayList<ParameterActief> media = new java.util.ArrayList<ParameterActief>();


	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();

		if (medium.getOmschrijving().length() < 1) {
			errors.add(ActionErrors.GLOBAL_ERROR,	new ActionError("beheer.media.ontbreken.medium"));
		}

		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		medium = new ParameterActief();
		medium.setActief(false);
	}
		

	/**
	 * Gets the medium
	 * @return Returns a ParameterActief
	 */
	public ParameterActief getMedium() {
		return medium;
	}
	/**
	 * Sets the medium
	 * @param medium The medium to set
	 */
	public void setMedium(ParameterActief medium) {
		this.medium = medium;
	}
	
	/**
	 * Gets the media
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<ParameterActief> getMedia() {
		return media;
	}
	/**
	 * Sets the media
	 * @param media The media to set
	 */
	public void setMedia(java.util.ArrayList<ParameterActief> media) {
		this.media = media;
	}

}

