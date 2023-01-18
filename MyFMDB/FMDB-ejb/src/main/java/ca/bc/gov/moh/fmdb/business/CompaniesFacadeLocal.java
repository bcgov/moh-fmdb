/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        CompaniesFacadeLocal.java                      *
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
import ca.bc.gov.moh.fmdb.entity.Companies;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Chris.Prince
 */
@Local
public interface CompaniesFacadeLocal {

    void create(Companies Companies);

    void edit(Companies Companies);

    void remove(Companies Companies);

    Companies find(Object id);

    List<Companies> findAll();
    
    List<Companies> searchCompanies(String companyName);
    
    Companies findCompanyById(Long companyId) throws ItemNotFoundFMDBException;

}
