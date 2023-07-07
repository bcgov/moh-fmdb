/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VSpecialAuthFormsFacade.java                   *
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

import ca.bc.gov.moh.fmdb.entity.VSpecialAuthForms;
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
public class VSpecialAuthFormsFacade implements VSpecialAuthFormsFacadeLocal {
    @PersistenceContext(unitName="FMDB-ejbPU")
    private EntityManager em;

    public void create(VSpecialAuthForms vSpecialAuthForms) {
        em.persist(vSpecialAuthForms);
    }

    public void edit(VSpecialAuthForms vSpecialAuthForms) {
        em.merge(vSpecialAuthForms);
    }

    public void remove(VSpecialAuthForms vSpecialAuthForms) {
        em.remove(em.merge(vSpecialAuthForms));
    }

    public VSpecialAuthForms find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.VSpecialAuthForms.class, id);
    }

    public List<VSpecialAuthForms> findReportDataByCriteria(String reportView) {
        String queryConditions = "";
        
        if(reportView.equals("Active"))
            queryConditions = " s.saCompletedDate IS NULL AND s.saRequiredYn = 'Y' AND s.subType not in ('I', 'PM')";
        else if(reportView.equals("Completed"))
            queryConditions = " s.saCompletedDate is not null AND s.saRequiredYn = 'Y' AND s.subType not in ('I', 'PM')";
        
        //String queryStr = "SELECT s FROM Submission s WHERE s.submissionId is not null";
        String queryStr = "select s from VSpecialAuthForms s WHERE " + queryConditions;
        System.err.println(queryStr);
        Query query = em.createQuery(queryStr);

        List<VSpecialAuthForms> result = query.getResultList();
        System.err.println(result.size());
        return result;
    }
}
