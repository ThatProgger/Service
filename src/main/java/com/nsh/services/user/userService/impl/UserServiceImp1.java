package com.nsh.services.user.userService.impl;


import com.nsh.services.user.enums.UserStatus;
import com.nsh.services.user.model.User;
import com.nsh.services.user.repository.UserRepository;
import com.nsh.services.user.userService.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UserServiceImp1 implements UserService {
    private Logger logger = LogManager.getLogger(UserServiceImp1.class);
    @Autowired
    private UserRepository userRepository;



    @Override
    public User register(User user) {
        try {
            user.setUserStatus(UserStatus.ACTIVE);
            User registeredUser = userRepository.save(user);
            if(logger.isDebugEnabled())
            logger.info("[register] - user: {} successfully registered.", registeredUser);
            return registeredUser;
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        Iterable<User> iterable = userRepository.findAll();
        iterable.forEach(userList::add);
        if(logger.isDebugEnabled())
        log.info("[getAll] - {} users found", userList.size());
        return userList;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(logger.isDebugEnabled())
        logger.info("[findByUsername] - user: {} found by username: {}", user, username);
        return user;
    }

    @Override
    public User findById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if(logger.isDebugEnabled())
            logger.info("[findById] - user: {} found by id: {}", user, id);
            return user;
        }
        return null;
    }


    @Override
    public long count() {
        long count = userRepository.count();
        if(logger.isDebugEnabled())
        logger.info("[count] - amount users: {}", count);
        return count;
    }
}
