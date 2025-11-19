import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;



public class FileManager {

    public static void fileUpdater() {

    }


    public static void fileCreator(String username) {
        File userFile = new File("users/" + username + ".csv");
        if (!userFile.exists()) {
            try (FileWriter writer = new FileWriter(userFile)) {
                writer.append("AccountType,Balance,AccountNumber\n" );
                writer.append("Savings,0,0000000001\n");
                writer.append("Invest,0,0000000002");
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
