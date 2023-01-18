/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VSingleDrugSubStatusFacade.java                *
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

import ca.bc.gov.moh.fmdb.entity.VDins;
import ca.bc.gov.moh.fmdb.entity.VReviewQuestions;
import ca.bc.gov.moh.fmdb.entity.VSingleDrugSubStatus;
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
public class VSingleDrugSubStatusFacade implements VSingleDrugSubStatusFacadeLocal {
    @PersistenceContext(unitName="FMDB-ejbPU")
    private EntityManager em;

    public void create(VSingleDrugSubStatus vSingleDrugSubStatus) {
        em.persist(vSingleDrugSubStatus);
    }

    public void edit(VSingleDrugSubStatus vSingleDrugSubStatus) {
        em.merge(vSingleDrugSubStatus);
    }

    public void remove(VSingleDrugSubStatus vSingleDrugSubStatus) {
        em.remove(em.merge(vSingleDrugSubStatus));
    }

    public VSingleDrugSubStatus find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.VSingleDrugSubStatus.class, id);
    }

    public List<VSingleDrugSubStatus> findReportDataByCriteria(String chemicalName) {
        
        String queryStr = "select o from VSingleDrugSubStatus o WHERE o.chemicalName like :chemicalNameParam " ;
        System.err.println(queryStr);
        Query query = em.createQuery(queryStr);
        
        query.setParameter("chemicalNameParam", chemicalName + "%");
        List<VSingleDrugSubStatus> result = query.getResultList();
        System.err.println(result.size());
        return result;
    }

    public List<VDins> findDinsData(String chemicalName) {
        
        String queryStr = "select o from VDins o, VChemicals cc where o.chemicalId = cc.chemicalId and cc.chemicalNm like :chemicalNameParam " ;
        System.err.println(queryStr);
        Query query = em.createQuery(queryStr);
        
        query.setParameter("chemicalNameParam", chemicalName + "%");
        List<VDins> result = query.getResultList();
        System.err.println(result.size());
        return result;
    }
    
    public List<VReviewQuestions> findReviewQuestionsData(String chemicalName) {
        
        String queryStr = "select o from VReviewQuestions o, VChemicals cc, VSubmissionReviewDetails srd where o.submissionReviewId = srd.submissionReviewId and cc.submissionId = srd.submissionId and  cc.chemicalNm like :chemicalNameParam " ;
        System.err.println(queryStr);
        Query query = em.createQuery(queryStr);
        
        query.setParameter("chemicalNameParam", chemicalName + "%");
        List<VReviewQuestions> result = query.getResultList();
        System.err.println(result.size());
        return result;
    }
}
