/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SubmissionTypesFacadeLocal.java                *
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
import ca.bc.gov.moh.fmdb.entity.SubmissionTypes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Chris.Prince
 */
@Local
public interface SubmissionTypesFacadeLocal {

    void create(SubmissionTypes SubmissionTypes);

    void edit(SubmissionTypes SubmissionTypes);

    void remove(SubmissionTypes SubmissionTypes);

    SubmissionTypes find(Object id);

    List<SubmissionTypes> findAll();
    List<SubmissionTypes> findAllActive();
    
    void saveSubmissionTypeCodes(SubmissionTypes subTypes, boolean newCode) throws SaveFMDBException;
    
    public long findAssociatedSubmissions(String code);

}
