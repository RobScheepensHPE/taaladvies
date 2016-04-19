package be.vlaanderen.sbs.s6.taaladvies.oproep;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.framework.logging.AppLogger;
import be.vlaanderen.sbs.s6.taaladvies.model.Categorie;
import be.vlaanderen.sbs.s6.taaladvies.model.Oproep;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterBase;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;
import be.vlaanderen.sbs.s6.util.validation.REValidator;

public class Oproep_TaalvragenInvoerForm extends ActionForm
{
    /**
     *
     */
    private static final long serialVersionUID = -5527700730399948049L;
    Oproep oproep = new Oproep();
    java.util.ArrayList<ParameterActief> media = new java.util.ArrayList<ParameterActief>();
    java.util.ArrayList<ParameterActief> herkomsten = new java.util.ArrayList<ParameterActief>();
    java.util.ArrayList<ParameterBase> domeinen = new java.util.ArrayList<ParameterBase>();
    java.util.ArrayList<ParameterActief> statussen = new java.util.ArrayList<ParameterActief>();
    java.util.ArrayList<Taalvraag> taalvragen = new java.util.ArrayList<Taalvraag>();
    Taalvraag taalvraag = new Taalvraag();
    boolean adm = false;
    String useEopro = "false";

    public String getUseEopro()
    {
	return useEopro;
    }

