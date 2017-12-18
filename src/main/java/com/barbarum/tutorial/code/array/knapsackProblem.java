package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

import java.util.Arrays;

public class knapsackProblem {

    public static int calculateMaximumBenefit(int[] weight, int[] benefit, int limit) {
        int dp[][] = new int[limit + 1][weight.length];

        fillDP(dp);

        return calculate(weight, benefit, 0, limit, dp);
    }

    private static void fillDP(int[][] dp) {
        for (int[] item : dp) {
            Arrays.fill(item, -1);
        }
    }

    private static int calculate(int[] weight, int[] benefit, int start, int limit, int[][] dp) {
        if (start == weight.length) {
            return 0;
        }
        if (dp[limit][start] != -1) {
            return dp[limit][start];
        }
        // if weight of current element is greater than limit, then skip it and only calculate excluded case.
        if (weight[start] > limit) {
            dp[limit][start] = calculate(weight, benefit, start + 1, limit, dp);
        } else {
            int maximumIfInclude = benefit[start] + calculate(weight, benefit, start + 1, limit - weight[start], dp);
            int maximumInExclude = calculate(weight, benefit, start + 1, limit, dp);
            dp[limit][start] = Math.max(maximumIfInclude, maximumInExclude);
        }

        return dp[limit][start];
    }

    public static void main(String args[]) {
        PrintUtil.println("Output->"
                , calculateMaximumBenefit(new int[]{2, 2, 4, 5}, new int[]{3, 7, 2, 9}, 10));
    }
}
