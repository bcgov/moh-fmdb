/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VSubmByApplicantFacadeLocal.java               *
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

//import java.util.Date;
import ca.bc.gov.moh.fmdb.entity.VSubmByApplicant;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wande.he
 */
@Local
public interface VSubmByApplicantFacadeLocal {

    void create(VSubmByApplicant vSubmByApplicant);

    void edit(VSubmByApplicant vSubmByApplicant);

    void remove(VSubmByApplicant vSubmByApplicant);

    VSubmByApplicant find(Object id);

    List<VSubmByApplicant> findReportDataByCriteria(String reportView, String company);

}
