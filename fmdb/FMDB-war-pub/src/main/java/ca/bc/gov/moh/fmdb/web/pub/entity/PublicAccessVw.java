/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        PublicAccessVw.java                            *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package ca.bc.gov.moh.fmdb.web.pub.entity;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity(name = "PublicAccess")
@Table(name = "FMDB_PUBLIC_ACCESS_VW")
public class PublicAccessVw implements Serializable {

    @Column(name = "CDR_REVIEW_YN")
    private String cdrReviewYn;
    @Column(name = "CEDAC_RECOMMENDATION_DT")
    private Timestamp cedacRecommendationDt;
    @Column(name = "DBC_ACTUAL_DT", nullable = true)
    private Timestamp dbcActualDt;
    @Column(name = "CHEMICAL_NM", nullable = false)
    private String chemicalNm;
    @Column(name = "COMPANY_NOTICE_SENT_DT")
    private Timestamp companyNoticeSentDt;
    @Column(name = "LEGAL_NM", nullable = false)
    private String legalNm;
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "LETTER_BIN")
    private Serializable letterBin;
    @Column(name = "MEDICAL_INDICATIONS_TXT")
    private String medicalIndicationsTxt;
    @Column(name = "PSD_WEB_COMMENTS")
    private String psdWebComments;
    @Column(name = "RECEIVED_DT")
    private Timestamp receivedDt;
    @Column(name = "REVIEW_STATUS_DSC", nullable = false)
    private String reviewStatusDsc;
    @Id
    @Column(name = "SUBMISSION_ID", nullable = false)
    private Long submissionId;
    @Column(name = "SUBMISSION_TYPE_DSC", nullable = false)
    private String submissionTypeDsc;
    @Column(name = "TRADE_NM")
    private String tradeNm;
    @Column(name = "TENTATIVE_DT_COMPLEX")
    private Timestamp tentativeDtComplex;
    @Column(name = "TENTATIVE_DT_STANDARD")
    private Timestamp tentativeDtStandard;
    @Column(name = "DBR_PHARMANET_DT")
    private Timestamp dbrPharmanetDt;
    

    public PublicAccessVw() {
    }

    public String getCdrReviewYn() {
        if (cdrReviewYn != null) {
            if (cdrReviewYn.equals("N")) {
                cdrReviewYn = "No";
            } else if (cdrReviewYn.equals("Y")) {
                cdrReviewYn = "<a href='https://www.cadth.ca/about-cadth/what-we-do/products-services/cdr/reports' target='new_window'>Yes</a>";
            }

        }
        return cdrReviewYn;
    }

    public void setCdrReviewYn(String cdrReviewYn) {
        this.cdrReviewYn = cdrReviewYn;
    }

    public Timestamp getCedacRecommendationDt() {
        return cedacRecommendationDt;
    }

    public void setCedacRecommendationDt(Timestamp cedacRecommendationDt) {
        this.cedacRecommendationDt = cedacRecommendationDt;
    }

    public String getChemicalNm() {
        return chemicalNm;
    }

    public void setChemicalNm(String chemicalNm) {
        this.chemicalNm = chemicalNm;
    }

    public Timestamp getCompanyNoticeSentDt() {
        return companyNoticeSentDt;
    }

    public void setCompanyNoticeSentDt(Timestamp companyNoticeSentDt) {
        this.companyNoticeSentDt = companyNoticeSentDt;
    }

    public String getLegalNm() {
        return legalNm;
    }

    public void setLegalNm(String legalNm) {
        this.legalNm = legalNm;
    }

    public Serializable getLetterBin() {
        return letterBin;
    }

    public void setLetterBin(Serializable letterBin) {
        this.letterBin = letterBin;
    }

    public String getMedicalIndicationsTxt() {
        return medicalIndicationsTxt;
    }

    public void setMedicalIndicationsTxt(String medicalIndicationsTxt) {
        this.medicalIndicationsTxt = medicalIndicationsTxt;
    }

    public String getPsdWebComments() {
        return psdWebComments;
    }

    public void setPsdWebComments(String psdWebComments) {
        this.psdWebComments = psdWebComments;
    }

    public Timestamp getReceivedDt() {
        return receivedDt;
    }

    public void setReceivedDt(Timestamp receivedDt) {
        this.receivedDt = receivedDt;
    }
    
    public Timestamp getTentativeDtComplex() {
        return tentativeDtComplex;
    }

    public void setTentativeDtComplex(Timestamp tentativeDtComplex) {
        this.tentativeDtComplex = tentativeDtComplex;
    }
    
    public Timestamp getTentativeDtStandard() {
        return tentativeDtStandard;
    }

    public void setTentativeDtStandard(Timestamp tentativeDtStandard) {
        this.tentativeDtStandard = tentativeDtStandard;
    }    

    public String getReviewStatusDsc() {

        if (reviewStatusDsc != null 
                && !reviewStatusDsc.isEmpty() 
                && reviewStatusDsc.equals("Limited Coverage-Special Authority required")) {
            reviewStatusDsc = "<a href='http://www.health.gov.bc.ca/pharmacare/sa/criteria/restricted/restrictedtable.html' target='new_window'>Limited Coverage</a>. Special Authority required</a>";
        }

        return reviewStatusDsc;
    }

    public void setReviewStatusDsc(String reviewStatusDsc) {
        this.reviewStatusDsc = reviewStatusDsc;
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }

    public String getSubmissionTypeDsc() {
        return submissionTypeDsc;
    }

    public void setSubmissionTypeDsc(String submissionTypeDsc) {
        this.submissionTypeDsc = submissionTypeDsc;
    }

    public String getTradeNm() {
        return tradeNm;
    }

    public void setTradeNm(String tradeNm) {
        this.tradeNm = tradeNm;
    }

    /**
     * @return the dbcActualDt
     */
    public Timestamp getDbcActualDt() {
        return dbcActualDt;
    }

    /**
     * @param dbcActualDt the dbcActualDt to set
     */
    public void setDbcActualDt(Timestamp dbcActualDt) {
        this.dbcActualDt = dbcActualDt;
    }

    /**
     * @return the dbrPharmentDt
     */
    public Timestamp getDbrPharmanetDt() {
        return dbrPharmanetDt;
    }

    /**
     * @param dbrPharmentDt the dbrPharmentDt to set
     */
    public void setDbrPharmanetDt(Timestamp dbrPharmentDt) {
        this.dbrPharmanetDt = dbrPharmentDt;
    }
}
