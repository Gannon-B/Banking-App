import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Menu {

    static String username;
    static String password;
    static Scanner scanner = new Scanner(System.in);


    public static void initialize(){
        System.out.print("\n\n\nWelcome to the user banking program\n\n" + "Would you like to:\n 1: login: (Existing Users)\n 2: create a new account?: (New Users)\n\n");
        String userChoice = scanner.nextLine();
        if (userChoice.equals("1")) {
            userLogin();
        }
        else if (userChoice.equals("2")) {
            createLogin();
        }
        else {
            System.out.println("Invalid input. Please try again.");
            initialize();
        }
    }

    public static void userLogin() {
        System.out.println("Please enter your username, or type 'back' to create a new account: ");
        username = scanner.nextLine().trim();

        if (username.equalsIgnoreCase("back")) {
            initialize();
            return;
        }

        if (username.trim().isEmpty()) {
            System.out.println("Invalid Username");
            userLogin();
            return;
        }

        System.out.println("Please enter your password, or type 'back' to create a new account: ");
        password = scanner.nextLine().trim();

        if (password.equalsIgnoreCase("back")) {
            initialize();
            return;
        }

        if (password.trim().isEmpty()) {
            System.out.println("Invalid password");
            userLogin();
            return;
        }


        File userDir = new File("Users"); // make sure this folder exists
        if (!userDir.exists()) {
            userDir.mkdir(); // create the folder if it doesn't exist
        }

        String filePassword = "";

        try {
            filePassword = new String(Files.readAllBytes(Paths.get("passwords/" + username + ".txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        File userFile = new File(userDir, username + ".csv");
        if (userFile.exists() && password.equalsIgnoreCase(filePassword)) {
            System.out.println("Welcome back, " + username + "! Loading your accounts...");
            // Clear globalAccountList first to avoid duplicates
            Account.globalAccountList.clear();
            FileManager.fileReader(username);
            System.out.println("Loaded " + Account.globalAccountList.size() + " account(s).");
            mainMenu();
        }
        else if (userFile.exists() && !password.equalsIgnoreCase(filePassword)) {
            System.out.println("Invalid Password");
            userLogin();
        }
        else {
            System.out.println("No user found with username '" + username + "'.");
            System.out.println("You can create a new account for this user.");
            initialize();
        }
    }

    public static void createLogin() {
        System.out.println("Please enter your desired username, or type 'back' to login: ");
        String desiredUsername = scanner.nextLine().trim();
        System.out.println("Please enter your desired password, or type 'back' to login: ");
        String desiredPassword = scanner.nextLine().trim();
        if (desiredUsername.equalsIgnoreCase("back")) {
            initialize();
            return;
        }

        if (desiredUsername.trim().isEmpty()) {
            System.out.println("Invalid Username");
            createLogin();
            return;
        }

        if (desiredPassword.equalsIgnoreCase("back")) {
            initialize();
            return;
        }

        if (desiredPassword.trim().isEmpty()) {
            System.out.println("Invalid Password");
            createLogin();
            return;
        }

        File userDir = new File("Users");
        File desiredFile = new File(userDir, desiredUsername + ".csv");
        if (desiredFile.exists()) {
            System.out.println("Username already exists, choose a different name.");
            createLogin();
        }
        else {
            System.out.println("Wonderful Choice");
            FileManager.fileCreator(desiredUsername);
            FileManager.passwordFileCreator(desiredUsername, desiredPassword);
            initialize();
        }
    }

    public static void mainMenu() {
        System.out.println("\n\n\n\n\n\n\n");
        System.out.println("1. Choose Account");
        System.out.println("2. Create Account");
        System.out.println("3. Remove Account");
        System.out.println("4. Logout");
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
        else if (choice == 3) {
            System.out.println("\n\n\n\n\n\n\n");
            deleteAccount();
        }
        else if (choice == 4) {
            System.out.println("\n\n\n\n\n\n\n");
            initialize();
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

        if (accountType.equalsIgnoreCase("back")) {
            mainMenu();
            return;
        }

        if (accountType.trim().isEmpty()) {
            System.out.println("Invalid Account Type!");
            createAccount();
            return;
        }

        boolean exists = false;

        // Check if account type already exists
        for (int i = 0; i < Account.globalAccountList.size(); i++) {
            if (accountType.equalsIgnoreCase(Account.globalAccountList.get(i).getAccountType())) {
                exists = true;
                break;
            }
        }

        if (exists) {
            System.out.println("Account already exists!");
            createAccount();
        } else {
            String accountNumber = Integer.toString(FileManager.AccountNumbersFile());
            Account newAccount = new Account(accountType, balance, accountNumber);
            Account.globalAccountList.add(newAccount);
            System.out.println("Account created successfully!");
            FileManager.fileUpdater(username);
            mainMenu(); // return to main menu after successful creation
        }
    }


    public static void deleteAccount() {
        for (int i = 0; i < Account.globalAccountList.size(); i++) {
            Account account = Account.globalAccountList.get(i);
            System.out.println("Account " + i + ": " + account.getAccountType());
        }
        System.out.println("Select account to delete or press back to return to menu: ");
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
                    deleteAccount();
                }
                else {
                    for (int i = 0; i < Account.globalAccountList.size(); i++) {
                        if (choice == i) {
                            Account.globalAccountList.remove(i);
                            FileManager.fileUpdater(username);
                            mainMenu();
                        }
                    }
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid number format!");
                deleteAccount();
            }
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
        System.out.println("3. Transfer");
        System.out.println("4. Back");
        System.out.print("Select Option: ");

        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.print("\n\n\n\n\n\n\n");

            if (choice == 1) {
                deposit(account);
            }
            else if (choice == 2) {
                withdraw(account);
            }
            else if (choice == 3) {
                transfer(account);
            }else if (choice == 4) {
                chooseAccount();
            } else {
                System.out.println("Invalid choice!");
                displayAccount(account);
            }
        }
        else {
            scanner.nextLine();
            System.out.println("Invalid choice!");
            displayAccount(account);
        }
    }

    public static void deposit(Account account) {

        System.out.println("Enter Deposit Amount, press 0 to go back: ");
        if (scanner.hasNextInt()) {
            double amountDeposited = scanner.nextInt();
            if (amountDeposited > 0) {
                scanner.nextLine();
                account.Deposit(amountDeposited);
                FileManager.fileUpdater(username);
                Menu.mainMenu();
            }
            else if (amountDeposited == 0) {
                displayAccount(account);
            }
            else {
                System.out.println("Invalid amount!");
                deposit(account);
            }
        }
        else {
            scanner.nextLine();
            System.out.println("Invalid amount!");
            deposit(account);
        }


    }

    public static void withdraw(Account account) {

        System.out.println("Enter Withdraw Amount, press 0 to go back: ");
        if (scanner.hasNextInt()) {
            double amountWithdrawn = scanner.nextInt();
            if (amountWithdrawn > 0 && amountWithdrawn <= account.getBalance()) {
                scanner.nextLine();
                account.Withdraw(amountWithdrawn);
                FileManager.fileUpdater(username);
                Menu.mainMenu();
            }
            else if (amountWithdrawn == 0) {
                displayAccount(account);
            }
            else {
                System.out.println("Invalid amount!");
                withdraw(account);
            }

        }
        else {
            scanner.nextLine();
            System.out.println("Invalid amount!");
            withdraw(account);
        }


    }

    public static void transfer(Account account) {

        if (Account.globalAccountList.size() > 1) {
            System.out.println("Enter Transfer Amount, press 0 to go back: ");
            if (scanner.hasNextInt()) {
                double amountToTransfer = scanner.nextInt();
                if (amountToTransfer > 0 && amountToTransfer <= account.getBalance()) {
                    scanner.nextLine();
                    Account depositAccount = null;

                    for (int i = 0; i < Account.globalAccountList.size(); i++) {
                        Account tempAccount = Account.globalAccountList.get(i);
                        if (!tempAccount.getAccountType().equals(account.getAccountType())) {
                            System.out.println("Account " + i + ": " + tempAccount.getAccountType());
                        }
                    }
                    System.out.println("Enter your account Index or enter 'back' to return to account: ");
                    String input = scanner.nextLine();
                    System.out.println("\n\n\n\n\n\n\n");
                    if (input.equalsIgnoreCase("back")) {
                        displayAccount(account);
                        userPrompt(account);
                    } else if (!input.equalsIgnoreCase("back")) {
                        try {
                            int choice = Integer.parseInt(input);
                            if (choice > Account.globalAccountList.size() - 1 || choice < 0) {
                                System.out.println("Invalid choice");
                                transfer(account);
                            } else {
                                account.Withdraw(amountToTransfer);
                                depositAccount = Account.globalAccountList.get(choice);
                                depositAccount.Deposit(amountToTransfer);
                                FileManager.fileUpdater(username);
                                mainMenu();
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number format!");
                            withdraw(account);
                        }
                    }


                } else if (amountToTransfer == 0) {
                    displayAccount(account);
                } else {
                    System.out.println("Invalid amount!");
                    transfer(account);
                }

            } else {
                scanner.nextLine();
                System.out.println("Invalid amount!");
                transfer(account);
            }
        }
        else {
            System.out.println("You only have one account");
            displayAccount(account);
            userPrompt(account);
        }
    }










}
