package com.bank.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    private int accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type" , nullable = false , length = 20)
    private AccountType accountType;

    @Column(nullable = false , precision = 12, scale = 2)
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    public Account() {}

    public Account( AccountType accountType, BigDecimal balance, User user) {
        this.accountType = accountType;
        this.balance = balance;
        this.user = user;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    @Override
//    public String toString() {
//        return "Account{" +
//                "accountNumber=" + accountNumber +
//                ", accountType=" + accountType +
//                ", balance=" + balance +
//                ", user=" + user +
//                '}';
//    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", accountType=" + accountType +
                ", balance=" + balance +
                ", userId=" + user.getUserID() +
                '}';
    }

}
