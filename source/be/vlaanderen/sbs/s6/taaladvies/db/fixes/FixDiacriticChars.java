/*
 * Created on Nov 29, 2004
 */
package be.vlaanderen.sbs.s6.taaladvies.db.fixes;

//import java.io.ByteArrayInputStream;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.apache.commons.lang.StringUtils;
import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.TextExtractingVisitor;
import be.vlaanderen.sbs.s6.framework.logging.AppLogger;
import be.vlaanderen.sbs.s6.taaladvies.db.IDbConnection;
import be.vlaanderen.sbs.s6.taaladvies.db.MyDbConnection;
import be.vlaanderen.sbs.s6.taaladvies.taalunie.TaalunieInitialization;

/**
 * @author nzvwbg
 */
public class FixDiacriticChars {
    private int startID = -1;
    private int stopID = -1;
    private Parser parser = new Parser();

    public FixDiacriticChars(int startID, int stopID){
        this.startID = startID;
        this.stopID = stopID;
    }




    /**
     * <code>oldTdTlvAwd</code>: naam van de oude td_tlv_awd
     */
    private final String tdTlvAwd = "td_tlv_awd";


    /**
     * <code>getAllTdTlvAwdRows</code>: sql query om alle taalvragen op te halen
     */
    private String getAllTdTlvAwdRows = "SELECT DISTINCT 	" +
	"									adv_vzk_id AS oproepId" +
	"									, tlv_ds AS vraag" +
	"									, tlv_spc_htm_db AS vraagHTML" +
	"									, tlv_inf_db AS informatie" +
	"									, tlv_inf_htm_db AS informatieHTML" +
	//"									, tlv_vgn_nr AS volgnummer" +
	//"									, tlv_vrl_agh_fg AS afgehandeld" +
	"									, awd_krt_db AS antwoord" +
	"									, awd_krt_htm_db AS antwoordHTML" +
	"									, awd_tol_db AS toelichting" +
	"									, awd_tol_htm_db AS toelichtingHTML" +
	"									, awd_bjz_db AS bijzonderheid" +
	"									, awd_bjz_htm_db AS bijzonderheidHTML" +
	"									, ttl_db AS titel" +
	"									, ttl_htm_db AS titelHTML" +
	//"									, rvt_id AS relevantieId" +
	"									, tlv_her_db AS herformulering" +
	"									, tlv_her_htm_db AS herformuleringHTML " +
	//"									, drb_dt AS distributiedatum" +
	//"									, tlv_upd_fg AS updated" +
	" 									, tlv_id AS id " +
	"					 FROM "  + tdTlvAwd ;
	//"						 ORDER BY tlv_id "	;


	/**
	 * <code>updateAllNewTdTlvAwdRows</code>: sql query om alle taalvragen in de nieuwe td_tlv_awd tabel te updaten
	 * voor velden van het datatype CLOB
	 */
    private final String updateTdTlvAwdRows = "UPDATE " + tdTlvAwd
    												+ " SET tlv_ds = ?" +
    														", tlv_inf_db = ?" +
    														", awd_krt_db = ?" +
    														", awd_tol_db = ?" +
    														", awd_bjz_db = ?" +
    														", ttl_db = ?" +
    														", tlv_her_db = ?"
    												+ " WHERE tlv_id = ? ";



    /**
     * @param insertPst de prepared statement
     * @param index het index nummer
     * @param stringToInsert de string die als ascii stream geïnserteerd moet worden
     * @throws SQLException de sql exceptie als iets fout gaat
     */
    /*private void insertAsciiStream(PreparedStatement insertPst, int index, String stringToInsert) throws SQLException {
        byte[] bStringToInsert = stringToInsert.getBytes();
        ByteArrayInputStream baisStringToInsert = new ByteArrayInputStream(bStringToInsert);
        insertPst.setAsciiStream(index, baisStringToInsert, bStringToInsert.length);
    }*/

