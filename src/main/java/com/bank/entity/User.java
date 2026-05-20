package com.bank.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column( nullable = false , length = 100 )
    private String name;

    @Column( nullable = false , unique = true , length = 100 )
    private String email;

    @Column( nullable = false , length = 10 )
    private String phoneNumber;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts = new ArrayList<>();


    public User(){

    }

    public User(int userId, String name, String email, String phoneNumber) {
        this.userId= userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getUserID() {
        return userId;
    }

    public void setUserID(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
