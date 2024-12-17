import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BruteForce {
    private static final BruteForce BRUTE = new BruteForce();
    private final Command COMMAND = Command.BRUTE_FORCE;
    private final Alphabet ALPHABET = Alphabet.getInstance();
    private final CaesarCipher CAESAR = CaesarCipher.getCaesarCipher();
    private final CLI CLIENT = CLI.getInstance();
    private Path path;

    private BruteForce() {
    }

    public static BruteForce getInstance() {
        return BRUTE;
    }


    public void bruteForceFileAnalysis(Path encryptedFilePath) {
        this.path = encryptedFilePath;
        try {
            List<String> lines = Files.readAllLines(encryptedFilePath);
            Map<Integer, Integer> englishKeyMatchScores = new HashMap<>();
            Map<Integer, Integer> ukrainianKeyMatchScores = new HashMap<>();

            for (int englishKey = 1; englishKey <= ALPHABET.getEnglishAlphabet().length; englishKey++) {
                int score = 0;
                for (String line : lines) {
                    String decryptedText = CAESAR.decrypt(line.toCharArray(), englishKey);
                    score += calculateMatchScore(decryptedText);
                }
                englishKeyMatchScores.put(englishKey, score);
            }
            for (int ukrainianKey = 1; ukrainianKey <= ALPHABET.getUkrainianAlphabet().length; ukrainianKey++) {
                int score = 0;
                for (String line : lines) {
                    String decryptedText = CAESAR.decrypt(line.toCharArray(), ukrainianKey);
                    score += calculateMatchScore(decryptedText);
                }
                ukrainianKeyMatchScores.put(ukrainianKey, score);
            }

            displayTopKeys(englishKeyMatchScores, ukrainianKeyMatchScores);
        } catch (IOException e) {
            throw new RuntimeException("Брут_Форсе не працює");
        }

    }

    private int calculateMatchScore(String text) {
        String[] commonBigrams = {"th", "he", "in", "er", "an", "re", "on", "at", "en", "nd","на", "та", "во", "ре", "до", "по", "за", "ти", "ми", "мо"};
        String[] commonTrigrams = {"the", "and", "ing", "her", "hat", "his", "tha", "ere", "for", "ent","про", "при", "що", "бул", "так", "як", "але", "щоб", "аби"};
        int score = 0;
        char[] chars = text.toCharArray();

        for (int i = 0; i < chars.length - 1; i++) {
            String bigram = new String(new char[]{chars[i], chars[i + 1]});
            if (containsPattern(bigram, commonBigrams)) {
                score++;
            }

            if (i < chars.length - 2) {
                String trigram = new String(new char[] {chars[i], chars[i + 1], chars[i + 2]});
                if (containsPattern(trigram, commonTrigrams)) {
                    score++;
                }
            }
        }
        return score;
    }

    private boolean containsPattern(String textPattern, String[] patterns) {
        for (String pattern : patterns) {
            if (textPattern.equals(pattern)) return true;
        }
        return false;
    }

    private void displayTopKeys(Map<Integer, Integer> englishScores, Map<Integer, Integer> ukrainianScores) {
        System.out.println(COMMAND + " пропонує такі ключі");
        System.out.println("Найкращі ключі для англійського тексту");
        englishScores.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .limit(3)
                .forEach(entry -> System.out.println("Ключ: " + entry.getKey() + ", Оцінка: " + entry.getValue()));

        System.out.println("Найкращі ключі для українського тексту:");
        ukrainianScores.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .limit(3)
                .forEach(entry -> System.out.println("Ключ: " + entry.getKey() + ", Оцінка: " + entry.getValue()));
    chooseTheKey();
    }

    private void chooseTheKey() {
        int key = CLIENT.chooseYouDestiny();
        if (key == 0) return;
        Runner.getInstance().processArguments(Command.DECRYPT, path, key);
    }
}
