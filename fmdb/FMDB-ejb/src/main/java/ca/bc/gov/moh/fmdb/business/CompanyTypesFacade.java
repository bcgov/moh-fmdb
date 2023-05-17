/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        CompanyTypesFacade.java                        *
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

import ca.bc.gov.moh.fmdb.business.exception.SaveFMDBException;
import ca.bc.gov.moh.fmdb.entity.CompanyTypes;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.ejb.TransactionAttributeType;
import ca.bc.gov.moh.fmdb.logging.Logger;
import ca.bc.gov.moh.fmdb.logging.LogManager;
import ca.bc.gov.moh.fmdb.Constants;

/**
 *
 * @author Chris.Prince
 */
@Stateless
public class CompanyTypesFacade implements CompanyTypesFacadeLocal {
    
    private Logger logger = LogManager.getLogManager().getLogger(Constants.EJB_LOGGER);
    
    @PersistenceContext( unitName="FMDB-ejbPU", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void create(CompanyTypes CompanyTypes) {
        em.persist(CompanyTypes);
    }

    public void edit(CompanyTypes CompanyTypes) {
        em.merge(CompanyTypes);
    }

    public void remove(CompanyTypes CompanyTypes) {
        em.remove(em.merge(CompanyTypes));
    }

    public CompanyTypes find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.CompanyTypes.class, id);
    }
    
    /**
     * finds all of the CompanyTypes regardless of the active code. These are used
     * on the admin screen.
     * 
     * @return List<CompanyTypes> - a list of all CompanyTypes
     */
    public List<CompanyTypes> findAll() {
        return em.createQuery("select object(o) from CompanyTypes as o").getResultList();
    }
    
    /**
     * finds all of the active CompanyTypes which are set to active. 
     * 
     * @return List<CompanyTypes> - a list of active CompanyTypes
     */
    public List<CompanyTypes> findAllActive() {
    
        String queryStr = "SELECT c FROM CompanyTypes c WHERE c.activeYn = :active" ;   
        Query query = em.createQuery(queryStr);
        query.setParameter("active", 'Y');
        
        List<CompanyTypes> result = query.getResultList();
        
        return result;
        
    }
    
    /**
     * saves or updates a CompanyTypes code. If the id is null then it is a new code
     * which requires the new ID to be determined.
     * 
     * @param companyTypeCodes - CompanyTypes entity containing the code info
     * @throws ca.bc.gov.moh.fmdb.business.exception.SaveFMDBException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveCompanyTypeCodes(CompanyTypes companyTypeCodes) throws SaveFMDBException {
        if (companyTypeCodes == null) {
            throw new SaveFMDBException("Cannot save the code since it's NULL.", SaveFMDBException.SaveExceptionType.SUBMISSION_NULL);
        }


        //See if we're trying to create a NEW submission, or update an EXISTING one
        if (companyTypeCodes.getCompanyTypeCd() == null) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Attempting to create a new code");
            }

            //Get next available id #

            Query query = em.createNamedQuery("CompanyTypes.findMaxId");
            List resultQry = query.getResultList();
            String lastNumberUsed = "";
            if (resultQry != null) 
            {
                if (resultQry.get(0) instanceof List)
                {
                    List lastNumberUsedList = (List) resultQry.get(0);
                    lastNumberUsed = lastNumberUsedList.get(0).toString();
                }
                else
                {
                    lastNumberUsed = resultQry.get(0).toString();
                }
            }

            Long newNumber = new Long(lastNumberUsed);
            newNumber++;

            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Next available ID = '" + newNumber + "'.");
            }


            companyTypeCodes.setCompanyTypeCd(newNumber.toString());
            this.em.persist(companyTypeCodes);
        } else {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Attempting to save an existing code with id '" +
                        companyTypeCodes.getCompanyTypeCd() + "'");
            }

            if (this.find(companyTypeCodes.getCompanyTypeCd()) == null) {
                String msg = "Cannot save changes to the code with id '" +
                        companyTypeCodes.getCompanyTypeCd() + "' since the code doesn't exist.";

                throw new SaveFMDBException(msg, SaveFMDBException.SaveExceptionType.CODE_NOT_FOUND);
            }
            
            this.em.merge(companyTypeCodes);
        }
    }
    
    /**
     * when a user de-activates a code this method is executed to determine how many submissions are
     * using that code.
     * 
     * @param code - the code which is being set to inactive.
     * @return long - a count of Submissions.
     */
    public long findAssociatedSubmissions(String code) {
        
        //String queryStr = "SELECT COUNT(srd.biaRequstedCd) FROM CompanyTypes c WHERE srd.biaRequstedCd = :code AND srd.companyNoticeSentDt IS NULL";
        String queryStr = "SELECT COUNT(s.submissionId) FROM Submission s, Companies c, CompanyTypes ct WHERE (c.companyTypeCd = ct.companyTypeCd) AND (s.companyId = c.companyId) AND (ct.companyTypeCd = :code)";
        
        Query query = em.createQuery(queryStr);
        query.setParameter("code", code);

        List result = query.getResultList();
        long resultsize = result.size();
        Long resultLong = (Long)result.get(0);

        return resultLong.longValue();


    }
}
