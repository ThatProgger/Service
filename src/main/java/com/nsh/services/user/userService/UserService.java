package com.nsh.services.user.userService;


import com.nsh.services.user.model.User;

import java.util.List;

public interface UserService {

    public User register(User user);

    public List<User> getAll();

    public User findByUsername (final String username);

    public User findById (final int id);

    public long count ();
}
