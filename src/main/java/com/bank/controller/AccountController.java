package com.bank.controller;

import com.bank.entity.Account;
import com.bank.service.interfaces.AccountService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class AccountController {

    AccountService accountService;
    Scanner scanner;

    public AccountController(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    public void showAccountMenu() {

        while (true) {

            System.out.println("======Account Menu======");
            System.out.println("1. create Accoun");
            System.out.println("2. find Account by number");
            System.out.println("3. view all Accounts");
            System.out.println("4. view account by userId");
            System.out.println("5. Back to main menu");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    findAccountByNumber();
                    break;
                case 3:
                    viewAllAccounts();
                    break;
                case 4:
                    viewAccountByUserId();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice , try again");

            }
        }

    }

    private void createAccount() {
        System.out.print("Enter existing user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter account type (SAVINGS/CURRENT): ");
        String accountType = scanner.nextLine();

        System.out.print("Enter initial balance: ");
        BigDecimal initialBalance = scanner.nextBigDecimal();
        scanner.nextLine();

        accountService.createAccount(userId, accountType, initialBalance);
    }

    private void findAccountByNumber() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();

        Account account = accountService.getAccountByNumber(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
        } else {
            System.out.println(account);
        }

    }

    private void viewAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();

        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    private void viewAccountByUserId() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        List<Account> accounts = accountService.getAllAccountsByUserID(userId);

        if (accounts.isEmpty()) {
            System.out.println("No accounts found for this user.");
            return;
        }

        for (Account account : accounts) {
            System.out.println(account);
        }

    }
}


