/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        RegionsFacade.java                             *
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

import ca.bc.gov.moh.fmdb.entity.Regions;
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
public class RegionsFacade implements RegionsFacadeLocal {
    @PersistenceContext( unitName="FMDB-ejbPU", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void create(Regions Regions) {
        em.persist(Regions);
    }

    public void edit(Regions Regions) {
        em.merge(Regions);
    }

    public void remove(Regions Regions) {
        em.remove(em.merge(Regions));
    }

    public Regions find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.Regions.class, id);
    }

    public List<Regions> findAll() {
        return em.createQuery("select object(o) from Regions as o").getResultList();
    }
    
    public Regions findByCode(String code){
        String queryStr = "select object(o) from Regions as o where o.fmdbRegionsPK.regionCd = :code";
        Query query = em.createQuery(queryStr);
        query.setParameter("code", code);
        List list = query.getResultList();
        if(list.size()>0){
            return (Regions)list.get(0);
        }
        else
            return null;
    }
    
    public List<Regions> findByCountry(String code){
        String queryStr = "select object(o) from Regions as o where o.fmdbRegionsPK.countryCd = :code";
        Query query = em.createQuery(queryStr);
        query.setParameter("code", code);
        return query.getResultList();
    }
}
