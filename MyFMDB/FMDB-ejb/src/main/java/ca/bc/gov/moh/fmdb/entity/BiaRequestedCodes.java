/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        BiaRequestedCodes.java                         *
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
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author Chris.Prince
 */
@Entity
@Table(name = "FMDB_BIA_REQUESTED_CODES")
//@NamedQueries({@NamedQuery(name = "FmdbBiaRequestedCodes.findByBiaRequstedCd", query = "SELECT f FROM FmdbBiaRequestedCodes f WHERE f.biaRequstedCd = :biaRequstedCd"), @NamedQuery(name = "FmdbBiaRequestedCodes.findByBiaRequestedDsc", query = "SELECT f FROM FmdbBiaRequestedCodes f WHERE f.biaRequestedDsc = :biaRequestedDsc"), @NamedQuery(name = "FmdbBiaRequestedCodes.findByActiveYn", query = "SELECT f FROM FmdbBiaRequestedCodes f WHERE f.activeYn = :activeYn"), @NamedQuery(name = "FmdbBiaRequestedCodes.findBySortOrderNbr", query = "SELECT f FROM FmdbBiaRequestedCodes f WHERE f.sortOrderNbr = :sortOrderNbr"), @NamedQuery(name = "FmdbBiaRequestedCodes.findByCreatedOnDtm", query = "SELECT f FROM FmdbBiaRequestedCodes f WHERE f.createdOnDtm = :createdOnDtm"), @NamedQuery(name = "FmdbBiaRequestedCodes.findByCreatedByNm", query = "SELECT f FROM FmdbBiaRequestedCodes f WHERE f.createdByNm = :createdByNm"), @NamedQuery(name = "FmdbBiaRequestedCodes.findByLastModifiedDtm", query = "SELECT f FROM FmdbBiaRequestedCodes f WHERE f.lastModifiedDtm = :lastModifiedDtm"), @NamedQuery(name = "FmdbBiaRequestedCodes.findByLastModifiedByNm", query = "SELECT f FROM FmdbBiaRequestedCodes f WHERE f.lastModifiedByNm = :lastModifiedByNm"), @NamedQuery(name = "FmdbBiaRequestedCodes.findByStatelessTransactionNbr", query = "SELECT f FROM FmdbBiaRequestedCodes f WHERE f.statelessTransactionNbr = :statelessTransactionNbr")})
@NamedNativeQueries({
    @NamedNativeQuery(name = "BiaRequestedCode.findMaxId",
    query = "SELECT MAX(CAST(BIA_REQUSTED_CD AS NUMBER)) FROM FMDB_BIA_REQUESTED_CODES")
})
@EntityListeners({AuditListener.class})        
public class BiaRequestedCodes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "BIA_REQUSTED_CD", nullable = false)
    private String biaRequstedCd;
    @Column(name = "BIA_REQUESTED_DSC", nullable = false)
    private String biaRequestedDsc;
    @Column(name = "ACTIVE_YN", nullable = false)
    private char activeYn;
    @Column(name = "SORT_ORDER_NBR")
    private String sortOrderNbr;
    @Column(name = "CREATED_ON_DTM", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdOnDtm;
    @Column(name = "CREATED_BY_NM", nullable = false)
    private String createdByNm;
    @Column(name = "LAST_MODIFIED_DTM")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDtm;
    @Column(name = "LAST_MODIFIED_BY_NM")
    private String lastModifiedByNm;
    @Column(name = "STATELESS_TRANSACTION_NBR", nullable = false)
    @Version
    private Long statelessTransactionNbr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "biaRequstedCd")
    private Collection<SubmissionReviewDetails> fmdbSubmissionReviewDetailsCollection;

    public BiaRequestedCodes() {
    }

    public BiaRequestedCodes(String biaRequstedCd) {
        this.biaRequstedCd = biaRequstedCd;
    }

    public BiaRequestedCodes(String biaRequstedCd, String biaRequestedDsc, char activeYn, Date createdOnDtm, String createdByNm, Long statelessTransactionNbr) {
        this.biaRequstedCd = biaRequstedCd;
        this.biaRequestedDsc = biaRequestedDsc;
        this.activeYn = activeYn;
        this.createdOnDtm = createdOnDtm;
        this.createdByNm = createdByNm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public String getBiaRequstedCd() {
        return biaRequstedCd;
    }

    public void setBiaRequstedCd(String biaRequstedCd) {
        this.biaRequstedCd = biaRequstedCd;
    }

    public String getBiaRequestedDsc() {
        return biaRequestedDsc;
    }

    public void setBiaRequestedDsc(String biaRequestedDsc) {
        this.biaRequestedDsc = biaRequestedDsc;
    }

    public char getActiveYn() {
        return activeYn;
    }

    public void setActiveYn(char activeYn) {
        this.activeYn = activeYn;
    }

    public boolean getActiveYnBool() {
        Character active = this.activeYn;

        boolean retVal = false;
        if (active == null || active.equals('N')) {
            retVal = false;
        } else if (active.equals('Y')) {
            retVal = true;
        }

        return retVal;

    }

    public void setActiveYnBool(boolean active) {

        if (active) {
            activeYn = 'Y';
        } else {
            activeYn = 'N';
        }

    }

    public String getSortOrderNbr() {
        return sortOrderNbr;
    }

    public void setSortOrderNbr(String sortOrderNbr) {
        this.sortOrderNbr = sortOrderNbr;
    }

    public Date getCreatedOnDtm() {
        return createdOnDtm;
    }

    public void setCreatedOnDtm(Date createdOnDtm) {
        this.createdOnDtm = createdOnDtm;
    }

    public String getCreatedByNm() {
        return createdByNm;
    }

    public void setCreatedByNm(String createdByNm) {
        this.createdByNm = createdByNm;
    }

    public Date getLastModifiedDtm() {
        return lastModifiedDtm;
    }

    public void setLastModifiedDtm(Date lastModifiedDtm) {
        this.lastModifiedDtm = lastModifiedDtm;
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

    public Collection<SubmissionReviewDetails> getFmdbSubmissionReviewDetailsCollection() {
        return fmdbSubmissionReviewDetailsCollection;
    }

    public void setFmdbSubmissionReviewDetailsCollection(Collection<SubmissionReviewDetails> fmdbSubmissionReviewDetailsCollection) {
        this.fmdbSubmissionReviewDetailsCollection = fmdbSubmissionReviewDetailsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (biaRequstedCd != null ? biaRequstedCd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BiaRequestedCodes)) {
            return false;
        }
        BiaRequestedCodes other = (BiaRequestedCodes) object;
        if ((this.biaRequstedCd == null && other.biaRequstedCd != null) || (this.biaRequstedCd != null && !this.biaRequstedCd.equals(other.biaRequstedCd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbBiaRequestedCodes[biaRequstedCd=" + biaRequstedCd + "]";
    }
}
