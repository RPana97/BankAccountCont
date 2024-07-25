package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Main menu method
    public static void mainMenu(BankAccount account, ArrayList<BankAccount> accounts) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\nHello, " + account.getName() + "! Please choose an option:");
            System.out.println("1. Check balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    account.getAccountInfo();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = input.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = input.nextDouble();
                    account.withdrawal(withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter the account number to transfer to: ");
                    int targetAccountNumber = input.nextInt();
                    BankAccount targetAccount = null;
                    for (BankAccount acc : accounts) {
                        if (acc.getAccountNumber() == targetAccountNumber) {
                            targetAccount = acc;
                            break;
                        }
                    }
                    if (targetAccount != null) {
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = input.nextDouble();
                        account.transfer(targetAccount, transferAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                    System.out.println("Goodbye, " + account.getName() + "!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<BankAccount> accounts = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        BankAccount account1 = new BankAccount("John Doe", 5000);
        BankAccount account2 = new BankAccount("Jane Smith", 300);

        accounts.add(account1);
        accounts.add(account2);

        // Create an account using the no-parameter constructor
        System.out.println("Create a new account:");
        BankAccount account3 = new BankAccount();
        accounts.add(account3);

        // Select an account to interact with
        while (true) {
            System.out.println("\nSelect an account to interact with:");
            for (int i = 0; i < accounts.size(); i++) {
                System.out.println((i + 1) + ". Account number: " + accounts.get(i).getAccountNumber() + ", Name: " + accounts.get(i).getName());
            }
            System.out.println((accounts.size() + 1) + ". Exit");
            int accountChoice = input.nextInt();

            if (accountChoice == accounts.size() + 1) {
                break;
            } else if (accountChoice > 0 && accountChoice <= accounts.size()) {
                BankAccount selectedAccount = accounts.get(accountChoice - 1);
                mainMenu(selectedAccount, accounts);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
