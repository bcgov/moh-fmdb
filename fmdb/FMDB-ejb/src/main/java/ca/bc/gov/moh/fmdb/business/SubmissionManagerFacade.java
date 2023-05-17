/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SubmissionManagerFacade.java                   *
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
import ca.bc.gov.moh.fmdb.business.exception.ValidationFMDBException;
import ca.bc.gov.moh.fmdb.entity.BiaRequestedCodes;
import ca.bc.gov.moh.fmdb.entity.Companies;
import ca.bc.gov.moh.fmdb.entity.Dins;
import ca.bc.gov.moh.fmdb.entity.PlaLogs;
import ca.bc.gov.moh.fmdb.entity.PlaStatusTypes;
import ca.bc.gov.moh.fmdb.entity.ReviewQuestions;
import ca.bc.gov.moh.fmdb.entity.ReviewStatuses;
import ca.bc.gov.moh.fmdb.model.SearchDTO;
import ca.bc.gov.moh.fmdb.model.SearchResultDTO;
import ca.bc.gov.moh.fmdb.entity.Submission;
import ca.bc.gov.moh.fmdb.logging.Logger;
import ca.bc.gov.moh.fmdb.logging.LogManager;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
//@Interceptors(value=LoggerInterceptor.class)
public class SubmissionManagerFacade implements SubmissionManagerFacadeLocal {
    private Logger logger = LogManager.getLogManager().getLogger(Constants.EJB_LOGGER);
    
    @EJB
    private SubmissionFacadeLocal submissionFacade;
    @EJB
    private CompaniesFacadeLocal companiesFacade;
    
    public List<SearchResultDTO> searchSubmission(SearchDTO searchDTO) {

        //May want to consider caching the results in this bean (if bean is stateful)
        //If the caching route is taken, will want to have a way to clear the cache once
        //the search results are no longer required.
        List<SearchResultDTO> searchResultDTO = submissionFacade.searchSubmission(searchDTO);

        return searchResultDTO;
    }
    
    public Submission retrieveSubmission(long submissionNumber) throws ItemNotFoundFMDBException {
        Submission submission =  submissionFacade.findSubmissionById(submissionNumber);

        return submission;
    }
    
    /**
     *  
     *  Saves the passed submission.<br/><br/>
     *  The reason this method uses <code>TransactionAttributeType.NOT_SUPPORTED</code> a call is 
     *  made to another transactional method that performs the save.  Thus, this method can be used
     *  for cleanup operations should the transaction roll back.  If this method was part of a transaction, 
     *  the cleanup would have to be done by the caller.  However, since the caller has no idea of how 
     *  to perform the cleanup, it must be done here.
     *  
     *  @param submission
     *  @throws ValidationFMDBException
     *  @throws SaveFMDBException
     *  
     */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void saveSubmission(Submission submission) throws ValidationFMDBException, SaveFMDBException {
     
        boolean newSubmission = submission.getSubmissionId() == null ? true : false;
        Long submissionNo = submission.getSubmissionNo();
        
        //Replace any ids <= 0 with NULL
        Dins currentDin;
        for(Iterator<Dins> itr = submission.getFmdbChemicals().getFmdbDinsCollection().iterator(); itr.hasNext(); ) {
            currentDin = itr.next();
            
            if(currentDin.getDinId() != null && currentDin.getDinId() <= 0) {
                currentDin.setDinId(null);
            }
        }
        
        ReviewQuestions currentQuestion;
        for(Iterator<ReviewQuestions> itr = submission.getFmdbSubmissionReviewDetails().getFmdbReviewQuestionsCollection().iterator(); itr.hasNext(); ) {
            currentQuestion = itr.next();
            
            if(currentQuestion.getReviewQuestionId() != null && currentQuestion.getReviewQuestionId() <= 0) {
                currentQuestion.setReviewQuestionId(null);
            }
        }
        
        PlaLogs currentLog;
        for(Iterator<PlaLogs> itr = submission.getFmdbProductListingAgreement().getFmdbPlaLogsCollection().iterator(); itr.hasNext(); ) {
            currentLog = itr.next();
            
            if(currentLog.getPlaLogId() != null && currentLog.getPlaLogId() <= 0) {
                currentLog.setPlaLogId(null);
            }
        }

        prepareSubmissionForSave(submission);
        
        try {
            this.submissionFacade.saveSubmission(submission);
            
            if(newSubmission) {
                if(this.logger.isDebugEnabled()) {
                    this.logger.debug("The creation of the new submission was successful.  Its submission id is '" + 
                                    submission.getSubmissionId() + "' and number is '" + 
                                    submission.getSubmissionNo() + "'");
                }
            }
            else {
                if(this.logger.isDebugEnabled()) {
                    this.logger.debug("The save was successful for submission with id '" + 
                                    submission.getSubmissionId() + "' and number '" + 
                                    submission.getSubmissionNo() + "'");
                }
            }
        }
        catch(SaveFMDBException se) {
            throw se;
        }
        catch(Exception e) {           
            submission.setSubmissionNo(submissionNo);
            
            String msg;
            
            if(newSubmission) {
                msg =   "A problem occured while attempting to create a new submission.";
            }
            else {
                msg =   "A problem occured while trying to save changes to an existing submission with id '" + 
                        submission.getSubmissionId() + "' and number '" + submission.getSubmissionNo() + "'.";
            }
            
            throw new SaveFMDBException(msg, e, SaveFMDBException.SaveExceptionType.OTHER);
        }
    }
    
    
    
