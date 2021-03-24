package com.lonewolf.backend.util;

public class StringExtensions {
    private StringExtensions() {
    }

    public static String capitalize(String message) {
        StringBuilder messageBuilder = new StringBuilder();
        String[] words = message.trim().toLowerCase().split(" ");

        for (String word : words) {
            if (!word.isEmpty()) {
                messageBuilder.append(String.valueOf(word.charAt(0)).toUpperCase()).append(word.substring(1)).append(" ");
            }
        }

        return messageBuilder.toString().trim();
    }
}
