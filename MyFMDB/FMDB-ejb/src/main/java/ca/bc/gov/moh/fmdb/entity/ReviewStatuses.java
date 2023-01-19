/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ReviewStatuses.java                            *
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

/**
 *
 * @author Chris.Prince
 */
@Entity
@Table(name = "FMDB_REVIEW_STATUSES")
//@NamedQueries({@NamedQuery(name = "FmdbReviewStatuses.findByReviewStatusCd", query = "SELECT f FROM FmdbReviewStatuses f WHERE f.reviewStatusCd = :reviewStatusCd"), @NamedQuery(name = "FmdbReviewStatuses.findByReviewStatusDsc", query = "SELECT f FROM FmdbReviewStatuses f WHERE f.reviewStatusDsc = :reviewStatusDsc"), @NamedQuery(name = "FmdbReviewStatuses.findByActiveYn", query = "SELECT f FROM FmdbReviewStatuses f WHERE f.activeYn = :activeYn"), @NamedQuery(name = "FmdbReviewStatuses.findBySortOrderNbr", query = "SELECT f FROM FmdbReviewStatuses f WHERE f.sortOrderNbr = :sortOrderNbr"), @NamedQuery(name = "FmdbReviewStatuses.findByCreatedByNm", query = "SELECT f FROM FmdbReviewStatuses f WHERE f.createdByNm = :createdByNm"), @NamedQuery(name = "FmdbReviewStatuses.findByCreatedOnDtm", query = "SELECT f FROM FmdbReviewStatuses f WHERE f.createdOnDtm = :createdOnDtm"), @NamedQuery(name = "FmdbReviewStatuses.findByLastModifiedByNm", query = "SELECT f FROM FmdbReviewStatuses f WHERE f.lastModifiedByNm = :lastModifiedByNm"), @NamedQuery(name = "FmdbReviewStatuses.findByLastModifiedDtm", query = "SELECT f FROM FmdbReviewStatuses f WHERE f.lastModifiedDtm = :lastModifiedDtm"), @NamedQuery(name = "FmdbReviewStatuses.findByStatelessTransactionNbr", query = "SELECT f FROM FmdbReviewStatuses f WHERE f.statelessTransactionNbr = :statelessTransactionNbr")})
@NamedNativeQueries(
{
    @NamedNativeQuery(name = "ReviewStatuses.findMaxId", 
               query = "SELECT MAX(CAST(REVIEW_STATUS_CD AS NUMBER)) FROM FMDB_REVIEW_STATUSES")
               
})
@EntityListeners({AuditListener.class})
public class ReviewStatuses implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "REVIEW_STATUS_CD", nullable = false)
    private String reviewStatusCd;
    @Column(name = "REVIEW_STATUS_DSC", nullable = false)
    private String reviewStatusDsc;
    @Column(name = "ACTIVE_YN", nullable = false)
    private String activeYn;
    @Column(name = "SORT_ORDER_NBR")
    private Long sortOrderNbr;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewStatusCd")
    private Collection<SubmissionReviewDetails> fmdbSubmissionReviewDetailsCollection;

    public ReviewStatuses() {
    }

    public ReviewStatuses(String reviewStatusCd) {
        this.reviewStatusCd = reviewStatusCd;
    }

    public ReviewStatuses(String reviewStatusCd, String reviewStatusDsc, String activeYn, String createdByNm, Date createdOnDtm, Long statelessTransactionNbr) {
        this.reviewStatusCd = reviewStatusCd;
        this.reviewStatusDsc = reviewStatusDsc;
        this.activeYn = activeYn;
        this.createdByNm = createdByNm;
        this.createdOnDtm = createdOnDtm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public String getReviewStatusCd() {
        return reviewStatusCd;
    }

    public void setReviewStatusCd(String reviewStatusCd) {
        this.reviewStatusCd = reviewStatusCd;
    }

    public String getReviewStatusDsc() {
        return reviewStatusDsc;
    }

    public void setReviewStatusDsc(String reviewStatusDsc) {
        this.reviewStatusDsc = reviewStatusDsc;
    }

    public String getActiveYn() {
        return activeYn;
    }

    public void setActiveYn(String activeYn) {
        this.activeYn = activeYn;
    }
    
    public boolean getActiveYnBool() {
        String active = this.activeYn;

        boolean retVal = false;
        if (active == null || active.equals("N")) {
            retVal = false;
        } else if (active.equals("Y")) {
            retVal = true;
        }

        return retVal;

    }

    public void setActiveYnBool(boolean active) {

        if (active) {
            activeYn = "Y";
        } else {
            activeYn = "N";
        }

    }     

    public Long getSortOrderNbr() {
        return sortOrderNbr;
    }

    public void setSortOrderNbr(Long sortOrderNbr) {
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

    public Collection<SubmissionReviewDetails> getFmdbSubmissionReviewDetailsCollection() {
        return fmdbSubmissionReviewDetailsCollection;
    }

    public void setFmdbSubmissionReviewDetailsCollection(Collection<SubmissionReviewDetails> fmdbSubmissionReviewDetailsCollection) {
        this.fmdbSubmissionReviewDetailsCollection = fmdbSubmissionReviewDetailsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewStatusCd != null ? reviewStatusCd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReviewStatuses)) {
            return false;
        }
        ReviewStatuses other = (ReviewStatuses) object;
        if ((this.reviewStatusCd == null && other.reviewStatusCd != null) || (this.reviewStatusCd != null && !this.reviewStatusCd.equals(other.reviewStatusCd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbReviewStatuses[reviewStatusCd=" + reviewStatusCd + "]";
    }

}
