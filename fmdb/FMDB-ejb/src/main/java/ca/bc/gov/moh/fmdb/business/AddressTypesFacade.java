/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        AddressTypesFacade.java                        *
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

import ca.bc.gov.moh.fmdb.entity.AddressTypes;
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
public class AddressTypesFacade implements AddressTypesFacadeLocal {
    @PersistenceContext( unitName="FMDB-ejbPU", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void create(AddressTypes AddressTypes) {
        em.persist(AddressTypes);
    }

    public void edit(AddressTypes AddressTypes) {
        em.merge(AddressTypes);
    }

    public void remove(AddressTypes AddressTypes) {
        em.remove(em.merge(AddressTypes));
    }

    public AddressTypes find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.AddressTypes.class, id);
    }

    public List<AddressTypes> findAll() {
        return em.createQuery("select object(o) from AddressTypes as o").getResultList();
    }

}
