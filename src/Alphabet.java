public class Alphabet {
    public static final Alphabet ALPHABET = new Alphabet();

    public static Alphabet getInstance() {
        return ALPHABET;
    }

    private Alphabet() {
    }

    private final static String ENGLISH_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static String ENGLISH_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private final static String UKRAINIAN_UPPERCASE = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ";
    private final static String UKRAINIAN_LOWERCASE = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
    private final static char[] ENGLISH_ALPHABET = (ENGLISH_UPPERCASE + ENGLISH_LOWERCASE).toCharArray();
    private final static char[] UKRAINIAN_ALPHABET = (UKRAINIAN_UPPERCASE + UKRAINIAN_LOWERCASE).toCharArray();
    private final static char[] MARKS = new char[]{'.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};


    public char[] getMARKS() {
        return MARKS;
    }


    public char[] getUkrainianAlphabet() {
        return UKRAINIAN_ALPHABET;
    }

    public char[] getEnglishAlphabet() {
        return ENGLISH_ALPHABET;
    }

}