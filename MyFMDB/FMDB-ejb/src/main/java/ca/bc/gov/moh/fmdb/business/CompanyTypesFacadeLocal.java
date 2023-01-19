/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        CompanyTypesFacadeLocal.java                   *
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
import ca.bc.gov.moh.fmdb.entity.CompanyTypes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Chris.Prince
 */
@Local
public interface CompanyTypesFacadeLocal {

    void create(CompanyTypes CompanyTypes);

    void edit(CompanyTypes CompanyTypes);

    void remove(CompanyTypes CompanyTypes);

    CompanyTypes find(Object id);

    List<CompanyTypes> findAll();

    List<CompanyTypes> findAllActive();

    void saveCompanyTypeCodes(CompanyTypes companyTypeCodes) throws SaveFMDBException;

    long findAssociatedSubmissions(String code);
}
