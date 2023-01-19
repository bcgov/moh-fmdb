/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ContactTypesFacade.java                        *
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

import ca.bc.gov.moh.fmdb.entity.ContactTypes;
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
public class ContactTypesFacade implements ContactTypesFacadeLocal {
    @PersistenceContext( unitName="FMDB-ejbPU", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void create(ContactTypes ContactTypes) {
        em.persist(ContactTypes);
    }

    public void edit(ContactTypes ContactTypes) {
        em.merge(ContactTypes);
    }

    public void remove(ContactTypes ContactTypes) {
        em.remove(em.merge(ContactTypes));
    }

    public ContactTypes find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.ContactTypes.class, id);
    }

    public List<ContactTypes> findAll() {
        return em.createQuery("select object(o) from ContactTypes as o").getResultList();
    }

}
