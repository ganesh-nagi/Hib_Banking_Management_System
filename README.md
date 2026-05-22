
MySQL Database
Layer	Responsibility
Controller	Handles console menu, user input, and output
Service	Handles validation and business decisions
Repository	Handles Hibernate database operations
Entity	Maps Java classes to database tables
Config	Manages Hibernate configuration and SessionFactory
Folder Structure
src/main/java/com/bank
│
├── config
│   └── HibernateUtil.java
│
├── controller
│   ├── MainController.java
│   ├── UserController.java
│   ├── AccountController.java
│   └── BankTransactionController.java
│
├── entity
│   ├── User.java
│   ├── Account.java
│   ├── AccountType.java
│   ├── BankTransaction.java
│   └── TransactionType.java
│
├── repository
│   ├── interfaces
│   │   ├── UserRepository.java
│   │   ├── AccountRepository.java
│   │   └── BankTransactionRepository.java
│   │
│   └── impl
│       ├── UserRepositoryImpl.java
│       ├── AccountRepositoryImpl.java
│       └── BankTransactionRepositoryImpl.java
│
├── service
│   ├── interfaces
│   │   ├── UserService.java
│   │   ├── AccountService.java
│   │   └── BankTransactionService.java
│   │
│   └── impl
│       ├── UserServiceImpl.java
│       ├── AccountServiceImpl.java
│       └── BankTransactionServiceImpl.java
│
└── main
    └── BankApplication.java
Database Tables

The project uses three main tables:

users
accounts
bank_transactions
Entity Relationships
User to Account

One user can have many accounts.

User 1 ─────── many Accounts

Hibernate mapping:

@OneToMany(mappedBy = "user")
private List<Account> accounts;
@ManyToOne
@JoinColumn(name = "user_id")
private User user;

Database relationship:

users.user_id → accounts.user_id
Account to BankTransaction

One account can have many bank transactions.

Account 1 ─────── many BankTransactions

Hibernate mapping:

@OneToMany(mappedBy = "account")
private List<BankTransaction> transactions;
@ManyToOne
@JoinColumn(name = "account_number")
private Account account;

Database relationship:

accounts.account_number → bank_transactions.account_number
Database Schema Overview
users
Column	Description
user_id	Primary key
name	User name
email	Unique user email
phoneNumber	User phone number
accounts
Column	Description
account_number	Primary key
account_type	SAVINGS or CURRENT
balance	Account balance
user_id	Foreign key referencing users
bank_transactions
Column	Description
transaction_id	Primary key
transaction_type	DEPOSIT or WITHDRAW
amount	Transaction amount
transaction_time	Date and time of transaction
account_number	Foreign key referencing accounts
Atomic Banking Operations

Deposit, withdrawal, and transfer are handled using Hibernate transactions.

Deposit Flow
Find account
      ↓
Increase balance
      ↓
Save DEPOSIT transaction
      ↓
Commit transaction
Withdraw Flow
Find account
      ↓
Check sufficient balance
      ↓
Decrease balance
      ↓
Save WITHDRAW transaction
      ↓
Commit transaction
Transfer Flow
Find sender account
      ↓
Find receiver account
      ↓
Check sender balance
      ↓
Debit sender account
      ↓
Credit receiver account
      ↓
Save WITHDRAW record for sender
      ↓
Save DEPOSIT record for receiver
      ↓
Commit transaction

If any step fails, the transaction is rolled back.

Console Menus
Main Menu
===== BANKING SYSTEM =====
1. User Management
2. Account Management
3. Transaction Management
4. Exit
User Menu
1. Create User
2. Find User By ID
3. View All Users
4. Back To Main Menu
Account Menu
1. Create Account
2. Find Account By Number
3. View All Accounts
4. View Accounts By User ID
5. Back To Main Menu
Transaction Menu
1. Deposit Money
2. Withdraw Money
3. Transfer Money
4. View All Transactions
5. View Transactions By Account Number
6. Back To Main Menu
Screenshots
Main Menu

User Management

Account Management

Transaction Management

Transfer Money

Hibernate Configuration

The project uses hibernate.cfg.xml to configure Hibernate.

Main responsibilities:

Database connection
MySQL driver configuration
Hibernate dialect
SQL logging
Entity mappings
Automatic table update

Example:

<property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/hibernate_bank</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">YOUR_PASSWORD</property>

<property name="hibernate.show_sql">true</property>
<property name="hibernate.format_sql">true</property>
<property name="hibernate.hbm2ddl.auto">update</property>

Do not push your real database password to GitHub. Replace it with YOUR_PASSWORD.

How to Run the Project
Prerequisites

Make sure these are installed:

Java 17 or above
Maven
MySQL
IntelliJ IDEA or any Java IDE
Step 1: Clone the Repository
git clone https://github.com/ganesh-nagi/Hib_Banking_Management_System.git
Step 2: Open Project

Open the project in IntelliJ IDEA or any Java IDE.

Step 3: Create Database

Run this in MySQL:

CREATE DATABASE hibernate_bank;
Step 4: Update Database Password

Open:

src/main/resources/hibernate.cfg.xml

Update:

<property name="hibernate.connection.password">YOUR_PASSWORD</property>

Use your local MySQL password.

Step 5: Run Application

Run:

BankApplication.java

Hibernate will create or update the required tables automatically.

Git Workflow Used

This project was developed phase by phase using Git branches.

Branch	Purpose
main	Stable code
phase-2-account-module	Account module
phase-3-transaction-module	Deposit and withdraw
phase-4-transfer-module	Transfer money

Typical workflow:

git checkout -b feature-branch
git add .
git commit -m "meaningful commit message"
git push -u origin feature-branch

Changes are merged into main using Pull Requests.

Important Concepts Learned
Hibernate maps Java objects to database tables.
HQL uses Java class and field names, not SQL table names.
SessionFactory should be created only once.
persist() is used for saving new objects.
merge() is used for updating detached objects.
Banking operations must be atomic.
Deposit, withdrawal, and transfer should use database transactions.
Git branches help protect stable code.
Clean architecture improves maintainability.
Future Improvements
Add login system
Add password hashing
Add custom exceptions
Improve input validation
Add transfer ID grouping
Add account status such as ACTIVE or CLOSED
Add admin module
Add transaction filtering by date
Build Spring Boot REST API version
Add Spring Data JPA version
Add unit tests
Add Docker support
Add proper logging using SLF4J and Logback
Add Flyway or Liquibase for database migrations
Author

Nagendra Ganesh

GitHub: ganesh-nagi

Project Type

Backend learning project focused on:

Java layered architecture
Hibernate ORM
Database relationships
Transaction management
Git and GitHub workflow
