/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        CompanyContacts.java                           *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fmdbwar.pages.admin;

import ca.bc.gov.moh.fmdb.business.AddressTypesFacadeLocal;
import ca.bc.gov.moh.fmdb.business.AddressesFacadeLocal;
import ca.bc.gov.moh.fmdb.business.CompaniesFacadeLocal;
import ca.bc.gov.moh.fmdb.business.CompanyTypesFacadeLocal;
import ca.bc.gov.moh.fmdb.business.ConfigurationManagerFacadeLocal;
import ca.bc.gov.moh.fmdb.business.ContactDetailsFacadeLocal;
import ca.bc.gov.moh.fmdb.business.ContactNamesFacadeLocal;
import ca.bc.gov.moh.fmdb.business.ContactTypesFacadeLocal;
import ca.bc.gov.moh.fmdb.business.CountriesFacadeLocal;
import ca.bc.gov.moh.fmdb.business.RegionsFacadeLocal;
import ca.bc.gov.moh.fmdb.business.exception.ItemNotFoundFMDBException;
import ca.bc.gov.moh.fmdb.entity.Addresses;
import ca.bc.gov.moh.fmdb.entity.Companies;
import ca.bc.gov.moh.fmdb.entity.CompanyTypes;
import ca.bc.gov.moh.fmdb.entity.ContactDetails;
import ca.bc.gov.moh.fmdb.entity.ContactNames;
import ca.bc.gov.moh.fmdb.entity.Countries;
import ca.bc.gov.moh.fmdb.entity.Regions;
import ca.bc.gov.moh.fmdb.entity.RegionsPK;
import fmdbwar.Constants;
import fmdbwar.util.RegionsComparator;
import fmdbwar.util.SelectItemHelper;
import fmdbwar.validator.EmailAddressValidator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version CompanyContacts.java
 * @version Created on Sep 11, 2010, 11:17:08 AM
 * @author Greg.Pascucci
 */
@ViewScoped
@Named("CompanyContacts")
public class CompanyContacts implements Serializable {

    @EJB
    private ConfigurationManagerFacadeLocal configurationManagerFacade;
    @EJB
    private ContactDetailsFacadeLocal contactDetailsFacade;
    @EJB
    private RegionsFacadeLocal regionsFacade;
    @EJB
    private ContactTypesFacadeLocal contactTypesFacade;
    @EJB
    private CompaniesFacadeLocal companiesFacade;
    @EJB
    private AddressTypesFacadeLocal addressTypesFacade;
    @EJB
    private AddressesFacadeLocal addressesFacade;
    @EJB
    private CountriesFacadeLocal countriesFacade;
    @EJB
    CompanyTypesFacadeLocal companyTypesFacade;
    @EJB
    ContactNamesFacadeLocal contactNamesFacade;
    private Companies company;
    private Addresses companyAddress;
    private ContactDetails companyDetails;
    private ContactNames contact;
    private List<Companies> companies;
    private List<SelectItem> companyTypes;
    private List<SelectItem> contactNames;
    private List<SelectItem> regionItems;
    private List<SelectItem> countryItems;
    private Long selectedContact;
    private String companySearch;

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public CompanyContacts() {
        company = new Companies();
        company.setCompanyTypeCd(new CompanyTypes());
        companyAddress = new Addresses();
        companyAddress.setFmdbRegions(new Regions(new RegionsPK()));
        company.setFmdbAddressesCollection(new ArrayList<Addresses>());
        company.getFmdbAddressesCollection().add(companyAddress);
        companyAddress.setCompanyId(company);
        companyDetails = new ContactDetails();
        company.setContactDetails(new ArrayList<ContactDetails>());
        company.getContactDetails().add(companyDetails);
        companyDetails.setCompany(company);
        company.setFmdbContactNamesCollection(new ArrayList<ContactNames>());

        contact = new ContactNames();
        contact.setContactDetail(new ContactDetails());
        contact.setAddress(new Addresses());
        contact.getAddress().setFmdbRegions(new Regions(new RegionsPK()));
    }

