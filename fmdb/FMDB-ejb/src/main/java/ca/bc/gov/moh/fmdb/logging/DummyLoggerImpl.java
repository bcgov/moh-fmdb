/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        DummyLoggerImpl.java                           *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package ca.bc.gov.moh.fmdb.logging;

/**
 *  
 *  Logging implementation that doesn't perform any logging.
 *  
 */
public class DummyLoggerImpl implements Logger, java.io.Serializable {
    private String name;
    
    public DummyLoggerImpl(String name) {
        this.name = "DUMMY_LOGGER: " + name;
    }
    
    public String getName() {
        return this.name;
    }

    public void debug(Object message) {
        
    }

    public void debug(Object message, Throwable t) {
        
    }

    public boolean isDebugEnabled() {
        return false;
    }

    public void error(Object message) {
        
    }

    public void error(Object message, Throwable t) {
        
    }

    public boolean isErrorEnabled() {
        return false;
    }

    public void fatal(Object message) {
        
    }

    public void fatal(Object message, Throwable t) {
        
    }

    public boolean isFatalEnabled() {
       return false;
    }

    public void info(Object message) {
        
    }

    public void info(Object message, Throwable t) {
        
    }

    public boolean isInfoEnabled() {
       return false;
    }

    public void warn(Object message) {
        
    }

    public void warn(Object message, Throwable t) {
        
    }

    public boolean isWarnEnabled() {
        return false;
    }

}
