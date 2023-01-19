/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VBudgetImpactAnalysisFacade.java               *
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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author wande.he
 */
@Stateless
public class VBudgetImpactAnalysisFacade implements VBudgetImpactAnalysisFacadeLocal {
    @PersistenceContext(unitName="FMDB-ejbPU")
    private EntityManager em;

    public void create(VBudgetImpactAnalysis vBudgetImpactAnalysis) {
        em.persist(vBudgetImpactAnalysis);
    }

    public void edit(VBudgetImpactAnalysis vBudgetImpactAnalysis) {
        em.merge(vBudgetImpactAnalysis);
    }

    public void remove(VBudgetImpactAnalysis vBudgetImpactAnalysis) {
        em.remove(em.merge(vBudgetImpactAnalysis));
    }

    public VBudgetImpactAnalysis find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.VBudgetImpactAnalysis.class, id);
    }

    public List<VBudgetImpactAnalysis> findReportDataByCriteria(String reportView) {
        String queryConditions = "";
        
        if(reportView.equals("Active"))
            queryConditions = " o.biaActualDate IS NULL AND o.biaDesc LIKE 'Yes%' AND o.subType NOT IN ('I', 'PM')";
        else if(reportView.equals("Completed"))
            queryConditions = " o.biaActualDate IS NOT NULL AND upper(o.biaDesc) LIKE 'YES%' AND o.subType NOT IN ('I', 'PM')";
        
        String queryStr = "select o from VBudgetImpactAnalysis o WHERE " + queryConditions;
        System.err.println(queryStr);
        Query query = em.createQuery(queryStr);

        List<VBudgetImpactAnalysis> result = query.getResultList();
        System.err.println(result.size());
        return result;
    }
}
