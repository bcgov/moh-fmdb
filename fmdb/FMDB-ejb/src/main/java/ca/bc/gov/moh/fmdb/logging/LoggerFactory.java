/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        LoggerFactory.java                             *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package ca.bc.gov.moh.fmdb.logging;

/**
 *
 * Factory for obtaining the application's logging implementation.
 * In the future, may want to consider using an IoC container rather 
 * than factories.
 * 
 * @author david.tulk
 */
public class LoggerFactory implements java.io.Serializable {
    
    /**
     *  
     *  Creates a logger with the passed name.
     *  
     *  @param name the name of the logger
     *  @return the newly constructed logger
     *  
     */
    public static Logger createLogger(String name) {
        return new PrintStreamLoggerImpl(name, System.out);
        //return new DummyLoggerImpl(name);
    }
    
    /**
     *  
     *  Creates a logger using the class for the name.
     *  
     *  @param name the name of the logger
     *  @return the newly constructed logger
     *  
     */
    public static Logger createLogger(Class name) {
        return new PrintStreamLoggerImpl(name, System.out);
        //return new DummyLoggerImpl(name.getName());
    }
    
}
