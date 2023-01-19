/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ContactNamesFacade.java                        *
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

import ca.bc.gov.moh.fmdb.entity.ContactNames;
import ca.bc.gov.moh.fmdb.entity.Regions;
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
public class ContactNamesFacade implements ContactNamesFacadeLocal {
    @PersistenceContext( unitName="FMDB-ejbPU", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void create(ContactNames ContactNames) {
        ContactNames.getAddress().setFmdbRegions(em.find(Regions.class,ContactNames.getAddress().getFmdbRegions().getFmdbRegionsPK()));
        em.persist(ContactNames);
    }

    public void edit(ContactNames contactNames) {
        contactNames.getAddress().setFmdbRegions(em.find(Regions.class,contactNames.getAddress().getFmdbRegions().getFmdbRegionsPK()));
        em.merge(contactNames);
    }

    public void remove(ContactNames contact) {
        contact.getAddress().setFmdbRegions(null);
        em.remove(em.merge(contact));
    }

    public ContactNames find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.ContactNames.class, id);
    }

    public List<ContactNames> findAll() {
        return em.createQuery("select object(o) from ContactNames as o").getResultList();
    }

}
