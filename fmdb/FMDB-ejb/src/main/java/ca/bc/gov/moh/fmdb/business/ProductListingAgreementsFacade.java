/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ProductListingAgreementsFacade.java            *
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

import ca.bc.gov.moh.fmdb.entity.ProductListingAgreement;
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
public class ProductListingAgreementsFacade implements ProductListingAgreementsFacadeLocal {
    @PersistenceContext( unitName="FMDB-ejbPU", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void create(ProductListingAgreement ProductListingAgreement) {
        em.persist(ProductListingAgreement);
    }

    public void edit(ProductListingAgreement ProductListingAgreement) {
        em.merge(ProductListingAgreement);
    }

    public void remove(ProductListingAgreement ProductListingAgreement) {
        em.remove(em.merge(ProductListingAgreement));
    }

    public ProductListingAgreement find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.ProductListingAgreement.class, id);
    }

    public List<ProductListingAgreement> findAll() {
        return em.createQuery("select object(o) from ProductListingAgreement as o").getResultList();
    }

}
