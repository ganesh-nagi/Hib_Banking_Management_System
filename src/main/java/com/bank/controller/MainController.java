package com.bank.controller;

import java.util.Scanner;

public class MainController {

    private final UserController userController;
    private final AccountController accountController;
    private final Scanner scanner;

    public MainController(UserController userController,AccountController accountController, Scanner scanner) {
        this.userController = userController;
        this.accountController = accountController;
        this.scanner = scanner;
    }

    public void start(){

        while(true){
            System.out.println("=====BANKING SYSTEM======");
            System.out.println("1.user Management ");
            System.out.println("2.Account Management ");
            System.out.println("3. Exit ");
            System.out.println("Enter your choice :");

            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    userController.ShowUserMenu();
                    break;
                case 2:
                    accountController.showAccountMenu();
                    break;
                case 3:
                    System.out.println("Application Closed");
                    return;
                default:
                    System.out.println("Invalid choice , try again");
            }

        }

    }
}
