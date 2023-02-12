package com.nsh.services.user.controllers;

import com.nsh.services.user.model.Role;
import com.nsh.services.user.model.User;
import com.nsh.services.user.roleService.RoleService;
import com.nsh.services.user.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("users")
public class UserPage {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public String getUser (Model model){
        List<Role> roleList = roleService.findAllRoles();

//        List<RoleDto> roleList = Arrays.asList(
//                RoleDto.builder().id(1).role("ADMIN").build(),
//                RoleDto.builder().id(2).role("USER").build(),
//                RoleDto.builder().id(3).role("TECHNICAL").build()
//        );
        List<User> users = userService.getAll();

        model.addAttribute("user", new User ());
        model.addAttribute("roleList", roleList);
        model.addAttribute("userList", users);


        return "user";
    }



    @PostMapping
    public String postUser (@ModelAttribute User user){


        List<Role> fakeRoles = user.getRoles();
        List<Role> validRoles = fakeRoles.stream().map(role -> {
            Role r = roleService.findByRole(role.getRole());
            return  roleService.findByRole(role.getRole());
        }).collect(Collectors.toList());

        user.setRoles(validRoles);

        String password = user.getPassword();
        password = bCryptPasswordEncoder.encode(password);
        user.setPassword(password);
        userService.register(user);

        return "redirect:/users";
    }

    @PostMapping("update")
    public String updateUser (@ModelAttribute User user){
        System.err.println(user);
        return "redirect:/users";
    }
}
