package com.nsh.services.user.controllers;

import com.nsh.services.user.model.Role;
import com.nsh.services.user.model.User;
import com.nsh.services.user.roleService.RoleService;
import com.nsh.services.user.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("users")
public class UserPage {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping
    public String getUser (Model model){
        List<Role> roleList = roleService.findAllRoles();
        List<User> users = userService.getAll();

        model.addAttribute("user", new User ());
        model.addAttribute("roleList", roleList);
        model.addAttribute("userList", users);


        return "user";
    }



    @PostMapping
    public String postUser (@ModelAttribute User user){
        System.err.println(user);
        return "redirect:/users";
    }
}
