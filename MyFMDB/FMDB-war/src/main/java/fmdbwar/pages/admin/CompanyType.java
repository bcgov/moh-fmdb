/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        CompanyType.java                               *
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

import ca.bc.gov.moh.fmdb.business.CompanyTypesFacadeLocal;
import ca.bc.gov.moh.fmdb.business.exception.SaveFMDBException;
import ca.bc.gov.moh.fmdb.entity.CompanyTypes;
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
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @author greg.perkins
 */
@ViewScoped
@Named("CompanyType")
public class CompanyType implements Serializable{

    @EJB
    private CompanyTypesFacadeLocal typeFacade;
    private CompanyTypes type;
    private List<SelectItem> companyTypes;
    private List<CompanyTypes> allTypes;
    private String selectedType;

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public CompanyType() {
    }

    @PostConstruct
    public void init(){
        clear();
        type = new CompanyTypes();
        allTypes = typeFacade.findAll();
        companyTypes = SelectItemHelper.buildSelectItemList(allTypes, "companyTypeDsc", "companyTypeCd");
    }
    
    public String selectType(ValueChangeEvent event){
        
        selectedType = (String) event.getNewValue();
        if (selectedType!=null){
            type = find(allTypes);
        }else{
            type = new CompanyTypes();
        }
        return null;
    }
    
    private CompanyTypes find(List<CompanyTypes> allTypes) {
        for (CompanyTypes type : allTypes) {
            if (type.getCompanyTypeCd().equals(selectedType)){
                return type;
            }
        }
        return null;
    }       
    
    public String add(){
        if (!validate()){
            try{
                typeFacade.saveCompanyTypeCodes(type);
            } catch (SaveFMDBException ex) {
                Logger.getLogger(CompanyTypes.class.getName()).log(Level.SEVERE, null, ex);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Add successful", null));
            init();
        }
        return null;
    }
    
    public String update(){
        if (!validate()){
            try{
                typeFacade.saveCompanyTypeCodes(type);
            } catch (SaveFMDBException ex) {
                Logger.getLogger(CompanyTypes.class.getName()).log(Level.SEVERE, null, ex);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Update successful", null));
            init();
        }        
        return null;
    }
    
    public String clear(){
        type = new CompanyTypes();
        selectedType = null;
        return null;
    }
    
    private boolean validate(){
        return false;
    }

    public List<SelectItem> getCompanyTypes() {
        return companyTypes;
    }

    public void setCompanyTypes(List<SelectItem> companyTypes) {
        this.companyTypes = companyTypes;
    }

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }

    public CompanyTypes getType() {
        return type;
    }

    public void setType(CompanyTypes type) {
        this.type = type;
    }

    
    
}

