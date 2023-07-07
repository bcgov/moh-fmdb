/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        BiaRequestedCodesFacadeLocal.java              *
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
import ca.bc.gov.moh.fmdb.entity.BiaRequestedCodes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Chris.Prince
 */
@Local
public interface BiaRequestedCodesFacadeLocal {

    void create(BiaRequestedCodes BiaRequestedCodes);

    void edit(BiaRequestedCodes BiaRequestedCodes);

    void remove(BiaRequestedCodes BiaRequestedCodes);

    BiaRequestedCodes find(Object id);

    List<BiaRequestedCodes> findAll();
    
    List<BiaRequestedCodes> findAllActive();
    
    /**
     *  
     *  Saves the code.  If the code doesn't have an id associated with it, 
     *  a new code is created.  If it does have an id, the code is updated.
     *  If the code is to be newly created, then the returned object will contain 
     *  the new code id. 
     *  
     *  @param BiaRequestedCodes the code
     *  @throws SaveFMDBException if a problem occured during the save operation
     *  
     */
    void saveBiaRequestedCodes(BiaRequestedCodes biaRequestedCodes) throws SaveFMDBException;
    public long findAssociatedSubmissions(String code);
}
