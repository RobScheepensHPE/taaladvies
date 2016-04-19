package be.vlaanderen.sbs.s6.taaladvies;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class LoginForm extends ActionForm {

	/**
     *
     */
    private static final long serialVersionUID = 5090716141061414646L;
	private String login;
	private String paswoord;

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
		return errors;
	}

	/**
	 * Gets the login
	 * @return Returns a String
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * Sets the login
	 * @param login The login to set
	 */
	public void setLogin(String login) {

		this.login = login;
	}


	/**
	 * Gets the paswoord
	 * @return Returns a String
	 */
	public String getPaswoord() {
		return paswoord;
	}
	/**
	 * Sets the paswoord
	 * @param paswoord The paswoord to set
	 */
	public void setPaswoord(String paswoord) {
		this.paswoord = paswoord;
	}


}

