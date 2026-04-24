/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ContactDetailsFacade.java                      *
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

import ca.bc.gov.moh.fmdb.entity.Companies;
import ca.bc.gov.moh.fmdb.entity.ContactDetails;
import java.util.Collection;
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
public class ContactDetailsFacade implements ContactDetailsFacadeLocal {
    @PersistenceContext( unitName="FMDB-ejbPU", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void create(ContactDetails ContactDetails) {
        em.persist(ContactDetails);
    }

    public void edit(ContactDetails ContactDetails) {
        em.merge(ContactDetails);
    }

    public void remove(ContactDetails ContactDetails) {
        em.remove(em.merge(ContactDetails));
    }

    public ContactDetails find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.ContactDetails.class, id);
    }

    public List<ContactDetails> findAll() {
        return em.createQuery("select object(o) from ContactDetails as o").getResultList();
    }

    public ContactDetails findCompanyContactDetails(Companies company) {
        
        Collection<ContactDetails> contacts = company.getContactDetails();
        if(contacts != null){
            for(ContactDetails cd : contacts){
                if(cd.getContactName() == null)
                    return cd;
            }
        }
        return null;
    }

}
