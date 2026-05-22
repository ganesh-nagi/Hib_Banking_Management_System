package com.bank.service.interfaces;

import com.bank.entity.BankTransaction;

import java.math.BigDecimal;
import java.util.List;

public interface BankTransactionService {

    void deposit(int accountNumber, BigDecimal amount);

    List<BankTransaction> getAllTransactions();

    List<BankTransaction> getAllTransactionsByAccountNumber(int accountNumber);

    void withdraw(int accountNumber, BigDecimal amount);

    void transfer(int fromAccountNumber, int toAccountNumber, BigDecimal amount);
}
