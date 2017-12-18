package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

public class LongestIncreasingSubSequence {

    public static int find(int[] nums) {
        int len;

        if (nums == null || (len = nums.length) == 0) {
            return 0;
        }
        if (len == 1) {
            return 1;
        }

        int[] dp = new int[len];
        dp[len - 1] = 1;

        return findInBackward(nums, dp);

    }


    private static int findInBackward(int[] nums, int[] dp) {
        int totalMax = dp[nums.length - 1];

        for (int i = nums.length - 2; i >= 0; i--) {
            int target = nums[i];
            int max = findMax(nums, target, i + 1, dp);

            dp[i] = max;
            totalMax = Math.max(max, totalMax);
        }

        return totalMax;
    }

    private static int findMax(int[] nums, int target, int start, int[] dp) {
        int max = 0;
        for (int i = start; i < nums.length; i++) {// Some duplications
            if (nums[i] > target) {
                max = Math.max(max, dp[i]);
            }
        }
        return 1 + max;
    }

    public static int findV2(int[] nums) {
        // Store the tail element index of each list.
        int[] table = new int[nums.length];
        int len;

        table[0] = nums[0];
        len = 1;


        for (int i = 1; i < nums.length; i++) {

            if (nums[i] < table[0]) {
                table[0] = nums[i];
            } else if (nums[i] > table[len - 1]) {
                table[len++] = nums[i];
            } else {
                table[binarySearch(table, nums[i], len - 1)] = nums[i];
            }
        }

        return len;
    }

    private static int binarySearch(int[] table, int num, int end) {
        int low = 0;
        int high = end;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (table[mid] < num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    public static void main(String args[]) {
        PrintUtil.println(new int[]{12, 18, 7, 34, 30, 28, 90, 88}, LongestIncreasingSubSequence::findV2);
        PrintUtil.println(new int[]{-99, 12, 18, 7, 34, 30, 28, 90, 88}, LongestIncreasingSubSequence::findV2);

//        LIS(new int[]{-99, 12, 18, 7, 34, 30, 28, 90, 88});
    }
}
