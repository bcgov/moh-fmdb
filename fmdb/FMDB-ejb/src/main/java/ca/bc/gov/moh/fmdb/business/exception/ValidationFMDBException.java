/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ValidationFMDBException.java                   *
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
public class ValidationFMDBException extends BaseFMDBException {
    public static enum ValidationExceptionType {
        FILE_TYPE, FILE_SIZE, REQUIRED_BIA_REQUESTED_CODE, REQUIRED_REVIEW_STATUS_CODE, REQUIRED_PLA_STATUS_CODE
    }
    
    private ValidationExceptionType exceptionType;
    
    public ValidationFMDBException(String message, ValidationExceptionType exceptionType) {
        this(message, null, exceptionType);
    }
    
    public ValidationFMDBException(Throwable cause, ValidationExceptionType exceptionType) {
       this(null, cause, exceptionType);
    }
    
    public ValidationFMDBException(String message, Throwable cause, ValidationExceptionType exceptionType) {
        super(message, cause);
        this.exceptionType = exceptionType;
    }
    
    public boolean isFileTypeException() {
        return this.exceptionType.name().equals(ValidationExceptionType.FILE_TYPE.name());
    }
    
    public boolean isFileSizeException() {
        return this.exceptionType.name().equals(ValidationExceptionType.FILE_SIZE.name());
    }
    
    public boolean isBiaCodeRequiredFieldException() {
        return this.exceptionType.name().equals(ValidationExceptionType.REQUIRED_BIA_REQUESTED_CODE.name());
    }
    
    public boolean isPlaStatusCodeRequiredFieldException() {
         return this.exceptionType.name().equals(ValidationExceptionType.REQUIRED_PLA_STATUS_CODE.name());
    }
    
    public boolean isReviewStatusCodeRequiredFieldException() {
         return this.exceptionType.name().equals(ValidationExceptionType.REQUIRED_REVIEW_STATUS_CODE.name());
    }
    
    public ValidationExceptionType getExceptionType() {
        return this.exceptionType;
    }
    
}
