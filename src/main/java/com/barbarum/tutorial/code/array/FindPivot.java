package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

public class FindPivot {


    /**
     * Binary Search
     */
    public static int findPivot(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("");
        }

        if (nums.length == 1 || nums[0] < nums[nums.length - 1]) {
            return 0;
        }

        return findPivotIndex(nums, 0, nums.length - 1);
    }

    private static int findPivotIndex(int nums[], int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;

        // if the mid element is pivot
        if (mid > start && nums[mid - 1] > nums[mid]) {
            return mid;
        }
        // if next element of the mid element is pivot
        if (mid < end && nums[mid] > nums[mid + 1]) {
            return mid + 1;
        }

        return nums[start] >= nums[mid] ? findPivotIndex(nums, start, mid - 1) : findPivotIndex(nums, mid + 1, end);
    }


    public static void main(String args[]) {
        int nums[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        for (int num : nums) {
            ArrayRotate.rotate(nums, 1);
            PrintUtil.println("Input -> ", nums);
            System.out.println(String.format("Output -> %s", findPivot(nums)));
        }
    }
    
}
