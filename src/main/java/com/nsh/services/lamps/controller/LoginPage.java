package com.nsh.services.lamps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Passes a page with the possibility of authorization to the client side.
 * @author Mikhail Dedyukhin
 * @since 1.0
  */
@Controller
@RequestMapping("login")
public class LoginPage {


    @GetMapping
    public String login (){
        return "login";
    }
}
