package com.nsh.services.user.UserDetailsService;


import com.nsh.services.user.enums.UserStatus;
import com.nsh.services.user.model.Role;
import com.nsh.services.user.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class UserDetailsFactory {
    private static Logger logger = LogManager.getLogger(UserDetailsFactory.class);
    public UserDetailsFactory (){

    }

    public static UserDetails create (User user){
        return new UserDetailsImpl1(
                user.getUsername(),
                user.getPassword(),
                user.getUserStatus() == UserStatus.ACTIVE ? true : false,
                authorities(user.getRoles())
        );
    }

    private static Collection<? extends GrantedAuthority> authorities (List<Role> roles){
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = roles.stream().map(role -> {
            return new SimpleGrantedAuthority(role.getRole());
        }).collect(Collectors.toList());
        return simpleGrantedAuthorities;
    }

}
