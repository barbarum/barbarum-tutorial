package com.barbarum.tutorial.util;

public class BasicUtil {


    /**
     * Checks if an string is not null, and has at least one character.
     *
     * @param item the given string.
     * @return true if the string is not null and has at least one character, otherwise false.
     */
    public static boolean hasContent(String item) {
        return item != null && !item.isEmpty();
    }
}
