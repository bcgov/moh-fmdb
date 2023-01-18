/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VBudgetImpactAnalysisFacadeLocal.java          *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.bc.gov.moh.fmdb.business;

import ca.bc.gov.moh.fmdb.entity.VBudgetImpactAnalysis;
//import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wande.he
 */
@Local
public interface VBudgetImpactAnalysisFacadeLocal {

    void create(VBudgetImpactAnalysis vBudgetImpactAnalysis);

    void edit(VBudgetImpactAnalysis vBudgetImpactAnalysis);

    void remove(VBudgetImpactAnalysis vBudgetImpactAnalysis);

    VBudgetImpactAnalysis find(Object id);

    List<VBudgetImpactAnalysis> findReportDataByCriteria(String reportView);

}
