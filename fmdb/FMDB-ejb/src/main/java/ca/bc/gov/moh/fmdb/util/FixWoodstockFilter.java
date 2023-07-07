/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        FixWoodstockFilter.java                        *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.bc.gov.moh.fmdb.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Fixes IE7 Security errors when accesssing Woodstock via HTTPS
 * 
 * This filter should be applied to the FacesServlet in web.xml
 * 
 * @author greg.perkins
 */
public class FixWoodstockFilter implements Filter{

    /**
     * Replaces relative calls to the bootstrap.js file with 
     * absolute ones when accessing via HTTPS.
     * 
     * @param request ServletRequest
     * @param response ServletResponse
     * @param chain FilterChain
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       String requestURI = ((HttpServletRequest)request).getRequestURI();
       String queryString = ((HttpServletRequest)request).getQueryString();
        if (
           !requestURI.contains("images") 
        && !(queryString!=null && queryString.contains("lnkViewPDF"))
        ){
       BufferedHttpResponseWrapper wrapper = new BufferedHttpResponseWrapper((HttpServletResponse)response);
       chain.doFilter(request, wrapper);
       byte[] data = wrapper.getBytes();
       String responseTxt = new String(wrapper.getBytes());
       if (responseTxt.indexOf("Select contact")>=0){
           responseTxt = responseTxt.replaceAll(". Select contact .", "- Select contact -");
           System.out.println(responseTxt);
       }
        if (request.isSecure()){
                responseTxt = responseTxt.replaceAll( ((HttpServletRequest)request).getContextPath() + "/theme/com/sun/webui/jsf/suntheme4_2-080320/javascript/bootstrap.js", "https://"+request.getServerName()+":"+request.getServerPort() + ((HttpServletRequest)request).getContextPath() + "/theme/com/sun/webui/jsf/suntheme4_2-080320/javascript/bootstrap.js");
        }
       data = responseTxt.getBytes();

       response.getOutputStream().write(data);
       response.getOutputStream().flush();
       response.getOutputStream().close();
       }else{
           chain.doFilter(request, response);
       }
    }

    /**
     * Does nothing
     * @param config FilterConfig
     * @throws javax.servlet.ServletException
     */
    public void init(FilterConfig config) throws ServletException {}

    /**
     * Does nothing
     */
    public void destroy() {}

}
