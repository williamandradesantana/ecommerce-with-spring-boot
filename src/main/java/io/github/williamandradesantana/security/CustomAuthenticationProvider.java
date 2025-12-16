package io.github.williamandradesantana.security;

import io.github.williamandradesantana.modules.user.entity.User;
import io.github.williamandradesantana.modules.user.services.UserServices;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserServices userServices;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(UserServices userServices, PasswordEncoder passwordEncoder) {
        this.userServices = userServices;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        User userFound = userServices.getByUsername(login);
        if (userFound == null) {
            throw getUsernameNotFoundException();
        }

        var passwordMatches = passwordEncoder.matches(password, userFound.getPassword());

        if (passwordMatches) {
            return new CustomAuthentication(userFound);
        }
        throw getUsernameNotFoundException();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);

    }

    private UsernameNotFoundException getUsernameNotFoundException() {
        return new UsernameNotFoundException("Username or password incorrect!");
    }
}
