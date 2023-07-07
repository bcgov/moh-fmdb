/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        Chemicals.java                                 *
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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "FMDB_CHEMICALS")
@SequenceGenerator(name="CHEMICAL_SEQUENCE", sequenceName="FMDB_CHE_SEQ", allocationSize=1)
@EntityListeners({AuditListener.class})
public class Chemicals implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CHEMICAL_SEQUENCE")
    @Column(name = "CHEMICAL_ID", nullable = false)
    private Long chemicalId;
    @Column(name = "CHEMICAL_NM", nullable = false)
    private String chemicalNm;
    @Column(name = "MEDICAL_INDICATIONS_TXT")
    private String medicalIndicationsTxt;
    @Column(name = "MARKET_RELEASE_DT")
    @Temporal(TemporalType.DATE)
    private Date marketReleaseDt;
    @Column(name = "NOC_DT")
    @Temporal(TemporalType.DATE)
    private Date nocDt;
    @Column(name = "PATENT_EXPIRY_DT")
    @Temporal(TemporalType.DATE)
    private Date patentExpiryDt;
    @Column(name = "TRADE_NM")
    private String tradeNm;
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
    @OneToOne
    @JoinColumn(name = "SUBMISSION_ID", referencedColumnName = "SUBMISSION_ID")
    private Submission submission;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chemicalId", fetch=FetchType.EAGER, orphanRemoval=true)
    private Collection<Dins> fmdbDinsCollection;

    public Chemicals() {
    }

    public Chemicals(Long chemicalId) {
        this.chemicalId = chemicalId;
    }

    public Chemicals(Long chemicalId, String chemicalNm, String createdByNm, Date createdOnDtm, Long statelessTransactionNbr) {
        this.chemicalId = chemicalId;
        this.chemicalNm = chemicalNm;
        this.createdByNm = createdByNm;
        this.createdOnDtm = createdOnDtm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public Long getChemicalId() {
        return chemicalId;
    }

    public void setChemicalId(final Long chemicalId) {
        this.chemicalId = chemicalId;
    }

    public String getChemicalNm() {
        return chemicalNm;
    }

    public void setChemicalNm(final String chemicalNm) {
        this.chemicalNm = chemicalNm;
    }

    public String getMedicalIndicationsTxt() {
        return medicalIndicationsTxt;
    }

    public void setMedicalIndicationsTxt(final String medicalIndicationsTxt) {
        this.medicalIndicationsTxt = medicalIndicationsTxt;
    }

    public Date getMarketReleaseDt() {
        return marketReleaseDt;
    }

    public void setMarketReleaseDt(final Date marketReleaseDt) {
        this.marketReleaseDt = marketReleaseDt;
    }

    public Date getNocDt() {
        return nocDt;
    }

    public void setNocDt(final Date nocDt) {
        this.nocDt = nocDt;
    }

    public Date getPatentExpiryDt() {
        return patentExpiryDt;
    }

    public void setPatentExpiryDt(final Date patentExpiryDt) {
        this.patentExpiryDt = patentExpiryDt;
    }

    public String getTradeNm() {
        return tradeNm;
    }

    public void setTradeNm(final String tradeNm) {
        this.tradeNm = tradeNm;
    }

    public String getCreatedByNm() {
        return createdByNm;
    }

    public void setCreatedByNm(final String createdByNm) {
        this.createdByNm = createdByNm;
    }

    public Date getCreatedOnDtm() {
        return createdOnDtm;
    }

    public void setCreatedOnDtm(final Date createdOnDtm) {
        this.createdOnDtm = createdOnDtm;
    }

    public String getLastModifiedByNm() {
        return lastModifiedByNm;
    }

    public void setLastModifiedByNm(final String lastModifiedByNm) {
        this.lastModifiedByNm = lastModifiedByNm;
    }

    public Date getLastModifiedDtm() {
        return lastModifiedDtm;
    }

    public void setLastModifiedDtm(final Date lastModifiedDtm) {
        this.lastModifiedDtm = lastModifiedDtm;
    }

    public Long getStatelessTransactionNbr() {
        return statelessTransactionNbr;
    }

    public void setStatelessTransactionNbr(final Long statelessTransactionNbr) {
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(final Submission submission) {
        this.submission = submission;
    }

    public Collection<Dins> getFmdbDinsCollection() {
        return fmdbDinsCollection;
    }

    public void setFmdbDinsCollection(final Collection<Dins> fmdbDinsCollection) {
        this.fmdbDinsCollection = fmdbDinsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chemicalId != null ? chemicalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object object) {
        boolean retValue = true;
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chemicals)) {
            //retValue = false;
            return false;
        }
        
        final Chemicals other = (Chemicals) object;
        if ((this.chemicalId == null && other.chemicalId != null) || (this.chemicalId != null && !this.chemicalId.equals(other.chemicalId))) {
            retValue = false;
        }
        return retValue;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbChemicals[chemicalId=" + chemicalId + "]";
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
    public Chemicals clone() throws CloneNotSupportedException {
        Chemicals chem = new Chemicals();
        
        chem.setChemicalId(this.getChemicalId());
        chem.setChemicalNm(this.getChemicalNm());
        chem.setCreatedByNm(this.getCreatedByNm());
        chem.setCreatedOnDtm(this.getCreatedOnDtm() == null ? null : (Date)this.getCreatedOnDtm().clone());
        List<Dins> dins = new LinkedList();
        for(Iterator<Dins> itr = chem.getFmdbDinsCollection().iterator(); itr.hasNext(); ) {
            dins.add(itr.next().clone());
        }
        chem.setFmdbDinsCollection(dins);
        chem.setLastModifiedByNm(this.getLastModifiedByNm());
        chem.setLastModifiedDtm(this.getLastModifiedDtm() == null ? null : (Date)this.getLastModifiedDtm().clone());
        chem.setMarketReleaseDt(this.getMarketReleaseDt() == null ? null : (Date)this.getMarketReleaseDt().clone());
        chem.setMedicalIndicationsTxt(this.getMedicalIndicationsTxt());
        chem.setNocDt(this.getNocDt() == null ? null : (Date)this.getNocDt().clone());
        chem.setPatentExpiryDt(this.getPatentExpiryDt() == null ? null : (Date)this.getPatentExpiryDt().clone());
        chem.setStatelessTransactionNbr(this.getStatelessTransactionNbr());
        //chem.setSubmission(this.getSubmission()); Set submission from within submission.clone()
        chem.setTradeNm(this.getTradeNm());
        
        return chem;
    }
}
