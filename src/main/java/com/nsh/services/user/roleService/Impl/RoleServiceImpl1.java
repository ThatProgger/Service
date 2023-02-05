package com.nsh.services.user.roleService.Impl;

import com.nsh.services.user.model.Role;
import com.nsh.services.user.repository.RoleRepository;
import com.nsh.services.user.roleService.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The class implements the RoleService interface and allows you to organize work with the database
 * @author Mikhail Dedyukhin
 * @since 1.0
 * @see RoleService
 */
@Service
@Slf4j
public class RoleServiceImpl1 implements RoleService {
    Logger logger = LogManager.getLogger(RoleServiceImpl1.class);
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public int count() {
        int count = (int) roleRepository.count();
        if(logger.isDebugEnabled())
        logger.info("[count] - found: {} entries", count);
        return count;
    }

    @Override
    public Role findById(int id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if(optionalRole.isPresent()){
            if(logger.isDebugEnabled())
            logger.info("[findById] - role: {} found by id {}", optionalRole.get(), id);
            return optionalRole.get();
        } else {
            if(logger.isDebugEnabled())
            logger.warn("[findById] - role by id {} not found", id);
        }
        return null;
    }

    @Override
    public List<Role> findAllRoles() {
        Iterable<Role> iterable = roleRepository.findAll();
        List<Role> roleList = new ArrayList<>();
        iterable.forEach(roleList::add);
        if(roleList.size() > 0)
            if(logger.isDebugEnabled())
            logger.info("[findAllRoles] - roles found: {}", roleList.size());
        else
            if(logger.isDebugEnabled())
            logger.info("[findAllRoles] - roles not found");
        return roleList;
    }

    @Override
    public Role saveRole(Role role) throws NullPointerException{
        if(role == null)
            throw new NullPointerException("The role to save is null");
        try {
           Role registeredRole = roleRepository.save(role);
            if(logger.isDebugEnabled())
           logger.info("[saveRole] - role: {} successfully registered.", registeredRole);
           return registeredRole;
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Role findByRole(String role) throws NullPointerException {
        if(role.equals("") || role == null)
            throw new NullPointerException("The role is null");
        Role r = roleRepository.findByRole(role);
        if(logger.isDebugEnabled())
        logger.info("IN findByRole - role: {} found", r);
        return r;
    }
}
