/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        Addresses.java                                 *
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
import javax.persistence.JoinColumns;
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
@Table(name = "FMDB_ADDRESSES")
@SequenceGenerator(name="ADDRESS_SEQUENCE", sequenceName="FMDB_ADR_SEQ", allocationSize=1)
@EntityListeners({AuditListener.class})
public class Addresses implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADDRESS_SEQUENCE")    
    @Column(name = "ADDRESS_ID", nullable = false)
    private Long addressId;
    @Column(name = "ADDRESS_FIRST_LINE_TXT")
    private String addressFirstLineTxt;
    @Column(name = "ADDRESS_SECOND_LINE_TXT")
    private String addressSecondLineTxt;
    @Column(name = "POSTAL_CODE_TXT")
    private String postalCodeTxt;
    @Column(name = "CITY_NM")
    private String cityNm;
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
    @JoinColumn(name = "ADDRESS_TYPE_CD", referencedColumnName = "ADDRESS_TYPE_CD")
    @ManyToOne
    private AddressTypes addressTypeCd;
    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "COMPANY_ID")
    @ManyToOne
    private Companies companyId;
    
    @JoinColumn(name = "CONTACT_NAME_ID", referencedColumnName = "CONTACT_NAME_ID")
    @OneToOne(cascade = CascadeType.ALL)
    private ContactNames contactName;
    
    @JoinColumns({@JoinColumn(name = "REGION_CD", referencedColumnName = "REGION_CD"), @JoinColumn(name = "COUNTRY_CD", referencedColumnName = "COUNTRY_CD")})
    @ManyToOne//(cascade = CascadeType.ALL)
    private Regions fmdbRegions;

    public Addresses() {
    }

    public Addresses(Long addressId) {
        this.addressId = addressId;
    }

    public Addresses(Long addressId, String addressFirstLineTxt, String postalCodeTxt, String cityNm, Date createdOnDtm, String createdByNm, Long statelessTransactionNbr) {
        this.addressId = addressId;
        this.addressFirstLineTxt = addressFirstLineTxt;
        this.postalCodeTxt = postalCodeTxt;
        this.cityNm = cityNm;
        this.createdOnDtm = createdOnDtm;
        this.createdByNm = createdByNm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddressFirstLineTxt() {
        return addressFirstLineTxt;
    }

    public void setAddressFirstLineTxt(String addressFirstLineTxt) {
        this.addressFirstLineTxt = addressFirstLineTxt;
    }

    public String getAddressSecondLineTxt() {
        return addressSecondLineTxt;
    }

    public void setAddressSecondLineTxt(String addressSecondLineTxt) {
        this.addressSecondLineTxt = addressSecondLineTxt;
    }

    public String getPostalCodeTxt() {
        return postalCodeTxt;
    }

    public void setPostalCodeTxt(String postalCodeTxt) {
        this.postalCodeTxt = postalCodeTxt;
    }

    public String getCityNm() {
        return cityNm;
    }

    public void setCityNm(String cityNm) {
        this.cityNm = cityNm;
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

    public AddressTypes getAddressTypeCd() {
        return addressTypeCd;
    }

    public void setAddressTypeCd(AddressTypes addressTypeCd) {
        this.addressTypeCd = addressTypeCd;
    }

    public Companies getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Companies companyId) {
        this.companyId = companyId;
    }

    public ContactNames getContactName() {
        return contactName;
    }

    public void setContactName(ContactNames contactName) {
        this.contactName = contactName;
    }

    public Regions getFmdbRegions() {
        return fmdbRegions;
    }

    public void setFmdbRegions(Regions fmdbRegions) {
        this.fmdbRegions = fmdbRegions;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addressId != null ? addressId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Addresses)) {
            return false;
        }
        Addresses other = (Addresses) object;
        if ((this.addressId == null && other.addressId != null) || (this.addressId != null && !this.addressId.equals(other.addressId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbAddresses[addressId=" + addressId + "]";
    }

}
