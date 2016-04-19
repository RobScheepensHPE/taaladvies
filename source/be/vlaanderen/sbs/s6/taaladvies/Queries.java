package be.vlaanderen.sbs.s6.taaladvies;


public class Queries {
    public static String ALL_MEDIA = "SELECT mdm_id AS id, mdm_ds AS omschrijving, atf_fg AS actief FROM tp_mdm ORDER BY mdm_ds";
    public static String ALL_MEDIA_ACTIEF = "SELECT mdm_id AS id, mdm_ds AS omschrijving, atf_fg AS actief FROM tp_mdm WHERE atf_fg = 't' ORDER BY mdm_ds";
    public static String MEDIUM_BY_PK = "SELECT mdm_id AS id, mdm_ds AS omschrijving, atf_fg AS actief FROM tp_mdm WHERE mdm_id = ?";
    public static String INSERT_MEDIUM = "INSERT INTO tp_mdm(mdm_ds, atf_fg) VALUES(?,?)";
    public static String UPDATE_MEDIUM = "UPDATE tp_mdm SET mdm_ds = ?, atf_fg = ? WHERE mdm_id = ?";
    public static String ID_MEDIUM_EMAIL = "SELECT mdm_id AS id, mdm_ds AS omschrijving, atf_fg AS actief FROM tp_mdm WHERE mdm_ds = 'E-mail' AND atf_fg = 't' ORDER BY mdm_ds";

    public static String ALL_GEBRUIKERS = "SELECT tav_id AS id, dmn_id AS domeinId, tav_usr_id AS login, gst_fg AS geslacht, atf_fg AS actief, tav_nm AS naam, tav_vrn_nm AS voornaam, tav_ea AS email FROM td_tav ORDER BY tav_nm";
    public static String GEBRUIKER_BY_PK = "SELECT tav_id AS id, dmn_id AS domeinId, tav_usr_id AS login, gst_fg AS geslacht, atf_fg AS actief, tav_nm AS naam, tav_vrn_nm AS voornaam, tav_ea AS email FROM td_tav WHERE tav_id = ?";
    public static String GEBRUIKER_BY_LOGIN = "SELECT tav_id AS id, dmn_id AS domeinId, tav_usr_id AS login, gst_fg AS geslacht, atf_fg AS actief, tav_nm AS naam, tav_vrn_nm AS voornaam, tav_ea AS email FROM td_tav WHERE tav_usr_id = ?";
    public static String INSERT_GEBRUIKER = "INSERT INTO td_tav(tav_usr_id, tav_nm, tav_vrn_nm, tav_ea, gst_fg, dmn_id, atf_fg) VALUES(?,?,?,?,?,?,?)";
    public static String UPDATE_GEBRUIKER = "UPDATE td_tav SET tav_usr_id = ?, tav_nm = ?, tav_vrn_nm = ?, tav_ea = ?, gst_fg = ?, dmn_id = ?, atf_fg = ? WHERE tav_id = ?";

    public static String DOELGROEPEN_BY_PARENT = "SELECT dgp_id AS id, dgp_spc_tx AS omschrijving, atf_fg AS actief, dgp_alg_id AS parentId FROM tp_dgp WHERE dgp_alg_id = ? ORDER BY dgp_spc_tx";
    public static String DOELGROEPEN_BY_PARENT_ACTIEF = "SELECT dgp_id AS id, dgp_spc_tx AS omschrijving, atf_fg AS actief, dgp_alg_id AS parentId FROM tp_dgp WHERE dgp_alg_id = ? AND atf_fg = 't' ORDER BY dgp_spc_tx";
    public static String ALL_DOELGROEPEN = "SELECT dgp_id AS id, dgp_spc_tx AS omschrijving, atf_fg AS actief, dgp_alg_id AS parentId FROM tp_dgp ORDER BY dgp_spc_tx";
    public static String ALL_DOELGROEPEN_ACTIEF = "SELECT dgp_id AS id, dgp_spc_tx AS omschrijving, atf_fg AS actief, dgp_alg_id AS parentId FROM tp_dgp WHERE atf_fg = 't' ORDER BY dgp_spc_tx";
    public static String DOELGROEPEN_BY_DOMEIN = "SELECT a.dgp_id AS id, a.dgp_spc_tx AS omschrijving, a.atf_fg AS actief, a.dgp_alg_id AS parentId FROM tp_dgp a, tp_dgp_alg b WHERE a.dgp_alg_id = b.dgp_alg_id AND b.dmn_id = ? ORDER BY dgp_spc_tx";
    public static String DOELGROEPEN_BY_DOMEIN_ACTIEF = "SELECT a.dgp_id AS id, a.dgp_spc_tx AS omschrijving, a.atf_fg AS actief, a.dgp_alg_id AS parentId FROM tp_dgp a, tp_dgp_alg b WHERE a.dgp_alg_id = b.dgp_alg_id AND b.dmn_id = ? AND a.atf_fg = 't' ORDER BY dgp_spc_tx";
    public static String DOELGROEP_BY_PK = "SELECT dgp_id AS id, dgp_spc_tx AS omschrijving, atf_fg AS actief, dgp_alg_id AS parentId FROM tp_dgp WHERE dgp_id = ?";
    public static String INSERT_DOELGROEP = "INSERT INTO tp_dgp(dgp_spc_tx, atf_fg, dgp_alg_id) VALUES(?,?,?)";
    public static String UPDATE_DOELGROEP = "UPDATE tp_dgp SET dgp_spc_tx = ?, atf_fg = ?, dgp_alg_id = ? WHERE dgp_id = ?";

    public static String DOELGROEPEN_ALGEMEEN_BY_PARENT = "SELECT dgp_alg_id AS id, dgp_alg_ds AS omschrijving, atf_fg AS actief, dmn_id AS parentId FROM tp_dgp_alg WHERE dmn_id = ? ORDER BY dgp_alg_ds";
    public static String DOELGROEPEN_ALGEMEEN_BY_PARENT_ACTIEF = "SELECT dgp_alg_id AS id, dgp_alg_ds AS omschrijving, atf_fg AS actief, dmn_id AS parentId FROM tp_dgp_alg WHERE dmn_id = ? AND atf_fg = 't' ORDER BY dgp_alg_ds";
    public static String ALL_DOELGROEPEN_ALGEMEEN = "SELECT dgp_alg_id AS id, dgp_alg_ds AS omschrijving, atf_fg AS actief, dmn_id AS parentId FROM tp_dgp_alg ORDER BY dgp_alg_ds";
    public static String ALL_DOELGROEPEN_ALGEMEEN_ACTIEF = "SELECT dgp_alg_id AS id, dgp_alg_ds AS omschrijving, atf_fg AS actief, dmn_id AS parentId FROM tp_dgp_alg WHERE atf_fg = 't' ORDER BY dgp_alg_ds";
    public static String DOELGROEP_ALGEMEEN_BY_PK = "SELECT dgp_alg_id AS id, dgp_alg_ds AS omschrijving, atf_fg AS actief, dmn_id AS parentId FROM tp_dgp_alg WHERE dgp_alg_id = ?";
    public static String INSERT_DOELGROEP_ALGEMEEN = "INSERT INTO tp_dgp_alg(dgp_alg_ds, atf_fg, dmn_id) VALUES(?,?,?)";
    public static String UPDATE_DOELGROEP_ALGEMEEN = "UPDATE tp_dgp_alg SET dgp_alg_ds = ?, atf_fg = ?, dmn_id = ? WHERE dgp_alg_id = ?";

    public static String ALL_NASLAGWERKEN = "SELECT nsw_id AS id, nsw_ttl_ds AS omschrijving, atf_fg AS actief, nsw_ttl_htm_db AS omschrijvingHTML, nsw_lng_ttl_db AS titelVoluit, nsw_ttl_lng_htm_db AS titelVoluitHTML, ctg_bgf_id AS parentId FROM tp_nsw ORDER BY nsw_ttl_ds";
    public static String ALL_NASLAGWERKEN_ACTIEF = "SELECT nsw_id AS id, nsw_ttl_ds AS omschrijving, atf_fg AS actief, nsw_ttl_htm_db AS omschrijvingHTML, nsw_lng_ttl_db AS titelVoluit, nsw_ttl_lng_htm_db AS titelVoluitHTML, ctg_bgf_id AS parentId FROM tp_nsw WHERE atf_fg = 't' ORDER BY nsw_ttl_ds";
    public static String NASLAGWERK_BY_PK = "SELECT nsw_id AS id, nsw_ttl_ds AS omschrijving, atf_fg AS actief, nsw_ttl_htm_db AS omschrijvingHTML, nsw_lng_ttl_db AS titelVoluit, nsw_ttl_lng_htm_db AS titelVoluitHTML, ctg_bgf_id AS parentId FROM tp_nsw WHERE nsw_id = ?";
    public static String INSERT_NASLAGWERK = "INSERT INTO tp_nsw(nsw_ttl_ds, atf_fg, ctg_bgf_id, nsw_lng_ttl_db, nsw_ttl_lng_htm_db) VALUES(?,?,?,?,?)";
    public static String UPDATE_NASLAGWERK = "UPDATE tp_nsw SET nsw_ttl_ds = ?, atf_fg = ?, ctg_bgf_id = ?, nsw_lng_ttl_db = ?, nsw_ttl_lng_htm_db = ? WHERE nsw_id = ?";
    public static String NASLAGWERKEN_BY_PARENT = "SELECT nsw_id AS id, nsw_ttl_ds AS omschrijving, atf_fg AS actief, nsw_ttl_htm_db AS omschrijvingHTML, nsw_lng_ttl_db AS titelVoluit, nsw_ttl_lng_htm_db AS titelVoluitHTML, ctg_bgf_id AS parentId FROM tp_nsw WHERE ctg_bgf_id = ? ORDER BY nsw_ttl_ds";
    public static String NASLAGWERKEN_BY_PARENT_ACTIEF = "SELECT nsw_id AS id, nsw_ttl_ds AS omschrijving, atf_fg AS actief, nsw_ttl_htm_db AS omschrijvingHTML, nsw_lng_ttl_db AS titelVoluit, nsw_ttl_lng_htm_db AS titelVoluitHTML, ctg_bgf_id AS parentId FROM tp_nsw WHERE ctg_bgf_id = ? AND atf_fg = 't' ORDER BY nsw_ttl_ds";

