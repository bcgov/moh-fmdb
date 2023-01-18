/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VChemicals.java                                *
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
@NamedQuery(name = "VChemicals.findAll", query = "select o from VChemicals o")
@Table(name = "FMDB_CHEMICALS")
public class VChemicals implements Serializable {
    @Id
    @Column(name="CHEMICAL_ID", nullable = false)
    private Long chemicalId;
    @Column(name="CHEMICAL_NM", nullable = false)
    private String chemicalNm;
    @Column(name="CREATED_BY_NM", nullable = false)
    private String createdByNm;
    @Column(name="CREATED_ON_DTM", nullable = false)
    private Timestamp createdOnDtm;
    @Column(name="LAST_MODIFIED_BY_NM")
    private String lastModifiedByNm;
    @Column(name="LAST_MODIFIED_DTM")
    private Timestamp lastModifiedDtm;
    @Column(name="MARKET_RELEASE_DT")
    private Timestamp marketReleaseDt;
    @Column(name="MEDICAL_INDICATIONS_TXT")
    private String medicalIndicationsTxt;
    @Column(name="NOC_DT")
    private Timestamp nocDt;
    @Column(name="PATENT_EXPIRY_DT")
    private Timestamp patentExpiryDt;
    @Column(name="STATELESS_TRANSACTION_NBR", nullable = false)
    private Long statelessTransactionNbr;
    @Column(name="SUBMISSION_ID", nullable = false)
    private Long submissionId;
    @Column(name="TRADE_NM")
    private String tradeNm;

    public VChemicals() {
    }

    public Long getChemicalId() {
        return chemicalId;
    }

    public void setChemicalId(Long chemicalId) {
        this.chemicalId = chemicalId;
    }

    public String getChemicalNm() {
        return chemicalNm;
    }

    public void setChemicalNm(String chemicalNm) {
        this.chemicalNm = chemicalNm;
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

    public Timestamp getMarketReleaseDt() {
        return marketReleaseDt;
    }

    public void setMarketReleaseDt(Timestamp marketReleaseDt) {
        this.marketReleaseDt = marketReleaseDt;
    }

    public String getMedicalIndicationsTxt() {
        return medicalIndicationsTxt;
    }

    public void setMedicalIndicationsTxt(String medicalIndicationsTxt) {
        this.medicalIndicationsTxt = medicalIndicationsTxt;
    }

    public Timestamp getNocDt() {
        return nocDt;
    }

    public void setNocDt(Timestamp nocDt) {
        this.nocDt = nocDt;
    }

    public Timestamp getPatentExpiryDt() {
        return patentExpiryDt;
    }

    public void setPatentExpiryDt(Timestamp patentExpiryDt) {
        this.patentExpiryDt = patentExpiryDt;
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

    public String getTradeNm() {
        return tradeNm;
    }

    public void setTradeNm(String tradeNm) {
        this.tradeNm = tradeNm;
    }
}
