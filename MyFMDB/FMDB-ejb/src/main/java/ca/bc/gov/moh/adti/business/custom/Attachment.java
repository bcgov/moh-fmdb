/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.bc.gov.moh.adti.business.custom;

import java.io.Serializable;
import java.util.Date;


/**
 * Decompiled and copied from the old ADTI class that was previously a dependency
 * the actual source code no longer exists and was never meant to be a shared library
 * it didn't make sense to keep as a compiled dependency
 * 
 * Need to keep the old package name as these are blobs serialized in the database
 * and wont de-serialize with a different package name
 * @author trevor.schiavone
 */
public class Attachment
    implements Serializable
{

    private long id;
    private String filename;
    private String filetype;
    private long size;
    private byte file[];
    private Date createDate;
    private String createdBy;

    public Attachment()
    {
    }

    public Attachment(long id, String name, String filetype, long size, byte file[], 
            Date createDate, String createdBy)
    {
        this.id = id;
        filename = name;
        this.size = size;
        this.file = file;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.filetype = filetype;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public String getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public long getSize()
    {
        return size;
    }

    public void setSize(long size)
    {
        this.size = size;
    }

    public byte[] getFile()
    {
        return file;
    }

    public void setFile(byte file[])
    {
        this.file = file;
    }

    public String getFiletype()
    {
        return filetype;
    }

    public void setFiletype(String filetype)
    {
        this.filetype = filetype;
    }
}
