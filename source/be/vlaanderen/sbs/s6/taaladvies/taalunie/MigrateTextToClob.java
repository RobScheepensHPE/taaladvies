/*
 * Created on Nov 29, 2004
 */
package be.vlaanderen.sbs.s6.taaladvies.taalunie;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import be.vlaanderen.sbs.s6.framework.logging.AppLogger;
import be.vlaanderen.sbs.s6.taaladvies.db.IDbConnection;
import be.vlaanderen.sbs.s6.taaladvies.db.MyDbConnection;

/**
 * @author nzvwbg
 */
public class MigrateTextToClob
{
    private int startID = -1;
    private int stopID = -1;

    public MigrateTextToClob(int startID, int stopID)
    {
	this.startID = startID;
	this.stopID = stopID;
    }
    /**
         * <code>oldTdTks</code>: naam van de oude td_tks tabel
         */
    private final String oldTdTks = "old_td_tks";
    /**
         * <code>newTdTks</code>: naam van de nieuwe td_tks tabel
         */
    private final String newTdTks = "td_tks";
    /**
         * <code>getAllOldTdTksRows</code>: sql query om alle rijen van de oude td_tks tabel op te halen
         */
    private final String getAllOldTdTksRows = "SELECT hfd_ttl_ds, hfd_ttl_htm_db, tks_id FROM " + oldTdTks;
    /**
         * <code>updateAllNewTdTksRows</code>: sql query om alle rijen in de nieuwe td_tks tabel te updaten voor velden van
         * het datatype CLOB
         */
    private final String updateAllNewTdTksRows =
	"UPDATE " + newTdTks + " SET hfd_ttl_ds = ?" + ", hfd_ttl_htm_db = ? " + " WHERE tks_id = ? ";
    /**
         * <code>oldTdTsb</code>: naam van de oude td_tsb tabel
         */
    private final String oldTdTsb = "old_td_tsb";
    /**
         * <code>newTdTsb</code>: naam van de nieuwe td_tsb tabel
         */
    private final String newTdTsb = "td_tsb";
    /**
         * <code>getAllOldTdTsbRows</code>: sql query om alle rijen van de oude td_tsb tabel op te halen
         */
    private final String getAllOldTdTsbRows =
	"SELECT tsb_ttl_htm_db" + ", tsb_db" + ", tsb_ttl_ds " + ", tsb_id" + " FROM " + oldTdTsb;
    /**
         * <code>updateAllNewTdTsbRows</code>: : sql query om alle rijen in de nieuwe td_tsb tabel te updaten voor velden van
         * het datatype CLOB
         */
    private final String updateAllNewTdTsbRows =
	"UPDATE " + newTdTsb + " SET tsb_ttl_htm_db = ?" + ", tsb_db = ? " + ", tsb_ttl_ds =? " + " WHERE tsb_id = ? ";
    /**
         * <code>oldTdTlvAwd</code>: naam van de oude td_tlv_awd
         */
    private final String oldTdTlvAwd = "old_td_tlv_awd";
    /**
         * <code>getAllOldTdTlvAwdRows</code>: sql query om alle taalvragen op te halen
         */
    private String getAllOldTdTlvAwdRows = "SELECT 	" +
    // " , adv_vzk_id AS oproepId" +
	"										tlv_ds AS vraag" +
	// " , tlv_spc_htm_db AS vraagHTML" +
	"									, tlv_inf_db AS informatie" +
	// " , tlv_inf_htm_db AS informatieHTML" +
	// " , tlv_vgn_nr AS volgnummer" +
	// " , tlv_vrl_agh_fg AS afgehandeld" +
	"									, awd_krt_db AS antwoord" +
	// " , awd_krt_htm_db AS antwoordHTML" +
	"									, awd_tol_db AS toelichting" +
	// " , awd_tol_htm_db AS toelichtingHTML" +
	"									, awd_bjz_db AS bijzonderheid" +
	// " , awd_bjz_htm_db AS bijzonderheidHTML" +
	"									, ttl_db AS titel" +
	// " , ttl_htm_db AS titelHTML" +
	// " , rvt_id AS relevantieId" +
	// " , tlv_her_db AS herformulering" +
	// " , tlv_her_htm_db AS herformuleringHTML " +
	// " , drb_dt AS distributiedatum" +
	// " , tlv_upd_fg AS updated" +
	" 									, tlv_id AS id" + "					 FROM " + oldTdTlvAwd/*
                                                                                                                                                                 * where
                                                                                                                                                                 * tlv_vrl_agh_fg =
                                                                                                                                                                 * 't'
                                                                                                                                                                 */;
    // " ORDER BY tlv_id " ;
    /**
         * <code>newTdTlvAwd</code>: naam van de nieuwe td_tlv_awd tabel
         */
    private final String newTdTlvAwd = "td_tlv_awd";
    /**
         * <code>updateAllNewTdTlvAwdRows</code>: sql query om alle taalvragen in de nieuwe td_tlv_awd tabel te updaten voor
         * velden van het datatype CLOB
         */
    private final String updateAllNewTdTlvAwdRows =
	"UPDATE " + newTdTlvAwd + " SET tlv_ds = ?" + ", tlv_inf_db = ?" + ", awd_krt_db = ?" + ", awd_tol_db = ?"
	    + ", awd_bjz_db = ?" + ", ttl_db = ?" + " WHERE tlv_id = ? ";

