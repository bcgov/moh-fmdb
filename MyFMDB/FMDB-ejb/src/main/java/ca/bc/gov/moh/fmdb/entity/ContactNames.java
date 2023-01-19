/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ContactNames.java                              *
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
@Table(name = "FMDB_CONTACT_NAMES")
@SequenceGenerator(name = "CNAMES_SEQUENCE", sequenceName = "FMDB_CN_SEQ", allocationSize = 1)
@EntityListeners({AuditListener.class})
public class ContactNames implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CNAMES_SEQUENCE")
    @Column(name = "CONTACT_NAME_ID", nullable = false)
    private Long contactNameId;
    @Column(name = "FIRST_NM")
    private String firstNm;
    @Column(name = "LAST_NM")
    private String lastNm;
    @Column(name = "JOB_TITLE_NM")
    private String jobTitleNm;
    @Column(name = "EFFECTIVE_DT")
    @Temporal(TemporalType.DATE)
    private Date effectiveDt;
    @Column(name = "EXPIRY_DT")
    @Temporal(TemporalType.DATE)
    private Date expiryDt;
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
    //@JoinColumn(name = "CONTACT_NAME_ID", referencedColumnName = "CONTACT_NAME_ID")
    @OneToOne(mappedBy = "contactName", cascade = CascadeType.ALL)
    private ContactDetails contactDetail;
    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "COMPANY_ID")
    @ManyToOne
    private Companies companyId;
    @JoinColumn(name = "CONTACT_TYPE_CD", referencedColumnName = "CONTACT_TYPE_CD")
    @ManyToOne
    private ContactTypes contactTypeCd;
    @OneToOne(mappedBy = "contactName", cascade = CascadeType.ALL)
    private Addresses address;
    @OneToMany(mappedBy = "contactId")
    private Collection<Submission> contactsCollection;

    public ContactNames() {
    }

    public ContactNames(Long contactNameId) {
        this.contactNameId = contactNameId;
    }

    public ContactNames(Long contactNameId, String lastNm, String jobTitleNm, String createdByNm, Date createdOnDtm, Long statelessTransactionNbr) {
        this.contactNameId = contactNameId;
        this.lastNm = lastNm;
        this.jobTitleNm = jobTitleNm;
        this.createdByNm = createdByNm;
        this.createdOnDtm = createdOnDtm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public Long getContactNameId() {
        return contactNameId;
    }

    public void setContactNameId(final Long contactNameId) {
        this.contactNameId = contactNameId;
    }

    public String getFirstNm() {
        return firstNm;
    }

    public void setFirstNm(final String firstNm) {
        this.firstNm = firstNm;
    }

    public String getLastNm() {
        return lastNm;
    }

    public void setLastNm(final String lastNm) {
        this.lastNm = lastNm;
    }

    public String getJobTitleNm() {
        return jobTitleNm;
    }

    public void setJobTitleNm(final String jobTitleNm) {
        this.jobTitleNm = jobTitleNm;
    }

    public Date getEffectiveDt() {
        return effectiveDt;
    }

    public void setEffectiveDt(final Date effectiveDt) {
        this.effectiveDt = effectiveDt;
    }

    public Date getExpiryDt() {
        return expiryDt;
    }

    public void setExpiryDt(final Date expiryDt) {
        this.expiryDt = expiryDt;
    }

    public String getCreatedByNm() {
        return createdByNm;
    }

    public void setCreatedByNm(final String createdByNm) {
        this.createdByNm = createdByNm;
    }

    public Date getCreatedOnDtm() {
        return createdOnDtm;
    }

    public void setCreatedOnDtm(final Date createdOnDtm) {
        this.createdOnDtm = createdOnDtm;
    }

    public String getLastModifiedByNm() {
        return lastModifiedByNm;
    }

    public void setLastModifiedByNm(final String lastModifiedByNm) {
        this.lastModifiedByNm = lastModifiedByNm;
    }

    public Date getLastModifiedDtm() {
        return lastModifiedDtm;
    }

    public void setLastModifiedDtm(final Date lastModifiedDtm) {
        this.lastModifiedDtm = lastModifiedDtm;
    }

    public Long getStatelessTransactionNbr() {
        return statelessTransactionNbr;
    }

    public void setStatelessTransactionNbr(final Long statelessTransactionNbr) {
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public ContactDetails getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(final ContactDetails contactDetail) {
        this.contactDetail = contactDetail;
    }

    public Companies getCompanyId() {
        return companyId;
    }

    public void setCompanyId(final Companies companyId) {
        this.companyId = companyId;
    }

    public ContactTypes getContactTypeCd() {
        return contactTypeCd;
    }

    public void setContactTypeCd(final ContactTypes contactTypeCd) {
        this.contactTypeCd = contactTypeCd;
    }

    public Addresses getAddress() {
        return address;
    }

    public void setAddress(final Addresses address) {
        this.address = address;
    }

    public Collection<Submission> getContactsCollection() {
        return contactsCollection;
    }

    public void setContactsCollection(Collection<Submission> contactsCollection) {
        this.contactsCollection = contactsCollection;
    }

    public String getDisplayName() {
        return this.lastNm + ", " + this.firstNm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contactNameId != null ? contactNameId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object object) {
        if (object instanceof ContactNames) {
            final ContactNames other = (ContactNames) object;
            if (this.contactNameId == other.contactNameId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbContactNames[contactNameId=" + contactNameId + "]";
    }
}
