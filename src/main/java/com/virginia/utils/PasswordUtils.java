package com.virginia.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * 生成随机密码
 *
 * @author Virginia
 */
@Component
public class PasswordUtils {
    // 定义密码字符集，包括大写字母、小写字母和数字
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String ALL_CHARS = UPPERCASE + LOWERCASE + DIGITS;

    // 使用SecureRandom提高随机性和安全性
    private static final SecureRandom random = new SecureRandom();

    /**
     * 生成指定长度范围内的随机密码
     *
     * @param minLength 最小长度（包括）
     * @param maxLength 最大长度（包括）
     * @return 生成的随机密码
     * @throws IllegalArgumentException 如果minLength或maxLength无效
     */
    public static String generateRandomPassword(int minLength, int maxLength) {
        if (minLength < 6 || maxLength > 16 || minLength > maxLength) {
            throw new IllegalArgumentException("密码长度必须在6到16之间，且最小长度不能大于最大长度。");
        }

        // 随机确定密码长度
        int length = random.nextInt(maxLength - minLength + 1) + minLength;

        StringBuilder password = new StringBuilder(length);

        // 确保密码包含至少一个大写字母、一个小写字母和一个数字
        password.append(getRandomChar(UPPERCASE));
        password.append(getRandomChar(LOWERCASE));
        password.append(getRandomChar(DIGITS));

        // 填充剩余的字符
        for (int i = 3; i < length; i++) {
            password.append(getRandomChar(ALL_CHARS));
        }

        // 打乱字符顺序以确保随机性
        return shuffleString(password.toString());
    }

    /**
     * 从给定的字符集中随机选择一个字符
     *
     * @param chars 字符集
     * @return 随机选择的字符
     */
    private static char getRandomChar(String chars) {
        int index = random.nextInt(chars.length());
        return chars.charAt(index);
    }

    /**
     * 打乱字符串中字符的顺序
     *
     * @param input 原始字符串
     * @return 打乱后的字符串
     */
    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            // 交换字符
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }
        return new String(characters);
    }

}
