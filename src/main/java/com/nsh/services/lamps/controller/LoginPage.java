package com.nsh.services.lamps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class LoginPage {


    @GetMapping
    public String login (){
        return "login";
    }
}
