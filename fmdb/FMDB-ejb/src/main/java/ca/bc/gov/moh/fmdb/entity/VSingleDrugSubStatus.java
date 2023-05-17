/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        VSingleDrugSubStatus.java                      *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package ca.bc.gov.moh.fmdb.entity;

import java.io.Serializable;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "VSingleDrugSubStatus.findAll", 
    query = "select o from VSingleDrugSubStatus o")
@Table(name = "FMDB_SINGLE_DRUG_SUB_STATUS_VW")
public class VSingleDrugSubStatus implements Serializable {
    @Column(name="APPLICANT_ADDRESS", nullable = false)
    private String applicantAddress;
    @Column(name="APPLICANT_NAME", nullable = false)
    private String applicantName;
    @Column(name="APPLICANT_NOTICE_COMPLETED_DT")
    private Timestamp applicantNoticeCompletedDt;
    @Column(name="BIA_ASSIGNED_TO")
    private String biaAssignedTo;
    @Column(name="BIA_COMPLETED_DT")
    private Timestamp biaCompletedDt;
    @Column(name="BIA_DAYS")
    private Long biaDays;
    @Column(name="BIA_INITIATE_DT")
    private Timestamp biaInitiateDt;
    @Column(name="BIA_NOTES")
    private String biaNotes;
    @Column(name="BIA_REQUESTED_BY")
    private String biaRequestedBy;
    @Column(name="BIA_STATUS", nullable = false)
    private String biaStatus;
    @Column(name="BIA_TARGET_DT")
    private Timestamp biaTargetDt;
    @Column(name="BRIEFING_NOTE_COMPLETED_DT")
    private Timestamp briefingNoteCompletedDt;
    @Column(name="CDR_DRUG_REVIEW")
    private String cdrDrugReview;
    @Column(name="CEDAC_ACTUAL_MEETING")
    private Timestamp cedacActualMeeting;
    @Column(name="CEDAC_NOTES")
    private String cedacNotes;
    @Column(name="CEDAC_RECOMMENDATION")
    private String cedacRecommendation;
    @Column(name="CEDAC_REVIEW_COMPLETED_DT")
    private Timestamp cedacReviewCompletedDt;
    @Column(name="CEDAC_REVIEW_DAYS")
    private Long cedacReviewDays;
    @Column(name="CEDAC_REVIEW_INITIATE_DT")
    private Timestamp cedacReviewInitiateDt;
    @Column(name="CEDAC_REVIEW_STATUS")
    private String cedacReviewStatus;
    @Column(name="CEDAC_REVIEW_TARGET_DT")
    private Timestamp cedacReviewTargetDt;
    @Column(name="CHEMICAL_ID", nullable = false)
    private Long chemicalId;
    private String chemicalName;
    @Column(name="CHEMICAL_TRADE_NAME")
    private String chemicalTradeName;
    @Column(nullable = false)
    private String city;
    @Column(name="COMPANY_DETAILS_COMMENTS")
    private String companyDetailsComments;
    @Column(name="COMPANY_TYPE", nullable = false)
    private String companyType;
    @Column(name="CONTACT_ADDRESS", nullable = false)
    private String contactAddress;
    @Column(name="CONTACT_CELL")
    private String contactCell;
    @Column(name="CONTACT_CITY", nullable = false)
    private String contactCity;
    @Column(name="CONTACT_COUNTRY", nullable = false)
    private String contactCountry;
    @Column(name="CONTACT_EMAIL")
    private String contactEmail;
    @Column(name="CONTACT_EXT")
    private String contactExt;
    @Column(name="CONTACT_FAX")
    private String contactFax;
    @Column(name="CONTACT_NAME")
    private String contactName;
    @Column(name="CONTACT_POSTAL_ZIP", nullable = false)
    private String contactPostalZip;
    @Column(name="CONTACT_PROV_STATE", nullable = false)
    private String contactProvState;
    @Column(name="CONTACT_WORK")
    private String contactWork;
    @Column(nullable = false)
    private String country;
    @Column(name="DATE_RELEASED")
    private Timestamp dateReleased;
    @Column(name="DBC_COMPLETED_DT")
    private Timestamp dbcCompletedDt;
    @Column(name="DBC_NOTES")
    private String dbcNotes;
    @Column(name="DBC_RECOMMENDATION")
    private String dbcRecommendation;
    @Column(name="DBC_RECOMMENDATION_FINALIZED")
    private Timestamp dbcRecommendationFinalized;
    @Column(name="DBC_TARGET_DT")
    private Timestamp dbcTargetDt;
    @Column(name="DBR_INITIATE_DT")
    private Timestamp dbrInitiateDt;
    @Column(name="DRUG_STATUS_ADDITIONAL_INFO")
    private String drugStatusAdditionalInfo;
    private String ext;
    private String fax;
    private String indication;
    @Column(name="NOC_DATE")
    private Timestamp nocDate;
    @Column(name="PATENT_EXPIRY")
    private Timestamp patentExpiry;
    @Column(name="PLA_COMPLETED_DT")
    private Timestamp plaCompletedDt;
    @Column(name="PLA_INITIATE_DT")
    private Timestamp plaInitiateDt;
    @Column(name="PNET_COMPLETED_DT")
    private Timestamp pnetCompletedDt;
    @Column(name="POSTAL_ZIP", nullable = false)
    private String postalZip;
    @Column(name="PROV_STATE", nullable = false)
    private String provState;
    @Column(name="PSD_REVIEW_COMPLETED_DT")
    private Timestamp psdReviewCompletedDt;
    @Column(name="PSD_REVIEW_DAYS")
    private Long psdReviewDays;
    @Column(name="PSD_REVIEW_INITIATE_DT")
    private Timestamp psdReviewInitiateDt;
    @Column(name="PSD_STATUS", nullable = false)
    private String psdStatus;
    @Column(name="RECEIVED_DATE")
    private Timestamp receivedDate;
    @Id
    private Long rn;
    @Column(name="SA_FORMS_COMPLETED_DT")
    private Timestamp saFormsCompletedDt;
    @Column(name="SA_FORMS_DAYS")
    private Long saFormsDays;
    @Column(name="SA_FORMS_INITIATE_DT")
    private Timestamp saFormsInitiateDt;
    @Column(name="SA_FORMS_STATUS")
    private String saFormsStatus;
    @Column(name="SA_FORMS_TARGET_DT")
    private Timestamp saFormsTargetDt;
    @Column(name="SA_NOTES")
    private String saNotes;
    @Column(name="SUBMISSION_NO", nullable = false)
    private Long submissionNo;
    @Column(name="SUBMISSION_REVIEW_ID", nullable = false)
    private Long submissionReviewId;
    @Column(name="SUB_TYPE", nullable = false)
    private String subType;
    private String title;
    private String work;
    private String xref;

