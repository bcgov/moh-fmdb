/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        CompaniesFacade.java                           *
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

import ca.bc.gov.moh.fmdb.business.exception.ItemNotFoundFMDBException;
import ca.bc.gov.moh.fmdb.entity.Addresses;
import ca.bc.gov.moh.fmdb.entity.Companies;
import ca.bc.gov.moh.fmdb.entity.CompanyTypes;
import ca.bc.gov.moh.fmdb.entity.ContactDetails;
import ca.bc.gov.moh.fmdb.entity.ContactNames;
import ca.bc.gov.moh.fmdb.entity.Regions;
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
public class CompaniesFacade implements CompaniesFacadeLocal {
    @PersistenceContext( unitName="FMDB-ejbPU", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void create(Companies companies) {
        Collection<Addresses> addresses = companies.getFmdbAddressesCollection();
        for (Addresses address : addresses) {
            address.setFmdbRegions(em.find(Regions.class,address.getFmdbRegions().getFmdbRegionsPK()));
        }
        em.persist(companies);
    }

    public void edit(Companies companies) {
        Collection<Addresses> addresses = companies.getFmdbAddressesCollection();
        for (Addresses address : addresses) {
            //The EntityManager is not persisting changes to any of the dropdown values that are tied to code tables
            address.setFmdbRegions(em.find(Regions.class,address.getFmdbRegions().getFmdbRegionsPK()));
            if (address.getAddressId()==null){
                em.persist(address);
            }
        }   
        Collection<ContactNames> contacts = companies.getFmdbContactNamesCollection();
        for (ContactNames contactNames : contacts) {
            if (contactNames.getContactNameId()==null){
                contactNames.getAddress().setFmdbRegions(em.find(Regions.class,contactNames.getAddress().getFmdbRegions().getFmdbRegionsPK()));
                em.persist(contactNames);
            }
        }
        Collection<ContactDetails> details = companies.getContactDetails();
        for (ContactDetails contactDetails : details) {
            if (contactDetails.getContactDtlId()==null){
                contactDetails.setCompany(companies);
                em.persist(contactDetails);
            }
        }
        
        //The EntityManager is not persisting changes to any of the dropdown values that are tied to code tables
        companies.setCompanyTypeCd(em.find(CompanyTypes.class,companies.getCompanyTypeCd().getCompanyTypeCd()));
        
        em.merge(companies);
    }

    public void remove(Companies Companies) {
        em.remove(em.merge(Companies));
    }

    public Companies find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.Companies.class, id);
    }

    public List<Companies> findAll() {
        return em.createQuery("select object(o) from Companies as o").getResultList();
    }
    
    public List<Companies> searchCompanies(String companyName) {
        String queryStr = "SELECT c FROM Companies c WHERE UPPER(c.legalNm) LIKE :companyName order by c.legalNm";
        
        Query query = em.createQuery(queryStr);
        query.setParameter("companyName", companyName == null ? "%" : "%" + companyName.trim().toUpperCase()+ "%");
        
        List<Companies> result = query.getResultList();
        System.out.println("CompaniesFacade: result = " + result.size());
        
        return result;
    }
    
    public Companies findCompanyById(Long companyId) throws ItemNotFoundFMDBException {
        
        String queryStr = "SELECT c FROM Companies c WHERE c.companyId = :companyId";
        Query query = em.createQuery(queryStr);
        query.setParameter("companyId", companyId);
        
        List<Companies> resultQry = query.getResultList();
        if(resultQry.size() == 0) {
            throw new ItemNotFoundFMDBException("The company with ID '" + companyId + "' could not be found.", ItemNotFoundFMDBException.ItemNotFoundExceptionType.SUBMISSION);
        }
        
        em.refresh(resultQry.get(0));
        return resultQry.get(0);
    }
}
