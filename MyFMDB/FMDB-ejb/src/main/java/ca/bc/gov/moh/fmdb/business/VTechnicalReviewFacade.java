/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VTechnicalReviewFacade.java                    *
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

import ca.bc.gov.moh.fmdb.entity.VTechnicalReview;
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
public class VTechnicalReviewFacade implements VTechnicalReviewFacadeLocal {
    @PersistenceContext(unitName="FMDB-ejbPU")
    private EntityManager em;

    public void create(VTechnicalReview vTechnicalReview) {
        em.persist(vTechnicalReview);
    }

    public void edit(VTechnicalReview vTechnicalReview) {
        em.merge(vTechnicalReview);
    }

    public void remove(VTechnicalReview vTechnicalReview) {
        em.remove(em.merge(vTechnicalReview));
    }

    public VTechnicalReview find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.VTechnicalReview.class, id);
    }

    public List<VTechnicalReview> findReportDataByCriteria(String reportView) {
        String queryConditions = "";
        
        if(reportView.equals("Active"))
            queryConditions = " o.reviewCompletionDate IS NULL AND o.requestedYn = 'Y' AND o.subType NOT IN ('I', 'PM')";
        else if(reportView.equals("Completed"))
            queryConditions = " o.reviewCompletionDate IS NOT NULL AND o.requestedYn = 'Y' AND o.subType NOT IN ('I', 'PM')";
        
        String queryStr = "select o from VTechnicalReview o WHERE " + queryConditions;
        System.err.println(queryStr);
        Query query = em.createQuery(queryStr);

        List<VTechnicalReview> result = query.getResultList();
        System.err.println(result.size());
        return result;
    }

 /*
    public List<VTechnicalReview> findSubmissionByCriteria(String reportView, String sortBy, Date dateFrom, Date dateTo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
 */
}
