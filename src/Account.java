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