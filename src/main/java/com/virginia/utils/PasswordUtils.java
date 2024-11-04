package com.virginia.utils;

import java.security.SecureRandom;

/**
 *Generate random password
 *
 * @author Virginia
 */

public class PasswordUtils {
    // Define the password character set, including uppercase letters, lowercase letters, and numbers
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    // Generate a random password with its length between 6 and 16
    public static String generateRandomPassword() {
        // random password length, between 6 and 16
        int length = 6 + RANDOM.nextInt(11); // 6 + (random digit between 6 and 10)

        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }

        return password.toString();
    }
}
