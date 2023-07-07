/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VSpecialAuthForms.java                         *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

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
@NamedQuery(name = "VSpecialAuthForms.findAll", 
    query = "select o from VSpecialAuthForms o")
@Table(name = "FMDB_SPECIAL_AUTH_FORMS_VW")
public class VSpecialAuthForms implements Serializable {
    private String applicant;
    private String cdr;
    @Column(name="CHEMICAL_NAME", nullable = false)
    private String chemicalName;
    private String indication;
    @Column(name="PSD_STATUS", nullable = false)
    private String psdStatus;
    @Id
    private Long rn;
    @Column(name="SA_COMPLETED_DATE")
    private Timestamp saCompletedDate;
    @Column(name="SA_DAYS")
    private Long saDays;
    @Column(name="SA_INITIATED_DATE")
    private Timestamp saInitiatedDate;
    @Column(name="SA_NOTES")
    private String saNotes;
    @Column(name="SA_REQUIRED_YN")
    private String saRequiredYn;
    @Column(name="SA_TARGET_DATE")
    private Timestamp saTargetDate;
    @Column(name="SUBMISSION_NO", nullable = false)
    private Long submissionNo;
    @Column(name="SUB_TYPE", nullable = false)
    private String subType;

    public VSpecialAuthForms() {
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
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

    public Timestamp getSaCompletedDate() {
        return saCompletedDate;
    }

    public void setSaCompletedDate(Timestamp saCompletedDate) {
        this.saCompletedDate = saCompletedDate;
    }

    public Long getSaDays() {
        return saDays;
    }

    public void setSaDays(Long saDays) {
        this.saDays = saDays;
    }

    public Timestamp getSaInitiatedDate() {
        return saInitiatedDate;
    }

    public void setSaInitiatedDate(Timestamp saInitiatedDate) {
        this.saInitiatedDate = saInitiatedDate;
    }

    public String getSaNotes() {
        return saNotes;
    }

    public void setSaNotes(String saNotes) {
        this.saNotes = saNotes;
    }

    public String getSaRequiredYn() {
        return saRequiredYn;
    }

    public void setSaRequiredYn(String saRequiredYn) {
        this.saRequiredYn = saRequiredYn;
    }

    public Timestamp getSaTargetDate() {
        return saTargetDate;
    }

    public void setSaTargetDate(Timestamp saTargetDate) {
        this.saTargetDate = saTargetDate;
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
            (saInitiatedDate == null) ? "" : dateFormatter.format(saInitiatedDate), 
            (saTargetDate == null) ? "" : dateFormatter.format(saTargetDate), 
            (saCompletedDate == null) ? "" : dateFormatter.format(saCompletedDate), 
            (saDays == null) ? "" : String.format("%1d", saDays), 
            saNotes, 
            psdStatus
        };
        return arr;
    }
}
