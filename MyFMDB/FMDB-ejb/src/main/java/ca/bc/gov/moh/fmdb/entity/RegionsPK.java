/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        RegionsPK.java                                 *
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

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Chris.Prince
 */
@Embeddable
public class RegionsPK implements Serializable {
    @Column(name = "REGION_CD", nullable = false)
    private String regionCd;
    @Column(name = "COUNTRY_CD", nullable = false)
    private String countryCd;

    public RegionsPK() {
    }

    public RegionsPK(String regionCd, String countryCd) {
        this.regionCd = regionCd;
        this.countryCd = countryCd;
    }

    public String getRegionCd() {
        return regionCd;
    }

    public void setRegionCd(String regionCd) {
        this.regionCd = regionCd;
    }

    public String getCountryCd() {
        return countryCd;
    }

    public void setCountryCd(String countryCd) {
        this.countryCd = countryCd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (regionCd != null ? regionCd.hashCode() : 0);
        hash += (countryCd != null ? countryCd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegionsPK)) {
            return false;
        }
        RegionsPK other = (RegionsPK) object;
        if ((this.regionCd == null && other.regionCd != null) || (this.regionCd != null && !this.regionCd.equals(other.regionCd))) {
            return false;
        }
        if ((this.countryCd == null && other.countryCd != null) || (this.countryCd != null && !this.countryCd.equals(other.countryCd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbRegionsPK[regionCd=" + regionCd + ", countryCd=" + countryCd + "]";
    }

}
