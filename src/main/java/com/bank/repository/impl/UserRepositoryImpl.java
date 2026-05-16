package com.bank.repository.impl;

import com.bank.config.HibernateUtil;
import com.bank.entity.User;
import com.bank.repository.interfaces.UserRepository;
import jakarta.transaction.SystemException;
//import jakarta.transaction.Transaction;
import org.hibernate.Transaction;
import org.hibernate.Session;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public boolean saveUser(User user) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            transaction = (Transaction) session.beginTransaction();
            session.persist(user);
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println("Error while saving user" +e.getMessage());
        }
        return true;
    }

    @Override
    public User findUserById(int userId) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.find(User.class, userId);
        }
        catch (Exception e){
            System.out.println("Error While Finding User :" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<User> findAllUsers() {

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from User").list();
        }
        catch (Exception e){
            System.out.println("Error while fetching users :" + e.getMessage());
            //used ArrayList instead of List.of()
            List<User> users = new java.util.ArrayList<>();
            return users;
        }
    }


}
