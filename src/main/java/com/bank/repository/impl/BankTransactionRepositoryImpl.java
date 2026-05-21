package com.bank.repository.impl;

import com.bank.config.HibernateUtil;
import com.bank.entity.BankTransaction;
import com.bank.repository.interfaces.BankTransactionRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
}
