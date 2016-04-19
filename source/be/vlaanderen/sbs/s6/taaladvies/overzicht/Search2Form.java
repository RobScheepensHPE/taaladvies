package be.vlaanderen.sbs.s6.taaladvies.overzicht;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterBase;

public class Search2Form extends ActionForm {

	/**
     *
     */
    private static final long serialVersionUID = -1105922936700328620L;
	private java.util.ArrayList<ParameterBase> gemeenten = new java.util.ArrayList<ParameterBase>();
	private java.util.ArrayList<ParameterBase> landen = new java.util.ArrayList<ParameterBase>();
	private java.util.ArrayList<ParameterActief> media = new java.util.ArrayList<ParameterActief>();
	private java.util.ArrayList<ParameterActief> herkomsten = new java.util.ArrayList<ParameterActief>();

	private Oproep oproep = new Oproep();

	private boolean eigenTV = true;
	private boolean eigenTXT = true;
	private boolean alleTV = true;
	private boolean alleTXT = true;

	private String datum1_DD;
	private String datum1_MM;
	private String datum1_YYYY;

	private String datum2_DD;
	private String datum2_MM;
	private String datum2_YYYY;

	private String datum3_DD;
	private String datum3_MM;
	private String datum3_YYYY;

	//private String datum1;
	//private String datum2;
	//private String datum3;

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		oproep = new Oproep();
		oproep.setMediumId(0);
		oproep.getDistributie().setMediumId(0);
		eigenTV = false;
		eigenTXT = false;
		alleTV = false;
		alleTXT = false;
		//datum1 = new String();
		//datum2 = new String();
		//datum3 = new String();
	}

	/**
	 * Gets the gemeenten
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<ParameterBase> getGemeenten() {
		return gemeenten;
	}
	/**
	 * Sets the gemeenten
	 * @param gemeenten The gemeenten to set
	 */
	public void setGemeenten(java.util.ArrayList<ParameterBase> gemeenten) {
		this.gemeenten = gemeenten;
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
	 * Gets the eigenTV
	 * @return Returns a boolean
	 */
	public boolean getEigenTV() {
		return eigenTV;
	}
	/**
	 * Sets the eigenTV
	 * @param eigenTV The eigenTV to set
	 */
	public void setEigenTV(boolean eigenTV) {
		this.eigenTV = eigenTV;
	}


	/**
	 * Gets the eigenTXT
	 * @return Returns a boolean
	 */
	public boolean getEigenTXT() {
		return eigenTXT;
	}
	/**
	 * Sets the eigenTXT
	 * @param eigenTXT The eigenTXT to set
	 */
	public void setEigenTXT(boolean eigenTXT) {
		this.eigenTXT = eigenTXT;
	}


	/**
	 * Gets the alleTV
	 * @return Returns a boolean
	 */
	public boolean getAlleTV() {
		return alleTV;
	}
	/**
	 * Sets the alleTV
	 * @param alleTV The alleTV to set
	 */
	public void setAlleTV(boolean alleTV) {
		this.alleTV = alleTV;
	}


	/**
	 * Gets the alleTXT
	 * @return Returns a boolean
	 */
	public boolean getAlleTXT() {
		return alleTXT;
	}
	/**
	 * Sets the alleTXT
	 * @param alleTXT The alleTXT to set
	 */
	public void setAlleTXT(boolean alleTXT) {
		this.alleTXT = alleTXT;
	}


	/**
	 * Gets the datum1
	 * @return Returns a String
	 */
	public String getDatum1() {
		return datum1_DD + "-" + datum1_MM + "-" + datum1_YYYY;
	}
	/**
	 * Sets the datum1
	 * @param datum1 The datum1 to set
	 */
	public void setDatum1(String datum1) {
		//this.datum1 = datum1;
	}


	/**
	 * Gets the datum3
	 * @return Returns a String
	 */
	public String getDatum3() {
		return datum3_DD + "-" + datum3_MM + "-" + datum3_YYYY;
	}
	/**
	 * Sets the datum3
	 * @param datum3 The datum3 to set
	 */
	public void setDatum3(String datum3) {
		//this.datum3 = datum3;
	}


	/**
	 * Gets the datum2
	 * @return Returns a String
	 */
	public String getDatum2() {
		return datum2_DD + "-" + datum2_MM + "-" + datum2_YYYY;
	}
	/**
	 * Sets the datum2
	 * @param datum2 The datum2 to set
	 */
	public void setDatum2(String datum2) {
		//this.datum2 = datum2;
	}


	/**
	 * Gets the datum1_DD
	 * @return Returns a String
	 */
	public String getDatum1_DD() {
		return datum1_DD;
	}
	/**
	 * Sets the datum1_DD
	 * @param datum1_DD The datum1_DD to set
	 */
	public void setDatum1_DD(String datum1_DD) {
		this.datum1_DD = datum1_DD;
	}


	/**
	 * Gets the datum1_MM
	 * @return Returns a String
	 */
	public String getDatum1_MM() {
		return datum1_MM;
	}
	/**
	 * Sets the datum1_MM
	 * @param datum1_MM The datum1_MM to set
	 */
	public void setDatum1_MM(String datum1_MM) {
		this.datum1_MM = datum1_MM;
	}


	/**
	 * Gets the datum1_YYYY
	 * @return Returns a String
	 */
	public String getDatum1_YYYY() {
		return datum1_YYYY;
	}
	/**
	 * Sets the datum1_YYYY
	 * @param datum1_YYYY The datum1_YYYY to set
	 */
	public void setDatum1_YYYY(String datum1_YYYY) {
		this.datum1_YYYY = datum1_YYYY;
	}


	/**
	 * Gets the datum2_DD
	 * @return Returns a String
	 */
	public String getDatum2_DD() {
		return datum2_DD;
	}
	/**
	 * Sets the datum2_DD
	 * @param datum2_DD The datum2_DD to set
	 */
	public void setDatum2_DD(String datum2_DD) {
		this.datum2_DD = datum2_DD;
	}


	/**
	 * Gets the datum2_MM
	 * @return Returns a String
	 */
	public String getDatum2_MM() {
		return datum2_MM;
	}
	/**
	 * Sets the datum2_MM
	 * @param datum2_MM The datum2_MM to set
	 */
	public void setDatum2_MM(String datum2_MM) {
		this.datum2_MM = datum2_MM;
	}


	/**
	 * Gets the datum2_YYYY
	 * @return Returns a String
	 */
	public String getDatum2_YYYY() {
		return datum2_YYYY;
	}
	/**
	 * Sets the datum2_YYYY
	 * @param datum2_YYYY The datum2_YYYY to set
	 */
	public void setDatum2_YYYY(String datum2_YYYY) {
		this.datum2_YYYY = datum2_YYYY;
	}


	/**
	 * Gets the datum3_DD
	 * @return Returns a String
	 */
	public String getDatum3_DD() {
		return datum3_DD;
	}
	/**
	 * Sets the datum3_DD
	 * @param datum3_DD The datum3_DD to set
	 */
	public void setDatum3_DD(String datum3_DD) {
		this.datum3_DD = datum3_DD;
	}


	/**
	 * Gets the datum3_MM
	 * @return Returns a String
	 */
	public String getDatum3_MM() {
		return datum3_MM;
	}
	/**
	 * Sets the datum3_MM
	 * @param datum3_MM The datum3_MM to set
	 */
	public void setDatum3_MM(String datum3_MM) {
		this.datum3_MM = datum3_MM;
	}


	/**
	 * Gets the datum3_YYYY
	 * @return Returns a String
	 */
	public String getDatum3_YYYY() {
		return datum3_YYYY;
	}
	/**
	 * Sets the datum3_YYYY
	 * @param datum3_YYYY The datum3_YYYY to set
	 */
	public void setDatum3_YYYY(String datum3_YYYY) {
		this.datum3_YYYY = datum3_YYYY;
	}


}


