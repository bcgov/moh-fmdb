/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ConfigurationManagerFacade.java                *
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
import ca.bc.gov.moh.fmdb.entity.CompanyTypes;
import ca.bc.gov.moh.fmdb.entity.PlaStatusTypes;
import ca.bc.gov.moh.fmdb.entity.ReviewStatuses;
import ca.bc.gov.moh.fmdb.entity.ReviewerNameTypes;
import ca.bc.gov.moh.fmdb.entity.SubmissionTypes;
import ca.bc.gov.moh.fmdb.logging.LogManager;
import ca.bc.gov.moh.fmdb.logging.Logger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author graham.townsend
 */
@Stateful
public class ConfigurationManagerFacade implements ConfigurationManagerFacadeLocal {

    @EJB
    SubmissionTypesFacadeLocal submissionTypesFacade;
    @EJB
    BiaRequestedCodesFacadeLocal biaRequestedCodesFacade;
    @EJB
    ReviewStatusesFacadeLocal reviewStatusesFacade;
    @EJB
    ReviewerNameTypesFacadeLocal reviewerNameTypesFacade;
    @EJB
    CompanyTypesFacadeLocal companyTypesFacade;
    @EJB
    PlaStatusTypesFacadeLocal plaStatusTypesFacade;
    private Logger logger = LogManager.getLogManager().getLogger(Constants.EJB_LOGGER);

    /*
     * loads up a list of all submission types from the database. Returns the list
     * to the managed bean to populate the drop downs for submission types. 
     * 
     */
    public List<SubmissionTypes> findAllSubmissionTypes() {
        List<SubmissionTypes> submissionTypes = submissionTypesFacade.findAll();
        return submissionTypes;
    }

    /*
     * loads up a list of all active submission types from the database. Returns the list
     * to the managed bean to populate the drop downs for submission types. 
     * 
     */
    public List<SubmissionTypes> findAllActiveSubmissionTypes() {
        List<SubmissionTypes> submissionTypes = submissionTypesFacade.findAllActive();
        return submissionTypes;
    }

    /*
     * loads up a list of all bia requested codes from the database. Returns the list
     * to the managed bean to populate the drop downs for bia requested codes. 
     * 
     */
    public List<BiaRequestedCodes> findAllBiaRequestedCodes() {
        List<BiaRequestedCodes> biaRequestedCodes = this.biaRequestedCodesFacade.findAll();

        return biaRequestedCodes;
    }

    public List<BiaRequestedCodes> findAllActiveBiaRequestedCodes() {
        List<BiaRequestedCodes> biaRequestedCodes = this.biaRequestedCodesFacade.findAllActive();

        return biaRequestedCodes;
    }

    /*
     * loads up a list of all review status codes from the database. Returns the list
     * to the managed bean to populate the drop downs for the review status codes. 
     * 
     */
    public List<ReviewStatuses> findAllReviewStatuses() {

        List<ReviewStatuses> reviewStatuses = this.reviewStatusesFacade.findAll();

        return reviewStatuses;

    }

    /*
     * loads up a list of all review status codes from the database. Returns the list
     * to the managed bean to populate the drop downs for the review status codes. 
     * 
     */
    public List<ReviewStatuses> findAllActiveReviewStatuses() {

        List<ReviewStatuses> reviewStatuses = this.reviewStatusesFacade.findAllActive();

        return reviewStatuses;

    }

    /*
     * loads up a list of all reviewer name type codes from the database. Returns the list
     * to the managed bean to populate the drop downs for the reviewer name type codes. 
     * 
     */
    public List<ReviewerNameTypes> findAllReviewerNameTypes() {

        List<ReviewerNameTypes> reviewerNameTypes = this.reviewerNameTypesFacade.findAll();

        return reviewerNameTypes;
    }

    /*
     * loads up a list of all active reviewer name type codes from the database. Returns the list
     * to the managed bean to populate the drop downs for the reviewer name type codes. 
     * 
     */
    public List<ReviewerNameTypes> findAllActiveReviewerNameTypes() {

        List<ReviewerNameTypes> reviewerNameTypes = this.reviewerNameTypesFacade.findAllActive();

        return reviewerNameTypes;
    }

