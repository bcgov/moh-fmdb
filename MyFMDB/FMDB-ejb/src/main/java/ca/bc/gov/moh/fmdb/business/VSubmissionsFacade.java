/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VSubmissionsFacade.java                        *
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

import ca.bc.gov.moh.fmdb.entity.VSubmissions;
import java.util.Date;
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
public class VSubmissionsFacade implements VSubmissionsFacadeLocal {
    @PersistenceContext(unitName="FMDB-ejbPU")
    private EntityManager em;

    public void create(VSubmissions vSubmissions) {
        em.persist(vSubmissions);
    }

    public void edit(VSubmissions vSubmissions) {
        em.merge(vSubmissions);
    }

    public void remove(VSubmissions vSubmissions) {
        em.remove(em.merge(vSubmissions));
    }

    public VSubmissions find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.VSubmissions.class, id);
    }

    public List<VSubmissions> findAll() {
        return em.createQuery("select object(o) from VSubmissions as o").getResultList();
    }

    public List<VSubmissions> findSubmissionsByCriteria(String reportView, String sortBy, Date dateFrom, Date dateTo) {
        String queryConditions = "";
        
        if(reportView.equals("Active"))
            queryConditions = " s.decisionDate IS NULL ";
        else if(reportView.equals("Completed"))
                queryConditions = " s.decisionDate between :dateFromParam and :dateToParam ";
        
        
        if (reportView.equals("Completed") && sortBy.equals("Completed by Year"))
            queryConditions = queryConditions + " order by s.decisionDate ";
        if (sortBy.equals("Completed by Type"))    
            queryConditions = queryConditions + " order by s.subType ";
        
        
        //String queryStr = "SELECT s FROM Submission s WHERE s.submissionId is not null";
        String queryStr = "select s from VSubmissions s WHERE " + queryConditions;
        System.err.println(queryStr);
        Query query = em.createQuery(queryStr);
        if(reportView.equals("Completed")){
            query.setParameter("dateFromParam", dateFrom);
            query.setParameter("dateToParam", dateTo);
        }
        
        List<VSubmissions> result = query.getResultList();
        System.err.println(result.size());
        return result;
    }    
}
