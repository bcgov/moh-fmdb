/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SearchResultDTOImpl.java                       *
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

import java.util.Date;

/**
 *
 * @author graham.townsend
 */
public class SearchResultDTOImpl extends SearchDTOImpl implements SearchResultDTO {

    private Date receivedDate;

    public SearchResultDTOImpl(Long submissionNumber, Date receivedDate, String submissionType, String companyName) {
        this(submissionNumber, receivedDate, submissionType, companyName, "", "");
    }
    
    public SearchResultDTOImpl(Long submissionNumber, Date receivedDate, String submissionType, String companyName, String chemicalName, String tradeName) {
        this.setSubmissionNumber(submissionNumber);
        this.setReceivedDate(receivedDate);
        this.setSubmissionType(submissionType);
        this.setCompanyName(companyName);
        this.setChemicalName(chemicalName);
        this.setTradeName(tradeName);
    }
        
    public SearchResultDTOImpl(Long submissionNumber, Date receivedDate) {
        this(submissionNumber, receivedDate, "", "", "", "");
    }
    
    public SearchResultDTOImpl() {
        this(null, null);
    }
    
    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getSubmissionNumberAsString() {
        Long subNo = this.getSubmissionNumber();
        
        return subNo == null ? null : subNo.toString();
    }
    
    /**
     *  
     *  This method is required for the hyperlink from the search results table to function.
     *  Witout this method, the search page will simply reload when the submission number hyperlink
     *  is clicked.
     *  
     *  The method body is empty.
     *  
     */
    public void setSubmissionNumberAsString(String submissionNumber) {
        
    }
}
