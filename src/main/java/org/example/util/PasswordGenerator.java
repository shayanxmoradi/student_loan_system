package org.example.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PasswordGenerator {
    public static String passowrd;

    public static String passwordGenerator() {
        int lenght = 8;
        Random rand = new Random();

        StringBuilder password = new StringBuilder(lenght);


        char[] specialChars = {'&', '%', '$', '#', '@'};


        password.append((char)rand.nextInt(97, 122));
        password.append((char)rand.nextInt(65, 90));
        password.append( (char)rand.nextInt(49, 57));
        password.append(specialChars[rand.nextInt(specialChars.length)]);
        String allChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789" + new String(specialChars);
        for (int i = 4; i < lenght; i++) {
            password.append(allChars.charAt(rand.nextInt(allChars.length())));
        }
        return shuffleString(password.toString());
    }

    public static String shuffleString(String input) {
        List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }

        Collections.shuffle(characters);

        StringBuilder shuffledString = new StringBuilder();
        for (char c : characters) {
            shuffledString.append(c);
        }

        return shuffledString.toString();
    }
}