    /*
     * loads up a list of all company types from the database. Returns the list
     * to the managed bean to populate the drop downs for the company types on the admin pages. 
     * 
     */
    public List<CompanyTypes> findAllCompanyTypes() {

        List<CompanyTypes> companyTypes = this.companyTypesFacade.findAll();

        return companyTypes;

    }

    /*
     * loads up a list of all company types from the database with the active flag 'Y'. Returns the list
     * to the managed bean to populate the drop downs for the company types. 
     * 
     */
    public List<CompanyTypes> findAllActiveCompanyTypes() {

        List<CompanyTypes> companyTypes = this.companyTypesFacade.findAllActive();

        return companyTypes;

    }

    /*
     * loads up a list of all PLA status types from the database. Returns the list
     * to the managed bean to populate the drop downs for the PLA status types. 
     * 
     */
    public List<PlaStatusTypes> findAllPlaStatusTypes() {
        List<PlaStatusTypes> plaStatusTypes = this.plaStatusTypesFacade.findAll();

        return plaStatusTypes;

    }

    /*
     * loads up a list of all active PLA status types from the database. Returns the list
     * to the managed bean to populate the drop downs for the PLA status types. 
     * 
     */
    public List<PlaStatusTypes> findAllActivePlaStatusTypes() {
        List<PlaStatusTypes> plaStatusTypes = this.plaStatusTypesFacade.findAllActive();

        return plaStatusTypes;

    }

