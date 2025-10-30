import java.util.Scanner;
import java.util.ArrayList;

public class Account {

    private static int nextId = 0;
    private final String accountNumber;


    private String accountType;
    private double balance;

    public static ArrayList<Account> globalAccountList = new ArrayList<Account>();

    public Account(String accountType, double balance) {
        this.accountType = accountType;
        this.balance = balance;
        StringBuilder id = new StringBuilder(String.valueOf(nextId++));
        String initID = String.valueOf(nextId++);
        for  (int i = 0; i < 10-initID.length(); i++) {
            id.insert(0, "0");

        }

        this.accountNumber = id.toString();
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }


    public void Deposit(double amountDeposited) {

       balance += amountDeposited;
    }


   public void Withdraw(double amountWithdrawn) {
        balance -= amountWithdrawn;

    }

}












/*

import java.util.Scanner;

public class Account {

    public double balance;
    public double balanceToTransferSavings;
    public double balanceToTransferChecking;
    public double balanceToTransferInvestment;

    Scanner scanner = new Scanner(System.in);


    public Account(double initialBalance){

        this.balance = initialBalance;

    }


    public void DisplayBalance() {
        System.out.println("Your balance: $" + balance);
    }

        public void UserPrompt(){
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.print("Select Option: ");
            int choice = scanner.nextInt();
            System.out.print("\n\n\n\n\n\n\n");

            if (choice == 1){
                Deposit();
            }
            else if (choice == 2){
                Withdraw();
            }
            else if (choice == 3){
                Transfer();
            }
        }



        public void Deposit() {

            System.out.print("Enter deposit amount: ");
            double amountDeposited = scanner.nextInt();


            balance += amountDeposited;
            DisplayBalance();
            ChooseAccountScreen.chooseAccount();

        }


        public void Withdraw() {
            System.out.println("Enter withdrawal amount: ");
            double amountWithdrawn = scanner.nextInt();
            balance -= amountWithdrawn;
            DisplayBalance();
            ChooseAccountScreen.chooseAccount();

        }


        public void Transfer() {
            System.out.println("Enter Transfer amount: ");
            double amountTransferred = scanner.nextInt();
            System.out.println("Select account to transfer to: ");
            System.out.println("1. Savings");
            System.out.println("2. Checking");
            System.out.println("3. Investments");
            int choice =  scanner.nextInt();
            balance -= amountTransferred;
            if (choice == 1) {
                balanceToTransferSavings += amountTransferred;
            }
            else if (choice == 2) {
                balanceToTransferChecking += amountTransferred;
            }
            else if (choice == 3) {
                balanceToTransferInvestment += amountTransferred;
            }
            else {
                System.out.println("Invalid choice!");
            }



            DisplayBalance();
            ChooseAccountScreen.chooseAccount();

        }



}
*/