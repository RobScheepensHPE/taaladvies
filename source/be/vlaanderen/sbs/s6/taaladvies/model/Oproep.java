package be.vlaanderen.sbs.s6.taaladvies.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import be.vlaanderen.sbs.s6.taaladvies.Queries;
import be.vlaanderen.sbs.s6.taaladvies.db.IDbConnection;
import be.vlaanderen.sbs.s6.taaladvies.db.MyDbConnection;
import be.vlaanderen.sbs.s6.taaladvies.utils.Util;
import com.informix.jdbc.IfxStatement;

public class Oproep implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -4830687767169087964L;
    private int id;
    private int type;
    // this will be the hardcoded type to discern a oproep whether its for taalvragen or for a tekst
    // an oproep for taalvragen will be 1, an oproep for a tekst will be 2
    private Date deadline;
    private String deadlineString;
    private String deadline_d;
    private String deadline_m;
    private String deadline_y;
    private Date oproepdatum = new Date(new java.util.Date().getTime());
    private String oproepdatumString;
    private String oproepdatum_d;
    private String oproepdatum_m;
    private String oproepdatum_y;
    private String naam;
    private String voornaam;
    private String functie;
    private String straat;
    private String huisnummer;
    private String busnummer;
    private String telefoon;
    private String fax;
    private String email;
    private String geslacht;
    private String organisatie;
    private String opmerking;
    private String herkomstnummer;
    private boolean afgehandeld;
    private boolean slot;
    private boolean ofloadtaalunie;
    private int domeinId;
    private int doelgroepId;
    private int herkomstId;
    private int statusId;
    // private int relevantieId;
    private int mediumId = 1; // op 1 geïnitialiseerd omdat "telefoon" standaard moet staan... zijnde dus id 1 uit medium
    private int distributieId;
    private int gemeenteId;
    private int gebruikerId;
    private ParameterBase domein;
    private Doelgroep doelgroep;
    private ParameterActief herkomst;
    private ParameterActief status;
    // private ParameterActief relevantie;
    private ParameterActief medium;
    private Distributie distributie;
    private Gemeente gemeente;
    private Gebruiker gebruiker;
    private Tekst tekst;
    private Taalvraag eersteVraag;

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
     * Gets the type
     * @return Returns a int
     */
    public int getType()
    {
	return type;
    }

    /**
     * Sets the type
     * @param type The type to set
     */
    public void setType(int type)
    {
	this.type = type;
    }

    /**
     * Gets the deadline
     * @return Returns a Date
     */
    public Date getDeadline()
    {
	if (deadline_d != null && !deadline_d.equals(""))
	{
	    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	    ParsePosition pos1 = new ParsePosition(0);
	    StringBuffer deadlineStrBuf = new StringBuffer();
	    deadlineStrBuf.append(deadline_d);
	    deadlineStrBuf.append("-");
	    deadlineStrBuf.append(deadline_m);
	    deadlineStrBuf.append("-");
	    deadlineStrBuf.append(deadline_y);
	    java.util.Date deadlineUtilDate = df.parse(deadlineStrBuf.toString(), pos1);
	    Date deadlineSqlDate = new java.sql.Date(deadlineUtilDate.getTime());
	    deadline = deadlineSqlDate;
	    return deadlineSqlDate;
	} else
	{
	    return deadline;
	}
    }

    public String getDeadlineString()
    {
	if (deadline != null)
	{
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	    String dateString = formatter.format(deadline);
	    return dateString;
	} else
	{
	    return deadlineString;
	}
    }

    /**
     * Sets the deadline
     * @param deadline The deadline to set
     */
    public void setDeadline(Date deadline)
    {
	this.deadline = deadline;
    }

    public void setDeadlineString(String deadlineString)
    {
	this.deadlineString = deadlineString;
    }

    /**
     * Gets the oproepdatum
     * @return Returns a Date
     */
    public Date getOproepdatum()
    {
	if (oproepdatum_d != null && !oproepdatum_d.equals(""))
	{
	    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	    ParsePosition pos1 = new ParsePosition(0);
	    StringBuffer oproepdatumStrBuf = new StringBuffer();
	    oproepdatumStrBuf.append(oproepdatum_d);
	    oproepdatumStrBuf.append("-");
	    oproepdatumStrBuf.append(oproepdatum_m);
	    oproepdatumStrBuf.append("-");
	    oproepdatumStrBuf.append(oproepdatum_y);
	    java.util.Date oproepdatumUtilDate = df.parse(oproepdatumStrBuf.toString(), pos1);
	    Date oproepdatumSqlDate = new java.sql.Date(oproepdatumUtilDate.getTime());
	    oproepdatum = oproepdatumSqlDate;
	    return oproepdatumSqlDate;
	} else
	{
	    return oproepdatum;
	}
    }

    public String getOproepdatumString()
    {
	if (oproepdatum != null)
	{
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	    String dateString = formatter.format(oproepdatum);
	    return dateString;
	} else
	{
	    return oproepdatumString;
	}
    }

    /**
     * Sets the oproepdatum
     * @param oproepdatum The oproepdatum to set
     */
    public void setOproepdatum(Date oproepdatum)
    {
	this.oproepdatum = oproepdatum;
    }

    public void setOproepdatumString(String oproepdatumString)
    {
	this.oproepdatumString = oproepdatumString;
    }

    /**
     * Gets the naam
     * @return Returns a String
     */
    public String getNaam()
    {
	if (naam != null)
	{
	    naam.trim();
	}
	return naam;
    }

    /**
     * Sets the naam
     * @param naam The naam to set
     */
    public void setNaam(String naam)
    {
	this.naam = naam;
    }

    /**
     * Gets the voornaam
     * @return Returns a String
     */
    public String getVoornaam()
    {
	if (voornaam != null)
	{
	    voornaam.trim();
	}
	return voornaam;
    }

    /**
     * Sets the voornaam
     * @param voornaam The voornaam to set
     */
    public void setVoornaam(String voornaam)
    {
	this.voornaam = voornaam;
    }

    /**
     * Gets the functie
     * @return Returns a String
     */
    public String getFunctie()
    {
	if (functie != null)
	{
	    functie.trim();
	}
	return functie;
    }

    /**
     * Sets the functie
     * @param functie The functie to set
     */
    public void setFunctie(String functie)
    {
	this.functie = functie;
    }

    /**
     * Gets the straat
     * @return Returns a String
     */
    public String getStraat()
    {
	if (straat != null)
	{
	    straat.trim();
	}
	return straat;
    }

    /**
     * Sets the straat
     * @param straat The straat to set
     */
    public void setStraat(String straat)
    {
	this.straat = straat;
    }

    /**
     * Gets the huisnummer
     * @return Returns a String
     */
    public String getHuisnummer()
    {
	if (huisnummer != null)
	{
	    huisnummer.trim();
	}
	return huisnummer;
    }

    /**
     * Sets the huisnummer
     * @param huisnummer The huisnummer to set
     */
    public void setHuisnummer(String huisnummer)
    {
	this.huisnummer = huisnummer;
    }

    /**
     * Gets the busnummer
     * @return Returns a String
     */
    public String getBusnummer()
    {
	if (busnummer != null)
	{
	    busnummer.trim();
	}
	return busnummer;
    }

    /**
     * Sets the busnummer
     * @param busnummer The busnummer to set
     */
    public void setBusnummer(String busnummer)
    {
	this.busnummer = busnummer;
    }

    /**
     * Gets the telefoon
     * @return Returns a String
     */
    public String getTelefoon()
    {
	if (telefoon != null)
	{
	    telefoon.trim();
	}
	return telefoon;
    }

    /**
     * Sets the telefoon
     * @param telefoon The telefoon to set
     */
    public void setTelefoon(String telefoon)
    {
	this.telefoon = telefoon;
    }

    /**
     * Gets the fax
     * @return Returns a String
     */
    public String getFax()
    {
	if (fax != null)
	{
	    fax.trim();
	}
	return fax;
    }

    /**
     * Sets the fax
     * @param fax The fax to set
     */
    public void setFax(String fax)
    {
	this.fax = fax;
    }

    /**
     * Gets the email
     * @return Returns a String
     */
    public String getEmail()
    {
	if (email != null)
	{
	    email.trim();
	}
	return email;
    }

    /**
     * Sets the email
     * @param email The email to set
     */
    public void setEmail(String email)
    {
	this.email = email;
    }

    /**
     * Gets the geslacht
     * @return Returns a String
     */
    public String getGeslacht()
    {
	if (geslacht != null)
	{
	    geslacht.trim();
	}
	return geslacht;
    }

    /**
     * Sets the geslacht
     * @param geslacht The geslacht to set
     */
    public void setGeslacht(String geslacht)
    {
	this.geslacht = geslacht;
    }

    /**
     * Gets the organisatie
     * @return Returns a String
     */
    public String getOrganisatie()
    {
	if (organisatie != null)
	{
	    organisatie.trim();
	}
	return organisatie;
    }

    /**
     * Sets the organisatie
     * @param organisatie The organisatie to set
     */
    public void setOrganisatie(String organisatie)
    {
	this.organisatie = organisatie;
    }

    /**
     * Gets the opmerking
     * @return Returns a String
     */
    public String getOpmerking()
    {
	if (opmerking != null)
	{
	    opmerking.trim();
	}
	return opmerking;
    }

    /**
     * Sets the opmerking
     * @param opmerking The opmerking to set
     */
    public void setOpmerking(String opmerking)
    {
	this.opmerking = opmerking;
    }

    /**
     * Gets the herkomstnummer
     * @return Returns a String
     */
    public String getHerkomstnummer()
    {
	if (herkomstnummer != null)
	{
	    herkomstnummer.trim();
	}
	return herkomstnummer;
    }

    /**
     * Sets the herkomstnummer
     * @param herkomstnummer The herkomstnummer to set
     */
    public void setHerkomstnummer(String herkomstnummer)
    {
	this.herkomstnummer = herkomstnummer;
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
     * Gets the slot
     * @return Returns a boolean
     */
    public boolean getSlot()
    {
	return slot;
    }

    /**
     * Sets the slot
     * @param slot The slot to set
     */
    public void setSlot(boolean slot)
    {
	this.slot = slot;
    }

    /**
     * Gets the domeinId
     * @return Returns a int
     */
    public int getDomeinId()
    {
	return domeinId;
    }

    /**
     * Sets the domeinId
     * @param domeinId The domeinId to set
     */
    public void setDomeinId(int domeinId)
    {
	this.domeinId = domeinId;
    }

    /**
     * Gets the doelgroepId
     * @return Returns a int
     */
    public int getDoelgroepId()
    {
	return doelgroepId;
    }

    /**
     * Sets the doelgroepId
     * @param doelgroepId The doelgroepId to set
     */
    public void setDoelgroepId(int doelgroepId)
    {
	this.doelgroepId = doelgroepId;
    }

    /**
     * Gets the herkomstId
     * @return Returns a int
     */
    public int getHerkomstId()
    {
	return herkomstId;
    }

    /**
     * Sets the herkomstId
     * @param herkomstId The herkomstId to set
     */
    public void setHerkomstId(int herkomstId)
    {
	this.herkomstId = herkomstId;
    }

    /**
     * Gets the statusId
     * @return Returns a int
     */
    public int getStatusId()
    {
	return statusId;
    }

    /**
     * Sets the statusId
     * @param statusId The statusId to set
     */
    public void setStatusId(int statusId)
    {
	this.statusId = statusId;
    }

    /**
     * Gets the mediumId
     * @return Returns a int
     */
    public int getMediumId()
    {
	return mediumId;
    }

    /**
     * Sets the mediumId
     * @param mediumId The mediumId to set
     */
    public void setMediumId(int mediumId)
    {
	this.mediumId = mediumId;
    }

    /**
     * Gets the distributieId
     * @return Returns a int
     */
    public int getDistributieId()
    {
	return distributieId;
    }

    /**
     * Sets the distributieId
     * @param distributieId The distributieId to set
     */
    public void setDistributieId(int distributieId)
    {
	this.distributieId = distributieId;
    }

    /**
     * Gets the gemeenteId
     * @return Returns a int
     */
    public int getGemeenteId()
    {
	return gemeenteId;
    }

    /**
     * Sets the gemeenteId
     * @param gemeenteId The gemeenteId to set
     */
    public void setGemeenteId(int gemeenteId)
    {
	this.gemeenteId = gemeenteId;
    }

    /**
     * Gets the gebruikerId
     * @return Returns a int
     */
    public int getGebruikerId()
    {
	return gebruikerId;
    }

    /**
     * Sets the gebruikerId
     * @param gebruikerId The gebruikerId to set
     */
    public void setGebruikerId(int gebruikerId)
    {
	this.gebruikerId = gebruikerId;
    }

    /**
     * Gets the domein
     * @return Returns a Domein
     */
    public ParameterBase getDomein()
    {
	if (domein == null)
	{
	    if (domeinId != 0)
	    {
		domein = (ParameterBase) ParameterBase.findByPK(Queries.DOMEIN_BY_PK, domeinId);
	    } else
	    {
		domein = new ParameterBase();
	    }
	}
	return domein;
    }

    /**
     * Sets the domein
     * @param domein The domein to set
     */
    public void setDomein(ParameterBase domein)
    {
	this.domein = domein;
    }

    /**
     * Gets the doelgroep
     * @return Returns a Doelgroep
     */
    public Doelgroep getDoelgroep()
    {
	if (doelgroep == null)
	{
	    if (doelgroepId != 0)
	    {
		doelgroep = (Doelgroep) Doelgroep.findByPK(doelgroepId);
	    } else
	    {
		doelgroep = new Doelgroep();
	    }
	}
	return doelgroep;
    }

    /**
     * Sets the doelgroep
     * @param doelgroep The doelgroep to set
     */
    public void setDoelgroep(Doelgroep doelgroep)
    {
	this.doelgroep = doelgroep;
    }

    /**
     * Gets the herkomst
     * @return Returns a ParameterActief
     */
    public ParameterActief getHerkomst()
    {
	if (herkomst == null)
	{
	    if (herkomstId != 0)
	    {
		herkomst = (ParameterActief) ParameterActief.findByPK(Queries.HERKOMST_BY_PK, herkomstId);
	    } else
	    {
		herkomst = new ParameterActief();
	    }
	}
	return herkomst;
    }

    /**
     * Sets the herkomst
     * @param herkomst The herkomst to set
     */
    public void setHerkomst(ParameterActief herkomst)
    {
	this.herkomst = herkomst;
    }

    /**
     * Gets the status
     * @return Returns a ParameterActief
     */
    public ParameterActief getStatus()
    {
	if (status == null)
	{
	    if (statusId != 0)
	    {
		status = (ParameterActief) ParameterActief.findByPK(Queries.STATUS_BY_PK, statusId);
	    } else
	    {
		status = new ParameterActief();
	    }
	}
	return status;
    }

    /**
     * Sets the status
     * @param status The status to set
     */
    public void setStatus(ParameterActief status)
    {
	this.status = status;
    }

    /**
     * Gets the medium
     * @return Returns a ParameterActief
     */
    public ParameterActief getMedium()
    {
	if (medium == null)
	{
	    if (mediumId != 0)
	    {
		medium = (ParameterActief) ParameterActief.findByPK(Queries.MEDIUM_BY_PK, mediumId);
	    } else
	    {
		medium = new ParameterActief();
	    }
	}
	return medium;
    }

    /**
     * Sets the medium
     * @param medium The medium to set
     */
    public void setMedium(ParameterActief medium)
    {
	this.medium = medium;
    }

    /**
     * Gets the distributie
     * @return Returns a Distributie
     */
    public Distributie getDistributie()
    {
	if (distributie == null)
	{
	    if (distributieId != 0)
	    {
		distributie = Distributie.findByPK(distributieId);
	    } else
	    {
		distributie = new Distributie();
	    }
	}
	return distributie;
    }

    public Distributie getDistributieAsIs()
    {
	return distributie;
    }

    /**
     * Sets the distributie
     * @param distributie The distributie to set
     */
    public void setDistributie(Distributie distributie)
    {
	this.distributie = distributie;
    }

    /**
     * Gets the gemeente
     * @return Returns a Gemeente
     */
    public Gemeente getGemeente()
    {
	if (gemeente == null)
	{
	    if (gemeenteId != 0)
	    {
		gemeente = (Gemeente) Gemeente.findByPK(gemeenteId);
	    } else
	    {
		gemeente = new Gemeente();
	    }
	}
	return gemeente;
    }

    /**
     * Sets the gemeente
     * @param gemeente The gemeente to set
     */
    public void setGemeente(Gemeente gemeente)
    {
	this.gemeente = gemeente;
    }

    /**
     * Gets the gebruiker
     * @return Returns a Gebruiker
     */
    public Gebruiker getGebruiker()
    {
	if (gebruiker == null)
	{
	    if (gebruikerId != 0)
	    {
		gebruiker = Gebruiker.findByPK(gebruikerId);
	    } else
	    {
		gebruiker = new Gebruiker();
	    }
	}
	return gebruiker;
    }

    /**
     * Sets the gebruiker
     * @param gebruiker The gebruiker to set
     */
    public void setGebruiker(Gebruiker gebruiker)
    {
	this.gebruiker = gebruiker;
    }

    /**
     * Gets the Tekst linked to this Oproep
     * @return Returns a Tekst
     */
    public Tekst getTekst()
    {
	if (tekst == null)
	{
	    if (id != 0)
	    {
		tekst = Tekst.findByOproep(id);
	    } else
	    {
		tekst = new Tekst();
	    }
	}
	return tekst;
    }

    /**
     * Sets the tekst
     * @param tekst The tekst to set
     */
    public void setTekst(Tekst tekst)
    {
	this.tekst = tekst;
    }

    public static java.util.ArrayList<Oproep> findByAdminSearch(Oproep oproep, String startdatum, String einddatum,
	String datum, int useGebruiker)
    {
	if (startdatum == null || startdatum.equals("--"))
	{
	    startdatum = "";
	}
	if (einddatum == null || einddatum.equals("--"))
	{
	    einddatum = "";
	}
	if (datum == null || datum.equals("--"))
	{
	    datum = "";
	}
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	ArrayList<Oproep> allOproepen = new ArrayList<Oproep>();
	StringBuffer sql = new StringBuffer();
	SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	ParsePosition pos1 = new ParsePosition(0);
	ParsePosition pos2 = new ParsePosition(0);
	ParsePosition pos3 = new ParsePosition(0);
	try
	{
	    if (con != null)
	    {
		sql.append("SELECT			a.orp_id AS id, a.adv_vzk_typ_cd AS type, a.tav_id AS gebruikerId, ");
		sql.append("				a.orp_sts_cd AS statusId, ");
		sql.append("				a.dln_dt AS deadline, a.orp_dt AS oproepdatum, ");
		sql.append("				a.orr_nm AS naam, a.orr_vrn_nm AS voornaam ");
		sql.append("FROM 			td_opr a, tr_drb c ");
		if (oproep.getGemeente().getId() != 0 || oproep.getGemeenteId() != 0
		    || oproep.getGemeente().getParentId() != 0)
		{
		    sql.append("				, tp_gmn b ");
		}
		sql.append("WHERE				a.drb_id = c.drb_id ");
		if (oproep.getGemeente().getId() != 0 || oproep.getGemeenteId() != 0
		    || oproep.getGemeente().getParentId() != 0)
		{
		    sql.append("AND			a.gmn_id = b.gmn_id ");
		}
		if (!oproep.getNaam().equals(""))
		{
		    sql.append("AND			UPPER(a.orr_nm) LIKE UPPER(?) ");
		}
		if (!oproep.getVoornaam().equals(""))
		{
		    sql.append("AND				UPPER(a.orr_vrn_nm) LIKE UPPER(?) ");
		}
		if (!oproep.getOrganisatie().equals(""))
		{
		    sql.append("AND				UPPER(a.utl_org_nm) LIKE UPPER(?) ");
		}
		if (!oproep.getStraat().equals(""))
		{
		    sql.append("AND				UPPER(a.orr_ads_tx) LIKE UPPER(?) ");
		}
		if (!oproep.getGemeente().getPost().equals(""))
		{
		    sql.append("AND				b.pst_cd = ? ");
		}
		if (oproep.getGemeenteId() != 0)
		{
		    sql.append("AND				a.gmn_id = ? ");
		}
		if (oproep.getGemeente().getParentId() != 0)
		{
		    sql.append("AND				b.lnd_cd = ? ");
		}
		if (oproep.getMediumId() != 0)
		{
		    sql.append("AND				a.mdm_id = ? ");
		}
		if (oproep.getDistributie().getMediumId() != 0)
		{
		    sql.append("AND				c.mdm_id = ? ");
		}
		if (oproep.getHerkomstId() != 0)
		{
		    sql.append("AND				a.hkm_id = ? ");
		}
		if (!oproep.getTelefoon().equals(""))
		{
		    sql.append("AND				a.orr_tel_nr LIKE ? ");
		}
		if (!oproep.getFax().equals(""))
		{
		    sql.append("AND				a.orr_fax_nr LIKE ? ");
		}
		if (!datum.equals(""))
		{
		    sql.append("AND				a.orp_dt = ? ");
		} else
		{
		    if (!startdatum.equals(""))
		    {
			sql.append("AND				a.orp_dt >= ? ");
		    }
		    if (!einddatum.equals(""))
		    {
			sql.append("AND				a.orp_dt <= ? ");
		    }
		}
		if (useGebruiker == 2)
		{
		    sql.append("AND				a.tav_id = ? ");
		} else if (useGebruiker == 3)
		{
		    sql.append("AND				a.tav_id != ? ");
		}
		sql.append("AND				a.adv_vzk_typ_cd = ? ");
		sql.append("AND				a.agh_fg = 't' ");
		pst = con.prepareStatement(sql.toString());
		int index = 1;
		if (!oproep.getNaam().equals(""))
		{
		    pst.setString(index, "%" + oproep.getNaam() + "%");
		    index++;
		}
		if (!oproep.getVoornaam().equals(""))
		{
		    pst.setString(index, "%" + oproep.getVoornaam() + "%");
		    index++;
		}
		if (!oproep.getOrganisatie().equals(""))
		{
		    pst.setString(index, "%" + oproep.getOrganisatie() + "%");
		    index++;
		}
		if (!oproep.getStraat().equals(""))
		{
		    pst.setString(index, "%" + oproep.getStraat() + "%");
		    index++;
		}
		if (!oproep.getGemeente().getPost().equals(""))
		{
		    pst.setString(index, oproep.getGemeente().getPost());
		    index++;
		}
		if (oproep.getGemeenteId() != 0)
		{
		    pst.setInt(index, oproep.getGemeenteId());
		    index++;
		}
		if (oproep.getGemeente().getParentId() != 0)
		{
		    pst.setInt(index, oproep.getGemeente().getParentId());
		    index++;
		}
		if (oproep.getMediumId() != 0)
		{
		    pst.setInt(index, oproep.getMediumId());
		    index++;
		}
		if (oproep.getDistributie().getMediumId() != 0)
		{
		    pst.setInt(index, oproep.getDistributie().getMediumId());
		    index++;
		}
		if (oproep.getHerkomstId() != 0)
		{
		    pst.setInt(index, oproep.getHerkomstId());
		    index++;
		}
		if (!oproep.getTelefoon().equals(""))
		{
		    pst.setString(index, "%" + oproep.getTelefoon() + "%");
		    index++;
		}
		if (!oproep.getFax().equals(""))
		{
		    pst.setString(index, "%" + oproep.getFax() + "%");
		    index++;
		}
		if (!datum.equals(""))
		{
		    java.util.Date datumDate = df.parse(datum, pos3);
		    Date datumUtilDate = new java.sql.Date(datumDate.getTime());
		    pst.setDate(index, datumUtilDate);
		    index++;
		} else
		{
		    if (!startdatum.equals(""))
		    {
			java.util.Date startdatumDate = df.parse(startdatum, pos1);
			Date startdatumUtilDate = new java.sql.Date(startdatumDate.getTime());
			pst.setDate(index, startdatumUtilDate);
			index++;
		    }
		    if (!einddatum.equals(""))
		    {
			java.util.Date einddatumDate = df.parse(einddatum, pos2);
			Date einddatumUtilDate = new java.sql.Date(einddatumDate.getTime());
			pst.setDate(index, einddatumUtilDate);
			index++;
		    }
		}
		if (useGebruiker != 1)
		{
		    pst.setInt(index, oproep.getGebruikerId());
		    index++;
		}
		pst.setInt(index, oproep.getType());
		rs = pst.executeQuery();
		while (rs.next())
		{
		    Oproep result = new Oproep();
		    result.setId(rs.getInt("id"));
		    result.setType(rs.getInt("type"));
		    result.setDeadline(rs.getDate("deadline"));
		    result.setOproepdatum(rs.getDate("oproepdatum"));
		    result.setNaam(rs.getString("naam"));
		    result.setVoornaam(rs.getString("voornaam"));
		    result.setStatusId(rs.getInt("statusId"));
		    result.setGebruikerId(rs.getInt("gebruikerId"));
		    allOproepen.add(result);
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
	return allOproepen;
    }

    /**
     * Gets the Oproep from the database by it's id
     * @return Returns a Oproep
     * @param id The id to retrieve
     */
    public static Oproep findByPKAfgehandeld(int id)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	Oproep oproep = new Oproep();
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.OPROEP_BY_PK_AFGEHANDELD);
		pst.setInt(1, id);
		rs = pst.executeQuery();
		if (rs.next())
		{
		    oproep.setId(id);
		    oproep.setType(rs.getInt("type"));
		    oproep.setDeadline(rs.getDate("deadline"));
		    oproep.setOproepdatum(rs.getDate("oproepdatum"));
		    oproep.setNaam(rs.getString("naam"));
		    oproep.setVoornaam(rs.getString("voornaam"));
		    oproep.setFunctie(rs.getString("functie"));
		    oproep.setStraat(rs.getString("straat"));
		    oproep.setHuisnummer(rs.getString("huisnummer"));
		    oproep.setBusnummer(rs.getString("busnummer"));
		    oproep.setTelefoon(rs.getString("telefoon"));
		    oproep.setFax(rs.getString("fax"));
		    oproep.setEmail(rs.getString("email"));
		    oproep.setGeslacht(rs.getString("geslacht"));
		    oproep.setOrganisatie(rs.getString("organisatie"));
		    oproep.setOpmerking(rs.getString("opmerking"));
		    oproep.setHerkomstnummer(rs.getString("herkomstnummer"));
		    oproep.setAfgehandeld(rs.getBoolean("afgehandeld"));
		    oproep.setSlot(rs.getBoolean("slot"));
		    oproep.setDoelgroepId(rs.getInt("doelgroepId"));
		    oproep.setHerkomstId(rs.getInt("herkomstId"));
		    oproep.setStatusId(rs.getInt("statusId"));
		    oproep.setMediumId(rs.getInt("mediumId"));
		    oproep.setDistributieId(rs.getInt("distributieId"));
		    oproep.setGemeenteId(rs.getInt("gemeenteId"));
		    oproep.setGebruikerId(rs.getInt("gebruikerId"));
		    oproep.setDomeinId(rs.getInt("domeinId"));
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
	return oproep;
    }

    /**
     * Gets the Oproep from the database by it's id
     * @return Returns a Oproep
     * @param id The id to retrieve
     */
    public static Oproep findByPK(int id)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	Oproep oproep = new Oproep();
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.OPROEP_BY_PK);
		pst.setInt(1, id);
		rs = pst.executeQuery();
		if (rs.next())
		{
		    oproep.setId(id);
		    oproep.setType(rs.getInt("type"));
		    oproep.setDeadline(rs.getDate("deadline"));
		    oproep.setOproepdatum(rs.getDate("oproepdatum"));
		    oproep.setNaam(rs.getString("naam"));
		    oproep.setVoornaam(rs.getString("voornaam"));
		    oproep.setFunctie(rs.getString("functie"));
		    oproep.setStraat(rs.getString("straat"));
		    oproep.setHuisnummer(rs.getString("huisnummer"));
		    oproep.setBusnummer(rs.getString("busnummer"));
		    String telefoon = rs.getString("telefoon");
		    if (telefoon != null)
			telefoon = telefoon.trim();
		    else
			telefoon = "";
		    oproep.setTelefoon(telefoon);
		    oproep.setFax(rs.getString("fax"));
		    oproep.setEmail(rs.getString("email"));
		    oproep.setGeslacht(rs.getString("geslacht"));
		    oproep.setOrganisatie(rs.getString("organisatie"));
		    oproep.setOpmerking(rs.getString("opmerking"));
		    oproep.setHerkomstnummer(rs.getString("herkomstnummer"));
		    oproep.setAfgehandeld(rs.getBoolean("afgehandeld"));
		    oproep.setSlot(rs.getBoolean("slot"));
		    oproep.setDoelgroepId(rs.getInt("doelgroepId"));
		    oproep.setHerkomstId(rs.getInt("herkomstId"));
		    oproep.setStatusId(rs.getInt("statusId"));
		    oproep.setMediumId(rs.getInt("mediumId"));
		    oproep.setDistributieId(rs.getInt("distributieId"));
		    oproep.setGemeenteId(rs.getInt("gemeenteId"));
		    oproep.setGebruikerId(rs.getInt("gebruikerId"));
		    oproep.setDomeinId(rs.getInt("domeinId"));
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
	return oproep;
    }

    /**
     * Checkt of een taalunie herkomstnummer al aanwezig is in de databank
     * @return boolean die stelt of het herkomstnummer al in de databank zit
     * @param herkomstNummer het te zoeken herkomstnummer
     */
    public static boolean taalunieVraagAlreadyInDB(String herkomstNummer)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	boolean hkmInDb = false;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.OPROEP_BY_HERKOMSTNUMMER_TAALUNIE);
		pst.setString(1, herkomstNummer);
		rs = pst.executeQuery();
		if (rs.next())
		{
		    // als er een result lijst wordt teruggegeven, bestaat het herkomstnummer al in de database
		    hkmInDb = true;
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
	return hkmInDb;
    }

    /**
     * Gets all Oproepen from the database
     * @return Returns a ArrayList
     */
    public static ArrayList<Oproep> findAll()
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	ArrayList<Oproep> allOproepen = new ArrayList<Oproep>();
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.ALL_OPROEPEN);
		rs = pst.executeQuery();
		while (rs.next())
		{
		    Oproep oproep = new Oproep();
		    oproep.setId(rs.getInt("id"));
		    oproep.setType(rs.getInt("type"));
		    oproep.setDeadline(rs.getDate("deadline"));
		    oproep.setOproepdatum(rs.getDate("oproepdatum"));
		    oproep.setNaam(rs.getString("naam"));
		    oproep.setVoornaam(rs.getString("voornaam"));
		    oproep.setFunctie(rs.getString("functie"));
		    oproep.setStraat(rs.getString("straat"));
		    oproep.setHuisnummer(rs.getString("huisnummer"));
		    oproep.setBusnummer(rs.getString("busnummer"));
		    oproep.setTelefoon(rs.getString("telefoon"));
		    oproep.setFax(rs.getString("fax"));
		    oproep.setEmail(rs.getString("email"));
		    oproep.setGeslacht(rs.getString("geslacht"));
		    oproep.setOrganisatie(rs.getString("organisatie"));
		    oproep.setOpmerking(rs.getString("opmerking"));
		    oproep.setHerkomstnummer(rs.getString("herkomstnummer"));
		    oproep.setAfgehandeld(rs.getBoolean("afgehandeld"));
		    oproep.setSlot(rs.getBoolean("slot"));
		    oproep.setDoelgroepId(rs.getInt("doelgroepId"));
		    oproep.setHerkomstId(rs.getInt("herkomstId"));
		    oproep.setStatusId(rs.getInt("statusId"));
		    oproep.setMediumId(rs.getInt("mediumId"));
		    oproep.setDistributieId(rs.getInt("distributieId"));
		    oproep.setGemeenteId(rs.getInt("gemeenteId"));
		    oproep.setGebruikerId(rs.getInt("gebruikerId"));
		    oproep.setDomeinId(rs.getInt("domeinId"));
		    allOproepen.add(oproep);
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
	return allOproepen;
    }

    /**
     * Gets all "open" (afgehandeld == false) Oproepen By Type from the database
     * @return Returns a ArrayList
     * @param type The type to use
     */
    public static ArrayList<Oproep> findAllOpenByType(int type)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	ArrayList<Oproep> allOproepen = new ArrayList<Oproep>();
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.ALL_OPEN_OPROEPEN_BY_TYPE);
		pst.setInt(1, type);
		rs = pst.executeQuery();
		while (rs.next())
		{
		    Oproep oproep = new Oproep();
		    oproep.setId(rs.getInt("id"));
		    oproep.setType(rs.getInt("type"));
		    oproep.setDeadline(rs.getDate("deadline"));
		    oproep.setOproepdatum(rs.getDate("oproepdatum"));
		    oproep.setNaam(rs.getString("naam"));
		    oproep.setVoornaam(rs.getString("voornaam"));
		    oproep.setFunctie(rs.getString("functie"));
		    oproep.setStraat(rs.getString("straat"));
		    oproep.setHuisnummer(rs.getString("huisnummer"));
		    oproep.setBusnummer(rs.getString("busnummer"));
		    oproep.setTelefoon(rs.getString("telefoon"));
		    oproep.setFax(rs.getString("fax"));
		    oproep.setEmail(rs.getString("email"));
		    oproep.setGeslacht(rs.getString("geslacht"));
		    oproep.setOrganisatie(rs.getString("organisatie"));
		    oproep.setOpmerking(rs.getString("opmerking"));
		    oproep.setHerkomstnummer(rs.getString("herkomstnummer"));
		    oproep.setAfgehandeld(false);
		    oproep.setSlot(rs.getBoolean("slot"));
		    oproep.setDoelgroepId(rs.getInt("doelgroepId"));
		    oproep.setHerkomstId(rs.getInt("herkomstId"));
		    oproep.setStatusId(rs.getInt("statusId"));
		    oproep.setMediumId(rs.getInt("mediumId"));
		    oproep.setDistributieId(rs.getInt("distributieId"));
		    oproep.setGemeenteId(rs.getInt("gemeenteId"));
		    oproep.setGebruikerId(rs.getInt("gebruikerId"));
		    oproep.setDomeinId(rs.getInt("domeinId"));
		    allOproepen.add(oproep);
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
	return allOproepen;
    }

    /**
     * Gets all "open" (afgehandeld == false) Oproepen By Type and Gebruiker from the database
     * @return Returns a ArrayList
     * @param type The type to use
     * @param gebruikerId The gebruikerId to use
     */
    public static ArrayList<Oproep> findAllOpenByTypeAndGebruiker(int type, int gebruikerId)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	ArrayList<Oproep> allOproepen = new ArrayList<Oproep>();
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.ALL_OPEN_OPROEPEN_BY_TYPE_AND_GEBRUIKER);
		pst.setInt(1, type);
		pst.setInt(2, gebruikerId);
		rs = pst.executeQuery();
		while (rs.next())
		{
		    Oproep oproep = new Oproep();
		    oproep.setId(rs.getInt("id"));
		    oproep.setType(rs.getInt("type"));
		    oproep.setDeadline(rs.getDate("deadline"));
		    oproep.setOproepdatum(rs.getDate("oproepdatum"));
		    oproep.setNaam(rs.getString("naam"));
		    oproep.setVoornaam(rs.getString("voornaam"));
		    oproep.setFunctie(rs.getString("functie"));
		    oproep.setStraat(rs.getString("straat"));
		    oproep.setHuisnummer(rs.getString("huisnummer"));
		    oproep.setBusnummer(rs.getString("busnummer"));
		    oproep.setTelefoon(rs.getString("telefoon"));
		    oproep.setFax(rs.getString("fax"));
		    oproep.setEmail(rs.getString("email"));
		    oproep.setGeslacht(rs.getString("geslacht"));
		    oproep.setOrganisatie(rs.getString("organisatie"));
		    oproep.setOpmerking(rs.getString("opmerking"));
		    oproep.setHerkomstnummer(rs.getString("herkomstnummer"));
		    oproep.setAfgehandeld(false);
		    oproep.setSlot(rs.getBoolean("slot"));
		    oproep.setDoelgroepId(rs.getInt("doelgroepId"));
		    oproep.setHerkomstId(rs.getInt("herkomstId"));
		    oproep.setStatusId(rs.getInt("statusId"));
		    oproep.setMediumId(rs.getInt("mediumId"));
		    oproep.setDistributieId(rs.getInt("distributieId"));
		    oproep.setGemeenteId(rs.getInt("gemeenteId"));
		    oproep.setGebruikerId(rs.getInt("gebruikerId"));
		    oproep.setDomeinId(rs.getInt("domeinId"));
		    allOproepen.add(oproep);
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
	return allOproepen;
    }

    /**
     * Gets all "open" (afgehandeld == false) Oproepen By Type and NOT by Gebruiker from the database
     * @return Returns a ArrayList
     * @param type The type to use
     * @param gebruikerId The gebruikerId to use
     */
    public static ArrayList<Oproep> findAllOpenByTypeAndNotGebruiker(int type, int gebruikerId)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	ArrayList<Oproep> allOproepen = new ArrayList<Oproep>();
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.ALL_OPEN_OPROEPEN_BY_TYPE_AND_NOT_GEBRUIKER);
		pst.setInt(1, type);
		pst.setInt(2, gebruikerId);
		rs = pst.executeQuery();
		while (rs.next())
		{
		    Oproep oproep = new Oproep();
		    oproep.setId(rs.getInt("id"));
		    oproep.setType(rs.getInt("type"));
		    oproep.setDeadline(rs.getDate("deadline"));
		    oproep.setOproepdatum(rs.getDate("oproepdatum"));
		    oproep.setNaam(rs.getString("naam"));
		    oproep.setVoornaam(rs.getString("voornaam"));
		    oproep.setFunctie(rs.getString("functie"));
		    oproep.setStraat(rs.getString("straat"));
		    oproep.setHuisnummer(rs.getString("huisnummer"));
		    oproep.setBusnummer(rs.getString("busnummer"));
		    oproep.setTelefoon(rs.getString("telefoon"));
		    oproep.setFax(rs.getString("fax"));
		    oproep.setEmail(rs.getString("email"));
		    oproep.setGeslacht(rs.getString("geslacht"));
		    oproep.setOrganisatie(rs.getString("organisatie"));
		    oproep.setOpmerking(rs.getString("opmerking"));
		    oproep.setHerkomstnummer(rs.getString("herkomstnummer"));
		    oproep.setAfgehandeld(false);
		    oproep.setSlot(rs.getBoolean("slot"));
		    oproep.setDoelgroepId(rs.getInt("doelgroepId"));
		    oproep.setHerkomstId(rs.getInt("herkomstId"));
		    oproep.setStatusId(rs.getInt("statusId"));
		    oproep.setMediumId(rs.getInt("mediumId"));
		    oproep.setDistributieId(rs.getInt("distributieId"));
		    oproep.setGemeenteId(rs.getInt("gemeenteId"));
		    oproep.setGebruikerId(rs.getInt("gebruikerId"));
		    oproep.setDomeinId(rs.getInt("domeinId"));
		    allOproepen.add(oproep);
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
	return allOproepen;
    }

    public static ArrayList<Oproep> findAllNietToegeeigendeOfTaalunieversum()
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	ArrayList<Oproep> allOproepen = new ArrayList<Oproep>();
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.ALL_NIET_TOEGEEIGENDE_OPROEPEN_TAALUNIEVERSUM);
		rs = pst.executeQuery();
		while (rs.next())
		{
		    Oproep oproep = new Oproep();
		    oproep.setId(rs.getInt("id"));
		    oproep.setType(rs.getInt("type"));
		    oproep.setDeadline(rs.getDate("deadline"));
		    oproep.setOproepdatum(rs.getDate("oproepdatum"));
		    oproep.setNaam(rs.getString("naam"));
		    oproep.setVoornaam(rs.getString("voornaam"));
		    oproep.setFunctie(rs.getString("functie"));
		    oproep.setStraat(rs.getString("straat"));
		    oproep.setHuisnummer(rs.getString("huisnummer"));
		    oproep.setBusnummer(rs.getString("busnummer"));
		    oproep.setTelefoon(rs.getString("telefoon"));
		    oproep.setFax(rs.getString("fax"));
		    oproep.setEmail(rs.getString("email"));
		    oproep.setGeslacht(rs.getString("geslacht"));
		    oproep.setOrganisatie(rs.getString("organisatie"));
		    oproep.setOpmerking(rs.getString("opmerking"));
		    oproep.setHerkomstnummer(rs.getString("herkomstnummer"));
		    oproep.setAfgehandeld(false);
		    oproep.setSlot(rs.getBoolean("slot"));
		    oproep.setDoelgroepId(rs.getInt("doelgroepId"));
		    oproep.setHerkomstId(rs.getInt("herkomstId"));
		    oproep.setStatusId(rs.getInt("statusId"));
		    oproep.setMediumId(rs.getInt("mediumId"));
		    oproep.setDistributieId(rs.getInt("distributieId"));
		    oproep.setGemeenteId(rs.getInt("gemeenteId"));
		    oproep.setGebruikerId(rs.getInt("gebruikerId"));
		    oproep.setDomeinId(rs.getInt("domeinId"));
		    allOproepen.add(oproep);
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
	return allOproepen;
    }

    public static String findHoogsteHerkomstNummerOfTaalunieversum()
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	String hoogsteHerkomstnummer = "0";
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.HOOGSTE_HERKOMSTNUMMER_TAALUNIEVERSUM);
		rs = pst.executeQuery();
		if (rs.next())
		{
		    hoogsteHerkomstnummer = rs.getString(1);
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
	return hoogsteHerkomstnummer;
    }

    /**
     * Inserts a Oproep
     * @return Return int
     * @param oproep The Oproep to insert
     */
    public static int insert(Oproep oproep)
    {
	int id = 0;
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	// SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	// ParsePosition pos1 = new ParsePosition(0);
	// ParsePosition pos2 = new ParsePosition(0);
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.INSERT_OPROEP);
		Util.pstSetInt(pst, 1, oproep.getType());
		pst.setDate(2, oproep.getDeadline());
		pst.setDate(3, oproep.getOproepdatum());
		pst.setString(4, oproep.getNaam());
		pst.setString(5, oproep.getVoornaam());
		pst.setString(6, oproep.getTelefoon());
		pst.setString(7, oproep.getHerkomstnummer());
		pst.setBoolean(8, false);
		pst.setBoolean(9, false);
		Util.pstSetInt(pst, 10, oproep.getHerkomstId());
		Util.pstSetInt(pst, 11, oproep.getStatusId());
		Util.pstSetInt(pst, 12, oproep.getMediumId());
		Util.pstSetInt(pst, 13, oproep.getDistributieId());
		Util.pstSetInt(pst, 14, oproep.getGebruikerId());
		Util.pstSetInt(pst, 15, oproep.getDomeinId());
		pst.setString(16, "0");
		pst.setString(17, oproep.getEmail());
		pst.setString(18, oproep.getFax());
		pst.setString(19, oproep.getOrganisatie());
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
     * Inserts the received taalvragen xml
     * @return Return int
     * @param String The ophaalURL to insert
     * @param String The xml to insert
     * @throws SQLException
     */
    public static int insertTaalvragenXML(String ophaalURL, String xml) throws SQLException
    {
	int id = 0;
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.INSERT_XML_INTO_TAALUNIEXML);
		pst.setString(1, ophaalURL);
		Util.pstSetClob(pst, 2, xml);
		pst.executeUpdate();
		id = ((IfxStatement) pst).getSerial();
	    } else
	    {
		System.err.println("Geen connectie !");
	    }
	} catch (java.sql.SQLException e)
	{
	    e.printStackTrace(System.err);
	    throw e;
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
     * Inserts the received taalvragen xml
     * @return Return int
     * @param String The ophaalURL to insert
     * @param String The xml to insert
     * @throws SQLException
     */
    public static int insertTaalvragenXMLFoutBoodschap(int xmlId, String foutboodschap) throws SQLException
    {
	int id = 0;
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.UPDATE_FOUTBOODSCHAP_INTO_TAALUNIEXML);
		pst.setString(1, foutboodschap);
		Util.pstSetClob(pst, 1, foutboodschap);
		pst.setInt(2, xmlId);
		pst.executeUpdate();
	    } else
	    {
		System.err.println("Geen connectie !");
	    }
	} catch (java.sql.SQLException e)
	{
	    e.printStackTrace(System.err);
	    throw e;
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
     * Inserts a Oproep
     * @return Return int
     * @param oproep The Oproep to insert
     */
    public static void fillit()
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.INSERT_OPROEPWITHID);
		for (int i = 270; i < 13270; i++)
		{
		    pst.setInt(1, i);
		    pst.setInt(2, 1);
		    pst.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
		    pst.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
		    pst.setString(5, "Vankersschaever");
		    pst.setString(6, "Mattijs");
		    pst.setString(7, "0475851658");
		    pst.setString(8, "10");
		    pst.setBoolean(9, false);
		    pst.setBoolean(10, false);
		    pst.setInt(11, 1);
		    pst.setInt(12, 1);
		    pst.setInt(13, 1);
		    pst.setInt(14, 55);
		    pst.setInt(15, 1);
		    pst.setInt(16, 1);
		    pst.executeUpdate();
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
     * Updates a Oproep
     * @return Return boolean
     * @param oproep The Oproep to change
     */
    public static boolean update(Oproep oproep)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	// SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	// ParsePosition pos1 = new ParsePosition(0);
	// ParsePosition pos2 = new ParsePosition(0);
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.UPDATE_OPROEP);
		Util.pstSetInt(pst, 1, oproep.getType());
		pst.setDate(2, oproep.getDeadline());
		pst.setDate(3, oproep.getOproepdatum());
		pst.setString(4, oproep.getNaam());
		pst.setString(5, oproep.getVoornaam());
		pst.setString(6, oproep.getTelefoon());
		pst.setString(7, oproep.getHerkomstnummer());
		pst.setBoolean(8, false);
		pst.setBoolean(9, false);
		Util.pstSetInt(pst, 10, oproep.getHerkomstId());
		Util.pstSetInt(pst, 11, oproep.getStatusId());
		Util.pstSetInt(pst, 12, oproep.getMediumId());
		Util.pstSetInt(pst, 13, oproep.getDistributieId());
		Util.pstSetInt(pst, 14, oproep.getGebruikerId());
		Util.pstSetInt(pst, 15, oproep.getDomeinId());
		pst.setString(16, oproep.getEmail());
		pst.setInt(17, oproep.getId());
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
     * "Opens" a Oproep
     * @return Return boolean
     * @param id The id of the Oproep to open
     */
    public static boolean open(int id)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.OPEN_OPROEP);
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
     * "Closes" a Oproep
     * @return Return boolean
     * @param id The id of the Oproep to close
     */
    public static boolean close(int id)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.CLOSE_OPROEP);
		pst.setInt(1, id);
		int check = pst.executeUpdate();
		if (check == 1)
		{
		    // oproep is closed successfully, now check if it has to be uploaded to taalunieversum
		    return addToOpladenTaalunie(id);
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
     * Checks if the oproep has its "herkomst" from "Taalunieversum". If so the oproep has to be put in the queue table to be
     * oploaded to the "Taalunieversum".
     * @param id The id of the Oproep
     * @return Return boolean
     */
    private static boolean addToOpladenTaalunie(int id)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		int herkomstID = 0;
		int taalunieversumHerkomstID = 0;
		// first get "herkomstid" of "oproep"
		pst = con.prepareStatement(Queries.OPROEP_BY_PK);
		pst.setInt(1, id);
		ResultSet resultSet = pst.executeQuery();
		if (resultSet.next())
		{
		    herkomstID = resultSet.getInt("herkomstId");
		} else
		{
		    return false;
		}
		// get "herkomstid" of "Taalunieversum"
		pst = con.prepareStatement(Queries.HERKOMST_BY_NAME);
		pst.setString(1, "Taalunieversum");
		resultSet = pst.executeQuery();
		if (resultSet.next())
		{
		    taalunieversumHerkomstID = resultSet.getInt("id");
		} else
		{
		    return false;
		}
		// if herkomstID of oproep is herkomstId of Taalunieversum, then add oproep to upload table
		if (herkomstID != 0 && (herkomstID == taalunieversumHerkomstID))
		{
		    pst = con.prepareStatement(Queries.INSERT_OPROEP_INTO_TAALUNIE);
		    pst.setInt(1, id);
		    int insertnr = pst.executeUpdate();
		    if (insertnr == 1)
		    {
			return true;
		    } else
		    {
			return false;
		    }
		} else
		{ // not the same, so "oproep" didn't have to be inserted
		    return true;
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
     * Updates a Oproep with the "klantengegevens"
     * @return Return boolean
     * @param oproep The Oproep to change
     */
    public static boolean updateKlantenGegevens(Oproep oproep)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.UPDATE_KLANTENGEGEVENS_OPROEP);
		pst.setString(1, oproep.getFunctie());
		pst.setString(2, oproep.getStraat());
		pst.setString(3, oproep.getHuisnummer());
		pst.setString(4, oproep.getBusnummer());
		pst.setString(5, oproep.getFax());
		pst.setString(6, oproep.getEmail());
		pst.setString(7, oproep.getGeslacht());
		pst.setString(8, oproep.getOrganisatie());
		Util.pstSetClob(pst, 9, oproep.getOpmerking());
		Util.pstSetInt(pst, 10, oproep.getGemeenteId());
		Util.pstSetInt(pst, 11, oproep.getDoelgroepId());
		pst.setString(12, oproep.getNaam());
		pst.setString(13, oproep.getVoornaam());
		pst.setString(14, oproep.getTelefoon());
		Util.pstSetInt(pst, 15, oproep.getDomeinId());
		Util.pstSetInt(pst, 16, oproep.getHerkomstId());
		pst.setString(17, oproep.getHerkomstnummer());
		pst.setInt(18, oproep.getId());
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
     * deletes a Oproep
     * @return Return boolean
     * @param id The id of the Oproep to delete
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
		pst = con.prepareStatement(Queries.DELETE_OPROEP);
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
     * deletes a sent Oproep from the taalunie queue
     * @return Return boolean
     * @param id The id of the Oproep to remove from the queue
     */
    public static boolean removeFromTaalunieSendQueue(int id)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.DELETE_VERZONDEN_OPROEP_TAALUNIE);
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
     * Updates a Oproep to be handled by another "Gebruiker"
     * @return Return boolean
     * @param oproepId The id of the Oproep to change
     * @param gebruikerId The id of the Gebruiker taking over the oproep
     */
    public static boolean overname(int oproepId, int gebruikerId)
    {
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(Queries.OVERNAME_OPROEP);
		pst.setInt(1, gebruikerId);
		pst.setInt(2, oproepId);
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
     * Gets the deadline_d
     * @return Returns a String
     */
    public String getDeadline_d()
    {
	if (deadline != null)
	{
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	    String dateString = formatter.format(deadline);
	    dateString = dateString.substring(0, 2);
	    return dateString;
	} else
	{
	    return deadline_d;
	}
    }

    /**
     * Sets the deadline_d
     * @param deadline_d The deadline_d to set
     */
    public void setDeadline_d(String deadline_d)
    {
	this.deadline_d = deadline_d;
    }

    /**
     * Gets the deadline_m
     * @return Returns a String
     */
    public String getDeadline_m()
    {
	if (deadline != null)
	{
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	    String dateString = formatter.format(deadline);
	    dateString = dateString.substring(3, 5);
	    return dateString;
	} else
	{
	    return deadline_m;
	}
    }

    /**
     * Sets the deadline_m
     * @param deadline_m The deadline_m to set
     */
    public void setDeadline_m(String deadline_m)
    {
	this.deadline_m = deadline_m;
    }

    /**
     * Gets the deadline_y
     * @return Returns a String
     */
    public String getDeadline_y()
    {
	if (deadline != null)
	{
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	    String dateString = formatter.format(deadline);
	    dateString = dateString.substring(6, 10);
	    return dateString;
	} else
	{
	    return deadline_y;
	}
    }

    /**
     * Sets the deadline_y
     * @param deadline_y The deadline_y to set
     */
    public void setDeadline_y(String deadline_y)
    {
	this.deadline_y = deadline_y;
    }

    /**
     * Gets the oproepdatum_d
     * @return Returns a String
     */
    public String getOproepdatum_d()
    {
	if (oproepdatum != null)
	{
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	    String dateString = formatter.format(oproepdatum);
	    dateString = dateString.substring(0, 2);
	    return dateString;
	} else
	{
	    return oproepdatum_d;
	}
    }

    /**
     * Sets the oproepdatum_d
     * @param oproepdatum_d The oproepdatum_d to set
     */
    public void setOproepdatum_d(String oproepdatum_d)
    {
	this.oproepdatum_d = oproepdatum_d;
    }

    /**
     * Gets the oproepdatum_m
     * @return Returns a String
     */
    public String getOproepdatum_m()
    {
	if (oproepdatum != null)
	{
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	    String dateString = formatter.format(oproepdatum);
	    dateString = dateString.substring(3, 5);
	    return dateString;
	} else
	{
	    return oproepdatum_m;
	}
    }

    /**
     * Sets the oproepdatum_m
     * @param oproepdatum_m The oproepdatum_m to set
     */
    public void setOproepdatum_m(String oproepdatum_m)
    {
	this.oproepdatum_m = oproepdatum_m;
    }

    /**
     * Gets the oproepdatum_y
     * @return Returns a String
     */
    public String getOproepdatum_y()
    {
	if (oproepdatum != null)
	{
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	    String dateString = formatter.format(oproepdatum);
	    dateString = dateString.substring(6, 10);
	    return dateString;
	} else
	{
	    return oproepdatum_y;
	}
    }

    /**
     * Sets the oproepdatum_y
     * @param oproepdatum_y The oproepdatum_y to set
     */
    public void setOproepdatum_y(String oproepdatum_y)
    {
	this.oproepdatum_y = oproepdatum_y;
    }

    /**
     * Gets the ofloadtaalunie
     * @return Returns a boolean
     */
    public boolean getOfloadtaalunie()
    {
	return ofloadtaalunie;
    }

    /**
     * Sets the ofloadtaalunie
     * @param ofloadtaalunie The ofloadtaalunie to set
     */
    public void setOfloadtaalunie(boolean ofloadtaalunie)
    {
	this.ofloadtaalunie = ofloadtaalunie;
    }

    /**
     * Gets the eersteVraag
     * @return Returns a Taalvraag
     */
    public Taalvraag getEersteVraag()
    {
	if (eersteVraag == null)
	{
	    java.util.ArrayList<Taalvraag> taalvragen = Taalvraag.findAllByOproep(id);
	    eersteVraag = taalvragen.get(0);
	    if (!eersteVraag.getTitel().equals(""))
	    {
		String titel = eersteVraag.getTitel();
		String newTitel;
		if (titel.length() > 40)
		{
		    newTitel = titel.substring(0, 40) + "...";
		} else
		{
		    newTitel = titel;
		}
		eersteVraag.setTitel(newTitel);
	    } else
	    {
		String vraag = eersteVraag.getVraag();
		String newVraag;
		if (vraag.length() > 40)
		{
		    newVraag = vraag.substring(0, 40) + "...";
		} else
		{
		    newVraag = vraag;
		}
		eersteVraag.setVraag(newVraag);
	    }
	}
	return eersteVraag;
    }

    /**
     * Sets the eersteVraag
     * @param eersteVraag The eersteVraag to set
     */
    public void setEersteVraag(Taalvraag eersteVraag)
    {
	this.eersteVraag = eersteVraag;
    }
}
