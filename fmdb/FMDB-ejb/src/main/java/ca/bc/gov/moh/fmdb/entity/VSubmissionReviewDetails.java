/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VSubmissionReviewDetails.java                  *
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "VSubmissionReviewDetails.findAll", 
    query = "select o from VSubmissionReviewDetails o")
@Table(name = "FMDB_SUBMISSION_REVIEW_DETAILS")
public class VSubmissionReviewDetails implements Serializable {
    @Column(name="BIA_ACTUAL_DT")
    private Timestamp biaActualDt;
    @Column(name="BIA_ASSIGNED_TO_NM")
    private String biaAssignedToNm;
    @Column(name="BIA_NOTES_TXT")
    private String biaNotesTxt;
    @Column(name="BIA_REQUESTED_BY_NM")
    private String biaRequestedByNm;
    @Column(name="BIA_REQUESTED_DT")
    private Timestamp biaRequestedDt;
    @Column(name="BIA_REQUSTED_CD")
    private String biaRequstedCd;
    @Column(name="BIA_TARGET_DT")
    private Timestamp biaTargetDt;
    @Column(name="BRIEFING_NOTE_SIGNED_DT")
    private Timestamp briefingNoteSignedDt;
    @Column(name="CDR_INITIATE_DT")
    private Timestamp cdrInitiateDt;
    @Column(name="CDR_REVIEW_YN")
    private String cdrReviewYn;
    @Column(name="CEDAC_COMMENTS_TXT")
    private String cedacCommentsTxt;
    @Column(name="CEDAC_MEET_ACTUAL_DT")
    private Timestamp cedacMeetActualDt;
    @Column(name="CEDAC_MEET_TARGET_DT")
    private Timestamp cedacMeetTargetDt;
    @Column(name="CEDAC_RECOMMENDATION_DT")
    private Timestamp cedacRecommendationDt;
    @Column(name="CEDAC_RECOMMENDATION_TXT")
    private String cedacRecommendationTxt;
    @Column(name="COMMENTS_TXT")
    private String commentsTxt;
    @Column(name="COMPANY_NOTICE_SENT_DT")
    private Timestamp companyNoticeSentDt;
    @Column(name="CONTACT_NAME_COMMENTS_TXT")
    private String contactNameCommentsTxt;
    @Column(name="CREATED_BY_NM", nullable = false)
    private String createdByNm;
    @Column(name="CREATED_ON_DTM", nullable = false)
    private Timestamp createdOnDtm;
    @Column(name="DBC_ACTUAL_DT")
    private Timestamp dbcActualDt;
    @Column(name="DBC_NOTES_TXT")
    private String dbcNotesTxt;
    @Column(name="DBC_RECOMMENDATION_FINALIZED_D")
    private Timestamp dbcRecommendationFinalizedD;
    @Column(name="DBC_RECOMMENDATION_NOTES_TXT")
    private String dbcRecommendationNotesTxt;
    @Column(name="DBC_TARGET_DT")
    private Timestamp dbcTargetDt;
    @Column(name="DBR_INITIATED_DT")
    private Timestamp dbrInitiatedDt;
    @Column(name="DBR_PHARMANET_DT")
    private Timestamp dbrPharmanetDt;
    @Column(name="LAST_MODIFIED_BY_NM")
    private String lastModifiedByNm;
    @Column(name="LAST_MODIFIED_DTM")
    private Timestamp lastModifiedDtm;
    @Column(name="LETTER_BIN")
    private byte[] letterBin;
    @Column(name="LETTER_IMAGE_PUBLIC_YN")
    private String letterImagePublicYn;
    @Column(name="REVIEW_STATUS_CD")
    private String reviewStatusCd;
    @Column(name="SA_COMPLETED_DT")
    private Timestamp saCompletedDt;
    @Column(name="SA_INITIATED_DT")
    private Timestamp saInitiatedDt;
    @Column(name="SA_NOTES_TXT")
    private String saNotesTxt;
    @Column(name="SA_REQUIRED_YN")
    private String saRequiredYn;
    @Column(name="SA_TARGET_DT")
    private Timestamp saTargetDt;
    @Column(name="STATELESS_TRANSACTION_NBR", nullable = false)
    private Long statelessTransactionNbr;
    @Column(name="SUBMISSION_ID", nullable = false)
    private Long submissionId;
    @Id
    @Column(name="SUBMISSION_REVIEW_ID", nullable = false)
    private Long submissionReviewId;

