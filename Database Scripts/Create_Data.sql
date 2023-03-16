SET CLIENT_ENCODING TO 'utf8';
-------------------------------------------------
--        Create Sequences to go here          --
-------------------------------------------------

-------------------------------------------------
--    Create Table Data to go here             --
-------------------------------------------------

-------------------------------------------------
--            Create Constraints               --
-------------------------------------------------
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


ALTER TABLE FMDB.fmdb_addresses
ADD CONSTRAINT adr_adrt_fk FOREIGN KEY (address_type_cd) 
REFERENCES FMDB.fmdb_address_types (address_type_cd)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_addresses
ADD CONSTRAINT adr_cn_fk FOREIGN KEY (contact_name_id) 
REFERENCES FMDB.fmdb_contact_names (contact_name_id)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_addresses
ADD CONSTRAINT adr_com_fk FOREIGN KEY (company_id) 
REFERENCES FMDB.fmdb_companies (company_id)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_addresses
ADD CONSTRAINT adr_reg_fk FOREIGN KEY (region_cd, country_cd) 
REFERENCES FMDB.fmdb_regions (region_cd, country_cd)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_chemicals
ADD CONSTRAINT che_sub_fk FOREIGN KEY (submission_id) 
REFERENCES FMDB.fmdb_submission (submission_id)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_companies
ADD CONSTRAINT com_ct1_fk FOREIGN KEY (company_type_cd) 
REFERENCES FMDB.fmdb_company_types (company_type_cd)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_contact_details
ADD CONSTRAINT cd_cn_fk FOREIGN KEY (contact_name_id) 
REFERENCES FMDB.fmdb_contact_names (contact_name_id)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_contact_details
ADD CONSTRAINT cd_com_fk FOREIGN KEY (company_id) 
REFERENCES FMDB.fmdb_companies (company_id)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_contact_names
ADD CONSTRAINT cn_com_fk FOREIGN KEY (company_id) 
REFERENCES FMDB.fmdb_companies (company_id)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_contact_names
ADD CONSTRAINT cn_ct_fk FOREIGN KEY (contact_type_cd) 
REFERENCES FMDB.fmdb_contact_types (contact_type_cd)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_dins
ADD CONSTRAINT din_che_fk FOREIGN KEY (chemical_id) 
REFERENCES FMDB.fmdb_chemicals (chemical_id)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_economic_statuses
ADD CONSTRAINT es_sub_fk FOREIGN KEY (submission_id) 
REFERENCES FMDB.fmdb_submission (submission_id)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_pla_logs
ADD CONSTRAINT pl_pla_fk FOREIGN KEY (pla_id) 
REFERENCES FMDB.fmdb_product_listing_agreement (pla_id)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_product_listing_agreement
ADD CONSTRAINT pla_pst_fk FOREIGN KEY (pla_status_cd) 
REFERENCES FMDB.fmdb_pla_status_types (pla_status_cd)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_product_listing_agreement
ADD CONSTRAINT pla_sub_fk FOREIGN KEY (submission_id) 
REFERENCES FMDB.fmdb_submission (submission_id)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_regions
ADD CONSTRAINT reg_ctry_fk FOREIGN KEY (country_cd) 
REFERENCES FMDB.fmdb_countries (country_cd)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_review_questions
ADD CONSTRAINT rq_rnt_fk FOREIGN KEY (reviewer_name_cd) 
REFERENCES FMDB.fmdb_reviewer_name_types (reviewer_name_cd)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_review_questions
ADD CONSTRAINT rq_srd_fk FOREIGN KEY (submission_review_id) 
REFERENCES FMDB.fmdb_submission_review_details (submission_review_id)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_submission
ADD CONSTRAINT sub_cn_fk FOREIGN KEY (contact_name_id) 
REFERENCES FMDB.fmdb_contact_names (contact_name_id)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_submission
ADD CONSTRAINT sub_com_fk FOREIGN KEY (company_id) 
REFERENCES FMDB.fmdb_companies (company_id)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_submission
ADD CONSTRAINT sub_st_fk FOREIGN KEY (submission_type_cd) 
REFERENCES FMDB.fmdb_submission_types (submission_type_cd)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_submission_review_details
ADD CONSTRAINT srd_brc_fk FOREIGN KEY (bia_requsted_cd) 
REFERENCES FMDB.fmdb_bia_requested_codes (bia_requsted_cd)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_submission_review_details
ADD CONSTRAINT srd_rs_fk FOREIGN KEY (review_status_cd) 
REFERENCES FMDB.fmdb_review_statuses (review_status_cd)
ON DELETE NO ACTION;

ALTER TABLE FMDB.fmdb_submission_review_details
ADD CONSTRAINT srd_sub_fk FOREIGN KEY (submission_id) 
REFERENCES FMDB.fmdb_submission (submission_id)
ON DELETE NO ACTION;