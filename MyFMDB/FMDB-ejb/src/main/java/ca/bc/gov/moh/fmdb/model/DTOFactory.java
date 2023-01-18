/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        DTOFactory.java                                *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * Factory class to create new instance of a Data Transfer Object (DTO)
 * 
 */

package ca.bc.gov.moh.fmdb.model;

/**
 *
 * @author graham.townsend
 */
public class DTOFactory {
    
    /*
     * creates a new instance of a SearchDTO 
     */ 
    public static SearchDTO createSearchDTO() {
        return new SearchDTOImpl();
    } 
    
    /*
     * creates a new instance of a SearchDTO 
     */ 
    public static SearchResultDTO createSearchResultDTO() {
        return new SearchResultDTOImpl();
    }
    
    
}
