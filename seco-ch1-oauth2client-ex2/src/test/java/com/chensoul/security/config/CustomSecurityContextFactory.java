package com.chensoul.security.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class CustomSecurityContextFactory
        implements WithSecurityContextFactory<WithCustomUser> {

    @Override
    public SecurityContext createSecurityContext(WithCustomUser withCustomUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        Collection<? extends GrantedAuthority> authorities = List.of(() -> "read");
        String clientId = "client_id";

        OAuth2User principal = new DefaultOAuth2User(authorities, Map.of(clientId, clientId), clientId);

        var a = new OAuth2AuthenticationToken(principal, authorities, clientId);
        context.setAuthentication(a);
        return context;
    }
}
