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
import fish.payara.security.openid.api.OpenIdContext;
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
    private OpenIdContext context;

    @Inject
    private OidcConfig oidcConfig;

    public void logout() throws ServletException, IOException {

        String idToken = "";
        if (context.getIdentityToken() != null) {
            idToken = context.getIdentityToken().getToken();
        }

        /**
         * Currently Keycloak does not support logging out of SiteMinder IDP's automatically so we set the Keycloak
         * Logout redirect_uri= parameter to be the SiteMinder logout and we set the SiteMinder returl= parameter to be
         * application which chains both logouts for full Single Sign Out. https://github.com/bcgov/ocp-sso/issues/4
         */
        String logoutUrl
                = oidcConfig.getSiteminderLogoutUri()
                + "?retnow=1&returl="
                + oidcConfig.getRedirectUri();
        String keycloakLogoutUrl
                = oidcConfig.getProviderUri()
                + "protocol/openid-connect/logout?post_logout_redirect_uri="
                + URLEncoder.encode(logoutUrl, "UTF-8")
                + "&id_token_hint=" + idToken;

        FacesContext.getCurrentInstance().getExternalContext().redirect(keycloakLogoutUrl);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

}
