/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fmdbwar.servlet;

import ca.bc.gov.moh.fmdb.business.VSingleDrugSubStatusFacadeLocal;
import ca.bc.gov.moh.fmdb.entity.VDins;
import ca.bc.gov.moh.fmdb.entity.VReviewQuestions;
import ca.bc.gov.moh.fmdb.entity.VSingleDrugSubStatus;
import com.opencsv.CSVWriter;
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
 * @author trevor.schiavone
 */
public class SingleDrugSubmissionStatusReportServlet extends HttpServlet{
    
    @EJB
    private VSingleDrugSubStatusFacadeLocal vSingleDrugSubStatusFacade;
    private static final String APP_NAME = "Formulary management";
    private static final String REPORT_NAME = "Single Drug Submission Status";
    private static final String FILE_NAME = "SingleDrugSubStatusReport.csv";
    
     
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
        response.setHeader("Content-Disposition", "attachment; filename=\"" + FILE_NAME + "\"");
        response.setHeader("Cache-Control", "no-store, no-cache"); // Fix for downloading attachments in IE over SSL
        response.setHeader("Pragma", "private"); // Fix for downloading attachments in IE over SSL
        
        
        String chemicalName = (String) request.getSession().getAttribute("chemicalName");
        List<VSingleDrugSubStatus> reportData = vSingleDrugSubStatusFacade.findReportDataByCriteria(chemicalName);
        List<VDins> dins = vSingleDrugSubStatusFacade.findDinsData(chemicalName);
        List<VReviewQuestions> reviewQuestions = vSingleDrugSubStatusFacade.findReviewQuestionsData(chemicalName);
        
        String[] dinIds = new String[dins.size() + 1];
        dinIds[0] = "DINs";
        
        String[] questions = new String[reviewQuestions.size() + 1];
        questions[0] = "QuestionTxt";
        
        String[] conclusions = new String[reviewQuestions.size() + 1];
        conclusions[0] = "Conclusion";
        
        for (int i = 0; i < dins.size(); i++) {
            dinIds[i + 1] = dins.get(i).getDinNoString();
        }
        
        for (int i = 0; i < reviewQuestions.size(); i++) {
            questions[i + 1] = reviewQuestions.get(i).getQuestionTxt();
            conclusions[i + 1] = reviewQuestions.get(i).getQuestionTxt();
        }
        
        PrintWriter out = response.getWriter();
        
        try {
        
            CSVWriter writer = new CSVWriter(out);         

            // Write report headers to CSV
            writer.writeAll(buildReportHeader(chemicalName, reportData.size()));

            // Write data to CSV
            for (VSingleDrugSubStatus submission : reportData) {
                List<String[]> reportSection = submission.buildReportSection(dinIds, questions, conclusions); 
                writer.writeAll(reportSection);
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
    
    private List<String[]> buildReportHeader(String chemicalName, int totalSubmissions) {

        List<String[]> reportHeader = new ArrayList();
        String[] app = {APP_NAME};
        String[] blank = {};
        String[] report = {REPORT_NAME};
        String[] chemName = {"Chemical Name", chemicalName};
        String[] totalSubs = {"Total Submissions", String.format("%1d", totalSubmissions)};
        
        reportHeader.add(app);
        reportHeader.add(blank);
        reportHeader.add(report);
        reportHeader.add(blank);
        reportHeader.add(chemName);
        reportHeader.add(totalSubs);
        reportHeader.add(blank);
        
        
        return reportHeader;
            
    }
}