    public void setUseEopro(String useEopro)
    {
	this.useEopro = useEopro;
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
    {
	System.err.println("useeopro:" + getUseEopro());
	ActionErrors errors = new ActionErrors();
	if (request.getParameter("Oproep Afsluiten.x") != null)
	{
	    // boolean b_titel = true;
	    // boolean b_vraag = true;
	    // boolean b_antwoord = true;
	    // boolean b_categorien = true;
	    for (int i = 0, j = taalvragen.size(); i < j; i++)
	    {
		Taalvraag taalvraag = (Taalvraag) taalvragen.get(i);
		java.util.ArrayList<Categorie> categorien = Categorie.findByTaalvraagId(taalvraag.getId());
		// if (taalvraag.getTitel().equals("") || taalvraag.getTitelHTML().equals(Util.EOPRO_EMPTY_HTMLFORM)) {
		if (taalvraag.getTitel() == null || taalvraag.getTitel().equals(""))
		{
		    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.afsluiten.ontbreken.titels", taalvraag
			.getVraag()));
		} /*
		   * else { errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.afsluiten.ontbreken.titels", )); }
		   */
		// if (taalvraag.getVraagHTML().equals("") || taalvraag.getVraagHTML().equals(Util.EOPRO_EMPTY_HTMLFORM)) {
		if (taalvraag.getVraag() != null && taalvraag.getVraag().equals(""))
		{
		    if (taalvraag.getTitel() != null && !taalvraag.getTitel().equals(""))
		    {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.afsluiten.ontbreken.vragen", taalvraag
			    .getTitel()));
		    } else
		    {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.afsluiten.ontbreken.vragen", taalvraag
			    .getVraag()));
		    }
		}
		if (taalvraag.getAntwoordHTML().equals("") || taalvraag.getAntwoordHTML().equals(Util.EOPRO_EMPTY_HTMLFORM))
		{
		    if (taalvraag.getTitel() != null && !taalvraag.getTitel().equals(""))
		    {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.afsluiten.ontbreken.antwoorden",
			    taalvraag.getTitel()));
		    } else
		    {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.afsluiten.ontbreken.antwoorden",
			    taalvraag.getVraag()));
		    }
		}
		if (categorien.size() < 1)
		{
		    if (taalvraag.getTitel() != null && !taalvraag.getTitel().equals(""))
		    {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.afsluiten.ontbreken.categorien",
			    taalvraag.getTitel()));
		    } else
		    {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.afsluiten.ontbreken.categorien",
			    taalvraag.getVraag()));
		    }
		}
	    }
	    /*
	     * if (!b_titel) { errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.afsluiten.ontbreken.titels")); } if
	     * (!b_vraag) { errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.afsluiten.ontbreken.vragen")); } if
	     * (!b_antwoord) { errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.afsluiten.ontbreken.antwoorden"));
	     * } if (!b_categorien) { errors.add(ActionErrors.GLOBAL_ERROR, new
	     * ActionError("oproep.afsluiten.ontbreken.categorien")); }
	     */
	}
	this.checkDate(errors);
	if (oproep.getId() == 0 || taalvragen == null || taalvragen.size() == 0)
	{
	    if (request.getParameter("Toevoegen.x") == null && request.getParameter("Annuleren.x") == null)
	    {
		/*
		 * if (taalvraag.getVraagHTML().equals("") || taalvraag.getVraagHTML().equals(Util.EOPRO_EMPTY_HTMLFORM)) {
		 * errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("taalvraag.ontbreken.vraag")); }
		 */
		AppLogger log = AppLogger.getInstance();
		log.debug("taalvraag.titel = " + taalvraag.getTitel());
		if ("".equals(taalvraag.getVraag()) || taalvraag.getVraag() == null)
		{
		    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("taalvraag.ontbreken.vraag"));
		}
	    }
	}
	if (oproep.getMedium().getOmschrijving() == null) {
	    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.medium"));
	}
	if (oproep.getDistributie().getMedium().getOmschrijving() == null) {
	    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.distributie.medium"));
	}
	if (oproep.getId() != 0)
	{
	    if (oproep.getDistributie().getMedium().getOmschrijving() != null)
	    {
		if (!oproep.getDistributie().getMedium().getOmschrijving().equals("E-mail"))
		{
		    boolean check = false;
		    for (int i = 0, j = taalvragen.size(); i < j; i++)
		    {
			Taalvraag vraag = (Taalvraag) taalvragen.get(i);
			if (vraag.getDistributiedatum() != null)
			{
			    check = true;
			}
		    }
		    if (check)
		    {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.medium.algedistribueerd"));
		    }
		}
	    }
	}
	if (!oproep.getEmail().trim().equals(""))
	{
	    if (!REValidator.validate("email", oproep.getEmail().trim()))
	    {
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("global.error.validation.email"));
	    }
	}
	if (request.getParameter("Distributie.x") != null)
	{
	    if (request.getParameter("ro") == null)
	    {
		if (oproep.getDistributie().getMediumId() != 0)
		{
		    String medium_uit = oproep.getDistributie().getMedium().getOmschrijving().trim();
		    if (medium_uit.equals("E-mail"))
		    {
			if (oproep.getEmail().trim().equals(""))
			{
			    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.distributie.ontbreken.email"));
			}
		    }
		}
	    }
	}
	return errors;
    }

    public ActionErrors checkDate(ActionErrors errors)
    {
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
	if (!oproepdatum.toString().trim().equals("--"))
	{
	    if (!Util.isValidDate(oproepdatum.toString(), "-"))
	    {
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.fout.oproepdatum"));
	    } else
	    {
		if (!REValidator.validate("correctDayOrMonth", oproep.getOproepdatum_d()))
		{
		    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.fout.oproepdatum"));
		}
		if (!REValidator.validate("correctDayOrMonth", oproep.getOproepdatum_m()))
		{
		    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.fout.oproepdatum"));
		}
		if (!REValidator.validate("positiveInteger", oproep.getOproepdatum_y()))
		{
		    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.fout.oproepdatum"));
		}
	    }
	}
	if (!deadline.toString().trim().equals("--"))
	{
	    if (!Util.isValidDate(deadline.toString(), "-"))
	    {
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.fout.deadline"));
	    } else
	    {
		if (!REValidator.validate("correctDayOrMonth", oproep.getDeadline_d()))
		{
		    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.fout.deadline"));
		}
		if (!REValidator.validate("correctDayOrMonth", oproep.getDeadline_m()))
		{
		    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.fout.deadline"));
		}
		if (!REValidator.validate("positiveInteger", oproep.getDeadline_y()))
		{
		    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("oproep.fout.deadline"));
		}
	    }
	}
	return errors;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
	oproep = new Oproep();
	taalvraag = new Taalvraag();
    }

    /**
     * Gets the oproep
     * @return Returns a Oproep
     */
    public Oproep getOproep()
    {
	return oproep;
    }

    /**
     * Sets the oproep
     * @param oproep The oproep to set
     */
    public void setOproep(Oproep oproep)
    {
	this.oproep = oproep;
    }

    /**
     * Gets the media
     * @return Returns a java.util.ArrayList
     */
    public java.util.ArrayList<ParameterActief> getMedia()
    {
	return media;
    }

    /**
     * Sets the media
     * @param media The media to set
     */
    public void setMedia(java.util.ArrayList<ParameterActief> media)
    {
	this.media = media;
    }

    /**
     * Gets the herkomsten
     * @return Returns a java.util.ArrayList
     */
    public java.util.ArrayList<ParameterActief> getHerkomsten()
    {
	return herkomsten;
    }

    /**
     * Sets the herkomsten
     * @param herkomsten The herkomsten to set
     */
    public void setHerkomsten(java.util.ArrayList<ParameterActief> herkomsten)
    {
	this.herkomsten = herkomsten;
    }

    /**
     * Gets the domeinen
     * @return Returns a java.util.ArrayList
     */
    public java.util.ArrayList<ParameterBase> getDomeinen()
    {
	return domeinen;
    }

    /**
     * Sets the domeinen
     * @param domeinen The domeinen to set
     */
    public void setDomeinen(java.util.ArrayList<ParameterBase> domeinen)
    {
	this.domeinen = domeinen;
    }

    /**
     * Gets the taalvragen
     * @return Returns a java.util.ArrayList
     */
    public java.util.ArrayList<Taalvraag> getTaalvragen()
    {
	return taalvragen;
    }

    /**
     * Sets the taalvragen
     * @param taalvragen The taalvragen to set
     */
    public void setTaalvragen(java.util.ArrayList<Taalvraag> taalvragen)
    {
	this.taalvragen = taalvragen;
    }

    /**
     * Gets the statussen
     * @return Returns a java.util.ArrayList
     */
    public java.util.ArrayList<ParameterActief> getStatussen()
    {
	return statussen;
    }

    /**
     * Sets the statussen
     * @param statussen The statussen to set
     */
    public void setStatussen(java.util.ArrayList<ParameterActief> statussen)
    {
	this.statussen = statussen;
    }

    /**
     * Gets the taalvraag
     * @return Returns a Taalvraag
     */
    public Taalvraag getTaalvraag()
    {
	return taalvraag;
    }

    /**
     * Sets the taalvraag
     * @param taalvraag The taalvraag to set
     */
    public void setTaalvraag(Taalvraag taalvraag)
    {
	this.taalvraag = taalvraag;
    }

    /**
     * Gets the adm
     * @return Returns a boolean
     */
    public boolean getAdm()
    {
	return adm;
    }

    /**
     * Sets the adm
     * @param adm The adm to set
     */
    public void setAdm(boolean adm)
    {
	this.adm = adm;
    }
}
