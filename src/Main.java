//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the

void main() {
    System.out.println(System.getProperty("user.dir"));
    File accountNumberFile = new File("data/AccountNumbers.txt");
    if (!accountNumberFile.exists()) {
        FileManager.AccountNumberFileReset();
    }
    Menu.initialize();
}