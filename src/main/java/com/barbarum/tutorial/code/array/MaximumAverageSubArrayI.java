package com.barbarum.tutorial.code.array;

/**
 * 643. https://leetcode.com/problems/maximum-average-subarray-i/description/
 * Note:
 * 1. 1 <= k <= n <= 30,000.
 * 2. Elements of the given array will be in the range [-10,000, 10,000].
 */

// DP
public class MaximumAverageSubArrayI {

    public static double findMaxAverage(int[] nums, int k) {
        int sumEndHere = sum(nums, 0, k - 1);
        int maximum = sumEndHere;

        for (int i = k; i < nums.length; i++) {
            sumEndHere = sumEndHere - nums[i - k] + nums[i];
            maximum = Math.max(maximum, sumEndHere);
        }
        return maximum / (k * 1.0);
    }

    private static int sum(int[] nums, int start, int end) {
        int sum = nums[start];
        for (int i = start + 1; i <= end; i++) {
            sum += nums[i];
        }
        return sum;
    }
}
