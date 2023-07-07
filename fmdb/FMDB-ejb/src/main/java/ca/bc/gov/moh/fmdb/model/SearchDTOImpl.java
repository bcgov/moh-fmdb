/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SearchDTOImpl.java                             *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.bc.gov.moh.fmdb.model;

import java.io.Serializable;

/**
 *
 * @author graham.townsend
 */
public class SearchDTOImpl implements SearchDTO, Serializable {

    private Long submissionNumber;
    private String chemicalName;
    private String submissionType;
    private String tradeName;
    private String companyName;

    public String getChemicalName() {
        return chemicalName == null ? chemicalName : chemicalName.trim();
    }

    public void setChemicalName(String chemicalName) {
        this.chemicalName = chemicalName;
    }

    public String getCompanyName() {
        return companyName == null ? companyName : companyName.trim();
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getSubmissionNumber() {
        return submissionNumber;
    }

    public void setSubmissionNumber(Long submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    public String getSubmissionType() {
        return submissionType == null ? submissionType : submissionType.trim();
    }

    public void setSubmissionType(String submissionType) {
        this.submissionType = submissionType;
    }

    public String getTradeName() {
        return tradeName == null ? tradeName : tradeName.trim();
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

}
