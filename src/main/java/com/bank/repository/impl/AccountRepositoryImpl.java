package com.bank.repository.impl;

import com.bank.config.HibernateUtil;
import com.bank.entity.Account;
import com.bank.repository.interfaces.AccountRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {

    @Override
    public boolean saveAccount(Account account) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            session.persist(account);
            transaction.commit();
            return true;
        }
        catch(Exception e){

            if(transaction != null){
                transaction.rollback();
            }
            System.out.println("Error While Saving Account" + e.getMessage());
            return false;
        }
    }

    @Override
    public Account findAccountByNumber(int accountNumber) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(Account.class , accountNumber);
        }
        catch(Exception e){
            System.out.println("Error While Finding Account by Account Number" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Account> findAllAccounts() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Account" , Account.class).list();
        }
        catch(Exception e){
            System.out.println("Error While Finding All Accounts" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Account> findAllAccountsByUserId(int userId) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery(
                    "FROM Account a WHERE a.user.userId = :userId",Account.class
            ).setParameter("userId",userId).list();
        }
        catch(Exception e){
            System.out.println("Error While Finding All Accounts by Account Id" + e.getMessage());
            return null;
        }
    }
}
