-- ------------ Write DROP-TRIGGER-stage scripts -----------

DROP TRIGGER IF EXISTS person_pwn_pk_trg
ON fmdb.person_pwn;

-- ------------ Write DROP-FUNCTION-stage scripts -----------

DROP ROUTINE IF EXISTS fmdb.fmdb_microstrategy$businessdaysbetween(IN TIMESTAMP WITHOUT TIME ZONE, IN TIMESTAMP WITHOUT TIME ZONE);

DROP ROUTINE IF EXISTS fmdb.person_pwn_pk_trg$person_pwn();

-- ------------ Write DROP-FOREIGN-KEY-CONSTRAINT-stage scripts -----------

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

-- ------------ Write DROP-CONSTRAINT-stage scripts -----------

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

-- ------------ Write DROP-INDEX-stage scripts -----------

DROP INDEX IF EXISTS fmdb.adr_adrt_fk_i;

DROP INDEX IF EXISTS fmdb.adr_cn_fk_i;

DROP INDEX IF EXISTS fmdb.adr_com_fk_i;

DROP INDEX IF EXISTS fmdb.adr_reg_fk_i;

DROP INDEX IF EXISTS fmdb.com_ct1_fk_i;

DROP INDEX IF EXISTS fmdb.cd_cn_fk_i;

DROP INDEX IF EXISTS fmdb.cd_com_fk_i;

DROP INDEX IF EXISTS fmdb.cn_com_fk_i;

DROP INDEX IF EXISTS fmdb.cn_ct_fk_i;

DROP INDEX IF EXISTS fmdb.din_che_fk_i;

DROP INDEX IF EXISTS fmdb.es_sub_fk_i;

DROP INDEX IF EXISTS fmdb.pl_pla_fk_i;

DROP INDEX IF EXISTS fmdb.pla_pst_fk_i;

DROP INDEX IF EXISTS fmdb.pla_sub_fk_i;

DROP INDEX IF EXISTS fmdb.reg_ctry_fk_i;

DROP INDEX IF EXISTS fmdb.rq_rnt_fk_i;

DROP INDEX IF EXISTS fmdb.rq_srd_fk_i;

DROP INDEX IF EXISTS fmdb.sub_com_fk_i;

DROP INDEX IF EXISTS fmdb.sub_st_fk_i;

DROP INDEX IF EXISTS fmdb.srd_brc_fk_i;

DROP INDEX IF EXISTS fmdb.srd_rs_fk_i;

DROP INDEX IF EXISTS fmdb.srd_sub_fk_i;

-- ------------ Write DROP-VIEW-stage scripts -----------

DROP VIEW IF EXISTS fmdb.fmdb_budget_impact_analysis_vw;

DROP VIEW IF EXISTS fmdb.fmdb_drug_benefit_committee_vw;

DROP VIEW IF EXISTS fmdb.fmdb_public_access_vw;

DROP VIEW IF EXISTS fmdb.fmdb_single_drug_sub_status_vw;

DROP VIEW IF EXISTS fmdb.fmdb_special_auth_forms_vw;

DROP VIEW IF EXISTS fmdb.fmdb_subm_by_applicant_vw;

DROP VIEW IF EXISTS fmdb.fmdb_submissions_vw;

DROP VIEW IF EXISTS fmdb.fmdb_technical_review_vw;

-- ------------ Write DROP-TABLE-stage scripts -----------

DROP TABLE IF EXISTS fmdb.databasechangeloglock;

DROP TABLE IF EXISTS fmdb.fmdb_address_types;

DROP TABLE IF EXISTS fmdb.fmdb_addresses;

DROP TABLE IF EXISTS fmdb.fmdb_bia_requested_codes;

DROP TABLE IF EXISTS fmdb.fmdb_chemicals;

DROP TABLE IF EXISTS fmdb.fmdb_companies;

DROP TABLE IF EXISTS fmdb.fmdb_company_types;

DROP TABLE IF EXISTS fmdb.fmdb_contact_details;

DROP TABLE IF EXISTS fmdb.fmdb_contact_names;

DROP TABLE IF EXISTS fmdb.fmdb_contact_types;

DROP TABLE IF EXISTS fmdb.fmdb_countries;

DROP TABLE IF EXISTS fmdb.fmdb_dins;

DROP TABLE IF EXISTS fmdb.fmdb_economic_statuses;

DROP TABLE IF EXISTS fmdb.fmdb_pla_logs;

DROP TABLE IF EXISTS fmdb.fmdb_pla_status_types;

DROP TABLE IF EXISTS fmdb.fmdb_product_listing_agreement;

DROP TABLE IF EXISTS fmdb.fmdb_regions;

DROP TABLE IF EXISTS fmdb.fmdb_review_questions;

DROP TABLE IF EXISTS fmdb.fmdb_review_statuses;

DROP TABLE IF EXISTS fmdb.fmdb_reviewer_name_types;

DROP TABLE IF EXISTS fmdb.fmdb_submission;

DROP TABLE IF EXISTS fmdb.fmdb_submission_review_details;

DROP TABLE IF EXISTS fmdb.fmdb_submission_types;

DROP TABLE IF EXISTS fmdb.person_pwn;

-- ------------ Write DROP-SEQUENCE-stage scripts -----------

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

-- ------------ Write DROP-DATABASE-stage scripts -----------

-- ------------ Write CREATE-DATABASE-stage scripts -----------

CREATE SCHEMA IF NOT EXISTS fmdb;

-- ------------ Write CREATE-SEQUENCE-stage scripts -----------

CREATE SEQUENCE IF NOT EXISTS fmdb.fmdb_adr_seq
INCREMENT BY 1
START WITH 911
MAXVALUE 9223372036854775807
MINVALUE 1
NO CYCLE
CACHE 20;

CREATE SEQUENCE IF NOT EXISTS fmdb.fmdb_cd_seq
INCREMENT BY 1
START WITH 961
MAXVALUE 9223372036854775807
MINVALUE 1
NO CYCLE
CACHE 20;



CREATE SEQUENCE IF NOT EXISTS fmdb.fmdb_che_seq
INCREMENT BY 1
START WITH 11654
MAXVALUE 9223372036854775807
MINVALUE 1
NO CYCLE
CACHE 20;

CREATE SEQUENCE IF NOT EXISTS fmdb.fmdb_cn_seq
INCREMENT BY 1
START WITH 703
MAXVALUE 9223372036854775807
MINVALUE 1
NO CYCLE
CACHE 20;

CREATE SEQUENCE IF NOT EXISTS fmdb.fmdb_com_seq
INCREMENT BY 1
START WITH 594
MAXVALUE 9223372036854775807
MINVALUE 1
NO CYCLE
CACHE 20;

CREATE SEQUENCE IF NOT EXISTS fmdb.fmdb_din_seq
INCREMENT BY 1
START WITH 2071
MAXVALUE 9223372036854775807
MINVALUE 1
NO CYCLE
CACHE 20;

CREATE SEQUENCE IF NOT EXISTS fmdb.fmdb_es_seq
INCREMENT BY 1
START WITH 1975
MAXVALUE 9223372036854775807
MINVALUE 1
NO CYCLE
CACHE 20;

CREATE SEQUENCE IF NOT EXISTS fmdb.fmdb_pl_seq
INCREMENT BY 1
START WITH 219
MAXVALUE 9223372036854775807
MINVALUE 1
NO CYCLE
CACHE 20;

CREATE SEQUENCE IF NOT EXISTS fmdb.fmdb_pla_seq
INCREMENT BY 1
START WITH 856
MAXVALUE 9223372036854775807
MINVALUE 1
NO CYCLE
CACHE 20;

CREATE SEQUENCE IF NOT EXISTS fmdb.fmdb_rq_seq
INCREMENT BY 1
START WITH 361
MAXVALUE 9223372036854775807
MINVALUE 1
NO CYCLE
CACHE 20;

CREATE SEQUENCE IF NOT EXISTS fmdb.fmdb_srd_seq
INCREMENT BY 1
START WITH 1619
MAXVALUE 9223372036854775807
MINVALUE 1
NO CYCLE
CACHE 20;

CREATE SEQUENCE IF NOT EXISTS fmdb.fmdb_sub_seq
INCREMENT BY 1
START WITH 11702
MAXVALUE 9223372036854775807
MINVALUE 1
NO CYCLE
CACHE 20;

CREATE SEQUENCE IF NOT EXISTS fmdb.person_pwn_pk_seq
INCREMENT BY 1
START WITH 19
MAXVALUE 9223372036854775807
MINVALUE 1
NO CYCLE
CACHE 1;

-- ------------ Write CREATE-TABLE-stage scripts -----------

CREATE TABLE fmdb.databasechangeloglock(
    id NUMERIC(38,0) NOT NULL,
    locked NUMERIC(1,0) NOT NULL,
    lockgranted TIMESTAMP(6) WITHOUT TIME ZONE,
    lockedby CHARACTER VARYING(255)
)
        WITH (
        OIDS=FALSE
        );

CREATE TABLE fmdb.fmdb_address_types(
    address_type_cd CHARACTER VARYING(15) NOT NULL,
    address_type_dsc CHARACTER VARYING(250) NOT NULL DEFAULT 'COMPANY ADDRESS',
    active_yn CHARACTER(1) NOT NULL DEFAULT 'Y',
    sort_order_nbr CHARACTER VARYING(5),
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    last_modified_by_nm CHARACTER VARYING(30),
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );

CREATE TABLE fmdb.fmdb_addresses(
    address_id NUMERIC(15,0) NOT NULL,
    address_first_line_txt CHARACTER VARYING(30),
    address_second_line_txt CHARACTER VARYING(30),
    postal_code_txt CHARACTER VARYING(10),
    city_nm CHARACTER VARYING(25),
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    last_modified_by_nm CHARACTER VARYING(30),
    company_id NUMERIC(15,0),
    address_type_cd CHARACTER VARYING(15) NOT NULL,
    region_cd CHARACTER VARYING(15),
    country_cd CHARACTER VARYING(15),
    contact_name_id NUMERIC(15,0),
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );
COMMENT ON COLUMN fmdb.fmdb_addresses.address_first_line_txt
     IS 'the first line of an address';
