package com.bank.service.interfaces;

import com.bank.entity.User;

import java.util.List;

public interface UserService {

    void createUser(String name, String phoneNumber, String email);

    User findUserById(int userId);
    List<User> findAllUsers();
}
