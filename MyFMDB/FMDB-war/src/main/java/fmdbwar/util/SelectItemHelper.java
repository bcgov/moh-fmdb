/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SelectItemHelper.java                          *
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

import org.apache.commons.beanutils.PropertyUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author greg.perkins
 */
public class SelectItemHelper {

    public static List buildSelectItemList(List list, String labelProperty, String valueProperty){
        return buildSelectItemList(list, labelProperty, valueProperty, true);
    }

    /**
     * Overloaded method to take in two label properties.
     * Appends two label properites together, for instance a first and last name.
     * @return String
     */
    public static List buildSelectItemList(List list, String labelProperty1, String labelProperty2, String valueProperty){
        return buildSelectItemListTwoLabels(list, labelProperty1, labelProperty2 , valueProperty, true);
    }
    
    public static List buildSelectItemList(List list, String labelProperty, String valueProperty, boolean doSort){
        List outList = new ArrayList();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Object item = it.next();
            SelectItem selectItem = new SelectItem();
            try{
                String label = (PropertyUtils.getProperty(item, labelProperty) == null) ? "" : PropertyUtils.getProperty(item, labelProperty).toString();
                selectItem.setLabel(label);
                selectItem.setValue(PropertyUtils.getProperty(item, valueProperty));
            }catch(Exception e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            outList.add(selectItem);
        }
        if (doSort){
        Collections.sort(outList,new Comparator<SelectItem>(){

            public int compare(SelectItem o1, SelectItem o2) {
                return o1.getLabel().compareTo(o2.getLabel());
            }
            
        });
        }
        return outList;
    }

    public static List buildSelectItemListTwoLabels(List list, String labelProperty1, String labelProperty2, String valueProperty, boolean doSort){
        List outList = new ArrayList();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Object item = it.next();
            SelectItem selectItem = new SelectItem();
            try{
                String fname = (PropertyUtils.getProperty(item, labelProperty1) == null) ? "" : PropertyUtils.getProperty(item, labelProperty1).toString();
                String lname = (PropertyUtils.getProperty(item, labelProperty2) == null) ? "" : PropertyUtils.getProperty(item, labelProperty2).toString();
                String label = fname + ", " + lname;
                selectItem.setLabel(label);
                selectItem.setValue(PropertyUtils.getProperty(item, valueProperty));
            }catch(Exception e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            outList.add(selectItem);
        }
        if (doSort){
        Collections.sort(outList,new Comparator<SelectItem>(){

            public int compare(SelectItem o1, SelectItem o2) {
                return o1.getLabel().compareTo(o2.getLabel());
            }

        });
        }
        return outList;
    }
}
