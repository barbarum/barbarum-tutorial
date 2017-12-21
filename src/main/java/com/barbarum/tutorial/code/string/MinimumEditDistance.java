package com.barbarum.tutorial.code.string;

public class MinimumEditDistance {

    /**
     * http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
     * <p>
     * Given two strings str1 and str2 and below operations that can performed on str1. Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.
     * <p>
     * Insert
     * Remove
     * Replace
     * All of the above operations are of equal cost.
     * <p>
     * Input:   str1 = "geek", str2 = "gesek"
     * Output:  1
     * We can convert str1 into str2 by inserting a 's'.
     * <p>
     * Input:   str1 = "cat", str2 = "cut"
     * Output:  1
     * We can convert str1 into str2 by replacing 'a' with 'u'.
     * <p>
     * Input:   str1 = "sunday", str2 = "saturday"
     * Output:  3
     * Last three and first characters are same.  We basically
     * need to convert "un" to "atur".  This can be done using
     * below three operations.
     * Replace 'n' with 'r', insert t, insert a
     */

    public static int findMinimumEditDistance(String a, String b) {
        if (a == null) {
            return b == null ? 0 : b.length();
        }
        if (b == null) {
            return a.length();
        }

        int aLen = a.length();
        int bLen = b.length();

        int[][] cache = new int[aLen + 1][bLen + 1];

        findMinimumEditDistance(a, b, aLen, bLen, cache);

        return cache[aLen][bLen];
    }

    private static void findMinimumEditDistance(String a, String b, int aLen, int bLen, int[][] cache) {
        for (int i = 0; i <= aLen; i++) {
            cache[i][0] = i;
        }

        for (int j = 0; j <= bLen; j++) {
            cache[0][j] = j;
        }

        for (int i = 1; i <= aLen; i++) {
            for (int j = 1; j <= bLen; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    cache[i][j] = cache[i - 1][j - 1];
                } else {
                    cache[i][j] = 1 + minimum(cache[i - 1][j], cache[i][j - 1], cache[i - 1][j - 1]);
                }
            }
        }
    }

    private static int minimum(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public static void main(String args[]) {
        System.out.println(findMinimumEditDistance("sunday", "saturday"));
    }
}