COMMENT ON COLUMN fmdb.fmdb_addresses.address_second_line_txt
     IS 'the second line of an address';
COMMENT ON COLUMN fmdb.fmdb_addresses.postal_code_txt
     IS 'contains zip code or canadian postal code';
COMMENT ON COLUMN fmdb.fmdb_addresses.city_nm
     IS 'the  name of the city';
COMMENT ON COLUMN fmdb.fmdb_addresses.contact_name_id
     IS 'a unique system generated ID';

CREATE TABLE fmdb.fmdb_bia_requested_codes(
    bia_requsted_cd CHARACTER VARYING(15) NOT NULL,
    bia_requested_dsc CHARACTER VARYING(250) NOT NULL,
    active_yn CHARACTER(1) NOT NULL,
    sort_order_nbr CHARACTER VARYING(5),
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    last_modified_by_nm CHARACTER VARYING(30),
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );

CREATE TABLE fmdb.fmdb_chemicals(
    chemical_id NUMERIC(15,0) NOT NULL,
    chemical_nm CHARACTER VARYING(120) NOT NULL,
    medical_indications_txt CHARACTER VARYING(4000),
    market_release_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    noc_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    patent_expiry_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    trade_nm CHARACTER VARYING(120),
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    last_modified_by_nm CHARACTER VARYING(30),
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    submission_id NUMERIC(15,0) NOT NULL,
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );
COMMENT ON COLUMN fmdb.fmdb_chemicals.chemical_nm
     IS 'the chemical or generic name of the drug(s) Chemical name, a long example, 61 characters:';
COMMENT ON COLUMN fmdb.fmdb_chemicals.medical_indications_txt
     IS 'Clinical Condition the drug is used to treat.  "Indication, example 1, 883 characters: 

Community-acquired pneumonia (mild to moderate) due to S. pneumoniae, including multi-drug resistant strains (MDRSP){*MDRSP: Multi-drug resistant S. pneumoniae are isolates resistant to two or more of the following antibiotics: penicillin (penicillin-resistant S. pneumoniae or PRSP), macrolides (erythromycin/macrolide-resistant S. pneumoniae or ERSP/MRSP), 2nd generation cephalosporins (e.g., cefuroxime), tetracyclines and trimethoprim-sulfamethoxazole.}, H. influenzae, M. catarrhalis, C. (Chlamydia) pneumoniae, L. pneumophila, M. pneumoniae, S. aureus.
Acute bacterial exacerbation of chronic bronchitis due to S. pneumoniae, H. influenzae, M. catarrhalis.
Acute sinusitis due to S. pneumoniae, H. influenzae, M. catarrhalis, S. aureus.
Tonsillitis/Pharyngitis due to S. pyogenes (group A-? Hemolytic Streptococci) as an alternative when ?-lactam antibiotics are not appropriate.

Indication, example 2, 1507 characters:

Rheumatoid Arthritis
reducing the signs and symptoms, inducing major clinical response and clinical remission, inhibiting the progression of structural damage and improving physical function in adult patients with moderately to severely active rheumatoid arthritis. HUMIRA can be used alone or in combination with methotrexate (MTX) or other Disease-Modifying Anti-rheumatic Drugs (DMARDs).
When used as first-line treatment in recently diagnosed patients who have not been previously treated with methotrexate (MTX), HUMIRA should be given in combination with MTX. HUMIRA can be given as monotherapy in case of intolerance to MTX or when treatment with MTX is contraindicated.
Psoriatic Arthritis
reducing the signs and symptoms of active arthritis in adult psoriatic arthritis patients. HUMIRA can be used in combination with methotrexate (MTX) in patients who do not respond adequately to methotrexate alone.
Ankylosing Spondylitis
reducing signs and symptoms in patients with ac';
COMMENT ON COLUMN fmdb.fmdb_chemicals.market_release_dt
     IS 'the date the drug was released on the market.';
COMMENT ON COLUMN fmdb.fmdb_chemicals.noc_dt
     IS 'The date health Canada issued notice of compliance approval.';
COMMENT ON COLUMN fmdb.fmdb_chemicals.patent_expiry_dt
     IS 'The date the Patent expires on a brand  name drug.';
COMMENT ON COLUMN fmdb.fmdb_chemicals.trade_nm
     IS 'the trade or brand name of the drug(s)';
COMMENT ON COLUMN fmdb.fmdb_chemicals.submission_id
     IS 'A system generated unique identifier for a submission number';

CREATE TABLE fmdb.fmdb_companies(
    company_id NUMERIC(15,0) NOT NULL,
    legal_nm CHARACTER VARYING(120) NOT NULL,
    alias_nm CHARACTER VARYING(40),
    expiry_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    expiry_reason_dsc CHARACTER VARYING(40),
    start_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    last_modified_by_nm CHARACTER VARYING(30),
    company_type_cd CHARACTER VARYING(15) NOT NULL,
    comment_txt CHARACTER VARYING(4000),
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );
COMMENT ON COLUMN fmdb.fmdb_companies.legal_nm
     IS 'The legal name of a  company or companies';
COMMENT ON COLUMN fmdb.fmdb_companies.alias_nm
     IS 'Also Known as name or alias';
COMMENT ON COLUMN fmdb.fmdb_companies.expiry_dt
     IS 'When the company expires, which could be caused by mergers , acquisitions and or bankrupt, or some other business failure.';
COMMENT ON COLUMN fmdb.fmdb_companies.expiry_reason_dsc
     IS 'The reason for the termination.';
COMMENT ON COLUMN fmdb.fmdb_companies.start_dt
     IS 'the date Moh enters the company into the database.';
COMMENT ON COLUMN fmdb.fmdb_companies.created_on_dtm
     IS 'date recorded created or entered';
COMMENT ON COLUMN fmdb.fmdb_companies.created_by_nm
     IS 'the userid who created or entered the record';
COMMENT ON COLUMN fmdb.fmdb_companies.last_modified_dtm
     IS 'the date the record was updated.';
COMMENT ON COLUMN fmdb.fmdb_companies.last_modified_by_nm
     IS 'the userid who updated the record.';
COMMENT ON COLUMN fmdb.fmdb_companies.company_type_cd
     IS 'the code for the description';

CREATE TABLE fmdb.fmdb_company_types(
    company_type_cd CHARACTER VARYING(15) NOT NULL,
    company_type_dsc CHARACTER VARYING(250) NOT NULL,
    active_yn CHARACTER(1) NOT NULL DEFAULT 'Y',
    sort_order_nbr CHARACTER VARYING(5),
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    last_modified_by_nm CHARACTER VARYING(30),
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );
COMMENT ON COLUMN fmdb.fmdb_company_types.company_type_cd
     IS 'the code for the description';
COMMENT ON COLUMN fmdb.fmdb_company_types.company_type_dsc
     IS 'the full description of the code';
COMMENT ON COLUMN fmdb.fmdb_company_types.created_on_dtm
     IS 'Identifies when the information was created.';
COMMENT ON COLUMN fmdb.fmdb_company_types.created_by_nm
     IS 'Identifies the userid or who created the information.';
COMMENT ON COLUMN fmdb.fmdb_company_types.last_modified_dtm
     IS 'the date the record was updated.';
COMMENT ON COLUMN fmdb.fmdb_company_types.last_modified_by_nm
     IS 'the user who updated the record.';

CREATE TABLE fmdb.fmdb_contact_details(
    contact_dtl_id NUMERIC(15,0) NOT NULL,
    cell_no CHARACTER VARYING(15),
    email_address CHARACTER VARYING(320),
    fax_no CHARACTER VARYING(15),
    work_phone_no CHARACTER VARYING(15),
    work_ph_ext_no CHARACTER VARYING(5),
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    last_modified_by_nm CHARACTER VARYING(30),
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    company_id NUMERIC(15,0),
    contact_name_id NUMERIC(15,0),
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );
COMMENT ON COLUMN fmdb.fmdb_contact_details.contact_dtl_id
     IS 'a unique system generated ID';
COMMENT ON COLUMN fmdb.fmdb_contact_details.cell_no
     IS 'cell phone number';
COMMENT ON COLUMN fmdb.fmdb_contact_details.email_address
     IS 'email address of contact';
COMMENT ON COLUMN fmdb.fmdb_contact_details.fax_no
     IS 'fax number at work.';
COMMENT ON COLUMN fmdb.fmdb_contact_details.work_phone_no
     IS 'work phone number + area code';
COMMENT ON COLUMN fmdb.fmdb_contact_details.work_ph_ext_no
     IS 'extension number at work';
COMMENT ON COLUMN fmdb.fmdb_contact_details.contact_name_id
     IS 'a unique system generated ID';

CREATE TABLE fmdb.fmdb_contact_names(
    contact_name_id NUMERIC(15,0) NOT NULL,
    first_nm CHARACTER VARYING(25),
    last_nm CHARACTER VARYING(25),
    job_title_nm CHARACTER VARYING(120),
    effective_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    expiry_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    last_modified_by_nm CHARACTER VARYING(30),
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    company_id NUMERIC(15,0),
    contact_type_cd CHARACTER VARYING(15) NOT NULL,
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );
COMMENT ON COLUMN fmdb.fmdb_contact_names.contact_name_id
     IS 'a unique system generated ID';
COMMENT ON COLUMN fmdb.fmdb_contact_names.first_nm
     IS 'first name of person';
COMMENT ON COLUMN fmdb.fmdb_contact_names.last_nm
     IS 'Last Name';
COMMENT ON COLUMN fmdb.fmdb_contact_names.job_title_nm
     IS 'the official position of the person';
COMMENT ON COLUMN fmdb.fmdb_contact_names.effective_dt
     IS 'The date the Ministry of Health is aware of the contact';
COMMENT ON COLUMN fmdb.fmdb_contact_names.expiry_dt
     IS 'contact no longer exists';

