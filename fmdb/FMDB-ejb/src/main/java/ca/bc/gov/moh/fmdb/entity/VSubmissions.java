/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VSubmissions.java                              *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

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
@NamedQuery(name = "VSubmissions.findAll", query = "select o from VSubmissions o")
@Table(name = "FMDB_SUBMISSIONS_VW")
public class VSubmissions
        implements Serializable {

    private String applicant;
    private String cdr;
    @Column(name = "CDR_RECOMMENDATION")
    private String cdrRecommendation;
    @Column(name = "CHEMICAL_NAME", nullable = false)
    private String chemicalName;
    @Column(name = "DATE_REVIEW_START")
    private Timestamp dateReviewStart;
    @Column(name = "DBC_DATE")
    private Timestamp dbcDate;
    @Column(name = "DBC_RECOMMENDATION")
    private String dbcRecommendation;
    @Column(name = "DECISION_DATE")
    private Timestamp decisionDate;
    private String indication;
    @Column(name = "PSD_STATUS", nullable = false)
    private String psdStatus;
    @Column(name = "REVIEW_DAYS")
    private Long reviewDays;
    @Id
    @Column(name = "SUBMISSION_NO", nullable = false)
    private Long submissionNo;
    @Column(name = "SUB_TYPE", nullable = false)
    private String subType;

    public VSubmissions() {
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
            cdrRecommendation, 
            (dbcDate == null) ? "" : dateFormatter.format( dbcDate), 
            dbcRecommendation, 
            psdStatus, 
            (dateReviewStart == null) ? "" : dateFormatter.format(dateReviewStart), 
            (decisionDate == null) ? "" : dateFormatter.format(decisionDate), 
            (reviewDays == null) ? "" : String.format("%1d", reviewDays)
        };
        return arr;
    }
}
