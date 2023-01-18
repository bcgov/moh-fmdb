/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SubmissionFacadeLocal.java                     *
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
import ca.bc.gov.moh.fmdb.entity.Submission;
import java.util.List;
import javax.ejb.Local;
import ca.bc.gov.moh.fmdb.model.SearchDTO;
import ca.bc.gov.moh.fmdb.model.SearchResultDTO;
import java.util.Date;

/**
 *
 * @author Chris.Prince
 */
@Local
public interface SubmissionFacadeLocal {

    void create(Submission Submission);

    void edit(Submission Submission);

    void remove(Submission Submission);

    Submission find(Object id);
    
    Submission findSubmissionById(long submissionNumber) throws ItemNotFoundFMDBException;
    
    /**
     *  
     *  Saves the submission.  If the submission doesn't have an id associated with it, 
     *  a new submission is created.  If it does have an id, the submission is updated.
     *  If the submission is to be newly created, then the returned submission will contain 
     *  the newly submission number and ids for iteself and its internal relationships.
     *  
     *  @param submission the submission
     *  @throws SaveFMDBException if a problem occured during the save operation
     *  
     */
    void saveSubmission(Submission submission) throws SaveFMDBException;
    
    List<Submission> findAll();

    List<SearchResultDTO> searchSubmission(SearchDTO searchDTO);

    List<Submission> findSubmissionByCriteria(String reportView, String sortBy, Date dateFrom, Date dateTo);
}
