/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SearchDTO.java                                 *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.bc.gov.moh.fmdb.web.model;

import java.io.Serializable;

/**
 *  
 *  Contains the criteria for a search performed on the public website.
 *  
 *  @author david.tulk
 *  
 */
public class SearchDTO implements Serializable {
    public static enum SortByCriteria {
        COMPANY_NAME, BRAND_NAME, GENERIC_NAME, INDICATION
    }
    
    private SortByCriteria criteria;
    private String searchString;
    private boolean bookmarkSearch;
    
    public SearchDTO(SortByCriteria criteria, String searchString) {
        this.criteria = criteria;
        this.searchString = searchString;
        this.bookmarkSearch = false;
    }
    
    public SearchDTO(SortByCriteria criteria, char searchString) {
        this.criteria = criteria;
        this.searchString = Character.toString(searchString);
        this.bookmarkSearch = true;
    }
    
    public SearchDTO(SortByCriteria criteria, String searchString, boolean bookmarkSearch) {
        this.criteria = criteria;
        this.searchString = searchString;
        this.bookmarkSearch = bookmarkSearch;
    }
    
    public boolean isBookmarkSearch() {
        return this.bookmarkSearch;
    }

    public String getSearchString() {
        return this.searchString == null ? "" : this.searchString.trim();
    }
    
    public boolean sortByCompanyName() {
        return this.criteria.equals(SortByCriteria.COMPANY_NAME);
    }
    
    public boolean sortByBrandName() {
        return this.criteria.equals(SortByCriteria.BRAND_NAME);
    }

    public boolean sortByGenericName() {
        return this.criteria.equals(SortByCriteria.GENERIC_NAME);
    }
    
    public boolean sortByIndication() {
        return this.criteria.equals(SortByCriteria.INDICATION);
    }

    public SortByCriteria getCriteria() {
        return this.criteria;
    }
    
}