CREATE TABLE fmdb.fmdb_contact_types(
    contact_type_cd CHARACTER VARYING(15) NOT NULL,
    contact_type_dsc CHARACTER VARYING(250) NOT NULL,
    active_yn CHARACTER(1) NOT NULL DEFAULT 'Y',
    sort_order_nbr CHARACTER VARYING(5),
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    last_modified_by_nm CHARACTER VARYING(30),
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );
COMMENT ON COLUMN fmdb.fmdb_contact_types.active_yn
     IS ' YN';

CREATE TABLE fmdb.fmdb_countries(
    country_cd CHARACTER VARYING(15) NOT NULL,
    country_dsc CHARACTER VARYING(250) NOT NULL,
    sort_order_nbr CHARACTER VARYING(5),
    active_yn CHARACTER(1) NOT NULL,
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    last_modified_by_nm CHARACTER VARYING(30),
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );

CREATE TABLE fmdb.fmdb_dins(
    din_id NUMERIC(15,0) NOT NULL,
    din_no NUMERIC(8,0),
    dosage_form_txt CHARACTER VARYING(120),
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    created_oin_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    last_modified_by_nm CHARACTER VARYING(30),
    lasted_modfied_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    chemical_id NUMERIC(15,0) NOT NULL,
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );
COMMENT ON COLUMN fmdb.fmdb_dins.din_id
     IS 'A unique identifier for DIN';
COMMENT ON COLUMN fmdb.fmdb_dins.din_no
     IS 'an 8 digit number ,assigned by the Therapeutic Produc ts directorate of Health Canada, to each drug approved for use in Canada in accordance with the Food and drug Regulation. the same druge (e.g. Amoxicillin, 250 mg capsules) can have several different DINS assocaited with it due to different mfts.';
COMMENT ON COLUMN fmdb.fmdb_dins.dosage_form_txt
     IS 'the quantity and how the drug is administered.';
COMMENT ON COLUMN fmdb.fmdb_dins.created_by_nm
     IS 'The userid of who created the information.';
COMMENT ON COLUMN fmdb.fmdb_dins.created_oin_dtm
     IS 'the date and time of when the information was created .';
COMMENT ON COLUMN fmdb.fmdb_dins.last_modified_by_nm
     IS 'The userid of who modified the information.';
COMMENT ON COLUMN fmdb.fmdb_dins.lasted_modfied_dtm
     IS 'The date and time of when the information was modified.';

CREATE TABLE fmdb.fmdb_economic_statuses(
    economic_id NUMERIC(15,0) NOT NULL,
    relative_year_no NUMERIC(38,0),
    psd_patient_actuals_qty NUMERIC(15,0),
    psd_expenditure_amt CHARACTER VARYING(15),
    psd_prediction_amt CHARACTER VARYING(15),
    psd_actual_amt CHARACTER VARYING(15),
    bc_actual_amt CHARACTER VARYING(15),
    analysis_notes CHARACTER VARYING(4000),
    bc_num_patient_actuals_qty NUMERIC(15,0),
    company_prediction_amt CHARACTER VARYING(15),
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    last_modified_by_nm CHARACTER VARYING(30),
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    submission_id NUMERIC(15,0),
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );
COMMENT ON COLUMN fmdb.fmdb_economic_statuses.economic_id
     IS 'Unique key. Generated from sequence';
COMMENT ON COLUMN fmdb.fmdb_economic_statuses.relative_year_no
     IS 'The year  the PSD is tracking the information. Values could be  -1 -2 -3 or +1 +2 +3.';
COMMENT ON COLUMN fmdb.fmdb_economic_statuses.psd_patient_actuals_qty
     IS 'the number of PSD beneficiaries for the drug.';
COMMENT ON COLUMN fmdb.fmdb_economic_statuses.psd_expenditure_amt
     IS 'the dollar amount  spent by PSD on the drug, expressed to 3 decimal places.';
COMMENT ON COLUMN fmdb.fmdb_economic_statuses.psd_prediction_amt
     IS 'the dollar amount predicted by  PSD , expressed to 3 decimal places.';
COMMENT ON COLUMN fmdb.fmdb_economic_statuses.bc_actual_amt
     IS 'the amount spent in bc on the drug, expressed to 3 decimal places.';
COMMENT ON COLUMN fmdb.fmdb_economic_statuses.analysis_notes
     IS 'any notes relevant to the economic part of the drug.';
COMMENT ON COLUMN fmdb.fmdb_economic_statuses.bc_num_patient_actuals_qty
     IS 'number of patients who  received the drug in BC; the number patients that purchased the drug in BC.';
COMMENT ON COLUMN fmdb.fmdb_economic_statuses.company_prediction_amt
     IS 'the dollar amount predicted by the company , expressed to 3 decimal places.';
COMMENT ON COLUMN fmdb.fmdb_economic_statuses.submission_id
     IS 'A system generated unique identifier for a submission number';

CREATE TABLE fmdb.fmdb_pla_logs(
    pla_log_id NUMERIC(15,0) NOT NULL,
    activity_details_txt CHARACTER VARYING(4000),
    activity_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    last_modified_by_nm CHARACTER VARYING(30),
    pla_id NUMERIC(15,0) NOT NULL,
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );
COMMENT ON COLUMN fmdb.fmdb_pla_logs.pla_log_id
     IS 'A unique identifier for the PLA log';
COMMENT ON COLUMN fmdb.fmdb_pla_logs.activity_details_txt
     IS 'The activity that was completed refering to a PLA';
COMMENT ON COLUMN fmdb.fmdb_pla_logs.activity_dt
     IS 'The date of a PLA activity completed';
COMMENT ON COLUMN fmdb.fmdb_pla_logs.pla_id
     IS 'A unique identifier for the PLA. generated from sequence';

CREATE TABLE fmdb.fmdb_pla_status_types(
    pla_status_cd CHARACTER VARYING(15) NOT NULL,
    pla_status_dsc CHARACTER VARYING(250) NOT NULL,
    active_yn CHARACTER(1) NOT NULL DEFAULT 'Y',
    sort_order_nbr CHARACTER VARYING(5),
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    last_modified_by_nm CHARACTER VARYING(30),
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );

CREATE TABLE fmdb.fmdb_product_listing_agreement(
    pla_id NUMERIC(15,0) NOT NULL,
    effective_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    expiry_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    comments_txt CHARACTER VARYING(4000),
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    last_modified_by_nm CHARACTER VARYING(30),
    pla_status_cd CHARACTER VARYING(15),
    submission_id NUMERIC(15,0) NOT NULL,
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );
COMMENT ON COLUMN fmdb.fmdb_product_listing_agreement.pla_id
     IS 'A unique identifier for the PLA. generated from sequence';
COMMENT ON COLUMN fmdb.fmdb_product_listing_agreement.effective_dt
     IS 'The date the PLA became effective for the company';
COMMENT ON COLUMN fmdb.fmdb_product_listing_agreement.expiry_dt
     IS 'The date the PLA expired for the company- no longer applicable.';
COMMENT ON COLUMN fmdb.fmdb_product_listing_agreement.comments_txt
     IS 'Comments about the PLA';
COMMENT ON COLUMN fmdb.fmdb_product_listing_agreement.created_on_dtm
     IS 'Identifies when the information was created.';
COMMENT ON COLUMN fmdb.fmdb_product_listing_agreement.created_by_nm
     IS 'Identifies the userid or who created the information.';
COMMENT ON COLUMN fmdb.fmdb_product_listing_agreement.last_modified_dtm
     IS 'Identifies when the information was modified.';
COMMENT ON COLUMN fmdb.fmdb_product_listing_agreement.last_modified_by_nm
     IS 'Identifies the userid or who modified the information.';
COMMENT ON COLUMN fmdb.fmdb_product_listing_agreement.submission_id
     IS 'A system generated unique identifier for a submission number';

CREATE TABLE fmdb.fmdb_regions(
    region_cd CHARACTER VARYING(15) NOT NULL,
    region_dsc CHARACTER VARYING(250) NOT NULL,
    sort_order_nbr CHARACTER VARYING(5),
    active_yn CHARACTER(1) NOT NULL DEFAULT 'Y',
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    last_modified_by_nm CHARACTER VARYING(30),
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    country_cd CHARACTER VARYING(15) NOT NULL,
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );

CREATE TABLE fmdb.fmdb_review_questions(
    review_question_id NUMERIC(15,0) NOT NULL,
    question_txt CHARACTER VARYING(4000),
    conclusion CHARACTER VARYING(4000),
    question_sent_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    target_completion_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    psd_requested_yn CHARACTER(1),
    actual_completion_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    last_modified_by_nm CHARACTER VARYING(30),
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    submission_review_id NUMERIC(15,0) NOT NULL,
    reviewer_name_cd CHARACTER VARYING(15),
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );
COMMENT ON COLUMN fmdb.fmdb_review_questions.review_question_id
     IS 'Unique key';
COMMENT ON COLUMN fmdb.fmdb_review_questions.question_txt
     IS '"clinical or economic question submitted to the reviewer.Review questions, example 1, 962 characters:
Review questions, example 2, 779 characters:
See comment notes for examples.';
COMMENT ON COLUMN fmdb.fmdb_review_questions.conclusion
     IS 'The conclusion the reviewer reached. "Conclusions, example 1, 1333 characters:
Conclusions, example 2, 734 characters:"';
COMMENT ON COLUMN fmdb.fmdb_review_questions.question_sent_dt
     IS 'The date the question was sent to the reviewer';
COMMENT ON COLUMN fmdb.fmdb_review_questions.target_completion_dt
     IS 'The date PSD expects a conclusion from the reviewer.';
COMMENT ON COLUMN fmdb.fmdb_review_questions.psd_requested_yn
     IS 'Y/N A conscious decision point indicating whether or not the  Pharmaceutical Services Division  requested the review. "value : Y,N, blanks
Y - A conscious decision to make request
N - A conscious decision NOT to make request
blanks - no decision has been made yet. "';
COMMENT ON COLUMN fmdb.fmdb_review_questions.actual_completion_dt
     IS 'The date PSD received a conclusion.';
COMMENT ON COLUMN fmdb.fmdb_review_questions.submission_review_id
     IS 'Surrogate key - system generated.';

