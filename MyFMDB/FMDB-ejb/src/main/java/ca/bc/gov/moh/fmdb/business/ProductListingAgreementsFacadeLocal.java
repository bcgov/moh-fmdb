/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        ProductListingAgreementsFacadeLocal.java       *
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

import ca.bc.gov.moh.fmdb.entity.ProductListingAgreement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Chris.Prince
 */
@Local
public interface ProductListingAgreementsFacadeLocal {

    void create(ProductListingAgreement ProductListingAgreement);

    void edit(ProductListingAgreement ProductListingAgreement);

    void remove(ProductListingAgreement ProductListingAgreement);

    ProductListingAgreement find(Object id);

    List<ProductListingAgreement> findAll();

}
