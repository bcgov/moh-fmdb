/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VSpecialAuthFormsFacadeLocal.java              *
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

import ca.bc.gov.moh.fmdb.entity.VSpecialAuthForms;
//import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wande.he
 */
@Local
public interface VSpecialAuthFormsFacadeLocal {

    void create(VSpecialAuthForms vSpecialAuthForms);

    void edit(VSpecialAuthForms vSpecialAuthForms);

    void remove(VSpecialAuthForms vSpecialAuthForms);

    VSpecialAuthForms find(Object id);

    //List<VTechnicalReview> findAll();
    
    List<VSpecialAuthForms> findReportDataByCriteria(String reportView);

}
