package be.vlaanderen.sbs.s6.taaladvies.distributie;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;
import be.vlaanderen.sbs.s6.taaladvies.model.Sjabloon;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;


public class DistributiekeuzeForm extends ActionForm {


	/**
     * 
     */
    private static final long serialVersionUID = -7002357872935891432L;
	private Oproep oproep = new Oproep();
	private Tekst tekst = new Tekst();
	private java.util.ArrayList<Taalvraag> taalvragen = new java.util.ArrayList<Taalvraag>();
	private java.util.ArrayList<Sjabloon> sjablonen = new java.util.ArrayList<Sjabloon>();

	private FormFile theFile;
	private String fileName;	
	private int index;
	private boolean ro;
		
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
System.err.println("val:\n" + oproep.getDistributie().getSjabloon().getHandtekening());
System.err.println("val:\n" + "@@@@@");
System.err.println("val:\n" + oproep.getDistributie().getSjabloon().getHandtekeningHTML());

		return errors;
	}


	public void reset(ActionMapping mapping, HttpServletRequest request) {
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
	 * Gets the taalvragen
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Taalvraag> getTaalvragen() {
		return taalvragen;
	}
	/**
	 * Sets the taalvragen
	 * @param taalvragen The taalvragen to set
	 */
	public void setTaalvragen(java.util.ArrayList<Taalvraag> taalvragen) {
		this.taalvragen = taalvragen;
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
	 * @param sjablonen The sjablonen to set
	 */
	public void setSjablonen(java.util.ArrayList<Sjabloon> sjablonen) {
		this.sjablonen = sjablonen;
	}


	/**
	 * Gets the index
	 * @return Returns a int
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * Sets the index
	 * @param index The index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}


	/**
	 * Gets the tekst
	 * @return Returns a Tekst
	 */
	public Tekst getTekst() {
		return tekst;
	}
	/**
	 * Sets the tekst
	 * @param tekst The tekst to set
	 */
	public void setTekst(Tekst tekst) {
		this.tekst = tekst;
	}


	/**
	 * Gets the ro
	 * @return Returns a boolean
	 */
	public boolean getRo() {
		return ro;
	}
	/**
	 * Sets the ro
	 * @param ro The ro to set
	 */
	public void setRo(boolean ro) {
		this.ro = ro;
	}


	/**
	 * Gets the theFile
	 * @return Returns a FormFile
	 */
	public FormFile getTheFile() {
		return theFile;
	}
	/**
	 * Sets the theFile
	 * @param theFile The theFile to set
	 */
	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}


	/**
	 * Gets the fileName
	 * @return Returns a String
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * Sets the fileName
	 * @param fileName The fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


}


