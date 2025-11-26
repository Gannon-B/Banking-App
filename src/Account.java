import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Account {

    private String AccountNumber;
    private String accountType;
    private double balance;
    public static ArrayList<Account> globalAccountList = new ArrayList<Account>();

    public Account(String accountType, double balance, String accountNumber) {
        this.accountType = accountType;
        this.balance = balance;
        this.AccountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAccountNumber() {
        return AccountNumber;
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