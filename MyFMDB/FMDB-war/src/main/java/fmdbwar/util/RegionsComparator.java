/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        RegionsComparator.java                         *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fmdbwar.util;

import ca.bc.gov.moh.fmdb.entity.Regions;
import java.util.Comparator;

/**
 *
 * @author Johnson.Ding
 */
public class RegionsComparator implements Comparator{

    public int compare(Object o1, Object o2) {
        Regions region1 = (Regions)o1;
        Regions region2 = (Regions)o2;
        
        if(region1.getRegionDsc().equals(region2.getRegionDsc())){
            return 0;
        }
        else{
            if(region1.getRegionDsc().compareTo(region2.getRegionDsc())>0){
                return 1;
            }
            else{
                return -1;
            }
        }
    }

}
