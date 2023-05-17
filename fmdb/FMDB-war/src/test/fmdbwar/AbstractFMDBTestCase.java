/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        AbstractFMDBTestCase.java                      *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/


package fmdbwar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import junit.framework.Test;
import junit.framework.TestSuite;
import net.sourceforge.jwebunit.junit.WebTestCase;
import net.sourceforge.jwebunit.util.TestingEngineRegistry;

/**
 *
 * @author Chris.Prince
 */
public abstract class AbstractFMDBTestCase extends WebTestCase {
    
    
    // Set the properties for the Keystore and Truststore to use with HTTPS
    static {
       // the certificate was added to the application server keystore and marked as trusted
        System.setProperty("javax.net.ssl.trustStore",
            "C:\\Apps\\Tools\\Sun\\Appserver\\domains\\domain1\\config\\keystore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "adminadmin");        
    }
    
    /** Creates a new instance of AbstractFMDBTestCase */
    public AbstractFMDBTestCase(String name) {
        super(name);        
    }
    
    public static Test suite() {
        final TestSuite suite = new TestSuite(AbstractFMDBTestCase.class);
        
        return suite;
    }
    
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    /** Brings the test client to the default home page.
     */
    protected void setUp() throws Exception {
        super.setUp();
        
        System.getProperties().put("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.getProperties().put("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient.HttpMethodDirector", "fatal");
        System.getProperties().put("net.sourceforge.jwebunit.htmlunit.HtmlUnitDialog.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.getProperties().put("org.apache.commons.logging.simplelog.log.net.sourceforge.jwebunit.htmlunit.HtmlUnitDialog", "fatal");
        
        setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
        getTestContext().setUserAgent("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; InfoPath.1; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30)");
        getTestContext().setBaseUrl("https://localhost:8181/FMDB");
        getTester().setScriptingEnabled(false);
        beginAt("/");
        
    }
    
 
    // ------------------------------------------------------------------------
    // Functional Test helpers
    // ------------------------------------------------------------------------
    
    /** Performs a login which is expected to be a successful login.  Does
     * not check the target result.
     */
    public void loginVld(final String user, final String pass ) {
        assertFormPresent("loginForm");
        setWorkingForm("loginForm");
        
        assertFormElementPresent("j_username");
        setTextField("j_username", user);
        
        assertFormElementPresent("j_password");
        setTextField("j_password", pass);
        
        assertSubmitButtonPresent("Login");
        submit("Login");
    }
    
    
    /** Performs a login which is expected to be an unsuccessful login.  Does
     * not check the target result.
     * 
     * Submits the password "asdf"
     * 
     * @param user - the username to submit with the loging
     */
    public void loginInVld(final String user ) {
        assertFormPresent("loginForm");
        setWorkingForm("loginForm");
        
        assertFormElementPresent("j_username");
        setTextField("j_username", user);
        
        assertFormElementPresent("j_password");
        setTextField("j_password", "asdf");
        
        assertSubmitButtonPresent("Login");
        submit("Login");
    }
    
    /**
     * Performs a logout.
     */
    public void logout() {
        // Log out
        assertLinkPresentWithImage("/login/images/b_exit_service.gif");
        clickLinkWithImage("/login/images/b_exit_service.gif");
    }
    
    // ------------------------------------------------------------------------
    // Assertion Helpers
    // ------------------------------------------------------------------------
    
}