CREATE TABLE fmdb.fmdb_review_statuses(
    review_status_cd CHARACTER VARYING(15) NOT NULL,
    review_status_dsc CHARACTER VARYING(250) NOT NULL,
    active_yn CHARACTER VARYING(1) NOT NULL DEFAULT 'Y',
    sort_order_nbr NUMERIC(10,0),
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    last_modified_by_nm CHARACTER VARYING(30),
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );
COMMENT ON COLUMN fmdb.fmdb_review_statuses.review_status_cd
     IS 'Values:Under review, Pending, Complete';

CREATE TABLE fmdb.fmdb_reviewer_name_types(
    reviewer_name_cd CHARACTER VARYING(15) NOT NULL,
    reviewer_name_dsc CHARACTER VARYING(250) NOT NULL,
    active_yn CHARACTER(1) NOT NULL,
    sort_order_nbr CHARACTER VARYING(5),
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    last_modified_by_nm CHARACTER VARYING(30),
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );

CREATE TABLE fmdb.fmdb_submission(
    submission_id NUMERIC(15,0) NOT NULL,
    submission_no NUMERIC(15,0) NOT NULL,
    received_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    psd_web_comments CHARACTER VARYING(500),
    post_submission_to_web_yn CHARACTER(1) DEFAULT NULL,
    xref_number_txt CHARACTER VARYING(40),
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    last_modified_by_nm CHARACTER VARYING(30),
    company_id NUMERIC(15,0),
    submission_type_cd CHARACTER VARYING(15) NOT NULL,
    contact_name_id NUMERIC(15,0),
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );
COMMENT ON COLUMN fmdb.fmdb_submission.submission_id
     IS 'A system generated unique identifier for a submission number';
COMMENT ON COLUMN fmdb.fmdb_submission.submission_no
     IS 'Unique identifier that is system generated starting at a know number. As of Jan 31st,2008 the start submission number assigned by the program area is 2000.  System generated numbers would start at this point.';
COMMENT ON COLUMN fmdb.fmdb_submission.received_dt
     IS 'the date the submission was received';
COMMENT ON COLUMN fmdb.fmdb_submission.psd_web_comments
     IS 'This a Y/N  indicator that the submission information should be posted to the public web site';
COMMENT ON COLUMN fmdb.fmdb_submission.post_submission_to_web_yn
     IS '"The type of submission received values Include:
1. New Submission,
2.New Combination,
3.New Indication,
4.Criteria Modifications,
5.Line extension,
6. Generic Drug. 
7. Class review 
8. Product monograph
9. Addtl Submission Info 
10. FOI
11. Resubmission "

';
COMMENT ON COLUMN fmdb.fmdb_submission.xref_number_txt
     IS 'This a prev submission number the current application is related to.';

CREATE TABLE fmdb.fmdb_submission_review_details(
    submission_review_id NUMERIC(15,0) NOT NULL,
    bia_actual_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    bia_assigned_to_nm CHARACTER VARYING(25),
    bia_notes_txt CHARACTER VARYING(4000),
    bia_requested_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    bia_target_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    bia_requested_by_nm CHARACTER VARYING(25),
    briefing_note_signed_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    cdr_initiate_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    cdr_review_yn CHARACTER(1),
    dbc_actual_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    dbc_notes_txt CHARACTER VARYING(4000),
    dbc_recommendation_finalized_d TIMESTAMP(0) WITHOUT TIME ZONE,
    dbc_recommendation_notes_txt CHARACTER VARYING(4000),
    dbc_target_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    dbr_initiated_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    dbr_pharmanet_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    sa_completed_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    sa_initiated_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    sa_notes_txt CHARACTER VARYING(4000),
    sa_required_yn CHARACTER(1),
    sa_target_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    cedac_comments_txt CHARACTER VARYING(4000),
    cedac_meet_actual_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    cedac_meet_target_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    cedac_recommendation_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    cedac_recommendation_txt CHARACTER VARYING(4000),
    comments_txt CHARACTER VARYING(500),
    company_notice_sent_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    letter_bin BYTEA,
    letter_image_public_yn CHARACTER(1),
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    last_modified_by_nm CHARACTER VARYING(30),
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    submission_id NUMERIC(15,0) NOT NULL,
    bia_requsted_cd CHARACTER VARYING(15),
    review_status_cd CHARACTER VARYING(15),
    contact_name_comments_txt CHARACTER VARYING(250),
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL,
    view_dbc_actual_date_yn CHARACTER(1),
    tentative_dt_standard TIMESTAMP(0) WITHOUT TIME ZONE,
    tentative_dt_complex TIMESTAMP(0) WITHOUT TIME ZONE
)
        WITH (
        OIDS=FALSE
        );
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.submission_review_id
     IS 'Surrogate key - system generated.';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.bia_actual_dt
     IS 'The Actual completion date for the BIA.';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.bia_assigned_to_nm
     IS 'the  person/staff in the BIA group assigned to the request';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.bia_notes_txt
     IS 'Miscellaneous notes entered by the PSD staff about the request.';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.bia_requested_dt
     IS 'The date the Budget Impact Analysis was requested';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.bia_target_dt
     IS 'The target completion date for the BIA.';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.bia_requested_by_nm
     IS 'the name of the person  who requested the BIA';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.briefing_note_signed_dt
     IS 'The date the Briefing Note was signed by Executive Director or ADM or some other authority.';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.cdr_initiate_dt
     IS 'The date the  CDR initiated a review of the submission.';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.cdr_review_yn
     IS 'Is the CDR reviewing this drug.';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.dbc_actual_dt
     IS 'The actual meeting date for the DBC.';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.dbc_notes_txt
     IS 'PSD notes about issues around the drug benefit committee';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.dbc_recommendation_finalized_d
     IS 'The date  the dbc recommendation is finalized.';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.dbc_recommendation_notes_txt
     IS 'DBC recommendation to PSD about the submission.';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.dbc_target_dt
     IS 'The target meeting date for the Drug benefit committee(DBC)';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.dbr_initiated_dt
     IS 'The date the DBR was sent to HIBC to be entered into PharmaNet.';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.dbr_pharmanet_dt
     IS 'The Pharmanet effective date .Typically up to 10 days after the DBR initiated date.';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.sa_completed_dt
     IS 'The date the SA forms were completed by Forms Management group.';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.sa_initiated_dt
     IS 'The date SA forms were requested';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.sa_notes_txt
     IS 'This issues about the SA forms';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.sa_required_yn
     IS 'An indicator to determine if the Special Authority forms are  required.';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.sa_target_dt
     IS 'The date you expect the SA forms to be completed by Forms Management group.';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.cedac_comments_txt
     IS 'PSD comments about the CEDAC review';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.cedac_meet_actual_dt
     IS 'The date the CEDAC meeting occurred.';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.cedac_meet_target_dt
     IS 'The target date for CEDAC to review the submission.';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.cedac_recommendation_dt
     IS 'The date CEDAC issued a recommendation.';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.cedac_recommendation_txt
     IS 'The comments box contains  2 examples of CEDAC recommended text. The first is a long example (453 characters) but I am sure there may be other even longer examples. The 2nd example is a typical length (300 characters).';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.company_notice_sent_dt
     IS 'Letter sent to the company indicating the review is closed';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.letter_bin
     IS 'PDF copy  of the Drug Benefit Committee Recommendation and PSD listing decision';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.letter_image_public_yn
     IS 'An indicator that this letter can be published to the public web site.';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.submission_id
     IS 'A system generated unique identifier for a submission number';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.review_status_cd
     IS 'values:Under review, Pending, Complete';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.contact_name_comments_txt
     IS 'general comments regard the submission contact.';
COMMENT ON COLUMN fmdb.fmdb_submission_review_details.view_dbc_actual_date_yn
     IS 'determine whether or not to make the acutal date field veiwable on the public site';

CREATE TABLE fmdb.fmdb_submission_types(
    submission_type_cd CHARACTER VARYING(15) NOT NULL,
    submission_type_dsc CHARACTER VARYING(250) NOT NULL,
    active_yn CHARACTER(1) NOT NULL DEFAULT 'Y',
    sort_order_nbr CHARACTER VARYING(5),
    created_on_dtm TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    created_by_nm CHARACTER VARYING(30) NOT NULL,
    last_modified_dtm TIMESTAMP(0) WITHOUT TIME ZONE,
    last_modified_by_nm CHARACTER VARYING(30),
    stateless_transaction_nbr NUMERIC(32,0) NOT NULL
)
        WITH (
        OIDS=FALSE
        );

CREATE TABLE fmdb.person_pwn(
    id NUMERIC(10,0) NOT NULL,
    first_name CHARACTER VARYING(25),
    last_name CHARACTER VARYING(25) NOT NULL,
    email CHARACTER VARYING(128),
    phone_number CHARACTER VARYING(20),
    create_dt TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    update_dt TIMESTAMP(0) WITHOUT TIME ZONE,
    record_status CHARACTER VARYING(1) NOT NULL
)
        WITH (
        OIDS=FALSE
        );

-- ------------ Write CREATE-VIEW-stage scripts -----------

CREATE OR REPLACE  VIEW fmdb.fmdb_budget_impact_analysis_vw (submission_no, chemical_name, applicant, indication, sub_type, cdr, bia_requested_name, bia_assigned_name, bia_requested_date, bia_target_date, bia_actual_date, bia_days, bia_notes, psd_status, bia_desc, rn) AS
SELECT
    sub.submission_no AS submission_no, CONCAT_WS('', ch.chemical_nm,
    CASE
        WHEN ch.trade_nm IS NULL THEN NULL
        ELSE '('
    END, ch.trade_nm,
    CASE
        WHEN ch.trade_nm IS NULL THEN NULL
        ELSE ')'
    END) AS chemical_name, co.alias_nm AS applicant, ch.medical_indications_txt AS indication, sut.submission_type_cd AS sub_type, srd.cdr_review_yn AS cdr, srd.bia_requested_by_nm AS bia_requested_name, srd.bia_assigned_to_nm AS bia_assigned_name, srd.bia_requested_dt AS bia_requested_date, srd.bia_target_dt AS bia_target_date, srd.bia_actual_dt AS bia_actual_date, COALESCE((EXTRACT (EPOCH FROM COALESCE(srd.bia_actual_dt, CURRENT_DATE) - srd.bia_requested_dt) / 86400)::NUMERIC, 0) AS bia_days, srd.bia_notes_txt AS bia_notes, rs.review_status_dsc AS psd_status, brc.bia_requested_dsc AS bia_desc, row_number() OVER (ORDER BY NULL) AS rn
    FROM fmdb.fmdb_submission AS sub, fmdb.fmdb_submission_types AS sut, fmdb.fmdb_companies AS co, fmdb.fmdb_chemicals AS ch, fmdb.fmdb_review_statuses AS rs, fmdb.fmdb_submission_review_details AS srd, fmdb.fmdb_bia_requested_codes AS brc
    WHERE sub.company_id = co.company_id AND sub.submission_id = ch.submission_id AND sub.submission_id = srd.submission_id AND sub.submission_type_cd = sut.submission_type_cd AND rs.review_status_cd = srd.review_status_cd AND brc.bia_requsted_cd = srd.bia_requsted_cd;

