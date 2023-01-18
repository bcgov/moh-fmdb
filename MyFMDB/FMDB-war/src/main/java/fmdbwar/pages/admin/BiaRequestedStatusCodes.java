/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        BiaRequestedStatusCodes.java                   *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fmdbwar.pages.admin;

import ca.bc.gov.moh.fmdb.business.BiaRequestedCodesFacadeLocal;
import ca.bc.gov.moh.fmdb.business.exception.SaveFMDBException;
import ca.bc.gov.moh.fmdb.entity.BiaRequestedCodes;
import fmdbwar.DropDownListBean;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * Managed bean for BiaRequestedStatusCodes.xhtml
 */
@Named("BiaRequestedStatusCodes")
@ViewScoped
public class BiaRequestedStatusCodes implements Serializable {

    @EJB
    private BiaRequestedCodesFacadeLocal biaRequestedCodesFacade;
    private BiaRequestedCodes biaRequestedCode;
    private List<BiaRequestedCodes> biaRequestedCodes;
    private String selectedBia;


    public BiaRequestedStatusCodes() {
    }

    @PostConstruct
    public void init() {
        clear();
        biaRequestedCodes = biaRequestedCodesFacade.findAll();
    }

    public String selectBiaRequestedCode(ValueChangeEvent event) {
        
       selectedBia = (String) event.getNewValue(); 
       if (selectedBia !=null){
            biaRequestedCode = find(biaRequestedCodes);
       }else{
           biaRequestedCode = new BiaRequestedCodes();
       }
       return null;
    }
    
    private BiaRequestedCodes find(List<BiaRequestedCodes> allTypes) {
        for (BiaRequestedCodes type : allTypes) {
            if (type.getBiaRequstedCd().equals(selectedBia)){
                return type;
            }
        }
        return null;
    }    

    public String add() {

        try {
            biaRequestedCodesFacade.saveBiaRequestedCodes(biaRequestedCode);
            FacesContext fc = FacesContext.getCurrentInstance();
            DropDownListBean ddl = (DropDownListBean) fc.getApplication().evaluateExpressionGet(fc, "#{DropDownListBean}", DropDownListBean.class);
            ddl.updateBiaRequestTypes();
        } catch (SaveFMDBException ex) {
            Logger.getLogger(BiaRequestedStatusCodes.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Add successful", null));
        init();

        return null;
    }

    public String update() {

        try {
            biaRequestedCodesFacade.saveBiaRequestedCodes(biaRequestedCode);
            FacesContext fc = FacesContext.getCurrentInstance();
            DropDownListBean ddl = (DropDownListBean) fc.getApplication().evaluateExpressionGet(fc, "#{DropDownListBean}", DropDownListBean.class);
            ddl.updateBiaRequestTypes();                
        } catch (SaveFMDBException ex) {
            Logger.getLogger(BiaRequestedStatusCodes.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Update successful", null));
        init();

        return null;
    }

    public String clear() {
        biaRequestedCode = new BiaRequestedCodes();
        selectedBia = null;
        return null;
    }

    /**
     * @return the biaRequestedCodes
     */
    public List<BiaRequestedCodes> getBiaRequestedCodes() {
        return biaRequestedCodes;
    }

    /**
     * @param biaRequestedCodes the biaRequestedCodes to set
     */
    public void setBiaRequestedCodes(List<BiaRequestedCodes> biaRequestedCodes) {
        this.biaRequestedCodes = biaRequestedCodes;
    }

    /**
     * @return the biaRequestedCode
     */
    public BiaRequestedCodes getBiaRequestedCode() {
        return biaRequestedCode;
    }

    /**
     * @param biaRequestedCode the biaRequestedCode to set
     */
    public void setBiaRequestedCode(BiaRequestedCodes biaRequestedCode) {
        this.biaRequestedCode = biaRequestedCode;
    }

    /**
     * @return the selectedBia
     */
    public String getSelectedBia() {
        return selectedBia;
    }

    /**
     * @param selectedBia the selectedBia to set
     */
    public void setSelectedBia(String selectedBia) {
        this.selectedBia = selectedBia;
    }
}