    public static String ALL_BIBLIOGRAFIEN = "SELECT ctg_bgf_id AS id, hfd_ds AS omschrijving, atf_fg AS actief FROM tp_ctg_bgf ORDER BY hfd_ds";
    public static String ALL_BIBLIOGRAFIEN_ACTIEF = "SELECT ctg_bgf_id AS id, hfd_ds AS omschrijving, atf_fg AS actief FROM tp_ctg_bgf WHERE atf_fg = 't' ORDER BY hfd_ds";
    public static String BIBLIOGRAFIE_BY_PK = "SELECT ctg_bgf_id AS id, hfd_ds AS omschrijving, atf_fg AS actief FROM tp_ctg_bgf WHERE ctg_bgf_id = ?";
    public static String INSERT_BIBLIOGRAFIE = "INSERT INTO tp_ctg_bgf(hfd_ds, atf_fg) VALUES(?,?)";
    public static String UPDATE_BIBLIOGRAFIE = "UPDATE tp_ctg_bgf SET hfd_ds = ?, atf_fg = ? WHERE ctg_bgf_id = ?";

    public static String ALL_SJABLONEN = "SELECT sbn_id AS id, sbn_ds AS omschrijving, atf_fg AS actief, ihd_tx AS inhoud, ihd_htm_db AS inhoudHTML, htk_tx AS handtekening, htk_htm_db AS handtekeningHTML, ond_rgl_tx AS onderwerp, slg_tx AS slotgroet, slg_htm_db AS slotgroetHTML FROM tp_sbn ORDER BY sbn_ds";
    public static String ALL_SJABLONEN_ACTIEF = "SELECT sbn_id AS id, sbn_ds AS omschrijving, atf_fg AS actief, ihd_tx AS inhoud, ihd_htm_db AS inhoudHTML, htk_tx AS handtekening, htk_htm_db AS handtekeningHTML, ond_rgl_tx AS onderwerp, slg_tx AS slotgroet, slg_htm_db AS slotgroetHTML FROM tp_sbn WHERE atf_fg = 't' ORDER BY sbn_ds";
    public static String SJABLOON_BY_PK = "SELECT sbn_id AS id, sbn_ds AS omschrijving, atf_fg AS actief, ihd_tx AS inhoud, ihd_htm_db AS inhoudHTML, htk_tx AS handtekening, htk_htm_db AS handtekeningHTML, ond_rgl_tx AS onderwerp, slg_tx AS slotgroet, slg_htm_db AS slotgroetHTML FROM tp_sbn WHERE sbn_id = ?";
    public static String INSERT_SJABLOON = "INSERT INTO tp_sbn(sbn_ds, atf_fg, ond_rgl_tx, ihd_tx, ihd_htm_db, htk_tx, htk_htm_db, slg_tx, slg_htm_db) VALUES(?,?,?,?,?,?,?,?,?)";
    public static String UPDATE_SJABLOON = "UPDATE tp_sbn SET sbn_ds = ?, atf_fg = ?, ond_rgl_tx = ?, ihd_tx = ?, ihd_htm_db = ?, htk_tx = ?, htk_htm_db = ?, slg_tx = ?, slg_htm_db = ? WHERE sbn_id = ?";

    public static String CATEGORIEN_BY_TAALVRAAG = "SELECT a.ctr_id AS id, a.ctr_ds AS omschrijving, a.atf_fg AS actief, a.odr_ctr_id AS parentId, a.ctr_nmr_tx AS nummer FROM tr_ctr a, tr_tlv_awd_ctr b WHERE a.ctr_id = b.ctr_id AND b.tlv_id = ?";
    public static String CATEGORIEN_BY_TEKST = "SELECT a.ctr_id AS id, a.ctr_ds AS omschrijving, a.atf_fg AS actief, a.odr_ctr_id AS parentId, a.ctr_nmr_tx AS nummer FROM tr_ctr a, tr_tks_ctr b WHERE a.ctr_id = b.ctr_id AND b.tks_id = ?";
    public static String CATEGORIEN_BY_PARENT = "SELECT ctr_id AS id, ctr_ds AS omschrijving, atf_fg AS actief, odr_ctr_id AS parentId, ctr_nmr_tx AS nummer, ctr_nmr_nr FROM tr_ctr WHERE odr_ctr_id = ? ORDER BY ctr_nmr_nr";
    public static String CATEGORIEN_BY_PARENT_NULL = "SELECT ctr_id AS id, ctr_ds AS omschrijving, atf_fg AS actief, odr_ctr_id AS parentId, ctr_nmr_tx AS nummer, ctr_nmr_nr FROM tr_ctr WHERE odr_ctr_id IS NULL ORDER BY ctr_nmr_nr";

    public static String CATEGORIEN_BY_HIERARCHIE = "SELECT ctr_id AS id, ctr_nmr_tx AS nummer FROM tr_ctr WHERE ctr_nmr_tx LIKE ?";

    public static String CATEGORIEN_BY_PARENT_ACTIEF = "SELECT ctr_id AS id, ctr_ds AS omschrijving, atf_fg AS actief, odr_ctr_id AS parentId, ctr_nmr_tx AS nummer, ctr_nmr_nr FROM tr_ctr WHERE odr_ctr_id = ? AND atf_fg = 't' ORDER BY ctr_nmr_nr";
    public static String CATEGORIEN_BY_PARENT_ACTIEF_NULL = "SELECT ctr_id AS id, ctr_ds AS omschrijving, atf_fg AS actief, odr_ctr_id AS parentId, ctr_nmr_tx AS nummer, ctr_nmr_nr FROM tr_ctr WHERE odr_ctr_id IS NULL AND atf_fg = 't' ORDER BY ctr_nmr_nr";
    public static String ALL_CATEGORIEN = "SELECT ctr_id AS id, ctr_ds AS omschrijving, atf_fg AS actief, odr_ctr_id AS parentId, ctr_nmr_tx AS nummer FROM tr_ctr";
    public static String ALL_CATEGORIEN_ACTIEF = "SELECT ctr_id AS id, ctr_ds AS omschrijving, atf_fg AS actief, odr_ctr_id AS parentId, ctr_nmr_tx AS nummer FROM tr_ctr WHERE atf_fg = 't'";
    public static String CATEGORIE_BY_PK = "SELECT ctr_id AS id, ctr_ds AS omschrijving, atf_fg AS actief, odr_ctr_id AS parentId, ctr_nmr_tx AS nummer FROM tr_ctr WHERE ctr_id = ?";
    public static String INSERT_CATEGORIE = "INSERT INTO tr_ctr(ctr_ds, atf_fg, odr_ctr_id, ctr_nmr_tx, ctr_nmr_nr) VALUES(?,?,?,?,?)";
    public static String INSERT_CATEGORIE_FOR_TAALVRAAG = "INSERT INTO tr_tlv_awd_ctr(tlv_id, ctr_id) VALUES(?,?)";
    public static String INSERT_CATEGORIE_FOR_TEKST = "INSERT INTO tr_tks_ctr(tks_id, ctr_id) VALUES(?,?)";
    public static String UPDATE_CATEGORIE = "UPDATE tr_ctr SET ctr_ds = ?, atf_fg = ?, odr_ctr_id = ?, ctr_nmr_tx = ?, ctr_nmr_nr = ? WHERE ctr_id = ?";

    public static String UPDATE_CATEGORIE_BY_PARENTNUMMER = "UPDATE tr_ctr SET ctr_nmr_tx = ? , ctr_nmr_nr = ? WHERE ctr_id = ?";

    public static String DELETE_CATEGORIE_FOR_TAALVRAAG = "DELETE FROM tr_tlv_awd_ctr WHERE tlv_id = ? AND ctr_id = ?";
    public static String DELETE_CATEGORIE_FOR_TEKST = "DELETE FROM tr_tks_ctr WHERE tks_id = ? AND ctr_id = ?";
    public static String DELETE_ALL_CATEGORIEN_FOR_TAALVRAAG = "DELETE FROM tr_tlv_awd_ctr WHERE tlv_id = ?";
    public static String DELETE_ALL_CATEGORIEN_FOR_TEKST = "DELETE FROM tr_tks_ctr WHERE tks_id = ?";

    public static String ALL_HERKOMSTEN = "SELECT hkm_id AS id, hkm_nm AS omschrijving, atf_fg AS actief FROM tp_hkm ORDER BY hkm_nm";
    public static String ALL_HERKOMSTEN_ACTIEF = "SELECT hkm_id AS id, hkm_nm AS omschrijving, atf_fg AS actief FROM tp_hkm WHERE atf_fg = 't' ORDER BY hkm_nm";
    public static String HERKOMST_BY_PK = "SELECT hkm_id AS id, hkm_nm AS omschrijving, atf_fg AS actief FROM tp_hkm WHERE hkm_id = ?";
    public static String HERKOMST_BY_NAME = "SELECT hkm_id AS id, hkm_nm AS omschrijving, atf_fg AS actief FROM tp_hkm WHERE hkm_nm = ?";
    public static String INSERT_HERKOMST = "INSERT INTO tp_hkm(hkm_nm, atf_fg) VALUES(?,?)";
    public static String UPDATE_HERKOMST = "UPDATE tp_hkm SET hkm_nm = ?, atf_fg = ? WHERE hkm_id = ?";

    public static String ALL_RELEVANTIES = "SELECT rvt_id AS id, rvt_ds AS omschrijving, atf_fg AS actief FROM tp_rvt ORDER BY rvt_ds";
    public static String ALL_RELEVANTIES_ACTIEF = "SELECT rvt_id AS id, rvt_ds AS omschrijving, atf_fg AS actief FROM tp_rvt WHERE atf_fg = 't' ORDER BY rvt_ds";
    public static String RELEVANTIE_BY_PK = "SELECT rvt_id AS id, rvt_ds AS omschrijving, atf_fg AS actief FROM tp_rvt WHERE rvt_id = ?";
    public static String INSERT_RELEVANTIE = "INSERT INTO tp_rvt(rvt_ds, atf_fg) VALUES(?,?)";
    public static String UPDATE_RELEVANTIE = "UPDATE tp_rvt SET rvt_ds = ?, atf_fg = ? WHERE rvt_id = ?";

