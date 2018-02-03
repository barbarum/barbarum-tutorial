package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

import java.util.Arrays;
import java.util.TreeSet;

public class FindIncreasingSubSequenceOfThree {

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

    public static void findSequenceV2(int nums[], int result[]) {
        if (nums == null || nums.length < 3) {
            return;
        }

        int[] leftFloor = new int[nums.length];
        int[] rightLargest = new int[nums.length];

        initialize(leftFloor);
        initialize(rightLargest);

        calculateLeftFloor(nums, leftFloor);
        calculateRightLargest(nums, rightLargest);

        calculateResult(nums, leftFloor, rightLargest, result);
    }

    private static void calculateResult(int[] nums, int[] leftFloor, int[] rightLargest, int[] result) {
        int maximum = 0;

        for (int i = 0; i < nums.length; i++) {
            if (leftFloor[i] == -1 || rightLargest[i] == -1) {
                continue;
            }
            int product = leftFloor[i] * nums[i] * rightLargest[i];
            if (maximum > product) {
                maximum = product;
                result[0] = leftFloor[i];
                result[1] = nums[i];
                result[2] = rightLargest[i];
            }
        }
    }

    private static void initialize(int[] cache) {
        Arrays.fill(cache, -1);
    }

    private static void calculateRightLargest(int[] nums, int[] result) {
        result[nums.length - 1] = -1;
        int maximum = result[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < maximum) {
                result[i] = maximum;
            } else {
                result[i] = -1;
                maximum = result[i];
            }
        }
    }

    private static void calculateLeftFloor(int[] nums, int[] result) {
        TreeSet<Integer> cache = new TreeSet<>();
        cache.add(nums[0]);
        result[0] = -1;

        for (int i = 1; i < nums.length; i++) {
            Integer item = cache.floor(nums[i]);
            result[i] = item != null ? item : -1;
            cache.add(nums[i]);
        }
    }

    public static void main(String args[]) {
        int result[] = new int[3];
        findSequence(new int[]{6, 1, 2, 3, 19, 10, 7}, result);
        PrintUtil.println("[findSequence] Output -> ", result);

        findSequenceV2(new int[]{6, 1, 2, 3, 19, 10, 7}, result);
        PrintUtil.println("[findSequenceV2] Output -> ", result);
    }
}
