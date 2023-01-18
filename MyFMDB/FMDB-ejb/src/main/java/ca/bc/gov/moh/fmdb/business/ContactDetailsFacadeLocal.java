/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ContactDetailsFacadeLocal.java                 *
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

import ca.bc.gov.moh.fmdb.entity.Companies;
import ca.bc.gov.moh.fmdb.entity.ContactDetails;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Chris.Prince
 */
@Local
public interface ContactDetailsFacadeLocal {

    void create(ContactDetails ContactDetails);

    void edit(ContactDetails ContactDetails);

    void remove(ContactDetails ContactDetails);

    ContactDetails find(Object id);

    List<ContactDetails> findAll();

    ContactDetails findCompanyContactDetails(Companies company);

}
