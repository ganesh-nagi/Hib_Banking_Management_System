package com.bank.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bank_transactions")
public class BankTransaction {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "transaction_id")
        private int transactionId;

        @Enumerated(EnumType.STRING)
        @Column(name = "transaction_type", nullable = false , length = 20)
        private TransactionType transactionType;

        @Column(nullable = false , precision =  12, scale = 2)
        private BigDecimal amount;

        @Column(name = "transaction_time" , nullable = false)
        private LocalDateTime TransactionTime;

        @ManyToOne
        @JoinColumn(name = "account_number" , nullable = false)
        private Account account;


        public BankTransaction() {
        }

        public BankTransaction( TransactionType transactionType,
                                BigDecimal amount,
                                LocalDateTime TransactionTime,
                                Account account) {
            this.transactionType = transactionType;
            this.amount = amount;
            this.TransactionTime = TransactionTime;
            this.account = account;
        }

    public int getTransactionId() {
        return transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTransactionTime() {
        return TransactionTime;
    }

    public Account getAccount() {
        return account;
    }

//    @Override
//    public String toString() {
//        return "BankTransaction{" +
//                "transactionId=" + transactionId +
//                ", transactionType=" + transactionType +
//                ", amount=" + amount +
//                ", TransactionTime=" + TransactionTime +
//                ", account=" + account +
//                '}';
//    }

    @Override
    public String toString() {
        return "BankTransaction{" +
                "transactionId=" + transactionId +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                ", transactionTime=" + getTransactionTime() +
                ", accountNumber=" + account.getAccountNumber() +
                '}';
    }
}
