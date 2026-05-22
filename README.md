# Hibernate Banking Management System

A console-based Banking Management System built using **Java, Hibernate ORM, Maven, and MySQL**.

This project is a layered backend application that demonstrates how real banking operations such as user creation, account management, deposits, withdrawals, and money transfers can be handled using Hibernate with proper entity relationships and transaction management.

---

## Project Status

Completed modules:

- User Management
- Account Management
- Deposit and Withdraw Transactions
- Atomic Money Transfer
- Transaction History
- Hibernate Entity Relationships
- Git + GitHub Branch Workflow

---

## Tech Stack

| Technology | Purpose |

| **Java** | Core programming language |
| **Hibernate ORM** | Object-Relational Mapping (ORM) framework |
| **MySQL** | Relational database management system |
| **Maven** | Dependency management and project build tool |
| **JDBC Driver** | MySQL database connectivity link |
| **IntelliJ IDEA** | Integrated Development Environment (IDE) |
| **Git & GitHub** | Distributed version control and code hosting |


---

## Main Features

### User Management

- Create user
- Find user by ID
- View all users

### Account Management

- Create account for an existing user
- Find account by account number
- View all accounts
- View accounts by user ID

### Transaction Management

- Deposit money
- Withdraw money
- Transfer money between accounts
- View all transactions
- View transactions by account number

---

## Project Architecture

The project follows a layered architecture:

Controller Layer
      ↓
Service Layer
      ↓
Repository Layer
      ↓
Hibernate ORM
      ↓
MySQL Database



| Layer | Responsibility |

| **Controller** | Handles menu, user input, and output |
| **Service** | Handles validation and business decisions |
| **Repository** | Handles Hibernate database operations |
| **Entity** | Represents database tables as Java classes |
| **Config** | Manages Hibernate configuration and SessionFactory |


## Folder Structure

# src/main/java/com/bank
# │
# ├── config
# │   └── HibernateUtil.java
# │
# ├── controller
# │   ├── MainController.java
# │   ├── UserController.java
# │   ├── AccountController.java
# │   └── BankTransactionController.java
# │
# ├── entity
# │   ├── User.java
# │   ├── Account.java
# │   ├── AccountType.java
# │   ├── BankTransaction.java
# │   └── TransactionType.java
# │
# ├── repository
# │   ├── interfaces
# │   │   ├── UserRepository.java
# │   │   ├── AccountRepository.java
# │   │   └── BankTransactionRepository.java
# │   │
# │   └── impl
# │       ├── UserRepositoryImpl.java
# │       ├── AccountRepositoryImpl.java
# │       └── BankTransactionRepositoryImpl.java
# │
# ├── service
# │   ├── interfaces
# │   │   ├── UserService.java
# │   │   ├── AccountService.java
# │   │   └── BankTransactionService.java
# │   │
# │   └── impl
# │       ├── UserServiceImpl.java
# │       ├── AccountServiceImpl.java
# │       └── BankTransactionServiceImpl.java
# │
# └── main
#     └── BankApplication.java



##Database Tables

users
accounts
bank_transactions


## Entity Relationships


## User to Account   User 1 ─────── many Accounts

# @OneToMany(mappedBy = "user")
# private List<Account> accounts;

# @ManyToOne
# @JoinColumn(name = "user_id")
# private User user;

# Database relationship:
# users.user_id → accounts.user_id


## Account to BankTransaction     Account 1 ─────── many BankTransactions

# @OneToMany(mappedBy = "account")
# private List<BankTransaction> transactions;

# @ManyToOne
# @JoinColumn(name = "account_number")
# private Account account;

## Database relationship:
#  accounts.account_number → bank_transactions.account_number


## Database Schema Overview

# users
# Column         	Description

# user_id	        Primary key
# name	          User name
# email	          Unique user email
# phoneNumber	    User phone number

------------------------------------
# accounts
# Column	             Description

# account_number	     Primary key
# account_type	       SAVINGS or CURRENT
# balance	             Account balance
# user_id	             Foreign key referencing users

------------------------------------
# bank_transactions
# Column              	Description

# transaction_id	      Primary key
# transaction_type	    DEPOSIT or WITHDRAW
# amount	              Transaction amount
# transaction_time	    Date and time of transaction
# account_number	      Foreign key referencing accounts

-------------------------------------

## Hibernate Configuration

