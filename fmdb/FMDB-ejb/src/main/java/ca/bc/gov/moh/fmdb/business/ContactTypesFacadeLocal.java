/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ContactTypesFacadeLocal.java                   *
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

import ca.bc.gov.moh.fmdb.entity.ContactTypes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Chris.Prince
 */
@Local
public interface ContactTypesFacadeLocal {

    void create(ContactTypes ContactTypes);

    void edit(ContactTypes ContactTypes);

    void remove(ContactTypes ContactTypes);

    ContactTypes find(Object id);

    List<ContactTypes> findAll();

}
