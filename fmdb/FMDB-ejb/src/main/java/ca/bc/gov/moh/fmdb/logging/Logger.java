/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        Logger.java                                    *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package ca.bc.gov.moh.fmdb.logging;

/**
 * 
 * Interface to the FMDB logging facility.  The configuration for 
 * a specific logger is implementation dependent.
 * 
 * @author david.tulk
 */
public interface Logger  {
    
    
    /**
     *  
     *  Gets the name associated with the logger.
     *  
     *  @return the logger's name
     *  
     */
    public String getName();
    
    public void debug(Object message);
    public void debug(Object message, Throwable t);
    public boolean isDebugEnabled();
    
    public void error(Object message);
    public void error(Object message, Throwable t);
    public boolean isErrorEnabled();
    
    public void fatal(Object message);
    public void fatal(Object message, Throwable t);
    public boolean isFatalEnabled();
    
    public void info(Object message);
    public void info(Object message, Throwable t);
    public boolean isInfoEnabled();
    
    public void warn(Object message);
    public void warn(Object message, Throwable t);
    public boolean isWarnEnabled();
    
}
