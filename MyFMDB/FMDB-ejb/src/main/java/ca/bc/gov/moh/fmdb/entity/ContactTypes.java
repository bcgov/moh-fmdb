/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ContactTypes.java                              *
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
@Table(name = "FMDB_CONTACT_TYPES")
//@NamedQueries({@NamedQuery(name = "FmdbContactTypes.findByContactTypeCd", query = "SELECT f FROM FmdbContactTypes f WHERE f.contactTypeCd = :contactTypeCd"), @NamedQuery(name = "FmdbContactTypes.findByContactTypeDsc", query = "SELECT f FROM FmdbContactTypes f WHERE f.contactTypeDsc = :contactTypeDsc"), @NamedQuery(name = "FmdbContactTypes.findByActiveYn", query = "SELECT f FROM FmdbContactTypes f WHERE f.activeYn = :activeYn"), @NamedQuery(name = "FmdbContactTypes.findBySortOrderNbr", query = "SELECT f FROM FmdbContactTypes f WHERE f.sortOrderNbr = :sortOrderNbr"), @NamedQuery(name = "FmdbContactTypes.findByCreatedByNm", query = "SELECT f FROM FmdbContactTypes f WHERE f.createdByNm = :createdByNm"), @NamedQuery(name = "FmdbContactTypes.findByCreatedOnDtm", query = "SELECT f FROM FmdbContactTypes f WHERE f.createdOnDtm = :createdOnDtm"), @NamedQuery(name = "FmdbContactTypes.findByLastModifiedByNm", query = "SELECT f FROM FmdbContactTypes f WHERE f.lastModifiedByNm = :lastModifiedByNm"), @NamedQuery(name = "FmdbContactTypes.findByLastModifiedDtm", query = "SELECT f FROM FmdbContactTypes f WHERE f.lastModifiedDtm = :lastModifiedDtm"), @NamedQuery(name = "FmdbContactTypes.findByStatelessTransactionNbr", query = "SELECT f FROM FmdbContactTypes f WHERE f.statelessTransactionNbr = :statelessTransactionNbr")})
public class ContactTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "CONTACT_TYPE_CD", nullable = false)
    private String contactTypeCd;
    @Column(name = "CONTACT_TYPE_DSC", nullable = false)
    private String contactTypeDsc;
    @Column(name = "ACTIVE_YN", nullable = false)
    private char activeYn;
    @Column(name = "SORT_ORDER_NBR")
    private String sortOrderNbr;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contactTypeCd")
    private Collection<ContactNames> fmdbContactNamesCollection;

    public ContactTypes() {
    }

    public ContactTypes(String contactTypeCd) {
        this.contactTypeCd = contactTypeCd;
    }

    public ContactTypes(String contactTypeCd, String contactTypeDsc, char activeYn, String createdByNm, Date createdOnDtm, Long statelessTransactionNbr) {
        this.contactTypeCd = contactTypeCd;
        this.contactTypeDsc = contactTypeDsc;
        this.activeYn = activeYn;
        this.createdByNm = createdByNm;
        this.createdOnDtm = createdOnDtm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public String getContactTypeCd() {
        return contactTypeCd;
    }

    public void setContactTypeCd(String contactTypeCd) {
        this.contactTypeCd = contactTypeCd;
    }

    public String getContactTypeDsc() {
        return contactTypeDsc;
    }

    public void setContactTypeDsc(String contactTypeDsc) {
        this.contactTypeDsc = contactTypeDsc;
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

    public Collection<ContactNames> getFmdbContactNamesCollection() {
        return fmdbContactNamesCollection;
    }

    public void setFmdbContactNamesCollection(Collection<ContactNames> fmdbContactNamesCollection) {
        this.fmdbContactNamesCollection = fmdbContactNamesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contactTypeCd != null ? contactTypeCd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactTypes)) {
            return false;
        }
        ContactTypes other = (ContactTypes) object;
        if ((this.contactTypeCd == null && other.contactTypeCd != null) || (this.contactTypeCd != null && !this.contactTypeCd.equals(other.contactTypeCd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbContactTypes[contactTypeCd=" + contactTypeCd + "]";
    }

}