CREATE OR REPLACE  VIEW fmdb.fmdb_drug_benefit_committee_vw (submission_no, chemical_name, applicant, indication, sub_type, cdr, date_review_start, dateofcdr_recommendation, cdr_recommendation, dbc_target_date, dbc_actual_date, dbcrec_finalized_date, dbc_days, dbcrec_notes, dbc_notes, psd_status, rn) AS
SELECT
    sub.submission_no AS submission_no, CONCAT_WS('', ch.chemical_nm,
    CASE
        WHEN ch.trade_nm IS NULL THEN NULL
        ELSE '('
    END, ch.trade_nm,
    CASE
        WHEN ch.trade_nm IS NULL THEN NULL
        ELSE ')'
    END) AS chemical_name, co.alias_nm AS applicant, ch.medical_indications_txt AS indication, sut.submission_type_cd AS sub_type, srd.cdr_review_yn AS cdr,
    CASE COALESCE(srd.cdr_review_yn, 'N')
        WHEN 'Y' THEN srd.cedac_recommendation_dt
        WHEN 'N' THEN sub.received_dt
    END AS date_review_start, srd.cedac_recommendation_dt AS dateofcdr_recommendation, srd.cedac_recommendation_txt AS cdr_recommendation, srd.dbc_target_dt AS dbc_target_date, srd.dbc_actual_dt AS dbc_actual_date, srd.dbc_recommendation_finalized_d AS dbcrec_finalized_date, COALESCE((EXTRACT (EPOCH FROM COALESCE(srd.dbc_actual_dt, CURRENT_DATE) - srd.dbc_target_dt) / 86400)::NUMERIC, 0) AS dbc_days, srd.dbc_recommendation_notes_txt AS dbcrec_notes, srd.dbc_notes_txt AS dbc_notes, rs.review_status_dsc AS psd_status, row_number() OVER (ORDER BY NULL) AS rn
    FROM fmdb.fmdb_submission AS sub, fmdb.fmdb_submission_types AS sut, fmdb.fmdb_companies AS co, fmdb.fmdb_chemicals AS ch, fmdb.fmdb_review_statuses AS rs, fmdb.fmdb_submission_review_details AS srd
    WHERE sub.company_id = co.company_id AND sub.submission_id = ch.submission_id AND sub.submission_id = srd.submission_id AND sub.submission_type_cd = sut.submission_type_cd AND rs.review_status_cd = srd.review_status_cd;

CREATE OR REPLACE  VIEW fmdb.fmdb_public_access_vw (submission_id, trade_nm, chemical_nm, legal_nm, medical_indications_txt, submission_type_dsc, cdr_review_yn, received_dt, cedac_recommendation_dt, review_status_dsc, company_notice_sent_dt, letter_bin, psd_web_comments, tentative_dt_complex, tentative_dt_standard, dbc_actual_dt, dbr_pharmanet_dt) AS
SELECT
    s.submission_id, c.trade_nm, c.chemical_nm, cp.legal_nm, c.medical_indications_txt, st.submission_type_dsc, rd.cdr_review_yn, s.received_dt, rd.cedac_recommendation_dt, rs.review_status_dsc, rd.company_notice_sent_dt, l.letter_bin, s.psd_web_comments, rd.tentative_dt_complex, rd.tentative_dt_standard,
    CASE
        WHEN rd.view_dbc_actual_date_yn IS NULL THEN NULL
        WHEN rd.view_dbc_actual_date_yn = 'N' THEN NULL
        WHEN rd.view_dbc_actual_date_yn = 'Y' THEN rd.dbc_actual_dt
    END, rd.dbr_pharmanet_dt
    FROM fmdb.fmdb_chemicals AS c, fmdb.fmdb_submission_types AS st, fmdb.fmdb_review_statuses AS rs, fmdb.fmdb_submission_review_details AS rd
    LEFT OUTER JOIN (SELECT
        rd2.letter_bin, rd2.submission_review_id
        FROM fmdb.fmdb_submission_review_details AS rd2
        WHERE rd2.letter_image_public_yn = 'Y') AS l
        ON (l.submission_review_id = rd.submission_review_id), fmdb.fmdb_submission AS s
    LEFT OUTER JOIN fmdb.fmdb_companies AS cp
        ON (cp.company_id = s.company_id)
    WHERE st.submission_type_cd = s.submission_type_cd AND s.submission_id = c.submission_id AND s.submission_id = rd.submission_id AND rs.review_status_cd = rd.review_status_cd AND s.post_submission_to_web_yn = 'Y' AND rd.submission_review_id = (SELECT
        rd3.submission_review_id
        FROM fmdb.fmdb_submission_review_details AS rd3
        WHERE rd3.submission_id = s.submission_id
        LIMIT 1);

