/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        PlaStatusTypesFacadeLocal.java                 *
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
import ca.bc.gov.moh.fmdb.entity.PlaStatusTypes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Chris.Prince
 */
@Local
public interface PlaStatusTypesFacadeLocal {

    void create(PlaStatusTypes PlaStatusTypes);

    void edit(PlaStatusTypes PlaStatusTypes);

    void remove(PlaStatusTypes PlaStatusTypes);

    PlaStatusTypes find(Object id);

    List<PlaStatusTypes> findAll();
    List<PlaStatusTypes> findAllActive();
    
    void savePlaStatusCodes(PlaStatusTypes plaStatusTypes) throws SaveFMDBException;
    
    public long findAssociatedSubmissions(String code);
}
