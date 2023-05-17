/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        EconomicStatusesFacade.java                    *
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

import ca.bc.gov.moh.fmdb.entity.EconomicStatuses;
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
public class EconomicStatusesFacade implements EconomicStatusesFacadeLocal {
    @PersistenceContext( unitName="FMDB-ejbPU", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void create(EconomicStatuses EconomicStatuses) {
        em.persist(EconomicStatuses);
    }

    public void edit(EconomicStatuses EconomicStatuses) {
        em.merge(EconomicStatuses);
    }

    public void remove(EconomicStatuses EconomicStatuses) {
        em.remove(em.merge(EconomicStatuses));
    }

    public EconomicStatuses find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.EconomicStatuses.class, id);
    }

    public List<EconomicStatuses> findAll() {
        return em.createQuery("select object(o) from EconomicStatuses as o").getResultList();
    }

}
