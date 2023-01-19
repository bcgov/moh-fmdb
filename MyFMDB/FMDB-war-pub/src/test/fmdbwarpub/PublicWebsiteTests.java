/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        PublicWebsiteTests.java                        *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fmdbwarpub;

import ca.bc.gov.moh.fmdb.entity.attachment.Attachment;
import ca.bc.gov.moh.fmdb.web.pub.PublicAcessVwControler;
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
public class PublicWebsiteTests {

    public PublicWebsiteTests() {
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
    public void testAttachmentRetrieval() {
        PublicAcessVwControler controller = new PublicAcessVwControler();
        Attachment attachment = controller.getAttachment(10045L);
        
        assertEquals("The filename is incorrect", "u.pdf", attachment.getFilename());
    }
    
}