    public VSingleDrugSubStatus() {
    }
    
    public List<String[]> buildReportSection(String[] dinIds, String[] questions, String[] conclusions) {
             
        List<String[]> reportSection = new ArrayList();
        
        String[] blank = {};

        reportSection.add(new String[] {"Submission Background"});
        reportSection.add(new String[] {"Submission No", Long.toString(submissionNo)});
        reportSection.add(new String[] {"Xref", xref});
        reportSection.add(new String[] {"Received Date", formatDate(receivedDate)});
        reportSection.add(new String[] {"Chemical (Trade)", chemicalTradeName});
        reportSection.add(new String[] {"SubType", subType});
        reportSection.add(new String[] {"Applicant Name", applicantName});
        reportSection.add(new String[] {"Indication", indication});
        reportSection.add(new String[] {"PSD Status", psdStatus});
        reportSection.add(new String[] {"CDR Drug Review", cdrDrugReview});
        reportSection.add(blank);
        
        reportSection.add(new String[] {"TimeLines"});
        reportSection.add(new String[] {"", "Status (if applicaable)", "Initiated", "Target", "Completed", "Days Count"});
        reportSection.add(new String[] {"PSD Review", psdStatus, formatDate(psdReviewInitiateDt), "", formatDate(psdReviewCompletedDt), Long.toString(psdReviewDays) });
        reportSection.add(new String[] {"CEDAC Review", cedacReviewStatus, formatDate(cedacReviewInitiateDt), formatDate(cedacReviewTargetDt), formatDate(cedacReviewCompletedDt), Long.toString(cedacReviewDays)});
        reportSection.add(new String[] {"BIA", biaStatus, formatDate(biaInitiateDt), formatDate(biaTargetDt), formatDate(biaCompletedDt), Long.toString(biaDays)});
        reportSection.add(new String[] {"DBC", "", "", formatDate(dbcTargetDt), formatDate(dbcCompletedDt)});
        reportSection.add(new String[] {"SA Forms", saFormsStatus, formatDate(saFormsInitiateDt), formatDate(saFormsTargetDt), formatDate(saFormsCompletedDt), Long.toString(saFormsDays)});
        reportSection.add(new String[] {"Briefing / Decision Note", "", "", "", formatDate(briefingNoteCompletedDt)});
        reportSection.add(new String[] {"PLA", "", formatDate(plaInitiateDt), "", formatDate(plaCompletedDt)});
        reportSection.add(new String[] {"DBR", "", formatDate(dbrInitiateDt)});
        reportSection.add(new String[] {"Pnet", "", "", "", formatDate(pnetCompletedDt)});
        reportSection.add(new String[] {"Applicant Notice", "", "", "", formatDate(applicantNoticeCompletedDt)});
        reportSection.add(new String[] {"Tech Review"});
        reportSection.add(blank);
        
        reportSection.add(new String[] {"Other Information"});
        reportSection.add(new String[] {"NocDate", formatDate(nocDate)});
        reportSection.add(new String[] {"DateReleased", formatDate(dateReleased)});
        reportSection.add(new String[] {"PatentExpiry", formatDate(patentExpiry)});
        reportSection.add(dinIds);
        reportSection.add(blank);
        
        reportSection.add(new String[] {"Drug Details Additional Info"});
        reportSection.add(new String[] {"BIA Requested By", biaRequestedBy});
        reportSection.add(new String[] {"BIA Assigned To", biaAssignedTo});
        reportSection.add(new String[] {"BIA Notes", biaNotes});
        reportSection.add(new String[] {"CEDAC Actual Meeting", formatDate(cedacActualMeeting)});
        reportSection.add(new String[] {"CEDAC Recommendation", cedacRecommendation});
        reportSection.add(new String[] {"CEDAC Notes", cedacNotes});
        reportSection.add(questions);
        reportSection.add(conclusions);
        reportSection.add(new String[] {"DBC Recommendation Finalized", formatDate(dbcRecommendationFinalized)});
        reportSection.add(new String[] {"DBC Recommendation", dbcRecommendation});
        reportSection.add(new String[] {"DBC Notes", dbcNotes});
        reportSection.add(new String[] {"SA Notes", saNotes});
        reportSection.add(blank);
        
        reportSection.add(new String[] {"Drug Status Additional Info"});
        reportSection.add(new String[] {"Company Type", companyType});
        reportSection.add(new String[] {"Applicant Address", applicantAddress});
        reportSection.add(new String[] {"Prov/State", provState});
        reportSection.add(new String[] {"City", city});
        reportSection.add(new String[] {"Country", country});
        reportSection.add(new String[] {"Postal Zip", postalZip});
        reportSection.add(new String[] {"Work", work, ext});
        reportSection.add(new String[] {"Fax", fax});
        reportSection.add(new String[] {"Contact Name", contactName});
        reportSection.add(new String[] {"Title", title});
        reportSection.add(new String[] {"Contact Address", contactAddress});
        reportSection.add(new String[] {"Contact Prov/State", contactProvState});
        reportSection.add(new String[] {"Contact City", contactCity});
        reportSection.add(new String[] {"Contact Country", contactCountry});
        reportSection.add(new String[] {"Contact Postal Zip", contactPostalZip});
        reportSection.add(new String[] {"Contact Work", contactWork, contactExt});
        reportSection.add(new String[] {"Contact Cell", contactCell});
        reportSection.add(new String[] {"Contact Fax", contactFax});
        reportSection.add(new String[] {"Contact Email", contactEmail});
        reportSection.add(new String[] {"Company Details Comments", companyDetailsComments});
        
        reportSection.add(blank);
        reportSection.add(blank);
        reportSection.add(blank);
        
        return reportSection;
    }
    
