/** *****************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VSubmByApplicantFacade.java                    *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 ****************************************************************************** */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.bc.gov.moh.fmdb.business;

import ca.bc.gov.moh.fmdb.entity.VSubmByApplicant;
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
public class VSubmByApplicantFacade implements VSubmByApplicantFacadeLocal {

    @PersistenceContext(unitName = "FMDB-ejbPU")
    private EntityManager em;

    public void create(VSubmByApplicant vSubmByApplicant) {
        em.persist(vSubmByApplicant);
    }

    public void edit(VSubmByApplicant vSubmByApplicant) {
        em.merge(vSubmByApplicant);
    }

    public void remove(VSubmByApplicant vSubmByApplicant) {
        em.remove(em.merge(vSubmByApplicant));
    }

    public VSubmByApplicant find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.VSubmByApplicant.class, id);
    }

    public List<VSubmByApplicant> findReportDataByCriteria(String reportView, String company) {
        String queryConditions = "";

        if (reportView.equals("Active")) {
            queryConditions = " o.decisionDate IS NULL AND o.legalNm LIKE :companyParam";
        } else if (reportView.equals("Completed")) {
            queryConditions = " o.decisionDate IS NOT NULL AND o.legalNm LIKE :companyParam";
        }

        String queryStr = "select o from VSubmByApplicant o WHERE " + queryConditions;
        System.err.println(queryStr);
        Query query = em.createQuery(queryStr);
        query.setParameter("companyParam", "%" + company + "%");

        List<VSubmByApplicant> result = query.getResultList();
        System.err.println(result.size());
        return result;
    }

}
