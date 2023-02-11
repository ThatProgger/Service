package com.nsh.services.user.convert;

import com.nsh.services.user.model.Role;
import com.nsh.services.user.roleService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class ConvertRoles implements Converter<Integer, Role> {
    @Autowired
    private RoleService roleService;

    @Override
    public <U> Converter<Integer, U> andThen(Converter<? super Role, ? extends U> after) {
        return Converter.super.andThen(after);
    }

    @Override
    public Role convert(Integer source) {
        System.err.println(source);

        Role role = Role.builder().id(0L).role("role").build();
        return role;
    }
}