    public static String ALL_ZOEKOMGEVINGEN = "SELECT zgv_id AS id, zgv_ds AS omschrijving, atf_fg AS actief FROM tp_zgv ORDER BY zgv_ds";
    public static String ALL_ZOEKOMGEVINGEN_ACTIEF = "SELECT zgv_id AS id, zgv_ds AS omschrijving, atf_fg AS actief FROM tp_zgv WHERE atf_fg = 't' ORDER BY zgv_ds";
    public static String ZOEKOMGEVING_BY_PK = "SELECT zgv_id AS id, zgv_ds AS omschrijving, atf_fg AS actief FROM tp_zgv WHERE zgv_id = ?";
    public static String INSERT_ZOEKOMGEVING = "INSERT INTO tp_zgv(zgv_ds, atf_fg) VALUES(?,?)";
    public static String UPDATE_ZOEKOMGEVING = "UPDATE tp_zgv SET zgv_ds = ?, atf_fg = ? WHERE zgv_id = ?";

    public static String ALL_STATUSSEN = "SELECT orp_sts_cd AS id, orp_sts_ds AS omschrijving, atf_fg AS actief FROM tp_orp_sts ORDER BY orp_sts_ds";
    public static String ALL_STATUSSEN_ACTIEF = "SELECT orp_sts_cd AS id, orp_sts_ds AS omschrijving, atf_fg AS actief FROM tp_orp_sts WHERE atf_fg = 't' ORDER BY orp_sts_ds";
    public static String STATUS_BY_PK = "SELECT orp_sts_cd AS id, orp_sts_ds AS omschrijving, atf_fg AS actief FROM tp_orp_sts WHERE orp_sts_cd = ?";
    public static String INSERT_STATUS = "INSERT INTO tp_orp_sts(orp_sts_cd, orp_sts_ds, atf_fg) VALUES(?,?,?)";
    public static String UPDATE_STATUS = "UPDATE tp_orp_sts SET orp_sts_ds = ?, atf_fg = ? WHERE orp_sts_cd = ?";
    public static String MAX_STATUS = "select max(orp_sts_cd) as max from tp_orp_sts";

    public static String ALL_TOEGANGSRECHTEN = "SELECT tgr_id AS id, tgr_ds AS omschrijving, tgr_oms_tx AS beschrijving FROM tp_tgr";
    public static String TOEGANGSRECHT_BY_PK = "SELECT tgr_id AS id, tgr_ds AS omschrijving, tgr_oms_tx AS beschrijving FROM tp_tgr WHERE tgr_id = ?";
    public static String INSERT_TOEGANGSRECHT = "INSERT INTO tp_tgr(tgr_ds, tgr_oms_tx) VALUES(?,?)";
    public static String UPDATE_TOEGANGSRECHT = "UPDATE tp_tgr SET tgr_ds = ?, tgr_oms_tx = ? WHERE tgr_id = ?";

    public static String ALL_DOMEINEN = "SELECT dmn_id AS id, dmn_nm AS omschrijving FROM tp_dmn ORDER BY dmn_nm";
    public static String DOMEIN_BY_PK = "SELECT dmn_id AS id, dmn_nm AS omschrijving FROM tp_dmn WHERE dmn_id = ?";
    public static String ID_DOMEIN_EXTERN = "SELECT dmn_id AS id, dmn_nm AS omschrijving FROM tp_dmn WHERE dmn_nm = \"Extern\"";
    public static String INSERT_DOMEIN = "INSERT INTO tp_dmn(dmn_nm) VALUES(?)";
    public static String UPDATE_DOMEIN = "UPDATE tp_dmn SET dmn_nm = ? WHERE dmn_id = ?";

    public static String ZOEK_GEMEENTE = "SELECT gmn_id AS id, gmn_ds AS omschrijving, pst_cd AS post, lnd_cd AS parentId FROM tp_gmn WHERE UPPER(gmn_ds) LIKE UPPER(?) AND pst_cd LIKE ? AND lnd_cd = ? ORDER BY gmn_ds";
    public static String GEMEENTEN_BY_PARENT = "SELECT gmn_id AS id, gmn_ds AS omschrijving, pst_cd AS post, lnd_cd AS parentId FROM tp_gmn WHERE lnd_cd = ? ORDER BY gmn_ds";
    public static String ALL_GEMEENTEN = "SELECT gmn_id AS id, gmn_ds AS omschrijving, pst_cd AS post, lnd_cd AS parentId FROM tp_gmn ORDER BY gmn_ds";
    public static String GEMEENTE_BY_PK = "SELECT gmn_id AS id, gmn_ds AS omschrijving, pst_cd AS post, lnd_cd AS parentId FROM tp_gmn WHERE gmn_id = ?";
    public static String INSERT_GEMEENTE = "INSERT INTO tp_gmn(gmn_ds, pst_cd, lnd_cd) VALUES(?,?,?)";
    public static String UPDATE_GEMEENTE = "UPDATE tp_gmn SET gmn_ds = ?, pst_cd = ?, lnd_cd = ? WHERE gmn_id = ?";

    public static String ALL_LANDEN = "SELECT lnd_cd AS id, lnd_nm AS omschrijving FROM tp_lnd ORDER BY lnd_nm";
    public static String LAND_BY_PK = "SELECT lnd_cd AS id, lnd_nm AS omschrijving FROM tp_lnd WHERE lnd_cd = ?";
    public static String INSERT_LAND = "INSERT INTO tp_lnd(lnd_nm) VALUES(?)";
    public static String UPDATE_LAND = "UPDATE tp_lnd SET lnd_nm = ? WHERE lnd_cd = ?";


    public static String TOEGANGSRECHTEN_BY_GEBRUIKER = "SELECT a.tgr_id AS id, a.tgr_ds AS omschrijving, a.tgr_oms_tx AS beschrijving FROM tp_tgr a, tr_tav_tgr b WHERE a.tgr_id = b.tgr_id AND b.tav_id = ?";
    public static String INSERT_TOEGANGSRECHTEN_BY_GEBRUIKER = "INSERT INTO tr_tav_tgr(tav_id, tgr_id) VALUES(?,?)";
    public static String DELETE_TOEGANGSRECHTEN_BY_GEBRUIKER = "DELETE FROM tr_tav_tgr WHERE tav_id = ?";

    public static String TAALVRAGEN_AFGEHANDELD = "SELECT tlv_id AS id, adv_vzk_id AS oproepId, tlv_ds AS vraag, tlv_spc_htm_db AS vraagHTML, tlv_inf_db AS informatie, tlv_inf_htm_db AS informatieHTML, tlv_vgn_nr AS volgnummer, tlv_vrl_agh_fg AS afgehandeld, awd_krt_db AS antwoord, awd_krt_htm_db AS antwoordHTML, awd_tol_db AS toelichting, awd_tol_htm_db AS toelichtingHTML, awd_bjz_db AS bijzonderheid, awd_bjz_htm_db AS bijzonderheidHTML, ttl_db AS titel, ttl_htm_db AS titelHTML, rvt_id AS relevantieId, tlv_her_db AS herformulering, tlv_her_htm_db AS herformuleringHTML FROM td_tlv_awd" /* where tlv_vrl_agh_fg = 't' */;

    public static String TAALVRAGEN_SEARCH_FIXED_COUNT = "SELECT MAX(B.tlv_id) AS max , count(B.tlv_id) AS count FROM td_opr A, td_tlv_awd B, tr_drb X WHERE A.agh_fg = 't' AND B.adv_vzk_id = A.orp_id  AND (A.drb_id = X.drb_id ) ";

    /*BUGFIX    2006-10-30*/
    /*Result: correct display of special charachters in title*/
    public static String TAALVRAGEN_SEARCH_FIXED_PREVIOUS = " B.tlv_id AS id, B.adv_vzk_id AS oproepId, B.tlv_ds AS vraag, B.ttl_db AS titel, B.ttl_htm_db as titelHTML, A.orp_dt AS datum FROM td_opr A, td_tlv_awd B, tr_drb X WHERE A.agh_fg = 't' AND B.adv_vzk_id = A.orp_id AND B.tlv_id <= ? AND (A.orp_dt > ? OR (A.orp_dt = ? and B.tlv_id < ?))  AND (A.drb_id = X.drb_id ) ";

    /*BUGFIX    2006-10-30*/
    /*Result: correct display of special charachters in title*/
    public static String TAALVRAGEN_SEARCH_FIXED_NEXT = " B.tlv_id AS id, B.adv_vzk_id AS oproepId, B.tlv_ds AS vraag, B.ttl_db AS titel, B.ttl_htm_db as titelHTML, A.orp_dt AS datum FROM td_opr A, td_tlv_awd B, tr_drb X WHERE A.agh_fg = 't' AND B.adv_vzk_id = A.orp_id AND B.tlv_id <= ?  AND (A.drb_id = X.drb_id ) ";

    // SEARCH SINGLE CATEGORY FOR TAALVRAAG
    public static String TAALVRAGEN_CATEGORIESEARCH_FIXED_COUNT = "SELECT MAX(B.tlv_id) AS max, count(B.tlv_id) AS count FROM TD_OPR A, TD_TLV_AWD B, TR_TLV_AWD_CTR C, TR_CTR D, TR_DRB X WHERE agh_fg = 't' AND B.adv_vzk_id = A.orp_id AND B.TLV_ID = C.TLV_ID AND C.CTR_ID = D.CTR_ID AND D.CTR_NMR_TX LIKE ?  AND (A.drb_id = X.drb_id ) ";

    /*BUGFIX    2006-10-30*/
    /*Result: correct display of special charachters in title*/
    public static String TAALVRAGEN_CATEGORIESEARCH_FIXED_PREVIOUS = " B.tlv_id AS id, B.adv_vzk_id AS oproepId, B.tlv_ds AS vraag,B.ttl_db AS titel,B.ttl_htm_db as titelHTML,  A.orp_dt AS datum FROM TD_OPR A, TD_TLV_AWD B, TR_TLV_AWD_CTR C, TR_CTR D, TR_DRB X WHERE agh_fg = 't' AND B.adv_vzk_id = A.orp_id AND B.TLV_ID = C.TLV_ID AND C.CTR_ID = D.CTR_ID AND B.tlv_id <= ? AND (A.orp_dt > ? OR (A.orp_dt = ? and B.tlv_id < ?))  AND (A.drb_id = X.drb_id ) ";

