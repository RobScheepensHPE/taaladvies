package be.vlaanderen.sbs.s6.taaladvies.overzicht;

import org.apache.struts.action.ActionForm;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;

public class EigenTekstenForm extends ActionForm {

	/**
     * 
     */
    private static final long serialVersionUID = 2540887945432154602L;
	java.util.ArrayList<Oproep> oproepen = new java.util.ArrayList<Oproep>();
	int totalsize = 0;
	int currentindex = 0;
	
	/**
	 * Gets the oproepen
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Oproep> getOproepen() {
		return oproepen;
	}
	/**
	 * Sets the oproepen
	 * @param oproepen The oproepen to set
	 */
	public void setOproepen(java.util.ArrayList<Oproep> oproepen) {
		this.oproepen = oproepen;
	}

	/**
	 * Gets the totalsize
	 * @return Returns a int
	 */
	public int getTotalsize() {
		return totalsize;
	}
	/**
	 * Sets the totalsize
	 * @param totalsize The totalsize to set
	 */
	public void setTotalsize(int totalsize) {
		this.totalsize = totalsize;
	}


	/**
	 * Gets the currentindex
	 * @return Returns a int
	 */
	public int getCurrentindex() {
		return currentindex;
	}
	/**
	 * Sets the currentindex
	 * @param currentindex The currentindex to set
	 */
	public void setCurrentindex(int currentindex) {
		this.currentindex = currentindex;
	}


}


