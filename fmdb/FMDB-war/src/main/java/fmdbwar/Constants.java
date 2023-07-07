/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        Constants.java                                 *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package fmdbwar;

/**
 *  
 *  Contains contants used throughout the Web tier.
 *  
 *  @author david.tulk
 *  
 */
public class Constants {
    
    //Loggers
    public static final String WEB_LOGGER = "WEB_LOGGER";
    
    //Actions - navigation
    
    //Admin Company Contacts
    public static final String SELECTED_COMPANY = "SelectedCompany";
    public static final String ADDRESS_TYPE_COMPANY = "1";
    public static final String ADDRESS_TYPE_CONTACT = "2";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String PHONE_NUMBER_PATTERN = "\\(\\d{3}\\)\\d{3}-\\d{4}";
    public static final String COMPANY_WORK = " Company Work ";
    public static final String CONTACT_WORK = " Contact Work ";
    public static final String CONTACT_CELL = " Contact Cell ";
    public static final String EMPTY_STRING = "";
    public static final String CONTACT_NAMES_DROP_DOWN_TITLE = "Select contact";
    public static final String SELECTED_CONTACT = "selectedContact";
    public static final String COUNTRIES_DROP_DOWN_TITLE = "Select Country";
    public static final String COMPANY_SEARCH_BEAN = "CompanySearchBean";
    public static final String COMPANY_SEARCH = "companySearch";
    public static final String INVALID = "Invalid";
    public static final String NUMBER_FORMAT = "Number format (XXX)XXX-XXXX";
    public static final String SHOW_ADMIN_MENU_PAGE = "showAdminMenuPage";
    public static final String COMPANY_FAX = " Company Fax ";
    public static final String CONTACT_FAX = " Contact Fax ";
    public static final String SPACE_STRING = " ";
    public static final String CONTACT_MAP = "contactMap";
    public static final String PROV_STATE_NOT_EXIST = "The input prov/state doesn't exist!";
    public static final String INVALID_EMAIL_ADDRESS = "Invalid email address!"; 
    
    //reports
    public static final String ACTIVE = "Active";
    public static final String COMPLETED = "Completed";
    public static final String COMPLETED_BY_YEAR = "Completed by Year";
    public static final String COMPLETED_BY_TYPE = "Completed by Type";
    public static final String ACTIVE_BY_TARGET_DATE = "Active by Target Date";
    public static final String COMPLETE_BY_ACTUAL_DATE = "Complete by Actual Date";
    public static final String ACTIVE_CODE = "1";
    public static final String COMPLETED_CODE = "2";
    public static final String COMPLETED_BY_YEAR_CODE = "1";
    public static final String COMPLETED_BY_TYPE_CODE = "2";
    public static final String ACTIVE_BY_TARGET_DATE_CODE = "1";
    public static final String COMPLETE_BY_ACTUAL_DATE_CODE = "2";
    public static final String NO_RECORD_MATCH = "No records match the selected parameters";
    
    //Other
    public static final String DRUG_SUBMISSION = "drugSubmission";
    public static final String FLAG_NO = "N";
    public static final String FLAG_YES = "Y";
    public static final String COUNTRY_CODE_CANADA = "1";
    public static final String COUNTRY_CODE_US = "2";
    public static final String FUNCTION = "function";
    public static final String FUNCTION_ADMIN = "Admin";
    public static final String FUNCTION_REPORTS = "Reports";
    public static final String FUNCTION_SUBMISSION = "Submission";
    public static final String FUNCTION_DRUG_SEARCH = "drugSearch";
    public static final String ADMIN_TYPE = "adminType";
    public static final String ADMIN_BIA_REQUESTED_STATUS_CODE = "adminBiaRequestedStatusCode";
    public static final String ADMIN_COMPANY_CONTACTS = "adminCompanyContacts";
    public static final String ADMIN_COMPANY_TYPE = "adminCompanyType";
    public static final String ADMIN_PHARMA_CARE_STATUS_CODE = "adminPharmaCareStatusCode";
    public static final String ADMIN_PLA_STATUS_CODES = "adminPlaStatusCodes";
    public static final String ADMIN_REVIEWER = "adminReviewer";
    public static final String ADMIN_SUBMISSION_TYPE = "adminSubmissionType";
    public static final String MENU_FROM = "menuFrom";
}
