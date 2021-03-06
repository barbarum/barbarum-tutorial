package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

public class FindLongestIncreasingSubSequence {

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
        int longest = dp[nums.length - 1];

        for (int i = nums.length - 2; i >= 0; i--) {
            dp[i] = findLongest(nums, nums[i], i + 1, dp);
            longest = Math.max(dp[i], longest);
        }

        return longest;
    }

    private static int findLongest(int[] nums, int target, int start, int[] dp) {
        int longest = 0;
        for (int i = start; i < nums.length; i++) {// Some duplication work?
            if (nums[i] > target) {
                longest = Math.max(longest, dp[i]);
            }
        }
        return 1 + longest;
    }

    /**
     * http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
     */
    public static int findV2(int[] num) {
        // Store the tail element index of each list.
        int[] table = new int[num.length];
        int len;

        table[0] = num[0];
        len = 1;

        for (int i = 1; i < num.length; i++) {
            if (num[i] < table[0]) {
                table[0] = num[i];
            } else if (num[i] > table[len - 1]) {
                table[len++] = num[i];
            } else {
                table[binarySearch(table, num[i], len - 1)] = num[i];
            }
        }

        return len;
    }

    private static int binarySearch(int[] table, int target, int end) {
        int low = 0;
        int high = end;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (table[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    public static void main(String args[]) {
        PrintUtil.println(new int[]{12, 18, 7, 34, 30, 28, 90, 88}, FindLongestIncreasingSubSequence::find);
        PrintUtil.println(new int[]{-99, 12, 18, 7, 34, 30, 28, 90, 88}, FindLongestIncreasingSubSequence::find);
        PrintUtil.println(new int[]{12, 18, 7, 34, 30, 28, 90, 88}, FindLongestIncreasingSubSequence::findV2);
        PrintUtil.println(new int[]{-99, 12, 18, 7, 34, 30, 28, 90, 88}, FindLongestIncreasingSubSequence::findV2);
    }
}