CREATE OR REPLACE  VIEW fmdb.fmdb_single_drug_sub_status_vw (submission_no, xref, received_date, chemical_id, chemicalname, chemical_trade_name, submission_review_id, sub_type, applicant_name, indication, psd_status, cdr_drug_review, psd_review_initiate_dt, psd_review_completed_dt, psd_review_days, cedac_review_status, cedac_review_initiate_dt, cedac_review_target_dt, cedac_review_completed_dt, cedac_review_days, bia_status, bia_initiate_dt, bia_target_dt, bia_completed_dt, bia_days, dbc_target_dt, dbc_completed_dt, sa_forms_status, sa_forms_initiate_dt, sa_forms_target_dt, sa_forms_completed_dt, sa_forms_days, briefing_note_completed_dt, pla_initiate_dt, pla_completed_dt, pla_days, dbr_initiate_dt, pnet_completed_dt, applicant_notice_completed_dt, noc_date, date_released, patent_expiry, bia_requested_by, bia_assigned_to, bia_notes, cedac_actual_meeting, cedac_recommendation, cedac_notes, dbc_recommendation_finalized, dbc_recommendation, dbc_notes, sa_notes, drug_status_additional_info, company_type, applicant_address, prov_state, city, country, postal_zip, work, ext, fax, contact_name, title, contact_address, contact_prov_state, contact_city, contact_country, contact_postal_zip, contact_work, contact_ext, contact_cell, contact_fax, contact_email, company_details_comments, rn) AS
SELECT
    s.submission_no, s.xref_number_txt AS xref, s.received_dt AS received_date, ch.chemical_id,
    /* for DINs from joining table FMDB_DINS */
    ch.chemical_nm AS chemicalname, CONCAT_WS('', ch.chemical_nm,
    CASE
        WHEN ch.trade_nm IS NULL THEN NULL
        ELSE '('
    END, ch.trade_nm,
    CASE
        WHEN ch.trade_nm IS NULL THEN NULL
        ELSE ')'
    END) AS chemical_trade_name, COALESCE(srd.submission_review_id, 0),
    /* for Technical Review from joining FMDB_REVIEW_QUESTIONS */
    (SELECT
        submission_type_dsc
        FROM fmdb.fmdb_submission_types AS st
        WHERE s.submission_type_cd = st.submission_type_cd) AS sub_type, co.legal_nm AS applicant_name, ch.medical_indications_txt AS indication, (SELECT
        review_status_dsc
        FROM fmdb.fmdb_review_statuses AS rs
        WHERE srd.review_status_cd = rs.review_status_cd) AS psd_status, srd.cdr_review_yn AS cdr_drug_review,
    /* Timelines start */
    /* PSD Review */
    CASE srd.cdr_review_yn
        WHEN 'Y' THEN srd.cedac_recommendation_dt
        WHEN 'N' THEN s.received_dt
    END AS psd_review_initiate_dt, srd.company_notice_sent_dt AS psd_review_completed_dt, COALESCE((EXTRACT (EPOCH FROM COALESCE(srd.company_notice_sent_dt, CURRENT_DATE) - (CASE srd.cdr_review_yn
        WHEN 'Y' THEN srd.cedac_recommendation_dt
        WHEN 'N' THEN s.received_dt
    END)) / 86400)::NUMERIC, 0) AS psd_review_days,
    /* CEDAC REVIEW */
    srd.cdr_review_yn AS cedac_review_status, srd.cdr_initiate_dt AS cedac_review_initiate_dt, srd.cedac_meet_target_dt AS cedac_review_target_dt, srd.cedac_recommendation_dt AS cedac_review_completed_dt, COALESCE((EXTRACT (EPOCH FROM COALESCE(srd.cedac_recommendation_dt, CURRENT_DATE) - srd.cdr_initiate_dt) / 86400)::NUMERIC, 0) AS cedac_review_days,
    /* BIA */
    (SELECT
        bia_requested_dsc
        FROM fmdb.fmdb_bia_requested_codes AS brc
        WHERE srd.bia_requsted_cd = brc.bia_requsted_cd) AS bia_status, srd.bia_requested_dt AS bia_initiate_dt, srd.bia_target_dt AS bia_target_dt, srd.bia_actual_dt AS bia_completed_dt, COALESCE((EXTRACT (EPOCH FROM COALESCE(srd.bia_actual_dt, CURRENT_DATE) - srd.bia_requested_dt) / 86400)::NUMERIC, 0) AS bia_days,
    /* BDC */
    srd.dbc_target_dt AS dbc_target_dt, srd.dbc_actual_dt AS dbc_completed_dt,
    /* SA Forms */
    srd.sa_required_yn AS sa_forms_status, srd.sa_initiated_dt AS sa_forms_initiate_dt, srd.sa_target_dt AS sa_forms_target_dt, srd.sa_completed_dt AS sa_forms_completed_dt, COALESCE((EXTRACT (EPOCH FROM COALESCE(srd.sa_completed_dt, CURRENT_DATE) - srd.sa_initiated_dt) / 86400)::NUMERIC, 0) AS sa_forms_days,
    /* Briefing/Decision Note */
    srd.briefing_note_signed_dt AS briefing_note_completed_dt,
    /* PLA */
    pla.effective_dt AS pla_initiate_dt, pla.expiry_dt AS pla_completed_dt, COALESCE((EXTRACT (EPOCH FROM COALESCE(pla.expiry_dt, CURRENT_DATE) - pla.effective_dt) / 86400)::NUMERIC, 0) AS pla_days,
    /* DBR */
    srd.dbr_initiated_dt AS dbr_initiate_dt,
    /* Pnet */
    srd.dbr_pharmanet_dt AS pnet_completed_dt,
    /* Applicant notice */
    srd.company_notice_sent_dt AS applicant_notice_completed_dt,
    /* Technical Review Question dates from FMDB_REVIEW_QUESTIONS table as a sub report */
    /* Timelines end */
    ch.noc_dt AS noc_date, ch.market_release_dt AS date_released, ch.patent_expiry_dt AS patent_expiry,
    /* DINs from FMDB_DINS table as a sub report */
    srd.bia_requested_by_nm AS bia_requested_by, srd.bia_assigned_to_nm AS bia_assigned_to, srd.bia_notes_txt AS bia_notes, srd.cedac_meet_actual_dt AS cedac_actual_meeting, srd.cedac_recommendation_txt AS cedac_recommendation, srd.cedac_comments_txt AS cedac_notes,
    /* Technical Review Question from FMDB_REVIEW_QUESTIONS table as a sub report */
    srd.dbc_recommendation_finalized_d AS dbc_recommendation_finalized, srd.dbc_recommendation_notes_txt AS dbc_recommendation, srd.dbc_notes_txt AS dbc_notes, srd.sa_notes_txt AS sa_notes, srd.comments_txt AS drug_status_additional_info,
    /* Applicant as follow */
    (SELECT
        company_type_dsc
        FROM fmdb.fmdb_company_types AS ct
        WHERE co.company_type_cd = ct.company_type_cd) AS company_type, addr_co.address_first_line_txt AS applicant_address, (SELECT
        region_dsc
        FROM fmdb.fmdb_regions AS rg
        WHERE addr_co.region_cd = rg.region_cd) AS prov_state, addr_co.city_nm AS city, (SELECT
        country_dsc
        FROM fmdb.fmdb_countries AS c
        WHERE addr_co.country_cd = c.country_cd) AS country, addr_co.postal_code_txt AS postal_zip, applicant.work_phone_no AS work, applicant.work_ph_ext_no AS ext, applicant.fax_no AS fax,
    /* Contact as follow */
    CONCAT_WS('', cn.first_nm, ' ', cn.last_nm) AS contact_name, cn.job_title_nm AS title, addr_cn.address_first_line_txt AS contact_address, (SELECT
        region_dsc
        FROM fmdb.fmdb_regions AS rg
        WHERE addr_cn.region_cd = rg.region_cd) AS contact_prov_state, addr_cn.city_nm AS contact_city, (SELECT
        country_dsc
        FROM fmdb.fmdb_countries AS c
        WHERE addr_cn.country_cd = c.country_cd) AS contact_country, addr_cn.postal_code_txt AS contact_postal_zip, contact.work_phone_no AS contact_work, contact.work_ph_ext_no AS contact_ext, contact.cell_no AS contact_cell, contact.fax_no AS contact_fax, contact.email_address AS contact_email, srd.contact_name_comments_txt AS company_details_comments, row_number() OVER (ORDER BY NULL) AS rn
    FROM
    /* Address for Applicant */
    /* Address for Contact */
    fmdb.fmdb_chemicals AS ch
    LEFT OUTER JOIN fmdb.fmdb_submission AS s
        ON (ch.submission_id = s.submission_id)
    LEFT OUTER JOIN fmdb.fmdb_companies AS co
        ON (s.company_id = co.company_id)
    LEFT OUTER JOIN fmdb.fmdb_addresses AS addr_co
        ON (s.company_id = addr_co.company_id)
    LEFT OUTER JOIN fmdb.fmdb_contact_details AS applicant
        ON (s.company_id = applicant.company_id)
    LEFT OUTER JOIN fmdb.fmdb_contact_names AS cn
        ON (s.contact_name_id = cn.contact_name_id)
    LEFT OUTER JOIN fmdb.fmdb_addresses AS addr_cn
        ON (s.contact_name_id = addr_cn.contact_name_id)
    LEFT OUTER JOIN fmdb.fmdb_contact_details AS contact
        ON (s.contact_name_id = contact.contact_name_id)
    LEFT OUTER JOIN fmdb.fmdb_submission_review_details AS srd
        ON (ch.submission_id = srd.submission_id)
    LEFT OUTER JOIN fmdb.fmdb_product_listing_agreement AS pla
        ON (ch.submission_id = pla.submission_id)
    WHERE applicant.contact_name_id IS NULL;

CREATE OR REPLACE  VIEW fmdb.fmdb_special_auth_forms_vw (submission_no, chemical_name, applicant, indication, sub_type, cdr, sa_initiated_date, sa_target_date, sa_completed_date, sa_days, sa_notes, psd_status, sa_required_yn, rn) AS
SELECT
    sub.submission_no AS submission_no, CONCAT_WS('', ch.chemical_nm,
    CASE
        WHEN ch.trade_nm IS NULL THEN NULL
        ELSE '('
    END, ch.trade_nm,
    CASE
        WHEN ch.trade_nm IS NULL THEN NULL
        ELSE ')'
    END) AS chemical_name, co.alias_nm AS applicant, ch.medical_indications_txt AS indication, sut.submission_type_cd AS sub_type, srd.cdr_review_yn AS cdr, srd.sa_initiated_dt AS sa_initiated_date, srd.sa_target_dt AS sa_target_date, srd.sa_completed_dt AS sa_completed_date, COALESCE((EXTRACT (EPOCH FROM COALESCE(srd.sa_completed_dt, CURRENT_DATE) - srd.sa_initiated_dt) / 86400)::NUMERIC, 0) AS sa_days, srd.sa_notes_txt AS sa_notes, rs.review_status_dsc AS psd_status, srd.sa_required_yn AS sa_required_yn, row_number() OVER (ORDER BY NULL) AS rn
    FROM fmdb.fmdb_submission AS sub, fmdb.fmdb_submission_types AS sut, fmdb.fmdb_companies AS co, fmdb.fmdb_chemicals AS ch, fmdb.fmdb_review_statuses AS rs, fmdb.fmdb_submission_review_details AS srd
    WHERE sub.company_id = co.company_id AND sub.submission_id = ch.submission_id AND sub.submission_id = srd.submission_id AND sub.submission_type_cd = sut.submission_type_cd AND rs.review_status_cd = srd.review_status_cd;

CREATE OR REPLACE  VIEW fmdb.fmdb_subm_by_applicant_vw (submission_no, chemical_name, applicant, indication, sub_type, cdr, dateofcdr_recommendation, cdr_recommendation, dbc_date, dbc_recommendation, psd_status, date_review_start, decision_date, review_days, legal_nm, rn) AS
SELECT
    sub.submission_no AS submission_no, CONCAT_WS('', ch.chemical_nm,
    CASE
        WHEN ch.trade_nm IS NULL THEN NULL
        ELSE '('
    END, ch.trade_nm,
    CASE
        WHEN ch.trade_nm IS NULL THEN NULL
        ELSE ')'
    END) AS chemical_name, co.alias_nm AS applicant, ch.medical_indications_txt AS indication, sut.submission_type_cd AS sub_type, srd.cdr_review_yn AS cdr, srd.cedac_recommendation_dt AS dateofcdr_recommendation, srd.cedac_recommendation_txt AS cdr_recommendation, srd.dbc_recommendation_finalized_d AS dbc_date, srd.dbc_recommendation_notes_txt AS dbc_recommendation, rs.review_status_dsc AS psd_status,
    CASE COALESCE(srd.cdr_review_yn, 'N')
        WHEN 'Y' THEN srd.cedac_recommendation_dt
        WHEN 'N' THEN sub.received_dt
    END AS date_review_start, srd.company_notice_sent_dt AS decision_date,
    CASE COALESCE(srd.cdr_review_yn, 'N')
        WHEN 'Y' THEN COALESCE((EXTRACT (EPOCH FROM COALESCE(srd.company_notice_sent_dt, CURRENT_DATE) - srd.cedac_recommendation_dt) / 86400)::NUMERIC, 0)
        WHEN 'N' THEN COALESCE((EXTRACT (EPOCH FROM COALESCE(srd.company_notice_sent_dt, CURRENT_DATE) - sub.received_dt) / 86400)::NUMERIC, 0)
    END AS review_days, co.legal_nm, row_number() OVER (ORDER BY NULL) AS rn
    FROM fmdb.fmdb_submission AS sub, fmdb.fmdb_submission_types AS sut, fmdb.fmdb_companies AS co, fmdb.fmdb_chemicals AS ch, fmdb.fmdb_review_statuses AS rs, fmdb.fmdb_submission_review_details AS srd
    WHERE sub.company_id = co.company_id AND sub.submission_id = ch.submission_id AND sub.submission_id = srd.submission_id AND sub.submission_type_cd = sut.submission_type_cd AND rs.review_status_cd = srd.review_status_cd;

