/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ReviewerNameTypesFacade.java                   *
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
import ca.bc.gov.moh.fmdb.entity.ReviewerNameTypes;
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
public class ReviewerNameTypesFacade implements ReviewerNameTypesFacadeLocal {
    @PersistenceContext( unitName="FMDB-ejbPU", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;
    private Logger logger = LogManager.getLogManager().getLogger(Constants.EJB_LOGGER);
    
    public void create(ReviewerNameTypes ReviewerNameTypes) {
        em.persist(ReviewerNameTypes);
    }

    public void edit(ReviewerNameTypes ReviewerNameTypes) {
        em.merge(ReviewerNameTypes);
    }

    public void remove(ReviewerNameTypes ReviewerNameTypes) {
        em.remove(em.merge(ReviewerNameTypes));
    }

    public ReviewerNameTypes find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.ReviewerNameTypes.class, id);
    }
    
    /**
     * finds all of the ReviewerNameTypes regardless of the active code. These are used
     * on the admin screen.
     * 
     * @return List<ReviewerNameTypes> - a list of all ReviewerNameTypes
     */
    public List<ReviewerNameTypes> findAll() {
        return em.createQuery("select object(o) from ReviewerNameTypes as o").getResultList();
    }
    
     /**
     * finds all of the active ReviewerNameTypes which are set to active. 
     * 
     * @return List<ReviewerNameTypes> - a list of active ReviewerNameTypes
     */
    public List<ReviewerNameTypes> findAllActive() {
        
        String queryStr = "SELECT r FROM ReviewerNameTypes r WHERE r.activeYn = :active" ;   
        Query query = em.createQuery(queryStr);
        query.setParameter("active", 'Y');
        
        List<ReviewerNameTypes> result = query.getResultList();
        
        return result;
        
    }
    
    /**
     * saves or updates a ReviewerNameTypes code. If the id is null then it is a new code
     * which requires the new ID to be determined.
     * 
     * @param reviewerTypes - ReviewerNameTypes entity containing the code info
     * @throws ca.bc.gov.moh.fmdb.business.exception.SaveFMDBException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveReviewerTypes(ReviewerNameTypes reviewerTypes) throws SaveFMDBException {
        if (reviewerTypes == null) {
            throw new SaveFMDBException("Cannot save the code since it's NULL.", SaveFMDBException.SaveExceptionType.SUBMISSION_NULL);
        }


        //See if we're trying to create a NEW submission, or update an EXISTING one
        if (reviewerTypes.getReviewerNameCd() == null) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Attempting to create a new code");
            }

            //Get next available id #

            Query query = em.createNamedQuery("ReviewerNameTypes.findMaxId");
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


            reviewerTypes.setReviewerNameCd(newNumber.toString());
            this.em.persist(reviewerTypes);
        } else {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Attempting to save an existing code with id '" +
                        reviewerTypes.getReviewerNameCd() + "'");
            }

            if (this.find(reviewerTypes.getReviewerNameCd()) == null) {
                String msg = "Cannot save changes to the code with id '" +
                        reviewerTypes.getReviewerNameCd() + "' since the code doesn't exist.";

                throw new SaveFMDBException(msg, SaveFMDBException.SaveExceptionType.CODE_NOT_FOUND);
            }

            this.em.merge(reviewerTypes);
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
        
        String queryStr = "SELECT COUNT(s.submissionId) " +
                          "FROM Submission s, SubmissionReviewDetails srd, ReviewQuestions rq, ReviewerNameTypes rnt " +
                          "WHERE (srd.fmdbReviewQuestionsCollection = rq.reviewQuestionId) " +
                          "AND (s.submissionReviewDetails = srd.submissionReviewId) " +
                          "AND (rq.reviewerNameCd = rnt.reviewerNameCd) " + 
                          "AND (rnt.reviewerNameCd = :code)";
        
        Query query = em.createQuery(queryStr);
        
        query.setParameter("code", code);

        List result = query.getResultList();
        
        Long resultLong = (Long)result.get(0);

        return resultLong.longValue();


    }

}
