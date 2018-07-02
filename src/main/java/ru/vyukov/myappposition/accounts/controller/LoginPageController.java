package ru.vyukov.myappposition.accounts.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;

@Controller
public class LoginPageController {


    @GetMapping("/")
    public String loginPage(Principal principal) {
        return "login";
    }

}