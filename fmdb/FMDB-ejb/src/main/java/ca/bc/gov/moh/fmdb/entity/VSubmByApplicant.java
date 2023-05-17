/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VSubmByApplicant.java                          *
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
@NamedQuery(name = "VSubmByApplicant.findAll", 
    query = "select o from VSubmByApplicant o")
@Table(name = "FMDB_SUBM_BY_APPLICANT_VW")
public class VSubmByApplicant implements Serializable {
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
    @Column(name="DBC_DATE")
    private Timestamp dbcDate;
    @Column(name="DBC_RECOMMENDATION")
    private String dbcRecommendation;
    @Column(name="DECISION_DATE")
    private Timestamp decisionDate;
    private String indication;
    @Column(name="LEGAL_NM", nullable = false)
    private String legalNm;
    @Column(name="PSD_STATUS", nullable = false)
    private String psdStatus;
    @Column(name="REVIEW_DAYS")
    private Long reviewDays;
    @Id
    private Long rn;
    @Column(name="SUBMISSION_NO", nullable = false)
    private Long submissionNo;
    @Column(name="SUB_TYPE", nullable = false)
    private String subType;

    public VSubmByApplicant() {
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

    public Timestamp getDbcDate() {
        return dbcDate;
    }

    public void setDbcDate(Timestamp dbcDate) {
        this.dbcDate = dbcDate;
    }

    public String getDbcRecommendation() {
        return dbcRecommendation;
    }

    public void setDbcRecommendation(String dbcRecommendation) {
        this.dbcRecommendation = dbcRecommendation;
    }

    public Timestamp getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(Timestamp decisionDate) {
        this.decisionDate = decisionDate;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getLegalNm() {
        return legalNm;
    }

    public void setLegalNm(String legalNm) {
        this.legalNm = legalNm;
    }

    public String getPsdStatus() {
        return psdStatus;
    }

    public void setPsdStatus(String psdStatus) {
        this.psdStatus = psdStatus;
    }

    public Long getReviewDays() {
        return reviewDays;
    }

    public void setReviewDays(Long reviewDays) {
        this.reviewDays = reviewDays;
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
            (dateofcdrRecommendation == null) ? "" : dateFormatter.format(dateofcdrRecommendation), 
            cdrRecommendation, 
            (dbcDate == null) ? "" : dateFormatter.format(dbcDate), 
            dbcRecommendation, 
            psdStatus, 
            (dateReviewStart == null) ? "" : dateFormatter.format(dateReviewStart), 
            (decisionDate == null) ? "" : dateFormatter.format(decisionDate), 
            (reviewDays == null) ? "" : String.format("%1d", reviewDays)
        };
        return arr;
    }
}
