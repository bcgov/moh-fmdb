-------------------------------------------------
--          Drop Constraints                   --
-------------------------------------------------
ALTER TABLE fmdb.fmdb_addresses DROP CONSTRAINT adr_adrt_fk;

ALTER TABLE fmdb.fmdb_addresses DROP CONSTRAINT adr_cn_fk;

ALTER TABLE fmdb.fmdb_addresses DROP CONSTRAINT adr_com_fk;

ALTER TABLE fmdb.fmdb_addresses DROP CONSTRAINT adr_reg_fk;

ALTER TABLE fmdb.fmdb_chemicals DROP CONSTRAINT che_sub_fk;

ALTER TABLE fmdb.fmdb_companies DROP CONSTRAINT com_ct1_fk;

ALTER TABLE fmdb.fmdb_contact_details DROP CONSTRAINT cd_cn_fk;

ALTER TABLE fmdb.fmdb_contact_details DROP CONSTRAINT cd_com_fk;

ALTER TABLE fmdb.fmdb_contact_names DROP CONSTRAINT cn_com_fk;

ALTER TABLE fmdb.fmdb_contact_names DROP CONSTRAINT cn_ct_fk;

ALTER TABLE fmdb.fmdb_dins DROP CONSTRAINT din_che_fk;

ALTER TABLE fmdb.fmdb_economic_statuses DROP CONSTRAINT es_sub_fk;

ALTER TABLE fmdb.fmdb_pla_logs DROP CONSTRAINT pl_pla_fk;

ALTER TABLE fmdb.fmdb_product_listing_agreement DROP CONSTRAINT pla_pst_fk;

ALTER TABLE fmdb.fmdb_product_listing_agreement DROP CONSTRAINT pla_sub_fk;

ALTER TABLE fmdb.fmdb_regions DROP CONSTRAINT reg_ctry_fk;

ALTER TABLE fmdb.fmdb_review_questions DROP CONSTRAINT rq_rnt_fk;

ALTER TABLE fmdb.fmdb_review_questions DROP CONSTRAINT rq_srd_fk;

ALTER TABLE fmdb.fmdb_submission DROP CONSTRAINT sub_cn_fk;

ALTER TABLE fmdb.fmdb_submission DROP CONSTRAINT sub_com_fk;

ALTER TABLE fmdb.fmdb_submission DROP CONSTRAINT sub_st_fk;

ALTER TABLE fmdb.fmdb_submission_review_details DROP CONSTRAINT srd_brc_fk;

ALTER TABLE fmdb.fmdb_submission_review_details DROP CONSTRAINT srd_rs_fk;

ALTER TABLE fmdb.fmdb_submission_review_details DROP CONSTRAINT srd_sub_fk;

ALTER TABLE fmdb.databasechangeloglock DROP CONSTRAINT pk_databasechangeloglock;

ALTER TABLE fmdb.fmdb_address_types DROP CONSTRAINT adrt_pk;

ALTER TABLE fmdb.fmdb_address_types DROP CONSTRAINT avcon_1202340926_activ_005;

ALTER TABLE fmdb.fmdb_addresses DROP CONSTRAINT adr_pk;

ALTER TABLE fmdb.fmdb_bia_requested_codes DROP CONSTRAINT avcon_1202340926_activ_006;

ALTER TABLE fmdb.fmdb_bia_requested_codes DROP CONSTRAINT brc_pk;

ALTER TABLE fmdb.fmdb_chemicals DROP CONSTRAINT che_pk;

ALTER TABLE fmdb.fmdb_chemicals DROP CONSTRAINT che_sub_uniq;

ALTER TABLE fmdb.fmdb_companies DROP CONSTRAINT com_pk;

ALTER TABLE fmdb.fmdb_company_types DROP CONSTRAINT avcon_1202340926_activ_001;

ALTER TABLE fmdb.fmdb_company_types DROP CONSTRAINT ct1_pk;

ALTER TABLE fmdb.fmdb_contact_details DROP CONSTRAINT cd_pk;

ALTER TABLE fmdb.fmdb_contact_names DROP CONSTRAINT cn_pk;

ALTER TABLE fmdb.fmdb_contact_types DROP CONSTRAINT avcon_1202340926_activ_008;

ALTER TABLE fmdb.fmdb_contact_types DROP CONSTRAINT ct_pk;

ALTER TABLE fmdb.fmdb_countries DROP CONSTRAINT avcon_1202340926_activ_003;

ALTER TABLE fmdb.fmdb_countries DROP CONSTRAINT ctry_pk;

ALTER TABLE fmdb.fmdb_dins DROP CONSTRAINT din_pk;

ALTER TABLE fmdb.fmdb_economic_statuses DROP CONSTRAINT es_pk;

ALTER TABLE fmdb.fmdb_pla_logs DROP CONSTRAINT pl_pk;

