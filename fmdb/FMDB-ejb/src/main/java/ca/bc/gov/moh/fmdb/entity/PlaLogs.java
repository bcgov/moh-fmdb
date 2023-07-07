/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        PlaLogs.java                                   *
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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "FMDB_PLA_LOGS")
@SequenceGenerator(name="PLALOGS_SEQUENCE", sequenceName="FMDB_PL_SEQ", allocationSize=1)
@EntityListeners({AuditListener.class})
public class PlaLogs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PLALOGS_SEQUENCE")
    @Column(name = "PLA_LOG_ID", nullable = false)
    private Long plaLogId;
    @Column(name = "ACTIVITY_DETAILS_TXT")
    private String activityDetailsTxt;
    @Column(name = "ACTIVITY_DT")
    @Temporal(TemporalType.DATE)
    private Date activityDt;
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
    @JoinColumn(name = "PLA_ID", referencedColumnName = "PLA_ID")
    @ManyToOne
    private ProductListingAgreement plaId;

    public PlaLogs() {
    }

    public PlaLogs(ProductListingAgreement pla) {
        this.plaId = pla;
    }
    
    public PlaLogs(Long plaLogId) {
        this.plaLogId = plaLogId;
    }

    public PlaLogs(Long plaLogId, Date createdOnDtm, String createdByNm, Long statelessTransactionNbr) {
        this.plaLogId = plaLogId;
        this.createdOnDtm = createdOnDtm;
        this.createdByNm = createdByNm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public Long getPlaLogId() {
        return plaLogId;
    }

    public void setPlaLogId(Long plaLogId) {
        this.plaLogId = plaLogId;
    }

    public String getActivityDetailsTxt() {
        return activityDetailsTxt;
    }

    public void setActivityDetailsTxt(String activityDetailsTxt) {
        this.activityDetailsTxt = activityDetailsTxt;
    }

    public Date getActivityDt() {
        return activityDt;
    }

    public void setActivityDt(Date activityDt) {
        this.activityDt = activityDt;
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

    public ProductListingAgreement getPlaId() {
        return plaId;
    }

    public void setPlaId(ProductListingAgreement plaId) {
        this.plaId = plaId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = prime * hash + ((plaLogId == null ? 0 : plaLogId.hashCode()));
        hash = prime * hash + ((activityDt == null ? 0 : activityDt.hashCode()));
        hash = prime * hash + ((activityDetailsTxt == null ? 0 : activityDetailsTxt.hashCode()));
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // This method has been updated to work in the case that id fields are 
        // not set (e.g. when deleting a yet to be persisted record from a list 
        // of new records that have not been saved.
        if (!(object instanceof PlaLogs)) {
            return false;
        }
        PlaLogs other = (PlaLogs) object;
        if ((this.plaLogId == null && other.plaLogId != null) || (this.plaLogId != null && !this.plaLogId.equals(other.plaLogId))
                || (this.plaLogId == null && other.plaLogId == null 
                    && ((this.activityDt != null && !this.activityDt.equals(other.activityDt)) 
                        || (this.activityDetailsTxt != null && !this.activityDetailsTxt.equals(other.activityDetailsTxt))))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbPlaLogs[plaLogId=" + plaLogId + "], Date: [" + this.activityDt + "], Activity: [" + this.getActivityDetailsTxt() + "]";
    }

}
