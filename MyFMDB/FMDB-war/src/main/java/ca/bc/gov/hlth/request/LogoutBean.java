/*******************************************************************************
 * Copyright © 2015, Province of British Columbia.                             *
 *                                                                             *
 * All rights reserved.                                                        *
 *                                                                             *
 * File:                        UserBean.java                                  *
 * Date of Last Commit: $Date::                                              $ *
 * Revision Number:      $Rev::                                              $ *
 * Last Commit by:    $Author::                                              $ *
 *                                                                             *
 *******************************************************************************/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.bc.gov.hlth.request;


import ca.bc.gov.hlth.security.OidcConfig;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;

@RequestScoped
@Named("Logout")
public class LogoutBean implements Serializable {
    
    @Inject 
    private OidcConfig oidcConfig;
    
    public void logout() throws ServletException, IOException {
        
        /* Currently Keycloak does not support logging out of SiteMinder IDP's automatically
        so we set the Keycloak Logout redirect_uri= paramter to be the SiteMinder logout
        and we set the Siteminder returl= parameter to be application which chains both logouts for full Single Sign Out.
        https://github.com/bcgov/ocp-sso/issues/4 */
        
        String siteMinderLogoutUrl = oidcConfig.getSiteminderLogoutUri() + "?retnow=1&returl=" + oidcConfig.getRedirectUri();
        String keycloakLogoutUrl = oidcConfig.getProviderUri() + "protocol/openid-connect/logout?redirect_uri=" + URLEncoder.encode(siteMinderLogoutUrl, "UTF-8");
        
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .redirect(keycloakLogoutUrl);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
  
}
