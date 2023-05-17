/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        JavaLoggingLoggerImpl.java                     *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package ca.bc.gov.moh.fmdb.logging;

import java.util.logging.Level;

/**
 *
 * @author david.tulk
 */
public class JavaLoggingLoggerImpl implements Logger, java.io.Serializable {
    transient java.util.logging.Logger logger;
    
    public JavaLoggingLoggerImpl(String name) {
        this.logger = java.util.logging.Logger.getLogger(name);
    }
    
    public JavaLoggingLoggerImpl(Class name) {
        this(name.getName());
    }
    
    public String getName() {
        return this.logger.getName();
    }

    /**
     *  
     *  Logs a message at the FINE level.
     * 
     *  @param message the message to log
     *  
     */
    public void debug(Object message) {
        this.log(Level.FINE, message, null);
    }

    /**
     *  
     *  Logs a message at the FINE level.
     * 
     *  @param message the message to log
     *  @param t the throwable
     *  
     */
    public void debug(Object message, Throwable t) {
        this.log(Level.FINE, message, t);
    }

    /**
     *  
     *  Logs a message at the SEVERE level.
     * 
     *  @param message the message to log
     *  
     */
    public void error(Object message) {
        this.log(Level.SEVERE, message, null);
    }

    /**
     *  
     *  Logs a message at the SEVERE level.
     * 
     *  @param message the message to log
     *  @param t the throwable
     *  
     */
    public void error(Object message, Throwable t) {
       this.log(Level.SEVERE, message, t);
    }

    /**
     *  
     *  Logs a message at the SEVERE level.
     * 
     *  @param message the message to log
     *  
     */
    public void fatal(Object message) {
        this.log(Level.SEVERE, message, null);
    }

    /**
     *  
     *  Logs a message at the SEVERE level.
     * 
     *  @param message the message to log
     *  @param t the throwable
     *  
     */
    public void fatal(Object message, Throwable t) {
        this.log(Level.SEVERE, message, t);
    }

    public void info(Object message) {
         this.log(Level.INFO, message, null);
    }

    public void info(Object message, Throwable t) {
         this.logger.log(Level.INFO, message.toString(), t);
    }

    public void warn(Object message) {
        this.log(Level.WARNING, message, null);
    }

    public void warn(Object message, Throwable t) {
         this.log(Level.WARNING, message, t);
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
        return this.logger.isLoggable(Level.FINE);
    }

    public boolean isErrorEnabled() {
        return this.logger.isLoggable(Level.SEVERE);
    }

    public boolean isFatalEnabled() {
        return this.logger.isLoggable(Level.SEVERE);
    }

    public boolean isInfoEnabled() {
        return this.logger.isLoggable(Level.INFO);
    }

    public boolean isWarnEnabled() {
        return this.logger.isLoggable(Level.WARNING);
    }
    
}