    /*BUGFIX    2006-10-30*/
    /*Result: correct display of special charachters in title*/
    public static String TAALVRAGEN_CATEGORIESEARCH_FIXED_NEXT = " B.tlv_id AS id, B.adv_vzk_id AS oproepId, B.tlv_ds AS vraag,B.ttl_db AS titel, B.ttl_htm_db as titelHTML, A.orp_dt AS datum FROM TD_OPR A, TD_TLV_AWD B, TR_TLV_AWD_CTR C, TR_CTR D, TR_DRB X WHERE agh_fg = 't' AND B.adv_vzk_id = A.orp_id AND B.TLV_ID = C.TLV_ID AND C.CTR_ID = D.CTR_ID AND B.tlv_id <= ?  AND (A.drb_id = X.drb_id ) ";

    //public static String TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_NEXT		= " B.tlv_id AS id, B.adv_vzk_id AS oproepId, B.tlv_ds AS vraag,B.ttl_db AS titel, A.orp_dt AS datum FROM TD_OPR A, TD_TLV_AWD B, TR_TLV_AWD_CTR C, TR_CTR D, TR_TLV_AWD_CTR E, TR_CTR F WHERE agh_fg = 't' AND B.adv_vzk_id = A.orp_id AND B.TLV_ID = C.TLV_ID AND C.TLV_ID = E.TLV_ID AND C.CTR_ID != E.CTR_ID AND C.CTR_ID = D.CTR_ID AND E.CTR_ID = F.CTR_ID AND B.tlv_id <= ? ";

    // SEARCH MULTIPLE CATEGORIES FOR TAALVRAAG
    //public static String TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_COUNT 	=
//	"SELECT MAX(B.tlv_id) AS max , count(DISTINCT B.tlv_id) AS count " +
//	"FROM TD_OPR A, TD_TLV_AWD B, TR_TLV_AWD_CTR C, TR_CTR D, TR_TLV_AWD_CTR E, TR_CTR F " +
//	"WHERE agh_fg = 't' AND B.adv_vzk_id = A.orp_id AND B.TLV_ID = C.TLV_ID " +
    //	"AND C.TLV_ID = E.TLV_ID AND C.CTR_ID != E.CTR_ID AND C.CTR_ID = D.CTR_ID " +
    //	"AND D.CTR_NMR_TX LIKE ? AND E.CTR_ID = F.CTR_ID AND F.CTR_NMR_TX LIKE ? ";
    public static String TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_COUNT_SELECT = "SELECT MAX(B.tlv_id) AS max , count(B.tlv_id) AS count ";

    public static String TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_COUNT_FROM = " FROM TD_OPR A, TD_TLV_AWD B, TR_DRB X";
//																			", TR_TLV_AWD_CTR C, TR_CTR D" +
//																			", TR_TLV_AWD_CTR E, TR_CTR F ";

    public static String TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_COUNT_WHERE = " WHERE agh_fg = 't' AND B.adv_vzk_id = A.orp_id " +
            " AND (A.drb_id = X.drb_id ) ";
//																			"AND B.TLV_ID = C.TLV_ID " +
//																			"AND B.TLV_ID = E.TLV_ID " ;
    //"AND C.CTR_ID != E.CTR_ID " +
//																			"AND C.CTR_ID = D.CTR_ID " +
//																			"AND D.CTR_NMR_TX LIKE ? " +
//																			"AND E.CTR_ID = F.CTR_ID " +
    //																			"AND F.CTR_NMR_TX LIKE ? ";
    //	public static String TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_PREVIOUS	= " B.tlv_id AS id, B.adv_vzk_id AS oproepId, B.tlv_ds AS vraag,B.ttl_db AS titel, A.orp_dt AS datum FROM TD_OPR A, TD_TLV_AWD B, TR_TLV_AWD_CTR C, TR_CTR D, TR_TLV_AWD_CTR E, TR_CTR F WHERE agh_fg = 't' AND B.adv_vzk_id = A.orp_id AND B.TLV_ID = C.TLV_ID AND C.TLV_ID = E.TLV_ID AND C.CTR_ID != E.CTR_ID AND C.CTR_ID = D.CTR_ID AND E.CTR_ID = F.CTR_ID AND B.tlv_id <= ? AND (A.orp_dt > ? OR (A.orp_dt = ? and B.tlv_id < ?)) ";


    /*BUGFIX    2006-10-30*/
    /*Result: correct display of special charachters in title*/
    public static String TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_PREVIOUS_SELECT =
            " B.tlv_id AS id, B.adv_vzk_id AS oproepId, B.tlv_ds AS vraag,B.ttl_db AS titel, B.ttl_htm_db as titelHTML, A.orp_dt AS datum ";

    public static String TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_PREVIOUS_FROM =
            " FROM TD_OPR A, TD_TLV_AWD B, TR_DRB X ";

    public static String TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_PREVIOUS_WHERE =
            " WHERE agh_fg = 't' AND B.adv_vzk_id = A.orp_id " +
                    " AND (A.drb_id = X.drb_id )  " +
//		" AND B.TLV_ID = C.TLV_ID AND B.TLV_ID = E.TLV_ID " +
//		" AND C.CTR_ID != E.CTR_ID " +
//		" AND C.CTR_ID = D.CTR_ID " +
//		" AND E.CTR_ID = F.CTR_ID " +
                    " AND B.tlv_id <= ? AND (A.orp_dt > ? OR (A.orp_dt = ? and B.tlv_id < ?)) ";


    /*BUGFIX    2006-10-30*/
    /*Result: correct display of special charachters in title*/
    public static String TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_NEXT_SELECT =
            " B.tlv_id AS id, B.adv_vzk_id AS oproepId, B.tlv_ds AS vraag,B.ttl_db AS titel, B.ttl_htm_db as titelHTML, A.orp_dt AS datum ";


    public static String TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_NEXT_FROM =
            " FROM TD_OPR A, TD_TLV_AWD B, TR_DRB X ";

    public static String TAALVRAGEN_MULTICATEGORIESEARCH_FIXED_NEXT_WHERE =
            " WHERE agh_fg = 't' AND B.adv_vzk_id = A.orp_id AND (A.drb_id = X.drb_id )" +
//		" AND B.TLV_ID = C.TLV_ID " +
//		" AND C.TLV_ID = E.TLV_ID " +
//		" AND C.CTR_ID != E.CTR_ID " +
//		" AND C.CTR_ID = D.CTR_ID " +
//		" AND E.CTR_ID = F.CTR_ID " +
                    " AND B.tlv_id <= ? ";

    public static String TAALVRAAG_BY_PK = "SELECT tlv_id AS id, adv_vzk_id AS oproepId, tlv_ds AS vraag, tlv_spc_htm_db AS vraagHTML, tlv_inf_db AS informatie, tlv_inf_htm_db AS informatieHTML, tlv_vgn_nr AS volgnummer, tlv_vrl_agh_fg AS afgehandeld, awd_krt_db AS antwoord, awd_krt_htm_db AS antwoordHTML, awd_tol_db AS toelichting, awd_tol_htm_db AS toelichtingHTML, awd_bjz_db AS bijzonderheid, awd_bjz_htm_db AS bijzonderheidHTML, ttl_db AS titel, ttl_htm_db AS titelHTML, rvt_id AS relevantieId, tlv_her_db AS herformulering, tlv_her_htm_db AS herformuleringHTML, drb_dt AS distributiedatum FROM td_tlv_awd WHERE tlv_id = ?";
    public static String TAALVRAGEN_BY_PARENT = "SELECT tlv_id AS id, adv_vzk_id AS oproepId, tlv_ds AS vraag, tlv_spc_htm_db AS vraagHTML, tlv_inf_db AS informatie, tlv_inf_htm_db AS informatieHTML, tlv_vgn_nr AS volgnummer, tlv_vrl_agh_fg AS afgehandeld, awd_krt_db AS antwoord, awd_krt_htm_db AS antwoordHTML, awd_tol_db AS toelichting, awd_tol_htm_db AS toelichtingHTML, awd_bjz_db AS bijzonderheid, awd_bjz_htm_db AS bijzonderheidHTML, ttl_db AS titel, ttl_htm_db AS titelHTML, rvt_id AS relevantieId, tlv_her_db AS herformulering, tlv_her_htm_db AS herformuleringHTML, drb_dt AS distributiedatum FROM td_tlv_awd WHERE adv_vzk_id = ? ORDER BY tlv_vgn_nr";
    public static String TAALVRAGEN_OFLOAD = "SELECT a.tlv_ds AS vraag, a.awd_krt_db AS antwoord, a.awd_tol_db AS toelichting, b.opr_hkm_nr AS herkomstnummer, ttl_db AS titel, b.orr_ea AS email FROM td_tlv_awd a, td_opr b WHERE a.adv_vzk_id = b.orp_id AND b.agh_fg = 't' AND b.ofl_fg = 'f'";

    public static String INSERT_TAALVRAAG = "INSERT INTO td_tlv_awd(ttl_db, ttl_htm_db, tlv_ds, tlv_spc_htm_db, awd_krt_db, awd_krt_htm_db, awd_tol_db, awd_tol_htm_db, tlv_vgn_nr, tlv_vrl_agh_fg, adv_vzk_id) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    public static String UPDATE_TAALVRAAG = "UPDATE td_tlv_awd SET ttl_db = ?, ttl_htm_db = ?, tlv_ds = ?, tlv_spc_htm_db = ?, awd_krt_db = ?, awd_krt_htm_db = ?, awd_tol_db = ?, awd_tol_htm_db = ?, tlv_vgn_nr = ?, tlv_vrl_agh_fg = ?, adv_vzk_id = ? WHERE tlv_id = ?";
    public static String UPDATE_KENMERKEN = "UPDATE td_tlv_awd SET tlv_inf_db = ?, tlv_inf_htm_db = ?, tlv_her_db = ?, tlv_her_htm_db = ?, rvt_id = ? WHERE tlv_id = ?";
    public static String UPDATE_BIJZONDERHEID = "UPDATE td_tlv_awd SET awd_bjz_db = ?, awd_bjz_htm_db = ? WHERE tlv_id = ?";
    public static String UPDATE_DISTRIBUTIEDATUM = "UPDATE td_tlv_awd SET drb_dt = ? WHERE tlv_id = ?";
    public static String UPDATE_TAALVRAAG_VOLGNUMMER = "UPDATE td_tlv_awd SET tlv_vgn_nr = ? WHERE tlv_id = ?";
    public static String DELETE_TAALVRAAG = "DELETE FROM td_tlv_awd WHERE tlv_id = ?";
    public static String TAALVRAAG_MAX_VOLGNUMMER = "SELECT max(tlv_vgn_nr) AS maximum FROM td_tlv_awd WHERE adv_vzk_id = ?";
    public static String INSERT_TEKST_IN_TAALVRAAG = "INSERT INTO tr_tlv_awd_tks(tlv_id, tks_id) VALUES(?,?)";
    public static String DELETE_TEKST_IN_TAALVRAAG = "DELETE FROM tr_tlv_awd_tks WHERE tlv_id = ? AND tks_id = ?";
    public static String DELETE_TEKSTEN_IN_TAALVRAAG = "DELETE FROM tr_tlv_awd_tks WHERE tlv_id = ?";
    //public static String DELETE_VERITY_FOR_TAALVRAAG = "DELETE FROM verity_tlv WHERE tlv_id = ?";
    //public static String DELETE_VERITY_FOR_TEKST = "DELETE FROM verity_tks WHERE tks_id = ?";

