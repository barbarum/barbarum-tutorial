package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

public class FindLongestBitonicSubSequence {

    public static int find(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }

        int len = nums.length;
        int increase[] = new int[len];
        int decrease[] = new int[len];

        increase[0] = 1;
        decrease[len - 1] = 1;


        findIncreaseMaximum(nums, increase);
        findDecreaseMaximum(nums, decrease);

        return calculateMaximum(increase, decrease);
    }

    private static void findIncreaseMaximum(int[] nums, int[] dp) {
        for (int i = 1; i < nums.length; i++) {
            dp[i] = findIncreaseMaximumInBackward(nums, nums[i], i - 1, dp);
        }
    }

    private static int findIncreaseMaximumInBackward(int[] nums, int target, int end, int[] dp) {
        int max = 0;
        for (int i = end; i >= 0; i--) {
            if (nums[i] < target) {
                max = Math.max(max, dp[i]);
            }
        }
        return 1 + max;
    }


    private static void findDecreaseMaximum(int[] nums, int[] decrease) {
        for (int i = nums.length - 2; i >= 0; i--) {
            decrease[i] = findDecreaseMaximumInForward(nums, nums[i], i + 1, decrease);
        }
    }

    private static int findDecreaseMaximumInForward(int[] nums, int target, int start, int[] decrease) {
        int max = 0;

        for (int i = start; i < nums.length; i++) {
            if (nums[i] < target) {
                max = Math.max(max, decrease[i]);
            }
        }

        return 1 + max;
    }


    private static int calculateMaximum(int[] increase, int[] decrease) {
        int max = 0;
        for (int i = 0; i < increase.length; i++) {
            max = Math.max(max, increase[i] + decrease[i] - 1);
        }
        return max;
    }


    public static void main(String args[]) {
        PrintUtil.println(new int[]{12, 18, 7, 34, 30, 28, 90, 88}, FindLongestBitonicSubSequence::find);
    }

}
