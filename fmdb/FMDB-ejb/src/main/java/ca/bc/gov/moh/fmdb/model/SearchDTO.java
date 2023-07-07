/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SearchDTO.java                                 *
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

/**
 *
 * @author graham.townsend
 */
public interface SearchDTO {

    String getChemicalName();

    String getCompanyName();

    Long getSubmissionNumber();

    String getSubmissionType();

    String getTradeName();

    void setChemicalName(String chemicalName);

    void setCompanyName(String companyName);

    void setSubmissionNumber(Long submissionNumber);

    void setSubmissionType(String submissionType);

    void setTradeName(String tradeName);

}