# Database connection
# MySQL driver configuration
# Hibernate dialect
# SQL logging
# Entity mappings
# Automatic table update

-----------------------------------------------------------------------------------------------

# <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
# <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/hibernate_bank</property>
# <property name="hibernate.connection.username">root</property>
# <property name="hibernate.connection.password">YOUR_PASSWORD</property>

# <property name="hibernate.show_sql">true</property>
# <property name="hibernate.format_sql">true</property>
# <property name="hibernate.hbm2ddl.auto">update</property>

------------------------------------------------------------------------------------------------

## CONSOLE MENU
 
# Main Menu
----------------------------------------
# ===== BANKING SYSTEM =====
1. User Management
2. Account Management
3. Transaction Management
4. Exit
----------------------------------------

# User Menu 
----------------------------------------
1. Create User
2. Find User By ID
3. View All Users
4. Back To Main Menu
----------------------------------------

# Account Menu
----------------------------------------
1. Create Account
2. Find Account By Number
3. View All Accounts
4. View Accounts By User ID
5. Back To Main Menu
----------------------------------------

# Transaction Menu
----------------------------------------
1. Deposit Money
2. Withdraw Money
3. Transfer Money
4. View All Transactions
5. View Transactions By Account Number
6. Back To Main Menu
----------------------------------------

## ============ HOW TO RUN THE PROJECT =================

# Prerequisites
# Make sure these are installed:

# Java 17 or above
#   Maven
#   MySQL
#   IntelliJ IDEA or any Java IDE

# Step 1: Clone the Repository

 #  git clone https://github.com/ganesh-nagi/Hib_Banking_Management_System.git

# Step 2: Open Project

 #  Open the project in your IDE.

# Step 3: Create Database
#    CREATE DATABASE hibernate_bank;
# Step 4: Update Database Password
  Open : 
    src/main/resources/hibernate.cfg.xml
  Update:
    <property name="hibernate.connection.password">YOUR_PASSWORD</property>
 #  Use your local MySQL password.

# Step 5: Run Application
  BankApplication.java
# Hibernate will create/update tables automatically.


## ================ Maven Dependencies ==============

# Main dependencies used:

--------------------------------------------------------------------
#     <dependency>
#       <groupId>org.hibernate.orm</groupId>
#       <artifactId>hibernate-core</artifactId>
#       <version>6.5.2.Final</version>
#       <scope>compile</scope>
#     </dependency>
# 
#     <dependency>
#       <groupId>com.mysql</groupId>
#       <artifactId>mysql-connector-j</artifactId>
#       <version>9.6.0</version>
#       <scope>compile</scope>
#     </dependency>

#     <dependency>
#       <groupId>jakarta.persistence</groupId>
#       <artifactId>jakarta.persistence-api</artifactId>
#       <version>3.2.0-M1</version>
#       <scope>compile</scope>
#     </dependency>
---------------------------------------------------------------------


## Git Workflow Used

# This project was developed phase by phase using Git branches.

  # Branch	                          Purpose

  # main	                     -     Stable code
  # phase-2-account-module	   -     Account module
  # phase-3-transaction-module -    Deposit and withdraw
  # phase-4-transfer-module    -   	Transfer money

# Typical workflow:

  # git checkout -b feature-branch
  # git add .
  # git commit -m "meaningful commit message"
  # git push -u origin feature-branch

# changes are merged into main using Pull Requests.


## =============== Important Lessons Learned ===============

# ->  Hibernate maps Java objects to database tables.
# ->  HQL uses Java class and field names, not SQL table names.
# ->  SessionFactory should be created only once.
# ->  persist() is used for new objects.
# ->  merge() is used for updating detached objects.
# ->  Banking operations must be atomic.
# ->  Deposit, withdraw, and transfer should use transactions.
# ->  Git branches help protect stable code.
# ->  A good backend project needs clean architecture and clear responsibility separation.
 
## Future Improvements

  # Add login system
  # Add password hashing
  # Add custom exceptions
  # Add input validation improvements
  # Add transfer ID grouping
  # Add account status such as ACTIVE or CLOSED
  # Add admin module
  # Add transaction filtering by date
  # Add Spring Boot REST API version
  # Add Spring Data JPA version
  # Add unit tests
  # Add Docker support
  # Add proper logging using SLF4J/Logback
  # Add Flyway or Liquibase for database migrations


