/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        UtilityHelper.java                             *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fmdbwar.view;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author graham.townsend
 */
public class UtilityHelper {
    
    /**
     *  
     *  Calculates the difference in days between today's date and <code>dateToCalculate</code> date.
     *  
     *  @param dateToCalculate the date to compare to today's date
     *  @return <code>dateToCalculate</code> - today
     *  
     */
    public static long calculateDays(Date dateToCalculate) {
        return calculateDays(Calendar.getInstance().getTime(), dateToCalculate);
    }
    
    /**
     *  
     *  Calculates the difference in days between <code>fromDate</code> and <code>toDate</code>.
     *  
     *  @param dateToCalculate the date to compare to today's date
     *  @return <code>toDate</code> - <code>fromDate</code>
     *  
     */
    public static long calculateDays(Date fromDate, Date toDate) {
        long diff = 0;
        
        if(fromDate != null && toDate != null) {
            diff = toDate.getTime() - fromDate.getTime() ;
            diff = (diff/(1000*3600*24));
        }
        
        return diff;
    }
    
    /**
     *  
     *  Calculates the difference in days between <code>fromDate</code> and <code>toDate</code>, 
     *  but does nnot include weekend days (i.e. Saturday and Sunday).
     *  
     *  @param dateToCalculate the date to compare to today's date
     *  @return <code>toDate</code> - <code>fromDate</code>
     *  
     */
    public static long calculateBusinessDays(Date fromDate, Date toDate) {
        Calendar fromCal = Calendar.getInstance();
        fromCal.setTime(fromDate);
        long fromTime = fromCal.getTimeInMillis();
        long fromDays = fromTime / (1000*3600*24);
        
        Calendar toCal = Calendar.getInstance();
        toCal.setTime(toDate);
        long toTime = toCal.getTimeInMillis();
        long toDays = toTime / (1000*3600*24);
        
        //Determine the # of days leading up to beginning of the first Sunday and
        //the # of business days following the last Sunday
        long numBusDaysBetweenFromDateAndFirstWeekend = getNumberBusinessDaysBeforeOrAfterWeekend(fromCal, true);
        long numBusDaysBetweenLastWeekendAndToDate = getNumberBusinessDaysBeforeOrAfterWeekend(toCal, false);
        
        //If from and to dates are both business days and fall in the same week then must adjust numBusDaysBetweenFromDateAndFirstWeekend
        if( !doesWeekendFallBetweenDates(fromCal, toCal)) {
            //numBusDaysBetweenFromDateAndFirstWeekend = toDays - fromDays - 1;
            //no need to do any more claculating if we know that the dates are less than 5
            return toDays-fromDays;
            
        }
        
        long diff = toDays - fromDays - numBusDaysBetweenFromDateAndFirstWeekend - numBusDaysBetweenLastWeekendAndToDate;
        
        //Determine # of weeks
        long numFullWeeks = diff / 7;
        
                
        return numBusDaysBetweenFromDateAndFirstWeekend + numFullWeeks * 5 + numBusDaysBetweenLastWeekendAndToDate - 1;
    }
    
    /**
     *  
     *  Determines whether or not one or more weekends fall between the passed dates.
     *  the method will always return true if one or both of the days are on
     *  the weekend.
     *  
     *  @param day1 the first date
     *  @param day2 the second date
     *  @return whether or not one or more weekends occur between the dates
     *  
     */
    public static boolean doesWeekendFallBetweenDates(Calendar day1, Calendar day2) {
        if( calculateDays(day1.getTime(), day2.getTime()) >= 5 ) {
            return true;
        }
        
        Calendar dayCalculator = (Calendar) day1.clone();
        
        while(  dayCalculator.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && 
                dayCalculator.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && 
                dayCalculator.before(day2) ) {
            
            dayCalculator.add(Calendar.DATE, 1);
        }
        
        return dayCalculator.equals(day2) ? false : true;
    }
    
    private static int getNumberBusinessDaysBeforeOrAfterWeekend(Calendar cal, boolean countNumBusDaysBetweenCalDateAndFirstWeekend) {
        int numDays;
        
        switch(cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                numDays = countNumBusDaysBetweenCalDateAndFirstWeekend ? 5 : 1;
                break;
            case Calendar.TUESDAY:
                numDays = countNumBusDaysBetweenCalDateAndFirstWeekend ? 4 : 2;
                break;
            case Calendar.WEDNESDAY:
                numDays = countNumBusDaysBetweenCalDateAndFirstWeekend ? 3 : 3;
                break;
            case Calendar.THURSDAY:
                numDays = countNumBusDaysBetweenCalDateAndFirstWeekend ? 2 : 4;
                break;
            case Calendar.FRIDAY:
                numDays = countNumBusDaysBetweenCalDateAndFirstWeekend ? 1 : 5;
                break;
            case Calendar.SATURDAY:
                numDays = countNumBusDaysBetweenCalDateAndFirstWeekend ? 0 : 0;
                break;
            case Calendar.SUNDAY:
                numDays = countNumBusDaysBetweenCalDateAndFirstWeekend ? 0 : 0;
                break;
            default:
                numDays = 0;
                break;
        }
        
        return numDays;
    }
}
