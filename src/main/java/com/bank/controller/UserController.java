package com.bank.controller;

import com.bank.entity.User;
import com.bank.service.interfaces.UserService;

import java.util.List;
import java.util.Scanner;

public class UserController {

    private final UserService userService;
    private final Scanner scanner;

    public UserController(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    public void ShowUserMenu(){

        while(true){
            System.out.println("=====User Menu=====");
            System.out.println("1. create User");
            System.out.println("2. find user by id ");
            System.out.println("3. view all users ");
            System.out.println("4. back to main menu");
            System.out.println("0. Enter your choice : ");

            int choice = new Scanner(System.in).nextInt();
            scanner.nextLine();


            switch (choice){
                case 1:
                    createUser();
                    break;
                case 2:
                    findUserById();
                    break;
                case 3:
                    findAllUsers();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice , try again");
            }
        }
    }
    public void createUser(){
        System.out.println("Enter name :");
        String name = scanner.nextLine();

        System.out.println("Enter phone number :");
        String phoneNumber = scanner.nextLine();

        System.out.println("Enter email id :");
        String email = scanner.nextLine();

         userService.createUser(name,phoneNumber,email);

    }
    public void findUserById(){
        System.out.println("Enter user id :");
        int id = scanner.nextInt();
        User user = userService.findUserById(id);

        if(user == null){
            System.out.println("User not found");
        }
        else{
            System.out.println("User found  - " + user);
        }
    }
    public void findAllUsers(){

        List<User> users = userService.findAllUsers();
        if(users.isEmpty()){
            System.out.println("No users found.");
            return;
        }
        for(User user : users){
            System.out.println(user);
        }

    }

}
