--
begin work ;
set constraints all deferred ;


--
-- unload tables
--
unload to /infx2/Taaladvies20090730/Unload20090803/td_tks.unl select * from td_tks ;
unload to /infx2/Taaladvies20090730/Unload20090803/td_tsb.unl select * from td_tsb ;
unload to /infx2/Taaladvies20090730/Unload20090803/td_tlv_awd.unl select * from td_tlv_awd ;

unload to /infx2/Taaladvies20090730/Unload20090803/td_tks_new.unl select rvt_id, tks_id, adv_vzk_id, hfd_ttl_htm_db from td_tks ;
unload to /infx2/Taaladvies20090730/Unload20090803/td_tsb_new.unl select tsb_id, tks_id, tsb_ttl_htm_db, tsb_db, tsb_htm_db, tsb_vgn_nr from td_tsb ;
unload to /infx2/Taaladvies20090730/Unload20090803/td_tlv_awd_new.unl select drb_dt, tlv_her_db, tlv_her_htm_db, rvt_id, ttl_htm_db, tlv_id, adv_vzk_id, tlv_spc_htm_db, tlv_inf_db, tlv_inf_htm_db, tlv_vgn_nr, tlv_vrl_agh_fg, awd_krt_htm_db, awd_tol_db, awd_tol_htm_db, awd_bjz_db, awd_bjz_htm_db, tlv_upd_fg from td_tlv_awd ;


--
-- drop tables
--
drop table td_tks ;
drop table td_tsb ;
drop table td_tlv_awd ;


--
-- recreate tables with old layout
--
create table "informix".old_td_tks
  (
    rvt_id integer,
    tks_id serial not null ,
    adv_vzk_id integer not null ,
    hfd_ttl_ds "informix".lvarchar,
    hfd_ttl_htm_db text
  );
revoke all on "informix".old_td_tks from "public";

create table "informix".old_td_tsb
  (
    tsb_id serial not null ,
    tks_id integer not null ,
    tsb_ttl_htm_db text,
    tsb_db "informix".clob,
    tsb_htm_db text,
    tsb_vgn_nr integer not null ,
    tsb_ttl_ds "informix".lvarchar
  );
revoke all on "informix".old_td_tsb from "public";

create table "informix".old_td_tlv_awd
  (
    drb_dt date,
    tlv_her_db text,
    tlv_her_htm_db text,
    rvt_id integer,
    ttl_db "informix".lvarchar,
    ttl_htm_db text,
    tlv_id serial not null ,
    adv_vzk_id integer not null ,
    tlv_ds "informix".lvarchar,
    tlv_spc_htm_db text,
    tlv_inf_db "informix".clob,
    tlv_inf_htm_db text,
    tlv_vgn_nr integer not null ,
    tlv_vrl_agh_fg "informix".boolean not null ,
    awd_krt_db "informix".lvarchar,
    awd_krt_htm_db text,
    awd_tol_db "informix".clob,
    awd_tol_htm_db text,
    awd_bjz_db "informix".clob,
    awd_bjz_htm_db text,
    tlv_upd_fg smallint
  );
revoke all on "informix".old_td_tlv_awd from "public";


--
-- recreate indexes and constraints on tables with old layout
--
create unique index uix_old_td_tks_tks_id on old_td_tks (tks_id) ;
alter table old_td_tks add constraint (primary key (tks_id) constraint "informix".pk_old_td_tks) ;
create index "informix".old_orp_idx on "informix".old_td_tks (adv_vzk_id) using btree ;
alter table "informix".old_td_tks add constraint (foreign key (adv_vzk_id) references "informix".td_opr );
alter table "informix".old_td_tks add constraint (foreign key (rvt_id) references "informix".tp_rvt );

create unique index uix_old_td_tsb_tsb_id on old_td_tsb (tsb_id);
alter table old_td_tsb add constraint (primary key (tsb_id) constraint "informix".old_pk_td_tsb) ;
create index "informix".old_tks_idx on "informix".old_td_tsb (tks_id) using btree ;
alter table "informix".old_td_tsb add constraint (foreign key (tks_id) references "informix".old_td_tks );

create unique index "informix".uix_old_td_tlv_tlv_id on "informix".old_td_tlv_awd (tlv_id) ;
alter table "informix".old_td_tlv_awd add constraint (primary key (tlv_id) constraint pk_old_td_tlv) ;
create index "informix".old_agh_index on "informix".old_td_tlv_awd (tlv_vrl_agh_fg) using btree ;
create index "informix".old_oproep_index on "informix".old_td_tlv_awd (adv_vzk_id) using btree ;
alter table "informix".old_td_tlv_awd add constraint (foreign key (rvt_id) references "informix".tp_rvt );


--
-- load data into tables with old layout
--
load from /infx2/Taaladvies20090730/Unload20090803/td_tks.unl insert into old_td_tks;
load from /infx2/Taaladvies20090730/Unload20090803/td_tsb.unl insert into old_td_tsb;
load from /infx2/Taaladvies20090730/Unload20090803/td_tlv_awd.unl insert into old_td_tlv_awd;


--
-- create tables with new layout
--
create table "informix".td_tks
  (
    rvt_id integer,
    tks_id serial not null ,
    adv_vzk_id integer not null ,
    hfd_ttl_ds clob,
    hfd_ttl_htm_db text
  );
revoke all on "informix".td_tks from "public";

