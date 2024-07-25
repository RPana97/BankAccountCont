package org.example;

import java.util.Scanner;

public class BankAccount {
    private String name;
    private int accountBalance;
    private int accountNumber;
    private static int accountNumberCounter = 1;

    // Constructor with account number
    public BankAccount(String accountHolder, int initialBalance) {
        this.name = accountHolder;
        this.accountBalance = initialBalance;
        this.accountNumber = accountNumberCounter++;
    }

    // No-parameter constructor
    public BankAccount() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter account holder's name: ");
        this.name = input.nextLine();
        System.out.print("Enter starting balance: ");
        this.accountBalance = input.nextInt();
        this.accountNumber = accountNumberCounter++;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for accountNumber
    public int getAccountNumber() {
        return accountNumber;
    }

    // Deposit method for double amount
    public void deposit(double deposit) {
        accountBalance += deposit;
    }

    // Withdrawal method for double amount
    public void withdrawal(double withdrawal) {
        if (withdrawal > accountBalance) {
            System.out.println("Insufficient funds.");
        } else {
            accountBalance -= withdrawal;
        }
    }

    // Method to get account information
    public void getAccountInfo() {
        System.out.println("Account number: " + accountNumber + ", Name: " + name + ", Balance: $" + accountBalance);
    }

    // Transfer method
    public void transfer(BankAccount targetAccount, double amount) {
        if (amount > accountBalance) {
            System.out.println("Insufficient funds.");
        } else {
            this.withdrawal(amount);
            targetAccount.deposit(amount);
            System.out.println("Transfer successful.");
        }
    }
}
