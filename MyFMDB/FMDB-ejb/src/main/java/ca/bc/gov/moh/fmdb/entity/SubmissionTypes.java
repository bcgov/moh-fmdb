/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SubmissionTypes.java                           *
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
@Table(name = "FMDB_SUBMISSION_TYPES")
//@NamedQueries({@NamedQuery(name = "FmdbSubmissionTypes.findBySubmissionTypeCd", query = "SELECT f FROM FmdbSubmissionTypes f WHERE f.submissionTypeCd = :submissionTypeCd"), @NamedQuery(name = "FmdbSubmissionTypes.findBySubmissionTypeDsc", query = "SELECT f FROM FmdbSubmissionTypes f WHERE f.submissionTypeDsc = :submissionTypeDsc"), @NamedQuery(name = "FmdbSubmissionTypes.findByActiveYn", query = "SELECT f FROM FmdbSubmissionTypes f WHERE f.activeYn = :activeYn"), @NamedQuery(name = "FmdbSubmissionTypes.findBySortOrderNbr", query = "SELECT f FROM FmdbSubmissionTypes f WHERE f.sortOrderNbr = :sortOrderNbr"), @NamedQuery(name = "FmdbSubmissionTypes.findByCreatedOnDtm", query = "SELECT f FROM FmdbSubmissionTypes f WHERE f.createdOnDtm = :createdOnDtm"), @NamedQuery(name = "FmdbSubmissionTypes.findByCreatedByNm", query = "SELECT f FROM FmdbSubmissionTypes f WHERE f.createdByNm = :createdByNm"), @NamedQuery(name = "FmdbSubmissionTypes.findByLastModifiedDtm", query = "SELECT f FROM FmdbSubmissionTypes f WHERE f.lastModifiedDtm = :lastModifiedDtm"), @NamedQuery(name = "FmdbSubmissionTypes.findByLastModifiedByNm", query = "SELECT f FROM FmdbSubmissionTypes f WHERE f.lastModifiedByNm = :lastModifiedByNm"), @NamedQuery(name = "FmdbSubmissionTypes.findByStatelessTransactionNbr", query = "SELECT f FROM FmdbSubmissionTypes f WHERE f.statelessTransactionNbr = :statelessTransactionNbr")})
@NamedNativeQueries({
    @NamedNativeQuery(name = "SubmissionTypes.findMaxId",
    query = "SELECT MAX(CAST(SUBMISSION_TYPE_CD AS NUMBER)) FROM FMDB_SUBMISSION_TYPES")
})
@EntityListeners({AuditListener.class})
public class SubmissionTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "SUBMISSION_TYPE_CD", nullable = false)
    private String submissionTypeCd;
    @Column(name = "SUBMISSION_TYPE_DSC", nullable = false)
    private String submissionTypeDsc;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "submissionTypeCd")
    private Collection<Submission> fmdbSubmissionCollection;

    public SubmissionTypes() {
    }

    public SubmissionTypes(String submissionTypeCd) {
        this.submissionTypeCd = submissionTypeCd;
    }

    public SubmissionTypes(String submissionTypeCd, String submissionTypeDsc, char activeYn, Date createdOnDtm, String createdByNm, Long statelessTransactionNbr) {
        this.submissionTypeCd = submissionTypeCd;
        this.submissionTypeDsc = submissionTypeDsc;
        this.activeYn = activeYn;
        this.createdOnDtm = createdOnDtm;
        this.createdByNm = createdByNm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public String getSubmissionTypeCd() {
        return submissionTypeCd;
    }

    public void setSubmissionTypeCd(String submissionTypeCd) {
        this.submissionTypeCd = submissionTypeCd;
    }

    public String getSubmissionTypeDsc() {
        return submissionTypeDsc;
    }

    public void setSubmissionTypeDsc(String submissionTypeDsc) {
        this.submissionTypeDsc = submissionTypeDsc;
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

    public Collection<Submission> getFmdbSubmissionCollection() {
        return fmdbSubmissionCollection;
    }

    public void setFmdbSubmissionCollection(Collection<Submission> fmdbSubmissionCollection) {
        this.fmdbSubmissionCollection = fmdbSubmissionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (submissionTypeCd != null ? submissionTypeCd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubmissionTypes)) {
            return false;
        }
        SubmissionTypes other = (SubmissionTypes) object;
        if ((this.submissionTypeCd == null && other.submissionTypeCd != null) || (this.submissionTypeCd != null && !this.submissionTypeCd.equals(other.submissionTypeCd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbSubmissionTypes[submissionTypeCd=" + submissionTypeCd + "]";
    }
}
