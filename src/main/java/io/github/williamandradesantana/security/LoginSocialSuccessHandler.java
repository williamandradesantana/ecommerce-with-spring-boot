package io.github.williamandradesantana.security;

import io.github.williamandradesantana.modules.user.dto.UserRequestDTO;
import io.github.williamandradesantana.modules.user.entity.User;
import io.github.williamandradesantana.modules.user.services.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSocialSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final String DEFAULT_PASSWORD = "321";
    private final UserServices userServices;
    public LoginSocialSuccessHandler(UserServices userServices) {
        this.userServices = userServices;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
             HttpServletResponse response,
             Authentication authentication) throws ServletException, IOException {

        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = oAuth2AuthenticationToken.getPrincipal();

        String email = oAuth2User.getAttribute("email");

        User user = userServices.getByEmail(email);

        if (user == null) {
            user = registeringUser(email);
        }

        authentication = new CustomAuthentication(user);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        super.onAuthenticationSuccess(request, response, authentication);
    }

    private User registeringUser(String email) {
        User user = new User();

        user.setUsername(extractUsernameFromEmail(email));
        user.setEmail(email);
        user.setRole("CUSTOMER");
        user.setPassword(DEFAULT_PASSWORD);
        userServices.create(new UserRequestDTO(user));
        return user;
    }

    private String extractUsernameFromEmail(String email) {
        return email.substring(0, email.indexOf("@"));
    }
}