    /**
     * @param insertPst de prepared statement
     * @param index het index nummer
     * @param stringToInsert de string die als UNICODE stream geïnserteerd moet worden
     * @throws SQLException de sql exceptie als iets fout gaat
     */
    private void insertUnicodeStream(PreparedStatement insertPst, int index, String stringToInsert) throws SQLException {
        Reader reader = new StringReader(stringToInsert);
        insertPst.setCharacterStream(index, reader, stringToInsert.length());
    }



	/**
     * Haalt alle taalvragen op uit de tabel td_tlv_awd en vervangt de plain text velden door de gestripte html versie om het character encoding probleem
     * op te lossen
	 * @throws ParserException
     */
    public void fixTdTlvAwd() throws ParserException{
        //initialiseer de appConf class nodig voor de resources , constants
        new TaalunieInitialization().init();

        // als start en stop id gespecifieerd zijn, voeg toe aan query
        if(startID != -1 && (stopID != -1)){
            getAllTdTlvAwdRows += " WHERE tlv_id >= " + startID + "and tlv_id <= " + stopID ;
        }
        getAllTdTlvAwdRows += " ORDER BY tlv_id ";

        AppLogger.getInstance().info("select query: " + getAllTdTlvAwdRows);
		IDbConnection dbconnection = MyDbConnection.getInstance();
		Connection con = dbconnection.getConnection();
		PreparedStatement pst = null;
		PreparedStatement updatePst = null;
		ResultSet set = null;
		int counter = 0;
		try {
			if (con !=null) {
				pst = con.prepareStatement(getAllTdTlvAwdRows);
				updatePst = con.prepareStatement(updateTdTlvAwdRows);

				set = pst.executeQuery();
				while(set.next()){
				    counter++;
				    int tlvId = set.getInt("id");
				    boolean tlvIdisNull = set.wasNull();

				    fixValue("vraag", "vraagHTML",  1, updatePst, set, tlvId);
				    fixValue("informatie", "informatieHTML",  2, updatePst, set, tlvId);
				    fixValue("antwoord", "antwoordHTML",  3, updatePst, set, tlvId);
				    fixValue("toelichting", "toelichtingHTML",  4, updatePst, set, tlvId);
				    fixValue("bijzonderheid", "bijzonderheidHTML",  5, updatePst, set, tlvId);
				    fixValue("titel", "titelHTML",  6, updatePst, set, tlvId);

		    		String herDb = replaceNull(set.getString("herformulering"));
		    		String herDbHTML = replaceNull(set.getString("herformuleringHTML"));
		    		String herDbStripped = stripHtml(herDbHTML);
		    		AppLogger.getInstance().debug(herDb + " + " + herDbHTML + " = " + herDbStripped);
		    		if(StringUtils.trim(herDb).length() != 0 && StringUtils.trim(herDbStripped).length() == 0){
		    			AppLogger.getInstance().error(herDb + " not fixed (id=" + tlvId + ")");
		    			updatePst.setString(7, herDbStripped);
		    		} else{
		    			updatePst.setString(7, herDbStripped);
		    		}

				    //System.out.println("id= " +tlvId);
				    if(tlvIdisNull){
				        updatePst.setNull(8, Types.INTEGER);
				        System.out.println("wasnull");
				    } else {
				        updatePst.setInt(8, tlvId);
				    }

		    		updatePst.addBatch();
				    //updatePst.executeUpdate();
				    if(counter == 100){
				        counter = 0;
				        int[] is = updatePst.executeBatch();
				        AppLogger.getInstance().error("updated " + is.length + " rows ...");
				    }
				}
				//execute last batch
				if(counter > 0){
				    int[] is = updatePst.executeBatch();
//				    for (int i = 0; i < is.length; i++) {
//						System.out.println("***" + is[i]);
//					}
				    AppLogger.getInstance().error("updated " + is.length + " rows ...");
				}

			} else {
				AppLogger.getInstance().info("Geen connectie !");
			}
		} catch (java.sql.SQLException e) {
		    AppLogger.getInstance().fatal("\n\n------\nsql state: "
					+ e.getSQLState()
					+ "\nerror code: "
					+ e.getErrorCode()
					+ "\n-----\n\n");
			e.printStackTrace(System.err);

		} finally {
			try {
				if (con != null) {
					if (pst != null) {pst.close();}
					if (set != null) {set.close();}
					if (updatePst != null) {updatePst.close();}
					dbconnection.releaseConnection(con);
				}

			} catch (java.sql.SQLException sqle) {
				sqle.printStackTrace(System.err);
				AppLogger.getInstance().fatal("\n\n------\nsql state: "
				        							+ sqle.getSQLState()
				        							+ "\nerror code: "
				        							+ sqle.getErrorCode()
				        							+ "\n-----\n\n");
			}
		}



    }

