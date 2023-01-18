/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ReviewStatusesFacade.java                      *
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
import ca.bc.gov.moh.fmdb.entity.ReviewStatuses; 
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
public class ReviewStatusesFacade implements ReviewStatusesFacadeLocal {
    @PersistenceContext( unitName="FMDB-ejbPU", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;
    private Logger logger = LogManager.getLogManager().getLogger(Constants.EJB_LOGGER);
    
    public void create(ReviewStatuses ReviewStatuses) {
        em.persist(ReviewStatuses);
    }

    public void edit(ReviewStatuses ReviewStatuses) {
        em.merge(ReviewStatuses);
    }

    public void remove(ReviewStatuses ReviewStatuses) {
        em.remove(em.merge(ReviewStatuses));
    }

    public ReviewStatuses find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.ReviewStatuses.class, id);
    }
    
    /**
     * finds all of the ReviewStatuses regardless of the active code. These are used
     * on the admin screen.
     * 
     * @return List<ReviewStatuses> - a list of all ReviewStatuses
     */
    public List<ReviewStatuses> findAll() {
        return em.createQuery("select object(o) from ReviewStatuses as o").getResultList();
    }
    
    /**
     * finds all of the active ReviewStatuses which are set to active. 
     * 
     * @return List<ReviewStatuses> - a list of active ReviewStatuses
     */
    public List<ReviewStatuses> findAllActive() {
        
        String queryStr = "SELECT r FROM ReviewStatuses r WHERE r.activeYn = :active" ;   
        Query query = em.createQuery(queryStr);
        query.setParameter("active", "Y");
        
        List<ReviewStatuses> result = query.getResultList();
        
        return result;
        
    }
    
    /**
     * saves or updates a ReviewStatuses code. If the id is null then it is a new code
     * which requires the new ID to be determined.
     * 
     * @param reviewStatusCodes - ReviewStatuses entity containing the code info
     * @throws ca.bc.gov.moh.fmdb.business.exception.SaveFMDBException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveReviewStatusCodes(ReviewStatuses reviewStatusCodes) throws SaveFMDBException {
        if (reviewStatusCodes == null) {
            throw new SaveFMDBException("Cannot save the code since it's NULL.", SaveFMDBException.SaveExceptionType.SUBMISSION_NULL);
        }


        //See if we're trying to create a NEW submission, or update an EXISTING one
        if (reviewStatusCodes.getReviewStatusCd() == null) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Attempting to create a new code");
            }

            //Get next available id #

            Query query = em.createNamedQuery("ReviewStatuses.findMaxId");
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


            reviewStatusCodes.setReviewStatusCd(newNumber.toString());
            this.em.persist(reviewStatusCodes);
        } else {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Attempting to save an existing code with id '" +
                        reviewStatusCodes.getReviewStatusCd() + "'");
            }

            if (this.find(reviewStatusCodes.getReviewStatusCd()) == null) {
                String msg = "Cannot save changes to the code with id '" +
                        reviewStatusCodes.getReviewStatusCd() + "' since the code doesn't exist.";

                throw new SaveFMDBException(msg, SaveFMDBException.SaveExceptionType.CODE_NOT_FOUND);
            }
            
            this.em.merge(reviewStatusCodes);
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
        
        String queryStr = "SELECT COUNT(s.submissionId) FROM Submission s, SubmissionReviewDetails srd, ReviewStatuses rs WHERE (srd.reviewStatusCd = rs.reviewStatusCd) AND (s.submissionReviewDetails = srd.submissionReviewId) AND (rs.reviewStatusCd = :code)";
        Query query = em.createQuery(queryStr);
        query.setParameter("code", code);

        List result = query.getResultList();
        
        Long resultLong = (Long)result.get(0);

        return resultLong.longValue();


    }
    
}
