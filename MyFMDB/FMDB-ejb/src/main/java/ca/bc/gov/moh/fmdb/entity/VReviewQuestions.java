/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VReviewQuestions.java                          *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package ca.bc.gov.moh.fmdb.entity;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "VReviewQuestions.findAll", 
    query = "select o from VReviewQuestions o")
@Table(name = "FMDB_REVIEW_QUESTIONS")
public class VReviewQuestions implements Serializable {
    @Column(name="ACTUAL_COMPLETION_DT")
    private Timestamp actualCompletionDt;
    private String conclusion;
    @Column(name="CREATED_BY_NM", nullable = false)
    private String createdByNm;
    @Column(name="CREATED_ON_DTM", nullable = false)
    private Timestamp createdOnDtm;
    @Column(name="LAST_MODIFIED_BY_NM")
    private String lastModifiedByNm;
    @Column(name="LAST_MODIFIED_DTM")
    private Timestamp lastModifiedDtm;
    @Column(name="PSD_REQUESTED_YN")
    private String psdRequestedYn;
    @Column(name="QUESTION_SENT_DT")
    private Timestamp questionSentDt;
    @Column(name="QUESTION_TXT")
    private String questionTxt;
    @Column(name="REVIEWER_NAME_CD")
    private String reviewerNameCd;
    @Id
    @Column(name="REVIEW_QUESTION_ID", nullable = false)
    private Long reviewQuestionId;
    @Column(name="STATELESS_TRANSACTION_NBR", nullable = false)
    private Long statelessTransactionNbr;
    @Column(name="SUBMISSION_REVIEW_ID", nullable = false)
    private Long submissionReviewId;
    @Column(name="TARGET_COMPLETION_DT")
    private Timestamp targetCompletionDt;

    public VReviewQuestions() {
    }

    public Timestamp getActualCompletionDt() {
        return actualCompletionDt;
    }

    public void setActualCompletionDt(Timestamp actualCompletionDt) {
        this.actualCompletionDt = actualCompletionDt;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getCreatedByNm() {
        return createdByNm;
    }

    public void setCreatedByNm(String createdByNm) {
        this.createdByNm = createdByNm;
    }

    public Timestamp getCreatedOnDtm() {
        return createdOnDtm;
    }

    public void setCreatedOnDtm(Timestamp createdOnDtm) {
        this.createdOnDtm = createdOnDtm;
    }

    public String getLastModifiedByNm() {
        return lastModifiedByNm;
    }

    public void setLastModifiedByNm(String lastModifiedByNm) {
        this.lastModifiedByNm = lastModifiedByNm;
    }

    public Timestamp getLastModifiedDtm() {
        return lastModifiedDtm;
    }

    public void setLastModifiedDtm(Timestamp lastModifiedDtm) {
        this.lastModifiedDtm = lastModifiedDtm;
    }

    public String getPsdRequestedYn() {
        return psdRequestedYn;
    }

    public void setPsdRequestedYn(String psdRequestedYn) {
        this.psdRequestedYn = psdRequestedYn;
    }

    public Timestamp getQuestionSentDt() {
        return questionSentDt;
    }

    public void setQuestionSentDt(Timestamp questionSentDt) {
        this.questionSentDt = questionSentDt;
    }

    public String getQuestionTxt() {
        return questionTxt;
    }

    public void setQuestionTxt(String questionTxt) {
        this.questionTxt = questionTxt;
    }

    public String getReviewerNameCd() {
        return reviewerNameCd;
    }

    public void setReviewerNameCd(String reviewerNameCd) {
        this.reviewerNameCd = reviewerNameCd;
    }

    public Long getReviewQuestionId() {
        return reviewQuestionId;
    }

    public void setReviewQuestionId(Long reviewQuestionId) {
        this.reviewQuestionId = reviewQuestionId;
    }

    public Long getStatelessTransactionNbr() {
        return statelessTransactionNbr;
    }

    public void setStatelessTransactionNbr(Long statelessTransactionNbr) {
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public Long getSubmissionReviewId() {
        return submissionReviewId;
    }

    public void setSubmissionReviewId(Long submissionReviewId) {
        this.submissionReviewId = submissionReviewId;
    }

    public Timestamp getTargetCompletionDt() {
        return targetCompletionDt;
    }

    public void setTargetCompletionDt(Timestamp targetCompletionDt) {
        this.targetCompletionDt = targetCompletionDt;
    }
}
