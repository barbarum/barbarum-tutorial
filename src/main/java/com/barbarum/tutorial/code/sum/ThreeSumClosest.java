package com.barbarum.tutorial.code.sum;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/3sum-closest/description/
 */
public class ThreeSumClosest {

    public static int find3SumCloset(int[] nums, int target) {
        Arrays.sort(nums);

        int result = nums[0] + nums[1] + nums[nums.length - 1];

        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1;
            int end = nums.length - 1;

            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum < target) {
                    start++;
                } else if (sum > target) {
                    end--;
                } else {
                    return target;
                }
                if (Math.abs(target - sum) < Math.abs(target - result)) {
                    result = sum;
                }
            }
        }

        return result;
    }

    public static void main(String args[]) {
        System.out.println(find3SumCloset(new int[]{1, 1, 1, 0}, 100));
    }
}