    // SEARCH ONLY MAIN TEXTTITEL
    public static String TEKSTEN_SEARCH_FIXED_COUNT = "SELECT MAX(B.tks_id) AS max , count(DISTINCT B.tks_id) AS count FROM TD_OPR A, TD_TKS B, TR_DRB X WHERE A.AGH_FG = 't' AND B.ADV_VZK_ID = A.ORP_ID  AND (A.drb_id = X.drb_id ) ";
    public static String TEKSTEN_SEARCH_FIXED_PREVIOUS = " B.tks_id AS id, B.adv_vzk_id AS oproepId, B.hfd_ttl_ds AS titel, A.orp_dt AS datum FROM TD_OPR A, TD_TKS B , TR_DRB X WHERE A.AGH_FG = 't' AND B.ADV_VZK_ID = A.ORP_ID AND B.tks_id <= ? AND (A.orp_dt > ? OR (A.orp_dt = ? and B.tks_id < ?))  AND (A.drb_id = X.drb_id ) ";
    public static String TEKSTEN_SEARCH_FIXED_NEXT = " B.tks_id AS id, B.adv_vzk_id AS oproepId, B.hfd_ttl_ds AS titel, A.orp_dt AS datum FROM TD_OPR A, TD_TKS B, TR_DRB X WHERE A.AGH_FG = 't' AND B.ADV_VZK_ID = A.ORP_ID AND B.tks_id <= ?  AND (A.drb_id = X.drb_id ) ";

    // SEARCH MAIN TEXTTITEL + SUBTEXTS
    public static String TEKSTENTB_SEARCH_FIXED_COUNT = "SELECT MAX(B.tks_id) AS max , count(DISTINCT B.tks_id) AS count FROM TD_OPR A, TD_TKS B, TD_TSB C, TR_DRB X WHERE A.AGH_FG = 't' AND B.ADV_VZK_ID = A.ORP_ID AND B.TKS_ID = C.TKS_ID  AND (A.drb_id = X.drb_id )";
    public static String TEKSTENTB_SEARCH_FIXED_PREVIOUS = " DISTINCT B.tks_id AS id, B.adv_vzk_id AS oproepId, B.hfd_ttl_ds AS titel, A.orp_dt AS datum FROM TD_OPR A, TD_TKS B, TD_TSB C, TR_DRB X WHERE A.AGH_FG = 't' AND B.ADV_VZK_ID = A.ORP_ID AND B.TKS_ID = C.TKS_ID AND B.TKS_ID <= ? AND (A.ORP_DT > ? OR (A.ORP_DT = ? and B.TKS_ID < ?))  AND (A.drb_id = X.drb_id ) ";
    public static String TEKSTENTB_SEARCH_FIXED_NEXT = " DISTINCT B.tks_id AS id, B.adv_vzk_id AS oproepId, B.hfd_ttl_ds AS titel, A.orp_dt AS datum FROM TD_OPR A, TD_TKS B, TD_TSB C, TR_DRB X WHERE A.AGH_FG = 't' AND B.ADV_VZK_ID = A.ORP_ID AND B.TKS_ID = C.TKS_ID AND B.TKS_ID <= ?  AND (A.drb_id = X.drb_id ) ";

    // SEARCH SINGLE CATEGORY FOR TEXT
    public static String TEKSTEN_CATEGORIESEARCH_FIXED_COUNT = "SELECT MAX(B.tks_id) AS max , count(DISTINCT B.tks_id) AS count FROM TD_OPR A, TD_TKS B, TR_TKS_CTR C, TR_CTR D, TR_DRB X WHERE A.AGH_FG = 't' AND B.ADV_VZK_ID = A.ORP_ID AND B.TKS_ID = C.TKS_ID AND C.CTR_ID = D.CTR_ID AND D.CTR_NMR_TX LIKE ?  AND (A.drb_id = X.drb_id ) ";
    public static String TEKSTEN_CATEGORIESEARCH_FIXED_PREVIOUS = " DISTINCT B.tks_id AS id, B.adv_vzk_id AS oproepId, B.hfd_ttl_ds AS titel, A.orp_dt AS datum FROM TD_OPR A, TD_TKS B, TR_TKS_CTR C, TR_CTR D, TR_DRB X WHERE A.AGH_FG = 't' AND B.ADV_VZK_ID = A.ORP_ID AND B.TKS_ID = C.TKS_ID AND C.CTR_ID = D.CTR_ID AND B.TKS_ID <= ? AND (A.ORP_DT > ? OR (A.ORP_DT = ? and B.TKS_ID < ?))  AND (A.drb_id = X.drb_id ) ";
    public static String TEKSTEN_CATEGORIESEARCH_FIXED_NEXT = " DISTINCT B.tks_id AS id, B.adv_vzk_id AS oproepId, B.hfd_ttl_ds AS titel, A.orp_dt AS datum FROM TD_OPR A, TD_TKS B, TR_TKS_CTR C, TR_CTR D, TR_DRB X WHERE A.AGH_FG = 't' AND B.ADV_VZK_ID = A.ORP_ID AND B.TKS_ID = C.TKS_ID AND C.CTR_ID = D.CTR_ID AND B.TKS_ID <= ?  AND (A.drb_id = X.drb_id ) ";

    // SEARCH MULTIPLE CATEGORIES FOR TEXT
//	public static String TEKSTEN_MULTICATEGORIESEARCH_FIXED_COUNT 	=
//		"SELECT MAX(B.tks_id) AS max , count(DISTINCT B.tks_id) AS count " +
//		" FROM TD_OPR A, TD_TKS B, TR_TKS_CTR C, TR_CTR D, TR_TKS_CTR E, TR_CTR F " +
//		" WHERE AGH_FG = 't' AND B.ADV_VZK_ID = A.ORP_ID " +
//		" AND B.TKS_ID = C.TKS_ID " +
//		" AND C.TKS_ID = E.TKS_ID " +
//		" AND C.CTR_ID != E.CTR_ID " +
//		" AND C.CTR_ID = D.CTR_ID " +
//		" AND D.CTR_NMR_TX LIKE ? " +
    //		" AND E.CTR_ID = F.CTR_ID  " +
    //		" AND F.CTR_NMR_TX LIKE ? ";
    public static String TEKSTEN_MULTICATEGORIESEARCH_FIXED_COUNT_SELECT =
            "SELECT MAX(B.tks_id) AS max , count(DISTINCT B.tks_id) AS count ";
    public static String TEKSTEN_MULTICATEGORIESEARCH_FIXED_COUNT_FROM =
            " FROM TD_OPR A, TD_TKS B, TR_DRB X";
    public static String TEKSTEN_MULTICATEGORIESEARCH_FIXED_COUNT_WHERE =
            " WHERE AGH_FG = 't' AND B.ADV_VZK_ID = A.ORP_ID " +
                    " AND (A.drb_id = X.drb_id ) ";

    //public static String TEKSTEN_MULTICATEGORIESEARCH_FIXED_PREVIOUS	= " DISTINCT B.tks_id AS id, B.adv_vzk_id AS oproepId, B.hfd_ttl_ds AS titel, A.orp_dt AS datum FROM TD_OPR A, TD_TKS B, TR_TKS_CTR C, TR_CTR D, TR_TKS_CTR E, TR_CTR F WHERE AGH_FG = 't' AND B.ADV_VZK_ID = A.ORP_ID AND B.TKS_ID = C.TKS_ID AND C.TKS_ID = E.TKS_ID AND C.CTR_ID != E.CTR_ID AND C.CTR_ID = D.CTR_ID AND E.CTR_ID = F.CTR_ID AND B.TKS_ID <= ? AND (A.ORP_DT > ? OR (A.ORP_DT = ? and B.TKS_ID < ?)) ";
    public static String TEKSTEN_MULTICATEGORIESEARCH_FIXED_PREVIOUS_SELECT =
            " DISTINCT B.tks_id AS id, B.adv_vzk_id AS oproepId, B.hfd_ttl_ds AS titel, A.orp_dt AS datum ";

    public static String TEKSTEN_MULTICATEGORIESEARCH_FIXED_PREVIOUS_FROM =
            " FROM TD_OPR A, TD_TKS B, TR_DRB X ";

    public static String TEKSTEN_MULTICATEGORIESEARCH_FIXED_PREVIOUS_WHERE =
            " WHERE AGH_FG = 't' AND B.ADV_VZK_ID = A.ORP_ID " +
                    " AND (A.drb_id = X.drb_id ) " +
                    " AND B.TKS_ID <= ? AND (A.ORP_DT > ? OR (A.ORP_DT = ? and B.TKS_ID < ?)) ";


    //public static String TEKSTEN_MULTICATEGORIESEARCH_FIXED_NEXT		= " DISTINCT B.tks_id AS id, B.adv_vzk_id AS oproepId, B.hfd_ttl_ds AS titel, A.orp_dt AS datum FROM TD_OPR A, TD_TKS B, TR_TKS_CTR C, TR_CTR D, TR_TKS_CTR E, TR_CTR F WHERE AGH_FG = 't' AND B.ADV_VZK_ID = A.ORP_ID AND B.TKS_ID = C.TKS_ID AND C.TKS_ID = E.TKS_ID AND C.CTR_ID != E.CTR_ID AND C.CTR_ID = D.CTR_ID AND E.CTR_ID = F.CTR_ID AND B.TKS_ID <= ? ";
    public static String TEKSTEN_MULTICATEGORIESEARCH_FIXED_NEXT_SELECT =
            " DISTINCT B.tks_id AS id, B.adv_vzk_id AS oproepId, B.hfd_ttl_ds AS titel, A.orp_dt AS datum ";

