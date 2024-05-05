package com.example.internetshopcar.helper;

public class Helper {
    public static String get10Words(String desc) {
        // Trim leading and trailing whitespace
        String trimmedDesc = desc.trim();

        // Split the description into words
        String[] words = trimmedDesc.split("\\s+");

        // Check if the description has more than 10 words
        if (words.length > 10) {
            // Build a string with the first 10 words
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                result.append(words[i]).append(" ");
            }
            result.append("...");
            return result.toString().trim();
        } else {
            // If the description has 10 or fewer words, return the original description
            return trimmedDesc;
        }
    }
}

