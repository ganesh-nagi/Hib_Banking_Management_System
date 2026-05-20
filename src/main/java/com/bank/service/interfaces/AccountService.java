package com.bank.service.interfaces;

import com.bank.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    void createAccount(int UserID, String accountTypeIntput, BigDecimal initialBalance);

    Account getAccountByNumber(int accountNumber);

    List<Account> getAllAccounts();

    List<Account> getAllAccountsByUserID(int userId);

}
