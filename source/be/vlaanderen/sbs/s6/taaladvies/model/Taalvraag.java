package be.vlaanderen.sbs.s6.taaladvies.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import be.vlaanderen.sbs.s6.framework.logging.AppLogger;
import be.vlaanderen.sbs.s6.taaladvies.Queries;
import be.vlaanderen.sbs.s6.taaladvies.db.IDbConnection;
import be.vlaanderen.sbs.s6.taaladvies.db.MyDbConnection;
import be.vlaanderen.sbs.s6.taaladvies.overzicht.SearchForm;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;
import be.vlaanderen.sbs.s6.utils.DbUtil;
import com.informix.jdbc.IfxStatement;

public class Taalvraag implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 7936811924770851806L;
    private String titel;
    private String titelHTML;
    private int id;
    private String vraag;
    private String vraagHTML;
    private String informatie;
    private String informatieHTML;
    private String herformulering;
    private String herformuleringHTML;
    private int volgnummer = 1;
    private boolean afgehandeld;
    private String antwoord;
    private String antwoordHTML;
    private String toelichting;
    private String toelichtingHTML;
    private String bijzonderheid;
    private String bijzonderheidHTML;
    private Date distributiedatum;
    private String distributiedatumString;
    private int oproepId;
    private int relevantieId;
    private Oproep oproep;
    private ParameterActief relevantie;
    private ArrayList<Categorie> categorien;
    private ArrayList<Naslagreferentie> naslagreferenties;
    private ArrayList<Bron> bronnen;
    private ArrayList<Citaat> citaten;
    private ArrayList<Frequentie> frequenties;
    private ArrayList<Webreferentie> webreferenties;
    private ArrayList<Notitie> notities;
    private ArrayList<Tekst> teksten;
    private boolean check_titel = true;
    private boolean check_vraag = true;
    private boolean check_antwoord = true;
    private boolean check_toelichting = true;
    private boolean check_bijzonderheid = true;
    private boolean check_categorien;
    private boolean check_naslagreferenties;
    private boolean check_bronnen;
    private boolean check_citaten;
    private boolean check_frequenties;
    private boolean check_webreferenties;
    private boolean check_notities;
    private boolean check_teksten = true;

    /**
     * Gets the titel
     * @return Returns a String
     */
    public String getTitel()
    {
	return titel;
    }

    /**
     * Sets the titel
     * @param titel The titel to set
     */
    public void setTitel(String titel)
    {
	if (titel != null)
	{
	    titel = titel.trim();
	}
	this.titel = titel;
    }

    /**
     * Gets the titelHTML
     * @return Returns a String
     */
    public String getTitelHTML()
    {
	return titelHTML;
    }

    /**
     * Sets the titelHTML
     * @param titelHTML The titelHTML to set
     */
    public void setTitelHTML(String titelHTML)
    {
	if (titelHTML != null)
	{
	    titelHTML = titelHTML.trim();
	}
	this.titelHTML = titelHTML;
    }

    /**
     * Gets the id
     * @return Returns a int
     */
    public int getId()
    {
	return id;
    }

    /**
     * Sets the id
     * @param id The id to set
     */
    public void setId(int id)
    {
	this.id = id;
    }

    /**
     * Gets the vraag
     * @return Returns a String
     */
    public String getVraag()
    {
	return vraag;
    }

    /**
     * Sets the vraag
     * @param vraag The vraag to set
     */
    public void setVraag(String vraag)
    {
	if (vraag != null)
	{
	    vraag = vraag.trim();
	}
	this.vraag = vraag;
    }

    /**
     * Gets the vraagHTML
     * @return Returns a String
     */
    public String getVraagHTML()
    {
	return vraagHTML;
    }

    /**
     * Sets the vraagHTML
     * @param vraagHTML The vraagHTML to set
     */
    public void setVraagHTML(String vraagHTML)
    {
	if (vraagHTML != null)
	{
	    vraagHTML = vraagHTML.trim();
	}
	this.vraagHTML = vraagHTML;
    }

    /**
     * Gets the informatie
     * @return Returns a String
     */
    public String getInformatie()
    {
	return informatie;
    }

    /**
     * Sets the informatie
     * @param informatie The informatie to set
     */
    public void setInformatie(String informatie)
    {
	if (informatie != null)
	{
	    informatie = informatie.trim();
	}
	this.informatie = informatie;
    }

    /**
     * Gets the informatieHTML
     * @return Returns a String
     */
    public String getInformatieHTML()
    {
	return informatieHTML;
    }

    /**
     * Sets the informatieHTML
     * @param informatieHTML The informatieHTML to set
     */
    public void setInformatieHTML(String informatieHTML)
    {
	if (informatieHTML != null)
	{
	    informatieHTML = informatieHTML.trim();
	}
	this.informatieHTML = informatieHTML;
    }

    /**
     * Gets the herformulering
     * @return Returns a String
     */
    public String getHerformulering()
    {
	return herformulering;
    }

    /**
     * Sets the herformulering
     * @param herformulering The herformulering to set
     */
    public void setHerformulering(String herformulering)
    {
	if (herformulering != null)
	{
	    herformulering = herformulering.trim();
	}
	this.herformulering = herformulering;
    }

    /**
     * Gets the herformuleringHTML
     * @return Returns a String
     */
    public String getHerformuleringHTML()
    {
	return herformuleringHTML;
    }

    /**
     * Sets the herformuleringHTML
     * @param herformuleringHTML The herformuleringHTML to set
     */
    public void setHerformuleringHTML(String herformuleringHTML)
    {
	if (herformuleringHTML != null)
	{
	    herformuleringHTML = herformuleringHTML.trim();
	}
	this.herformuleringHTML = herformuleringHTML;
    }

    /**
     * Gets the volgnummer
     * @return Returns a int
     */
    public int getVolgnummer()
    {
	return volgnummer;
    }

    /**
     * Sets the volgnummer
     * @param volgnummer The volgnummer to set
     */
    public void setVolgnummer(int volgnummer)
    {
	this.volgnummer = volgnummer;
    }

    /**
     * Gets the afgehandeld
     * @return Returns a boolean
     */
    public boolean getAfgehandeld()
    {
	return afgehandeld;
    }

    /**
     * Sets the afgehandeld
     * @param afgehandeld The afgehandeld to set
     */
    public void setAfgehandeld(boolean afgehandeld)
    {
	this.afgehandeld = afgehandeld;
    }

    /**
     * Gets the antwoord
     * @return Returns a String
     */
    public String getAntwoord()
    {
	return antwoord;
    }

    /**
     * Sets the antwoord
     * @param antwoord The antwoord to set
     */
    public void setAntwoord(String antwoord)
    {
	if (antwoord != null)
	{
	    antwoord = antwoord.trim();
	}
	this.antwoord = antwoord;
    }

    /**
     * Gets the antwoordHTML
     * @return Returns a String
     */
    public String getAntwoordHTML()
    {
	if (null != antwoordHTML)
	{
	    return antwoordHTML;
	} else
	{
	    return "";
	}
    }

    /**
     * Sets the antwoordHTML
     * @param antwoordHTML The antwoordHTML to set
     */
    public void setAntwoordHTML(String antwoordHTML)
    {
	if (antwoordHTML != null)
	{
	    antwoordHTML = antwoordHTML.trim();
	}
	this.antwoordHTML = antwoordHTML;
    }

    /**
     * Gets the toelichting
     * @return Returns a String
     */
    public String getToelichting()
    {
	return toelichting;
    }

    /**
     * Sets the toelichting
     * @param toelichting The toelichting to set
     */
    public void setToelichting(String toelichting)
    {
	if (toelichting != null)
	{
	    toelichting = toelichting.trim();
	}
	this.toelichting = toelichting;
    }

    /**
     * Gets the toelichtingHTML
     * @return Returns a String
     */
    public String getToelichtingHTML()
    {
	return toelichtingHTML;
    }

    /**
     * Sets the toelichtingHTML
     * @param toelichtingHTML The toelichtingHTML to set
     */
    public void setToelichtingHTML(String toelichtingHTML)
    {
	if (toelichtingHTML != null)
	{
	    toelichtingHTML = toelichtingHTML.trim();
	}
	this.toelichtingHTML = toelichtingHTML;
    }

    /**
     * Gets the bijzonderheid
     * @return Returns a String
     */
    public String getBijzonderheid()
    {
	return bijzonderheid;
    }

    /**
     * Sets the bijzonderheid
     * @param bijzonderheid The bijzonderheid to set
     */
    public void setBijzonderheid(String bijzonderheid)
    {
	if (bijzonderheid != null)
	{
	    bijzonderheid = bijzonderheid.trim();
	}
	this.bijzonderheid = bijzonderheid;
    }

    /**
     * Gets the bijzonderheidHTML
     * @return Returns a String
     */
    public String getBijzonderheidHTML()
    {
	return bijzonderheidHTML;
    }

    /**
     * Sets the bijzonderheidHTML
     * @param bijzonderheidHTML The bijzonderheidHTML to set
     */
    public void setBijzonderheidHTML(String bijzonderheidHTML)
    {
	if (bijzonderheidHTML != null)
	{
	    bijzonderheidHTML = bijzonderheidHTML.trim();
	}
	this.bijzonderheidHTML = bijzonderheidHTML;
    }

    /**
     * Gets the distributiedatum
     * @return Returns a Date
     */
    public Date getDistributiedatum()
    {
	return distributiedatum;
    }

    /**
     * Sets the distributiedatum
     * @param distributiedatum The distributiedatum to set
     */
    public void setDistributiedatum(Date distributiedatum)
    {
	this.distributiedatum = distributiedatum;
    }

    /**
     * Gets the distributiedatumString
     * @return Returns a String
     */
    public String getDistributiedatumString()
    {
	if (distributiedatum != null)
	{
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	    return formatter.format(distributiedatum);
	} else
	{
	    return distributiedatumString;
	}
    }

    /**
     * Sets the distributiedatumString
     * @param distributiedatumString The distributiedatumString to set
     */
    public void setDistributiedatumString(String distributiedatumString)
    {
	this.distributiedatumString = distributiedatumString;
    }

    /**
     * Gets the oproepId
     * @return Returns a int
     */
    public int getOproepId()
    {
	return oproepId;
    }

    /**
     * Sets the oproepId
     * @param oproepId The oproepId to set
     */
    public void setOproepId(int oproepId)
    {
	this.oproepId = oproepId;
    }

    /**
     * Gets the relevantieId
     * @return Returns a int
     */
    public int getRelevantieId()
    {
	return relevantieId;
    }

    /**
     * Sets the relevantieId
     * @param relevantieId The relevantieId to set
     */
    public void setRelevantieId(int relevantieId)
    {
	this.relevantieId = relevantieId;
    }

    /**
     * Gets the oproep
     * @return Returns a Oproep
     */
    public Oproep getOproep()
    {
	if (oproep == null)
	{
	    if (oproepId != 0)
	    {
		oproep = Oproep.findByPK(oproepId);
	    } else
	    {
		oproep = new Oproep();
	    }
	}
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
     * Gets the relevantie
     * @return Returns a ParameterActief
     */
    public ParameterActief getRelevantie()
    {
	if (relevantie == null)
	{
	    if (relevantieId != 0)
	    {
		relevantie = (ParameterActief) ParameterActief.findByPK(Queries.RELEVANTIE_BY_PK, relevantieId);
	    } else
	    {
		relevantie = new ParameterActief();
	    }
	}
	return relevantie;
    }

    /**
     * Sets the relevantie
     * @param relevantie The relevantie to set
     */
    public void setRelevantie(ParameterActief relevantie)
    {
	this.relevantie = relevantie;
    }

    /**
     * Gets all Taalvragen by Oproep from the database
     * @param criteria form containing the parameters that need to be searched on
     * @return list of Taalvragen that match the search-criteria
     */
    public static int[] findCountBySearchCriteria(SearchForm criteria)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	int[] values = {0, 0};
	try
	{
	    if (con != null)
	    {
		/* MAX & COUNT QUERY */
		String query = null;
		int index = 1;
		if (criteria.isCategorie())
		{
		    StringBuffer sqlSelect = new StringBuffer(Queries.TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_COUNT_SELECT);
		    StringBuffer sqlFrom = new StringBuffer(Queries.TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_COUNT_FROM);
		    StringBuffer sqlWhere = new StringBuffer(Queries.TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_COUNT_WHERE);
		    int counter = 0;
		    for (Iterator<Categorie> iter = criteria.getCategorieList().iterator(); iter.hasNext();)
		    {
			counter++;
			Categorie categorie = iter.next();
			if (categorie != null && categorie.getId() != 0)
			{
			    String catRelName = "TR_TLV_AWD_CTR" + counter;
			    String catParName = "TR_CTR" + counter;
			    sqlFrom.append(", TR_TLV_AWD_CTR ");
			    sqlFrom.append(catRelName);
			    sqlFrom.append(", TR_CTR ");
			    sqlFrom.append(catParName);
			    sqlWhere.append(" AND B.TLV_ID = ").append(catRelName).append(".TLV_ID ").append(" AND ").append(
				catRelName).append(".CTR_ID = ").append(catParName).append(".CTR_ID ").append(" AND (")
				.append(catParName).append(".CTR_NMR_TX LIKE ? ");
			    // if (categorie.getNummer().indexOf(".") == -1)
			    // {
			    sqlWhere.append(" OR ").append(catParName).append(".CTR_NMR_TX LIKE ? ");
			    // }
			    sqlWhere.append(")");
			}
		    }
		    query = sqlSelect.toString() + sqlFrom + sqlWhere + criteria.getVariablePartTLV();
		    System.out.println(query);
		} else
		{
		    query = Queries.TAALVRAGEN_SEARCH_FIXED_COUNT + criteria.getVariablePartTLV();
		}
		System.err.println("count-query: " + query);
		AppLogger.getInstance().debug(query);
		pst = con.prepareStatement(query);
		if (criteria.isCategorie())
		{
		    System.err.println("before:" + index);
		    for (Iterator<Categorie> iter = criteria.getCategorieList().iterator(); iter.hasNext();)
		    {
			System.err.println("in1:" + index);
			Categorie categorie = iter.next();
			if (categorie != null && categorie.getId() != 0)
			{
			    index = generateWildcard(index, pst, categorie);
			}
			System.err.println("in2:" + index);
		    }
		    System.err.println("after:" + index);
		    // pst.setString(index++, criteria.getCategorie1().getNummer().trim() + "%");
		    //
		    // if(criteria.getCategorie2() != null)
		    // pst.setString(index++, criteria.getCategorie2().getNummer().trim() + "%");
		}
		if (criteria.getDateSearch() == 2)
		{
		    if (criteria.getSingleDate() != null)
		    {
			DbUtil.pstSetDate(pst, index++, criteria.getDSingleDate());
		    }
		} else
		{
		    if (criteria.getStartDate() != null)
		    {
			DbUtil.pstSetDate(pst, index++, criteria.getDStartDate());
		    }
		    if (criteria.getEndDate() != null)
		    {
			DbUtil.pstSetDate(pst, index++, criteria.getDEndDate());
		    }
		}
		long start = System.currentTimeMillis();
		System.out.println(index);
		rs = pst.executeQuery();
		long end = System.currentTimeMillis();
		AppLogger.getInstance().debug("Time to run query: " + (end - start) + " milliseconds");
		if (rs.next())
		{
		    values[0] = rs.getInt("count");
		    if (values[0] != 0)
		    {
			values[1] = rs.getInt("max");
		    }
		}
	    } else
	    {
		System.err.println("Geen connectie !");
	    }
	} catch (java.sql.SQLException e)
	{
	    e.printStackTrace(System.err);
	} finally
	{
	    try
	    {
		if (con != null)
		{
		    if (rs != null)
		    {
			rs.close();
		    }
		    if (pst != null)
		    {
			pst.close();
		    }
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		sqle.printStackTrace(System.err);
	    }
	}
	return values;
    }

    /**
     * generates the correct wildcard for categorie matching
     * @param index index of pst
     * @param pst the preparedstatement
     * @param categorie the category
     * @return index of the wildcard?
     * @throws SQLException when an SQL exception occurs
     */
    public static int generateWildcard(int index, PreparedStatement pst, Categorie categorie) throws SQLException
    {
	// als er geen '.' karakter aanwezig is, match dan exact om te vermijden dat bvb categorie 12 ook gevonden wordt bij het
	// zoeken naar cat 1
	// en match ook expliciet op bvb 1.%
	/*
	 * if (categorie.getNummer().indexOf(".") == -1) {
	 */
	pst.setString(index++, categorie.getNummer());
	pst.setString(index++, categorie.getNummer() + ".%");
	/*
	 * } else { pst.setString(index++, categorie.getNummer() + "%"); }
	 */
	return index;
    }

    /**
     * Gets all Taalvragen by Oproep from the database
     * @param cache SearchCache
     * @return list of Taalvragen that match the search-criteria
     */
    public static Taalvraag[] findBySearchCriteria(SearchCache cache)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	Taalvraag[] allTaalvragen = null;
	if (cache.getCurrentCount() == cache.getPageCount())
	{
	    int index = cache.getMaxCount() % cache.getDisplayAmount();
	    if (index == 0)
	    {
		allTaalvragen = new Taalvraag[cache.getDisplayAmount()];
	    } else
	    {
		allTaalvragen = new Taalvraag[index];
	    }
	} else
	{
	    allTaalvragen = new Taalvraag[cache.getDisplayAmount()];
	}
	int index = 1;
	int arrayIndex = 0;
	try
	{
	    if (con != null)
	    {
		if (cache.getInterval() == 2) // Previous
		{
		    arrayIndex = allTaalvragen.length - 1;
		    AppLogger.getInstance().debug("BQ : " + cache.getBackwardQuery());
		    pst = con.prepareStatement(cache.getBackwardQuery());
		    pst.setInt(index++, cache.getIdLimit());
		    DbUtil.pstSetDate(pst, index++, cache.getCurrentMinDate());
		    DbUtil.pstSetDate(pst, index++, cache.getCurrentMinDate());
		    pst.setInt(index++, cache.getCurrentMinId());
		} else
		{
		    if (cache.getInterval() == 1)
		    {
			AppLogger.getInstance().debug("FQ : " + cache.getForwardQueryNext());
			pst = con.prepareStatement(cache.getForwardQueryNext());
		    } else
		    {
			AppLogger.getInstance().debug("1stQ : " + cache.getForwardQuery());
			pst = con.prepareStatement(cache.getForwardQuery());
		    }
		    pst.setInt(index++, cache.getIdLimit());
		    if (cache.getInterval() == 1)
		    {
			DbUtil.pstSetDate(pst, index++, cache.getCurrentMaxDate());
			DbUtil.pstSetDate(pst, index++, cache.getCurrentMaxDate());
			pst.setInt(index++, cache.getCurrentMaxId());
		    }
		    AppLogger.getInstance().debug("ID Limit: " + cache.getIdLimit());
		    AppLogger.getInstance().debug("Current Max ID: " + cache.getCurrentMaxId());
		    AppLogger.getInstance().debug("Current Min ID: " + cache.getCurrentMinId());
		}
		if (cache.getSingleDate() != null)
		{
		    DbUtil.pstSetDate(pst, index++, cache.getSingleDate());
		} else
		{
		    if (cache.getMinDate() != null)
		    {
			DbUtil.pstSetDate(pst, index++, cache.getMinDate());
		    }
		    if (cache.getMaxDate() != null)
		    {
			DbUtil.pstSetDate(pst, index++, cache.getMaxDate());
		    }
		}
		long start = System.currentTimeMillis();
		rs = pst.executeQuery();
		long end = System.currentTimeMillis();
		AppLogger.getInstance().debug("Time to run query: " + (end - start) + " milliseconds");
		int recordAmount = 0;
		while (rs.next())
		{
		    recordAmount++;
		    AppLogger.getInstance().debug("ID : " + String.valueOf(rs.getInt("id")));
		    Taalvraag taalvraag = new Taalvraag();
		    taalvraag.setId(rs.getInt("id"));
		    taalvraag.setTitel(rs.getString("titel"));
		    /* BUGFIX 2006-10-30 */
		    taalvraag.setTitelHTML(rs.getString("titelHTML"));
		    /* Result: correct display of special charachters in title */
		    if (rs.wasNull())
		    {
			taalvraag.setTitel(rs.getString("vraag"));
		    }
		    taalvraag.setOproepId(rs.getInt("oproepId"));
		    if (cache.getInterval() == 2)
		    {
			allTaalvragen[arrayIndex--] = taalvraag;
		    } else
		    {
			allTaalvragen[arrayIndex++] = taalvraag;
		    }
		    end = System.currentTimeMillis();
		}
		AppLogger.getInstance().debug("Finished Records");
	    } else
	    {
		System.err.println("Geen connectie !");
	    }
	} catch (java.sql.SQLException e)
	{
	    e.printStackTrace(System.err);
	} finally
	{
	    try
	    {
		if (con != null)
		{
		    if (rs != null)
		    {
			rs.close();
		    }
		    if (pst != null)
		    {
			pst.close();
		    }
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		sqle.printStackTrace(System.err);
	    }
	}
	return allTaalvragen;
    }

    private static String buildBtsQuery(SearchForm criteria)
    {
	boolean queryStarted = false;
	// Nakijken welke velden er moeten doorzocht worden
	StringBuffer velden = new StringBuffer("");
	StringBuffer query = new StringBuffer("");
	AppLogger.getInstance().debug("Titel:" + criteria.getTitel());
	if (criteria.getTitel())
	{
	    queryStarted = true;
	    velden.append("'ttl_db'");
	}
	AppLogger.getInstance().debug("Vraag = " + criteria.getVraag());
	if (criteria.getVraag())
	{
	    if (queryStarted)
	    {
		velden.append(", ");
	    } else
	    {
		queryStarted = true;
	    }
	    velden.append("'tlv_ds'");
	}
	AppLogger.getInstance().debug("Antwoord:" + criteria.getAntwoord());
	if (criteria.getAntwoord())
	{
	    if (queryStarted)
	    {
		velden.append(", ");
	    } else
	    {
		queryStarted = true;
	    }
	    velden.append("'awd_krt_db'");
	}
	AppLogger.getInstance().debug("Toelichting:" + criteria.getToelichting());
	if (criteria.getToelichting())
	{
	    if (queryStarted)
	    {
		velden.append(", ");
	    } else
	    {
		queryStarted = true;
	    }
	    velden.append("'awd_tol_db'");
	}
	AppLogger.getInstance().debug("Bijzonderheid:" + criteria.getBijzonderheid());
	if (criteria.getBijzonderheid())
	{
	    if (queryStarted)
	    {
		velden.append(", ");
	    } else
	    {
		queryStarted = true;
	    }
	    velden.append("'awd_bjz_db'");
	}
	if (queryStarted)
	{
	    // Trefwoordenlijst parsen
	    if (criteria.getAndTerms() != null)
	    {
		String andTerms=criteria.getAndTerms();
		andTerms=andTerms.replace("\\", "\\\\");
		andTerms=andTerms.replace("'", "''");
		andTerms=andTerms.replace("+", "\\+");
		andTerms=andTerms.replace("-", "\\-");
		andTerms=andTerms.replace("&&", "\\&&");
		andTerms=andTerms.replace("||", "\\||");
		andTerms=andTerms.replace("!", "\\!");
		andTerms=andTerms.replace("(", "\\(");
		andTerms=andTerms.replace(")", "\\)");
		andTerms=andTerms.replace("{", "\\{");
		andTerms=andTerms.replace("}", "\\}");
		andTerms=andTerms.replace("[", "\\[");
		andTerms=andTerms.replace("]", "\\]");
		andTerms=andTerms.replace("^", "\\^");
		andTerms=andTerms.replace("\"", "\\\"");
		andTerms=andTerms.replace("~", "\\~");
		andTerms=andTerms.replace("*", "\\*");
		andTerms=andTerms.replace("?", "\\?");
		andTerms=andTerms.replace(":", "\\:");
		andTerms=andTerms.trim();
		StringTokenizer tokenizer = new StringTokenizer(andTerms, " ");
		while (tokenizer.hasMoreTokens())
		{
		    query.append(" AND B.tlv_id IN(SELECT DISTINCT tlv_id FROM bts_tlv WHERE veld in (");
		    query.append(velden);
		    query.append(") and bts_contains(waarde, '\"");
		    query.append(tokenizer.nextToken());
		    query.append("\"'))");
		}
	    }
	    if (criteria.getPhrase() != null)
	    {
		//if (criteria.getAndTerms() != null)
		//{
		    query.append(" AND ");
		//}
		query.append(" B.tlv_id IN(SELECT DISTINCT tlv_id FROM bts_tlv WHERE veld in (");
		query.append(velden);
		query.append(") AND bts_contains(waarde, '\"");
		String phrase=criteria.getPhrase();
		phrase=phrase.replace("\\", "\\\\"); 
		phrase=phrase.replace("'", "''");               
		phrase=phrase.replace("+", "\\+");
		phrase=phrase.replace("-", "\\-");
		phrase=phrase.replace("&&", "\\&&");
		phrase=phrase.replace("||", "\\||");
		phrase=phrase.replace("!", "\\!");
		phrase=phrase.replace("(", "\\(");
		phrase=phrase.replace(")", "\\)");
		phrase=phrase.replace("{", "\\{");
		phrase=phrase.replace("}", "\\}");
		phrase=phrase.replace("[", "\\[");
		phrase=phrase.replace("]", "\\]");
		phrase=phrase.replace("^", "\\^");
		phrase=phrase.replace("\"", "\\\"");
		phrase=phrase.replace("~", "\\~");
		phrase=phrase.replace("*", "\\*");
		phrase=phrase.replace("?", "\\?");
		phrase=phrase.replace(":", "\\:");
		query.append(phrase);
		query.append("\"'))");
	    }
	}
	return query.toString();
    }

    public static void buildVariableQueryPart(SearchForm criteria)
    {
	StringBuffer query = new StringBuffer("");
	if (criteria.getDateSearch() == 2)
	{
	    if (criteria.getSingleDate() != null)
	    {
		query.append(" AND A.ORP_DT = ? ");
	    }
	} else
	{
	    if (criteria.getStartDate() != null)
	    {
		query.append(" AND A.ORP_DT >= ? ");
	    }
	    if (criteria.getEndDate() != null)
	    {
		query.append(" AND A.ORP_DT <= ? ");
	    }
	}
	if (criteria.getRelevantie() > 0)
	{
	    query.append(" AND B.RVT_ID = ");
	    query.append(criteria.getRelevantie());
	}
	if (criteria.isEmailUit())
	{
	    query.append(" AND X.MDM_ID = ");
	    query.append(ParameterActief.MEDIUM_EMAIL_ID);
	}
	if (criteria.getUserId() != 0)
	{
	    if (criteria.getEigenTV()/* || criteria.getEigenTXT()*/)
	    {
		query.append(" AND A.TAV_ID = ");
		query.append(criteria.getUserId());
	    } else if (criteria.getAlleTV()/* || criteria.getAlleTXT()*/)
	    {
		query.append(" AND A.TAV_ID != ");
		query.append(criteria.getUserId());
	    }
	}
	if (!criteria.isCategorie() && (criteria.getAndTerms() != null || criteria.getPhrase() != null))
	{
	    query.append(buildBtsQuery(criteria));
	}
	AppLogger.getInstance().debug("Query: " + query);
	criteria.setVariablePartTLV(query.toString());
    }

    public static String[] buildFixedQueries(SearchForm criteria)
    {
	String[] queries = new String[3];
	// BackwardQuery
	StringBuffer backwardQuery = new StringBuffer();
	backwardQuery.append("SELECT FIRST ");
	if (criteria.getEigenTXT() || criteria.getAlleTXT())
	{
	    backwardQuery.append(SearchForm.DISPLAY_AMOUNT);
	} else
	{
	    backwardQuery.append(SearchForm.DISPLAY_AMOUNT * 2);
	}
	if (criteria.isCategorie())
	{
	    // if(criteria.getCategorie2() != null)
	    // {
	    // backwardQuery.append(Queries.TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_PREVIOUS);
	    // backwardQuery.append(" AND D.CTR_NMR_TX LIKE \"" + criteria.getCategorie1().getNummer().trim() + "%\" AND
	    // F.CTR_NMR_TX LIKE \"" + criteria.getCategorie2().getNummer().trim() + "%\"");
	    // }
	    // else
	    // {
	    // backwardQuery.append(Queries.TAALVRAGEN_CATEGORIESEARCH_FIXED_PREVIOUS);
	    // backwardQuery.append(" AND D.CTR_NMR_TX LIKE \"" + criteria.getCategorie1().getNummer().trim() + "%\"");
	    // }
	    String sqlSelect = Queries.TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_PREVIOUS_SELECT;
	    String sqlFrom = Queries.TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_PREVIOUS_FROM;
	    String sqlWhere = Queries.TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_PREVIOUS_WHERE;
	    int counter = 0;
	    for (Iterator<Categorie> iter = criteria.getCategorieList().iterator(); iter.hasNext();)
	    {
		counter++;
		Categorie categorie = iter.next();
		if (categorie != null)
		{
		    String catRelName = "TR_TLV_AWD_CTR" + counter;
		    String catParName = "TR_CTR" + counter;
		    sqlFrom += ", TR_TLV_AWD_CTR " + catRelName + ", TR_CTR " + catParName;
		    sqlWhere +=
			" AND B.TLV_ID = " + catRelName + ".TLV_ID " + " AND " + catRelName + ".CTR_ID = " + catParName
			    + ".CTR_ID ";
		    /*
		     * if (categorie.getNummer().indexOf(".") == -1) {
		     */
		    sqlWhere +=
			" AND (" + catParName + ".CTR_NMR_TX LIKE \"" + categorie.getNummer() + "\" OR " + catParName
			    + ".CTR_NMR_TX LIKE \"" + categorie.getNummer() + ".%\"" + ")";/*
											    * } else { sqlWhere += " AND " +
											    * catParName + ".CTR_NMR_TX LIKE \""
											    * + categorie.getNummer() + "%\""; }
											    */
		}
	    }
	    backwardQuery.append(sqlSelect);
	    backwardQuery.append(sqlFrom);
	    backwardQuery.append(sqlWhere);
	} else
	{
	    backwardQuery.append(Queries.TAALVRAGEN_SEARCH_FIXED_PREVIOUS);
	}
	backwardQuery.append(criteria.getVariablePartTLV());
	backwardQuery.append(" ORDER BY A.ORP_DT, B.TLV_ID DESC");
	queries[0] = backwardQuery.toString();
	// ForwardQuery
	StringBuffer forwardQuery = new StringBuffer();
	forwardQuery.append("SELECT FIRST ");
	if (criteria.getEigenTXT() || criteria.getAlleTXT())
	{
	    forwardQuery.append(SearchForm.DISPLAY_AMOUNT);
	} else
	{
	    forwardQuery.append(SearchForm.DISPLAY_AMOUNT * 2);
	}
	if (criteria.isCategorie())
	{
	    // if(criteria.getCategorie2() != null)
	    // {
	    // forwardQuery.append(Queries.TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_NEXT);
	    // forwardQuery.append(" AND D.CTR_NMR_TX LIKE \"" + criteria.getCategorie1().getNummer().trim() + "%\" AND
	    // F.CTR_NMR_TX LIKE \"" + criteria.getCategorie2().getNummer().trim() + "%\"");
	    // }
	    // else
	    // {
	    // forwardQuery.append(Queries.TAALVRAGEN_CATEGORIESEARCH_FIXED_NEXT);
	    // forwardQuery.append(" AND D.CTR_NMR_TX LIKE \"" + criteria.getCategorie1().getNummer().trim() + "%\"");
	    // }
	    String sqlSelect = Queries.TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_NEXT_SELECT;
	    String sqlFrom = Queries.TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_NEXT_FROM;
	    String sqlWhere = Queries.TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_NEXT_WHERE;
	    int counter = 0;
	    for (Iterator<Categorie> iter = criteria.getCategorieList().iterator(); iter.hasNext();)
	    {
		counter++;
		Categorie categorie = iter.next();
		if (categorie != null)
		{
		    String catRelName = "TR_TLV_AWD_CTR" + counter;
		    String catParName = "TR_CTR" + counter;
		    sqlFrom += ", TR_TLV_AWD_CTR " + catRelName + ", TR_CTR " + catParName;
		    sqlWhere +=
			" AND B.TLV_ID = " + catRelName + ".TLV_ID " + " AND " + catRelName + ".CTR_ID = " + catParName
			    + ".CTR_ID ";
		    /*
		     * if (categorie.getNummer().indexOf(".") == -1) {
		     */
		    sqlWhere +=
			" AND (" + catParName + ".CTR_NMR_TX LIKE \"" + categorie.getNummer() + "\" OR " + catParName
			    + ".CTR_NMR_TX LIKE \"" + categorie.getNummer() + ".%\"" + ")";
		    /*
		     * } else { sqlWhere += " AND " + catParName + ".CTR_NMR_TX LIKE \"" + categorie.getNummer() + "%\""; }
		     */
		}
	    }
	    forwardQuery.append(sqlSelect);
	    forwardQuery.append(sqlFrom);
	    forwardQuery.append(sqlWhere);
	} else
	{
	    forwardQuery.append(Queries.TAALVRAGEN_SEARCH_FIXED_NEXT);
	}
	queries[1] = forwardQuery.toString() + criteria.getVariablePartTLV() + " ORDER BY A.ORP_DT DESC, B.TLV_ID";
	forwardQuery.append(" AND (A.orp_dt < ? or (A.orp_dt = ? and B.tlv_id > ?)) ");
	forwardQuery.append(criteria.getVariablePartTLV());
	forwardQuery.append(" ORDER BY A.ORP_DT DESC, B.TLV_ID");
	queries[2] = forwardQuery.toString();
	return queries;
    }

    /**
     * Gets all Taalvragen by Oproep from the database
     * @return Returns an ArrayList containing the 'taalvragen'
     */
    public static ArrayList<Taalvraag> findAfgehandeld()
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	ArrayList<Taalvraag> allTaalvragen = new ArrayList<Taalvraag>();
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.TAALVRAGEN_AFGEHANDELD);
		rs = pst.executeQuery();
		while (rs.next())
		{
		    Taalvraag taalvraag = new Taalvraag();
		    taalvraag.setId(rs.getInt("id"));
		    taalvraag.setVraag(rs.getString("vraag"));
		    taalvraag.setVraagHTML(rs.getString("vraagHTML"));
		    taalvraag.setInformatie(rs.getString("informatie"));
		    taalvraag.setInformatieHTML(rs.getString("informatieHTML"));
		    taalvraag.setVolgnummer(rs.getInt("volgnummer"));
		    taalvraag.setAfgehandeld(true);
		    taalvraag.setAntwoord(rs.getString("antwoord"));
		    taalvraag.setAntwoordHTML(rs.getString("antwoordHTML"));
		    taalvraag.setToelichting(rs.getString("toelichting"));
		    taalvraag.setToelichtingHTML(rs.getString("toelichtingHTML"));
		    taalvraag.setBijzonderheid(rs.getString("bijzonderheid"));
		    taalvraag.setBijzonderheidHTML(rs.getString("bijzonderheidHTML"));
		    taalvraag.setOproepId(rs.getInt("oproepId"));
		    taalvraag.setTitel(rs.getString("titel"));
		    taalvraag.setTitelHTML(rs.getString("titelHTML"));
		    taalvraag.setRelevantieId(rs.getInt("relevantieId"));
		    taalvraag.setHerformulering(rs.getString("herformulering"));
		    taalvraag.setHerformuleringHTML(rs.getString("herformuleringHTML"));
		    taalvraag.setDistributiedatum(rs.getDate("distributiedatum"));
		    allTaalvragen.add(taalvraag);
		}
	    } else
	    {
		System.err.println("Geen connectie !");
	    }
	} catch (java.sql.SQLException e)
	{
	    e.printStackTrace(System.err);
	} finally
	{
	    try
	    {
		if (con != null)
		{
		    if (rs != null)
		    {
			rs.close();
		    }
		    if (pst != null)
		    {
			pst.close();
		    }
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		sqle.printStackTrace(System.err);
	    }
	}
	return allTaalvragen;
    }

    /**
     * Gets all Taalvragen not yet Ofloaded from the database
     * @return Returns a ArrayList
     */
    public static ArrayList<String> findAllToOfload()
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	ArrayList<String> allTaalvragen = new ArrayList<String>();
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.TAALVRAGEN_OFLOAD);
		rs = pst.executeQuery();
		while (rs.next())
		{
		    StringBuffer sb = new StringBuffer();
		    sb.append(rs.getString("vraag"));
		    sb.append("|");
		    sb.append(rs.getString("antwoord"));
		    sb.append("|");
		    sb.append(rs.getString("toelichting"));
		    sb.append("|");
		    sb.append(rs.getString("herkomstnummer"));
		    sb.append("|");
		    sb.append(rs.getString("titel"));
		    sb.append("|");
		    sb.append(rs.getString("email"));
		    allTaalvragen.add(sb.toString());
		}
	    } else
	    {
		System.err.println("Geen connectie !");
	    }
	} catch (java.sql.SQLException e)
	{
	    e.printStackTrace(System.err);
	} finally
	{
	    try
	    {
		if (con != null)
		{
		    if (rs != null)
		    {
			rs.close();
		    }
		    if (pst != null)
		    {
			pst.close();
		    }
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		sqle.printStackTrace(System.err);
	    }
	}
	return allTaalvragen;
    }

    /**
     * Gets the Taalvraag from the database by it's id
     * @param id The id to retrieve
     * @return Returns a Taalvraag
     */
    public static Taalvraag findByPK(int id)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	Taalvraag taalvraag = new Taalvraag();
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.TAALVRAAG_BY_PK);
		pst.setInt(1, id);
		rs = pst.executeQuery();
		if (rs.next())
		{
		    taalvraag.setId(id);
		    taalvraag.setVraag(rs.getString("vraag"));
		    taalvraag.setVraagHTML(rs.getString("vraagHTML"));
		    taalvraag.setInformatie(rs.getString("informatie"));
		    taalvraag.setInformatieHTML(rs.getString("informatieHTML"));
		    taalvraag.setVolgnummer(rs.getInt("volgnummer"));
		    taalvraag.setAfgehandeld(rs.getBoolean("afgehandeld"));
		    taalvraag.setAntwoord(rs.getString("antwoord"));
		    taalvraag.setAntwoordHTML(rs.getString("antwoordHTML"));
		    taalvraag.setToelichting(rs.getString("toelichting"));
		    taalvraag.setToelichtingHTML(rs.getString("toelichtingHTML"));
		    taalvraag.setBijzonderheid(rs.getString("bijzonderheid"));
		    taalvraag.setBijzonderheidHTML(rs.getString("bijzonderheidHTML"));
		    taalvraag.setOproepId(rs.getInt("oproepId"));
		    taalvraag.setTitel(rs.getString("titel"));
		    taalvraag.setTitelHTML(rs.getString("titelHTML"));
		    taalvraag.setRelevantieId(rs.getInt("relevantieId"));
		    taalvraag.setHerformulering(rs.getString("herformulering"));
		    taalvraag.setHerformuleringHTML(rs.getString("herformuleringHTML"));
		    taalvraag.setDistributiedatum(rs.getDate("distributiedatum"));
		}
	    } else
	    {
		System.err.println("Geen connectie !");
	    }
	} catch (java.sql.SQLException e)
	{
	    e.printStackTrace(System.err);
	} finally
	{
	    try
	    {
		if (con != null)
		{
		    if (rs != null)
		    {
			rs.close();
		    }
		    if (pst != null)
		    {
			pst.close();
		    }
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		sqle.printStackTrace(System.err);
	    }
	}
	return taalvraag;
    }

    /**
     * Gets all Taalvragen by Oproep from the database
     * @param oproepId The id from the oproep we want to get the taalvragen for
     * @return Returns a ArrayList
     */
    public static ArrayList<Taalvraag> findAllByOproep(int oproepId)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	ArrayList<Taalvraag> allTaalvragen = new ArrayList<Taalvraag>();
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.TAALVRAGEN_BY_PARENT);
		pst.setInt(1, oproepId);
		rs = pst.executeQuery();
		while (rs.next())
		{
		    Taalvraag taalvraag = new Taalvraag();
		    taalvraag.setId(rs.getInt("id"));
		    taalvraag.setVraag(rs.getString("vraag"));
		    taalvraag.setVraagHTML(rs.getString("vraagHTML"));
		    taalvraag.setInformatie(rs.getString("informatie"));
		    taalvraag.setInformatieHTML(rs.getString("informatieHTML"));
		    taalvraag.setVolgnummer(rs.getInt("volgnummer"));
		    taalvraag.setAfgehandeld(rs.getBoolean("afgehandeld"));
		    taalvraag.setAntwoord(rs.getString("antwoord"));
		    taalvraag.setAntwoordHTML(rs.getString("antwoordHTML"));
		    taalvraag.setToelichting(rs.getString("toelichting"));
		    taalvraag.setToelichtingHTML(rs.getString("toelichtingHTML"));
		    taalvraag.setBijzonderheid(rs.getString("bijzonderheid"));
		    taalvraag.setBijzonderheidHTML(rs.getString("bijzonderheidHTML"));
		    taalvraag.setOproepId(oproepId);
		    taalvraag.setTitel(rs.getString("titel"));
		    taalvraag.setTitelHTML(rs.getString("titelHTML"));
		    taalvraag.setRelevantieId(rs.getInt("relevantieId"));
		    taalvraag.setHerformulering(rs.getString("herformulering"));
		    taalvraag.setHerformuleringHTML(rs.getString("herformuleringHTML"));
		    taalvraag.setDistributiedatum(rs.getDate("distributiedatum"));
		    allTaalvragen.add(taalvraag);
		}
	    } else
	    {
		System.err.println("Geen connectie !");
	    }
	} catch (java.sql.SQLException e)
	{
	    e.printStackTrace(System.err);
	} finally
	{
	    try
	    {
		if (con != null)
		{
		    if (rs != null)
		    {
			rs.close();
		    }
		    if (pst != null)
		    {
			pst.close();
		    }
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		sqle.printStackTrace(System.err);
	    }
	}
	return allTaalvragen;
    }

    /**
     * Inserts a Tekst for a Taalvraag
     * @param taalvraagId The TaalvraagId to insert
     * @param tekstId The TekstId to insert
     * @return Return boolean
     */
    public static boolean insertTekst(int taalvraagId, int tekstId)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.INSERT_TEKST_IN_TAALVRAAG);
		pst.setInt(1, taalvraagId);
		pst.setInt(2, tekstId);
		int check = pst.executeUpdate();
		return check == 1;
	    } else
	    {
		System.err.println("Geen connectie !");
	    }
	} catch (java.sql.SQLException e)
	{
	    e.printStackTrace(System.err);
	} finally
	{
	    try
	    {
		if (con != null)
		{
		    if (pst != null)
		    {
			pst.close();
		    }
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		sqle.printStackTrace(System.err);
	    }
	}
	return false;
    }

    /**
     * Deletes a Tekst for a Taalvraag
     * @param taalvraagId The TaalvraagId to delete
     * @param tekstId The TekstId to delete
     * @return Return boolean
     */
    public static boolean deleteTekst(int taalvraagId, int tekstId)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.DELETE_TEKST_IN_TAALVRAAG);
		pst.setInt(1, taalvraagId);
		pst.setInt(2, tekstId);
		int check = pst.executeUpdate();
		return check == 1;
	    } else
	    {
		System.err.println("Geen connectie !");
	    }
	} catch (java.sql.SQLException e)
	{
	    e.printStackTrace(System.err);
	} finally
	{
	    try
	    {
		if (con != null)
		{
		    if (pst != null)
		    {
			pst.close();
		    }
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		sqle.printStackTrace(System.err);
	    }
	}
	return false;
    }

    /**
     * Deletes all Teksten for a Taalvraag
     * @param taalvraagId The TaalvraagId to delete
     * @return Return boolean
     */
    public static boolean deleteTeksten(int taalvraagId)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.DELETE_TEKSTEN_IN_TAALVRAAG);
		pst.setInt(1, taalvraagId);
		int check = pst.executeUpdate();
		return check == 1;
	    } else
	    {
		System.err.println("Geen connectie !");
	    }
	} catch (java.sql.SQLException e)
	{
	    e.printStackTrace(System.err);
	} finally
	{
	    try
	    {
		if (con != null)
		{
		    if (pst != null)
		    {
			pst.close();
		    }
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		sqle.printStackTrace(System.err);
	    }
	}
	return false;
    }

    /**
     * Inserts a Taalvraag
     * @param taalvraag The Taalvraag to insert
     * @return Return int
     */
    public static int insert(Taalvraag taalvraag)
    {
	int id = 0;
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.INSERT_TAALVRAAG);
		if (taalvraag.getVraag() == null)
		{ // Weer te verwijderen, moet opgevangen worden
		    taalvraag.setVraag("");
		}
		if (taalvraag.getAntwoord() == null)
		{
		    taalvraag.setAntwoord("");
		}
		if (taalvraag.getToelichting() == null)
		{
		    taalvraag.setToelichting("");
		}
		if (taalvraag.getTitel() == null)
		{
		    taalvraag.setTitel("");
		}
		if (taalvraag.getTitelHTML() == null)
		{
		    taalvraag.setTitelHTML("");
		}
		Util.pstSetClob(pst, 1, taalvraag.getTitel());
		Util.pstSetClob(pst, 2, taalvraag.getTitelHTML());
		Util.pstSetClob(pst, 3, taalvraag.getVraag());
		Util.pstSetClob(pst, 4, taalvraag.getVraagHTML());
		Util.pstSetClob(pst, 5, taalvraag.getAntwoord());
		Util.pstSetClob(pst, 6, taalvraag.getAntwoordHTML());
		Util.pstSetClob(pst, 7, taalvraag.getToelichting());
		Util.pstSetClob(pst, 8, taalvraag.getToelichtingHTML());
		pst.setInt(9, taalvraag.getVolgnummer());
		pst.setBoolean(10, taalvraag.getAfgehandeld());
		pst.setInt(11, taalvraag.getOproepId());
		pst.executeUpdate();
		id = ((IfxStatement) pst).getSerial();
	    } else
	    {
		System.err.println("Geen connectie !");
	    }
	} catch (java.sql.SQLException e)
	{
	    e.printStackTrace(System.err);
	} finally
	{
	    try
	    {
		if (con != null)
		{
		    if (pst != null)
		    {
			pst.close();
		    }
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		sqle.printStackTrace(System.err);
	    }
	}
	return id;
    }

    /**
     * Updates a Taalvraag
     * @param taalvraag The Taalvraag to change
     * @return Return boolean
     */
    public static boolean update(Taalvraag taalvraag)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.UPDATE_TAALVRAAG);
		Util.pstSetClob(pst, 1, taalvraag.getTitel());
		Util.pstSetClob(pst, 2, taalvraag.getTitelHTML());
		Util.pstSetClob(pst, 3, taalvraag.getVraag());
		Util.pstSetClob(pst, 4, taalvraag.getVraagHTML());
		Util.pstSetClob(pst, 5, taalvraag.getAntwoord());
		Util.pstSetClob(pst, 6, taalvraag.getAntwoordHTML());
		Util.pstSetClob(pst, 7, taalvraag.getToelichting());
		Util.pstSetClob(pst, 8, taalvraag.getToelichtingHTML());
		pst.setInt(9, taalvraag.getVolgnummer());
		pst.setBoolean(10, taalvraag.getAfgehandeld());
		pst.setInt(11, taalvraag.getOproepId());
		pst.setInt(12, taalvraag.getId());
		int check = pst.executeUpdate();
		return check == 1;
	    } else
	    {
		System.err.println("Geen connectie !");
		return false;
	    }
	} catch (java.sql.SQLException e)
	{
	    e.printStackTrace(System.err);
	    return false;
	} finally
	{
	    try
	    {
		if (con != null)
		{
		    if (pst != null)
		    {
			pst.close();
		    }
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		sqle.printStackTrace(System.err);
	    }
	}
    }

    /**
     * Updates a Taalvraag with the "Kenmerken"
     * @param taalvraag The Taalvraag to change
     * @return Return boolean
     */
    public static boolean updateKenmerken(Taalvraag taalvraag)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.UPDATE_KENMERKEN);
		if (taalvraag.getInformatie() == null)
		{
		    taalvraag.setInformatie("");
		}
		if (taalvraag.getHerformulering() == null)
		{
		    taalvraag.setHerformulering("");
		}
		Util.pstSetClob(pst, 1, taalvraag.getInformatie());
		Util.pstSetClob(pst, 2, taalvraag.getInformatieHTML());
		Util.pstSetClob(pst, 3, taalvraag.getHerformulering());
		Util.pstSetClob(pst, 4, taalvraag.getHerformuleringHTML());
		Util.pstSetInt(pst, 5, taalvraag.getRelevantieId());
		pst.setInt(6, taalvraag.getId());
		int check = pst.executeUpdate();
		return check == 1;
	    } else
	    {
		System.err.println("Geen connectie !");
		return false;
	    }
	} catch (java.sql.SQLException e)
	{
	    e.printStackTrace(System.err);
	    return false;
	} finally
	{
	    try
	    {
		if (con != null)
		{
		    if (pst != null)
		    {
			pst.close();
		    }
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		sqle.printStackTrace(System.err);
	    }
	}
    }

    /**
     * Updates a Taalvraag with the "Bijzonderheid"
     * @param taalvraag The Taalvraag to change
     * @return Return boolean
     */
    public static boolean updateBijzonderheid(Taalvraag taalvraag)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.UPDATE_BIJZONDERHEID);
		Util.pstSetClob(pst, 1, taalvraag.getBijzonderheid());
		Util.pstSetClob(pst, 2, taalvraag.getBijzonderheidHTML());
		pst.setInt(3, taalvraag.getId());
		int check = pst.executeUpdate();
		return check == 1;
	    } else
	    {
		System.err.println("Geen connectie !");
		return false;
	    }
	} catch (java.sql.SQLException e)
	{
	    e.printStackTrace(System.err);
	    return false;
	} finally
	{
	    try
	    {
		if (con != null)
		{
		    if (pst != null)
		    {
			pst.close();
		    }
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		sqle.printStackTrace(System.err);
	    }
	}
    }

    /**
     * Updates a Taalvraag with the "Distributiedatum"
     * @param taalvraag The Taalvraag to change
     * @return Return boolean
     */
    public static boolean updateDistributiedatum(Taalvraag taalvraag)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.UPDATE_DISTRIBUTIEDATUM);
		pst.setDate(1, taalvraag.getDistributiedatum());
		pst.setInt(2, taalvraag.getId());
		int check = pst.executeUpdate();
		return check == 1;
	    } else
	    {
		System.err.println("Geen connectie !");
		return false;
	    }
	} catch (java.sql.SQLException e)
	{
	    e.printStackTrace(System.err);
	    return false;
	} finally
	{
	    try
	    {
		if (con != null)
		{
		    if (pst != null)
		    {
			pst.close();
		    }
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		sqle.printStackTrace(System.err);
	    }
	}
    }

    /**
     * Updates a Taalvraag to change the "volgnummer"
     * @param taalvraag The Taalvraag to change
     * @return Return boolean
     */
    public static boolean updateVolgnummer(Taalvraag taalvraag)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.UPDATE_TAALVRAAG_VOLGNUMMER);
		pst.setInt(1, taalvraag.getVolgnummer());
		pst.setInt(2, taalvraag.getId());
		int check = pst.executeUpdate();
		return check == 1;
	    } else
	    {
		System.err.println("Geen connectie !");
		return false;
	    }
	} catch (java.sql.SQLException e)
	{
	    e.printStackTrace(System.err);
	    return false;
	} finally
	{
	    try
	    {
		if (con != null)
		{
		    if (pst != null)
		    {
			pst.close();
		    }
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		sqle.printStackTrace(System.err);
	    }
	}
    }

    /**
     * Deletes a Taalvraag
     * @param id The id to delete
     * @return Return boolean
     */
    public static boolean delete(int id)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.DELETE_TAALVRAAG);
		pst.setInt(1, id);
		int check = pst.executeUpdate();
		return check == 1;
	    } else
	    {
		System.err.println("Geen connectie !");
		return false;
	    }
	} catch (java.sql.SQLException e)
	{
	    e.printStackTrace(System.err);
	    return false;
	} finally
	{
	    try
	    {
		if (con != null)
		{
		    if (pst != null)
		    {
			pst.close();
		    }
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		sqle.printStackTrace(System.err);
	    }
	}
    }

    /**
     * Deletes a Verity Search entry for a Taalvraag
     * @param taalvraagId The id of the taalvraag to delete
     * @return Return boolean
     */
    /*
     * public static boolean deleteVerityEntry(int taalvraagId) { IDbConnection dbconnection = MyDbConnection.getInstance();
     * Connection con = dbconnection.getConnection(); PreparedStatement pst = null; try { if (con != null) { pst =
     * con.prepareStatement(Queries.DELETE_VERITY_FOR_TAALVRAAG); pst.setInt(1, taalvraagId); int check = pst.executeUpdate();
     * return check == 1; } else { System.err.println("Geen connectie !"); return false; } } catch (java.sql.SQLException e) {
     * e.printStackTrace(System.err); return false; } finally { try { if (con != null) { if (pst != null) { pst.close(); }
     * dbconnection.releaseConnection(con); } } catch (java.sql.SQLException sqle) { sqle.printStackTrace(System.err); } } }
     */
    /**
     * Gets the maximum number of al the volgnummers from the taalvragen linked to a oproep
     * @param oproepId The oproepId to retrieve the maximum volgnummer of its taalvragen from
     * @return Returns a int
     */
    public static int findMaxVolgnummer(int oproepId)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.TAALVRAAG_MAX_VOLGNUMMER);
		pst.setInt(1, oproepId);
		rs = pst.executeQuery();
		if (rs.next())
		{
		    return rs.getInt("maximum");
		}
	    } else
	    {
		System.err.println("Geen connectie !");
	    }
	} catch (java.sql.SQLException e)
	{
	    e.printStackTrace(System.err);
	} finally
	{
	    try
	    {
		if (con != null)
		{
		    if (rs != null)
		    {
			rs.close();
		    }
		    if (pst != null)
		    {
			pst.close();
		    }
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		sqle.printStackTrace(System.err);
	    }
	}
	return 1;
    }

    /**
     * Gets the categorien
     * @return Returns a ArrayList
     */
    public ArrayList<Categorie> getCategorien()
    {
	if (categorien == null)
	{
	    if (id != 0)
	    {
		categorien = Categorie.findByTaalvraagId(id);
		if (categorien.size() == 0)
		{
		    categorien = null;
		}
	    } else
	    {
		categorien = null;
	    }
	}
	return categorien;
    }

    /**
     * Sets the categorien
     * @param categorien The categorien to set
     */
    public void setCategorien(ArrayList<Categorie> categorien)
    {
	this.categorien = categorien;
    }

    /**
     * Gets the naslagreferenties
     * @return Returns a ArrayList
     */
    public ArrayList<Naslagreferentie> getNaslagreferenties()
    {
	if (naslagreferenties == null)
	{
	    if (id != 0)
	    {
		naslagreferenties = Naslagreferentie.findAllByParent(id, 0);
		if (naslagreferenties.size() == 0)
		{
		    naslagreferenties = null;
		}
	    } else
	    {
		naslagreferenties = null;
	    }
	}
	return naslagreferenties;
    }

    /**
     * Sets the naslagreferenties
     * @param naslagreferenties The naslagreferenties to set
     */
    public void setNaslagreferenties(ArrayList<Naslagreferentie> naslagreferenties)
    {
	this.naslagreferenties = naslagreferenties;
    }

    /**
     * Gets the bronnen
     * @return Returns a ArrayList
     */
    public ArrayList<Bron> getBronnen()
    {
	if (bronnen == null)
	{
	    if (id != 0)
	    {
		bronnen = Bron.findAllByParent(id, 0);
		if (bronnen.size() == 0)
		{
		    bronnen = null;
		}
	    } else
	    {
		bronnen = null;
	    }
	}
	return bronnen;
    }

    /**
     * Sets the bronnen
     * @param bronnen The bronnen to set
     */
    public void setBronnen(ArrayList<Bron> bronnen)
    {
	this.bronnen = bronnen;
    }

    /**
     * Gets the citaten
     * @return Returns a ArrayList
     */
    public ArrayList<Citaat> getCitaten()
    {
	if (citaten == null)
	{
	    if (id != 0)
	    {
		citaten = Citaat.findAllByParent(id, 0);
		if (citaten.size() == 0)
		{
		    citaten = null;
		}
	    } else
	    {
		citaten = null;
	    }
	}
	return citaten;
    }

    /**
     * Sets the citaten
     * @param citaten The citaten to set
     */
    public void setCitaten(ArrayList<Citaat> citaten)
    {
	this.citaten = citaten;
    }

    /**
     * Gets the frequenties
     * @return Returns a ArrayList
     */
    public ArrayList<Frequentie> getFrequenties()
    {
	if (frequenties == null)
	{
	    if (id != 0)
	    {
		frequenties = Frequentie.findAllByParent(id, 0);
		if (frequenties.size() == 0)
		{
		    frequenties = null;
		}
	    } else
	    {
		frequenties = null;
	    }
	}
	return frequenties;
    }

    /**
     * Sets the frequenties
     * @param frequenties The frequenties to set
     */
    public void setFrequenties(ArrayList<Frequentie> frequenties)
    {
	this.frequenties = frequenties;
    }

    /**
     * Gets the webreferenties
     * @return Returns a ArrayList
     */
    public ArrayList<Webreferentie> getWebreferenties()
    {
	if (webreferenties == null)
	{
	    if (id != 0)
	    {
		webreferenties = Webreferentie.findAllByParent(id, 0);
		if (webreferenties.size() == 0)
		{
		    webreferenties = null;
		}
	    } else
	    {
		webreferenties = null;
	    }
	}
	return webreferenties;
    }

    /**
     * Gets the teksten
     * @return Returns a ArrayList
     */
    public ArrayList<Tekst> getTeksten()
    {
	if (teksten == null)
	{
	    if (id != 0)
	    {
		teksten = Tekst.findAllByTaalvraag(id);
		if (teksten.size() == 0)
		{
		    teksten = null;
		}
	    } else
	    {
		teksten = null;
	    }
	}
	return teksten;
    }

    /**
     * Sets the teksten
     * @param teksten The teksten to set
     */
    public void setTeksten(ArrayList<Tekst> teksten)
    {
	this.teksten = teksten;
    }

    /**
     * Sets the webreferenties
     * @param webreferenties The webreferenties to set
     */
    public void setWebreferenties(ArrayList<Webreferentie> webreferenties)
    {
	this.webreferenties = webreferenties;
    }

    /**
     * Gets the notities
     * @return Returns a ArrayList
     */
    public ArrayList<Notitie> getNotities()
    {
	if (notities == null)
	{
	    if (id != 0)
	    {
		notities = Notitie.findAllByParent(id, 0);
		if (notities.size() == 0)
		{
		    notities = null;
		}
	    } else
	    {
		notities = null;
	    }
	}
	return notities;
    }

    /**
     * Sets the notities
     * @param notities The notities to set
     */
    public void setNotities(ArrayList<Notitie> notities)
    {
	this.notities = notities;
    }

    /**
     * Gets the check_titel
     * @return Returns a boolean
     */
    public boolean getCheck_titel()
    {
	return check_titel;
    }

    /**
     * Sets the check_titel
     * @param check_titel The check_titel to set
     */
    public void setCheck_titel(boolean check_titel)
    {
	this.check_titel = check_titel;
    }

    /**
     * Gets the check_vraag
     * @return Returns a boolean
     */
    public boolean getCheck_vraag()
    {
	return check_vraag;
    }

    /**
     * Sets the check_vraag
     * @param check_vraag The check_vraag to set
     */
    public void setCheck_vraag(boolean check_vraag)
    {
	this.check_vraag = check_vraag;
    }

    /**
     * Gets the check_antwoord
     * @return Returns a boolean
     */
    public boolean getCheck_antwoord()
    {
	return check_antwoord;
    }

    /**
     * Sets the check_antwoord
     * @param check_antwoord The check_antwoord to set
     */
    public void setCheck_antwoord(boolean check_antwoord)
    {
	this.check_antwoord = check_antwoord;
    }

    /**
     * Gets the check_toelichting
     * @return Returns a boolean
     */
    public boolean getCheck_toelichting()
    {
	return check_toelichting;
    }

    /**
     * Sets the check_toelichting
     * @param check_toelichting The check_toelichting to set
     */
    public void setCheck_toelichting(boolean check_toelichting)
    {
	this.check_toelichting = check_toelichting;
    }

    /**
     * Gets the check_bijzonderheid
     * @return Returns a boolean
     */
    public boolean getCheck_bijzonderheid()
    {
	return check_bijzonderheid;
    }

    /**
     * Sets the check_bijzonderheid
     * @param check_bijzonderheid The check_bijzonderheid to set
     */
    public void setCheck_bijzonderheid(boolean check_bijzonderheid)
    {
	this.check_bijzonderheid = check_bijzonderheid;
    }

    /**
     * Gets the check_categorien
     * @return Returns a boolean
     */
    public boolean getCheck_categorien()
    {
	return check_categorien;
    }

    /**
     * Sets the check_categorien
     * @param check_categorien The check_categorien to set
     */
    public void setCheck_categorien(boolean check_categorien)
    {
	this.check_categorien = check_categorien;
    }

    /**
     * Gets the check_naslagreferenties
     * @return Returns a boolean
     */
    public boolean getCheck_naslagreferenties()
    {
	return check_naslagreferenties;
    }

    /**
     * Sets the check_naslagreferenties
     * @param check_naslagreferenties The check_naslagreferenties to set
     */
    public void setCheck_naslagreferenties(boolean check_naslagreferenties)
    {
	this.check_naslagreferenties = check_naslagreferenties;
    }

    /**
     * Gets the check_bronnen
     * @return Returns a boolean
     */
    public boolean getCheck_bronnen()
    {
	return check_bronnen;
    }

    /**
     * Sets the check_bronnen
     * @param check_bronnen The check_bronnen to set
     */
    public void setCheck_bronnen(boolean check_bronnen)
    {
	this.check_bronnen = check_bronnen;
    }

    /**
     * Gets the check_citaten
     * @return Returns a boolean
     */
    public boolean getCheck_citaten()
    {
	return check_citaten;
    }

    /**
     * Sets the check_citaten
     * @param check_citaten The check_citaten to set
     */
    public void setCheck_citaten(boolean check_citaten)
    {
	this.check_citaten = check_citaten;
    }

    /**
     * Gets the check_frequenties
     * @return Returns a boolean
     */
    public boolean getCheck_frequenties()
    {
	return check_frequenties;
    }

    /**
     * Sets the check_frequenties
     * @param check_frequenties The check_frequenties to set
     */
    public void setCheck_frequenties(boolean check_frequenties)
    {
	this.check_frequenties = check_frequenties;
    }

    /**
     * Gets the check_webreferenties
     * @return Returns a boolean
     */
    public boolean getCheck_webreferenties()
    {
	return check_webreferenties;
    }

    /**
     * Sets the check_webreferenties
     * @param check_webreferenties The check_webreferenties to set
     */
    public void setCheck_webreferenties(boolean check_webreferenties)
    {
	this.check_webreferenties = check_webreferenties;
    }

    /**
     * Gets the check_notities
     * @return Returns a boolean
     */
    public boolean getCheck_notities()
    {
	return check_notities;
    }

    /**
     * Sets the check_notities
     * @param check_notities The check_notities to set
     */
    public void setCheck_notities(boolean check_notities)
    {
	this.check_notities = check_notities;
    }

    /**
     * Gets the check_teksten
     * @return Returns a boolean
     */
    public boolean getCheck_teksten()
    {
	return check_teksten;
    }

    /**
     * Sets the check_teksten
     * @param check_teksten The check_teksten to set
     */
    public void setCheck_teksten(boolean check_teksten)
    {
	this.check_teksten = check_teksten;
    }

    /**
     * Testing purposes only !!!
     * @param args args...
     */
    public static void main(String[] args)
    {
	String titel = "titel";
	String vraag = "vraag";
	String antwoord = "antwoord";
	String bijzonderheid = "bijzonderheid";
	String toelichting = "toelichting";
	Taalvraag taalvraag = new Taalvraag();
	// Taalvraag.updateBijzonderheid(taalvraag);
	for (int i = 800; i < 900; i++)
	{
	    taalvraag.setTitel(titel + i);
	    taalvraag.setTitelHTML("<b>" + taalvraag.getTitel() + "</b>");
	    taalvraag.setVraag(vraag + i);
	    taalvraag.setVraagHTML("<b>" + taalvraag.getVraag() + "</b>");
	    taalvraag.setAntwoord(antwoord + i);
	    taalvraag.setAntwoordHTML("<b>" + taalvraag.getAntwoord() + "</b>");
	    taalvraag.setBijzonderheid(bijzonderheid + i);
	    taalvraag.setBijzonderheidHTML("<b>" + taalvraag.getBijzonderheid() + "</b>");
	    taalvraag.setToelichting(toelichting + i);
	    taalvraag.setToelichtingHTML("<b>" + taalvraag.getToelichting() + "</b>");
	    taalvraag.setInformatie("");
	    taalvraag.setInformatieHTML("");
	    taalvraag.setHerformulering("");
	    taalvraag.setHerformuleringHTML("");
	    taalvraag.setOproepId(5);
	    taalvraag.setAfgehandeld(false);
	    taalvraag.setVolgnummer(i);
	    Taalvraag.insert(taalvraag);
	}
    }
}
