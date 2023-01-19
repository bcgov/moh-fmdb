/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        BaseFMDBException.java                         *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package ca.bc.gov.moh.fmdb.business.exception;

/**
 *  
 *  Base class for all FMDB exceptions.
 *  
 */
public class BaseFMDBException extends Exception {
    public BaseFMDBException(String message) {
        super(message);
    }
    
    public BaseFMDBException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BaseFMDBException(Throwable cause) {
        super(cause);
    }
}