    public VSubmissionReviewDetails() {
    }

    public Timestamp getBiaActualDt() {
        return biaActualDt;
    }

    public void setBiaActualDt(Timestamp biaActualDt) {
        this.biaActualDt = biaActualDt;
    }

    public String getBiaAssignedToNm() {
        return biaAssignedToNm;
    }

    public void setBiaAssignedToNm(String biaAssignedToNm) {
        this.biaAssignedToNm = biaAssignedToNm;
    }

    public String getBiaNotesTxt() {
        return biaNotesTxt;
    }

    public void setBiaNotesTxt(String biaNotesTxt) {
        this.biaNotesTxt = biaNotesTxt;
    }

    public String getBiaRequestedByNm() {
        return biaRequestedByNm;
    }

    public void setBiaRequestedByNm(String biaRequestedByNm) {
        this.biaRequestedByNm = biaRequestedByNm;
    }

    public Timestamp getBiaRequestedDt() {
        return biaRequestedDt;
    }

    public void setBiaRequestedDt(Timestamp biaRequestedDt) {
        this.biaRequestedDt = biaRequestedDt;
    }

    public String getBiaRequstedCd() {
        return biaRequstedCd;
    }

    public void setBiaRequstedCd(String biaRequstedCd) {
        this.biaRequstedCd = biaRequstedCd;
    }

    public Timestamp getBiaTargetDt() {
        return biaTargetDt;
    }

    public void setBiaTargetDt(Timestamp biaTargetDt) {
        this.biaTargetDt = biaTargetDt;
    }

    public Timestamp getBriefingNoteSignedDt() {
        return briefingNoteSignedDt;
    }

    public void setBriefingNoteSignedDt(Timestamp briefingNoteSignedDt) {
        this.briefingNoteSignedDt = briefingNoteSignedDt;
    }

    public Timestamp getCdrInitiateDt() {
        return cdrInitiateDt;
    }

    public void setCdrInitiateDt(Timestamp cdrInitiateDt) {
        this.cdrInitiateDt = cdrInitiateDt;
    }

    public String getCdrReviewYn() {
        return cdrReviewYn;
    }

    public void setCdrReviewYn(String cdrReviewYn) {
        this.cdrReviewYn = cdrReviewYn;
    }

    public String getCedacCommentsTxt() {
        return cedacCommentsTxt;
    }

    public void setCedacCommentsTxt(String cedacCommentsTxt) {
        this.cedacCommentsTxt = cedacCommentsTxt;
    }

    public Timestamp getCedacMeetActualDt() {
        return cedacMeetActualDt;
    }

    public void setCedacMeetActualDt(Timestamp cedacMeetActualDt) {
        this.cedacMeetActualDt = cedacMeetActualDt;
    }

    public Timestamp getCedacMeetTargetDt() {
        return cedacMeetTargetDt;
    }

    public void setCedacMeetTargetDt(Timestamp cedacMeetTargetDt) {
        this.cedacMeetTargetDt = cedacMeetTargetDt;
    }

    public Timestamp getCedacRecommendationDt() {
        return cedacRecommendationDt;
    }

    public void setCedacRecommendationDt(Timestamp cedacRecommendationDt) {
        this.cedacRecommendationDt = cedacRecommendationDt;
    }

    public String getCedacRecommendationTxt() {
        return cedacRecommendationTxt;
    }

    public void setCedacRecommendationTxt(String cedacRecommendationTxt) {
        this.cedacRecommendationTxt = cedacRecommendationTxt;
    }

    public String getCommentsTxt() {
        return commentsTxt;
    }

    public void setCommentsTxt(String commentsTxt) {
        this.commentsTxt = commentsTxt;
    }

    public Timestamp getCompanyNoticeSentDt() {
        return companyNoticeSentDt;
    }

