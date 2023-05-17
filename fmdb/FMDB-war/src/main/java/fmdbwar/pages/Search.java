/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        Search.java                                    *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package fmdbwar.pages;

import ca.bc.gov.moh.fmdb.business.SubmissionManagerFacadeLocal;
import ca.bc.gov.moh.fmdb.model.DTOFactory;
import ca.bc.gov.moh.fmdb.model.SearchDTO;
import ca.bc.gov.moh.fmdb.model.SearchResultDTO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("Search")
@ViewScoped
public class Search implements Serializable {

    @EJB
    private SubmissionManagerFacadeLocal submissionManagerFacade;
    
    private SearchDTO searchDTO = DTOFactory.createSearchDTO();
    private Long submissionNumber;
    private Long submissionNumberDetail;
    private String chemicalName;
    private String submissionType;
    private String tradeName;
    private String companyName;
    private List<SearchResultDTO> searchResultCollection;
    private int scrollerPage = 1;

    @PostConstruct
    public void init() {

    }

    public String clear() {
        chemicalName = null;
        submissionType = null;
        tradeName = null;
        companyName = null;
        submissionNumber = null;
        
        return null;
    }

    public String clearResults() {
        searchResultCollection = null;
        return null;
    }

    private boolean valdiateForm() {
        boolean hasErrors = false;
        FacesContext ctx = FacesContext.getCurrentInstance();

        if ((chemicalName==null || "".equals(chemicalName)) && (companyName == null || "".equals(companyName)) && submissionNumber == null && submissionType == null && (tradeName == null || "".equals(tradeName))) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "At least one field must be entered.", null));
            hasErrors = true;
        }

        return hasErrors;
    }

    public String search() {

        if (!valdiateForm()) {
            searchDTO.setChemicalName(chemicalName);
            searchDTO.setCompanyName(companyName);
            searchDTO.setSubmissionNumber(submissionNumber);
            searchDTO.setSubmissionType(submissionType);
            searchDTO.setTradeName(tradeName);
            searchResultCollection = submissionManagerFacade.searchSubmission(searchDTO);
        }

        return null;
    }

    public int getTotalNumberOfRows() {
        return searchResultCollection.size();
    }

    public String resetPage() {
        return null;
    }

    public String selectRow() {

        return "Details";
    }

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
     * @return the chemicalName
     */
    public String getChemicalName() {
        return chemicalName;
    }

    /**
     * @param chemicalName the chemicalName to set
     */
    public void setChemicalName(String chemicalName) {
        this.chemicalName = chemicalName;
    }

    /**
     * @return the submissionType
     */
    public String getSubmissionType() {
        return submissionType;
    }

    /**
     * @param submissionType the submissionType to set
     */
    public void setSubmissionType(String submissionType) {
        this.submissionType = submissionType;
    }

    /**
     * @return the tradeName
     */
    public String getTradeName() {
        return tradeName;
    }

    /**
     * @param tradeName the tradeName to set
     */
    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
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
     * @return the searchResultCollection
     */
    public List<SearchResultDTO> getSearchResultCollection() {
        return searchResultCollection;
    }

    /**
     * @param searchResultCollection the searchResultCollection to set
     */
    public void setSearchResultCollection(List<SearchResultDTO> searchResultCollection) {
        this.searchResultCollection = searchResultCollection;
    }

    /**
     * @return the submissionNumberDetail
     */
    public Long getSubmissionNumberDetail() {
        return submissionNumberDetail;
    }

    /**
     * @param submissionNumberDetail the submissionNumberDetail to set
     */
    public void setSubmissionNumberDetail(Long submissionNumberDetail) {
        this.submissionNumberDetail = submissionNumberDetail;
    }

    /**
     * @return the scrollerPage
     */
    public int getScrollerPage() {
        return scrollerPage;
    }

    /**
     * @param scrollerPage the scrollerPage to set
     */
    public void setScrollerPage(int scrollerPage) {
        this.scrollerPage = scrollerPage;
    }

}