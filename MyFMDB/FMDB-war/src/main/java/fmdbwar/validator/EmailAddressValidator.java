/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        EmailAddressValidator.java                     *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fmdbwar.validator;

/**
 *
 * @author Johnson.Ding
 */
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;
import java.util.StringTokenizer;

/**
 * A class to provide stronger validation of email addresses.
 *
 */
public class EmailAddressValidator {

    public static boolean isValidEmailAddress(String emailAddress) {
        // a null string is invalid
        if (emailAddress == null) {
            return false;
        }

        // a string without a "@" is an invalid email address
        if (emailAddress.indexOf("@") < 0) {
            return false;
        }

        // a string without a "."  is an invalid email address
        if (emailAddress.indexOf(".") < 0) {
            return false;
        }

        if (lastEmailFieldTwoCharsOrMore(emailAddress) == false) {
            return false;
        }

        try {
            InternetAddress.parse(emailAddress);
            return true;
        } catch (AddressException ae) {
            // log exception
            return false;
        }
    }

    /**
     * Returns true if the last email field (i.e., the country code, or something
     * like .com, .biz, .cc, etc.) is two chars or more in length, which it really
     * must be to be legal.
     */
    private static boolean lastEmailFieldTwoCharsOrMore(String emailAddress) {
        if (emailAddress == null) {
            return false;
        }
        StringTokenizer st = new StringTokenizer(emailAddress, ".");
        String lastToken = null;
        while (st.hasMoreTokens()) {
            lastToken = st.nextToken();
        }

        if (lastToken.length() >= 2) {
            return true;
        } else {
            return false;
        }
    }
}

