/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        BufferedServletOutputStream.java               *
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
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 * Simple extension of a ServletOutputStream that writes to
 * a buffer rather than the response.
 *
 * @author greg.perkins
 */
public class BufferedServletOutputStream extends ServletOutputStream{

    /**
     * Buffer to store the response in
     */
    private ByteArrayOutputStream baos;

    /**
     * Creates a new BufferedServletOutputStream
     * @param baos ByteArrayOutputStream - Buffer
     */
    public BufferedServletOutputStream(ByteArrayOutputStream baos){
        this.baos = baos;
    }

    /**
     * Writes to the buffer rather than the response
     * @param b
     * @throws java.io.IOException
     */
    @Override
    public void write(int b) throws IOException {
        baos.write(b);
    }

    public void setWriteListener(WriteListener writeListener) {

    }

    public boolean isReady() {
      return true;
    }
}
