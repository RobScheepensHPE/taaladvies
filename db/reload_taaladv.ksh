#!/usr/bin/ksh
#
------------------------------------------------------------------------
------------------------------------------
# reload_taaladv.ksh
# vdabbepe # 21.11.2006
# add: check if active cluster node
#
# 01/2007 : Conny Huvaere : add transactions # 06/2007 : Conny Huvaere :

#             - actualisering bts_tlv via triggers
#             - rebuild index on bts_tlv
#             - bts_tks : insert into ... select ...from
#                            (ter vervaging van unload / load)
#
------------------------------------------------------------------------
------------------------------------------
# 01/08/2007 : Conny
#              Update statistics in commentaar gezet nav 600-02-3737842
#              Indien op een tabel zowel een bts-index als een gewone
btree index bestaat,
#              dan mag geen update statistics gebeuren
#
------------------------------------------------------------------------
------------------------------------------



# ---------------
# Set environment
# ---------------
. /export/home/informix/Instances/dev1150_ol/dev1150_ol.profile
BASEDIR=/export/home/informix/Instances/dev1150_ol/Taaladvies
CLIENT_LOCALE=nl_BE.1252
DB_LOCALE=nl_BE.1252
LOG=$BASEDIR/log/taaladvies.log
MAIL_FROM="dtaaladv@ifxcluster1.vlaanderen.be"
MAIL_TO="dbms@et.vlaanderen.be"
STATUS="UNKNOWN"

export CLIENT_LOCALE DB_LOCALE LOG UDATE

cd $BASEDIR/scripts


# -----------------------
# Hercreeer bts-tables
# -----------------------
#echo "Taaladvies : start reload Bts tables : "`date +"%d/%m/%Y %H:%M:%S"` > $LOG 
#echo "Rebuild index on bts_tlv : started " >> $LOG 
dbaccess taaladvies <<!Eof
   BEGIN WORK ;
   DROP TABLE bts_tlv ;
   CREATE TABLE bts_tlv (tlv_id integer not null , waarde clob, veld varchar (20) ) ;
   REVOKE ALL ON bts_tlv FROM "public" ;
   GRANT SELECT ON bts_tlv TO "<d_taaladvies>" ;
   INSERT INTO bts_tlv   SELECT tlv_id , ttl_db, 'ttl_db' FROM td_tlv_awd;
   INSERT INTO bts_tlv   SELECT tlv_id , tlv_ds, 'tlv_ds' FROM td_tlv_awd;
   INSERT INTO bts_tlv   SELECT tlv_id , awd_krt_db, 'awd_krt_db' FROM td_tlv_awd;
   INSERT INTO bts_tlv   SELECT tlv_id , awd_tol_db, 'awd_tol_db' FROM td_tlv_awd;
   INSERT INTO bts_tlv   SELECT tlv_id , awd_bjz_db, 'awd_bjz_db' FROM td_tlv_awd;
   CREATE INDEX ix_bts_tlv ON bts_tlv (tlv_id) ;
   ALTER TABLE bts_tlv ADD CONSTRAINT (FOREIGN KEY (tlv_id) REFERENCES td_tlv_awd) ;
   CREATE INDEX tlv_bts_bts_tlv_waarde   ON bts_tlv (waarde bts_clob_ops)  
    USING bts (delete='immediate')   IN <bts_tlv_extspace> ;
   COMMIT WORK;
!Eof
if ! [ $? = 0 ] ; then
#   echo "Rebuild index on bts_tlv : failed." >> $LOG
   STATUS="ERROR"
#else
#   echo "Rebuild index on bts_tlv succeeded." >> $LOG
fi

#echo "Rebuild bts_tks : started " >> $LOG
dbaccess taaladvies <<!Eof
   BEGIN WORK;
   DROP TABLE bts_tks ;
   CREATE TABLE bts_tks (       tks_id integer not null ,       waarde clob,       veld varchar (20) ) ;
   REVOKE ALL ON bts_tks FROM "public" ;
   GRANT SELECT ON bts_tks TO "rtaaladv" as "<d_taaladvies>" ;
   INSERT INTO bts_tks   SELECT tks_id , hfd_ttl_ds, 'hfd_ttl_ds' FROM td_tks;
   INSERT INTO bts_tks   SELECT tks_id , tsb_ttl_ds, 'tsb_ttl_ds' FROM td_tsb;
   INSERT INTO bts_tks   SELECT tks_id , tsb_db, 'tsb_db' FROM td_tsb;
   CREATE INDEX ix_bts_tks ON bts_tks (tks_id) ;
   ALTER TABLE bts_tks   ADD CONSTRAINT (FOREIGN KEY (tks_id) REFERENCES td_tks) ;
   CREATE INDEX tlv_bts_bts_tks_waarde   ON bts_tks (waarde bts_clob_ops)  
    USING bts (delete='immediate')   IN <bts_tlv_extspace> ;
   COMMIT WORK;
!Eof
if ! [ $? = 0 ] ; then
#   echo "Rebuild bts_tks : failed." >> $LOG
   STATUS="ERROR"
else
#   echo "Rebuild bts_tks :  succeeded." >> $LOG
   STATUS="SUCCESS"
fi

#dbaccess taaladvies <<!Eof
#   UPDATE STATISTICS HIGH FOR TABLE bts_tlv ;
#   UPDATE STATISTICS HIGH FOR TABLE bts_tks ;
#!Eof


#echo "Taaladvies : stop reload Bts tables : "`date +"%d/%m/%Y %H:%M:%S"` >> $LOG (echo "Subject: App Taaladvies - reload bts_tks : $STATUS";echo;cat "$LOG") | /usr/lib/sendmail -F "$MAIL_FROM" -t ${MAIL_TO}

if [ $STATUS = "ERROR" ] ; then
    exit 104 ;
else
    exit 0
fi