    public String formatDate(Timestamp inputDate) {
    
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = (inputDate == null) ? "" : sdf.format(receivedDate);
        
        return formattedDate;
    }

    public String getApplicantAddress() {
        return applicantAddress;
    }

    public void setApplicantAddress(String applicantAddress) {
        this.applicantAddress = applicantAddress;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public Timestamp getApplicantNoticeCompletedDt() {
        return applicantNoticeCompletedDt;
    }

    public void setApplicantNoticeCompletedDt(Timestamp applicantNoticeCompletedDt) {
        this.applicantNoticeCompletedDt = applicantNoticeCompletedDt;
    }

    public String getBiaAssignedTo() {
        return biaAssignedTo;
    }

    public void setBiaAssignedTo(String biaAssignedTo) {
        this.biaAssignedTo = biaAssignedTo;
    }

    public Timestamp getBiaCompletedDt() {
        return biaCompletedDt;
    }

    public void setBiaCompletedDt(Timestamp biaCompletedDt) {
        this.biaCompletedDt = biaCompletedDt;
    }

    public Long getBiaDays() {
        return biaDays;
    }

    public void setBiaDays(Long biaDays) {
        this.biaDays = biaDays;
    }

    public Timestamp getBiaInitiateDt() {
        return biaInitiateDt;
    }

    public void setBiaInitiateDt(Timestamp biaInitiateDt) {
        this.biaInitiateDt = biaInitiateDt;
    }

    public String getBiaNotes() {
        return biaNotes;
    }

    public void setBiaNotes(String biaNotes) {
        this.biaNotes = biaNotes;
    }

    public String getBiaRequestedBy() {
        return biaRequestedBy;
    }

    public void setBiaRequestedBy(String biaRequestedBy) {
        this.biaRequestedBy = biaRequestedBy;
    }

    public String getBiaStatus() {
        return biaStatus;
    }

    public void setBiaStatus(String biaStatus) {
        this.biaStatus = biaStatus;
    }

    public Timestamp getBiaTargetDt() {
        return biaTargetDt;
    }

    public void setBiaTargetDt(Timestamp biaTargetDt) {
        this.biaTargetDt = biaTargetDt;
    }

    public Timestamp getBriefingNoteCompletedDt() {
        return briefingNoteCompletedDt;
    }

    public void setBriefingNoteCompletedDt(Timestamp briefingNoteCompletedDt) {
        this.briefingNoteCompletedDt = briefingNoteCompletedDt;
    }

    public String getCdrDrugReview() {
        return cdrDrugReview;
    }

    public void setCdrDrugReview(String cdrDrugReview) {
        this.cdrDrugReview = cdrDrugReview;
    }

    public Timestamp getCedacActualMeeting() {
        return cedacActualMeeting;
    }

    public void setCedacActualMeeting(Timestamp cedacActualMeeting) {
        this.cedacActualMeeting = cedacActualMeeting;
    }

    public String getCedacNotes() {
        return cedacNotes;
    }

    public void setCedacNotes(String cedacNotes) {
        this.cedacNotes = cedacNotes;
    }

    public String getCedacRecommendation() {
        return cedacRecommendation;
    }

    public void setCedacRecommendation(String cedacRecommendation) {
        this.cedacRecommendation = cedacRecommendation;
    }

    public Timestamp getCedacReviewCompletedDt() {
        return cedacReviewCompletedDt;
    }

    public void setCedacReviewCompletedDt(Timestamp cedacReviewCompletedDt) {
        this.cedacReviewCompletedDt = cedacReviewCompletedDt;
    }

    public Long getCedacReviewDays() {
        return cedacReviewDays;
    }

    public void setCedacReviewDays(Long cedacReviewDays) {
        this.cedacReviewDays = cedacReviewDays;
    }

    public Timestamp getCedacReviewInitiateDt() {
        return cedacReviewInitiateDt;
    }

    public void setCedacReviewInitiateDt(Timestamp cedacReviewInitiateDt) {
        this.cedacReviewInitiateDt = cedacReviewInitiateDt;
    }

    public String getCedacReviewStatus() {
        return cedacReviewStatus;
    }

    public void setCedacReviewStatus(String cedacReviewStatus) {
        this.cedacReviewStatus = cedacReviewStatus;
    }

    public Timestamp getCedacReviewTargetDt() {
        return cedacReviewTargetDt;
    }

    public void setCedacReviewTargetDt(Timestamp cedacReviewTargetDt) {
        this.cedacReviewTargetDt = cedacReviewTargetDt;
    }

    public Long getChemicalId() {
        return chemicalId;
    }

    public void setChemicalId(Long chemicalId) {
        this.chemicalId = chemicalId;
    }

    public String getChemicalTradeName() {
        return chemicalTradeName;
    }

    public void setChemicalTradeName(String chemicalTradeName) {
        this.chemicalTradeName = chemicalTradeName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyDetailsComments() {
        return companyDetailsComments;
    }

    public void setCompanyDetailsComments(String companyDetailsComments) {
        this.companyDetailsComments = companyDetailsComments;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactCell() {
        return contactCell;
    }

    public void setContactCell(String contactCell) {
        this.contactCell = contactCell;
    }

    public String getContactCity() {
        return contactCity;
    }

    public void setContactCity(String contactCity) {
        this.contactCity = contactCity;
    }

    public String getContactCountry() {
        return contactCountry;
    }

    public void setContactCountry(String contactCountry) {
        this.contactCountry = contactCountry;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactExt() {
        return contactExt;
    }

    public void setContactExt(String contactExt) {
        this.contactExt = contactExt;
    }

    public String getContactFax() {
        return contactFax;
    }

    public void setContactFax(String contactFax) {
        this.contactFax = contactFax;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPostalZip() {
        return contactPostalZip;
    }

    public void setContactPostalZip(String contactPostalZip) {
        this.contactPostalZip = contactPostalZip;
    }

    public String getContactProvState() {
        return contactProvState;
    }

    public void setContactProvState(String contactProvState) {
        this.contactProvState = contactProvState;
    }

    public String getContactWork() {
        return contactWork;
    }

    public void setContactWork(String contactWork) {
        this.contactWork = contactWork;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Timestamp getDateReleased() {
        return dateReleased;
    }

    public void setDateReleased(Timestamp dateReleased) {
        this.dateReleased = dateReleased;
    }

    public Timestamp getDbcCompletedDt() {
        return dbcCompletedDt;
    }

    public void setDbcCompletedDt(Timestamp dbcCompletedDt) {
        this.dbcCompletedDt = dbcCompletedDt;
    }

    public String getDbcNotes() {
        return dbcNotes;
    }

    public void setDbcNotes(String dbcNotes) {
        this.dbcNotes = dbcNotes;
    }

    public String getDbcRecommendation() {
        return dbcRecommendation;
    }

    public void setDbcRecommendation(String dbcRecommendation) {
        this.dbcRecommendation = dbcRecommendation;
    }

    public Timestamp getDbcRecommendationFinalized() {
        return dbcRecommendationFinalized;
    }

    public void setDbcRecommendationFinalized(Timestamp dbcRecommendationFinalized) {
        this.dbcRecommendationFinalized = dbcRecommendationFinalized;
    }

    public Timestamp getDbcTargetDt() {
        return dbcTargetDt;
    }

    public void setDbcTargetDt(Timestamp dbcTargetDt) {
        this.dbcTargetDt = dbcTargetDt;
    }

    public Timestamp getDbrInitiateDt() {
        return dbrInitiateDt;
    }

    public void setDbrInitiateDt(Timestamp dbrInitiateDt) {
        this.dbrInitiateDt = dbrInitiateDt;
    }

    public String getDrugStatusAdditionalInfo() {
        return drugStatusAdditionalInfo;
    }

    public void setDrugStatusAdditionalInfo(String drugStatusAdditionalInfo) {
        this.drugStatusAdditionalInfo = drugStatusAdditionalInfo;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public Timestamp getNocDate() {
        return nocDate;
    }

    public void setNocDate(Timestamp nocDate) {
        this.nocDate = nocDate;
    }

    public Timestamp getPatentExpiry() {
        return patentExpiry;
    }

    public void setPatentExpiry(Timestamp patentExpiry) {
        this.patentExpiry = patentExpiry;
    }

    public Timestamp getPlaCompletedDt() {
        return plaCompletedDt;
    }

    public void setPlaCompletedDt(Timestamp plaCompletedDt) {
        this.plaCompletedDt = plaCompletedDt;
    }

    public Timestamp getPlaInitiateDt() {
        return plaInitiateDt;
    }

    public void setPlaInitiateDt(Timestamp plaInitiateDt) {
        this.plaInitiateDt = plaInitiateDt;
    }

    public Timestamp getPnetCompletedDt() {
        return pnetCompletedDt;
    }

    public void setPnetCompletedDt(Timestamp pnetCompletedDt) {
        this.pnetCompletedDt = pnetCompletedDt;
    }

    public String getPostalZip() {
        return postalZip;
    }

    public void setPostalZip(String postalZip) {
        this.postalZip = postalZip;
    }

    public String getProvState() {
        return provState;
    }

    public void setProvState(String provState) {
        this.provState = provState;
    }

    public Timestamp getPsdReviewCompletedDt() {
        return psdReviewCompletedDt;
    }

    public void setPsdReviewCompletedDt(Timestamp psdReviewCompletedDt) {
        this.psdReviewCompletedDt = psdReviewCompletedDt;
    }

    public Long getPsdReviewDays() {
        return psdReviewDays;
    }

    public void setPsdReviewDays(Long psdReviewDays) {
        this.psdReviewDays = psdReviewDays;
    }

    public Timestamp getPsdReviewInitiateDt() {
        return psdReviewInitiateDt;
    }

    public void setPsdReviewInitiateDt(Timestamp psdReviewInitiateDt) {
        this.psdReviewInitiateDt = psdReviewInitiateDt;
    }

    public String getPsdStatus() {
        return psdStatus;
    }

    public void setPsdStatus(String psdStatus) {
        this.psdStatus = psdStatus;
    }

    public Timestamp getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Timestamp receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Long getRn() {
        return rn;
    }

    public void setRn(Long rn) {
        this.rn = rn;
    }

    public Timestamp getSaFormsCompletedDt() {
        return saFormsCompletedDt;
    }

    public void setSaFormsCompletedDt(Timestamp saFormsCompletedDt) {
        this.saFormsCompletedDt = saFormsCompletedDt;
    }

    public Long getSaFormsDays() {
        return saFormsDays;
    }

    public void setSaFormsDays(Long saFormsDays) {
        this.saFormsDays = saFormsDays;
    }

    public Timestamp getSaFormsInitiateDt() {
        return saFormsInitiateDt;
    }

    public void setSaFormsInitiateDt(Timestamp saFormsInitiateDt) {
        this.saFormsInitiateDt = saFormsInitiateDt;
    }

    public String getSaFormsStatus() {
        return saFormsStatus;
    }

    public void setSaFormsStatus(String saFormsStatus) {
        this.saFormsStatus = saFormsStatus;
    }

    public Timestamp getSaFormsTargetDt() {
        return saFormsTargetDt;
    }

    public void setSaFormsTargetDt(Timestamp saFormsTargetDt) {
        this.saFormsTargetDt = saFormsTargetDt;
    }

    public String getSaNotes() {
        return saNotes;
    }

    public void setSaNotes(String saNotes) {
        this.saNotes = saNotes;
    }

    public Long getSubmissionNo() {
        return submissionNo;
    }

    public void setSubmissionNo(Long submissionNo) {
        this.submissionNo = submissionNo;
    }

    public Long getSubmissionReviewId() {
        return submissionReviewId;
    }

    public void setSubmissionReviewId(Long submissionReviewId) {
        this.submissionReviewId = submissionReviewId;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getXref() {
        return xref;
    }

    public void setXref(String xref) {
        this.xref = xref;
    }

    public String getChemicalName() {
        return chemicalName;
    }

    public void setChemicalName(String chemicalName) {
        this.chemicalName = chemicalName;
    }
}
