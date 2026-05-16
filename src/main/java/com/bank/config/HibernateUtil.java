package com.bank.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    public static SessionFactory sessionFactory;

    public HibernateUtil() {

    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            try {
                sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            }
            catch(Exception e ){
                System.out.println("Session Creation Failed" + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }

public static void shutdown(){

        if(sessionFactory!=null){

        sessionFactory.close();

        }
    }
}