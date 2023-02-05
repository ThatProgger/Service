package com.nsh.services.user.repository;


import com.nsh.services.user.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
//    public User findByUsername(final String username);

    public User findByUsername(String username);
}
