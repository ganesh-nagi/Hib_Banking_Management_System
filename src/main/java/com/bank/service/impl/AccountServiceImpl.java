package com.bank.service.impl;

import com.bank.entity.Account;
import com.bank.entity.AccountType;
import com.bank.entity.User;
import com.bank.repository.interfaces.AccountRepository;
import com.bank.repository.interfaces.UserRepository;
import com.bank.service.interfaces.AccountService;

import java.math.BigDecimal;
import java.util.List;

public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private UserRepository userRepository;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createAccount(int userId, String accountTypeIntput, BigDecimal initialBalance) {

        if(userId <0){
            System.out.println("Invalid user ID");
            return;
        }

        if(accountTypeIntput == null || accountTypeIntput.isEmpty()){
            System.out.println("Invalid account type");
            return;
        }
        if(initialBalance == null || initialBalance.compareTo(BigDecimal.ZERO) == 0){
            System.out.println("Initial balance cannot be negative");
            return;
        }

        User user = userRepository.findUserById(userId);

        if(user == null){
            System.out.println("user not created , create user first!!");
            return;
        }

        AccountType accountType;

        try {
            accountType = AccountType.valueOf(accountTypeIntput);
        }
        catch (IllegalArgumentException e){
            System.out.println("Invalid account type , user SAVINGS or CURRENT");
            return;
        }

        Account account = new Account(accountType , initialBalance ,user);

        boolean isSaved = accountRepository.saveAccount(account);

        if(isSaved){
            System.out.println("Account created Successfully.");
            System.out.println("Generated Account Number : " + account.getAccountNumber());
        }
        else {
            System.out.println("Account creation Failed.");
        }
    }

    @Override
    public Account getAccountByNumber(int accountNumber) {
        if(accountNumber<=0){
            System.out.println("Invalid account number");
            return null;
        }
        return accountRepository.findAccountByNumber(accountNumber);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAllAccounts();
    }

    @Override
    public List<Account> getAllAccountsByUserID(int userId) {
        if(userId <0){
            System.out.println("Invalid user ID");
            return null;
        }
        return accountRepository.findAllAccountsByUserId(userId);
    }
}
