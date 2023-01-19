/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        Dins.java                                      *
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
@Table(name = "FMDB_DINS")
@SequenceGenerator(name="DIN_SEQUENCE", sequenceName="FMDB_DIN_SEQ", allocationSize=1)
@EntityListeners({AuditListener.class})
public class Dins implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DIN_SEQUENCE")
    @Column(name = "DIN_ID", nullable = false)
    private Long dinId;
    @Column(name = "DIN_NO")
    private Integer dinNo;
    @Column(name = "DOSAGE_FORM_TXT")
    private String dosageFormTxt;
    @Column(name = "CREATED_BY_NM", nullable = false)
    private String createdByNm;
    @Column(name = "CREATED_OIN_DTM", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdOnDtm;
    @Column(name = "LAST_MODIFIED_BY_NM")
    private String lastModifiedByNm;
    @Column(name = "LASTED_MODFIED_DTM")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDtm;
    @Column(name = "STATELESS_TRANSACTION_NBR", nullable = false)
    @Version
    private Long statelessTransactionNbr;
    @JoinColumn(name = "CHEMICAL_ID", referencedColumnName = "CHEMICAL_ID")
    @ManyToOne
    private Chemicals chemicalId;

    public Dins() {
    }
    
    public Dins(Chemicals chemical) {
        this.chemicalId = chemical;
    }
    
    public Dins(Long dinId) {
        this.dinId = dinId;
    }

    public Dins(Long dinId, String createdByNm, Date createdOinDtm, Long statelessTransactionNbr) {
        this.dinId = dinId;
        this.createdByNm = createdByNm;
        this.createdOnDtm = createdOinDtm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public Long getDinId() {
        return dinId;
    }

    public void setDinId(Long dinId) {
        this.dinId = dinId;
    }

    public Integer getDinNo() {
        return dinNo;
    }

    public void setDinNo(Integer dinNo) {
        this.dinNo = dinNo;
    }

    public String getDosageFormTxt() {
        return dosageFormTxt;
    }

    public void setDosageFormTxt(String dosageFormTxt) {
        this.dosageFormTxt = dosageFormTxt;
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

    public void setCreatedOnDtm(Date createdOinDtm) {
        this.createdOnDtm = createdOinDtm;
    }

    public String getLastModifiedByNm() {
        return lastModifiedByNm;
    }

    public void setLastModifiedByNm(String lastModifiedByNm) {
        this.lastModifiedByNm = lastModifiedByNm;
    }

    public Date getLastedModfiedOnDtm() {
        return lastModifiedDtm;
    }

    public void setLastedModfiedOnDtm(Date lastedModfiedDtm) {
        this.lastModifiedDtm = lastedModfiedDtm;
    }

    public Long getStatelessTransactionNbr() {
        return statelessTransactionNbr;
    }

    public void setStatelessTransactionNbr(Long statelessTransactionNbr) {
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public Chemicals getChemicalId() {
        return chemicalId;
    }

    public void setChemicalId(Chemicals chemicalId) {
        this.chemicalId = chemicalId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = prime * hash + ((dinId == null ? 0 : dinId.hashCode()));
        hash = prime * hash + ((dinNo == null ? 0 : dinNo.hashCode()));
        hash = prime * hash + ((dosageFormTxt == null ? 0 : dosageFormTxt.hashCode()));
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // This method has been updated to work in the case that id fields are 
        // not set (e.g. when deleting a yet to be persisted record from a list 
        // of new records that have not been saved.
        if (!(object instanceof Dins)) {
            return false;
        }
        Dins other = (Dins) object;
        if ((this.dinId == null && other.dinId != null) || (this.dinId != null && !this.dinId.equals(other.dinId))
                || (this.dinId == null && other.dinId == null 
                    && ((this.dinNo != null && !this.dinNo.equals(other.dinNo)) 
                        || (this.dosageFormTxt != null && !this.dosageFormTxt.equals(other.dosageFormTxt))))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbDins[dinId=" + dinId + "]";
    }

    /**
     *  
     *  Returns a deep copy of the object.
     *  
     *  @return a copy of the object
     *  @throws java.lang.CloneNotSupportedException if the object cannot be cloned
     *  
     */
    @Override
    public Dins clone() throws CloneNotSupportedException {
        Dins din = new Dins();
        
        din.setChemicalId(this.getChemicalId());
        din.setCreatedByNm(this.getCreatedByNm());
        din.setCreatedOnDtm(this.getCreatedOnDtm());
        din.setDinId(this.getDinId());
        din.setDinNo(this.getDinNo());
        din.setDosageFormTxt(this.getDosageFormTxt());
        din.setLastedModfiedOnDtm(this.getLastedModfiedOnDtm());
        din.setLastModifiedByNm(this.getLastModifiedByNm());
        din.setStatelessTransactionNbr(this.getStatelessTransactionNbr());
        
        return din;
    }
}
