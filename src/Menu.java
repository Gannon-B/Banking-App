import java.util.Scanner;
import java.util.ArrayList;


public class Menu {

    public static void mainMenu() {
        System.out.println("\n\n\n\n\n\n\n");
        System.out.println("1. Choose Account");
        System.out.println("2. Create Account");
        Scanner mainChoice = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        int choice = mainChoice.nextInt();
        if (choice == 1) {
            System.out.println("\n\n\n\n\n\n\n");
            chooseAccount();
        }
        else if (choice == 2) {
            System.out.println("\n\n\n\n\n\n\n");
            createAccount();
        }
    }



    public static void chooseAccount() {
        Scanner accountChoice = new Scanner(System.in);
        for (int i = 0; i < Account.globalAccountList.size(); i++) {
            Account account = Account.globalAccountList.get(i);
            System.out.println("Account " + i + ": " + account.getAccountType());
        }
        int choice = accountChoice.nextInt();
        System.out.println("\n\n\n\n\n\n\n");
        for (int i = 0; i < Account.globalAccountList.size(); i++) {
            if (choice == i) {
                displayAccount(Account.globalAccountList.get(i));
            }
        }


    }


    public static void createAccount() {
        Scanner accountScanner = new Scanner(System.in);
        System.out.println("Enter Account Type:");
        String accountType = accountScanner.nextLine();
        double balance = 0;
        Account newAccount  = new Account(accountType, balance);
        Account.globalAccountList.add(newAccount);
        mainMenu();
    }

    public static void displayAccount(Account account) {
        Scanner accountScanner = new Scanner(System.in);
        account.getAccountType();
        account.getAccountNumber();
        account.getBalance();
        System.out.println("Account Type: " + account.getAccountType());
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Account Balance: " + account.getBalance());
        userPrompt(account);
    }


    public static void userPrompt(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.print("Select Option: ");
        int choice = scanner.nextInt();
        System.out.print("\n\n\n\n\n\n\n");

        if (choice == 1){
            System.out.println("Enter Deposit Amount:");
            double amountDeposited = scanner.nextInt();
            account.Deposit(amountDeposited);
            Menu.mainMenu();
        }
        else if (choice == 2){
            System.out.println("Enter Withdraw Amount:");
            double amountWithdrawn = scanner.nextInt();
            account.Withdraw(amountWithdrawn);
            Menu.mainMenu();
        }
    }
















}
