/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ConfigurationManagerFacadeLocal.java           *
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
import ca.bc.gov.moh.fmdb.entity.SubmissionTypes;
import ca.bc.gov.moh.fmdb.entity.BiaRequestedCodes;
import ca.bc.gov.moh.fmdb.entity.ReviewStatuses;
import ca.bc.gov.moh.fmdb.entity.ReviewerNameTypes;
import ca.bc.gov.moh.fmdb.entity.CompanyTypes;
import ca.bc.gov.moh.fmdb.entity.PlaStatusTypes;
import java.util.List;

import javax.ejb.Local;

/**
 *  
 *  Manager used to obtain configuration data, such as codes and types.
 *  
 */
@Local
public interface ConfigurationManagerFacadeLocal {
    
    public List<SubmissionTypes> findAllSubmissionTypes(); 
    public List<SubmissionTypes> findAllActiveSubmissionTypes(); 
    public void saveSubmissionTypeCodes(SubmissionTypes codes, boolean newCode) throws SaveFMDBException;
    
    public List<BiaRequestedCodes> findAllBiaRequestedCodes();
    public List<BiaRequestedCodes> findAllActiveBiaRequestedCodes();
    public void saveBiaRequestedCodes(BiaRequestedCodes biaRequestedCodes) throws SaveFMDBException;
    
    public List<ReviewStatuses> findAllReviewStatuses();
    public List<ReviewStatuses> findAllActiveReviewStatuses();
    public void saveReviewStatusCodes(ReviewStatuses reviewStatusesCodes) throws SaveFMDBException;
    
    public List<ReviewerNameTypes> findAllReviewerNameTypes();
    public List<ReviewerNameTypes> findAllActiveReviewerNameTypes();
    public void saveReviewerNameTypes(ReviewerNameTypes reviewerNameTypes) throws SaveFMDBException;
    
    public List<CompanyTypes> findAllCompanyTypes();
    public List<CompanyTypes> findAllActiveCompanyTypes();
    public void saveCompanyTypeCodes(CompanyTypes codes) throws SaveFMDBException;
    
    public List<PlaStatusTypes> findAllPlaStatusTypes();
    public List<PlaStatusTypes> findAllActivePlaStatusTypes();
    public void savePlaStatusCodes(PlaStatusTypes codes) throws SaveFMDBException;
    
     
   
    
    
    
    public long findAssociatedSubmissionForCode(String code, String codeType);
    
}
