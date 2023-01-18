/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.bc.gov.hlth.security;
import fish.payara.security.annotations.ClaimsDefinition;
import fish.payara.security.annotations.OpenIdAuthenticationDefinition;

/**
 *
 * @author trevor.schiavone
 */
@OpenIdAuthenticationDefinition(
    providerURI="#{oidcConfig.providerUri}",
    clientId="#{oidcConfig.clientId}",
    clientSecret="#{oidcConfig.clientSecret}",
    redirectURI="#{oidcConfig.redirectUri}",
    scope="#{oidcConfig.scope}",
    claimsDefinition = @ClaimsDefinition( callerNameClaim="#{oidcConfig.callerNameClaim}", callerGroupsClaim="#{oidcConfig.callerGroupsClaim}" ),
    extraParameters = {
        "idps_to_show=idir" //comma separated list of which identity providers show up on the login page. By default all are shown.
    }
)
public class KeycloakSecurityBean {
    
}
