/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        CompanyTypes.java                              *
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
@Table(name = "FMDB_COMPANY_TYPES")
//@NamedQueries({@NamedQuery(name = "FmdbCompanyTypes.findByCompanyTypeCd", query = "SELECT f FROM FmdbCompanyTypes f WHERE f.companyTypeCd = :companyTypeCd"), @NamedQuery(name = "FmdbCompanyTypes.findByCompanyTypeDsc", query = "SELECT f FROM FmdbCompanyTypes f WHERE f.companyTypeDsc = :companyTypeDsc"), @NamedQuery(name = "FmdbCompanyTypes.findByActiveYn", query = "SELECT f FROM FmdbCompanyTypes f WHERE f.activeYn = :activeYn"), @NamedQuery(name = "FmdbCompanyTypes.findBySortOrderNbr", query = "SELECT f FROM FmdbCompanyTypes f WHERE f.sortOrderNbr = :sortOrderNbr"), @NamedQuery(name = "FmdbCompanyTypes.findByCreatedOnDtm", query = "SELECT f FROM FmdbCompanyTypes f WHERE f.createdOnDtm = :createdOnDtm"), @NamedQuery(name = "FmdbCompanyTypes.findByCreatedByNm", query = "SELECT f FROM FmdbCompanyTypes f WHERE f.createdByNm = :createdByNm"), @NamedQuery(name = "FmdbCompanyTypes.findByLastModifiedDtm", query = "SELECT f FROM FmdbCompanyTypes f WHERE f.lastModifiedDtm = :lastModifiedDtm"), @NamedQuery(name = "FmdbCompanyTypes.findByLastModifiedByNm", query = "SELECT f FROM FmdbCompanyTypes f WHERE f.lastModifiedByNm = :lastModifiedByNm"), @NamedQuery(name = "FmdbCompanyTypes.findByStatelessTransactionNbr", query = "SELECT f FROM FmdbCompanyTypes f WHERE f.statelessTransactionNbr = :statelessTransactionNbr")})
@NamedNativeQueries({
    @NamedNativeQuery(name = "CompanyTypes.findMaxId",
    query = "SELECT MAX(CAST(COMPANY_TYPE_CD AS NUMBER)) FROM FMDB_COMPANY_TYPES")
})
@EntityListeners({AuditListener.class})
public class CompanyTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "COMPANY_TYPE_CD", nullable = false)
    private String companyTypeCd;
    @Column(name = "COMPANY_TYPE_DSC", nullable = false)
    private String companyTypeDsc;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyTypeCd")
    private Collection<Companies> fmdbCompaniesCollection;

    public CompanyTypes() {
    }

    public CompanyTypes(String companyTypeCd) {
        this.companyTypeCd = companyTypeCd;
    }

    public CompanyTypes(String companyTypeCd, String companyTypeDsc, char activeYn, Date createdOnDtm, String createdByNm, Long statelessTransactionNbr) {
        this.companyTypeCd = companyTypeCd;
        this.companyTypeDsc = companyTypeDsc;
        this.activeYn = activeYn;
        this.createdOnDtm = createdOnDtm;
        this.createdByNm = createdByNm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public String getCompanyTypeCd() {
        return companyTypeCd;
    }

    public void setCompanyTypeCd(String companyTypeCd) {
        this.companyTypeCd = companyTypeCd;
    }

    public String getCompanyTypeDsc() {
        return companyTypeDsc;
    }

    public void setCompanyTypeDsc(String companyTypeDsc) {
        this.companyTypeDsc = companyTypeDsc;
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

    public Collection<Companies> getFmdbCompaniesCollection() {
        return fmdbCompaniesCollection;
    }

    public void setFmdbCompaniesCollection(Collection<Companies> fmdbCompaniesCollection) {
        this.fmdbCompaniesCollection = fmdbCompaniesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyTypeCd != null ? companyTypeCd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyTypes)) {
            return false;
        }
        CompanyTypes other = (CompanyTypes) object;
        if ((this.companyTypeCd == null && other.companyTypeCd != null) || (this.companyTypeCd != null && !this.companyTypeCd.equals(other.companyTypeCd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbCompanyTypes[companyTypeCd=" + companyTypeCd + "]";
    }
}
