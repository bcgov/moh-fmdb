/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        DropDownListBean.java                          *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fmdbwar;

import ca.bc.gov.moh.fmdb.business.ConfigurationManagerFacadeLocal;
import ca.bc.gov.moh.fmdb.entity.BiaRequestedCodes;
import ca.bc.gov.moh.fmdb.entity.PlaStatusTypes;
import ca.bc.gov.moh.fmdb.entity.ReviewStatuses;
import ca.bc.gov.moh.fmdb.entity.ReviewerNameTypes;
import ca.bc.gov.moh.fmdb.entity.SubmissionTypes;
import fmdbwar.util.SelectItemHelper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named("DropDownListBean")
@ApplicationScoped
public class DropDownListBean {

    @EJB
    private ConfigurationManagerFacadeLocal configurationManagerFacade;
    
    private List<SelectItem> submissionTypes;
    private List<SelectItem> reviewStatuses;
    private List<SelectItem> biaRequestTypes;
    private List<SelectItem> reviewerNameTypes;
    private List<SelectItem> plaStatusTypes;
    private List<SelectItem> cdrDrugReviewYN;
    private List<SelectItem> safRequiredYN;
    private List<SelectItem> psdRequested;
    
    
    @PostConstruct
    private void init() {
        
        cdrDrugReviewYN = createYnList();
        safRequiredYN = createYnList();
        psdRequested = createYnList();
        
        updateActiveSubmissionTypes();
        updateReviewStatuses();
        updateBiaRequestTypes();
        updatePlaStatusTypes();
        updateReviewerNameTypes();
    }
    
    public void updateActiveSubmissionTypes() {
        List<SubmissionTypes> codes = this.configurationManagerFacade.findAllActiveSubmissionTypes();
        submissionTypes = SelectItemHelper.buildSelectItemList(codes, "submissionTypeDsc", "submissionTypeCd");
    }
       
    public void updateReviewStatuses() { 
        List<ReviewStatuses> statCodes = this.configurationManagerFacade.findAllReviewStatuses();
        reviewStatuses = SelectItemHelper.buildSelectItemList(statCodes, "reviewStatusDsc", "reviewStatusCd");
    }
    
    public void updateBiaRequestTypes() { 
        List<BiaRequestedCodes> biaCodes = this.configurationManagerFacade.findAllBiaRequestedCodes();
        biaRequestTypes = SelectItemHelper.buildSelectItemList(biaCodes, "biaRequestedDsc", "biaRequstedCd");    
    }
    
    public void updatePlaStatusTypes() { 
        List<PlaStatusTypes> plaCodes = this.configurationManagerFacade.findAllPlaStatusTypes();
        plaStatusTypes = SelectItemHelper.buildSelectItemList(plaCodes, "plaStatusDsc", "plaStatusCd");
    }
    
    public void updateReviewerNameTypes() {         
        List<ReviewerNameTypes> rntCodes = this.configurationManagerFacade.findAllActiveReviewerNameTypes();
        reviewerNameTypes = SelectItemHelper.buildSelectItemList(rntCodes, "reviewerNameDsc", "reviewerNameCd"); 
    }
    
       
    public List<SelectItem> createYnList() {
        
        List<SelectItem> ynList = new ArrayList<>();
        SelectItem selectItem = new SelectItem();
        selectItem.setLabel("Yes");
        selectItem.setValue('Y');
        ynList.add(selectItem);
        selectItem = new SelectItem();
        selectItem.setLabel("No");
        selectItem.setValue('N');
        ynList.add(selectItem);
        
        return ynList;
    }

    /**
     * @return the submissionTypes
     */
    public List<SelectItem> getSubmissionTypes() {
        return submissionTypes;
    }

    public List<SelectItem> getReviewStatuses() {
        return reviewStatuses;
    }

    public List<SelectItem> getBiaRequestTypes() {
        return biaRequestTypes;
    }

    public List<SelectItem> getReviewerNameTypes() {
        return reviewerNameTypes;
    }

    public List<SelectItem> getPlaStatusTypes() {
        return plaStatusTypes;
    }

    public List<SelectItem> getCdrDrugReviewYN() {
        return cdrDrugReviewYN;
    }

    public List<SelectItem> getSafRequiredYN() {
        return safRequiredYN;
    }

    public List<SelectItem> getPsdRequested() {
        return psdRequested;
    }



}
