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
        String initID = String.valueOf(nextId++);
        StringBuilder id = new StringBuilder(String.valueOf(initID));

        for  (int i = 0; i < 10-initID.length(); i++) {
            id.insert(0, "0");

        }

        this.accountNumber = id.toString();
    }

    public Account(String accountType, double balance, String accountNumber) {
        this.accountType = accountType;
        this.balance = balance;
        this.accountNumber = accountNumber;
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