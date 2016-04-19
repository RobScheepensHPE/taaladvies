package be.vlaanderen.sbs.s6.taaladvies.overzicht;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;

public class Result2Form extends ActionForm {

	/**
     * 
     */
    private static final long serialVersionUID = -5887081071418948041L;
	java.util.ArrayList<Oproep> oproepen1 = new java.util.ArrayList<Oproep>();
	int totalsize1 = 0;
	int currentindex1 = 0;
	
	java.util.ArrayList<Oproep> oproepen2 = new java.util.ArrayList<Oproep>();
	int totalsize2 = 0;
	int currentindex2 = 0;
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}
	
	/**
	 * Gets the oproepen1
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Oproep> getOproepen1() {
		return oproepen1;
	}
	/**
	 * Sets the oproepen1
	 * @param oproepen1 The oproepen1 to set
	 */
	public void setOproepen1(java.util.ArrayList<Oproep> oproepen1) {
		this.oproepen1 = oproepen1;
	}


	/**
	 * Gets the totalsize1
	 * @return Returns a int
	 */
	public int getTotalsize1() {
		return totalsize1;
	}
	/**
	 * Sets the totalsize1
	 * @param totalsize1 The totalsize1 to set
	 */
	public void setTotalsize1(int totalsize1) {
		this.totalsize1 = totalsize1;
	}


	/**
	 * Gets the currentindex1
	 * @return Returns a int
	 */
	public int getCurrentindex1() {
		return currentindex1;
	}
	/**
	 * Sets the currentindex1
	 * @param currentindex1 The currentindex1 to set
	 */
	public void setCurrentindex1(int currentindex1) {
		this.currentindex1 = currentindex1;
	}


	/**
	 * Gets the oproepen2
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Oproep> getOproepen2() {
		return oproepen2;
	}
	/**
	 * Sets the oproepen2
	 * @param oproepen2 The oproepen2 to set
	 */
	public void setOproepen2(java.util.ArrayList<Oproep> oproepen2) {
		this.oproepen2 = oproepen2;
	}


	/**
	 * Gets the totalsize2
	 * @return Returns a int
	 */
	public int getTotalsize2() {
		return totalsize2;
	}
	/**
	 * Sets the totalsize2
	 * @param totalsize2 The totalsize2 to set
	 */
	public void setTotalsize2(int totalsize2) {
		this.totalsize2 = totalsize2;
	}


	/**
	 * Gets the currentindex2
	 * @return Returns a int
	 */
	public int getCurrentindex2() {
		return currentindex2;
	}
	/**
	 * Sets the currentindex2
	 * @param currentindex2 The currentindex2 to set
	 */
	public void setCurrentindex2(int currentindex2) {
		this.currentindex2 = currentindex2;
	}


}


