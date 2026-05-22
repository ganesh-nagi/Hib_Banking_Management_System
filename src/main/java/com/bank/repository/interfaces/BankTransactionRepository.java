package com.bank.repository.interfaces;

import com.bank.entity.Account;
import com.bank.entity.BankTransaction;
import com.bank.entity.TransactionType;

import java.math.BigDecimal;
import java.util.List;

public interface BankTransactionRepository {

    boolean saveTransaction(BankTransaction bankTransaction);

    List<BankTransaction> findAllTransactions();

    List<BankTransaction> findTransactionByAccountNumber(int accountNumber);

    boolean processAccountTransaction(int accountNumber , BigDecimal amount , TransactionType transactionType);

    boolean processTransfer(int fromAccountNumber, int toAccountNumber, BigDecimal amount );
}