    public static String TEKSTEN_MULTICATEGORIESEARCH_FIXED_NEXT_FROM =
            " FROM TD_OPR A, TD_TKS B, TR_DRB X ";

    public static String TEKSTEN_MULTICATEGORIESEARCH_FIXED_NEXT_WHERE =
            " WHERE AGH_FG = 't' AND B.ADV_VZK_ID = A.ORP_ID " +
                    " AND (A.drb_id = X.drb_id ) " +
                    " AND B.TKS_ID <= ? ";


    public static String TEKST_BY_PK = "SELECT tks_id AS id, adv_vzk_id AS oproepId, hfd_ttl_ds AS titel, hfd_ttl_htm_db AS titelHTML, rvt_id AS relevantieId FROM td_tks WHERE tks_id = ?";
    public static String TEKST_BY_PARENT = "SELECT tks_id AS id, adv_vzk_id AS oproepId, hfd_ttl_ds AS titel, hfd_ttl_htm_db AS titelHTML, rvt_id AS relevantieId FROM td_tks WHERE adv_vzk_id = ?";
    public static String INSERT_TEKST = "INSERT INTO td_tks(hfd_ttl_ds, hfd_ttl_htm_db, adv_vzk_id, rvt_id) VALUES(?,?,?,?)";
    public static String UPDATE_TEKST = "UPDATE td_tks SET hfd_ttl_ds = ?, hfd_ttl_htm_db = ?, adv_vzk_id = ?, rvt_id = ? WHERE tks_id = ?";
    public static String TEKSTEN_BY_TAALVRAAG = "SELECT a.tks_id AS id, a.adv_vzk_id AS oproepId, a.hfd_ttl_ds AS titel, a.hfd_ttl_htm_db AS titelHTML, a.rvt_id AS relevantieId FROM td_tks a, tr_tlv_awd_tks b WHERE a.tks_id = b.tks_id AND b.tlv_id = ?";
    public static String DELETE_TEKST = "DELETE FROM td_tks WHERE tks_id = ?";

    // Search WITHOUT searching sub-texts
    public static String TEKST_SEARCH_NTB = "SELECT tks_id AS id, adv_vzk_id AS oproepId, hfd_ttl_ds AS titel, hfd_ttl_htm_db AS titelHTML, rvt_id AS relevantieId FROM td_tks WHERE tks_id = ?";

    // Search WITH searching sub-texts
    public static String TEKST_SEARCH_TB = "SELECT tks_id AS id, adv_vzk_id AS oproepId, hfd_ttl_ds AS titel, hfd_ttl_htm_db AS titelHTML, rvt_id AS relevantieId FROM td_tks WHERE tks_id = ?";

    public static String TEKSTBLOK_BY_PK = "SELECT tsb_id AS id, tks_id AS tekstId, tsb_ttl_ds AS titel, tsb_ttl_htm_db AS titelHTML, tsb_db AS tekstblok, tsb_htm_db AS tekstblokHTML, tsb_vgn_nr AS volgnummer FROM td_tsb WHERE tsb_id = ?";
    public static String TEKSTBLOKKEN_BY_PARENT = "SELECT tsb_id AS id, tks_id AS tekstId, tsb_ttl_ds AS titel, tsb_ttl_htm_db AS titelHTML, tsb_db AS tekstblok, tsb_htm_db AS tekstblokHTML, tsb_vgn_nr AS volgnummer FROM td_tsb WHERE tks_id = ? ORDER BY tsb_vgn_nr";
    public static String INSERT_TEKSTBLOK = "INSERT INTO td_tsb(tsb_ttl_ds, tsb_ttl_htm_db, tsb_db, tsb_htm_db, tsb_vgn_nr, tks_id) VALUES(?,?,?,?,?,?)";
    public static String UPDATE_TEKSTBLOK = "UPDATE td_tsb SET tsb_ttl_ds = ?, tsb_ttl_htm_db = ?, tsb_db = ?, tsb_htm_db = ?, tsb_vgn_nr = ?, tks_id = ? WHERE tsb_id = ?";
    public static String UPDATE_TEKSTBLOK_VOLGNUMMER = "UPDATE td_tsb SET tsb_vgn_nr = ? WHERE tsb_id = ?";
    public static String TEKSTBLOK_MAX_VOLGNUMMER = "SELECT max(tsb_vgn_nr) AS maximum FROM td_tsb WHERE tks_id = ?";
    public static String DELETE_TEKSTBLOK = "DELETE FROM td_tsb WHERE tsb_id = ?";

