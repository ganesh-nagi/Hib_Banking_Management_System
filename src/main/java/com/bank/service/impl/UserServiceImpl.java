package com.bank.service.impl;

import com.bank.entity.User;
import com.bank.repository.interfaces.UserRepository;
import com.bank.service.interfaces.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(String name, String phoneNumber, String email) {
        if(name==null || name.isEmpty()){
            System.out.println("Name cannot be empty");
            return;
        }
        if(phoneNumber==null || phoneNumber.isEmpty()){
            System.out.println("Phone number cannot be empty");
            return;
        }
        if(email==null || email.isEmpty()){
            System.out.println("Email cannot be empty");
            return;
        }

        User user = new User(name,phoneNumber,email);
        boolean isSaved = userRepository.saveUser(user);
        if(isSaved){
            System.out.println("User has been saved successfully");
        }
        else {
            System.out.println("User creation failed ");
        }
    }

    @Override
    public User findUserById(int userId) {
        if(userId<=0){
            System.out.println("Invalid user id");
            return null;
        }

        return userRepository.findUserById(userId);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }
}
