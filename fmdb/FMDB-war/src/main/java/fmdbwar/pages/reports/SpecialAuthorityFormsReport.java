/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SpecialAuthorityFormsReport.java               *
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
 * Backing bean for the Special Authority Forms Report
 *
 * @author john.au
 */
@Named("SpecialAuthorityFormsReport")
@ViewScoped
public class SpecialAuthorityFormsReport implements Serializable {

    private String reportView = null; // reportView variable used in the JSP
    private SelectItem[] reportViewOptions = null; // reportView options used by the drop down

    /** Creates a new instance of SpecialAuthorityFormsReport */
    public SpecialAuthorityFormsReport() {
    }

    /**
     * Runs the report.
     */
    public void runReportAction() {

        try {
            this.navigateToReportViewerPage();
        } catch (IOException e) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(e.toString()));
            e.printStackTrace();
        }
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
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/pages/reports/SpecialAuthorityFormsReport");
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
        if (reportView.equals(Constants.ACTIVE_CODE)) {
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
    
}