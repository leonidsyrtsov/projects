import java.nio.file.Path;

public class Runner {

    private static final Runner RUNNER = new Runner();
    private final BruteForce BRUTE = BruteForce.getInstance();
    private final FileService FILE = FileService.getInstance();
    private final CLI CLIENT = CLI.getInstance();
    private Command command;
    private Path path;
    private int key;

    public static Runner getInstance() {
        return RUNNER;
    }

    private Runner() {
    }

    public static void run(String... args) {
        if (args == null || args.length == 0) {
            RUNNER.CLIENT.chooseCommandCLI();
        } else if (args.length == 2) {
            RUNNER.runBruteForce(args);
        } else if (args.length == 3) {
            RUNNER.runWithFullArgs(args);
        } else {
            System.out.println("Невірна кількість аргументів. Використовуйте 2 або 3 аргументи.");
        }
    }

    public void runWithFullArgs(String... args) {
        try {
            validatorArguments(args);
            processArguments(command, path, key);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new RuntimeException("Не валідний аргумент", e);
        }
    }


    public void runBruteForce(String... args) {
        try {
            validatorForBrute(args);
            processArgumentsBrute(path);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new RuntimeException("Не валідний аргумент", e);

        }
    }

    public void processArgumentsBrute(Path path) {
        BRUTE.bruteForceFileAnalysis(path);
    }

    private void validatorForBrute(String... args) {
        try {
            this.command = Command.valueOf(args[0].toUpperCase());
            this.path = Path.of(args[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Помилка: аргументів. Введіть команду, шлях до файлу і ключ.");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Помилка у веденні даних.");
        }
    }


    public void processArguments(Command command, Path path, int key) {
        FILE.processCommand(command, path, key);
    }

    private void validatorArguments(String... args) {
        try {
            this.command = Command.valueOf(args[0].toUpperCase());
            this.path = Path.of(args[1]);
            this.key = Integer.parseInt(args[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Помилка: недостатньо аргументів. Введіть команду, шлях до файлу і ключ.");
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Помилка: ключ повинен бути цілим числом.");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Помилка у веденні даних.");
        }
    }

}