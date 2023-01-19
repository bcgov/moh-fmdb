/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        Details.java                                   *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fmdbwar.pages;

import ca.bc.gov.moh.adti.business.custom.Attachment;
import ca.bc.gov.moh.fmdb.business.AddressesFacadeLocal;
import ca.bc.gov.moh.fmdb.business.ContactDetailsFacadeLocal;
import ca.bc.gov.moh.fmdb.business.ReviewerNameTypesFacadeLocal;
import ca.bc.gov.moh.fmdb.business.SubmissionManagerFacadeLocal;
import ca.bc.gov.moh.fmdb.business.UserManagerFacadeLocal;
import ca.bc.gov.moh.fmdb.business.exception.ItemNotFoundFMDBException;
import ca.bc.gov.moh.fmdb.business.exception.SaveFMDBException;
import ca.bc.gov.moh.fmdb.business.exception.ValidationFMDBException;
import ca.bc.gov.moh.fmdb.entity.Addresses;
import ca.bc.gov.moh.fmdb.entity.BiaRequestedCodes;
import ca.bc.gov.moh.fmdb.entity.Companies;
import ca.bc.gov.moh.fmdb.entity.ContactDetails;
import ca.bc.gov.moh.fmdb.entity.ContactNames;
import ca.bc.gov.moh.fmdb.entity.Dins;
import ca.bc.gov.moh.fmdb.entity.EconomicStatuses;
import ca.bc.gov.moh.fmdb.entity.PlaLogs;
import ca.bc.gov.moh.fmdb.entity.PlaStatusTypes;
import ca.bc.gov.moh.fmdb.entity.ProductListingAgreement;
import ca.bc.gov.moh.fmdb.entity.ReviewQuestions;
import ca.bc.gov.moh.fmdb.entity.ReviewStatuses;
import ca.bc.gov.moh.fmdb.entity.ReviewerNameTypes;
import ca.bc.gov.moh.fmdb.entity.Submission;
import fmdbwar.util.SelectItemHelper;
import fmdbwar.view.UtilityHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.FileUploadEvent;

@Named("Details")
@ViewScoped
public class Details implements Serializable {

    @EJB
    private SubmissionManagerFacadeLocal submissionManagerFacade;
    @EJB
    private ContactDetailsFacadeLocal contactDetailsFacade;
    @EJB
    private AddressesFacadeLocal addressFacade;
    @EJB
    private ReviewerNameTypesFacadeLocal reviewerFacade;
    @EJB
    private UserManagerFacadeLocal userManager;
    private Long submissionNumber;
    private Long contactId;
    private Submission submission;
    private Dins din;
    private Integer selectedDin;
    private ReviewQuestions reviewQuestion;
    private PlaLogs plaLog;
    private EconomicStatuses economicStatusZero;
    private EconomicStatuses economicStatusMinusTwo;
    private EconomicStatuses economicStatusMinusOne;
    private EconomicStatuses economicStatusOne;
    private EconomicStatuses economicStatusTwo;
    private EconomicStatuses economicStatusThree;
    private Integer selectedQuestion;
    private Integer selectedPlaLog;
    private Integer selectedCompany;
    private Integer selectedContact;
    private String companyName;
    private List<SelectItem> dinCodes;
    private List<Companies> companies;
    private Companies company;
    private Addresses companyAddresses;
    private ContactDetails companyDetails;    
    private ContactNames contact;
    private List<SelectItem> contactNames;    
    private String fileName;

    @PostConstruct
    public void init() {
        
        String submissionNumberParam = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("submissionNumber");
        if (submissionNumberParam == null || submissionNumberParam.isEmpty()) {
            newSubmission();
        }
        else {
            submissionNumber = Long.parseLong(submissionNumberParam);
            newSubmission();
            selectSubmission();
        }
        
    }
    
    public Details() {

    }
    
    private void clearCompany(){

        company = new Companies();
        companyAddresses = new Addresses();
        companyDetails = new ContactDetails();    
        contact = new ContactNames();
        contactNames = new ArrayList<>();      
        company.setContactDetails(new LinkedList<>());
    }