    @PostConstruct
    public void init() {
        List<CompanyTypes> types = this.getConfigurationManagerFacade().findAllActiveCompanyTypes();
        companyTypes = (List<SelectItem>) SelectItemHelper.buildSelectItemList(types, "companyTypeDsc", "companyTypeCd");

        List<Regions> canadaRegions = regionsFacade.findByCountry(Constants.COUNTRY_CODE_CANADA);
        Collections.sort(canadaRegions, new RegionsComparator());

        List<Regions> usRegions = regionsFacade.findByCountry(Constants.COUNTRY_CODE_US);
        Collections.sort(usRegions, new RegionsComparator());

        canadaRegions.addAll(usRegions);
        regionItems = (List<SelectItem>) SelectItemHelper.buildSelectItemList(canadaRegions, "regionDsc", "fmdbRegionsPK.regionCd", false);

        List<Countries> cnt = countriesFacade.findAll();
        countryItems = (List<SelectItem>) SelectItemHelper.buildSelectItemList(cnt, "countryDsc", "countryCd");

        companyAddress.setAddressTypeCd(addressTypesFacade.find("1"));
        contact.getAddress().setAddressTypeCd(addressTypesFacade.find("2"));
    }

    public void search() {
        companies = companiesFacade.searchCompanies(companySearch);
    }

    /**
     * Populate company information from bean to UI
     */
    private void populateCompanyInfo() {
        if (company != null) {
            companyAddress = addressesFacade.findCompanyAddress(company);
            if (companyAddress == null) {
                companyAddress = new Addresses();
                companyAddress.setAddressTypeCd(addressTypesFacade.find("1"));
                companyAddress.setCompanyId(company);
            }
            companyDetails = contactDetailsFacade.findCompanyContactDetails(company);
            if (companyDetails == null) {
                companyDetails = new ContactDetails();
                companyDetails.setCompany(company);
            }
        }
    }

    public void selectCompany(Companies searchedCompany) throws ItemNotFoundFMDBException {

        company = searchedCompany; 
        populateCompanyInfo();
        if (company.getCompanyTypeCd() == null) {
            company.setCompanyTypeCd(new CompanyTypes());
        }
        
        if (companyAddress.getFmdbRegions() == null) {
            companyAddress.setFmdbRegions(new Regions(new RegionsPK()));
            company.getFmdbAddressesCollection().add(companyAddress);
        }

        if (companyDetails.getContactDtlId() == null) {
            company.getContactDetails().add(companyDetails);
        }
        contactNames = SelectItemHelper.buildSelectItemList(new ArrayList(company.getFmdbContactNamesCollection()), "displayName", "contactNameId");
        clearContact();
        
    }

