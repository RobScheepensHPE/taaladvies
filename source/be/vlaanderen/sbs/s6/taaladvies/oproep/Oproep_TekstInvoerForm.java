package be.vlaanderen.sbs.s6.taaladvies.oproep;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.model.Categorie;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterBase;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekst;
import be.vlaanderen.sbs.s6.taaladvies.model.Tekstblok;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;
import be.vlaanderen.sbs.s6.util.validation.REValidator;

public class Oproep_TekstInvoerForm extends ActionForm {


	/**
     * 
     */
    private static final long serialVersionUID = 2723444940657082342L;
	Oproep oproep = new Oproep();
	java.util.ArrayList<ParameterActief> media = new java.util.ArrayList<ParameterActief>();
	java.util.ArrayList<ParameterActief> herkomsten = new java.util.ArrayList<ParameterActief>();
	java.util.ArrayList<ParameterBase> domeinen = new java.util.ArrayList<ParameterBase>();
	java.util.ArrayList<ParameterActief> statussen = new java.util.ArrayList<ParameterActief>();
	java.util.ArrayList<ParameterActief> relevanties = new java.util.ArrayList<ParameterActief>();
		
	Tekst tekst = new Tekst();
	java.util.ArrayList<Tekstblok> tekstblokken = new java.util.ArrayList<Tekstblok>();
	String useEopro ="false";


    public String getUseEopro() {
        return useEopro;
    }
    public void setUseEopro(String useEopro) {
        this.useEopro = useEopro;
    }
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();


