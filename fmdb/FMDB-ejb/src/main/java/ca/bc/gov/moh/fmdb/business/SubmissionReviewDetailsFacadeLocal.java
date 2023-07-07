/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SubmissionReviewDetailsFacadeLocal.java        *
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

import ca.bc.gov.moh.fmdb.entity.SubmissionReviewDetails;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Chris.Prince
 */
@Local
public interface SubmissionReviewDetailsFacadeLocal {

    void create(SubmissionReviewDetails SubmissionReviewDetails);

    void edit(SubmissionReviewDetails SubmissionReviewDetails);

    void remove(SubmissionReviewDetails SubmissionReviewDetails);

    SubmissionReviewDetails find(Object id);

    List<SubmissionReviewDetails> findAll();

}
