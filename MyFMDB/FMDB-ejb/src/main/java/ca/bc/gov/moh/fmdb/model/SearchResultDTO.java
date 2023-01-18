/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SearchResultDTO.java                           *
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
public interface SearchResultDTO extends SearchDTO {

    Date getReceivedDate();

    void setReceivedDate(Date receivedDate);
    
    String getSubmissionNumberAsString();
}
