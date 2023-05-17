/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        BufferedHttpResponseWrapper.java               *
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * HttpServletResponseWrapper that buffers all output rather
 * than writing it to the client.
 * 
 * @author greg.perkins
 */
public class BufferedHttpResponseWrapper extends HttpServletResponseWrapper{

    /**
     * Buffers
     */
    ByteArrayOutputStream baos;
    BufferedServletOutputStream os;
    PrintWriter pw;
    
    /**
     * Creates a new BufferedHttpResponseWrapper
     * @param response HttpServletResponse - Response object to wrap
     */
    public BufferedHttpResponseWrapper(HttpServletResponse response){
        //Populate the wrapped response
        super(response);
        
        //Create our buffers
        baos = new ByteArrayOutputStream();
        os = new BufferedServletOutputStream(baos);
        pw = new PrintWriter(baos);
    }

    /**
     * Returns the buffered OutputStream
     * @return ServletOutputStream
     * @throws java.io.IOException
     */
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return os;
    }
    
    /**
     * Returns the buffered PrintWriter
     * @return PrintWriter
     */
    @Override
    public PrintWriter getWriter(){
        return pw;
    }
    
    /**
     * Returns the contents of the buffer.
     * @return byte[]
     */
    public byte[] getBytes(){
        return baos.toByteArray();
    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void flushBuffer() throws IOException {
        
    }

    @Override
    public void resetBuffer() {
        
    }

    public void clearBuffers() {
        baos = new ByteArrayOutputStream();
        os = new BufferedServletOutputStream(baos);
        pw = new PrintWriter(baos);
    }
    
   
}

