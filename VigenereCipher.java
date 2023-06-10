public class VigenereCipher {
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        int keyLength = key.length();
        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);
            if (Character.isLetter(ch)) {
                char encryptedChar = (char) (((ch - 'A' + key.charAt(i % keyLength) - 'A') % 26) + 'A');
                ciphertext.append(encryptedChar);
            } else {
                ciphertext.append(ch);
            }
        }
        return ciphertext.toString();
    }

    public static void main(String[] args) {
        String plaintext = "HELLO WORLD";
        String key = "KEY";
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Plaintext: " + plaintext);
        System.out.println("Ciphertext: " + ciphertext);
    }
}