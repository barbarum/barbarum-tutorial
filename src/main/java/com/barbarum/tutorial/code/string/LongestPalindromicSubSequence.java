package com.barbarum.tutorial.code.string;

public class LongestPalindromicSubSequence {


    public static int findLongestSubSequenceCount(String str) {
        int length = 0;
        if (str == null || (length = str.length()) <= 1) {
            return length;
        }

        int dp[][] = new int[length][length];

        fillLongestSubSequenceCount(str, dp);

        return dp[0][length - 1];
    }

    private static void fillLongestSubSequenceCount(String str, int[][] dp) {
        for (int i = 0; i < str.length(); i++) {
            dp[i][i] = 1;
        }
        for (int i = 1; i < str.length(); i++) {
            dp[i - 1][i] = str.charAt(i) == str.charAt(i - 1) ? 2 : 1;
        }

        for (int length = 3; length <= str.length(); length++) {
            for (int start = 0; start <= str.length() - length; start++) {
                int end = start + length - 1;
                if (str.charAt(start) == str.charAt(end)) {
                    dp[start][end] = dp[start + 1][end - 1] + 2;
                } else {
                    dp[start][end] = Math.max(dp[start + 1][end], dp[start][end - 1]);
                }
            }

        }
    }

    public static void main(String args[]) {
        System.out.println(findLongestSubSequenceCount("LPSSAPAL"));
    }
}
