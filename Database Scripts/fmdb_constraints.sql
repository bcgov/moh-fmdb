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