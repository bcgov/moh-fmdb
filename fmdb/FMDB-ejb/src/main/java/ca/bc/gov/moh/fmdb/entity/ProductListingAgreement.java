/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ProductListingAgreement.java                   *
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
import java.util.LinkedList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "FMDB_PRODUCT_LISTING_AGREEMENT")
@SequenceGenerator(name="PLA_SEQUENCE", sequenceName="FMDB_PLA_SEQ", allocationSize=1)
@EntityListeners({AuditListener.class})
public class ProductListingAgreement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PLA_SEQUENCE")
    @Column(name = "PLA_ID", nullable = false)
    private Long plaId;
    @Column(name = "EFFECTIVE_DT")
    @Temporal(TemporalType.DATE)
    //private String effectiveDt;
    private Date effectiveDt;
    @Column(name = "EXPIRY_DT")
    @Temporal(TemporalType.DATE)
    private Date expiryDt;
    @Column(name = "COMMENTS_TXT")
    private String commentsTxt;
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
    @JoinColumn(name = "PLA_STATUS_CD", referencedColumnName = "PLA_STATUS_CD")
    @ManyToOne
    private PlaStatusTypes plaStatusCd;
    @JoinColumn(name = "SUBMISSION_ID", referencedColumnName = "SUBMISSION_ID")
    @OneToOne
    private Submission submissionId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plaId", fetch=FetchType.EAGER, orphanRemoval=true)
    private Collection<PlaLogs> fmdbPlaLogsCollection;

    public ProductListingAgreement() {
    }

    public ProductListingAgreement(Submission submission) {
        this.submissionId = submission;
        this.fmdbPlaLogsCollection = new LinkedList<PlaLogs>();
    }
    
    public ProductListingAgreement(Long plaId) {
        this.plaId = plaId;
    }

    public ProductListingAgreement(Long plaId, Date createdOnDtm, String createdByNm, Long statelessTransactionNbr) {
        this.plaId = plaId;
        this.createdOnDtm = createdOnDtm;
        this.createdByNm = createdByNm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public Long getPlaId() {
        return plaId;
    }

    public void setPlaId(Long plaId) {
        this.plaId = plaId;
    }

    public Date getEffectiveDt() {
        return effectiveDt;
    }

    public void setEffectiveDt(Date effectiveDt) {
        this.effectiveDt = effectiveDt;
    }

    public Date getExpiryDt() {
        return expiryDt;
    }

    public void setExpiryDt(Date expiryDt) {
        this.expiryDt = expiryDt;
    }

    public String getCommentsTxt() {
        return commentsTxt;
    }

    public void setCommentsTxt(String commentsTxt) {
        this.commentsTxt = commentsTxt;
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

    public PlaStatusTypes getPlaStatusCd() {
        return plaStatusCd;
    }

    public void setPlaStatusCd(PlaStatusTypes plaStatusCd) {
        this.plaStatusCd = plaStatusCd;
    }

    public Submission getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Submission submissionId) {
        this.submissionId = submissionId;
    }

    public Collection<PlaLogs> getFmdbPlaLogsCollection() {
        return fmdbPlaLogsCollection;
    }

    public void setFmdbPlaLogsCollection(Collection<PlaLogs> fmdbPlaLogsCollection) {
        this.fmdbPlaLogsCollection = fmdbPlaLogsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plaId != null ? plaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductListingAgreement)) {
            return false;
        }
        ProductListingAgreement other = (ProductListingAgreement) object;
        if ((this.plaId == null && other.plaId != null) || (this.plaId != null && !this.plaId.equals(other.plaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbProductListingAgreement[plaId=" + plaId + "]";
    }

}
