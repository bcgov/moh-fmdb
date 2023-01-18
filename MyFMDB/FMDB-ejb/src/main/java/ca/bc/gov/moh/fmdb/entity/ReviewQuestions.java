/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ReviewQuestions.java                           *
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

import ca.bc.gov.moh.fmdb.util.AuditListener;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author Chris.Prince
 */
@Entity
@Table(name = "FMDB_REVIEW_QUESTIONS")
@SequenceGenerator(name="RQUESTIONS_SEQUENCE", sequenceName="FMDB_RQ_SEQ", allocationSize=1)
@EntityListeners({AuditListener.class})
public class ReviewQuestions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RQUESTIONS_SEQUENCE")
    @Column(name = "REVIEW_QUESTION_ID", nullable = false)
    private Long reviewQuestionId;
    @Column(name = "QUESTION_TXT")
    private String questionTxt;
    @Column(name = "CONCLUSION")
    private String conclusion;
    @Column(name = "QUESTION_SENT_DT")
    @Temporal(TemporalType.DATE)
    private Date questionSentDt;
    @Column(name = "TARGET_COMPLETION_DT")
    @Temporal(TemporalType.DATE)
    private Date targetCompletionDt;
    @Column(name = "PSD_REQUESTED_YN")
    private Character psdRequestedYn;
    @Column(name = "ACTUAL_COMPLETION_DT")
    @Temporal(TemporalType.DATE)
    private Date actualCompletionDt;
    @Column(name = "CREATED_BY_NM", nullable = false)
    private String createdByNm;
    @Column(name = "CREATED_ON_DTM", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdOnDtm;
    @Column(name = "LAST_MODIFIED_BY_NM")
    private String lastModifiedByNm;
    @Column(name = "LAST_MODIFIED_DTM")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDtm;
    @Column(name = "STATELESS_TRANSACTION_NBR", nullable = false)
    @Version
    private Long statelessTransactionNbr;
    @JoinColumn(name = "REVIEWER_NAME_CD", referencedColumnName = "REVIEWER_NAME_CD")
    @ManyToOne
    private ReviewerNameTypes reviewerNameCd;
    @JoinColumn(name = "SUBMISSION_REVIEW_ID", referencedColumnName = "SUBMISSION_REVIEW_ID")
    @ManyToOne
    private SubmissionReviewDetails submissionReviewId;
    
    
    public ReviewQuestions() {
    }

    public ReviewQuestions(SubmissionReviewDetails submissionReviewDetails) {
        this.submissionReviewId = submissionReviewDetails;
    }
    
    public ReviewQuestions(Long reviewQuestionId) {
        this.reviewQuestionId = reviewQuestionId;
    }

    public ReviewQuestions(Long reviewQuestionId, String createdByNm, Date createdOnDtm, Long statelessTransactionNbr) {
        this.reviewQuestionId = reviewQuestionId;
        this.createdByNm = createdByNm;
        this.createdOnDtm = createdOnDtm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public Long getReviewQuestionId() {
        return reviewQuestionId;
    }

    public void setReviewQuestionId(Long reviewQuestionId) {
        this.reviewQuestionId = reviewQuestionId;
    }

    public String getQuestionTxt() {
        return questionTxt;
    }

    public void setQuestionTxt(String questionTxt) {
        this.questionTxt = questionTxt;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public Date getQuestionSentDt() {
        return questionSentDt;
    }

    public void setQuestionSentDt(Date questionSentDt) {
        this.questionSentDt = questionSentDt;
    }

    public Date getTargetCompletionDt() {
        return targetCompletionDt;
    }

    public void setTargetCompletionDt(Date targetCompletionDt) {
        this.targetCompletionDt = targetCompletionDt;
    }

    public Character getPsdRequestedYn() {
        return psdRequestedYn;
    }

    public void setPsdRequestedYn(Character psdRequestedYn) {
        this.psdRequestedYn = psdRequestedYn;
    }

    public Date getActualCompletionDt() {
        return actualCompletionDt;
    }

    public void setActualCompletionDt(Date actualCompletionDt) {
        this.actualCompletionDt = actualCompletionDt;
    }

    public String getCreatedByNm() {
        return createdByNm;
    }

    public void setCreatedByNm(String createdByNm) {
        this.createdByNm = createdByNm;
    }

    public Date getCreatedOnDtm() {
        return createdOnDtm;
    }

    public void setCreatedOnDtm(Date createdOnDtm) {
        this.createdOnDtm = createdOnDtm;
    }

    public String getLastModifiedByNm() {
        return lastModifiedByNm;
    }

    public void setLastModifiedByNm(String lastModifiedByNm) {
        this.lastModifiedByNm = lastModifiedByNm;
    }

    public Date getLastModifiedDtm() {
        return lastModifiedDtm;
    }

    public void setLastModifiedDtm(Date lastModifiedDtm) {
        this.lastModifiedDtm = lastModifiedDtm;
    }

    public Long getStatelessTransactionNbr() {
        return statelessTransactionNbr;
    }

    public void setStatelessTransactionNbr(Long statelessTransactionNbr) {
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public ReviewerNameTypes getReviewerNameCd() {
        return reviewerNameCd;
    }

    public void setReviewerNameCd(ReviewerNameTypes reviewerNameCd) {
        this.reviewerNameCd = reviewerNameCd;
    }

    public SubmissionReviewDetails getSubmissionReviewId() {
        return submissionReviewId;
    }

    public void setSubmissionReviewId(SubmissionReviewDetails submissionReviewId) {
        this.submissionReviewId = submissionReviewId;
    }

    public String getReviewQuestionIdStr() {
        return reviewQuestionId.toString();
    }

    public void setReviewQuestionIdStr(String reviewQuestionIdStr) {
        //this.reviewQuestionIdStr = reviewQuestionIdStr;
    }
    
    

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = prime * hash + ((reviewQuestionId == null ? 0 : reviewQuestionId.hashCode()));
        hash = prime * hash + ((questionTxt == null ? 0 : questionTxt.hashCode()));
        hash = prime * hash + ((conclusion == null ? 0 : conclusion.hashCode()));
        hash = prime * hash + ((questionSentDt == null ? 0 : questionSentDt.hashCode()));
        hash = prime * hash + ((targetCompletionDt == null ? 0 : targetCompletionDt.hashCode()));
        hash = prime * hash + ((psdRequestedYn == null ? 0 : psdRequestedYn.hashCode()));
        hash = prime * hash + ((actualCompletionDt == null ? 0 : actualCompletionDt.hashCode()));
        hash = prime * hash + ((reviewerNameCd == null ? 0 : reviewerNameCd.hashCode()));
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // This method has been updated to work in the case that id fields are 
        // not set (e.g. when deleting a yet to be persisted record from a list 
        // of new records that have not been saved.
        if (!(object instanceof ReviewQuestions)) {
            return false;
        }
        ReviewQuestions other = (ReviewQuestions) object;
        if ((this.reviewQuestionId == null && other.reviewQuestionId != null) || (this.reviewQuestionId != null && !this.reviewQuestionId.equals(other.reviewQuestionId))
                || (this.reviewQuestionId == null && other.reviewQuestionId == null 
                    && ((this.questionTxt != null && !this.questionTxt.equals(other.questionTxt)) 
                        || (this.conclusion != null && !this.conclusion.equals(other.conclusion)) 
                        || (this.questionSentDt != null && !this.questionSentDt.equals(other.questionSentDt)) 
                        || (this.targetCompletionDt != null && !this.targetCompletionDt.equals(other.targetCompletionDt)) 
                        || (this.psdRequestedYn != null && !this.psdRequestedYn.equals(other.psdRequestedYn)) 
                        || (this.actualCompletionDt != null && !this.actualCompletionDt.equals(other.actualCompletionDt)) 
                        || (this.reviewerNameCd != null && !this.reviewerNameCd.equals(other.reviewerNameCd))))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbReviewQuestions[reviewQuestionId=" + reviewQuestionId + "]";
    }

}
