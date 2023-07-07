/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        DrugBenefitCommitteeReportServlet.java         *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fmdbwar.servlet;

import com.opencsv.CSVWriter;
import ca.bc.gov.moh.fmdb.business.VDrugBenefitCommitteeFacadeLocal;
import ca.bc.gov.moh.fmdb.entity.VDrugBenefitCommittee;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adam.hoplock
 */
public class DrugBenefitCommitteeReportServlet extends HttpServlet {

    @EJB
    private VDrugBenefitCommitteeFacadeLocal vDrugBenefitCommitteeFacade;
    private static String appName = "Formulary management";
    private static String reportName = "Drug Benefit Committee";
    private static String fileName = "DrugBenefitCommitteeReport.csv";

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Content-Type", "text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setHeader("Cache-Control", "no-store, no-cache"); // Fix for downloading attachments in IE over SSL
        response.setHeader("Pragma", "private"); // Fix for downloading attachments in IE over SSL
        PrintWriter out = response.getWriter();

        try {
            CSVWriter writer = new CSVWriter(out);

            String reportView = (String) request.getSession().getAttribute("reportView");
            List<VDrugBenefitCommittee> reportData = vDrugBenefitCommitteeFacade.findReportDataByCriteria(reportView);

            // Write report headers to CSV
            writer.writeAll(buildReportHeader(reportView, reportData.size()));

            // Write data to CSV
            for (VDrugBenefitCommittee rowData : reportData) {
                writer.writeNext(rowData.toReportRow());
            }

            writer.close();
            out.close();

         } catch (Exception e) {
             // Unexpected exception; send back to client for now.
             throw new ServletException(e);
         }
    }

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Generates the report headers for the CSV file
     */
    private List<String[]> buildReportHeader(String reportView, int totalSubmissions) {

        List<String[]> reportHeader = new ArrayList();
        String[] app = {appName};
        String[] blank = {};
        String[] report = {reportName};
        String[] misc = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
            "Total Number of Submissions:"};
        String[] parameters = {"Report View:",
            reportView,
            "", "", "", "", "", "", "", "", "", "", "", "", "",
            String.format("%1d", totalSubmissions)};
        String[] columnNames = {"Sub No",
            "Chemical(Trade) Name",
            "Applicant",
            "Indication",
            "Sub Type",
            "CDR",
            "Review Start Dt",
            "Date of CDR Recom",
            "CDR Recomm",
            "DBC Target Dt",
            "DBC Actual Dt",
            "DBC rec Final Dt",
            "DBC Days",
            "DBC rec Notes",
            "DBC Notes",
            "PSD Status"
        };

        reportHeader.add(app); // Add app name
        reportHeader.add(blank); // Add blank row
        reportHeader.add(report); // Add report name
        reportHeader.add(misc); // Add additional report info row
        reportHeader.add(parameters); // Add report parameters
        reportHeader.add(blank); // Add blank row
        reportHeader.add(columnNames); // Add report column names

        return reportHeader;
    }
}
