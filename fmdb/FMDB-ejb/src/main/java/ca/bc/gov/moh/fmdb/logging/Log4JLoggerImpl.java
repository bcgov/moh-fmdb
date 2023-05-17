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

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 *
 * Log4J implementation of the logger.
 *
 * @author david.tulk
 */
public class Log4JLoggerImpl implements ca.bc.gov.moh.fmdb.logging.Logger, java.io.Serializable {

    static final Logger logger = LogManager.getLogger(Log4JLoggerImpl.class.getName());

    @Override
    public String getName() {
        return this.logger.getName();
    }

    @Override
    public void debug(Object message) {
        this.log(Level.DEBUG, message, null);
    }

    @Override
    public void debug(Object message, Throwable t) {
        this.log(Level.DEBUG, message, t);
    }

    @Override
    public void error(Object message) {
        this.log(Level.ERROR, message, null);
    }

    @Override
    public void error(Object message, Throwable t) {
        this.log(Level.ERROR, message, t);
    }

    @Override
    public void fatal(Object message) {
        this.log(Level.FATAL, message, null);
    }

    @Override
    public void fatal(Object message, Throwable t) {
        this.log(Level.FATAL, message, t);
    }

    @Override
    public void info(Object message) {
        this.log(Level.INFO, message, null);
    }

    @Override
    public void info(Object message, Throwable t) {
        this.logger.log(Level.INFO, message.toString(), t);
    }

    @Override
    public void warn(Object message) {
        this.log(Level.WARN, message, null);
    }

    @Override
    public void warn(Object message, Throwable t) {
        this.log(Level.WARN, message, t);
    }

    private void log(Level level, Object message, Throwable t) {
        if (t == null) {
            this.logger.log(level, String.valueOf(message));
        } else {
            this.logger.log(level, String.valueOf(message), t);
        }
    }

    @Override
    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return this.logger.isEnabled(Level.ERROR);
    }

    @Override
    public boolean isFatalEnabled() {
        return this.logger.isEnabled(Level.FATAL);
    }

    @Override
    public boolean isInfoEnabled() {
        return this.logger.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return this.logger.isEnabled(Level.WARN);
    }

}
