package com.nsh.services.user.convert;

import com.nsh.services.user.model.Role;
import com.nsh.services.user.roleService.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class ConvertRoles implements Converter<String, Role> {
    private Logger logger = LogManager.getLogger(ConvertRoles.class);

    @Autowired
    private RoleService roleService;

    @Override
    public Role convert(String source) {
        Role role = Role.builder().id(0L).users(null).role(source).build();
        return role;
    }
}
