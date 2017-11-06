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


    /**
     * Checks if an integer array is not null, and has at least one integer.
     *
     * @param item the given integer array.
     * @return true if an integer array is not null, and has at least one integer., otherwise false.
     */
    public static boolean hasContent(int[] item) {
        return item != null && item.length > 0;
    }


    /**
     * Check if the given index is in the range of the given data array.
     *
     * @param data  the given int array.
     * @param index the given index.
     * @return true if it is, otherwise false.
     */
    public static boolean inRangeOf(int[] data, int index) {
        return index >= 0 && index < data.length;
    }
}
