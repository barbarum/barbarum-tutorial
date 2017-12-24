package com.barbarum.tutorial.code.string;

import java.text.MessageFormat;
import java.util.Arrays;


public class MinimumCut {

    public static int cut(String text, int k) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        int[] dp = new int[text.length() + 1];
        Arrays.fill(dp, -1);

        dp[0] = 0;

        fillMinimumCuts(text, k, dp);

        return dp[text.length()];
    }

    private static void fillMinimumCuts(String text, int k, int[] dp) {
        int maximum = maximumPossibleCuts(k);

        for (int subLength = 1; subLength <= text.length(); subLength++) {
            int cuts = Math.max(1, dp[subLength - 1]);
            for (; cuts <= maximum; cuts++) {
                if (actualCharacters(subLength, cuts) <= maximumOfCharacters(k, cuts)) {
                    dp[subLength] = cuts;
                    break;
                }
            }
            if (cuts > maximum) {
                // This could not be reached if it's possible to cut.
                break;
            }
        }
    }

    private static int maximumPossibleCuts(int k) {
        int length = (k - 3 - 1) / 2;
        return (int) Math.pow(10, length) - 1;
    }

    private static int maximumOfCharacters(int k, int numOfCuts) {
        return numOfCuts * k;
    }

    private static int actualCharacters(int length, int numOfCuts) {
        // 1 + 3 + 1 + 1 = 6
        return length + 3 * numOfCuts + numOfCuts * length(numOfCuts) + sum(numOfCuts);
    }

    private static int sum(int n) { // 1
        int length = length(n); // 1
        int temp = length; // 1
        int result = length * n; // 1

        while ((temp--) > 0) { // temp = 0
            result -= (Math.pow(10, temp) - 1); // 1
        }

        return result; // 1
    }

    private static int length(int n) {
        int num = n;
        int bit = 0;

        while (num != 0) {
            num = num / 10;
            bit++;
        }

        return bit;
    }

    public static void main(String args[]) {
        String text = "That is a cat and this is a dog";
        int k = 8;
        int cuts = cut(text, k);

        System.out.println("Num of cuts: " + cuts);
        printCutDetail(text, cuts, k);
    }

    private static void printCutDetail(String s, int numOfCuts, int k) {
        int start = 0;
        for (int i = 1; i <= numOfCuts; i++) {
            int subLength = k - 3 - length(numOfCuts) - length(i);
            String subString = s.substring(start, Math.min(s.length(), start + subLength));
            System.out.println(MessageFormat.format("{0}({1}/{2})", subString, i, numOfCuts));
            start = start + subLength;
        }
    }
}
