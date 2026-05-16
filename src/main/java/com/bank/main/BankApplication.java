package com.bank.main;

import com.bank.config.HibernateUtil;
import com.bank.controller.MainController;
import com.bank.controller.UserController;
import com.bank.repository.impl.UserRepositoryImpl;
import com.bank.repository.interfaces.UserRepository;
import com.bank.service.impl.UserServiceImpl;
import com.bank.service.interfaces.UserService;

import java.util.Scanner;

public class BankApplication
{
    public static void main( String[] args )
    {

        Scanner scanner = new Scanner(System.in);


        UserRepository userRepository = new UserRepositoryImpl();

        UserService userService = new UserServiceImpl(userRepository);

        UserController userController = new UserController(userService , scanner);

        MainController mainController = new MainController(userController , scanner);

        mainController.start();

        HibernateUtil.shutdown();

        scanner.close();

    }
}
