/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ItemNotFoundFMDBException.java                 *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package ca.bc.gov.moh.fmdb.business.exception;

/**
 *
 * @author david.tulk
 */
public class ItemNotFoundFMDBException extends BaseFMDBException {
    public static enum ItemNotFoundExceptionType {
        SUBMISSION
    }
    
    private ItemNotFoundExceptionType exceptionType;
    
    public ItemNotFoundFMDBException(String message, ItemNotFoundExceptionType exceptionType) {
        this(message, null, exceptionType);
    }
    
    public ItemNotFoundFMDBException(Throwable cause, ItemNotFoundExceptionType exceptionType) {
       this(null, cause, exceptionType);
    }
    
    public ItemNotFoundFMDBException(String message, Throwable cause, ItemNotFoundExceptionType exceptionType) {
        super(message, cause);
        this.exceptionType = exceptionType;
    }
     
    public boolean isSubmissionNotFoundException() {
        return this.exceptionType.name().equals(ItemNotFoundExceptionType.SUBMISSION.name());
    }
    
    public ItemNotFoundExceptionType getExceptionType() {
        return this.exceptionType;
    }
    
}
