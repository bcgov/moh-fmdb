/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VDrugBenefitCommittee.java                     *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.bc.gov.moh.fmdb.entity;

import java.io.Serializable;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "VDrugBenefitCommittee.findAll", 
    query = "select o from VDrugBenefitCommittee o")
@Table(name = "FMDB_DRUG_BENEFIT_COMMITTEE_VW")
public class VDrugBenefitCommittee implements Serializable {
    private String applicant;
    private String cdr;
    @Column(name="CDR_RECOMMENDATION")
    private String cdrRecommendation;
    @Column(name="CHEMICAL_NAME", nullable = false)
    private String chemicalName;
    @Column(name="DATEOFCDR_RECOMMENDATION")
    private Timestamp dateofcdrRecommendation;
    @Column(name="DATE_REVIEW_START")
    private Timestamp dateReviewStart;
    @Column(name="DBCREC_FINALIZED_DATE")
    private Timestamp dbcrecFinalizedDate;
    @Column(name="DBCREC_NOTES")
    private String dbcrecNotes;
    @Column(name="DBC_ACTUAL_DATE")
    private Timestamp dbcActualDate;
    @Column(name="DBC_DAYS")
    private Long dbcDays;
    @Column(name="DBC_NOTES")
    private String dbcNotes;
    @Column(name="DBC_TARGET_DATE")
    private Timestamp dbcTargetDate;
    private String indication;
    @Column(name="PSD_STATUS", nullable = false)
    private String psdStatus;
    @Id
    private Long rn;
    @Column(name="SUBMISSION_NO", nullable = false)
    private Long submissionNo;
    @Column(name="SUB_TYPE", nullable = false)
    private String subType;

    public VDrugBenefitCommittee() {
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getCdr() {
        return cdr;
    }

    public void setCdr(String cdr) {
        this.cdr = cdr;
    }

    public String getCdrRecommendation() {
        return cdrRecommendation;
    }

    public void setCdrRecommendation(String cdrRecommendation) {
        this.cdrRecommendation = cdrRecommendation;
    }

    public String getChemicalName() {
        return chemicalName;
    }

    public void setChemicalName(String chemicalName) {
        this.chemicalName = chemicalName;
    }

    public Timestamp getDateofcdrRecommendation() {
        return dateofcdrRecommendation;
    }

    public void setDateofcdrRecommendation(Timestamp dateofcdrRecommendation) {
        this.dateofcdrRecommendation = dateofcdrRecommendation;
    }

    public Timestamp getDateReviewStart() {
        return dateReviewStart;
    }

    public void setDateReviewStart(Timestamp dateReviewStart) {
        this.dateReviewStart = dateReviewStart;
    }

    public Timestamp getDbcrecFinalizedDate() {
        return dbcrecFinalizedDate;
    }

    public void setDbcrecFinalizedDate(Timestamp dbcrecFinalizedDate) {
        this.dbcrecFinalizedDate = dbcrecFinalizedDate;
    }

    public String getDbcrecNotes() {
        return dbcrecNotes;
    }

    public void setDbcrecNotes(String dbcrecNotes) {
        this.dbcrecNotes = dbcrecNotes;
    }

    public Timestamp getDbcActualDate() {
        return dbcActualDate;
    }

    public void setDbcActualDate(Timestamp dbcActualDate) {
        this.dbcActualDate = dbcActualDate;
    }

    public Long getDbcDays() {
        return dbcDays;
    }

    public void setDbcDays(Long dbcDays) {
        this.dbcDays = dbcDays;
    }

    public String getDbcNotes() {
        return dbcNotes;
    }

    public void setDbcNotes(String dbcNotes) {
        this.dbcNotes = dbcNotes;
    }

    public Timestamp getDbcTargetDate() {
        return dbcTargetDate;
    }

    public void setDbcTargetDate(Timestamp dbcTargetDate) {
        this.dbcTargetDate = dbcTargetDate;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getPsdStatus() {
        return psdStatus;
    }

    public void setPsdStatus(String psdStatus) {
        this.psdStatus = psdStatus;
    }

    public Long getRn() {
        return rn;
    }

    public void setRn(Long rn) {
        this.rn = rn;
    }

    public Long getSubmissionNo() {
        return submissionNo;
    }

    public void setSubmissionNo(Long submissionNo) {
        this.submissionNo = submissionNo;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }
  
    /**
     * Moving reporting over from Crystal Reports to OpenCSV which requires a
     * String array for each row of data
     */
    public String[] toReportRow() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String[] arr = {
            (submissionNo == null) ? "" : String.format("%1d", submissionNo), 
            chemicalName, 
            applicant, 
            indication, 
            subType, 
            cdr, 
            (dateReviewStart == null) ? "" : dateFormatter.format(dateReviewStart), 
            (dateofcdrRecommendation == null) ? "" : dateFormatter.format(dateofcdrRecommendation), 
            cdrRecommendation, 
            (dbcTargetDate == null) ? "" : dateFormatter.format(dbcTargetDate), 
            (dbcActualDate == null) ? "" : dateFormatter.format(dbcActualDate), 
            (dbcrecFinalizedDate == null) ? "" : dateFormatter.format(dbcrecFinalizedDate), 
            (dbcDays == null) ? "" : String.format("%1d", dbcDays), 
            dbcrecNotes, 
            dbcNotes, 
            psdStatus
        };
        return arr;
    }
}
