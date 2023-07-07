/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        SingleDrugSubStatusReport.java                 *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package fmdbwar.pages.reports;



import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Backing bean for the Single Drug Sub Status Report
 */
public class SingleDrugSubStatusReport implements Serializable {


    private String chemicalName;

    /** Creates a new instance of SingleDrugSubStatusReport */
    public SingleDrugSubStatusReport() {
    }

    /**
     * Runs the report.
     */
    public void runReportAction() {
        try {
            navigateToReportViewerPage();
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
    protected void navigateToReportViewerPage()
       throws IOException {
        
        try {
            //Pass the report parameters on to the servlet
            HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.getSession().setAttribute("chemicalName", chemicalName);
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/pages/reports/SingleDrugSubmissionStatusReport");
        } catch (IOException ex) {
            throw ex;
        }
    }

    /**
     * @return the chemicalName
     */
    public String getChemicalName() {
        return chemicalName;
    }

    /**
     * @param chemicalName the chemicalName to set
     */
    public void setChemicalName(String chemicalName) {
        this.chemicalName = chemicalName;
    }    
}