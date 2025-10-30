
import java.util.Scanner;

public class ChooseAccountScreen {

    public static void chooseAccount(){
        Account savings = new  Account(100);
        Account checking  = new  Account(100);
        Account investments = new  Account(100);


        Scanner accountChoice = new Scanner(System.in);

        System.out.println("1. Savings");
        System.out.println("2. Checking");
        System.out.println("3. Investments");
        System.out.print("Choose Account #: ");
        int choice = accountChoice.nextInt();

        System.out.println("\n\n\n\n\n\n\n");

        if (choice == 1) {
            savings.DisplayBalance();
            savings.UserPrompt();
        }
        else if (choice == 2) {
        checking.DisplayBalance();
        checking.UserPrompt();
        }
        else if (choice == 3) {
            investments.DisplayBalance();
            investments.UserPrompt();
        }
        else{
            System.out.println("Invalid choice");
        }

    }




}