    public List<Companies> searchCompanies(String companyName) {
        
        List<Companies> companies = companiesFacade.searchCompanies(companyName);

        return companies;
    }
    
    public Companies retrieveCompany(Long companyId) throws ItemNotFoundFMDBException {
        Companies company =  companiesFacade.findCompanyById(companyId);
        
        return company;
    }

/**
     *
     *  Performs any operations required prior to saving the submission.
     *  Only call this method when the submission is to be saved.
     *
     *  @return the prepared submission
     *
     */
    private void prepareSubmissionForSave(Submission submission) {


        Companies company = submission.getCompanyId();
        if(company == null || company.getCompanyId() == null || company.getCompanyId() == 0) {
            submission.setCompanyId(null);
        }

        Character cdr = submission.getFmdbSubmissionReviewDetails().getCdrReviewYn();
        if(cdr != null && cdr != 'Y' && cdr != 'N') {
            submission.getFmdbSubmissionReviewDetails().setCdrReviewYn(null);
        }

        Character sa = submission.getFmdbSubmissionReviewDetails().getSaRequiredYn();
        if(sa != null && sa != 'Y' && sa != 'N') {
            submission.getFmdbSubmissionReviewDetails().setSaRequiredYn(null);
        }

        BiaRequestedCodes biaCodes = submission.getFmdbSubmissionReviewDetails().getBiaRequstedCd();
        if(biaCodes != null && (biaCodes.getBiaRequstedCd() == null || biaCodes.getBiaRequstedCd().trim().equals(""))) {
            submission.getFmdbSubmissionReviewDetails().setBiaRequstedCd(null);
        }

        ReviewStatuses reviewCodes = submission.getFmdbSubmissionReviewDetails().getReviewStatusCd();
        if(reviewCodes != null && (reviewCodes.getReviewStatusCd() == null || reviewCodes.getReviewStatusCd().trim().equals(""))) {
            submission.getFmdbSubmissionReviewDetails().setReviewStatusCd(null);
        }

        PlaStatusTypes plaCodes = submission.getFmdbProductListingAgreement().getPlaStatusCd();
        if(plaCodes != null && (plaCodes.getPlaStatusCd() == null || plaCodes.getPlaStatusCd().trim().equals(""))) {
            submission.getFmdbProductListingAgreement().setPlaStatusCd(null);
        }

        submission.getFmdbProductListingAgreement().setSubmissionId(submission);

        if (submission.getContactId() == null || submission.getContactId().getContactNameId() == null){
            submission.setContactId(null);
        }
    }
    
}
