/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        DrugSearch.java                                *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

package fmdbwarpub;

import ca.bc.gov.moh.fmdb.web.model.SearchDTO;
import ca.bc.gov.moh.fmdb.web.model.SearchDTO.SortByCriteria;
import ca.bc.gov.moh.fmdb.web.pub.PublicAcessVwControler;
import ca.bc.gov.moh.fmdb.web.pub.entity.PublicAccessVw;
import ca.bc.gov.moh.fmdb.web.util.FMDBUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * @author john.au
 */
@Named("DrugSearch")
@ViewScoped
public class DrugSearch implements Serializable {

    // Sort and Search drop down list items
    private SelectItem[] sortAndSearchOptions = null;

    // Sort value selected by the user
    private String sortBy = null;

    // Search text entered by the user
    private String searchText = null;

    // Search results returned from the database resulting from a user search
    private List<PublicAccessVw> searchResults = null;

    // If the user clicks a bookmark, the bookmark value is stored here
    private String bookMark = null;
    
    private String[] alphabets = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                                  "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                                  "U", "V", "W", "X", "Y", "Z"
                                 };

    /**
     * Constructor creates a PublicAcessVwControler and stores in the session.
     * This object contains all methods to query the database
     */
    public DrugSearch() {
        if (this.getSessionMap().get(Constants.PUBLIC_WEB_CONTROLLER_KEY) == null) {
            this.getSessionMap().put(Constants.PUBLIC_WEB_CONTROLLER_KEY,
                    new PublicAcessVwControler());
        }
    }

    /**
     * If the user clicks the Search button in DrugSearch.jsp, this action
     * is invoked. Stores the search text entered by the user in the session,
     * along with a flag indicating that a bookmark search was not initiated
     *
     * @return
     */
    public String searchAction() {
        this.getSessionMap().put(Constants.SEARCH_TYPE_KEY, searchText);
        this.getSessionMap().put(Constants.SEARCH_TYPE_BOOKMARK, "N");

        setSearchResults(getSearchResultCollection());

        return null;
    }

    /**
     * If the user clicks a bookmark in DrugSearch.jsp, this action is invoked.
     * The bookmark value is stored in the session in the same place as the
     * search text. It also sets a flag indicating that a bookmark search was
     * initiated
     *
     * @return
     */
    public String bookMarkAction() {
        this.getSessionMap().put(Constants.SEARCH_TYPE_KEY, bookMark);
        this.getSessionMap().put(Constants.SEARCH_TYPE_BOOKMARK, "Y");

        setSearchResults(getSearchResultCollection());

        return null;
    }

    /**
     * Sets the sort options drop down list in the JSP
     *
     * @return
     */
    public SelectItem[] getSortAndSearchOptions() {
        if (sortAndSearchOptions == null) {
            sortAndSearchOptions = new SelectItem[]{
                new SelectItem(SortByCriteria.BRAND_NAME.name(), "Brand Name"),
                new SelectItem(SortByCriteria.COMPANY_NAME.name(), "Company Name"),
                new SelectItem(SortByCriteria.GENERIC_NAME.name(), "Generic Name"),
                new SelectItem(SortByCriteria.INDICATION.name(), "Indication")};
        }

        return sortAndSearchOptions;
    }

    /**
     * Using the sortBy variable, this method builds a SortByCriteria object.
     * The SortByCriteria object is required by the SearchDTO inorder to
     * perform a search against the database
     *
     * @return
     */
    private SortByCriteria getSortCriteria() {
        String selection = getSortBy();
        SortByCriteria sortCriteria;

        if (selection == null) {
            return Constants.DEFAULT_SORT_BY_CRITERIA;
        }
        if (selection.equals(SortByCriteria.BRAND_NAME.name())) {
            sortCriteria = SortByCriteria.BRAND_NAME;
        } else if (selection.equals(SortByCriteria.COMPANY_NAME.name())) {
            sortCriteria = SortByCriteria.COMPANY_NAME;
        } else if (selection.equals(SortByCriteria.GENERIC_NAME.name())) {
            sortCriteria = SortByCriteria.GENERIC_NAME;
        } else if (selection.equals(SortByCriteria.INDICATION.name())) {
            sortCriteria = SortByCriteria.INDICATION;
        } else {
            sortCriteria = Constants.DEFAULT_SORT_BY_CRITERIA;
        }

        return sortCriteria;
    }

    /**
     * Sets the header of the search results panel in DrugSearch.jsp. The
     * header includes the sort by criteria and the user entered text or the
     * bookmark link they selected
     *
     * @return
     */
    public String getResultsTitle() {
        if (getSearchResultCollection().size() > 0) {
            String msg = FMDBUtil.getMessage("publicWeb.resultsTitle");
            String selection = sortBy;
            String criteriaTitle = "";
            if (selection.equals(SortByCriteria.BRAND_NAME.name())) {
                criteriaTitle = "Brand Name";
            } else if (selection.equals(SortByCriteria.COMPANY_NAME.name())) {
                criteriaTitle = "Company Name";
            } else if (selection.equals(SortByCriteria.GENERIC_NAME.name())) {
                criteriaTitle = "Generic Name";
            } else if (selection.equals(SortByCriteria.INDICATION.name())) {
                criteriaTitle = "Indication";
            }
            return String.format(msg, criteriaTitle,
                    this.getSessionMap().get(Constants.SEARCH_TYPE_KEY));
        } else {
            return "";
        }
    }

    /**
     * Performs the search against the database and returns a list of results
     *
     * @return
     */
    private List<PublicAccessVw> getSearchResultCollection() {
        String letter = (String) this.getSessionMap().get(Constants.SEARCH_TYPE_KEY);

        boolean searchFlag = false;
        String bookmarkSearch =
                (String) this.getSessionMap().get(Constants.SEARCH_TYPE_BOOKMARK);
        if (bookmarkSearch != null && bookmarkSearch.equalsIgnoreCase("Y")) {
            searchFlag = true;
        }
        if (letter != null && !letter.equals("")) {
            List<PublicAccessVw> results = 
                this.getService().getSearchResults(
                    new SearchDTO(getSortCriteria(), letter, searchFlag));
            return results;
        } else {
            return null;
        }
    }

    /**
     * Retrieves the PublicAcessVwControler from the session. The controler is
     * used to query the database for search results
     *
     * @return
     */
    private PublicAcessVwControler getService() {
        return (PublicAcessVwControler) this.getSessionMap().get(
                Constants.PUBLIC_WEB_CONTROLLER_KEY);
    }

    /**
     * @return the sortBy
     */
    public String getSortBy() {
        return sortBy;
    }

    /**
     * @param sortBy the sortBy to set
     */
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * @return the searchText
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * @param searchText the searchText to set
     */
    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    /**
     * @return the searchResults
     */
    public List<PublicAccessVw> getSearchResults() {
        return searchResults;
    }

    /**
     * @param searchResults the searchResults to set
     */
    public void setSearchResults(List<PublicAccessVw> searchResults) {
        this.searchResults = searchResults;
    }

    /**
     * @return the bookMark
     */
    public String getBookMark() {
        return bookMark;
    }

    /**
     * @param bookMark the bookMark to set
     */
    public void setBookMark(String bookMark) {
        this.bookMark = bookMark;
    }

    public Map getSessionMap(){
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }
    
    public String[] getAlphabets() {
        return alphabets;
    }
    
    public void setAlphabets() {
        
    }
}
