/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        UtilityHelperTests.java                        *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fmdbwar;

import fmdbwar.view.UtilityHelper;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author david.tulk
 */
public class UtilityHelperTests {

    public UtilityHelperTests() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void testWeekendBetweenDates() {
        //Object[0] = FROM date; Object[1] = TO date; Object[3] = expected result
        List<Object[]> vals = new LinkedList();
        
        //Test data
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 1), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Boolean(true)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 2), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Boolean(true)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 3), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Boolean(true)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 4), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Boolean(true)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 5), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Boolean(true)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 6), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Boolean(true)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 7), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Boolean(true)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 8), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Boolean(true)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 9), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Boolean(true)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 10), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Boolean(false)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Boolean(false)});
                                
        
        Object[] current;
        Calendar fromCal, toCal;
        Boolean expected, result;
        int counter = 0;
        for(Iterator<Object[]> itr = vals.iterator(); itr.hasNext(); ) {
            current = itr.next();
            fromCal = (Calendar) current[0];
            toCal = (Calendar) current[1];
            expected = (Boolean) current[2];
            result = UtilityHelper.doesWeekendFallBetweenDates(fromCal, toCal);
            Assert.assertEquals("Index for which error occured: [" + counter + "]", expected.booleanValue(), result.booleanValue());
            counter++;
        }
    }
    
    @Test
    public void testBusinessDaysCalculation() {
        //Object[0] = FROM date; Object[1] = TO date; Object[3] = expected result
        List<Object[]> vals = new LinkedList();
        
        //Test data
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 1), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Long(6)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 2), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Long(6)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 3), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Long(6)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 4), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Long(5)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 5), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Long(4)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 6), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Long(3)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 7), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Long(2)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 8), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Long(1)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 9), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Long(1)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 10), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Long(1)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new GregorianCalendar(2008, Calendar.MARCH, 11), 
                                new Long(0)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.JANUARY, 1), 
                                new GregorianCalendar(2008, Calendar.DECEMBER, 31), 
                                new Long(261)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.JANUARY, 13), 
                                new GregorianCalendar(2008, Calendar.FEBRUARY, 6), 
                                new Long(17)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.JANUARY, 16), 
                                new GregorianCalendar(2008, Calendar.FEBRUARY, 6), 
                                new Long(15)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 12), 
                                new GregorianCalendar(2008, Calendar.MARCH, 16), 
                                new Long(3)});
        vals.add(new Object[] { new GregorianCalendar(2008, Calendar.MARCH, 12), 
                                new GregorianCalendar(2008, Calendar.MARCH, 23), 
                                new Long(8)});
    
        
        Object[] current;
        Calendar fromCal, toCal;
        Long expected, result;
        int counter = 0;
        for(Iterator<Object[]> itr = vals.iterator(); itr.hasNext(); ) {
            current = itr.next();
            fromCal = (Calendar) current[0];
            toCal = (Calendar) current[1];
            expected = (Long) current[2];
            result = UtilityHelper.calculateBusinessDays(fromCal.getTime(), toCal.getTime());
            Assert.assertEquals("Index for which error occured: [" + counter + "]", expected.longValue(), result.longValue());
            counter++;
        }
    }
    
}