    public static String ALL_OPEN_OPROEPEN_BY_TYPE_AND_GEBRUIKER = "SELECT orp_id AS id, adv_vzk_typ_cd AS type, tav_id AS gebruikerId, mdm_id AS mediumId, orp_sts_cd AS statusId, drb_id AS distributieId, dgp_id AS doelgroepId, hkm_id AS herkomstId, gmn_id AS gemeenteId, dln_dt AS deadline, orp_dt AS oproepdatum, orr_gst_fg AS geslacht, orr_hus_nr AS huisnummer, orr_bus_nr AS busnummer, orr_tel_nr AS telefoon, orr_fax_nr AS fax, agh_fg AS afgehandeld, orp_opm_db AS opmerking, slt_fg AS slot, orr_nm AS naam, orr_vrn_nm AS voornaam, orr_fnt_ds AS functie, orr_ads_tx AS straat, orr_ea AS email, opr_hkm_nr AS herkomstnummer, utl_org_nm AS organisatie, dmn_id AS domeinId FROM td_opr WHERE adv_vzk_typ_cd = ? AND tav_id = ? AND agh_fg = 'f' ORDER BY orp_dt, orp_id";
    public static String ALL_OPEN_OPROEPEN_BY_TYPE_AND_NOT_GEBRUIKER = "SELECT orp_id AS id, adv_vzk_typ_cd AS type, tav_id AS gebruikerId, mdm_id AS mediumId, orp_sts_cd AS statusId, drb_id AS distributieId, dgp_id AS doelgroepId, hkm_id AS herkomstId, gmn_id AS gemeenteId, dln_dt AS deadline, orp_dt AS oproepdatum, orr_gst_fg AS geslacht, orr_hus_nr AS huisnummer, orr_bus_nr AS busnummer, orr_tel_nr AS telefoon, orr_fax_nr AS fax, agh_fg AS afgehandeld, orp_opm_db AS opmerking, slt_fg AS slot, orr_nm AS naam, orr_vrn_nm AS voornaam, orr_fnt_ds AS functie, orr_ads_tx AS straat, orr_ea AS email, opr_hkm_nr AS herkomstnummer, utl_org_nm AS organisatie, dmn_id AS domeinId FROM td_opr WHERE adv_vzk_typ_cd = ? AND tav_id != ? AND agh_fg = 'f' ORDER BY orp_dt, orp_id";
    public static String ALL_OPEN_OPROEPEN_BY_TYPE = "SELECT orp_id AS id, adv_vzk_typ_cd AS type, tav_id AS gebruikerId, mdm_id AS mediumId, orp_sts_cd AS statusId, drb_id AS distributieId, dgp_id AS doelgroepId, hkm_id AS herkomstId, gmn_id AS gemeenteId, dln_dt AS deadline, orp_dt AS oproepdatum, orr_gst_fg AS geslacht, orr_hus_nr AS huisnummer, orr_bus_nr AS busnummer, orr_tel_nr AS telefoon, orr_fax_nr AS fax, agh_fg AS afgehandeld, orp_opm_db AS opmerking, slt_fg AS slot, orr_nm AS naam, orr_vrn_nm AS voornaam, orr_fnt_ds AS functie, orr_ads_tx AS straat, orr_ea AS email, opr_hkm_nr AS herkomstnummer, utl_org_nm AS organisatie, dmn_id AS domeinId FROM td_opr WHERE adv_vzk_typ_cd = ? AND agh_fg = 'f' ORDER BY orp_dt, orp_id";
    public static String ALL_NIET_TOEGEEIGENDE_OPROEPEN_TAALUNIEVERSUM = "SELECT orp_id AS id, adv_vzk_typ_cd AS type, tav_id AS gebruikerId, mdm_id AS mediumId, orp_sts_cd AS statusId, drb_id AS distributieId, dgp_id AS doelgroepId, td_opr.hkm_id AS herkomstId, gmn_id AS gemeenteId, dln_dt AS deadline, orp_dt AS oproepdatum, orr_gst_fg AS geslacht, orr_hus_nr AS huisnummer, orr_bus_nr AS busnummer, orr_tel_nr AS telefoon, orr_fax_nr AS fax, agh_fg AS afgehandeld, orp_opm_db AS opmerking, slt_fg AS slot, orr_nm AS naam, orr_vrn_nm AS voornaam, orr_fnt_ds AS functie, orr_ads_tx AS straat, orr_ea AS email, opr_hkm_nr AS herkomstnummer, utl_org_nm AS organisatie, dmn_id AS domeinId FROM td_opr, tp_hkm WHERE adv_vzk_typ_cd =1 AND tav_id IS NULL AND agh_fg = 'f' AND tp_hkm.hkm_nm = 'Taalunieversum' AND td_opr.hkm_id = tp_hkm.hkm_id  ORDER BY orp_dt ASC, orp_id";
    public static String ALL_GESLOTEN_OPROEPEN_TO_UPLOAD_TO_TAALUNIE = "SELECT td_opr.orp_id AS id, tav_nm AS naam, tav_vrn_nm AS voornaam, opr_hkm_nr AS herkomstnummer, td_tlv_awd.awd_tol_db AS toelichting, td_tlv_awd.awd_krt_db AS antwoord, td_tlv_awd.ttl_db AS titel, td_tlv_awd.rvt_id AS tao FROM td_opr, td_opr_opladen_tu, td_tav, td_tlv_awd WHERE td_opr_opladen_tu.orp_id = td_opr.orp_id AND td_opr.tav_id = td_tav.tav_id AND td_tlv_awd.adv_vzk_id = td_opr.orp_id";
    public static String ALL_OPROEPEN = "SELECT orp_id AS id, adv_vzk_typ_cd AS type, tav_id AS gebruikerId, mdm_id AS mediumId, orp_sts_cd AS statusId, drb_id AS distributieId, dgp_id AS doelgroepId, hkm_id AS herkomstId, gmn_id AS gemeenteId, dln_dt AS deadline, orp_dt AS oproepdatum, orr_gst_fg AS geslacht, orr_hus_nr AS huisnummer, orr_bus_nr AS busnummer, orr_tel_nr AS telefoon, orr_fax_nr AS fax, agh_fg AS afgehandeld, orp_opm_db AS opmerking, slt_fg AS slot, orr_nm AS naam, orr_vrn_nm AS voornaam, orr_fnt_ds AS functie, orr_ads_tx AS straat, orr_ea AS email, opr_hkm_nr AS herkomstnummer, utl_org_nm AS organisatie, dmn_id AS domeinId FROM td_opr";
    public static String OPROEP_BY_PK = "SELECT orp_id AS id, adv_vzk_typ_cd AS type, tav_id AS gebruikerId, mdm_id AS mediumId, orp_sts_cd AS statusId, drb_id AS distributieId, dgp_id AS doelgroepId, hkm_id AS herkomstId, gmn_id AS gemeenteId, dln_dt AS deadline, orp_dt AS oproepdatum, orr_gst_fg AS geslacht, orr_hus_nr AS huisnummer, orr_bus_nr AS busnummer, orr_tel_nr AS telefoon, orr_fax_nr AS fax, agh_fg AS afgehandeld, orp_opm_db AS opmerking, slt_fg AS slot, orr_nm AS naam, orr_vrn_nm AS voornaam, orr_fnt_ds AS functie, orr_ads_tx AS straat, orr_ea AS email, opr_hkm_nr AS herkomstnummer, utl_org_nm AS organisatie, dmn_id AS domeinId FROM td_opr WHERE orp_id = ?";
    public static String OPROEP_BY_PK_AFGEHANDELD = "SELECT orp_id AS id, adv_vzk_typ_cd AS type, tav_id AS gebruikerId, mdm_id AS mediumId, orp_sts_cd AS statusId, drb_id AS distributieId, dgp_id AS doelgroepId, hkm_id AS herkomstId, gmn_id AS gemeenteId, dln_dt AS deadline, orp_dt AS oproepdatum, orr_gst_fg AS geslacht, orr_hus_nr AS huisnummer, orr_bus_nr AS busnummer, orr_tel_nr AS telefoon, orr_fax_nr AS fax, agh_fg AS afgehandeld, orp_opm_db AS opmerking, slt_fg AS slot, orr_nm AS naam, orr_vrn_nm AS voornaam, orr_fnt_ds AS functie, orr_ads_tx AS straat, orr_ea AS email, opr_hkm_nr AS herkomstnummer, utl_org_nm AS organisatie, dmn_id AS domeinId FROM td_opr WHERE orp_id = ? AND agh_fg = 't'";
    public static String OPROEP_BY_HERKOMSTNUMMER_TAALUNIE = "SELECT td_opr.orp_id  FROM td_opr, tp_hkm WHERE adv_vzk_typ_cd = 1 AND tp_hkm.hkm_nm = 'Taalunieversum' AND td_opr.hkm_id = tp_hkm.hkm_id AND td_opr.opr_hkm_nr = ?";
    public static String INSERT_OPROEP = "INSERT INTO td_opr(adv_vzk_typ_cd, dln_dt, orp_dt, orr_nm, orr_vrn_nm, orr_tel_nr, opr_hkm_nr, agh_fg, slt_fg, hkm_id, orp_sts_cd, mdm_id, drb_id, tav_id, dmn_id, orr_gst_fg, orr_ea, orr_fax_nr, utl_org_nm) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static String UPDATE_OPROEP = "UPDATE td_opr SET adv_vzk_typ_cd = ?, dln_dt = ?, orp_dt = ?, orr_nm = ?, orr_vrn_nm = ?, orr_tel_nr = ?, opr_hkm_nr = ?, agh_fg = ?, slt_fg = ?, hkm_id = ?, orp_sts_cd = ?, mdm_id = ?, drb_id = ?, tav_id = ?, dmn_id = ?, orr_ea = ? WHERE orp_id = ?";
    public static String UPDATE_KLANTENGEGEVENS_OPROEP = "UPDATE td_opr SET orr_fnt_ds = ?, orr_ads_tx = ?, orr_hus_nr = ?, orr_bus_nr = ?, orr_fax_nr = ?, orr_ea = ?, orr_gst_fg = ?, utl_org_nm = ?, orp_opm_db = ?, gmn_id = ?, dgp_id = ?, orr_nm = ?, orr_vrn_nm = ?, orr_tel_nr = ?, dmn_id = ?, hkm_id = ?, opr_hkm_nr = ? WHERE orp_id = ?";
    public static String DELETE_OPROEP = "DELETE FROM td_opr WHERE orp_id = ?";
    public static String OPEN_OPROEP = "UPDATE td_opr SET agh_fg = 'f' WHERE orp_id = ?";
    public static String CLOSE_OPROEP = "UPDATE td_opr SET agh_fg = 't' WHERE orp_id = ?";
    public static String OVERNAME_OPROEP = "UPDATE td_opr SET tav_id = ? WHERE orp_id = ?";
    public static String INSERT_OPROEP_INTO_TAALUNIE = "INSERT INTO td_opr_opladen_tu(orp_id) VALUES(?)";
    public static String INSERT_XML_INTO_TAALUNIEXML = "INSERT INTO td_taalunie_xml(ophaaldatum, ophaalurl, taalvragen_xml) VALUES(current,?,?)";
    public static String UPDATE_FOUTBOODSCHAP_INTO_TAALUNIEXML = "UPDATE td_taalunie_xml SET foutboodschap = ? WHERE xml_id = ?";
    public static String DELETE_VERZONDEN_OPROEP_TAALUNIE = "DELETE FROM td_opr_opladen_tu WHERE orp_id = ?";
    public static String HOOGSTE_HERKOMSTNUMMER_TAALUNIEVERSUM = "SELECT FIRST 1 opr_hkm_nr::int AS herkomstnummer FROM td_opr, tp_hkm WHERE adv_vzk_typ_cd = 1 AND tp_hkm.hkm_nm = 'Taalunieversum' AND td_opr.hkm_id = tp_hkm.hkm_id  ORDER BY 1 DESC";

    //Te verwijderen, genereren testgegevens

    public static String INSERT_OPROEPWITHID = "INSERT INTO td_opr(orp_id, adv_vzk_typ_cd, dln_dt, orp_dt, orr_nm, orr_vrn_nm, orr_tel_nr, opr_hkm_nr, agh_fg, slt_fg, hkm_id, orp_sts_cd, mdm_id, drb_id, tav_id, dmn_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public static String NASLAGREFERENTIES_BY_PARENT_TLV = "SELECT nsg_ref_id AS id, nsg_vnt_ds AS variant, ctt_db AS citaat, ctt_url_htm_db AS citaatHTML, pgn_nmr_tx AS paginas, nsw_id AS naslagwerkId, tlv_id AS taalvraagId, tks_id AS tekstId FROM td_nsg_ref WHERE tlv_id = ? AND tks_id IS NULL";
    public static String NASLAGREFERENTIES_BY_PARENT_TKS = "SELECT nsg_ref_id AS id, nsg_vnt_ds AS variant, ctt_db AS citaat, ctt_url_htm_db AS citaatHTML, pgn_nmr_tx AS paginas, nsw_id AS naslagwerkId, tlv_id AS taalvraagId, tks_id AS tekstId FROM td_nsg_ref WHERE tlv_id IS NULL AND tks_id = ?";
    public static String NASLAGREFERENTIE_BY_PK = "SELECT nsg_ref_id AS id, nsg_vnt_ds AS variant, ctt_db AS citaat, ctt_url_htm_db AS citaatHTML, pgn_nmr_tx AS paginas, nsw_id AS naslagwerkId, tlv_id AS taalvraagId, tks_id AS tekstId FROM td_nsg_ref WHERE nsg_ref_id = ?";
    public static String INSERT_NASLAGREFERENTIE = "INSERT INTO td_nsg_ref(nsg_vnt_ds, ctt_db, ctt_url_htm_db, pgn_nmr_tx, nsw_id, tlv_id, tks_id) VALUES(?,?,?,?,?,?,?)";
    public static String UPDATE_NASLAGREFERENTIE = "UPDATE td_nsg_ref SET nsg_vnt_ds = ?, ctt_db = ?, ctt_url_htm_db = ?, pgn_nmr_tx = ?, nsw_id = ?, tlv_id = ?, tks_id = ? WHERE nsg_ref_id = ?";
    public static String DELETE_NASLAGREFERENTIE = "DELETE FROM td_nsg_ref WHERE nsg_ref_id = ?";
    public static String DELETE_ALL_NASLAGREFERENTIES_FOR_TAALVRAAG = "DELETE FROM td_nsg_ref WHERE tlv_id = ?";
    public static String DELETE_ALL_NASLAGREFERENTIES_FOR_TEKST = "DELETE FROM td_nsg_ref WHERE tks_id = ?";

