import java.util.ArrayList;
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

    // Main menu method
    public static void mainMenu(BankAccount account, ArrayList<BankAccount> accounts) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\nHello, " + account.name + "! Please choose an option:");
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
                        if (acc.accountNumber == targetAccountNumber) {
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
                    System.out.println("Goodbye, " + account.name + "!");
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
                System.out.println((i + 1) + ". Account number: " + accounts.get(i).accountNumber + ", Name: " + accounts.get(i).name);
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
