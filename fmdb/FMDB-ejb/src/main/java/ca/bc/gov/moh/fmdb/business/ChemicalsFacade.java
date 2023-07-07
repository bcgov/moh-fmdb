/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ChemicalsFacade.java                           *
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

import ca.bc.gov.moh.fmdb.entity.Chemicals;
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
public class ChemicalsFacade implements ChemicalsFacadeLocal {
    @PersistenceContext( unitName="FMDB-ejbPU", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void create(Chemicals Chemicals) {
        em.persist(Chemicals);
    }

    public void edit(Chemicals Chemicals) {
        em.merge(Chemicals);
    }

    public void remove(Chemicals Chemicals) {
        em.remove(em.merge(Chemicals));
    }

    public Chemicals find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.Chemicals.class, id);
    }

    public List<Chemicals> findAll() {
        return em.createQuery("select object(o) from Chemicals as o").getResultList();
    }

}