    public void newSubmission() {

        submission = Submission.generateEmptySubmission();
        clearDin();
        clearQuestion();
        clearPlaLog();
        clearEconomicStatuses();
        clearCompany();
        fileName = null;

    }
    
    public void selectSubmission() {
        System.out.println("selectSubmission: submissionNumber = " + submissionNumber);
        if (submissionNumber != null) {
            try {
                submission = submissionManagerFacade.retrieveSubmission(submissionNumber);

                if (submission.getFmdbSubmissionReviewDetails().getLetterBin()==null) {
                    submission.getFmdbSubmissionReviewDetails().setLetterBin(new Attachment());
                    fileName = null;
                } else {
                    Attachment att = (Attachment) submission.getFmdbSubmissionReviewDetails().getLetterBin();
                    fileName = att.getFilename();
                }

                if (submission.getFmdbSubmissionReviewDetails().getReviewStatusCd() == null) {
                    submission.getFmdbSubmissionReviewDetails().setReviewStatusCd(new ReviewStatuses());
                }

                if (submission.getFmdbSubmissionReviewDetails().getBiaRequstedCd() == null) {
                    submission.getFmdbSubmissionReviewDetails().setBiaRequstedCd(new BiaRequestedCodes());
                }

                if (this.submission.getFmdbChemicals().getFmdbDinsCollection() == null) {
                    submission.getFmdbChemicals().setFmdbDinsCollection(new ArrayList<Dins>());
                }

                List<Dins> dins = new ArrayList(this.submission.getFmdbChemicals().getFmdbDinsCollection());
                setDinCodes((List<SelectItem>) SelectItemHelper.buildSelectItemList(dins, "dosageFormTxt", "dinId"));

                ProductListingAgreement plaAgreement = submission.getFmdbProductListingAgreement();

                if (plaAgreement == null) {
                    plaAgreement = new ProductListingAgreement();
                    plaAgreement.setPlaStatusCd(new PlaStatusTypes());
                    plaAgreement.setSubmissionId(submission);
                    submission.setFmdbProductListingAgreement(plaAgreement);
                } else if (plaAgreement.getPlaStatusCd() == null) {
                    plaAgreement.setPlaStatusCd(new PlaStatusTypes());
                }

                if (plaAgreement.getFmdbPlaLogsCollection() == null) {
                    plaAgreement.setFmdbPlaLogsCollection(new LinkedList<PlaLogs>());
                }

                if (submission.getCompanyId() == null) {
                    clearCompany();
                } else {
                    setCompanyDetails(submission.getCompanyId().getCompanyId());
                }
                
                if (submission.getContactId()!=null && submission.getContactId().getContactNameId()!=null){
                    contactId = submission.getContactId().getContactNameId();
                    contact = submission.getContactId();
                }

                if (submission.getFmdbEconomicStatusesCollection() == null) {
                    submission.setFmdbEconomicStatusesCollection(new LinkedList<EconomicStatuses>());
                } else {
                    Collection<EconomicStatuses> ecStats = submission.getFmdbEconomicStatusesCollection();
                    for (EconomicStatuses es : ecStats) {
                        switch (es.getRelativeYearNo().intValue()) {
                            case -2:
                                economicStatusMinusTwo = es;
                                continue;
                            case -1:
                                economicStatusMinusOne = es;
                                continue;
                            case 0:
                                economicStatusZero = es;
                                continue;
                            case 1:
                                economicStatusOne = es;
                                continue;
                            case 2:
                                economicStatusTwo = es;
                                continue;
                            case 3:
                                economicStatusThree = es;
                                continue;
                        }
                    }
                }

            } catch (ItemNotFoundFMDBException ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Submission not found."));
                Logger.getLogger(Details.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void deleteAttachment() {
        submission.getFmdbSubmissionReviewDetails().setLetterBin(new Attachment());
        fileName = null;
    }

    public void processUpload(FileUploadEvent ue) {
        byte[] file = ue.getFile().getContents();
        String name = ue.getFile().getFileName();
        String content = ue.getFile().getContentType();
        Date saveDate = new Date();
        String user = this.userManager.getUsernameOfLoggedInUser();

        // Fix for CAST #8521 
        // The first parameter is the id of the attachment. This is not required for our purposes
        Pattern p = Pattern.compile("^(([0-9a-zA-Z\\. :])+\\\\)*");
        Matcher m = p.matcher(name);
        String newName = m.replaceFirst("");
        
        Attachment attachment = new Attachment(0, newName, content, file.length, file, saveDate, user);

        //File type and size meets requirements
        fileName = newName;
        submission.getFmdbSubmissionReviewDetails().setLetterBin(attachment);

    }

    public String selectPlaLog() {
        List<PlaLogs> plaLogsList = new ArrayList(submission.getFmdbProductListingAgreement().getFmdbPlaLogsCollection());
        plaLog = plaLogsList.get(selectedPlaLog);
        return null;
    }

    public String addPlaLog() {

            plaLog.setPlaId(submission.getFmdbProductListingAgreement());
            submission.getFmdbProductListingAgreement().getFmdbPlaLogsCollection().add(plaLog);
            return clearPlaLog();

    }

    public String updatePlaLog() {

        return clearPlaLog();

    }

    public String deletePlaLog() {
        submission.getFmdbProductListingAgreement().getFmdbPlaLogsCollection().remove(plaLog);
        return clearPlaLog();
    }

    public String clearPlaLog() {
        plaLog = new PlaLogs();
        selectedPlaLog = null;
        return null;
    }

    public void search() {
        companies = submissionManagerFacade.searchCompanies(company.getLegalNm());
    }

    public String selectCompany() {
        try {
            company = companies.get(selectedCompany);
            setCompanyDetails(company.getCompanyId());
            submission.setCompanyId(company);

        } catch (ItemNotFoundFMDBException ex) {
            Logger.getLogger(Details.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public String selectContact(ValueChangeEvent event) { 
        if (event.getNewValue() != null ) {
            long selectedContact = Long.valueOf(event.getNewValue().toString());
            if (selectedContact != 0) {
                Collection<ContactNames> listOfContacts = company.getFmdbContactNamesCollection();
                for (ContactNames name : listOfContacts) {
                    if (name.getContactNameId().equals(selectedContact)) {
                        contact = name;
                        submission.setContactId(contact);
                        return null;
                    }
                }
                submission.setContactId(null);
            }
        } else {
            contact = new ContactNames();
        }
        
        return null;
    }

    public void setCompanyDetails(Long companyId) throws ItemNotFoundFMDBException {
        clearCompany();
        company = submissionManagerFacade.retrieveCompany(companyId);
        companyAddresses = addressFacade.findCompanyAddress(company);
        companyDetails = contactDetailsFacade.findCompanyContactDetails(company);

        List<ContactNames> cntCodesLName = new ArrayList(company.getFmdbContactNamesCollection());
        contactNames = SelectItemHelper.buildSelectItemList(cntCodesLName, "displayName", "contactNameId");
    }

    private boolean validateReviewQuestion() {
        boolean hasErrors = false;

        if (reviewQuestion.getReviewerNameCd().getReviewerNameCd() == null || "".equals(reviewQuestion.getReviewerNameCd().getReviewerNameCd())) {
            hasErrors = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Reviewer is required.", null));
        }

        return hasErrors;
    }

    public String selectQuestion() {
        List<ReviewQuestions> questions = new ArrayList(submission.getFmdbSubmissionReviewDetails().getFmdbReviewQuestionsCollection());
        reviewQuestion = questions.get(selectedQuestion);
        return null;
    }

    public void addQuestion() {
        if (!validateReviewQuestion()) {
            reviewQuestion.setSubmissionReviewId(submission.getFmdbSubmissionReviewDetails());
            reviewQuestion.setReviewerNameCd(reviewerFacade.find(reviewQuestion.getReviewerNameCd().getReviewerNameCd()));
            submission.getFmdbSubmissionReviewDetails().getFmdbReviewQuestionsCollection().add(reviewQuestion);
            clearQuestion();
        } 
    }

    public void updateQuestion() {
        if (!validateReviewQuestion()) {
            reviewQuestion.setSubmissionReviewId(submission.getFmdbSubmissionReviewDetails());
            reviewQuestion.setReviewerNameCd(reviewerFacade.find(reviewQuestion.getReviewerNameCd().getReviewerNameCd()));
            clearQuestion();
        } 
    }

    public void deleteQuestion() {
        submission.getFmdbSubmissionReviewDetails().getFmdbReviewQuestionsCollection().remove(reviewQuestion);
        clearQuestion();
    }

    public void clearQuestion() {
        reviewQuestion = new ReviewQuestions();
        reviewQuestion.setReviewerNameCd(new ReviewerNameTypes());
        selectedQuestion = null;
    }

    private boolean validateDin() {
        boolean hasErrors = false;
        FacesContext ctx = FacesContext.getCurrentInstance();

        if (din == null) {
            ctx.addMessage("dinMessages", new FacesMessage("No DIN is selected."));
            hasErrors = true;
        }

        if (din.getDinNo() == null && (din.getDosageFormTxt() == null || "".equals(din.getDosageFormTxt()))) {
            ctx.addMessage("dinMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "At least one DIN field must be entered.", null));
            hasErrors = true;
        } else {
            try {
                Integer x = din.getDinNo();
                List<Dins> dins = new ArrayList(this.submission.getFmdbChemicals().getFmdbDinsCollection());
                for (Dins aDin : dins) {
                    if (aDin.getDinNo() != null && aDin.getDinNo().equals(x)) {
                        ctx.addMessage("dinMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Din number " + din.getDinNo() + " already exists.", null));
                        hasErrors = true;
                    }
                }
            } catch (NumberFormatException nFE) {
                ctx.addMessage("dinMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, din.getDinNo() + " is not a valid Integer." + nFE.getMessage(), null));
                hasErrors = true;
            }
        }

        return hasErrors;
    }
    
    private boolean validateDinAllowNumberMatch() {
        boolean hasErrors = false;
        
        FacesContext ctx = FacesContext.getCurrentInstance();

        if (din == null) {
            ctx.addMessage("dinMessages", new FacesMessage("No DIN is selected."));
            hasErrors = true;
        }

        if (din.getDinNo() == null && (din.getDosageFormTxt() == null || "".equals(din.getDosageFormTxt()))) {
            ctx.addMessage("dinMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "At least one DIN field must be entered.", null));
            hasErrors = true;
        }
        return hasErrors;
    }
    
    //Sets the selected din to the din we're editing
    public String selectDin() {
        List<Dins> dins = new ArrayList(submission.getFmdbChemicals().getFmdbDinsCollection());
        din = dins.get(selectedDin);
        return null;
    }

    public String addDin() {
        if (!validateDin()) {
            din.setChemicalId(submission.getFmdbChemicals());
            submission.getFmdbChemicals().getFmdbDinsCollection().add(din);
            return clearDin();
        } else {
            return null;
        }
    }

    public String updateDin() {
        if (!validateDinAllowNumberMatch()) {
            return clearDin();
        } else {
            return null;
        }
    }

    public String deleteDin() {
        submission.getFmdbChemicals().getFmdbDinsCollection().remove(din);
        return clearDin();
    }

    public String clearDin() {
        din = new Dins();
        selectedDin = null;
        return null;
    }

    public long calculateDateDiffernce(Date firstDate, Date secondDate) {
        long diff = 0;

        if (firstDate != null) {
            if (secondDate == null) {
                diff = UtilityHelper.calculateDays(firstDate);
            } else {
                diff = UtilityHelper.calculateDays(secondDate, firstDate);
            }
        }
       return diff;
    }

    public long getBiaDateDifference() 
    {
        return this.calculateDateDiffernce(
                submission.getFmdbSubmissionReviewDetails().getBiaTargetDt() != null ? submission.getFmdbSubmissionReviewDetails().getBiaTargetDt() : null,
                submission.getFmdbSubmissionReviewDetails().getBiaActualDt() != null ? submission.getFmdbSubmissionReviewDetails().getBiaActualDt() : null);
    }

    public long getDbcDateDifference() {

        return this.calculateDateDiffernce(submission.getFmdbSubmissionReviewDetails().getDbcTargetDt() != null ? submission.getFmdbSubmissionReviewDetails().getDbcTargetDt() : null,
                submission.getFmdbSubmissionReviewDetails().getDbcActualDt() != null ? submission.getFmdbSubmissionReviewDetails().getDbcActualDt() : null);

    }

    public long getSafDateDifference() {
        return this.calculateDateDiffernce(submission.getFmdbSubmissionReviewDetails().getSaTargetDt() != null ? submission.getFmdbSubmissionReviewDetails().getSaTargetDt() : null,
                submission.getFmdbSubmissionReviewDetails().getSaCompletedDt() != null ? submission.getFmdbSubmissionReviewDetails().getSaCompletedDt() : null);

    }

    public long getDrdDateDifference() {
        return this.calculateDateDiffernce(reviewQuestion.getTargetCompletionDt() != null ? reviewQuestion.getTargetCompletionDt() : null,
                reviewQuestion.getActualCompletionDt() != null ? reviewQuestion.getActualCompletionDt() : null);

    }

    public long getCdrDateDifference() {
        return this.calculateDateDiffernce(submission.getFmdbSubmissionReviewDetails().getCedacMeetTargetDt() != null ? submission.getFmdbSubmissionReviewDetails().getCedacMeetTargetDt() : null,
                submission.getFmdbSubmissionReviewDetails().getCedacMeetActualDt() != null ? submission.getFmdbSubmissionReviewDetails().getCedacMeetActualDt() : null);
    }

    public String getDecisionDays(){
        String reviewed = "Y";
        if (submission.getFmdbSubmissionReviewDetails().getCdrReviewYn() == null || "N".equals(submission.getFmdbSubmissionReviewDetails().getCdrReviewYn().toString())) {
            reviewed = "N";
        }

        if (submission.getFmdbSubmissionReviewDetails().getCedacRecommendationDt() == null) {
            return null;
        }

        return "Y".equals(reviewed)?
        String.valueOf(calculateDateDiffernce(submission.getFmdbSubmissionReviewDetails().getDbrPharmanetDt(), submission.getFmdbSubmissionReviewDetails().getCedacRecommendationDt())):
        String.valueOf(calculateDateDiffernce(submission.getFmdbSubmissionReviewDetails().getDbrPharmanetDt(), submission.getReceivedDt()));
    }
    
    public String getDecisionBusinessDays(){
        long weekendDays = 0;
        long allDays =  getDecisionDays() == null ? -1 : Long.parseLong(getDecisionDays());
        Date noticeDate = submission.getFmdbSubmissionReviewDetails().getDbrPharmanetDt();
        for (int i=0; i<allDays; i++){
            Date newNoticeDate = new Date(noticeDate.getTime());
            newNoticeDate.setDate(noticeDate.getDate()-i);
            if (newNoticeDate.getDay()>5 || newNoticeDate.getDay()==0){
                weekendDays++;
            }
        }
        return  allDays == -1 ? null : String.valueOf(allDays - weekendDays);
    }
    
    private void clearEconomicStatuses() {
        economicStatusMinusTwo = new EconomicStatuses();
        economicStatusMinusOne = new EconomicStatuses();
        economicStatusZero = new EconomicStatuses();
        economicStatusOne = new EconomicStatuses();
        economicStatusTwo = new EconomicStatuses();
        economicStatusThree = new EconomicStatuses();
    }

    public String save() {
        try {
            submissionManagerFacade.saveSubmission(submission);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Submission Saved Succesfully.", null));     
            submissionNumber = submission.getSubmissionNo();
            newSubmission();
            selectSubmission();
        } catch (ValidationFMDBException ex) {
            Logger.getLogger(Details.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SaveFMDBException ex) {
            Logger.getLogger(Details.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public String delete() {

        return null;
    }

    public String returnPreviousPage() {

        return "search";
    }

    // <editor-fold desc="Getters and Setters">
    /**
     * @return the submissionNumber
     */
    public Long getSubmissionNumber() {
        return submissionNumber;
    }

    /**
     * @param submissionNumber the submissionNumber to set
     */
    public void setSubmissionNumber(Long submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    /**
     * @return the submission
     */
    public Submission getSubmission() {
        return submission;
    }

    /**
     * @param submission the submission to set
     */
    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    /**
     * @return the submissionManagerFacade
     */
    public SubmissionManagerFacadeLocal getSubmissionManagerFacade() {
        return submissionManagerFacade;
    }

    /**
     * @param submissionManagerFacade the submissionManagerFacade to set
     */
    public void setSubmissionManagerFacade(SubmissionManagerFacadeLocal submissionManagerFacade) {
        this.submissionManagerFacade = submissionManagerFacade;
    }

    /**
     * @return the submissionManagerFacade
     */
    public ContactDetailsFacadeLocal getContactDetailsFacade() {
        return contactDetailsFacade;
    }

    /**
     * @param contactDetailsFacade
     */
    public void setContactDetailsFacade(ContactDetailsFacadeLocal contactDetailsFacade) {
        this.contactDetailsFacade = contactDetailsFacade;
    }

    /**
     * @return the dinCodes
     */
    public List<SelectItem> getDinCodes() {
        return dinCodes;
    }

    /**
     * @param dinCodes the dinCodes to set
     */
    public void setDinCodes(List<SelectItem> dinCodes) {
        this.dinCodes = dinCodes;
    }

    /**
     * @return the din
     */
    public Dins getDin() {
        return din;
    }

    /**
     * @param din the din to set
     */
    public void setDin(Dins din) {
        this.din = din;
    }

    /**
     * @return the reviewQuestion
     */
    public ReviewQuestions getReviewQuestion() {
        return reviewQuestion;
    }

    /**
     * @param reviewQuestion the reviewQuestion to set
     */
    public void setReviewQuestion(ReviewQuestions reviewQuestion) {
        this.reviewQuestion = reviewQuestion;
    }

    /**
     * @return the selectedQuestion
     */
    public Integer getSelectedQuestion() {
        return selectedQuestion;
    }

    /**
     * @param selectedQuestion the selectedQuestion to set
     */
    public void setSelectedQuestion(Integer selectedQuestion) {
        this.selectedQuestion = selectedQuestion;
    }

    /**
     * @return the contactNames
     */
    public List<SelectItem> getContactNames() {
        return contactNames;
    }

    /**
     * @param contactNames the contactNames to set
     */
    public void setContactNames(List<SelectItem> contactNames) {
        this.contactNames = contactNames;
    }

    /**
     * @return the contactId
     */
    public Long getContactId() {
        return contactId;
    }

    /**
     * @param contactId the contactId to set
     */
    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    /**
     * @return the contact
     */
    public ContactNames getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(ContactNames contact) {
        this.contact = contact;
    }

    /**
     * @return the selectedPlaLog
     */
    public Integer getSelectedPlaLog() {
        return selectedPlaLog;
    }

    /**
     * @param selectedPlaLog the selectedPlaLog to set
     */
    public void setSelectedPlaLog(Integer selectedPlaLog) {
        this.selectedPlaLog = selectedPlaLog;
    }

    /**
     * @return the plalog
     */
    public PlaLogs getPlaLog() {
        return plaLog;
    }

    /**
     * @param plalog the plalog to set
     */
    public void setPlalog(PlaLogs plaLog) {
        this.plaLog = plaLog;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the companies
     */
    public List<Companies> getCompanies() {
        return companies;
    }

    /**
     * @param companies the companies to set
     */
    public void setCompanies(List<Companies> companies) {
        this.companies = companies;
    }

    /**
     * @return the company
     */
    public Companies getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(Companies company) {
        this.company = company;
    }

    /**
     * @return the selectedCompany
     */
    public Integer getSelectedCompany() {
        return selectedCompany;
    }

    /**
     * @param selectedCompany the selectedCompany to set
     */
    public void setSelectedCompany(Integer selectedCompany) {
        this.selectedCompany = selectedCompany;
    }

    /**
     * @return the companyAddresses
     */
    public Addresses getCompanyAddresses() {
        return companyAddresses;
    }

    /**
     * @param companyAddresses the companyAddresses to set
     */
    public void setCompanyAddresses(Addresses companyAddresses) {
        this.companyAddresses = companyAddresses;
    }

    /**
     * @return the companyDetails
     */
    public ContactDetails getCompanyDetails() {
        return companyDetails;
    }

    /**
     * @param companyDetails the companyDetails to set
     */
    public void setCompanyDetails(ContactDetails companyDetails) {
        this.companyDetails = companyDetails;
    }

    /**
     * @return the selectedContact
     */
    public Integer getSelectedContact() {
        return selectedContact;
    }

    /**
     * @param selectedContact the selectedContact to set
     */
    public void setSelectedContact(Integer selectedContact) {
        this.selectedContact = selectedContact;
    }

    /**
     * @return the economicStatusZero
     */
    public EconomicStatuses getEconomicStatus() {
        return economicStatusZero;
    }

    /**
     * @param economicStatusZero the economicStatusZero to set
     */
    public void setEconomicStatus(EconomicStatuses economicStatus) {
        this.economicStatusZero = economicStatus;
    }

    /**
     * @return the economicStatusMinusTwo
     */
    public EconomicStatuses getEconomicStatusMinusTwo() {
        return economicStatusMinusTwo;
    }

    /**
     * @param economicStatusMinusTwo the economicStatusMinusTwo to set
     */
    public void setEconomicStatusMinusTwo(EconomicStatuses economicStatusMinusTwo) {
        this.economicStatusMinusTwo = economicStatusMinusTwo;
    }

    /**
     * @return the economicStatusMinusOne
     */
    public EconomicStatuses getEconomicStatusMinusOne() {
        return economicStatusMinusOne;
    }

    /**
     * @param economicStatusMinusOne the economicStatusMinusOne to set
     */
    public void setEconomicStatusMinusOne(EconomicStatuses economicStatusMinusOne) {
        this.economicStatusMinusOne = economicStatusMinusOne;
    }

    /**
     * @return the economicStatusOne
     */
    public EconomicStatuses getEconomicStatusOne() {
        return economicStatusOne;
    }

    /**
     * @param economicStatusOne the economicStatusOne to set
     */
    public void setEconomicStatusOne(EconomicStatuses economicStatusOne) {
        this.economicStatusOne = economicStatusOne;
    }

    /**
     * @return the economicStatusTwo
     */
    public EconomicStatuses getEconomicStatusTwo() {
        return economicStatusTwo;
    }

    /**
     * @param economicStatusTwo the economicStatusTwo to set
     */
    public void setEconomicStatusTwo(EconomicStatuses economicStatusTwo) {
        this.economicStatusTwo = economicStatusTwo;
    }

    /**
     * @return the economicStatusThree
     */
    public EconomicStatuses getEconomicStatusThree() {
        return economicStatusThree;
    }

    /**
     * @param economicStatusThree the economicStatusThree to set
     */
    public void setEconomicStatusThree(EconomicStatuses economicStatusThree) {
        this.economicStatusThree = economicStatusThree;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param file the filen to set
     */
    public void setFileName(String file) {
        this.fileName = file;
    }
// </editor-fold>

    public Integer getSelectedDin() {
        return selectedDin;
    }

    public void setSelectedDin(Integer selectedDin) {
        this.selectedDin = selectedDin;
    }
}


