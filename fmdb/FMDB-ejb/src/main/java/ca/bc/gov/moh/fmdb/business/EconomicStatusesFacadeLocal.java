/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        EconomicStatusesFacadeLocal.java               *
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

import ca.bc.gov.moh.fmdb.entity.EconomicStatuses;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Chris.Prince
 */
@Local
public interface EconomicStatusesFacadeLocal {

    void create(EconomicStatuses EconomicStatuses);

    void edit(EconomicStatuses EconomicStatuses);

    void remove(EconomicStatuses EconomicStatuses);

    EconomicStatuses find(Object id);

    List<EconomicStatuses> findAll();

}
