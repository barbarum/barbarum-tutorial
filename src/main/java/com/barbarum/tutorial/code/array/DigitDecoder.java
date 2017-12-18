package com.barbarum.tutorial.code.array;

public class DigitDecoder {

    public static int decode(String digit) {
        int length = digit.length();
        int dp[] = new int[length + 1];

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= length; i++) {
            if (digit.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }

            if (digit.charAt(i - 2) == '1' || (digit.charAt(i - 2) == '2' && digit.charAt(i - 1) < '7')) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[length];
    }
}
