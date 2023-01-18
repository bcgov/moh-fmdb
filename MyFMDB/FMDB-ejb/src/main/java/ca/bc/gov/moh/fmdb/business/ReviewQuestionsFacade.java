/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ReviewQuestionsFacade.java                     *
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

import ca.bc.gov.moh.fmdb.entity.ReviewQuestions;
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
public class ReviewQuestionsFacade implements ReviewQuestionsFacadeLocal {
    @PersistenceContext( unitName="FMDB-ejbPU", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void create(ReviewQuestions ReviewQuestions) {
        em.persist(ReviewQuestions);
    }

    public void edit(ReviewQuestions ReviewQuestions) {
        em.merge(ReviewQuestions);
    }

    public void remove(ReviewQuestions ReviewQuestions) {
        em.remove(em.merge(ReviewQuestions));
    }

    public ReviewQuestions find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.ReviewQuestions.class, id);
    }

    public List<ReviewQuestions> findAll() {
        return em.createQuery("select object(o) from ReviewQuestions as o").getResultList();
    }

}
