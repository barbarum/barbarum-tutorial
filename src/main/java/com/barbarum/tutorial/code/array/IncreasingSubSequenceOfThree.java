package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

public class IncreasingSubSequenceOfThree {

    public static void findSequence(int nums[], int result[]) {
        int maximum = 0;


        for (int i = 1; i < nums.length - 1; i++) {
            int left = findLargestSmallerInLeft(nums, nums[i], i - 1);
            if (left < 0) {
                continue;
            }

            int right = findLargestGreaterInRight(nums, nums[i], i + 1);
            if (right < 0) {
                continue;
            }

            int product = nums[left] * nums[i] * nums[right];
            if (product > maximum) {
                maximum = product;
                result[0] = nums[left];
                result[1] = nums[i];
                result[2] = nums[right];
            }
        }
    }

    private static int findLargestGreaterInRight(int[] nums, int target, int start) {
        int max = 0;
        int result = -1;

        for (int i = start; i < nums.length; i++) {
            if (nums[i] > target && nums[i] > max) {
                max = nums[i];
                result = i;
            }
        }

        return result;
    }

    private static int findLargestSmallerInLeft(int[] nums, int target, int end) {
        int max = 0;
        int result = -1;

        for (int i = 0; i <= end; i++) {
            if (nums[i] < target && nums[i] > max) {
                max = nums[i];
                result = i;
            }
        }

        return result;
    }

    public static void main(String args[]) {
        int result[] = new int[3];
        findSequence(new int[]{6, 1, 2, 3, 19, 10, 7}, result);
        PrintUtil.println("Output -> ", result);
    }
}
