/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SubmissionReviewDetails.java                   *
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
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "FMDB_SUBMISSION_REVIEW_DETAILS")
@SequenceGenerator(name="SRDETAILS_SEQUENCE", sequenceName="FMDB_SRD_SEQ", allocationSize=1)
@EntityListeners({AuditListener.class})
public class SubmissionReviewDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SRDETAILS_SEQUENCE")
    @Column(name = "SUBMISSION_REVIEW_ID", nullable = false)
    private Long submissionReviewId;
    @Column(name = "VIEW_DBC_ACTUAL_DATE_YN", nullable = true)
    private Character viewDBCActualDate_YN;
    @Column(name = "BIA_ACTUAL_DT")
    @Temporal(TemporalType.DATE)
    private Date biaActualDt;
    @Column(name = "BIA_ASSIGNED_TO_NM")
    private String biaAssignedToNm;
    @Column(name = "BIA_NOTES_TXT")
    private String biaNotesTxt;
    @Column(name = "BIA_REQUESTED_DT")
    @Temporal(TemporalType.DATE)
    private Date biaRequestedDt;
    @Column(name = "BIA_TARGET_DT")
    @Temporal(TemporalType.DATE)
    private Date biaTargetDt;
    @Column(name = "BIA_REQUESTED_BY_NM")
    private String biaRequestedByNm;
    @Column(name = "BRIEFING_NOTE_SIGNED_DT")
    @Temporal(TemporalType.DATE)
    private Date briefingNoteSignedDt;
    @Column(name = "CDR_INITIATE_DT")
    @Temporal(TemporalType.DATE)
    private Date cdrInitiateDt;
    @Column(name = "CDR_REVIEW_YN")
    private Character cdrReviewYn;
    @Column(name = "DBC_ACTUAL_DT")
    @Temporal(TemporalType.DATE)
    private Date dbcActualDt;
    @Column(name = "DBC_NOTES_TXT")
    private String dbcNotesTxt;
    @Column(name = "DBC_RECOMMENDATION_FINALIZED_D")
    @Temporal(TemporalType.DATE)
    private Date dbcRecommendationFinalizedD;
    @Column(name = "DBC_RECOMMENDATION_NOTES_TXT")
    private String dbcRecommendationNotesTxt;
    @Column(name = "DBC_TARGET_DT")
    @Temporal(TemporalType.DATE)
    private Date dbcTargetDt;
    @Column(name = "DBR_INITIATED_DT")
    @Temporal(TemporalType.DATE)
    private Date dbrInitiatedDt;
    @Column(name = "DBR_PHARMANET_DT")
    @Temporal(TemporalType.DATE)
    private Date dbrPharmanetDt;
    @Column(name = "SA_COMPLETED_DT")
    @Temporal(TemporalType.DATE)
    private Date saCompletedDt;
    @Column(name = "SA_INITIATED_DT")
    @Temporal(TemporalType.DATE)
    private Date saInitiatedDt;
    @Column(name = "SA_NOTES_TXT")
    private String saNotesTxt;
    @Column(name = "SA_REQUIRED_YN")
    private Character saRequiredYn;
    @Column(name = "SA_TARGET_DT")
    @Temporal(TemporalType.DATE)
    private Date saTargetDt;
    @Column(name = "CEDAC_COMMENTS_TXT")
    private String cedacCommentsTxt;
    @Column(name = "CEDAC_MEET_ACTUAL_DT")
    @Temporal(TemporalType.DATE)
    private Date cedacMeetActualDt;
    @Column(name = "CEDAC_MEET_TARGET_DT")
    @Temporal(TemporalType.DATE)
    private Date cedacMeetTargetDt;
    @Column(name = "CEDAC_RECOMMENDATION_DT")
    @Temporal(TemporalType.DATE)
    private Date cedacRecommendationDt;
    @Column(name = "CEDAC_RECOMMENDATION_TXT")
    private String cedacRecommendationTxt;
    @Column(name = "COMMENTS_TXT")
    private String commentsTxt;
    @Column(name = "CONTACT_NAME_COMMENTS_TXT")
    private String contactNameCommentsTxt;
    @Column(name = "COMPANY_NOTICE_SENT_DT")
    @Temporal(TemporalType.DATE)
    private Date companyNoticeSentDt;
    @Lob
    @Column(name = "LETTER_BIN")
    private Serializable letterBin;
    @Column(name = "LETTER_IMAGE_PUBLIC_YN")
    private Character letterImagePublicYn;
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
    @Column(name = "TENTATIVE_DT_STANDARD")
    @Temporal(TemporalType.DATE)
    private Date tentativeDtStandard;    
    @Column(name = "TENTATIVE_DT_COMPLEX")
    @Temporal(TemporalType.DATE)
    private Date tentativeDtComplex;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "submissionReviewId", fetch=FetchType.EAGER, orphanRemoval=true)
    private Collection<ReviewQuestions> fmdbReviewQuestionsCollection;

    @JoinColumn(name = "BIA_REQUSTED_CD", referencedColumnName = "BIA_REQUSTED_CD")
    @ManyToOne
    private BiaRequestedCodes biaRequstedCd;

    @JoinColumn(name = "REVIEW_STATUS_CD", referencedColumnName = "REVIEW_STATUS_CD")
    @ManyToOne
    private ReviewStatuses reviewStatusCd;
    
    @JoinColumn(name = "SUBMISSION_ID", referencedColumnName = "SUBMISSION_ID")
    @OneToOne
    private Submission submissionId;


    public SubmissionReviewDetails() {
    }

    public SubmissionReviewDetails(Long submissionReviewId) {
        this.submissionReviewId = submissionReviewId;
    }

    public SubmissionReviewDetails(Long submissionReviewId, String createdByNm, Date createdOnDtm, Long statelessTransactionNbr) {
        this.submissionReviewId = submissionReviewId;
        this.createdByNm = createdByNm;
        this.createdOnDtm = createdOnDtm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public Long getSubmissionReviewId() {
        return submissionReviewId;
    }

    public void setSubmissionReviewId(Long submissionReviewId) {
        this.submissionReviewId = submissionReviewId;
    }

    public Date getBiaActualDt() {
        return biaActualDt;
    }

    public void setBiaActualDt(Date biaActualDt) {
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

    public Date getBiaRequestedDt() {
        return biaRequestedDt;
    }

    public void setBiaRequestedDt(Date biaRequestedDt) {
        this.biaRequestedDt = biaRequestedDt;
    }

    public Date getBiaTargetDt() {
        return biaTargetDt;
    }

    public void setBiaTargetDt(Date biaTargetDt) {
        this.biaTargetDt = biaTargetDt;
    }

    public String getBiaRequestedByNm() {
        return biaRequestedByNm;
    }

    public void setBiaRequestedByNm(String biaRequestedByNm) {
        this.biaRequestedByNm = biaRequestedByNm;
    }

    public Date getBriefingNoteSignedDt() {
        return briefingNoteSignedDt;
    }

    public void setBriefingNoteSignedDt(Date briefingNoteSignedDt) {
        this.briefingNoteSignedDt = briefingNoteSignedDt;
    }

    public Date getCdrInitiateDt() {
        return cdrInitiateDt;
    }

    public void setCdrInitiateDt(Date cdrInitiateDt) {
        this.cdrInitiateDt = cdrInitiateDt;
    }

    public Character getCdrReviewYn() {
        return cdrReviewYn;
    }

    public void setCdrReviewYn(Character cdrReviewYn) {
        this.cdrReviewYn = cdrReviewYn;
    }

    public Date getDbcActualDt() {
        return dbcActualDt;
    }

    public void setDbcActualDt(Date dbcActualDt) {
        this.dbcActualDt = dbcActualDt;
    }

    public String getDbcNotesTxt() {
        return dbcNotesTxt;
    }

    public void setDbcNotesTxt(String dbcNotesTxt) {
        this.dbcNotesTxt = dbcNotesTxt;
    }

    public Date getDbcRecommendationFinalizedD() {
        return dbcRecommendationFinalizedD;
    }

    public void setDbcRecommendationFinalizedD(Date dbcRecommendationFinalizedD) {
        this.dbcRecommendationFinalizedD = dbcRecommendationFinalizedD;
    }

    public String getDbcRecommendationNotesTxt() {
        return dbcRecommendationNotesTxt;
    }

    public void setDbcRecommendationNotesTxt(String dbcRecommendationNotesTxt) {
        this.dbcRecommendationNotesTxt = dbcRecommendationNotesTxt;
    }

    public Date getDbcTargetDt() {
        return dbcTargetDt;
    }

    public void setDbcTargetDt(Date dbcTargetDt) {
        this.dbcTargetDt = dbcTargetDt;
    }

    public Date getDbrInitiatedDt() {
        return dbrInitiatedDt;
    }

    public void setDbrInitiatedDt(Date dbrInitiatedDt) {
        this.dbrInitiatedDt = dbrInitiatedDt;
    }

    public Date getDbrPharmanetDt() {
        return dbrPharmanetDt;
    }

    public void setDbrPharmanetDt(Date dbrPharmanetDt) {
        this.dbrPharmanetDt = dbrPharmanetDt;
    }

    public Date getSaCompletedDt() {
        return saCompletedDt;
    }

    public void setSaCompletedDt(Date saCompletedDt) {
        this.saCompletedDt = saCompletedDt;
    }

    public Date getSaInitiatedDt() {
        return saInitiatedDt;
    }

    public void setSaInitiatedDt(Date saInitiatedDt) {
        this.saInitiatedDt = saInitiatedDt;
    }

    public String getSaNotesTxt() {
        return saNotesTxt;
    }

    public void setSaNotesTxt(String saNotesTxt) {
        this.saNotesTxt = saNotesTxt;
    }

    public Character getSaRequiredYn() {
        return saRequiredYn;
    }

    public void setSaRequiredYn(Character saRequiredYn) {
        this.saRequiredYn = saRequiredYn;
    }

    public Date getSaTargetDt() {
        return saTargetDt;
    }

    public void setSaTargetDt(Date saTargetDt) {
        this.saTargetDt = saTargetDt;
    }

    public String getCedacCommentsTxt() {
        return cedacCommentsTxt;
    }

    public void setCedacCommentsTxt(String cedacCommentsTxt) {
        this.cedacCommentsTxt = cedacCommentsTxt;
    }

    public Date getCedacMeetActualDt() {
        return cedacMeetActualDt;
    }

    public void setCedacMeetActualDt(Date cedacMeetActualDt) {
        this.cedacMeetActualDt = cedacMeetActualDt;
    }

    public Date getCedacMeetTargetDt() {
        return cedacMeetTargetDt;
    }

    public void setCedacMeetTargetDt(Date cedacMeetTargetDt) {
        this.cedacMeetTargetDt = cedacMeetTargetDt;
    }

    public Date getCedacRecommendationDt() {
        return cedacRecommendationDt;
    }

    public void setCedacRecommendationDt(Date cedacRecommendationDt) {
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

    public Date getCompanyNoticeSentDt() {
        return companyNoticeSentDt;
    }

    public void setCompanyNoticeSentDt(Date companyNoticeSentDt) {
        this.companyNoticeSentDt = companyNoticeSentDt;
    }
    
    public Serializable getLetterBin() {
        return letterBin;
        
    }

    public void setLetterBin(Serializable letterBin) {
        this.letterBin = letterBin;
    }
    
    public Character getLetterImagePublicYn() {
        return letterImagePublicYn;
    }

    public void setLetterImagePublicYn(Character letterImagePublicYn) {
        this.letterImagePublicYn = letterImagePublicYn;
    }

    public Boolean getLetterImagePublicBool() {
        return new Character('Y').equals(letterImagePublicYn);
    }

    public void setLetterImagePublicBool(Boolean letterImagePublicBool) {
        this.letterImagePublicYn = letterImagePublicBool ? 'Y' : 'N';
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
    
    public Date getTentativeDtStandard() {
        return tentativeDtStandard;
    }

    public void setTentativeDtStandard(Date tentativeDtStandard) {
        this.tentativeDtStandard = tentativeDtStandard;
    }
    
    public Date getTentativeDtComplex() {
        return tentativeDtComplex;
    }

    public void setTentativeDtComplex(Date tentativeDtComplex) {
        this.tentativeDtComplex = tentativeDtComplex;
    }    

    public Collection<ReviewQuestions> getFmdbReviewQuestionsCollection() {
        return fmdbReviewQuestionsCollection;
    }

    public void setFmdbReviewQuestionsCollection(Collection<ReviewQuestions> fmdbReviewQuestionsCollection) {
        this.fmdbReviewQuestionsCollection = fmdbReviewQuestionsCollection;
    }

    public BiaRequestedCodes getBiaRequstedCd() {
        return biaRequstedCd;
    }

    public void setBiaRequstedCd(BiaRequestedCodes biaRequstedCd) {
        this.biaRequstedCd = biaRequstedCd;
    }

    public ReviewStatuses getReviewStatusCd() {
        return reviewStatusCd;
    }

    public void setReviewStatusCd(ReviewStatuses reviewStatusCd) {
        this.reviewStatusCd = reviewStatusCd;
    }

    public Submission getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Submission submissionId) {
        this.submissionId = submissionId;
    }

    public void setContactNameCommentsTxt(String contactNameCommentsTxt) {
        this.contactNameCommentsTxt = contactNameCommentsTxt;
    }

    public String getContactNameCommentsTxt() {
        return contactNameCommentsTxt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (submissionReviewId != null ? submissionReviewId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubmissionReviewDetails)) {
            return false;
        }
        SubmissionReviewDetails other = (SubmissionReviewDetails) object;
        if ((this.submissionReviewId == null && other.submissionReviewId != null) || (this.submissionReviewId != null && !this.submissionReviewId.equals(other.submissionReviewId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbSubmissionReviewDetails[submissionReviewId=" + submissionReviewId + "]";
    }

    /**
     * @return the viewDBCActualDate_YN
     */
    public Character getViewDBCActualDate_YN() {
        return viewDBCActualDate_YN;
    }

    /**
     * @param viewDBCActualDate_YN the viewDBCActualDate_YN to set
     */
    public void setViewDBCActualDate_YN(Character viewDBCActualDate_YN) {
        this.viewDBCActualDate_YN = viewDBCActualDate_YN;
    }

    /**
     * @return the viewDBCActualDate_YN
     */
    public Boolean getViewDBCActualDateBool() {
        return new Character('Y').equals(viewDBCActualDate_YN);
    }

    /**
     * @param viewDBCActualDate_YN the viewDBCActualDate_YN to set
     */
    public void setViewDBCActualDateBool(Boolean viewDBCActualDateBool) {
        this.viewDBCActualDate_YN = viewDBCActualDateBool ? 'Y':'N';
    }


}