    /*
     * saves  BiaRequestedCodes to the database from the admin module.  
     * 
     */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void saveBiaRequestedCodes(BiaRequestedCodes biaRequestedCodes) throws SaveFMDBException {

        boolean newCode = biaRequestedCodes.getBiaRequstedCd() == null ? true : false;

        try {

            this.biaRequestedCodesFacade.saveBiaRequestedCodes(biaRequestedCodes);

            if (newCode) {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("The creation of the new code was successful.");
                }
            } else {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("The save was successful for BIA with id '" +
                            biaRequestedCodes.getBiaRequstedCd() + "'");
                }
            }
        } catch (SaveFMDBException se) {
            throw se;
        } catch (Exception e) {
            e.printStackTrace();
            String msg;

            if (newCode) {
                msg = "A problem occured while attempting to create a new code.";
            } else {
                msg = "A problem occured while trying to save changes to an existing code with id '" +
                        biaRequestedCodes.getBiaRequstedCd() + "'.";
            }

            throw new SaveFMDBException(msg, e, SaveFMDBException.SaveExceptionType.OTHER);
        }


    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void saveCompanyTypeCodes(CompanyTypes codes) throws SaveFMDBException {

        boolean newCode = codes.getCompanyTypeCd() == null ? true : false;

        try {

            this.companyTypesFacade.saveCompanyTypeCodes(codes);

            if (newCode) {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("The creation of the new code was successful.");
                }
            } else {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("The save was successful for company code with id '" +
                            codes.getCompanyTypeCd() + "'");
                }
            }
        } catch (SaveFMDBException se) {
            throw se;
        } catch (Exception e) {
            e.printStackTrace();
            String msg;

            if (newCode) {
                msg = "A problem occured while attempting to create a new code.";
            } else {
                msg = "A problem occured while trying to save changes to an existing code with id '" +
                        codes.getCompanyTypeCd() + "'.";
            }

            throw new SaveFMDBException(msg, e, SaveFMDBException.SaveExceptionType.OTHER);
        }


    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void saveReviewStatusCodes(ReviewStatuses codes) throws SaveFMDBException {

        boolean newCode = codes.getReviewStatusCd() == null ? true : false;

        try {

            this.reviewStatusesFacade.saveReviewStatusCodes(codes);

            if (newCode) {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("The creation of the new code was successful.");
                }
            } else {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("The save was successful for review status with id '" +
                            codes.getReviewStatusCd() + "'");
                }
            }
        } catch (SaveFMDBException se) {
            throw se;
        } catch (Exception e) {
            e.printStackTrace();
            String msg;

            if (newCode) {
                msg = "A problem occured while attempting to create a new code.";
            } else {
                msg = "A problem occured while trying to save changes to an existing code with id '" +
                        codes.getReviewStatusCd() + "'.";
            }

            throw new SaveFMDBException(msg, e, SaveFMDBException.SaveExceptionType.OTHER);
        }


    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void savePlaStatusCodes(PlaStatusTypes codes) throws SaveFMDBException {

        boolean newCode = codes.getPlaStatusCd() == null ? true : false;

        try {

            this.plaStatusTypesFacade.savePlaStatusCodes(codes);

            if (newCode) {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("The creation of the new code was successful.");
                }
            } else {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("The save was successful for PLA with id '" +
                            codes.getPlaStatusCd() + "'");
                }
            }
        } catch (SaveFMDBException se) {
            throw se;
        } catch (Exception e) {
            e.printStackTrace();
            String msg;

            if (newCode) {
                msg = "A problem occured while attempting to create a new code.";
            } else {
                msg = "A problem occured while trying to save changes to an existing code with id '" +
                        codes.getPlaStatusCd() + "'.";
            }

            throw new SaveFMDBException(msg, e, SaveFMDBException.SaveExceptionType.OTHER);
        }


    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void saveSubmissionTypeCodes(SubmissionTypes codes, boolean newCode) throws SaveFMDBException {


        try {

            this.submissionTypesFacade.saveSubmissionTypeCodes(codes, newCode);

            if (newCode) {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("The creation of the new code was successful.");
                }
            } else {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("The save was successful for submission with id '" +
                            codes.getSubmissionTypeCd() + "'");
                }
            }
        } catch (SaveFMDBException se) {
            throw se;
        } catch (Exception e) {
            e.printStackTrace();
            String msg;

            if (newCode) {
                msg = "A problem occured while attempting to create a new code.";
            } else {
                msg = "A problem occured while trying to save changes to an existing code with id '" +
                        codes.getSubmissionTypeCd() + "'.";
            }

            throw new SaveFMDBException(msg, e, SaveFMDBException.SaveExceptionType.OTHER);
        }


    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void saveReviewerNameTypes(ReviewerNameTypes codes) throws SaveFMDBException {

        boolean newCode = codes.getReviewerNameCd() == null ? true : false;

        try {

            this.reviewerNameTypesFacade.saveReviewerTypes(codes);

            if (newCode) {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("The creation of the new code was successful.");
                }
            } else {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("The save was successful for reviewer with id '" +
                            codes.getReviewerNameCd() + "'");
                }
            }
        } catch (SaveFMDBException se) {
            throw se;
        } catch (Exception e) {
            e.printStackTrace();
            String msg;

            if (newCode) {
                msg = "A problem occured while attempting to create a new code.";
            } else {
                msg = "A problem occured while trying to save changes to an existing code with id '" +
                        codes.getReviewerNameCd() + "'.";
            }

            throw new SaveFMDBException(msg, e, SaveFMDBException.SaveExceptionType.OTHER);
        }


    }

    public long findAssociatedSubmissionForCode(String code, String codeType) {

        long noSubmissions = 0;

        if (codeType.equals("bia")) {
            noSubmissions = this.biaRequestedCodesFacade.findAssociatedSubmissions(code);
        } else if (codeType.equals("companyType")) {
            noSubmissions = this.companyTypesFacade.findAssociatedSubmissions(code);
        } else if (codeType.equals("pla")) {
            noSubmissions = this.plaStatusTypesFacade.findAssociatedSubmissions(code);
        } else if (codeType.equals("submission")) {
            noSubmissions = this.submissionTypesFacade.findAssociatedSubmissions(code);
        } else if (codeType.equals("reviewStatus")) {
            noSubmissions = this.reviewStatusesFacade.findAssociatedSubmissions(code);
        } else if (codeType.equals("reviewer")) {
            noSubmissions = this.reviewerNameTypesFacade.findAssociatedSubmissions(code);
        }

        return noSubmissions;
    }
}
