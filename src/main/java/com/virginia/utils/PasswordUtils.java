package com.virginia.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 *Generate random password
 *
 * @author Virginia
 */
@Component
public class PasswordUtils {
    // Define the password character set, including uppercase letters, lowercase letters, and numbers
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String ALL_CHARS = UPPERCASE + LOWERCASE + DIGITS;

    // Improve randomness and security with secure random
    private static final SecureRandom random = new SecureRandom();

    /**
     *Generate random passwords within the specified length range
     *
     * @param minLength minimum length (inclusive)
     * @param maxLength maximum length (inclusive)
     * @return generated random password
     * @throws IllegalArgumentException if minLength or maxLength is invalid
     */
    public static String generateRandomPassword(int minLength, int maxLength) {
        if (minLength < 6 || maxLength > 16 || minLength > maxLength) {
            throw new IllegalArgumentException("密码长度必须在6到16之间，且最小长度不能大于最大长度。");
        }

        // Randomly determine password length
        int length = random.nextInt(maxLength - minLength + 1) + minLength;

        StringBuilder password = new StringBuilder(length);

        // Make sure your password contains at least one uppercase letter, one lowercase letter, and one number
        password.append(getRandomChar(UPPERCASE));
        password.append(getRandomChar(LOWERCASE));
        password.append(getRandomChar(DIGITS));

        // Fill in remaining characters
        for (int i = 3; i < length; i++) {
            password.append(getRandomChar(ALL_CHARS));
        }

        // Shuffle character order to ensure randomness
        return shuffleString(password.toString());
    }

    /**
     *Randomly select a character from the given character set
     *
     * @param chars character set
     * @return randomly selected characters
     */
    private static char getRandomChar(String chars) {
        int index = random.nextInt(chars.length());
        return chars.charAt(index);
    }

    /**
     *Shuffle the order of characters in the string
     *
     * @param input original string
     * @return shuffled string
     */
    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            // swap characters
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }
        return new String(characters);
    }

}
