/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SubmissionReviewDetailsFacade.java             *
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

import ca.bc.gov.moh.fmdb.entity.SubmissionReviewDetails;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author Chris.Prince
 */
@Stateless
public class SubmissionReviewDetailsFacade implements SubmissionReviewDetailsFacadeLocal {
    @PersistenceContext( unitName="FMDB-ejbPU", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void create(SubmissionReviewDetails SubmissionReviewDetails) {
        em.persist(SubmissionReviewDetails);
    }

    public void edit(SubmissionReviewDetails SubmissionReviewDetails) {
        em.merge(SubmissionReviewDetails);
    }

    public void remove(SubmissionReviewDetails SubmissionReviewDetails) {
        em.remove(em.merge(SubmissionReviewDetails));
    }

    public SubmissionReviewDetails find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.SubmissionReviewDetails.class, id);
    }

    public List<SubmissionReviewDetails> findAll() {
        return em.createQuery("select object(o) from SubmissionReviewDetails as o").getResultList();
    }

}
