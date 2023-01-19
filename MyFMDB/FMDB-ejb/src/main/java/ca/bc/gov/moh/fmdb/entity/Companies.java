/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        Companies.java                                 *
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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "FMDB_COMPANIES")
@SequenceGenerator(name="COMPANY_SEQUENCE", sequenceName="FMDB_COM_SEQ", allocationSize=1)
@EntityListeners({AuditListener.class})
public class Companies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COMPANY_SEQUENCE")
    @Column(name = "COMPANY_ID", nullable = false)
    private Long companyId;
    @Column(name = "LEGAL_NM", nullable = false)
    private String legalNm;
    @Column(name = "ALIAS_NM")
    private String aliasNm;
    @Column(name = "EXPIRY_DT")
    @Temporal(TemporalType.DATE)
    private Date expiryDt;
    @Column(name = "EXPIRY_REASON_DSC")
    private String expiryReasonDsc;
    @Column(name = "START_DT")
    @Temporal(TemporalType.DATE)
    private Date startDt;
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
    @Column(name = "COMMENT_TXT")
    private String commentTxt;

    // A company has a Contact Detail
    @OneToMany(mappedBy = "company", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Collection<ContactDetails> contactDetail;
    @OneToMany(mappedBy = "companyId")
    private Collection<Submission> fmdbSubmissionCollection;
    @JoinColumn(name = "COMPANY_TYPE_CD", referencedColumnName = "COMPANY_TYPE_CD")
    @ManyToOne
    private CompanyTypes companyTypeCd;
    @OneToMany(mappedBy = "companyId", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Collection<ContactNames> fmdbContactNamesCollection;
    @OneToMany(mappedBy = "companyId", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Collection<Addresses> fmdbAddressesCollection;

    public Companies() {
    }

    public Companies(Long companyId) {
        this.companyId = companyId;
    }

    public Companies(Long companyId, String legalNm, Date createdOnDtm, String createdByNm, Long statelessTransactionNbr) {
        this.companyId = companyId;
        this.legalNm = legalNm;
        this.createdOnDtm = createdOnDtm;
        this.createdByNm = createdByNm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(final Long companyId) {
        this.companyId = companyId;
    }

    public String getLegalNm() {
        return legalNm;
    }

    public void setLegalNm(final String legalNm) {
        this.legalNm = legalNm;
    }

    public String getAliasNm() {
        return aliasNm;
    }

    public void setAliasNm(final String aliasNm) {
        this.aliasNm = aliasNm;
    }

    public Date getExpiryDt() {
        return expiryDt;
    }

    public void setExpiryDt(final Date expiryDt) {
        this.expiryDt = expiryDt;
    }

    public String getExpiryReasonDsc() {
        return expiryReasonDsc;
    }

    public void setExpiryReasonDsc(final String expiryReasonDsc) {
        this.expiryReasonDsc = expiryReasonDsc;
    }

    public Date getStartDt() {
        return startDt;
    }

    public void setStartDt(final Date startDt) {
        this.startDt = startDt;
    }

    public Date getCreatedOnDtm() {
        return createdOnDtm;
    }

    public void setCreatedOnDtm(final Date createdOnDtm) {
        this.createdOnDtm = createdOnDtm;
    }

    public String getCreatedByNm() {
        return createdByNm;
    }

    public void setCreatedByNm(final String createdByNm) {
        this.createdByNm = createdByNm;
    }

    public Date getLastModifiedDtm() {
        return lastModifiedDtm;
    }

    public void setLastModifiedDtm(final Date lastModifiedDtm) {
        this.lastModifiedDtm = lastModifiedDtm;
    }

    public String getLastModifiedByNm() {
        return lastModifiedByNm;
    }

    public void setLastModifiedByNm(final String lastModifiedByNm) {
        this.lastModifiedByNm = lastModifiedByNm;
    }

    public Long getStatelessTransactionNbr() {
        return statelessTransactionNbr;
    }

    public void setStatelessTransactionNbr(final Long statelessTransactionNbr) {
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public String getCommentTxt() {
        return commentTxt;
    }

    public void setCommentTxt(final String commentTxt) {
        this.commentTxt = commentTxt;
    }

    public Collection<ContactDetails> getContactDetails() {
        return contactDetail;
    }

    public void setContactDetails(final Collection<ContactDetails> contactDetail) {
        this.contactDetail = contactDetail;
    }

    public Collection<Submission> getFmdbSubmissionCollection() {
        return fmdbSubmissionCollection;
    }

    public void setFmdbSubmissionCollection(final Collection<Submission> fmdbSubmissionCollection) {
        this.fmdbSubmissionCollection = fmdbSubmissionCollection;
    }

    public CompanyTypes getCompanyTypeCd() {
        return companyTypeCd;
    }

    public void setCompanyTypeCd(final CompanyTypes companyTypeCd) {
        this.companyTypeCd = companyTypeCd;
    }

    public Collection<ContactNames> getFmdbContactNamesCollection() {
        return fmdbContactNamesCollection;
    }

    public void setFmdbContactNamesCollection(final Collection<ContactNames> fmdbContactNamesCollection) {
        this.fmdbContactNamesCollection = fmdbContactNamesCollection;
    }

    public Collection<Addresses> getFmdbAddressesCollection() {
        return fmdbAddressesCollection;
    }

    public void setFmdbAddressesCollection(final Collection<Addresses> fmdbAddressesCollection) {
        this.fmdbAddressesCollection = fmdbAddressesCollection;
    }
    
    public String getCompanyIdStr() {
        return companyId.toString();
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyId != null ? companyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object object) {
        boolean retValue = true;
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companies)) {
            //retValue = false;
            return false;
        }
        
        final Companies other = (Companies) object;
        if ((this.companyId == null && other.companyId != null) || (this.companyId != null && !this.companyId.equals(other.companyId))) {
            retValue = false;
        }
        return retValue;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbCompanies[companyId=" + companyId + "]";
    }
}
