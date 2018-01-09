package com.barbarum.tutorial.code.array;

import com.barbarum.tutorial.util.PrintUtil;

public class FindPivotInSortedRotatedArray {


    /**
     * Binary Search
     */
    public static int findPivot(int[] num) {
        if (num == null || num.length == 0) {
            throw new IllegalArgumentException("");
        }

        if (num.length == 1 || num[0] < num[num.length - 1]) {
            return 0;
        }

        return findPivotIndex(num, 0, num.length - 1);
    }

    private static int findPivotIndex(int num[], int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;

        // if the mid element is pivot
        if (mid > start && num[mid - 1] > num[mid]) {
            return mid;
        }
        // if next element of the mid element is pivot
        if (mid < end && num[mid] > num[mid + 1]) {
            return mid + 1;
        }

        return num[start] >= num[mid] ? findPivotIndex(num, start, mid - 1) : findPivotIndex(num, mid + 1, end);
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
