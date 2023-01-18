/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VSubmissionsFacadeLocal.java                   *
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

import ca.bc.gov.moh.fmdb.entity.VSubmissions;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wande.he
 */
@Local
public interface VSubmissionsFacadeLocal {

    void create(VSubmissions vSubmissions);

    void edit(VSubmissions vSubmissions);

    void remove(VSubmissions vSubmissions);

    VSubmissions find(Object id);

    List<VSubmissions> findAll();
    
    List<VSubmissions> findSubmissionsByCriteria(String reportView, String sortBy, Date dateFrom, Date dateTo);

}
