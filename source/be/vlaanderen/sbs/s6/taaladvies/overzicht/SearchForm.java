package be.vlaanderen.sbs.s6.taaladvies.overzicht;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import be.vlaanderen.sbs.s6.taaladvies.Queries;
import be.vlaanderen.sbs.s6.taaladvies.appconf.AppConf;
import be.vlaanderen.sbs.s6.taaladvies.model.Categorie;
import be.vlaanderen.sbs.s6.taaladvies.model.ParameterActief;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;

public class SearchForm extends ActionForm
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static int DISPLAY_AMOUNT = Integer.parseInt(AppConf.getConstant("NO_OF_RESULTS"));
    private boolean ownerIrrelevant;
    // Extra criteria
    private boolean eigenTV = true;
    private boolean eigenTXT = true;
    private boolean alleTV = true;
    private boolean alleTXT = true;
    private boolean oproepAfgesloten = true;
    private boolean titel = true;
    private boolean vraag = true;
    private boolean antwoord = true;
    private boolean toelichting = true;
    private boolean bijzonderheid = true;
    private boolean tekstTitel = true;
    private boolean tekstSubtitels = true;
    private boolean tekstVelden = true;
    private boolean emailUit;
    private int relevantie = 0;
    private int interval = 0;
    private int userId = 0;
    private String startDate;
    private String endDate;
    private String singleDate;
    private String startDateD;
    private String startDateM;
    private String startDateY;
    private String endDateD;
    private String endDateM;
    private String endDateY;
    private String singleDateD;
    private String singleDateM;
    private String singleDateY;
    private Date dStartDate;
    private Date dEndDate;
    private Date dSingleDate;
    // Trefwoorden per zoekmanier
    private String andTerms;
    private String phrase;
    private ArrayList<ParameterActief> relevanties = ParameterActief.findAllActief(Queries.ALL_RELEVANTIES_ACTIEF);
    // private boolean categorie;
    private Categorie categorie1;
    private Categorie categorie2;
    private List<Categorie> categorieList = new ArrayList<Categorie>();
    // Radio-Button voor type DATUM-search
    private int dateSearch = 1;
    // variable Query part
    private String variablePartTLV = null;
    private String variablePartTXT = null;

    /**
     * Gets the antwoord
     * @return Returns a boolean
     */
    public boolean getAntwoord()
    {
	return antwoord;
    }

    /**
     * Sets the antwoord
     * @param antwoord The antwoord to set
     */
    public void setAntwoord(boolean antwoord)
    {
	this.antwoord = antwoord;
    }

    /**
     * Gets the bijzonderheid
     * @return Returns a boolean
     */
    public boolean getBijzonderheid()
    {
	return bijzonderheid;
    }

    /**
     * Sets the bijzonderheid
     * @param bijzonderheid The bijzonderheid to set
     */
    public void setBijzonderheid(boolean bijzonderheid)
    {
	this.bijzonderheid = bijzonderheid;
    }

    /**
     * Gets the tekstSubtitels
     * @return Returns a boolean
     */
    public boolean getTekstSubtitels()
    {
	return tekstSubtitels;
    }

    /**
     * Sets the tekstSubtitels
     * @param tekstSubtitels The tekstSubtitels to set
     */
    public void setTekstSubtitels(boolean tekstSubtitels)
    {
	this.tekstSubtitels = tekstSubtitels;
    }

    /**
     * Gets the tekstTitel
     * @return Returns a boolean
     */
    public boolean getTekstTitel()
    {
	return tekstTitel;
    }

    /**
     * Sets the tekstTitel
     * @param tekstTitel The tekstTitel to set
     */
    public void setTekstTitel(boolean tekstTitel)
    {
	this.tekstTitel = tekstTitel;
    }

    /**
     * Gets the tekstVelden
     * @return Returns a boolean
     */
    public boolean getTekstVelden()
    {
	return tekstVelden;
    }

    /**
     * Sets the tekstVelden
     * @param tekstVelden The tekstVelden to set
     */
    public void setTekstVelden(boolean tekstVelden)
    {
	this.tekstVelden = tekstVelden;
    }

    /**
     * Gets the titel
     * @return Returns a boolean
     */
    public boolean getTitel()
    {
	return titel;
    }

    /**
     * Sets the titel
     * @param titel The titel to set
     */
    public void setTitel(boolean titel)
    {
	this.titel = titel;
    }

    /**
     * Gets the toelichting
     * @return Returns a boolean
     */
    public boolean getToelichting()
    {
	return toelichting;
    }

    /**
     * Sets the toelichting
     * @param toelichting The toelichting to set
     */
    public void setToelichting(boolean toelichting)
    {
	this.toelichting = toelichting;
    }

    /**
     * Gets the vraag
     * @return Returns a boolean
     */
    public boolean getVraag()
    {
	return vraag;
    }

    /**
     * Sets the vraag
     * @param vraag The vraag to set
     */
    public void setVraag(boolean vraag)
    {
	this.vraag = vraag;
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
    {
	ActionErrors errors = new ActionErrors();
	if (eigenTV || eigenTXT || alleTV || alleTXT)
	{
	    if (!isCategorie())
	    {
		if (andTerms == null && phrase == null)
		{
		    errors.add("missing", new ActionError("zoeken.ontbreken.trefwoorden"));
		} else
		{
		    if (andTerms != null && andTerms.trim().indexOf(" ")==-1 && andTerms.trim().indexOf("'")>0)
		    {
			errors.add("", new ActionError("zoeken.fout.singlequotes"));
		    }
		}
		// Zoeken op taalvragen
		if (eigenTV || alleTV)
		{
		    if (!getVraag() && !getAntwoord() && !getBijzonderheid() && !getTitel() && !getToelichting())
		    {
			errors.add("missing", new ActionError("zoeken.ontbreken.zoekvelden", "taalvragen"));
		    }
		}
		// Zoeken op teksten
		if (eigenTXT || alleTXT)
		{
		    if (!getTekstSubtitels() && !getTekstTitel() && !getTekstVelden())
		    {
			errors.add("missing", new ActionError("zoeken.ontbreken.zoekvelden", "teksten"));
		    }
		}
	    } else
	    {
		if (categorieList == null || categorieList.size() == 0)
		{
		    errors.add("missing", new ActionError("zoeken.ontbreken.categorie"));
		}
	    }
	    if (dateSearch == 1)
	    {
		int correctDate = 0;
		if (startDateD != null)
		{
		    correctDate++;
		}
		if (startDateM != null)
		{
		    correctDate++;
		}
		if (startDateY != null)
		{
		    correctDate++;
		}
		if (correctDate > 0 && correctDate < 3)
		{
		    errors.add("", new ActionError("global.error.validation.date", "(vanaf datum)"));
		} else if (correctDate == 3)
		{
		    startDate = startDateD + "-" + startDateM + "-" + startDateY;
		    if (!Util.isValidDate(startDate, "-"))
		    {
			errors.add("", new ActionError("global.error.validation.date", "(vanaf datum)"));
		    }
		}
		correctDate = 0;
		if (endDateD != null)
		{
		    correctDate++;
		}
		if (endDateM != null)
		{
		    correctDate++;
		}
		if (endDateY != null)
		{
		    correctDate++;
		}
		if (correctDate > 0 && correctDate < 3)
		{
		    errors.add("", new ActionError("global.error.validation.date", "(tot en met datum)"));
		} else if (correctDate == 3)
		{
		    endDate = endDateD + "-" + endDateM + "-" + endDateY;
		    if (!Util.isValidDate(endDate, "-"))
		    {
			errors.add("", new ActionError("global.error.validation.date", "(tot en met datum)"));
		    }
		}
		if (endDate != null && startDate != null)
		{
		    if (!getDEndDate().equals(getDStartDate()))
		    {
			if (getDEndDate().before(getDStartDate()))
			{
			    errors.add("", new ActionError("global.error.validation.dateInterval"));
			}
		    }
		}
	    } else
	    {
		int correctDate = 0;
		if (singleDateD != null)
		{
		    correctDate++;
		}
		if (singleDateM != null)
		{
		    correctDate++;
		}
		if (singleDateY != null)
		{
		    correctDate++;
		}
		if (correctDate > 0 && correctDate < 3)
		{
		    errors.add("", new ActionError("global.error.validation.date", "(op datum)"));
		} else if (correctDate == 3)
		{
		    singleDate = singleDateD + "-" + singleDateM + "-" + singleDateY;
		    if (!Util.isValidDate(singleDate, "-"))
		    {
			errors.add("", new ActionError("global.error.validation.date", "(op datum)"));
		    }
		}
	    }
	} else
	{
	    errors.add("", new ActionError("application.error.missing.selection", "taalvragen en/of teksten"));
	}
	if (!errors.isEmpty())
	{
	    request.getSession().removeAttribute("resultlist");
	}
	return errors;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
	eigenTV = false;
	eigenTXT = false;
	alleTV = false;
	alleTXT = false;
	emailUit = false;
	dStartDate = null;
	dEndDate = null;
	dSingleDate = null;
	endDate = null;
	endDateD = null;
	endDateM = null;
	endDateY = null;
	startDate = null;
	startDateD = null;
	startDateM = null;
	startDateY = null;
	singleDate = null;
	singleDateD = null;
	singleDateM = null;
	singleDateY = null;
	// if (request.getParameter("categorie") != null && request.getParameter("categorie").equals("false")) {
	titel = false;
	vraag = false;
	antwoord = false;
	toelichting = false;
	bijzonderheid = false;
	tekstTitel = false;
	tekstSubtitels = false;
	tekstVelden = false;
	// }
	ownerIrrelevant = false;
    }

    /**
     * Gets the and
     * @return Returns a String
     */
    public String getAndTerms()
    {
	return andTerms;
    }

    /**
     * Sets the and
     * @param andTerms The and to set
     */
    public void setAndTerms(String andTerms)
    {
	this.andTerms = Util.processInputString(andTerms);
    }

    /**
     * Gets the phrase
     * @return Returns a String
     */
    public String getPhrase()
    {
	return phrase;
    }

    /**
     * Sets the phrase
     * @param phrase The phrase to set
     */
    public void setPhrase(String phrase)
    {
	this.phrase = Util.processInputString(phrase);
    }

    /**
     * Gets the alleTV
     * @return Returns a boolean
     */
    public boolean getAlleTV()
    {
	return alleTV;
    }

    /**
     * Sets the alleTV
     * @param alleTV The alleTV to set
     */
    public void setAlleTV(boolean alleTV)
    {
	this.alleTV = alleTV;
    }

    /**
     * Gets the alleTXT
     * @return Returns a boolean
     */
    public boolean getAlleTXT()
    {
	return alleTXT;
    }

    /**
     * Sets the alleTXT
     * @param alleTXT The alleTXT to set
     */
    public void setAlleTXT(boolean alleTXT)
    {
	this.alleTXT = alleTXT;
    }

    /**
     * Gets the eigenTV
     * @return Returns a boolean
     */
    public boolean getEigenTV()
    {
	return eigenTV;
    }

    /**
     * Sets the eigenTV
     * @param eigenTV The eigenTV to set
     */
    public void setEigenTV(boolean eigenTV)
    {
	this.eigenTV = eigenTV;
    }

    /**
     * Gets the eigenTXT
     * @return Returns a boolean
     */
    public boolean getEigenTXT()
    {
	return eigenTXT;
    }

    /**
     * Sets the eigenTXT
     * @param eigenTXT The eigenTXT to set
     */
    public void setEigenTXT(boolean eigenTXT)
    {
	this.eigenTXT = eigenTXT;
    }

    /**
     * Gets the startDate
     * @return Returns a String
     */
    public String getStartDate()
    {
	return startDate;
    }

    /**
     * Sets the startDate
     * @param startDate The startDate to set
     */
    public void setStartDate(String startDate)
    {
	this.startDate = startDate;
    }

    /**
     * Gets the endDate
     * @return Returns a String
     */
    public String getEndDate()
    {
	return endDate;
    }

    /**
     * Sets the endDate
     * @param endDate The endDate to set
     */
    public void setEndDate(String endDate)
    {
	this.endDate = endDate;
    }

    /**
     * Gets the relevantie
     * @return Returns a int
     */
    public int getRelevantie()
    {
	return relevantie;
    }

    /**
     * Sets the relevantie
     * @param relevantie The relevantie to set
     */
    public void setRelevantie(int relevantie)
    {
	this.relevantie = relevantie;
    }

    /**
     * Gets the relevanties
     * @return Returns a ArrayList
     */
    public ArrayList<ParameterActief> getRelevanties()
    {
	return relevanties;
    }

    /**
     * Gets the interval
     * @return Returns a int
     */
    public int getInterval()
    {
	return interval;
    }

    /**
     * Sets the interval
     * @param interval The interval to set
     */
    public void setInterval(int interval)
    {
	this.interval = interval;
    }

    /**
     * Gets the variablePartTLV
     * @return Returns a String
     */
    public String getVariablePartTLV()
    {
	return variablePartTLV;
    }

    /**
     * Sets the variablePartTLV
     * @param variablePartTLV The variablePartTLV to set
     */
    public void setVariablePartTLV(String variablePartTLV)
    {
	this.variablePartTLV = variablePartTLV;
    }

    /**
     * Gets the variablePartTXT
     * @return Returns a String
     */
    public String getVariablePartTXT()
    {
	return variablePartTXT;
    }

    /**
     * Sets the variablePartTXT
     * @param variablePartTXT The variablePartTXT to set
     */
    public void setVariablePartTXT(String variablePartTXT)
    {
	this.variablePartTXT = variablePartTXT;
    }

    /**
     * Sets the endDate
     * @return the end date.
     */
    public Date getDEndDate()
    {
	if (dEndDate == null)
	{
	    try
	    {
		dEndDate = new SimpleDateFormat("dd-MM-yyyy").parse(endDate);
	    } catch (Exception ex)
	    {
		return null;
	    }
	}
	return dEndDate;
    }

    /**
     * Sets the startDate
     * @return a start date.
     */
    public Date getDStartDate()
    {
	if (dStartDate == null)
	{
	    try
	    {
		dStartDate = new SimpleDateFormat("dd-MM-yyyy").parse(startDate);
	    } catch (Exception ex)
	    {
		return null;
	    }
	}
	return dStartDate;
    }

    /**
     * Gets the oproepAfgesloten
     * @return Returns a boolean
     */
    public boolean getOproepAfgesloten()
    {
	return oproepAfgesloten;
    }

    /**
     * Sets the oproepAfgesloten
     * @param oproepAfgesloten The oproepAfgesloten to set
     */
    public void setOproepAfgesloten(boolean oproepAfgesloten)
    {
	this.oproepAfgesloten = oproepAfgesloten;
    }

    // /**
    // * Gets the categorie
    // * @return Returns a boolean
    // */
    // public boolean getCategorie() {
    // return categorie;
    // }
    /**
     * Gets the categorie
     * @return Returns a boolean
     */
    public boolean isCategorie()
    {
	return categorieList.size() > 0;
    }

    // /**
    // * Sets the categorie
    // * @param categorie The categorie to set
    // */
    // public void setCategorie(boolean categorie)
    // {
    // this.categorie = categorie;
    // }
    /**
     * Gets the categorie1
     * @return Returns a Categorie
     */
    public Categorie getCategorie1()
    {
	return categorie1;
    }

    /**
     * Sets the categorie1
     * @param categorie1 The categorie1 to set
     */
    public void setCategorie1(Categorie categorie1)
    {
	this.categorie1 = categorie1;
    }

    /**
     * Gets the categorie2
     * @return Returns a Categorie
     */
    public Categorie getCategorie2()
    {
	return categorie2;
    }

    /**
     * Sets the categorie2
     * @param categorie2 The categorie2 to set
     */
    public void setCategorie2(Categorie categorie2)
    {
	this.categorie2 = categorie2;
    }

    /**
     * Gets the ownerIrrelevant
     * @return Returns a boolean
     */
    public boolean getOwnerIrrelevant()
    {
	return ownerIrrelevant;
    }

    /**
     * Sets the ownerIrrelevant
     * @param ownerIrrelevant The ownerIrrelevant to set
     */
    public void setOwnerIrrelevant(boolean ownerIrrelevant)
    {
	this.ownerIrrelevant = ownerIrrelevant;
    }

    /**
     * Gets the userId
     * @return Returns a int
     */
    public int getUserId()
    {
	return userId;
    }

    /**
     * Sets the userId
     * @param userId The userId to set
     */
    public void setUserId(int userId)
    {
	this.userId = userId;
    }

    /**
     * Gets the startDateD
     * @return Returns a String
     */
    public String getStartDateD()
    {
	return startDateD;
    }

    /**
     * Sets the startDateD
     * @param startDateD The startDateD to set
     */
    public void setStartDateD(String startDateD)
    {
	this.startDateD = Util.processInputString(startDateD);
    }

    /**
     * Gets the startDateM
     * @return Returns a String
     */
    public String getStartDateM()
    {
	return startDateM;
    }

    /**
     * Sets the startDateM
     * @param startDateM The startDateM to set
     */
    public void setStartDateM(String startDateM)
    {
	this.startDateM = Util.processInputString(startDateM);
    }

    /**
     * Gets the startDateY
     * @return Returns a String
     */
    public String getStartDateY()
    {
	return startDateY;
    }

    /**
     * Sets the startDateY
     * @param startDateY The startDateY to set
     */
    public void setStartDateY(String startDateY)
    {
	this.startDateY = Util.processInputString(startDateY);
    }

    /**
     * Gets the endDateD
     * @return Returns a String
     */
    public String getEndDateD()
    {
	return endDateD;
    }

    /**
     * Sets the endDateD
     * @param endDateD The endDateD to set
     */
    public void setEndDateD(String endDateD)
    {
	this.endDateD = Util.processInputString(endDateD);
    }

    /**
     * Gets the endDateM
     * @return Returns a String
     */
    public String getEndDateM()
    {
	return endDateM;
    }

    /**
     * Sets the endDateM
     * @param endDateM The endDateM to set
     */
    public void setEndDateM(String endDateM)
    {
	this.endDateM = Util.processInputString(endDateM);
    }

    /**
     * Gets the endDateY
     * @return Returns a String
     */
    public String getEndDateY()
    {
	return endDateY;
    }

    /**
     * Sets the endDateY
     * @param endDateY The endDateY to set
     */
    public void setEndDateY(String endDateY)
    {
	this.endDateY = Util.processInputString(endDateY);
    }

    /**
     * Gets the dateSearch
     * @return Returns a int
     */
    public int getDateSearch()
    {
	return dateSearch;
    }

    /**
     * Sets the dateSearch
     * @param dateSearch The dateSearch to set
     */
    public void setDateSearch(int dateSearch)
    {
	this.dateSearch = dateSearch;
    }

    /**
     * Gets the singleDate
     * @return Returns a Date
     */
    public String getSingleDate()
    {
	return singleDate;
    }

    /**
     * Sets the singleDate
     * @param singleDate The singleDate to set
     */
    public void setSingleDate(String singleDate)
    {
	this.singleDate = singleDate;
    }

    /**
     * Gets the singleDateD
     * @return Returns a String
     */
    public String getSingleDateD()
    {
	return singleDateD;
    }

    /**
     * Sets the singleDateD
     * @param singleDateD The singleDateD to set
     */
    public void setSingleDateD(String singleDateD)
    {
	this.singleDateD = Util.processInputString(singleDateD);
    }

    /**
     * Gets the singleDateM
     * @return Returns a String
     */
    public String getSingleDateM()
    {
	return singleDateM;
    }

    /**
     * Sets the singleDateM
     * @param singleDateM The singleDateM to set
     */
    public void setSingleDateM(String singleDateM)
    {
	this.singleDateM = Util.processInputString(singleDateM);
    }

    /**
     * Sets the singleDate
     * @param dSingleDate a single date.
     */
    public void setDSingleDate(Date dSingleDate)
    {
	this.dSingleDate = dSingleDate;
    }

    public Date getDSingleDate()
    {
	if (dSingleDate == null)
	{
	    try
	    {
		dSingleDate = new SimpleDateFormat("dd-MM-yyyy").parse(singleDate);
	    } catch (Exception ex)
	    {
		return null;
	    }
	}
	return dSingleDate;
    }

    /**
     * Gets the singleDateY
     * @return Returns a String
     */
    public String getSingleDateY()
    {
	return singleDateY;
    }

    /**
     * Sets the singleDateY
     * @param singleDateY The singleDateY to set
     */
    public void setSingleDateY(String singleDateY)
    {
	this.singleDateY = Util.processInputString(singleDateY);
    }

    /**
     * Expands the Categorie list to the requiredSize, by adding <Categorie> objects until the size is OK.
     * @param requiredSize the size to grow to
     */
    private void growCategorieList(int requiredSize)
    {
	while (requiredSize >= this.getCategorieList().size())
	{
	    this.getCategorieList().add(new Categorie());
	}
    }

    /**
     * @param index index
     * @return Categorie categorie
     */
    public Categorie getCategorie(int index)
    {
	growCategorieList(index);
	return categorieList.get(index);
    }

    /**
     * @param index int
     * @param aCategorie Categorie
     */
    public void setCategorie(int index, Categorie aCategorie)
    {
	this.growCategorieList(index);
	categorieList.set(index, aCategorie);
    }

    /**
     * @param index int
     */
    public void removeCategorie(int index)
    {
	if (categorieList != null && (categorieList.size() > index))
	{
	    categorieList.remove(index);
	}
    }

    /**
     * @return Returns the categorieList.
     */
    public List<Categorie> getCategorieList()
    {
	return categorieList;
    }

    /**
     * @param categorieList The categorieList to set.
     */
    public void setCategorieList(List<Categorie> categorieList)
    {
	this.categorieList = categorieList;
    }

    /**
     * @return Returns the emailUit.
     */
    public boolean isEmailUit()
    {
	return emailUit;
    }

    /**
     * @param emailUit The emailUit to set.
     */
    public void setEmailUit(boolean emailUit)
    {
	this.emailUit = emailUit;
    }
}
