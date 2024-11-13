import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileService {

    private static final FileService FILE_SERVICE = new FileService();
    private final CaesarCipher CAESAR_CIPHER = CaesarCipher.getCaesarCipher();


   private FileService(){}

    public static FileService getInstance() {
        return FILE_SERVICE;
    }

    public void processCommand(Command command, Path path, int key) {
        try {
            if (!Files.isRegularFile(path)) {
                throw new RuntimeException("Не є файлом");
            }

            List<String> textLines = Files.readAllLines(path);

            Path outputPath = createOutputPath(command, path);
            String crypt;
            for (String line : textLines) {
                if (command == Command.ENCRYPT) {
                    crypt = CAESAR_CIPHER.encrypt(line.toCharArray(), key);
                } else if (command == Command.DECRYPT) {
                    crypt = CAESAR_CIPHER.decrypt(line.toCharArray(), key);
                } else {
                    throw new RuntimeException("BRUTE_FORCE з ключем або ввели неправильну команду");
                }
                Files.writeString(outputPath, crypt + System.lineSeparator(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);

            }

            System.out.println("Операція " + command + " успішна, назва файла " + outputPath.toAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Помилка при обробці файла: " + e.getMessage());
        }
    }

    private Path createOutputPath(Command command, Path path) throws IOException {
        String originalFileName = path.getFileName().toString();
        String modifiedFileName;
        if (originalFileName.contains("[ENCRYPTED]")) {
            modifiedFileName = originalFileName.replace("[ENCRYPTED]", "[DECRYPTED]");
        } else {
            int extensionIndex = originalFileName.lastIndexOf(".");
            String baseName = (extensionIndex == -1) ? originalFileName : originalFileName.substring(0, extensionIndex);
            modifiedFileName = baseName + "[" + command + "ED" + "]" + (extensionIndex == -1 ? "" : originalFileName.substring(extensionIndex));
        }
        return path.getParent().resolve(modifiedFileName);
    }
}



