/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SubmissionManagerFacadeLocal.java              *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.bc.gov.moh.fmdb.business;

import ca.bc.gov.moh.fmdb.business.exception.ItemNotFoundFMDBException;
import ca.bc.gov.moh.fmdb.business.exception.SaveFMDBException;
import ca.bc.gov.moh.fmdb.business.exception.ValidationFMDBException;
import ca.bc.gov.moh.fmdb.entity.Companies;
import ca.bc.gov.moh.fmdb.model.SearchDTO;
import ca.bc.gov.moh.fmdb.model.SearchResultDTO;
import ca.bc.gov.moh.fmdb.model.submission.*;
import ca.bc.gov.moh.fmdb.entity.Submission;
import java.util.List;
import javax.ejb.Local;

/**
 *  
 *  Provides an interface for interacting with submissions.
 *  
 */
@Local
public interface SubmissionManagerFacadeLocal {
    
    /**
     *  
     *  Obtains the results for a submission search.
     *  
     *  @param searchDTO the search parameters
     *  @return the search results
     *  
     */
    List<SearchResultDTO> searchSubmission(SearchDTO searchDTO);
    
    /**
     *  
     *  Obtains the Submission data based on a submission id.
     *  
     *  @param submissionId - the Id of a Submission
     *  @return Submission - the Submission entity 
     *  @throws ItemNotFoundFMDBException if the submission could not be found
     * 
     */
    public Submission retrieveSubmission(long submissionId) throws ItemNotFoundFMDBException;
    
    /**
     *  
     *  Saves the submission.  If the submission doesn't have an id associated with it, 
     *  a new submission is created.  If it does have an id, the submission is updated.
     *  If the submission is to be newly created, then the returned submission will contain 
     *  the newly submission number and ids for iteself and its internal relationships.
     *  
     *  @param submission the submission
     *  @throws ValidationFMDBException if the submission failed validation
     *  @throws SaveFMDBException if a problem occured during the save operation
     *  
     */
    public void saveSubmission(Submission submission) throws ValidationFMDBException, SaveFMDBException;
    
    /**
     *  
     *  Obtains the results for a company search.
     *  
     *  @param companyName the search parameters
     *  @return the search results
     *  
     */
    List<Companies> searchCompanies(String companyName);
    
    /**
     *  
     *  Obtains a Companies object based on its Id
     *  
     *  @param companyId the search parameters
     *  @return the Companies object
     *  
     */
    Companies retrieveCompany(Long companyId) throws ItemNotFoundFMDBException;
    
}
