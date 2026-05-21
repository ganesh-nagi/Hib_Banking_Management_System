package com.bank.main;

import com.bank.config.HibernateUtil;
import com.bank.controller.AccountController;
import com.bank.controller.BankTransactionController;
import com.bank.controller.MainController;
import com.bank.controller.UserController;
import com.bank.repository.impl.AccountRepositoryImpl;
import com.bank.repository.impl.BankTransactionRepositoryImpl;
import com.bank.repository.impl.UserRepositoryImpl;
import com.bank.repository.interfaces.AccountRepository;
import com.bank.repository.interfaces.BankTransactionRepository;
import com.bank.repository.interfaces.UserRepository;
import com.bank.service.impl.AccountServiceImpl;
import com.bank.service.impl.BankTransactionServiceImpl;
import com.bank.service.impl.UserServiceImpl;
import com.bank.service.interfaces.AccountService;
import com.bank.service.interfaces.BankTransactionService;
import com.bank.service.interfaces.UserService;

import java.util.Scanner;

public class BankApplication
{
    public static void main( String[] args )
    {

        Scanner scanner = new Scanner(System.in);


        UserRepository userRepository = new UserRepositoryImpl();
        AccountRepository accountRepository = new AccountRepositoryImpl();
        BankTransactionRepository bankTransactionRepository = new BankTransactionRepositoryImpl();

        UserService userService = new UserServiceImpl(userRepository);
        AccountService accountService = new AccountServiceImpl(accountRepository , userRepository);
        BankTransactionService bankTransactionService = new BankTransactionServiceImpl(bankTransactionRepository ,accountRepository);

        UserController userController = new UserController(userService , scanner);
        AccountController accountController = new AccountController(accountService, scanner);
        BankTransactionController bankTransactionController = new BankTransactionController(bankTransactionService , scanner);

        MainController mainController = new MainController(userController ,accountController ,bankTransactionController , scanner);

        HibernateUtil.getSessionFactory();

        mainController.start();

        HibernateUtil.shutdown();

        scanner.close();

    }
}
