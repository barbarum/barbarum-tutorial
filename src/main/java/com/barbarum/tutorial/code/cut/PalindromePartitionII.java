package com.barbarum.tutorial.code.cut;

public class PalindromePartitionII {

    public static int getMinimumPalinedromePartition(String string) {
        if (string == null || string.isEmpty() || isPalinedrome(string, 0, string.length() - 1)) {
            return 0;
        }

        boolean[][] dp = new boolean[string.length()][string.length()];
        int[] result = new int[string.length()];

        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = true;
        }

        verifyPalinedrome(string, dp);
        calculateMiniumCuts(dp, result);

        return result[string.length() - 1];
    }

    private static void calculateMiniumCuts(boolean[][] dp, int[] result) {
        int start = 0;

        for (int end = 1; end < result.length; end++) {
            // if [start, end] is a palinedrome.
            if (dp[start][end]) {
                result[end] = 0;
                continue;
            }

            // if not;
            result[end] = Integer.MAX_VALUE;
            for (int k = end - 1; k >= 0; k--) {
                if (dp[k][end]) {
                    result[end] = Math.min(result[end], 1 + result[k]);
                }
            }
        }
    }

    private static boolean isPalinedrome(String string, int start, int end) {
        if (start > end) {
            return false;
        }

        while (start < end) {
            if (string.charAt(start++) != string.charAt(end--)) {
                return false;
            }
        }

        return true;
    }


    private static void verifyPalinedrome(String string, boolean[][] dp) {
        int maxLength = string.length();
        for (int len = 2; len <= maxLength; len++) {
            for (int start = 0; start <= maxLength - len; start++) {
                int end = start + len - 1;
                dp[start][end] = string.charAt(start) == string.charAt(end)
                        && (len == 2 || dp[start + 1][end - 1]);
            }
        }
    }

    public static void main(String args[]) {
        System.out.println(getMinimumPalinedromePartition("ababbbabbababa"));
    }
}
