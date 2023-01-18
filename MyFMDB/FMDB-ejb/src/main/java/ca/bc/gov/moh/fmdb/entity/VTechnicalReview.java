/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VTechnicalReview.java                          *
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
@NamedQuery(name = "VTechnicalReview.findAll", 
    query = "select o from VTechnicalReview o")
@Table(name = "FMDB_TECHNICAL_REVIEW_VW")
public class VTechnicalReview implements Serializable {
    private String applicant;
    private String cdr;
    @Column(name="CHEMICAL_NAME", nullable = false)
    private String chemicalName;
    private String indication;
    @Column(name="PSD_STATUS", nullable = false)
    private String psdStatus;
    @Column(name="QUESTION_SENT_DATE")
    private Timestamp questionSentDate;
    @Column(name="REQUESTED_YN")
    private String requestedYn;
    @Column(name="REVIEWER_NAME", nullable = false)
    private String reviewerName;
    @Column(name="REVIEW_COMPLETION_DATE")
    private Timestamp reviewCompletionDate;
    @Column(name="REVIEW_DAYS")
    private Long reviewDays;
    @Column(name="REVIEW_TARGET_DATE")
    private Timestamp reviewTargetDate;
    @Id
    private Long rn;
    @Column(name="SUBMISSION_NO", nullable = false)
    private Long submissionNo;
    @Column(name="SUB_TYPE", nullable = false)
    private String subType;

    public VTechnicalReview() {
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

    public String getChemicalName() {
        return chemicalName;
    }

    public void setChemicalName(String chemicalName) {
        this.chemicalName = chemicalName;
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

    public Timestamp getQuestionSentDate() {
        return questionSentDate;
    }

    public void setQuestionSentDate(Timestamp questionSentDate) {
        this.questionSentDate = questionSentDate;
    }

    public String getRequestedYn() {
        return requestedYn;
    }

    public void setRequestedYn(String requestedYn) {
        this.requestedYn = requestedYn;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public Timestamp getReviewCompletionDate() {
        return reviewCompletionDate;
    }

    public void setReviewCompletionDate(Timestamp reviewCompletionDate) {
        this.reviewCompletionDate = reviewCompletionDate;
    }

    public Long getReviewDays() {
        return reviewDays;
    }

    public void setReviewDays(Long reviewDays) {
        this.reviewDays = reviewDays;
    }

    public Timestamp getReviewTargetDate() {
        return reviewTargetDate;
    }

    public void setReviewTargetDate(Timestamp reviewTargetDate) {
        this.reviewTargetDate = reviewTargetDate;
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
            reviewerName, 
            (questionSentDate == null) ? "" : dateFormatter.format(questionSentDate), 
            (reviewTargetDate == null) ? "" : dateFormatter.format(reviewTargetDate), 
            (reviewCompletionDate == null) ? "" : dateFormatter.format(reviewCompletionDate), 
            (reviewDays == null) ? "" : String.format("%1d", reviewDays), 
            psdStatus
        };
        return arr;
    }
}