CREATE OR REPLACE  VIEW fmdb.fmdb_submissions_vw (submission_no, chemical_name, applicant, indication, sub_type, cdr, cdr_recommendation, dbc_date, dbc_recommendation, psd_status, date_review_start, decision_date, review_days) AS
SELECT
    sub.submission_no AS submission_no, CONCAT_WS('', ch.chemical_nm,
    CASE
        WHEN ch.trade_nm IS NULL THEN NULL
        ELSE '('
    END, ch.trade_nm,
    CASE
        WHEN ch.trade_nm IS NULL THEN NULL
        ELSE ')'
    END) AS chemical_name, co.alias_nm AS applicant, ch.medical_indications_txt AS indication, sut.submission_type_cd AS sub_type, srd.cdr_review_yn AS cdr, srd.cedac_recommendation_txt AS cdr_recommendation, srd.dbc_actual_dt AS dbc_date, srd.dbc_recommendation_notes_txt AS dbc_recommendation, rs.review_status_dsc AS psd_status,
    CASE COALESCE(srd.cdr_review_yn, 'N')
        WHEN 'Y' THEN srd.cedac_recommendation_dt
        WHEN 'N' THEN sub.received_dt
    END AS date_review_start, srd.dbr_pharmanet_dt AS decision_date,
    CASE COALESCE(srd.cdr_review_yn, 'N')
        WHEN 'Y' THEN COALESCE((EXTRACT (EPOCH FROM COALESCE(srd.company_notice_sent_dt, CURRENT_DATE) - srd.cedac_recommendation_dt) / 86400)::NUMERIC, 0)
        WHEN 'N' THEN COALESCE((EXTRACT (EPOCH FROM COALESCE(srd.company_notice_sent_dt, CURRENT_DATE) - sub.received_dt) / 86400)::NUMERIC, 0)
    END AS review_days
    FROM fmdb.fmdb_submission AS sub, fmdb.fmdb_submission_types AS sut, fmdb.fmdb_companies AS co, fmdb.fmdb_chemicals AS ch, fmdb.fmdb_review_statuses AS rs, fmdb.fmdb_submission_review_details AS srd
    WHERE sub.company_id = co.company_id AND sub.submission_id = ch.submission_id AND sub.submission_id = srd.submission_id AND sub.submission_type_cd = sut.submission_type_cd AND rs.review_status_cd = srd.review_status_cd;

CREATE OR REPLACE  VIEW fmdb.fmdb_technical_review_vw (submission_no, chemical_name, applicant, indication, sub_type, cdr, reviewer_name, question_sent_date, review_target_date, review_completion_date, review_days, psd_status, requested_yn, rn) AS
SELECT
    sub.submission_no AS submission_no, CONCAT_WS('', ch.chemical_nm,
    CASE
        WHEN ch.trade_nm IS NULL THEN NULL
        ELSE '('
    END, ch.trade_nm,
    CASE
        WHEN ch.trade_nm IS NULL THEN NULL
        ELSE ')'
    END) AS chemical_name, co.alias_nm AS applicant, ch.medical_indications_txt AS indication, sut.submission_type_cd AS sub_type, srd.cdr_review_yn AS cdr, rnt.reviewer_name_dsc AS reviewer_name, rq.question_sent_dt AS question_sent_date, rq.target_completion_dt AS review_target_date, rq.actual_completion_dt AS review_completion_date, COALESCE((EXTRACT (EPOCH FROM COALESCE(rq.actual_completion_dt, CURRENT_DATE) - rq.question_sent_dt) / 86400)::NUMERIC, 0) AS review_days, rs.review_status_dsc AS psd_status, rq.psd_requested_yn AS requested_yn, row_number() OVER (ORDER BY NULL) AS rn
    FROM fmdb.fmdb_submission AS sub, fmdb.fmdb_submission_types AS sut, fmdb.fmdb_companies AS co, fmdb.fmdb_chemicals AS ch, fmdb.fmdb_review_statuses AS rs, fmdb.fmdb_submission_review_details AS srd, fmdb.fmdb_reviewer_name_types AS rnt, fmdb.fmdb_review_questions AS rq
    WHERE sub.company_id = co.company_id AND sub.submission_id = ch.submission_id AND sub.submission_id = srd.submission_id AND sub.submission_type_cd = sut.submission_type_cd AND rs.review_status_cd = srd.review_status_cd AND rq.submission_review_id = srd.submission_review_id AND rq.reviewer_name_cd = rnt.reviewer_name_cd;

-- ------------ Write CREATE-INDEX-stage scripts -----------

CREATE INDEX adr_adrt_fk_i
ON fmdb.fmdb_addresses
USING BTREE (address_type_cd ASC);

CREATE INDEX adr_cn_fk_i
ON fmdb.fmdb_addresses
USING BTREE (contact_name_id ASC);

CREATE INDEX adr_com_fk_i
ON fmdb.fmdb_addresses
USING BTREE (company_id ASC);

CREATE INDEX adr_reg_fk_i
ON fmdb.fmdb_addresses
USING BTREE (region_cd ASC, country_cd ASC);

CREATE INDEX com_ct1_fk_i
ON fmdb.fmdb_companies
USING BTREE (company_type_cd ASC);

CREATE INDEX cd_cn_fk_i
ON fmdb.fmdb_contact_details
USING BTREE (contact_name_id ASC);

CREATE INDEX cd_com_fk_i
ON fmdb.fmdb_contact_details
USING BTREE (company_id ASC);

CREATE INDEX cn_com_fk_i
ON fmdb.fmdb_contact_names
USING BTREE (company_id ASC);

CREATE INDEX cn_ct_fk_i
ON fmdb.fmdb_contact_names
USING BTREE (contact_type_cd ASC);

CREATE INDEX din_che_fk_i
ON fmdb.fmdb_dins
USING BTREE (chemical_id ASC);

CREATE INDEX es_sub_fk_i
ON fmdb.fmdb_economic_statuses
USING BTREE (submission_id ASC);

CREATE INDEX pl_pla_fk_i
ON fmdb.fmdb_pla_logs
USING BTREE (pla_id ASC);

CREATE INDEX pla_pst_fk_i
ON fmdb.fmdb_product_listing_agreement
USING BTREE (pla_status_cd ASC);

CREATE INDEX pla_sub_fk_i
ON fmdb.fmdb_product_listing_agreement
USING BTREE (submission_id ASC);

CREATE INDEX reg_ctry_fk_i
ON fmdb.fmdb_regions
USING BTREE (country_cd ASC);

CREATE INDEX rq_rnt_fk_i
ON fmdb.fmdb_review_questions
USING BTREE (reviewer_name_cd ASC);

CREATE INDEX rq_srd_fk_i
ON fmdb.fmdb_review_questions
USING BTREE (submission_review_id ASC);

CREATE INDEX sub_com_fk_i
ON fmdb.fmdb_submission
USING BTREE (company_id ASC);

CREATE INDEX sub_st_fk_i
ON fmdb.fmdb_submission
USING BTREE (submission_type_cd ASC);

CREATE INDEX srd_brc_fk_i
ON fmdb.fmdb_submission_review_details
USING BTREE (bia_requsted_cd ASC);

CREATE INDEX srd_rs_fk_i
ON fmdb.fmdb_submission_review_details
USING BTREE (review_status_cd ASC);

CREATE UNIQUE INDEX srd_sub_fk_i
ON fmdb.fmdb_submission_review_details
USING BTREE (submission_id ASC);

-- ------------ Write CREATE-CONSTRAINT-stage scripts -----------

ALTER TABLE fmdb.databasechangeloglock
ADD CONSTRAINT pk_databasechangeloglock PRIMARY KEY (id);

ALTER TABLE fmdb.fmdb_address_types
ADD CONSTRAINT adrt_pk PRIMARY KEY (address_type_cd);

ALTER TABLE fmdb.fmdb_address_types
ADD CONSTRAINT avcon_1202340926_activ_005 CHECK (active_yn IN ('Y', 'N', ' ""'));

ALTER TABLE fmdb.fmdb_addresses
ADD CONSTRAINT adr_pk PRIMARY KEY (address_id);

ALTER TABLE fmdb.fmdb_bia_requested_codes
ADD CONSTRAINT avcon_1202340926_activ_006 CHECK (active_yn IN ('Y', 'N', ' ""'));

ALTER TABLE fmdb.fmdb_bia_requested_codes
ADD CONSTRAINT brc_pk PRIMARY KEY (bia_requsted_cd);

ALTER TABLE fmdb.fmdb_chemicals
ADD CONSTRAINT che_pk PRIMARY KEY (chemical_id);

ALTER TABLE fmdb.fmdb_chemicals
ADD CONSTRAINT che_sub_uniq UNIQUE (submission_id);

ALTER TABLE fmdb.fmdb_companies
ADD CONSTRAINT com_pk PRIMARY KEY (company_id);

ALTER TABLE fmdb.fmdb_company_types
ADD CONSTRAINT avcon_1202340926_activ_001 CHECK (active_yn IN ('Y', 'N', ' ""'));

ALTER TABLE fmdb.fmdb_company_types
ADD CONSTRAINT ct1_pk PRIMARY KEY (company_type_cd);

ALTER TABLE fmdb.fmdb_contact_details
ADD CONSTRAINT cd_pk PRIMARY KEY (contact_dtl_id);

ALTER TABLE fmdb.fmdb_contact_names
ADD CONSTRAINT cn_pk PRIMARY KEY (contact_name_id);

ALTER TABLE fmdb.fmdb_contact_types
ADD CONSTRAINT avcon_1202340926_activ_008 CHECK (active_yn IN ('Y', 'N', ' ""'));

ALTER TABLE fmdb.fmdb_contact_types
ADD CONSTRAINT ct_pk PRIMARY KEY (contact_type_cd);

ALTER TABLE fmdb.fmdb_countries
ADD CONSTRAINT avcon_1202340926_activ_003 CHECK (active_yn IN ('Y', 'N', ' ""'));

ALTER TABLE fmdb.fmdb_countries
ADD CONSTRAINT ctry_pk PRIMARY KEY (country_cd);

ALTER TABLE fmdb.fmdb_dins
ADD CONSTRAINT din_pk PRIMARY KEY (din_id);

ALTER TABLE fmdb.fmdb_economic_statuses
ADD CONSTRAINT es_pk PRIMARY KEY (economic_id);

