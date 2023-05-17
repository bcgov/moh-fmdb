/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SubmissionType.java                            *
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

import ca.bc.gov.moh.fmdb.business.SubmissionTypesFacadeLocal;
import ca.bc.gov.moh.fmdb.business.exception.SaveFMDBException;
import ca.bc.gov.moh.fmdb.entity.SubmissionTypes;
import fmdbwar.DropDownListBean;
import fmdbwar.util.SelectItemHelper;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 */
@Named("SubmissionType")
@ViewScoped
public class SubmissionType implements Serializable {

    @EJB
    private SubmissionTypesFacadeLocal typeFacade;
    private SubmissionTypes type;
    private List<SelectItem> submissionTypes;
    private List<SubmissionTypes> allTypes;
    private String selectedType;

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public SubmissionType() {
    }

    @PostConstruct
    public void init() {
        clear();
        allTypes = typeFacade.findAll();
        //This could maybe be moved to dropdownlist bean along with active submission types
        //Note the the submission types currently in DropDownListBean is just for active types (used by Search and Details) 
        submissionTypes = SelectItemHelper.buildSelectItemList(allTypes, "submissionTypeDsc", "submissionTypeCd");
    }

    public String selectType(ValueChangeEvent event){
        
        selectedType = (String) event.getNewValue();
        if (selectedType != null) {
            type = find(allTypes); //find by cd value
        } else {
            type = new SubmissionTypes();
        }
        return null;
    }

    public String add() {
        String message = "Add successful";
        Severity f = FacesMessage.SEVERITY_INFO;
        if (!validate()) {
            try {
                typeFacade.saveSubmissionTypeCodes(type, true);
                FacesContext fc = FacesContext.getCurrentInstance();
                DropDownListBean ddl = (DropDownListBean) fc.getApplication().evaluateExpressionGet(fc, "#{DropDownListBean}", DropDownListBean.class);
                ddl.updateActiveSubmissionTypes();
            } catch (SaveFMDBException ex) {
                Logger.getLogger(SubmissionTypes.class.getName()).log(Level.SEVERE, null, ex);
                message = ex.getMessage();
                f = FacesMessage.SEVERITY_ERROR;
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(f, message, null));
            init();
        }
        return null;
    }

    public String update() {
        String message = "Update successful";
        Severity f = FacesMessage.SEVERITY_INFO;
        if (!validate()) {
            try {
                typeFacade.saveSubmissionTypeCodes(type, false);
                FacesContext fc = FacesContext.getCurrentInstance();
                DropDownListBean ddl = (DropDownListBean) fc.getApplication().evaluateExpressionGet(fc, "#{DropDownListBean}", DropDownListBean.class);
                ddl.updateActiveSubmissionTypes();
            } catch (SaveFMDBException ex) {
                Logger.getLogger(SubmissionTypes.class.getName()).log(Level.SEVERE, null, ex);
                message = ex.getMessage();
                f = FacesMessage.SEVERITY_ERROR;
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(f, message, null));
            init();
        }
        return null;
    }

    public String clear() {
        type = new SubmissionTypes();
        selectedType = null;
        return null;
    }

    private SubmissionTypes find(List<SubmissionTypes> allTypes) {
        for (SubmissionTypes submissionTypes : allTypes) {
            if (submissionTypes.getSubmissionTypeCd().equals(selectedType)) {
                return submissionTypes;
            }
        }
        return null;
    }

    private boolean validate() {
        return false;
    }

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }

    public SubmissionTypes getType() {
        return type;
    }

    public void setType(SubmissionTypes type) {
        this.type = type;
    }

    public List<SelectItem> getSubmissionTypes() {
        return submissionTypes;
    }
}