    /**
         * Haalt alle teksblokken uit de originele tabel en inserteert ze in de nieuwe tabel, waarbij velden van het datatype
         * "lvarchar" omgezet worden naar "CLOB" omwille van performantieproblemen bij het sorteren van verity rijen op
         * lvarchars
         */
    public void convertTdTsb()
    {
	// initialiseer de appConf class nodig voor de resources , constants
	new TaalunieInitialization().init();
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	PreparedStatement updatePst = null;
	ResultSet set = null;
	int counter = 0;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(getAllOldTdTsbRows);
		updatePst = con.prepareStatement(updateAllNewTdTsbRows);
		set = pst.executeQuery();
		while (set.next())
		{
		    counter++;
		    String tsbTtlHtmDb = replaceNull(set.getString(1));
		    insertAsciiStream(updatePst, 1, tsbTtlHtmDb);
		    String tsbDb = replaceNull(set.getString(2));
		    insertAsciiStream(updatePst, 2, tsbDb);
		    String tsbTtlDs = replaceNull(set.getString(3));
		    insertAsciiStream(updatePst, 3, tsbTtlDs);
		    int tsbId = set.getInt(4);
		    if (set.wasNull())
		    {
			updatePst.setNull(4, Types.INTEGER);
		    } else
		    {
			updatePst.setInt(4, tsbId);
		    }
		    updatePst.addBatch();
		    if (counter == 100)
		    {
			counter = 0;
			int[] is = updatePst.executeBatch();
			AppLogger.getInstance().error("updated " + is.length + " rows ...");
		    }
		}
		if (counter > 0)
		{
		    int[] is = updatePst.executeBatch();
		    AppLogger.getInstance().error("updated " + is.length + " rows ...");
		}
	    } else
	    {
		AppLogger.getInstance().info("Geen connectie !");
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
		    if (pst != null) {pst.close();}
		    if (updatePst != null) {updatePst.close();}
		    if (set != null) {set.close();}
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		sqle.printStackTrace(System.err);
	    }
	}
    }

    /**
         * @param insertPst de prepared statement
         * @param index het index nummer
         * @param stringToInsert de string die als ascii stream geïnserteerd moet worden
         * @throws SQLException de sql exceptie als iets fout gaat
         */
    private void insertAsciiStream(PreparedStatement insertPst, int index, String stringToInsert) throws SQLException
    {
	byte[] bStringToInsert = stringToInsert.getBytes();
	ByteArrayInputStream baisStringToInsert = new ByteArrayInputStream(bStringToInsert);
	insertPst.setAsciiStream(index, baisStringToInsert, bStringToInsert.length);
    }

    /**
         * Haalt alle taalvragen uit de originele tabel en inserteert ze in de nieuwe tabel, waarbij velden van het datatype
         * "lvarchar" omgezet worden naar "CLOB" omwille van performantieproblemen bij het sorteren van verity rijen op
         * lvarchars
         */
    public void convertTdTlvAwd()
    {
	// initialiseer de appConf class nodig voor de resources , constants
	new TaalunieInitialization().init();
	// als start en stop id gespecifieerd zijn, voeg toe aan query
	if (startID != -1 && (stopID != -1))
	{
	    getAllOldTdTlvAwdRows += " WHERE tlv_id >= " + startID + "and tlv_id <= " + stopID;
	}
	getAllOldTdTlvAwdRows += " ORDER BY tlv_id ";
	AppLogger.getInstance().info("select query: " + getAllOldTdTlvAwdRows);
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	PreparedStatement updatePst = null;
	ResultSet set = null;
	int counter = 0;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(getAllOldTdTlvAwdRows);
		updatePst = con.prepareStatement(updateAllNewTdTlvAwdRows);
		set = pst.executeQuery();
		while (set.next())
		{
		    counter++;
		    String tlvDs = replaceNull(set.getString("vraag"));
		    insertAsciiStream(updatePst, 1, tlvDs);
		    String tlvInfDb = replaceNull(set.getString("informatie"));
		    insertAsciiStream(updatePst, 2, tlvInfDb);
		    String awdKrtDb = replaceNull(set.getString("antwoord"));
		    insertAsciiStream(updatePst, 3, awdKrtDb);
		    String awdTolDb = replaceNull(set.getString("toelichting"));
		    insertAsciiStream(updatePst, 4, awdTolDb);
		    String awdBjzDb = replaceNull(set.getString("bijzonderheid"));
		    insertAsciiStream(updatePst, 5, awdBjzDb);
		    String ttlDb = replaceNull(set.getString("titel"));
		    insertAsciiStream(updatePst, 6, ttlDb);
		    int tlvId = set.getInt("id");
		    if (set.wasNull())
		    {
			updatePst.setNull(1, Types.INTEGER);
		    } else
		    {
			updatePst.setInt(7, tlvId);
		    }
		    AppLogger.getInstance().info("id= " + tlvId);
		    AppLogger.getInstance().info("vraag: " + tlvDs);
		    AppLogger.getInstance().info("informatie: " + tlvInfDb);
		    AppLogger.getInstance().info("antwoord: " + awdKrtDb);
		    AppLogger.getInstance().info("toelichting: " + awdTolDb);
		    AppLogger.getInstance().info("bijzonderheid: " + awdBjzDb);
		    AppLogger.getInstance().info("titel: " + ttlDb);
		    AppLogger.getInstance().info("going to insert row");
		    updatePst.addBatch();
		    // updatePst.executeUpdate();
		    if (counter == 100)
		    {
			counter = 0;
			int[] is = updatePst.executeBatch();
			AppLogger.getInstance().error("updated " + is.length + " rows ...");
		    }
		}
		// execute last batch
		if (counter > 0)
		{
		    int[] is = updatePst.executeBatch();
		    AppLogger.getInstance().error("updated " + is.length + " rows ...");
		}
	    } else
	    {
		AppLogger.getInstance().info("Geen connectie !");
	    }
	} catch (java.sql.SQLException e)
	{
	    AppLogger.getInstance().fatal(
		"\n\n------\nsql state: " + e.getSQLState() + "\nerror code: " + e.getErrorCode() + "\n-----\n\n");
	    e.printStackTrace(System.err);
	} finally
	{
	    try
	    {
		if (con != null)
		{
		    if (pst != null) {pst.close();}
		    if (updatePst != null) {updatePst.close();}
		    if (set != null) {set.close();}
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		sqle.printStackTrace(System.err);
		AppLogger.getInstance().fatal(
		    "\n\n------\nsql state: " + sqle.getSQLState() + "\nerror code: " + sqle.getErrorCode() + "\n-----\n\n");
	    }
	}
    }

    /**
         * Haalt alle teksten uit de originele tabel en inserteert ze in de nieuwe tabel, waarbij velden van het datatype
         * "lvarchar" omgezet worden naar "CLOB" omwille van performantieproblemen bij het sorteren van verity rijen op
         * lvarchars
         */
    public void convertTdTks()
    {
	// initialiseer de appConf class nodig voor de resources , constants
	new TaalunieInitialization().init();
	IDbConnection dbconnection = MyDbConnection.getInstance();
	Connection con = dbconnection.getConnection();
	PreparedStatement pst = null;
	PreparedStatement updatePst = null;
	ResultSet set = null;
	int counter = 0;
	try
	{
	    if (con != null)
	    {
		pst = con.prepareStatement(getAllOldTdTksRows);
		updatePst = con.prepareStatement(updateAllNewTdTksRows);
		set = pst.executeQuery();
		while (set.next())
		{
		    counter++;
		    String hfdTtlDs = replaceNull(set.getString(1));
		    insertAsciiStream(updatePst, 1, hfdTtlDs);
		    String hfdTtlHtmDb = replaceNull(set.getString(2));
		    insertAsciiStream(updatePst, 2, hfdTtlHtmDb);
		    int tksId = set.getInt(3);
		    if (set.wasNull())
		    {
			updatePst.setNull(3, Types.INTEGER);
		    } else
		    {
			updatePst.setInt(3, tksId);
		    }
		    AppLogger.getInstance().info("tksid: " + tksId);
		    AppLogger.getInstance().info("hfd_ttl_ds: " + hfdTtlDs);
		    AppLogger.getInstance().info("hfd_ttl_htm_db: " + hfdTtlHtmDb);
		    updatePst.addBatch();
		    if (counter == 100)
		    {
			counter = 0;
			int[] is = updatePst.executeBatch();
			AppLogger.getInstance().error("updated " + is.length + " rows ...");
		    }
		}
		if (counter > 0)
		{
		    int[] is = updatePst.executeBatch();
		    AppLogger.getInstance().error("updated " + is.length + " rows ...");
		}
	    } else
	    {
		AppLogger.getInstance().info("Geen connectie !");
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
		    if (pst != null) {pst.close();}
		    if (updatePst != null) {updatePst.close();}
		    if (set != null) {set.close();}
		    dbconnection.releaseConnection(con);
		}
	    } catch (java.sql.SQLException sqle)
	    {
		sqle.printStackTrace(System.err);
	    }
	}
    }

    /**
         * @param input String die naar een lege string wordt geconverteerd als die null is
         * @return de originele string of een lege string
         */
    private String replaceNull(String input)
    {
	String returnString = input;
	if (input == null)
	{
	    returnString = "";
	}
	return returnString;
    }

    /**
         * @param args argumenten moeten ofwel leeg zijn of wel 2 integers die bepalen wat de eerste en laatste te verwerken id
         *        is voor td_tlv_awd
         */
    public static void main(String[] args)
    {
	System.out.println("new version");
	int startID = -1;
	int stopID = -1;
	if (args.length == 2)
	{
	    startID = Integer.parseInt(args[0]);
	    stopID = Integer.parseInt(args[1]);
	} else if (args.length != 0)
	{
	    System.err.println("start op zonder parameters om complete tabellen te migreren");
	    System.err.println("start op met startID stopID om enkel die range voor tabel td_tlv_awd te migreren");
	    System.exit(1);
	}
	MigrateTextToClob migrateTextToClob = new MigrateTextToClob(startID, stopID);
	AppLogger.getInstance().info("migrate td_tlv_awd");
	if (startID != -1)
	{
	    AppLogger.getInstance().info("first ID=" + startID + " and last Id=" + stopID);
	}
	migrateTextToClob.convertTdTlvAwd();
	AppLogger.getInstance().info("done migrating td_tlv_awd");
	if (startID != -1)
	{
	    AppLogger.getInstance().info("first ID=" + startID + " and last Id=" + stopID);
	    System.exit(0);
	}
	AppLogger.getInstance().info("migrate td_tks");
	migrateTextToClob.convertTdTks();
	AppLogger.getInstance().info("done migrating td_tks");
	AppLogger.getInstance().info("migrate td_tsb");
	migrateTextToClob.convertTdTsb();
	AppLogger.getInstance().info("done migrating td_tsb");
    }
}
