/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SaveFMDBException.java                         *
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
public class SaveFMDBException extends BaseFMDBException {
    public static enum SaveExceptionType {
        SUBMISSION_NOT_FOUND, SUBMISSION_NULL, OTHER, CODE_NOT_FOUND
    }
    
    private SaveExceptionType exceptionType;
    
    public SaveFMDBException(String message, SaveExceptionType exceptionType) {
        this(message, null, exceptionType);
    }
    
    public SaveFMDBException(Throwable cause, SaveExceptionType exceptionType) {
       this(null, cause, exceptionType);
    }
    
    public SaveFMDBException(String message, Throwable cause, SaveExceptionType exceptionType) {
        super(message, cause);
        this.exceptionType = exceptionType;
    }
     
    public boolean isSubmissionNotFoundSaveException() {
        return this.exceptionType.name().equals(SaveExceptionType.SUBMISSION_NOT_FOUND.name());
    }
    
    public boolean isSubmissionNullSaveException() {
        return this.exceptionType.name().equals(SaveExceptionType.SUBMISSION_NULL.name());
    }
    
    public boolean isOtherSaveException() {
        return this.exceptionType.name().equals(SaveExceptionType.OTHER.name());
    }
    
    public boolean isCodeNotFoundSaveException() {
        return this.exceptionType.name().equals(SaveExceptionType.CODE_NOT_FOUND.name());
    }
    
    public SaveExceptionType getExceptionType() {
        return this.exceptionType;
    }
    
}
