/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        UserManagerFacadeLocal.java                    *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package ca.bc.gov.moh.fmdb.business;

import javax.ejb.Local;

/**
 *  
 *  Provides an interface to an authenticated user.
 *  
 */
@Local
public interface UserManagerFacadeLocal {
    
    /**
     *  
     *  Obtains the username of the user that is logged in.
     *  
     *  @return the username
     *  
     */
    String getUsernameOfLoggedInUser();
    
}
