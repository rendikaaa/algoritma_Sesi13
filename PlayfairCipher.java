public class PlayfairCipher {
    private static char[][] generateKeyMatrix(String key) {
        boolean[] usedChars = new boolean[26];
        char[][] matrix = new char[5][5];
        int row = 0, col = 0;
        for (int i = 0; i < key.length(); i++) {
            char ch = Character.toUpperCase(key.charAt(i));
            if (Character.isLetter(ch) && !usedChars[ch - 'A']) {
                matrix[row][col] = ch;
                usedChars[ch - 'A'] = true;
                col++;
                if (col == 5) {
                    row++;
                    col = 0;
                }
            }
        }
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (ch != 'J' && !usedChars[ch - 'A']) {
                matrix[row][col] = ch;
                col++;
                if (col == 5) {
                    row++;
                    col = 0;
                }
            }
        }
        return matrix;
    }

    public static String encrypt(String plaintext, String key) {
        char[][] matrix = generateKeyMatrix(key);
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i += 2) {
            char ch1 = Character.toUpperCase(plaintext.charAt(i));
            char ch2 = (i + 1 < plaintext.length()) ? Character.toUpperCase(plaintext.charAt(i + 1)) : 'X';
            if (!Character.isLetter(ch1) || !Character.isLetter(ch2)) {
                continue;
            }
            int row1 = -1, col1 = -1, row2 = -1, col2 = -1;
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (matrix[j][k] == ch1) {
                        row1 = j;
                        col1 = k;
                    } else if (matrix[j][k] == ch2) {
                        row2 = j;
                        col2 = k;
                    }
                }
            }
            if (row1 == -1 || col1 == -1 || row2 == -1 || col2 == -1) {
                continue;
            }
            char encryptedChar1, encryptedChar2;
            if (row1 == row2) {
                encryptedChar1 = matrix[row1][(col1 + 1) % 5];
                encryptedChar2 = matrix[row2][(col2 + 1) % 5];
            } else if (col1 == col2) {
                encryptedChar1 = matrix[(row1 + 1) % 5][col1];
                encryptedChar2 = matrix[(row2 + 1) % 5][col2];
            } else {
                encryptedChar1 = matrix[row1][col2];
                encryptedChar2 = matrix[row2][col1];
            }
            ciphertext.append(encryptedChar1).append(encryptedChar2);
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