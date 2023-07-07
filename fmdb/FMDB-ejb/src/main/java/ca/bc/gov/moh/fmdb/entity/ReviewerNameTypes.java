/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ReviewerNameTypes.java                         *
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
@Table(name = "FMDB_REVIEWER_NAME_TYPES")
//@NamedQueries({@NamedQuery(name = "FmdbReviewerNameTypes.findByReviewerNameCd", query = "SELECT f FROM FmdbReviewerNameTypes f WHERE f.reviewerNameCd = :reviewerNameCd"), @NamedQuery(name = "FmdbReviewerNameTypes.findByReviewerNameDsc", query = "SELECT f FROM FmdbReviewerNameTypes f WHERE f.reviewerNameDsc = :reviewerNameDsc"), @NamedQuery(name = "FmdbReviewerNameTypes.findByActiveYn", query = "SELECT f FROM FmdbReviewerNameTypes f WHERE f.activeYn = :activeYn"), @NamedQuery(name = "FmdbReviewerNameTypes.findBySortOrderNbr", query = "SELECT f FROM FmdbReviewerNameTypes f WHERE f.sortOrderNbr = :sortOrderNbr"), @NamedQuery(name = "FmdbReviewerNameTypes.findByCreatedByNm", query = "SELECT f FROM FmdbReviewerNameTypes f WHERE f.createdByNm = :createdByNm"), @NamedQuery(name = "FmdbReviewerNameTypes.findByCreatedOnDtm", query = "SELECT f FROM FmdbReviewerNameTypes f WHERE f.createdOnDtm = :createdOnDtm"), @NamedQuery(name = "FmdbReviewerNameTypes.findByLastModifiedByNm", query = "SELECT f FROM FmdbReviewerNameTypes f WHERE f.lastModifiedByNm = :lastModifiedByNm"), @NamedQuery(name = "FmdbReviewerNameTypes.findByLastModifiedDtm", query = "SELECT f FROM FmdbReviewerNameTypes f WHERE f.lastModifiedDtm = :lastModifiedDtm"), @NamedQuery(name = "FmdbReviewerNameTypes.findByStatelessTransactionNbr", query = "SELECT f FROM FmdbReviewerNameTypes f WHERE f.statelessTransactionNbr = :statelessTransactionNbr")})
@NamedNativeQueries({
    @NamedNativeQuery(name = "ReviewerNameTypes.findMaxId",
    query = "SELECT MAX(CAST(REVIEWER_NAME_CD AS NUMBER)) FROM FMDB_REVIEWER_NAME_TYPES")
})
@EntityListeners({AuditListener.class})
public class ReviewerNameTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "REVIEWER_NAME_CD", nullable = false)
    private String reviewerNameCd;
    @Column(name = "REVIEWER_NAME_DSC", nullable = false)
    private String reviewerNameDsc;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewerNameCd")
    private Collection<ReviewQuestions> fmdbReviewQuestionsCollection;

    public ReviewerNameTypes() {
    }

    public ReviewerNameTypes(String reviewerNameCd) {
        this.reviewerNameCd = reviewerNameCd;
    }

    public ReviewerNameTypes(String reviewerNameCd, String reviewerNameDsc, char activeYn, String createdByNm, Date createdOnDtm, Long statelessTransactionNbr) {
        this.reviewerNameCd = reviewerNameCd;
        this.reviewerNameDsc = reviewerNameDsc;
        this.activeYn = activeYn;
        this.createdByNm = createdByNm;
        this.createdOnDtm = createdOnDtm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public String getReviewerNameCd() {
        return reviewerNameCd;
    }

    public void setReviewerNameCd(String reviewerNameCd) {
        this.reviewerNameCd = reviewerNameCd;
    }

    public String getReviewerNameDsc() {
        return reviewerNameDsc;
    }

    public void setReviewerNameDsc(String reviewerNameDsc) {
        this.reviewerNameDsc = reviewerNameDsc;
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

    public Collection<ReviewQuestions> getFmdbReviewQuestionsCollection() {
        return fmdbReviewQuestionsCollection;
    }

    public void setFmdbReviewQuestionsCollection(Collection<ReviewQuestions> fmdbReviewQuestionsCollection) {
        this.fmdbReviewQuestionsCollection = fmdbReviewQuestionsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewerNameCd != null ? reviewerNameCd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReviewerNameTypes)) {
            return false;
        }
        ReviewerNameTypes other = (ReviewerNameTypes) object;
        if ((this.reviewerNameCd == null && other.reviewerNameCd != null) || (this.reviewerNameCd != null && !this.reviewerNameCd.equals(other.reviewerNameCd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbReviewerNameTypes[reviewerNameCd=" + reviewerNameCd + "]";
    }
}
