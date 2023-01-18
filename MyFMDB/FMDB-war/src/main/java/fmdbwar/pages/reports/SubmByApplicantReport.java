/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SubmByApplicantReport.java                     *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package fmdbwar.pages.reports;

import fmdbwar.Constants;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * Backing bean for the Submission by Applicant Report
 */
@Named("SubmByApplicantReport")
@ViewScoped
public class SubmByApplicantReport implements Serializable {

    private String reportView = null; // reportView variable used in the JSP
    private SelectItem[] reportViewOptions = null; // reportView options used by the drop down
    private String company = null; // company variable used by the text field in the JSP

    /** Creates a new instance of SubmByApplicantReport */
    public SubmByApplicantReport() {
    }

    /**
     * Runs the report.
     *
     * @return
     */
    public String runReportAction() {

        try {
            this.navigateToReportViewerPage();
        } catch (IOException e) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(e.toString()));
            e.printStackTrace();
            return null;
        }

        return null;
    }
        /**
     * Moving reporting over from Crystal Reports to OpenCSV which requires a
     * new servlet for each report
     * @throws java.io.IOException
     */
    protected void navigateToReportViewerPage() throws IOException {

        try {
            //Pass the report parameters on to the servlet
            HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.getSession().setAttribute("reportView", getReportViewValue());
            request.getSession().setAttribute("company", company);
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/pages/reports/SubmByApplicantReport");
        } catch (IOException ex) {
            throw ex;
        }
    }

    /**
     * Returns the descriptive value of reportView as selected by the user in
     * the JSP
     *
     * @return
     */
    public String getReportViewValue() {        
        if (getReportView().equals(Constants.ACTIVE_CODE)) {
            return Constants.ACTIVE;
        } else {
            return Constants.COMPLETED;
        }
    }
    
    /**
     * Returns the list of reportView options. Used by the JSP
     *
     * @return
     */
    public SelectItem[] getReportViewOptions() {
        if (reportViewOptions == null) {
            reportViewOptions = new SelectItem[]{
                new SelectItem(Constants.ACTIVE_CODE, Constants.ACTIVE),
                new SelectItem(Constants.COMPLETED_CODE, Constants.COMPLETED)};
        }
        return reportViewOptions;
    }

    /**
     * @return the reportView
     */
    public String getReportView() {
        return reportView;
    }

    /**
     * @param reportView the reportView to set
     */
    public void setReportView(String reportView) {
        this.reportView = reportView;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }
    
}