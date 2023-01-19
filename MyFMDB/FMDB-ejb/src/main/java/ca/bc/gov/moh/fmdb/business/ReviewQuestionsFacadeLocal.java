/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ReviewQuestionsFacadeLocal.java                *
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

import ca.bc.gov.moh.fmdb.entity.ReviewQuestions;
import java.util.List;
import javax.ejb.Local;

/**
 * 
 * @author Chris.Prince
 */
@Local
public interface ReviewQuestionsFacadeLocal {

    void create(ReviewQuestions ReviewQuestions);

    void edit(ReviewQuestions ReviewQuestions);

    void remove(ReviewQuestions ReviewQuestions);

    ReviewQuestions find(Object id);

    List<ReviewQuestions> findAll();

}
