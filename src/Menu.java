import java.util.Scanner;
import java.util.ArrayList;


public class Menu {

    static Scanner scanner = new Scanner(System.in);

    public static void mainMenu() {
        System.out.println("\n\n\n\n\n\n\n");
        System.out.println("1. Choose Account");
        System.out.println("2. Create Account");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
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
        for (int i = 0; i < Account.globalAccountList.size(); i++) {
            Account account = Account.globalAccountList.get(i);
            System.out.println("Account " + i + ": " + account.getAccountType());
        }
        System.out.println("Enter your account Index or enter 'back' to return to menu: ");
        String input = scanner.nextLine();
        System.out.println("\n\n\n\n\n\n\n");
        if (input.equalsIgnoreCase("back")) {
            mainMenu();
        }
        else if (!input.equalsIgnoreCase("back")) {
            try {
                int choice = Integer.parseInt(input);
                if (choice > Account.globalAccountList.size() - 1 || choice < 0) {
                    System.out.println("Invalid choice");
                    chooseAccount();
                }
                else {
                for (int i = 0; i < Account.globalAccountList.size(); i++) {
                    if (choice == i) {
                        displayAccount(Account.globalAccountList.get(i));
                    }
                }
              }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid number format!");
                chooseAccount();
            }
        }
    }


    public static void createAccount() {
        System.out.println("Enter Account Type or say 'back' to return to menu:");
        String accountType = scanner.nextLine();
        double balance = 0;
        if (accountType.equalsIgnoreCase("back")){
            mainMenu();
        }
        else {
            Account newAccount = new Account(accountType, balance);
            Account.globalAccountList.add(newAccount);
            mainMenu();
        }
    }


    public static void displayAccount(Account account) {
        account.getAccountType();
        account.getAccountNumber();
        account.getBalance();
        System.out.println("Account Type: " + account.getAccountType());
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Account Balance: " + account.getBalance());
        userPrompt(account);
    }


    public static void userPrompt(Account account) {
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Back");
        System.out.print("Select Option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.print("\n\n\n\n\n\n\n");
        if (choice == 1){
            System.out.println("Enter Deposit Amount:");
            double amountDeposited = scanner.nextInt();
            scanner.nextLine();
            account.Deposit(amountDeposited);
            Menu.mainMenu();
        }
        else if (choice == 2){
            System.out.println("Enter Withdraw Amount:");
            double amountWithdrawn = scanner.nextInt();
            scanner.nextLine();
            account.Withdraw(amountWithdrawn);
            Menu.mainMenu();
        }
        else if (choice == 3){
            chooseAccount();
        }
    }
















}
