/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ReviewStatusesFacadeLocal.java                 *
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
 
import ca.bc.gov.moh.fmdb.business.exception.SaveFMDBException;
import ca.bc.gov.moh.fmdb.entity.ReviewStatuses;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Chris.Prince
 */
@Local
public interface ReviewStatusesFacadeLocal {

    void create(ReviewStatuses ReviewStatuses);

    void edit(ReviewStatuses ReviewStatuses);

    void remove(ReviewStatuses ReviewStatuses);

    ReviewStatuses find(Object id);

    List<ReviewStatuses> findAll();
    
    List<ReviewStatuses> findAllActive();
    
    void saveReviewStatusCodes(ReviewStatuses reviewStatuses) throws SaveFMDBException;
    
    public long findAssociatedSubmissions(String code);

}
