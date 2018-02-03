package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

import java.util.Arrays;

public class knapsackProblem {

    public static int calculateMaximumBenefit(int[] weight, int[] benefit, int limit) {
        int dp[][] = new int[limit + 1][weight.length];
        fillDP(dp);
        return calculate(limit, 0, weight, benefit, dp);
    }

    private static int calculate(int limit, int start, int[] weight, int[] benefit, int[][] dp) {
        if (start == weight.length) {
            return 0;
        }
        if (dp[limit][start] != -1) {
            return dp[limit][start];
        }
        // if weight of current element is greater than limit, then skip it and only calculate excluded case.
        if (weight[start] > limit) {
            dp[limit][start] = calculate(limit, start + 1, weight, benefit, dp);
        } else {
            int maximumIfInclude = benefit[start] + calculate(limit - weight[start], start + 1, weight, benefit, dp);
            int maximumIfExclude = calculate(limit, start + 1, weight, benefit, dp);
            dp[limit][start] = Math.max(maximumIfInclude, maximumIfExclude);
        }

        return dp[limit][start];
    }

    private static void fillDP(int[][] dp) {
        for (int[] item : dp) {
            Arrays.fill(item, -1);
        }
    }

    public static void main(String args[]) {
        PrintUtil.println("Output->"
                , calculateMaximumBenefit(new int[]{2, 2, 4, 5}, new int[]{3, 7, 2, 9}, 10));
    }
}
