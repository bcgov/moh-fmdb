/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        CountriesFacade.java                           *
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

import ca.bc.gov.moh.fmdb.entity.Countries;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author Chris.Prince
 */
@Stateless
public class CountriesFacade implements CountriesFacadeLocal {

    @PersistenceContext(unitName = "FMDB-ejbPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void create(Countries Countries) {
        em.persist(Countries);
    }

    public void edit(Countries Countries) {
        em.merge(Countries);
    }

    public void remove(Countries Countries) {
        em.remove(em.merge(Countries));
    }

    public Countries find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.Countries.class, id);
    }

    public List<Countries> findAll() {
        return em.createQuery("select object(o) from Countries as o").getResultList();
    }

    public Countries findByCountry(String country) {
        String queryStr = "select object(o) from Countries as o where o.countryDsc = :country";
        Query query = em.createQuery(queryStr);
        query.setParameter("country", country);
        List list = query.getResultList();
        if (list.size() > 0) {
            return (Countries) list.get(0);
        } else {
            return null;
        }
    }
}
