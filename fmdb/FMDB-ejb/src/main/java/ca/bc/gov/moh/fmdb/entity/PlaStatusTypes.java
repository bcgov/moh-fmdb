/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        PlaStatusTypes.java                            *
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
@Table(name = "FMDB_PLA_STATUS_TYPES")
@NamedNativeQueries({
    @NamedNativeQuery(name = "PlaStatusTypes.findMaxId",
    query = "SELECT MAX(CAST(PLA_STATUS_CD AS NUMBER)) FROM FMDB_PLA_STATUS_TYPES")
})
@EntityListeners({AuditListener.class})
//@NamedQueries({@NamedQuery(name = "FmdbPlaStatusTypes.findByPlaStatusCd", query = "SELECT f FROM FmdbPlaStatusTypes f WHERE f.plaStatusCd = :plaStatusCd"), @NamedQuery(name = "FmdbPlaStatusTypes.findByPlaStatusDsc", query = "SELECT f FROM FmdbPlaStatusTypes f WHERE f.plaStatusDsc = :plaStatusDsc"), @NamedQuery(name = "FmdbPlaStatusTypes.findByActiveYn", query = "SELECT f FROM FmdbPlaStatusTypes f WHERE f.activeYn = :activeYn"), @NamedQuery(name = "FmdbPlaStatusTypes.findBySortOrderNbr", query = "SELECT f FROM FmdbPlaStatusTypes f WHERE f.sortOrderNbr = :sortOrderNbr"), @NamedQuery(name = "FmdbPlaStatusTypes.findByCreatedOnDtm", query = "SELECT f FROM FmdbPlaStatusTypes f WHERE f.createdOnDtm = :createdOnDtm"), @NamedQuery(name = "FmdbPlaStatusTypes.findByCreatedByNm", query = "SELECT f FROM FmdbPlaStatusTypes f WHERE f.createdByNm = :createdByNm"), @NamedQuery(name = "FmdbPlaStatusTypes.findByLastModifiedDtm", query = "SELECT f FROM FmdbPlaStatusTypes f WHERE f.lastModifiedDtm = :lastModifiedDtm"), @NamedQuery(name = "FmdbPlaStatusTypes.findByLastModifiedByNm", query = "SELECT f FROM FmdbPlaStatusTypes f WHERE f.lastModifiedByNm = :lastModifiedByNm"), @NamedQuery(name = "FmdbPlaStatusTypes.findByStatelessTransactionNbr", query = "SELECT f FROM FmdbPlaStatusTypes f WHERE f.statelessTransactionNbr = :statelessTransactionNbr")})
public class PlaStatusTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "PLA_STATUS_CD", nullable = false)
    private String plaStatusCd;
    @Column(name = "PLA_STATUS_DSC", nullable = false)
    private String plaStatusDsc;
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
    @OneToMany(mappedBy = "plaStatusCd")
    private Collection<ProductListingAgreement> fmdbProductListingAgreementCollection;

    public PlaStatusTypes() {
    }

    public PlaStatusTypes(String plaStatusCd) {
        this.plaStatusCd = plaStatusCd;
    }

    public PlaStatusTypes(String plaStatusCd, String plaStatusDsc, char activeYn, Date createdOnDtm, String createdByNm, Long statelessTransactionNbr) {
        this.plaStatusCd = plaStatusCd;
        this.plaStatusDsc = plaStatusDsc;
        this.activeYn = activeYn;
        this.createdOnDtm = createdOnDtm;
        this.createdByNm = createdByNm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public String getPlaStatusCd() {
        return plaStatusCd;
    }

    public void setPlaStatusCd(String plaStatusCd) {
        this.plaStatusCd = plaStatusCd;
    }

    public String getPlaStatusDsc() {
        return plaStatusDsc;
    }

    public void setPlaStatusDsc(String plaStatusDsc) {
        this.plaStatusDsc = plaStatusDsc;
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

    public Collection<ProductListingAgreement> getFmdbProductListingAgreementCollection() {
        return fmdbProductListingAgreementCollection;
    }

    public void setFmdbProductListingAgreementCollection(Collection<ProductListingAgreement> fmdbProductListingAgreementCollection) {
        this.fmdbProductListingAgreementCollection = fmdbProductListingAgreementCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plaStatusCd != null ? plaStatusCd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlaStatusTypes)) {
            return false;
        }
        PlaStatusTypes other = (PlaStatusTypes) object;
        if ((this.plaStatusCd == null && other.plaStatusCd != null) || (this.plaStatusCd != null && !this.plaStatusCd.equals(other.plaStatusCd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbPlaStatusTypes[plaStatusCd=" + plaStatusCd + "]";
    }
}
