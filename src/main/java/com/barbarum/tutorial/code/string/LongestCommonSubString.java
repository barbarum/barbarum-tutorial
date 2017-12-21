package com.barbarum.tutorial.code.string;

import java.util.*;

public class LongestCommonSubString {

    public static List<String> findLongestCommonSubString(String a, String b) {
        if (a == null || a.length() == 0 || b == null || b.length() == 0) {
            return null;
        }

        int[][] firstMatchIndex = new int[a.length() + 1][b.length() + 1];
        initialize(firstMatchIndex);

        int length = calculateAndFindLongest(a, b, firstMatchIndex);

        return length == 0 ? Collections.emptyList() : convert(b, length, firstMatchIndex);
    }

    private static int calculateAndFindLongest(String a, String b, int[][] cache) {
        int longest = 0;
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    cache[i][j] = cache[i - 1][j - 1] != -1 ? cache[i - 1][j - 1] : j - 1;
                    longest = Math.max(longest, j - cache[i][j]);
                }
            }
        }
        return longest;
    }

    private static void initialize(int[][] firstMatchIndex) {
        for (int i = 0; i < firstMatchIndex.length; i++) {
            Arrays.fill(firstMatchIndex[i], -1);
        }
    }

    private static List<String> convert(String b, int length, int[][] cache) {
        Set<String> result = new HashSet<>();

        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[i].length; j++) {
                if (cache[i][j] != -1 && length == j - cache[i][j]) {
                    result.add(b.substring(cache[i][j], j));
                }
            }
        }

        return new ArrayList<>(result);
    }

    public static void main(String args[]) {
        System.out.println(findLongestCommonSubString("LCLC", "CLCL"));
    }
}
