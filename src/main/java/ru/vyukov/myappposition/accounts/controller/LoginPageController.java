package ru.vyukov.myappposition.accounts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoginPageController {


    @GetMapping("/")
    public String loginPage(Principal principal) {
        return "login";
    }

}