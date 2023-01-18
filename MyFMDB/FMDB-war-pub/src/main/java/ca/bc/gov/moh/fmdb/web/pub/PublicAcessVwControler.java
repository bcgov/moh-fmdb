/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        PublicAcessVwControler.java                    *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.bc.gov.moh.fmdb.web.pub;

import ca.bc.gov.moh.adti.business.custom.Attachment;
import ca.bc.gov.moh.fmdb.web.model.SearchDTO;
import ca.bc.gov.moh.fmdb.web.pub.entity.PublicAccessVw;
import java.util.List;
import javax.persistence.*;

/**
 * @author chris.prince
 */
public class PublicAcessVwControler {

    private  transient EntityManagerFactory emf;

    private EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("FMDB-war-pubPU");
        }

        return emf.createEntityManager();
    }

    public PublicAccessVw[] getPublicAccessVw() {
        PublicAccessVw[] retValue = null;
        
        final EntityManager em = getEntityManager();
       
        try {
           
            final Query q = em.createQuery("select object(c) from PublicAccess as c");
            retValue = (PublicAccessVw[]) q.getResultList().toArray(new PublicAccessVw[0]);
        } finally {
            em.close();
        }
        
        return retValue;
    }
    
    /**
     *  
     *  Gets the attachment associated with the passed submission id
     *  or <code>null</code> if there is no attachment associated with 
     *  that id.
     *  
     *  @param submissionId the submission id
     *  @return the attachment
     *  
     */
    public Attachment getAttachment(Long submissionId) {
        final EntityManager em = getEntityManager();
        Attachment attachment;
        
        try {
            Query query = em.createQuery("SELECT p.letterBin FROM PublicAccess p WHERE p.submissionId = :subId");
            query.setParameter("subId", submissionId);
            
            List results = query.getResultList();
            if(results.size() == 0) {
                attachment = null;
            }
            else {
                attachment = (Attachment) results.get(0);
            }
        }
        finally {
            em.close();
        }
        
        return attachment;
    }
    
    /**
     *  
     *  Performs a search based on the passed criteria.<br/>
     *  NOTE: There is no explicit transaction management here since 
     *  we are only dealing with a READ-ONLY query.
     *  
     *  @param criteria the criteria
     *  @return the results
     *  
     */
    public List<PublicAccessVw> getSearchResults(SearchDTO criteria) {
        List<PublicAccessVw> results = null;
        final EntityManager em = getEntityManager();
        try {
            String queryString = "SELECT p FROM PublicAccess p WHERE ";
            Query query;
            
            if(criteria.isBookmarkSearch()) {
                if(criteria.sortByBrandName()) {
                    queryString += " UPPER(p.tradeNm) LIKE :val ORDER BY p.tradeNm ASC";
                }
                else if(criteria.sortByCompanyName()) {
                    queryString += " UPPER(p.legalNm) LIKE :val ORDER BY p.legalNm ASC";
                }
                else if(criteria.sortByGenericName()) {
                    queryString += " UPPER(p.chemicalNm) LIKE :val ORDER BY p.chemicalNm ASC";
                }
                else if(criteria.sortByIndication()) {
                    queryString += " UPPER(p.medicalIndicationsTxt) LIKE :val ORDER BY p.medicalIndicationsTxt ASC";
                }
                else {
                    //<BUG>: a new values was added to the enum, but not handled by the search function
                    throw new RuntimeException("[1 - BUG] Unsupported " + SearchDTO.SortByCriteria.class.getCanonicalName() + " enum value '" + criteria.getCriteria().name() + "'. Add the check in the search method.");
                }
                
                query = em.createQuery(queryString);
                query.setParameter("val", criteria.getSearchString().toUpperCase() + "%");
            }
            else {
                //Not a bookmark search
                
                String orderByClause;
                
                if(criteria.sortByBrandName()) {
                    orderByClause = " ORDER BY p.tradeNm ASC";
                }
                else if(criteria.sortByCompanyName()) {
                    orderByClause = " ORDER BY p.legalNm ASC";
                }
                else if(criteria.sortByGenericName()) {
                    orderByClause = " ORDER BY p.chemicalNm ASC";
                }
                else if(criteria.sortByIndication()) {
                    orderByClause = " ORDER BY p.medicalIndicationsTxt ASC";
                }
                else {
                    //<BUG>: a new values was added to the enum, but not handled by the search function
                    throw new RuntimeException("[2 - BUG] Unsupported " + SearchDTO.SortByCriteria.class.getCanonicalName() + " enum value '" + criteria.getCriteria().name() + "'. Add the check in the search method.");
                }
                
                queryString += "UPPER(p.tradeNm) LIKE :trade OR UPPER(p.legalNm) LIKE :company OR UPPER(p.chemicalNm) LIKE :chemical OR UPPER(p.medicalIndicationsTxt) LIKE :indication" + orderByClause;
                
                query = em.createQuery(queryString);
                query.setParameter("company", "%" + criteria.getSearchString().toUpperCase() + "%");
                query.setParameter("trade", "%" + criteria.getSearchString().toUpperCase() + "%");
                query.setParameter("chemical", "%" + criteria.getSearchString().toUpperCase() + "%");
                query.setParameter("indication", "%" + criteria.getSearchString().toUpperCase() + "%");
            }
            results = query.getResultList();
            for(PublicAccessVw pav : results){
                try{
                    em.refresh(pav);
                }
                catch(EntityNotFoundException e){
                    //not sure if this will happen
                }
            }
        }
        finally {
            em.close();
        }
        
        return results;
    }
    
}
