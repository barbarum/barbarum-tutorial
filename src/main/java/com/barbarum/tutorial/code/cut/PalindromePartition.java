package com.barbarum.tutorial.code.cut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PalindromePartition {

    //Palindrome
    public static List<List<String>> palindromePartition(String string) {
        if (string == null || string.isEmpty()) {
            return Collections.emptyList();
        }

        List<List<String>> result = new ArrayList<>();
        boolean[][] dp = new boolean[string.length()][string.length()];

        fillPalinedrome(string, dp);

        fillResults(string, dp, string.length() - 1, new LinkedList<>(), result);

        return result;
    }

    private static void fillPalinedrome(String string, boolean[][] dp) {
        for (int i = 0; i < string.length(); i++) {
            dp[i][i] = true;
        }

        for (int len = 2; len <= string.length(); len++) {
            for (int start = 0; start <= string.length() - len; start++) {
                int end = start + len - 1;
                dp[start][end] = string.charAt(start) == string.charAt(end)
                        && (len == 2 || dp[start + 1][end - 1]);
            }
        }
    }

    private static void fillResults(String string, boolean[][] dp, int end, LinkedList<String> suffix, List<List<String>> result) {
        // opt-out
        if (end < 0) {
            result.add(suffix);
            return;
        }

        // loop index (end), find previous palindrome start.
        for (int start = 0; start <= end; start++) {
            if (dp[start][end]) {
                LinkedList<String> newSuffix = new LinkedList<>(suffix);
                newSuffix.addFirst(string.substring(start, end + 1));
                fillResults(string, dp, start - 1, newSuffix, result);

            }
        }
    }

    public static void main(String args[]) {
        System.out.println(palindromePartition("aab"));
    }
}
