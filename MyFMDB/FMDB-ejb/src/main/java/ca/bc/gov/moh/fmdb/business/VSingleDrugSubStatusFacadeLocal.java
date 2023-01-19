/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VSingleDrugSubStatusFacadeLocal.java           *
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

import ca.bc.gov.moh.fmdb.entity.VDins;
import ca.bc.gov.moh.fmdb.entity.VReviewQuestions;
import ca.bc.gov.moh.fmdb.entity.VSingleDrugSubStatus;
//import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wande.he
 */
@Local
public interface VSingleDrugSubStatusFacadeLocal {

    void create(VSingleDrugSubStatus vSingleDrugSubStatus);

    void edit(VSingleDrugSubStatus vSingleDrugSubStatus);

    void remove(VSingleDrugSubStatus vSingleDrugSubStatus);

    VSingleDrugSubStatus find(Object id);

    List<VSingleDrugSubStatus> findReportDataByCriteria(String chemicalName);
    List<VDins> findDinsData(String chemicalName);
    List<VReviewQuestions> findReviewQuestionsData(String chemicalName);
}
