public class CaesarCipher {


    public static final CaesarCipher CAESAR_CIPHER = new CaesarCipher();
    private final Alphabet ALPHABET = Alphabet.getInstance();


    private CaesarCipher() {
    }

    public static CaesarCipher getCaesarCipher() {
        return CAESAR_CIPHER;
    }


    public String encrypt(char[] chars, int key) {
        for (int i = 0; i < chars.length; i++) {
            if (isCharInAlphabet(chars[i], ALPHABET.getEnglishAlphabet())) {
                chars[i] = shiftEncrypt(chars[i], ALPHABET.getEnglishAlphabet(), key);
            } else if (isCharInAlphabet(chars[i], ALPHABET.getUkrainianAlphabet())) {
                chars[i] = shiftEncrypt(chars[i], ALPHABET.getUkrainianAlphabet(), key);
            } else if (isCharInAlphabet(chars[i], ALPHABET.getMARKS())) {
                chars[i] = shiftEncrypt(chars[i], ALPHABET.getMARKS(), key);
            }
        }
        return new String(chars);
    }

    public String decrypt(char[] chars, int key) {
        for (int i = 0; i < chars.length; i++) {
            if (isCharInAlphabet(chars[i], ALPHABET.getEnglishAlphabet())) {
                chars[i] = shiftDecrypt(chars[i], ALPHABET.getEnglishAlphabet(), key);
            } else if (isCharInAlphabet(chars[i], ALPHABET.getUkrainianAlphabet())) {
                chars[i] = shiftDecrypt(chars[i], ALPHABET.getUkrainianAlphabet(), key);
            } else if (isCharInAlphabet(chars[i], ALPHABET.getMARKS())) {
                chars[i] = shiftDecrypt(chars[i], ALPHABET.getMARKS(), key);
            }
        }
        return new String(chars);
    }



    private boolean isCharInAlphabet(char readingChar, char[] alphabetArray) {
        return new String(alphabetArray).indexOf(readingChar) != -1;
    }

    private char shiftEncrypt(char charIsRead, char[] alphabetArray, int key) {
        int index = new String(alphabetArray).indexOf(charIsRead);
        int newIndex = (index + key) % alphabetArray.length;
        if (newIndex < 0) {
            newIndex += alphabetArray.length;
        }
        return alphabetArray[newIndex];
    }

    private char shiftDecrypt(char charIsRead, char[] alphabetArray, int key) {
        int index = new String(alphabetArray).indexOf(charIsRead);
        int newIndex = (index - key) % alphabetArray.length;
        if (newIndex < 0) {
            newIndex += alphabetArray.length;
        }
        return alphabetArray[newIndex];
    }

}
