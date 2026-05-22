package com.bank.repository.impl;

import com.bank.config.HibernateUtil;
import com.bank.entity.Account;
import com.bank.entity.BankTransaction;
import com.bank.entity.TransactionType;
import com.bank.repository.interfaces.BankTransactionRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionRepositoryImpl implements BankTransactionRepository {

    @Override
    public boolean saveTransaction(BankTransaction bankTransaction) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(bankTransaction);
            transaction.commit();
            return true;
        }
        catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error while saving transaction" + e.getMessage());
            return false;
        }
    }

    @Override
    public List<BankTransaction> findAllTransactions() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from BankTransaction" , BankTransaction.class).list();
        }
        catch (Exception e) {
            System.out.println("Error while fetching transactions" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<BankTransaction> findTransactionByAccountNumber(int accountNumber) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM BankTransaction bt WHERE  bt.account.accountNumber = :accountNumber"
                    , BankTransaction.class).setParameter("accountNumber", accountNumber).list();
        }
        catch (Exception e) {
            System.out.println("Error while fetching transactions" + e.getMessage());
//            List<BankTransaction> bankTransactions = new java.util.ArrayList<>();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean processAccountTransaction(int accountNumber, BigDecimal amount, TransactionType transactionType) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Account account = session.get(Account.class, accountNumber);

            if(account == null) {
                System.out.println("Account number " + accountNumber + " not found");
                transaction.rollback();
                return false;
            }
            if(transactionType == TransactionType.WITHDRAW && account.getBalance().compareTo(amount) < 0) {
                System.out.println("Insufficient Balance");
                System.out.println("Available balance: " + account.getBalance());
                transaction.rollback();
                return false;
            }

            BigDecimal updateBalance;
            if(transactionType == TransactionType.DEPOSIT) {
                updateBalance = account.getBalance().subtract(amount);
            }
            else if(transactionType == TransactionType.WITHDRAW) {
                updateBalance = account.getBalance().add(amount);
            }
            else {
                System.out.println("Invalid transaction type");
                transaction.rollback();
                return false;
            }
            account.setBalance(updateBalance);
            BankTransaction bankTransaction = new BankTransaction(
                    transactionType ,
                    amount,
                    LocalDateTime.now() ,
                    account
            );
            session.merge(account);
            session.persist(bankTransaction);
            transaction.commit();

            System.out.println(transactionType + " successful");
            System.out.println("Updated Balance :"+ updateBalance);

            return true;
        }
        catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            System.out.println("Transaction Failed " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean processTransfer(int fromAccountNumber, int toAccountNumber, BigDecimal amount) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Account fromAccount = session.get(Account.class, fromAccountNumber);
            Account toAccount = session.get(Account.class, toAccountNumber);

            if (fromAccount == null) {
                System.out.println("Sender account not found.");
                transaction.rollback();
                return false;
            }

            if (toAccount == null) {
                System.out.println("Receiver account not found.");
                transaction.rollback();
                return false;
            }

            if (fromAccountNumber == toAccountNumber) {
                System.out.println("Cannot transfer to the same account.");
                transaction.rollback();
                return false;
            }

            if (fromAccount.getBalance().compareTo(amount) < 0) {
                System.out.println("Insufficient balance.");
                System.out.println("Available balance: " + fromAccount.getBalance());
                transaction.rollback();
                return false;
            }

            BigDecimal senderUpdatedBalance = fromAccount.getBalance().subtract(amount);
            BigDecimal receiverUpdatedBalance = toAccount.getBalance().add(amount);

            fromAccount.setBalance(senderUpdatedBalance);
            toAccount.setBalance(receiverUpdatedBalance);

            BankTransaction debitTransaction = new BankTransaction(
                    TransactionType.WITHDRAW,
                    amount,
                    LocalDateTime.now(),
                    fromAccount
            );

            BankTransaction creditTransaction = new BankTransaction(
                    TransactionType.DEPOSIT,
                    amount,
                    LocalDateTime.now(),
                    toAccount
            );

            session.merge(fromAccount);
            session.merge(toAccount);

            session.persist(debitTransaction);
            session.persist(creditTransaction);

            transaction.commit();

            System.out.println("Transfer successful.");
            System.out.println("Sender updated balance: " + senderUpdatedBalance);
            System.out.println("Receiver updated balance: " + receiverUpdatedBalance);

            return true;

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println("Transfer failed: " + e.getMessage());
            return false;
        }
    }
}