ALTER TABLE fmdb.fmdb_pla_status_types DROP CONSTRAINT avcon_1202340926_activ_002;

ALTER TABLE fmdb.fmdb_pla_status_types DROP CONSTRAINT pst_pk;

ALTER TABLE fmdb.fmdb_product_listing_agreement DROP CONSTRAINT pla_pk;

ALTER TABLE fmdb.fmdb_regions DROP CONSTRAINT avcon_1202340926_activ_004;

ALTER TABLE fmdb.fmdb_regions DROP CONSTRAINT reg_pk;

ALTER TABLE fmdb.fmdb_review_questions DROP CONSTRAINT avcon_1202340926_psd_r_000;

ALTER TABLE fmdb.fmdb_review_questions DROP CONSTRAINT rq_pk;

ALTER TABLE fmdb.fmdb_review_statuses DROP CONSTRAINT rs_pk;

ALTER TABLE fmdb.fmdb_reviewer_name_types DROP CONSTRAINT avcon_1202340926_activ_007;

ALTER TABLE fmdb.fmdb_reviewer_name_types DROP CONSTRAINT rnt_pk;

ALTER TABLE fmdb.fmdb_submission DROP CONSTRAINT avcon_1202340926_post_000;

ALTER TABLE fmdb.fmdb_submission DROP CONSTRAINT sub_pk;

ALTER TABLE fmdb.fmdb_submission DROP CONSTRAINT sub_submission_no_uk;

ALTER TABLE fmdb.fmdb_submission_review_details DROP CONSTRAINT avcon_1202340926_cdr_r_000;

ALTER TABLE fmdb.fmdb_submission_review_details DROP CONSTRAINT avcon_1202340926_lette_000;

ALTER TABLE fmdb.fmdb_submission_review_details DROP CONSTRAINT avcon_1202340926_sa_re_000;

ALTER TABLE fmdb.fmdb_submission_review_details DROP CONSTRAINT srd_pk;

ALTER TABLE fmdb.fmdb_submission_types DROP CONSTRAINT avcon_1202340926_activ_000;

ALTER TABLE fmdb.fmdb_submission_types DROP CONSTRAINT st_pk;

ALTER TABLE fmdb.person_pwn DROP CONSTRAINT person_pwn_pk;
-------------------------------------------------
--          Drop Sequences                     --
-------------------------------------------------
DROP SEQUENCE IF EXISTS fmdb.fmdb_adr_seq;
DROP SEQUENCE IF EXISTS fmdb.fmdb_cd_seq;
DROP SEQUENCE IF EXISTS fmdb.fmdb_che_seq;
DROP SEQUENCE IF EXISTS fmdb.fmdb_cn_seq;
DROP SEQUENCE IF EXISTS fmdb.fmdb_com_seq;
DROP SEQUENCE IF EXISTS fmdb.fmdb_din_seq;
DROP SEQUENCE IF EXISTS fmdb.fmdb_es_seq;
DROP SEQUENCE IF EXISTS fmdb.fmdb_pl_seq;
DROP SEQUENCE IF EXISTS fmdb.fmdb_pla_seq;
DROP SEQUENCE IF EXISTS fmdb.fmdb_rq_seq;
DROP SEQUENCE IF EXISTS fmdb.fmdb_srd_seq;
DROP SEQUENCE IF EXISTS fmdb.fmdb_sub_seq;
DROP SEQUENCE IF EXISTS fmdb.person_pwn_pk_seq;
-------------------------------------------------
--          Delete Data from Tables            --
-------------------------------------------------
DELETE FROM fmdb.databasechangeloglock;
DELETE FROM fmdb.fmdb_address_types;
DELETE FROM fmdb.fmdb_addresses;
DELETE FROM fmdb.fmdb_bia_requested_codes;
DELETE FROM fmdb.fmdb_chemicals;
DELETE FROM fmdb.fmdb_companies;
DELETE FROM fmdb.fmdb_company_types;
DELETE FROM fmdb.fmdb_contact_details;
DELETE FROM fmdb.fmdb_contact_names;
DELETE FROM fmdb.fmdb_contact_types;
DELETE FROM fmdb.fmdb_countries;
DELETE FROM fmdb.fmdb_dins;
DELETE FROM fmdb.fmdb_economic_statuses;
DELETE FROM fmdb.fmdb_pla_logs;
DELETE FROM fmdb.fmdb_pla_status_types;
DELETE FROM fmdb.fmdb_product_listing_agreement;
DELETE FROM fmdb.fmdb_regions;
DELETE FROM fmdb.fmdb_review_questions;
DELETE FROM fmdb.fmdb_review_statuses;
DELETE FROM fmdb.fmdb_reviewer_name_types;
DELETE FROM fmdb.fmdb_submission;
DELETE FROM fmdb.fmdb_submission_review_details;
DELETE FROM fmdb.fmdb_submission_types;
DELETE FROM fmdb.person_pwn;