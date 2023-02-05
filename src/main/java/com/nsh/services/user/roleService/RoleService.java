package com.nsh.services.user.roleService;



import com.nsh.services.user.model.Role;

import java.util.List;

public interface RoleService {
    public int count ();
    public Role findById (int id);
    public List<Role> findAllRoles ();
    public Role findByRole (String role) throws NullPointerException;
    public Role saveRole (Role role) throws NullPointerException;
}
