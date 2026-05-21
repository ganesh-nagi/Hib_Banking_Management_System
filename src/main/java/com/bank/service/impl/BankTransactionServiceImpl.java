package com.bank.service.impl;

import com.bank.entity.Account;
import com.bank.entity.BankTransaction;
import com.bank.entity.TransactionType;
import com.bank.repository.interfaces.AccountRepository;
import com.bank.repository.interfaces.BankTransactionRepository;
import com.bank.service.interfaces.BankTransactionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionServiceImpl implements BankTransactionService {

    private final BankTransactionRepository bankTransactionRepository;
    private final AccountRepository accountRepository;

    public BankTransactionServiceImpl(BankTransactionRepository bankTransactionRepository, AccountRepository accountRepository) {
        this.bankTransactionRepository = bankTransactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void deposit(int accountNumber, BigDecimal amount) {

        if(accountNumber <= 0 ) {
            System.out.println("Invalid account number");
            return;
        }
        if(amount == null  || amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Deposit amount must be greater than zero");
            return;
        }
        Account account = accountRepository.findAccountByNumber(accountNumber);

        if(account == null) {
            System.out.println("Account not found");
            return;
        }

        BigDecimal updateBalance = account.getBalance().add(amount);
        account.setBalance(updateBalance);

        boolean accountUpdated = accountRepository.updateAccount(account);
        if(!accountUpdated) {
            System.out.println("Deposit Failed while updating Account balance");
            return;
        }

        BankTransaction bankTransaction = new BankTransaction(
                TransactionType.DEPOSIT ,
                amount ,
                LocalDateTime.now() ,
                account
                );

        boolean transacionSaved = bankTransactionRepository.saveTransaction(bankTransaction);
        if(transacionSaved) {
            System.out.println("Deposited Successfully");
            System.out.println("Updated Balance : " + updateBalance);
        }
        else {
            System.out.println("Deposited amount updated , but transaction history failed .");
        }
    }

    @Override
    public List<BankTransaction> getAllTransactions() {
        return bankTransactionRepository.findAllTransactions();
    }

    @Override
    public List<BankTransaction> getAllTransactionsByAccountNumber(int accountNumber) {
        if(accountNumber <= 0) {
            System.out.println("Invalid account number");
            return new ArrayList<>();
        }
        return bankTransactionRepository.findTransactionByAccountNumber(accountNumber);
    }
}
