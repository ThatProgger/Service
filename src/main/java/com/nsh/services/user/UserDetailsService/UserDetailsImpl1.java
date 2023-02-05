package com.nsh.services.user.UserDetailsService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl1 implements UserDetails {
    private Logger logger = LogManager.getLogger(UserDetailsServiceImpl1.class);
    private final String username;
    private final String password;
    private final boolean enabled;
    private final Collection<? extends  GrantedAuthority> authorities;

    public UserDetailsImpl1(String username, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(logger.isDebugEnabled())
        logger.info("[getAuthorities] - collection: {}", authorities);

        return authorities;
    }

    @Override
    public String getPassword() {
        if(logger.isDebugEnabled())
        logger.info("[getPassword] - password: {}", password);
        return password;
    }


    @Override
    public String getUsername() {
        if(logger.isDebugEnabled())
        logger.info("[getUsername] - username: {}", username);
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(logger.isDebugEnabled())
        logger.info("[isEnabled] - enabled: {}", enabled);
        return enabled;
    }
}