    public static String BRONNEN_BY_PARENT_TLV = "SELECT brn_id AS id, brn_vnt_ds AS variant, brn_vmd_db AS titel, brn_vmd_htm_db AS titelHTML, ctt_db AS citaat, ctt_url_htm_db AS citaatHTML, pgn_nmr_tx AS paginas, tlv_id AS taalvraagId, tks_id AS tekstId FROM td_brn WHERE tlv_id = ? AND tks_id IS NULL";
    public static String BRONNEN_BY_PARENT_TKS = "SELECT brn_id AS id, brn_vnt_ds AS variant, brn_vmd_db AS titel, brn_vmd_htm_db AS titelHTML, ctt_db AS citaat, ctt_url_htm_db AS citaatHTML, pgn_nmr_tx AS paginas, tlv_id AS taalvraagId, tks_id AS tekstId FROM td_brn WHERE tlv_id IS NULL AND tks_id = ?";
    public static String BRON_BY_PK = "SELECT brn_id AS id, brn_vnt_ds AS variant, brn_vmd_db AS titel, brn_vmd_htm_db AS titelHTML, ctt_db AS citaat, ctt_url_htm_db AS citaatHTML, pgn_nmr_tx AS paginas, tlv_id AS taalvraagId, tks_id AS tekstId FROM td_brn WHERE brn_id = ?";
    public static String INSERT_BRON = "INSERT INTO td_brn(brn_vnt_ds, brn_vmd_db, brn_vmd_htm_db, ctt_db, ctt_url_htm_db, pgn_nmr_tx, tlv_id, tks_id) VALUES(?,?,?,?,?,?,?,?)";
    public static String UPDATE_BRON = "UPDATE td_brn SET brn_vnt_ds = ?, brn_vmd_db = ?, brn_vmd_htm_db = ?, ctt_db = ?, ctt_url_htm_db = ?, pgn_nmr_tx = ?, tlv_id = ?, tks_id = ? WHERE brn_id = ?";
    public static String DELETE_BRON = "DELETE FROM td_brn WHERE brn_id = ?";
    public static String DELETE_ALL_BRONNEN_FOR_TAALVRAAG = "DELETE FROM td_brn WHERE tlv_id = ?";
    public static String DELETE_ALL_BRONNEN_FOR_TEKST = "DELETE FROM td_brn WHERE tks_id = ?";

    public static String CITATEN_BY_PARENT_TLV = "SELECT ctt_id AS id, ctt_vnt_ds AS variant, spc_ds AS specificatie, ctt_db AS citaat, ctt_htm_db AS citaatHTML, ctt_ul AS url, zgv_id AS zoekomgevingId, tlv_id AS taalvraagId, tks_id AS tekstId FROM td_ctt WHERE tlv_id = ? AND tks_id IS NULL ";
    public static String CITATEN_BY_PARENT_TKS = "SELECT ctt_id AS id, ctt_vnt_ds AS variant, spc_ds AS specificatie, ctt_db AS citaat, ctt_htm_db AS citaatHTML, ctt_ul AS url, zgv_id AS zoekomgevingId, tlv_id AS taalvraagId, tks_id AS tekstId FROM td_ctt WHERE tlv_id IS NULL AND tks_id = ? ";
    public static String CITAAT_BY_PK = "SELECT ctt_id AS id, ctt_vnt_ds AS variant, spc_ds AS specificatie, ctt_db AS citaat, ctt_htm_db AS citaatHTML, ctt_ul AS url, zgv_id AS zoekomgevingId, tlv_id AS taalvraagId, tks_id AS tekstId FROM td_ctt WHERE ctt_id = ?";
    public static String INSERT_CITAAT = "INSERT INTO td_ctt(ctt_vnt_ds, spc_ds, ctt_db, ctt_htm_db, ctt_ul, zgv_id, tlv_id, tks_id) VALUES(?,?,?,?,?,?,?,?)";
    public static String UPDATE_CITAAT = "UPDATE td_ctt SET ctt_vnt_ds = ?, spc_ds = ?, ctt_db = ?, ctt_htm_db = ?, ctt_ul = ?, zgv_id = ?, tlv_id = ?, tks_id = ? WHERE ctt_id = ?";
    public static String DELETE_CITAAT = "DELETE FROM td_ctt WHERE ctt_id = ?";
    public static String DELETE_ALL_CITATEN_FOR_TAALVRAAG = "DELETE FROM td_ctt WHERE tlv_id = ?";
    public static String DELETE_ALL_CITATEN_FOR_TEKST = "DELETE FROM td_ctt WHERE tks_id = ?";

    public static String FREQUENTIES_BY_PARENT_TLV = "SELECT frq_ozk_id AS id, frq_vnt_ds AS variant, frq_spc_ds AS specificatie, frq_nf AS aantal, zgv_id AS zoekomgevingId, tlv_id AS taalvraagId, tks_id AS tekstId FROM td_frq_ozk WHERE tlv_id = ? AND tks_id IS NULL";
    public static String FREQUENTIES_BY_PARENT_TKS = "SELECT frq_ozk_id AS id, frq_vnt_ds AS variant, frq_spc_ds AS specificatie, frq_nf AS aantal, zgv_id AS zoekomgevingId, tlv_id AS taalvraagId, tks_id AS tekstId FROM td_frq_ozk WHERE tlv_id IS NULL AND tks_id = ?";
    public static String FREQUENTIE_BY_PK = "SELECT frq_ozk_id AS id, frq_vnt_ds AS variant, frq_spc_ds AS specificatie, frq_nf AS aantal, zgv_id AS zoekomgevingId, tlv_id AS taalvraagId, tks_id AS tekstId FROM td_frq_ozk WHERE frq_ozk_id = ?";
    public static String INSERT_FREQUENTIE = "INSERT INTO td_frq_ozk(frq_vnt_ds, frq_spc_ds, frq_nf, zgv_id, tlv_id, tks_id) VALUES(?,?,?,?,?,?)";
    public static String UPDATE_FREQUENTIE = "UPDATE td_frq_ozk SET frq_vnt_ds = ?, frq_spc_ds = ?, frq_nf = ?, zgv_id = ?, tlv_id = ?, tks_id = ? WHERE frq_ozk_id = ?";
    public static String DELETE_FREQUENTIE = "DELETE FROM td_frq_ozk WHERE frq_ozk_id = ?";
    public static String DELETE_ALL_FREQUENTIES_FOR_TAALVRAAG = "DELETE FROM td_frq_ozk WHERE tlv_id = ?";
    public static String DELETE_ALL_FREQUENTIES_FOR_TEKST = "DELETE FROM td_frq_ozk WHERE tks_id = ?";

    public static String WEBREFERENTIES_BY_PARENT_TLV = "SELECT www_sck_id AS id, www_sck_ul AS url, www_sck_htm_db AS toelichtingHTML, tlt_db AS toelichting, tlv_id AS taalvraagId, tks_id AS tekstId, omg_db AS omgeving FROM td_www_sck WHERE tlv_id = ? AND tks_id IS NULL";
    public static String WEBREFERENTIES_BY_PARENT_TKS = "SELECT www_sck_id AS id, www_sck_ul AS url, www_sck_htm_db AS toelichtingHTML, tlt_db AS toelichting, tlv_id AS taalvraagId, tks_id AS tekstId, omg_db AS omgeving FROM td_www_sck WHERE tlv_id IS NULL AND tks_id = ?";
    public static String WEBREFERENTIE_BY_PK = "SELECT www_sck_id AS id, www_sck_ul AS url, www_sck_htm_db AS toelichtingHTML, tlt_db AS toelichting, tlv_id AS taalvraagId, tks_id AS tekstId, omg_db AS omgeving FROM td_www_sck WHERE www_sck_id = ?";
    public static String INSERT_WEBREFERENTIE = "INSERT INTO td_www_sck(www_sck_ul, tlt_db, www_sck_htm_db, tlv_id, tks_id, omg_db) VALUES(?,?,?,?,?,?)";
    public static String UPDATE_WEBREFERENTIE = "UPDATE td_www_sck SET www_sck_ul = ?, tlt_db = ?, www_sck_htm_db = ?, tlv_id = ?, tks_id = ?, omg_db = ? WHERE www_sck_id = ?";
    public static String DELETE_WEBREFERENTIE = "DELETE FROM td_www_sck WHERE www_sck_id = ?";
    public static String DELETE_ALL_WEBREFERENTIES_FOR_TAALVRAAG = "DELETE FROM td_www_sck WHERE tlv_id = ?";
    public static String DELETE_ALL_WEBREFERENTIES_FOR_TEKST = "DELETE FROM td_www_sck WHERE tks_id = ?";

    public static String NOTITIES_BY_PARENT_TLV = "SELECT ntt_id AS id, ntt_db AS notitie, ntt_htm_db AS notitieHTML, ntt_dt AS datum, tav_id AS gebruikerId, tlv_id AS taalvraagId, tks_id AS tekstId FROM td_ntt WHERE tlv_id = ? AND tks_id IS NULL";
    public static String NOTITIES_BY_PARENT_TKS = "SELECT ntt_id AS id, ntt_db AS notitie, ntt_htm_db AS notitieHTML, ntt_dt AS datum, tav_id AS gebruikerId, tlv_id AS taalvraagId, tks_id AS tekstId FROM td_ntt WHERE tlv_id IS NULL AND tks_id = ?";
    public static String NOTITIE_BY_PK = "SELECT ntt_id AS id, ntt_db AS notitie, ntt_htm_db AS notitieHTML, ntt_dt AS datum, tav_id AS gebruikerId, tlv_id AS taalvraagId, tks_id AS tekstId FROM td_ntt WHERE ntt_id = ?";
    public static String INSERT_NOTITIE = "INSERT INTO td_ntt(ntt_db, ntt_htm_db, ntt_dt, tav_id, tlv_id, tks_id) VALUES(?,?,?,?,?,?)";
    public static String UPDATE_NOTITIE = "UPDATE td_ntt SET ntt_db = ?, ntt_htm_db = ?, ntt_dt = ?, tav_id = ?, tlv_id = ?, tks_id = ? WHERE ntt_id = ?";
    public static String DELETE_NOTITIE = "DELETE FROM td_ntt WHERE ntt_id = ?";
    public static String DELETE_ALL_NOTITIES_FOR_TAALVRAAG = "DELETE FROM td_ntt WHERE tlv_id = ?";
    public static String DELETE_ALL_NOTITIES_FOR_TEKST = "DELETE FROM td_ntt WHERE tks_id = ?";

    public static String ALL_DISTRIBUTIES = "SELECT drb_id AS id, drb_dt AS distributiedatum, mdm_id AS mediumId, sbn_id AS sjabloonId FROM tr_drb";
    public static String DISTRIBUTIE_BY_PK = "SELECT drb_id AS id, drb_dt AS distributiedatum, mdm_id AS mediumId, sbn_id AS sjabloonId FROM tr_drb WHERE drb_id = ?";
    public static String INSERT_DISTRIBUTIE = "INSERT INTO tr_drb(drb_dt, mdm_id, sbn_id) VALUES(?,?,?)";
    public static String UPDATE_DISTRIBUTIE = "UPDATE tr_drb SET drb_dt = ?, mdm_id = ?, sbn_id = ? WHERE drb_id = ?";
    public static String DELETE_DISTRIBUTIE = "DELETE FROM tr_drb WHERE drb_id = ?";


}


