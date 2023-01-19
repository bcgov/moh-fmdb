/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        LoggerInterceptor.java                         *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package ca.bc.gov.moh.fmdb.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import ca.bc.gov.moh.fmdb.logging.Logger;
import ca.bc.gov.moh.fmdb.logging.LoggerFactory;

/**
 * 
 * An interceptor to provide logging capability to EJBs. 
 * 
 * @author david.tulk
 */
public class LoggerInterceptor {
    private Logger logger = LoggerFactory.createLogger(LoggerInterceptor.class);
    
    @AroundInvoke
    public Object log(InvocationContext invocation) throws Exception {
        this.logger.debug("CALLING: " + invocation.getMethod().getName());
        Object object = invocation.proceed( );
        this.logger.debug("RETURNING FROM: " + invocation.getMethod().getName());
        
        return object;
    }
    
}
