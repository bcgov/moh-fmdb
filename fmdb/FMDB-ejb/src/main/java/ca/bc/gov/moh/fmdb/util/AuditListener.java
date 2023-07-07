/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        AuditListener.java                             *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.bc.gov.moh.fmdb.util;

import java.security.Principal;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.apache.commons.beanutils.PropertyUtils;

/**
 *
 * @author greg.perkins
 */
public class AuditListener {

    public AuditListener(){
        System.out.println("Entity Listener created");
    }

    @PrePersist
    public void prePersist(Object entity){
        try{
            PropertyUtils.setProperty(entity, "createdOnDtm", new Date());
            PropertyUtils.setProperty(entity, "createdByNm", findUserName());
        }catch(Exception e){
           e.printStackTrace();
        }
    }
    @PreUpdate
    public void preUpdate(Object entity){
        try{
            PropertyUtils.setProperty(entity, "lastModifiedDtm", new Date());
            PropertyUtils.setProperty(entity, "lastModifiedByNm", findUserName());
        }catch(Exception e){
           e.printStackTrace();
        }
    }

    public String findUserName() {
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Principal principal = facesContext.getExternalContext().getUserPrincipal();
        String user = principal.getName();

        return user == null ? "" : user;
    }

}
