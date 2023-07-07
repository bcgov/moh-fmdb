/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VTechnicalReviewFacadeLocal.java               *
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

import ca.bc.gov.moh.fmdb.entity.VTechnicalReview;
//import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wande.he
 */
@Local
public interface VTechnicalReviewFacadeLocal {

    void create(VTechnicalReview vTechnicalReview);

    void edit(VTechnicalReview vTechnicalReview);

    void remove(VTechnicalReview vTechnicalReview);

    VTechnicalReview find(Object id);

    //List<VSpecialAuthForms> findAll();
    
    List<VTechnicalReview> findReportDataByCriteria(String reportView);

}
