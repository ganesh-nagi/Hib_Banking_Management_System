package com.bank.repository.interfaces;

import com.bank.entity.User;

import java.util.List;

public interface UserRepository {

    boolean saveUser(User user);
    User findUserById(int userId);
    List<User> findAllUsers();
}
