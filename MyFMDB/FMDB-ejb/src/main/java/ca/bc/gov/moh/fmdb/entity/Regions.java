/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        Regions.java                                   *
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
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "FMDB_REGIONS")
//@NamedQueries({@NamedQuery(name = "FmdbRegions.findByRegionCd", query = "SELECT f FROM FmdbRegions f WHERE f.fmdbRegionsPK.regionCd = :regionCd"), @NamedQuery(name = "FmdbRegions.findByRegionDsc", query = "SELECT f FROM FmdbRegions f WHERE f.regionDsc = :regionDsc"), @NamedQuery(name = "FmdbRegions.findBySortOrderNbr", query = "SELECT f FROM FmdbRegions f WHERE f.sortOrderNbr = :sortOrderNbr"), @NamedQuery(name = "FmdbRegions.findByActiveYn", query = "SELECT f FROM FmdbRegions f WHERE f.activeYn = :activeYn"), @NamedQuery(name = "FmdbRegions.findByCreatedByNm", query = "SELECT f FROM FmdbRegions f WHERE f.createdByNm = :createdByNm"), @NamedQuery(name = "FmdbRegions.findByCreatedOnDtm", query = "SELECT f FROM FmdbRegions f WHERE f.createdOnDtm = :createdOnDtm"), @NamedQuery(name = "FmdbRegions.findByLastModifiedByNm", query = "SELECT f FROM FmdbRegions f WHERE f.lastModifiedByNm = :lastModifiedByNm"), @NamedQuery(name = "FmdbRegions.findByLastModifiedDtm", query = "SELECT f FROM FmdbRegions f WHERE f.lastModifiedDtm = :lastModifiedDtm"), @NamedQuery(name = "FmdbRegions.findByStatelessTransactionNbr", query = "SELECT f FROM FmdbRegions f WHERE f.statelessTransactionNbr = :statelessTransactionNbr"), @NamedQuery(name = "FmdbRegions.findByCountryCd", query = "SELECT f FROM FmdbRegions f WHERE f.fmdbRegionsPK.countryCd = :countryCd")})
public class Regions implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegionsPK fmdbRegionsPK;
    @Column(name = "REGION_DSC", nullable = false)
    private String regionDsc;
    @Column(name = "SORT_ORDER_NBR")
    private String sortOrderNbr;
    @Column(name = "ACTIVE_YN", nullable = false)
    private char activeYn;
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
    @JoinColumn(name = "COUNTRY_CD", referencedColumnName = "COUNTRY_CD", insertable = false, updatable = false)
    @ManyToOne
    private Countries fmdbCountries;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fmdbRegions")
    private Collection<Addresses> fmdbAddressesCollection;

    public Regions() {
    }

    public Regions(RegionsPK fmdbRegionsPK) {
        this.fmdbRegionsPK = fmdbRegionsPK;
    }

    public Regions(RegionsPK fmdbRegionsPK, String regionDsc, char activeYn, String createdByNm, Date createdOnDtm, Long statelessTransactionNbr) {
        this.fmdbRegionsPK = fmdbRegionsPK;
        this.regionDsc = regionDsc;
        this.activeYn = activeYn;
        this.createdByNm = createdByNm;
        this.createdOnDtm = createdOnDtm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public Regions(String regionCd, String countryCd) {
        this.fmdbRegionsPK = new RegionsPK(regionCd, countryCd);
    }

    public RegionsPK getFmdbRegionsPK() {
        return fmdbRegionsPK;
    }

    public void setFmdbRegionsPK(RegionsPK fmdbRegionsPK) {
        this.fmdbRegionsPK = fmdbRegionsPK;
    }

    public String getRegionDsc() {
        return regionDsc;
    }

    public void setRegionDsc(String regionDsc) {
        this.regionDsc = regionDsc;
    }

    public String getSortOrderNbr() {
        return sortOrderNbr;
    }

    public void setSortOrderNbr(String sortOrderNbr) {
        this.sortOrderNbr = sortOrderNbr;
    }

    public char getActiveYn() {
        return activeYn;
    }

    public void setActiveYn(char activeYn) {
        this.activeYn = activeYn;
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

    public Countries getFmdbCountries() {
        return fmdbCountries;
    }

    public void setFmdbCountries(Countries fmdbCountries) {
        this.fmdbCountries = fmdbCountries;
    }

    public Collection<Addresses> getFmdbAddressesCollection() {
        return fmdbAddressesCollection;
    }

    public void setFmdbAddressesCollection(Collection<Addresses> fmdbAddressesCollection) {
        this.fmdbAddressesCollection = fmdbAddressesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fmdbRegionsPK != null ? fmdbRegionsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Regions)) {
            return false;
        }
        Regions other = (Regions) object;
        if ((this.fmdbRegionsPK == null && other.fmdbRegionsPK != null) || (this.fmdbRegionsPK != null && !this.fmdbRegionsPK.equals(other.fmdbRegionsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbRegions[fmdbRegionsPK=" + fmdbRegionsPK + "]";
    }

}
