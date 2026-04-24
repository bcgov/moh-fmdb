/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        PharmaCareStatusCodes.java                     *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * CompanyType.java
 *
 * Created on Sep 13, 2010, 3:12:24 PM
 */
 
package fmdbwar.pages.admin;

import ca.bc.gov.moh.fmdb.business.ReviewStatusesFacadeLocal;
import ca.bc.gov.moh.fmdb.business.exception.SaveFMDBException;
import ca.bc.gov.moh.fmdb.entity.ReviewStatuses;
import fmdbwar.DropDownListBean;
import fmdbwar.util.SelectItemHelper;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

/**
 * Managed bean for BiaRequestedStatusCodes.xhtml
 */
@Named("PharmaCareStatusCodes")
@ViewScoped
public class PharmaCareStatusCodes implements Serializable{

    @EJB
    private ReviewStatusesFacadeLocal typeFacade;
    private ReviewStatuses type;
    private List<ReviewStatuses> allTypes;
    private String selectedType;

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public PharmaCareStatusCodes() {
    }

    @PostConstruct
    public void init(){
        clear();
        allTypes = typeFacade.findAll();
    }
    
    public String selectType(ValueChangeEvent event){
        
        selectedType = (String) event.getNewValue();
        
        if (selectedType!=null){
            type = find(allTypes);
        }else{
            type = new ReviewStatuses();
        }
        return null;
    }
    
    private ReviewStatuses find(List<ReviewStatuses> allTypes) {
        for (ReviewStatuses type : allTypes) {
            if (type.getReviewStatusCd().equals(selectedType)){
                return type;
            }
        }
        return null;
    }      
    
    public String add(){

        try{
            typeFacade.saveReviewStatusCodes(type);
            FacesContext fc = FacesContext.getCurrentInstance();
            DropDownListBean ddl = (DropDownListBean) fc.getApplication().evaluateExpressionGet(fc, "#{DropDownListBean}", DropDownListBean.class);
            ddl.updateReviewStatuses();
        } catch (SaveFMDBException ex) {
            Logger.getLogger(ReviewStatuses.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Add successful", null));
        init();

        return null;
    }
    
    public String update(){

        try{
            typeFacade.saveReviewStatusCodes(type);
            FacesContext fc = FacesContext.getCurrentInstance();
            DropDownListBean ddl = (DropDownListBean) fc.getApplication().evaluateExpressionGet(fc, "#{DropDownListBean}", DropDownListBean.class);
            ddl.updateReviewStatuses();            
        } catch (SaveFMDBException ex) {
            Logger.getLogger(ReviewStatuses.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Update successful", null));
        init();
        
        return null;
    }
    
    public String clear(){
        type = new ReviewStatuses();
        selectedType = null;
        return null;
    }

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }

    public ReviewStatuses getType() {
        return type;
    }

    public void setType(ReviewStatuses type) {
        this.type = type;
    }
    
    
}

