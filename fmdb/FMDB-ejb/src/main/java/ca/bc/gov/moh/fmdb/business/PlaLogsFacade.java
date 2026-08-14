/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        PlaLogsFacade.java                             *
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

import ca.bc.gov.moh.fmdb.entity.PlaLogs;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;

/**
 *
 * @author Chris.Prince
 */
@Stateless
public class PlaLogsFacade implements PlaLogsFacadeLocal {
    @PersistenceContext( unitName="FMDB-ejbPU", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void create(PlaLogs PlaLogs) {
        em.persist(PlaLogs);
    }

    public void edit(PlaLogs PlaLogs) {
        em.merge(PlaLogs);
    }

    public void remove(PlaLogs PlaLogs) {
        em.remove(em.merge(PlaLogs));
    }

    public PlaLogs find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.PlaLogs.class, id);
    }

    public List<PlaLogs> findAll() {
        return em.createQuery("select object(o) from PlaLogs as o").getResultList();
    }

}
