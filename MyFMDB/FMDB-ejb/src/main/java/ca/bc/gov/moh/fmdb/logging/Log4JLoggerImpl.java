/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        Log4JLoggerImpl.java                           *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package ca.bc.gov.moh.fmdb.logging;

import org.apache.log4j.Level;

/**
 * 
 * Log4J implementation of the logger.
 * 
 * @author david.tulk
 */
public class Log4JLoggerImpl implements Logger, java.io.Serializable {
    private org.apache.log4j.Logger logger;
    
    public Log4JLoggerImpl(String name) {
        this.logger = org.apache.log4j.Logger.getLogger(name);
    }
    
    public Log4JLoggerImpl(Class name) {
        this.logger = org.apache.log4j.Logger.getLogger(name);
    }
        
    public String getName() {
        return this.logger.getName();
    }

    public void debug(Object message) {
        this.log(Level.DEBUG, message, null);
    }

    public void debug(Object message, Throwable t) {
        this.log(Level.DEBUG, message, t);
    }

    public void error(Object message) {
        this.log(Level.ERROR, message, null);
    }

    public void error(Object message, Throwable t) {
       this.log(Level.ERROR, message, t);
    }
    
    public void fatal(Object message) {
        this.log(Level.FATAL, message, null);
    }
    
    public void fatal(Object message, Throwable t) {
        this.log(Level.FATAL, message, t);
    }

    public void info(Object message) {
         this.log(Level.INFO, message, null);
    }

    public void info(Object message, Throwable t) {
         this.logger.log(Level.INFO, message.toString(), t);
    }

    public void warn(Object message) {
        this.log(Level.WARN, message, null);
    }

    public void warn(Object message, Throwable t) {
         this.log(Level.WARN, message, t);
    }
    
    private void log(Level level, Object message, Throwable t) {
        if(t == null) {
             this.logger.log(level, String.valueOf(message));
        }
        else {
             this.logger.log(level, String.valueOf(message), t);
        }
    }
    
    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    public boolean isErrorEnabled() {
        return this.logger.isEnabledFor(Level.ERROR);
    }

    public boolean isFatalEnabled() {
        return this.logger.isEnabledFor(Level.FATAL);
    }

    public boolean isInfoEnabled() {
        return this.logger.isInfoEnabled();
    }

    public boolean isWarnEnabled() {
        return this.logger.isEnabledFor(Level.WARN);
    }

}
