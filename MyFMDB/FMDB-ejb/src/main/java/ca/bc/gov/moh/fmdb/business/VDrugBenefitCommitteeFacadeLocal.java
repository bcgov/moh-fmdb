/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VDrugBenefitCommitteeFacadeLocal.java          *
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

import ca.bc.gov.moh.fmdb.entity.VDrugBenefitCommittee;
//import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wande.he
 */
@Local
public interface VDrugBenefitCommitteeFacadeLocal {

    void create(VDrugBenefitCommittee vDrugBenefitCommittee);

    void edit(VDrugBenefitCommittee vDrugBenefitCommittee);

    void remove(VDrugBenefitCommittee vDrugBenefitCommittee);

    VDrugBenefitCommittee find(Object id);

    List<VDrugBenefitCommittee> findReportDataByCriteria(String reportView);

}
