package com.barbarum.tutorial.leetcode;

public class PalindromicSubstring {


    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return null;
        }
        if (s.length() == 1) {
            return s;
        }

        int minStart = 0, maxEnd = 1;
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
        int depth = 0, start = back, end = forward;

        while (start >= 0 && end < charString.length && charString[start--] == charString[end++]) {
            depth++;
        }

        return depth;
    }


}
