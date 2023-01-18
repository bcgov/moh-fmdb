/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        AttachmentRetrievalServlet.java                *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package ca.bc.gov.moh.fmdb.web.servlet;

import ca.bc.gov.moh.adti.business.custom.Attachment;
import ca.bc.gov.moh.fmdb.web.pub.PublicAcessVwControler;
import fmdbwarpub.Constants;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *  This servlet is used to retrieve an attachment and send it to the client.
 *  @author david.tulk
 */
public class AttachmentRetrievalServlet extends HttpServlet {
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //Get the service
            //Note that for performance reasons, the service may be placed in application scope at a later date.
            PublicAcessVwControler controller;
            HttpSession session = request.getSession();
            if(session != null) {
                controller = (PublicAcessVwControler) session.getAttribute(Constants.PUBLIC_WEB_CONTROLLER_KEY);
            }
            else {
                throw new Exception("Your session has timed out: Please perform the search again.");
            }
            
            
            //Get submission id from request            
            String submissionIdString = request.getParameter(Constants.QUERY_PARAM_SUBMISSION_ID);
            long submissionId = 0;
            try {
                submissionId = Long.parseLong(submissionIdString);
            }
            catch(NumberFormatException nfe) {
                //Not a long            
                throw new Exception("Invalid submission id.");
            }           
            
            //-----------------> VERY IMPORTANT!!! <--------------
            //There should be a security check performed here to ensure that the attachment
            //associated with this submission id can in fact be accessed. The check is important
            //because it is trivial for the end user to manually set the submission id in the
            //query parameter
            //Is the above necessary? submissions that can't be accessed wouldn't be accessible
            //by the view anyway
            
            
            Attachment attachment = controller.getAttachment(submissionId);
            
            response.setContentType(attachment.getFiletype());
            ServletOutputStream os = response.getOutputStream();
            
            try {
                os.write(attachment.getFile());
                os.flush();                
            }
            finally { 
                os.close();
            }
        }
        catch (Exception e) {
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Attachment</title>");  
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Attachment could not be found</h1>");
                out.println("</body>");
                out.println("</html>");

            } 
            finally { 
                out.close();                
            }
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
    * Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
    * Returns a short description of the servlet.
    */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
