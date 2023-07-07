/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        LoggerPhaseListener.java                       *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fmdbwar.interceptor;

import ca.bc.gov.moh.fmdb.logging.Logger;
import ca.bc.gov.moh.fmdb.logging.LoggerFactory;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * 
 * Provides a logging facility that can be used before or
 * after any phase in the JSF lifecycle.
 * 
 * @author david.tulk
 */
public class LoggerPhaseListener implements PhaseListener {
    private Logger logger = LoggerFactory.createLogger(LoggerPhaseListener.class);
    
    public void afterPhase(PhaseEvent e) {
        logger.debug("AFTER PHASE EVENT: " + e.getPhaseId());
    }
    
    public void beforePhase(PhaseEvent e) {
        logger.debug("BEFORE PHASE EVENT: " + e.getPhaseId());
    }
    
    /**
     * 
     * Gets the phase(s) for which this phase listener will be processed.
     * e.g. if the method returns <code>PhaseId.PROCESS_VALIDATIONS</code> 
     * the <code>afterPhase</code> and <code>beforePhase</code> will only
     * be called during the process validation phase.
     * 
     * @return the phase id for which this listener should be called
     */
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
    
}
