package com.barbarum.tutorial.code.string;

import java.util.*;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/description/
 * <p>
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example:
 * <p>
 * Input: "babad"
 * <p>
 * Output: "bab"
 * <p>
 * Note: "aba" is also a valid answer.
 * Example:
 * <p>
 * Input: "cbbd"
 * <p>
 * Output: "bb"
 */
public class LongestPalindromicSubstring {


    public static List<String> findLongestPalindromicSubString(String s) {
        int length = 0;
        if (s == null || (length = s.length()) == 0) {
            return Collections.emptyList();
        }
        if (s.length() == 1) {
            return Arrays.asList(s);
        }

        boolean dp[][] = new boolean[length][length];
        int distance = determinePalindromicSubString(s, dp);
        return convert(s, distance, dp);
    }

    private static int determinePalindromicSubString(String s, boolean[][] dp) {
        int distance = 1;
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        for (int i = 1; i < s.length(); i++) {
            dp[i - 1][i] = s.charAt(i - 1) == s.charAt(i);
            if (dp[i - 1][i]) {
                distance = 2;
            }
        }
        for (int length = 3; length <= s.length(); length++) {
            for (int start = 0; start <= s.length() - length; start++) {
                int end = start + length - 1;
                if (s.charAt(start) == s.charAt(end) && dp[start + 1][end - 1]) {
                    dp[start][end] = true;
                    distance = length;
                }
            }
        }
        return distance;
    }

    private static List<String> convert(String s, int distance, boolean[][] dp) {
        Set<String> result = new HashSet<>();
        for (int i = distance; i <= s.length(); i++) {
            if (dp[i - distance][i - 1]) {
                result.add(s.substring(i - distance, i));
            }
        }
        return new ArrayList<>(result);
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return null;
        }
        if (s.length() == 1) {
            return s;
        }

        int minStart = 0;
        int maxEnd = 1;
        char[] charString = s.toCharArray();

        for (int i = 1; i < charString.length; i++) {
            if (charString[i] == charString[i - 1]) {
                // Find the depth
                int depth = findDepth(charString, i - 1, i);
                if ((maxEnd - minStart) < depth * 2) {
                    minStart = i - depth;
                    maxEnd = i + depth;
                }
            }
            if (i + 1 < charString.length && charString[i - 1] == charString[i + 1]) {
                // Find the depth
                int depth = findDepth(charString, i, i);
                if (maxEnd - minStart < depth * 2 - 1) {
                    minStart = i - depth + 1;
                    maxEnd = i + depth;
                }
            }
        }
        return s.substring(minStart, maxEnd);
    }

    private static int findDepth(char[] charString, int back, int forward) {
        int depth = 0;
        int start = back;
        int end = forward;

        while (start >= 0 && end < charString.length && charString[start--] == charString[end++]) {
            depth++;
        }

        return depth;
    }


    public static void main(String args[]) {
        System.out.println(findLongestPalindromicSubString("banana"));
    }
}