ALTER TABLE fmdb.fmdb_pla_logs
ADD CONSTRAINT pl_pk PRIMARY KEY (pla_log_id);

ALTER TABLE fmdb.fmdb_pla_status_types
ADD CONSTRAINT avcon_1202340926_activ_002 CHECK (active_yn IN ('Y', 'N', ' ""'));

ALTER TABLE fmdb.fmdb_pla_status_types
ADD CONSTRAINT pst_pk PRIMARY KEY (pla_status_cd);

ALTER TABLE fmdb.fmdb_product_listing_agreement
ADD CONSTRAINT pla_pk PRIMARY KEY (pla_id);

ALTER TABLE fmdb.fmdb_regions
ADD CONSTRAINT avcon_1202340926_activ_004 CHECK (active_yn IN ('Y', 'N', ' ""'));

ALTER TABLE fmdb.fmdb_regions
ADD CONSTRAINT reg_pk PRIMARY KEY (region_cd, country_cd);

ALTER TABLE fmdb.fmdb_review_questions
ADD CONSTRAINT avcon_1202340926_psd_r_000 CHECK (psd_requested_yn IN ('Y', 'N', ' ""'));

ALTER TABLE fmdb.fmdb_review_questions
ADD CONSTRAINT rq_pk PRIMARY KEY (review_question_id);

ALTER TABLE fmdb.fmdb_review_statuses
ADD CONSTRAINT rs_pk PRIMARY KEY (review_status_cd);

ALTER TABLE fmdb.fmdb_reviewer_name_types
ADD CONSTRAINT avcon_1202340926_activ_007 CHECK (active_yn IN ('Y', 'N', ' ""'));

ALTER TABLE fmdb.fmdb_reviewer_name_types
ADD CONSTRAINT rnt_pk PRIMARY KEY (reviewer_name_cd);

ALTER TABLE fmdb.fmdb_submission
ADD CONSTRAINT avcon_1202340926_post_000 CHECK (post_submission_to_web_yn IN ('Y', 'N', ' ""'));

ALTER TABLE fmdb.fmdb_submission
ADD CONSTRAINT sub_pk PRIMARY KEY (submission_id);

ALTER TABLE fmdb.fmdb_submission
ADD CONSTRAINT sub_submission_no_uk UNIQUE (submission_no);

ALTER TABLE fmdb.fmdb_submission_review_details
ADD CONSTRAINT avcon_1202340926_cdr_r_000 CHECK (cdr_review_yn IN ('Y', 'N', ' ""'));

ALTER TABLE fmdb.fmdb_submission_review_details
ADD CONSTRAINT avcon_1202340926_lette_000 CHECK (letter_image_public_yn IN ('Y', 'N', ' ""'));

ALTER TABLE fmdb.fmdb_submission_review_details
ADD CONSTRAINT avcon_1202340926_sa_re_000 CHECK (sa_required_yn IN ('Y', 'N', ' ""'));

ALTER TABLE fmdb.fmdb_submission_review_details
ADD CONSTRAINT srd_pk PRIMARY KEY (submission_review_id);

ALTER TABLE fmdb.fmdb_submission_types
ADD CONSTRAINT avcon_1202340926_activ_000 CHECK (active_yn IN ('Y', 'N', ' ""'));

ALTER TABLE fmdb.fmdb_submission_types
ADD CONSTRAINT st_pk PRIMARY KEY (submission_type_cd);

ALTER TABLE fmdb.person_pwn
ADD CONSTRAINT person_pwn_pk PRIMARY KEY (id);

-- ------------ Write CREATE-FOREIGN-KEY-CONSTRAINT-stage scripts -----------
/*
ALTER TABLE fmdb.fmdb_addresses
ADD CONSTRAINT adr_adrt_fk FOREIGN KEY (address_type_cd) 
REFERENCES fmdb.fmdb_address_types (address_type_cd)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_addresses
ADD CONSTRAINT adr_cn_fk FOREIGN KEY (contact_name_id) 
REFERENCES fmdb.fmdb_contact_names (contact_name_id)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_addresses
ADD CONSTRAINT adr_com_fk FOREIGN KEY (company_id) 
REFERENCES fmdb.fmdb_companies (company_id)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_addresses
ADD CONSTRAINT adr_reg_fk FOREIGN KEY (region_cd, country_cd) 
REFERENCES fmdb.fmdb_regions (region_cd, country_cd)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_chemicals
ADD CONSTRAINT che_sub_fk FOREIGN KEY (submission_id) 
REFERENCES fmdb.fmdb_submission (submission_id)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_companies
ADD CONSTRAINT com_ct1_fk FOREIGN KEY (company_type_cd) 
REFERENCES fmdb.fmdb_company_types (company_type_cd)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_contact_details
ADD CONSTRAINT cd_cn_fk FOREIGN KEY (contact_name_id) 
REFERENCES fmdb.fmdb_contact_names (contact_name_id)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_contact_details
ADD CONSTRAINT cd_com_fk FOREIGN KEY (company_id) 
REFERENCES fmdb.fmdb_companies (company_id)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_contact_names
ADD CONSTRAINT cn_com_fk FOREIGN KEY (company_id) 
REFERENCES fmdb.fmdb_companies (company_id)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_contact_names
ADD CONSTRAINT cn_ct_fk FOREIGN KEY (contact_type_cd) 
REFERENCES fmdb.fmdb_contact_types (contact_type_cd)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_dins
ADD CONSTRAINT din_che_fk FOREIGN KEY (chemical_id) 
REFERENCES fmdb.fmdb_chemicals (chemical_id)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_economic_statuses
ADD CONSTRAINT es_sub_fk FOREIGN KEY (submission_id) 
REFERENCES fmdb.fmdb_submission (submission_id)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_pla_logs
ADD CONSTRAINT pl_pla_fk FOREIGN KEY (pla_id) 
REFERENCES fmdb.fmdb_product_listing_agreement (pla_id)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_product_listing_agreement
ADD CONSTRAINT pla_pst_fk FOREIGN KEY (pla_status_cd) 
REFERENCES fmdb.fmdb_pla_status_types (pla_status_cd)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_product_listing_agreement
ADD CONSTRAINT pla_sub_fk FOREIGN KEY (submission_id) 
REFERENCES fmdb.fmdb_submission (submission_id)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_regions
ADD CONSTRAINT reg_ctry_fk FOREIGN KEY (country_cd) 
REFERENCES fmdb.fmdb_countries (country_cd)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_review_questions
ADD CONSTRAINT rq_rnt_fk FOREIGN KEY (reviewer_name_cd) 
REFERENCES fmdb.fmdb_reviewer_name_types (reviewer_name_cd)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_review_questions
ADD CONSTRAINT rq_srd_fk FOREIGN KEY (submission_review_id) 
REFERENCES fmdb.fmdb_submission_review_details (submission_review_id)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_submission
ADD CONSTRAINT sub_cn_fk FOREIGN KEY (contact_name_id) 
REFERENCES fmdb.fmdb_contact_names (contact_name_id)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_submission
ADD CONSTRAINT sub_com_fk FOREIGN KEY (company_id) 
REFERENCES fmdb.fmdb_companies (company_id)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_submission
ADD CONSTRAINT sub_st_fk FOREIGN KEY (submission_type_cd) 
REFERENCES fmdb.fmdb_submission_types (submission_type_cd)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_submission_review_details
ADD CONSTRAINT srd_brc_fk FOREIGN KEY (bia_requsted_cd) 
REFERENCES fmdb.fmdb_bia_requested_codes (bia_requsted_cd)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_submission_review_details
ADD CONSTRAINT srd_rs_fk FOREIGN KEY (review_status_cd) 
REFERENCES fmdb.fmdb_review_statuses (review_status_cd)
ON DELETE NO ACTION;

ALTER TABLE fmdb.fmdb_submission_review_details
ADD CONSTRAINT srd_sub_fk FOREIGN KEY (submission_id) 
REFERENCES fmdb.fmdb_submission (submission_id)
ON DELETE NO ACTION;

*/

-- ------------ Write CREATE-FUNCTION-stage scripts -----------

CREATE OR REPLACE FUNCTION fmdb.fmdb_microstrategy$businessdaysbetween(IN enddate TIMESTAMP WITHOUT TIME ZONE, IN startdate TIMESTAMP WITHOUT TIME ZONE)
RETURNS DOUBLE PRECISION
AS
$BODY$
DECLARE
    thedays NUMERIC(10, 0);
BEGIN
    SELECT
        rnum, COUNT(*)
        INTO STRICT thedays
        FROM (SELECT
            row_number() OVER (ORDER BY NULL) AS rnum
            FROM aws_oracle_ext.SYS_ALL_OBJECTS
        LIMIT
        CASE
            WHEN TRUNC((EXTRACT (EPOCH FROM ENDDATE - STARTDATE) / 86400)::NUMERIC + 1) > 0 THEN TRUNC((EXTRACT (EPOCH FROM ENDDATE - STARTDATE) / 86400)::NUMERIC + 1)
            ELSE 0
        END) AS var_sbq
        WHERE (SELECT
            aws_oracle_ext.TO_CHAR(STARTDATE + (rnum::NUMERIC || ' days')::INTERVAL - (1::NUMERIC || ' days')::INTERVAL, 'DY')) NOT IN ('SAT', 'SUN');
    RETURN thedays;
/* EXCEPTION */
/* RETURN 0; */
END;
$BODY$
LANGUAGE  plpgsql;

CREATE OR REPLACE FUNCTION fmdb.person_pwn_pk_trg$person_pwn()
RETURNS trigger
AS
$BODY$
BEGIN
    SELECT
        nextval('fmdb.person_pwn_pk_seq')
        INTO STRICT new.id;
    RETURN NEW;
END;
$BODY$
LANGUAGE  plpgsql;

-- ------------ Write CREATE-TRIGGER-stage scripts -----------

CREATE TRIGGER person_pwn_pk_trg
BEFORE INSERT
ON fmdb.person_pwn
FOR EACH ROW
WHEN (new.id IS NULL)
EXECUTE PROCEDURE fmdb.person_pwn_pk_trg$person_pwn();

