package com.bank.repository.interfaces;

import com.bank.entity.Account;
import com.bank.entity.BankTransaction;

import java.util.List;

public interface BankTransactionRepository {

    boolean saveTransaction(BankTransaction bankTransaction);

    List<BankTransaction> findAllTransactions();

    List<BankTransaction> findTransactionByAccountNumber(int accountNumber);
}
