/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        Submission.java                                *
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

import ca.bc.gov.moh.fmdb.util.AuditListener;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author Chris.Prince
 */
@Entity
@Table(name = "FMDB_SUBMISSION")
@SequenceGenerator(name="SUBMISSION_SEQUENCE", sequenceName="FMDB_SUB_SEQ", allocationSize=1)
@EntityListeners({AuditListener.class})
public class Submission implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SUBMISSION_SEQUENCE")
    @Column(name = "SUBMISSION_ID", nullable = false)
    private Long submissionId;
    @Column(name = "SUBMISSION_NO", nullable = false)
    private Long submissionNo;
    @Column(name = "RECEIVED_DT")
    @Temporal(TemporalType.DATE)
    private Date receivedDt;
    @Column(name = "PSD_WEB_COMMENTS")
    private String psdWebComments;
    @Column(name = "POST_SUBMISSION_TO_WEB_YN")
    private Character postSubmissionToWebYn;
    @Column(name = "XREF_NUMBER_TXT")
    private String xrefNumberTxt;
    @Column(name = "CREATED_ON_DTM", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdOnDtm;
    @Column(name = "CREATED_BY_NM", nullable = false)
    private String createdByNm;
    @Column(name = "LAST_MODIFIED_DTM")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDtm;
    @Column(name = "LAST_MODIFIED_BY_NM")
    private String lastModifiedByNm;
    @Column(name = "STATELESS_TRANSACTION_NBR", nullable = false)
    @Version
    private Long statelessTransactionNbr;

    @JoinColumn(name = "CONTACT_NAME_ID", referencedColumnName = "CONTACT_NAME_ID")
    @ManyToOne
    private ContactNames contactId;

    @OneToMany(mappedBy = "submissionId", cascade=CascadeType.ALL)
    private Collection<EconomicStatuses> fmdbEconomicStatusesCollection;

    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "COMPANY_ID")
    @ManyToOne
    private Companies companyId;

    @JoinColumn(name = "SUBMISSION_TYPE_CD", referencedColumnName = "SUBMISSION_TYPE_CD")
    @ManyToOne
    private SubmissionTypes submissionTypeCd;

    @OneToOne(mappedBy = "submissionId", cascade=CascadeType.ALL)
    private SubmissionReviewDetails submissionReviewDetails;

    @OneToOne(mappedBy = "submissionId", cascade=CascadeType.ALL)
    private ProductListingAgreement productListingAgreement;

    @OneToOne(mappedBy = "submission", cascade=CascadeType.ALL)
    private Chemicals chemical;

    public Submission() {
    }

    public Submission(Long submissionId) {
        this.submissionId = submissionId;
    }

    public Submission(Long submissionId, Long submissionNo, Date createdOnDtm, String createdByNm, Long statelessTransactionNbr) {
        this.submissionId = submissionId;
        this.submissionNo = submissionNo;
        this.createdOnDtm = createdOnDtm;
        this.createdByNm = createdByNm;
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    /**
     *
     *  Initializes an empty submission so it's ready for the UI.  This includes
     *  setting default values for the fields.
     *
     */
    public static Submission generateEmptySubmission() {
        Submission submission = new Submission();

        //Details
        submission.setReceivedDt(new Date());
        //submission.setSubmissionTypeCd(new SubmissionTypes(""));
        submission.setPostSubmissionToWebYn('N');
        Chemicals chemical = new Chemicals();
        chemical.setSubmission(submission);
        submission.setChemical(chemical);
        chemical.setFmdbDinsCollection(new LinkedList<Dins>());

        //Status
        SubmissionReviewDetails srDetails = new SubmissionReviewDetails();
        srDetails.setSubmissionId(submission);
        submission.setFmdbSubmissionReviewDetails(srDetails);
        srDetails.setCdrReviewYn(' ');
        srDetails.setReviewStatusCd(new ReviewStatuses());
        srDetails.setBiaRequstedCd(new BiaRequestedCodes());
        srDetails.setLetterImagePublicYn('N');
        srDetails.setSaRequiredYn(' ');

        //Review
        srDetails.setFmdbReviewQuestionsCollection(new LinkedList<ReviewQuestions>());

        //Company Details
        generateEmptyCompany(submission);

        //PLA
        ProductListingAgreement pla = new ProductListingAgreement();
        pla.setSubmissionId(submission);
        submission.setFmdbProductListingAgreement(pla);
        //pla.setPlaStatusCd(new PlaStatusTypes(""));
        pla.setFmdbPlaLogsCollection(new LinkedList());

        //Economic
        generateMissingEconomicStatuses(submission);

        submission.getFmdbProductListingAgreement().setPlaStatusCd(new PlaStatusTypes());
        submission.setSubmissionTypeCd(new SubmissionTypes());
        submission.getFmdbChemicals().setFmdbDinsCollection(new LinkedList<Dins>());
        submission.getFmdbSubmissionReviewDetails().setFmdbReviewQuestionsCollection(new LinkedList<ReviewQuestions>());
        submission.getFmdbProductListingAgreement().setFmdbPlaLogsCollection(new LinkedList<PlaLogs>());

        return submission;
    }

    /**
     * Generates an empty company
     * @param submission
     */
    private static void generateEmptyCompany(Submission submission) {
        Companies company = new Companies();
        submission.setCompanyId(company);
        List addresses = new LinkedList<Addresses>();
        Addresses address = new Addresses();
        addresses.add(address);
        Regions region = new Regions();
        address.setFmdbRegions(region);
        region.setFmdbCountries(new Countries());
        company.setFmdbAddressesCollection(addresses);
        //company.setCompanyTypeCd(new CompanyTypes(""));
        company.setFmdbContactNamesCollection(new LinkedList<ContactNames>());
        Collection<ContactDetails> cd = new ArrayList<ContactDetails>();
        cd.add(new ContactDetails());
        company.setContactDetails(cd);
    }

    /**
     *
     *  Adds any missing statuses.  There may be some missing if
     *  data is inputted using a means other than the FMDB UI.
     *
     *  @param submission the submission
     *
     */
    private static void generateMissingEconomicStatuses(Submission submission) {
        Collection<EconomicStatuses> economicItems = submission.getFmdbEconomicStatusesCollection();
        if(economicItems == null) {
            economicItems = new LinkedList<EconomicStatuses>();
            submission.setFmdbEconomicStatusesCollection(economicItems);
        }

        Map<String, EconomicStatuses> items = new HashMap();
        EconomicStatuses currentStatus;
        for(Iterator itr = economicItems.iterator(); itr.hasNext(); ) {
            currentStatus = (EconomicStatuses) itr.next();
            items.put(currentStatus.getRelativeYearNo().toString(), currentStatus);
        }

        //Create any missing economic statuses
        for(int i = -2; i <= 3; i++) {
            String val = Integer.toString(i);
            if( !items.containsKey(val)) {
                economicItems.add( new EconomicStatuses(new BigInteger(val), submission) );
            }
        }
    }


    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(final Long submissionId) {
        this.submissionId = submissionId;
    }

    public Long getSubmissionNo() {
        return submissionNo;
    }

    public void setSubmissionNo(Long submissionNo) {
        this.submissionNo = submissionNo;
    }

    public Date getReceivedDt() {
        return receivedDt;
    }

    public void setReceivedDt(final Date receivedDt) {
        this.receivedDt = receivedDt;
    }

    public String getPsdWebComments() {
        return psdWebComments;
    }

    public void setPsdWebComments(final String psdWebComments) {
        this.psdWebComments = psdWebComments;
    }

    public Character getPostSubmissionToWebYn() {
        return postSubmissionToWebYn;
    }

    public void setPostSubmissionToWebYn(final Character postSubmissionToWebYn) {
        this.postSubmissionToWebYn = postSubmissionToWebYn;
    }

    public Boolean getPostSubmissionToWebBool() {
        return new Character('Y').equals(postSubmissionToWebYn);
    }

    public void setPostSubmissionToWebBool(final Boolean postSubmissionToWebBool) {
        postSubmissionToWebYn = postSubmissionToWebBool ? 'Y' : 'N';
    }
    public String getXrefNumberTxt() {
        return xrefNumberTxt;
    }

    public void setXrefNumberTxt(final String xrefNumberTxt) {
        this.xrefNumberTxt = xrefNumberTxt;
    }

    public Date getCreatedOnDtm() {
        return createdOnDtm;
    }

    public void setCreatedOnDtm(final Date createdOnDtm) {
        this.createdOnDtm = createdOnDtm;
    }

    public String getCreatedByNm() {
        return createdByNm;
    }

    public void setCreatedByNm(final String createdByNm) {
        this.createdByNm = createdByNm;
    }

    public Date getLastModifiedDtm() {
        return lastModifiedDtm;
    }

    public void setLastModifiedDtm(final Date lastModifiedDtm) {
        this.lastModifiedDtm = lastModifiedDtm;
    }

    public String getLastModifiedByNm() {
        return lastModifiedByNm;
    }

    public void setLastModifiedByNm(final String lastModifiedByNm) {
        this.lastModifiedByNm = lastModifiedByNm;
    }

    public Long getStatelessTransactionNbr() {
        return statelessTransactionNbr;
    }

    public void setStatelessTransactionNbr(final Long statelessTransactionNbr) {
        this.statelessTransactionNbr = statelessTransactionNbr;
    }

    public Chemicals getFmdbChemicals() {
        return chemical;
    }

    public void setChemical(final Chemicals chemical) {
        this.chemical = chemical;
    }

    public ProductListingAgreement getFmdbProductListingAgreement() {
        return productListingAgreement;
    }

    public void setFmdbProductListingAgreement(final ProductListingAgreement productListingAgreement) {
        this.productListingAgreement = productListingAgreement;
    }

    public Collection<EconomicStatuses> getFmdbEconomicStatusesCollection() {
        return fmdbEconomicStatusesCollection;
    }

    public void setFmdbEconomicStatusesCollection(final Collection<EconomicStatuses> fmdbEconomicStatusesCollection) {
        this.fmdbEconomicStatusesCollection = fmdbEconomicStatusesCollection;
    }

    public Companies getCompanyId() {
        return companyId;
    }

    public void setCompanyId(final Companies companyId) {
        this.companyId = companyId;
    }

    public SubmissionTypes getSubmissionTypeCd() {
        return submissionTypeCd;
    }

    public void setSubmissionTypeCd(final SubmissionTypes submissionTypeCd) {
        this.submissionTypeCd = submissionTypeCd;
    }

    public SubmissionReviewDetails getFmdbSubmissionReviewDetails() {
        return submissionReviewDetails;
    }

    public void setFmdbSubmissionReviewDetails(final SubmissionReviewDetails submissionReviewDetails) {
        this.submissionReviewDetails = submissionReviewDetails;
    }

    public ContactNames getContactId() {
        return contactId;
    }

    public void setContactId(final ContactNames contactId) {
        this.contactId = contactId;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (submissionId != null ? submissionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object object) {
        boolean retValue = true;
        
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Submission)) {
            retValue = false;
        }
        final Submission other = (Submission) object;
        if ((this.submissionId == null && other.submissionId != null) || (this.submissionId != null && !this.submissionId.equals(other.submissionId))) {
            retValue = false;
        }
        return retValue;
    }

    @Override
    public String toString() {
        return "ca.bc.gov.moh.fmdb.entity.FmdbSubmission[submissionId=" + submissionId + "]";
    }
    
    /**
     *  
     *  Returns a deep copy of the submission.
     *  
     *  @return a copy of the submission
     *  @throws java.lang.CloneNotSupportedException if the submission cannot be cloned
     *  
     */
    @Override
    public Submission clone() throws CloneNotSupportedException {
        Submission submission = new Submission();
        
        submission.setChemical(submission.getFmdbChemicals().clone());
        submission.getFmdbChemicals().setSubmission(submission);
        //submission.setCompanyId(this.getCompanyId().clone());
        
        
        //TO-DO: finish this clone method
        
        
        
        
        return submission;
    }

}
