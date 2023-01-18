/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SubmissionFacade.java                          *
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
import ca.bc.gov.moh.fmdb.business.exception.ItemNotFoundFMDBException;
import ca.bc.gov.moh.fmdb.business.exception.SaveFMDBException;
import ca.bc.gov.moh.fmdb.entity.*;
import ca.bc.gov.moh.fmdb.logging.LogManager;
import ca.bc.gov.moh.fmdb.logging.Logger;
import ca.bc.gov.moh.fmdb.model.SearchDTO;
import ca.bc.gov.moh.fmdb.model.SearchResultDTO;
import java.util.Collection;
import java.util.Date;
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
public class SubmissionFacade implements SubmissionFacadeLocal {
    private Logger logger = LogManager.getLogManager().getLogger(Constants.EJB_LOGGER);
    
    @PersistenceContext( unitName="FMDB-ejbPU", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void create(Submission Submission) {
        em.persist(Submission);
    }

    public void edit(Submission Submission) {
        em.merge(Submission);
    }

    public void remove(Submission Submission) {
        em.remove(em.merge(Submission));
    }

    public Submission find(Object id) {
        return em.find(ca.bc.gov.moh.fmdb.entity.Submission.class, id);
    }

    public List<Submission> findAll() {
        return em.createQuery("select object(o) from Submission as o").getResultList();
    }
    
    
    
    public Submission findSubmissionById(long submissionNumber) throws ItemNotFoundFMDBException {
        String queryStr = "SELECT s FROM Submission s WHERE s.submissionNo = :submissionNumber" ;   
        Query query = em.createQuery(queryStr);
        query.setParameter("submissionNumber", submissionNumber);
        
        List<Submission> result = query.getResultList();
        if(result.size() == 0) {
            throw new ItemNotFoundFMDBException("The submission with number '" + submissionNumber + "' could not be found.", ItemNotFoundFMDBException.ItemNotFoundExceptionType.SUBMISSION);
        }
        
        Submission submission = result.get(0);
        //Touched by Perkins on August 25th 2010.
        submission.getFmdbChemicals().getFmdbDinsCollection();
        submission.getFmdbSubmissionReviewDetails().getFmdbReviewQuestionsCollection();

        return submission;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveSubmission(Submission submission) throws SaveFMDBException {
        if(submission == null) {
            throw new SaveFMDBException("Cannot save the submission since it's NULL.", SaveFMDBException.SaveExceptionType.SUBMISSION_NULL);
        }
        
        
         //See if we're trying to create a NEW submission, or update an EXISTING one
        if(submission.getSubmissionId() == null) {
            if(this.logger.isDebugEnabled()) {
                this.logger.debug("Attempting to create a new submission");
            }

            //Get next available submission #
            //NOTE: May want to consider using a different method here, as it's possible
            //      that two concurrent saves will result in the same submission # being used.
            //      Once method would be to have a table for generating the numbers, similar
            //      to sequence tables.
            Query query = em.createQuery("SELECT MAX(s.submissionNo) FROM Submission s");
            long lastSubmissionNumberUsed = (Long)query.getSingleResult();
            long newSubmissionNumber = lastSubmissionNumberUsed + 1;

            if(this.logger.isDebugEnabled()) {
                this.logger.debug("Next available SubmissionNumber = '" + newSubmissionNumber + "'.");
            }

            submission.setSubmissionNo(newSubmissionNumber);
            
            this.em.persist(submission);
        }
        else {
            if(this.logger.isDebugEnabled()) {
                this.logger.debug("Attempting to save an existing submission with id '" + 
                                submission.getSubmissionId() + "' and number '" + 
                                submission.getSubmissionNo() + "'");
            }
            
            //Does the submission exist?  It may have been deleted.
            //NOTE: May want to consider using pessimistic locking for submission edits.
            //However, this would still not prevent deletions occuring externally from this system.
            if( this.find(submission.getSubmissionId()) == null ) {
                String msg =    "Cannot save changes to the submission with id '" + 
                                submission.getSubmissionId() + "' and number '" + 
                                submission.getSubmissionNo() + "' since the submission doesn't exist.";

                throw new SaveFMDBException(msg, SaveFMDBException.SaveExceptionType.SUBMISSION_NOT_FOUND);
            }

            //This section of if statemnends and loops persists new portions of an existing agreement, such as DINS, PLALogs and so on,
            // because for whatever reason the entity manager is not up to the task.
            if (submission.getFmdbProductListingAgreement().getPlaId()==null){
                em.persist(submission.getFmdbProductListingAgreement());
            }

            Collection<Dins> dins = submission.getFmdbChemicals().getFmdbDinsCollection();
            for (Dins aDin : dins) {
                if (aDin.getCreatedOnDtm() == null) {
                    this.em.persist(aDin);
                }                
            }

            Collection<ReviewQuestions> questions = submission.getFmdbSubmissionReviewDetails().getFmdbReviewQuestionsCollection();
            for (ReviewQuestions reviewQuestion : questions) {
                if (reviewQuestion.getCreatedByNm() == null) {
                    this.em.persist(reviewQuestion);
                }                
            }

            Collection<PlaLogs> logs = submission.getFmdbProductListingAgreement().getFmdbPlaLogsCollection();
            for (PlaLogs aLog : logs) {
                if (aLog.getCreatedByNm()==null) {
                    this.em.persist(aLog);
                }
            }

            //The EntityManager is not persisting changes to any of the dropdown values that are tied to code tables
            if (submission.getSubmissionTypeCd() != null) {
                submission.setSubmissionTypeCd(this.em.find(SubmissionTypes.class, submission.getSubmissionTypeCd().getSubmissionTypeCd()));
            }
            
            if (submission.getFmdbSubmissionReviewDetails().getReviewStatusCd() != null) { 
                submission.getFmdbSubmissionReviewDetails().setReviewStatusCd(this.em.find(ReviewStatuses.class, submission.getFmdbSubmissionReviewDetails().getReviewStatusCd().getReviewStatusCd()));
            }
            
            if (submission.getFmdbSubmissionReviewDetails().getBiaRequstedCd() != null) { 
                submission.getFmdbSubmissionReviewDetails().setBiaRequstedCd(this.em.find(BiaRequestedCodes.class, submission.getFmdbSubmissionReviewDetails().getBiaRequstedCd().getBiaRequstedCd()));
            }
            
            if (submission.getFmdbProductListingAgreement().getPlaStatusCd() != null) { 
                submission.getFmdbProductListingAgreement().setPlaStatusCd(this.em.find(PlaStatusTypes.class, submission.getFmdbProductListingAgreement().getPlaStatusCd().getPlaStatusCd()));
            }
            
            this.em.merge(submission);
        }
    }
    
    public List<SearchResultDTO> searchSubmission(SearchDTO searchDTO) {
        StringBuilder queryBuilder = new StringBuilder();
        boolean subNoANDED = false, subTypeANDED = false, companyANDED = false, chemNameANDED = false, chemTradeANDED = false;
        
        queryBuilder.append("SELECT new ca.bc.gov.moh.fmdb.model.SearchResultDTOImpl(s.submissionNo, s.receivedDt, t.submissionTypeDsc, c.legalNm, chem.chemicalNm, chem.tradeNm) ");
        queryBuilder.append("FROM Submission s LEFT JOIN s.submissionTypeCd t LEFT JOIN s.companyId c LEFT JOIN s.chemical chem ");
        queryBuilder.append("WHERE ");
        
        if(searchDTO.getSubmissionNumber() != null) {
            subNoANDED = true;
            queryBuilder.append("s.submissionNo = :submissionNumber AND ");
        }
        if(searchDTO.getSubmissionType() != null  &&  !searchDTO.getSubmissionType().equals("")) {
            subTypeANDED = true;
            queryBuilder.append("s.submissionTypeCd.submissionTypeCd = :submissionType AND ");
        }
        if(searchDTO.getCompanyName() != null  &&  !searchDTO.getCompanyName().equals("")) {
            companyANDED = true;
            queryBuilder.append("UPPER(s.companyId.legalNm) LIKE :companyName AND ");
        }
        if(searchDTO.getChemicalName() != null  &&  !searchDTO.getChemicalName().equals("")) {
            chemNameANDED = true;
            //queryBuilder.append("chem.chemicalNm = :chemicalName AND ");
            queryBuilder.append("UPPER(chem.chemicalNm) LIKE :chemicalName AND ");
        }
        if(searchDTO.getTradeName() != null  &&  !searchDTO.getTradeName().equals("")) {
            chemTradeANDED = true;
            //queryBuilder.append("chem.tradeNm = :tradeName AND ");
            queryBuilder.append("UPPER(chem.tradeNm) LIKE :tradeName AND ");
        }
        
        //Remove last AND (there must have been a minimum of 1 criteria above, so there will always be a trailing 'AND')
        queryBuilder.replace(queryBuilder.length() - 4, queryBuilder.length(), "");
        queryBuilder.append(" ORDER BY s.submissionNo DESC");
        
        Query query = em.createQuery(queryBuilder.toString());
        if(subNoANDED) {query.setParameter("submissionNumber", searchDTO.getSubmissionNumber());}
        if(subTypeANDED) {query.setParameter("submissionType", searchDTO.getSubmissionType());}
        if(companyANDED) {query.setParameter("companyName", "%"+searchDTO.getCompanyName().toUpperCase() + "%");}
        //if(chemNameANDED) {query.setParameter("chemicalName", searchDTO.getChemicalName());}
        if(chemNameANDED) {query.setParameter("chemicalName", "%"+searchDTO.getChemicalName().toUpperCase() + "%");}
        if(chemTradeANDED) {query.setParameter("tradeName", "%"+searchDTO.getTradeName().toUpperCase() + "%");}
        
        return query.getResultList();
    }

    public List<Submission> findSubmissionByCriteria(String reportView, String sortBy, Date dateFrom, Date dateTo) {
        String queryConditions = "";
        
        if(reportView.equals("Active"))
            queryConditions = "srd.companyNoticeSentDt IS NULL ";
        else if(reportView.equals("Completed"))
                queryConditions = "srd.companyNoticeSentDt IS NOT NULL and srd.companyNoticeSentDt between :dateFromParam and :dateToParam ";
        
        if ((reportView.equals("Completed")) && (sortBy.equals("Complete by Year")))
            queryConditions = queryConditions + " order by srd.companyNoticeSentDt ";
        if (sortBy.equals("Completed by Type"))    
            queryConditions = queryConditions + " order by s.SubmissionTypeCd ";
        
        //String queryStr = "SELECT s FROM Submission s WHERE s.submissionId is not null";
        String queryStr = "SELECT s FROM Submission s, SubmissionReviewDetails srd " +
                "WHERE srd.submissionId = s.submissionId and " + queryConditions;
        
        Query query = em.createQuery(queryStr);
        query.setParameter("dateFromParam", dateFrom);
        query.setParameter("dateToParam", dateTo);
        
        List<Submission> result = query.getResultList();
        return result;
    }
    
}
