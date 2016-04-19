package be.vlaanderen.sbs.s6.taaladvies.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class Tekst implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 592267274171605783L;
    private int id;
    private String titel;
    private String titelHTML;
    private int oproepId;
    private int relevantieId;
    private Oproep oproep;
    private ParameterActief relevantie;
    private java.util.ArrayList<Tekstblok> tekstblokken;
    private java.util.ArrayList<Categorie> categorien;
    private java.util.ArrayList<Naslagreferentie> naslagreferenties;
    private java.util.ArrayList<Bron> bronnen;
    private java.util.ArrayList<Citaat> citaten;
    private java.util.ArrayList<Frequentie> frequenties;
    private java.util.ArrayList<Webreferentie> webreferenties;
    private java.util.ArrayList<Notitie> notities;
    private boolean check_titel = true;
    private boolean check_categorien;
    private boolean check_naslagreferenties;
    private boolean check_bronnen;
    private boolean check_citaten;
    private boolean check_frequenties;
    private boolean check_webreferenties;
    private boolean check_notities;

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
	this.titelHTML = titelHTML;
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
     * Gets the Tekst from the database by it's id
     * @param id The id to retrieve
     * @return Returns a Tekst
     */
    public static Tekst findByPK(int id)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	Tekst tekst = new Tekst();
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.TEKST_BY_PK);
		pst.setInt(1, id);
		rs = pst.executeQuery();
		if (rs.next())
		{
		    tekst.setId(id);
		    tekst.setTitel(rs.getString("titel"));
		    tekst.setTitelHTML(rs.getString("titelHTML"));
		    tekst.setOproepId(rs.getInt("oproepId"));
		    tekst.setRelevantieId(rs.getInt("relevantieId"));
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
	return tekst;
    }

    /**
     * Gets the Tekst from the database by it's id with all info
     * @param id The id to retrieve
     * @return Returns a Tekst
     */
    public static Tekst findByPKFull(int id)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	Tekst tekst = new Tekst();
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.TEKST_BY_PK);
		pst.setInt(1, id);
		rs = pst.executeQuery();
		if (rs.next())
		{
		    tekst.setId(id);
		    tekst.setTitel(rs.getString("titel"));
		    tekst.setTitelHTML(rs.getString("titelHTML"));
		    tekst.setOproepId(rs.getInt("oproepId"));
		    tekst.setRelevantieId(rs.getInt("relevantieId"));
		    tekst.setBronnen(tekst.getBronnen());
		    tekst.setCategorien(tekst.getCategorien());
		    tekst.setCitaten(tekst.getCitaten());
		    tekst.setFrequenties(tekst.getFrequenties());
		    tekst.setNaslagreferenties(tekst.getNaslagreferenties());
		    tekst.setNotities(tekst.getNotities());
		    tekst.setTekstblokken(tekst.getTekstblokken());
		    tekst.setWebreferenties(tekst.getWebreferenties());
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
	return tekst;
    }

    /**
     * Gets all Teksten from the database linked to a taalvraag
     * @return Returns a ArrayList
     */
    public static ArrayList<Tekst> findAllByTaalvraag(int taalvraagId)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	ArrayList<Tekst> allTeksten = new ArrayList<Tekst>();
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.TEKSTEN_BY_TAALVRAAG);
		pst.setInt(1, taalvraagId);
		rs = pst.executeQuery();
		while (rs.next())
		{
		    Tekst tekst = new Tekst();
		    tekst.setId(rs.getInt("id"));
		    tekst.setTitel(rs.getString("titel"));
		    tekst.setTitelHTML(rs.getString("titelHTML"));
		    tekst.setOproepId(rs.getInt("oproepId"));
		    tekst.setRelevantieId(rs.getInt("relevantieId"));
		    allTeksten.add(tekst);
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
	return allTeksten;
    }

    /**
     * Gets the Tekst from the database by the parent oproepId
     * @param id The id to retrieve
     * @return Returns a Tekst
     */
    public static Tekst findByOproep(int id)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	Tekst tekst = new Tekst();
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.TEKST_BY_PARENT);
		pst.setInt(1, id);
		rs = pst.executeQuery();
		if (rs.next())
		{
		    tekst.setId(rs.getInt("id"));
		    tekst.setTitel(rs.getString("titel"));
		    tekst.setTitelHTML(rs.getString("titelHTML"));
		    tekst.setOproepId(rs.getInt("oproepId"));
		    tekst.setRelevantieId(rs.getInt("relevantieId"));
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
	return tekst;
    }

    /**
     * Inserts a Tekst
     * @param tekst The Tekst to insert
     * @return Return int
     */
    public static int insert(Tekst tekst)
    {
	int id = 0;
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.INSERT_TEKST);
		if (tekst.getTitel() == null)
		{
		    tekst.setTitel("");
		}
		if (tekst.getTitelHTML() == null)
		{
		    tekst.setTitelHTML("");
		}
		// pst.setString(1, tekst.getTitel());
		Util.pstSetClob(pst, 1, tekst.getTitel());
		Util.pstSetClob(pst, 2, tekst.getTitelHTML());
		pst.setInt(3, tekst.getOproepId());
		Util.pstSetInt(pst, 4, tekst.getRelevantieId());
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
     * Updates a Tekst
     * @param tekst The Tekst to change
     * @return Return boolean
     */
    public static boolean update(Tekst tekst)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.UPDATE_TEKST);
		if (tekst.getTitel() == null)
		{
		    tekst.setTitel("");
		}
		// pst.setString(1, tekst.getTitel());
		Util.pstSetClob(pst, 1, tekst.getTitel());
		Util.pstSetClob(pst, 2, tekst.getTitelHTML());
		pst.setInt(3, tekst.getOproepId());
		Util.pstSetInt(pst, 4, tekst.getRelevantieId());
		pst.setInt(5, tekst.getId());
		int check = pst.executeUpdate();
		if (check == 1)
		{
		    return true;
		} else
		{
		    return false;
		}
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
     * Deletes a Tekst
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
		pst = con.prepareStatement(Queries.DELETE_TEKST);
		pst.setInt(1, id);
		int check = pst.executeUpdate();
		if (check == 1)
		{
		    return true;
		} else
		{
		    return false;
		}
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
     * Deletes the Verity Entry for a Tekst
     * @param tekstId The id of the Tekst to delete
     * @return Return boolean
     */
    /*
     * public static boolean deleteVerityEntry(int tekstId) { IDbConnection dbconnection = MyDbConnection.getInstance();
     * Connection con = dbconnection.getConnection(); PreparedStatement pst = null; try { if (con != null) { pst =
     * con.prepareStatement(Queries.DELETE_VERITY_FOR_TEKST); pst.setInt(1, tekstId); int check = pst.executeUpdate(); if (check
     * == 1) { return true; } else { return false; } } else { System.err.println("Geen connectie !"); return false; } } catch
     * (java.sql.SQLException e) { e.printStackTrace(System.err); return false; } finally { try { if (con != null) { if (pst !=
     * null) {pst.close();} dbconnection.releaseConnection(con); } } catch (java.sql.SQLException sqle) {
     * sqle.printStackTrace(System.err); } } }
     */
    /**
     * Gets the tekstblokken
     * @return Returns a java.util.ArrayList
     */
    public java.util.ArrayList<Tekstblok> getTekstblokken()
    {
	if (tekstblokken == null)
	{
	    if (id != 0)
	    {
		tekstblokken = Tekstblok.findByParent(id);
		if (tekstblokken.size() == 0)
		{
		    tekstblokken = null;
		}
	    } else
	    {
		tekstblokken = null;
	    }
	}
	return tekstblokken;
    }

    /**
     * Sets the tekstblokken
     * @param tekstblokken The tekstblokken to set
     */
    public void setTekstblokken(java.util.ArrayList<Tekstblok> tekstblokken)
    {
	this.tekstblokken = tekstblokken;
    }

    /**
     * Gets the categorien
     * @return Returns a java.util.ArrayList
     */
    public java.util.ArrayList<Categorie> getCategorien()
    {
	if (categorien == null)
	{
	    if (id != 0)
	    {
		categorien = Categorie.findByTekstId(id);
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
    public void setCategorien(java.util.ArrayList<Categorie> categorien)
    {
	this.categorien = categorien;
    }

    /**
     * Gets the naslagreferenties
     * @return Returns a java.util.ArrayList
     */
    public java.util.ArrayList<Naslagreferentie> getNaslagreferenties()
    {
	if (naslagreferenties == null)
	{
	    if (id != 0)
	    {
		naslagreferenties = Naslagreferentie.findAllByParent(0, id);
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
    public void setNaslagreferenties(java.util.ArrayList<Naslagreferentie> naslagreferenties)
    {
	this.naslagreferenties = naslagreferenties;
    }

    /**
     * Gets the bronnen
     * @return Returns a java.util.ArrayList
     */
    public java.util.ArrayList<Bron> getBronnen()
    {
	if (bronnen == null)
	{
	    if (id != 0)
	    {
		bronnen = Bron.findAllByParent(0, id);
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
    public void setBronnen(java.util.ArrayList<Bron> bronnen)
    {
	this.bronnen = bronnen;
    }

    /**
     * Gets the citaten
     * @return Returns a java.util.ArrayList
     */
    public java.util.ArrayList<Citaat> getCitaten()
    {
	if (citaten == null)
	{
	    if (id != 0)
	    {
		citaten = Citaat.findAllByParent(0, id);
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
    public void setCitaten(java.util.ArrayList<Citaat> citaten)
    {
	this.citaten = citaten;
    }

    /**
     * Gets the frequenties
     * @return Returns a java.util.ArrayList
     */
    public java.util.ArrayList<Frequentie> getFrequenties()
    {
	if (frequenties == null)
	{
	    if (id != 0)
	    {
		frequenties = Frequentie.findAllByParent(0, id);
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
    public void setFrequenties(java.util.ArrayList<Frequentie> frequenties)
    {
	this.frequenties = frequenties;
    }

    /**
     * Gets the webreferenties
     * @return Returns a java.util.ArrayList
     */
    public java.util.ArrayList<Webreferentie> getWebreferenties()
    {
	if (webreferenties == null)
	{
	    if (id != 0)
	    {
		webreferenties = Webreferentie.findAllByParent(0, id);
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
     * Sets the webreferenties
     * @param webreferenties The webreferenties to set
     */
    public void setWebreferenties(java.util.ArrayList<Webreferentie> webreferenties)
    {
	this.webreferenties = webreferenties;
    }

    /**
     * Gets the notities
     * @return Returns a java.util.ArrayList
     */
    public java.util.ArrayList<Notitie> getNotities()
    {
	if (notities == null)
	{
	    if (id != 0)
	    {
		notities = Notitie.findAllByParent(0, id);
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
    public void setNotities(java.util.ArrayList<Notitie> notities)
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

    private static String buildBtsQuery(SearchForm criteria)
    {
	boolean queryStarted = false;
	// Nakijken welke velden er moeten doorzocht worden
	StringBuffer query = new StringBuffer("");
	StringBuffer velden = new StringBuffer("");
	if (criteria.getTekstTitel())
	{
	    queryStarted = true;
	    velden.append("'hfd_ttl_ds'");
	}
	if (criteria.getTekstSubtitels())
	{
	    if (queryStarted)
	    {
		velden.append(", ");
	    } else
	    {
		queryStarted = true;
	    }
	    velden.append("'tsb_ttl_ds'");
	}
	if (criteria.getTekstVelden())
	{
	    if (queryStarted)
	    {
		velden.append(", ");
	    } else
	    {
		queryStarted = true;
	    }
	    velden.append("'tsb_db'");
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
		    query.append(" AND B.tks_id IN(SELECT DISTINCT tks_id FROM bts_tks WHERE veld in (");
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
		query.append(" B.tks_id IN(SELECT DISTINCT tks_id FROM bts_tks WHERE veld in (");
		query.append(velden);
		query.append(") and bts_contains(waarde, '\"");
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
	    if (/*criteria.getEigenTV() || */criteria.getEigenTXT())
	    {
		query.append(" AND A.TAV_ID = ");
		query.append(criteria.getUserId());
	    } else if (/*criteria.getAlleTV() || */criteria.getAlleTXT())
	    {
		query.append(" AND A.TAV_ID != ");
		query.append(criteria.getUserId());
	    }
	}
	if (!criteria.isCategorie() && (criteria.getAndTerms() != null || criteria.getPhrase() != null))
	{
	    query.append(Tekst.buildBtsQuery(criteria));
	}
	criteria.setVariablePartTXT(query.toString());
    }

    public static String[] buildFixedQueries(SearchForm criteria)
    {
	String[] queries = new String[3];
	// BackwardQuery
	StringBuffer backwardQuery = new StringBuffer();
	backwardQuery.append("SELECT FIRST ");
	if (criteria.getEigenTV() || criteria.getAlleTV())
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
	    // backwardQuery.append(Queries.TEKSTEN_MULTICATEGORIESEARCH_FIXED_PREVIOUS);
	    // backwardQuery.append(" AND D.CTR_NMR_TX LIKE \"" + criteria.getCategorie1().getNummer().trim() + "%\" AND
	    // F.CTR_NMR_TX LIKE \"" + criteria.getCategorie2().getNummer().trim() + "%\"");
	    // }
	    // else
	    // {
	    // backwardQuery.append(Queries.TEKSTEN_CATEGORIESEARCH_FIXED_PREVIOUS);
	    // backwardQuery.append(" AND D.CTR_NMR_TX LIKE \"" + criteria.getCategorie1().getNummer().trim() + "%\"");
	    // }
	    String sqlSelect = Queries.TEKSTEN_MULTICATEGORIESEARCH_FIXED_PREVIOUS_SELECT;
	    String sqlFrom = Queries.TEKSTEN_MULTICATEGORIESEARCH_FIXED_PREVIOUS_FROM;
	    String sqlWhere = Queries.TEKSTEN_MULTICATEGORIESEARCH_FIXED_PREVIOUS_WHERE;
	    int counter = 0;
	    for (Iterator<Categorie> iter = criteria.getCategorieList().iterator(); iter.hasNext();)
	    {
		counter++;
		Categorie categorie = iter.next();
		if (categorie != null)
		{
		    String catRelName = "TR_TKS_CTR" + counter;
		    String catParName = "TR_CTR" + counter;
		    sqlFrom += ", TR_TKS_CTR " + catRelName + ", TR_CTR " + catParName;
		    sqlWhere +=
			" AND B.TKS_ID = " + catRelName + ".TKS_ID " + " AND " + catRelName + ".CTR_ID = " + catParName
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
	    backwardQuery.append(sqlSelect + sqlFrom + sqlWhere);
	} else
	{
	    if (criteria.getTekstSubtitels() || criteria.getTekstVelden())
	    {
		backwardQuery.append(Queries.TEKSTENTB_SEARCH_FIXED_PREVIOUS);
	    } else
	    {
		backwardQuery.append(Queries.TEKSTEN_SEARCH_FIXED_PREVIOUS);
	    }
	}
	backwardQuery.append(criteria.getVariablePartTXT());
	backwardQuery.append(" ORDER BY ORP_DT, TKS_ID DESC");
	queries[0] = backwardQuery.toString();
	// ForwardQuery
	StringBuffer forwardQuery = new StringBuffer();
	forwardQuery.append("SELECT FIRST ");
	if (criteria.getEigenTV() || criteria.getAlleTV())
	{
	    forwardQuery.append(SearchForm.DISPLAY_AMOUNT);
	} else
	{
	    forwardQuery.append(SearchForm.DISPLAY_AMOUNT * 2);
	}
	if (criteria.isCategorie())
	{
	    String sqlSelect = Queries.TEKSTEN_MULTICATEGORIESEARCH_FIXED_NEXT_SELECT;
	    String sqlFrom = Queries.TEKSTEN_MULTICATEGORIESEARCH_FIXED_NEXT_FROM;
	    String sqlWhere = Queries.TEKSTEN_MULTICATEGORIESEARCH_FIXED_NEXT_WHERE;
	    int counter = 0;
	    for (Iterator<Categorie> iter = criteria.getCategorieList().iterator(); iter.hasNext();)
	    {
		counter++;
		Categorie categorie = iter.next();
		if (categorie != null)
		{
		    String catRelName = "TR_TKS_CTR" + counter;
		    String catParName = "TR_CTR" + counter;
		    sqlFrom += ", TR_TKS_CTR " + catRelName + ", TR_CTR " + catParName;
		    sqlWhere +=
			" AND B.TKS_ID = " + catRelName + ".TKS_ID " + " AND " + catRelName + ".CTR_ID = " + catParName
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
	    forwardQuery.append(sqlSelect + sqlFrom + sqlWhere);
	    // if(criteria.getCategorie2() != null)
	    // {
	    // forwardQuery.append(Queries.TEKSTEN_MULTICATEGORIESEARCH_FIXED_NEXT);
	    // forwardQuery.append(" AND D.CTR_NMR_TX LIKE \"" + criteria.getCategorie1().getNummer().trim() + "%\" AND
	    // F.CTR_NMR_TX LIKE \"" + criteria.getCategorie2().getNummer().trim() + "%\"");
	    // }
	    // else
	    // {
	    // forwardQuery.append(Queries.TEKSTEN_CATEGORIESEARCH_FIXED_NEXT);
	    // forwardQuery.append(" AND D.CTR_NMR_TX LIKE \"" + criteria.getCategorie1().getNummer().trim() + "%\"");
	    // }
	} else
	{
	    if (criteria.getTekstSubtitels() || criteria.getTekstVelden())
	    {
		forwardQuery.append(Queries.TEKSTENTB_SEARCH_FIXED_NEXT);
	    } else
	    {
		forwardQuery.append(Queries.TEKSTEN_SEARCH_FIXED_NEXT);
	    }
	}
	queries[1] = forwardQuery.toString() + criteria.getVariablePartTXT() + " ORDER BY ORP_DT DESC, TKS_ID";
	forwardQuery.append(" AND (A.orp_dt < ? or (A.orp_dt = ? and B.tks_id > ?))");
	forwardQuery.append(criteria.getVariablePartTXT());
	forwardQuery.append(" ORDER BY A.ORP_DT DESC, B.TKS_ID");
	queries[2] = forwardQuery.toString();
	return queries;
    }

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
		String query = null;
		if (criteria.isCategorie())
		{
		    // if(criteria.getCategorie2() != null)
		    // query = Queries.TEKSTEN_MULTICATEGORIESEARCH_FIXED_COUNT + criteria.getVariablePartTXT();
		    // else
		    // query = Queries.TEKSTEN_CATEGORIESEARCH_FIXED_COUNT + criteria.getVariablePartTXT();
		    //
		    String sqlSelect = Queries.TEKSTEN_MULTICATEGORIESEARCH_FIXED_COUNT_SELECT;
		    String sqlFrom = Queries.TEKSTEN_MULTICATEGORIESEARCH_FIXED_COUNT_FROM;
		    String sqlWhere = Queries.TEKSTEN_MULTICATEGORIESEARCH_FIXED_COUNT_WHERE;
		    int counter = 0;
		    for (Iterator<Categorie> iter = criteria.getCategorieList().iterator(); iter.hasNext();)
		    {
			counter++;
			Categorie categorie = iter.next();
			if (categorie != null && categorie.getId() != 0)
			{
			    String catRelName = "TR_TKS_CTR" + counter;
			    String catParName = "TR_CTR" + counter;
			    sqlFrom += ", TR_TKS_CTR " + catRelName + ", TR_CTR " + catParName;
			    sqlWhere +=
				" AND B.TKS_ID = " + catRelName + ".TKS_ID " + " AND " + catRelName + ".CTR_ID = "
				    + catParName + ".CTR_ID " + " AND (" + catParName + ".CTR_NMR_TX LIKE ? ";
			    // if (categorie.getNummer().indexOf(".") == -1)
			    // {
			    sqlWhere += " OR " + catParName + ".CTR_NMR_TX LIKE ? ";
			    // }
			    sqlWhere += ")";
			}
		    }
		    query = sqlSelect + sqlFrom + sqlWhere + criteria.getVariablePartTXT();
		} else
		{
		    /* MAX & COUNT QUERY */
		    if (criteria.getTekstSubtitels() || criteria.getTekstVelden())
		    {
			query = Queries.TEKSTENTB_SEARCH_FIXED_COUNT + criteria.getVariablePartTXT();
		    } else
		    {
			query = Queries.TEKSTEN_SEARCH_FIXED_COUNT + criteria.getVariablePartTXT();
		    }
		}
		System.err.println("count-query: " + query);
		AppLogger.getInstance().debug(query);
		pst = con.prepareStatement(query);
		int index = 1;
		if (criteria.isCategorie())
		{
		    // pst.setString(index++, criteria.getCategorie1().getNummer().trim() + "%");
		    //
		    // if(criteria.getCategorie2() != null)
		    // pst.setString(index++, criteria.getCategorie2().getNummer().trim() + "%");
		    for (Iterator<Categorie> iter = criteria.getCategorieList().iterator(); iter.hasNext();)
		    {
			Categorie categorie = iter.next();
			if (categorie != null && categorie.getId() != 0)
			{
			    index = Taalvraag.generateWildcard(index, pst, categorie);
			}
		    }
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

    public static Tekst[] findBySearchCriteria(SearchCache cache)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	Tekst[] allTeksten = null;
	if (cache.getCurrentCount() == cache.getPageCount())
	{
	    int index = cache.getMaxCount() % cache.getDisplayAmount();
	    if (index == 0)
	    {
		allTeksten = new Tekst[cache.getDisplayAmount()];
	    } else
	    {
		allTeksten = new Tekst[index];
	    }
	} else
	{
	    allTeksten = new Tekst[cache.getDisplayAmount()];
	}
	int index = 1;
	int arrayIndex = 0;
	try
	{
	    if (con != null)
	    {
		if (cache.getInterval() == 2) // Previous
		{
		    arrayIndex = allTeksten.length - 1;
		    AppLogger.getInstance().debug(cache.getBackwardQuery());
		    pst = con.prepareStatement(cache.getBackwardQuery());
		    pst.setInt(index++, cache.getIdLimit());
		    DbUtil.pstSetDate(pst, index++, cache.getCurrentMinDate());
		    DbUtil.pstSetDate(pst, index++, cache.getCurrentMinDate());
		    pst.setInt(index++, cache.getCurrentMinId());
		} else
		{
		    if (cache.getInterval() == 1)
		    {
			AppLogger.getInstance().debug(cache.getForwardQueryNext());
			pst = con.prepareStatement(cache.getForwardQueryNext());
		    } else
		    {
			AppLogger.getInstance().debug(cache.getForwardQuery());
			pst = con.prepareStatement(cache.getForwardQuery());
		    }
		    pst.setInt(index++, cache.getIdLimit());
		    if (cache.getInterval() == 1)
		    {
			DbUtil.pstSetDate(pst, index++, cache.getCurrentMaxDate());
			DbUtil.pstSetDate(pst, index++, cache.getCurrentMaxDate());
			pst.setInt(index++, cache.getCurrentMaxId());
		    }
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
		// DEBUG !!!
		int recordAmount = 0;
		while (rs.next())
		{
		    // DEBUG !!!
		    recordAmount++;
		    Tekst tekst = new Tekst();
		    tekst.setId(rs.getInt("id"));
		    tekst.setTitel(rs.getString("titel"));
		    tekst.setOproepId(rs.getInt("oproepId"));
		    if (cache.getInterval() == 2)
		    {
			allTeksten[arrayIndex--] = tekst;
		    } else
		    {
			allTeksten[arrayIndex++] = tekst;
		    }
		    long newEnd = System.currentTimeMillis();
		    end = newEnd;
		}
		// DEBUG !!!
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
	return allTeksten;
    }
}
