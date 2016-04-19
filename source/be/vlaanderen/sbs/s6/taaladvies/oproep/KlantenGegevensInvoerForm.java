package be.vlaanderen.sbs.s6.taaladvies.oproep;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Doelgroep;
import be.vlaanderen.sbs.s6.taaladvies.model.DoelgroepAlgemeen;
import be.vlaanderen.sbs.s6.taaladvies.model.Gemeente;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterBase;
import be.vlaanderen.sbs.s6.util.validation.REValidator;

public class KlantenGegevensInvoerForm extends ActionForm {

	/**
     * 
     */
    private static final long serialVersionUID = -8167739142024628692L;
	Oproep oproep = new Oproep();
	java.util.ArrayList<DoelgroepAlgemeen> doelgroepenAlgemeen = new java.util.ArrayList<DoelgroepAlgemeen>();
	java.util.ArrayList<Doelgroep> doelgroepen = new java.util.ArrayList<Doelgroep>();
	java.util.ArrayList<ParameterBase> landen = new java.util.ArrayList<ParameterBase>();
	java.util.ArrayList<Gemeente> gemeenten = new java.util.ArrayList<Gemeente>();
	java.util.ArrayList<ParameterBase> domeinen = new java.util.ArrayList<ParameterBase>();
	java.util.ArrayList<ParameterActief> herkomsten = new java.util.ArrayList<ParameterActief>();	
	
	boolean adm = false;	
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();

		if (request.getParameter("Zoek Gemeente.x") == null) 
		{			
			if (request.getParameter("Wijzigen.x") != null || request.getParameter("Button").equals("Overzicht")) {
				if (oproep.getGemeenteId() == 0)				
				{
					if(oproep.getGemeente() != null && (isFilledIn(oproep.getGemeente().getOmschrijving()) || isFilledIn(oproep.getGemeente().getPost())))
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.klant.gemeente.zoeken"));								
				}
				if (!oproep.getEmail().trim().equals("")) {
					if (!REValidator.validate("email", oproep.getEmail().trim())) {
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("global.error.validation.email"));			
					}
				}
			}
		}
		return errors;
	}

	private boolean isFilledIn(String check)
	{
		boolean isFilledIn = false;
		
		if(check != null)
		{
			if(!check.trim().equals(""))
			{
				isFilledIn = true;
			}
		}
		
		return isFilledIn;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		oproep = new Oproep();
	}


	/**
	 * Gets the oproep
	 * @return Returns a Oproep
	 */
	public Oproep getOproep() {
		return oproep;
	}
	/**
	 * Sets the oproep
	 * @param oproep The oproep to set
	 */
	public void setOproep(Oproep oproep) {
		this.oproep = oproep;
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
	 * Gets the landen
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<ParameterBase> getLanden() {
		return landen;
	}
	/**
	 * Sets the landen
	 * @param landen The landen to set
	 */
	public void setLanden(java.util.ArrayList<ParameterBase> landen) {
		this.landen = landen;
	}


	/**
	 * Gets the gemeenten
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Gemeente> getGemeenten() {
		return gemeenten;
	}
	/**
	 * Sets the gemeenten
	 * @param gemeenten The gemeenten to set
	 */
	public void setGemeenten(java.util.ArrayList<Gemeente> gemeenten) {
		this.gemeenten = gemeenten;
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


	/**
	 * Gets the herkomsten
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<ParameterActief> getHerkomsten() {
		return herkomsten;
	}
	/**
	 * Sets the herkomsten
	 * @param herkomsten The herkomsten to set
	 */
	public void setHerkomsten(java.util.ArrayList<ParameterActief> herkomsten) {
		this.herkomsten = herkomsten;
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
