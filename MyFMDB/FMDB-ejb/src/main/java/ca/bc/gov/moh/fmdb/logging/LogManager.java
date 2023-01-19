/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        LogManager.java                                *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package ca.bc.gov.moh.fmdb.logging;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *  
 *  Manages a group of loggers.  Loggers are created and retrieved from
 *  the LogManager.  Logger names are case insensitive, and are stored
 *  within the LogManager in upper case format.<br/><br/>
 *  The LogManager is threadsafe.
 *  <br/><br/>
 *  LogManager is a singleton.  In the future, the singleton behaviour
 *  should be defined externally using a container like Spring.
 *  
 *  @author david.tulk
 *  
 */
public class LogManager implements java.io.Serializable {
    private Map<String, Logger> loggers;
    private static LogManager logManager = new LogManager();
    private Object LOCK = new Object();
    
    private LogManager() {
        this.loggers = new HashMap<String, Logger>();
    }
    
    public static LogManager getLogManager() {
        return logManager;
    }
    
    /**
     *  
     *  Gets the logger with the passed name, or creates one if this 
     *  log manager doesn't have a reference to the logger.
     *  
     *  @param name the logger name, case insensitive
     *  @return the logger
     *  
     */
    public Logger getLogger(String name) {
        synchronized(LOCK) {
            Logger logger;
            if(this.loggers.containsKey(name.toUpperCase())) {
                logger = this.loggers.get(name.toUpperCase());
            }
            else {
                logger = LoggerFactory.createLogger(name);
                this.loggers.put(name.toUpperCase(), logger);
            }

            return logger;
        }
    }
    
    /**
     *  
     *  Gets the logger with the passed name, or creates one if this 
     *  log manager doesn't have a reference to the logger.
     *  
     *  @param name the logger name, case insensitive
     *  @return the logger
     *  
     */
    public Logger getLogger(Class clazz) {
        synchronized(LOCK) {
            return this.getLogger(clazz.getCanonicalName());
        }
    }
    
    /**
     *  
     *  Cleans up the log manager by releasing all of its loggers.
     *  
     */
    public void releaseLoggers() {
         synchronized(LOCK) {
             for(Iterator<String> itr = this.loggers.keySet().iterator(); itr.hasNext(); ) {
                 //NOTE: May want to add a shutdown() method in Logger and call it prior to calling itr.remove()
                 
                 itr.remove();
             }
         }
    }
}
