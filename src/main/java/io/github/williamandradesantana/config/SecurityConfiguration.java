package io.github.williamandradesantana.config;

import io.github.williamandradesantana.modules.user.services.UserServices;
import io.github.williamandradesantana.security.CustomUserDetailsService;
import io.github.williamandradesantana.security.LoginSocialSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, LoginSocialSuccessHandler loginSocialSuccessHandler) throws Exception {

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(configurer -> {
                    configurer.loginPage("/login");
                })
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/api/v1/ordersItems/**").permitAll();
                    authorize.requestMatchers("/api/v1/login/**").permitAll();
                    authorize.requestMatchers("/api/v1/clients/**").permitAll();
                    authorize.requestMatchers("/login/**").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .oauth2ResourceServer((oauth2rs) -> oauth2rs.jwt(Customizer.withDefaults()))
                .oauth2Login(oauth2 -> {
                    oauth2.successHandler(loginSocialSuccessHandler);
                    oauth2.loginPage("/login");
                })
                .build();
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // remove "ROLE_" prefix
    }

    @Bean
    public UserDetailsService userDetailsService(UserServices userServices) {
        return new CustomUserDetailsService(userServices);
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        var authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthorityPrefix("");

        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
        return converter;
    }
}