    public void validateEmail() {
        String email = contact.getContactDetail().getEmailAddress().toString();
        if(email.isEmpty()) {
            return;
        }
        if (EmailAddressValidator.isValidEmailAddress(email)) {
            String errorMessage = Constants.INVALID_EMAIL_ADDRESS;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(errorMessage));
        }
    }

    public String addContact() {
            contactNamesFacade.create(contact);
            contact.setCompanyId(company);
            company.getFmdbContactNamesCollection().add(contact);
            contactNames = SelectItemHelper.buildSelectItemList(new ArrayList(company.getFmdbContactNamesCollection()), "displayName", "contactNameId");
            FacesContext.getCurrentInstance().addMessage("contactMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Contact Added Successfully", null));
            clearContact();
        return null;
    }

    public String updateContact() {
        try {
            contactNamesFacade.edit(contact);
            contactNames = null;
            company = companiesFacade.findCompanyById(contact.getCompanyId().getCompanyId());
            contactNames = SelectItemHelper.buildSelectItemList(new ArrayList(company.getFmdbContactNamesCollection()), "displayName", "contactNameId");
            clearContact();
            FacesContext.getCurrentInstance().addMessage("contactMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Contact Updated Successfully", null));

        } catch (ItemNotFoundFMDBException ex) {
            Logger.getLogger(CompanyContacts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String deleteContact() {
        try {
            contactNamesFacade.remove(contact);
            company.getFmdbContactNamesCollection().remove(contact);
            contactNames = null;
            company = companiesFacade.findCompanyById(contact.getCompanyId().getCompanyId());
            contactNames = SelectItemHelper.buildSelectItemList(new ArrayList(company.getFmdbContactNamesCollection()), "displayName", "contactNameId");
            clearContact();
        } catch (ItemNotFoundFMDBException ex) {
            Logger.getLogger(CompanyContacts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void clearContact() {
        selectedContact = null;
        contact = new ContactNames();
        contact.setContactTypeCd(contactTypesFacade.find("1"));
        contact.setContactDetail(new ContactDetails());
        contact.setAddress(new Addresses());
        contact.getAddress().setContactName(contact);
        contact.getAddress().setFmdbRegions(new Regions(new RegionsPK()));
        contact.getAddress().setAddressTypeCd(addressTypesFacade.find("2"));
        contact.setContactDetail(new ContactDetails());
        contact.getContactDetail().setContactName(contact);
        contact.setCompanyId(company);

    }
    
     /**
     * JSF validator for contact phone
     * @param context
     * @param component
     * @param value 
     */
    public void validatePhone(FacesContext context, UIComponent component, Object value) {
        
        if (!isValidPhoneNumber(value)) {
            String errorMessage = "The phone number you provided [" + value.toString() + "] is invalid. Accepted format is: (XXX)XXX-XXXX";
           
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            throw new ValidatorException(message);
        } 
    }


    /**
     * Validate phone number which must be in format (XXX)123-1234
     * @param phoneNumber
     * @return 
     */
    public boolean isValidPhoneNumber(Object phoneNumber) {
        if(phoneNumber.toString().isEmpty()) {
            return true;
        }
        Pattern pattern = Pattern.compile(Constants.PHONE_NUMBER_PATTERN);
        Matcher match = pattern.matcher(phoneNumber.toString());

        if (!match.matches()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Validate region
     * @param context
     * @param component
     * @param value
     */
    public void validateRegion(FacesContext context,
            UIComponent component, Object value) {

        String countryCd = "";
        String regionCd = value.toString();
        
        if (!regionCd.isEmpty()) {
            Regions region = regionsFacade.findByCode(regionCd);
            if (component.getAttributes().get("companyCountryCd") != null) {
                countryCd = ((HtmlSelectOneMenu) component.getAttributes().get("companyCountryCd")).getSubmittedValue().toString();
            } else if (component.getAttributes().get("contactCountryCd") != null) {
                countryCd = ((HtmlSelectOneMenu) component.getAttributes().get("contactCountryCd")).getSubmittedValue().toString();
            }

            if (!(region.getFmdbRegionsPK().getCountryCd().equalsIgnoreCase(countryCd))) {
                String errorMessage = "The province/state value selected does not belong to the selected country";
                FacesMessage message = new FacesMessage(errorMessage);
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
        }
    }

    /**
     * Validate province or state which must exist
     * @param context
     * @param component
     * @param value
     */
    public void validateProvState(FacesContext context,
            UIComponent component, Object value) {

        List<Regions> regions = regionsFacade.findAll();

        boolean exist = false;
        for (Regions region : regions) {
            if (region.getRegionDsc().equalsIgnoreCase(value.toString())) {
                exist = true;
            }
        }
        if (exist == false) {
            String errorMessage = Constants.PROV_STATE_NOT_EXIST;
            FacesMessage message = new FacesMessage(errorMessage);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

    /**
     * Validate email address
     * @param context
     * @param component
     * @param value
     */
    public void validateEmail(FacesContext context, UIComponent component, Object value) {
        String email = value.toString();
        if(email.isEmpty()) {
            return;
        }
        EmailAddressValidator validator = new EmailAddressValidator();
        boolean valid = validator.isValidEmailAddress(email);
        if (valid == false) {
            String errorMessage = "The email address you provided [" + value + "] is invalid.";
            FacesMessage message = new FacesMessage(errorMessage);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

    public String selectContact(ValueChangeEvent event) {
        
        if (event.getNewValue() != null ) {
            selectedContact = Long.valueOf(event.getNewValue().toString());
            contact = contactNamesFacade.find(selectedContact);

            if (contact.getAddress() == null) {
                contact.setAddress(new Addresses());
                contact.getAddress().setAddressTypeCd(addressTypesFacade.find("2"));
            }
            if (contact.getAddress().getFmdbRegions() == null) {
                contact.getAddress().setFmdbRegions(new Regions(new RegionsPK()));
            }
            if (contact.getAddress().getContactName()==null){
                contact.getAddress().setContactName(contact);
            }
            if (contact.getContactDetail() == null) {
                contact.setContactDetail(new ContactDetails());
            }
        } else {
            clearContact();
        }
        return null;
    }

    public String add() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Add successful", null));
        companiesFacade.create(company);
        clearContact();
        return null;
    }

    public String update() {
        companiesFacade.edit(company);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Update successful", null));
        return null;
    }

    public void clear() {
        company = new Companies();
        company.setCompanyTypeCd(new CompanyTypes());
        companyAddress = new Addresses();
        companyAddress.setFmdbRegions(new Regions(new RegionsPK()));
        companyAddress.setAddressTypeCd(addressTypesFacade.find("1"));
        companyAddress.setCompanyId(company);
        company.setFmdbAddressesCollection(new ArrayList<Addresses>());
        company.getFmdbAddressesCollection().add(companyAddress);
        companyDetails = new ContactDetails();
        company.setContactDetails(new ArrayList<ContactDetails>());
        company.getContactDetails().add(companyDetails);
        companyDetails.setCompany(company);
        company.setFmdbContactNamesCollection(new ArrayList<ContactNames>());

        contact = new ContactNames();
        contact.setContactDetail(new ContactDetails());
        contact.setAddress(new Addresses());
        contact.getAddress().setFmdbRegions(new Regions(new RegionsPK()));
        contact.getAddress().setAddressTypeCd(addressTypesFacade.find("2"));
        selectedContact = null;
        contactNames = new ArrayList<SelectItem>();
        
        companies = null;
        companySearch = null;

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
     * @return the configurationManagerFacade
     */
    public ConfigurationManagerFacadeLocal getConfigurationManagerFacade() {
        return configurationManagerFacade;
    }

    /**
     * @param configurationManagerFacade the configurationManagerFacade to set
     */
    public void setConfigurationManagerFacade(ConfigurationManagerFacadeLocal configurationManagerFacade) {
        this.configurationManagerFacade = configurationManagerFacade;
    }

    /**
     * @return the companyTypes
     */
    public List<SelectItem> getCompanyTypes() {
        return companyTypes;
    }

    /**
     * @param companyTypes the companyTypes to set
     */
    public void setCompanyTypes(List<SelectItem> companyTypes) {
        this.companyTypes = companyTypes;
    }

    /**
     * @return the companyAddresses
     */
    public Addresses getCompanyAddresses() {
        return companyAddress;
    }

    /**
     * @param companyAddresses the companyAddresses to set
     */
    public void setCompanyAddresses(Addresses companyAddresses) {
        this.companyAddress = companyAddresses;
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
     * @return the contact
     */
    public ContactNames getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(ContactNames contactName) {
        this.contact = contactName;
    }

    /**
     * @return the regionItems
     */
    public List<SelectItem> getRegionItems() {
        return regionItems;
    }

    /**
     * @param regionItems the regionItems to set
     */
    public void setRegionItems(List<SelectItem> provinceItems) {
        this.regionItems = provinceItems;
    }

    /**
     * @return the countryItems
     */
    public List<SelectItem> getCountryItems() {
        return countryItems;
    }

    /**
     * @param countryItems the countryItems to set
     */
    public void setCountryItems(List<SelectItem> countryItems) {
        this.countryItems = countryItems;
    }

    /**
     * @return the selectedContact
     */
    public Long getSelectedContact() {
        return selectedContact;
    }

    /**
     * @param selectedContact the selectedContact to set
     */
    public void setSelectedContact(Long selectedContact) {
        this.selectedContact = selectedContact;
    }

    public List<Companies> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Companies> companies) {
        this.companies = companies;
    }

    public String getCompanySearch() {
        return companySearch;
    }

    public void setCompanySearch(String companySearch) {
        this.companySearch = companySearch;
    }

}