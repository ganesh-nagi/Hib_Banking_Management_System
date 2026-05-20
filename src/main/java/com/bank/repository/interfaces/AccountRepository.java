package com.bank.repository.interfaces;

import com.bank.entity.Account;

import java.util.List;

public interface AccountRepository {

    boolean saveAccount(Account account);

    Account findAccountByNumber(int accountNumber);

    List<Account> findAllAccounts();

    List<Account> findAllAccountsByUserId(int accountId);
}
