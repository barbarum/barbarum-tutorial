package com.barbarum.tutorial.code.string;

public class PalindromicMinimumCut {

    public static int findMinimumCut(String s) {
        int length;
        if (s == null || (length = s.length()) <= 1) {
            return 0;
        }

        boolean dp[][] = new boolean[length][length];
        int minimum[] = new int[length];

        determinePalindromicSubString(s, dp);
        calculateMinimumCut(s, dp, minimum);

        return minimum[length - 1];
    }

    private static void determinePalindromicSubString(String s, boolean[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = true;
        }
        for (int i = 1; i < dp.length; i++) {
            dp[i - 1][i] = s.charAt(i - 1) == s.charAt(i);
        }
        for (int length = 3; length <= s.length(); length++) {
            for (int start = 0; start <= s.length() - length; start++) {
                int end = start + length - 1;
                dp[start][end] = s.charAt(start) == s.charAt(end) && dp[start + 1][end - 1];
            }
        }
    }

    private static void calculateMinimumCut(String s, boolean[][] dp, int[] minimum) {
        minimum[0] = 0;
        minimum[1] = 0;

        for (int end = 2; end < s.length(); end++) {
            if (dp[0][end]) {
                minimum[end] = 0;
                continue;
            }
            minimum[end] = s.length() + 1;
            for (int start = end; start > 0; start--) {
                if (dp[start][end]) {
                    minimum[end] = Math.min(minimum[end], 1 + minimum[start - 1]);
                }
            }
        }
    }

    public static void main(String args[]) {
        String text = "ababbbabbababa";
        System.out.println(String.format("Minimum Cuts '%s'-> %s", text, PalindromicMinimumCut.findMinimumCut(text)));
    }
}
