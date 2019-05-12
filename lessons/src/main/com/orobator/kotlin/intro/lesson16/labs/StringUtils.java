package com.orobator.kotlin.intro.lesson16.labs;

// Convert this class to Kotlin extensions
public class StringUtils {
    private StringUtils() {
    }

    public static boolean startsWithCapital(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        char firstChar = s.charAt(0);
        return Character.isUpperCase(firstChar);
    }

    public static boolean isEmailAddress(String s) {
        String shortestEmail = "a@b.c";
        if (s == null || s.length() < shortestEmail.length()) {
            return false;
        }

        return s.contains("@");
    }

    public static int countChar(String s, char c) {
        if (s == null) {
            return 0;
        }

        int occurrences = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                occurrences++;
            }
        }

        return occurrences;
    }
}
