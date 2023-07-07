/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VDins.java                                     *
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
@NamedQuery(name = "VDins.findAll", query = "select o from VDins o")
@Table(name = "FMDB_DINS")
public class VDins implements Serializable {
    @Column(name="CHEMICAL_ID", nullable = false)
    private Long chemicalId;
    @Column(name="CREATED_BY_NM", nullable = false)
    private String createdByNm;
    @Column(name="CREATED_OIN_DTM", nullable = false)
    private Timestamp createdOinDtm;
    @Id
    @Column(name="DIN_ID", nullable = false)
    private Long dinId;
    @Column(name="DIN_NO")
    private Long dinNo;
    @Column(name="DOSAGE_FORM_TXT")
    private String dosageFormTxt;
    @Column(name="LASTED_MODFIED_DTM")
    private Timestamp lastedModfiedDtm;
    @Column(name="LAST_MODIFIED_BY_NM")
    private String lastModifiedByNm;
    @Column(name="STATELESS_TRANSACTION_NBR", nullable = false)
    private Long statelessTransactionNbr;

    public VDins() {
    }

    public Long getChemicalId() {
        return chemicalId;
    }

    public void setChemicalId(Long chemicalId) {
        this.chemicalId = chemicalId;
    }

    public String getCreatedByNm() {
        return createdByNm;
    }

    public void setCreatedByNm(String createdByNm) {
        this.createdByNm = createdByNm;
    }

    public Timestamp getCreatedOinDtm() {
        return createdOinDtm;
    }

    public void setCreatedOinDtm(Timestamp createdOinDtm) {
        this.createdOinDtm = createdOinDtm;
    }

    public Long getDinId() {
        return dinId;
    }

    public void setDinId(Long dinId) {
        this.dinId = dinId;
    }
    
    public String getDinNoString(){
        if(dinNo == null)
            return null;
        return dinNo.toString();
    }

    public Long getDinNo() {
        return dinNo;
    }

    public void setDinNo(Long dinNo) {
        this.dinNo = dinNo;
    }

    public String getDosageFormTxt() {
        return dosageFormTxt;
    }

    public void setDosageFormTxt(String dosageFormTxt) {
        this.dosageFormTxt = dosageFormTxt;
    }

    public Timestamp getLastedModfiedDtm() {
        return lastedModfiedDtm;
    }

    public void setLastedModfiedDtm(Timestamp lastedModfiedDtm) {
        this.lastedModfiedDtm = lastedModfiedDtm;
    }

    public String getLastModifiedByNm() {
        return lastModifiedByNm;
    }

    public void setLastModifiedByNm(String lastModifiedByNm) {
        this.lastModifiedByNm = lastModifiedByNm;
    }

    public Long getStatelessTransactionNbr() {
        return statelessTransactionNbr;
    }

    public void setStatelessTransactionNbr(Long statelessTransactionNbr) {
        this.statelessTransactionNbr = statelessTransactionNbr;
    }
}
