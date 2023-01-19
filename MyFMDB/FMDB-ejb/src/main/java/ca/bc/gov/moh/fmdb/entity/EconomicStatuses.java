/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        EconomicStatuses.java                          *
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
import java.math.BigInteger;
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
@Table(name = "FMDB_ECONOMIC_STATUSES")
@SequenceGenerator(name="ECONOMIC_SEQUENCE", sequenceName="FMDB_ES_SEQ", allocationSize=1)
@EntityListeners({AuditListener.class})
public class EconomicStatuses implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ECONOMIC_SEQUENCE")
    @Column(name = "ECONOMIC_ID", nullable = false)
    private Long economicId;
    @Column(name = "RELATIVE_YEAR_NO")
    private BigInteger relativeYearNo;
    @Column(name = "PSD_PATIENT_ACTUALS_QTY")
    private Long psdPatientActualsQty;
    @Column(name = "PSD_EXPENDITURE_AMT")
    private String psdExpenditureAmt;
    @Column(name = "PSD_PREDICTION_AMT")
    private String psdPredictionAmt;
    @Column(name = "PSD_ACTUAL_AMT")
    private String psdActualAmt;
    @Column(name = "BC_ACTUAL_AMT")
    private String bcActualAmt;
    @Column(name = "ANALYSIS_NOTES")
    private String analysisNotes;
    @Column(name = "BC_NUM_PATIENT_ACTUALS_QTY")
    private Long bcNumPatientActualsQty;
    @Column(name = "COMPANY_PREDICTION_AMT")
    private String companyPredictionAmt;
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
    @JoinColumn(name = "SUBMISSION_ID", referencedColumnName = "SUBMISSION_ID")
    @ManyToOne
    private Submission submissionId;

    public EconomicStatuses() {
    }

    public EconomicStatuses(Long economicId) {
        this.economicId = economicId;
    }

    public EconomicStatuses(BigInteger relativeYearNo, Submission submission) {
        this.relativeYearNo = relativeYearNo;
        this.submissionId = submission;
    }
    
    public EconomicStatuses(Long economicId, String createdByNm, Date createdOnDtm, Long statelessTransactionNbr) {
        this.economicId = economicId;
        this.createdByNm = createdByNm;
        this.createdOnDtm = createdOnDtm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public Long getEconomicId() {
        return economicId;
    }

    public void setEconomicId(Long economicId) {
        this.economicId = economicId;
    }

    public BigInteger getRelativeYearNo() {
        return relativeYearNo;
    }

    public void setRelativeYearNo(BigInteger relativeYearNo) {
        this.relativeYearNo = relativeYearNo;
    }

    public Long getPsdPatientActualsQty() {
        return psdPatientActualsQty;
    }

    public void setPsdPatientActualsQty(Long psdPatientActualsQty) {
        this.psdPatientActualsQty = psdPatientActualsQty;
    }

    public String getPsdExpenditureAmt() {
        return psdExpenditureAmt;
    }

    public void setPsdExpenditureAmt(String psdExpenditureAmt) {
        this.psdExpenditureAmt = psdExpenditureAmt;
    }

    public String getPsdPredictionAmt() {
        return psdPredictionAmt;
    }

    public void setPsdPredictionAmt(String psdPredictionAmt) {
        this.psdPredictionAmt = psdPredictionAmt;
    }

    public String getPsdActualAmt() {
        return psdActualAmt;
    }

    public void setPsdActualAmt(String psdActualAmt) {
        this.psdActualAmt = psdActualAmt;
    }

    public String getBcActualAmt() {
        return bcActualAmt;
    }

    public void setBcActualAmt(String bcActualAmt) {
        this.bcActualAmt = bcActualAmt;
    }

    public String getAnalysisNotes() {
        return analysisNotes;
    }

    public void setAnalysisNotes(String analysisNotes) {
        this.analysisNotes = analysisNotes;
    }

    public Long getBcNumPatientActualsQty() {
        return bcNumPatientActualsQty;
    }

    public void setBcNumPatientActualsQty(Long bcNumPatientActualsQty) {
        this.bcNumPatientActualsQty = bcNumPatientActualsQty;
    }

    public String getCompanyPredictionAmt() {
        return companyPredictionAmt;
    }

    public void setCompanyPredictionAmt(String companyPredictionAmt) {
        this.companyPredictionAmt = companyPredictionAmt;
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

    public Submission getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Submission submissionId) {
        this.submissionId = submissionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (economicId != null ? economicId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EconomicStatuses)) {
            return false;
        }
        EconomicStatuses other = (EconomicStatuses) object;
        if ((this.economicId == null && other.economicId != null) || (this.economicId != null && !this.economicId.equals(other.economicId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbEconomicStatuses[economicId=" + economicId + "]";
    }

}
