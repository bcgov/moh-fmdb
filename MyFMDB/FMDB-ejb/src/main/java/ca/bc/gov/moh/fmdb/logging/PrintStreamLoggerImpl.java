/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        PrintStreamLoggerImpl.java                     *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package ca.bc.gov.moh.fmdb.logging;

import java.io.PrintStream;

/**
 *  
 * A basic logger that writes messages to a <code>java.io.PrintStream</code> such
 * as <code>java.System.out</code> and <code>java.System.err</code>.<br/>
 * The implementation is thread safe.
 * 
 * @author david.tulk
 */
public class PrintStreamLoggerImpl implements Logger, java.io.Serializable {
    private String name;
    private transient PrintStream out;
    private transient final Object LOCK = new Object();
    
    public PrintStreamLoggerImpl(String name, PrintStream stream) {
        this.name = name;
        this.out = stream;
    }
    
    public PrintStreamLoggerImpl(Class name, PrintStream stream) {
        this.name = name.getName();
        this.out = stream;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void debug(Object message) {
        this.print("[DEBUG]: " + message);
    }
    
    public void debug(Object message, Throwable t) {
        this.print("[DEBUG]: " + message, t);
    }

    public void error(Object message) {
        this.print("[ERROR]: " + message);
    }

    public void error(Object message, Throwable t) {
        this.print("[ERROR]: " + message, t);
    }

    public void fatal(Object message) {
        this.print("[FATAL]: " + message);
    }

    public void fatal(Object message, Throwable t) {
        this.print("[FATAL]: " + message, t);
    }

    public void info(Object message) {
        this.print("[INFO]: " + message);
    }

    public void info(Object message, Throwable t) {
        this.print("[INFO]: " + message, t);
    }

    public void warn(Object message) {
        this.print("[WARN]: " + message);
    }

    public void warn(Object message, Throwable t) {
        this.print("[WARN]: " + message, t);
    }
    
    private void print(Object message) {
        synchronized(LOCK) {
            this.out.println(message);
        }
    }
    
    private void print(Object message, Throwable t) {
        synchronized(LOCK) {
            this.out.println(message);
            t.printStackTrace(this.out);
        }
    }

    public boolean isDebugEnabled() {
        return true;
    }

    public boolean isErrorEnabled() {
        return true;
    }

    public boolean isFatalEnabled() {
        return true;
    }

    public boolean isInfoEnabled() {
        return true;
    }

    public boolean isWarnEnabled() {
        return true;
    }
}
