package ru.baranova.NauJava.utils;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?";

    public static String generateRandomPassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALLOWED_CHARACTERS.length());
            password.append(ALLOWED_CHARACTERS.charAt(index));
        }
        
        return password.toString();
    }

}