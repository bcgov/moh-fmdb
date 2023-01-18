/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VDrugBenefitCommitteeFacade.java               *
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

import ca.bc.gov.moh.fmdb.entity.VDrugBenefitCommittee;
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
public class VDrugBenefitCommitteeFacade implements VDrugBenefitCommitteeFacadeLocal {
    @PersistenceContext(unitName="FMDB-ejbPU")
    private EntityManager em;

    public void create(VDrugBenefitCommittee vDrugBenefitCommittee) {
        em.persist(vDrugBenefitCommittee);
    }

    public void edit(VDrugBenefitCommittee vDrugBenefitCommittee) {
        em.merge(vDrugBenefitCommittee);
    }

    public void remove(VDrugBenefitCommittee vDrugBenefitCommittee) {
        em.remove(em.merge(vDrugBenefitCommittee));
    }

    public VDrugBenefitCommittee find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.VDrugBenefitCommittee.class, id);
    }

    public List<VDrugBenefitCommittee> findReportDataByCriteria(String reportView) {
        String queryConditions = "";
        
        if(reportView.equals("Active"))
            queryConditions = " o.dbcrecFinalizedDate IS NULL AND (o.dbcTargetDate IS NOT NULL OR o.dbcActualDate IS NOT NULL )" +
                    " AND o.subType NOT IN ('I', 'PM') ORDER BY o.dbcTargetDate";
        else if(reportView.equals("Completed"))
            queryConditions = " o.dbcrecFinalizedDate IS NOT NULL AND (o.dbcTargetDate IS NOT NULL OR o.dbcActualDate IS NOT NULL )" +
                    " AND o.subType NOT IN ('I', 'PM') ORDER BY o.dbcActualDate";
        
        String queryStr = "select o from VDrugBenefitCommittee o WHERE " + queryConditions;
        System.err.println(queryStr);
        Query query = em.createQuery(queryStr);

        List<VDrugBenefitCommittee> result = query.getResultList();
        System.err.println(result.size());
        return result;
    }

 }
