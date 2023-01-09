package com.company.utils;

import com.company.payload.CustomOAuth2User;
import com.company.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("AuthenticationSuccessHandler invoked");
        System.out.println("Authentication name: " + authentication.getName());

        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();

        String oauth2ClientName = oauthUser.getOauth2ClientName();

        System.out.println("Principal: " + oauthUser);

        userService.processOAuthPostLogin(oauthUser.getEmail(), oauthUser.getLogin(), oauth2ClientName);

        response.sendRedirect("/list");
    }
}
