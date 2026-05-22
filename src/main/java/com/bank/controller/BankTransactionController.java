package com.bank.controller;

import com.bank.entity.BankTransaction;
import com.bank.repository.interfaces.BankTransactionRepository;
import com.bank.service.interfaces.BankTransactionService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class BankTransactionController {

    private final BankTransactionService bankTransactionService;
    private final Scanner scanner;

    public BankTransactionController(BankTransactionService bankTransactionService, Scanner scanner) {
        this.bankTransactionService = bankTransactionService;
        this.scanner = scanner;
    }


    public void showTransactionMenu() {

        while (true)
        {

            System.out.println("======TRANSACTION MENU======");
            System.out.println("1. Deposit money ");
            System.out.println("2. Withdraw money ");
            System.out.println("3. Transfer money ");
            System.out.println("4. view all transactions");
            System.out.println("5. view transactions by accountNumber");
            System.out.println("6. Back to main menu");
            System.out.println("Enter your choice : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {

                case 1:
                    depositMoney();
                    break;
                case 2:
                    withdrawMoney();
                    break;
                case 3:
                    transferMoney();
                    break;
                case 4:
                    viewAllTransactions();
                    break;
                case 5:
                    viewTransactionsByAccountNumber();
                     break;
                 case 6:
                     return;
                 default:

            }
        }
    }

    public void depositMoney() {
        System.out.println("Enter accountNumber : ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter deposit amount : ");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();

        bankTransactionService.deposit(accountNumber, amount);
    }
    public void viewAllTransactions() {

        List<BankTransaction> transactions = bankTransactionService.getAllTransactions();

        if(transactions.isEmpty()) {
            System.out.println("No transactions found");
            return;
        }
        for (BankTransaction transaction : transactions) {
            System.out.println(transaction);
        }

    }
    public void viewTransactionsByAccountNumber() {

        System.out.println("Enter account number : ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();

        List<BankTransaction> transactions = bankTransactionService.getAllTransactionsByAccountNumber(accountNumber);
        if(transactions.isEmpty()) {
            System.out.println("No transactions found");
            return;
        }
        for (BankTransaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public void withdrawMoney() {
        System.out.println("Enter account number : ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter withdraw amount : ");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();

        bankTransactionService.withdraw(accountNumber, amount);
    }

    public void transferMoney() {
        System.out.println("Enter sender account number : ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter receiver account number : ");
        int accountNumber2 = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter transfer money : ");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();

        bankTransactionService.transfer(accountNumber, accountNumber2, amount);
    }
}