		if (request.getParameter("Nieuwe Oproep Tekst.x") != null || request.getParameter("Zoeken.x") != null || request.getParameter("Profielen.x") != null || request.getParameter("In Behandeling.x") != null || request.getParameter("Nieuwe Oproep Taalvragen.x") != null || request.getParameter("Nieuwe Oproep Tekst Alles.x") != null || request.getParameter("Zoeken Alles.x") != null || request.getParameter("Profielen Alles.x") != null || request.getParameter("In Behandeling Alles.x") != null || request.getParameter("Nieuwe Oproep Taalvragen Alles.x") != null) {
			java.util.ArrayList<Tekstblok> tekstblokken = tekst.getTekstblokken();			
			if (tekstblokken == null || tekstblokken.size() == 0) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.ontbreken.tekstblokken"));
			}
		}

		if (request.getParameter("Herschikken.x") != null || request.getParameter("Oproep Afsluiten.x") != null || request.getParameter("Distributie.x") != null || request.getParameter("Volledige Tekst.x") != null || request.getParameter("Nieuwe Oproep Tekst.x") != null || request.getParameter("Zoeken.x") != null || request.getParameter("Profielen.x") != null || request.getParameter("In Behandeling.x") != null || request.getParameter("Nieuwe Oproep Taalvragen.x") != null || request.getParameter("Nieuwe Oproep Tekst Alles.x") != null || request.getParameter("Zoeken Alles.x") != null || request.getParameter("Profielen Alles.x") != null || request.getParameter("In Behandeling Alles.x") != null || request.getParameter("Nieuwe Oproep Taalvragen Alles.x") != null || request.getParameter("Nieuw Tekstblok.x") != null || request.getParameter("Klant.x") != null || request.getParameter("Kopieer.x") != null || request.getParameter("Tekst invoeren.x") != null || request.getParameter("Tekst wijzigen.x") != null || request.getParameter("Button") != null) {						
			if (request.getParameter("ro") == null) {
				if (tekst.getTitelHTML().trim().equals("") || tekst.getTitelHTML().trim().equals(Util.EOPRO_EMPTY_HTMLFORM)) {
	
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("algemeen.ontbreken.titel"));
				}			
				if (!oproep.getEmail().trim().equals("")) {
					if (!REValidator.validate("email", oproep.getEmail().trim())) {
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("global.error.validation.email"));			
					}
				}			
				this.checkDate(errors);
			}
		}			
		
		if (request.getParameter("Wijzigen.x") != null || request.getParameter("Toevoegen.x") != null) {
			
			this.checkDate(errors);
			if (!oproep.getEmail().trim().equals("")) {
				if (!REValidator.validate("email", oproep.getEmail().trim())) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("global.error.validation.email"));			
				}
			}		
		}		
		
		if (request.getParameter("Oproep Afsluiten.x") != null) {
			
			java.util.ArrayList<Categorie> categorien = Categorie.findByTekstId(tekst.getId());
			if (categorien.size() < 1) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.afsluiten.tekst.ontbreken.categorien"));
			}
			if (tekstblokken.size() < 1) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.afsluiten.ontbreken.tekstblokken"));
			}					
				
		}

		if (request.getParameter("Distributie.x") != null) {
			
			if (request.getParameter("ro") == null) {
				if (oproep.getDistributie().getMediumId() != 0) {				
					String medium_uit = oproep.getDistributie().getMedium().getOmschrijving().trim();
		
					if (medium_uit.equals("E-mail")) {
						if (oproep.getEmail() == null || oproep.getEmail().trim().equals("")) {
							errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.distributie.ontbreken.email")); 
						}
					}
				}
			}
		}
		
		if (request.getParameter("Nieuw Tekstblok.x") != null) {
			java.util.ArrayList<Tekstblok> tekstblokken = tekst.getTekstblokken();			
			if (tekstblokken != null) {
				if (tekstblokken.size() == 1) {
					Tekstblok check_tekstblok = tekstblokken.get(0);
					if (check_tekstblok.getTitel().equals("")) {
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("tekstblok.invoeren.ontbreken.titel.eerstetekstblok"));
					}
				}
			}
		}
		
		if (request.getParameter("Herschikken.x") != null) {	
			for (int i = 0, j = tekstblokken.size(); i < j ; i++) {
				Tekstblok tekstblok = tekstblokken.get(i);
				if (!REValidator.validate("positiveInteger", request.getParameter("toChange_" + (tekstblok.getId())))) {				
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("global.error.validation.positiveInteger"));				
				}
			}
		}	
		return errors;
	}


	public ActionErrors checkDate(ActionErrors errors) {

		oproep.setDeadline(null);
		oproep.setOproepdatum(null);
		StringBuffer oproepdatum = new StringBuffer();
		StringBuffer deadline = new StringBuffer();
		oproepdatum.append(oproep.getOproepdatum_d());
		oproepdatum.append("-");
		oproepdatum.append(oproep.getOproepdatum_m());
		oproepdatum.append("-");
		oproepdatum.append(oproep.getOproepdatum_y());
		deadline.append(oproep.getDeadline_d());
		deadline.append("-");
		deadline.append(oproep.getDeadline_m());
		deadline.append("-");
		deadline.append(oproep.getDeadline_y());

		if (!oproepdatum.toString().trim().equals("--")) {
			if (!Util.isValidDate(oproepdatum.toString(), "-"))	{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.fout.oproepdatum"));
			}
			else {		
				if (!REValidator.validate("correctDayOrMonth", oproep.getOproepdatum_d())) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.fout.oproepdatum"));
				}
				if (!REValidator.validate("correctDayOrMonth", oproep.getOproepdatum_m())) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.fout.oproepdatum"));
				}
				if (!REValidator.validate("positiveInteger", oproep.getOproepdatum_y())) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.fout.oproepdatum"));
				}
			}
		}
		if (!deadline.toString().trim().equals("--")) {
			if (!Util.isValidDate(deadline.toString(), "-")) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.fout.deadline"));
			}
			else {		
				if (!REValidator.validate("correctDayOrMonth", oproep.getDeadline_d())) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.fout.deadline"));
				}
				if (!REValidator.validate("correctDayOrMonth", oproep.getDeadline_m())) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.fout.deadline"));
				}
				if (!REValidator.validate("positiveInteger", oproep.getDeadline_y())) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.fout.deadline"));
				}
			}
		}	
		return errors;		
	}


	public void reset(ActionMapping mapping, HttpServletRequest request) {
		oproep = new Oproep();
		tekst = new Tekst();
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
	 * Gets the tekstblokken
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<Tekstblok> getTekstblokken() {
		return tekstblokken;
	}
	/**
	 * Sets the tekstblokken
	 * @param tekstblokken The tekstblokken to set
	 */
	public void setTekstblokken(java.util.ArrayList<Tekstblok> tekstblokken) {
		this.tekstblokken = tekstblokken;
	}


	/**
	 * Gets the statussen
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<ParameterActief> getStatussen() {
		return statussen;
	}
	/**
	 * Sets the statussen
	 * @param statussen The statussen to set
	 */
	public void setStatussen(java.util.ArrayList<ParameterActief> statussen) {
		this.statussen = statussen;
	}


	/**
	 * Gets the relevanties
	 * @return Returns a java.util.ArrayList
	 */
	public java.util.ArrayList<ParameterActief> getRelevanties() {
		return relevanties;
	}
	/**
	 * Sets the relevanties
	 * @param relevanties The relevanties to set
	 */
	public void setRelevanties(java.util.ArrayList<ParameterActief> relevanties) {
		this.relevanties = relevanties;
	}


}


