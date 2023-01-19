/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ContactDetails.java                            *
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
@Table(name = "FMDB_CONTACT_DETAILS")
@SequenceGenerator(name="CDETAILS_SEQUENCE", sequenceName="FMDB_CD_SEQ", allocationSize=1)
@EntityListeners({AuditListener.class})
public class ContactDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CDETAILS_SEQUENCE")
    @Column(name = "CONTACT_DTL_ID", nullable = false)
    private Long contactDtlId;
    @Column(name = "CELL_NO")
    private String cellNo;
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Column(name = "FAX_NO")
    private String faxNo;
    @Column(name = "WORK_PHONE_NO")
    private String workPhoneNo;
    @Column(name = "WORK_PH_EXT_NO")
    private String workPhExtNo;
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

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "COMPANY_ID")
    private Companies company;
    
    @JoinColumn(name = "CONTACT_NAME_ID", referencedColumnName = "CONTACT_NAME_ID")
    @OneToOne(cascade = CascadeType.ALL)
    private ContactNames contactName;

    public ContactDetails() {
    }

    public ContactDetails(Long contactDtlId) {
        this.contactDtlId = contactDtlId;
    }

    public ContactDetails(Long contactDtlId, String workPhoneNo, String createdByNm, Date createdOnDtm, Long statelessTransactionNbr) {
        this.contactDtlId = contactDtlId;
        this.workPhoneNo = workPhoneNo;
        this.createdByNm = createdByNm;
        this.createdOnDtm = createdOnDtm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public Long getContactDtlId() {
        return contactDtlId;
    }

    public void setContactDtlId(final Long contactDtlId) {
        this.contactDtlId = contactDtlId;
    }

    public String getCellNo() {
        return cellNo;
    }

    public void setCellNo(final String cellNo) {
        this.cellNo = cellNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(final String faxNo) {
        this.faxNo = faxNo;
    }

    public String getWorkPhoneNo() {
        return workPhoneNo;
    }

    public void setWorkPhoneNo(final String workPhoneNo) {
        this.workPhoneNo = workPhoneNo;
    }

    public String getWorkPhExtNo() {
        return workPhExtNo;
    }

    public void setWorkPhExtNo(final String workPhExtNo) {
        this.workPhExtNo = workPhExtNo;
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

    public Companies getCompany() {
        return company;
    }

    public void setCompany(final Companies company) {
        this.company = company;
    }

    public ContactNames getContactName() {
        return contactName;
    }

    public void setContactName(final ContactNames contactName) {
        this.contactName = contactName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contactDtlId != null ? contactDtlId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object object) {
        boolean retValue = true;
        
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactDetails)) {
            retValue = false;
        }
//        final ContactDetails other = (ContactDetails) object;
//        if ((this.contactDtlId == null && other.contactDtlId != null) || (this.contactDtlId != null && !this.contactDtlId.equals(other.contactDtlId))) {
//            retValue = false;
//        }
        return retValue;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbContactDetails[contactDtlId=" + contactDtlId + "]";
    }

}
