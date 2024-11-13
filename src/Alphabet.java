public class Alphabet {
    public static final Alphabet ALPHABET = new Alphabet();

    public static Alphabet getInstance() {
        return ALPHABET;
    }
    private Alphabet() {}

    private final String ENGLISH_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String ENGLISH_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private final String UKRAINIAN_UPPERCASE = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ";
    private final String UKRAINIAN_LOWERCASE = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
    private final char [] ENGLISH_ALPHABET = (ENGLISH_UPPERCASE + ENGLISH_LOWERCASE).toCharArray();
    private final char [] UKRAINIAN_ALPHABET = (UKRAINIAN_UPPERCASE + UKRAINIAN_LOWERCASE).toCharArray();
    private final char[] MARKS = new char[]{'.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};


    public char[] getMARKS() {
        return MARKS;
    }


    public char[] getUKRAINIAN_ALPHABET() {
        return UKRAINIAN_ALPHABET;
    }

    public char[] getENGLISH_ALPHABET() {
        return ENGLISH_ALPHABET;
    }

}