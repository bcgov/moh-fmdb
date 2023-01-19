/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        FMDBUtil.java                                  *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.bc.gov.moh.fmdb.web.util;

import fmdbwarpub.Constants;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 *
 * @author Laura.Funfer
 */
public class FMDBUtil {    
    
    private static ResourceBundle resources = ResourceBundle.getBundle(Constants.MESSAGE_RESOURCES);
    
    public static String getFormattedDate(Timestamp timestamp) {
        
        if(timestamp == null) {
            return "";
        }
        
        SimpleDateFormat formatter = new SimpleDateFormat(getMessage("publicWeb.dateFormat"));        
        
        return formatter.format(new Date(timestamp.getTime()));
    } 
    
    public static String getMessage(String key) {
        return resources.getString(key);
    }
    
    public static String nullOrString(String s){
        return (s == null)?"":s;
    }
}
