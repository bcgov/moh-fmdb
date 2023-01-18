/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        LoginTest.java                                 *
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

/**
 *
 * @author Chris.Prince
 */
public class LoginTest extends AbstractFMDBTestCase implements Test {

    /** Creates a new instance of LoginTest */
    public LoginTest(String name) {
        super(name);
    }

    // ------------------------------------------------------------------------
    // JUnit Overrides and Support
    // ------------------------------------------------------------------------
    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public static junit.framework.Test suite() {
        final TestSuite suite = (junit.framework.TestSuite) AbstractFMDBTestCase.suite();
        suite.addTestSuite(LoginTest.class);

        return suite;
    }

    // ------------------------------------------------------------------------
    // Unit Tests
    // ------------------------------------------------------------------------
    /** The default URL we start at in the setup redirects  to the Login page.
     */
    public void testDefaultPageForwardsToLogin() {
        setName("The default page of a new session redirects to the page \"Application Logon Page\"");
        assertFormPresent("loginForm");

    }

    /** Check that the login form is present. Login and be redirected to the
     * start page, GIS Recipient Search
     */
    public void testLoginForm() {
        //System.out.println("**** Testing Login ***************************");
        setName("Login with the Dave:sunny89 form and then we are redirected to the search form.");

        loginVld("Dave", "sunny89");

        assertTitleEquals("Login Proxy - Formulary Management");

        assertLinkPresent("redirectHereLink");
        assertFormPresent("loginProxyForm");

//        getTester().setScriptingEnabled(true);
 //       clickLink("redirectHereLink");
  //      getTester().setScriptingEnabled(false);
   //     assertTitleEquals("Drug Submission Search Page - Formulary Management");
    }

}
