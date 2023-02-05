package com.nsh.services.user.repository;


import com.nsh.services.user.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    public Role findByRole(String role);
}
