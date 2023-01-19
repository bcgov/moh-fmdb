/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        AddressesFacadeLocal.java                      *
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

import ca.bc.gov.moh.fmdb.entity.Addresses;
import ca.bc.gov.moh.fmdb.entity.Companies;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Chris.Prince
 */
@Local
public interface AddressesFacadeLocal {

    void create(Addresses Addresses);

    void edit(Addresses Addresses);

    void remove(Addresses Addresses);

    Addresses find(Object id);

    List<Addresses> findAll();
    
    public Addresses findByCompany(Long companyId);
    
    public Addresses findCompanyAddress(Companies c);
}