	/**
	 * @param plainColName colname
	 * @param htmlColName colname for html value
	 * @param index index for preparedstatement
	 * @param updatePst
	 * @param set
	 * @param tlvId
	 * @return
	 * @throws SQLException
	 * @throws ParserException
	 */
	private void fixValue(String plainColName, String htmlColName, int index, PreparedStatement updatePst, ResultSet set, int tlvId) throws SQLException, ParserException {
		String value = replaceNull(set.getString(plainColName));
		String valueHTML = replaceNull(set.getString(htmlColName));
		String valueHTMLStripped = stripHtml(valueHTML);
		AppLogger.getInstance().debug(value + " + " + valueHTML + " = " + valueHTMLStripped);
		if(StringUtils.trim(value).length() != 0 && StringUtils.trim(valueHTMLStripped).length() == 0){
			AppLogger.getInstance().error(value + " not fixed (id=" + tlvId + "): '"
					+ value + "' -> '" + valueHTMLStripped + "'");
			insertUnicodeStream(updatePst, index, value);
		} else{
			insertUnicodeStream(updatePst, index, valueHTMLStripped);
		}
	}

    /**
     * strips html
     *
     * @param stringToStrip
     * @return
     * @throws ParserException
     */
    private String stripHtml(String stringToStrip) throws ParserException {
		parser.setInputHTML(stringToStrip);
		TextExtractingVisitor visitor = new TextExtractingVisitor();
		parser.visitAllNodesWith(visitor);
        return visitor.getExtractedText();
	}



	/**
     * @param input String die naar een lege string wordt geconverteerd als die null is
     * @return de originele string of een lege string
     */
    private String replaceNull(String input){
        String returnString = input;
        if(input == null){
            returnString = "";
        }
        return returnString;
    }

    /**
     * @param args argumenten moeten ofwel leeg zijn of wel 2 integers die bepalen wat de eerste en laatste te verwerken id is voor td_tlv_awd
     * @throws ParserException
     */
    public static void main(String[] args) throws ParserException {

        System.out.println("new version");

        int startID = -1;
        int stopID = -1;
        if(args.length == 2){
            startID = Integer.parseInt(args[0]);
            stopID = Integer.parseInt(args[1]);
        } else if(args.length != 0){
            System.err.println("start op zonder parameters om de diakritische tekens de complete tabel td_tlv_awd te corrigeren");
            System.err.println("start op met startID stopID om enkel die range voor tabel td_tlv_awd te corrigeren");
            System.exit(1);
        }


        FixDiacriticChars fixDiacriticChars = new FixDiacriticChars(startID, stopID);

        AppLogger.getInstance().info("migrate td_tlv_awd");
        if(startID != -1){
            AppLogger.getInstance().info("first ID=" + startID + " and last Id=" + stopID);
        }
        fixDiacriticChars.fixTdTlvAwd();
        AppLogger.getInstance().info("done migrating td_tlv_awd");

        if(startID != -1){
            AppLogger.getInstance().info("first ID=" + startID + " and last Id=" + stopID);
            System.exit(0);
        }
    }
}
