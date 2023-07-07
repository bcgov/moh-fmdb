/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ReviewerNameTypesFacadeLocal.java              *
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
import ca.bc.gov.moh.fmdb.entity.ReviewerNameTypes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Chris.Prince
 */
@Local
public interface ReviewerNameTypesFacadeLocal {

    void create(ReviewerNameTypes ReviewerNameTypes);

    void edit(ReviewerNameTypes ReviewerNameTypes);

    void remove(ReviewerNameTypes ReviewerNameTypes);

    ReviewerNameTypes find(Object id);

    List<ReviewerNameTypes> findAll();
    
    List<ReviewerNameTypes> findAllActive();
    
    void saveReviewerTypes(ReviewerNameTypes reviewerNameTypes) throws SaveFMDBException;
    
    public long findAssociatedSubmissions(String code);
}
