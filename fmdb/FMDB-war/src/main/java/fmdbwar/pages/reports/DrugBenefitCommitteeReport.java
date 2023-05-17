/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        DrugBenefitCommitteeReport.java                *
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
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * Backing bean for the Drug Benefit Committee Report
 */
@Named("DrugBenefitCommitteeReport")
@ViewScoped
public class DrugBenefitCommitteeReport implements Serializable {

    private String reportView = null; // reportView from the JSP
    private String sortBy = null; // sortBy from the JSP
    private SelectItem[] reportViewOptions = null; // contains the reportView drop down options
    private SelectItem[] sortByOptions = null; // contains the sortBy drop down options

    /** Creates a new instance of DrugBenefitCommitteeReport */
    public DrugBenefitCommitteeReport() {
    }

    /**
     * Run the report
     *
     * @return
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
     */
    protected void navigateToReportViewerPage() throws IOException {

        try {
            //Pass the report parameters on to the servlet
            HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.getSession().setAttribute("reportView", getReportViewValue());
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/pages/reports/DrugBenefitCommitteeReport");
        } catch (IOException ex) {
            throw ex;
        }
    }

    /**
     * This method is called by the JSP to update the Sort By drop down list
     *
     * @param event
     * @return
     */
    public String updateView(ValueChangeEvent event) {
        
        reportView = (String) event.getNewValue();
        if (reportView.equals(Constants.ACTIVE_CODE)) {
            setSortBy(Constants.ACTIVE_BY_TARGET_DATE_CODE);
        }
        if (reportView.equals(Constants.COMPLETED_CODE)) {
            setSortBy(Constants.COMPLETE_BY_ACTUAL_DATE_CODE);
        }
        return null;
    }

    /**
     * Returns the descriptive value of the reportView selected by the user in
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
     * Returns the reportView options for the drop down list displayed in the
     * JSP
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
     * Returns the sortBy options for the drop down list displayed in the JSP
     *
     * @return
     */
    public SelectItem[] getSortByOptions() {
        if (sortByOptions == null) {
            sortByOptions = new SelectItem[]{
                new SelectItem(Constants.ACTIVE_BY_TARGET_DATE_CODE,
                        Constants.ACTIVE_BY_TARGET_DATE),
                new SelectItem(Constants.COMPLETE_BY_ACTUAL_DATE_CODE,
                        Constants.COMPLETE_BY_ACTUAL_DATE)};
        }
        return sortByOptions;
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
     * @return the sortBy
     */
    public String getSortBy() {
        return sortBy;
    }

    /**
     * @param sortBy the sortBy to set
     */
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
    
}