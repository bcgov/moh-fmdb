/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        Reviewer.java                                  *
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

import ca.bc.gov.moh.fmdb.business.ReviewerNameTypesFacadeLocal;
import ca.bc.gov.moh.fmdb.business.exception.SaveFMDBException;
import ca.bc.gov.moh.fmdb.entity.ReviewerNameTypes;
import fmdbwar.DropDownListBean;
import fmdbwar.util.SelectItemHelper;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * Managed bean for Reviewer.xhtml
 */
@Named("Reviewer")
@ViewScoped
public class Reviewer implements Serializable{

    @EJB
    private ReviewerNameTypesFacadeLocal typeFacade;
    private ReviewerNameTypes type;
    private List<SelectItem> reviewerNameTypes;
    private List<ReviewerNameTypes> allTypes;
    private String selectedType;

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public Reviewer() {
    }

    @PostConstruct
    public void init(){
        clear();
        allTypes = typeFacade.findAll();
        reviewerNameTypes = SelectItemHelper.buildSelectItemList(allTypes, "reviewerNameDsc", "reviewerNameCd");                
    }
    
    public String selectType(ValueChangeEvent event){
        
        selectedType = (String) event.getNewValue();
        if (selectedType!=null){
            type = find(allTypes);
        }else{
            type = new ReviewerNameTypes();
        }
        return null;
    }
    
    private ReviewerNameTypes find(List<ReviewerNameTypes> allTypes) {
        for (ReviewerNameTypes type : allTypes) {
            if (type.getReviewerNameCd().equals(selectedType)){
                return type;
            }
        }
        return null;
    }    
    
    public String add(){

        try{
            typeFacade.saveReviewerTypes(type);
            FacesContext fc = FacesContext.getCurrentInstance();
            DropDownListBean ddl = (DropDownListBean) fc.getApplication().evaluateExpressionGet(fc, "#{DropDownListBean}", DropDownListBean.class);
            ddl.updateReviewerNameTypes();
        } catch (SaveFMDBException ex) {
            Logger.getLogger(ReviewerNameTypes.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Add successful", null));
        init();

        return null;
    }
    
    public String update(){

        try{
            typeFacade.saveReviewerTypes(type);
            FacesContext fc = FacesContext.getCurrentInstance();
            DropDownListBean ddl = (DropDownListBean) fc.getApplication().evaluateExpressionGet(fc, "#{DropDownListBean}", DropDownListBean.class);
            ddl.updateReviewerNameTypes();            
        } catch (SaveFMDBException ex) {
            Logger.getLogger(ReviewerNameTypes.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Update successful", null));
        init();
       
        return null;
    }
    
    public String clear(){
        type = new ReviewerNameTypes();
        selectedType = null;
        return null;
    }

    public List<SelectItem> getReviewerNameTypes() {
        return reviewerNameTypes;
    }

    public void setReviewerNameTypes(List<SelectItem> ReviewerNameTypes) {
        this.reviewerNameTypes = ReviewerNameTypes;
    }

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }

    public ReviewerNameTypes getType() {
        return type;
    }

    public void setType(ReviewerNameTypes type) {
        this.type = type;
    }
    
}

