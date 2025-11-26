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

    /*public Account(String accountType, double balance) {
        this.accountType = accountType;
        this.balance = balance;
       // theAccountNumber = 0;
        File accountNumberFile = new File("data/AccountNumbers.txt");
        try {
            String content = Files.readString(accountNumberFile.toPath()).trim();
            //theAccountNumber = Integer.parseInt(content);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String initID = String.valueOf(theAccountNumber);
        StringBuilder id = new StringBuilder(String.valueOf(initID));

        for  (int i = 0; i < 10-initID.length(); i++) {
            id.insert(0, "0");

        }

        //this.theAccountNumber = id.toString();
    }
*/

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