    public void setCompanyNoticeSentDt(Timestamp companyNoticeSentDt) {
        this.companyNoticeSentDt = companyNoticeSentDt;
    }

    public String getContactNameCommentsTxt() {
        return contactNameCommentsTxt;
    }

    public void setContactNameCommentsTxt(String contactNameCommentsTxt) {
        this.contactNameCommentsTxt = contactNameCommentsTxt;
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

    public Timestamp getDbcActualDt() {
        return dbcActualDt;
    }

    public void setDbcActualDt(Timestamp dbcActualDt) {
        this.dbcActualDt = dbcActualDt;
    }

    public String getDbcNotesTxt() {
        return dbcNotesTxt;
    }

    public void setDbcNotesTxt(String dbcNotesTxt) {
        this.dbcNotesTxt = dbcNotesTxt;
    }

    public Timestamp getDbcRecommendationFinalizedD() {
        return dbcRecommendationFinalizedD;
    }

    public void setDbcRecommendationFinalizedD(Timestamp dbcRecommendationFinalizedD) {
        this.dbcRecommendationFinalizedD = dbcRecommendationFinalizedD;
    }

    public String getDbcRecommendationNotesTxt() {
        return dbcRecommendationNotesTxt;
    }

    public void setDbcRecommendationNotesTxt(String dbcRecommendationNotesTxt) {
        this.dbcRecommendationNotesTxt = dbcRecommendationNotesTxt;
    }

    public Timestamp getDbcTargetDt() {
        return dbcTargetDt;
    }

    public void setDbcTargetDt(Timestamp dbcTargetDt) {
        this.dbcTargetDt = dbcTargetDt;
    }

    public Timestamp getDbrInitiatedDt() {
        return dbrInitiatedDt;
    }

    public void setDbrInitiatedDt(Timestamp dbrInitiatedDt) {
        this.dbrInitiatedDt = dbrInitiatedDt;
    }

    public Timestamp getDbrPharmanetDt() {
        return dbrPharmanetDt;
    }

    public void setDbrPharmanetDt(Timestamp dbrPharmanetDt) {
        this.dbrPharmanetDt = dbrPharmanetDt;
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

    public byte[] getLetterBin() {
        return letterBin;
    }

    public void setLetterBin(byte[] letterBin) {
        this.letterBin = letterBin;
    }

    public String getLetterImagePublicYn() {
        return letterImagePublicYn;
    }

    public void setLetterImagePublicYn(String letterImagePublicYn) {
        this.letterImagePublicYn = letterImagePublicYn;
    }

    public String getReviewStatusCd() {
        return reviewStatusCd;
    }

    public void setReviewStatusCd(String reviewStatusCd) {
        this.reviewStatusCd = reviewStatusCd;
    }

    public Timestamp getSaCompletedDt() {
        return saCompletedDt;
    }

    public void setSaCompletedDt(Timestamp saCompletedDt) {
        this.saCompletedDt = saCompletedDt;
    }

    public Timestamp getSaInitiatedDt() {
        return saInitiatedDt;
    }

    public void setSaInitiatedDt(Timestamp saInitiatedDt) {
        this.saInitiatedDt = saInitiatedDt;
    }

    public String getSaNotesTxt() {
        return saNotesTxt;
    }

    public void setSaNotesTxt(String saNotesTxt) {
        this.saNotesTxt = saNotesTxt;
    }

    public String getSaRequiredYn() {
        return saRequiredYn;
    }

    public void setSaRequiredYn(String saRequiredYn) {
        this.saRequiredYn = saRequiredYn;
    }

    public Timestamp getSaTargetDt() {
        return saTargetDt;
    }

    public void setSaTargetDt(Timestamp saTargetDt) {
        this.saTargetDt = saTargetDt;
    }

    public Long getStatelessTransactionNbr() {
        return statelessTransactionNbr;
    }

    public void setStatelessTransactionNbr(Long statelessTransactionNbr) {
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }

    public Long getSubmissionReviewId() {
        return submissionReviewId;
    }

    public void setSubmissionReviewId(Long submissionReviewId) {
        this.submissionReviewId = submissionReviewId;
    }
}
