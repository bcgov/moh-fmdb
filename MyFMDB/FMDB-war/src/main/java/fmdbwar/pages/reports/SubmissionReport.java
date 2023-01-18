/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SubmissionReport.java                          *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package fmdbwar.pages.reports;


import fmdbwar.Constants;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;


/**
 * Backing bean for the Submission Report
 */
@Named("SubmissionReport")
@ViewScoped
public class SubmissionReport implements Serializable {

    private String reportView = null; 
    private String sortBy = null; 
    private SelectItem[] reportViewOptions = null; 
    private SelectItem[] sortByOptions = null;
    private Date fromDate = null; 
    private Date toDate = null; 
    private boolean sortByDisabled = true; 
                                           
    private boolean fromDateDisabled = true; 
                                             
    private boolean toDateDisabled = true; 
                                           
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 

    public SubmissionReport() {
    }

    /**
     * Action method to run the report
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
    protected void navigateToReportViewerPage()
       throws IOException {
        
        try {
            //Pass the report parameters on to the servlet
            HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.getSession().setAttribute("reportView", getReportViewValue());
            request.getSession().setAttribute("sortByValue", getSortByValue());
            request.getSession().setAttribute("fromDate", getFromDate());
            request.getSession().setAttribute("toDate", getToDate());
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/pages/reports/SubmissionReport");
        } catch (IOException ex) {
            throw ex;
        }
    }

    /**
     * Enables or disables the JSP fields based on what is selected in the
     * reportView drop down list
     *
     * @return
     */
    public String updateView(ValueChangeEvent event) {
        
        reportView = (String) event.getNewValue();
        if (reportView.equals(Constants.COMPLETED_CODE)) {
            setSortByDisabled(false);
            setFromDateDisabled(false);
            setToDateDisabled(false);
        }
        return null;
    }


    /**
     * Returns the descriptive representation of the reportView based on
     * what the user selects in the JSP
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
     * Returns the descriptive representation of the sortBy based on what the
     * user selects in the JSP
     *
     * @return
     */
    public String getSortByValue() {
        if (getReportViewValue().equals(Constants.ACTIVE)) {
            return "";
        } else {            
            if (sortBy.equals(Constants.COMPLETED_BY_YEAR_CODE)) {
                return Constants.COMPLETED_BY_YEAR;
            } else {
                return Constants.COMPLETED_BY_TYPE;
            }
        }
    }

    /**
     * Returns the reportView drop down list values. This method is
     * used in the JSP
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
     * Returns the sortBy drop down list values. This method is used in the JSP
     *
     * @return
     */
    public SelectItem[] getSortByOptions() {
        if (sortByOptions == null) {
            sortByOptions = new SelectItem[]{
                new SelectItem(Constants.COMPLETED_BY_YEAR_CODE,
                        Constants.COMPLETED_BY_YEAR),
                new SelectItem(Constants.COMPLETED_BY_TYPE_CODE,
                        Constants.COMPLETED_BY_TYPE)};
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

    /**
     * @return the sortByDisabled
     */
    public boolean isSortByDisabled() {
        return sortByDisabled;
    }

    /**
     * @param sortByDisabled the sortByDisabled to set
     */
    public void setSortByDisabled(boolean sortByDisabled) {
        this.sortByDisabled = sortByDisabled;
    }

    /**
     * @return the fromDateDisabled
     */
    public boolean isFromDateDisabled() {
        return fromDateDisabled;
    }

    /**
     * @param fromDateDisabled the fromDateDisabled to set
     */
    public void setFromDateDisabled(boolean fromDateDisabled) {
        this.fromDateDisabled = fromDateDisabled;
    }

    /**
     * @return the toDateDisabled
     */
    public boolean isToDateDisabled() {
        return toDateDisabled;
    }

    /**
     * @param toDateDisabled the toDateDisabled to set
     */
    public void setToDateDisabled(boolean toDateDisabled) {
        this.toDateDisabled = toDateDisabled;
    }

    /**
     * @return the fromDate
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the toDate
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * @param toDate the toDate to set
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

}