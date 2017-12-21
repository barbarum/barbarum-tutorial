package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

import static com.barbarum.tutorial.util.InputUtil.swap;

public class NextGreaterNumberUsingSameDigits {


    public static void findNextGreaterNumber(int[] nums) {

        int pivot = findPivot(nums);

        if (pivot == -1) {
            return;
        }

        int swapIndex = findSwapIndex(nums, nums[pivot], pivot + 1, nums.length - 1);

        swap(nums, pivot, swapIndex);

        reverse(nums, pivot + 1, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }


    private static int findPivot(int nums[]) {
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    private static int findSwapIndex(int nums[], int target, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (target > nums[i]) {
                return i - 1;
            }
        }
        return end;
    }


    public static void main(String args[]) {
        int nums[] = new int[]{6, 9, 3, 8, 6, 5, 2};

        PrintUtil.println("Input -> ", nums);
        findNextGreaterNumber(nums);

        PrintUtil.println("Output ->", nums);
    }
}
