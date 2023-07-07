/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        Constants.java                                 *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package fmdbwarpub;

import ca.bc.gov.moh.fmdb.web.model.SearchDTO.SortByCriteria;

/**
 *  
 *  Contains contants used throughout the Web tier.
 *  
 *  @author david.tulk
 *  
 */
public class Constants {
    public static final String WEB_LOGGER = "WEB_LOGGER";
    
    public static final String BEAN_PUBLIC_WEB_DRUG_SEARCH = "PublicWebSearchBean";
    public static final String SEARCH_RESULTS_KEY = "PublicWebSearchResults";
    public static final String SEARCH_TYPE_KEY = "PublicWebSearchType";
    public static final String SEARCH_TYPE_BOOKMARK = "BookmarkSearch";
    
    public static final String PUBLIC_WEB_CONTROLLER_KEY = "PublicWebController";
    
    public static final String QUERY_PARAM_SUBMISSION_ID = "sub";
    
    public static final String MESSAGE_RESOURCES = "Bundle";
    
    /**
     *  The default criteria to sort by.  The default is only used if there is a bug in the system (or the frontend form submission was manipulated).
     *  e.g. if the user selected a value in the dropdown that didn't exist.
     */
    public static final SortByCriteria DEFAULT_SORT_BY_CRITERIA = SortByCriteria.COMPANY_NAME;
}
