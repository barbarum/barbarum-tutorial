package com.barbarum.tutorial.code.array;

public class MaximumSubArray {

    // DP solution
    public static int findMaximumSubArraySum(int[] nums) {
        int maxEndingHere = nums[0];
        int maximum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
            maximum = Math.max(maximum, maxEndingHere);
        }

        return maximum;
    }


    public static void main(String args[]) {
        System.out.println(findMaximumSubArraySum(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
