package io.github.williamandradesantana.security;

import io.github.williamandradesantana.modules.user.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServices services;

    public CustomUserDetailsService(UserServices userServices) {
        this.services = userServices;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = services.getByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not exists!");

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole())
                .build();
    }
}
