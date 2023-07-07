/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        AddressTypes.java                              *
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
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "FMDB_ADDRESS_TYPES")
//@NamedQueries({@NamedQuery(name = "FmdbAddressTypes.findByAddressTypeCd", query = "SELECT f FROM FmdbAddressTypes f WHERE f.addressTypeCd = :addressTypeCd"), @NamedQuery(name = "FmdbAddressTypes.findByAddressTypeDsc", query = "SELECT f FROM FmdbAddressTypes f WHERE f.addressTypeDsc = :addressTypeDsc"), @NamedQuery(name = "FmdbAddressTypes.findByActiveYn", query = "SELECT f FROM FmdbAddressTypes f WHERE f.activeYn = :activeYn"), @NamedQuery(name = "FmdbAddressTypes.findBySortOrderNbr", query = "SELECT f FROM FmdbAddressTypes f WHERE f.sortOrderNbr = :sortOrderNbr"), @NamedQuery(name = "FmdbAddressTypes.findByCreatedOnDtm", query = "SELECT f FROM FmdbAddressTypes f WHERE f.createdOnDtm = :createdOnDtm"), @NamedQuery(name = "FmdbAddressTypes.findByCreatedByNm", query = "SELECT f FROM FmdbAddressTypes f WHERE f.createdByNm = :createdByNm"), @NamedQuery(name = "FmdbAddressTypes.findByLastModifiedDtm", query = "SELECT f FROM FmdbAddressTypes f WHERE f.lastModifiedDtm = :lastModifiedDtm"), @NamedQuery(name = "FmdbAddressTypes.findByLastModifiedByNm", query = "SELECT f FROM FmdbAddressTypes f WHERE f.lastModifiedByNm = :lastModifiedByNm"), @NamedQuery(name = "FmdbAddressTypes.findByStatelessTransactionNbr", query = "SELECT f FROM FmdbAddressTypes f WHERE f.statelessTransactionNbr = :statelessTransactionNbr")})
public class AddressTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ADDRESS_TYPE_CD", nullable = false)
    private String addressTypeCd;
    @Column(name = "ADDRESS_TYPE_DSC", nullable = false)
    private String addressTypeDsc;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addressTypeCd")
    private Collection<Addresses> fmdbAddressesCollection;

    public AddressTypes() {
    }

    public AddressTypes(String addressTypeCd) {
        this.addressTypeCd = addressTypeCd;
    }

    public AddressTypes(String addressTypeCd, String addressTypeDsc, char activeYn, Date createdOnDtm, String createdByNm, Long statelessTransactionNbr) {
        this.addressTypeCd = addressTypeCd;
        this.addressTypeDsc = addressTypeDsc;
        this.activeYn = activeYn;
        this.createdOnDtm = createdOnDtm;
        this.createdByNm = createdByNm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public String getAddressTypeCd() {
        return addressTypeCd;
    }

    public void setAddressTypeCd(String addressTypeCd) {
        this.addressTypeCd = addressTypeCd;
    }

    public String getAddressTypeDsc() {
        return addressTypeDsc;
    }

    public void setAddressTypeDsc(String addressTypeDsc) {
        this.addressTypeDsc = addressTypeDsc;
    }

    public char getActiveYn() {
        return activeYn;
    }

    public void setActiveYn(char activeYn) {
        this.activeYn = activeYn;
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

    public Collection<Addresses> getFmdbAddressesCollection() {
        return fmdbAddressesCollection;
    }

    public void setFmdbAddressesCollection(Collection<Addresses> fmdbAddressesCollection) {
        this.fmdbAddressesCollection = fmdbAddressesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addressTypeCd != null ? addressTypeCd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AddressTypes)) {
            return false;
        }
        AddressTypes other = (AddressTypes) object;
        if ((this.addressTypeCd == null && other.addressTypeCd != null) || (this.addressTypeCd != null && !this.addressTypeCd.equals(other.addressTypeCd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbAddressTypes[addressTypeCd=" + addressTypeCd + "]";
    }

}
