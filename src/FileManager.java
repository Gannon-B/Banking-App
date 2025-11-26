import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.nio.file.Files;


public class FileManager {



    public static void fileUpdater(String username) {
        File userFile = new File("users/" + username + ".csv");
        try (FileWriter writer = new FileWriter(userFile)) {
            writer.append("AccountType,Balance,AccountNumber\n" );
            for (int i = 0; i < Account.globalAccountList.size(); i++) {
                Account account = Account.globalAccountList.get(i);
                writer.append(account.getAccountType()+","+account.getBalance()+","+account.getAccountNumber()+ "\n");
                writer.flush();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void AccountNumberFileReset(){
        File accountNumberFile = new File("data/AccountNumbers.txt");
        int theAccountNumber = 0;


        try (FileWriter writer = new FileWriter(accountNumberFile)) {
            writer.write(Integer.toString(theAccountNumber));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int AccountNumbersFile(){
        File accountNumberFile = new File("data/AccountNumbers.txt");
        int theAccountNumber = 0;


        try  {
            String content = Files.readString(accountNumberFile.toPath()).trim();
            theAccountNumber = Integer.parseInt(content);
            System.out.println("content is " + content);
            System.out.println("Account number is " + theAccountNumber);
            int newNumber = theAccountNumber + 1;

            try (FileWriter writer = new FileWriter(accountNumberFile)) {
                writer.write(Integer.toString(newNumber));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return theAccountNumber;
    }



    public static void fileCreator(String username) {
        File userFile = new File("users/" + username + ".csv");
        if (!userFile.exists()) {
            try (FileWriter writer = new FileWriter(userFile)) {
                writer.append("AccountType,Balance,AccountNumber\n" );
                writer.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("File Already Exists");
        }
    }





    public static void fileReader(String username) {
        String file = "users/" + username + ".csv";
        String line;
        boolean firstRow = true; // skip header

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {

                if (firstRow) {
                    firstRow = false;
                    continue;
                }
                String[] values = line.split(",");
                for (String value : values) {
                    System.out.print(value + " | ");
                }

                Account newAccount = new Account(values[0], Double.parseDouble(values[1]), values[2]);
                Account.globalAccountList.add(newAccount);
                System.out.println();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
























}
