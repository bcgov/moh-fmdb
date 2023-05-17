/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        BiaRequestedCodesFacade.java                   *
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

import ca.bc.gov.moh.fmdb.Constants;
import ca.bc.gov.moh.fmdb.business.exception.SaveFMDBException;
import ca.bc.gov.moh.fmdb.entity.BiaRequestedCodes;
import ca.bc.gov.moh.fmdb.logging.LogManager;
import ca.bc.gov.moh.fmdb.logging.Logger;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author Chris.Prince
 */
@Stateless
public class BiaRequestedCodesFacade implements BiaRequestedCodesFacadeLocal {

    private Logger logger = LogManager.getLogManager().getLogger(Constants.EJB_LOGGER);
    @PersistenceContext(unitName = "FMDB-ejbPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void create(BiaRequestedCodes BiaRequestedCodes) {
        em.persist(BiaRequestedCodes);
    }

    public void edit(BiaRequestedCodes BiaRequestedCodes) {
        em.merge(BiaRequestedCodes);
    }

    public void remove(BiaRequestedCodes BiaRequestedCodes) {
        em.remove(em.merge(BiaRequestedCodes));
    }

    public BiaRequestedCodes find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.BiaRequestedCodes.class, id);
    }

    /**
     * finds all of the BiaRequestedCodes regardless of the active code. These are used
     * on the admin screen.
     * 
     * @return List<BiaRequestedCodes> - a list of all BiaRequestedCodes
     */
    public List<BiaRequestedCodes> findAll() {
        return em.createQuery("select object(o) from BiaRequestedCodes as o").getResultList();
    }
    
    /**
     * returns a List of all the active BIA codes used on the submission screens.
     * 
     * @return List<BiaRequestedCodes> - a list of active codes
     */
    public List<BiaRequestedCodes> findAllActive() {
    
        String queryStr = "SELECT b FROM BiaRequestedCodes b WHERE b.activeYn = :active" ;   
        Query query = em.createQuery(queryStr);
        query.setParameter("active", 'Y');
        
        List<BiaRequestedCodes> result = query.getResultList();
        
        return result;
        
    }
    
    /**
     * saves or updates a BIA code. If the BIA id is null then it is a new code
     * which requires the new ID to be determined.
     * 
     * @param biaRequestedCodes - BiaRequestedCodes entity containing the code info
     * @throws ca.bc.gov.moh.fmdb.business.exception.SaveFMDBException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveBiaRequestedCodes(BiaRequestedCodes biaRequestedCodes) throws SaveFMDBException {
        if (biaRequestedCodes == null) {
            throw new SaveFMDBException("Cannot save the code since it's NULL.", SaveFMDBException.SaveExceptionType.SUBMISSION_NULL);
        }


        //See if we're trying to create a NEW submission, or update an EXISTING one
        if (biaRequestedCodes.getBiaRequstedCd() == null) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Attempting to create a new code");
            }

            //Get next available id #

            Query query = em.createNamedQuery("BiaRequestedCode.findMaxId");
            List resultQry = query.getResultList();
            String lastNumberUsed = "";
            if (resultQry != null) {
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


            biaRequestedCodes.setBiaRequstedCd(newNumber.toString());
            this.em.persist(biaRequestedCodes);
        } else {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Attempting to save an existing code with id '" +
                        biaRequestedCodes.getBiaRequstedCd() + "'");
            }

            if (this.find(biaRequestedCodes.getBiaRequstedCd()) == null) {
                String msg = "Cannot save changes to the code with id '" +
                        biaRequestedCodes.getBiaRequstedCd() + "' since the code doesn't exist.";

                throw new SaveFMDBException(msg, SaveFMDBException.SaveExceptionType.CODE_NOT_FOUND);
            }

            this.em.merge(biaRequestedCodes);
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
        
        BiaRequestedCodes biaCodes = new BiaRequestedCodes(code);
        String queryStr = "SELECT COUNT(srd.biaRequstedCd) FROM SubmissionReviewDetails srd WHERE srd.biaRequstedCd = :code AND srd.companyNoticeSentDt IS NULL";
        Query query = em.createQuery(queryStr);
        query.setParameter("code", biaCodes);

        List result = query.getResultList();
        
        Long resultLong = (Long)result.get(0);

        return resultLong.longValue();


    }
}
