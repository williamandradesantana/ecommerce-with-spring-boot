package io.github.williamandradesantana.security;

import io.github.williamandradesantana.modules.user.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {

    public User getUserByLogged() throws UsernameNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof CustomAuthentication customAuthentication) return customAuthentication.getUser();
        return null;
    }
}
