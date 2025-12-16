package io.github.williamandradesantana.common.controller;

import io.github.williamandradesantana.security.CustomAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String loginPage() {
        return  "login";
    }

    @GetMapping("/")
    @ResponseBody
    public String home(Authentication authentication) {
        if (authentication instanceof CustomAuthentication customAuthentication) {
            return customAuthentication.getName();
        }
        return "Hello World! " + authentication.getName();
    }
}
