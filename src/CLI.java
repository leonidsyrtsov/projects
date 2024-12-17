import java.util.Scanner;

public class CLI {
    private static final CLI INSTANCECLI = new CLI();
    private final Scanner SCANNER = new Scanner(System.in);
    private Runner runner;

    private CLI() {
    }

    public static CLI getInstance() {
        return INSTANCECLI;
    }

    public void chooseCommandCLI() {
        runner = Runner.getInstance();
        String CHOOSE = "Введіть [Команду]->[ E ][ D ][ B ]";
        System.out.println(CHOOSE);
        String temp = SCANNER.nextLine();
        if (temp.equalsIgnoreCase("E")) {
            runner.runWithFullArgs(runCLI("ENCRYPT"));
        } else if (temp.equalsIgnoreCase("D")) {
            runner.runWithFullArgs(runCLI("DECRYPT"));
        } else if (temp.equalsIgnoreCase("B")) {
            runner.runBruteForce(bruteCLI("BRUTE_FORCE"));
        }else {
            throw new IllegalArgumentException("Не валідна команда");
        }
    }

    public String[] runCLI(String command) {
        String GREETINGS = "Ви у консолі введіть [Файл][Ключ]";
        System.out.println(GREETINGS);
        String[] args = new String[3];
        args[0] = command;
        args[1] = SCANNER.nextLine();
        args[2] = SCANNER.nextLine();
        return args;
    }

    public String[] bruteCLI(String command) {
        String GREETINGS = "Введіть шлях до файлу для підбору ключа";
        System.out.println(GREETINGS);
        String[] args = new String[2];
        args[0] = command;
        args[1] = SCANNER.nextLine();
        return args;
    }

    public int chooseYouDestiny() {
        System.out.println("ENTER [Q] for exit or [key-value] for DECRYPT");
        String temp = SCANNER.nextLine();
        if (temp.equalsIgnoreCase("Q")) {
            return 0;
        }
        return Integer.parseInt(temp);
    }
}
