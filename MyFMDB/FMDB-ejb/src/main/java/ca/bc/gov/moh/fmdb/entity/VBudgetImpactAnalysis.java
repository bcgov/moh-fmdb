/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VBudgetImpactAnalysis.java                     *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.bc.gov.moh.fmdb.entity;

import java.io.Serializable;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQuery(name = "VBudgetImpactAnalysis.findAll", 
    query = "select o from VBudgetImpactAnalysis o")
@Table(name = "FMDB_BUDGET_IMPACT_ANALYSIS_VW")
public class VBudgetImpactAnalysis implements Serializable {
    private String applicant;
    @Column(name="BIA_ACTUAL_DATE")
    private Timestamp biaActualDate;
    @Column(name="BIA_ASSIGNED_NAME")
    private String biaAssignedName;
    @Column(name="BIA_DAYS")
    private Long biaDays;
    @Column(name="BIA_DESC", nullable = false)
    private String biaDesc;
    @Column(name="BIA_NOTES")
    private String biaNotes;
    @Column(name="BIA_REQUESTED_DATE")
    private Timestamp biaRequestedDate;
    @Column(name="BIA_REQUESTED_NAME")
    private String biaRequestedName;
    @Column(name="BIA_TARGET_DATE")
    private Timestamp biaTargetDate;
    private String cdr;
    @Column(name="CHEMICAL_NAME", nullable = false)
    private String chemicalName;
    private String indication;
    @Column(name="PSD_STATUS", nullable = false)
    private String psdStatus;
    @Id
    private Long rn;
    @Column(name="SUBMISSION_NO", nullable = false)
    private Long submissionNo;
    @Column(name="SUB_TYPE", nullable = false)
    private String subType;

    public VBudgetImpactAnalysis() {
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public Timestamp getBiaActualDate() {
        return biaActualDate;
    }

    public void setBiaActualDate(Timestamp biaActualDate) {
        this.biaActualDate = biaActualDate;
    }

    public String getBiaAssignedName() {
        return biaAssignedName;
    }

    public void setBiaAssignedName(String biaAssignedName) {
        this.biaAssignedName = biaAssignedName;
    }

    public Long getBiaDays() {
        return biaDays;
    }

    public void setBiaDays(Long biaDays) {
        this.biaDays = biaDays;
    }

    public String getBiaDesc() {
        return biaDesc;
    }

    public void setBiaDesc(String biaDesc) {
        this.biaDesc = biaDesc;
    }

    public String getBiaNotes() {
        return biaNotes;
    }

    public void setBiaNotes(String biaNotes) {
        this.biaNotes = biaNotes;
    }

    public Timestamp getBiaRequestedDate() {
        return biaRequestedDate;
    }

    public void setBiaRequestedDate(Timestamp biaRequestedDate) {
        this.biaRequestedDate = biaRequestedDate;
    }

    public String getBiaRequestedName() {
        return biaRequestedName;
    }

    public void setBiaRequestedName(String biaRequestedName) {
        this.biaRequestedName = biaRequestedName;
    }

    public Timestamp getBiaTargetDate() {
        return biaTargetDate;
    }

    public void setBiaTargetDate(Timestamp biaTargetDate) {
        this.biaTargetDate = biaTargetDate;
    }

    public String getCdr() {
        return cdr;
    }

    public void setCdr(String cdr) {
        this.cdr = cdr;
    }

    public String getChemicalName() {
        return chemicalName;
    }

    public void setChemicalName(String chemicalName) {
        this.chemicalName = chemicalName;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getPsdStatus() {
        return psdStatus;
    }

    public void setPsdStatus(String psdStatus) {
        this.psdStatus = psdStatus;
    }

    public Long getRn() {
        return rn;
    }

    public void setRn(Long rn) {
        this.rn = rn;
    }

    public Long getSubmissionNo() {
        return submissionNo;
    }

    public void setSubmissionNo(Long submissionNo) {
        this.submissionNo = submissionNo;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }
  
    /**
     * Moving reporting over from Crystal Reports to OpenCSV which requires a
     * String array for each row of data
     */
    public String[] toReportRow() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String[] arr = {
            (submissionNo == null) ? "" : String.format("%1d", submissionNo), 
            chemicalName, 
            applicant, 
            indication, 
            subType, 
            cdr, 
            biaRequestedName, 
            biaAssignedName, 
            (biaRequestedDate == null) ? "" : dateFormatter.format(biaRequestedDate), 
            (biaTargetDate == null) ? "" : dateFormatter.format(biaTargetDate), 
            (biaActualDate == null) ? "" : dateFormatter.format(biaActualDate), 
            (biaDays == null) ? "" : String.format("%1d", biaDays), 
            biaNotes, 
            psdStatus
        };
        return arr;
    }
}
