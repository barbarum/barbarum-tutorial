package com.barbarum.tutorial.code.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PalindromePartition {

    //Palindrome
    public static List<List<String>> palindromePartition(String string) {
        if (string == null || string.isEmpty()) {
            return Collections.emptyList();
        }

        List<List<String>> result = new ArrayList<>();
        boolean[][] dp = new boolean[string.length()][string.length()];

        determinePalindrome(string, dp);
        fillResults(string, dp, 0, string.length() - 1, new ArrayList<>(), result);

        return result;
    }

    private static void determinePalindrome(String string, boolean[][] dp) {
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

    private static void fillResults(String string, boolean[][] dp, int start, int end, List<String> cache, List<List<String>> result) {
        // opt-out
        if (start > end) {
            result.add(new ArrayList<>(cache));
            return;
        }

        // loop index (end), find previous palindrome start.
        for (int i = start; i <= end; i++) {
            if (dp[start][i]) {
                cache.add(string.substring(start, i + 1));
                fillResults(string, dp, i + 1, end, cache, result);
                cache.remove(cache.size() - 1);
            }
        }
    }

    public static void main(String args[]) {
        System.out.println(palindromePartition("aab"));
    }
}