create table "informix".td_tsb
  (
    tsb_id serial not null ,
    tks_id integer not null ,
    tsb_ttl_htm_db text,
    tsb_db "informix".clob,
    tsb_htm_db text,
    tsb_vgn_nr integer not null ,
    tsb_ttl_ds clob,
    PRIMARY KEY(tsb_id)
  );
revoke all on "informix".td_tsb from "public";

create table "informix".td_tlv_awd
  (
    drb_dt date,
    tlv_her_db text,
    tlv_her_htm_db text,
    rvt_id integer,
    ttl_db clob,
    ttl_htm_db text,
    tlv_id serial not null,
    adv_vzk_id integer not null ,
    tlv_ds clob,
    tlv_spc_htm_db text,
    tlv_inf_db "informix".clob,
    tlv_inf_htm_db text,
    tlv_vgn_nr integer not null ,
    tlv_vrl_agh_fg "informix".boolean not null ,
    awd_krt_db clob,
    awd_krt_htm_db text,
    awd_tol_db "informix".clob,
    awd_tol_htm_db text,
    awd_bjz_db "informix".clob,
    awd_bjz_htm_db text,
    tlv_upd_fg smallint,
    PRIMARY KEY(tlv_id)
  );
revoke all on "informix".td_tlv_awd from "public";


--
-- create grants on tables with new and old layout
--

grant select, update, insert, delete on "informix".old_td_tks to "dtaaladv" as "informix";
grant select, update, insert, delete on "informix".td_tks to "dtaaladv" as "informix";

grant select, update, insert, delete on "informix".old_td_tsb to "dtaaladv" as "informix";
grant select, update, insert, delete on "informix".td_tsb to "dtaaladv" as "informix";

grant select, update, insert, delete on "informix".old_td_tlv_awd to "dtaaladv" as "informix";
grant select, update, insert, delete on "informix".td_tlv_awd to "dtaaladv" as "informix";


--
-- recreate indexes and constraints on tables with new layout
--
alter table td_tks add constraint (primary key (tks_id)) ;
create index "informix".orp_idx on "informix".td_tks (adv_vzk_id) using btree;

alter table "informix".td_tks add constraint (foreign key (adv_vzk_id) references "informix".td_opr constraint fk_td_tks_td_opr);
alter table "informix".td_tks add constraint (foreign key (rvt_id) references "informix".tp_rvt constraint fk_td_tks_tp_rvt);

alter table "informix".td_tsb add constraint (foreign key (tks_id) references "informix".td_tks constraint fk_td_tsb_td_tks);

create index "informix".agh_index on "informix".td_tlv_awd (tlv_vrl_agh_fg) using btree ;
create index "informix".oproep_index on "informix".td_tlv_awd (adv_vzk_id) using btree ;
alter table "informix".td_tlv_awd add constraint (foreign key (rvt_id) references "informix".tp_rvt constraint fk_td_tlv_awd_tp_rvt) ;


--
-- load data into tables with new layout
--
load from /infx2/Taaladvies20090730/Unload20090803/td_tks_new.unl insert into td_tks (rvt_id, tks_id, adv_vzk_id, hfd_ttl_htm_db) ;
load from /infx2/Taaladvies20090730/Unload20090803/td_tsb_new.unl insert into td_tsb (tsb_id, tks_id, tsb_ttl_htm_db, tsb_db, tsb_htm_db, tsb_vgn_nr) ;
load from /infx2/Taaladvies20090730/Unload20090803/td_tlv_awd_new.unl insert into td_tlv_awd (drb_dt, tlv_her_db, tlv_her_htm_db, rvt_id, ttl_htm_db, tlv_id, adv_vzk_id, tlv_spc_htm_db, tlv_inf_db, tlv_inf_htm_db, tlv_vgn_nr, tlv_vrl_agh_fg, awd_krt_htm_db, awd_tol_db, awd_tol_htm_db, awd_bjz_db, awd_bjz_htm_db, tlv_upd_fg) ;


drop table td_taalunie_xml ;
create table td_taalunie_xml
(
	xml_id serial,
	ophaaldatum datetime YEAR TO SECOND not null unique,
	ophaalurl lvarchar not null,
	taalvragen_xml "informix".clob not null,
	foutboodschap "informix".clob
);

-- voor de taalvragen

CREATE TABLE bts_tlv (
	tlv_id integer not null ,
	waarde clob, veld varchar (20)
);

ALTER TABLE bts_tlv ADD CONSTRAINT (FOREIGN KEY (tlv_id) REFERENCES td_tlv_awd) ;

-- Index aanmaken (achteraan de juiste SMART BLOB SPACE specifiëren !!!)
CREATE INDEX tlv_bts_bts_tlv_waarde   ON bts_tlv (waarde bts_clob_ops)   USING bts (delete='immediate')   IN bts_tlv_extspace ;



---------------------------



-- voor de teksten

CREATE TABLE bts_tks (
       tks_id integer not null ,
       waarde clob,
       veld varchar (20)
);


ALTER TABLE bts_tks   ADD CONSTRAINT (FOREIGN KEY (tks_id) REFERENCES td_tks) ;

-- Index aanmaken (achteraan de juiste SMART BLOB SPACE specifiëren !!!)
CREATE INDEX tlv_bts_bts_tks_waarde   ON bts_tks (waarde bts_clob_ops)   USING bts (delete='immediate')   IN bts_tlv_extspace ;





commit ;


