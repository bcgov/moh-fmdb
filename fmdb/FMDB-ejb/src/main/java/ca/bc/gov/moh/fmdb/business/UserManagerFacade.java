/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        UserManagerFacade.java                         *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package ca.bc.gov.moh.fmdb.business;

import java.security.Principal;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 *
 * @author david.tulk
 */
@Stateless
public class UserManagerFacade implements UserManagerFacadeLocal {
    @Resource
    private SessionContext sessionContext;
    
    public String getUsernameOfLoggedInUser() {
        Principal principal = this.sessionContext.getCallerPrincipal();
        String user = principal.getName();
        
        return user == null ? "" : user;
    }
    
}
