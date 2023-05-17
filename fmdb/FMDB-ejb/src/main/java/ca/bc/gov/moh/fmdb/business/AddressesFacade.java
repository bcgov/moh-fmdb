/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        AddressesFacade.java                           *
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

import ca.bc.gov.moh.fmdb.entity.Addresses;
import ca.bc.gov.moh.fmdb.entity.Companies;
import java.util.Collection;
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
public class AddressesFacade implements AddressesFacadeLocal {
    @PersistenceContext( unitName="FMDB-ejbPU", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void create(Addresses Addresses) {
        em.persist(Addresses);
    }

    public void edit(Addresses Addresses) {
        em.merge(Addresses);
    }

    public void remove(Addresses Addresses) {
        em.remove(em.merge(Addresses));
    }

    public Addresses find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.Addresses.class, id);
    }

    public List<Addresses> findAll() {
        return em.createQuery("select object(o) from Addresses as o").getResultList();
    }
    
    public Addresses findByCompany(Long companyId){
        String queryStr = "select object(o) from Addresses as o where o.companyId.companyId = :companyId";
        queryStr += " and o.contactNameId is null";
        Query query = em.createQuery(queryStr);
        query.setParameter("companyId", companyId);
        List list = query.getResultList();
        if(list.size()>0)
            return (Addresses)list.get(0);
        else
            return null;
    }
    
    public Addresses findCompanyAddress(Companies c){
        Collection<Addresses> addrs = c.getFmdbAddressesCollection();
        if(addrs != null){
            for(Addresses addr : addrs){
                if(addr.getContactName() == null)
                    return addr;
            }
        }
        return null;
    }
    
}
